<%@page import="org.displaytag.decorator.MultilevelTotalTableDecorator"%>
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>


<!-- <div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>-->

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->
<div class="mainContent">
       <h3 class="pageNameButtons">
    		<span class="floatRight"></span>
	        <label>Auditoría</label>
       </h3>
	<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp"%>
	<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp"%>
	<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp"%>
	<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp"%>
	<div class="criteria">
		<s:form id="frmBuscarPlanificaciones" method="POST"
			action="listAuditoriasPlataforma" validate="false" theme="css_xhtml">
			<p class="criteria">
				<span> <label style="width: 100px;" class="fieldText">Entidad:</label>
					<s:select id="auditoriaPlataforma.sourceName"
						name="auditoriaPlataforma.sourceName" emptyOption="false"
						theme="simple" labelposition="left" list="comboEntidad"
						listKey="codigo" headerKey="" headerValue="Todos"
						listValue="descripcion" cssClass="W240" 
						value="%{auditoriaPlataforma.sourceName}" disabled="false" />
				</span> <span> <label style="width: 100px;" class="fieldText">Modificador:</label>
					<s:textfield name="auditoriaPlataforma.adtUsuario"
						id="auditoriaPlataforma.adtUsuario" theme="simple"
						labelposition="left" size="100"
						cssStyle="width:237px;" maxlength="255"
						cssClass="input_tablas_registro" />
				</span>
			</p>
			<p class="criteria">
				<span> <label class="fieldText" style="width: 100px;">
						Fecha Desde: </label><s:textfield name="auditoriaPlataforma.fechaDesde"
						value="%{auditoriaPlataforma.fechaDesde}"
						onchange="validateDates('auditoriaPlataforma.fechaDesde','auditoriaPlataforma.fechaHasta',this)"
						id="auditoriaPlataforma.fechaDesde" theme="simple" 
						size="10"  cssClass="datepicker">
						<s:param name="value">
    							<s:date name="auditoriaPlataforma.fechaDesde" format="dd/MM/yyyy HH:mm"/>
  						</s:param>
						</s:textfield> </span></p>
					<p class="criteria"><span>
                        <label class="fieldTextNoIco" style="width: 100px;">Fecha Hasta:</label>
					<s:textfield name="auditoriaPlataforma.fechaHasta"
						value="%{auditoriaPlataforma.fechaHasta}"
						id="auditoriaPlataforma.fechaHasta" theme="simple"
						onchange="validateDates('auditoriaPlataforma.fechaDesde','auditoriaPlataforma.fechaHasta',this)"
						style="width:60px;" size="10" cssClass="datepicker">
						<s:param name="value">
    							<s:date name="auditoriaPlataforma.fechaHasta" format="dd/MM/yyyy HH:mm"/>
  						</s:param>
						</s:textfield>
						</span>
                    <span>
				 <label style="width: 100px;" class="fieldText">Operación:</label>
					<s:select id="auditoriaPlataforma.logAccion"
						name="auditoriaPlataforma.logAccion" emptyOption="false"
						theme="simple" labelposition="left" list="comboOperacion"
						listKey="codigo" headerKey="" headerValue="Todas"
						listValue="descripcion" cssClass="W240"
						value="%{auditoriaPlataforma.logAccion}" disabled="false" />
				</span>

			</p>
			<p class="criteria">
				<span style="width: 100%"> <label style="width: 100px;"
					class="fieldText">Descripción:</label> <s:textfield
						name="auditoriaPlataforma.sourceDescription"
						id="auditoriaPlataforma.sourceDescription" theme="simple"
						cssStyle="width: 620px;"
						value="%{auditoriaPlataforma.sourceDescription}" />

				</span>
			</p>
			<div class="footerCriteria">
				<span class="leftSide"></span> <span class="rightSide"> <s:submit
						theme="simple" value="%{getText('buttons.text.search')}"
						cssClass="button" />
				</span>
			</div>
	</div>
	</s:form>
	<h4 class="titular">Encontradas ${resultCount} entradas</h4>
	<table class="tablaHeader">
		<thead>
			<tr>
				<th class="superHeader" colspan="5">
					<div class="floatLeft"></div>
					<div class="floatRight">
						<s:a cssClass="button" onclick="makeExcell(this)">Exportar Excel</s:a>
						<!-- <input type="button" class="button" value="Exportar Excel">-->
					</div>
				</th>
			</tr>
		</thead>
	</table>
	<display:table id="tableId"
		summary="Tabla de resultados de búsqueda de logs"
		name="listaAuditorias" pagesize="${pageSize}" requestURI=""
		defaultsort="1" defaultorder="ascending" sort="external" export="true"
		cellpadding="0" cellspacing="0" partialList="true"
		size='<%=request.getAttribute("totalSize")%>'
		decorator="es.mpr.template.web.decorators.TableWrapper">
		<display:setProperty name="css.tr.even" value="null" />
		<display:setProperty name="css.tr.odd" value="odd" />
		<%-- Id --%>
		<display:column property="logId" titleKey="plataforma.auditoria.logId"
			sortable="true" headerClass="separator center" class="" />
		<%-- operacion --%>
		<display:column titleKey="plataforma.auditoria.operacion"
			sortable="true" headerClass="TH70 separator center"
			class="${tableId.operacionCSSClass}" property="operacionHTML"
			media="html" />
		<display:column titleKey="plataforma.auditoria.operacion"
			sortable="true" headerClass="TH70 separator center"
			property="operacion" media="excel" />
		<%-- entidad --%>
		<display:column property="sourceName"
			titleKey="plataforma.auditoria.entidad" sortable="true"
			headerClass="separator center" class="" />
		<display:column property="sourceId"
			titleKey="plataforma.auditoria.sourceId" sortable="true"
			headerClass="separator center" class="center" />
		<display:column property="sourceDescription"
			titleKey="plataforma.auditoria.descripcion" sortable="false"
			headerClass="separator center" class="" />
		<display:column property="adtFecha"
			titleKey="plataforma.auditoria.fecha"
			format="{0,date,dd/MM/yyyy HH:mm}" sortable="true"
			headerClass="separator center" class="" />
		<display:column property="adtUsuario"
			titleKey="plataforma.auditoria.usuario" sortable="true"
			headerClass="separator center" class="" />
	</display:table>
	<br />
</div>


</div>
