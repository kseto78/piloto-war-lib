<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
	<script>
		function makeRequestProducto(){
			var idProducto = document.getElementById('proveedorMisim.producto.idProducto').value;
			
			if(idProducto != "0" && idProducto !="-2" && idProducto != ""){
				$.ajax({
			        type: "POST",
			        url: "ajaxLoadProducto.action",
			        data: {idProducto:document.getElementById('proveedorMisim.producto.idProducto').value}, // serializes the form's elements.
			        success: function(data)
			        {	
			     		var nomProd = data.nombre;
			     	 	var codProd = data.codigo;
			     	 	$("input[name='proveedorMisim.producto.nombre']").val(nomProd);
			     		$("input[name='proveedorMisim.producto.codigo']").val(codProd);
			        },
			        error: function(data)
			        {
			     	   alert("error..."); 
			        }
			      });
			}
		}

		function makeRequestEndpoint(){
			var idEndpoint = document.getElementById('proveedorMisim.endpoint.idEndpoint').value;
			
			if(idEndpoint != "0" && idEndpoint !="-2" && idEndpoint != ""){
				$.ajax({
			        type: "POST",
			        url: "ajaxLoadEndpoint.action",
			        data: {idEndpoint:document.getElementById('proveedorMisim.endpoint.idEndpoint').value}, // serializes the form's elements.
			        success: function(data)
			        {	
				        var comEndp = data.comunicacion.idComunicacion;
			     		var nomEndp = data.nombre;
			     	 	var urlEndp = data.urlEndpoint;
			     	 	var servEndp = data.serviceName;
			     	 	var targEndp = data.targetName;
			     	 	var portEndp = data.portName;
			     	 	var operEndp = data.operation;
			     	 	var timEndp = data.timeout;
			     	 	$("select[name='proveedorMisim.endpoint.comunicacion.idComunicacion']").val(comEndp);
			     		$("input[name='proveedorMisim.endpoint.nombre']").val(nomEndp);
			     		$("input[name='proveedorMisim.endpoint.urlEndpoint']").val(urlEndp);
			     		$("input[name='proveedorMisim.endpoint.serviceName']").val(servEndp);
			     		$("input[name='proveedorMisim.endpoint.urlTargetName']").val(targEndp);
			     		$("input[name='proveedorMisim.endpoint.portName']").val(portEndp);
			     		$("input[name='proveedorMisim.endpoint.urlOperation']").val(operEndp);
			     		$("input[name='proveedorMisim.endpoint.timeout']").val(timEndp);
			        },
			        error: function(data)
			        {
			     	   	alert("error..."); 
			        }
			      });
			}
		}

		function makeRequestTransformacion(){
			var idTransformacion = document.getElementById('proveedorMisim.transformacion.idTransformacion').value;
			
			if(idTransformacion != "0" && idTransformacion !="-2" && idTransformacion != ""){			
				$.ajax({
			        type: "POST",
			        url: "ajaxLoadTransformacion.action",
			        data: {idTransformacion:document.getElementById('proveedorMisim.transformacion.idTransformacion').value}, // serializes the form's elements.
			        success: function(data)
			        {	
			     		var nomTrans = data.nombre;
			     	 	var xslPTrans = data.xslPeticion;
			     		var xslRTrans = data.xslRespuesta;
			     	 	var xslFTrans = data.xslFault;
			     		$("input[name='proveedorMisim.transformacion.nombre']").val(nomTrans);
			     		$("textarea[name='proveedorMisim.transformacion.xslPeticion']").val(xslPTrans);
			     		$("textarea[name='proveedorMisim.transformacion.xslRespuesta']").val(xslRTrans);
			     		$("textarea[name='proveedorMisim.transformacion.xslFault']").val(xslFTrans);
			        },
			        error: function(data)
			        {
			     	   alert("error..."); 
			        }
			      });
			}
		}

	</script>
<div class="mainContent">
	<s:form id="frmUpdateProveedorMisim" method="POST" action="updateProveedorMisim"
		theme="simple" cssClass="">
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:submit theme="simple"
					value="%{getText('buttons.text.save')}" cssClass="button" /> <input
				type="button" onclick="javascript:location.href='${volver}'"
				class="button" value="Volver">
			</span> <label>EDICIÓN PROVEEDOR MISIM</label>
		</h3>
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresMisimJS.jsp"%>
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
					<label style="width: 130px;" class="fieldText">Nombre Proveedor (*):</label>
					<s:textfield name="proveedorMisim.nombre" value="%{proveedorMisim.nombre}"
						id="proveedorMisim.nombre" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>
				
