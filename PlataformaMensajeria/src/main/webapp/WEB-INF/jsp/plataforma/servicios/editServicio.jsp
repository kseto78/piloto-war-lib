<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href="permisoDenegado.action";
	
	</script>
</plataforma:securityRedirect>

<div class="mainContent">

	<s:form id="frmEditServicio" method="POST" action="updateServicio"
		validate="false" theme="simple" cssClass="">
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:if
					test='%{servicio.pendienteaprobacion == "1"}'>
					<s:submit theme="simple" value="%{getText('buttons.text.aprobar')}"
						cssClass="button" />
				</s:if> <s:else>
					<s:submit theme="simple" value="%{getText('buttons.text.save')}"
						cssClass="button" />
				</s:else> <input type="button" onclick="javascript:location.href='${volver}'"
				class="button" value="Volver">
			</span> <label>EDICIÓN DE SERVICIO</label>
		</h3>
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp"%>
		<%@include
			file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp"%>
		<sj:dialog  id="dialogPassbook" title="Detalle Passbook" cssStyle="display:none" autoOpen="false"></sj:dialog>
		<sj:dialog id="dialogPlanifications" title="Planificación"
			cssStyle="min-height:150px;display:none" autoOpen="false">
			<div class="editContainer">
				<div class="nameDescription">
					<label>Editar Días y horas</label>
				</div>
				<div class="editContent">
					<p class="criteria">
						<span> <label class="fieldText" style="width: 120px;">Días
								de la semana:</label> L <s:checkbox id="planificacion.lunes"
								name="planificacion.lunes" value="%{planificacion.lunes}"
								theme="simple" /> M <s:checkbox id="planificacion.martes"
								name="planificacion.martes" value="%{planificacion.martes}"
								theme="simple" /> X <s:checkbox id="planificacion.miercoles"
								name="planificacion.miercoles"
								value="%{planificacion.miercoles}" theme="simple" /> J <s:checkbox
								id="planificacion.jueves" name="planificacion.jueves"
								value="%{planificacion.jueves}" theme="simple" /> V <s:checkbox
								id="planificacion.viernes" name="planificacion.viernes"
								value="%{planificacion.viernes}" theme="simple" /> S <s:checkbox
								id="planificacion.sabado" name="planificacion.sabado"
								value="%{planificacion.sabado}" theme="simple" /> D <s:checkbox
								id="planificacion.domingo" name="planificacion.domingo"
								value="%{planificacion.domingo}" theme="simple" />
						</span>
					<p class="criteria">
						<label class="fieldText" style="width: 120px;">Hora
							Inicio:</label>
						<s:select id="planificacion.horaDesde"
							name="planificacion.horaDesde" emptyOption="false" theme="simple"
							labelposition="left" list="comboHorasInicio" listKey="codigo"
							listValue="descripcion" cssClass="W65"
							value="%{planificacion.horaDesde}" disabled="false" />
						Hora Fin:
						<s:select id="planificacion.horaHasta"
							name="planificacion.horaHasta" emptyOption="false" theme="simple"
							labelposition="left" list="comboHorasFin" listKey="codigo"
							listValue="descripcion" cssClass="W65"
							value="%{planificacion.horaHasta}" disabled="false" />
					</p>
				</div>
			</div>
			<div class="footerPopup">
				<span class="leftSide"></span> <span class="rightSide"> 
					<s:submit id="Guardar" name="Guardar" value="Guardar" onclick="return validaPlanJS('frmEditPlanificacion','planificacion')" cssClass="button"/>
					        
				</span>
			</div>
		</sj:dialog>
		<div class="editContainer">
			<div class="nameDescription">
				<label>Datos Generales</label>
			</div>
			<s:hidden name="servicio.multiorganismo"
					id="servicio.multiorganismo" value="%{servicio.multiorganismo}" />
			<div class="editContent">
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">ID:</label> <label
						style="width: 120px;">${servicio.servicioId}</label>

				</p>

				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Aplicacion
						(*):</label>
					<s:select id="servicio.aplicacionid" name="servicio.aplicacionid"
						emptyOption="true" theme="simple" labelposition="left"
						list="comboAplicaciones" listKey="codigo" listValue="descripcion"
						cssClass="" value="%{servicio.aplicacionid}" disabled="false"
						onchange="cargarNuevaAplicacion()" cssStyle="width:150px"
						disable="%{readonly}" />
					<input type="button" value="Ir a Aplicación" class="button"
						onclick='javascript:location.href="editAplicacion.action?idAplicacion=${servicio.aplicacionid}&from=editServicio.action&idFrom=${idServicio}&var=idServicio"' />
				</p>

				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Canal (*):</label>
					<%
						String canalDisabled = (String) request.getAttribute("canalDisabled");
					%>
					<%
						if(canalDisabled!=null){
					%>
					<s:select onchange="checkCanalHeader(this)" id="servicio.canalid"
						name="servicio.canalid" emptyOption="true" theme="simple"
						labelposition="left" list="comboCanales" listKey="codigo"
						listValue="descripcion" cssStyle="width:150px" cssClass=""
						value="%{servicio.canalid}" disabled="true" />
					<input type="hidden" name="servicio.canalid" id="servicio.canalid"
						value="${servicio.canalid}">
					<%
						}else{
					%>
					<s:select onchange="checkCanalHeader(this)" id="servicio.canalid"
						name="servicio.canalid" emptyOption="true" theme="simple"
						labelposition="left" list="comboCanales" listKey="codigo"
						listValue="descripcion" cssStyle="width:150px" cssClass=""
						value="%{servicio.canalid}" disabled="false" />

					<%
						}
					%>
				</p>

				<p class="criteria">
					<s:hidden theme="simple" id="servicio.servicioId"
						name="servicio.servicioId" value="%{servicio.servicioId}" />
					<s:hidden theme="simple" id="idServicio" name="idServicio"
						value="%{servicio.servicioId}" />
					<s:hidden theme="simple" id="vapidPublicKey" name="vapidPublicKey" value="%{vapidPublicKey}" />
					<s:hidden theme="simple" id="vapidPrivateKey" name="vapidPrivateKey" value="%{vapidPublicKey}" />
					
					<label style="width: 120px;" class="fieldText">Nombre (*):</label>
					<s:textfield name="servicio.nombre" value="%{servicio.nombre}"
						id="servicio.nombre" theme="simple" labelposition="left" size="70"
						maxlength="255" cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Descripción:</label>
					<s:textarea name="servicio.descripcion" id="servicio.descripcion"
						theme="simple" rows="6" cols="70" value="%{servicio.descripcion}">
					</s:textarea>
				</p>

				<s:if test='%{servicio.pendienteaprobacion == "1"}'>
					<p class="criteria">
						<label class="fieldText" style="width: 120px;">Estado</label> 
						<strong>Pendiente de aprobación</strong> 
						<input type="hidden" name="servicio.isActivo"
							id="servicio.isActivo" value="%{servicio.activado}"/>
					</p>
				</s:if>
				<s:else>
					<p class="criteria">
						<label class="fieldText" style="width: 120px;">Activo</label>
						<s:checkbox theme="simple" id="servicio.isActivo"
							name="servicio.isActivo" value="%{servicio.activado}" />
					</p>
				</s:else>
				<label id="exclusivoLabel">
					<p class="criteria">
							<label class="fieldText" style="width: 120px;">Premium</label>
							<s:checkbox theme="simple" id="servicio.isPremium"
								name="servicio.isPremium" value="%{servicio.premium}" onclick="selectOnlyThis(this.id)"/>
					</p>				
					<p class="criteria">
						<label class="fieldText" style="width: 120px;">Exclusivo</label>
						<s:checkbox theme="simple" id="servicio.isExclusivo"
							name="servicio.isExclusivo" value="%{servicio.exclusivo}" onclick="selectOnlyThis(this.id)"/>
					</p>
				</label>	
				
				
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Nº Max.
						envíos (*):</label>
					<s:textfield name="servicio.nmaxenvios"
						value="%{servicio.nmaxenvios}" id="servicio.nmaxenvios"
						theme="simple" labelposition="left"
						onKeyPress="return numbersonly(this, event)" size="20"
						maxlength="6" cssClass="" />
				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Nº Max.
						Reintentos:</label>
					<s:textfield name="servicio.numeroMaxReenvios"
						value="%{servicio.numeroMaxReenvios}" id="servicio.numeroMaxReenvios"
						theme="simple"
						onKeyPress="return numbersonly(this, event)"
						labelposition="left" size="6" maxlength="6" cssClass="" />
				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Historificación:</label>
										<span> <c:set var="rh1" value="" /> <c:set var="rh2"
							value="" /> <c:set var="rh3" value="" /> <c:set var="rh4"
							value="" /> 
							<c:choose>
    							<c:when test="${servicio.historificacion==1}">
        	 						<c:set var="rh1" value="checked" />
        						</c:when>  
   								<c:when test="${servicio.historificacion==2}">
        	 						<c:set var="rh2" value="checked" />
        						</c:when>  
        						<c:when test="${servicio.historificacion==3}">
        	 						<c:set var="rh3" value="checked" />
        						</c:when>  
							    <c:otherwise>
							       <c:set var="rh4" value="checked" />
							    </c:otherwise>
							</c:choose>
						<input type="radio" id="servicio.historificacion"
						name="servicio.historificacion" value="1" ${rh1} />30 días 
						<input type="radio" id="servicio.historificacion"
						name="servicio.historificacion" value="2" ${rh2} />60 días 
						<input type="radio" id="servicio.historificacion"
						name="servicio.historificacion" value="3" ${rh3} />90 días 
						<input type="radio" id="servicio.historificacion"
						name="servicio.historificacion" value="4" ${rh4} />Otro
					</span>
					<s:textfield name="servicio.historificacionInput"
						value="%{servicio.historificacionInput}"
						id="servicio.historificacionInput" theme="simple"
						onKeyPress="return numbersonly(this, event)" labelposition="left"
						size="3" maxlength="3" cssClass="" />
					<label> días</label>
				</p>
				<p class="criteria">
					<label style="width: 425px; margin-left: 130px;" class="fieldText"><i>Si
							el periodo de tiempo es superior a 90 días debe indicar el motivo</i></label>
					<s:textarea style="margin-left: 130px;" theme="simple" rows="6"
						cols="70" name="servicio.motivohistorificacion"
						labelposition="left" id="servicio.motivohistorificacion"
						value="%{servicio.motivohistorificacion}" />
				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Conservación:</label>
					<span> <c:set var="rc1" value="" /> <c:set var="rc2"
							value="" /> <c:set var="rc3" value="" /> <c:set var="rc4"
							value="" /> <c:if test="${servicio.conservacion==1}">
							<c:set var="rc1" value="checked" />
						</c:if> <c:if test="${servicio.conservacion==2}">
							<c:set var="rc2" value="checked" />
						</c:if> <c:if test="${servicio.conservacion==3}">
							<c:set var="rc3" value="checked" />
						</c:if> <c:if test="${servicio.conservacion==4}">
							<c:set var="rc4" value="checked" />
						</c:if> <input type="radio" id="servicio.conservacion"
						name="servicio.conservacion" value="1" ${rc1} />1 año <input
						type="radio" id="servicio.conservacion"
						name="servicio.conservacion" value="2" ${rc2} />2 años <input
						type="radio" id="servicio.conservacion"
						name="servicio.conservacion" value="3" ${rc3} />3 años <input
						type="radio" id="servicio.conservacion"
						name="servicio.conservacion" value="4" ${rc4} />Otro
					</span>
					<s:textfield name="servicio.conservacionInput"
						value="%{servicio.conservacionInput}"
						id="servicio.conservacionInput" theme="simple"
						onKeyPress="return numbersonly(this, event)" labelposition="left"
						size="2" maxlength="2" cssClass="" />
					<label> años</label>
				</p>
				<p class="criteria">
					<label style="width: 425px; margin-left: 130px;" class="fieldText"><i>Si
							el periodo de tiempo es superior a 3 años debe indicar el motivo</i></label>
					<s:textarea style="margin-left: 130px;" theme="simple" rows="6"
						cols="70" name="servicio.motivoconservacion" labelposition="left"
						id="servicio.motivoconservacion" 
						value="%{servicio.motivoconservacion}" />
				</p>
				<!--  para la parte de MAIL -->
				<p class="criteria">
					<label id="frommailLabel" style="width: 120px; visibility: hidden; display: none;"
						class="fieldText">Cuenta Env&iacute;o:</label>
					<s:textfield name="servicio.frommail" value="%{servicio.frommail}"
						id="servicio.frommail" onblur="verifyEmail(this)" theme="simple"
						cssStyle="visibility:hidden display:none" labelposition="left" size="70"
						maxlength="255" cssClass="" />
				</p>


				<p class="criteria">
					<label id="frommailnameLabel"
						style="width: 120px; visibility: hidden; display: none;" class="fieldText">Nombre
						C. Env&iacute;o:</label>
					<s:textfield name="servicio.frommailname"
						value="%{servicio.frommailname}" id="servicio.frommailname"
						theme="simple" cssStyle="visibility:hidden display:none" labelposition="left"
						size="70" maxlength="255" cssClass="" />
				</p>
				<!--  FIN parte de MAIL -->

				<p class="criteria">
					<label id="endpointLabel" style="width: 120px; visibility: hidden; display: none;" class="fieldText">EndPoint (*):</label>
					<s:textfield name="servicio.endpoint" value="%{servicio.endpoint}"
						id="servicio.endpoint" theme="simple" cssStyle="visibility:hidden;display:none;" labelposition="left"
						size="70" maxlength="255" cssClass="" />
				</p>

				<p class="criteria">
					<label id="nombreloteenvioLabel"
						style="width: 121px; visibility: hidden; display: none;" class="fieldText">Nombre
						Lote Envío (*):</label>
					<s:textfield name="servicio.nombreloteenvio"
						value="%{servicio.nombreloteenvio}" id="servicio.nombreloteenvio"
						theme="simple" cssStyle="visibility:hidden;display:none;" labelposition="left"
						size="70" maxlength="255" cssClass="" />
				</p>
				<!--  FIN parte de RECEPCIÓN SMS -->
				<!--  para la parte de PUSH -->
				<p class="criteria" id="plataformaid" style="visibility: hidden; display: none;">
					<label id="plataformaLabel" theme="simple" style="width: 120px"
						class="fieldText">Plataforma (*):</label>
					<s:checkbox theme="simple" name="servicio.isAndroidPlataforma"
						id="newPlataformaAndroid" value="%{servicio.isAndroidPlataforma}" />
					Android
					<s:checkbox theme="simple" name="servicio.isIosPlataforma"
						id="newPlataformaiOS" value="%{servicio.isIosPlataforma}" />
					iOS
				</p>

				<p class="criteria">
					<label id="badgeLabel" style="width: 180px; visibility: hidden; display: none;"
						class="fieldText">Agrupar notificaciones cada (*):</label>
					<s:textfield name="servicio.badge" value="%{servicio.badge}"
						id="servicio.badge" theme="simple" cssStyle="visibility:hidden;display:none;"
						onKeyPress="return numbersonly(this, event)" labelposition="left"
						size="30" maxlength="255" cssClass="" />
				</p>

				<p class="criteria">
					<label id="fcmprojectkeyLabel"
						style="width: 180px; visibility: hidden; display: none;" class="fieldText">FCM
						API Key (*):</label>
					<s:textfield name="servicio.fcmprojectkey"
						value="%{servicio.fcmprojectkey}" id="servicio.fcmprojectkey"
						theme="simple" cssStyle="visibility:hidden;display:none;" labelposition="left"
						size="30" maxlength="255" cssClass="" />
				</p>

				<p class="criteria">
					<label id="apnsrutacertificadoLabel"
						style="width: 180px; visibility: hidden; display: none;" class="fieldText">APNS
						Ruta Certificado (*):</label>
					<s:textfield name="servicio.apnsrutacertificado"
						value="%{servicio.apnsrutacertificado}"
						id="servicio.apnsrutacertificado" theme="simple"
						cssStyle="visibility:hidden;display:none;" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>

				<p class="criteria">
					<label id="apnspasswordcertificadoLabel"
						style="width: 179px; visibility: hidden; display: none;" class="fieldText">APNS
						Password Certificado (*):</label>
					<s:password name="servicio.apnspasswordcertificado"
						value="%{servicio.apnspasswordcertificado}"
						id="servicio.apnspasswordcertificado" theme="simple"
						cssStyle="visibility:hidden;display:none;" labelposition="left" size="24"
						maxlength="55" cssClass="" showPassword="true" />
				</p>

				<!--  FIN parte de push -->
				
				<!-- Inicio parte WEB push -->
				<p class="criteria">
					 <span>
					<label id="caducidadWebPush" style="width: 120px; visibility: hidden; display: none;" class="fieldText">Caducidad Notificación</label>
					<s:textfield name="servicio.caducidadWebPush"
						value="%{servicio.caducidadWebPush}" id="servicio.caducidadWebPush"
						theme="simple" cssStyle="visibility:hidden;display:none;"
						onKeyPress="return numbersonly(this, event)"
						labelposition="left" size="6" maxlength="6" cssClass="" />
					</span>
					 <span>
						 <input id="botonclaves" type="button" value="Generar Claves" class="button"  style="visibility:hidden;display:none;disabled;"
						 onclick="return generarClaves()"/>
					</span>
				</p>
				
				<p class="criteria">
					<label id="vapidPublicKeyLabel"
						style="width: 120px; visibility: hidden; display: none;" class="fieldText">
						Clave Pública (*):</label>
					<s:textfield name="servicio.vapidPublicKey"
						value="%{servicio.vapidPublicKey}"
						id="servicio.vapidPublicKey" theme="simple" disabled="true"
						cssStyle="visibility:hidden;display:none;disabled;" labelposition="left" size="95"
						maxlength="255" cssClass="" />
				</p>

				<p class="criteria">
					<label id="vapidPrivateKeyLabel"
						style="width: 120px; visibility: hidden; display: none;" class="fieldText">
						Clave Privada (*):</label>
					<s:textfield name="servicio.vapidPrivateKey"
						value="%{servicio.vapidPrivateKey}"
						id="servicio.vapidPrivateKey" theme="simple" disabled="true"
						cssStyle="visibility:hidden;display:none;disabled=true;" labelposition="left" size="50"
						maxlength="55" cssClass="" showPassword="true" />
				</p>
				
				<!-- Fin parte WEB PUSH -->

				<p class="criteria">
					<label style="width: 150px;" class="fieldText"><i>(*)
							Campos obligatorios</i></label>
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
					<s:checkbox theme="simple" name="servicio.isInformesActivo"
						id="servicio.isInformesActivo"
						value="%{servicio.isInformesActivo}" />
				</p>
				<p class="criteria">
					<label theme="simple" style="width: 120px;" class="fieldText">Destinatarios:</label>
					<s:textarea name="servicio.informesdestinatarios"
						id="servicio.informesdestinatarios"
						placeholder="Introduce direcciones de email separadas por ';'"
						theme="simple" cssClass="W240"
						value="%{servicio.informesdestinatarios}" />
				</p>
				<p class="criteria">
					<label theme="simple" style="width: 120px;" class="fieldText">Agrupación</label>
				<p class="criteria">
					<label theme="simple"
						style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total
						de mensajes agrupador por estado:</label>
					<s:checkbox theme="simple" name="servicio.isAgrupacionEstado"
						id="servicio.agrupacionestado"
						value="%{servicio.isAgrupacionEstado}" />
				</p>
				<p class="criteria" id="agrupacioncodorg">
					<label theme="simple"
						style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total
						de mensajes agrupador por organismo:</label>
					<s:checkbox theme="simple" name="servicio.isAgrupacionCodOrg"
						id="servicio.agrupacioncodorg"
						value="%{servicio.isAgrupacionCodOrg}" />
				</p>
				<p class="criteria" id="agrupacioncodsia">
					<label theme="simple"
						style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total
						de mensajes agrupador por procedimiento:</label>
					<s:checkbox theme="simple" name="servicio.isAgrupacionCodSia"
						id="servicio.agrupacioncodsia"
						value="%{servicio.isAgrupacionCodSia}" />
				</p>
				<p class="criteria" id="agrupacioncodorgpagador"
					style="visibility: hidden">
					<label theme="simple"
						style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total
						de mensajes agrupador por organismo pagador:</label>
					<s:checkbox theme="simple"
						name="servicio.isAgrupacionCodOrgPagador"
						id="servicio.agrupacioncodorgpagador"
						value="%{servicio.isAgrupacionCodOrgPagador}" />
				</p>
				</p>
			</div>
		</div>
	</s:form>

	<div class="editContainer" id="containerMultiorganismo"
		name="containerMultiorganismo" style="display: none">
		<div class="nameDescription">
			<label>Organismos</label>
			<p>Un servicio Multiorganismo puede tener asociado un conjunto
				ilimitado de organismos</p>
		</div>
		<div class="editContent">
			<s:form id="formaddServicioOrganismo" theme="simple" validate="false"
				name="formaddServicioOrganismo" method="POST"
				action="addServicioOrganismos">

				<p class="criteria">
					<label class="fieldText" style="width: 120px;">Multiorganismo</label>
					<s:checkbox theme="simple" id="servicio.multiorganismo"
						onchange="activarMultiorganismo()" name="servicio.multiorganismo"
						value="%{servicio.multiorganismo}" />
						 <a class="addLink" id="addItem" onclick="insertarNuevoOrganismo()"
						name="addItem">Añadir Item</a>
				</p>
				<s:hidden name="servicio.multiorganismo"
					id="servicio.multiorganismo" value="%{servicio.multiorganismo}" />

				</p>
				<s:hidden theme="simple" id="idServicio" name="idServicio"
						value="%{servicio.servicioId}" />
						
				<div class="ui-widget" class ="ui-autocomplete-loading">
					<p class="criteria">
					<label style="width: 120px;"
							class="fieldText">Organismo</label>
					<s:textfield name="search"
							value=""
							id="search" theme="simple"
							labelposition="left" size="55" maxlength="255"
					/>
            			
				</div>
				</p>

			</s:form>

			<script type="text/javascript">
                   function checkBotonEliminarSeleccionados3(){
                       var listaChecks = document.getElementById('formDeleteOrganismoSelected').checkDelListServiciosOrganismos;

                       var botonEliminarSeleccionados = document.getElementById('eliminaSeleccionadosOrganismos');
                       var enable=false;
                 		if(listaChecks.checked){
                   			enable=true;
                   		}
                       for (i = 0; lcheck = listaChecks[i]; i++) {
                           if (lcheck.checked) {
                               enable=true;   
                           }
                       }
                       if(enable){
                           botonEliminarSeleccionados.disabled="";
                       }else{
                           botonEliminarSeleccionados.disabled="disabled";
                       }
                   }
                   function selectAllSO(checkAllSO){
                       var listaChecks = document.getElementById('formDeleteOrganismoSelected').checkDelListServiciosOrganismos;
                       if(listaChecks.checked!="undefined"){
                    	   if(checkAllSO.checked){
                    		   listaChecks.checked=true;
                    	   }else{
                    		   listaChecks.checked=false;
                    	   }
                       }
                       
                       for (i = 0; lcheck = listaChecks[i]; i++) {
                           if (checkAllSO.checked) {
                               lcheck.checked=true;   
                           }else{
                        	   lcheck.checked=false;
                           }
                       }  
                       checkBotonEliminarSeleccionados3();
                   }
               
		</script>

			<s:form id="formDeleteOrganismoSelected"
				onsubmit="return confirmDeleteSelected();" theme="simple"
				validate="false" name="formDeleteOrganismoSelected" method="POST"
				action="deleteServicioOrganismosSelected">

				<table cellspacing="0" cellpadding="0" border="0"
					onload="checkCanalHeader(this)">
					<thead>
						<tr>
							<th class=""><input type="checkbox" id="checkAllSO"
								theme="simple" onclick="selectAllSO(this)" /></th>
							<th class="TH100">Código Organismo</th>
							<th class="TH150">Nombre</th>
							<th class="TH240">Descripción</th>
							<s:if test='%{servicio.canalid == 1}'>
								<th class="TH20 separator"></th>
							</s:if>
							<th class="TH45 separator"></th>
						</tr>
					</thead>

					<tbody>
						<s:iterator value="%{listaServicioOrganismos}" var="organismo"
							status="organismoStatus">
							<tr
								class="<s:if test='#organismoStatus.odd == true '></s:if><s:else>odd</s:else>">
								<td class="darkTD TH15"><input type="checkbox"
									onclick="checkBotonEliminarSeleccionados3()"
									id="checkDelListServiciosOrganismos"
									name="checkDelListServiciosOrganismos"
									value="${organismo.servicioOrganismoId}" /> <input
									type="hidden" id="__checkbox_checkDelListServiciosOrganismos"
									name="__checkbox_checkDelListServiciosOrganismos" /></td>
								<td><s:label value="%{DIR3Organismo}" /></td>
								<td><s:label value="%{nombreOrganismo}" /></td>
								<td><s:label value="%{descripcionOrganismo}" /></td>
								<s:if test='%{servicio.canalid == 1}'>
									<td class="buttons"><span class="edit">
											<div
												id="ajaxloader_ajax_${organismo.servicioOrganismoId}">
												<span id="ajax_${organismo.servicioOrganismoId}"
													name="ajax" title="Ver Passbook"
													onclick="return loadPassbook(this,${organismo.servicioOrganismoId},${servicio.servicioId},${organismo.organismoId})"
													class="btnPassbook passbook_link">
													</span>
											</div>
									</td>
								</s:if>
								
								<td class="buttons"><span class="edit"><a 
										class="btnEdit" title="Editar"
										href="editOrganismo.action?idOrganismo=${organismo.organismoId}&from=editServicio.action&idFrom=${servicio.servicioId}&var=idServicio"></a>
								</span> 
								<span class="delete"> <a class="btnDelete"
										title="Eliminar" onclick="return confirmDelete();"
										href="deleteServicioOrganismo.action?idServicioOrganismo=${organismo.servicioOrganismoId}&idServicio=${servicio.servicioId}&idOrganismo=${organismo.organismoId}"></a>
								</span></td>

							</tr>
						</s:iterator>
						<s:if test="%{listaServicioOrganismos == null}">
							<tr>

								<td colspan="6">No se ha configurado servidor para el
									servicio</td>
							</tr>
							<script>document.getElementById('checkAllSO').style.visibility="hidden";</script>
						</s:if>
						<s:elseif test='%{servicio.canalid == 1}'>
							<tr>
								<tfoot>
									<td colspan="5"><s:submit
											id="eliminaSeleccionadosOrganismos"
											name="eliminaSeleccionadosOrganismos" theme="simple"
											disabled="true"
											value="%{getText('button.plataforma.eliminarseleccionados')}"
											cssClass="button" />
									<td>
								</tfoot>
							</tr>
						</s:elseif>
						<s:else>
							<tr>
								<tfoot>
									<td colspan="4"><s:submit
											id="eliminaSeleccionadosOrganismos"
											name="eliminaSeleccionadosOrganismos" theme="simple"
											disabled="true"
											value="%{getText('button.plataforma.eliminarseleccionados')}"
											cssClass="button" />
									<td>
								</tfoot>
							</tr>
						</s:else>

					</tbody>
				</table>
				<s:hidden name="idServicio" id="idServicio" value="%{idServicio}" />
			</s:form>

		</div>
	</div>



	<div class="editContainer">
		<div class="nameDescription">
			<label>Servidores/Proveedores</label>
			<p>Servidores / Proveedores por Defecto. Se utilizan si las
				planificaciones no tienen asociado servidor/proveedor</p>
		</div>
		<div class="editContent">
			<s:form id="formaddServidorServicio" theme="simple" validate="false"
				name="formaddServidorServicio" method="POST"
				action="addServidorServicio">
				<p class="criteria">
					<span style="width: 300px;"> <label style="width: 145px;"
						class="fieldText">Servidor / Proveedor:</label> <s:select
							id="servidorServicio.servidorId"
							name="servidorServicio.servidorId" emptyOption="true"
							theme="simple" labelposition="left" title="Servidor / Proveedor"
							list="comboConfiguraciones" listKey="codigo"
							listValue="descripcion" cssClass="" cssStyle="width:138px"
							value="%{servidorServicio.servidorId}" disabled="false" /> <s:hidden
							name="idServicio" id="idServicio" value="%{idServicio}" />
					</span> 
					 <a class="addLink" id="addItem"
						onclick="return checkItem('formaddServidorServicio',false,'${servicio.canalid}');"
						name="addItem">Añadir Item</a>
				</p>
				<p class="criteria">
					<label id="headerSMSLabel" style="width: 145px; visibility: hidden"
						class="fieldText">Header SMS (*):</label>
					<s:textfield name="servidorServicio.headerSMS" title="Header SMS"
						value="%{servidorServicio.headerSMS}"
						id="servidorServicio.headerSMS" theme="simple"
						cssStyle="visibility:hidden" labelposition="left" size="20"
						maxlength="255" cssClass="" />
				</p>
				<p class="criteria">
					<label id="proveedorUsuarioSMSLabel"
						style="width: 145px; visibility: hidden" class="fieldText">Proveedor
						Usuario (*):</label>
					<s:textfield name="servidorServicio.proveedorUsuarioSMS"
						title="Proveedor usuario SMS"
						value="%{servidorServicio.proveedorUsuarioSMS}"
						id="servidorServicio.proveedorUsuarioSMS" theme="simple"
						cssStyle="visibility:hidden" labelposition="left" size="20"
						maxlength="255" cssClass="" />
				</p>
				<p class="criteria">
					<label id="proveedorPassSMSLabel"
						style="width: 145px; visibility: hidden" class="fieldText">Proveedor
						Contraseña (*):</label>
					<s:password name="servidorServicio.proveedorPasswordSMS"
						title="Proveedor contraseña SMS"
						value="%{servidorServicio.proveedorPasswordSMS}"
						id="servidorServicio.proveedorPasswordSMS" theme="simple"
						cssStyle="visibility:hidden" labelposition="left" size="15"
						maxlength="255" cssClass="" showPassword="true" />
				</p>
				<p class="criteria">
					<label id="prefijoSMSLabel" style="width: 145px; visibility: hidden"
						class="fieldText">Prefijo SMS:</label>
					<s:textfield name="servidorServicio.prefijoSMS" title="Prefijo SMS"
						value="%{servidorServicio.prefijoSMS}"
						id="servidorServicio.prefijoSMS" theme="simple"
						cssStyle="visibility:hidden" labelposition="left" size="20"
						maxlength="255" cssClass="" />
				</p>
			</s:form>
			<script type="text/javascript">
                   function checkBotonEliminarSeleccionados(){
                       var listaChecks = document.getElementById('formDeleteSelected').checkDelListServidorServicios;

                       var botonEliminarSeleccionados = document.getElementById('eliminaSeleccionadosS');
                       var enable=false;
                 		if(listaChecks.checked){
                   			enable=true;
                   		}
                       for (i = 0; lcheck = listaChecks[i]; i++) {
                           if (lcheck.checked) {
                               enable=true;   
                           }
                       }
                       if(enable){
                           botonEliminarSeleccionados.disabled="";
                       }else{
                           botonEliminarSeleccionados.disabled="disabled";
                       }
                   }
                   function selectAllS(checkAllS){
                       var listaChecks = document.getElementById('formDeleteSelected').checkDelListServidorServicios;
                       if(listaChecks.checked!="undefined"){
                    	   if(checkAllS.checked){
                    		   listaChecks.checked=true;
                    	   }else{
                    		   listaChecks.checked=false;
                    	   }
                       }
                       
                       for (i = 0; lcheck = listaChecks[i]; i++) {
                           if (checkAllS.checked) {
                               lcheck.checked=true;   
                           }else{
                        	   lcheck.checked=false;
                           }
                       }  
                       checkBotonEliminarSeleccionados();
                   }
               
		</script>

			<s:form id="formDeleteSelected"
				onsubmit="return confirmDeleteSelected();" theme="simple"
				validate="false" name="formDeleteSelected" method="POST"
				action="deleteServidorServiciosSelected">

				<table cellspacing="0" cellpadding="0" border="0"
					onload="checkCanalHeader(this)">
					<thead>
						<tr>
							<th class=""><input type="checkbox" id="checkAllS"
								theme="simple" onclick="selectAllS(this)" /></th>
							<th class="TH200">Servidor / Proveedor</th>
							<th class="TH80separator" id="auna">Header:</th>
							<th class="TH50separator" id="aune">Usuario:</th>
							<th class="TH50separator" id="auni">Organismo:</th>
							<th class="TH50separator" id="auno">Prefijo SMS:</th>
							<th class="TH20 separator"></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="%{listaServidoresServicios}"
							var="servidorServicio" status="servidorServicioStatus">
							<tr
								class="<s:if test='#servidorServicioStatus.odd == true '></s:if><s:else>odd</s:else>">
								<td class="darkTD TH15"><input type="checkbox"
									onclick="checkBotonEliminarSeleccionados()"
									id="checkDelListServidorServicios"
									name="checkDelListServidorServicios"
									value="${servidorServicio.servidorServicioId}" /> <input
									type="hidden" id="__checkbox_checkDelListServidorServicios"
									name="__checkbox_checkDelListServidorServicios" /></td>
								<td><s:label value="%{nombreServidor}" /></td>
								<td id="aunaValue1"><s:label value="%{headerSMS}" /></td>
								<td id="auneValue1"><s:label value="%{proveedorUsuarioSMS}" /></td>
								<td id="auniValue1"><s:label value="%{DIR3Organismo}" /></td>
								<td id="aunoValue1"><s:label value="%{prefijoSMS}" /></td>
								<td class="buttons"><span class="delete"> <a
										class="btnDelete" onclick="return confirmDelete();"
										href="deleteServidorServicio.action?servidorServicioId=${servidorServicio.servidorServicioId}&idServicio=${servicio.servicioId}&idOrganismo=${servidorServicio.organismoId}"></a>
								</span></td>
							</tr>
						</s:iterator>
						<s:if test="%{listaServidoresServicios == null}">
							<tr>

								<td colspan="7">No se ha configurado servidor para el
									servicio</td>
							</tr>
							<script>document.getElementById('checkAllS').style.visibility="hidden";</script>
						</s:if>
						<s:else>
							<tr>
							<tfoot>
								<td colspan="6"><s:submit id="eliminaSeleccionadosS"
										name="eliminaSeleccionadosS" theme="simple" disabled="true"
										value="%{getText('button.plataforma.eliminarseleccionados')}"
										cssClass="button" />
								<td>
							</tfoot>
							</tr>
						</s:else>

					</tbody>
				</table>
				<s:hidden name="idServicio" id="idServicio" value="%{idServicio}" />
			</s:form>

		</div>
	</div>

	<div class="editContainer">
		<div class="nameDescription">
			<label>Planificaciones</label>
			<p>Determina los horarios en los que el servicio va a estar
				disponible para realizar los env&iacute;os</p>
		</div>
		<div class="editContent">
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
			<s:form id="formAddPlanificacion" theme="simple" validate="false"
				onsubmit="return validaPlanJS('formAddPlanificacion','planificacionServidor')"
				name="formAddPlanificacion" method="POST"
				action="addPlanificacionServicio">
				<p class="criteria">
					<span style="width: 350px;"> <label style="width: 120px;"
						class="fieldText">Servidor / Proveedor:</label> <s:select
							id="planificacionServidor.servidorId"
							name="planificacionServidor.servidorId" emptyOption="true"
							theme="simple" labelposition="left"
							list="comboConfiguracionesPlan" listKey="codigo"
							listValue="descripcion" cssClass=""
							value="%{planificacionServidor.servidorId}" /> <!-- Cambiado planificacionServicio por Servidor en id-->
						<s:hidden name="planificacionServidor.servicioId"
							id="planificacionServidor.servicioId" value="%{idServicio}" /> 
						<s:hidden
							name="servicio.canalid" id="servicio.canalid"
							value="%{servicio.canalid}" />
					</span>
				</p>
				<p class="criteria">
					<span> <label class="fieldText">L <s:checkbox
								id="planificacionServidor.lunes"
								name="planificacionServidor.lunes"
								value="%{planificacionServidor.lunes}" theme="simple" />
					</label> <label class="fieldTextNoIco"> M <s:checkbox
								id="planificacionServidor.martes"
								name="planificacionServidor.martes"
								value="%{planificacionServidor.martes}" theme="simple" />
					</label> <label class="fieldTextNoIco"> X <s:checkbox
								id="planificacionServidor.miercoles"
								name="planificacionServidor.miercoles"
								value="%{planificacionServidor.miercoles}" theme="simple" />
					</label> <label class="fieldTextNoIco">J <s:checkbox
								id="planificacionServidor.jueves"
								name="planificacionServidor.jueves"
								value="%{planificacionServidor.jueves}" theme="simple" />
					</label> <label class="fieldTextNoIco">V <s:checkbox
								id="planificacionServidor.viernes"
								name="planificacionServidor.viernes"
								value="%{planificacionServidor.viernes}" theme="simple" />
					</label> <label class="fieldTextNoIco">S <s:checkbox
								id="planificacionServidor.sabado"
								name="planificacionServidor.sabado"
								value="%{planificacionServidor.sabado}" theme="simple" />
					</label> <label class="fieldTextNoIco">D <s:checkbox
								id="planificacionServidor.domingo"
								name="planificacionServidor.domingo"
								value="%{planificacionServidor.domingo}" theme="simple" />
					</label>

					</span> <span> <label class="fieldText">Hora Inicio:</label> <s:select
							id="planificacionServidor.horaDesde"
							name="planificacionServidor.horaDesde" emptyOption="false"
							theme="simple" labelposition="left" list="comboHorasInicio"
							listKey="codigo" listValue="descripcion" cssClass="W65"
							value="%{planificacionServidor.horaDesde}" disabled="false" />
					</span> <span> <label class="fieldText">Hora Fin:</label> <s:select
							id="planificacionServidor.horaHasta"
							name="planificacionServidor.horaHasta" emptyOption="false"
							theme="simple" labelposition="left" list="comboHorasFin"
							listKey="codigo" listValue="descripcion" cssClass="W65"
							value="%{planificacionServidor.horaHasta}" disabled="false" />
					</span> <a class="addLink" id="addItem"
						onclick="return checkItem('formAddPlanificacion',true,'planificacionServidor');"
						name="addItem">Añadir Item</a>
				</p>

			</s:form>
			<s:form id="formDeletePlanificacionSeleccionadas"
				onsubmit="return confirmDeleteSelected();" theme="simple"
				validate="false" name="formDeletePlanificacionSeleccionadas"
				method="POST" action="deletePlanificacionesServidorSelected">

				<script type="text/javascript">
                   function checkBotonEliminarSeleccionados2(){
                       var listaChecks = document.getElementById('formDeletePlanificacionSeleccionadas').checkDelListPlanificaciones;

                       var botonEliminarSeleccionados = document.getElementById('eliminaSeleccionadosP');
                       var enable=false;
                 		if(listaChecks.checked){
                   			enable=true;
                   		}
                       for (i = 0; lcheck = listaChecks[i]; i++) {
                           if (lcheck.checked) {
                               enable=true;   
                           }
                       }
                       if(enable){
                           botonEliminarSeleccionados.disabled="";
                       }else{
                           botonEliminarSeleccionados.disabled="disabled";
                       }
                   }
                   function selectAllP(checkAllP){
                       var listaChecks = document.getElementById('formDeletePlanificacionSeleccionadas').checkDelListPlanificaciones;
                       if(listaChecks.checked!="undefined"){
                    	   if(checkAllP.checked){
                    		   listaChecks.checked=true;
                    	   }else{
                    		   listaChecks.checked=false;
                    	   }
                       }
                       
                       for (i = 0; lcheck = listaChecks[i]; i++) {
                           if (checkAllP.checked) {
                               lcheck.checked=true;   
                           }else{
                        	   lcheck.checked=false;
                           }
                       }  
                       checkBotonEliminarSeleccionados2();
                   }
               </script>
				<table cellspacing="0" cellpadding="0" border="0">
					<thead>
						<tr>
							<th class=""><input type="checkbox" id="checkAllP"
								theme="simple" onclick="selectAllP(this)" /></th>
							<th class="TH110">Días</th>
							<th class="TH70 separator">Hora Inicio</th>
							<th class="TH70 separator">Hora Fin</th>
							<th class="TH150">Servidor / Proveedor</th>
							<th class="TH35">Activo</th>
							<th class="TH70" id="aunip">Organismo</th>
							<th class="TH45 separator"></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="%{listaPlanificacionesServicio}"
							var="planificacionServicio" status="planificacionServicioStatuts">
							<tr
								class="<s:if test='#planificacionServicioStatuts.odd == true '></s:if><s:else>odd</s:else>">
								<!-- <td>L, M, J</td> -->
								<td class="darkTD TH15"><input type="checkbox"
									onclick="checkBotonEliminarSeleccionados2()"
									id="checkDelListPlanificaciones"
									name="checkDelListPlanificaciones"
									value="${planificacionServicio.planificacionId }" />
									</center> <input type="hidden"
									idd="__checkbox_checkDelListPlanificaciones"
									name="__checkbox_checkDelListPlanificaciones" />
								<td><s:label value="%{dias}" /></td>
								<td><s:label value="%{horaDesde}" /></td>
								<td><s:label value="%{horaHasta}" /></td>
								<td><s:label value="%{nombreServidor}" /></td>
								<td>
									<c:choose>
									    <c:when test="${activo == 'true' }">
									        <span class="activo"></span>
									    </c:when>    
									    <c:otherwise>
									        <span class="inactivo"></span>
									    </c:otherwise>
									</c:choose>				
								</td>
								<td><s:label value="%{dir3Organismo}" /></td>
								<td class="buttons"><span class="edit">
										<div
											id="ajaxloader_ajax_${planificacionServicio.planificacionId}">
											<span id="ajax_${planificacionServicio.planificacionId}"
												name="ajax" title="Editar"
												onclick="return loadPlan(this,${planificacionServicio.planificacionId},${idServicio},'idServicio','updatePlanificacionServ')"
												class="btnEdit planifications_link"></span>
										</div>
								</span> <span class="delete"> <a class="btnDelete"
										onclick="return confirmDelete();" title="Eliminar"
										href="deletePlanificacionServicio.action?idPlanificacion=${planificacionId}&idServicio=${servicio.servicioId}"></a>
								</span></td>
							</tr>
						</s:iterator>
						<s:if test="%{listaPlanificacionesServicio == null}">
							<tr>
								<td colspan="7">No se ha configurado ninguna planificación
									para el servicio</td>
							</tr>
							<script>document.getElementById('checkAllP').style.visibility="hidden";</script>
						</s:if>
						<s:else>
							<tr>
							<tfoot>
								<td colspan="7"><span class="leftSide"> <s:submit
											id="eliminaSeleccionadosP" name="eliminaSeleccionados"
											theme="simple" disabled="true"
											value="%{getText('button.plataforma.eliminarseleccionados')}"
											cssClass="button" />
								</span>
								<td>
							</tfoot>
							</tr>

						</s:else>
					</tbody>
				</table>
				<s:hidden name="idServicio" id="idServicio" value="%{idServicio}" />

			</s:form>
			<script>
				checkCanalHeader(document.getElementById("servicio.canalid"));
				
			</script>
		</div>


	</div>
	<div class="editContainer">
		<div class="nameDescription">
			<label>Auditoría</label>
		</div>
		<div class="editContent">
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 120px;"
					class="fieldText">Creador:</label> <strong><s:label
							theme="simple" id="servicio.creadopor" name="servicio.creadopor"
							value="%{servicio.creadopor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Creación:</label> <strong><s:label theme="simple"
							id="servicio.fechacreacion" name="servicio.fechacreacion"
							value="%{servicio.fechacreacion}" /></strong>
				</span>
			</p>
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 120px;"
					class="fieldText">Último Modificador:</label> <strong><s:label
							theme="simple" id="servicio.modificadopor"
							name="servicio.modificadopor" value="%{servicio.modificadopor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Última Modificación:</label> <strong><s:label theme="simple"
							id="servicio.fechamodificacion" name="servicio.fechamodificacion"
							value="%{servicio.fechamodificacion}" /></strong>
				</span>
			</p>
		</div>
	</div>

