package com.ebix.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;

import com.ebix.util.HbernateUtil;

/**
 * Servlet Filter implementation class HibernateSessionRequestFilter
 */
public class HibernateSessionRequestFilter implements Filter {
	private SessionFactory sf;

	/**
	 * Default constructor.
	 */
	public HibernateSessionRequestFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			if (!sf.getCurrentSession().getTransaction().isActive())
				HbernateUtil.getSession().beginTransaction();
			// Call the next filter (continue request processing)
			chain.doFilter(request, response);
			// Commit and cleanup
			if (HbernateUtil.getSession().getTransaction().isActive())
				HbernateUtil.getSession().getTransaction().commit();
			HbernateUtil.closeSession();
		} catch (StaleObjectStateException staleEx) {
			throw staleEx;
		} catch (Throwable ex) {
			// Rollback only
			try {
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}
			} catch (Throwable rbEx) {
			}
			// Let others handle it... maybe another interceptor for exceptions?
			throw new ServletException(ex);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		sf = HbernateUtil.getAnnotatedSessionFactory();
	}

}
