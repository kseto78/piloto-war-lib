<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<!-- ****************************************************************************** -->
<!-- Formulario                                                                     -->
<!-- ****************************************************************************** -->

<div id="contenedor_principal">
<s:form id="frmCrearGrupo" method="POST" action="adminAltaGrupo"
	validate="false"  theme="css_xhtml">
<fieldset>
		<table summary="Tabla formulario datos del grupo" cellpadding="0" cellspacing="4">
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
				<!-- nombre -->
				<td headers="header0"><s:textfield
					name="form.name" required="true" theme="xhtml"
					cssClass="input_tablas_registro" size="20" maxlength="15" 
					key="field.grupo.nombreGrupo" labelposition="left" /></td>
			</tr>
			<tr>
				<!-- descripcion -->
				<td headers="header0">
					<s:textfield name="form.description" required="false" 
					theme="xhtml" cssClass="input_tablas_registro" 
					key="field.grupo.descripcion" labelposition="left" 
					size="50" maxlength="45" /></td>
			</tr>
			</tbody>
		</table>
	</fieldset>
	
	<br/>

	<!-- ****************************************************************************** -->
	<!-- Botones																		-->
	<!-- ****************************************************************************** -->


<div>    <s:submit theme="simple" value="%{getText('buttons.text.save')}" cssClass="boton_template" /> </div>
    &nbsp;
<div >
	
	<s:url id="url" action="adminGrupos"/>
	<s:a href="%{url}" 
	labelposition="left" ><s:text name="buttons.text.volver" /></s:a>
</div>
</s:form>
 </div>