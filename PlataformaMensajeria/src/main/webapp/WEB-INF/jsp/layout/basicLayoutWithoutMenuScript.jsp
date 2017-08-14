<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<tiles:importAttribute name="title"/>
<title><s:text name="%{#attr['title']}"/></title>
<%-- Tenplate Basico
  Template en el que sea crea una cabecera, un menu a la izquierda, el
  cuerpo y un pie

  @param title Titulo de la pagina
  @param header Cabecera
  @param menu Menu
  @param body Body
  @param footer Pie
--%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="Expire" content="now" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/aplicacionesExternas.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/menuhtml.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/listadoshtml.css" type="text/css" />

</head>

<body>

<div id="contenedor">

	<div id="contenedor_principal">

	<div id="contmenu_nivel2">
		<div>
			<tiles:insertAttribute name='menu'/>
		</div>
	</div>
	<div id="contender_nivel2">
		<tiles:insertAttribute name='body'/>			

	</div>
	
	</div>
	
</div>

</body>

</html>