<!DOCTYPE html>
<%@page import="com.app.model.Family"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
<%@ include file="/WEB-INF/css/table_style.css"%>
<%@ include file="/WEB-INF/css/general_style.css"%>
<%@ include file="/WEB-INF/css/main-page-style.css"%>
</style>
<html lang="en">
<body>
	<%@ include file="barner.jsp" %>
	<div>
		<div>
			<%
				Family family = (Family) request.getAttribute("Family");
				if (family == null) {
					family = new Family();
					request.setAttribute("Family", family);
				}
				//out.println(" Family Object: " + family.toString());
				String s = (String) request.getAttribute("mensaje");
				if(s != null && s.contains("success")) {
			%>
				<div class="success-div"><spring:message code="<%=s %>" /></div>
			<%
				} else if(s != null) {
			%>
				<div class="error-div"><spring:message code="<%=s %>" /></div>
			<%
				}
			%>
			<form:form method="post" action="saveFamMemb" modelAttribute="Family">
				<table border="0" style="width: 340px">
					<tr>
						<td style="text-align: right; width: 200px"><spring:message code="name" />:</td>
						<td><form:input path="nombre" /></td>
						<td>(*)</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 200px"><spring:message code="fatherLastName" />:</td>
						<td><form:input path="apellidoPaterno" /></td>
						<td>(*)</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 200px"><spring:message code="motherLastName" />:</td>
						<td><form:input path="apellidoMaterno" /></td>
						<td>(*)</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 200px"><spring:message code="phone" />:</td>
						<td><form:input path="telefono" /></td>
						<td>(*)</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 200px"><spring:message code="residence" />:</td>
						<td><form:input path="domicilio" /></td>
					</tr>
					<tr>
					<td style="text-align: right; width: 200px"><a href="<%=request.getContextPath().equals("") ? "/" : request.getContextPath() %>"><spring:message code="mainPage" /></a></td>
				<%
					if (family.getId() == null) {
				%>
						<td style="text-align: right;">
							<input type="submit" name="Guardar" value='<spring:message code="create" />' />
							<input type="reset" name="reset" value='<spring:message code="delete" />' />
						</td>
					<%
						} else {
					%>
						<td style="text-align: right;">
							<input type="submit" name="Actualizar" value="Actualizar" />
							<input type="submit" name="Eliminar" value="Eliminar" />
						</td>
				<%
					}
				%>
					</tr>
				</table>
				<%
					if (family.getId() != null) {
				%>
				<form:input path="Id" type="hidden" />
				<%
					}
				%>
			</form:form>
		</div>
	</div>
</body>
</html>
