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
<script language="javascript">

        	function operacionChanged(op)
        	{
				if(op == "firmar" || op == "cofirmar" || op == "contrafirmar"){
					$("#operacion").value = op;
				}
				
        		if(op == "firmar"){
        			document.getElementById("ficheroDatos").disabled = '';
        			document.getElementById("ficheroFirma").disabled = 'disabled';
				document.getElementById("ficheroFirma").value = '';
        			document.getElementById("botonCargar").disabled = 'disabled';
				var signersList = document.getElementById("firmantes");
				signersList.length = 0;
        		}
        		else if(op == "cofirmar"){
				document.getElementById("ficheroDatos").disabled = '';
        			document.getElementById("ficheroFirma").disabled = '';
        			document.getElementById("botonCargar").disabled = 'disabled';
				var signersList = document.getElementById("firmantes");
				signersList.length = 0;
        		}
        		else if(op == "contrafirmar"){
        			document.getElementById("ficheroDatos").disabled = 'disabled';
				document.getElementById("ficheroDatos").value = '';
        			document.getElementById("ficheroFirma").disabled = '';
        			document.getElementById("botonCargar").disabled = '';
        		}
        	}

        	function cambioTipoContrafirma(tipo)
        	{
        		document.getElementById('modoContrafirma').value = tipo;
        	}

        	function configurarOperacion()
        	{
			configuraFirma();

			// Configuramos la configuracion de firma por defecto
			clienteFirma.setSignatureAlgorithm(document.getElementById("algoritmo").value);
			clienteFirma.setSignatureFormat(document.getElementById("formato").value);
			clienteFirma.setSignatureMode(document.getElementById("modoFirma").value);
			//clienteFirma.setShowExpiratedCertificates(false);


			// Si hay filtro lo configuramos
			var filter = document.getElementById("filtroCertificados").value;
			if(filter != undefined && filter != null && filter != '') {
				clienteFirma.setCertFilter(filter);
			}

			// Configuramos los ficheros de firma
			fdatos = document.getElementById("ficheroDatos").value;
			if(fdatos != null && fdatos != undefined && trim(fdatos) != "") {
				clienteFirma.setFileuri(fdatos);
			}
			ffirma = document.getElementById("ficheroFirma").value;
			if(ffirma != null && ffirma != undefined && trim(ffirma) != "") {
				clienteFirma.setElectronicSignatureFile(ffirma);
			}
		}

        	function realizarOperacion()
        	{
			// Limpiamos la configuracion del cliente
			initialize();

			// Configuramos los datos de la operacion
			configurarOperacion();

			// Ejecutamos la operacion que corresponda
			var op = document.getElementById('operacion').value;

			if(op=="firmar")
			{
				clienteFirma.sign();
			}
			else if(op=="cofirmar")
			{
				clienteFirma.coSign();
			}
			else if(op=="contrafirmar")
			{
				var tipo=document.getElementById('modoContrafirma').value;
				if(tipo=="NODOS")
				{
					contraFirmarNodos(obtenerNodosContrafirma());
				}
				else if(tipo=="FIRMANTES")
				{
					contraFirmarFirmantes(obtenerFirmantesContrafirma());
				}
				else if(tipo=="ARBOL")
				{
					contraFirmarArbol();
				}
				else if(tipo=="HOJAS")
				{
					contraFirmarHojas();
				}
			}

			// Guardamos la firma en disco si todo ha salido bien
			if(!clienteFirma.isError()) {
				clienteFirma.saveSignToFile();
			}

			/* Solo mostramos el error via JavaScript si se ha configurado que no lo muestre el propio cliente */
			else if (!showErrors) {
				alert(clienteFirma.getErrorMessage());
			}
		}

			function obtenerNodosContrafirma()
			{
				var signersList = document.getElementById("firmantes");
				var result;
				for (var i = 0; i < signersList.length; i++)  {
					if (signersList.options[i].selected)  {
						if(result == undefined) result = "";
						result += i + "\\n";
					}
				}
				return result;
			}


			function obtenerFirmantesContrafirma()
			{
				var signersList = document.getElementById("firmantes");
				var result = "";
				for (var i = 0; i < signersList.length; i++)  {
					if (signersList.options[i].selected)  {
						var signerElem = signersList.options[i].text;
						while(signerElem.substr(0, 3) == "-- ") {
							signerElem = signerElem.substr(3);
						}
						result += signerElem + "\\n";
					}
				}
				return result;
			}


       	 	function cargarFirma()
	        	{
				// Limpiamos la configuracion del cliente
				initialize();

				clienteFirma.setSignatureFormat(document.getElementById("formato").value);
				clienteFirma.setElectronicSignatureFile(document.getElementById("ficheroFirma").value);
				var signersStructure = getEstructuraNodos();
				if(signersStructure != undefined && signersStructure != 'null') {
					var signers = signersStructure.split('\n');
					var signersList = document.getElementById("firmantes");
					// Empezamos a partir de 1 para obviar el nodo raiz del arbol
					signersList.length = signers.length-1;
					for(var i=0; i<signersList.length; i++) {
						signersList.options[i].text = tabulaChildNodes(signers[i+1]);
						signersList.options[i].value = signers[i+1];
					}
				}
				else {
					alert("No se ha podido extraer una estructura de firmantes de la firma indicada");
				}
			}

			function tabulaChildNodes(text)
	        	{
				var result = "";
				var temp = text;
				while(temp.indexOf('\t') != -1) {
					result = result + "-- ";
					temp = temp.substr(1);
				}
				return result + temp;
			}

			function trim(cad)
			{
				return cad.replace(/^(\s|\t|\r|\n)*|(\s|\t|\r|\n)*$/g,"");
			}

