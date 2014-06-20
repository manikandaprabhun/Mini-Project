package com.ebix.test;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ebix.domain.Cats;
import com.ebix.domain.Projects;
import com.ebix.util.HbernateUtil;

/**
 * Servlet implementation class DisplayServ
 */
@WebServlet(name = "DisplayServ", urlPatterns = "/DisplayServ", initParams = {
		@WebInitParam(name = "usr1", value = "Mani"),
		@WebInitParam(name = "usr2", value = "Eapp") })
public class DisplayServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SessionFactory factory;
	static Logger log = Logger.getLogger(DisplayServ.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayServ() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		factory = HbernateUtil.getAnnotatedSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;
		try {
			tx = session.beginTransaction();
			java.util.List<Cats> usr = session.createQuery(
					"FROM Cats as u WHERE u.name = 'java'").list();
			if (null != usr || !usr.isEmpty()) {
				Cats c = usr.get(0);
				Set<Projects> pro = c.getCats();
				log.warn(c);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

}
