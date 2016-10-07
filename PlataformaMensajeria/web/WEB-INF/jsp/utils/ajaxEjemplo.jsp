<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<!-- ****************************************************************************** -->
<!-- Formulario                                                                     -->
<!-- ****************************************************************************** -->

<script>
$(document).ready( function() {
	$.subscribe('removeErrors', function(event,data) {
		$('.errorLabel').html('').removeClass('errorLabel');
		$('#formerrors').html('');
	});

	$.subscribe('handleJsonResult', function(event, data) {
				
		if ($("#escuderia").val() != -1){
			$('#result').html("<br>Datos de la escuder&iacute;a<br><ul id=\"datos\"></ul>");
		    var list = $('#datos');
			$.each(event.originalEvent.data.datosEscuderiaList, function(indice, data) {
				list.append('<li>'+data.codigo+': '+data.descripcion+'</li>\n');		
			})	
		}
	});

	$.subscribe('recargarAjax', function(event, data) {
		if ($("#escuderia").val() != -1){
			var form = $('#formEjemploAjax');
			$.ajax({
					type: "POST",
					url: form.attr('action'),
					data: form.serialize(),
					success: function( response ) {
						
						//var encoded = $.toJSON(response.datosEscuderiaList);
						//$("#formEjemploAjax_campoTexto").val(encoded);
						
						$('#result').html("<br>Datos de la escuder&iacute;a<br><ul id=\"datos\"></ul>");
						var list = $('#datos');
						$.each(response.datosEscuderiaList, function(indice, datosEscuderia) {
														
							list.append('<li>'+datosEscuderia.codigo+': '+datosEscuderia.descripcion+'</li>\n');
						})
					}
			});
		}
	});
	
	$.subscribe('errorState', function(event, data) {
		
		alert(event.originalEvent.request.statusText);
		
		/*$('#result').html("<br>Datos de la escuder&iacute;a<br><ul id=\"datos\"></ul>");
		var list = $('#datos');
		list.append('<li>'+event.originalEvent.request.statusText+'</li>\n');*/
		
	});
	
	$.subscribe('beforeForm', function(event,data) {
		var fData = event.originalEvent.formData;
		alert('About to submit: \n\n' + fData[0].value + ' to target '+event.originalEvent.options.target+' with timeout '+event.originalEvent.options.timeout );
		var form = event.originalEvent.form[0]; 
		if (form.echo.value.length  < 2) { 
			alert('Please enter a value with min 2 characters'); 
			event.originalEvent.options.submit = false; 
		} 
	});
	   
	$.subscribe('completeForm', function(event,data) {
		alert('status: ' + event.originalEvent.status + '\n\nresponseText: \n' + event.originalEvent.request.responseText + 
		'\n\nThe output div should have already been updated with the responseText.');
	});
	
	$.subscribe('errorStateForm', function(event,data) {
		alert('status: ' + event.originalEvent.status + '\n\nrequest status: ' +event.originalEvent.request.status);
	});

});
</script>
<script language="javascript" src="js/json2.js"></script>
<script language="javascript" src="js/jquery.json-2.2.min.js"></script>
<div id="contenedor_principal">
	<s:url id="remoteurl" action="ejemploAjax" />
	<s:form id="formEjemploAjax" action="%{remoteurl}" validate="false" theme="css_xhtml">
		<fieldset>
			<table summary="Tabla formulario datos del grupo" cellpadding="0" cellspacing="4">
			<thead>
				<tr>
					<th headers="header0" align="left">
						<div class="titulobloque">
							<h2><s:text name="pages.ajax.title" /></h2>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<sj:select href="%{remoteurl}" id="escuderia" name="escuderia" 
						list="comboObjList" listKey="codigo" listValue="descripcion" formIds="formEjemploAjax" 
						cssClass="input_tablas_registro" label="Escuderia" labelposition="left"
						emptyOption="false" headerKey="-1" headerValue="Elija una escudería..."
						onChangeTopics="recargarAjax" />
						
						<br>
					</td>
				</tr>
				<tr>
					<td>
					<sj:submit id="ajaxjsonsubmit" formIds="formEjemploAjax"
								href="%{remoteurl}"
								dataType="json"
								onSuccessTopics="handleJsonResult"
								button="true"
								onErrorTopics="errorState"
								indicator="indicator"/>
						<img id="indicator" src="images/indicator.gif" alt="Cargando..." style="display:none"/>
					</td>
				</tr>
				<tr id="ajax">
					<td>
						<div id="result"></div>
					</td>
				</tr>
			</tbody>
			</table>
		</fieldset>
	</s:form>
</div>