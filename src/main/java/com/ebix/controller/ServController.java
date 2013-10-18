package com.ebix.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebix.domain.Cats;
import com.ebix.domain.Projects;
import com.ebix.test.AppDao;

/**
 * Servlet implementation class ServController
 */
public class ServController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		if(act.equals("product")){
			@SuppressWarnings("unchecked")
			List<Projects> projects = (List<Projects>) AppDao.all(Projects.class.getSimpleName());
			@SuppressWarnings("unchecked")
			List<Cats> cats = (List<Cats>) AppDao.all(Cats.class.getSimpleName());
			request.setAttribute("projects", projects);
			request.setAttribute("cats", cats);
			request.getRequestDispatcher("project.do").forward(request, response);
		}
	}

}
