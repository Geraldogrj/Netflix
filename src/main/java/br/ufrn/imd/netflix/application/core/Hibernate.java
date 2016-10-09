package br.ufrn.imd.netflix.application.core;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernate {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
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
