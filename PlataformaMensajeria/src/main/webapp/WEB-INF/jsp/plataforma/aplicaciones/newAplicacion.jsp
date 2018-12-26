<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
	<s:form id="frmNuevaAplicacion" method="POST" action="saveAplicacion"
		theme="simple">
		<h3 class="pageNameButtons">
			<span class="floatRight"> <s:submit theme="simple"
					value="%{getText('buttons.text.save')}" cssClass="button" /> <input
				type="button" onclick="javascript:location.href='${volver}'"
				class="button" value="Volver">
			</span> <label>CREACI�N APLICACI�N </label>
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
					<label style="width: 140px;" class="fieldText">Nombre (*):</label>
					<s:textfield name="aplicacion.nombre" value="%{aplicacion.nombre}"
						id="aplicacion.nombre" theme="simple" labelposition="left"
						size="85" maxlength="255" cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label theme="simple" style="width: 120px;" class="fieldText">Descripci�n
						(*):</label>
					<s:textarea theme="simple" rows="6" cols="82"
						name="aplicacion.descripcion" labelposition="left"
						id="aplicacion.descripcion" value="%{aplicacion.descripcion}" />

				</p>
				<p class="criteria">
					<label theme="simple" style="width: 120px;" class="fieldText">Activo:</label>
					<s:checkbox theme="simple" name="newActivo"
						id="newActivo" value="%{newActivo}"></s:checkbox>
				</p>
				<p class="criteria">
					<label style="width: 150px;" class="fieldText"><i>(*)
							Campos obligatorios</i></label>
				</p>
			</div>
		</div>
		<div class="editContainer">
			<div class="nameDescription">
				<label>Seguridad</label>
			</div>
			<div class="editContent">
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Usuario (*):</label>
					<s:textfield name="aplicacion.usuario"
						value="%{aplicacion.usuario}" id="aplicacion.usuario"
						theme="simple" labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Contrase�a
						(*):</label>
					<s:password name="aplicacion.password"
						value="%{aplicacion.password}" id="aplicacion.password"
						theme="simple" labelposition="left" size="45"
						maxlength="255" cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Rep.
						Contrase�a (*):</label>
					<s:password name="aplicacion.rePassword" 
						value="%{aplicacion.rePassword}" id="aplicacion.rePassword"
						theme="simple" labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
					<s:hidden name="checkPassword" id="checkPassword" value="true" />
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
					<label class="fieldText" style="width: 140px;">Responsable t�cnico: (*)</label>
					<s:textfield name="aplicacion.respTecnicoNombre"
						value="%{aplicacion.respTecnicoNombre}"
						id="aplicacion.respTecnicoNombre" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Email: (*)</label>
					<s:textfield name="aplicacion.respTecnicoEmail"
						value="%{aplicacion.respTecnicoEmail}"
						id="aplicacion.respTecnicoEmail" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>				
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Responsable funcional: (*)</label>
					<s:textfield name="aplicacion.respFuncionalNombre"
						value="%{aplicacion.respFuncionalNombre}"
						id="aplicacion.respFuncionalNombre" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
				</p>
				<p class="criteria">
					<label class="fieldText" style="width: 140px;">Email: (*)</label>
					<s:textfield name="aplicacion.respFuncionalEmail"
						value="%{aplicacion.respFuncionalEmail}"
						id="aplicacion.respFuncionalEmail" theme="simple"
						labelposition="left" size="45" maxlength="255"
						cssClass="input_tablas_registro" />
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
			<label>Auditor�a</label>
		</div>
		<div class="editContent">
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 140px;"
					class="fieldText">Creador:</label> <strong><s:label
							theme="simple" id="aplicacion.creadopor"
							name="aplicacion.creadopor" value="%{aplicacion.creadopor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						Creaci�n:</label> <strong><s:label theme="simple"
							id="aplicacion.fechacreacion" name="aplicacion.fechacreacion"
							value="%{aplicacion.fechacreacion}" /></strong>
				</span>
			</p>
			<p class="criteria">
				<span style="width: 340px;"> <label style="width: 140px;"
					class="fieldText">�ltimo Modificador:</label> <strong><s:label
							theme="simple" id="aplicacion.modificadopor"
							name="aplicacion.modificadopor"
							value="%{aplicacion.modificadopor}" /></strong>
				</span> <span> <label style="width: 150px;" class="fieldText">Fecha
						�ltima Modificaci�n:</label> <strong><s:label theme="simple"
							id="aplicacion.fechamodificacion"
							name="proveedorSMS.fechamodificacion"
							value="%{aplicacion.fechamodificacion}" /></strong>
				</span>
			</p>
		</div>
	</div>

	<script type="text/javascript">
		var oDiv = document.getElementById('aplicacion.password');
		oDiv
				.addEventListener(
						'keyup',
						function(e) {
							document.getElementById('repPassLabel').style.visibility = "visible";
							if (document.getElementById('aplicacion.password').value != document
									.getElementById('repPassLabel').value) {
								document.getElementById('checkPassword').value = "true";
							}
						}, true);
	</script>
</div>
