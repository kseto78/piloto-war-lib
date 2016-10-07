<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<!-- ****************************************************************************** -->
<!-- Formulario                                                                     -->
<!-- ****************************************************************************** -->

<div id="contenedor_principal">

	<s:form id="frmMail" method="POST" action="enviar" 
	enctype="multipart/form-data" validate="false" theme="css_xhtml">
		<fieldset>
				<table summary="Tabla formulario datos del email" cellpadding="0" cellspacing="4">
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
						<td headers="header0">
						<!-- Campo para -->
						<s:textfield key="mail.from" name="f.from" required="true"
							cssClass="input_tablas_registro" size="45" theme="xhtml" labelposition="left" />
						</td>
					</tr>
					<tr>
						<td headers="header0">
						<!-- Campo de -->
						<s:textfield key="mail.to" name="f.to" required="true"
							cssClass="input_tablas_registro" size="90" theme="xhtml" labelposition="left" />
						</td>
					</tr>
					<tr>
						<td headers="header0">
						<!-- Campo copia -->
						<s:textfield key="mail.cc" name="f.cc" cssClass="input_tablas_registro" 
							size="90" theme="xhtml" labelposition="left" />
						</td>
					</tr>
					<tr>
						<td headers="header0">
						<!-- Campo copia oculta -->
						<s:textfield key="mail.bcc" name="f.bcc" cssClass="input_tablas_registro" 
							size="90" theme="xhtml" labelposition="left" />
						</td>
					</tr>
					<tr>
						<td headers="header0">
						<!-- Campo asunto -->
						<s:textfield key="mail.subject" name="f.subject" required="true" 
							cssClass="input_tablas_registro" size="90" theme="xhtml" labelposition="left" />
						</td>
					</tr>
					<tr>
						<td headers="header0">
						<!-- Campo texto -->
						<s:textarea key="mail.text" name="f.htmlContents" rows="11" 
							cols="80" cssClass="input_tablas_registro" theme="xhtml" labelposition="left" />
						</td>
					</tr>
					<tr>
						<td headers="header0">
						<!-- Selectores de formato -->
						<s:select key="mail.content.type" name="f.contentType" 
							list="listaFormatosCorreo" listKey="codigo"
							listValue="descripcion" theme="xhtml" labelposition="left" 
							cssClass="input_tablas_registro" />
						</td>
					</tr>
					<tr>
						<td headers="header0">
						<!-- Adjuntos -->
						<s:file key="mail.adjuntar.primero" name="adjunto" 
							cssClass="input_tablas_registro" theme="xhtml" labelposition="left" />
						<s:file key="mail.adjuntar.segundo" name="adjunto" 
							cssClass="input_tablas_registro" theme="xhtml" labelposition="left" />	
						<s:file key="mail.adjuntar.tercero" name="adjunto" 
							cssClass="input_tablas_registro" theme="xhtml" labelposition="left" />		
						</td>
					</tr>
				</tbody>
			</table>
		</fieldset>
			
		<br>
		<!-- ****************************************************************************** -->
		<!-- Botones																		-->
		<!-- ****************************************************************************** -->
		<div>
			<s:submit theme="simple" value="%{getText('buttons.text.enviar')}" cssClass="input_boton_buscador" />
			&nbsp;
			<s:reset theme="simple" cssClass="input_boton_buscador"  value="%{getText('buttons.text.cancelar')}" />
		</div>
	
	</s:form>	
</div>