<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>


<%@ taglib prefix="framework" uri="/framework" %>
		
    	<div class="menu">
        	<plataforma:securityadmin usuarioLogueado="true" showIfGranted="true">
        	<h2>configuración</h2>
 					<div>
		        	<h3><span>Configuración General</span></h3>
		                <ul>
		                    <li><a href="buscarServidores.action" class="servSMTP">Servidores SMTP</a></li>
		                    <li><a href="buscarProveedorSMS.action" class="ProveSMS">Proveedores SMS</a></li>
		                    <li><a href="buscarReceptorSMS.action" class="RecepSMS">Receptores SMS</a></li> 
 		                    <li><a href="buscarServidorPush.action" class="RecepSMS">Servidores Push</a></li> 
		                </ul> 
					
		                <h3><span>Configuración Aplicaciones</span></h3>
		
		                <ul>
		                    <li><a href="buscarAplicaciones.action" class="Aplicaciones">Aplicaciones</a></li>
		                    <li><a href="buscarServicios.action" class="Servicios">Servicios</a></li>
		                    <li><a href="buscarPlanificaciones.action" class="Planificaciones">Planificaciones</a></li>
		                	<li><a href="buscarOrganismos.action" class="Usuarios">Organismos</a></li>
<!-- 		                	<li><a href="buscarServiciosMoviles.action" class="Servicios">Servicios Moviles</a> -->

		                </ul>            
		                <h3><span>Configuración Seguridad</span></h3>
		                <ul>
		
		                    <li><a href="buscarUsuarios.action" class="Usuarios">Usuarios</a></li>
		                    <!--<li><a href="../../Seguridad/Roles/Creacion.html" class="Roles">Roles</a></li>-->
		                </ul>
		            </div>
		            <div>
		            	<h3>
		            	<a href="buscarAuditoriasPlataforma.action">Auditoría</a>
		            	</h3>
		            	<h3>
		            	<a href="decodHome.action">Decodificador</a>
		            	</h3>
		            </div>
            </plataforma:securityadmin>
            <plataforma:securityadmin usuarioLogueado="true" showIfNotGranted="true" showIfGranted="false">
            <h2>gestión</h2>
            <div>
                <h3><a href="buscarGestionEnvios.action">Gestión de Envíos</a></h3>

                <h3><a href="buscarEstadisticas.action">Estadísticas Generales</a></h3>
                
               <h3><a href="buscarUsuariosPush.action">Usuarios Push</a></h3> 
                <!-- <ul>
                    <li><a href="#" class="CanalEMAIL">Canal EMAIL</a></li>
                    <li><a href="#" class="CanalSMS">Canal SMS</a></li>

                </ul>
 				-->                
		
			</plataforma:securityadmin>
             <plataforma:securityadmin usuarioLogueado="true" showIfNotGranted="false" showIfGranted="true">
            <h2>gestión</h2>
            <div>
                <h3><a href="buscarGestionEnvios.action">Gestión de Envíos</a></h3>

                <h3><a href="buscarEstadisticas.action">Estadísticas Generales</a></h3>
                
                <h3><a href="buscarUsuariosPush.action">Usuarios Push</a></h3> 
                <!-- <ul>
                    <li><a href="#" class="CanalEMAIL">Canal EMAIL</a></li>
                    <li><a href="#" class="CanalSMS">Canal SMS</a></li>

                </ul>
 				-->                
		
			</plataforma:securityadmin>
			</div>
			<div>
			
			
            </div>
            <plataforma:securityadmin usuarioLogueado="true" showIfNotGranted="true">
            <h2>aplicaciones</h2>
            <div>
            	<ul>

            	<s:iterator value="%{listaAplicacionesUsuario}" var="ap"
							status="aplicacionStatus">
							<li><a href="viewAplicacion.action?idAplicacion=${ap.aplicacionId}" class="BBDD060">${ap.nombre}</a></li>
				</s:iterator>
            	
                </ul>
            </div>
            </plataforma:securityadmin>
        </div>
	
