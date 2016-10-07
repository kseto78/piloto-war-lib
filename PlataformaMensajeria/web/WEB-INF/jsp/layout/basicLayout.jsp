<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <sj:head jqueryui="true" compressed="false"/>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
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
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="Expire" content="now" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />

<script type="text/javascript">
	$(function(){

		// Accordion
		$("#accordion").accordion({ header: "h3" });

		// Tabs
		$('#tabs').tabs();

		// Dialog			
		$('#dialog').dialog({
			autoOpen: false,
			width: 600,
			show: 'blind',
			hide: 'blind',
			modal: true
		});
		
		// Dialog Link
		$('.dialog_link').click(function(){
			$('#dialog').dialog('open');
			return false;
		});		
		
		$('#dialog').dialog({
			autoOpen: false,
			width: 250,
			modal: true,
			show: 'blind',
			hide: 'blind',
		});

		// Datepicker
		$('.datepicker').datepicker({
			inline: true
		});				
						
		//hover states on the static widgets
		$('.dialog_link, ul#icons li').hover(
			function() { $(this).addClass('ui-state-hover'); }, 
			function() { $(this).removeClass('ui-state-hover'); }
		);
				
	});
</script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/messagingApp.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/grids.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/custom-theme/jquery-ui-1.8.custom.css"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.custom.min.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/aplicacionesExternas.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/menuhtml.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/listadoshtml.css" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/menu/menuExpandable.js"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/js/utils.js'></script>
<script type="text/javascript">
	var imgsrc="<%=request.getContextPath()%>/images/layouts/";
</script>
<s:head/>
</head>

<body>

<div id="generalContent">
	
	<div id="contenedor_principal">

	<div id="contmenu_nivel2">
		<div>
<%-- 			<tiles:insertAttribute name='menu'/> --%>
		</div>
	</div>
	<div id="contender_nivel2">
		<tiles:insertAttribute name='body'/>			

	</div>
	
	</div>
	
</div>

</body>

</html>