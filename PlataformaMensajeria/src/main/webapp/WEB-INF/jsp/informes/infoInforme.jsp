<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<!-- ****************************************************************************** -->
<!-- Formulario                                                                     -->
<!-- ****************************************************************************** -->

<div id="contenedor_principal">

	<s:form id="frmInforme" method="POST" action="generarInforme" target="_blank"
		validate="false" theme="css_xhtml">
		<fieldset>
				<table summary="Tabla formulario datos del informe" cellpadding="0" cellspacing="4">
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
						<!-- Selector de formato -->
						<td headers="header0">
							<framework:catalog catalogname="formatosInformes"
                            name="form.tipoInforme" theme="xhtml" id="formatosInformes"
                            key="field.reports.formato" labelposition="left"
                            cssClass="input_tablas_registro" />
                        </td>
						<td headers="header0">&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</fieldset>
		<br/>
	
		<!-- ****************************************************************************** -->
		<!-- Botones																		-->
		<!-- ****************************************************************************** -->
		<s:submit theme="simple" value="%{getText('buttons.text.generar')}" cssClass="input_boton_buscador" />
		
	</s:form>
</div>