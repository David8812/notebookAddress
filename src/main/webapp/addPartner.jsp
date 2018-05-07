<!DOCTYPE html>
<%@page import="com.app.model.Partner"%>
<%@page import="com.app.model.Family"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
<%@ include file="/WEB-INF/css/table_style.css"%>
<%@ include file="/WEB-INF/css/general_style.css"%>
</style>
<html lang="en">
<body>
	<%@ include file="barner.jsp" %>
	<div>
		<div>
			<%
				Partner partner = (Partner) request.getAttribute("Partner");
				if (partner == null) {
					partner = new Partner();
					request.setAttribute("Partner", partner);
				}
				String s = (String) request.getAttribute("mensaje");
				if(s != null && s.contains("success")) {
				%>
					<div style="background-color:green; text-align: center; height: 20px; color: white; text-shadow: 1px 1px 2px #000;"><spring:message code="<%=s %>" /></div>
				<%
					} else if(s != null) {
				%>
					<div style="background-color:red; text-align: center; height: 20px; color: white; text-shadow: 1px 1px 2px #000;"><spring:message code="<%=s %>" /></div>
				<%
					}
				%>
			<form:form method="post" action="savePartner" modelAttribute="Partner">
				<table border="0" style="width: 360px;">
					<tr>
						<td style="text-align: right; width: 180px;"><spring:message code="name" />:</td>
						<td><form:input path="nombre" /></td>
						<td>(*)</td>
					</tr>
					<tr>
						<td style="text-align: right;"><spring:message code="fatherLastName" />:</td>
						<td><form:input path="apellidoPaterno" /></td>
						<td>(*)</td>
					</tr>
					<tr>
						<td style="text-align: right;"><spring:message code="motherLastName" />:</td>
						<td><form:input path="apellidoMaterno" /></td>
						<td>(*)</td>
					</tr>
					<tr>
						<td style="text-align: right;"><spring:message code="phone" />:</td>
						<td><form:input path="telefono" /></td>
						<td>(*)</td>
					</tr>
					<tr>
						<td style="text-align: right;"><spring:message code="residence" />:</td>
						<td><form:input path="domicilio" /></td>
					</tr>
					<tr>
						<td style="text-align: right;"><spring:message code="company" />:</td>
						<td><form:input path="company" /></td>
					</tr>
					<tr>
						<td style="text-align: right;"><spring:message code="area" />:</td>
						<td><form:input path="area" /></td>
					</tr>
					<tr>
						<td style="text-align: right;"><spring:message code="bussinessSector" />:</td>
						<td><form:input path="rubro" /></td>
						<td>(*)</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							<a href="<%=request.getContextPath().equals("") ? "/" : request.getContextPath() %>"><spring:message code="mainPage" /></a>
						</td>
					<%
						if (partner.getId() == null) {
					%>
						<td style="text-align: right;">
							<input type="submit" name="Guardar" value='<spring:message code="create" />' />
							<input type="reset" name="reset" value='<spring:message code="delete" />' />
						</td>
					<%
						} else {
					%>
						<td style="text-align: right;">
							<input type="submit" name="Actualizar" value='<spring:message code="update" />' />
							<input type="submit" name="Eliminar" value='<spring:message code="erase" />' />
						</td>
					<%
						}
					%>
					</tr>
					<%
					if (partner.getId() != null) {
					%>
						<form:input path="Id" type="hidden" />
					<%
					}
					%>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>
