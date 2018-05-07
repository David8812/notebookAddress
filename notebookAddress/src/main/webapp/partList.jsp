<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="com.app.model.Partner"%>
<%@page import="com.app.model.Family"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<style>
<%@ include file="/WEB-INF/css/table_style.css"%>
<%@ include file="/WEB-INF/css/general_style.css"%>
</style>
</head>
<body>
	<%@ include file="barner.jsp" %>
	<div>
		<div>
			<form action="<%=request.getContextPath() %>/callFromSearchPart" method="get">
				<table border="0" style="width: 100%">
					<tr>
						<td style="width: 18.5%; text-align: left;"><input name="search" type="text" /> <input type="submit" name="buscar" value='<spring:message code="search" />' /></td>
						<td style="width: 20%"><input type="submit" name="agregarNuevo" value='<spring:message code="addNew" />' /></td>
						<td style="text-align: right;"><a href="<%=request.getContextPath().equals("") ? "/" : request.getContextPath() %>"><spring:message code="mainPage" /></a></td>
					</tr>
				</table>
			</form>
		</div>
		<div>
			<table border="1" class="table table-hover">
				<thead>
					<tr>
						<td>Id</td>
						<td><spring:message code="name"/></td>
						<td><spring:message code="fatherLastName" /></td>
						<td><spring:message code="motherLastName" /></td>
						<td><spring:message code="phone" /></td>
						<td><spring:message code="residence" /></td>
						<td><spring:message code="company"/></td>
						<td><spring:message code="area" /></td>
						<td><spring:message code="bussinessSector" /></td>
					</tr>
				</thead>
				<tbody>
					<%
						List<Partner> list = (List<Partner>) request.getAttribute("list");
						if (list != null) {
							for (Partner part : list) {
								long id = part.getId();
								String nombre = part.getNombre();
								String appPat = part.getApellidoPaterno();
								String appMat = part.getApellidoMaterno();
								String telefono = part.getTelefono();
								String domicilio = part.getDomicilio();
								String empresa = part.getCompany();
								String area = part.getArea();
								String rubro = part.getRubro();
					%>
					<tr>
						<td><a href="<%=request.getContextPath() %>/editPartner?idPartner=<%=part.getId()%>"> <%=part.getId()%>
						</a></td>
						<td><%=nombre%></td>
						<td><%=appPat%></td>
						<td><%=appMat%></td>
						<td><%=telefono%></td>
						<td><%=domicilio%></td>
						<td><%=empresa%></td>
						<td><%=area%></td>
						<td><%=rubro%></td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
