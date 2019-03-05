<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
	<s:form id="frmNuevOrganismo" method="POST" action="salvePdpDiputaciones"
		theme="simple">
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:submit theme="simple"
					value="%{getText('buttons.text.save')}" cssClass="button" /> <input
				type="button" onclick="javascript:location.href='${volver}'"
				class="button" value="Volver">
			</span> <label>CREACI�N PDP-DIPUTACI�N</label>
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
					<s:textfield name="organismo.nombre" value="%{organismo.nombre}"
						id="organismo.nombre" theme="simple" labelposition="left"
						size="70" maxlength="255" cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label theme="simple" style="width: 120px;" class="fieldText">Descripci�n:</label>
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
		</div
	</s:form>
	
	<div class="editContainer">
		<div class="nameDescription">
			<label>Auditor�a</label>
		</div>
		<div class="editContent">
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 140px;"
					class="fieldText">Creador:</label> <strong><s:label
							theme="simple" id="organismo.creadopor"
							name="organismo.creadopor" value="%{organismo.creadopor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Creaci�n:</label> <strong><s:label theme="simple"
							id="organismo.fechacreacion" name="organismo.fechacreacion"
							value="%{organismo.fechaCreacion}" /></strong>
				</span>
			</p>
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 140px;"
					class="fieldText">�ltimo Modificador:</label> <strong><s:label
							theme="simple" id="organismo.modificadopor"
							name="organismo.modificadopor"
							value="%{organismo.modificadopor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						�ltima Modificaci�n:</label> <strong><s:label theme="simple"
							id="organismo.fechamodificacion"
							name="organismo.fechamodificacion"
							value="%{organismo.fechamodificacion}" /></strong>
				</span>
			</p>
		</div>
	</div>
</div>
