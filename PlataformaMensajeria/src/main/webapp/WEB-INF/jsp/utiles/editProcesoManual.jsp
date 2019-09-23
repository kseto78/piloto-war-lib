<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
		<s:form id="frmEjecucionJob" method="POST" action="ejecutarJobManual" validate="false" theme="simple" cssClass="">
		<s:hidden theme="simple" id="procesoManual.procesosManualesId"
			name="procesoManual.procesosManualesId" value="%{procesoManual.procesosManualesId}" />
			<s:hidden id = "procesoManual.parametro2" name="procesoManual.parametro2" />
			<s:hidden id = "tieneServicios" name="tieneServicios" value="%{tieneServicios}" />	
			<s:hidden id = "jobBean.parametro1" name="jobBean.parametro1" value="" />
			<s:hidden id = "datosServicios" name="datosServicios" value="" />
				
		<h3 class="pageNameButtons">
			<span class="floatRight"> 
			<s:submit type="button" theme="simple" class="button" value="%{getText('buttons.text.save')}" action="guardarProcesoManual" />			
			<input type="button"
				onclick="javascript:location.href='${volver}'" class="button"
				value="Volver">	
				
			</span> <label>EDICIÓN PLANIFICACIÓN MANUAL</label>
		</h3>
<%-- 		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp"%> --%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/warningForm.jsp"%>
		<%@include
			file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp"%>
				<sj:dialog  id="dialogMensajesReenvios" title="LISTA MENSAJES REENVIO" 	href="viewMensajeReenvios.action" 
				cssStyle="display:none" autoOpen="false" modal="true"
				width="950"></sj:dialog>
		
		<div class="editContainer">
			<div class="nameDescription">
				<label>Parametros Ejecución Manual</label>
			</div>
			<div class="editContent">
				<p class="criteria">
							<label style="width: 120px;" class="fieldText">Nombre (*):</label>
							<s:textfield name="procesoManual.nombre" value="%{procesoManual.nombre}" 
								id="procesoManual.nombre" theme="simple" labelposition="left"
								size="70" maxlength="255" cssClass="input_tablas_registro" />
				</p>
				<p class="criteria" style="height:24px">
					<label style="width: 120px;" class="fieldText" >Job :</label>
					<s:select id="procesoManual.procesoId" name="procesoManual.procesoId"
						emptyOption="false" theme="simple" labelposition="left"
						list="combolistaJobs" listKey="codigo" listValue="descripcion"
						cssClass="" value="%{procesoManual.procesoId}" disabled="false"
						cssStyle="width:150px" disable="false" />
				</p>
				<p class="criteria">
						<span style="width: 120px;height:26px"> <label class="fieldText">Busqueda de mensajes:</label>
						</span> 
						<span class="input_tablas_registro" style="border:none">
							<input type="radio" id="check.diasAtras" name="proceso.tipo" value="dias" checked="true"  onclick="checkBusqueda(this);"/> Dias atras
							<input type="radio" id="check.fecha" name="proceso.tipo" value="fecha"  onclick="checkBusqueda(this);"/> Fecha
						</span>
				</p>
				<p class="criteria" style="height:24px" id="diasEjecucion">
					<label style="width: 120px;" class="fieldText" >Numero de días busqueda:</label>
					<s:select id="procesoManual.parametro1" name="procesoManual.parametro1"
						emptyOption="false" theme="simple" labelposition="left"
						list="comboNumeroDias" listKey="codigo" listValue="descripcion"
						cssClass="" value="%{procesoManual.parametro1}" disabled="false"
						disable="false" />
				</p>
						
				<p class="criteria" id="fechasEjecucion">
		 		  <span style="width: 275px;">		 		  	
                     <label style="width: 120px;" class="fieldText">Fecha Inicio:</label>
                      <s:textfield
 								name="jobBean.fecha" value="%{jobBean.fecha}" id="jobBean.fecha" 
 								theme="simple"  style="width:60px;"
 								size="10" maxlength="10"
 								cssClass="datepickerEstadisticas" autocomplete="off">
 								<s:param name="value"> 
     							<s:date name="jobBean.fecha" format="dd/MM/yyyy"/>
   						</s:param> 
 						</s:textfield> 
           	      	</span>
           	      	<span>
	                     <label style="width: 100px;" class="fieldText">Fecha Fin:</label>
	                       <s:textfield
									name="jobBean.fechaFin" value="%{jobBean.fechaFin}" id="jobBean.fechaFin"
									theme="simple"  style="width:60px;"
									size="10" maxlength="10"
									cssClass="datepickerEstadisticas" autocomplete="off">
									<s:param name="value">
	    							<s:date name="jobBean.fechaFin" format="dd/MM/yyyy"/>
	  						</s:param>
							</s:textfield>
                     </span>           	      	   
      	      	</p>
          	     </s:form>
          	     
          	     
				<p class="criteria">
					<span style="width: 350px;"> <label style="width: 120px;"
						class="fieldText">Servicios Incluidos: </label> <s:select
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
								<td colspan="5">No se ha agregado ningun servicio para la ejecucion manual del job</td>
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
			<p class="criteria">
					<span style="width: 350px;"> <label style="width: 120px;"
						class="fieldText">Servicios Excluidos: </label>
					</span>					
				</p>
			<table id="idTablaServiciosAutomatica" cellspacing="0" cellpadding="0" border="0">
					<thead>
						<tr>
							<th class=""></th>
							<th class="TH100">Id Servicio</th>
							<th class="TH150">Nombre Servicio</th>
							<th class="TH280">Descripción Servicio</th>
							<th class="TH45 separator"></th>
						</tr>
					</thead>
					<tbody id="bodyTablaServiciosAutomatica">
						<s:iterator value="%{listaServicioOrganismos}" var="organismoServ"
							status="organismoServStatus">
							<tr
								class="<s:if test='#organismoServStatus.odd == true '></s:if><s:else>odd</s:else>">
								<td class="darkTD TH15"><input type="checkbox"
									onclick="checkBotonEliminarSeleccionadosAutomatica()"
									id="checkDelListOrganismosServiciosAutomatica"
									name="checkDelListOrganismosServiciosAutomatica"
									value="${organismoServ.servicioId}" /> <input type="hidden"
									id="__checkbox_checkDelListOrganismosServiciosAutomatica"
									name="__checkbox_checkDelListOrganismosServiciosAutomatica" /></td>
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
								<td colspan="5">No se ha agregado ningun servicio para la ejecucion automatica del job</td>
							</tr>						
						</s:if>
					</tbody>
				</table>
			<p class="criteria" style="height:24px">
					<label style="width: 120px;" class="fieldText">Ver mensajes a reenviar</label>
					<span id="ajaxloaderManual">
						<span class="btnMagGlass gestionEnvio_link" title="Ver Detalle" onclick="verMensajesManual('viewMensajeReenvios.action');"></span>
					</span>
			</p>
			<p class="criteria" style="float:right">			
				<s:submit type="button" theme="simple" class="button" value="Ejecutar" onclick="return confirmEjecutarManual()"/>
			</p>
		
				
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
							theme="simple" id="procesoManual.creadoPor"
							name="procesoManual.creadoPor" value="%{procesoManual.creadoPor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Creación:</label> <strong><s:label theme="simple"
							id="procesoManual.fechaCreacion" name="procesoManual.fechaCreacion"
							value="%{procesoManual.fechaCreacion}" /></strong>
				</span> 
			</p>			
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 140px;"
					class="fieldText">Último Modificador:</label> <strong><s:label
							theme="simple" id="procesoManual.modificadoPor"
							name="procesoManual.modificadoPor" value="%{procesoManual.modificadoPor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Última Modificación:</label> <strong><s:label theme="simple"
							id="procesoManual.fechaModificacion"
							name="procesoManual.fechaModificacion"
							value="%{procesoManual.fechaModificacion}" /></strong>
				</span>				
			</p>
		</div>
	</div>

