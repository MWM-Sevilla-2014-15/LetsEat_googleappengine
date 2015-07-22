package com.silicom.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silicom.cron.ResetBookedTables;
import com.silicom.utils.PropUtil;
import com.silicon.dao.UserDao;
import com.silicon.entities.User;

@SuppressWarnings("serial")
public class AdminLogin extends HttpServlet {

	private static final Logger _logger = Logger.getLogger(AdminLogin.class
			.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			String emailAdmin = getServletContext().getInitParameter(
					"AdministratorEmail");
			String name = (String) req.getParameter("name");
			String pass = (String) req.getParameter("pass");
			User user = new User(name, pass);
			String result = UserDao.isAdmin(user, emailAdmin);
			String url = PropUtil.ADMIN_URL;
			if (result.equals(PropUtil.IA_OK)) {
				url = PropUtil.RESTAURANT_REGIST_URL;
			}
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(url);
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			_logger.severe(e.toString());
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req, resp);
	}
}
