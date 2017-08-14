<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.*" %>
		<%
			List<String> msgPltField = null;
			if(request.getSession().getAttribute("MSGPLT_FIELD_ERROR")!=null){
				msgPltField = (List<String>) request.getSession().getAttribute("MSGPLT_FIELD_ERROR");
			
		
		%>
								<% for(String msg : msgPltField){ %>
			                		<div class="validation Error"><%=msg%></div>
			                	<%} 
								request.getSession().setAttribute("MSGPLT_FIELD_ERROR",null);
			                	%>
	      <%}%>