<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR,ROL_CAID">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
	<sj:dialog  id="dialogTree" title="ARBOL ORGANISMO PDP" cssStyle="display:none" autoOpen="false"></sj:dialog>
	<s:form id="frmEditOrganismo" method="POST" action="updatePdpDiputaciones"
		validate="false" theme="simple" cssClass="">
		<s:hidden id = "datosServicios" name="datosServicios" />
		<s:hidden id = "datosServidor" name="datosServidor" />
		<s:hidden id = "serviciosHijosSeleccionados" name="serviciosHijosSeleccionados" />
		<s:hidden id = "servidorHijosSeleccionados" name="servidorHijosSeleccionados" />
		<s:hidden id = "replicarHijosServidores" name="replicarHijosServidores" />
		<s:hidden id = "seleccionarHijosServidores" name="seleccionarHijosServidores" />
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:submit theme="simple"
					value="%{getText('buttons.text.save')}" cssClass="button"
					alt="%{getText('buttons.text.save')}" onclick="return formAlta();"/> <input type="button"
				onclick="javascript:location.href='${volver}'" class="button"
				value="Volver">
			</span> <label>EDICIÓN PDP-DIPUTACIÓN</label>
		</h3>
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/warningForm.jsp"%>
		<%@include
			file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp"%>
		<sj:dialog id="dialogPlanifications" title="Planificación"
			cssStyle="min-height:150px;display:none" autoOpen="false">			
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
				<s:hidden theme="simple" id="organismo.pdpDiputacionesId"
						name="organismo.pdpDiputacionesId" value="%{organismo.pdpDiputacionesId}" />
				<s:hidden theme="simple" id="pdpDiputacionesId" name="pdpDiputacionesId"
						value="%{organismo.pdpDiputacionesId}" />						
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
					<p class="criteria">
						<label style="width: 120px;" class="fieldText">Activo:</label>
						<s:checkbox theme="simple" id="organismo.isActivo"
							name="organismo.isActivo" value="%{organismo.activado}" />
					</p>
				<p class="criteria">
					<label style="width: 150px;" class="fieldText"><i>(*)
							Campos obligatorios</i></label>

				</p>
			</div>
		</div>
		<s:if test="%{!comboOrganismosHijos.isEmpty()}">
		<div class="editContainer" id="tablaServicios">
			<div class="nameDescription">
				<label>Servicios</label>
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
							name="serviciosOrganismos" id="serviciosOrganismos" value="%{idOrganismo}" />
					</span> 
					<span id="addItemServicio" style="display: inline-block;float: right;">
						<a class="addLink" onclick="return loadServicios()" name="addItem">Añadir Item</a>
					</span>
				</p>
					<p class="criteria">
						<label theme="simple" style="width: 150px;" class="fieldText">Replicar en todos los hijos:</label>
						<input type="checkbox" id="check1" name="replicarHijosServicios" value="true" id="replicarHijosServicios" onclick="selectOnlyThisService(this.id)">						
					</p>
					<p class="criteria">
						<label theme="simple" style="width: 150px;" class="fieldText">Seleccionar hijos:</label>
						<span id="checkSeleccionarServiciosHijos">
						<input type="checkbox" id="check2" name="seleccionarHijosServicios" value="true" id="seleccionarHijosServicios" onclick="selectOnlyThisService(this.id);">
						</span>				
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
					datosServicios[0][3].value=true;
				}else datosServicios[0][3].value=false;
				for (i = 0; lcheck = listaChecks[i]; i++) {
					if (lcheck.checked) {
						enable = true;
						datosServicios[i][3].value=true;
					}else datosServicios[i][3].value=false;
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
				validate="false" name="formDeleteOrganismoServicioSelected" method="POST">

				<table id="idTablaServicios" cellspacing="0" cellpadding="0" border="0"
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

					<tbody id="bodyTablaServicios">
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
			</s:form>

		</div>
	</div>
	</s:if>
<s:if test="%{!comboOrganismosHijos.isEmpty()}">
<div class="editContainer">
		<div class="nameDescription">
			<label>Servidores/Proveedores</label>
		</div>
		<div class="editContent">
			<form id="formaddServidorOrganismo" theme="simple" validate="false"
 				name="formaddServidorOrganismo" action="javascript:insertarNuevoServidor()"> 
				<p class="criteria">
					
					<span style="width: 300px;"> <label style="width: 145px;"
						class="fieldText">Servidor / Proveedor:</label> <s:select
							id="servidorOrganismo.servidorId"
							name="servidorOrganismo.servidorId" emptyOption="true" 
							theme="simple" labelposition="left" title="Servidor / Proveedor"
							list="comboServidoresOrganismos" listKey="codigo"
							listValue="descripcion" cssClass="" cssStyle="width:138px"
							value="%{servidorOrganismo.servidorId}" disabled="false" /> <s:hidden
							name="servidorOrganismo" id="servidorOrganismo" value="%{pdpDiputacionesId}" />
							
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
				<p class="criteria">
					<label theme="simple" style="width: 150px;" class="fieldText">Replicar en todos los hijos:</label>
					<input type="checkbox" id="check3" name="replicarHijosServidores" value="true" id="replicarHijosServidores" onclick="selectOnlyThisServer(this.id)">
				</p>
				<p class="criteria">
					<label theme="simple" style="width: 150px;" class="fieldText">Seleccionar hijos:</label>
					<span id="checkSeleccionarServidorHijos">
						<input type="checkbox" id="check4" name="seleccionarHijosServidores" value="true" id="seleccionarHijosServidores" onclick="selectOnlyThisServer(this.id)">
					</span>				
				</p>
			</form>
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
					<tbody id="bodyTablaServidorProveedor">
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
			</s:form>

		</div>
	</div>
	</s:if>
	</s:form>

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

</div>
<script>
var datosServicios = new Array();
var datosServidor = new Array();
var serviciosHijosSeleccionados = new Array();
var servidorHijosSeleccionados = new Array();

var idServicios = new Array();
for(var i=0;i<datosServicios.length;i++){
	  idServicios.push(datosServicios[i][0].value);
	}


function insertarNuevoOrganismo() {
	loadServicios();
}

function insertarNuevoServidor() {

	if(formaddServidorOrganismo.elements[3].value == ""){
		alert("Falta la contraseña del proveedor");
		return;
		}	
	
	var idServidor = document.getElementById("servidorOrganismo.servidorId").value; //Id del servidor
	var nombreServidor = document.getElementById("servidorOrganismo.servidorId").selectedOptions[0].text;
	var headerSMS = document.getElementById("servidorOrganismo.headerSMS").value;
	var proveedorUsuario = document.getElementById("servidorOrganismo.proveedorUsuarioSMS").value;
	var proveedorContrasena = document.getElementById("servidorOrganismo.proveedorPasswordSMS").value;

	var bodyTabla = '<tbody>'
	+'<tr class>'
		+'<td class="darkTD TH15"></td>'
		+'<td><label>'+nombreServidor+'</label></td>'
		+'<td><label></label></td>'
		+'<td id="aunaValue1"><label>'+headerSMS+'</label></td>'
		+'<td id="auneValue1"><label>'+proveedorUsuario+'</label></td>'
		+'<td class="buttons"><span class="delete"> <a class="btnDelete" onclick="return confirmDelete();" href="javascript:eliminarServidor()"></a>'
		+'</span></td>'
	+'</tr>'	
	+'</tbody>';
	document.getElementById("bodyTablaServidorProveedor").innerHTML = bodyTabla;
	datosServidor = idServidor+","+headerSMS+","+proveedorUsuario+","+proveedorContrasena;	
}

function eliminarServiciosCheckeds(){	

	if(confirmDeleteSelected()){
		for(var i=0;i<datosServicios.length;i++){
			 if(datosServicios[i][3].value==true){
					eliminarServicio(datosServicios[i][0].value);
					i--;
				 }
			}
		  var botonEliminarSeleccionados = document
			.getElementById('eliminaSeleccionadosOrganismosServicios');
	        botonEliminarSeleccionados.disabled = "disabled";
	        document.getElementById('checkAllSOO').checked = "";
	}
}

function eliminarServicio(idServicio){
	  for (var i=0; i<datosServicios.length; i++) {
	   		if(datosServicios[i][0].value == idServicio){
					datosServicios.splice(i,1);
					escribirTablaServicios();
		   		}   			
		}
	if(datosServicios.length == 0)
		if ( document.getElementById('eliminaSeleccionadosOrganismosServicios') !== null){
			var botonEliminarSeleccionados = document.getElementById('eliminaSeleccionadosOrganismosServicios');
	        botonEliminarSeleccionados.style.visibility="hidden"; 
	        botonEliminarSeleccionados.disabled = "disabled";
			}
		   
	}

function eliminarServidor(){		
	var bodyServidorVacio = '<tr><td colspan="6">No se ha configurado servidor para el servicio</td></tr>';
	document.getElementById("bodyTablaServidorProveedor").innerHTML = bodyServidorVacio;
	datosServidor = new Array();
}

function escribirTablaServicios(){
	if(document.getElementById("bodyTablaServicios").getElementsByTagName("td")[0].innerText == "No se ha configurado servidor para el organismo"){
		document.getElementById("bodyTablaServicios").innerHTML = "";	
		}	
	document.getElementById("bodyTablaServicios").innerHTML = "";
	  if(datosServicios.length == 0){
		  var trTabla = '<tr><td colspan="5">No se ha configurado servidor para el	organismo</td></tr>';
		  document.getElementById('checkAllSOO').style.visibility="hidden";
		  document.getElementById("bodyTablaServicios").innerHTML = trTabla;
		  return;
		  }
	  for (var i=0; i<datosServicios.length; i++) {		
		  var isChecked;
		  if(datosServicios[i][3].value) isChecked = "checked";
			  else isChecked="";
		  var trTabla = '<tr class>'
    		+'<td class="darkTD TH15"><input type="checkbox" onclick="checkBotonEliminarSeleccionados4()" id="checkDelListOrganismosServicios" name="checkDelListOrganismosServicios" '+isChecked+' value="'+datosServicios[i][0].value+'">'
    		+'<input type="hidden" id="__checkbox_checkDelListOrganismosServicios" name="__checkbox_checkDelListOrganismosServicios">'
    		+'</td><td><label>'+datosServicios[i][0].value+'</label></td><td><label>'+datosServicios[i][1].value+'</label></td><td><label>'+datosServicios[i][2].value+'</label></td><td class="buttons"><span class="edit">'    		 
    		+'<a class="btnDelete" title="Eliminar" onclick="return confirmDelete();" href="javascript:eliminarServicio('+datosServicios[i][0].value+')"></a></span></td></tr>';

		  document.getElementById("bodyTablaServicios").innerHTML = document.getElementById("bodyTablaServicios").innerHTML +trTabla;
		  
	  }			  
	  document.getElementById('checkAllSOO').style.visibility="";
	  var botonEliminarServicios = '<tfoot><tr><td colspan="4"><input type="button" value="Eliminar seleccionados" id="eliminaSeleccionadosOrganismosServicios" name="eliminaSeleccionadosOrganismosServicios" disabled="disabled" class="button" onclick="eliminarServiciosCheckeds()"></td><td></td></tr></tfoot>';
	  
	  if(document.getElementById("idTablaServicios").getElementsByTagName("tfoot")[0] == null){
		  document.getElementById("idTablaServicios").innerHTML = document.getElementById("idTablaServicios").innerHTML + botonEliminarServicios;
		  }
	  var botonEliminarSeleccionados = document.getElementById('eliminaSeleccionadosOrganismosServicios');
        botonEliminarSeleccionados.disabled = "disabled"; 
	  
}

function loadServicios() {
	
	if(document.getElementById("servicioOrganismos.servicioId").value == "") return;	
	
    for (var i=0; i<datosServicios.length; i++) {
   		if(datosServicios[i][0].value == document.getElementById("servicioOrganismos.servicioId").value){
			alert("El servicio ya esta añadido");
			return;
   		}
	}				

    var auxHtml = $('#addItemServicio').html();
	$('#addItemServicio').html("<p><img src=\"/sim/img/ajax-loader.gif\" width=\"auto\" id=\"addItemServicio\" height=\"auto\"/></p>");
		
	$.ajax({
        type: "POST",
        url: "ajaxLoadServiciosOrganismos.action",
        data: {idServicio:document.getElementById("servicioOrganismos.servicioId").value}, // serializes the form's elements.
        success: function(data)
        {
        	datos = data.items;
        	datos[3] = {value:false,text:"checked"};
	        datosServicios.push(datos);
	        escribirTablaServicios();	
	        $('#addItemServicio').html(auxHtml);
	        var botonEliminarSeleccionados = document.getElementById('eliminaSeleccionadosOrganismosServicios');
	        botonEliminarSeleccionados.style.visibility=""; 	      
        },
        error: function(data)
        {
     	   alert("error..."); 
        }
      });           
}

function selectOnlyThisService(id) {
	
	var idCheck = document.getElementById(id).checked;
	var idCheck2 = null;
	if(id=="check1"){
		idCheck2 = document.getElementById("check2");
		}
	else{
		idCheck2 = document.getElementById("check1");
		}
	
	if(idCheck) {
			idCheck2.checked = false;
		}
	if(document.getElementById('check2').checked == false){
			serviciosHijosSeleccionados = new Array();
			servidorHijosSeleccionados = new Array();
		}
	else{
			loadArbol();
		}
}

function selectOnlyThisServer(id) {
	var idCheck = document.getElementById(id).checked;
	var idCheck2 = null;
	if(id=="check3"){
		idCheck2 = document.getElementById("check4");
		}
	else{
		idCheck2 = document.getElementById("check3");
		}
	
	if(idCheck) {
			idCheck2.checked = false;
		}
	if(document.getElementById('check4').checked == false){
			servidorHijosSeleccionados = new Array();
		}
	else{
			loadArbolServidor();
		}
		
}

function formAlta(){	
		if(document.getElementById('check1').checked == true || document.getElementById('check2').checked == true){
			if(datosServicios.length == 0){
					alert("No ha añadido ningun servicio a la lista.")
					return false;
				}
			}
		if(datosServicios.length != 0){
			if(document.getElementById('check1').checked == false && document.getElementById('check2').checked == false){
					alert("Tiene servicios en la lista pero no ha seleccionado a los hijos");
					return false;
				}
			}
		if(serviciosHijosSeleccionados == "" && document.getElementById('check2').checked == true){
				alert("No ha seleccionado ningun organismo hijo para replicar los servicios");
				return false;
			}
		
		if(document.getElementById('check3').checked == true || document.getElementById('check4').checked == true){
			if(datosServidor.length == 0){
					alert("No ha añadido ningun servidor a la lista.");
					return false;
				}
		}
		if(datosServidor.length != 0){
			if(document.getElementById('check3').checked == false && document.getElementById('check4').checked == false){
					alert("Tiene un servidor en la lista pero no ha seleccionado a los hijos");
					return false;
				}
			}
		if(servidorHijosSeleccionados == "" && document.getElementById('check4').checked == true){
			alert("No ha seleccionado ningun organismo hijo para replicar el servidor");
			return false;
		}
			
		var idServicios = new Array();
		for(var i=0;i<datosServicios.length;i++){
			  idServicios.push(datosServicios[i][0].value);
			}		

		
		document.getElementById('datosServicios').value = idServicios;
		document.getElementById('datosServidor').value = datosServidor;	
		document.getElementById('serviciosHijosSeleccionados').value = serviciosHijosSeleccionados;
		document.getElementById('servidorHijosSeleccionados').value = servidorHijosSeleccionados;
		
		//No he podido anidar los forms, por eso antes de llegar al action guardo la informacion de los dos ultimos checks
		document.getElementById('replicarHijosServidores').value = document.getElementById('check3').checked; 			
		document.getElementById('seleccionarHijosServidores').value = document.getElementById('check4').checked;
		
		document.forms["frmEditOrganismo"].submit();			
	}

function loadArbol(enlace) {
	var idOrganismo = document.getElementById('pdpDiputacionesId').value;
	var auxHTML = $('span#checkSeleccionarServiciosHijos').html();
	$('span#checkSeleccionarServiciosHijos').html("<p><img src=\"/sim/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
	var url = "arbolPdpDiputacionesSeleccion.action?pdpDiputacionesId=" + idOrganismo + "&arbol=true";

	 var dialogoTree = $('#dialogTree');
	    dialogoTree.load(
        		url,
        		{
   	    			autoOpen: true,
   	    			width: 810,
   	    			modal: true,
   	    			show: 'blind',
   	    			hide: 'blind'
   	    		},
                function(responseText, textStatus, XMLHttpRequest) {
   	    			dialogoTree.dialog({
		           	 			autoOpen: true,
		           				width: 950,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           			});   	    			
   	    			$('span#checkSeleccionarServiciosHijos').html(auxHTML);
   	    			if(!responseText.includes("El pdp-diputacion seleccionado no tiene ningun organismo asociado")){
   	    				document.getElementById('check2').checked = true
   	   	    			}
   	    			
                }

        );
        return false;
	}
	
function loadArbolServidor(enlace) {
	var idOrganismo = document.getElementById('pdpDiputacionesId').value;
	var auxHTML = $('span#checkSeleccionarServidorHijos').html();
	$('span#checkSeleccionarServidorHijos').html("<p><img src=\"/sim/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
	var url = "arbolPdpDiputacionesSeleccionServidor.action?pdpDiputacionesId=" + idOrganismo + "&arbol=true";

	 var dialogoTree = $('#dialogTree');
	    dialogoTree.load(
        		url,
        		{
   	    			autoOpen: true,
   	    			width: 810,
   	    			modal: true,
   	    			show: 'blind',
   	    			hide: 'blind'
   	    		},
                function(responseText, textStatus, XMLHttpRequest) {
   	    			dialogoTree.dialog({
		           	 			autoOpen: true,
		           				width: 950,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           			});   	    			
   	    			$('span#checkSeleccionarServidorHijos').html(auxHTML);
   	    			if(!responseText.includes("El pdp-diputacion seleccionado no tiene ningun organismo asociado")){
   	    				document.getElementById('check4').checked = true
   	   	    			}
                }

        );
        return false;	    
	           
	}
function seleccion(idOrganismoHijoSelec){
	if(document.getElementById(idOrganismoHijoSelec).style.backgroundColor == "" || document.getElementById(idOrganismoHijoSelec).style.backgroundColor == 'white'){
			document.getElementById(idOrganismoHijoSelec).style.backgroundColor = '#dae5e9';		
			serviciosHijosSeleccionados.push(idOrganismoHijoSelec);
		}
	else {
			document.getElementById(idOrganismoHijoSelec).style.backgroundColor = 'white';
			if(document.getElementById('check2').checked == true){
				for (var i = 0; i < serviciosHijosSeleccionados.length; i++) {
			        if (serviciosHijosSeleccionados[i] === idOrganismoHijoSelec) {
			        	serviciosHijosSeleccionados.splice(i, 1);
			            i--;
			        }
				}
			}			
		}
}
function seleccionServidor(idOrganismoHijoSelec){
	if(document.getElementById(idOrganismoHijoSelec).style.backgroundColor == "" || document.getElementById(idOrganismoHijoSelec).style.backgroundColor == 'white'){
			document.getElementById(idOrganismoHijoSelec).style.backgroundColor = '#dae5e9';		
			servidorHijosSeleccionados.push(idOrganismoHijoSelec);
		}
	else {
			document.getElementById(idOrganismoHijoSelec).style.backgroundColor = 'white';
			if(document.getElementById('check2').checked == true){
				for (var i = 0; i < servidorHijosSeleccionados.length; i++) {
			        if (servidorHijosSeleccionados[i] === idOrganismoHijoSelec) {
			        	servidorHijosSeleccionados.splice(i, 1);
			            i--;
			        }
				}
			}			
		}
}
</script>