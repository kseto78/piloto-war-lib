<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">        	
		<s:form id="frmEditServidor" method="POST" action="updateReceptorSMS" validate="false" theme="simple" cssClass="">
        	<h3 class="pageNameButtons">
    			<span class="floatRight">
    				
                	<s:submit  theme="simple" value="%{getText('buttons.text.save')}" cssClass="button"/>
                    <input type="button" onclick="javascript:location.href='${volver}'" class="button" value="Volver">
                </span>
	            <label>EDICIÓN RECEPTOR SMS
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
                	<s:hidden theme="simple" id="receptorSMS.receptorSMSId" name="receptorSMS.receptorSMSId" value="%{receptorSMS.receptorSMSId}"/>
                	<s:hidden theme="simple" id="receptorSMSId" name="receptorSMSId" value="%{receptorSMS.receptorSMSId}" />
                        <label  style="width: 120px;" class="fieldText">Nombre (*):</label>
                        <s:textfield
						name="receptorSMS.nombre" value="%{receptorSMS.nombre}" id="receptorSMS.nombre"
						theme="simple" 
						labelposition="left"
						 size="85" maxlength="255"
						cssClass=""/>
					</p>
                    <p class="criteria">
                        <label style="width: 120px;" class="fieldText">Descripción (*):</label>
                        <s:textarea  name="receptorSMS.descripcion" id="receptorSMS.descripcion" theme="simple" rows="6" cols="82" value="%{receptorSMS.descripcion}"> </s:textarea>
                    </p>
                    <p class="criteria">
						<label id="userSMSLabel" style="width: 180px" class="fieldText">Usuario operadora (*):</label>
						<s:textfield
						name="receptorSMS.usuario" value="%{receptorSMS.usuario}" id="receptorSMS.usuario"
						theme="simple"
						 labelposition="left"
						 size="30" maxlength="255"
						cssClass=""/>
					</p>
					<p class="criteria">
						<label id="passwordSMSLabel" style="width: 180px" class="fieldText">Contraseña operadora (*):</label>
						<s:password
						name="receptorSMS.password" value="%{receptorSMS.password}" id="receptorSMS.password"
						theme="simple"
						 labelposition="left"
						 size="30" maxlength="255" showPassword="true"
						cssClass=""/>
					</p>
					<p class="criteria" id="repPassLabel" style="visibility:hidden">
						<label id="rePasswordSMSLabel" style="width: 180px" class="fieldText">Rep. Contraseña operadora (*):</label>
						<s:password
						name="receptorSMS.rePassword" value="" id="receptorSMS.rePassword"
						theme="simple"
						 labelposition="left"
						 showPassword="true"
						 size="30" maxlength="255"
						cssClass=""/>
						<s:hidden name="checkPassword" id="checkPassword" value="false"/>
					</p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Por defecto:</label>
						<s:checkbox theme="simple" name="receptorSMS.isDefecto" id="receptorSMS.isDefecto" value="%{receptorSMS.defecto}"></s:checkbox>
                    </p>                                        
                    <p class="criteria">
                        <label style="width: 120px;" class="fieldText">Activo:</label>
                        <s:checkbox theme="simple" id="receptorSMS.isActivo" name="receptorSMS.isActivo" value="%{receptorSMS.activado}" />
                    </p>
                    <p class="criteria">
                        <label style="width: 150px;" class="fieldText"><i>(*) Campos obligatorios</i></label>
                       
                    </p>                       
                </div>
            </div>
           </s:form>
           <script type="text/javascript">
				var oDiv = document.getElementById('receptorSMS.password');
				
				var f = function (e) {
					document.getElementById('repPassLabel').style.visibility="visible";
					document.getElementById('rePasswordSMSLabel').style.visibility="visible";
					document.getElementById('receptorSMS.rePassword').style.visibility="visible";
					document.getElementById('receptorSMS.rePassword').disabled="";
					if(document.getElementById('receptorSMS.password').value!=document.getElementById('repPassLabel').value){
						document.getElementById('checkPassword').value="true";
					}
				};
				
				if (oDiv.addEventListener)  // W3C DOM
					 oDiv.addEventListener('keyup',f,true);
				else if (oDiv.attachEvent) { // IE DOM
					 oDiv.attachEvent("onkeyup", f);
				}
			</script>
           <s:form id="formaddParametroReceptorSMS" theme="simple" validate="false" name="formaddParametroReceptorSMS" method="POST" action="addParametroReceptorSMS">
		            <div class="editContainer">
		            	<div class="nameDescription">
		                	<label>Configuración</label>
		                </div>
		            	<div class="editContent">
		            	<script lang="text/JavaScript">
		                	function changeValueType(){
		                		var divInputType = document.getElementById("inputType");
		                		var opcion = document.getElementById("parametroServidor.tipoParametroId");
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
		                          <select onchange="changeValueType()" id="parametroServidor.tipoParametroId" name="parametroServidor.tipoParametroId">
			                          <s:iterator value="%{tiposParametros}" var="param" status="status">
			                          		<option value="${tipoParametroId}" tipo="${tipoCampo}">${nombre}</option>
			                          </s:iterator>
		                          </select>
		                          <input type="hidden" id="parametroServidor.servidorId" name="parametroServidor.servidorId" value="${idReceptorSMS}" />
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
		                      <a class="addLink" id="addItem" onclick="return checkItem('formaddParametroReceptorSMS',false,'');" name="addItem">Añadir Item</a>
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
		            	        <td class="buttons"><span class="delete"> <a class="btnDelete" title="Eliminar" onclick="return confirmDelete();" href="deleteParametroReceptorSMS.action?parametroServidorId=${parametroServidorId}&idReceptorSMS=${receptorSMS.receptorSMSId}"></a> </span></td>
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
            		var sw=validaPlanJS('formAddPlanificacionReceptorSMS','planificacionServidor');
            		if(sw){
            			document.forms['formAddPlanificacionReceptorSMS'].submit();
            		}else{
            			return false;
            		}
            	}
            </script>
            <s:form id="formAddPlanificacionReceptorSMS" onsubmit="return validaPlanJS('frmEditPlanificacion','planificacionServidor')" theme="simple" validate="false" name="formAddPlanificacionReceptorSMS" method="POST" action="addPlanificacionReceptorSMS">
            <div class="editContainer">
            	<div class="nameDescription">
                	<label>Planificaciones</label>
                    <p>Determina los horarios en los que el receptor va a estar disponible para realizar los env&iacute;os</p>
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
											value="%{receptorSMS.receptorSMSId}" />                
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
                        <a class="addLink" id="addItem" onclick="return checkItem('formAddPlanificacionReceptorSMS',true,'planificacionServidor');" name="addItem">Añadir Item</a>
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
									<span id="ajax_${planificacionServidor.planificacionId}" name="ajax" title="Editar" onclick="return loadPlan(this,${planificacionServidor.planificacionId},${idReceptorSMS},'idReceptorSMS','updatePlanificacionReceptorSMS')" class="btnEdit planifications_link"></span> 
                                 </div>
                                 </span>                                
                                    <span class="delete">
                                  <a class="btnDelete" onclick="return confirmDelete();" title="Eliminar" href="deletePlanificacionReceptorSMS.action?planificacionId=${planificacionId}&idReceptorSMS=${receptorSMS.receptorSMSId}"></a>
                                 </span></td>
                           </tr>
                        </s:iterator>
                        <s:if test="%{listaPlanificacionesServidor == null}">
		          	      	<tr><td colspan="4">No se ha configurado ninguna planificación para el receptorSMS</td></tr>
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
                            <strong><s:label theme="simple" id="receptorSMS.creadoPor" name="receptorSMS.creadoPor" value="%{receptorSMS.creadoPor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Creación:</label>
                            <strong><s:label theme="simple" id="receptorSMS.fechaCreacion" name="receptorSMS.fechaCreacion" value="%{receptorSMS.fechaCreacion}"/></strong>
                        </span>
                    </p>
                    <p class="criteria">
                    	<span style="width: 340px;">
                        	<label style="width: 120px;" class="fieldText">Último Modificador:</label>
                            <strong><s:label theme="simple" id="receptorSMS.modificadoPor" name="receptorSMS.modificadoPor" value="%{receptorSMS.modificadoPor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Última Modificación:</label>
                            <strong><s:label theme="simple" id="receptorSMS.fechaModificacion" name="receptorSMS.fechaModificacion" value="%{receptorSMS.fechaModificacion}"/></strong>
                        </span>
                    </p>
                </div>
            </div>            
            </s:form>
        </div>