<!-- 				<p class="criteria"> -->
<!-- 					<label style="width: 130px; visibility: hidden; display:none" class="fieldText">Usuario:</label> -->
<%-- 					<s:textfield name="proveedorMisim.usuario" value="%{proveedorMisim.usuario}" --%>
<%-- 						id="proveedorMisim.usuario" theme="simple" cssStyle="visibility:hidden;display:none" labelposition="left" size="30" --%>
<%-- 						maxlength="255" cssClass="" /> --%>
<!-- 				</p> -->
				
<!-- 				<p class="criteria"> -->
<!-- 					<label style="width: 130px; visibility: hidden; display:none" class="fieldText">Password:</label> -->
<%-- 					<s:textfield name="proveedorMisim.password" value="%{proveedorMisim.password}" --%>
<%-- 						id="proveedorMisim.password" theme="simple" cssStyle="visibility:hidden;display:none" labelposition="left" size="30" --%>
<%-- 						maxlength="255" cssClass="" /> --%>
<!-- 				</p> -->
				
<!-- 				<p class="criteria"> -->
<!-- 					<label style="width: 130px; visibility: hidden; display:none" class="fieldText">Company:</label> -->
<%-- 					<s:textfield name="proveedorMisim.company" value="%{proveedorMisim.company}" --%>
<%-- 						id="proveedorMisim.company" theme="simple" cssStyle="visibility:hidden;display:none" labelposition="left" size="30" --%>
<%-- 						maxlength="255" cssClass="" /> --%>
<!-- 				</p> -->
				
