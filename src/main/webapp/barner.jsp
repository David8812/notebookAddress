<%@page import="com.app.model.Partner"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@page import="org.springframework.security.authentication.AnonymousAuthenticationToken"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div style="background-color: #B3AFFA; height: 25px">
	<table style="width: 100%;" border="0">
		<tr>
			<td style="vertical-align: top;">
				<%
				//String path = (String)request.getContextPath();//(String)request.getAttribute("javax.servlet.forward.request_uri");
				//out.println((String)request.getAttribute("javax.servlet.forward.request_uri"));
				//out.println((String)request.getContextPath());
				//String prefix = request.getQueryString();
				//out.println(" " + prefix);
				/*if(prefix != null && prefix.indexOf("language") != -1) {
					int idx = prefix.indexOf("language");
					if(idx > 0)
						idx--;
					prefix = prefix.substring(0, idx);
					if(prefix.length() == 0)
						prefix = null;
				}*/
				//out.println(prefix);
				Authentication auth = (Authentication) request.getSession().getAttribute("auth");
				if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
				%>
				<strong><spring:message code="welcome" /> <%=request.getSession().getAttribute("usuario") %> !!!</strong>
				<%} %>
			</td>
			<td style="text-align: right; vertical-align: top;">
				<%
				if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
				%>
				<a href="<%=request.getContextPath() %>/loggout"><spring:message code="logout" /></a>
				<%} 
				//out.println("URL: " + request.getRequestURL().toString());
				//out.println(" URI: " + request.getRequestURI());
				//out.println(" Ctx Path: " + request.getContextPath());
				//if(request.getRequestURL().toString().endsWith("index.jsp"))// && prefix == null) 
				{
				%>	
					<a>|</a>
					<a href="?language=es_US">English</a>
					<a>|</a> 
					<a href="?language=es_MX">Español</a></td>
				<%
					}
				%>
		</tr>
	</table>
</div>