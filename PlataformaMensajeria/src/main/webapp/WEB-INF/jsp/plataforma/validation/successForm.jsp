<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.*" %>
		<%
			List<String> msgPlt = null;
			if(request.getSession().getAttribute("MSGPLT")!=null){
				msgPlt = (List<String>) request.getSession().getAttribute("MSGPLT");
			
		
		%>
							<% for(String msg : msgPlt){ %>
		                		<div class="validation Success"><%=msg%></div>
		                	<%} 
							request.getSession().setAttribute("MSGPLT",null);
		                	%>
	      <%}%>