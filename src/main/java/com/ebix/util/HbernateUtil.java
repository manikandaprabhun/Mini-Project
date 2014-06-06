package com.ebix.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.ebix.domain.Cats;
import com.ebix.domain.Projects;
import com.ebix.domain.User;

public class HbernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static final SessionFactory annotatedSessionFactory = buildAnnotatedSessionFactory();
	private static ServiceRegistry serviceRegistry;
	private static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(
					configuration.getProperties()).buildServiceRegistry();
			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	private static SessionFactory buildAnnotatedSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(Cats.class)
					.addAnnotatedClass(Projects.class)
					.addAnnotatedClass(User.class).configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(
					configuration.getProperties()).buildServiceRegistry();
			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}

	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static SessionFactory getAnnotatedSessionFactory() {
		return annotatedSessionFactory;
	}

	public static Session getSession() {
		Session s = (Session) threadSession.get();
		// open a new Session, if this thread has none yet
		try {
			if (s == null) {
				s = annotatedSessionFactory.openSession();
				threadSession.set(s);
			}
		} catch (HibernateException ex) {
			throw ex;
		}
		return s;
	}

	public static void closeSession() {
		try {
			Session s = (Session) threadSession.get();
			threadSession.set(null);
			if (s != null && s.isOpen())
				s.close();
		} catch (HibernateException ex) {
			throw ex;
		}
	}
}
