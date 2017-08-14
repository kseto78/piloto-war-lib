<%@ page isErrorPage="true" %>
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<html>
<body>
<h1>
Error validaci&oacute;n funcional!!!!!
</h1>
<hr>
<h2>
Recibida la excepcion:<br>
<font color=red>
<% 
	Throwable exc=null;
	String message=null;
	if (exception !=null) { 
		exc=exception;
    } else {
   		exc=(Throwable)request.getAttribute(com.map.j2ee.exceptions.handler.ExceptionConstants.ERROR);
    }

	message=(String)request.getAttribute(com.map.j2ee.exceptions.handler.ExceptionConstants.ERROR_MESSAGE);
	if(message!=null) {
		try {
%>
	<s:text name="<%=message%>" />
<% 		} catch (Throwable e) {%>
			<%=message%>
<%      	}
	} %><br>
<% if (exc!=null) { %>

	<%=exc.toString()%>

<p> Con la siguiente traza
<pre>

<%
    java.io.ByteArrayOutputStream ostr = new java.io.ByteArrayOutputStream();
    exception.printStackTrace(new java.io.PrintStream(ostr));
    out.print(ostr);
%>
</pre>
<% } %>

</font>
</h2>
</body>
</html>
