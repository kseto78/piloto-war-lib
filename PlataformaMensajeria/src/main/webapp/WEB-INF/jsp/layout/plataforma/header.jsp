<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<%@page import="es.mpr.template.web.action.admin.UsuariosForm"%>
<%@page import="es.mpr.plataformamensajeria.util.MapUser" %>
<%@page import="org.springframework.security.context.*" %>
<%@page import="org.springframework.security.*" %>
<%@page import="com.map.j2ee.security.perm.model.UserVO" %>
<%@page import="org.apache.commons.beanutils.locale.LocaleBeanUtils" %>
<%@page import="java.lang.reflect.InvocationTargetException" %>
<%@page import="es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil" %>
<%@page import="es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties" %>
<%@page import="org.springframework.security.core.GrantedAuthority" %>
<%@page import="java.util.Collection" %>
<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>

	<div id="header">
    	<div class="leftHeader" >
        	<h1>SIM <br /><span title="Version_1_0_1" >Sistema Integral de Mensajería</span></h1>
        	<p class="md-entorno">
        	<%
        	 String url = (String) request.getRequestURL().toString();
        	 String entorno = "";
        		if(url.indexOf("des") != -1) {
        			entorno = "DES";
        		} else if (url.indexOf("pre") != -1) {
        			entorno = "PRE";
        		}
        	%>
        	<%=entorno %></p>
            <div>
            	<p class="headerLinks">            	
            	<plataforma:securityadmin usuarioLogueado="true" showIfNotGranted="true">
                	<a href="home.action">Inicio</a>
                </plataforma:securityadmin> | <a href="<%=PlataformaMensajeriaProperties.getInstance().getProperty("url.caid", "")%>">Incidencias</a> |  <a href="<%=request.getContextPath()%>/logout">Salir</a> 
               </p>               
                <p class="userRol">
                	<strong>Usuario:</strong>
                	<label><sec:authentication property="principal.userInfo.nombre"/> <sec:authentication property="principal.userInfo.apellidos"/></label>
                </p>
                <p class="userRol">
                	<strong>Perfil:</strong>
                	<%
                		String tipoUsuario =(String) request.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
                		String tipoUsuarioCodificado = "";
                		if(tipoUsuario != null && tipoUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)){
                			tipoUsuarioCodificado="Administrador";
                		}else if(tipoUsuario != null && tipoUsuario.equals(PlataformaMensajeriaUtil.ROL_PROPIETARIO)){
                			tipoUsuarioCodificado="Propiertario";
                		}else if(tipoUsuario != null && tipoUsuario.equals(PlataformaMensajeriaUtil.ROL_CAID)){
                			tipoUsuarioCodificado="Caid";
                		}
                	%>
                	<label><%=tipoUsuarioCodificado%></label>
                </p>
            </div>
        </div>
    	<!-- <div class="rightHeader"></div> -->
    </div>
