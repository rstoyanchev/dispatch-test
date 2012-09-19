package test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ForwardingAsyncServlet extends BaseServlet {

	public ForwardingAsyncServlet(String name, String target, CountDownLatch latch) {
		super(name, target, latch);
	}

	@Override
	protected void serviceInternal(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		final AsyncContext asyncContext = request.startAsync(request, response);

		logAction("Start application thread");

		Executors.newSingleThreadExecutor().submit(new Runnable() {
			@Override
			public void run() {
				try {
					await();  // wait for the container thread to finish
					logState(request, response);
					logAction("Forward from application thread to {}", getTarget());
					request.getRequestDispatcher(getTarget()).forward(request, response);
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
