<!DOCTYPE html>
<%-- Prevencion del cache --%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Locale"%>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.
%>
<%@page import="com.app.model.User"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="org.springframework.security.authentication.AnonymousAuthenticationToken"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@page import="com.app.model.Family"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<style>
<%@ include file="/WEB-INF/css/table_style.css"%>
<%@ include file="/WEB-INF/css/general_style.css"%>
<%@ include file="/WEB-INF/css/main-page-style.css"%>
</style>
<html lang="en">
<body>
	<div>
		<%@ include file="barner.jsp" %>
		<div>
			<%
				User user = new User();
				request.setAttribute("User", user);
				String s = (String) request.getSession().getAttribute("usuario");
				if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
			%>
			
			<div class="mainWrapper">
				<table border="0" class="table-align">
					<tr>
						<%
						if(request.getSession().getAttribute("bad-credential") != null) {
							request.getSession().removeAttribute("bad-credential");
						%>
						<td style="height: 20px; ">
							<div class="error-div">
								<spring:message code="bad-credential" />
							</div>
						</td>
						<%
						} else {
						%>
						<td style="height: 20px;">
							
						</td>
						<%} %>
					</tr>
					<tr>
						<td>
							<form:form method="post" action="validateLoggin" modelAttribute="User" >
								<table border="0">						
									<tr>
										<td style="text-align: right;"><spring:message code="user" />:</td>
										<td style="text-align: left;"><form:input path="userName" /></td>
									</tr>
									<tr>
										<td style="text-align: right;"><spring:message code="password" />:</td>
										<td><form:input path="password" type="password" /></td>
									</tr>
									<tr>
										<td><a href="<%=request.getContextPath() %>/registerNewUser"><spring:message code="register" /></a></td>
										<td style="text-align: right;">
											<table border="0" style="width: 100%;">
												<tr>
													<td><form:button><spring:message code="getin" /></form:button></td>
													<td></td>
													<td style="width: 50px"><input type="reset" name="reset" value="<spring:message code="delete" />"></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</form:form>
						</td>
					</tr>
				</table>
			</div>
			<%
				} else {
			%>
			<a href="<%=request.getContextPath() %>/findAllFamily"><spring:message code="listFamilyMembers" /></a><br> 
			<a href="<%=request.getContextPath() %>/findAllPartner"><spring:message code="listWorkPartners" /></a><br>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>
