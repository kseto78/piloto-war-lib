<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Plataforma de Mensajeria</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="JavaScript"> 
	function cargarMenu(){ 
		document.forms[0].submit();
	
	} 
</script>

</head>
<body onload="cargarMenu()">
<form name="indexForm" method="GET" action="home.action"></form>
</body>
</html>