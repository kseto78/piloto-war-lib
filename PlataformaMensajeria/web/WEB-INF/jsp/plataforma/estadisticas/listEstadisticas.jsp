<%@page import="org.displaytag.decorator.MultilevelTotalTableDecorator"%>
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>


<!-- <div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>-->

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->
<script type="text/javascript">
	$(function(){
	    $(".wrapper1").scroll(function(){
	        $(".wrapper2")
	            .scrollLeft($(".wrapper1").scrollLeft());
	    });
	    $(".wrapper2").scroll(function(){
	        $(".wrapper1")
	            .scrollLeft($(".wrapper2").scrollLeft());
	    });
	    var tableWidth = $(".wrapper2 table").width();
	    $(".wrapper1 .div1").css({"width": tableWidth});
	});
	
	function setValue(obj){
		var value = obj.value;
		document.getElementById('estadisticaBean.servicioId').value=value;
	}
	function makeRequest(){
		document.getElementById('estadisticaBean.servicioId').value='';
		  $('#sid option').each(function() {
			        $(this).remove();
			});
		$.ajax({
	        type: "POST",
	        url: "ajaxLoadServicios.action",
	        data: {idAplicacion:document.getElementById('estadisticaBean.aplicacionId').value}, // serializes the form's elements.
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
	function checkServicio(){
		document.getElementById('estadisticaBean.servicioId').value=document.getElementById('sid').value;
		return true;
	}
</script>
        <div class="mainContent">
        <h3 class="pageNameButtons">
    			<span class="floatRight"></span>
	            <label>Estadísticas Generales</label>
          	</h3>
        <%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
        <div class="criteria">
		<s:form id="frmBuscarEstadisticas" method="POST" action="buscarEstadisticas" onsubmit="checkServicio()"
		validate="false" theme="css_xhtml">            	
                   <p class="criteria">
                     <span>
                       <label style="width: 120px;" class="fieldText W90">Aplicaci&oacute;n:</label>
                       <s:select 
						id="estadisticaBean.aplicacionId" name="estadisticaBean.aplicacionId" 
						emptyOption="false" theme="simple" 
						labelposition="left" onchange="makeRequest()"
						list="comboAplicaciones" listKey="codigo"
						listValue="descripcion" cssClass="W245" 
						headerKey="0" headerValue="Todas"
						value="%{estadisticaBean.aplicacionId}" disabled="false" />
           	      	</span>
           	      	<span>
                       <label style="width: 120px;" class="fieldText W130">Servidor / Proveedor:</label>
                       <s:select cssStyle="width:200px;"
						id="estadisticaBean.servidorId" name="estadisticaBean.servidorId" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboServidores" listKey="codigo"
						listValue="descripcion" cssClass="W240" 
						headerKey="0" headerValue="Todos"
						value="%{estadisticaBean.servidorId}" disabled="false" />           	      	
           	      	</span>
           	      </p> 	
	              <p class="criteria">
           	      	<span>
                       <label style="width: 120px;" class="fieldText W90">Servicio:</label>
                       <s:select cssStyle="width:200px;"
						id="sid" name="sid" onchange="setValue(this)"
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboServicios" listKey="codigo"
						listValue="descripcion" cssClass="W245" 
						headerKey="0" headerValue="Todos"
						value="%{estadisticaBean.servicioId}" disabled="false" />           	      	
           	      	</span>
           	      	   <span>
                       <label style="width: 120px;" class="fieldText W130">Estado:</label>
                       <s:select cssStyle="width:200px;"
						id="estadisticaBean.estadoId" name="estadisticaBean.estadoId" 
						emptyOption="false" theme="simple" 
						labelposition="left" headerKey="0" headerValue="Todos"
						list="comboEstados" listKey="codigo"
						listValue="descripcion" cssClass="W240" 
						
						value="%{estadisticaBean.estadoId}" disabled="false" />           	      	
           	      	</span>	              
	              </p> 
	              <p class="criteria">
           	      	<span>
                       <label style="width: 120px;" class="fieldText W90">Canal:</label>
                       <s:select cssStyle="width:200px;"
						id="estadisticaBean.canalId" name="estadisticaBean.canalId" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboCanales" listKey="codigo"
						listValue="descripcion" cssClass="W245" 
						headerKey="0" headerValue="Todos"
						value="%{estadisticaBean.canalId}" disabled="false" />           	      	
           	      	</span>
	              </p>
	              <p class="criteria">
           	      	<span>
                    	<label style="width: 120px;" class="fieldText W90">Usuario:</label>
						<s:textfield  name="estadisticaBean.docUsuario" 
						id="estadisticaBean.docUsuario" 
						theme="simple" cssClass="W120" 
						value="%{estadisticaBean.docUsuario}"/> 
                    </span>
                    <span>
                    	<label style="width: 120px;" class="fieldText W130">Procedimiento/Servicio:</label>
						<s:textfield  name="estadisticaBean.codSIA" 
						id="estadisticaBean.codSIA" 
						theme="simple" cssClass="W120" 
						value="%{estadisticaBean.codSIA}"/> 
                    </span>
           	      </p>
           	      <p class="criteria">
           	      	<span>
                		<label style="width: 120px;" class="fieldText W90">Organismo:</label>
						<s:textfield  name="estadisticaBean.codOrganismo" 
						id="estadisticaBean.codOrganismo" 
						theme="simple" cssClass="W120" 
						value="%{estadisticaBean.codOrganismo}"/>     
                    </span>
                    <span>
                    	<label style="width: 120px;" class="fieldText W130">Organismo pagador:</label>
						<s:textfield  name="estadisticaBean.codOrganismoPagador" 
						id="estadisticaBean.codOrganismoPagador" 
						theme="simple" cssClass="W120" 
						value="%{estadisticaBean.codOrganismoPagador}"/> 
                    </span>
           	      </p> 	     	              
	<!-- ***************************** --><div class="hr"></div><!-- ********************************************** -->
                   <p class="criteria">
                 	<span style="width:600px">
                    	<label class="fieldText W90" style="width: 120px;">Vista:</label>
                    	<% String vistaS = (String)request.getAttribute("vistaIdSelected"); %>
                    	<input type="button" value="Días" id="btndias" onclick="chkViewValue(this)" class="<%=(vistaS!=null&&vistaS.equals("3"))?"buttonSelected":"buttonNoSelected" %>"/>
                    	<input type="button"  value="Meses" id="btnmeses" onclick="chkViewValue(this)" class="<%=(vistaS!=null&&vistaS.equals("2"))?"buttonSelected":"buttonNoSelected" %>"/>
                    	<input type="button" value="Años" id="btnannos" onclick="chkViewValue(this)" class="<%=(vistaS!=null&&vistaS.equals("1"))?"buttonSelected":"buttonNoSelected" %>"/>
                    	<input type="hidden" id="estadisticaBean.vistaId" name="estadisticaBean.vistaId" value="<%=vistaS%>"/>
                        Desde: <s:textfield
								name="estadisticaBean.fechaDesde" value="%{estadisticaBean.fechaDesde}" id="estadisticaBean.fechaDesde"
								theme="simple" style="width:60px;" 
								onchange="validateDates('estadisticaBean.fechaDesde','estadisticaBean.fechaHasta',this)"
								size="10" maxlength="10"
								cssClass="datepicker">
								<s:param name="value">
    							<s:date name="estadisticaBean.fechaDesde" format="dd/MM/yyyy"/>
  						</s:param>
						</s:textfield>
                        Hasta: <s:textfield
								name="estadisticaBean.fechaHasta" value="%{estadisticaBean.fechaHasta}" id="estadisticaBean.fechaHasta"
								onchange="validateDates('estadisticaBean.fechaDesde','estadisticaBean.fechaHasta',this)"
								theme="simple"  style="width:60px;"
								size="10" maxlength="10"
								cssClass="datepicker">
								<s:param name="value">
    							<s:date name="estadisticaBean.fechaHasta" format="dd/MM/yyyy"/>
  						</s:param>
						</s:textfield>
                    </span>
           	      </p> 
                   <p class="criteria">
                     <span>
                       <label style="width: 120px;" class="fieldText W90">Agrupar:</label>
                       <s:select cssStyle="width:200px;"
						id="estadisticaBean.agruparId" name="estadisticaBean.agruparId" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboAgrupar" listKey="codigo"
						listValue="descripcion" cssClass="W245"  
						
						value="%{estadisticaBean.agruparId}" disabled="false" />
           	      	</span>
           	      	    <s:hidden id="reverse" name="reverse" value="%{reverse}"/>
           	      	    <s:hidden id="estadisticaBean.servicioId" name="estadisticaBean.servicioId" value="%{estadisticaBean.servicioId}"/>
           	      </p> 
           	      <p class="criteria">
           	      	<i>Las estadísticas de los mensajes que han superado el tiempo de conservación estarán disponibles para ser consultadas por meses y años</i>
           	      </p>	                
                <div class="footerCriteria">
                    <span class="leftSide"></span>
                    <span class="rightSide">
                       <s:submit  theme="simple" value="%{getText('buttons.text.search')}" cssClass="button"/>
                    </span>
                </div>
            </div>
           </s:form>
		   
           <%
           MultilevelTotalTableDecorator totals = new org.displaytag.decorator.MultilevelTotalTableDecorator();
           totals.setGrandTotalDescription("Totales");
           totals.setGrandTotalLabel("right");
           totals.setGrandTotalSum("totals");
           pageContext.setAttribute("totals", totals);
           %>
           <script>
           		function reverseTable(){
           			var reverse = document.getElementById('reverse').value;
           			if(reverse=='false'){
           				reverse = 'true';
           			}else if(reverse=='true'){
           				reverse = 'false';
           			}
           			document.getElementById('reverse').value=reverse;
           			document.forms['frmBuscarEstadisticas'].submit();
           		}
           </script>
           
	<table class="tablaHeader" style="width: 790px">
		<thead>
			<tr> 
				<th class="superHeader" colspan="5">
					<div class="floatLeft">
						<a class="button" onclick="reverseTable()">Invertir tabla</a>
					</div>
					<div class="floatRight">
						<s:a cssClass="button" onclick="makeExcellWrap(this)">Exportar Excel</s:a>
						<!-- <input type="button" class="button" value="Exportar Excel">-->
					</div>
				</th>
			</tr>
		</thead>
	</table>    

	<div class="wrapper1" style="overflow-x: auto; width: 790px;margin-left: 9px">
	    <div class="div1" style="height: 20px">
	    </div>
	</div>
	<div style="overflow-x: auto; width: 790px;margin-left: 9px" class="wrapper2">       
		<display:table id="idFila" class="FixTable" name="listaFilaEstadisticaBean" export="true" decorator="totals" style="margin-left: 0; margin-right: 0; width:100%;" >
			<display:setProperty name="css.tr.even" value="null" />
			<display:setProperty name="css.td.even" value="BBDD 060" />
			<display:setProperty name="css.td.odd" value="BBDD 060" />
			<display:setProperty name="css.tr.odd"  value="odd" />
			<display:setProperty name="decorator.media.excel" value="org.displaytag.sample.decorators.HssfTotalWrapper" />
			<display:column property="nombreGrupo"  title="Agrupado por" class="color" headerClass="TH160 left"/>
			<c:forEach items="${idFila.nombreColumnas}" var="cl" varStatus="status">
				<display:column property="valor" format="{0,number,#,###}" class="tableCellError right" title="${idFila.titulo}" headerClass="TH80 center separator center" total="true"/>
			</c:forEach>
			<display:column property="valorTotal" format="{0,number,#,###}" class="color right" title="Total" headerClass="TH80 separator right" total="true" />
		</display:table>
	</div>  			
</div>