<!-- 				<p class="criteria"> -->
<!-- 					<label style="width: 130px; visibility: hidden; display:none" class="fieldText">Type:</label> -->
<%-- 					<s:textfield name="proveedorMisim.type" value="%{proveedorMisim.type}" --%>
<%-- 						id="proveedorMisim.type" theme="simple" cssStyle="visibility:hidden;display:none" labelposition="left" size="30" --%>
<%-- 						maxlength="255" cssClass="" /> --%>
<!-- 				</p> -->
				
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Codificación:</label>
					<s:textfield name="proveedorMisim.encoding" value="%{proveedorMisim.encoding}"
						id="proveedorMisim.encoding" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>
				
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Autenticación Básica:</label>
					<s:textfield name="proveedorMisim.basicAutentication" value="%{proveedorMisim.basicAutentication}"
						id="proveedorMisim.basicAutentication" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>
				
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Método:</label>
					<s:textfield name="proveedorMisim.method" value="%{proveedorMisim.method}"
						id="proveedorMisim.method" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>
				
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Tipo de Medio:</label>
					<s:textfield name="proveedorMisim.mediaType" value="%{proveedorMisim.mediaType}"
						id="proveedorMisim.mediaType" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>
				
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Añadir UIM:</label>
					<s:textfield name="proveedorMisim.anadirUim" value="%{proveedorMisim.anadirUim}"
						id="proveedorMisim.anadirUim" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>
				
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Usuario Autenticación:</label>
					<s:textfield name="proveedorMisim.userAutentication" value="%{proveedorMisim.userAutentication}"
						id="proveedorMisim.userAutentication" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>
				
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Password Autenticación:</label>
					<s:textfield name="proveedorMisim.passAutentication" value="%{proveedorMisim.passAutentication}"
						id="proveedorMisim.passAutentication" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>														
				
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Certificado:</label>
					<s:textfield name="proveedorMisim.certificado" value="%{proveedorMisim.certificado}"
						id="proveedorMisim.certificado" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>	
				
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Password Certificado:</label>
					<s:textfield name="proveedorMisim.certificadoPass" value="%{proveedorMisim.certificadoPass}"
						id="proveedorMisim.certificadoPass" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>																							
				
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Firma:</label>
					<s:textfield name="proveedorMisim.firma" value="%{proveedorMisim.firma}"
						id="proveedorMisim.firma" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>
				
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Cifrado:</label>
					<s:textfield name="proveedorMisim.cifrado" value="%{proveedorMisim.cifrado}"
						id="proveedorMisim.cifrado" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>
				
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Nodo Cifrado:</label>
					<s:textfield name="proveedorMisim.nodoCifrado" value="%{proveedorMisim.nodoCifrado}"
						id="proveedorMisim.nodoCifrado" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>
				
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Esquema Cifrado:</label>
					<s:textfield name="proveedorMisim.esquemaCifrado" value="%{proveedorMisim.esquemaCifrado}"
						id="proveedorMisim.esquemaCifrado" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>	
				
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Tipo Firma:</label>
					<s:textfield name="proveedorMisim.tipoFirma" value="%{proveedorMisim.tipoFirma}"
						id="proveedorMisim.tipoFirma" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
				</p>
				
				<p class="criteria">
					<label id="proveedorMisim.camposObligatoriosLabel" style="width: 150px;" labelposition="right" class="fieldText">
					<i>(*) Campos obligatorios</i></label>
				</p>	
				
			</div>
		</div>
	
		<div class="editContainer">
			<div class="nameDescription">
				<label>Productos</label>
				<p>Un Proveedor de Misim puede tener asociado un único producto.</p>
			</div>
			<div class="editContent">
					<p class="criteria">

						<span style="width: 350px;"> <label style="width: 130px;"
							class="fieldText">Productos: </label> 
							<s:select onchange="makeRequestProducto(); checkProductos(this);"
								id="proveedorMisim.producto.idProducto"
								name="proveedorMisim.producto.idProducto" emptyOption="true"
								theme="simple"  labelposition="left" title="Productos"
								list="comboProductosProveedorMisim" listKey="codigo"
								listValue="descripcion" cssClass="" cssStyle="width:180px"
								value="%{proveedorMisim.producto.idProducto}" disabled="false"/> 
							<s:hidden
								name="idProveedor" id="idProveedor" value="%{idProveedor}" />
						</span>
					</p>
					
					<p class="criteria">
					<label  id="proveedorMisim.nombreProductoLabel" style="width: 130px;" class="fieldText">Nombre Producto (*):</label>
					<s:textfield name="proveedorMisim.producto.nombre" value="%{proveedorMisim.producto.nombre}"
						id="proveedorMisim.producto.nombre" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
					</p>
					
					<p class="criteria">
					<label  id="proveedorMisim.codigoProductoLabel" style="width: 130px;" class="fieldText">Código Producto (*):</label>
					<s:textfield name="proveedorMisim.producto.codigo" value="%{proveedorMisim.producto.codigo}"
						id="proveedorMisim.producto.codigo" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
					</p>
					
				
			</div>
		</div>	
		
		<div class="editContainer">
			<div class="nameDescription">
				<label>Endpoints</label>
				<p>Un Proveedor de Misim puede tener asociado un único endpoint.</p>
			</div>
			<div class="editContent">
				  	<p class="criteria">

						<span style="width: 350px;"> <label style="width: 130px;"
							class="fieldText">Endpoints: </label> 
							<s:select onchange="makeRequestEndpoint(); checkEndpoints(this);"
								id="proveedorMisim.endpoint.idEndpoint"
								name="proveedorMisim.endpoint.idEndpoint" emptyOption="true"
								theme="simple" labelposition="left" title="Endpoints"
								list="comboEndpointsProveedorMisim" listKey="codigo"
								listValue="descripcion" cssClass="" cssStyle="width:180px"
								value="%{proveedorMisim.endpoint.idEndpoint}" disabled="false" /> 
						</span>
					</p>
					
				  	<p class="criteria">

						<span style="width: 350px;"> <label id="proveedorMisim.comboComunicacionLabel" style="width: 130px;"
							class="fieldText">Comunicación (*): </label> 
							<s:select
								id="proveedorMisim.endpoint.comunicacion.idComunicacion"
								name="proveedorMisim.endpoint.comunicacion.idComunicacion" emptyOption="true"
								theme="simple" labelposition="left" title="Comunicaciones"
								list="comboComunicacionesEndpoints" listKey="codigo"
								listValue="descripcion" cssClass="" cssStyle="width:70px"
								value="%{proveedorMisim.endpoint.comunicacion.idComunicacion}" disabled="false" /> 
								<s:hidden
								name="idEndpoint" id="idEndpoint" value="%{idEndpoint}" />
						</span>
					</p>
					
					<p class="criteria">
					<label id="proveedorMisim.nombreEndpointLabel" style="width: 130px;" class="fieldText">Nombre Endpoint (*):</label>
					<s:textfield name="proveedorMisim.endpoint.nombre" value="%{proveedorMisim.endpoint.nombre}"
						id="proveedorMisim.endpoint.nombre" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
					</p>

					<p class="criteria">
					<label id="proveedorMisim.urlEndpointEndpointLabel" style="width: 130px;" class="fieldText">Url Endpoint (*):</label>
					<s:textfield name="proveedorMisim.endpoint.urlEndpoint" value="%{proveedorMisim.endpoint.urlEndpoint}"
						id="proveedorMisim.endpoint.urlEndpoint" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
					</p>
					
					<p class="criteria">
					<label id="proveedorMisim.serviceNameEndpointLabel" style="width: 130px;" class="fieldText">Service Name:</label>
					<s:textfield name="proveedorMisim.endpoint.serviceName" value="%{proveedorMisim.endpoint.serviceName}"
						id="proveedorMisim.endpoint.serviceName" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
					</p>
					
					<p class="criteria">
					<label id="proveedorMisim.portNameEndpointLabel" style="width: 130px;" class="fieldText">Port Name:</label>
					<s:textfield name="proveedorMisim.endpoint.portName" value="%{proveedorMisim.endpoint.portName}"
						id="proveedorMisim.endpoint.portName" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
					</p>
					
					<p class="criteria">
					<label id="proveedorMisim.targetNameEndpointLabel" style="width: 130px;" class="fieldText">Target Name:</label>
					<s:textfield name="proveedorMisim.endpoint.urlTargetName" value="%{proveedorMisim.endpoint.urlTargetName}"
						id="proveedorMisim.endpoint.urlTargetName" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
					</p>
					
					<p class="criteria">
					<label id="proveedorMisim.operationEndpointLabel" style="width: 130px;" class="fieldText">Operation:</label>
					<s:textfield name="proveedorMisim.endpoint.urlOperation" value="%{proveedorMisim.endpoint.urlOperation}"
						id="proveedorMisim.endpoint.urlOperation" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
					</p>
					
					<p class="criteria">
						<label id="proveedorMisim.timeoutEndpointLabel" style="width: 130px;" class="fieldText">Timeout:</label>
						<s:textfield name="proveedorMisim.endpoint.timeout"
							value="%{proveedorMisim.endpoint.timeout}" id="proveedorMisim.endpoint.timeout"
							theme="simple"
							onKeyPress="return numbersonly(this, event)"
							labelposition="left" size="6" maxlength="6" cssClass="" />
					</p>
					
					
			</div>
		</div>
		
				<div class="editContainer">
			<div class="nameDescription">
				<label>Transformaciones</label>
				<p>Un Proveedor de Misim puede tener asociado una única transformación.</p>
			</div>
			<div class="editContent">
					<p class="criteria">

			  			<span style="width: 350px;"> <label style="width: 130px;"
							class="fieldText">Transformaciones: </label> 
							<s:select onchange="makeRequestTransformacion(); checkTransformaciones(this);"
								id="proveedorMisim.transformacion.idTransformacion"
								name="proveedorMisim.transformacion.idTransformacion" emptyOption="true"
								theme="simple" labelposition="left" title="Transformaciones"
								list="comboTransformacionesProveedorMisim" listKey="codigo"
								listValue="descripcion" cssClass="" cssStyle="width:180px"
								value="%{proveedorMisim.transformacion.idTransformacion}" disabled="false" /> 
						</span>
					</p>
					
					<p class="criteria">
					<label id="proveedorMisim.nombreTransformacionLabel" style="width: 150px;" class="fieldText">Nombre Transformación (*):</label>
					<s:textfield name="proveedorMisim.transformacion.nombre" value="%{proveedorMisim.transformacion.nombre}"
						id="proveedorMisim.transformacion.nombre" theme="simple" labelposition="left" size="30"
						maxlength="255" cssClass="" />
					</p>
					
					<p class="criteria">
						<label id="proveedorMisim.xslPeticionLabel" style="width: 130px;" class="fieldText">XSL Petición (*):</label>
						<s:textarea name="proveedorMisim.transformacion.xslPeticion" id="proveedorMisim.transformacion.xslPeticion"
							theme="simple" rows="12" cols="70" value="%{proveedorMisim.transformacion.xslPeticion}">
						</s:textarea>
					</p>

					<p class="criteria">
						<label id="proveedorMisim.xslRespuestaLabel" style="width: 130px;" class="fieldText">XSL Respuesta (*):</label>
						<s:textarea name="proveedorMisim.transformacion.xslRespuesta" id="proveedorMisim.transformacion.xslRespuesta"
							theme="simple" rows="12" cols="70" value="%{proveedorMisim.transformacion.xslRespuesta}">
						</s:textarea>
					</p>
					
					<p class="criteria">
						<label id="proveedorMisim.xslFaultLabel" style="width: 130px;" class="fieldText">XSL Fault:</label>
						<s:textarea name="proveedorMisim.transformacion.xslFault" id="proveedorMisim.transformacion.xslFault"
							theme="simple" rows="12" cols="70" value="%{proveedorMisim.transformacion.xslFault}">
						</s:textarea>
					</p>				
			</div>
		</div>
		
		<script>
			makeRequestProducto();
			makeRequestEndpoint();
			makeRequestTransformacion();	
			checkProductos(document.getElementById("proveedorMisim.producto.idProducto"));	
			checkEndpoints(document.getElementById("proveedorMisim.endpoint.idEndpoint"));
			checkTransformaciones(document.getElementById("proveedorMisim.transformacion.idTransformacion"));			
		</script>
	</s:form>	
	
	<s:form id="formaddParametroProveedorMisim" theme="simple" validate="false" name="formaddParametroProveedorMisim" method="POST" action="addParametroProveedorMisim">
		     <div class="editContainer">
		         <div class="nameDescription">
		             <label>Configuración</label>
		              <p>Las opciones de configuración únicamente están habilitadas para los servicios REST</p>
		         </div>

		         <div class="editContent">
		            <p class="criteria">
		            	<s:if test="%{proveedorMisim.endpoint.comunicacion.idComunicacion == 2}">

		                <span style="width: 270px; height: 30px;">
		                    <label style="width: 60px;" class="fieldText">Parámetro:</label>
							<s:textfield name="parametroProveedor.parametro" value="%{parametroProveedor.parametro}"
								id="parametroProveedor.parametro" theme="simple" labelposition="left" size="30"
								maxlength="255" cssClass="" />
		                    <input type="hidden" id="parametroProveedor.idProveedor" name="parametroProveedor.idProveedor" value="${idProveedor}" />
		                         
		            	</span> 
		                <span>
		                    <label style="width: 50px;" class="fieldText">Elige:</label>
		                           
		                    <input type="radio" id="parametroProveedor.tipoValor" name="parametroProveedor.tipoValor" value="parametroProveedor.valor" checked="true" /> Valor
		                    <input type="radio" id="parametroProveedor.tipoValor" name="parametroProveedor.tipoValor" value="parametroProveedor.variable" /> Variable
		                    
		                    <s:textfield name="parametroProveedor.resultadoValor" value="%{parametroProveedor.resultadoValor}"
								id="parametroProveedor.valor" theme="simple" labelposition="left" size="30"
								maxlength="255" cssClass="" />
								   
			          	</span>
		                <a class="addLink" id="addItem" onclick="insertarNuevoParametro()" name="addItem">Añadir Item</a>
		                </s:if>
		                
		            	<s:else>
		                <span style="width: 270px; height: 30px;">
		                    <label style="width: 60px;" class="fieldText">Parámetro:</label>
							<s:textfield name="parametroProveedor.parametro" value="%{parametroProveedor.parametro}" disabled="true"
								id="parametroProveedor.parametro" theme="simple" labelposition="left" size="30"
								maxlength="255" cssClass="" />
		                    <input type="hidden" id="parametroProveedor.idProveedor" name="parametroProveedor.idProveedor" value="${idProveedor}" />
		                         
		            	</span> 
		                <span>
		                    <label style="width: 50px;" class="fieldText">Elige:</label>
		                           
		                    <input type="radio" id="parametroProveedor.tipoValor" name="parametroProveedor.tipoValor" value="parametroProveedor.valor" checked="true" disabled="true"/> Valor
		                    <input type="radio" id="parametroProveedor.tipoValor" name="parametroProveedor.tipoValor" value="parametroProveedor.variable" disabled="true"/> Variable
		                    
		                    <s:textfield name="parametroProveedor.resultadoValor" value="%{parametroProveedor.resultadoValor}" disabled="true"
								id="parametroProveedor.valor" theme="simple" labelposition="left" size="30"
								maxlength="255" cssClass="" />
								   
			          	</span>
		                </s:else>		                
		            </p>
		            
		            </s:form>
		            
		            <s:form id="formDeleteParametrosProveedorMisimSeleccionados" onsubmit="return confirmDeleteSelected();" theme="simple" validate="false" name="formDeleteParametrosProveedorMisimSeleccionados"
						method="POST" action="deleteParametrosProveedorMisimSelected">
		            
		            <script type="text/javascript">
                   	function checkBotonEliminarSeleccionados6(){
                       var listaChecks = document.getElementById('formDeleteParametrosProveedorMisimSeleccionados').checkDelListParametrosProveedorMisim;

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
                       var listaChecks = document.getElementById('formDeleteParametrosProveedorMisimSeleccionados').checkDelListParametrosProveedorMisim;
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
		            	      <s:if test="%{proveedorMisim.endpoint.comunicacion.idComunicacion == 2}">
		            	      	<th class=""><input type="checkbox" id="checkAllP"
								theme="simple" onclick="selectAllP(this)" /></th>
							  </s:if>
							  <s:else>
		            	      	<th class=""><input type="checkbox" id="checkAllP" disabled="true"
								theme="simple" onclick="selectAllP(this)" /></th>							  
							  </s:else>
									
		            	        <th class="TH200">Parámetro</th>
		            	        <th class="separator">Valor</th>
		            	        <th class="separator">Variable</th>
		            	        <th class="TH20 separator"></th>
		          	        </tr>
		          	    </thead>
			          	<tbody>
							<s:iterator value="%{listaParametrosProveedorMisim}"
								var="parametroProveedor" status="parametroProveedorStatus">
								<tr
									class="<s:if test='#parametroProveedorStatus.odd == true '></s:if><s:else>odd</s:else>">
									<td class="darkTD TH15">
									
										<input type="checkbox"
										onclick="checkBotonEliminarSeleccionados6()"
										id="checkDelListParametrosProveedorMisim"
										name="checkDelListParametrosProveedorMisim"
										value="${parametroProveedor.idParametrosProveedor}" />
										</center> 
										<input type="hidden"
										idd="__checkbox_checkDelListParametrosProveedorMisim"
										name="__checkbox_checkDelListParametrosProveedorMisim" />
																			
									<td><s:label value="%{parametro}" /></td>
									<td><s:label value="%{valor}" /></td>
									<td><s:label value="%{variable}" /></td>
									<td class="buttons"><span class="delete"> <a class="btnDelete" title="Eliminar" onclick="return confirmDelete();" href="deleteParametroProveedorMisim.action?idParametrosProveedor=${parametroProveedor.idParametrosProveedor}&idProveedor=${parametroProveedor.idProveedor}"></a> </span></td>
<!-- 									<td class="buttons"> -->
<!-- 										<span class="delete"> <a class="btnDelete" -->
<!-- 											onclick="return confirmDelete();" title="Eliminar" -->
<%-- 											href="deletePlanificacionOrganismo.action?idPlanificacion=${planificacionId}&idOrganismo=${organismo.organismoId}"></a> --%>
<!-- 										</span></td> -->
								</tr>
							</s:iterator>
							<s:if test="%{listaParametrosProveedorMisim == null}">
								<tr>
									<td colspan="5">No se ha configurado ningún parámetro para el proveedor MISIM</td>
								</tr>
<!-- 								<script>document.getElementById('checkAllP').style.visibility="hidden";</script> -->
							</s:if>
							<s:else>
								<tr>
								<tfoot>
									<td colspan="4"><span class="leftSide"> <s:submit
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
		          	  </div>
		          	  <!-- INTRODUCIR LA PROPIEDAD PARAMETROSERVIDOR.SERVIDORID COMO HIDDEN -->
		            </div>
		            <s:hidden name="idProveedor" id="idProveedor" value="%{idProveedor}" />
            </s:form>  
	<script>
	
		function insertarNuevoParametro() {
			document.formaddParametroProveedorMisim.submit();
		}
		
    </script>       					
</div>