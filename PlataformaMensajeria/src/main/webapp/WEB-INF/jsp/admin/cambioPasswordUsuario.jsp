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
	<s:form id="frmCambioPasswordUsuario" method="POST" action="adminCambioPasswordUsuario"
		validate="false" theme="css_xhtml">
		<s:hidden name="form.uid" value="%{form.uid}" />
	    <s:hidden name="form.id" value="%{form.id}" />
	    <s:hidden name="form.tipoUsuario" value="%{form.tipoUsuario}" />
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
					<!-- username -->
					<td headers="header0"><s:textfield
						name="form.username" required="true" theme="css_xhtml"
						cssClass="input_tablas_registro" size="10" maxlength="10" 
	                    key="field.usuario.username" labelposition="left" readonly="true"/></td>
	                <td>&nbsp;</td>
				</tr>
				<tr>
				<tr>
					<!-- nueva password -->
					<td headers="header0"><s:password
						name="form.password" required="true" theme="css_xhtml"
						cssClass="input_tablas_registro" 
						key="field.usuario.newPassword" labelposition="left" size="40"
						maxlength="35" /></td>
					<!-- nueva password comprobación -->
					<td headers="header0"><s:password
						name="form.checkPassword" required="true" theme="css_xhtml"
						cssClass="input_tablas_registro" 
						key="field.usuario.checkPassword" labelposition="left" size="40"
						maxlength="35" /></td>
				</tr>
				</tbody>
			</table>
		</fieldset>
			
		<br>
		
		<!-- ****************************************************************************** -->
		<!-- Botones																		-->
		<!-- ****************************************************************************** -->
		<div>
			<s:submit theme="simple" value="%{getText('buttons.text.save')}" cssClass="boton_template" />
		</div>
		&nbsp;
		<div>
		    <s:url id="url" action="adminUsuarios"/>
		    <s:a href="%{url}" labelposition="left">
		    	<s:text name="buttons.text.volver" />
		    </s:a>
		</div>
	</s:form>
 </div>