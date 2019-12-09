<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR,ROL_PROPIETARIO,ROL_CAID">
<%-- <plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR"> --%>
	<script>
 		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<script>
function submitForm(){
	//en sms confirmar que tiene costes adicionales
	if(document.getElementById('cid').value == 2){
		var r = confirm("El envio de SMS puede incurrir costes asociados a la aplicacion seleccionada ¿Quiere continuar?");
		 if (r == true) {
				 var form = document.getElementById('frmEnvioMensajesAplicacion');
				form.submit();
		    }			
		}else{
			var form = document.getElementById('frmEnvioMensajesAplicacion');
			form.submit();
		}
	}
function setValue(obj){
	var value = obj.value;
	document.getElementById('envioMensajesAplicacionBean.servicioId').value=value;
}

function setValueCanal(obj){
	var value = obj.value;
	document.getElementById('envioMensajesAplicacionBean.canalId').value=value;
}
var datos;
function makeRequest(){
	document.getElementById('envioMensajesAplicacionBean.servicioId').value='';
	  $('#sid option').each(function() {
		        $(this).remove();
		});
	$.ajax({
        type: "POST",
        url: "ajaxLoadServiciosEnvioMensajes.action",
        data: {idAplicacion:document.getElementById('envioMensajesAplicacionBean.aplicacionId').value}, // serializes the form's elements.
        success: function(data)
        {
     	  datos = data.items;     	  
     	  $('#sid').append($('<option>', { 
     	        value: '',
     	        text : 'Todos' 
     	    }));
     	 
     	 for (var i=0; i<datos.length; i++) {
	  		$('#sid').append($('<option>', { 
		        value: datos[i].value,
		        text : datos[i].text 
		    }));   
     		 i++;
  			}
     	 
        },
        error: function(data)
        {
     	   alert("error..."); 
        }
      });
}

function makeRequestCanal(){

	if(datos == null) return;
			
	var servicioIndex = document.getElementById("sid").selectedIndex;
	var canalIndex = document.getElementById("cid").selectedIndex;
	
	if(servicioIndex == 0){
			$('#sid option').each(function() {
		        $(this).remove();
		});
			$('#sid').append($('<option>', { 
     	        value: '',
     	        text : 'Todos' 
     	    }));
     	 
     	 for (var i=0; i<datos.length; i++) {
	  		$('#sid').append($('<option>', { 
		        value: datos[i].value,
		        text : datos[i].text 
		    }));   
     		 i++;
  			}
     	$('#cid option').each(function() {
            $(this).remove();
    	});

    	 $('#cid').append($('<option>', { 
    	        value: '',
    	        text : 'Todos' 
    	    }));
    	 for (var i=0; i<canalesTotales.length; i++) {
 	  		$('#cid').append($('<option>', { 
 		        value: canalesTotales[i].value,
 		        text : canalesTotales[i].text 
 		    }));
      		 
   			}
			
			return;
		}

	var servicioId = document.getElementById('sid').value;
	var canalId = document.getElementById('cid').value;	
	
		
		$('#cid option').each(function() {
	        $(this).remove();
		});
	
		 $('#cid').append($('<option>', { 
		        value: '',
		        text : 'Todos' 
		    }));

		 for (var i=0; i<datos.length; i++) {
			 if(servicioId == datos[i].value ){
				 $('#cid').append($('<option>', { 
				        value: datos[i+1].value,
				        text : datos[i+1].text 
				    }));
				 }    
	  		 i++;
				}
		 if(canalIndex != 0) $("#cid").val(canalId);
	
}

