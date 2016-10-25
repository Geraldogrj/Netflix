package br.ufrn.imd.netflix.application.core;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Classe Gen�rica para realizar a��es com banco de dados.
 * @author robertodantas
 *
 * @param <T>
 */
public class Dao<T extends Model> {
	
	private final Class<T> entityClass;
	private int pageSize;
	
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
		}
		catch (NoResultException e){
			return null;
		}
		catch (Exception e){
			tx.rollback();
			throw e;
		}
		finally {tx.commit();}
		
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
		}
		catch (NoResultException e){
			return null;
		}
		catch (Exception e){
			tx.rollback();
			throw e;
		}
		finally {tx.commit();}
		
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
		}
		catch (NoResultException e){
			return null;
		}
		catch (Exception e){
			tx.rollback();
			throw e;
		}		
		finally {tx.commit();}
		return objs;
	}
	
	public void saveOrUpdate(T obj){
		Transaction tx = getCurrentSession().beginTransaction();
		try { getCurrentSession().saveOrUpdate(obj);}
		catch (Exception e) { tx.rollback(); throw e;}
		finally {tx.commit();}
	}
	
	public void delete(T obj){
		Transaction tx = getCurrentSession().beginTransaction();
		try { getCurrentSession().delete(obj);}
		catch (Exception e) { tx.rollback(); throw e;}
		finally {tx.commit();}
	}
	
	public List<T> findAll() throws NoResultException {
		Transaction tx = getCurrentSession().beginTransaction();
		List<T> objs = null;
		try {
			objs = getCurrentSession().createQuery(String.format("select t from %s t", entityClass.getSimpleName()), entityClass).getResultList();
		}
		catch (NoResultException e){return null;}
		catch (Exception e) { tx.rollback(); throw e;}
		finally {
			tx.commit();
		}
		return objs;
	}
	
	public List<T> findAll(int page){
		Transaction tx = getCurrentSession().beginTransaction();
		int maxRecords = getPageSize();
		int startRow = page * maxRecords;	
		
		Query<T> query = getCurrentSession().createQuery("select t from " + entityClass.getSimpleName() + " t ", entityClass);
		query.setMaxResults(maxRecords);
		query.setFirstResult(startRow);
		
		List<T> objs = null;
		try {
			objs = query.getResultList();
		}
		catch (NoResultException e){return null;}
		catch (Exception e) { tx.rollback(); throw e;}
		finally {
			tx.commit();
		}
		
		return objs;
	}
	
	public Long countAll(){
		Transaction tx = getCurrentSession().beginTransaction();
		Long countResult = new Long(0);
		try {
			countResult = (Long) getCurrentSession().createQuery(String.format("select count(t.id) from %s t ", entityClass.getSimpleName())).getSingleResult();
		}
		catch (Exception e){
			tx.rollback(); 
			return countResult;
			}
		finally {
			tx.commit();
		}
				
		return countResult;
	}
	
	public int numPages(){
		Long numPages = countAll() / getPageSize();
		return numPages.intValue();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	
	

}
