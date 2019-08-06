<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/tlds/PlataformaMensajeriaTags.tld" prefix="plataforma" %>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado" permisoIdAplicacion="${idAplicacion}"  allowedTo="ROL_ADMINISTRADOR,ROL_PROPIETARIO,ROL_CAID">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR,ROL_PROPIETARIO,ROL_CAID">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">        	
		   	<h3 class="pageNameButtons">
    			<label>${detalleAplicacion.nombre}
	            </label>
            </h3>
    
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>            
            <%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
                    <sj:dialog  id="dialogPlanifications" title="Planificación" cssStyle="min-height:90px;display:none" autoOpen="false">
					     	<div class="editContainer">
					        <div class="nameDescription">
					            <label>Editar Días y horas</label>
					        </div>
					        <div class="editContent">
					                	 <p class="criteria">
					                     	<span>
					                            <label class="fieldText" style="width: 120px;">Días de la semana:</label>
					                            L
					                            <s:checkbox id="planificacion.lunes" name="planificacion.lunes" value="%{planificacion.lunes}" theme="simple"/>
					                            M
					                            <s:checkbox id="planificacion.martes" name="planificacion.martes" value="%{planificacion.martes}" theme="simple"/>                          
					                            X
					                            <s:checkbox id="planificacion.miercoles" name="planificacion.miercoles" value="%{planificacion.miercoles}" theme="simple"/>                         
					                            J
					                            <s:checkbox id="planificacion.jueves" name="planificacion.jueves" value="%{planificacion.jueves}" theme="simple"/>                        
					                            V
					                            <s:checkbox id="planificacion.viernes" name="planificacion.viernes" value="%{planificacion.viernes}" theme="simple"/>  
					                            S
					                            <s:checkbox id="planificacion.sabado" name="planificacion.sabado" value="%{planificacion.sabado}" theme="simple"/>   
					                            D
					                            <s:checkbox id="planificacion.domingo" name="planificacion.domingo" value="%{planificacion.domingo}" theme="simple"/>              
											</span>
					                   <p class="criteria">
					                 		<label class="fieldText" style="width: 120px;">Hora Inicio:</label>
					                       		 <s:select 
												id="planificacion.horaDesde" name="planificacion.horaDesde" 
												emptyOption="false" theme="simple" 
												labelposition="left"
												list="comboHorasInicio" listKey="codigo"
												listValue="descripcion" cssClass="W65" 
												value="%{planificacion.horaDesde}" disabled="false" />
					                        Hora Fin:  <s:select 
												id="planificacion.horaHasta" name="planificacion.horaHasta" 
												emptyOption="false" theme="simple" 
												labelposition="left"
												list="comboHorasFin" listKey="codigo"
												listValue="descripcion" cssClass="W65" 
												value="%{planificacion.horaHasta}" disabled="false" />
					                    </p>                  
					        </div>
					    </div>
					    <div class="footerPopup">
					    	<span class="leftSide"></span>
					        <span class="rightSide">
					            <input type="submit" value="Guardar" class="button" />
					        </span>  	
					    </div>
			   </sj:dialog>
			   <div class="editContainer">
            	<div class="nameDescription">
                	<label>Datos Generales</label>
                </div>
            	<div class="editContent">
                	<p class="criteria">
                	    <label theme="simple" style="width: 120px;" class="fieldText">Nombre:</label>
                       	<strong><s:label value="%{detalleAplicacion.nombre}"/></strong>
                    </p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Descripción:</label>
							<strong><s:label value="%{detalleAplicacion.descripcion}"/></strong>
                    </p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Activo:</label>
                        
                        ${detalleAplicacion.isActivo}
                    </p>
                </div>
            </div>
            <div class="editContainer">
            	<div class="nameDescription">
                	<label>Seguridad</label></div><div class="editContent">
            	  	<p class="criteria">
            	    	<label theme="simple" class="fieldText" style="width: 120px;">Usuario:</label>
	            	    <strong>${detalleAplicacion.usuario}</strong>
    				</p>
            	  	<p class="criteria">
            	    	<label theme="simple" class="fieldText" style="width: 120px;">Contraseña:</label>
						<strong>${detalleAplicacion.passwordUnhashed}</strong>
          	  </div>
            </div>
            <div class="editContainer">
            	<div class="nameDescription">
                	<label>Usuarios</label>
                </div>
                <div class="editContent">
                <table border="0" cellpadding="0" cellspacing="0">
                        <thead>
                            <tr>
                               
                                <th class="TH200">Nombre</th>
                                <th class="TH200">Modo</th>
                            </tr>
                        </thead>
                        <!-- listaServiciosAplicacion -->
                        <tbody>
							<s:iterator value="%{detalleAplicacion.listaUsuariosAplicacion}" var="usuarioAplicacion" status="usuarioAplicacionStatus">
		            	      <tr class="<s:if test='#usuarioAplicacionStatus.odd == true '>odd</s:if>">
		            	      	<td><s:label value="%{nombreUsuario}" theme="simple"/></td>
		            	      	<td><s:label value="%{modoLectura}" theme="simple"/></td>
		          	          </tr>
		          	      </s:iterator>
                       
                      
                        <s:if test="%{detalleAplicacion.listaUsuariosAplicacion == null}">
                         	<tr>
                         		<td colspan="2">
                         		
                         			La aplicación no tienen ningún usuario asociado
                         		
                         		</td>
                         	</tr>
                         	                
                        </s:if>
                        </tbody>
                        </table>
		<!-- 		<table border="0" cellpadding="0" cellspacing="0">

                        <thead>
                            <tr>
                                <th class="TH200">Nombre</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Jaime del Álamo García</td>

                            </tr>
                            <tr class="odd">
                                <td>Pedro Carioca Fernández</td>
                            </tr>
                            <tr>
                                <td>John Arthur Lewis Sinclair</td>
                            </tr>
                        </tbody>

                  </table>-->
          	    </div>
            </div>
        <div class="editContainer">
           	  <div class="nameDescription">
                	<label>Servicios</label>
                    <p>Conjunto de servicios asociados a la aplicación.</p>
                </div>

            	<div class="editContent">        
            	  <s:iterator value="%{detalleAplicacion.listaServicios}" var="detalleServicio" status="detalleAplicacionStatus">        	
                  <div class="contentService">
                 
                   	<h3>SERVICIO ${detalleAplicacionStatus.index +1} 
                   		<span class="openServiceConfig" id="openConfig_${detalleServicio.servicioId}">Abrir config</span>
                   	</h3>
                   	
                  		<span class="rightSide">
	                    	<input  type="button" value="Editar Servicio" class="button" onclick='javascript:location.href="editServicioAplicacion.action?idServicio=${detalleServicio.servicioId}&idAplicacion=${detalleAplicacion.aplicacionId}"'/>
	                	</span>
		                
                   	    <p class="criteria">
                            <label class="fieldText" style="width: 120px;">Identificador:</label>
                            <strong>${detalleServicio.servicioId}</strong>
                        </p>
                        <p class="criteria">
                            <label class="fieldText" style="width: 120px;">Nombre:</label>
                            <strong>${detalleServicio.nombre}</strong>
                        </p>
                        
                        <s:if test="#detalleServicio.descripcion != null">
	                        <p class="criteria">
	                            <label class="fieldText" style="width: 120px;">Descripción:</label>
	                            <strong style="width: 450px;">${detalleServicio.descripcion}</strong>
	                        </p>
	                    </s:if>
	                    
                        <p class="criteria">
                            <label class="fieldText" style="width: 120px;">Canal:</label>
                            <strong>${detalleServicio.canalNombre}</strong>
                        </p>
						<s:if test="#detalleServicio.canalId==1 && #detalleServicio.fromMail != null">
							<p class="criteria">
								<label class="fieldText" style="width: 120px;">Cuenta Env&iacute;o:</label>
								<strong>${detalleServicio.fromMail}</strong>
							</p>								
							<s:if test="#detalleServicio.fromMailName != null">
								<p class="criteria">
									<label class="fieldText" style="width: 120px;">Nombre C. Env&iacute;o:</label>
									<strong>${detalleServicio.fromMailName}</strong>
								</p>
							</s:if>
						</s:if>
						
						<s:if test='#detalleServicio.historificacion != null'>
							<p class="criteria">
	                            <label class="fieldText" style="width: 120px;">Historificación:</label>
	                            <strong>${detalleServicio.historificacion} días</strong>
	                        </p>
                        </s:if>
                        
                        <s:if test='#detalleServicio.conservacion != null'>
	                        <p class="criteria">
	                            <label class="fieldText" style="width: 120px;">Conservación:</label>
	                            <strong>${detalleServicio.conservacion} años</strong>
	                        </p>
                        </s:if>
                        
                        <s:if test='#detalleServicio.pendienteaprobacion == "1"'>
	                        <p class="criteria">
	                            <label class="fieldText" style="width: 120px;">Estado</label>
	                            <strong>Pendiente de aprobación</strong>
	                        </p>
	                    </s:if>
	                    <s:else>
	                    	<p class="criteria">
                            	<label class="fieldText" style="width: 120px;">Activo</label> 
                            	${detalleServicio.isActivo}    
                        	</p>
	                    </s:else>
                        
                        <div class="serviceConfig" id="serviceConfig_${detalleServicio.servicioId}" style="display:none">
							<table cellspacing="0" cellpadding="0" border="0">
								<thead>
							 		<tr>
							        	<th class="superHeader" colspan="2">
							            	<div class="floatLeft">Configuraciones</div>
							             	<div class="floatRight"></div>
							         	</th>
							     	</tr>
								     <tr>
								         <th class="TH200">Servidor / Proveedor</th>
								         <th class="separator">Nº reintentos</th>
								     </tr>
								 </thead>
								 <tbody>
									 <s:iterator value="#detalleServicio.listaServidoresServicios" var="servidorServicio" status="servidorServicioStatus">
										 <s:if test='#servidorServicio != null'>
											 <tr class="<s:if test='#servidorServicioStatus.odd == true '></s:if><s:else>odd</s:else>">
												 <td>${servidorServicio.nombreServidor}</td>
												 <td>${servidorServicio.numIntentos}</td>
											 </tr> 
										 </s:if>
									 </s:iterator>
									 <s:if test='#detalleServicio.listaServidoresServicios == null'>
									 	<tr>
									 		<td colspan="2">No se ha configurado servidor para el servicio</td>
								 		</tr>
									 </s:if>
								 </tbody>
							</table>
	
		                    <table cellspacing="0" cellpadding="0" border="0">
	                           <thead>
	                            	<tr>
	                                    <th class="superHeader" colspan="6">
	                                        <div class="floatLeft">Planificaciones</div>
	                                        <div class="floatRight">
	                                    <plataforma:puedeEditarAplicacion idAplicacion="${idAplicacion}">    
											<div id="ajaxloader_ajaxNewPlan_${detalleServicio.servicioId}">
												<input type="button" id="ajaxNewPlan_${detalleServicio.servicioId}" name="ajaxNewPlan" onclick="return newPlan(this,${idAplicacion},${detalleServicio.servicioId})" class="button" value="Nuevo"> 
		                                	</div>
		                                </plataforma:puedeEditarAplicacion>
	                                	</div>
	                                    </th>
	                                </tr>
	                                <tr>
	                                    <th class="TH200">Días</th>
	                                    <th class="separator">Hora Inicio</th>
	                                    <th class="separator">Hora Fin</th>
	                                    <th class="separator">Servidor / Proveedor</th>
	                                    <plataforma:puedeEditarAplicacion idAplicacion="${idAplicacion}">
	                                    <th class="separator"></th>
	                                    </plataforma:puedeEditarAplicacion>
	                                </tr>
	                            </thead>
		                        <tbody>
		                        <s:iterator value="#detalleServicio.listaPlanificaciones" var="planificacionServicio" status="planificacionServicioStatuts">
		                             <tr colspan="6" class="<s:if test='#planificacionServicioStatuts.odd == true '></s:if><s:else>odd</s:else>">
		                               <td>${planificacionServicio.dias}</td>
		                                <td>${planificacionServicio.horaDesde}</td>
		                                <td>${planificacionServicio.horaHasta}</td>
		                                <td>${planificacionServicio.nombreServidor}</td>
		                                <plataforma:puedeEditarAplicacion idAplicacion="${idAplicacion}">
		                                <td>
											<span class="edit"> 
											<div id="ajaxloader_ajax_${planificacionServicio.planificacionId}">
											<span id="ajax_${planificacionServicio.planificacionId}" title="Editar" name="ajax" onclick="return loadPlan(this,${planificacionServicio.planificacionId},${idAplicacion},'idAplicacion','updatePlanificacionApp')" class="btnEdit planifications_link"></span> 
											</div>
											
											</span>
		                                	<span class="delete"> <a class="btnDelete" title="Eliminar" onclick="return confirmDelete();" href="deletePlanificacionServicioViewApp.action?idPlanificacion=${planificacionServicio.planificacionId}&idAplicacion=${idAplicacion}"></a> </span>
		                                </td>
		                                </plataforma:puedeEditarAplicacion>
		                           </tr>
		                        </s:iterator>
		                        <s:if test='#detalleServicio.listaPlanificaciones == null'>
		                                <tr><td colspan="6">No se ha configurado ninguna planificación para el servicio</td></tr>
		                            </s:if>
		                        </tbody>
		                    </table>
                        </div>
                    </div>
                    <div class="serviceSeparator"></div>  
                    </s:iterator>
                    
                    <span class="rightSide">
                    	<input  type="button" value="Nuevo Servicio" class="button" onclick='javascript:location.href="nuevoServicioAplicacion.action?idAplicacion=${detalleAplicacion.aplicacionId}"'/>
                    </span>
                    
					<script type="text/javascript">
					
					 $('.openServiceConfig').click(showContent);
					 
					 function showContent(){
						 var elementID = $(this).attr('id').split('_')[1];
						 $('#serviceConfig_'+elementID).slideToggle();
						 if ($(this).text() == 'Abrir config')
							 $(this).text('Cerrar config');
						 else
							 $(this).text('Abrir config');
						 
					 };
					
					</script>
			       