<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR,ROL_PROPIETARIO">
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
	            <label>CREACI�N SERVICIO</label>
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
						id="servicio.aplicacionId" name="servicio.aplicacionId" 
						emptyOption="true" theme="simple" 
						labelposition="left"
						list="comboAplicaciones" listKey="codigo"
						listValue="descripcion" cssClass="" cssStyle="width:150px"
						value="%{servicio.aplicacionId}" disabled="true"/>
						<input type="hidden" name="servicio.aplicacionId" id="servicio.aplicacionId" value="${servicio.aplicacionId}">
            		</p>
            		<p class="criteria">
            			 <label style="width: 120px;" class="fieldText">Canal (*):</label>
                       <s:select onchange="checkCanalHeader(this)"
						id="servicio.canalId" name="servicio.canalId" 
						emptyOption="true" theme="simple" 
						labelposition="left"
						list="comboCanales" listKey="codigo"
						listValue="descripcion" cssClass="" cssStyle="width:150px"
						value="%{servicio.canalId}" disabled="false" />
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
                        <label theme="simple" style="width: 120px;" class="fieldText">Descripci�n:</label>
                        <s:textarea theme="simple" rows="6" cols="70" name="servicio.descripcion"
						labelposition="left" id="servicio.descripcion" value="%{servicio.descripcion}"/> 
						
                    </p>
                	<p class="criteria">
                        <label style="width: 120px;" class="fieldText">N� Max. Env�os (*):</label>
                        <s:textfield
						name="servicio.nmaxenvios" value="%{servicio.nmaxenvios}" id="servicio.nmaxenvios"
						theme="simple"  onKeyPress="return numbersonly(this, event)"
						 labelposition="left"
						 size="6" maxlength="6"
						cssClass=""/>
                    </p>
                    <p class="criteria">
                    	<label style="width: 120px;" class="fieldText">Historificaci�n:</label>
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
						    
                            <input type="radio" id="newHistorificacion" name="newHistorificacion" value="1" ${rh1} />30 d�as    
                            <input type="radio" id="newHistorificacion" name="newHistorificacion" value="2" ${rh2} />60 d�as
                            <input type="radio" id="newHistorificacion" name="newHistorificacion" value="3" ${rh3} />90 d�as
                            <input type="radio" id="newHistorificacion" name="newHistorificacion" value="4" ${rh4} />Otro
						</span>
						<s:textfield
						name="servicio.historificacionInput" value="%{servicio.historificacionInput}" id="servicio.historificacionInput"
						theme="simple"  onKeyPress="return numbersonly(this, event)"
						 labelposition="left"
						 size="3" maxlength="3"
						cssClass=""/>
						<label> d�as</label>
                    </p>
                    <p class="criteria">
						<label style="width: 425px; margin-left: 130px;" class="fieldText"><i>Si el periodo de tiempo es superior a 90 d�as debe indicar el motivo</i></label>   
						<s:textarea style="margin-left: 130px;" theme="simple" rows="6" cols="70" name="servicio.motivoHistorificacion"
						labelposition="left" id="servicio.motivoHistorificacion" value="%{servicio.motivoHistorificacion}"/>
                    </p>
                    <p class="criteria">
                    	<label style="width: 120px;" class="fieldText">Conservaci�n:</label>
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
						    
                            <input type="radio" id="newConservacion" name="newConservacion" value="1" ${rc1} />1 a�o   
                            <input type="radio" id="newConservacion" name="newConservacion" value="2" ${rc2} />2 a�os
                            <input type="radio" id="newConservacion" name="newConservacion" value="3" ${rc3} />3 a�os
                            <input type="radio" id="newConservacion" name="newConservacion" value="4" ${rc4} />Otro
						</span>
						<s:textfield
						name="servicio.conservacionInput" value="%{servicio.conservacionInput}" id="servicio.conservacionInput"
						theme="simple"  onKeyPress="return numbersonly(this, event)"
						 labelposition="left"
						 size="2" maxlength="2"
						cssClass=""/>
						<label> a�os</label>
                    </p>
                    <p class="criteria">
                    	<label style="width: 425px; margin-left: 130px;" class="fieldText"><i>Si el periodo de tiempo es superior a 3 a�os debe indicar el motivo</i></label>     
						<s:textarea style="margin-left: 130px;" theme="simple" rows="6" cols="70" name="servicio.motivoConservacion"
						labelposition="left" id="servicio.motivoConservacion" value="%{servicio.motivoConservacion}"/> 
                    </p>
                    <p class="criteria">
                        <label id="fromMailLabel" style="width: 120px;visibility:hidden;display:none" class="fieldText">Cuenta Env&iacute;o:</label>
                        <s:textfield
						name="servicio.fromMail" value="%{servicio.fromMail}" id="servicio.fromMail" onblur="verifyEmail(this)"
						theme="simple" cssStyle="visibility:hidden;display:none"
						 labelposition="left"
						 size="85" maxlength="255"
						cssClass=""/>
                    </p>
                    <p class="criteria">
                        <label id="fromMailNameLabel" style="width: 120px;visibility:hidden;display:none" class="fieldText">Nombre C. Env&iacute;o:</label>
                        <s:textfield
						name="servicio.fromMailName" value="%{servicio.fromMailName}" id="servicio.fromMailName"
						theme="simple" cssStyle="visibility:hidden;display:none"
						 labelposition="left"
						 size="85" maxlength="255"
						cssClass=""/>
                    </p>                                                                
                   
                    <p class="criteria">
                        <label id="endPointLabel" style="width: 120px;visibility:hidden;display:none" class="fieldText">EndPoint (*):</label>
                         <s:textfield
							name="servicio.endPoint" value="%{servicio.endPoint}" id="servicio.endPoint"
							theme="simple" cssStyle="visibility:hidden;display:none"
						 	labelposition="left"
						 	size="85" maxlength="255"
							cssClass=""/>				
                    </p>
                   <p class="criteria">
					<label id="nombreLoteEnvioLabel"
						style="width: 140px; visibility: hidden;display:none" class="fieldText">Nombre
						Lote Env�o (*):</label>
					<s:textfield name="servicio.nombreLoteEnvio"
						value="%{servicio.nombreLoteEnvio}" id="servicio.nombreLoteEnvio"
						theme="simple" cssStyle="visibility:hidden;display:none" labelposition="left"
						size="85" maxlength="255" cssClass="" />
				</p>
                    
                    <p class="criteria" id="plataformaId" style="visibility:hidden;display:none" >
                        <label id="plataformaLabel" theme="simple" style="width: 120px" class="fieldText">Plataforma (*):</label>
						<s:checkbox theme="simple" name="newPlataformaAndroid" id="newPlataformaAndroid" value="%{newPlataformaAndroid}"/>Android
						<s:checkbox theme="simple" name="newPlataformaiOS" id="newPlataformaiOS" value="%{newPlataformaiOS}"/>iOS
                    </p>
                    
                    <p class="criteria">
                        <label id="badgeLabel" style="width: 180px;visibility:hidden;display:none" class="fieldText">Agrupar notificaciones cada (*):</label>
                        <s:textfield
						name="servicio.badge" value="%{servicio.badge}" id="servicio.badge"
						theme="simple" cssStyle="visibility:hidden" onKeyPress="return numbersonly(this, event)"
						labelposition="left"
						size="30" maxlength="255"
						cssClass=""/>
                    </p>
                    
                    <p class="criteria">
                        <label id="gcmProjectKeyLabel" style="width: 180px;visibility:hidden;display:none" class="fieldText">GCM API Key (*):</label>
                        <s:textfield
						name="servicio.gcmProjectKey" value="%{servicio.gcmProjectKey}" id="servicio.gcmProjectKey"
						theme="simple" cssStyle="visibility:hidden;display:none"
						labelposition="left"
						size="30" maxlength="255"
						cssClass="" style="width:180px"/>
                    </p>
                    
                     <p class="criteria">
                        <label id="apnsRutaCertificadoLabel" style="width: 180px;visibility:hidden;display:none" class="fieldText">APNS Ruta Certificado (*):</label>
                        <s:textfield
						name="servicio.apnsRutaCertificado" value="%{servicio.apnsRutaCertificado}" id="servicio.apnsRutaCertificado"
						theme="simple" cssStyle="visibility:hidden;display:none"
						labelposition="left"
						size="30" maxlength="255"
						cssClass=""/>
                    </p>
                    
                     <p class="criteria">
                        <label id="apnsPasswordCertificadoLabel" style="width: 179px;visibility:hidden;display:none" class="fieldText">APNS Password Certificado (*):</label>
                        <s:password
						name="servicio.apnsPasswordCertificado" value="%{servicio.apnsPasswordCertificado}" id="servicio.apnsPasswordCertificado"
						theme="simple" cssStyle="visibility:hidden;display:none"
						labelposition="left"
						size="24" maxlength="255" 
						cssClass="" showPassword="true"/>
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
						t�cnico: (*)</label>
					<s:textfield name="servicio.responsableTecnicoNombre"
						value="%{servicio.responsableTecnicoNombre}"
						id="servicio.responsableTecnicoNombre" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Email: (*)</label>
					<s:textfield name="servicio.responsableTecnicoEmail"
						value="%{servicio.responsableTecnicoEmail}"
						id="servicio.responsableTecnicoEmail" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Responsable
						funcional: (*)</label>
					<s:textfield name="servicio.responsableFuncionalNombre"
						value="%{servicio.responsableFuncionalNombre}"
						id="servicio.responsableFuncionalNombre" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Email: (*)</label>
					<s:textfield name="servicio.responsableFuncionalEmail"
						value="%{servicio.responsableFuncionalEmail}"
						id="servicio.responsableFuncionalEmail" theme="simple"
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
                    	<label theme="simple" style="width: 120px;" class="fieldText">Agrupaci�n</label>
                    	<p class="criteria">
                    		<label theme="simple" style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total de mensajes agrupador por estado:</label>
							<s:checkbox theme="simple" name="newAgrupacionEstado" id="servicio.agrupacionEstado" value="%{newAgrupacionEstado}"/>
                    	</p>
                    	<p class="criteria" id="agrupacionCodOrg">
                    		<label theme="simple" style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total de mensajes agrupador por organismo:</label>
							<s:checkbox theme="simple" name="newAgrupacionCodOrg" id="servicio.agrupacionCodOrg" value="%{newAgrupacionCodOrg}"/>
                    	</p>
                    	<p class="criteria" id="agrupacionCodSia">
                    		<label theme="simple" style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total de mensajes agrupador por procedimiento:</label>
							<s:checkbox theme="simple" name="newAgrupacionCodSia" id="servicio.agrupacionCodSia" value="%{newAgrupacionCodSia}"/>
                    	</p>
                    	<p class="criteria" id="agrupacionCodOrgPagador" style="visibility:hidden">
                    		<label theme="simple" style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total de mensajes agrupador por organismo pagador:</label>
							<s:checkbox theme="simple" name="agrupacionCodOrgPagador" id="servicio.agrupacionCodOrgPagador" 
							value="%{newAgrupacionCodOrgPagador}"/>
                    	</p>
                    </p>
                </div>
            </div>
        </s:form>                  
        </div>
<script>
	checkCanalHeader(document.getElementById("servicio.canalId"));
</script>