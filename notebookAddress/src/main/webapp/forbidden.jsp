<style>
<%@ include file="/WEB-INF/css/general_style.css"%>
</style>
<div>
	<h1>No tiene permitido el acceso a este lugar</h1>
	<a href="<%=request.getContextPath().equals("") ? "/" : request.getContextPath() %>">Página principal</a>
</div>