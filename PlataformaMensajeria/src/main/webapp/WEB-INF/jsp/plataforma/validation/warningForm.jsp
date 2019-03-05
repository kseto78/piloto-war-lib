<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.*" %>
		<%
			List<String> msgPltWarning = null;
			if(request.getSession().getAttribute("MSGPLT_WARNING")!=null){
				msgPltWarning = (List<String>) request.getSession().getAttribute("MSGPLT_WARNING");
			
		
		%>
							<% for(String msg : msgPltWarning){ %>
		                		<div class="validation Warning"><%=msg%></div>
		                	<%} 
							request.getSession().setAttribute("MSGPLT_WARNING",null);
		                	%>
	      <%}%>