<%@page import="com.app.model.User"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
<%@ include file="/WEB-INF/css/general_style.css"%>
</style>
<%@ include file="barner.jsp" %>
<div>
	<%
		User user = new User();
		request.setAttribute("User", user);
	%>
	<form:form action="saveNewUser" method="post" modelAttribute="User">
		<%
			String mensaje = (String) request.getAttribute("mensaje");
			if(mensaje != null && mensaje.contains("success")) {
		%>
		<div style="background-color: green; text-align: center; color: white; text-shadow: 1px 1px 2px #000;">
			<spring:message code="<%=mensaje %>" />
		</div>
		<% } else if(mensaje != null) {
		%>
		<div style="background-color: red; text-align: center; color: white; text-shadow: 1px 1px 2px #000;">
			<spring:message code="<%=mensaje %>" />
		</div>
		<%} %>
		<table border="0" style="width: 370px">
			<tr>
				<td style="text-align: right; width: 50%"><spring:message code="user" />:</td>
				<td style="text-align: left;"><form:input path="userName"/></td>
				<td>(*)</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 50%"><spring:message code="password" />:</td>
				<td><form:input path="password" type="password" /></td>
				<td>(*)</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 50%"><spring:message code="confirmPassword" />:</td>
				<td><form:input path="confirmPassword" type="password" /></td>
				<td>(*)</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 50%">Roll:</td>
				<td>
					<form:select path="roll">
						<form:option value="ADMIN">ADMIN</form:option>
						<form:option value="USER">USER</form:option>
					</form:select>
					(*)
				</td>
			</tr>
			<tr>
				<td style="text-align: right;"><a href="<%=request.getContextPath().equals("") ? "/" : request.getContextPath() %>"><spring:message code="mainPage" /></a></td>
				<td style="text-align: right;">
					<table border="0" style="width: 100%;">
						<tr>
							<td style="text-align: right;"><form:button><spring:message code="create" /></form:button></td>
							<td></td>
							<td style="width: 50px"><input type="reset" name="reset" value='<spring:message code="delete" />'></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</div>