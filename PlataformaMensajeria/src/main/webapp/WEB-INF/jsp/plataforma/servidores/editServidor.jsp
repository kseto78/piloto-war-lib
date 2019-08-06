<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR,ROL_CAID">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">        	
		<s:form id="frmEditServidor" method="POST" action="updateServidor" validate="false" theme="simple" cssClass="">
        	<h3 class="pageNameButtons">
    			<span class="floatRight">
    				<s:submit  theme="simple" value="%{getText('buttons.text.save')}" cssClass="button" title="%{getText('buttons.text.save')}" />
                    <input type="button" onclick="javascript:location.href='${volver}'" class="button" value="Volver" title="Volver">
                </span>
	            <label>EDICIÓN SERVIDOR SMTP 
	            </label>
            </h3>
         <%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
		<sj:dialog  id="dialogConnecting" title="Conectando..." cssStyle="display:none" autoOpen="false">
			<center>
					<img src="img/connecting.gif" alt="Conectando con el servidor SMTP"/>
					<a href="editServidor.action?idServidor=${servidor.servidorid}">Cancelar</a>
			</center>
		</sj:dialog>
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
                	<s:hidden theme="simple" id="servidor.servidorid" name="servidor.servidorid" value="%{servidor.servidorid}"/>
                	<s:hidden theme="simple" id="idServidor" name="idServidor" value="%{servidor.servidorid}" />
                        <label  style="width: 120px;" class="fieldText">Nombre (*):</label>
                        <s:textfield
						name="servidor.nombre" value="%{servidor.nombre}" id="servidor.nombre"
						theme="simple"  
						labelposition="left"
						 size="85" maxlength="255"
						cssClass="input_tablas_registro"/>
                    </p>
                    <p class="criteria">
                        <label style="width: 120px;" class="fieldText">Descripción (*):</label>
                        <s:textarea  name="servidor.descripcion" id="servidor.descripcion" theme="simple" rows="6" cols="82" value="%{servidor.descripcion}"> </s:textarea>
                    </p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Por defecto:</label>
						<s:checkbox theme="simple" name="servidor.isDefecto" id="servidor.isDefecto" value="%{servidor.defecto}"></s:checkbox>
                    </p>                    
                    <p class="criteria">
                        <label style="width: 120px;" class="fieldText">Activo:</label>
                        <s:checkbox theme="simple" id="servidor.isActivo" name="servidor.isActivo" value="%{servidor.activado}" />
                    </p>
                    <p class="criteria">
                        <label style="width: 150px;" class="fieldText"><i>(*) Campos obligatorios</i></label>
                       
                    </p>   
                </div>
            </div>
           </s:form>
           <s:form id="formaddParametroServidor" theme="simple" validate="false" name="formaddParametroServidor" method="POST" action="addParametroServidor">
		            <div class="editContainer">
		            	<div class="nameDescription">
		                	<label>Configuración</label>
		                	 <p>Las opciones de configuración variarán en función del canal seleccionado</p>
		                </div>
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
		            	<div class="editContent">
		            	  <p class="criteria">
		                      <span style="width: 255px;">
		                          <label style="width: 75px;" class="fieldText">Parámetro:</label>
		                          <select onchange="changeValueType()" id="parametroServidor.tipoparametroid" name="parametroServidor.tipoparametroid">
			                          <s:iterator value="%{tiposParametros}" var="param" status="status">
			                          		<option value="${tipoparametroid}" tipo="${tipocampo}">${nombre}</option>
			                          </s:iterator>
		                          </select>
		                          <input type="hidden" id="parametroServidor.servidorid" name="parametroServidor.servidorid" value="${idServidor}" />
		                         
		            	      </span> 
		                      <span>
		                          <label style="width: 50px;" class="fieldText">Valor:</label>
		                           
		                           <span id="inputType">
		                           <s:textfield
										name="parametroServidor.valor" value="%{parametroServidor.valor}" id="parametroServidor.valor"
										theme="simple" 
										title="Valor"
										labelposition="left"
										 size="20" maxlength="255"
										cssClass=""/>
										</span>
								   
			          	      </span>
		                      <a class="addLink" id="addItem" onclick="return checkItem('formaddParametroServidor',false,'');"  name="addItem">Añadir Item</a>
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
		            	      <tr class="<s:if test='#songStatus.odd == true '>odd</s:if>">
		            	        <td><s:label value="%{tipoNombre}"/></td>
		            	        <td><c:if test="${valor=='false'}">No</c:if>
		            	        <c:if test="${valor=='true'}">Si</c:if><c:if test="${valor!='true' && valor!='false'}">${valor}</c:if>	            	        
		            	        </td>
		            	        <td class="buttons"><span class="delete"> <a class="btnDelete" title="Eliminar" onclick="return confirmDelete();" href="deleteParametroServidor.action?parametroServidorId=${parametroservidorid}&idServidor=${servidor.servidorid}"></a> </span></td>
		          	          </tr>
		          	      </s:iterator>
		          	      <s:if test="%{listaParametrosServidor == null}">
		          	      	<tr><td colspan="3">No se ha configurado ningún parámetro para el servidor</td></tr>
		          	      </s:if>
		          	      </tbody>
		          	    </table>
		          	    <a class="addLink" id="addItem" href="checkSMTPConnection.action?idServidor=${servidor.servidorid}" onclick="connectingModal()" name="addItem">Comprobar conexión</a>
		          	  </div>
		          	  <!-- INTRODUCIR LA PROPIEDAD PARAMETROSERVIDOR.SERVIDORID COMO HIDDEN -->
		            </div>
            </s:form>
            <script>
            	function checkParams(){
            		var sw=validaPlanJS('formAddPlanificacion','planificacionServidor');
            		if(sw){
            			document.forms['formAddPlanificacion'].submit();
            		}else{
            			return false;
            		}
            	}
            </script>
            <s:form id="formAddPlanificacion" onsubmit="return validaPlanJS('frmEditPlanificacion','planificacionServidor')" theme="simple" validate="false" name="formAddPlanificacion" method="POST" action="addPlanificacion">
            <div class="editContainer">
            	<div class="nameDescription">
                	<label>Planificaciones</label>
                    <p>Determina los horarios en los que el servidor va a estar disponible para realizar los env&iacute;os</p>
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
											value="%{servidor.servidorid}" />                
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
                        	<label class="fieldText"> Hora Fin:</label>
                            		       <s:select 
											id="planificacionServidor.horaHasta" name="planificacionServidor.horaHasta" 
											emptyOption="false" theme="simple" 
											labelposition="left"
											list="comboHorasFin" listKey="codigo"
											listValue="descripcion" cssClass="W65" 
											value="%{planificacionServidor.horaHasta}" disabled="false" />
                        </span>
                        <a class="addLink" id="addItem" onclick="return checkItem('formAddPlanificacion',true,'planificacionServidor');" name="addItem">Añadir Item</a>
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
                             <tr class="<s:if test='#songStatus.odd == true '>odd</s:if>">
                               <!-- <td>L, M, J</td> -->
                               <td><s:label value="%{dias}"/></td>
                                <td><s:label value="%{horaDesde}"/></td>
                                <td><s:label value="%{horaHasta}"/></td>
                                <td class="buttons">
                                 <span class="edit"> 
                                <div id="ajaxloader_ajax_${planificacionServidor.planificacionId}">
									<span id="ajax_${planificacionServidor.planificacionId}" name="ajax" title="Editar" onclick="return loadPlan(this,${planificacionServidor.planificacionId},${servidor.servidorid},'idServidor','updatePlanificacionServer')" class="btnEdit planifications_link"></span> 
                                 </div>
                                 </span>
                                    <span class="delete">
                                  <a class="btnDelete" onclick="return confirmDelete();" title="Eliminar" href="deletePlanificacionServidor.action?planificacionId=${planificacionId}&idServidor=${servidor.servidorid}"></a>
                                 </span></td>
                           </tr>
                        </s:iterator>
                        <s:if test="%{listaPlanificacionesServidor == null}">
		          	      	<tr><td colspan="4">No se ha configurado ninguna planificación para el servidor</td></tr>
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
                            <strong><s:label theme="simple" id="servidor.creadopor" name="servidor.creadopor" value="%{servidor.creadopor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Creación:</label>
                            <strong><s:label theme="simple" id="servidor.fechacreacion" name="servidor.fechacreacion" value="%{servidor.fechacreacion}"/></strong>
                        </span>
                    </p>
                    <p class="criteria">
                    	<span style="width: 340px;">
                        	<label style="width: 120px;" class="fieldText">Último Modificador:</label>
                            <strong><s:label theme="simple" id="servidor.modificadopor" name="servidor.modificadopor" value="%{servidor.modificadopor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Última Modificación:</label>
                            <strong><s:label theme="simple" id="servidor.fechamodificacion" name="servidor.fechamodificacion" value="%{servidor.fechamodificacion}"/></strong>
                        </span>
                    </p>
                </div>
            </div>
            
            </s:form>
        </div>