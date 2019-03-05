<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
	<s:form id="frmNuevoServicioMovil" method="POST" action="salveServicioMovil" enctype="multipart/form-data"
		theme="simple">
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:submit theme="simple"
					value="%{getText('buttons.text.save')}" cssClass="button" /> <input
				type="button" onclick="javascript:location.href='${volver}'"
				class="button" value="Volver">
			</span> <label>CREACIÓN SERVICIO MÓVIL </label>
		</h3>
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
					<label style="width: 120px; height: 20px;" class="fieldText">URL del Aviso de la Suscripcion:</label>
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
								name="servicioMovil.modificadoPor" value="%{servicioMovil.modificadoPor}" /></strong>
					</span> <span> <label style="width: 150px;" class="fieldText">Fecha
							Última Modificación:</label> <strong><s:label theme="simple"
								id="servicioMovil.fechaModificacion"
								name="servicioMovil.fechaModificacion"
								value="%{servicioMovil.fechaModificacion}" /></strong>
					</span>
				</p>
			</div>
		</div>
<script>
function subidaFichero(){
	   if(document.getElementById('servicioMovil.icono').value.split('.').pop() == "exe"){
		  alert("El tipo de fichero no puede ser de tipo exe.");
		  document.getElementById('servicioMovil.icono').value = null; 
 	}        	   
}
</script>