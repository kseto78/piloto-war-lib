<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<%@ taglib prefix="framework" uri="/framework" %>
<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>

<div class="menu">  	
    	<plataforma:securityadmin usuarioLogueado="true" showIfNotGranted="false" showIfGranted="true">
    		<div >
    			<h3 id="desplegable0"><a href="home.action">Escritorio</a></h3>
    		</div>             
       </plataforma:securityadmin>  	
        <plataforma:securityadmin usuarioLogueado="true" showIfGranted="true">
        	<h2>configuración</h2>
				<div>
		        	<h3><span onclick="fncDesplegarMenu(1);" class="menuDesplegable">Configuración General</span></h3>
 			          	<div id="desplegable1">
			              <ul>
 			          		<li><a href="buscarServidores.action" id="desplegable11" class="servSMTP" >Servidores SMTP</a></li>
							<li><a href="buscarProveedorSMS.action" id="desplegable12" class="ProveSMS" >Proveedores SMS</a></li>
							<li><a href="buscarProveedoresMisim.action"  id="desplegable13" class="RecepSMS">Proveedores MISIM</a></li>
							<li><a href="buscarReceptorSMS.action"  id="desplegable14" class="RecepSMS">Receptores SMS</a></li>
							<li><a href="buscarServidorPush.action" id="desplegable15" class="RecepSMS">Servidores Push</a></li> 
							<li><a href="buscarServidorWebPush.action" id="desplegable16" class="RecepSMS">Servidores Web Push</a></li> 
			              </ul> 
		                </div>
					
		                <h3><span onclick="fncDesplegarMenu(2);" class="menuDesplegable">Configuración Aplicaciones</span></h3>
						<div id="desplegable2">
<!-- 						<div id="desplegable2">  -->
			                <ul>
			                    <li><a href="buscarAplicaciones.action" id="desplegable21" class="Aplicaciones">Aplicaciones</a></li>
			                    <li><a href="buscarServicios.action" id="desplegable22" class="Servicios">Servicios</a></li>
			                    <li><a href="buscarPlanificaciones.action" id="desplegable23" class="Planificaciones">Planificaciones</a></li>			                	
	 		                	<li><a href="buscarServiciosMoviles.action" id="desplegable25" class="Servicios">Servicios Moviles</a>
			                </ul>          
		                </div>  
		                   <h3><span onclick="fncDesplegarMenu(3)" class="menuDesplegable">Configuración Seguridad</span></h3>
 		                <div id="desplegable3">
<!-- 						<div id="desplegable3"> -->
			                <ul>	
			                    <li><a href="buscarUsuarios.action" id="desplegable31" class="Usuarios">Usuarios</a></li>
			                    <!--<li><a href="../../Seguridad/Roles/Creacion.html" class="Roles">Roles</a></li>-->
			                </ul>
			             </div>
			             <h3><span onclick="fncDesplegarMenu(20)" class="menuDesplegable">Configuración Organismos</span></h3>
 		                <div id="desplegable20">
			                <ul>	
			                	<li><a href="listarOrganismos.action" id="desplegable201" class="Usuarios">Organismos</a></li>
			                	
			                    <li><a href="altasMasivasOrganismosView.action" id="desplegable202" class="Usuarios">Altas masivas</a></li>
			                   	<li id="desplegable204" style="display:none"><a style="margin-left:26px; width: 110px;" 
			                    href="listarPdpDiputaciones.action" class="Usuarios" id="desplegable203">PdP-diputaciones</a></li>
			                    <!--<li><a href="../../Seguridad/Roles/Creacion.html" class="Roles">Roles</a></li>-->
			                </ul>
			             </div>
		            </div>
		            <div>
		            	<h3 id="desplegable4">
		            	<a href="buscarAuditoriasPlataforma.action">Auditoría</a>
		            	</h3>
		            	<h3 id="desplegable5">
		            	<a href="decodHome.action" >Decodificador</a>
		            	</h3>
		            </div>
            </plataforma:securityadmin>
            <plataforma:securityadmin usuarioLogueado="true" showIfNotGranted="true" showIfGranted="false">
            <h2>gestión</h2>
            <div>
                <h3 id="desplegable6"><a href="buscarGestionEnvios.action">Gestión de Envíos</a></h3>

                <h3 id="desplegable7"><a href="buscarEstadisticas.action">Estadísticas Generales</a></h3>
                
               <h3 id="desplegable8"><a href="buscarUsuariosPush.action">Usuarios Push</a></h3> 
               
                <h3 id="desplegable9"><a href="buscarUsuariosWebPush.action">Usuarios Web Push</a></h3>
                
                 <h3 id="desplegable10"><a href="envioMensajesHome.action">Envío de Mensajes</a></h3>                 
                <!-- <ul>
                    <li><a href="#" class="CanalEMAIL">Canal EMAIL</a></li>
                    <li><a href="#" class="CanalSMS">Canal SMS</a></li>

                </ul>
 				-->                
		
			</plataforma:securityadmin>
             <plataforma:securityadmin usuarioLogueado="true" showIfNotGranted="false" showIfGranted="true">
            <h2>gestión</h2>
            <div>
                <h3 id="desplegable6"><a href="buscarGestionEnvios.action">Gestión de Envíos</a></h3>

                <h3 id="desplegable7"><a href="buscarEstadisticas.action">Estadísticas Generales</a></h3>
                
                <h3 id="desplegable8"><a href="buscarUsuariosPush.action">Usuarios Push</a></h3> 
                
                <h3 id="desplegable9"><a href="buscarUsuariosWebPush.action">Usuarios Web Push</a></h3> 
                
                <h3 id="desplegable10"><a href="envioMensajesHome.action">Envío de Mensajes</a></h3> 
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
							<li><a id='${ap.aplicacionId}' href="viewAplicacion.action?idAplicacion=${ap.aplicacionId}" class="BBDD060">${ap.nombre}</a></li>						
				</s:iterator>
            	
                </ul>
            </div>
            </plataforma:securityadmin>
            
            
        </div>
        
        
