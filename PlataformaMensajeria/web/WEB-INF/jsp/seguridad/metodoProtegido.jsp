<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<!-- ****************************************************************************** -->
<!-- Formulario                                                                     -->
<!-- ****************************************************************************** -->

<div id="contenedor_principal">

	<fieldset>

			<table summary="Tabla formulario criterios de búsqueda" cellpadding="0" cellspacing="4">
			<thead>
				<tr>
					<th headers="header0" colspan="2" align="left">
						<div class="titulobloque">
							<h2><s:text name="menu.metodoProtegido" /></h2>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td headers="header0">

						<s:text name="pages.seguridad.metodoprotegido" />

					</td>
				</tr>
			</tbody>
			</table>

	</fieldset>

	<br>

    <s:form method="POST" action="resultadoMetodoProtegido"  cssClass="bloqueizq_contenido" >
        <s:submit theme="simple" value="%{getText('buttons.text.ejecutar')}" cssClass="input_boton_buscador" />
    </s:form>

</div>

