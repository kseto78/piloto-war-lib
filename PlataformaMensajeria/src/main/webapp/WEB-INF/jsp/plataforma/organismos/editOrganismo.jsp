<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
	<s:form id="frmEditOrganismo" method="POST" action="updateOrganismo"
		validate="false" theme="simple" cssClass="">
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:submit theme="simple"
					value="%{getText('buttons.text.save')}" cssClass="button"
					alt="%{getText('buttons.text.save')}" /> <input type="button"
				onclick="javascript:location.href='${volver}'" class="button"
				value="Volver">
			</span> <label>EDICIÓN ORGANISMOS </label>
		</h3>
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp"%>
		<%@include
			file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp"%>
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
				<span class="leftSide"></span> <span class="rightSide"> <input
					type="button" value="Guardar" class="button" />
				</span>
			</div>
		</sj:dialog>
		<div class="editContainer">
			<div class="nameDescription">
				<label>Datos Generales</label>
			</div>
			<div class="editContent">
				<s:hidden theme="simple" id="organismo.organismoId"
						name="organismo.organismoId" value="%{organismo.organismoId}" />
				<s:hidden theme="simple" id="idOrganismo" name="idOrganismo"
						value="%{organismo.organismoId}" />
				<s:if test="%{organismo.manual}">
					<p class="criteria">
						<label style="width: 120px;" class="fieldText">Código Organismo (*):</label>
						<s:textfield name="organismo.dir3" value="%{organismo.dir3}" 
							id="organismo.dir3" theme="simple" labelposition="left" size="70"
							maxlength="255" cssClass="input_tablas_registro" />
					</p>
					<p class="criteria">
						<label style="width: 120px;" class="fieldText">Nombre (*):</label>
						<s:textfield name="organismo.nombre" value="%{organismo.nombre}" 
							id="organismo.nombre" theme="simple" labelposition="left"
							size="70" maxlength="255" cssClass="input_tablas_registro" />
					</p>
					<p class="criteria">
						<label style="width: 120px;" class="fieldText">Descripción:</label>
						<s:textarea name="organismo.descripcion" id="organismo.descripcion" 
							theme="simple" rows="6" cols="70" value="%{organismo.descripcion}">
						</s:textarea>
					</p>
				</s:if>	
				<s:else>
				<p class="criteria">
						<label style="width: 120px;" class="fieldText">Código Organismo (*):</label>
						<s:textfield name="organismo.dir3" value="%{organismo.dir3}" disabled="true"
							id="organismo.dir3" theme="simple" labelposition="left" size="70"
							maxlength="255" cssClass="input_tablas_registro" />
					</p>
					<p class="criteria">
						<label style="width: 120px;" class="fieldText">Nombre (*):</label>
						<s:textfield name="organismo.nombre" value="%{organismo.nombre}" disabled="true"
							id="organismo.nombre" theme="simple" labelposition="left"
							size="70" maxlength="255" cssClass="input_tablas_registro" />
					</p>
					<p class="criteria">
						<label style="width: 120px;" class="fieldText">Descripción:</label>
						<s:textarea name="organismo.descripcion" id="organismo.descripcion" disabled="true"
							theme="simple" rows="6" cols="70" value="%{organismo.descripcion}">
						</s:textarea>
					</p>
				</s:else>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Activo:</label>
					<s:checkbox theme="simple" id="organismo.isActivo"
						name="organismo.isActivo" value="%{organismo.activado}" />
				</p>
				<p class="criteria">

					<span style="width: 350px;"> <label style="width: 120px;"
						class="fieldText">Organismo Pdp: </label> <s:select
							id="organismo.idOrganismoPdp"
							name="organismo.idOrganismoPdp" emptyOption="true"
							theme="simple" labelposition="left" title="Organismos Pdp"
							list="comboOrganismosPdp" listKey="codigo"
							listValue="descripcion" cssClass="" cssStyle="width:138px"
							value="%{organismo.idOrganismoPdp}" disabled="false" />
					</span>
				</p>
				<p class="criteria">
					<label style="width: 150px;" class="fieldText"><i>(*)
							Campos obligatorios</i></label>

				</p>
			</div>
		</div>

	</s:form>

	<div class="editContainer">
		<div class="nameDescription">
			<label>Servicios</label>
			<p>Un organismo puede tener asociado un conjunto ilimitado de
				servicios.</p>
		</div>
		<div class="editContent">
			<s:form id="formaddOrganismoServicio" theme="simple" validate="false"
				name="formaddOrganismoServicio" method="POST"
				action="addOrganismoServicios">

				<p class="criteria">

					<span style="width: 350px;"> <label style="width: 120px;"
						class="fieldText">Servicios: </label> <s:select
							id="servicioOrganismos.servicioId"
							name="servicioOrganismos.servicioId" emptyOption="true"
							theme="simple" labelposition="left" title="Organismos"
							list="comboServicioOrganismos" listKey="codigo"
							listValue="descripcion" cssClass="" cssStyle="width:138px"
							value="%{servicioOrganismos.servicioId}" disabled="false" /> <s:hidden
							name="idOrganismo" id="idOrganismo" value="%{idOrganismo}" />
					</span> <a class="addLink" id="addItem" onclick="insertarNuevoOrganismo()"
						name="addItem">Añadir Item</a>
				</p>

			</s:form>

			<script type="text/javascript">
				function checkBotonEliminarSeleccionados4() {
					var listaChecks = document
							.getElementById('formDeleteOrganismoServicioSelected').checkDelListOrganismosServicios;

					var botonEliminarSeleccionados = document
							.getElementById('eliminaSeleccionadosOrganismosServicios');
					var enable = false;
					if (listaChecks.checked) {
						enable = true;
					}
					for (i = 0; lcheck = listaChecks[i]; i++) {
						if (lcheck.checked) {
							enable = true;
						}
					}
					if (enable) {
						botonEliminarSeleccionados.disabled = "";
					} else {
						botonEliminarSeleccionados.disabled = "disabled";
					}
				}
				function selectAllSOO(checkAllSOO) {
					var listaChecks = document
							.getElementById('formDeleteOrganismoServicioSelected').checkDelListOrganismosServicios;
					if (listaChecks.checked != "undefined") {
						if (checkAllSOO.checked) {
							listaChecks.checked = true;
						} else {
							listaChecks.checked = false;
						}
					}

					for (i = 0; lcheck = listaChecks[i]; i++) {
						if (checkAllSOO.checked) {
							lcheck.checked = true;
						} else {
							lcheck.checked = false;
						}
					}
					checkBotonEliminarSeleccionados4();
				}
			</script>

			<s:form id="formDeleteOrganismoServicioSelected"
				onsubmit="return confirmDeleteSelected();" theme="simple"
				validate="false" name="formDeleteOrganismoServicioSelected" method="POST"
				action="deleteOrganismoServicioSelected">

				<table cellspacing="0" cellpadding="0" border="0"
					>
					<thead>
						<tr>
							<th class=""><input type="checkbox" id="checkAllSOO"
								theme="simple" onclick="selectAllSOO(this)" /></th>
							<th class="TH100">Id Servicio</th>
							<th class="TH150">Nombre Servicio</th>
							<th class="TH280">Descripción Servicio</th>
							<th class="TH45 separator"></th>
							
						</tr>
					</thead>

					<tbody>
						<s:iterator value="%{listaServicioOrganismos}" var="organismoServ"
							status="organismoServStatus">
							<tr
								class="<s:if test='#organismoServStatus.odd == true '></s:if><s:else>odd</s:else>">
								<td class="darkTD TH15"><input type="checkbox"
									onclick="checkBotonEliminarSeleccionados4()"
									id="checkDelListOrganismosServicios"
									name="checkDelListOrganismosServicios"
									value="${organismoServ.servicioOrganismoId}" /> <input type="hidden"
									id="__checkbox_checkDelListOrganismosServicios"
									name="__checkbox_checkDelListOrganismosServicios" /></td>
								<td><s:label value="%{servicioId}" /></td>
								<td><s:label value="%{nombreServicio}" /></td>
								<td><s:label value="%{descripcionServicio}" /></td>
								<td class="buttons"><span class="edit"> <a
										class="btnEdit" title="Editar"
										href="editServicio.action?idServicio=${organismoServ.servicioId}&from=editOrganismo.action&idFrom=${organismo.organismoId}&var=idOrganismo"></a>
								</span> <span class="delete"> <a class="btnDelete"
										title="Eliminar" onclick="return confirmDelete();"
										href="deleteOrganismoServicio.action?idServicioOrganismo=${organismoServ.servicioOrganismoId}&idServicio=${organismoServ.servicioId}&idOrganismo=${organismo.organismoId}"></a>
								</span></td>
								
							</tr>
						</s:iterator>
						<s:if test="%{listaServicioOrganismos == null}">
							<tr>

								<td colspan="5">No se ha configurado servidor para el
									organismo</td>
							</tr>
							<script>
								document.getElementById('checkAllSOO').style.visibility = "hidden";
							</script>
						</s:if>
						<s:else>
							<tr>
							<tfoot>
								<td colspan="4"><s:submit
										id="eliminaSeleccionadosOrganismosServicios"
										name="eliminaSeleccionadosOrganismosServicios" theme="simple"
										disabled="true"
										value="%{getText('button.plataforma.eliminarseleccionados')}"
										cssClass="button" />
								<td>
							</tfoot>
							</tr>
						</s:else>

					</tbody>
				</table>
				<s:hidden name="idOrganismo" id="idOrganismo" value="%{idOrganismo}" />
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
			<s:form id="formaddServidorOrganismo" theme="simple" validate="false"
				name="formaddServidorOrganismo" method="POST"
				action="addServidorOrganismo">
				<p class="criteria">
					
					<span style="width: 300px;"> <label style="width: 145px;"
						class="fieldText">Servidor / Proveedor:</label> <s:select
							id="servidorOrganismo.servidorId"
							name="servidorOrganismo.servidorId" emptyOption="true" onchange="checkTipoHeader(this)"
							theme="simple" labelposition="left" title="Servidor / Proveedor"
							list="comboServidoresOrganismos" listKey="codigo"
							listValue="descripcion" cssClass="" cssStyle="width:138px"
							value="%{servidorOrganismo.servidorId}" disabled="false" /> <s:hidden
							name="idOrganismo" id="idOrganismo" value="%{idOrganismo}" />
							
					</span> 
					 <a class="addLink" id="addItem"
						onclick="return checkItem('formaddServidorOrganismo',false,'2');"
						name="addItem">Añadir Item</a>
				</p>
				<p class="criteria">
					<label id="headerSMSOrganismoLabel" style="width: 145px;"
						class="fieldText">Header SMS (*):</label>
					<s:textfield name="servidorOrganismo.headerSMS" title="Header SMS"
						value="%{servidorOrganismo.headerSMS}" id="servidorOrganismo.headerSMS"
						theme="simple"  labelposition="left"
						size="20" maxlength="255" cssClass="" />
				</p>
				<p class="criteria">
					<label id="proveedorUsuarioSMSOrganismoLabel" style="width: 145px;"
						class="fieldText">Proveedor Usuario (*):</label>
					<s:textfield name="servidorOrganismo.proveedorUsuarioSMS" title="Proveedor usuario SMS"
						value="%{servidorOrganismo.proveedorUsuarioSMS}" id="servidorOrganismo.proveedorUsuarioSMS"
						theme="simple"  labelposition="left"
						size="20" maxlength="255" cssClass="" />
				</p>
				<p class="criteria">
					<label id="proveedorPassSMSOrganismoLabel" style="width: 145px;"
						class="fieldText">Proveedor Contraseña (*):</label>
					<s:password name="servidorOrganismo.proveedorPasswordSMS" title="Proveedor contraseña SMS"
						value="%{servidorOrganismo.proveedorPasswordSMS}" id="servidorOrganismo.proveedorPasswordSMS"
						theme="simple" labelposition="left"
						size="15" maxlength="255" cssClass="" showPassword="true"/>
				</p>
			</s:form>
			<!-- --------AQUE VA LA TABLA -->
			<script type="text/javascript">
                   function checkBotonEliminarSeleccionados5(){
                       var listaChecks = document.getElementById('formDeleteServidorOrganismoSelected').checkDelListServidorOrganismos;

                       var botonEliminarSeleccionados = document.getElementById('eliminaSeleccionadosSO');
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
                       var listaChecks = document.getElementById('formDeleteServidorOrganismoSelected').checkDelListServidorOrganismos;
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
                       checkBotonEliminarSeleccionados5();
                   }
               
		</script>

			<s:form id="formDeleteServidorOrganismoSelected"
				onsubmit="return confirmDeleteSelected();" theme="simple"
				validate="false" name="formDeleteServidorOrganismoSelected" method="POST"
				action="deleteServidorOrganismosSelected">
				
				<table cellspacing="0" cellpadding="0" border="0">
					<thead>
						<tr>
							<th class=""><input type="checkbox" id="checkAllS"
								theme="simple" onclick="selectAllS(this)" /></th>
							<th class="TH230">Servidor / Proveedor</th>
							<th class="TH50separator">Nº reintentos</th>
							<th class="TH80separator" id="auna">Header</th>
							<th class="TH50separator" id="aune">Usuario</th>
							<th class="TH20 separator"></th>
						</tr>
					</thead>
					<tbody >
						<s:iterator value="%{listaServidoresOrganismos}"
							var="servidorOrganismo" status="servidorOrganismoStatus">
							<tr
								class="<s:if test='#servidorOrganismoStatus.odd == true '></s:if><s:else>odd</s:else>">
								<td class="darkTD TH15"><input type="checkbox"
									onclick="checkBotonEliminarSeleccionados5()"
									id="checkDelListServidorOrganismos"
									name="checkDelListServidorOrganismos"
									value="${servidorOrganismo.servidorOrganismoId}" /> <input
									type="hidden" id="__checkbox_checkDelListServidorOrganismos"
									name="__checkbox_checkDelListServidorOrganismos" /></td>
								<td><s:label value="%{nombreServidor}" /></td>
								<td><s:label value="%{numIntentos}" /></td>
								<td id="aunaValue1"><s:label value="%{headerSMS}"/></td>
								<td id="auneValue1"><s:label value="%{proveedorUsuarioSMS}"/></td>
								<td class="buttons"><span class="delete"> <a
										class="btnDelete" onclick="return confirmDelete();"
										href="deleteServidorOrganismo.action?servidorOrganismoId=${servidorOrganismo.servidorOrganismoId}&idOrganismo=${organismo.organismoId}&idServidor=${servidorOrganismo.servidorId}"></a>
								</span></td>
							</tr>
						</s:iterator>
						<s:if test="%{listaServidoresOrganismos == null}">
							<tr>
								
								<td colspan="6" >No se ha configurado servidor para el
									servicio</td>
							</tr>
							<script>document.getElementById('checkAllS').style.visibility="hidden";</script>
						</s:if>
						<s:else>
							<tr>
							<tfoot>
								<td colspan="5"><s:submit id="eliminaSeleccionadosSO"
										name="eliminaSeleccionadosSO" theme="simple" disabled="true"
										value="%{getText('button.plataforma.eliminarseleccionados')}"
										cssClass="button" />
								<td>
							</tfoot>
							</tr>
						</s:else>

					</tbody>
				</table>
				<s:hidden name="idOrganismo" id="idOrganismo" value="%{idOrganismo}" />
			</s:form>

		</div>
	</div>

