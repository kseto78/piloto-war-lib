<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>
<!-- ****************************************************************************** -->
<!-- Formulario                                                                     -->
<!-- ****************************************************************************** -->


<div id="contenedor_principal">
<s:form id="frmUpdateOrganismo" method="POST" action="updateOrganismo" validate="false"  theme="css_xhtml">
<fieldset>
		<table summary="Tabla formulario edición organismo" cellpadding="0" cellspacing="4">
		<thead>
			<tr>
				<th headers="header0" colspan="2" align="left">
					<div class="titulobloque">
						<h2><s:text name="panels.template.organismos.mant" /></h2>
					</div>
				</th>
			</tr>
		</thead>
		<tbody>
		
				<tr>
					<!-- Id del Organismo -->
					<td headers="header0"><s:textfield
						name="organismo.id" value="%{organismo.id}" id="organismo.nombre"
						theme="xhtml" 
						key="help.field.general.codigo" labelposition="left"
						 size="20" maxlength="255"
						cssClass="input_tablas_registro" readonly="true"/></td>
				</tr>
				<tr>
					<!-- Nombre del Organismo -->
					<td headers="header0"><s:textfield
						name="organismo.nombre" value="%{organismo.nombre}" id="organismo.nombre"
						theme="xhtml" 
						key="field.general.nombre" labelposition="left"
						required="true" size="20" maxlength="255"
						cssClass="input_tablas_registro" /></td>
				</tr>

				<tr>
					<!-- Rol-->
					<td headers="header0"><s:textfield
						name="organismo.rol" theme="xhtml" value="%{organismo.rol}"
						key="field.organismo.rol" labelposition="left"
						required="false" size="20" maxlength="255"
						cssClass="input_tablas_registro" /></td>
				</tr>

				<tr>
					<!-- Organismo Dependiente-->
					<td headers="header0"><framework:catalog emptyOption="true" catalogname="organismos" 
						name="organismo.organismoPadre" theme="xhtml" value="%{organismo.organismoPadre}"
						key="field.organismo.organismoPadre" labelposition="left" 
						required="false" id="organismo.dependiente"
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
<div>
    <s:url id="urlV" action="manageOrganismo" />
	<s:a href="%{urlV}" 
		labelposition="left">
		<s:text name="buttons.text.volver" />
	</s:a>
	
</div>
</s:form>
 </div>