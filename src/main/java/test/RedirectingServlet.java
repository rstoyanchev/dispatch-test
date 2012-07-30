package test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RedirectingServlet extends BaseServlet {

	public RedirectingServlet(String name, String target, CountDownLatch latch) {
		super(name, target, latch);
	}

	@Override
	protected void serviceInternal(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		logger.debug("");
		logger.debug("Redirect to \"{}\"", getTarget());
		logger.debug("");

		res.sendRedirect(getTarget());
	}

}
