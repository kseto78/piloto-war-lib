<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
		
<script type="text/javascript" language="javascript" src="js/afirma/common-js/time.js"></script>
<script type="text/javascript" language="javascript" src="js/afirma/common-js/instalador.js"></script>
<script type="text/javascript" language="javascript" src="js/afirma/common-js/deployJava.js"></script>
<script type="text/javascript" language="javascript" src="js/afirma/common-js/firma.js"></script>
<script type="text/javascript" language="javascript" src="js/afirma/common-js/htmlEscape.js"></script>
<script type="text/javascript" language="javascript" src="js/afirma/common-js/utils.js"></script>
<script type="text/javascript" language="javascript" src="js/afirma/common-js/styles.js"></script>
<script type="text/javascript" language="javascript" src="js/afirma/common-js/firmaWeb.js"></script>
<script type="text/javascript" language="javascript" src="js/afirma/constantes.js"></script>
<script type="text/javascript" language="javascript">
		
		function firmar(element)
		{
			//Para evitar que se pulse dos veces el botón (dos peticiones no es buena idea) lo inhabilitamos
			$("#botonFirmar").disabled = "disabled";
			
			// Preparamos el cliente para firmar
			clienteFirma.initialize();
			clienteFirma.setShowErrors(true);
			
			// Configuramos el proceso de firma
			var formato= getFormato();
			clienteFirma.setSignatureFormat(formato);
			var modo= getModo();
			clienteFirma.setSignatureMode(modo);
			
			// Firmamos
			var ficheroFirmado = firmaWeb(element, document);
								
			// Recogemos el resultado (o el mensaje de error)
			if(!clienteFirma.isError())
			{
				var ext;
				var firma;
				if(formato == 'XADES' || formato=='XMLDSIGN')
				{
					firma = clienteFirma.getSignatureText();
					ext= ".xml";
				}
				else
				{
					firma = clienteFirma.getSignatureBase64Encoded();
					if(formato == 'NONE')
					{
						ext= ".fir";
					}
					else
					{
						ext= ".p7s";
					}
				}
					
				$("#formulario_firmaUsuario").val(firma);
				$("#result").html("Datos firmados correctamente");
					
				var guardar= confirm('¿Desea guardar la firma en un fichero?');
				if(guardar == true)
				{
					clienteFirma.saveSignToFile();
				}
			}
			else
			{
				$("#result").html(clienteFirma.getErrorMessage());
			}
			
			//Volvemos a activar el botón
			$("#botonFirmar").disabled="";
		}
			
		function getFormato()
		{
			var valueFormato = $("#formato option:selected").val();
								
			return valueFormato;
		}

		function getModo()
		{
			var valueModo = $("#modo option:selected").val();
							
			return valueModo;		
		}
		
		$(document).ready( function() {
		    $.subscribe('handleJsonResult', function(event,data) {
		    	
		    	var resultado = event.originalEvent.data.resultado;
		        $('#result').html(resultado);
		    });
		    
		    $.subscribe('errorState', function(event, data) {
				
				alert(event.originalEvent.request.statusText);				
			});
		});    
</script>
<script type="text/javascript">
	cargarAppletFirma('COMPLETA');
</script>
<div>
	<s:text name="pages.firmaForm.combos.formatoFirma" />
   	<select name="formato" id="formato">
   		<option value="CMS">CMS</option>
		<option value="XADES" selected="selected">XAdES</option>
		<option value="XADES Enveloping" >XAdES Enveloping</option>
		<option value="XMLDSIGN" >XML D-Sign</option>
    </select>
</div>
<div>
	<s:text name="pages.firmaForm.combos.modoFirma" />
   	<select name="modo" id="modo">
   		<option value="explicit">Explicita</option>
		<option value="implicit" selected="selected">Implicita</option>
   	</select>
</div>
<br/>
<s:form id="formulario" action="firmaWebJSON" method="POST" validate="false"  theme="css_xhtml">
	<s:text name="pages.firmaForm.text.datosUsuario" /> <br/><br/>
	<s:textfield name="nombre"  maxlength="90" theme="css_xhtml"
			cssClass="input_tablas_registro" value="" key="field.usuario.nombre"/>
	<br/>
	<s:textfield name="apellidos"  maxlength="90" theme="css_xhtml"
			cssClass="input_tablas_registro" value="" key="field.usuario.apellidos"/>
	<br/>
	<s:text name="pages.firmaForm.text.mensaje" /><br/>
	<textarea name="mensaje" id="mensaje" class="formens" cols="60" rows="5"></textarea><br/><br/>
	<input name="btnFirmar" id="botonFirmar" value="Firmar formulario" class="boton" type="button" onclick="firmar(this.form)" /><br/><br/>
	<s:hidden name="firmaUsuario"/>
	<sj:submit
		id="ajaxjsonsubmit" 
		formId="formulario" 
		value="Validar datos firmados" 
		dataType="json" 
		onSuccessTopics="handleJsonResult" 
		targets="result" 
		indicator="indicator"
		onErrorTopics="errorState"/>
</s:form>
	<br/><br/>
	<div id="result"><s:text name="pages.firmaForm.text.pendienteFirmar" /></div>
	<img id="indicator" src="images/indicator.gif" alt="..." style="display:none"/> 
	
	
