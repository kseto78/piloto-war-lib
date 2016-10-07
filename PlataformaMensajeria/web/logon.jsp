<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<%@page import="es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Plataforma de Mensajeria</title>
<meta http-equiv="Expire" content="now" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/login.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/messagingApp_new.css" />
<s:head />
</head>

<body>
	<div id="loginContainer">
		<div class="northLay">
			<img src="img/logo_ministerio.png" height="105px" style="margin: 6px"
				alt="logo Ministerio de Politica Territorial y Administración Pública">
		</div>
		<div class="centerlay" style="height: 600px">
			<div style="float: left; width: 30%; margin-top: 120px">
				<img src="img/logo_sim_2.png" width="340px"
					style="margin-left: 30px">
			</div>
			
			<form action="<%=PlataformaMensajeriaProperties.getInstance().getProperty("url.autentica", "")%>" method=post name="formulario"> 
				
			</form>
			
			
			<div class="rightLay">
				<div align="center" class="formularioLogin">
					<p align="left"
						style="font-size: 16px; padding: 10px; font-weight: bold;">
						El acceso a SIM requiere que te identifiques mediante el portal de
						AUTENTICA a trav&eacute;s de:
						<div align="left">
							<ul align="left"
								style="font-size: 15px; padding: 6px; margin-top: 18px">
								<li>- Certificado digital</li>
								<li>- Usuario y contrase&ntilde;a</li>
							</ul>
						</div>
					</p>
					<img src="img/logoautentica.png"
						onclick="javascript:enviar_formulario()"
						style="cursor: pointer;position: relative; top: -60px; left: 140px">
				</div>
			</div>

			<div style="clear: both;"></div>
		</div>
		<div id="loginFooter">
			<p>
				Copyright &copy; 2011. Ministerio de Hacienda y Administraciones
				P&uacute;blicas <br /> Todos los derechos reservados.
			</p>
		</div>
	</div>
	<script> 
		function enviar_formulario(){ 
		   document.formulario.submit();
		} 
	</script>
</body>

</html>