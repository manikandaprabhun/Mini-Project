package com.ebix.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ebix.domain.User;
import com.ebix.util.HbernateUtil;

public class Authenticate implements Filter {
	private static final String REDIRECT = "login.do";

	public void destroy() {		System.err.println("initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession httpSession = req.getSession();

		if (null == httpSession.getAttribute("user")) {
			if (null != req.getParameter("usr")
					|| null != req.getParameter("psw")) {
				User u = new User(req.getParameter("usr"),
						req.getParameter("psw"));
				Session session = HbernateUtil.getAnnotatedSessionFactory().getCurrentSession();
				try {
					@SuppressWarnings("unchecked")
					java.util.List<User> usr = session.createQuery(
							"FROM User as u WHERE u.name ='"
									+ req.getParameter("usr")
									+ "' AND u.password ='"
									+ req.getParameter("psw") + "'").list();
					if (null == usr || usr.isEmpty()) {
						request.getRequestDispatcher(REDIRECT).forward(request, response);
						return;
					}else{
						httpSession.setAttribute("user", u);
						chain.doFilter(request, response);
					}
				} catch (HibernateException e) {
					e.printStackTrace();
				}
			} else {
				request.getRequestDispatcher(REDIRECT).forward(request, response);
				return;
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.err.println("initialized");
	}

}
