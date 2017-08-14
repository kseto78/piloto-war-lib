<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.*" %>
		<%
			List<String> msgPltError = null;
			if(request.getSession().getAttribute("MSGPLT_ERROR")!=null){
				msgPltError = (List<String>) request.getSession().getAttribute("MSGPLT_ERROR");
			
		
		%>
							<% for(String msg : msgPltError){ %>
		                		<div class="validation Error"><%=msg%></div>
		                	<%} 
							request.getSession().setAttribute("MSGPLT_ERROR",null);
		                	%>
	      <%}%>