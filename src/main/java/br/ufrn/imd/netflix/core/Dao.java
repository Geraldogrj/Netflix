package br.ufrn.imd.netflix.core;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class Dao<T extends Model> {
	
	private final Class<T> entityClass;
	
	public Dao(Class<T> clazz) {
		entityClass = clazz;
		
	}
		 
	protected SessionFactory getSessionFactory(){
		return Hibernate.getSessionFactory();
	}
	
	public T findById(Integer id) throws NoResultException {
		return getSessionFactory().openSession().find(entityClass, id);
	}
	
	public T queryForOne(String query, Object ... params) throws NoResultException{
		
		Query<T> q =  getSessionFactory().openSession().createQuery(query, entityClass);
		
		for (int i = 0; i < params.length; i++){
			q.setParameter(i, params[i]);
		}
		
		T obj = null;
		
		try{
			obj = q.getSingleResult();
		}
		catch (NoResultException e){
			throw e;
		}

		return obj;
	}
	
	public List<T> queryForList(String query, Object ... params) throws NoResultException{
		
		Query<T> q =  getSessionFactory().openSession().createQuery(query, entityClass);
		
		for (int i = 0; i < params.length; i++){
			q.setParameter(i, params[i]);
		}
		
		List<T> objs = null;
		
		try{
			objs = q.getResultList();
		}
		catch (NoResultException e){
			throw e;
		}
		
		return objs;
	}
	
	public void saveOrUpdate(T obj){
		getSessionFactory().openSession().saveOrUpdate(obj);
	}
	
	public void delete(T obj){
		getSessionFactory().openSession().delete(obj);
	}
	
	public void findAll() throws NoResultException {
		getSessionFactory().openSession().createQuery(String.format("select t from %s", entityClass.getSimpleName()), entityClass);
	}
	
	

}
