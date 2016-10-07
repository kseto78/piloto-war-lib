<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="contenedor_principal">

<fieldset>
		<table summary="Tabla Errores" cellpadding="0" cellspacing="4">
		<thead>
			<tr>
				<th headers="header0">
					<div class="titulobloque">
						<h2><s:text name="errors.j2ee.generic.messageerrorpage" /></h2>
					</div>
				</th>
			</tr>
		</thead>
		<tbody>
		<tr>
			<td headers="header0"><s:text name="errors.j2ee.generic.messagesecurityerrorpage" /></td>
		</tr>
		<tr>
			<td headers="header0"><s:actionerror/></td>
		</tr>
		</tbody>
	</table>
</fieldset>