</div>

<script type="text/javascript">

function confirmEjecutarManual(){
	var r = confirm('¿Desea ejecutar la planificación manual?');
	if(r){return true;
	}else{return false;
	}
}

$('#frmEjecucionJob').submit(function() {	
		var idServicios = new Array();
		for(var i=0;i<datosServicios.length;i++){
			  idServicios.push(datosServicios[i][0].value);
			}
		
		document.getElementById('datosServicios').value = idServicios;
		document.getElementById('procesoManual.parametro2').value = idServicios;

		if(document.getElementById("check.diasAtras").checked){
			document.getElementById('jobBean.parametro1').value = document.getElementById('procesoManual.parametro1').value
			document.getElementById('jobBean.fecha').value = "";
			document.getElementById('jobBean.fechaFin').value = "";
		}else{
			document.getElementById('jobBean.parametro1').value = "";
			}
  		 return true; // return false to cancel form action
})

$(document).ready ( function(){
	document.getElementById('fechasEjecucion').style.display="none";
	
	const infoServidores = "${tieneServicios}";
	if (!!infoServidores){
		var arrayInfoServidores = infoServidores.split(';');
		for(var i=0;i<arrayInfoServidores.length;i++){		
			var infoServidor = arrayInfoServidores[i].split(',');			
			var idActual = infoServidor[0];
			var nombreActual = infoServidor[1];
			var desActual = infoServidor[2];
			var servActual = [{value:idActual},{value:nombreActual},{value:desActual},{value:false}];				 
			datosServicios.push(servActual);
		}
		escribirTablaServicios();
	}

	const infoServidoresAutomatica = "${tieneServiciosAutomatica}";
	if (!!infoServidoresAutomatica){
		var arrayInfoServidores = infoServidoresAutomatica.split(';');
		for(var i=0;i<arrayInfoServidores.length;i++){		
			var infoServidor = arrayInfoServidores[i].split(',');			
			var idActual = infoServidor[0];
			var nombreActual = infoServidor[1];
			var desActual = infoServidor[2];
			var servActual = [{value:idActual},{value:nombreActual},{value:desActual},{value:false}];				 
			datosServiciosAutomatica.push(servActual);
		}
		escribirTablaServiciosAutomatica();
	}
	
	
});

