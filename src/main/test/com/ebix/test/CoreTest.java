package com.ebix.test;

import static junit.framework.Assert.assertNotNull;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import com.ebix.domain.Cats;
import com.ebix.domain.Projects;
import com.ebix.util.HbernateUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class CoreTest {

	static {
		try {
			setUpClass();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Test
	public void loadMappings() {

		try {
			HbernateUtil.getSession().beginTransaction();
			List<Projects> projects = (List<Projects>) AppDao
					.all(Projects.class.getSimpleName());
			List<Cats> cats = (List<Cats>) AppDao.all(Cats.class
					.getSimpleName());

			assertNotNull(projects);
			assertNotNull(cats);
			HbernateUtil.getSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setUpClass() throws Exception {
		try {
			// Create initial context
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					"org.apache.naming.java.javaURLContextFactory");
			System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
			InitialContext ic = new InitialContext();

			ic.createSubcontext("java:");
			ic.createSubcontext("java:/comp");
			ic.createSubcontext("java:/comp/env");
			ic.createSubcontext("java:/comp/env/jdbc");

			// Construct DataSource
			ComboPooledDataSource ds = new ComboPooledDataSource();
			ds.setDriverClass("com.mysql.jdbc.Driver");
			ds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test");
			ds.setInitialPoolSize(10);
			ds.setMaxPoolSize(50);
			ds.setMinPoolSize(8);
			ds.setUser("root");
			ds.setPassword("admin");
			ic.bind("java:/comp/env/jdbc/test", ds);
		} catch (NamingException ex) {
			ex.printStackTrace();
		}

	}

}
