<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<s:actionerror/>
<s:fielderror/>

<!-- ****************************************************************************** -->
<!-- Formulario                                                                     -->
<!-- ****************************************************************************** -->

<div id="contenedor_principal">
<s:form id="frmMantUsuario" method="POST" action="adminActualizarUsuario"
	validate="false" theme="css_xhtml">
	<s:hidden name="form.uid" value="%{form.uid}" />
    <s:hidden name="form.id" value="%{form.id}" />
<fieldset>
		<table summary="Tabla formulario criterios de búsqueda" cellpadding="0" cellspacing="4">
		<thead>
			<tr>
				<th headers="header0" colspan="2" align="left">
					<div class="titulobloque">
						<h2><s:text name="panels.datosGenerales" /></h2>
					</div>
				</th>
			</tr>
		</thead>

		<tbody>
			<tr>
				<!-- tipoUsuario -->
				<td headers="header0"><s:select
					id="sTipoUsuario" name="sTipoUsuario" 
					emptyOption="false" theme="css_xhtml" 
					key="usuario.tipoUsuario" labelposition="left"
					list="contextUsuarios" listKey="codigo"
					listValue="descripcion" cssClass="input_tablas_registro" 
					value="%{form.tipoUsuario}" disabled="true" />
					<s:hidden name="form.tipoUsuario" 
					value="%{form.tipoUsuario}" />
				</td>
				<!-- username -->
				<td headers="header0"><s:textfield
					name="form.username" required="true" theme="css_xhtml"
					cssClass="input_tablas_registro" size="10" maxlength="10" 
                    key="field.usuario.username" labelposition="left" readonly="true"/></td>
			</tr>
			<tr>
				<!-- nombre -->
				<td headers="header0"><s:textfield
					name="form.nombre" required="true" theme="css_xhtml"
					cssClass="input_tablas_registro" size="20" maxlength="15" 
					key="field.usuario.nombre" labelposition="left" /></td>
				<!-- apellidos -->
				<td headers="header0"><s:textfield
					name="form.apellidos" required="true" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.apellidos" labelposition="left" size="20"
					maxlength="20" /></td>
			</tr>
			<tr>
				<!-- descripcion --><!--
				<td headers="header0">
					<s:textfield name="form.description" required="false" 
					theme="css_xhtml" cssClass="input_tablas_registro" 
					key="field.usuario.descripcion" labelposition="left" 
					size="50" maxlength="45" /></td>
				--><td colspan="2"><s:textarea name="form.description" required="false" 
					theme="css_xhtml" cssClass="input_tablas_registro" 
					key="field.usuario.descripcion" labelposition="left" 
					rows="8" cols="80" /></td>
			</tr>
			<tr>
				<!-- Organismo -->
				<td headers="header0"><framework:catalog
					id="organismos" emptyOption="true" catalogname="organismos"
					name="form.organismo" value="%{form.organismo}"
					key="field.usuario.organismo" labelposition="left"
					cssClass="input_tablas_registro" theme="css_xhtml" /></td>
				<!-- Unidad Organizacional -->
				<td headers="header0"><framework:catalog
					id="unidadesOrganizacionales" emptyOption="true" catalogname="unidadesOrganizacionales"
					name="form.unidadOrganizacional" value="%{form.unidadOrganizacional}"
					key="field.usuario.unidadOrganizacional" labelposition="left"
					cssClass="input_tablas_registro" theme="css_xhtml" /></td>
			</tr><!--
			<tr>
				 eliminado 
				<td headers="header0"><s:checkbox
					name="form.eliminado" theme="css_xhtml"
					key="field.usuario.eliminado" labelposition="left"
					cssClass="input_tablas_registro" fieldValue="true" 
					value="form.eliminado" /></td>
				 password 
				<td headers="header0">&nbsp;</td>
			</tr>
			--><tr>
				<!-- telefono1 -->
				<td headers="header0"><s:textfield
					name="form.telefono" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.telefono" labelposition="left" size="10"
					maxlength="9" /></td>
				<!-- telefono2 -->
				<td headers="header0"><s:textfield
					name="form.telefono2" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.telefono2" labelposition="left" size="10"
					maxlength="9" /></td>
			</tr>
			<tr>
				<!-- movil -->
				<td headers="header0"><s:textfield
					name="form.movil" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.movil" labelposition="left" size="10"
					maxlength="9" /></td>
				<!-- direCalle -->
				<td headers="header0"><s:textfield
					name="form.direccion" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.direccion" labelposition="left" size="45"
					maxlength="45" /></td>
			</tr><!--
			<tr>
				 direNum 
				<td headers="header0"><s:textfield
					name="form.numero" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.numero" labelposition="left" size="5"
					maxlength="4" /></td>
				 direPiso 
				<td headers="header0"><s:textfield name="form.piso"
					required="false" theme="css_xhtml" cssClass="input_tablas_registro"
					key="field.usuario.piso" labelposition="left" size="15" maxlength="15"  /></td>
			</tr>
			<tr>
				 direPuerta 
				<td headers="header0"><s:textfield
					name="form.puerta" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.puerta" labelposition="left" size="15"
					maxlength="15"  /></td>
				 direEscalera 
				<td headers="header0"><s:textfield
					name="form.escalera" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.escalera" labelposition="left" size="15"
					maxlength="15"  /></td>
			</tr>
			--><tr>
				<!-- codPostal -->
				<td headers="header0"><s:textfield
					name="form.codigoPostal" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.codpostal" labelposition="left" size="6"
					maxlength="5"  /></td>
				<!-- direCiudad -->
				<td headers="header0"><s:textfield
					name="form.ciudad" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.ciudad" labelposition="left" size="45"
					maxlength="45"  /></td>
			</tr>
			<tr>
				<!-- provincia -->
				<td headers="header0"><s:textfield
					name="form.provincia" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.provincia" labelposition="left" size="25"
					maxlength="25"  /></td>
				<!-- pais --><!--
				<td headers="header0"><s:textfield name="form.pais"
					required="false" theme="css_xhtml" cssClass="input_tablas_registro"
					key="field.usuario.pais" labelposition="left" size="25" maxlength="25"  /></td>
			--></tr>
			<tr>
				<!-- nif -->
				<td headers="header0"><s:textfield name="form.nif"
					required="false" theme="css_xhtml" cssClass="input_tablas_registro"
					key="field.usuario.nif" labelposition="left" size="10" maxlength="9"  /></td>
				<!-- cif -->
				<td headers="header0"><s:textfield name="form.cif"
					required="false" theme="css_xhtml" cssClass="input_tablas_registro"
					key="field.usuario.cif" labelposition="left" size="15" maxlength="15" /></td>
			</tr>
			<tr>
				<!-- email -->
				<td headers="header0"><s:textfield
					name="form.email" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.email" labelposition="left" size="45"
					maxlength="45" /></td>
				<!-- fax -->
				<td headers="header0"><s:textfield name="form.fax"
					required="false" theme="css_xhtml" cssClass="input_tablas_registro"
					key="field.usuario.fax" labelposition="left" size="10" maxlength="9"  /></td>
			</tr>
			<tr>
				<!-- despacho -->
				<td headers="header0"><s:textfield
					name="form.despacho" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.despacho" labelposition="left" size="45"
					maxlength="45" /></td>
				<!-- puntosContacto --><!--
				<td headers="header0"><s:textfield
					name="form.puntosContacto" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.puntosContacto" labelposition="left" size="45"
					maxlength="45" /></td>
			--></tr><!--
			<tr>
				 email2 
				<td headers="header0"><s:textfield
					name="form.email2" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.email2" labelposition="left" size="45"
					maxlength="45" /></td>
				 mailNoticias 
				<td headers="header0"><s:textfield
					name="form.mailNoticias" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.mailNoticias" labelposition="left" size="45"
					maxlength="45" /></td>
			</tr>
			<tr>
				 mailNovedades 
				<td headers="header0"><s:textfield
					name="form.mailNovedades" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.mailNovedades" labelposition="left" size="45"
					maxlength="45"  /></td>
				 mailContenidosInf 
				<td headers="header0"><s:textfield
					name="form.mailContenidos" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.mailContenidosInf" labelposition="left" size="45"
					maxlength="45"  /></td>
			</tr>
			<tr>
				 mailServicios 
				<td headers="header0"><s:textfield
					name="form.mailServicios" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.mailServicios" labelposition="left" size="45"
					maxlength="45"  /></td>
				 mailCampanas 
				<td headers="header0"><s:textfield
					name="form.mailCampanias" required="false" theme="css_xhtml"
					cssClass="input_tablas_registro" 
					key="field.usuario.mailCampanas" labelposition="left" size="45"
					maxlength="45"  /></td>
			</tr>
			--></tbody>
		</table>
	</fieldset>
	
	<br>

	<fieldset>
	<!-- DIV GRUPOS SCROLL -->
		<table summary="Tabla con los grupos del usuario" cellpadding="0" cellspacing="4">
		<thead>
			<tr>
				<th headers="header0" colspan="2" align="left">
					<div class="titulobloque">
						<h2><s:text name="panels.Grupos" /></h2>
					</div>
				</th>				
			</tr>
		</thead>

		<tbody>
			<tr>
				<td headers="header0">
					<s:checkboxlist name="form.checkBoxGruposSeleccionados"
					cssStyle="input_tablas_registro" theme="css_xhtml" 
					list="form.checkBoxGrupos" />
				</td>
			</tr>
		</tbody>
		</table>
	<!-- DIV GRUPOS Scroll -->
	</fieldset>
	
	<br>

	<!-- ****************************************************************************** -->
	<!-- Botones																		-->
	<!-- ****************************************************************************** -->
<div>    
<s:submit theme="simple" value="%{getText('buttons.text.save')}" cssClass="boton_template" /> 
<s:submit theme="simple" value="%{getText('buttons.text.changepassword')}" cssClass="boton_template" action="adminCambiarPasswordUsuario" /> 
</div>
	&nbsp;
    <div >
    <s:url id="url"
	action="adminUsuarios"/><s:a href="%{url}"  
	labelposition="left" ><s:text name="buttons.text.volver" /></s:a>
    </div>
</s:form>
 </div>