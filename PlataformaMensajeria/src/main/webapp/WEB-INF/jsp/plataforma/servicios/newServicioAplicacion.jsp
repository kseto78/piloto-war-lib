<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR,ROL_PROPIETARIO,ROL_CAID">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
		<s:form id="frmNuevoServicio" method="POST" action="saveServicioAplicacion" theme="simple" cssClass="">	
        	<h3 class="pageNameButtons">
    			<span class="floatRight">
    				<s:submit  theme="simple" value="%{getText('buttons.text.save')}" cssClass="button"/>
                    <input type="button" onclick="javascript:location.href='${volverAplicacion}'" class="button" value="Volver">
                </span>
	            <label>CREACIÓN SERVICIO</label>
            </h3>
        <%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>

            <div class="editContainer">
            	<div class="nameDescription">
                	<label>Datos Generales</label>
                </div>
                
            	<div class="editContent">
            		<p class="criteria">
            			 <label style="width: 120px;" class="fieldText">Aplicacion (*):</label>
                       <s:select
						id="servicio.aplicacionid" name="servicio.aplicacionid" 
						emptyOption="true" theme="simple" 
						labelposition="left"
						list="comboAplicaciones" listKey="codigo"
						listValue="descripcion" cssClass="" cssStyle="width:150px"
						value="%{servicio.aplicacionid}" disabled="true"/>
						<input type="hidden" name="servicio.aplicacionid" id="servicio.aplicacionid" value="${servicio.aplicacionid}">
            		</p>
            		<p class="criteria">
            			 <label style="width: 120px;" class="fieldText">Canal (*):</label>
                       <s:select onchange="checkCanalHeader(this)"
						id="servicio.canalid" name="servicio.canalid" 
						emptyOption="true" theme="simple" 
						labelposition="left"
						list="comboCanales" listKey="codigo"
						listValue="descripcion" cssClass="" cssStyle="width:150px"
						value="%{servicio.canalid}" disabled="false" />
            		</p>            	
                	<p class="criteria">
                        <label style="width: 120px;" class="fieldText">Nombre (*):</label>
                        <s:textfield
						name="servicio.nombre" value="%{servicio.nombre}" id="servicio.nombre"
						theme="simple" 
						 labelposition="left"
						 size="70" maxlength="255"
						cssClass=""/>
                    </p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Descripción:</label>
                        <s:textarea theme="simple" rows="6" cols="70" name="servicio.descripcion"
						labelposition="left" id="servicio.descripcion" value="%{servicio.descripcion}"/> 
						
                    </p>
                	<p class="criteria">
                        <label style="width: 120px;" class="fieldText">Nº Max. Envíos (*):</label>
                        <s:textfield
						name="servicio.nmaxenvios" value="%{servicio.nmaxenvios}" id="servicio.nmaxenvios"
						theme="simple"  onKeyPress="return numbersonly(this, event)"
						 labelposition="left"
						 size="6" maxlength="6"
						cssClass=""/>
                    </p>
                    <p class="criteria">
                    	<label style="width: 120px;" class="fieldText">Historificación:</label>
                    	<span>    
                    		<c:set var="rh1" value=""/>
                    		<c:set var="rh2" value=""/>
                    		<c:set var="rh3" value=""/>
                    		<c:set var="rh4" value=""/>
						    
						    <c:if test="${newHistorificacion==1}">
								<c:set var="rh1" value="checked"/>
							</c:if>
							<c:if test="${newHistorificacion==2}">
							    <c:set var="rh2" value="checked"/>
							</c:if>
							<c:if test="${newHistorificacion==3}">
							    <c:set var="rh3" value="checked"/>
							</c:if>
							<c:if test="${newHistorificacion==4}">
							    <c:set var="rh4" value="checked"/>
							</c:if>
						    
                            <input type="radio" id="newHistorificacion" name="newHistorificacion" value="1" ${rh1} />30 días    
                            <input type="radio" id="newHistorificacion" name="newHistorificacion" value="2" ${rh2} />60 días
                            <input type="radio" id="newHistorificacion" name="newHistorificacion" value="3" ${rh3} />90 días
                            <input type="radio" id="newHistorificacion" name="newHistorificacion" value="4" ${rh4} />Otro
						</span>
						<s:textfield
						name="servicio.historificacionInput" value="%{servicio.historificacionInput}" id="servicio.historificacionInput"
						theme="simple"  onKeyPress="return numbersonly(this, event)"
						 labelposition="left"
						 size="3" maxlength="3"
						cssClass=""/>
						<label> días</label>
                    </p>
                    <p class="criteria">
						<label style="width: 425px; margin-left: 130px;" class="fieldText"><i>Si el periodo de tiempo es superior a 90 días debe indicar el motivo</i></label>   
						<s:textarea style="margin-left: 130px;" theme="simple" rows="6" cols="70" name="servicio.motivohistorificacion"
						labelposition="left" id="servicio.motivohistorificacion" value="%{servicio.motivohistorificacion}"/>
                    </p>
                    <p class="criteria">
                    	<label style="width: 120px;" class="fieldText">Conservación:</label>
                    	<span>
                    		<c:set var="rc1" value=""/>
                    		<c:set var="rc2" value=""/>
                    		<c:set var="rc3" value=""/>
                    		<c:set var="rc4" value=""/>
						    
						    <c:if test="${newConservacion==1}">
								<c:set var="rc1" value="checked"/>
							</c:if>
							<c:if test="${newConservacion==2}">
							    <c:set var="rc2" value="checked"/>
							</c:if>
							<c:if test="${newConservacion==3}">
							    <c:set var="rc3" value="checked"/>
							</c:if>
							<c:if test="${newConservacion==4}">
							    <c:set var="rc4" value="checked"/>
							</c:if>
						    
                            <input type="radio" id="newConservacion" name="newConservacion" value="1" ${rc1} />1 año   
                            <input type="radio" id="newConservacion" name="newConservacion" value="2" ${rc2} />2 años
                            <input type="radio" id="newConservacion" name="newConservacion" value="3" ${rc3} />3 años
                            <input type="radio" id="newConservacion" name="newConservacion" value="4" ${rc4} />Otro
						</span>
						<s:textfield
						name="servicio.conservacionInput" value="%{servicio.conservacionInput}" id="servicio.conservacionInput"
						theme="simple"  onKeyPress="return numbersonly(this, event)"
						 labelposition="left"
						 size="2" maxlength="2"
						cssClass=""/>
						<label> años</label>
                    </p>
                    <p class="criteria">
                    	<label style="width: 425px; margin-left: 130px;" class="fieldText"><i>Si el periodo de tiempo es superior a 3 años debe indicar el motivo</i></label>     
						<s:textarea style="margin-left: 130px;" theme="simple" rows="6" cols="70" name="servicio.motivoconservacion"
						labelposition="left" id="servicio.motivoconservacion" value="%{servicio.motivoconservacion}"/> 
                    </p>
                    <p class="criteria">
					<label id="frommailLabel" style="width: 120px; visibility:hidden; display:none"
						class="fieldText">Cuenta Env&iacute;o:</label>
					<s:textfield name="servicio.frommail" value="%{servicio.frommail}"
						id="servicio.frommail" onblur="verifyEmail(this)" theme="simple"
						cssStyle="visibility:hidden;display:none;" labelposition="left" size="70"
						maxlength="255" cssClass="" />
					</p>
					<p class="criteria">
						<label id="frommailnameLabel"
							style="width: 120px; visibility: hidden;display:none;" class="fieldText">Nombre
							C. Env&iacute;o:</label>
						<s:textfield name="servicio.frommailname"
							value="%{servicio.frommailname}" id="servicio.frommailname"
							theme="simple" cssStyle="visibility:hidden;display:none;" labelposition="left"
							size="70" maxlength="255" cssClass="" />
					</p>                                                              
                   
	 				<p class="criteria">
						<label id="endpointLabel" style="width: 120px; visibility: hidden; display: none;" class="fieldText">EndPoint (*):</label>
						<s:textfield name="servicio.endpoint" value="%{servicio.endpoint}"
							id="servicio.endpoint" theme="simple" cssStyle="visibility:hidden;display:none;" labelposition="left"
							size="70" maxlength="255" cssClass="" />
					</p>
	
					<p class="criteria">
						<label id="nombreloteenvioLabel"
							style="width: 121px; visibility: hidden;display:none;" class="fieldText">Nombre
							Lote Envío (*):</label>
						<s:textfield name="servicio.nombreloteenvio"
							value="%{servicio.nombreloteenvio}" id="servicio.nombreloteenvio"
							theme="simple" cssStyle="visibility:hidden;display:none;" labelposition="left"
							size="70" maxlength="255" cssClass="" />
					</p>
                    
                    <p class="criteria" id="plataformaid" style="visibility:hidden;display:none;" >
                        <label id="plataformaLabel" theme="simple" style="width: 120px" class="fieldText">Plataforma (*):</label>
						<s:checkbox theme="simple" name="newPlataformaAndroid" id="newPlataformaAndroid" value="%{newPlataformaAndroid}"/>Android
						<s:checkbox theme="simple" name="newPlataformaiOS" id="newPlataformaiOS" value="%{newPlataformaiOS}"/>iOS
                    </p>
                    
                    <p class="criteria">
                        <label id="badgeLabel" style="width: 180px;visibility:hidden;display:none;" class="fieldText">Agrupar notificaciones cada (*):</label>
                        <s:textfield
						name="servicio.badge" value="%{servicio.badge}" id="servicio.badge"
						theme="simple" cssStyle="visibility:hidden; display:none;" onKeyPress="return numbersonly(this, event)"
						labelposition="left"
						size="30" maxlength="255"
						cssClass=""/>
                    </p>
                    
	  				<p class="criteria">
						<label id="fcmprojectkeyLabel"
							style="width: 180px; visibility:hidden; display:none;" class="fieldText">FCM
							API Key (*):</label>
						<s:textfield name="servicio.fcmprojectkey"
							value="%{servicio.fcmprojectkey}" id="servicio.fcmprojectkey"
							theme="simple" cssStyle="visibility:hidden;display:none;" labelposition="left"
							size="30" maxlength="255" cssClass="" />
					</p>
	
					<p class="criteria">
						<label id="apnsrutacertificadoLabel"
							style="width: 180px; visibility:hidden; display:none;" class="fieldText">APNS
							Ruta Certificado (*):</label>
						<s:textfield name="servicio.apnsrutacertificado"
							value="%{servicio.apnsrutacertificado}"
							id="servicio.apnsrutacertificado" theme="simple"
							cssStyle="visibility:hidden;display:none;" labelposition="left" size="30"
							maxlength="255" cssClass="" />
					</p>
	
					<p class="criteria">
						<label id="apnspasswordcertificadoLabel"
							style="width: 179px; visibility:hidden; display:none;" class="fieldText">APNS
							Password Certificado (*):</label>
						<s:password name="servicio.apnspasswordcertificado"
							value="%{servicio.apnspasswordcertificado}"
							id="servicio.apnspasswordcertificado" theme="simple"
							cssStyle="visibility:hidden;display:none;" labelposition="left" size="24"
							maxlength="255" cssClass="" showPassword="true"/>
					</p>
					<p class="criteria">
					<label id="caducidadCertificadoLabel"
						style="width: 179px; visibility: hidden; display: none;" class="fieldText">Caducidad Certificado:</label>					
						<s:textfield
 								name="servicio.caducidadCertificado" value="%{servicio.caducidadCertificado}" id="servicio.caducidadCertificado"
 								theme="simple" size="24" maxlength="55"
								cssClass="datepicker"
								cssStyle="visibility:hidden;display:none;width:200px !important;" labelposition="left"
								autocomplete="off"> 
								<s:param name="value">
    								<s:date name="servicio.caducidadCertificado" format="dd/MM/yyyy HH:mm"/>
    							</s:param>
						</s:textfield>    								
					</p>
                    
                    <p class="criteria">
                        <label style="width: 150px;" class="fieldText"><i>(*) Campos obligatorios</i></label>
                    </p>                       
                </div>
                
            </div>
            <div class="editContainer">
			<div class="nameDescription">
				<label>Contactos</label>
			</div>
			<div class="editContent">
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Responsable
						técnico: (*)</label>
					<s:textfield name="servicio.responsabletecniconombre"
						value="%{servicio.responsabletecniconombre}"
						id="servicio.responsabletecniconombre" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Email: (*)</label>
					<s:textfield name="servicio.responsabletecnicoemail"
						value="%{servicio.responsabletecnicoemail}"
						id="servicio.responsabletecnicoemail" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Responsable
						funcional: (*)</label>
					<s:textfield name="servicio.responsablefuncionalnombre"
						value="%{servicio.responsablefuncionalnombre}"
						id="servicio.responsablefuncionalnombre" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Email: (*)</label>
					<s:textfield name="servicio.responsablefuncionalemail"
						value="%{servicio.responsablefuncionalemail}"
						id="servicio.responsablefuncionalemail" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label style="width: 150px;" class="fieldText"><i>(*)
							Campos obligatorios</i></label>
				</p>
			</div>
		</div>
            <div class="editContainer">
            	<div class="nameDescription">
                	<label>Informes</label>
                </div>
            	<div class="editContent">
                	<p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Activo:</label>
						<s:checkbox theme="simple" name="newInformeActivo" id="newInformeActivo" value="%{newInformeActivo}"/>
                    </p>
                    <p class="criteria">
                    	<label theme="simple" style="width: 120px;" class="fieldText">Destinatarios:</label>
						<s:textarea  name="newInformesDestinatarios" id="newInformesDestinatarios" 
						placeholder="Introduce direcciones de email separadas por ';'"
						theme="simple" cssClass="W240" value="%{newInformesDestinatarios}"/> 
                    </p>
                    <p class="criteria">
                    	<label theme="simple" style="width: 120px;" class="fieldText">Agrupación</label>
                    	<p class="criteria">
                    		<label theme="simple" style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total de mensajes agrupador por estado:</label>
							<s:checkbox theme="simple" name="newAgrupacionEstado" id="servicio.agrupacionestado" value="%{newAgrupacionEstado}"/>
                    	</p>
                    	<p class="criteria" id="agrupacioncodorg">
                    		<label theme="simple" style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total de mensajes agrupador por organismo:</label>
							<s:checkbox theme="simple" name="newAgrupacionCodOrg" id="servicio.agrupacioncodorg" value="%{newAgrupacionCodOrg}"/>
                    	</p>
                    	<p class="criteria" id="agrupacioncodsia">
                    		<label theme="simple" style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total de mensajes agrupador por procedimiento:</label>
							<s:checkbox theme="simple" name="newAgrupacionCodSia" id="servicio.agrupacioncodsia" value="%{newAgrupacionCodSia}"/>
                    	</p>
                    	<p class="criteria" id="agrupacioncodorgpagador" style="visibility:hidden">
                    		<label theme="simple" style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total de mensajes agrupador por organismo pagador:</label>
							<s:checkbox theme="simple" name="agrupacioncodorgpagador" id="servicio.agrupacioncodorgpagador" 
							value="%{newAgrupacionCodOrgPagador}"/>
                    	</p>
                    </p>
                </div>
            </div>
        </s:form>                  
        </div>
<script>
	checkCanalHeader(document.getElementById("servicio.canalid"));
</script>