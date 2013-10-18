package com.ebix.test;

// Generated Jul 3, 2013 4:56:36 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import com.ebix.util.HbernateUtil;

/**
 * Home object for domain model class Task.
 * 
 * @see com.ebix.domain.Task
 * @author Hibernate Tools
 */
public class AppDao {

	private static final Log log = LogFactory.getLog(AppDao.class);
	private static SessionFactory factory = HbernateUtil
			.getAnnotatedSessionFactory();

	public void persist(Object transientInstance) {
		log.debug("persisting Task instance");
		try {
			HbernateUtil.getAnnotatedSessionFactory().getCurrentSession()
					.persist(transientInstance);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public void attachDirty(Object instance) {
		log.debug("attaching dirty Task instance");
		try {
			HbernateUtil.getAnnotatedSessionFactory().getCurrentSession()
					.saveOrUpdate(instance);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public void attachClean(Object instance) {
		log.debug("attaching clean Task instance");
		try {
			HbernateUtil.getAnnotatedSessionFactory().getCurrentSession()
					.lock(instance, LockMode.NONE);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public void delete(Object persistentInstance) {
		log.debug("deleting Task instance");
		try {
			HbernateUtil.getAnnotatedSessionFactory().getCurrentSession()
					.delete(persistentInstance);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public static List<?> all(String name) {
		log.debug("fetching all instance");
		List<?> l = null;
		try {
			l = HbernateUtil.getAnnotatedSessionFactory().getCurrentSession().createQuery("from "+name).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return l;
	}

	public Object merge(Object detachedInstance) {
		log.debug("merging Task instance");
		Object result = null;
		try {
			result = HbernateUtil.getAnnotatedSessionFactory()
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Object findById(int id, String className) {
		log.debug("getting Task instance with id: " + id);
		Object result = null;
		try {
			result = HbernateUtil.getAnnotatedSessionFactory()
					.getCurrentSession().get(className, id);
			if (result == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			log.debug("merge successful");
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List findByExample(Object instance) {
		log.debug("finding Task instance by example");
		try {
			List results = HbernateUtil.getAnnotatedSessionFactory()
					.getCurrentSession().createCriteria("com.ebix.domain.Task")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
