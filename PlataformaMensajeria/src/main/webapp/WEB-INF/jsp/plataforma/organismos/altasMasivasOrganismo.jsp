<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
	<s:form id="frmAltaMasivaOrganismo" method="POST" enctype="multipart/form-data" action="altasMasivasOrganismos"
		validate="false" theme="simple" cssClass="">
		<s:hidden id = "datosServicios" name="datosServicios" />
		<s:hidden id = "datosServidor" name="datosServidor" />
		<s:hidden id = "formatoArchivoExcel" name="formatoArchivoExcel" />
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:submit theme="simple"
					value="Aceptar" cssClass="button"
					alt="Aceptar" onclick="return formAlta();"/>
			</span> <label>Altas Masivas </label>
		</h3>
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp"%>
		<%@include
			file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp"%>	
		<div class="editContainer">
			<div class="nameDescription">
				<label>Carga de fichero</label>			
			</div>			<div class="editContent">	

				<div style="height:0px;overflow:hidden">
   							<input type="file" id="archivoExcel2" name="fileInput" />
						</div>		
					<p class="criteria">
						<span> 
							<label class="fieldText" style="width: 200px">Selecciona el fichero a importar(*): </label>
							<input type="file" id="archivoExcel" name="archivoExcel" style="display: none" onChange="Handlechange();"/>
						<img src="/sim/img/clipAltas.png" value="Click to select file" id="fakeBrowse" onclick="HandleBrowseClick();" height="18" width="18"/>
						<label class="fieldText" id="filename" name="filename" style="background:none;float: none;padding-left: 0px;" onclick="HandleBrowseClick();">Ningún archivo seleccionado</label>					
						</span>						
					</p>
				
				<p class="criteria">
					<span style="width: 340px;"> <label class="fieldText">Si desea descargar la plantilla pulse <a href="plantilla.xls">aquí</a></label> <strong><s:label
								theme="simple" /></strong>
					</span>
				</p>
				<p class="criteria">				
					<span style="width: 350px;"> <label style="width: 120px;"
						class="fieldText">PdP-diputación: </label> <s:select
							id="organismo.idPdpDiputaciones"
							name="organismo.idPdpDiputaciones" emptyOption="true"
							theme="simple" labelposition="left" title="PdP-diputaciones"
							list="comboOrganismosPdp" listKey="codigo"
							listValue="descripcion" cssClass="" cssStyle="width:138px"
							value="%{organismo.idPdpDiputaciones}" disabled="false" />
					</span>
										
				</p>
			</div>
	</div>

	</s:form>

	<div class="editContainer">
		<div class="nameDescription">
			<label>Servicios</label>			
		</div>
		<div class="editContent">
				<p class="criteria">
					<span style="width: 350px;"> <label style="width: 120px;"
						class="fieldText">Servicios(*): </label> <s:select
							id="servicioOrganismos.servicioId"
							name="servicioOrganismos.servicioId" emptyOption="true"
							theme="simple" labelposition="left" title="Organismos"
							list="comboServicioOrganismos" listKey="codigo"
							listValue="descripcion" cssClass="" cssStyle="width:138px"
							value="%{servicioOrganismos.servicioId}" disabled="false" />
					</span>
					<span id="addItemServicio" style="display: inline-block;float: right;">
						<a class="addLink" onclick="return loadServicios()" name="addItem">Añadir Item</a>
					</span>
				</p>
		
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

			<s:form id="formDeleteOrganismoServicioSelected" theme="simple"
				validate="false" name="formDeleteOrganismoServicioSelected" method="POST">

				<table id="idTablaServicios" cellspacing="0" cellpadding="0" border="0">
					<thead>
						<tr>
							<th class=""><input type="checkbox" id="checkAllSOO"
								theme="simple" onclick="selectAllSOO(this)" style="visibility: hidden;"/></th>
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
									value="${organismoServ.servicioId}" /> <input type="hidden"
									id="__checkbox_checkDelListOrganismosServicios"
									name="__checkbox_checkDelListOrganismosServicios" /></td>
								<td><s:label value="%{servicioId}" /></td>
								<td><s:label value="%{nombre}" /></td>
								<td><s:label value="%{descripcion}" /></td>
								<td class="buttons"><span class="delete"> <a class="btnDelete"
										title="Eliminar" onclick="return confirmDelete();"
										href="javascript:eliminarServicio(${organismoServ.servicioId})"></a>										
								</span></td>
								
							</tr>							
						</s:iterator>
						<s:if test="%{listaServicioOrganismos == null}">
							<tr>
								<td colspan="5">No se ha configurado ningun servicio</td>
							</tr>
							<script>
								document.getElementById('checkAllSOO').style.visibility = "hidden";
							</script>
						</s:if>
						<s:else>
							<tr>
							<tfoot>
								<td colspan="4"><input type="button" value="Eliminar seleccionados" 
								id="eliminaSeleccionadosOrganismosServicios" name="eliminaSeleccionadosOrganismosServicios" 
								class="button" disabled="disabled" onclick="eliminarServiciosCheckeds()">						
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
			<label>Servidores/Proveedores</label>			
		</div>
		<div class="editContent">
			<form id="formaddServidorOrganismo" theme="simple" validate="false"
				name="formaddServidorOrganismo" method="POST"
				action="javascript:insertarNuevoServidor()">
				<p class="criteria">
					<span style="width: 300px;"> <label style="width: 145px;"
						class="fieldText">Servidor / Proveedor(*):</label> <s:select
							id="servidorOrganismo.servidorId"
							name="servidorOrganismo.servidorId" emptyOption="true" onchange="checkTipoHeader(this)"
							theme="simple" labelposition="left" title="Servidor / Proveedor"
							list="comboServidoresOrganismos" listKey="codigo"
							listValue="descripcion" cssClass="" cssStyle="width:138px"
							value="%{servidorOrganismo.servidorId}" disabled="false" />
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
                       for (var i = 0; lcheck = listaChecks[i]; i++) {
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
                       
                       for (var i = 0; lcheck = listaChecks[i]; i++) {
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
							<th class=""></th>
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
							<tr	class>
								<td class="darkTD TH15"></td>
								<td><s:label value="%{nombreServidor}" /></td>
								<td><s:label value="%{numIntentos}" /></td>
								<td id="aunaValue1"><s:label value="%{headerSMS}"/></td>
								<td id="auneValue1"><s:label value="%{proveedorUsuarioSMS}"/></td>
								<td class="buttons"><span class="delete"> <a
										class="btnDelete" onclick="return confirmDelete();"
										href="javascript:eliminarServidor()"></a>
								</span></td>
							</tr>
							<s:hidden id = "proveedorPasswordSMS" name="proveedorPasswordSMS" value="%{proveedorPasswordSMS}" />
							<s:hidden id = "servidorId" name="servidorProveedorId" value="%{servidorId}" />
						</s:iterator>
						<s:if test="%{listaServidoresOrganismos == null}">
							<tr>
								
								<td colspan="6" >No se ha configurado servidor </td>
							</tr>
							<script>document.getElementById('checkAllS').style.visibility="hidden";</script>
						</s:if>						
					</tbody>
				</table>				
			</s:form>
		</div>
	</div>

	<script>
	function HandleBrowseClick()
		{
		    var fileinput = document.getElementById("archivoExcel");
		    fileinput.click();
		}

		function Handlechange()
		{
		    var fileinput = document.getElementById("archivoExcel");
		    var textinput = document.getElementById("filename");
		    if (fileinput.value != ""){
		    	textinput.innerHTML = fileinput.value.split(/(\\|\/)/g).pop();
			    }
		    else{
			    textinput.innerHTML = "Ningún archivo seleccionado";
			    }
		   	
		}
	
	$('#archivoExcel').bind('change', function() {		
		  if (this.files[0].size/1024 > 1024 ){
			  alert(" El fichero no puede ocupar mas de 1 MB");
			  archivoExcel.value = null;
			  document.getElementsByName('filename')[0].innerText = "Ningún archivo seleccionado";
			  }
		  if(this.files[0].name.split('.').pop() != "xls" && this.files[0].name.split('.').pop() != "xlsx"){
			  alert("El tipo de fichero no es de tipo excel");
			  archivoExcel.value = null;
			  document.getElementsByName('filename')[0].innerText = "Ningún archivo seleccionado";
			  }
		});
	
	var datosServicios = new Array();	
	var datosServidor = new Array();

		function formAlta(){
			var nombreArch = document.getElementById("archivoExcel").value;
			formatoArchivoExcel.value = nombreArch.split(".").pop(); 
			var idServicios = new Array();
			for(var i=0;i<datosServicios.length;i++){
				  idServicios.push(datosServicios[i][0].value);
				}

			document.getElementById('datosServicios').value = idServicios;
			document.getElementById('datosServidor').value = datosServidor;			
			document.forms["frmAltaMasivaOrganismo"].submit();			
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

			datosServidor = idServidor+","+headerSMS+","+proveedorUsuario+","+proveedorContrasena+","+nombreServidor;	
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
				var bodyServidorVacio = '<tr><td colspan="6">No se ha configurado ningun servidor</td></tr>';
				document.getElementById("bodyTablaServidorProveedor").innerHTML = bodyServidorVacio;
				datosServidor = new Array();			
		}
		
		function escribirTablaServicios(){
			if(document.getElementById("bodyTablaServicios").getElementsByTagName("td")[0].innerText == "No se ha configurado ningun servidor"){
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

		//Si se da un error en la alta, necesitamos poner la informacion de los servicios y servidores en sus variables correspondientes		
		$(document).ready ( function(){
			//Comprobamos si la tabla servicios  contiene datos para añadirlos a la variable global que contiene los servicios
			if(document.getElementById("bodyTablaServicios").rows[0].cells.length > 1){ 
				var numServicios = document.getElementById("bodyTablaServicios").rows.length;
				for(var i=0;i<numServicios-1;i++){ //Quitamos 1 por la fila que contiene el boton de "Eliminar Seleccionados"
					var idActual = document.getElementById("bodyTablaServicios").rows[i].cells[1].textContent;
					var nombreActual = document.getElementById("bodyTablaServicios").rows[i].cells[2].textContent;
					var desActual = document.getElementById("bodyTablaServicios").rows[i].cells[3].textContent;
					var servActual = [{value:idActual},{value:nombreActual},{value:desActual},{value:false}];
					document.getElementById("checkAllSOO").style = ""; 
					datosServicios.push(servActual);
					}
				}
			//Comprobamos si la tabla servidor contiene datos para añadirlos a la variable global que contiene los servidores	
			if(document.getElementById("bodyTablaServidorProveedor").rows[0].cells.length > 1){
					var idServidor = document.getElementById("servidorId").value;
					var nombreServidor = document.getElementById("bodyTablaServidorProveedor").rows[0].cells[1].textContent;
					var headerSMS = document.getElementById("bodyTablaServidorProveedor").rows[0].cells[3].textContent;
					var usuarioProveedor = document.getElementById("bodyTablaServidorProveedor").rows[0].cells[4].textContent;
					var passProveedor = document.getElementById("proveedorPasswordSMS").value;
					datosServidor = idServidor+","+headerSMS+","+usuarioProveedor+","+passProveedor+","+nombreServidor;
				}					
			});
	</script>
</div>