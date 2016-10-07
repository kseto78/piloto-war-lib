<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<!-- ****************************************************************************** -->
<!-- Formulario                                                                     -->
<!-- ****************************************************************************** -->

<div id="contenedor_principal">
<s:form id="frmConsGrupo" method="POST" action="adminEditarGrupo"
	validate="false" theme="css_xhtml">
         <s:hidden name="form.id" value="%{form.id}" />
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
				<td headers="header0"><s:label
					value="%{form.name}" required="false" theme="xhtml"
					 key="field.grupo.nombreGrupo" 
					labelposition="left" />
					<s:hidden name="form.name" value="%{form.name}" /></td>
			</tr>
			<tr>
				<!-- descripcion -->
				<td headers="header0">
					<s:label value="%{form.description}" required="false" 
					theme="xhtml"  
					key="field.grupo.descripcion" labelposition="left" /></td>
			</tr>
			</tbody>
		</table>
	</fieldset>
	
	<br/>

	<!-- ****************************************************************************** -->
	<!-- Botones																		-->
	<!-- ****************************************************************************** -->


<div>    <s:submit theme="simple" value="%{getText('buttons.text.editar')}"  cssClass="boton_template" /> </div>
&nbsp;
<div>
	
	<s:url id="url" action="adminGrupos"/>
	<s:a href="%{url}"  labelposition="left" >
	<s:text name="buttons.text.volver" /></s:a>
</div>
</s:form>
 </div>