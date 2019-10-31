<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR,ROL_CAID">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
	<s:form id="frmNuevoServicio" method="POST" action="saveServicio"
		theme="simple" cssClass="">
		<s:hidden id = "datosPlanificaciones" name="datosPlanificaciones" />
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:submit theme="simple"
					value="%{getText('buttons.text.save')}" cssClass="button" /> <input
				type="button" onclick="javascript:location.href='${volver}'"
				class="button" value="Volver">
			</span> <label>CREACIÓN SERVICIO</label>
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
					<s:select onchange="checkCanalHeader(this);cambioCanal(this);" id="servicio.canalid"
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
					<label theme="simple" style="width: 120px;" class="fieldText">Descripción:</label>
					<s:textarea theme="simple" rows="6" cols="70"
						name="servicio.descripcion" labelposition="left"
						id="servicio.descripcion" value="%{servicio.descripcion}" />

				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Nº Max.
						Envíos (*):</label>
					<s:textfield name="servicio.nmaxenvios"
						value="%{servicio.nmaxenvios}" id="servicio.nmaxenvios"
						theme="simple" onKeyPress="return numbersonly(this, event)"
						labelposition="left" size="6" maxlength="6" cssClass="" />
				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Historificación:</label>
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
						name="newHistorificacion" value="1" ${rh1} />30 días <input
						type="radio" id="newHistorificacion" name="newHistorificacion"
						value="2" ${rh2} />60 días <input type="radio"
						id="newHistorificacion" name="newHistorificacion" value="3" ${rh3} />90
						días <input type="radio" id="newHistorificacion"
						name="newHistorificacion" value="4" ${rh4} />Otro
					</span>
					<s:textfield name="servicio.historificacionInput"
						value="%{servicio.historificacionInput}"
						id="servicio.historificacionInput" theme="simple"
						onKeyPress="return numbersonly(this, event)" labelposition="left"
						size="3" maxlength="3" cssClass="" />
					<label> días</label>
				</p>
				<p class="criteria">
					<label style="width: 425px; margin-left: 125px;" class="fieldText"><i>Si
							el periodo de tiempo es superior a 90 días debe indicar el motivo</i></label>
					<s:textarea style="margin-left: 125px;" theme="simple" rows="6"
						cols="70" name="servicio.motivohistorificacion"
						labelposition="left" id="servicio.motivohistorificacion"
						value="%{servicio.motivohistorificacion}" />
				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Conservación:</label>
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
						value="1" ${rc1} />1 año <input type="radio" id="newConservacion"
						name="newConservacion" value="2" ${rc2} />2 años <input
						type="radio" id="newConservacion" name="newConservacion" value="3"
						${rc3} />3 años <input type="radio" id="newConservacion"
						name="newConservacion" value="4" ${rc4} />Otro
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
						Lote Envío (*):</label>
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
					<label style="width: 120px;" class="fieldText">Nº Max.
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
				<label id="extranjeroLabel"  style="display:none">
					<p class="criteria">
							<label class="fieldText" style="width: 120px;">SMS Extranjeros</label>
							<s:checkbox theme="simple" id="newExtranjero"
								name="newExtranjero" value="%{newExtranjero}"/>
					</p>
				</label>		
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
					<label theme="simple" style="width: 120px;" class="fieldText">Agrupación</label>
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
			<label>Planificaciones(*)</label>
			<p>Determina los horarios en los que el servicio va a estar
				disponible para realizar los env&iacute;os</p>
		</div>
		<div class="editContent">		
				<p class="criteria">
					<span style="width: 350px;"> <label style="width: 120px;"
						class="fieldText">Servidor / Proveedor:</label> <s:select style="min-width: 150px;"
							id="planificacionServidorSelect"
							name="planificacionServidorSelect" emptyOption="true"
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
						onclick="return comprobarPlanificacion();"
						name="addItem">Añadir Item</a>
				</p>			
							
				<table cellspacing="0" cellpadding="0" border="0">
					<thead>
						<tr>
							<th class=""><input type="checkbox" id="checkAllP"
								theme="simple" onclick="selectAllP(this)" /></th>
							<th class="TH110">Días</th>
							<th class="TH70 separator">Hora Inicio</th>
							<th class="TH70 separator">Hora Fin</th>
							<th class="TH150">Servidor / Proveedor</th>							
							<th class="TH45 separator"></th>
						</tr>
					</thead>
					<tbody id="bodyTablaPlanificaciones">
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
								<td colspan="8">No se ha configurado ninguna planificación
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
	var datosPlanificacion = new Array();

	checkCanalHeader(document.getElementById("servicio.canalid"));
	
	function escribirTablaPlanificaciones(){
		if(document.getElementById("bodyTablaPlanificaciones").getElementsByTagName("td")[0].innerText == "No se ha configurado ninguna planificación para el servicio"){
			document.getElementById("bodyTablaPlanificaciones").innerHTML = "";	
			}	
		document.getElementById("bodyTablaPlanificaciones").innerHTML = "";
		  if(datosPlanificacion.length == 0){
			  var trTabla = '<tr><td colspan="6">No se ha configurado ninguna planificación para el servicio</td></tr>';
			  document.getElementById("bodyTablaPlanificaciones").innerHTML = trTabla;			
			  document.getElementById('datosPlanificaciones').value = datosPlanificacion;
			  return;
			  }
		  document.getElementById('datosPlanificaciones').value = "";
		  for (var i=0; i<datosPlanificacion.length; i++) {		
			  var isChecked;
			  var trTabla = '<tr class>'
        		+'<td class="darkTD TH15"><input type="checkbox" onclick="checkBotonEliminarSeleccionados4()" id="checkDelListOrganismosServicios" name="checkDelListOrganismosServicios" '+""+' value="'+datosPlanificacion[i][0]+'">'
        		+'<input type="hidden" id="__checkbox_checkDelListOrganismosServicios" name="__checkbox_checkDelListOrganismosServicios">'
        		+'</td><td><label>'+datosPlanificacion[i][1]+'</label></td><td><label>'+datosPlanificacion[i][2]+'</label></td><td><label>'+datosPlanificacion[i][3]+'</label></td><td><label>'+datosPlanificacion[i][4]+'</label></td><td class="buttons"><span class="edit">'	        	 
        		+'<a class="btnDelete" title="Eliminar" onclick="return confirmDelete();" href="javascript:eliminarPlanificacion('+i+')"></a></span></td></tr>';

			  document.getElementById("bodyTablaPlanificaciones").innerHTML = document.getElementById("bodyTablaPlanificaciones").innerHTML +trTabla;			  
			  			  
		  }
		   var botonEliminarServicios = '<tfoot><tr><td colspan="4"><input type="button" value="Eliminar seleccionados" id="eliminaSeleccionadosOrganismosServicios" name="eliminaSeleccionadosOrganismosServicios" disabled="disabled" class="button" onclick="eliminarServiciosCheckeds()"></td><td></td></tr></tfoot>';
		  document.getElementById('datosPlanificaciones').value = datosPlanificacion.map(a =>a.join(";")).join(';');
	}
	
	function eliminarPlanificacion(idPlanificacion){
		datosPlanificacion.splice(idPlanificacion,1);
		escribirTablaPlanificaciones();
	}
	function comprobarPlanificacion(){
		var valid=true;		
		var i=0;
		var msg = "Atención\n";
		var prefix = "planificacionServidor";

		var idServidor = document.getElementById("planificacionServidorSelect").value; //Id del servidor
			var lunes,martes,miercoles,jueves,viernes,sabado,domingo;
			lunes = document.getElementById(prefix+".lunes");
			martes = document.getElementById(prefix+".martes");
			miercoles = document.getElementById(prefix+".miercoles");
			jueves = document.getElementById(prefix+".jueves");
			viernes = document.getElementById(prefix+".viernes");
			sabado = document.getElementById(prefix+".sabado");
			domingo= document.getElementById(prefix+".domingo");
			var horaDesde,horaHasta;
			horaDesde = document.getElementById(prefix+".horaDesde");
			horaHasta = document.getElementById(prefix+".horaHasta");
			var servidorId = document.getElementById(prefix+".servidorId");
			if(lunes.checked||martes.checked||miercoles.checked||jueves.checked||viernes.checked||sabado.checked||domingo.checked){
				
			}else{
				valid=false;
				msg+="Debe seleccionar al menos un día de la semana\n";
			}
			if(horaDesde.value==horaHasta.value){
				valid=false;
				msg+="Debe seleccionar Horas Inicio y Fin diferentes\n";
			}else{
				var horaInicio = horaDesde.value.split(":")[0];
				var minInicio = horaDesde.value.split(":")[1];
				
				var horaFin = horaHasta.value.split(":")[0];
				var minFin = horaHasta.value.split(":")[1];
				var desde = new Date();
				var hasta = new Date();
				desde.setHours(horaInicio);
				desde.setMinutes(minInicio);
				hasta.setHours(horaFin);				
				hasta.setMinutes(minFin);
				if(desde>hasta){
					valid=false;
					msg+="Hora Inicio no puede ser mayor que Hora Fin";
				}
				 for (var i=0; i<datosPlanificacion.length; i++) {
					var horaInicioPlanificacion = datosPlanificacion[i][2].split(":")[0];
					var minInicioPlanificacion = datosPlanificacion[i][2].split(":")[1];
						
					var horaFinPlanificacion = datosPlanificacion[i][3].split(":")[0];
					var minFinPlanificacion = datosPlanificacion[i][3].split(":")[1];
					var desdePlanificacion = new Date();
					var hastaPlanificacion = new Date();
					desdePlanificacion.setHours(horaInicioPlanificacion);
					desdePlanificacion.setMinutes(minInicioPlanificacion);
					hastaPlanificacion.setHours(horaFinPlanificacion);
					hastaPlanificacion.setMinutes(minFinPlanificacion);

						if(lunes.checked &&	datosPlanificacion[i][1].includes("L")){													
										if( (60*desde.getHours()+desde.getMinutes()) >= ( 60*desdePlanificacion.getHours()+desdePlanificacion.getMinutes()) 
													&& ( 60*desde.getHours()+desde.getMinutes()) < (hastaPlanificacion.getMinutes() + hastaPlanificacion.getHours()*60 )){
												valid = false;
												msg+="Se solapan las horas del lunes\n";
											}
									}			
							 
						if(martes.checked && datosPlanificacion[i][1].includes("M")){													
							if( (60*desde.getHours()+desde.getMinutes()) >= ( 60*desdePlanificacion.getHours()+desdePlanificacion.getMinutes()) 
									&& ( 60*desde.getHours()+desde.getMinutes()) < (hastaPlanificacion.getMinutes() + hastaPlanificacion.getHours()*60 )){
											valid = false;
											msg+="Se solapan las horas del martes\n";
										}
								}						 
						if(miercoles.checked && datosPlanificacion[i][1].includes("X")){													
							if( (60*desde.getHours()+desde.getMinutes()) >= ( 60*desdePlanificacion.getHours()+desdePlanificacion.getMinutes()) 
									&& ( 60*desde.getHours()+desde.getMinutes()) < (hastaPlanificacion.getMinutes() + hastaPlanificacion.getHours()*60 )){
											valid = false;
											msg+="Se solapan las horas del miercoles\n";
										}
								}
						if(jueves.checked && datosPlanificacion[i][1].includes("J")){													
							if( (60*desde.getHours()+desde.getMinutes()) >= ( 60*desdePlanificacion.getHours()+desdePlanificacion.getMinutes()) 
									&& ( 60*desde.getHours()+desde.getMinutes()) < (hastaPlanificacion.getMinutes() + hastaPlanificacion.getHours()*60 )){
									valid = false;
									msg+="Se solapan las horas del jueves\n";
								}
						}
						if(viernes.checked && datosPlanificacion[i][1].includes("V")){													
							if( (60*desde.getHours()+desde.getMinutes()) >= ( 60*desdePlanificacion.getHours()+desdePlanificacion.getMinutes()) 
									&& ( 60*desde.getHours()+desde.getMinutes()) < (hastaPlanificacion.getMinutes() + hastaPlanificacion.getHours()*60 )){
									valid = false;
									msg+="Se solapan las horas del viernes\n";
								}
						}
						if(sabado.checked && datosPlanificacion[i][1].includes("S")){													
							if( (60*desde.getHours()+desde.getMinutes()) >= ( 60*desdePlanificacion.getHours()+desdePlanificacion.getMinutes()) 
									&& ( 60*desde.getHours()+desde.getMinutes()) < (hastaPlanificacion.getMinutes() + hastaPlanificacion.getHours()*60 )){
									valid = false;
									msg+="Se solapan las horas del sabado\n";
								}
						}
						if(domingo.checked && datosPlanificacion[i][1].includes("D")){													
							if( (60*desde.getHours()+desde.getMinutes()) >= ( 60*desdePlanificacion.getHours()+desdePlanificacion.getMinutes()) 
									&& ( 60*desde.getHours()+desde.getMinutes()) < (hastaPlanificacion.getMinutes() + hastaPlanificacion.getHours()*60 )){
									valid = false;
									msg+="Se solapan las horas del domingo\n";
								}
						}
						 
						 
		  		   		   			
					}
			}
		
		if(!valid){
			alert(msg);
			return false;
		}else{
			agregarPlanificacion();
			return true;
		}
	}
	
	function agregarPlanificacion(){
		
		var idServidor = document.getElementById("planificacionServidorSelect").value; //Id del servidor
		var nombreServidor = document.getElementById("planificacionServidorSelect").selectedOptions[0].text;
		var horaInicio = document.getElementById("planificacionServidor.horaDesde").value;
		var horaFin = document.getElementById("planificacionServidor.horaHasta").value;
		var dias;
		if(document.getElementById("planificacionServidor.lunes").checked ){
			dias = "L";
			}
		if(document.getElementById("planificacionServidor.martes").checked){
			if (dias == null )dias = "M";
			else dias += ", M";
			}
		if(document.getElementById("planificacionServidor.miercoles").checked){
			if (dias == null )dias = "X";
			else dias += ", X";
			}
		if(document.getElementById("planificacionServidor.jueves").checked){
			if (dias == null )dias = "J";
			else dias += ", J";
			}
		if(document.getElementById("planificacionServidor.viernes").checked){
			if (dias == null )dias = "V";
			else dias += ", V";
			}
		if(document.getElementById("planificacionServidor.sabado").checked){
			if (dias == null )dias = "S";
			else dias += ", S";
			}
		if(document.getElementById("planificacionServidor.domingo").checked){
			if (dias == null )dias = "D";
			else dias += ", D";
			}

		datos = [idServidor,dias,horaInicio,horaFin,nombreServidor];		
		datosPlanificacion.push(datos);

		escribirTablaPlanificaciones();
		}
	
	
	
	function cambioCanal(){

		$('#planificacionServidorSelect option').each(function() {
	        $(this).remove();
			});		
 	    
		$.ajax({
	        type: "POST",
	        url: "ajaxLoadServidoresPorCanal.action",
	        data: {idCanal:document.getElementById("servicio.canalid").value}, // serializes the form's elements.
	        success: function(data)
	        {
	     	  datos = data.items;
	     	       	  
	     	  $('#planificacionServidorSelect').append($('<option>', { 
	     	        value: '',
	     	        text : '' 
	     	    }));	     	 
		     	 
	     	 for (var i=0; i<datos.length; i++) {
		  		$('#planificacionServidorSelect').append($('<option>', { 
			        value: datos[i].value,
			        text : datos[i].text 
			    }));	     		 
	  			}
	     	 
	        },
	        error: function(data)
	        {
	     	   alert("error..."); 
	        }
	      });

		}
	function cargarNuevaAplicacion() {              
        document.frmNuevoServicio.action="aplicacionSelectEvent.action";
    	document.frmNuevoServicio.submit();
	}

	//Si se da un error en la alta, necesitamos poner la informacion de la planificacion en sus variables correspondientes		
	$(document).ready ( function(){
			if(document.getElementById("servicio.canalid").value != ""){
					cambioCanal();
				}
			if(document.getElementById("datosPlanificaciones").value != ""){
				var datos = document.getElementById("datosPlanificaciones").value.split(';');
				
				for (var i=0; i<datos.length; i+=5) {
					datoActual = [datos[i],datos[i+1],datos[i+2],datos[i+3],datos[i+4]];
					datosPlanificacion.push(datoActual);					
				}								
				escribirTablaPlanificaciones();
			}
		});
	
</script>