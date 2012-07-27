package test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ForwardingServlet extends BaseServlet {

	public ForwardingServlet(String name, String target, CountDownLatch latch) {
		super(name, target, latch);
	}

	@Override
	protected void serviceInternal(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		logger.debug("");
		logger.debug("Forward to \"{}\"", getTarget());
		logger.debug("");

		req.getRequestDispatcher(getTarget()).forward(req, res);

		countDown();  // Let the application thread know we've finished
	}

}
