package br.ufrn.imd.netflix.application.core;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Classe responsável por carregar as configurações do banco de dados Hibernate
 * @author Geraldo e Roberto
 *
 */
@SuppressWarnings("unused")
public class Hibernate {

	private static final String MYSQL = "hibernate-mysql.cfg.xml";
	private static final String HQSQLDB = "hibernate-hqsqldb.cfg.xml";
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			SessionFactory sessionFactory = new Configuration().configure(MYSQL)
					.buildSessionFactory();
			return sessionFactory;

		} 
		catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

}