var canalesTotales;
function makeRequestCanalTodos(){
	
	var aplicacionId = document.getElementById('envioMensajesAplicacionBean.aplicacionId').value;
	if (aplicacionId == ""){
		aplicacionId = '0';
		}	
	document.getElementById('cid').value='';
	  $('#cid option').each(function() {
		        $(this).remove();
		});
	$.ajax({
        type: "POST",
         url: "ajaxLoadCanales.action",
        data: {idAplicacion:document.getElementById('envioMensajesAplicacionBean.aplicacionId').value},  // serializes the form's elements.
        success: function(data)
        {
     	  canalesTotales = data.items;
     	  $('#cid').append($('<option>', { 
     	        value: '',
     	        text : 'Todos' 
     	    }));
     	 $.each(canalesTotales, function (i, item) {
     		$('#cid').append($('<option>', { 
     	        value: item.value,
     	        text : item.text 
     	    }));     	   
     	});
        },
        error: function(data)
        {
     	   alert("error..."); 
        }
      });
}

function makeRequestServiciosPorCanal(){
	if(datos == null) return;
	
	var canalIndex = document.getElementById("cid").selectedIndex;
	var servicioIndex = document.getElementById("sid").selectedIndex;

	if (canalIndex == 0){
		$('#sid option').each(function() {
	        $(this).remove();
	});
		$('#sid').append($('<option>', { 
 	        value: '',
 	        text : 'Todos' 
 	    }));
 	 
 	 for (var i=0; i<datos.length; i++) {
  		$('#sid').append($('<option>', { 
	        value: datos[i].value,
	        text : datos[i].text 
	    }));   
 		 i++;
			}
 	$('#cid option').each(function() {
        $(this).remove();
	});

	 $('#cid').append($('<option>', { 
	        value: '',
	        text : 'Todos' 
	    }));
	 for (var i=0; i<canalesTotales.length; i++) {
	  		$('#cid').append($('<option>', { 
		        value: canalesTotales[i].value,
		        text : canalesTotales[i].text 
		    }));
  		 
			}
		
		return;
		}
	
	
	var canalId = document.getElementById('cid').value;
	var servicioId = document.getElementById('sid').value;
	
	$('#sid option').each(function() {
        $(this).remove();
	});
	$('#sid').append($('<option>', { 
	        value: '',
	        text : 'Todos' 
	    }));
	
	 for (var i=1; i<datos.length; i++) {
		 if(canalId == datos[i].value ){
			 $('#sid').append($('<option>', { 
			        value: datos[i-1].value,
			        text : datos[i-1].text 
			    }));
			 }    
  		 i++;
			}
	 if(servicioIndex != 0) $("#sid").val(servicioId);

}