</script>
<script type="text/javascript">
	cargarAppletFirma('COMPLETA');
</script>
<form name="prueba" id="prueba">
			<input type="hidden" value="firmar" id="operacion" />
			<input type="hidden" value="NODOS" id="modoContrafirma" />
			<table align="center">
			<tr>
				<td>
					<fieldset><legend>Formato de firma por defecto</legend>
					<table>
						<tr>
							<td>Algoritmo</td>
							<td>Formato</td>
							<td>Modo</td>
						</tr>
						<tr>
							<td>
								<select id="algoritmo">
									<option value="SHA1withRSA" selected="selected">SHA1 con RSA</option>
									<option value="MD5withRSA">MD5 con RSA</option>
									<option value="MD2withRSA">MD2 con RSA</option>
								</select>
							</td>
							<td>
								<select id="formato">
									<option value="CMS/PKCS#7" selected="selected">CMS/PKCS#7</option>
									<option value="CAdES">CAdES</option>
									<option value="XAdES Detached">XAdES/XAdES Detached</option>
									<option value="XAdES Enveloped">XAdES Enveloped</option>
									<option value="XAdES Enveloping" >XAdES Enveloping</option>
					<option value="XAdES Externally Detached" >XAdES Externally Detached</option>

									<option value="XMLDSig Detached">XMLDSig Detached</option>
									<option value="XMLDSig Enveloped">XMLDSig Enveloped</option>
									<option value="XMLDSig Enveloping">XMLDSig Enveloping</option>
					<option value="XMLdSign Externally Detached" >XMLdSign Externally Detached</option>

									<option value="PDF">Adobe PDF</option>
									<option value="ODF">ODF (Open Document Format)</option>
									<option value="OOXML">OOXML (Office Open XML)</option>
								</select>
							</td>
							<td>
								<select id="modoFirma">
									<option value="explicit" selected="selected">Expl�cita</option>
									<option value="implicit">Impl�cita</option>
								</select>
							</td>
						</tr>
					</table>
					</fieldset>
				</td>
			</tr>
			<tr>
				<td>
					<fieldset><legend>Certificados de firma</legend>
					<table>
						<tr>
							<td>
								<label for="filtroCertificados"><span>Filtro:</span></label><br/>
								<input type="text" id="filtroCertificados" name="filtroCertificados"/>
							</td>
						</tr>
					</table>
					</fieldset>
				</td>
			</tr>

			<tr>
				<td>
					<fieldset><legend>Tipo de operaci�n</legend>
					<table>
						<tr>
							<td>
								<input type="radio" name="operacion" value="firmar"
								onchange="operacionChanged('firmar')" onclick="operacionChanged('firmar')" checked="checked" />
									Firmar
							</td>
						</tr>
						<tr>
							<td>
								<input type="radio" name="operacion" value="cofirmar"
								onchange="operacionChanged('cofirmar')" onclick="operacionChanged('cofirmar')" />
									Co-firmar
							</td>
						</tr>
						<tr>
							<td>
								<input type="radio" name="operacion" value="contrafirmar"
								onchange="operacionChanged('contrafirmar')" onclick="operacionChanged('contrafirmar')" />
									Contra-firmar
							</td>
						</tr>
					</table>
					</fieldset>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<fieldset id="entradaDatos"><legend>Fichero a firmar</legend>
					<table>
						<tr>
							<td>
								<label for="ficheroDatos"><span>Fichero de datos:</span></label><br />
								<input type="text" id="ficheroDatos" />
							</td>
						</tr>
						<tr>
							<td>
								<label for="ficheroFirma"><span>Fichero de firma:</span></label><br />
								<input type="text" id="ficheroFirma" disabled="disabled" />
							</td>
						</tr>
					</table>
					</fieldset>
				</td>
			</tr>			
			<tr>
				<td colspan="2">
					<fieldset><legend>Firmantes</legend>
					<table>
						<tr>
							<td>
								<select name="tipoContrafirma" onchange="cambioTipoContrafirma(this.value)">
									<option value="NODOS" onSelect="cambioTipoContrafirma('NODOS')">Nodos seleccionados</option>
									<option value="FIRMANTES" onSelect="cambioTipoContrafirma('FIRMANTES')">Firmantes seleccionados</option>
									<option value="ARBOL" onSelect="cambioTipoContrafirma('ARBOL')">�rbol entero</option>
									<option value="HOJAS" onSelect="cambioTipoContrafirma('HOJAS')">Hojas del �rbol</option>
								</select>
							</td>
							<td rowspan="2" width="60%">
								<fieldset id="firmantesTD">
									<select multiple="multiple" name="firmantes" id="firmantes" size="5">					
									</select>
								</fieldset>
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="button" id="botonCargar" title="CargarFirma" onclick="cargarFirma();return false;" value="Cargar firma" disabled="disabled" />
							</td>
						</tr>
					</table>
					</fieldset>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input name="btnFirma" type="button" title="Firmar" value="Firmar" onclick="realizarOperacion(); return false;"/>
				</td>
			</tr>
		</table>
		</form>
	
