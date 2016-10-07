<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
	<s:form id="frmNuevOrganismo" method="POST" action="salveOrganismo"
		theme="simple">
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:submit theme="simple"
					value="%{getText('buttons.text.save')}" cssClass="button" /> <input
				type="button" onclick="javascript:location.href='${volver}'"
				class="button" value="Volver">
			</span> <label>CREACIÓN ORGANISMO </label>
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
					<label style="width: 120px;" class="fieldText">Código Organismo (*):</label>
					<s:textfield name="organismo.DIR3" value="%{organismo.DIR3}"
						id="organismo.DIR3" theme="simple" labelposition="left"
						size="70" maxlength="255" cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label style="width: 120px;" class="fieldText">Nombre (*):</label>
					<s:textfield name="organismo.nombre" value="%{organismo.nombre}"
						id="organismo.nombre" theme="simple" labelposition="left"
						size="70" maxlength="255" cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label theme="simple" style="width: 120px;" class="fieldText">Descripción:</label>
					<s:textarea theme="simple" rows="6" cols="70"
						name="organismo.descripcion" labelposition="left"
						id="organismo.descripcion" value="%{organismo.descripcion}" />

				</p>
				<p class="criteria">
					<label theme="simple" style="width: 120px;" class="fieldText">Activo:</label>
					<s:checkbox theme="simple" name="organismo.isActivo"
						id="organismo.isActivo" value="%{organismo.activado}"></s:checkbox>
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
							theme="simple" id="organismo.creadoPor"
							name="organismo.creadoPor" value="%{organismo.creadoPor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Creación:</label> <strong><s:label theme="simple"
							id="organismo.fechaCreacion" name="organismo.fechaCreacion"
							value="%{organismo.fechaCreacion}" /></strong>
				</span>
			</p>
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 140px;"
					class="fieldText">Último Modificador:</label> <strong><s:label
							theme="simple" id="organismo.modificadoPor"
							name="organismo.modificadoPor"
							value="%{organismo.modificadoPor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Última Modificación:</label> <strong><s:label theme="simple"
							id="organismo.fechaModificacion"
							name="organismo.fechaModificacion"
							value="%{organismo.fechaModificacion}" /></strong>
				</span>
			</p>
		</div>
	</div>

	
</div>
