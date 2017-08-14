<%@page import="org.displaytag.decorator.MultilevelTotalTableDecorator"%>
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>


<!-- <div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>-->

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->
<script>
function setValue(obj){
	var value = obj.value;
	document.getElementById('usuariosPush.servicioId').value=value;
}
function makeRequest(){
	document.getElementById('usuariosPush.servicioId').value='';
	  $('#sid option').each(function() {
		        $(this).remove();
		});
	$.ajax({
        type: "POST",
        url: "ajaxLoadServicios.action",
        data: {idAplicacion:document.getElementById('usuariosPush.aplicacionId').value}, // serializes the form's elements.
        success: function(data)
        {
     	  var items = data.items;
     	  $('#sid').append($('<option>', { 
     	        value: '',
     	        text : 'Todos' 
     	    }));
     	 $.each(items, function (i, item) {
     		$('#sid').append($('<option>', { 
     	        value: item.value,
     	        text : item.text 
     	    }));
     	});
        },
        error: function(data)
        {
     	   alert("error..."); 
        }
      });
}
</script>
<div class="mainContent">
	<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp"%>
	<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp"%>
	<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp"%>
	<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp"%>
	<div class="criteria">
		<s:form id="frmBuscarUsuariosPush" method="POST"
			action="listUsuariosPush" validate="false" theme="css_xhtml">
			<p class="criteria">
				<span> <label style="width: 100px;" class="fieldText">Aplicación:</label>
					<s:select id="usuariosPush.aplicacionId"
						name="usuariosPush.aplicacionId" emptyOption="false"
						theme="simple" onchange="makeRequest()" labelposition="left" 
						list="comboAplicaciones" listKey="codigo" headerKey="" 
						headerValue="Todas" listValue="descripcion" cssClass="W240" 
						value="%{usuariosPush.aplicacionId}" disabled="false" />
				</span> 
				<span> <label style="width: 100px;" class="fieldText">Plataforma:</label>
					<s:select id="usuariosPush.plataforma"
						name="usuariosPush.plataforma" emptyOption="false"
						theme="simple" labelposition="left" list="comboPlataformas"
						listKey="codigo" headerKey="" headerValue="Todas"
						listValue="descripcion" cssClass="W240" 
						value="%{usuariosPush.plataforma}" disabled="false" />
				</span>
			</p>
			<p class="criteria">
				<span> <label style="width: 100px;" class="fieldText">Servicio:</label>
					<s:select id="sid"
						name="sid" emptyOption="false"
						theme="simple" onchange="setValue(this)" labelposition="left" 
						list="comboServicios" listKey="codigo" headerKey="" 
						headerValue="Todos" listValue="descripcion" cssClass="W240" 
						value="%{usuariosPush.servicioId}" disabled="false" />
				</span> 
				<span> <label class="fieldText" style="width: 100px;">
						Fecha: </label>Desde: <s:textfield name="usuariosPush.fechaDesde"
						value="%{usuariosPush.fechaDesde}"
						onchange="validateDates('usuariosPush.fechaDesde','usuariosPush.fechaHasta',this)"
						id="usuariosPush.fechaDesde" theme="simple" format="dd/MM/yyyy"
						style="width:60px;" size="10" maxlength="10" cssClass="datepickerEstadisticas">
						<s:param name="value">
    							<s:date name="usuariosPush.fechaDesde" format="dd/MM/yyyy"/>
  						</s:param>
						</s:textfield>
					Hasta: <s:textfield name="usuariosPush.fechaHasta"
						value="%{usuariosPush.fechaHasta}"
						id="usuariosPush.fechaHasta" theme="simple"
						onchange="validateDates('usuariosPush.fechaDesde','usuariosPush.fechaHasta',this)"
						style="width:60px;" size="10" maxlength="10" cssClass="datepickerEstadisticas">
						<s:param name="value">
    							<s:date name="usuariosPush.fechaHasta" format="dd/MM/yyyy"/>
  						</s:param>
						</s:textfield>
				</span> 

			</p>
			<div class="footerCriteria">
				<span class="leftSide"></span> <span class="rightSide"> <s:submit
						theme="simple" value="%{getText('buttons.text.search')}"
						cssClass="button" />
				</span>
			</div>
	</div>
	<s:hidden id="usuariosPush.servicioId" name="usuariosPush.servicioId" value="%{usuariosPush.servicioId}"/>
	<s:hidden id="pageSizeSelected" name="pageSize" value="%{pageSize}"/> 
	</s:form>
	<h4 class="titular">Encontradas ${resultCount} entradas</h4>
	<table class="tablaHeader">
		<thead>
			<tr>
				<th class="superHeader" colspan="5">
					<div class="floatLeft"></div>
					<div class="floatRight">
						<s:a cssClass="button" onclick="makeExcell(this)">Exportar Excel</s:a>
					</div>
				</th>
			</tr>
		</thead>
	</table>
	<display:table id="tableId"
		summary="Tabla de resultados de búsqueda de usuarios Push"
		name="listaUsuariosPush" pagesize="${pageSize}" requestURI=""
		defaultsort="1" defaultorder="ascending" sort="external" export="true"
		cellpadding="0" cellspacing="0" partialList="true"
		size='<%=request.getAttribute("totalSize")%>'
		decorator="es.mpr.template.web.decorators.TableWrapper">
		<display:setProperty name="css.tr.even" value="null" />
		<display:setProperty name="css.tr.odd" value="odd" />
		<%-- Id --%>
		<display:column property="usuarioId" titleKey="plataforma.usuariospush.usuarioid"
			sortable="true" headerClass="separator center" class="center" />
		<%-- nombre usuario --%>
		<display:column property="nombreUsuario"
			titleKey="plataforma.usuariospush.username" sortable="true"
			headerClass="separator center" class="center" />
		<%-- aplicacion --%>
		<display:column property="aplicacion"
			titleKey="plataforma.usuariospush.aplicacionid" sortable="true"
			headerClass="separator center" class="center" />
		<%-- servicio --%>
		<display:column property="servicio"
			titleKey="plataforma.usuariospush.servicioid" sortable="true"
			headerClass="separator center" class="center" />
		<%-- plataforma --%>
		<display:column property="plataforma"
			titleKey="plataforma.usuariospush.plataformaid" sortable="true"
			headerClass="separator center" class="center" />
		<%-- fecha --%>
		<display:column property="fecha"
			titleKey="plataforma.auditoria.fecha"
			format="{0,date,dd/MM/yyyy HH:mm}" sortable="true"
			headerClass="separator center" class="center" />
	</display:table>
	<br />
</div>


</div>
