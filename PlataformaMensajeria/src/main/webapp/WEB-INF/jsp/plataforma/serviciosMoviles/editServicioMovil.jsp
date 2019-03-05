<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>	
</plataforma:securityRedirect>
<div class="mainContent">
	<s:form id="frmEditServicioMovil" method="POST" action="updateServicioMovil" enctype="multipart/form-data"
		validate="false" theme="simple" cssClass="">
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:submit theme="simple"
					value="%{getText('buttons.text.save')}" cssClass="button"
					alt="%{getText('buttons.text.save')}" /> 
			<input type="button"
				onclick="javascript:location.href='${volver}'" class="button"
				value="Volver">
			</span> <label>EDICIÓN SERVICIOS MÓVILES </label>
		</h3>
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp"%>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp"%>
		
		<div class="editContainer">
			<div class="nameDescription">
				<label>Datos Generales</label>
			</div>
			<div class="editContent">
			
				<p class="criteria">
					<s:hidden theme="simple" id="servicioMovil.servicioMovilId"
						name="servicioMovil.servicioMovilId" value="%{servicioMovil.servicioMovilId}" />
					<s:hidden theme="simple" id="idServicioMovil" name="idServicioMovil"
						value="%{servicioMovil.servicioMovilId}" />
						
					<label style="width: 120px;" class="fieldText">Nombre (*):</label>
					<s:textfield name="servicioMovil.nombre" value="%{servicioMovil.nombre}"
						id="servicioMovil.nombre" theme="simple" labelposition="left"
						size="70" maxlength="255" cssClass="input_tablas_registro" />
				</p>
				
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Descripción (*):</label>
					<s:textarea name="servicioMovil.descripcion" id="servicioMovil.descripcion"
						theme="simple" rows="6" cols="70" value="%{servicioMovil.descripcion}">
					</s:textarea>
				</p>
				
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">URL del Servicio:</label>
					<s:textfield name="servicioMovil.urlServicio" value="%{servicioMovil.urlServicio}"
						id="servicioMovil.urlServicio" theme="simple" labelposition="left"
						size="70" maxlength="255" cssClass="input_tablas_registro" />
				</p>
				
				<p class="criteria">
					<label style="width: 120px; height: 20px;" class="fieldText">URL del Aviso de la Suscripción:</label>
					<s:textfield name="servicioMovil.urlAvisoSuscripcion" value="%{servicioMovil.urlAvisoSuscripcion}"
						id="servicioMovil.urlAvisoSuscripcion" theme="simple" labelposition="left"
						size="70" maxlength="255" cssClass="input_tablas_registro" />
				</p>
				
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Usuario Endpoint:</label>
					<s:textfield name="servicioMovil.endpointUser" value="%{servicioMovil.endpointUser}"
						id="servicioMovil.endpointUser" theme="simple" labelposition="left"
						size="70" maxlength="255" cssClass="input_tablas_registro" />
				</p>
				
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Password Endpoint:</label>
					<s:textfield name="servicioMovil.endpointPass" value="%{servicioMovil.endpointPass}"
						id="servicioMovil.endpointPass" theme="simple" labelposition="left"
						size="70" maxlength="255" cssClass="input_tablas_registro" />
				</p>
				
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Imagen Servicio Móvil:</label>
					<s:if test="%{servicioMovil.icono != null}">
						<img style="width:60px; height:60px;" id="servicioMovil.imagen" src="data:image/jpeg;base64,${servicioMovil.icono}"/>				
						<span class="delete"> 
						<a class="btnDelete" onclick="return confirmDelete();"
						href="deleteImagen.action?idServicioMovil=${servicioMovil.servicioMovilId}"></a>
						</span>
					</s:if>
					<s:else>
					<input type="file" name="servicioMovil.icono" id="servicioMovil.icono" value="%{servicioMovil.icono}" onchange="subidaFichero()"/>
					</s:else>
				</p>
								
				<p class="criteria">
					<label style="width: 130px;" class="fieldText">Tipo Servicio Móvil:</label>
					<s:select name="servicioMovil.tipo" id="servicioMovil.tipo"
						emptyOption="false" theme="simple" labelposition="left"
						list="comboTiposServicios" listKey="codigo" 
						listValue="descripcion" cssStyle="width:140px" cssClass=""
						value="%{servicioMovil.tipo}" disabled="false"  />
				</p>
				
				<p class="criteria">
					<label theme="simple" style="width: 130px;" class="fieldText">Activo:</label>
					<s:checkbox theme="simple" name="servicioMovil.isEstado"
						id="servicioMovil.isEstado" value="%{servicioMovil.activado}"></s:checkbox>
				</p>
				
				<p class="criteria">
					<label theme="simple" style="width: 130px; height: 20px;" class="fieldText">Deseo recibir usuarios suscritos:</label>
					<s:checkbox theme="simple" name="servicioMovil.isIndSuscripcion" 
						id="servicioMovil.isIndSuscripcion" value="%{servicioMovil.suscrito}"></s:checkbox>
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
					<label class="fieldText" style="width: 140px;">Nombre Contacto:</label>
					<s:textfield name="servicioMovil.nombreContacto"
						value="%{servicioMovil.nombreContacto}"
						id="servicioMovil.nombreContacto" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Teléfono Contacto:</label>
					<s:textfield name="servicioMovil.telefonoContacto"
						value="%{servicioMovil.telefonoContacto}"
						id="servicioMovil.telefonoContacto" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>		
			</div>
		</div>
		
	<s:form id="formDeleteServicioMovilUsuarioSelected"
			onsubmit="return confirmDeleteSelected();" theme="simple"
			validate="false" name="formDeleteServicioMovilUsuarioSelected" method="POST"
			action="deleteServicioMovilUsuarioSelected">		
	
	<div class="editContainer">
			<div class="nameDescription">
				<label>Usuarios</label>
				<p>Un servicio movil puede tener asociado un conjunto ilimitado de
					usuarios.</p>
			</div>
			<div class="editContent">
				<display:table 
				id="tableId"
				summary="Tabla de resultados de búsqueda de usuarios del servicio movil"
				name="listaUsuariosServicioMovil" 
				
				class="" 
				pagesize="${pageSize}"
				requestURI="" 
				defaultsort="1" 
				defaultorder="ascending" 
				sort="external"
				export="true" 
				cellpadding="0" 
				cellspacing="0" 
				partialList="true"
				size='<%=request.getAttribute("totalSize")%>'
				decorator="es.mpr.template.web.decorators.TableWrapper">
				<display:setProperty name="css.tr.even" value="null" />
				<display:setProperty name="css.tr.odd"  value="odd" />
					
				<display:column property="nombreUsuario" titleKey="field.servicioMovilUsuario.nombreUsuario" sortable="false"
					headerClass="TH150 center" class="" style="text-align:center;"/>	
					
				<display:column property="nombre" titleKey="field.servicioMovilUsuario.nomUser" sortable="false"
					headerClass="TH150 separator center" class="" style="text-align:center;"/>
					
				<display:column property="apellido1" titleKey="field.servicioMovilUsuario.apellido1" sortable="false"
					headerClass="TH150 separator center" class="" style="text-align:center;"/>
				
				<display:column property="apellido2" titleKey="field.servicioMovilUsuario.apellido2" sortable="false"
					headerClass="TH160 separator center" class="" style="text-align:center;"/>
				
				<display:column property="dispositivoId" titleKey="field.servicioMovilUsuario.dispositivoId" sortable="false"
					headerClass="TH100 separator center" class="" style="text-align:center;"/>	
					
				<display:column property="plataforma" titleKey="field.servicioMovilUsuario.nomPlat" sortable="false"
					headerClass="TH90 separator center" class="" style="text-align:center;"/>	
					
