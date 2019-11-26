<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
	<s:form id="frmEditPlanificacion" method="POST" action="saveContacto"
		validate="false" cssClass="" theme="simple">
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:submit theme="simple"
					value="%{getText('buttons.text.save')}" cssClass="button" /> <input
				type="button" onclick="javascript:location.href='${volver}'"
				class="button" value="Volver">
			</span> <label>CREACIÓN DE CONTACTOS </label>
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
					<!-- 				Nombre -->
				<p class="criteria">
					<label class="fieldText" style="width: 120px;">Nombre (*):</label>
					<s:textfield name="contacto.nombre" value="%{contacto.nombre}"
						id="contacto.nombre" theme="simple" style="width:300px;" size="50"
						cssClass="" />
				</p>
				<!-- 				Apellidos -->
				<p class="criteria">
					<label class="fieldText" style="width: 120px;">Apellidos:</label>
					<s:textfield name="contacto.apellidos"
						value="%{contacto.apellidos}" id="contacto.apellidos"
						theme="simple" style="width:300px;" size="50" cssClass="" />
				</p>
				<!-- 				Email -->
				<p class="criteria">
					<label class="fieldText" style="width: 120px;">E-mail:</label>
					<s:textfield name="contacto.email" value="%{contacto.email}"
						id="contacto.email" theme="simple" style="width:300px;" size="50"
						cssClass="" />
				</p>
				<!-- 				Telefono -->
				<p class="criteria">
					<label class="fieldText" style="width: 120px;">Teléfono:</label>
					<s:textfield name="contacto.telefono" value="%{contacto.telefono}"
						id="contacto.telefono" theme="simple" style="width:300px;"
						size="50" cssClass="" />
				</p>

				<!-- Organismo -->
				<p class="criteria">
					<label class="fieldText" style="width: 120px;">Organismo:</label>
					<s:textfield name="contacto.organismo"
						value="%{contacto.organismo}" id="contacto.organismo"
						theme="simple" style="width:300px;" size="" cssClass="width:150px" />
				</p>
				<!-- 				Aplicacion -->
				<p class="criteria">
					<label class="fieldText" style="width: 120px;">Aplicación:</label>

					<s:select id="contacto.aplicacionid" name="contacto.aplicacionid"
						emptyOption="true" theme="simple" labelposition="left"
						list="comboAplicaciones" listKey="codigo" listValue="descripcion"
						cssStyle="width:150px" cssClass=""
						value="%{contacto.aplicacionid}" disabled="false"
						onchange="cargarNuevaAplicacion()" disable="%{readonly}" />

					<%-- 					<s:textfield name="contacto.nombreAplicacion" value="%{contacto.nombreAplicacion}" --%>
					<%-- 						id="contacto.nombreAplicacion" theme="simple" style="width:300px;" size="50" --%>
					<%-- 						cssClass="" /> --%>
				</p>
				<!-- 				Servicio -->
				<p class="criteria">
					<label class="fieldText" style="width: 120px;">Servicio:</label>

					<s:select id="sid" name="contacto.servicioid" emptyOption="false" theme="simple"
						onchange="setValue(this);" labelposition="left"
						list="comboServicios" listKey="codigo" headerValue="" headerKey=""
						listValue="descripcion" cssClass="" cssStyle="width:150px"
						value="%{contacto.servicioid}" />

					<%-- 					<s:textfield name="contacto.nombreServicio" value="%{contacto.nombreServicio}" --%>
					<%-- 						id="contacto.nombreServicio" theme="simple" style="width:300px;" size="50" --%>
					<%-- 						cssClass="" /> --%>
				</p>
			

			</div>
		</div>

		<div class="editContainer">
			<div class="nameDescription">
				<label>Auditoría</label>
			</div>
			<div class="editContent">
				<p class="criteria">
					<span style="width: 340px;"> <label style="width: 120px;"
						class="fieldText">Creador:</label> <strong><s:label
								theme="simple" id="contacto.creadoPor" name="contacto.creadoPor"
								value="%{contacto.creadoPor}" /></strong>
					</span> <span> <label style="width: 150px;" class="fieldText">Fecha
							Creación:</label> <strong><s:label theme="simple"
								id="contacto.fechaCreacion" name="contacto.fechaCreacion"
								value="%{contacto.fechaCreacion}" /></strong>
					</span>
				</p>

			</div>
		</div>
	</s:form>
</div>
<s:form id="loadUsuarioFromLDAP" name="loadUsuarioFromLDAP"
	action="loadUserByName" theme="simple">
	<input type="hidden" name="userNameToLoad" id="userNameToLoad" />
</s:form>
<script>
function cargarNuevaAplicacion(){
	
	$('#sid option').each(function() {
		        $(this).remove();
		});
	$.ajax({
	  type: "POST",
	  url: "ajaxLoadServicios.action",
	  data: {idAplicacion:document.getElementById('contacto.aplicacionid').value}, // serializes the form's elements.
	  success: function(data)
	  {
		  var items = data.items;
		 
		 $.each(items, function (i, item) {
			$('#sid').append($('<option>', { 
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
</script>