<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
	<s:form id="frmEditPlanificacion" method="POST" action="updateContactos"
		validate="false" theme="simple" cssClass="">
		<input type="hidden" name="contacto.contactoId" id="contacto.contactoId"	value="${idContacto}" />
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:submit theme="simple"
					value="%{getText('buttons.text.save')}" cssClass="button" /> <input
				type="button" onclick="javascript:location.href='${volver}'"
				class="button" value="Volver">
			</span> <label>EDICIÓN DE CONTACTOS </label>
		</h3>
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp"%>
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
						theme="simple" style="width:300px;" size="50" cssClass="" />
				</p>
				<!-- 				Aplicacion -->
				<p class="criteria">
					<label class="fieldText" style="width: 120px;">Aplicación:</label>
					<s:select 
						id="contacto.aplicacionid" name="contacto.aplicacionid" 
						emptyOption="true" theme="simple" 
						labelposition="left" 
						list="comboAplicaciones" listKey="codigo"
						listValue="descripcion" cssClass="W240"						
						value="%{contacto.aplicacionid}" disabled="false" onchange="cargarNuevaAplicacion()"/>
					
				</p>
				<!-- 				Servicio -->
				<p class="criteria">
					<label class="fieldText" style="width: 120px;">Servicio:</label>
					 <s:select 
						id="sid" name="contacto.servicioid" 
						emptyOption="true" theme="simple"
						labelposition="left"
						list="comboServicios" listKey="codigo" 
						listValue="descripcion" cssClass="W240"						
						value="%{contacto.servicioid}" disabled="false" />
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
                        <span style="width: 340px;">
                            <label style="width: 120px;" class="fieldText">Creador:</label>
                            <strong><s:label theme="simple" id="contacto.creadopor" name="contacto.creadopor" value="%{contacto.creadopor}"/></strong>
                        </span>
                        <span>
                            <label style="width: 150px;" class="fieldText">Fecha Creación:</label>
                            <strong><s:label theme="simple" id="contacto.fechacreacion" name="contacto.fechacreacion" value="%{contacto.fechacreacion}"/></strong>
                        </span>
                    </p>
               
              	 <p class="criteria">
                        <span style="width: 340px;">
                            <label style="width: 120px;" class="fieldText">Último Modificador:</label>
                            <strong><s:label theme="simple" id="contacto.modificadopor" name="contacto.modificadopor" value="%{contacto.modificadopor}"/></strong>
                        </span>
                        <span>
                            <label style="width: 150px;" class="fieldText">Fecha Última Modificación:</label>
                            <strong><s:label theme="simple" id="contacto.fechamodificacion" name="contacto.fechamodificacion" value="%{contacto.fechamodificacion}"/></strong>
                        </span>
                    </p>
             
                </div>
            </div>    	
</div>
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