function escribirTablaServiciosAutomatica(){
	if(document.getElementById("bodyTablaServiciosAutomatica").getElementsByTagName("td")[0].innerText == "No se ha agregado ningun servicio para la ejecucion automatica del job"){
		document.getElementById("bodyTablaServiciosAutomatica").innerHTML = "";	
		}	
	document.getElementById("bodyTablaServiciosAutomatica").innerHTML = "";
	  if(datosServiciosAutomatica.length == 0){
		  var trTabla = '<tr><td colspan="5">No se ha agregado ningun servicio para la ejecución automatica del job</td></tr>';
		  document.getElementById('checkAllSOOAutomatica').style.visibility="hidden";
		  document.getElementById("bodyTablaServiciosAutomatica").innerHTML = trTabla;
		  return;
		  }
	  for (var i=0; i<datosServiciosAutomatica.length; i++) {		
		  var isChecked;
		  if(datosServiciosAutomatica[i][3].value) isChecked = "checked";
			  else isChecked="";
		  var trTabla = '<tr class>'
    		+'<td class="darkTD TH15">'
    		+'<input type="hidden" id="__checkbox_checkDelListOrganismosServicios" name="__checkbox_checkDelListOrganismosServicios">'
    		+'</td><td><label>'+datosServiciosAutomatica[i][0].value+'</label></td><td><label>'+datosServiciosAutomatica[i][1].value+'</label></td><td><label>'+datosServiciosAutomatica[i][2].value+'</label></td><td class="buttons"><span class="edit">'	        	 
    		+ '</span></td></tr>';

		  document.getElementById("bodyTablaServiciosAutomatica").innerHTML = document.getElementById("bodyTablaServiciosAutomatica").innerHTML +trTabla;
		  
	  } 
	  		  
}


function verMensajesPaginar(url){
	document.getElementById('loading').style.visibility="";
	$('td').find('a').each(function(){
		 $(this).addClass('disabled-link');
		});

		$('.disabled-link').on('click', false);
	var dialogoEnvios = $('#dialogMensajesReenvios');
    dialogoEnvios.load(
    		url,
    		{
	    			
	    		},
            function(responseText, textStatus, XMLHttpRequest) {
	    			dialogoEnvios.dialog({
	           	 			autoOpen: true,
	           				width: 950,
	           				modal: true,
	           				show: 'blind',
	           				hide: 'blind',
	           			});	    			       	    		
            }

    );
    return false;	
}
function verMensajesManual(url){
	
	var auxHTML = $('span#ajaxloaderManual').html();
	$('span#ajaxloaderManual').html("<p><img src=\"/sim/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");

	var idServicios = new Array();
	for(var i=0;i<datosServicios.length;i++){
		  idServicios.push(datosServicios[i][0].value);
		}
	

	var par1 = document.getElementById("procesoManual.parametro1").value;
	var fechInicio = document.getElementById("jobBean.fecha").value;
	var fechFin = document.getElementById("jobBean.fechaFin").value;
	var urlRestante;
	if(document.getElementById("check.diasAtras").checked){
			urlRestante = '&jobBean.parametro1='+par1; 
		}else{
			 urlRestante = '&jobBean.fecha='+fechInicio+'&jobBean.fechaFin='+fechFin;	
			}
	if(!url.includes('datosServicios=')){
			url = url + '?datosServicios=' + idServicios + urlRestante;
		}

	var servExcluidos = new Array();
	for(var i=0;i<datosServiciosAutomatica.length;i++){
		servExcluidos.push(datosServiciosAutomatica[i][0].value);
		}

	if(!url.includes('datosServiciosExcluidos=')){
		url = url + '&datosServiciosExcluidos=' + servExcluidos ;
	}
	
	
 	var dialogoEnvios = $('#dialogMensajesReenvios');
    dialogoEnvios.load(
    		url,
    		{
	    			autoOpen: true,
	    			width: 510,
	    			modal: true,
	    			show: 'blind',
	    			hide: 'blind'
	    		},
            function(responseText, textStatus, XMLHttpRequest) {
	    			dialogoEnvios.dialog({
	           	 			autoOpen: true,
	           				width: 950,
	           				modal: true,
	           				show: 'blind',
	           				hide: 'blind',
	           			});
	    			$('span#ajaxloaderManual').html(auxHTML);        	    		
            }

    );
    return false;	
}


