package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.JobDAO;
import com.entity.Jobs;

/**
 * Servlet implementation class AddPostServlet
 */
@WebServlet("/add_post")
public class AddPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPostServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String title = request.getParameter("title");
			String category = request.getParameter("category");
			String status = request.getParameter("status");
			String location = request.getParameter("location");

			String desc = request.getParameter("desc");

			Jobs j = new Jobs();
			j.setTitle(title);
			j.setCategory(category);
			j.setStatus(status);
			j.setLocation(location);

			j.setDescription(desc);

			HttpSession session = request.getSession();

			JobDAO dao = new JobDAO(DBConnect.getConn());
			boolean f = dao.addJobs(j);
			if (f) {
				session.setAttribute("succMsg", "Job Post Successfully...");
				response.sendRedirect("view_jobs.jsp");
			} else {
				session.setAttribute("succMsg", "Something Went Wrong...");
				response.sendRedirect("view_jobs.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