</script>
<div class="mainContent">            
        <s:form id="frmEnvioMensajesAplicacion" enctype="multipart/form-data" method="POST" action="envioMensajesAplicacion" validate="false" cssClass="" theme="simple">
        <s:hidden id = "envioMensajesAplicacionBean.nombreAdjunto" name="envioMensajesAplicacionBean.nombreAdjunto" />
            <h3 class="pageNameButtons">
                <label>Envío de Mensajes
                </label>
            </h3>
        <%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
		
            <div class="editContainer">
                <div class="nameDescription">
                    <label>Datos Aplicación</label>
                </div>
                <div class="editContent">    
                     <p class="criteria">    
                       <label style="width: 120px;" class="fieldText">Aplicacion (*):</label>
                       <s:select 
						id="envioMensajesAplicacionBean.aplicacionId" name="envioMensajesAplicacionBean.aplicacionId" 
						emptyOption="false" theme="simple" onchange="makeRequest();makeRequestCanalTodos();"
						labelposition="left" 
						list="comboAplicaciones" listKey="codigo" headerValue="" headerKey=""
						listValue="descripcion" cssClass="" cssStyle="width:150px"				
						value="%{envioMensajesAplicacionBean.aplicacionId}" />
           	      	</p>

                 	<p class="criteria">
                       <label style="width: 120px;" class="fieldText">Servicio (*):</label>
                       <s:select 
						id="sid" name="sid" 
						emptyOption="false" theme="simple" onchange="makeRequestCanal();setValue(this);"
						labelposition="left"
						list="comboServicios" listKey="codigo" headerValue="" headerKey=""
						listValue="descripcion" cssClass="" cssStyle="width:150px"						
						value="%{envioMensajesAplicacionBean.servicioId}" />     	      	
           	      	</p>
                     <p class="criteria">
                       <label style="width: 120px;" class="fieldText">Canal (*):</label>
                       
                       <s:select onchange="makeRequestServiciosPorCanal();setValueCanal(this);checkCanalEnvio(this);" id="cid"
						name="canalId" emptyOption="false" theme="simple"
						labelposition="left" list="comboCanales" listKey="codigo"
						headerValue="" headerKey=""
						listValue="descripcion" cssStyle="width:150px" cssClass=""
						value="%{envioMensajesAplicacionBean.canalId}" disabled="false" />

           	      	 </p>   
                                  
                    <p class="criteria">
                        <label style="width: 150px;" class="fieldText"><i>(*) Campos obligatorios</i></label>
                    </p>                            
                </div>
                
            </div>
            <div class="editContainer"  id="divGeneral">
			<div class="nameDescription">
				<label>Datos Generales del Mensaje</label>
			</div>
			<div class="editContent">
				<p class="criteria">		
					<label style="width: 120px;" class="fieldText">Nombre Lote (*):</label>
					
					<s:textfield name="envioMensajesAplicacionBean.nombreLote"
							value="%{envioMensajesAplicacionBean.nombreLote}" id="envioMensajesAplicacionBean.nombreLote"
							theme="simple" cssStyle="" labelposition="left"
							size="45" maxlength="255" cssClass="" />
					<label class="tiptext"><img src="./img/icoHelp.png" height="10" width="10" "><label class="description">${textoAyudaNombreLote}</label></label>
				</p>

				
				<div class="ui-widget" class ="ui-autocomplete-loading">
					<p class="criteria">
					<label style="width: 120px;" id="organismoLabel" class="fieldText">Organismo: </label>
					<s:textfield name="search"
							value=""
							id="search" theme="simple"
							labelposition="left" size="55" maxlength="255"
					/>
            			
				</div>
				

				<p class="criteria">
					<label style="width: 150px;" class="fieldText"><i>(*) Campos obligatorios</i></label>
				</p>
			</div>
		</div>
		<div class="editContainer"  id="divDestinatarios">
            	<div class="nameDescription">
                	<label>Destinatarios</label>
                </div>           
                <div class="editContent">   
               		<p class="criteria">
						<label class="fieldText" id="idExternoLabelSMS" style="visibility:hidden;display:none;width: 120px;">Id Externo :</label>
						<label class="fieldText" id="idExternoLabel" style="width: 120px;">Id Externo:</label>
						<s:textfield name="envioMensajesAplicacionBean.idExterno" value="%{envioMensajesAplicacionBean.idExterno}"
							id="envioMensajesAplicacionBean.idExterno" theme="simple" labelposition="left" size="45" maxlength="255"
							cssClass="" />

