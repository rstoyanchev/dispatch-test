package test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class ServletB extends HttpServlet {

	private final Logger logger = LoggerFactory.getLogger(ServletB.class);

	@Override
	public void service(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {

		logger.debug("RequestURI={}, PathInfo={}", req.getRequestURI(), req.getPathInfo());

		final AsyncContext asyncContext = req.startAsync(req, res);

		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					logger.debug("RequestURI={}, PathInfo={}", req.getRequestURI(), req.getPathInfo());

//					asyncContext.dispatch("/WEB-INF/view.jsp");

					RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/view.jsp");
					disp.include(req, res);

				}
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					asyncContext.complete();
				}
			}
		});
	}

}
