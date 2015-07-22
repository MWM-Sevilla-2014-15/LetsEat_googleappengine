package com.silicom.cron;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.silicon.dao.RestaurantDao;

@SuppressWarnings("serial")
public class ResetBookedTables extends HttpServlet {
	private static final Logger _logger = Logger
			.getLogger(ResetBookedTables.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			RestaurantDao.resetBookedTables();
		} catch (Exception e) {
			_logger.severe("Cron ResetBookedTables NO ejecutado. Mensaje:"+e.toString());
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
