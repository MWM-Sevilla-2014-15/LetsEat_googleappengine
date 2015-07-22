package com.silicom.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silicom.utils.PropUtil;
import com.silicon.dao.RestaurantDao;
import com.silicon.dao.UserDao;
import com.silicon.entities.Restaurant;

@SuppressWarnings("serial")
public class CreateRestaurant extends HttpServlet {
	
	private static final Logger _logger = Logger.getLogger(CreateRestaurant.class
			.getName());
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String url = PropUtil.RESTAURANT_REGIST_URL;
		try {
			Restaurant restaurant = new Restaurant(
					(String) req.getParameter("name"),
					(String) req.getParameter("typeSelector"),
					(String) req.getParameter("desc"),
					(String) req.getParameter("telf"),
					(String) req.getParameter("m_t_open"),
					(String) req.getParameter("m_t_close"),
					(String) req.getParameter("t_t_open"),
					(String) req.getParameter("t_t_close"),
					Integer.valueOf((String) req.getParameter("avg_price")),
					Integer.valueOf((String) req.getParameter("discount")),
					Float.valueOf((String) req.getParameter("score")),
					Integer.valueOf((String) req.getParameter("nTables")),
					Float.valueOf((String) req.getParameter("lat")),
					Float.valueOf((String) req.getParameter("lon")));
			String result = RestaurantDao.create(restaurant);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(url);
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			_logger.severe(e.getMessage());
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req, resp);
	}
}
