package com.silicom.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silicom.utils.PropUtil;
import com.silicon.dao.UserDao;
import com.silicon.entities.User;

@SuppressWarnings("serial")
public class AdminLogin extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String emailAdmin = getServletContext().getInitParameter("AdministratorEmail");
		String name = (String) req.getParameter("name");
		String pass = (String) req.getParameter("pass");
		User user = new User (name, pass);
		String result = UserDao.isAdmin(user,emailAdmin);
		String url=PropUtil.ADMIN_URL;
		if (result.equals(PropUtil.IA_OK)){
			url=PropUtil.RESTAURANT_REGIST_URL;
		}
		try {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(req,resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
