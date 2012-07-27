/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String name;

	private final String target;

	private final CountDownLatch latch;

	public BaseServlet(String name, String target, CountDownLatch latch) {
		this.name = "Servlet " + name;
		this.target = target;
		this.latch = latch;
	}

	public String getTarget() {
		return target;
	}

	public CountDownLatch getLatch() {
		return latch;
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request = wrapRequest(request);
		logState(request);
		serviceInternal(request, response);
	}

	private HttpServletRequest wrapRequest(HttpServletRequest req) {
		if ((req.getParameter("wrap") != null) && !(req instanceof HttpServletRequestWrapper)) {
			return new HttpServletRequestWrapper(req);
		}
		return req;
	}

	protected void logState(HttpServletRequest request) {
		logger.debug("-----------------------------------------------");
		logger.debug("'{}' ({})", this.name, Thread.currentThread().getName().toUpperCase());
		logger.debug("-----------------------------------------------");
		logger.debug("request={}, type={}", request, request.getClass().getName());
		logger.debug("requestURI={}, contextPath={}, servletPath={}, pathInfo={}",
				new Object[] {request.getRequestURI(), request.getContextPath(), request.getServletPath(), request.getPathInfo()});
		logger.debug("parameterMap={}", request.getParameterMap());
		logger.debug("javax.servlet.forward.request_uri={}", request.getAttribute("javax.servlet.forward.request_uri"));
	}

	protected void logAction(String action, Object... args) {
		logger.debug("");
		logger.debug(action, args);
		logger.debug("");
	}

	protected void countDown() {
		if (this.latch != null) {
			this.latch.countDown();
		}
	}

	protected void await() {
		if (this.latch != null) {
			try {
				this.latch.await();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected abstract void serviceInternal(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;

}
