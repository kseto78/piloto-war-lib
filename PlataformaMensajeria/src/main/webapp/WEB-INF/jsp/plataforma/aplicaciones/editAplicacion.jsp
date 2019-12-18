<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR,ROL_CAID">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
	<s:form id="frmEditAplicacion" method="POST" action="updateAplicacion"
		validate="false" theme="simple" cssClass="">
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:submit theme="simple"
					value="%{getText('buttons.text.save')}" cssClass="button"
					alt="%{getText('buttons.text.save')}" /> <input type="button"
				onclick="javascript:location.href='${volver}'" class="button"
				value="Volver">
			</span> <label>EDICIÓN APLICACIONES </label>
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
					<s:hidden theme="simple" id="aplicacion.aplicacionId"
						name="aplicacion.aplicacionId" value="%{aplicacion.aplicacionId}" />
					<s:hidden theme="simple" id="idAplicacion" name="idAplicacion"
						value="%{aplicacion.aplicacionId}" />
					<label style="width: 140px;" class="fieldText">Nombre (*):</label>
					<s:textfield name="aplicacion.nombre" value="%{aplicacion.nombre}"
						id="aplicacion.nombre" theme="simple" labelposition="left"
						size="85" maxlength="255" cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label style="width: 140px;" class="fieldText">Descripción
						(*):</label>
					<s:textarea name="aplicacion.descripcion"
						id="aplicacion.descripcion" theme="simple" rows="6" cols="82"
						value="%{aplicacion.descripcion}">
					</s:textarea>
				</p>
				<p class="criteria">
					<label style="width: 140px;" class="fieldText">Activo:</label>
					<s:checkbox theme="simple" id="aplicacion.isActivo"
						name="aplicacion.isActivo" value="%{aplicacion.activado}" />
				</p>
				<p class="criteria">
					<label style="width: 150px;" class="fieldText"><i>(*)
							Campos obligatorios</i></label>

				</p>
			</div>
		</div>
		<div class="editContainer">
			<div class="nameDescription">
				<label>Seguridad</label>
			</div>
			<div class="editContent">
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Usuario (*):</label>
					<s:textfield name="aplicacion.usuario"
						value="%{aplicacion.usuario}" id="aplicacion.usuario"
						theme="simple" labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>

				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Contraseña
						(*):</label>
					<s:password name="aplicacion.password"
						value="%{aplicacion.password}" id="aplicacion.password"
						theme="simple" labelposition="left" size="45" maxlength="255"
						showPassword="true" cssClass="input_tablas_registro" />
				</p>
				<p class="criteria" id="repPassLabel" style="visibility: hidden">
					<label class="fieldText" style="width: 140px;">Rep.
						Contraseña (*):</label>
					<s:password name="aplicacion.rePassword" value=""
						id="aplicacion.rePassword" theme="simple" labelposition="left"
						showPassword="true" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
					<s:hidden name="checkPassword" id="checkPassword" value="false" />
				</p>
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
					<label class="fieldText" style="width: 140px;">Responsable técnico: (*)</label>
					<s:textfield name="aplicacion.respTecnicoNombre"
						value="%{aplicacion.respTecnicoNombre}"
						id="aplicacion.respTecnicoNombre" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Email: (*)</label>
					<s:textfield name="aplicacion.respTecnicoEmail"
						value="%{aplicacion.respTecnicoEmail}"
						id="aplicacion.respTecnicoEmail" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>				
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Responsable funcional: (*)</label>
					<s:textfield name="aplicacion.respFuncionalNombre"
						value="%{aplicacion.respFuncionalNombre}"
						id="aplicacion.respFuncionalNombre" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Email: (*)</label>
					<s:textfield name="aplicacion.respFuncionalEmail"
						value="%{aplicacion.respFuncionalEmail}"
						id="aplicacion.respFuncionalEmail" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>	
				<p class="criteria">
					<label style="width: 150px;" class="fieldText"><i>(*)
							Campos obligatorios</i></label>
				</p>			
			</div>
		</div>
	</s:form>
	<s:form id="frmEliminarServicioAplicacion"
		onsubmit="return confirmDeleteSelected();" theme="simple"
		method="POST" action="deleteServiciosAplicacionSeleccionados">
		<script type="text/javascript">
			function checkBotonEliminarSeleccionados() {
				var listaChecks = document
						.getElementById('frmEliminarServicioAplicacion').checkDelList;

				var botonEliminarSeleccionados = document
						.getElementById('eliminaSeleccionados');
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
			function selectAllP(checkAll) {
				var listaChecks = document
						.getElementById('frmEliminarServicioAplicacion').checkDelList;
				if (listaChecks.checked != "undefined") {
					if (checkAll.checked) {
						listaChecks.checked = true;
					} else {
						listaChecks.checked = false;
					}

				}

				for (i = 0; lcheck = listaChecks[i]; i++) {
					if (checkAll.checked) {
						lcheck.checked = true;
					} else {
						lcheck.checked = false;
					}
				}
				checkBotonEliminarSeleccionados();
			}
		</script>
		<div class="editContainer">
			<div class="nameDescription">
				<label>Servicios</label>
				<p>Una aplicación tendrá asociado un conjunto ilimitado de
					servicios.</p>
			</div>
			<div class="editContent">
				<table border="0" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="TH17"><input type="checkbox" id="checkAllP"
								name="checkAllP" theme="simple" onclick="selectAllP(this)" /></th>
							<th class="TH200 separator">Nombre de Servicio</th>
							<th class="separator">Canal</th>
							<th class="TH45 separator"></th>
						</tr>
					</thead>
					<!-- listaServiciosAplicacion -->
					<tbody>
						<s:iterator value="%{listaServiciosAplicacion}" var="servicio"
							status="servicioStatus">
							<tr class="<s:if test='#servicioStatus.odd == true '>odd</s:if>">
								<td class="darkTD TH15"><input type="checkbox"
									onclick="checkBotonEliminarSeleccionados()" id="checkDelList"
									name="checkDelList" value="${servicio.servicioId}" /></td>

								<td><s:label value="%{nombre}" theme="simple" /></td>
								<td><s:label value="%{canalnombre}" theme="simple" /></td>
								<td class="buttons"><span class="edit"> <a
										class="btnEdit" title="Editar"
										href="editServicio.action?idServicio=${servicioId}&from=editAplicacion.action&idFrom=${idAplicacion}&var=idAplicacion"></a>
								</span> <span class="delete"> <a class="btnDelete"
										title="Eliminar" onclick="return confirmDelete();"
										href="deleteServicioAplicacion.action?idServicio=${servicioId}&idAplicacion=${idAplicacion}"></a>
								</span></td>
							</tr>
						</s:iterator>


						<s:if test="%{listaServiciosAplicacion != null}">
							<tfoot>
								<tr>
									<td colspan="4"><span class="leftSide"> <s:submit
												id="eliminaSeleccionados" theme="simple" disabled="true"
												value="%{getText('button.plataforma.eliminarseleccionados')}"
												cssClass="button" />

									</span> <span class="rightSide"> <input type="button"
											value="Nueva Entrada" class="button"
											onclick='javascript:location.href="nuevoServicio.action?servicio.aplicacionid=${idAplicacion}&from=editAplicacion.action&idFrom=${idAplicacion}&var=idAplicacion"' />
									</span></td>
								</tr>
							</tfoot>
						</s:if>
						<s:else>
							<tr>
								<td colspan="4">La aplicación no tienen ningún servicio
									asignado</td>
								<script>
									document.getElementById('checkAllP').style.visibility = "hidden";
								</script>
							</tr>
							<tfoot>
								<tr>
									<td colspan="4"><span class="leftSide"> &nbsp; </span> <span
										class="rightSide"> <input type="button"
											value="Nueva Entrada" class="button"
											onclick='javascript:location.href="nuevoServicio.action?servicio.aplicacionid=${idAplicacion}&from=editAplicacion.action&idFrom=${idAplicacion}&var=idAplicacion"' />
									</span></td>
								</tr>
							</tfoot>
						</s:else>

					</tbody>
				</table>
			</div>
		</div>
		<input type="hidden" id="idAplicacion" name="idAplicacion"
			value="${idAplicacion}" />
	</s:form>
<!-- ****************************************************
***********************GESTION DE FICHEROS*********************
********************************************************* -->
<div class="editContainer">
		  			
		<div class="nameDescription">
			<label>Gestión de Documentos</label>
		</div>
		<div class="editContent">
		<s:form id="formaddDocumentoAplicacion" theme="simple" validate="false"
 				name="formaddDocumentoAplicacion" method="POST" enctype="multipart/form-data"
				action="addDocumentoAplicacion">
		<s:hidden theme="simple" id="idAplicacion" name="idAplicacion"
						value="%{aplicacion.aplicacionId}" />	
		<s:hidden theme="simple" id="nombreDocumento" name="nombreDocumento"
						value="%{nombreDocumento}" />
		<p class="criteria">
			<span style="width: 300px;"> <label style="width: 145px;"
						class="fieldText">Tipo:</label> <s:select
							id="tipoDocumento"
							name="tipoDocumento" emptyOption="true"
							theme="simple" labelposition="left" title="Tipo del fichero"
							list="comboDocumentosAplicaciones" listKey="codigo"
							listValue="descripcion" cssClass="" cssStyle="width:138px"
							 disabled="false" />
			</span>
			 <a class="addLink" id="addItem" onclick="insertarNuevoDocumento()"
							name="addItem">Añadir Item</a>
			
		</p>
		<p class="criteria">
			<span style="width: 300px;">
				<label style="width: 145px;"
						class="fieldText">Fichero:</label> 
				<input type="file" name="documento" id="documento" style="position: absolute;"> 
			 </span>
		 </p>
		</s:form>
			
			<s:form id="formDeleteDocumentoAplicacionSelected"
				onsubmit="return confirmDeleteSelected();" theme="simple"
				validate="false" name="formDeleteDocumentoAplicacionSelected" method="POST"
				action="deleteDocumentoAplicacionSelected">
				<s:hidden theme="simple" id="idAplicacion" name="idAplicacion"
						value="%{aplicacion.aplicacionId}" />
				<script type="text/javascript">
				  function checkBotonEliminarSeleccionados7(){
                      var listaChecks = document.getElementById('formDeleteDocumentoAplicacionSelected').checkDelListDocumentosAplicaciones;

                      var botonEliminarSeleccionados = document.getElementById('eliminaSeleccionadosDocumentosAplicaciones');
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
                 
                   function selectAllD(checkAllD){
                       var listaChecks = document.getElementById('formDeleteDocumentoAplicacionSelected').checkDelListDocumentosAplicaciones;
                       if(listaChecks.checked!="undefined"){
                    	   if(checkAllD.checked){
                    		   listaChecks.checked=true;
                    	   }else{
                    		   listaChecks.checked=false;
                    	   }
                       }
                       
                       for (i = 0; lcheck = listaChecks[i]; i++) {
                           if (checkAllD.checked) {
                               lcheck.checked=true;   
                           }else{
                        	   lcheck.checked=false;
                           }
                       }  
                       checkBotonEliminarSeleccionados7();
                   }
               </script>
				<table cellspacing="0" cellpadding="0" border="0"
					>
					<thead>
						<tr>
							<th class=""><input type="checkbox" id="checkAllD"
								theme="simple" onclick="selectAllD(this)" /></th>
							<th class="TH100">Elemento</th>
							<th class="TH150">Fichero</th>
							<th class="TH280">Fecha de subida</th>
							<th class="TH45 separator"></th>
							
						</tr>
					</thead>

					<tbody id="bodyTablaDocumentos">
						<s:iterator value="%{listaDocumentos}" var="aplicacionDoc"
							status="organismoServStatus">
							<tr
								class="<s:if test='#organismoServStatus.odd == true '></s:if><s:else>odd</s:else>">
								<td class="darkTD TH15"><input type="checkbox"
									onclick="checkBotonEliminarSeleccionados7()"
									id="checkDelListDocumentosAplicaciones"
									name="checkDelListDocumentosAplicaciones"
									value="${aplicacionDoc.elemento}" /> <input type="hidden"
									id="__checkbox_checkDelListDocumentosAplicaciones"
									name="__checkbox_checkDelListDocumentosAplicaciones" /></td>
								<td><s:label value="%{elemento}" /></td>
								<td><s:label value="%{fichero}" /></td>
								<td><s:label value="%{fechaSubida}" /></td>
								<td class="buttons">
									<span class="edit"> <a
											class="btnEdit" title="Descargar"
											href="descargarDocumentoAplicacion.action?tipoDocumento=${aplicacionDoc.elemento}&idAplicacion=${idAplicacion}&fichero=${aplicacionDoc.fichero}"></a>
									</span>
									<span class="delete"> <a class="btnDelete"
											title="Eliminar" onclick="return confirmDelete();"
											href="deleteDocumentoAplicacion.action?idAplicacion=${idAplicacion}&tipoDocumento=${aplicacionDoc.elemento}"></a>
									</span>
								</td>								
							</tr>
						</s:iterator>
						<s:if test="%{listaDocumentos == null}">
							<tr>

								<td colspan="5">La aplicación no tiene ningun documento asociado</td>
							</tr>
							<script>
// 								document.getElementById('selectAllD').style.visibility = "hidden";
							</script>
						</s:if>
						<s:else>
							<tr>
							<tfoot>
								<td colspan="4"><s:submit
										id="eliminaSeleccionadosDocumentosAplicaciones"
										name="eliminaSeleccionadosDocumentosAplicaciones" theme="simple"
										disabled="true"
										value="%{getText('button.plataforma.eliminarseleccionados')}"
										cssClass="button" />
								<td>
							</tfoot>
							</tr>
						</s:else>

					</tbody>
				</table>
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
								theme="simple" id="aplicacion.creadopor"
								name="aplicacion.creadopor" value="%{aplicacion.creadopor}" /></strong>
					</span> <span> <label style="width: 150px;" class="fieldText">Fecha
							Creación:</label> <strong><s:label theme="simple"
								id="aplicacion.fechacreacion" name="aplicacion.fechacreacion"
								value="%{aplicacion.fechacreacion}" /></strong>
					</span>
				</p>
				<p class="criteria">
					<span style="width: 340px;"> <label style="width: 140px;"
						class="fieldText">Último Modificador:</label> <strong><s:label
								theme="simple" id="aplicacion.modificadopor"
								name="aplicacion.modificadopor"
								value="%{aplicacion.modificadopor}" /></strong>
					</span> <span> <label style="width: 150px;" class="fieldText">Fecha
							Última Modificación:</label> <strong><s:label theme="simple"
								id="aplicacion.fechamodificacion"
								name="aplicacion.fechamodificacion"
								value="%{aplicacion.fechamodificacion}" /></strong>
					</span>
				</p>
			</div>
		</div>
		
	<script type="text/javascript">
		var oDiv = document.getElementById('aplicacion.password');
		var f = function(e) {
			document.getElementById('repPassLabel').style.visibility = "visible";
			if (document.getElementById('aplicacion.password').value != document
					.getElementById('repPassLabel').value) {
				document.getElementById('checkPassword').value = "true";
			}
		};
		if (oDiv.addEventListener) // W3C DOM
			oDiv.addEventListener('keyup', f, true);
		else if (oDiv.attachEvent) { // IE DOM
			oDiv.attachEvent("onkeyup", f);
		}
		//oDiv.addEventListener('keyup',f,true);
		
		function insertarNuevoDocumento(){
			var nomb = document.getElementById('documento');
			document.getElementById('nombreDocumento').value = nomb.value.split(/(\\|\/)/g).pop();
			var numDocumentos = document.getElementById("bodyTablaDocumentos").rows.length;

			if ($("#tipoDocumento option:selected").text() == '' ){
				alert('No ha seleccionado ningun tipo para el fichero');
				return;
			}
			if ( $("#documento").val() == ''){
				alert('No ha seleccionado ningun fichero');
				return;
			}
			for(var i=0;i<numDocumentos-1;i++){
					var tipoDocumento = document.getElementById("bodyTablaDocumentos").rows[i].cells[1].textContent;
					if( $("#tipoDocumento option:selected").text().replace(/\s/g, '') == tipoDocumento ){
							alert("No se pueden guardar dos documento del mismo tipo");
							return;
						}
				}
			
			
			
			document.formaddDocumentoAplicacion.submit();	
			}	
			$('#documento').bind('change', function() {			  
				  if(this.files[0].name.split('.').pop() != "pdf"){
					  alert("El tipo de fichero no es de tipo PDF");
					  documento.value = null;					  
					  }
				});
	</script>
</div>