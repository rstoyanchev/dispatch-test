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
package test.config;

import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;

import test.DispatchingAsyncServlet;
import test.ForwardingAsyncServlet;
import test.ForwardingServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		setupDispatchScenario(servletContext);

		setupForwardScenario(servletContext);

	}

	private void setupDispatchScenario(ServletContext servletContext) {

		CountDownLatch latch = new CountDownLatch(1);

		Dynamic servlet;
		servlet = servletContext.addServlet("DispA", new ForwardingServlet("A", "/dispScenarioB?b=B", latch));
		servlet.setAsyncSupported(true);
		servlet.addMapping("/dispScenarioA/*");

		servlet = servletContext.addServlet("DispB", new ForwardingServlet("B", "/dispScenarioC?c=C", null));
		servlet.setAsyncSupported(true);
		servlet.addMapping("/dispScenarioB/*");

		servlet = servletContext.addServlet("DispC", new DispatchingAsyncServlet("C", "/dispScenarioD?d=D", latch));
		servlet.setAsyncSupported(true);
		servlet.addMapping("/dispScenarioC/*");

		servlet = servletContext.addServlet("DispD", new ForwardingServlet("D", "/WEB-INF/page.jsp", null));
		servlet.setAsyncSupported(true);
		servlet.addMapping("/dispScenarioD/*");
	}

	private void setupForwardScenario(ServletContext servletContext) {

		CountDownLatch latch = new CountDownLatch(1);

		Dynamic servlet;
		servlet = servletContext.addServlet("ForwardA", new ForwardingServlet("A", "/forwardScenarioB?b=B", latch));
		servlet.setAsyncSupported(true);
		servlet.addMapping("/forwardScenarioA/*");

		servlet = servletContext.addServlet("ForwardB", new ForwardingServlet("B", "/forwardScenarioC?c=C", null));
		servlet.setAsyncSupported(true);
		servlet.addMapping("/forwardScenarioB/*");

		servlet = servletContext.addServlet("ForwardC", new ForwardingAsyncServlet("C", "/forwardScenarioD?d=D", latch));
		servlet.setAsyncSupported(true);
		servlet.addMapping("/forwardScenarioC/*");

		servlet = servletContext.addServlet("ForwardD", new ForwardingServlet("D", "/WEB-INF/page.jsp", null));
		servlet.setAsyncSupported(true);
		servlet.addMapping("/forwardScenarioD/*");
	}

}
