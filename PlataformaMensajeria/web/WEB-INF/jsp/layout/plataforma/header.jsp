<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<%@page import="es.mpr.template.web.action.admin.UsuariosForm"%>
<%@page import="com.map.j2ee.security.auth.*" %>
<%@page import="org.springframework.security.context.*" %>
<%@page import="org.springframework.security.*" %>
<%@page import="com.map.j2ee.security.perm.model.UserVO" %>
<%@page import="org.apache.commons.beanutils.locale.LocaleBeanUtils" %>
<%@page import="java.lang.reflect.InvocationTargetException" %>
<%@page import="es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil" %>
<%@page import="es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties" %>
<%  
	
	UsuariosForm form = new UsuariosForm();
	MapUser detallesUsuario = (MapUser) 
	SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
	
	GrantedAuthority[] roles=detallesUsuario.getAuthorities();

for (int i = 0; i < roles.length; i++) {
	GrantedAuthority grantedAuthority = roles[i];
  System.out.print(grantedAuthority.getAuthority());
}


/*
* Obtenemos todos los datos almacenados de este usuario en el LDAP.
*/

	UserVO tmpUserVO = (UserVO) detallesUsuario.getUserInfo();

/*
* Copiamos los datos al bean del formulario para que se muestren en la jsp.
*/

//LocaleBeanUtils beanUtils = new LocaleBeanUtils();
try {
	LocaleBeanUtils.copyProperties(form,tmpUserVO);
} catch (IllegalAccessException e) {
  e.printStackTrace();
} catch (InvocationTargetException e) {
  e.printStackTrace();
}
	%>
	<div id="header">
    	<div class="leftHeader" >
        	<h1>SIM <br /><span title="Version_1_0_1" >Sistema Integral de Mensajería</span></h1>
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
                		}
                	%>
                	<label><%=tipoUsuarioCodificado%></label>
                </p>
            </div>
        </div>
    	<!-- <div class="rightHeader"></div> -->
    </div>