function verMensajes(url){
	
	var auxHTML = $('span#ajaxloader').html();
	$('span#ajaxloader').html("<p><img src=\"/sim/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
	var valor1 = document.getElementById('proceso.parametro1').value;
	url = url + '?proceso.parametro1=' + valor1;
 	var dialogoEnvios = $('#dialogMensajesReenvios');
    dialogoEnvios.load(
    		url,
    		{
	    			autoOpen: true,
	    			width: 510,
	    			modal: true,
	    			show: 'blind',
	    			hide: 'blind'
	    		},
            function(responseText, textStatus, XMLHttpRequest) {
	    			dialogoEnvios.dialog({
	           	 			autoOpen: true,
	           				width: 950,
	           				modal: true,
	           				show: 'blind',
	           				hide: 'blind',
	           			});
	    			$('span#ajaxloader').html(auxHTML);        	    		
            }

    );
    return false;	
}
var datosServicios = new Array();
var datosServiciosAutomatica = new Array();

$(document).ready(function() {
  $('.timepicker').timepicker({
         timeFormat: 'HH:mm',         
       });
  const procesoTipo= "${proceso.tipo}";
  if(procesoTipo == "semanal"){
	  document.getElementById('proceso.tipoSemanal').checked = true;
	  document.getElementById('diasSemana').style.display="";
  }
});


// location.href='ejecutarJob.action?procesosId=${procesosId}'
	function ejecutarJob() {
		var idServicios = new Array();
		for(var i=0;i<datosServicios.length;i++){
			  idServicios.push(datosServicios[i][0].value);
			}
		
		document.getElementById('datosServicios').value = idServicios;              
	    document.frmEjecucionJob.action="ejecutarJob.action";
	    document.frmEjecucionJob.submit();
	}

	function guardarJob() {              
// 	    document.frmEjecucionJob.action="ejecutarJob.action";
		$('#procesoManual.parametro1').val(document.getElementById("procesoManual.parametro1").value);
		document.getElementById('proceso.parametro2').value = idServicios;

	    document.frmGuardarJob.submit();
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
	function escribirTablaServicios(){
		if(document.getElementById("bodyTablaServicios").getElementsByTagName("td")[0].innerText == "No se ha agregado ningun servicio para la ejecucion manual del job"){
			document.getElementById("bodyTablaServicios").innerHTML = "";	
			}	
		document.getElementById("bodyTablaServicios").innerHTML = "";
		  if(datosServicios.length == 0){
			  var trTabla = '<tr><td colspan="5">No se ha agregado ningun servicio para la ejecución manual del job</td></tr>';
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
	
	function handleClick(valor) {		
		if(valor.value == "semanal"){
				document.getElementById('diasSemana').style.display="";
			}
		else{
			document.getElementById('diasSemana').style.display="none";
			}
	}
	function checkBusqueda(valor) {		
		if(valor.value == "dias"){
				document.getElementById('fechasEjecucion').style.display="none";
				document.getElementById('diasEjecucion').style.display="";
			}
		else{
				document.getElementById('diasEjecucion').style.display="none";
				document.getElementById('fechasEjecucion').style.display="";
			}	 
	}
</script>