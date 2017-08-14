<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">        	
		<s:form id="frmEditServidor" method="POST" action="updateProveedorSMS" validate="false" theme="simple" cssClass="">
        	<h3 class="pageNameButtons">
    			<span class="floatRight">
    				
                	<s:submit  theme="simple" value="%{getText('buttons.text.save')}" cssClass="button"/>
                    <input type="button" onclick="javascript:location.href='${volver}'" class="button" value="Volver">
                </span>
	            <label>EDICIÓN PROVEEDOR SMS
	            </label>
            </h3> 
        <%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
		<sj:dialog  id="dialogPlanifications" title="Planificación" cssStyle="min-height:150px;display:none" autoOpen="false">
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
					            <input type="button" value="Guardar" class="button" />
					        </span>  	
					    </div>
			   </sj:dialog>		
            <div class="editContainer">
            	<div class="nameDescription">
                	<label>Datos Generales</label>
                </div>
            	<div class="editContent">
                	<p class="criteria">
                	<s:hidden theme="simple" id="proveedorSMS.proveedorSMSId" name="proveedorSMS.proveedorSMSId" value="%{proveedorSMS.proveedorSMSId}"/>
                	<s:hidden theme="simple" id="proveedorSMSId" name="proveedorSMSId" value="%{proveedorSMS.proveedorSMSId}" />
                        <label  style="width: 120px;" class="fieldText">Nombre (*):</label>
                        <s:textfield
						name="proveedorSMS.nombre" value="%{proveedorSMS.nombre}" id="proveedorSMS.nombre"
						theme="simple" 
						labelposition="left"
						 size="85" maxlength="255"
						cssClass=""/>
					</p>
					<p class="criteria">
						<label  style="width: 120px;" class="fieldText">URL Destino (*):</label>
                  
                        <s:textfield
						name="proveedorSMS.urldestino" value="%{proveedorSMS.urldestino}" id="proveedorSMS.urldestino"
						theme="simple" 
						labelposition="left"
						 size="85" maxlength="255"
						cssClass=""/>						
                    </p>
                    <p class="criteria">
	                    <label style="width: 130px;" class="fieldText">Método de Consulta (*):</label>
	                     <s:select list="#{'false':'Recepcion de Estado',
	                    'true':'Consulta de Estado'}" name="proveedorSMS.metodoconsulta" id="proveedorSMS.metodoconsulta"
	                      value ="%{proveedorSMS.metodoconsulta}" />
                    </p>
                    <p class="criteria">
                        <label style="width: 120px;" class="fieldText">Descripción (*):</label>
                        <s:textarea  name="proveedorSMS.descripcion" id="proveedorSMS.descripcion" theme="simple" rows="6" cols="82" value="%{proveedorSMS.descripcion}"> </s:textarea>
                    </p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Por defecto:</label>
						<s:checkbox theme="simple" name="proveedorSMS.isDefecto" id="proveedorSMS.isDefecto" value="%{proveedorSMS.defecto}"></s:checkbox>
                    </p>                                        
                    <p class="criteria">
                        <label style="width: 120px;" class="fieldText">Activo:</label>
                        <s:checkbox theme="simple" id="proveedorSMS.isActivo" name="proveedorSMS.isActivo" value="%{proveedorSMS.activado}" />
                    </p>
                    <p class="criteria">
                        <label style="width: 150px;" class="fieldText"><i>(*) Campos obligatorios</i></label>
                       
                    </p>                       
                </div>
            </div>
           </s:form>
           <s:form id="formaddParametroProveedorSMS" theme="simple" validate="false" name="formaddParametroProveedorSMS" method="POST" action="addParametroProveedorSMS">
		            <div class="editContainer">
		            	<div class="nameDescription">
		                	<label>Configuración</label>
		                </div>
		            	<div class="editContent">
		            	<script lang="text/JavaScript">
		                	function changeValueType(){
		                		var divInputType = document.getElementById("inputType");
		                		var opcion = document.getElementById("parametroServidor.tipoparametroid");
		                		var t = opcion.options[opcion.selectedIndex].getAttribute('tipo');
		                		if(t=='INT'){
		                			divInputType.innerHTML='<input type="text" id="parametroServidor.valor" title="Valor" name="parametroServidor.valor"  onKeyPress="return numbersonly(this, event)"/>';
		                		}else if(t=='STRING'){
		                			divInputType.innerHTML='<input type="text" id="parametroServidor.valor" title="Valor" name="parametroServidor.valor" />';
		                		}else if(t=='SELECT'){
		                			divInputType.innerHTML='<select id="parametroServidor.valor" style="width:173px" title="Valor" name="parametroServidor.valor"><option value="true">&nbsp;Si&nbsp;</option><option value="false">&nbsp;No&nbsp;</option></select> ';
		                		}
		                	}
		                </script>
		            	  <p class="criteria">
		                      <span style="width: 255px;">
		                          <label style="width: 75px;" class="fieldText">Parámetro:</label>
		                          <select onchange="changeValueType()" id="parametroServidor.tipoparametroid" name="parametroServidor.tipoparametroid">
			                          <s:iterator value="%{tiposParametros}" var="param" status="status">
			                          		<option value="${tipoparametroid}" tipo="${tipoCampo}">${nombre}</option>
			                          </s:iterator>
		                          </select>
		                          <input type="hidden" id="parametroServidor.servidorid" name="parametroServidor.servidorid" value="${idProveedorSMS}" />
		            	      </span> 
		                      <span>
		                          <label style="width: 50px;" class="fieldText">Valor:</label>
		                          <span id="inputType">
		                           <s:textfield
										name="parametroServidor.valor" value="%{parametroServidor.valor}" id="parametroServidor.valor"
										theme="simple" 
										title="Valor"
										labelposition="left"
										 size="20" maxlength="20"
										cssClass=""/>
									</span>
			          	      </span>
		                      <a class="addLink" id="addItem" onclick="return checkItem('formaddParametroProveedorSMS',false,'');" name="addItem">Añadir Item</a>
		                  </p>
		            	  <table cellspacing="0" cellpadding="0" border="0">
		            	    <thead>
		            	      <tr>
		            	        <th class="TH200">Parámetro</th>
		            	        <th class="separator">Valor</th>
		            	        <th class="TH20 separator"></th>
		          	        </tr>
		          	      </thead>
		          	      <tbody>
		          	      <s:iterator value="%{listaParametrosServidor}" var="parametroServidor" status="parametroServidorStatus">
		            	      <tr class="<s:if test='#parametroServidorStatus.odd == true '>odd</s:if>">
		            	        <td><s:label value="%{tipoNombre}"/></td>
		            	         <td><c:if test="${valor=='false'}">No</c:if>
		            	        <c:if test="${valor=='true'}">Si</c:if><c:if test="${valor!='true' && valor!='false'}">${valor}</c:if>	            	        
		            	        </td>
		            	        <td class="buttons"><span class="delete"> <a class="btnDelete" title="Eliminar" onclick="return confirmDelete();" href="deleteParametroProveedorSMS.action?parametroServidorId=${parametroservidorid}&idProveedorSMS=${proveedorSMS.proveedorSMSId}"></a> </span></td>
		          	          </tr>
		          	      </s:iterator>
		          	      <s:if test="%{listaParametrosServidor == null}">
		          	      	<tr><td colspan="3">No se ha configurado ningún parámetro para el servidor</td></tr>
		          	      </s:if>
		          	      </tbody>
		          	    </table>
		          	  </div>
		          	  <!-- INTRODUCIR LA PROPIEDAD PARAMETROSERVIDOR.SERVIDORID COMO HIDDEN -->
		            </div>
            </s:form>
           <script>
            	function checkParams(){
            		var sw=validaPlanJS('formAddPlanificacionProveedorSMS','planificacionServidor');
            		if(sw){
            			document.forms['formAddPlanificacionProveedorSMS'].submit();
            		}else{
            			return false;
            		}
            	}
            </script>
            <s:form id="formAddPlanificacionProveedorSMS" onsubmit="return validaPlanJS('frmEditPlanificacion','planificacionServidor')" theme="simple" validate="false" name="formAddPlanificacionProveedorSMS" method="POST" action="addPlanificacionProveedorSMS">
            <div class="editContainer">
            	<div class="nameDescription">
                	<label>Planificaciones</label>
                    <p>Determina los horarios en los que el proveedor va a estar disponible para realizar los env&iacute;os</p>
                </div>
            	<div class="editContent">
                	 <p class="criteria">
                     	<span>
                             <label class="fieldText">L 
                            <s:checkbox id="planificacionServidor.lunes" name="planificacionServidor.lunes" value="%{planificacionServidor.lunes}" theme="simple"/>
                            </label> 
                            <label class="fieldTextNoIco"> M
                            <s:checkbox id="planificacionServidor.martes" name="planificacionServidor.martes" value="%{planificacionServidor.martes}" theme="simple"/>                          
                            </label>
                            <label class="fieldTextNoIco"> X
                            <s:checkbox id="planificacionServidor.miercoles" name="planificacionServidor.miercoles" value="%{planificacionServidor.miercoles}" theme="simple"/>                         
                            </label> 
                            <label class="fieldTextNoIco">J
                            <s:checkbox id="planificacionServidor.jueves" name="planificacionServidor.jueves" value="%{planificacionServidor.jueves}" theme="simple"/>                        
                            </label> 
                            <label class="fieldTextNoIco">V
                            <s:checkbox id="planificacionServidor.viernes" name="planificacionServidor.viernes" value="%{planificacionServidor.viernes}" theme="simple"/>  
                            </label> 
                            <label class="fieldTextNoIco">S
                            <s:checkbox id="planificacionServidor.sabado" name="planificacionServidor.sabado" value="%{planificacionServidor.sabado}" theme="simple"/>   
                            </label> 
                            <label class="fieldTextNoIco">D
                            <s:checkbox id="planificacionServidor.domingo" name="planificacionServidor.domingo" value="%{planificacionServidor.domingo}" theme="simple"/>
                          	</label> 
                          	<s:hidden name="planificacionServidor.servidorId" id="planificacionServidor.servidorId"
											value="%{proveedorSMS.proveedorSMSId}" />                
						</span>                        
                        <span>
                        
                        	<label class="fieldText">Hora Inicio:</label>
		                          <s:select 
												id="planificacionServidor.horaDesde" name="planificacionServidor.horaDesde" 
												emptyOption="false" theme="simple" 
												labelposition="left"
												list="comboHorasInicio" listKey="codigo"
												listValue="descripcion" cssClass="W65" 
												value="%{planificacionServidor.horaDesde}" disabled="false" />
                        </span>
                        <span>
                        	<label class="fieldText">Hora Fin:</label>
                            		       <s:select 
												id="planificacionServidor.horaHasta" name="planificacionServidor.horaHasta" 
												emptyOption="false" theme="simple" 
												labelposition="left"
												list="comboHorasFin" listKey="codigo"
												listValue="descripcion" cssClass="W65" 
												value="%{planificacionServidor.horaHasta}" disabled="false" />
                        </span>
                        <a class="addLink" id="addItem" onclick="return checkItem('formAddPlanificacionProveedorSMS',true,'planificacionServidor');" name="addItem">Añadir Item</a>
                    </p>
                    <table cellspacing="0" cellpadding="0" border="0">
                        <thead>
                            <tr>                                
                                <th class="TH200">Días</th>
                                <th class="separator">Hora Inicio</th>
                                <th class="separator">Hora Fin</th>
                                <th class="TH45 separator"></th>
                            </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="%{listaPlanificacionesServidor}" var="planificacionServidor" status="planificacionServidorStatuts">
                             <tr class="<s:if test='#planificacionServidorStatuts.odd == true '>odd</s:if>">
                               <!-- <td>L, M, J</td> -->
                               <td><s:label value="%{dias}"/></td>
                                <td><s:label value="%{horaDesde}"/></td>
                                <td><s:label value="%{horaHasta}"/></td>
                                <td class="buttons">
                                <span class="edit"> 
                                 <div id="ajaxloader_ajax_${planificacionServidor.planificacionId}">
									<span id="ajax_${planificacionServidor.planificacionId}" name="ajax" title="Editar" onclick="return loadPlan(this,${planificacionServidor.planificacionId},${idProveedorSMS},'idProveedorSMS','updatePlanificacionProveedorSMS')" class="btnEdit planifications_link"></span> 
                                 </div>
                                 </span>                                
                                    <span class="delete">
                                  <a class="btnDelete" onclick="return confirmDelete();" title="Eliminar" href="deletePlanificacionProveedorSMS.action?planificacionId=${planificacionId}&idProveedorSMS=${proveedorSMS.proveedorSMSId}"></a>
                                 </span></td>
                           </tr>
                        </s:iterator>
                        <s:if test="%{listaPlanificacionesServidor == null}">
		          	      	<tr><td colspan="4">No se ha configurado ninguna planificación para el proveedorSMS</td></tr>
		          	      </s:if>
                        </tbody>
            		</table>
                </div>
            </div>
            <div class="editContainer">
            	<div class="nameDescription">
                	<label>Auditoría</label>
                </div>
            	<div class="editContent">
                	<p class="criteria">
                    	<span style="width: 340px;">
                        	<label style="width: 120px;" class="fieldText">Creador:</label>
                            <strong><s:label theme="simple" id="proveedorSMS.creadopor" name="proveedorSMS.creadopor" value="%{proveedorSMS.creadopor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Creación:</label>
                            <strong><s:label theme="simple" id="proveedorSMS.fechacreacion" name="proveedorSMS.fechacreacion" value="%{proveedorSMS.fechacreacion}"/></strong>
                        </span>
                    </p>
                    <p class="criteria">
                    	<span style="width: 340px;">
                        	<label style="width: 120px;" class="fieldText">Último Modificador:</label>
                            <strong><s:label theme="simple" id="proveedorSMS.modificadopor" name="proveedorSMS.modificadopor" value="%{proveedorSMS.modificadopor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Última Modificación:</label>
                            <strong><s:label theme="simple" id="proveedorSMS.fechamodificacion" name="proveedorSMS.fechamodificacion" value="%{proveedorSMS.fechamodificacion}"/></strong>
                        </span>
                    </p>
                </div>
            </div>            
            </s:form>
        </div>