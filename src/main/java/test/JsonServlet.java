package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class JsonServlet extends HttpServlet {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.debug("-----------------------------------------------");
		logger.debug("'Servlet JsonB' ({})", Thread.currentThread().getName().toUpperCase());
		logger.debug("-----------------------------------------------");

		logger.debug("");
		logger.debug("Writing JSON content");
		logger.debug("");

		response.setContentType("application/json");
		response.getWriter().print(" { \"foo\" : \"bar\" } ");

	}


}
