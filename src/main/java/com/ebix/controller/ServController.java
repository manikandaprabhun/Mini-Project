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
import com.ebix.util.ServConstans;

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

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		if (ServConstans.DELETE.equals(act)) {
			String id = request.getParameter("id");
			Projects projects = new Projects();
			projects.setId(Integer.parseInt(id));
			AppDao.delete(projects);
			loadAndRedirect(request, response);
		} else if (ServConstans.UPDATEFORM.equals(act)) {
			String id = request.getParameter("id");
			Projects upProd = (Projects) AppDao.findById(Integer.parseInt(id),
					Projects.class.getName());
			request.setAttribute("upProd", upProd);
			loadAndRedirect(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		if (ServConstans.HOME.equals(act)) {
			loadAndRedirect(request, response);
		} else if (ServConstans.NEWPRODUCT.equals(act)) {
			String name = request.getParameter("name");
			String category = request.getParameter("catgry");
			Projects projects = new Projects(name, new Cats(
					Integer.parseInt(category), null, null));
			AppDao.attachDirty(projects);
			loadAndRedirect(request, response);
		} else if (ServConstans.UPDATE.equals(act)) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String category = request.getParameter("catgry");
			Projects projects = new Projects(Integer.parseInt(id), name,
					new Cats(Integer.parseInt(category), null, null));
			AppDao.attachDirty(projects);
			loadAndRedirect(request, response);

		}
	}

	@SuppressWarnings("unchecked")
	private void loadAndRedirect(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Projects> projects = (List<Projects>) AppDao.all(Projects.class
				.getSimpleName());
		List<Cats> cats = (List<Cats>) AppDao.all(Cats.class.getSimpleName());
		request.setAttribute("projects", projects);
		request.setAttribute("cats", cats);
		request.getRequestDispatcher("project.do").forward(request, response);
	}

}
