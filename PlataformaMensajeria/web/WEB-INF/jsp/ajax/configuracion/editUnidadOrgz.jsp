<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>
<!-- ****************************************************************************** -->
<!-- Formulario                                                                     -->
<!-- ****************************************************************************** -->


<div id="contenedor_principal">
<s:form id="frmUpdateUnidadOrg" method="POST" action="updateUnidadOrg" validate="false"  theme="css_xhtml">
<fieldset>
		<table summary="Tabla formulario edición de Unidad Organizacional" cellpadding="0" cellspacing="4">
		<thead>
			<tr>
				<th headers="header0" colspan="2" align="left">
					<div class="titulobloque">
						<h2><s:text name="panels.template.unidades.mant" /></h2>
					</div>
				</th>
			</tr>
		</thead>
		<tbody>
		<tr>
			<!-- Id -->
			<td headers="header0"><s:textfield
				name="unidadOrganizacional.id" value="%{unidadOrganizacional.id}" id="unidadOrganizacional.id"
				theme="xhtml" 
				key="help.field.general.codigo" labelposition="left"
				 size="20" maxlength="255"
				cssClass="input_tablas_registro" readonly="true"/></td>
		</tr>
	
		<tr>
			<!-- Nombre -->
			<td headers="header0"><s:textfield
				name="unidadOrganizacional.nombre" value="%{unidadOrganizacional.nombre}" id="unidadOrganizacional.nombre"
				theme="xhtml" 
				key="field.general.nombre" labelposition="left"
				required="true" size="20" maxlength="255"
				cssClass="input_tablas_registro" /></td>
		</tr>
	
		<tr>
			<!-- Comunidad-->
			<td headers="header0"><framework:catalog headerKey="-1" headerValue=""  catalogname="comunidades" 
				dependentDropdownList="unidadOrganizacional.provincia,provinciasPorCA"
				id="comunidades" name="unidadOrganizacional.comunidad" value="%{unidadOrganizacional.comunidad}"
				key="field.test.ca" labelposition="left" 
				required="true" theme="xhtml"
				cssClass="input_tablas_registro" /></td>
		</tr>	
		<tr>
			<!-- Provincia-->
			<td headers="header0"><framework:catalog 
				dependentDropdownList="unidadOrganizacional.localidad,localidadesPorProvincia"
				id="unidadOrganizacional.provincia" name="unidadOrganizacional.provincia" value="%{unidadOrganizacional.provincia}"
				key="field.test.prov" labelposition="left" 
				required="true" theme="xhtml"
				cssClass="input_tablas_registro" /></td>
		</tr>	
		<tr>
			<!-- Localidad-->
			<td headers="header0"><framework:catalog 
				id="unidadOrganizacional.localidad" name="unidadOrganizacional.localidad" value="%{unidadOrganizacional.localidad}"
				key="field.configuracion.unidadesorg.localidad" labelposition="left" 
				required="true" theme="xhtml"
				cssClass="input_tablas_registro" /></td>
		</tr>	
	
		</tbody>
	</table>
</fieldset>
	
	<br>	
			
<!-- ****************************************************************************** -->
<!-- Botones																		-->
<!-- ****************************************************************************** -->
	&nbsp;
<div>
	<s:submit theme="simple" value="%{getText('buttons.text.save')}" cssClass="boton_template" />
</div>
<br>
<div >
	<s:url id="urlV" action="manageUnidadOrg" />
	<s:a href="%{urlV}" 
		labelposition="left">
		<s:text name="buttons.text.volver" />
	</s:a>
</div>
</s:form>
 </div>
		