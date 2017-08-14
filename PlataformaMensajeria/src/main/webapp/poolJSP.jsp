<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TemplateApplicationv3</title>
</head>
<body>
<form action="pool.action">
<% 
if (request.getAttribute("pool")!= null) {
		%>
<label>"El objeto del Pool es "+ <%= request.getAttribute("pool") %></label>
<% }
else { %>
	<input type="submit" value="Comprobar Pool">
<% }
%>
</form>
</body>
</html>