</div>

<script>
	const serviciosAEATGiss = "${serviciosAEATGiss}"; 
	var res = serviciosAEATGiss.split(",");

	res.forEach(function(idServActual) {
		  if (idServActual == idServicio.idServicio.value){
			  document.getElementById("servicio.isPremium").disabled = true;
			  document.getElementById("servicio.isExclusivo").disabled = true;			  
			  }
		});

	function cargarNuevaAplicacion() {              
        document.frmEditServicio.action="aplicacionSelectEditEvent.action";
	    document.frmEditServicio.submit();

	}
	function insertarNuevoOrganismo() {        
		//document.formaddServicioOrganismo.action="aplicacionSelectEditEvent.action";      
        document.formaddServicioOrganismo.submit();

	}

	function selectOnlyThis(id) {
		
		var idCheck = document.getElementById(id).checked;
		var idCheck2 = null;
		if(id=="servicio.isPremium"){
			idCheck2 = document.getElementById("servicio.isExclusivo");
			}
		else{
			idCheck2 = document.getElementById("servicio.isPremium");
			}
		
		if(idCheck) {
				idCheck2.checked = false;
			}
	}
	
	function activarMultiorganismo() {              
        document.formaddServicioOrganismo.action="activarMultiorganismoSelectEditEvent.action";
	    document.formaddServicioOrganismo.submit();
	}
	
	

</script>