<!-- ****************************************************
***********************PLANIFICACION*********************
********************************************************* -->
<div class="editContainer">
		<div class="nameDescription">
			<label>Planificaciones</label>
			<p>Determina los horarios en los que el servicio va a estar
				disponible para realizar los env&iacute;os</p>
		</div>
		<div class="editContent">
			<script>
            	function checkParams(){
            		var sw=validaPlanJS('formAddPlanificacionOrganismo','planificacionOrganismo');
            		if(sw){
            			document.forms['formAddPlanificacionOrganismo'].submit();
            		}else{
            			return false;
            		}
            	}
            </script>
			<s:form id="formAddPlanificacionOrganismo" theme="simple" validate="false"
				onsubmit="return validaPlanJS('formAddPlanificacionOrganismo','planificacionOrganismo')"
				name="borja.gutierrez.goiria@gmail.com." method="POST"
				action="addPlanificacionOrganismo">
				<p class="criteria">
					<span style="width: 350px;"> <label style="width: 120px;"
						class="fieldText">Servidor / Proveedor:</label> <s:select
							id="servidorOrganismo.servidorId" cssStyle="width:140px"
							name="servidorOrganismo.servidorId" emptyOption="true"
							theme="simple" labelposition="left"
							list="comboServidoresPlan" listKey="codigo"
							listValue="descripcion" cssClass=""
							value="%{servidorOrganismo.servidorId}" /> 
						<s:hidden name="planificacionOrganismo.servidorId"
							id="planificacionOrganismo.servidorId" value="%{servidorOrganismo.servidorId}" /> 
						<s:hidden theme="simple" id="idOrganismo" name="idOrganismo"
						value="%{organismo.organismoId}" />
					</span>
					<span style="width: 350px;"> <label style="width: 120px;"
						class="fieldText">Servicio:</label> <s:select
							id="servicio.servicioId"
							name="servicio.servicioId" emptyOption="true"
							theme="simple" labelposition="left" cssStyle="width:140px"
							list="comboServiciosPlan" listKey="codigo"
							listValue="descripcion" cssClass=""
							value="%{servicio.servicioId}" /> 
						<s:hidden name="planificacionOrganismo.servicioId"
							id="planificacionOrganismo.servicioId" value="%{servicio.servicioId}" /> 
					</span>
				</p>
				<p class="criteria">
					<span> <label class="fieldText">L <s:checkbox
								id="planificacionOrganismo.lunes"
								name="planificacionOrganismo.lunes"
								value="%{planificacionOrganismo.lunes}" theme="simple" />
					</label> <label class="fieldTextNoIco"> M <s:checkbox
								id="planificacionOrganismo.martes"
								name="planificacionOrganismo.martes"
								value="%{planificacionOrganismo.martes}" theme="simple" />
					</label> <label class="fieldTextNoIco"> X <s:checkbox
								id="planificacionOrganismo.miercoles"
								name="planificacionOrganismo.miercoles"
								value="%{planificacionOrganismo.miercoles}" theme="simple" />
					</label> <label class="fieldTextNoIco">J <s:checkbox
								id="planificacionOrganismo.jueves"
								name="planificacionOrganismo.jueves"
								value="%{planificacionOrganismo.jueves}" theme="simple" />
					</label> <label class="fieldTextNoIco">V <s:checkbox
								id="planificacionOrganismo.viernes"
								name="planificacionOrganismo.viernes"
								value="%{planificacionOrganismo.viernes}" theme="simple" />
					</label> <label class="fieldTextNoIco">S <s:checkbox
								id="planificacionOrganismo.sabado"
								name="planificacionOrganismo.sabado"
								value="%{planificacionOrganismo.sabado}" theme="simple" />
					</label> <label class="fieldTextNoIco">D <s:checkbox
								id="planificacionOrganismo.domingo"
								name="planificacionOrganismo.domingo"
								value="%{planificacionOrganismo.domingo}" theme="simple" />
					</label>

					</span> <span> <label class="fieldText">Hora Inicio:</label> <s:select
							id="planificacionOrganismo.horaDesde"
							name="planificacionOrganismo.horaDesde" emptyOption="false"
							theme="simple" labelposition="left" list="comboHorasInicio"
							listKey="codigo" listValue="descripcion" cssClass="W65"
							value="%{planificacionOrganismo.horaDesde}" disabled="false" />
					</span> <span> <label class="fieldText">Hora Fin:</label> <s:select
							id="planificacionOrganismo.horaHasta"
							name="planificacionOrganismo.horaHasta" emptyOption="false"
							theme="simple" labelposition="left" list="comboHorasFin"
							listKey="codigo" listValue="descripcion" cssClass="W65"
							value="%{planificacionOrganismo.horaHasta}" disabled="false" />
					</span> <a class="addLink" id="addItem"
						onclick="return checkItem('formAddPlanificacionOrganismo',true,'planificacionOrganismo');"
						name="addItem">Añadir Item</a>
				</p>

			</s:form>
			
			<!-- AQUI VA LA TABLA con las planificaciones-->
			<s:form id="formDeletePlanificacionOrganismoSeleccionadas"
				onsubmit="return confirmDeleteSelected();" theme="simple"
				validate="false" name="formDeletePlanificacionOrganismoSeleccionadas"
				method="POST" action="deletePlanificacionesOrganismoSelected">

				<script type="text/javascript">
                   function checkBotonEliminarSeleccionados6(){
                       var listaChecks = document.getElementById('formDeletePlanificacionOrganismoSeleccionadas').checkDelListPlanificacionesOrganismos;

                       var botonEliminarSeleccionados = document.getElementById('eliminaSeleccionadosPLO');
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
                       var listaChecks = document.getElementById('formDeletePlanificacionOrganismoSeleccionadas').checkDelListPlanificacionesOrganismos;
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
                       checkBotonEliminarSeleccionados6();
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
							<th class="TH100">Servidor / Proveedor</th>
							<th class="TH100">Servicio</th>
							<th class="TH45 separator"></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="%{listaPlanificacionesServicio}"
							var="planificacionOrganismo" status="planificacionOrganismoStatuts">
							<tr
								class="<s:if test='#planificacionOrganismoStatuts.odd == true '></s:if><s:else>odd</s:else>">
								<!-- <td>L, M, J</td> -->
								<td class="darkTD TH15"><input type="checkbox"
									onclick="checkBotonEliminarSeleccionados6()"
									id="checkDelListPlanificacionesOrganismos"
									name="checkDelListPlanificacionesOrganismos"
									value="${planificacionOrganismo.planificacionId }" />
									</center> <input type="hidden"
									idd="__checkbox_checkDelListPlanificacionesOrganismos"
									name="__checkbox_checkDelListPlanificacionesOrganismos" />
								<td><s:label value="%{dias}" /></td>
								<td><s:label value="%{horaDesde}" /></td>
								<td><s:label value="%{horaHasta}" /></td>
								<td><s:label value="%{nombreServidor}" /></td>
								<td><s:label value="%{nombreServicio}" /></td>
								<td class="buttons"><span class="edit">
										<div
											id="ajaxloader_ajax_${planificacionOrganismo.planificacionId}">
											<span id="ajax_${planificacionOrganismo.planificacionId}"
												name="ajax" title="Editar"
												onclick="return loadPlan(this,${planificacionOrganismo.planificacionId},${idOrganismo},'idOrganismo','updatePlanificacionOrganismo')"
												class="btnEdit planifications_link"></span>
										</div>
								</span> <span class="delete"> <a class="btnDelete"
										onclick="return confirmDelete();" title="Eliminar"
										href="deletePlanificacionOrganismo.action?idPlanificacion=${planificacionId}&idOrganismo=${organismo.organismoId}"></a>
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
								<td colspan="6"><span class="leftSide"> <s:submit
											id="eliminaSeleccionadosPLO" name="eliminaSeleccionadosPLO"
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
				<s:hidden name="idOrganismo" id="idOrganismo" value="%{idOrganismo}" />
			</s:form>
			
		</div>


	</div>


	<div class="editContainer">
		<div class="nameDescription">
			<label>Auditoría</label>
		</div>
		<div class="editContent">
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 140px;"
					class="fieldText">Creador:</label> <strong><s:label
							theme="simple" id="organismo.creadopor"
							name="organismo.creadopor" value="%{organismo.creadopor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Creación:</label> <strong><s:label theme="simple"
							id="organismo.fechacreacion" name="organismo.fechacreacion"
							value="%{organismo.fechacreacion}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Activo:</label> <strong><s:label theme="simple"
							id="organismo.fechaActivo" name="organismo.fechaActivo"
							value="%{organismo.fechaActivo}" /></strong>
				</span>
			</p>
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 140px;"
					class="fieldText">Último Modificador:</label> <strong><s:label
							theme="simple" id="organismo.modificadopor"
							name="organismo.modificadopor" value="%{organismo.modificadopor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Última Modificación:</label> <strong><s:label theme="simple"
							id="organismo.fechamodificacion"
							name="organismo.fechamodificacion"
							value="%{organismo.fechamodificacion}" /></strong>
				</span>
			</p>
		</div>
	</div>

	<script>
	
	
		function insertarNuevoOrganismo() {
			//document.formaddServicioOrganismo.action="aplicacionSelectEditEvent.action";      
			document.formaddOrganismoServicio.submit();

		}

		function insertarNuevoServidor() {
			//document.formaddServicioOrganismo.action="aplicacionSelectEditEvent.action";      
			document.formaddServidorOrganismo.submit();

		}
		
	</script>
</div>