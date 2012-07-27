<%@page import="org.slf4j.Logger"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PAGE.JSP</title>
</head>
<body>
  <h3>This is the content of <code>page.jsp</code>. Check the log file for output</h3>
  <%
    Logger logger = LoggerFactory.getLogger("test.page-jsp");
	logger.debug("-----------------------------------------------");
	logger.debug("'{}' ({})", "page.jsp", Thread.currentThread().getName().toUpperCase());
	logger.debug("-----------------------------------------------");
	logger.debug("request={}, type={}", request, request.getClass().getName());
	logger.debug("requestURI={}, contextPath={}, servletPath={}, pathInfo={}",
			new Object[] {request.getRequestURI(), request.getContextPath(), request.getServletPath(), request.getPathInfo()});
	logger.debug("parameterMap={}", request.getParameterMap());
	logger.debug("javax.servlet.forward.request_uri={}", request.getAttribute("javax.servlet.forward.request_uri"));
  %>
</body>
</html>