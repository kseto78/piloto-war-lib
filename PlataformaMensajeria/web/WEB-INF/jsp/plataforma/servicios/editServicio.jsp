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
					test='%{servicio.pendienteAprobacion == "1"}'>
					<s:submit theme="simple" value="%{getText('buttons.text.aprobar')}"
						cssClass="button" />
				</s:if> <s:else>
					<s:submit theme="simple" value="%{getText('buttons.text.save')}"
						cssClass="button" />
				</s:else> <input type="button" onclick="javascript:location.href='${volver}'"
				class="button" value="Volver">
			</span> <label>EDICI�N DE SERVICIO</label>
		</h3>
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp"%>
		<%@include
			file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp"%>
		<sj:dialog id="dialogPlanifications" title="Planificaci�n"
			cssStyle="min-height:150px;display:none" autoOpen="false">
			<div class="editContainer">
				<div class="nameDescription">
					<label>Editar D�as y horas</label>
				</div>
				<div class="editContent">
					<p class="criteria">
						<span> <label class="fieldText" style="width: 120px;">D�as
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
				<span class="leftSide"></span> <span class="rightSide"> <input
					type="button" value="Guardar" class="button" />
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
					<s:select id="servicio.aplicacionId" name="servicio.aplicacionId"
						emptyOption="true" theme="simple" labelposition="left"
						list="comboAplicaciones" listKey="codigo" listValue="descripcion"
						cssClass="" value="%{servicio.aplicacionId}" disabled="false"
						onchange="cargarNuevaAplicacion()" cssStyle="width:150px"
						disable="%{readonly}" />
					<input type="button" value="Ir a Aplicaci�n" class="button"
						onclick='javascript:location.href="editAplicacion.action?idAplicacion=${servicio.aplicacionId}&from=editServicio.action&idFrom=${idServicio}&var=idServicio"' />
				</p>

				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Canal (*):</label>
					<%
						String canalDisabled = (String) request.getAttribute("canalDisabled");
					%>
					<%
						if(canalDisabled!=null){
					%>
					<s:select onchange="checkCanalHeader(this)" id="servicio.canalId"
						name="servicio.canalId" emptyOption="true" theme="simple"
						labelposition="left" list="comboCanales" listKey="codigo"
						listValue="descripcion" cssStyle="width:150px" cssClass=""
						value="%{servicio.canalId}" disabled="true" />
					<input type="hidden" name="servicio.canalId" id="servicio.canalId"
						value="${servicio.canalId}">
					<%
						}else{
					%>
					<s:select onchange="checkCanalHeader(this)" id="servicio.canalId"
						name="servicio.canalId" emptyOption="true" theme="simple"
						labelposition="left" list="comboCanales" listKey="codigo"
						listValue="descripcion" cssStyle="width:150px" cssClass=""
						value="%{servicio.canalId}" disabled="false" />

					<%
						}
					%>
				</p>

				<p class="criteria">
					<s:hidden theme="simple" id="servicio.servicioId"
						name="servicio.servicioId" value="%{servicio.servicioId}" />
					<s:hidden theme="simple" id="idServicio" name="idServicio"
						value="%{servicio.servicioId}" />


					<label style="width: 120px;" class="fieldText">Nombre (*):</label>
					<s:textfield name="servicio.nombre" value="%{servicio.nombre}"
						id="servicio.nombre" theme="simple" labelposition="left" size="70"
						maxlength="255" cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Descripci�n:</label>
					<s:textarea name="servicio.descripcion" id="servicio.descripcion"
						theme="simple" rows="6" cols="70" value="%{servicio.descripcion}">
					</s:textarea>
				</p>

				<s:if test='%{servicio.pendienteAprobacion == "1"}'>
					<p class="criteria">
						<label class="fieldText" style="width: 120px;">Estado</label> <strong>Pendiente
							de aprobaci�n</strong> <input type="hidden" name="servicio.isActivo"
							id="servicio.isActivo" value="${servicio.activado}">
					</p>
				</s:if>
				<s:else>
					<p class="criteria">
						<label class="fieldText" style="width: 120px;">Activo</label>
						<s:checkbox theme="simple" id="servicio.isActivo"
							name="servicio.isActivo" value="%{servicio.activado}" />
					</p>
				</s:else>
				<p class="criteria">
						<label class="fieldText" style="width: 120px;">Premium</label>
						<s:checkbox theme="simple" id="servicio.isPremium"
							name="servicio.isPremium" value="%{servicio.premium}" />
				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">N� Max.
						env�os (*):</label>
					<s:textfield name="servicio.nmaxenvios"
						value="%{servicio.nmaxenvios}" id="servicio.nmaxenvios"
						theme="simple" labelposition="left"
						onKeyPress="return numbersonly(this, event)" size="20"
						maxlength="6" cssClass="" />
				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Historificaci�n:</label>
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
						name="servicio.historificacion" value="1" ${rh1} />30 d�as 
						<input type="radio" id="servicio.historificacion"
						name="servicio.historificacion" value="2" ${rh2} />60 d�as 
						<input type="radio" id="servicio.historificacion"
						name="servicio.historificacion" value="3" ${rh3} />90 d�as 
						<input type="radio" id="servicio.historificacion"
						name="servicio.historificacion" value="4" ${rh4} />Otro
					</span>
					<s:textfield name="servicio.historificacionInput"
						value="%{servicio.historificacionInput}"
						id="servicio.historificacionInput" theme="simple"
						onKeyPress="return numbersonly(this, event)" labelposition="left"
						size="3" maxlength="3" cssClass="" />
					<label> d�as</label>
				</p>
				<p class="criteria">
					<label style="width: 425px; margin-left: 130px;" class="fieldText"><i>Si
							el periodo de tiempo es superior a 90 d�as debe indicar el motivo</i></label>
					<s:textarea style="margin-left: 130px;" theme="simple" rows="6"
						cols="70" name="servicio.motivoHistorificacion"
						labelposition="left" id="servicio.motivoHistorificacion"
						value="%{servicio.motivoHistorificacion}" />
				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Conservaci�n:</label>
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
						name="servicio.conservacion" value="1" ${rc1} />1 a�o <input
						type="radio" id="servicio.conservacion"
						name="servicio.conservacion" value="2" ${rc2} />2 a�os <input
						type="radio" id="servicio.conservacion"
						name="servicio.conservacion" value="3" ${rc3} />3 a�os <input
						type="radio" id="servicio.conservacion"
						name="servicio.conservacion" value="4" ${rc4} />Otro
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
						cols="70" name="servicio.motivoConservacion" labelposition="left"
						id="servicio.motivoConservacion"
						value="%{servicio.motivoConservacion}" />
				</p>
				<!--  para la parte de MAIL -->
				<p class="criteria">
					<label id="fromMailLabel" style="width: 120px; visibility: hidden;"
						class="fieldText">Cuenta Env&iacute;o:</label>
					<s:textfield name="servicio.fromMail" value="%{servicio.fromMail}"
						id="servicio.fromMail" onblur="verifyEmail(this)" theme="simple"
						cssStyle="visibility:hidden" labelposition="left" size="70"
						maxlength="255" cssClass="" />
				</p>


				<p class="criteria">
					<label id="fromMailNameLabel"
						style="width: 120px; visibility: hidden;" class="fieldText">Nombre
						C. Env&iacute;o:</label>
					<s:textfield name="servicio.fromMailName"
						value="%{servicio.fromMailName}" id="servicio.fromMailName"
						theme="simple" cssStyle="visibility:hidden" labelposition="left"
						size="70" maxlength="255" cssClass="" />
				</p>
				<!--  FIN parte de MAIL -->

				<p class="criteria">
					<label id="endPointLabel" style="width: 120px;" class="fieldText">EndPoint
						(*):</label>
					<s:textfield name="servicio.endPoint" value="%{servicio.endPoint}"
						id="servicio.endPoint" theme="simple" labelposition="left"
						size="70" maxlength="255" cssClass="" />
				</p>

				<p class="criteria">
					<label id="nombreLoteEnvioLabel"
						style="width: 121px; visibility: hidden;" class="fieldText">Nombre
						Lote Env�o (*):</label>
					<s:textfield name="servicio.nombreLoteEnvio"
						value="%{servicio.nombreLoteEnvio}" id="servicio.nombreLoteEnvio"
						theme="simple" cssStyle="visibility:hidden" labelposition="left"
						size="70" maxlength="255" cssClass="" />
				</p>
				<!--  FIN parte de RECEPCI�N SMS -->
				<!--  para la parte de PUSH -->
				<p class="criteria" id="plataformaId" style="visibility: hidden;">
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
					<label id="badgeLabel" style="width: 180px; visibility: hidden;"
						class="fieldText">Agrupar notificaciones cada (*):</label>
					<s:textfield name="servicio.badge" value="%{servicio.badge}"
						id="servicio.badge" theme="simple" cssStyle="visibility:hidden"
						onKeyPress="return numbersonly(this, event)" labelposition="left"
						size="30" maxlength="255" cssClass="" />
				</p>

				<p class="criteria">
					<label id="gcmProjectKeyLabel"
						style="width: 180px; visibility: hidden;" class="fieldText">GCM
						API Key (*):</label>
					<s:textfield name="servicio.gcmProjectKey"
						value="%{servicio.gcmProjectKey}" id="servicio.gcmProjectKey"
						theme="simple" cssStyle="visibility:hidden" labelposition="left"
						size="30" maxlength="255" cssClass="" />
				</p>

				<p class="criteria">
					<label id="apnsRutaCertificadoLabel"
						style="width: 180px; visibility: hidden;" class="fieldText">APNS
						Ruta Certificado (*):</label>
					<s:textfield name="servicio.apnsRutaCertificado"
						value="%{servicio.apnsRutaCertificado}"
						id="servicio.apnsRutaCertificado" theme="simple"
						cssStyle="visibility:hidden" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>

				<p class="criteria">
					<label id="apnsPasswordCertificadoLabel"
						style="width: 179px; visibility: hidden;" class="fieldText">APNS
						Password Certificado (*):</label>
					<s:password name="servicio.apnsPasswordCertificado"
						value="%{servicio.apnsPasswordCertificado}"
						id="servicio.apnsPasswordCertificado" theme="simple"
						cssStyle="visibility:hidden" labelposition="left" size="24"
						maxlength="55" cssClass="" showPassword="true" />
				</p>

				<!--  FIN parte de push -->

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
					<s:checkbox theme="simple" name="servicio.isInformesActivo"
						id="servicio.isInformesActivo"
						value="%{servicio.isInformesActivo}" />
				</p>
				<p class="criteria">
					<label theme="simple" style="width: 120px;" class="fieldText">Destinatarios:</label>
					<s:textarea name="servicio.informesDestinatarios"
						id="servicio.informesDestinatarios"
						placeholder="Introduce direcciones de email separadas por ';'"
						theme="simple" cssClass="W240"
						value="%{servicio.informesDestinatarios}" />
				</p>
				<p class="criteria">
					<label theme="simple" style="width: 120px;" class="fieldText">Agrupaci�n</label>
				<p class="criteria">
					<label theme="simple"
						style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total
						de mensajes agrupador por estado:</label>
					<s:checkbox theme="simple" name="servicio.isAgrupacionEstado"
						id="servicio.agrupacionEstado"
						value="%{servicio.isAgrupacionEstado}" />
				</p>
				<p class="criteria" id="agrupacionCodOrg">
					<label theme="simple"
						style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total
						de mensajes agrupador por organismo:</label>
					<s:checkbox theme="simple" name="servicio.isAgrupacionCodOrg"
						id="servicio.agrupacionCodOrg"
						value="%{servicio.isAgrupacionCodOrg}" />
				</p>
				<p class="criteria" id="agrupacionCodSia">
					<label theme="simple"
						style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total
						de mensajes agrupador por procedimiento:</label>
					<s:checkbox theme="simple" name="servicio.isAgrupacionCodSia"
						id="servicio.agrupacionCodSia"
						value="%{servicio.isAgrupacionCodSia}" />
				</p>
				<p class="criteria" id="agrupacionCodOrgPagador"
					style="visibility: hidden">
					<label theme="simple"
						style="width: 280px; margin-left: 50px; display: inline-block; float: left;">Total
						de mensajes agrupador por organismo pagador:</label>
					<s:checkbox theme="simple"
						name="servicio.isAgrupacionCodOrgPagador"
						id="servicio.agrupacionCodOrgPagador"
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
				<p class="criteria">
					<label class="fieldText" style="width: 120px;">Multiorganismo</label>
					<s:checkbox theme="simple" id="servicio.multiorganismo"
						onchange="activarMultiorganismo()" name="servicio.multiorganismo"
						value="%{servicio.multiorganismo}" />
				</p>
				<s:hidden name="servicio.multiorganismo"
					id="servicio.multiorganismo" value="%{servicio.multiorganismo}" />

				</p>
				<p class="criteria">

					<span style="width: 350px;"> <label style="width: 120px;"
						class="fieldText">Organismo</label> <s:select
							id="servicioOrganismos.organismoId"
							name="servicioOrganismos.organismoId" emptyOption="true"
							theme="simple" labelposition="left" title="Organismos"
							list="comboServicioOrganismos" listKey="codigo"
							listValue="descripcion" cssClass="" cssStyle="width:138px"
							value="%{servicioOrganismos.organismoId}" disabled="false" /> <s:hidden
							name="idServicio" id="idServicio" value="%{idServicio}" /> <s:hidden
							name="idOrganismo" id="idOrganismo"
							value="%{servicioOrganismos.organismoId}" />
					</span> <a class="addLink" id="addItem" onclick="insertarNuevoOrganismo()"
						name="addItem">A�adir Item</a>
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
							<th class="TH100">C�digo Organismo</th>
							<th class="TH150">Nombre</th>
							<th class="TH280">Descripci�n</th>
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
								<td class="buttons"><span class="edit"> <a
										class="btnEdit" title="Editar"
										href="editOrganismo.action?idOrganismo=${organismo.organismoId}&from=editServicio.action&idFrom=${servicio.servicioId}&var=idServicio"></a>
								</span> <span class="delete"> <a class="btnDelete"
										title="Eliminar" onclick="return confirmDelete();"
										href="deleteServicioOrganismo.action?idServicioOrganismo=${organismo.servicioOrganismoId}&idServicio=${servicio.servicioId}&idOrganismo=${organismo.organismoId}"></a>
								</span></td>

							</tr>
						</s:iterator>
						<s:if test="%{listaServicioOrganismos == null}">
							<tr>

								<td colspan="5">No se ha configurado servidor para el
									servicio</td>
							</tr>
							<script>document.getElementById('checkAllSO').style.visibility="hidden";</script>
						</s:if>
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
					</span> <span> <label style="width: 100px;" class="fieldText">N�
							reintentos (*):</label> <s:textfield name="servidorServicio.numIntentos"
							value="%{servidorServicio.numIntentos}"
							id="servidorServicio.numIntentos" theme="simple"
							onKeyPress="return numbersonly(this, event)" labelposition="left"
							title="N� Reintentos" size="4" maxlength="255" cssClass="" />
					</span> <a class="addLink" id="addItem"
						onclick="return checkItem('formaddServidorServicio',false,'${servicio.canalId}');"
						name="addItem">A�adir Item</a>
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
						Contrase�a (*):</label>
					<s:password name="servidorServicio.proveedorPasswordSMS"
						title="Proveedor contrase�a SMS"
						value="%{servidorServicio.proveedorPasswordSMS}"
						id="servidorServicio.proveedorPasswordSMS" theme="simple"
						cssStyle="visibility:hidden" labelposition="left" size="16"
						maxlength="255" cssClass="" showPassword="true" />
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
							<th class="TH50separator">N� reintentos:</th>
							<th class="TH80separator" id="auna">Header:</th>
							<th class="TH50separator" id="aune">Usuario:</th>
							<th class="TH50separator" id="auni">Organismo:</th>
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
								<td><s:label value="%{numIntentos}" /></td>
								<td id="aunaValue1"><s:label value="%{headerSMS}" /></td>
								<td id="auneValue1"><s:label value="%{proveedorUsuarioSMS}" /></td>
								<td id="auniValue1"><s:label value="%{DIR3Organismo}" /></td>
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
							id="planificacionServidor.servicioId" value="%{idServicio}" /> <s:hidden
							name="servicio.canalId" id="servicio.canalId"
							value="%{servicio.canalId}" />
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
						name="addItem">A�adir Item</a>
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
							<th class="TH110">D�as</th>
							<th class="TH70 separator">Hora Inicio</th>
							<th class="TH70 separator">Hora Fin</th>
							<th class="TH200">Servidor / Proveedor</th>
							<th class="TH70">Organismo</th>
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
								<td><s:label value="%{DIR3Organismo}" /></td>
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
								<td colspan="7">No se ha configurado ninguna planificaci�n
									para el servicio</td>
							</tr>
							<script>document.getElementById('checkAllP').style.visibility="hidden";</script>
						</s:if>
						<s:else>
							<tr>
							<tfoot>
								<td colspan="6"><span class="leftSide"> <s:submit
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
				checkCanalHeader(document.getElementById("servicio.canalId"));
				
			</script>
		</div>


	</div>
	<div class="editContainer">
		<div class="nameDescription">
			<label>Auditor�a</label>
		</div>
		<div class="editContent">
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 120px;"
					class="fieldText">Creador:</label> <strong><s:label
							theme="simple" id="servicio.creadoPor" name="servicio.creadoPor"
							value="%{servicio.creadoPor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Creaci�n:</label> <strong><s:label theme="simple"
							id="servicio.fechaCreacion" name="servicio.fechaCreacion"
							value="%{servicio.fechaCreacion}" /></strong>
				</span>
			</p>
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 120px;"
					class="fieldText">�ltimo Modificador:</label> <strong><s:label
							theme="simple" id="servicio.modificadoPor"
							name="servicio.modificadoPor" value="%{servicio.modificadoPor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						�ltima Modificaci�n:</label> <strong><s:label theme="simple"
							id="servicio.fechaModificacion" name="servicio.fechaModificacion"
							value="%{servicio.fechaModificacion}" /></strong>
				</span>
			</p>
		</div>
	</div>

</div>

<script>
	function cargarNuevaAplicacion() {              
        document.frmEditServicio.action="aplicacionSelectEditEvent.action";
	    document.frmEditServicio.submit();

	}
	function insertarNuevoOrganismo() {        
		//document.formaddServicioOrganismo.action="aplicacionSelectEditEvent.action";      
        document.formaddServicioOrganismo.submit();

	}

	function activarMultiorganismo() {              
        document.formaddServicioOrganismo.action="activarMultiorganismoSelectEditEvent.action";
	    document.formaddServicioOrganismo.submit();
	//   var estado;	  
	//   if($('#servicio.multiorganismo').is(':checked')) { 
   //        estado ='1';  
   //     } else { 
    //    	estado ='0';
    //               } 
	//	var servicio = ${servicio.servicioId};
		//var listado = ${listaServicioOrganismos};
	//var sd = listado.length;
	//	 $.ajax({		 
	   //   type: "POST",
	//      url: "activarMultiorganismoSelectEditEvent.action",
	//	  data: "activo=" + estado+"_"+servicio,//+"_"+listado.length,
	//	  dataType : "json",
	//	  success: function() {
     //         alert("success");  
     //     }
	//	});
	}

</script>