<label class="tiptext"><img src="./img/icoHelp.png" height="10" width="10" "><label class="description">${textoAyudaIdExterno}</label></label>
					</p>

                	<p class="criteria">
                        <label theme="simple" id="idUsuarioLabel" style="width: 120px;visibility:hidden; display:none" 
                        class="fieldText">Id usuario (*):</label>
						<s:textfield name="envioMensajesAplicacionBean.idUsuario" value="%{envioMensajesAplicacionBean.idUsuario}"
							id="envioMensajesAplicacionBean.idUsuario" theme="simple" labelposition="left" size="45" maxlength="255"
							cssClass="" style="visibility:hidden;display:none;"/>                    
					</p>
                    <p class="criteria">
                    	<label theme="simple" id="movilLabel" style="width: 120px;visibility:hidden; display:none" 
                    	class="fieldText">Móvil (*):</label>
						<s:textfield name="envioMensajesAplicacionBean.movil" value="%{envioMensajesAplicacionBean.movil}"
							id="envioMensajesAplicacionBean.movil" theme="simple" labelposition="left" size="45" maxlength="255"
							cssClass="" style="visibility:hidden;display:none;"/>      
                    </p>
                    
                     <p class="criteria">
                    	<label theme="simple" id="toLabel" style="width: 120px;visibility:hidden; display:none" 
                    	class="fieldText">Para (*):</label>
						<s:textarea name="envioMensajesAplicacionBean.to" value="%{envioMensajesAplicacionBean.to}"
							placeholder="Introduce los destinatarios separados por ';'"
							id="envioMensajesAplicacionBean.to" theme="simple" labelposition="left" size="45" maxlength="255"
							cssClass="W240" style="visibility:hidden;display:none;"/>      
                    </p>
                   <p class="criteria">
                    	<label theme="simple" id="ccLabel" style="width: 120px;visibility:hidden; display:none" 
                    	class="fieldText">En copia :</label>
						<s:textarea name="envioMensajesAplicacionBean.cc" value="%{envioMensajesAplicacionBean.cc}"
							placeholder="Introduce los destinatarios separados por ';'"
							id="envioMensajesAplicacionBean.cc" theme="simple" labelposition="left" size="45" maxlength="255"
							cssClass="W240" style="visibility:hidden;display:none;"/>      
                    </p>
                     <p class="criteria">
                    	<label theme="simple" id="ccoLabel" style="width: 120px;visibility:hidden; display:none" 
                    	class="fieldText">En copia oculta :</label>
						<s:textarea name="envioMensajesAplicacionBean.cco" value="%{envioMensajesAplicacionBean.cco}"
						   placeholder="Introduce los destinatarios separados por ';'"
							id="envioMensajesAplicacionBean.cco" theme="simple" labelposition="left" size="45" maxlength="255"
							cssClass="W240" style="visibility:hidden;display:none;"/>      
                    </p>
                    
                  <p class="criteria">
					<label style="width: 150px;" class="fieldText"><i>(*) Campos obligatorios</i></label>
				  </p>
			  </div>
			</div>
            <div class="editContainer"  id="divMensaje">
            	<div class="nameDescription">
                	<label>Mensaje</label>
                </div>
            	<div class="editContent">
<!--             	email -->
					<p class="criteria">
                    	<label theme="simple" id="asuntoLabel" style="width: 120px;visibility:hidden; display:none" 
                    	class="fieldText">Asunto (*):</label>
						<s:textfield  name="envioMensajesAplicacionBean.asunto" id="envioMensajesAplicacionBean.asunto" 
						theme="simple" cssClass="W240"  value="%{envioMensajesAplicacionBean.asunto}" style="visibility:hidden;display:none;"/> 
                    </p>
<!--                     notificaciones push -->
                    <p class="criteria">
                    	<label theme="simple"  id="tituloLabel" style="width: 120px;visibility:hidden; display:none" 
                    	class="fieldText">Titulo (*):</label>
						<s:textfield  name="envioMensajesAplicacionBean.titulo" id="envioMensajesAplicacionBean.titulo" 
						theme="simple" cssClass="W240"  value="%{envioMensajesAplicacionBean.titulo}" style="visibility:hidden;display:none;"/> 
                    </p>
                    
                    <p class="criteria">
                    	<label theme="simple" id="mensajeLabel" style="width: 120px;" 
                    	class="fieldText">Mensaje (*):</label>
						<s:textarea  name="envioMensajesAplicacionBean.mensaje" value="%{envioMensajesAplicacionBean.mensaje}" id="envioMensajesAplicacionBean.mensaje" 
						theme="simple" maxlength="1000" cssClass="W240" /> 
                    </p>
                    
                    <p class="criteria">
                    	<label theme="simple" id="cuerpoLabel" style="width: 120px;visibility:hidden; display:none" 
                    	class="fieldText">Cuerpo (*):</label>
						<s:textarea  name="envioMensajesAplicacionBean.cuerpo" value="%{envioMensajesAplicacionBean.cuerpo}" id="envioMensajesAplicacionBean.cuerpo" 
						theme="simple" cssClass="W240" maxlength="1000" style="visibility:hidden;display:none;"/> 
                    </p>

                    
                    <p class="criteria">
                        <label theme="simple" id="adjuntoLabel" style="width: 120px;visibility:hidden; display:none" 
                        class="fieldText">Adjunto :</label>                        
							<input type="file" name="envioMensajesAplicacionBean.adjunto" id="envioMensajesAplicacionBean.adjunto" 
							value="%{envioMensajesAplicacionBean.adjunto}" onchange="subidaFichero()" style="visibility:hidden;display:none;"/>
						
					</p>
					
					<p class="criteria" id="passbook" style="visibility:hidden;display:none;" >
						<label class="fieldText" style="width: 120px;">Passbook</label>
						<s:checkbox onchange="checkPassbook()"
						    theme="simple" id="envioMensajesAplicacionBean.passbook"
							name="envioMensajesAplicacionBean.passbook"
							value="%{envioMensajesAplicacionBean.passbook}"/>
					</p>
					
