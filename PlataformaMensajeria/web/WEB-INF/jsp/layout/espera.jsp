<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<!doctype html public "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title><tiles:getAsString name="title" /></title>
<meta content="text/html; charset=iso-8859-1" http-equiv=content-type>
<LINK REL=StyleSheet HREF="./css/estilos.css" type=text/css>
</head>
<script>
function cerrar(){	
	if(window.opener.location.href.indexOf('home.do')>0) {
		//si es la home, recagar en GET
		window.opener.location.href='<%=request.getContextPath()%>/home.do';
	} else {
		window.opener.location.reload();
	}
	window.opener.focus();
	window.close();	
}
</script>
<body onload="cerrar();">

<table cellpadding="0" cellspacing="0" border="0" class="tabla_principal" align="center">
	<!-- logo y cabecera -->
	<tr>
		<td class="tabla_principal_td2">&nbsp;</td>
	</tr>
	<tr>
		<td class="tabla_principal_td4">
		<!--  ÁREA DE CONTENIDOS	-->
			<table cellpadding="0" cellspacing="0" border=0 class="agenda_tabla">
				<!-- título agenda	-->
				<tr>
					<td colspan="3" class="agenda_titulo">Espere, por favor</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</body>
</html>
