package com.ebix.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ListenContext implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent evnt) {

	}

	public void contextInitialized(ServletContextEvent evnt) {
		/*
		 * ServletContext sc = evnt.getServletContext(); User u = new
		 * User(sc.getInitParameter("mail"), sc.getInitParameter("mail"));
		 * sc.setAttribute("user", u);
		 */
//		ServletContext context = evnt.getServletContext();
//		System.setProperty("rootPath", context.getRealPath("/"));
	}

}
