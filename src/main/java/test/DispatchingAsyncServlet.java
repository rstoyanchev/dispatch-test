package test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DispatchingAsyncServlet extends BaseServlet {

	public DispatchingAsyncServlet(String name, String target, CountDownLatch latch) {
		super(name, target, latch);
	}

	@Override
	protected void serviceInternal(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		final AsyncContext asyncContext = request.startAsync(request, response);

		logAction("Start application thread");

		Executors.newSingleThreadExecutor().submit(new Runnable() {
			@Override
			public void run() {
				await();  // wait for the container thread to finish
				logState(request);
				logAction("Dispatch from application thread to \"{}\"", getTarget());

				asyncContext.dispatch(getTarget());
			}
		});
	}

}
