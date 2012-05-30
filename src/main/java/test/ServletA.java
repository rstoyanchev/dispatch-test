package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class ServletA extends HttpServlet {

	private final Logger logger = LoggerFactory.getLogger(ServletA.class);

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		logger.debug("Before: RequestURI={}, PathInfo={}", req.getRequestURI(), req.getPathInfo());

		RequestDispatcher disp = req.getRequestDispatcher("/servletB/b");
		disp.forward(req, res);

		logger.debug("After: RequestURI={}, PathInfo={}", req.getRequestURI(), req.getPathInfo());
	}

}
