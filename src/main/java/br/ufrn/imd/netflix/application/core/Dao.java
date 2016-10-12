package br.ufrn.imd.netflix.application.core;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Classe Genérica para realizar ações com banco de dados.
 * @author robertodantas
 *
 * @param <T>
 */
public class Dao<T extends Model> {
	
	private final Class<T> entityClass;
	
	public Dao(Class<T> clazz) {
		entityClass = clazz;
		
	}
		 
	protected SessionFactory getSessionFactory(){
		return Hibernate.getSessionFactory();
	}
	
	protected Session getCurrentSession(){
		return Hibernate.getSessionFactory().getCurrentSession();
	}
	
	public T findById(Integer id) throws NoResultException {
		Transaction tx = getCurrentSession().beginTransaction();
		T obj = null;
		try{
			obj = getCurrentSession().find(entityClass, id);
			tx.commit();
		}
		catch (Exception e){
			tx.rollback();
		}
		
		return obj;
	}
	
	public T queryForOne(String query, Object ... params) throws NoResultException{
		
		Transaction tx = getCurrentSession().beginTransaction();
		
		Query<T> q =  getCurrentSession().createQuery(query, entityClass);
		
		for (int i = 0; i < params.length; i++){
			q.setParameter(i, params[i]);
		}
		
		T obj = null;
		
		try{
			obj = q.getSingleResult();
			tx.commit();
		}
		catch (Exception e){
			tx.rollback();
			throw e;
		}
		
		return obj;
	}
	
	public List<T> queryForList(String query, Object ... params) throws NoResultException{
		
		Transaction tx = getCurrentSession().beginTransaction();
		
		Query<T> q =  getCurrentSession().createQuery(query, entityClass);
		
		for (int i = 0; i < params.length; i++){
			q.setParameter(i, params[i]);
		}
		
		List<T> objs = null;
		
		try{
			objs = q.getResultList();
			tx.commit();
		}
		catch (Exception e){
			tx.rollback();
			throw e;
		}		
		return objs;
	}
	
	public void saveOrUpdate(T obj){
		Transaction tx = getCurrentSession().beginTransaction();
		try { getCurrentSession().saveOrUpdate(obj); tx.commit();}
		catch (Exception e) { tx.rollback(); throw e;}
		
	}
	
	public void delete(T obj){
		Transaction tx = getCurrentSession().beginTransaction();
		try { getCurrentSession().delete(obj); tx.commit();}
		catch (Exception e) { tx.rollback(); throw e;}
	}
	
	public List<T> findAll() throws NoResultException {
		Transaction tx = getCurrentSession().beginTransaction();
		List<T> objs = null;
		try {objs = getCurrentSession().createQuery(String.format("select t from %s", entityClass.getSimpleName()), entityClass).getResultList();}
		catch (Exception e) { tx.rollback(); throw e;}
		return objs;
	}
	
	

}