<!-- 				notificaciones push -->

                    
                 <p class="criteria">
					<label style="width: 150px;" class="fieldText"><i>(*) Campos obligatorios</i></label>
				</p>
                </div>
            </div>
            
			<div class="editContainer" style="visibility:hidden;display:none;" id="passbookLabel">
				<div class="nameDescription">
					<label>Passbook </label>
				</div>
				<div class="editContent">
					<p class="criteria">
						<span style="width: 340px;"> <label class="fieldTextInfoPassbook" style="height:0px0px">Pulse <a href="peticionPassbook.txt" target="_blank">aquí</a> para descargarse un xml de ejemplo con la información requerida en los campos del formulario.</label> <strong><s:label
								theme="simple" /></strong>
						</span>
					</p>					
					<p class="criteria">		
						<label style="width: 120px;visibility:hidden;display:none;" id="urlLabel" class="fieldText">URL :</label>
						<s:textfield name="envioMensajesAplicacionBean.url"
								value="%{envioMensajesAplicacionBean.url}" id="envioMensajesAplicacionBean.url"
								theme="simple" labelposition="left" style="visibility:hidden;display:none;"
								size="45" maxlength="255" cssClass="W240" />
					</p>
	
					<p class="criteria">
						<label class="fieldText" id="logoPassbookLabel" style="width: 120px;visibility:hidden;display:none;">Logo :</label>
						<s:textfield name="envioMensajesAplicacionBean.logoPassbook"
							value="%{envioMensajesAplicacionBean.logoPassbook}"
							id="envioMensajesAplicacionBean.logoPassbook" theme="simple"
							labelposition="left" size="45" style="visibility:hidden;display:none;" maxlength="255"
							cssClass="W240" />
					</p>
					
					<p class="criteria">
						<label class="fieldText" id="descripcionPassbookLabel" style="width: 120px;visibility:hidden;display:none;">Descripción :</label>
						<s:textfield name="envioMensajesAplicacionBean.descripcionPassbook"
							value="%{envioMensajesAplicacionBean.descripcionPassbook}"
							id="envioMensajesAplicacionBean.descripcionPassbook" theme="simple"
							labelposition="left" size="45" style="visibility:hidden;display:none;"
							maxlength="255" cssClass="W240" />
					</p>		
					<br>		
					<div class="editContent">
						<label class="fieldText" id="camposPrincLabel" style="visibility:hidden; display:none">Campos Principales :</label>			
						<div class="editContent">
							<br></br>
							<p class="criteria">
								<label class="fieldText" id="keyPrincLabel" style="width: 120px;visibility:hidden; display:none">Clave :</label>			
								<s:textfield name="envioMensajesAplicacionBean.keyPrinc"
									value="%{envioMensajesAplicacionBean.keyPrinc}"
									id="envioMensajesAplicacionBean.keyPrinc" theme="simple"
									labelposition="right" size="45" style="visibility:hidden;display:none;"
									 maxlength="255" cssClass="" />
							</p>
							<p class="criteria">
								<label class="fieldText" id="labelPrincLabel" style="width: 120px;visibility:hidden; display:none">Etiqueta :</label>			
								<s:textfield name="envioMensajesAplicacionBean.labelPrinc"
									value="%{envioMensajesAplicacionBean.labelPrinc}"
									id="envioMensajesAplicacionBean.labelPrinc" theme="simple"
									labelposition="right" size="45" style="visibility:hidden;display:none;"
									 maxlength="255" cssClass="" />
							</p>
							<p class="criteria">
								<label class="fieldText" id="valuePrincLabel" style="width: 120px;visibility:hidden; display:none">Valor :</label>			
								<s:textfield name="envioMensajesAplicacionBean.valuePrinc"
									value="%{envioMensajesAplicacionBean.valuePrinc}"
									id="envioMensajesAplicacionBean.valuePrinc" theme="simple"
									labelposition="right" size="45" style="visibility:hidden;display:none;"
									 maxlength="255" cssClass="" />
							</p>
						</div>
					</div>
					<br>
					<div class="editContent">
						<label class="fieldText" id="campoSecLabel" style="visibility:hidden; display:none">Campos Secundarios :</label>				
						<div class="editContent">	
						<br></br>						
							<p class="criteria">
								<label class="fieldText" id="keySecLabel" style="width: 120px;visibility:hidden; display:none">Clave :</label>			
								<s:textfield name="envioMensajesAplicacionBean.keySec"
									value="%{envioMensajesAplicacionBean.keySec}"
									id="envioMensajesAplicacionBean.keySec" theme="simple"
									labelposition="right" size="45" style="visibility:hidden;display:none;"
									 maxlength="255" cssClass="" />
							</p>
							<p class="criteria">
								<label class="fieldText" id="labelSecLabel" style="width: 120px;;visibility:hidden; display:none">Etiqueta :</label>			
								<s:textfield name="envioMensajesAplicacionBean.labelSec"
									value="%{envioMensajesAplicacionBean.labelSec}"
									id="envioMensajesAplicacionBean.labelSec" theme="simple"
									labelposition="right" size="45" style="visibility:hidden;display:none;"
									 maxlength="255" cssClass="" />
							</p>
							<p class="criteria">
								<label class="fieldText" id="valueSecLabel" style="width: 120px;;visibility:hidden; display:none">Valor :</label>			
								<s:textfield name="envioMensajesAplicacionBean.valueSec"
									value="%{envioMensajesAplicacionBean.valueSec}"
									id="envioMensajesAplicacionBean.valueSec" theme="simple"
									labelposition="right" size="45" style="visibility:hidden;display:none;"
									 maxlength="255" cssClass="" />
							</p>
						</div>
					</div>	
					<br>						
					<div class="editContent">
						<label class="fieldText" id="campoAuxLabel" style="visibility:hidden; display:none">Campos Auxiliares :</label>					
						<div class="editContent">	
						<br></br>							
							<p class="criteria">
								<label class="fieldText" id="keyAuxLabel" style="width: 120px;visibility:hidden; display:none">Clave :</label>			
								<s:textfield name="envioMensajesAplicacionBean.keyAux"
									value="%{envioMensajesAplicacionBean.keyAux}"
									id="envioMensajesAplicacionBean.keyAux" theme="simple"
									labelposition="right" size="45" style="visibility:hidden;display:none;"
									 maxlength="255" cssClass="" />
							</p>
							<p class="criteria">
								<label class="fieldText" id="labelAuxLabel" style="width: 120px;;visibility:hidden; display:none">Etiqueta :</label>			
								<s:textfield name="envioMensajesAplicacionBean.labelAux"
									value="%{envioMensajesAplicacionBean.labelAux}"
									id="envioMensajesAplicacionBean.labelAux" theme="simple"
									labelposition="right" size="45" style="visibility:hidden;display:none;"
									 maxlength="255" cssClass="" />
							</p>
							<p class="criteria">
								<label class="fieldText" id="valueAuxLabel" style="width: 120px;;visibility:hidden; display:none">Valor :</label>			
								<s:textfield name="envioMensajesAplicacionBean.valueAux"
									value="%{envioMensajesAplicacionBean.valueAux}"
									id="envioMensajesAplicacionBean.valueAux" theme="simple"
									labelposition="right" size="45" style="visibility:hidden;display:none;"
									 maxlength="255" cssClass="" />
							</p>
						</div>
					</div>
					<br>			
					<div class="editContent">
						<label class="fieldText" id="campoTrasLabel" style="visibility:hidden; display:none">Campos Detalle Trasero :</label>		
						<div class="editContent">	
						<br></br>
							<p class="criteria">
								<label class="fieldText" id="keyTrasLabel" style="width: 120px;visibility:hidden; display:none">Clave :</label>			
								<s:textfield name="envioMensajesAplicacionBean.keyTras"
									value="%{envioMensajesAplicacionBean.keyTras}"
									id="envioMensajesAplicacionBean.keyTras" theme="simple"
									labelposition="right" size="45" style="visibility:hidden;display:none;"
									 maxlength="255" cssClass="" />
							</p>
							<p class="criteria">
								<label class="fieldText" id="labelTrasLabel" style="width: 120px;;visibility:hidden; display:none">Etiqueta :</label>			
								<s:textfield name="envioMensajesAplicacionBean.labelTras"
									value="%{envioMensajesAplicacionBean.labelTras}"
									id="envioMensajesAplicacionBean.labelTras" theme="simple"
									labelposition="right" size="45" style="visibility:hidden;display:none;"
									 maxlength="255" cssClass="" />
							</p>
							<p class="criteria">
								<label class="fieldText" id="valueTrasLabel" style="width: 120px;;visibility:hidden; display:none">Valor :</label>			
								<s:textfield name="envioMensajesAplicacionBean.valueTras"
									value="%{envioMensajesAplicacionBean.valueTras}"
									id="envioMensajesAplicacionBean.valueTras" theme="simple"
									labelposition="right" size="45" style="visibility:hidden;display:none;"
									 maxlength="255" cssClass="" />
							</p>
						</div>
					</div>
				</div>
			</div>
				  
				  
                  <span class="floatRight">
                     <input type="button" id="button" value="Enviar" class="button" onclick="submitForm()"/>
