<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<!-- ****************************************************************************** -->
<!-- Formulario                                                                     -->
<!-- ****************************************************************************** -->

<div id="contenedor_principal">

	<s:form method="POST" action="descargarFichero"
		validate="false"
		theme="css_xhtml">
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
						<!-- Fichero -->
						<td headers="header0"><s:textfield
							name="fichero" required="true" theme="xhtml"
							cssClass="input_tablas_registro" size="50" 
							key="field.files.rutaFile" labelposition="left" /></td>
						<td headers="header0">&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</fieldset>
		<br/>
	
		<!-- ****************************************************************************** -->
		<!-- Botones																		-->
		<!-- ****************************************************************************** -->
		<s:submit theme="simple" value="%{getText('buttons.text.descargar')}" cssClass="input_boton_buscador" />
		&nbsp;
		<s:reset theme="simple" cssClass="input_boton_buscador" value="%{getText('buttons.text.cancelar')}" />
		
	</s:form>
</div>