<!-- 	<tbody> -->
<%-- 		<s:iterator value="%{listaUsuariosServicioMovil}" var="usuario" --%>
<%-- 			status="usuarioStatus"> --%>
<%-- 				<tr class="<s:if test='#usuarioStatus.odd == true '>odd</s:if>"> --%>
<%-- 					<td><s:label value="%{nombreUsuario}" theme="simple" /></td> --%>
<%-- 					<td><s:label value="%{nomUser}" theme="simple" /></td> --%>
<%-- 					<td><s:label value="%{apellido1}" theme="simple" /></td> --%>
<%-- 					<td><s:label value="%{apellido2}" theme="simple" /></td> --%>
<%-- 					<td><s:label value="%{dispositivoId}" theme="simple" /></td> --%>
<%-- 					<td><s:label value="%{nomPlat}" theme="simple" /></td> --%>
<!-- 				</tr> -->
<%-- 		</s:iterator> --%>

<%-- 			<s:if test="%{listaUsuariosServicioMovil == null}"> --%>
<!-- 				<tr> -->
<!-- 					<td colspan="6">No existen usuarios suscritos</td> -->
<!-- 				</tr> -->
<%-- 			</s:if> --%>
<!-- 	</tbody> -->
</display:table>
		<s:hidden name="idServicioMovil" id="idServicioMovil" value="%{idServicioMovil}" />
		</div>
	</div>
</s:form>

	<div class="editContainer">
		<div class="nameDescription">
			<label>Auditoría</label>
		</div>
		<div class="editContent">
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 140px;"
					class="fieldText">Creador:</label> <strong><s:label
							theme="simple" id="servicioMovil.creadoPor"
							name="servicioMovil.creadoPor" value="%{servicioMovil.creadoPor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Creación:</label> <strong><s:label theme="simple"
							id="servicioMovil.fechaCreacion" name="servicioMovil.fechaCreacion"
							value="%{servicioMovil.fechaCreacion}" /></strong>
				</span>
			</p>
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 140px;"
					class="fieldText">Último Modificador:</label> <strong><s:label
							theme="simple" id="servicioMovil.modificadoPor"
							name="servicioMovil.modificadoPor" value="%{servicioMovil.modificadoPor}" />
					</strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Última Modificación:</label> <strong><s:label theme="simple"
							id="servicioMovil.fechaModificacion"
							name="servicioMovil.fechaModificacion"
							value="%{servicioMovil.fechaModificacion}" /></strong>
				</span>
			</p>
		</div>
	</div>
</s:form>	
</div>
<script>
function subidaFichero(){
	   if(document.getElementById('servicioMovil.icono').value.split('.').pop() == "exe"){
		  alert("El tipo de fichero no puede ser de tipo exe.");
		  document.getElementById('servicioMovil.icono').value = null; 
 	}        	   
}
</script>