<%-- 						<s:submit theme="simple" value="Enviar" class="button"/> --%>
                   </span>
                                           <s:hidden id="envioMensajesAplicacionBean.servicioId" name="envioMensajesAplicacionBean.servicioId" value="%{envioMensajesAplicacionBean.servicioId}"/>  
                                           <s:hidden id="envioMensajesAplicacionBean.canalId" name="envioMensajesAplicacionBean.canalId" value="%{envioMensajesAplicacionBean.canalId}"/>
        
           </s:form>
           
           <script type="text/javascript">
           function subidaFichero(){
        	   if(document.getElementById('envioMensajesAplicacionBean.adjunto').value.split('.').pop() == "exe"){
        		  alert("El tipo de fichero no puede ser de tipo exe.");
        		  document.getElementById('envioMensajesAplicacionBean.adjunto').value = null; 
            	}else{
                	   var nombreFich = document.getElementById('envioMensajesAplicacionBean.adjunto').value;
                		   
            			document.getElementById('envioMensajesAplicacionBean.nombreAdjunto').value = nombreFich.split(/(\\|\/)/g).pop();
                	}      	   
           }
           
           		checkCanalEnvio(document.getElementById('cid'));

           		$(".tiptext").mouseover(function() {
           		    $(this).children(".description").show();
           		}).mouseout(function() {
           		    $(this).children(".description").hide();
           		});
           		$(document).ready ( function(){
           			if (document.getElementById('envioMensajesAplicacionBean.passbook').value){
           					checkPassbook();
               			}
           			
           		});
           		               	
  		   </script>
			
        </div>