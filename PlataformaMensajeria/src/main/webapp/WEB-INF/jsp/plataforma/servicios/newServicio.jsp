<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
	<s:form id="frmNuevoServicio" method="POST" action="saveServicio"
		theme="simple" cssClass="">
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:submit theme="simple"
					value="%{getText('buttons.text.save')}" cssClass="button" /> <input
				type="button" onclick="javascript:location.href='${volver}'"
				class="button" value="Volver">
			</span> <label>CREACI�N SERVICIO</label>
		</h3>
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp"%>
		<%@include
			file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp"%>

		<div class="editContainer">
			<div class="nameDescription">
				<label>Datos Generales</label>
			</div>

			<div class="editContent">
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Aplicacion
						(*):</label>
					<s:select id="servicio.aplicacionid" name="servicio.aplicacionid"
                        emptyOption="true" theme="simple" labelposition="left"
                        list="comboAplicaciones" listKey="codigo" listValue="descripcion" cssStyle="width:150px"
                        cssClass="" value="%{servicio.aplicacionid}" disabled="false" onchange="cargarNuevaAplicacion()"
                        disable="%{readonly}" />


				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Canal (*):</label>
					<s:select onchange="checkCanalHeader(this)" id="servicio.canalid"
						name="servicio.canalid" emptyOption="true" theme="simple"
						labelposition="left" list="comboCanales" listKey="codigo" cssStyle="width:150px"
						listValue="descripcion" cssClass="" value="%{servicio.canalid}"
						disabled="false" />
				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Nombre (*):</label>
					<s:textfield name="servicio.nombre" value="%{servicio.nombre}"
						id="servicio.nombre" theme="simple" labelposition="left" size="70"
						maxlength="255" cssClass="" />
				</p>
				<p class="criteria">
					<label theme="simple" style="width: 120px;" class="fieldText">Descripci�n:</label>
					<s:textarea theme="simple" rows="6" cols="70"
						name="servicio.descripcion" labelposition="left"
						id="servicio.descripcion" value="%{servicio.descripcion}" />

				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">N� Max.
						Env�os (*):</label>
					<s:textfield name="servicio.nmaxenvios"
						value="%{servicio.nmaxenvios}" id="servicio.nmaxenvios"
						theme="simple" onKeyPress="return numbersonly(this, event)"
						labelposition="left" size="6" maxlength="6" cssClass="" />
				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Historificaci�n:</label>
					<span> <c:set var="rh1" value="" /> <c:set var="rh2"
							value="" /> <c:set var="rh3" value="" /> <c:set var="rh4"
							value="" /> <c:if test="${newHistorificacion==1}">
							<c:set var="rh1" value="checked" />
						</c:if> <c:if test="${newHistorificacion==2}">
							<c:set var="rh2" value="checked" />
						</c:if> <c:if test="${newHistorificacion==3}">
							<c:set var="rh3" value="checked" />
						</c:if> <c:if test="${newHistorificacion==4}">
							<c:set var="rh4" value="checked" />
						</c:if> <input type="radio" id="newHistorificacion"
						name="newHistorificacion" value="1" ${rh1} />30 d�as <input
						type="radio" id="newHistorificacion" name="newHistorificacion"
						value="2" ${rh2} />60 d�as <input type="radio"
						id="newHistorificacion" name="newHistorificacion" value="3" ${rh3} />90
						d�as <input type="radio" id="newHistorificacion"
						name="newHistorificacion" value="4" ${rh4} />Otro
					</span>
					<s:textfield name="servicio.historificacionInput"
						value="%{servicio.historificacionInput}"
						id="servicio.historificacionInput" theme="simple"
						onKeyPress="return numbersonly(this, event)" labelposition="left"
						size="3" maxlength="3" cssClass="" />
					<label> d�as</label>
				</p>
				<p class="criteria">
					<label style="width: 425px; margin-left: 125px;" class="fieldText"><i>Si
							el periodo de tiempo es superior a 90 d�as debe indicar el motivo</i></label>
					<s:textarea style="margin-left: 125px;" theme="simple" rows="6"
						cols="70" name="servicio.motivohistorificacion"
						labelposition="left" id="servicio.motivohistorificacion"
						value="%{servicio.motivohistorificacion}" />
				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Conservaci�n:</label>
					<span> <c:set var="rc1" value="" /> <c:set var="rc2"
							value="" /> <c:set var="rc3" value="" /> <c:set var="rc4"
							value="" /> <c:if test="${newConservacion==1}">
							<c:set var="rc1" value="checked" />
						</c:if> <c:if test="${newConservacion==2}">
							<c:set var="rc2" value="checked" />
						</c:if> <c:if test="${newConservacion==3}">
							<c:set var="rc3" value="checked" />
						</c:if> <c:if test="${newConservacion==4}">
							<c:set var="rc4" value="checked" />
						</c:if> <input type="radio" id="newConservacion" name="newConservacion"
						value="1" ${rc1} />1 a�o <input type="radio" id="newConservacion"
						name="newConservacion" value="2" ${rc2} />2 a�os <input
						type="radio" id="newConservacion" name="newConservacion" value="3"
						${rc3} />3 a�os <input type="radio" id="newConservacion"
						name="newConservacion" value="4" ${rc4} />Otro
					</span>
					<s:textfield name="servicio.conservacionInput"
						value="%{servicio.conservacionInput}"
						id="servicio.conservacionInput" theme="simple"
						onKeyPress="return numbersonly(this, event)" labelposition="left"
						size="2" maxlength="2" cssClass="" />
					<label> a�os</label>
				</p>
				<p class="criteria">
					<label style="width: 425px; margin-left: 130px;" class="fieldText"><i>Si
							el periodo de tiempo es superior a 3 a�os debe indicar el motivo</i></label>
					<s:textarea style="margin-left: 130px;" theme="simple" rows="6"
						cols="70" name="servicio.motivoconservacion" labelposition="left"
						id="servicio.motivoconservacion"
						value="%{servicio.motivoconservacion}" />
				</p>
				<p class="criteria">
					<label id="frommailLabel" style="width: 120px; visibility:hidden; display:none"
						class="fieldText">Cuenta Env&iacute;o (*):</label>
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
						Lote Env�o (*):</label>
					<s:textfield name="servicio.nombreloteenvio"
						value="%{servicio.nombreloteenvio}" id="servicio.nombreloteenvio"
						theme="simple" cssStyle="visibility:hidden;display:none;" labelposition="left"
						size="70" maxlength="255" cssClass="" />
				</p>

				<p class="criteria" id="plataformaid" style="visibility:hidden; display:none;">
					<label id="plataformaLabel" theme="simple" style="width: 120px"
						class="fieldText">Plataforma (*):</label>
					<s:checkbox theme="simple" name="newPlataformaAndroid"
						id="newPlataformaAndroid" value="%{newPlataformaAndroid}" />
					Android
					<s:checkbox theme="simple" name="newPlataformaiOS"
						id="newPlataformaiOS" value="%{newPlataformaiOS}" />
					iOS
				</p>

				<p class="criteria">
					<label id="badgeLabel" style="width: 180px; visibility:hidden; display:none;"
						class="fieldText">Agrupar notificaciones cada (*):</label>
					<s:textfield name="servicio.badge" value="%{servicio.badge}"
						id="servicio.badge" theme="simple" cssStyle="visibility:hidden;display:none;"
						onKeyPress="return numbersonly(this, event)" labelposition="left"
						size="30" maxlength="255" cssClass="" />
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
					<label style="width: 120px;" class="fieldText">N� Max.
						Reintentos:</label>
					<s:textfield name="servicio.numeroMaxReenvios"
						value="%{servicio.numeroMaxReenvios}" id="servicio.numeroMaxReenvios"
						theme="simple"
						onKeyPress="return numbersonly(this, event)"
						labelposition="left" size="6" maxlength="6" cssClass="" />
				</p>
				<p class="criteria">
					<label theme="simple" style="width: 120px;" class="fieldText">Activo:</label>
					<s:checkbox theme="simple" name="newActivo" id="newActivo"
						value="%{newActivo}" />
				</p>
				
				<label id="exclusivoLabel" style="display:none">
					<p class="criteria">
						<label theme="simple" style="width: 120px;" class="fieldText">Premium:</label>
						<s:checkbox theme="simple" name="newPremium" id="newPremium"
						value="%{newPremium}" />
					</p>									
					<p class="criteria">
						<label theme="simple" style="width: 120px;" class="fieldText">Exclusivo:</label>
						<s:checkbox theme="simple" name="newExclusivo" id="newExclusivo"
						value="%{newExclusivo}" />						
					</p>
				</label>	
								<!-- Inicio parte WEB push -->
				<p class="criteria">
					 <span>
					<label id="caducidadWebPush" style="width: 120px; visibility: hidden; display: none;" class="fieldText">Caducidad Notificaci�n</label>
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
						Clave P�blica (*):</label>
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
				<s:hidden theme="simple" id="vapidPublicKey" name="vapidPublicKey" value="%{vapidPublicKey}" />
				<s:hidden theme="simple" id="vapidPrivateKey" name="vapidPrivateKey" value="%{vapidPublicKey}" />
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
						t�cnico: (*)</label>
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
					<s:checkbox theme="simple" name="newInformeActivo"
						id="newInformeActivo" value="%{newInformeActivo}" />
				</p>
				<p class="criteria">
					<label theme="simple" style="width: 120px;" class="fieldText">Destinatarios:</label>
					<s:textarea name="newInformesDestinatarios"
						id="newInformesDestinatarios"
						placeholder="Introduce direcciones de email separadas por ';'"
						theme="simple" cssClass="W240" value="%{newInformesDestinatarios}" />
				</p>
				<p class="criteria">
					<label theme="simple" style="width: 120px;" class="fieldText">Agrupaci�n</label>
				<p class="criteria">
					<label theme="simple"
						style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total
						de mensajes agrupador por estado:</label>
					<s:checkbox theme="simple" name="newAgrupacionEstado"
						id="servicio.agrupacionestado" value="%{newAgrupacionEstado}" />
				</p>
				<p class="criteria" id="agrupacioncodorg" >
					<label theme="simple"
						style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total
						de mensajes agrupador por organismo:</label>
					<s:checkbox theme="simple" name="newAgrupacionCodOrg"
						id="servicio.agrupacioncodorg" value="%{newAgrupacionCodOrg}" />
				</p>
				<p class="criteria" id="agrupacioncodsia" >
					<label theme="simple"
						style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total
						de mensajes agrupador por procedimiento:</label>
					<s:checkbox theme="simple" name="newAgrupacionCodSia"
						id="servicio.agrupacioncodsia" value="%{newAgrupacionCodSia}" />
				</p>
				<p class="criteria" id="agrupacioncodorgpagador"
					style="visibility: hidden">
					<label theme="simple"
						style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total
						de mensajes agrupador por organismo pagador:</label>
					<s:checkbox theme="simple" name="agrupacioncodorgpagador"
						id="servicio.agrupacioncodorgpagador"
						value="%{newAgrupacionCodOrgPagador}" />
				</p>
				</p>
			</div>
		</div>
	</s:form>
	<div class="editContainer">
		<div class="nameDescription">
			<label>Auditor�a</label>
		</div>
		<div class="editContent">
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 120px;"
					class="fieldText">Creador:</label> <strong><s:label
							theme="simple" id="servicio.creadopor" name="servicio.creadopor"
							value="%{servicio.creadopor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Creaci�n:</label> <strong><s:label theme="simple"
							id="servicio.fechacreacion" name="servicio.fechacreacion"
							value="%{servicio.fechacreacion}" /></strong>
				</span>
			</p>
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 120px;"
					class="fieldText">�ltimo Modificador:</label> <strong><s:label
							theme="simple" id="servicio.modificadopor"
							name="servicio.modificadopor" value="%{servicio.modificadopor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						�ltima Modificaci�n:</label> <strong><s:label theme="simple"
							id="servicio.fechamodificacion" name="servicio.fechamodificacion"
							value="%{servicio.fechamodificacion}" /></strong>
				</span>
			</p>
		</div>
	</div>
</div>
<script>
	checkCanalHeader(document.getElementById("servicio.canalid"));

	function cargarNuevaAplicacion() {              
        document.frmNuevoServicio.action="aplicacionSelectEvent.action";
    	document.frmNuevoServicio.submit();
	}


	
</script>