<script language="Javascript">

window.onload = new function cargar(){

	//Agrega la compatibilidad para IE para el metodo string.endsWith
	if (!String.prototype.endsWith) {
		  String.prototype.endsWith = function(searchString, position) {
		      var subjectString = this.toString();
		      if (typeof position !== 'number' || !isFinite(position) 
		          || Math.floor(position) !== position || position > subjectString.length) {
		        position = subjectString.length;
		      }
		      position -= searchString.length;
		      var lastIndex = subjectString.indexOf(searchString, position);
		      return lastIndex !== -1 && lastIndex === position;
		  };
		}

	//Agrega la compatibilidad para IE para el metodo string.includes
	if (!String.prototype.includes) {
		  Object.defineProperty(String.prototype, 'includes', {
		    value: function(search, start) {
		      if (typeof start !== 'number') {
		        start = 0
		      }
		      
		      if (start + search.length > this.length) {
		        return false
		      } else {
		        return this.indexOf(search, start) !== -1
		      }
		    }
		  })
		}
	
	var url = window.location.href;

	var deplegable1= document.getElementById("desplegable1");
	var deplegable2= document.getElementById("desplegable2");
	var deplegable3= document.getElementById("desplegable3");
	var deplegable20= document.getElementById("desplegable20");
	var elem1 = "";
	var menu1 = "";

	if(url.endsWith('infoHome.action')){
		elem1 = document.getElementById('desplegable0');
		elem1.style.backgroundColor="#003366";
		menu1=4;
	}else if(url.endsWith('buscarServidores.action') || (url.includes('Servidor.action'))){
		elem1 = document.getElementById('desplegable11');
		elem1.style.backgroundColor="#003366";
		elem1.style.color = "white";
		menu1=1;
	}else if(url.includes('ProveedorSMS.action')){
		elem1 = document.getElementById('desplegable12');
		elem1.style.backgroundColor="#003366";
		elem1.style.color = "white";
		menu1=1;
	}else if(url.endsWith('buscarProveedoresMisim.action') || (url.includes('ProveedorMisim.action'))){
		elem1 = document.getElementById('desplegable13');
		elem1.style.backgroundColor="#003366";
		elem1.style.color = "white";
		menu1=1; 
	}else if(url.includes('ReceptorSMS.action')){
		elem1 = document.getElementById('desplegable14');
		elem1.style.backgroundColor="#003366";
		elem1.style.color = "white";
		menu1=1;
	}else if(url.includes('ServidorPush.action')){
		elem1 = document.getElementById('desplegable15');
		elem1.style.backgroundColor="#003366";
		elem1.style.color = "white";
		menu1=1;
	}else if(url.includes('ServidorWebPush.action')){
		elem1 = document.getElementById('desplegable16');
		elem1.style.backgroundColor="#003366";
		elem1.style.color = "white";
		menu1=1;
	}else if(url.endsWith('buscarAplicaciones.action') || url.includes('editAplicacion.action') || url.includes('nuevaAplicacion.action')){
		elem1 = document.getElementById('desplegable21');
		elem1.style.backgroundColor="#003366";
		elem1.style.color = "white";
		menu1=2;
	}else if(url.endsWith('buscarServicios.action') || url.includes('Servicio.action') || url.includes('aplicacionSelectEvent.action')){
		elem1 = document.getElementById('desplegable22');
		elem1.style.backgroundColor="#003366";
		elem1.style.color = "white";
		menu1=2;		
	}else if(url.endsWith('buscarPlanificaciones.action') || url.includes('Planificacion.action')){
		elem1 = document.getElementById('desplegable23');
		elem1.style.backgroundColor="#003366";
		elem1.style.color = "white";
		menu1=2;	
	}else if(url.includes('Organismos') || url.includes('Organismo') || url.includes('PdpDiputaciones')){						
			if(url.includes('altasMasivas')){
				elem1 = document.getElementById('desplegable202');
				elem1.style.backgroundColor="#003366";
				elem1.style.color = "white";
				document.getElementById("desplegable204").style.display = "";
			} else if(url.includes('PdpDiputaciones')){
				elem1 = document.getElementById('desplegable203');
				elem1.style.backgroundColor="#003366";
				elem1.style.color = "white";
				document.getElementById("desplegable204").style.display = "";
			} else{
				elem1 = document.getElementById('desplegable201');
				elem1.style.backgroundColor="#003366";
				elem1.style.color = "white";
				}
		
		menu1=20;
	}else if(url.endsWith('buscarServiciosMoviles.action') || url.includes('ServicioMovil.action')){
		elem1 = document.getElementById('desplegable25');
		elem1.style.backgroundColor="#003366";
		elem1.style.color = "white";
		menu1=2;	
	}else if(url.endsWith('buscarUsuarios.action') || url.includes('Usuario.action')){
		elem1 = document.getElementById('desplegable31');
		elem1.style.backgroundColor="#003366";
		elem1.style.color = "white";
		menu1=3;	
	}else if(url.includes('AuditoriasPlataforma.action')){
		elem1 = document.getElementById('desplegable4');
		elem1.style.backgroundColor="#003366";
		menu1=4;
	}else if(url.endsWith('decodHome.action') || url.includes('decodificador.action')){
		elem1 = document.getElementById('desplegable5');
		elem1.style.backgroundColor="#003366";
		menu1=4;
	}else if(url.includes('GestionEnvios.action')){
		elem1 = document.getElementById('desplegable6');
		elem1.style.backgroundColor="#003366";
		menu1=4;
	}else if(url.endsWith('buscarEstadisticas.action')){
		elem1 = document.getElementById('desplegable7');
		elem1.style.backgroundColor="#003366";
		menu1=4;
	}else if(url.includes('UsuariosPush.action')){
		elem1 = document.getElementById('desplegable8');
		elem1.style.backgroundColor="#003366";
		menu1=4;
	}else if(url.includes('UsuariosWebPush.action')){
		elem1 = document.getElementById('desplegable9');
		elem1.style.backgroundColor="#003366";
		menu1=4;
	}
	else if(url.endsWith('envioMensajesHome.action') || url.endsWith('envioMensajesAplicacion.action')){
		elem1 = document.getElementById('desplegable10');
		elem1.style.backgroundColor="#003366";
		menu1=4;
	}else if(url.includes('viewAplicacion.action')){

		var el = document.getElementById("listaAplicacionesUsuario").getElementsByTagName("aplicacionId");
		
// 		for(var i = 0; i < '%{listaAplicacionesUsuario}'.length; i++){
// 			if(url.includes("idAplicacion='${i.aplicacionId}")){
// 				elem1 = document.getElementById('num'+'${ap.aplicacionId}');

// 				alert('${ap.aplicacionId}');
// 				elem1.style.backgroundColor="#003366";
// 				elem1.style.color = "white";
// 				menu1=4;
// 				break;
// 			}
// 		}

	}

	if(menu1 == 1){
		deplegable2.style.display='none';
		deplegable3.style.display='none';
		deplegable20.style.display='none';
	}else if(menu1 == 2){
		deplegable1.style.display='none';
		deplegable3.style.display='none';
		deplegable20.style.display='none';
	}else if(menu1 == 3){
		deplegable1.style.display='none';
		deplegable2.style.display='none';
		deplegable20.style.display='none';
	}else if(menu1 == 4){
		deplegable1.style.display='none';
		deplegable2.style.display='none';
		deplegable3.style.display='none';
		deplegable20.style.display='none';
	}else if(menu1 == 20){
		deplegable1.style.display='none';
		deplegable2.style.display='none';
		deplegable3.style.display='none';		
	}


		
};


</script>