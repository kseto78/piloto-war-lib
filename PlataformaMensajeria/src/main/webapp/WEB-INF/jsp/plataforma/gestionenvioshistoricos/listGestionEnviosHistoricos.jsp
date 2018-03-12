<%@page import="org.displaytag.decorator.MultilevelTotalTableDecorator"%>
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/tlds/PlataformaMensajeriaTags.tld" prefix="plataforma" %>

<!-- <div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>-->

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->
<script>
function checkPageSize(){
	var pageSize = document.getElementById('pageSizeSelect').value;
	document.getElementById('pageSizeSelected').value=pageSize;
	return true; 
}
function submitForm(){
	var form = document.getElementById('frmBuscarGestionEnviosHistoricos');
	checkPageSize();
	form.submit();
}
function setValue(obj){
	var value = obj.value;
	document.getElementById('gestionEnvioHistoricoBean.servicioId').value=value;
}
function makeRequest(){
	document.getElementById('gestionEnvioHistoricoBean.servicioId').value='';
	  $('#sid option').each(function() {
		        $(this).remove();
		});
	$.ajax({
        type: "POST",
        url: "ajaxLoadServicios.action",
        data: {idAplicacion:document.getElementById('gestionEnvioHistoricoBean.aplicacionId').value}, // serializes the form's elements.
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
        <h3 class="pageNameButtons">
    			<span class="floatRight"></span>
	            <label>Gestión de Envíos Históricos</label>
          	</h3>        
        <%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
		
<sj:dialog  id="dialogLotes" title="DETALLE LOTE" cssStyle="display:block" autoOpen="false"></sj:dialog>
<sj:dialog  id="dialogEnviosEmail" title="DETALLE MENSAJE EMAIL" cssStyle="display:none" autoOpen="false"></sj:dialog>
<sj:dialog  id="dialogEnviosSMS" title="DETALLE MENSAJE SMS" cssStyle="display:none" autoOpen="false"></sj:dialog>
<sj:dialog  id="dialogEnviosNotificacionPush" title="DETALLE MENSAJE NOTIFICACION PUSH" cssStyle="display:none" autoOpen="false"></sj:dialog>
<sj:dialog  id="dialogHistoricoEnvio" title="DETALLE DESTINATARIO" cssStyle="display:none" autoOpen="false"></sj:dialog>
<sj:dialog  id="dialogMisim" title="DETALLE MISIM" cssStyle="display:none" autoOpen="false"></sj:dialog>
				
        <div class="criteria">
		<s:form id="frmBuscarGestionEnviosHistoricos" method="POST" action="listGestionEnviosHistoricos"
		validate="false" theme="css_xhtml" onsubmit="return checkPageSize()">            	
                   <p class="criteria">
                     <span>
                       <label style="width: 100px;" class="fieldText">Aplicacion:</label>
                       <s:select 
						id="gestionEnvioHistoricoBean.aplicacionId" name="gestionEnvioHistoricoBean.aplicacionId" 
						emptyOption="false" theme="simple" onchange="makeRequest()"
						labelposition="left" headerKey="" headerValue="Todas"
						list="comboAplicaciones" listKey="codigo"
						listValue="descripcion" cssClass="W240" 
						
						value="%{gestionEnvioHistoricoBean.aplicacionId}" disabled="false" />
           	      	</span>
           	      	<span>
                       <label style="width: 125px;" class="fieldText">Canal:</label>
                       <s:select 
						id="gestionEnvioHistoricoBean.canalId" name="gestionEnvioHistoricoBean.canalId" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboCanales" listKey="codigo" headerKey="" headerValue="Todos"
						listValue="descripcion" cssClass="W240" 
						
						value="%{gestionEnvioHistoricoBean.canalId}" disabled="false" />     	      	
           	      	</span>
           	      </p> 	
                   <p class="criteria">
                 	 <span>
                       <label style="width: 100px;" class="fieldText">Servicio:</label>
                       <s:select 
						id="sid" name="sid" 
						emptyOption="false" theme="simple"  onchange="setValue(this)"
						labelposition="left"
						list="comboServicios" listKey="codigo" headerKey="" headerValue="Todos"
						listValue="descripcion" cssClass="W240" 
						
						value="%{gestionEnvioHistoricoBean.servicioId}" disabled="false" />     	      	
           	      	</span>
                    <span>
                       <label style="width: 125px;" class="fieldText">Servidor / Proveedor:</label>
                       <s:select 
						id="gestionEnvioHistoricoBean.servidorId" name="gestionEnvioHistoricoBean.servidorId" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboServidores" listKey="codigo" headerKey="" headerValue="Todos"
						listValue="descripcion" cssClass="W240" 
						
						value="%{gestionEnvioHistoricoBean.servidorId}" disabled="false" />
           	      	</span>                    
                    
           	      </p> 
                   <p class="criteria">
                     <span>
                       <label style="width: 100px;" class="fieldText">Destinatarios:</label>
						<s:textarea  name="gestionEnvioHistoricoBean.destinatario" 
						id="gestionEnvioHistoricoBean.destinatario" 
						placeholder="Para una búsqueda exhaustiva utilice además la vista 'Destinatarios'"
						theme="simple" cssClass="W238" 
						value="%{gestionEnvioHistoricoBean.destinatario}"/> 
           	      	</span>
                    <span>
                       <label style="width: 125px;" class="fieldText">Estado:</label>
                       <s:select 
						id="gestionEnvioHistoricoBean.estadoId" name="gestionEnvioHistoricoBean.estadoId" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						
						list="comboEstados" listKey="codigo" headerKey="" headerValue="Todos"
						listValue="descripcion" cssClass="W240" 
						
						value="%{gestionEnvioHistoricoBean.estadoId}" disabled="false" />
           	      	</span>            	      	
           	      </p> 	      
                  <p class="criteria">
                     <!-- <span>
                       <label style="width: 100px;" class="fieldText">Telef. Móvil:</label>
						<s:textarea  name="gestionEnvioHistoricoBean.telefonoMovil" 
						id="gestionEnvioHistoricoBean.telefonoMovil" 
						placeholder="Introduce números de teléfono móvil separados por ';'"
						theme="simple" cssClass="W240" 
						value="%{gestionEnvioHistoricoBean.telefonoMovil}"/> 
           	      	</span>-->
                 	<span>
                    	<label class="fieldText" style="width: 100px;">
                        Fecha Inicio: </label>
						<s:textfield
								name="gestionEnvioHistoricoBean.fechaDesde" value="%{gestionEnvioHistoricoBean.fechaDesde}" id="gestionEnvioHistoricoBean.fechaDesde"
								onchange="validateDates('gestionEnvioHistoricoBean.fechaDesde','gestionEnvioHistoricoBean.fechaHasta',this)"
								theme="simple" style="width:80px;" 
								size="10"
								cssClass="datepicker">
								<s:param name="value">
    							<s:date name="gestionEnvioHistoricoBean.fechaDesde" format="dd/MM/yyyy HH:mm"/>
  						</s:param>
						</s:textfield></span></p>
						<p class="criteria"><span>
                         <label class="fieldTextNoIco" style="width: 100px;">Fecha Fin:</label> 
                         <s:textfield
								name="gestionEnvioHistoricoBean.fechaHasta" value="%{gestionEnvioHistoricoBean.fechaHasta}" id="gestionEnvioHistoricoBean.fechaHasta"
								onchange="validateDates('gestionEnvioHistoricoBean.fechaDesde','gestionEnvioHistoricoBean.fechaHasta',this)"
								theme="simple"  style="width:80px;"
								size="10" 
								cssClass="datepicker">
						<s:param name="value">
    						<s:date name="gestionEnvioHistoricoBean.fechaHasta" format="dd/MM/yyyy HH:mm"/>
  						</s:param>
						</s:textfield>
                    </span>
                    <span>
                       <label style="width: 125px;" class="fieldText">ID Lote:</label>
						<s:textfield  name="gestionEnvioHistoricoBean.idLote" 
						id="gestionEnvioHistoricoBean.idLote" 
						theme="simple" cssClass="W80" 
						value="%{gestionEnvioHistoricoBean.idLote}"/> 
						
						ID Mensaje:
						<s:textfield  name="gestionEnvioHistoricoBean.MensajeId" 
						id="gestionEnvioHistoricoBean.MensajeId" 
						theme="simple" cssClass="W80" 
						value="%{gestionEnvioHistoricoBean.MensajeId}"/> 
           	      	</span>          	      	
           	      </p> 
           	      <p class="criteria">
           	      	<span>
                    	<label style="width: 100px;" class="fieldText">Usuario:</label>
						<s:textfield  name="gestionEnvioHistoricoBean.docUsuario" 
						id="gestionEnvioHistoricoBean.docUsuario" 
						theme="simple" cssClass="W120" 
						value="%{gestionEnvioHistoricoBean.docUsuario}"/> 
                    </span>
                    <span>
                    	<label style="width: 125px;" class="fieldText">Procedimiento/Servicio:</label>
						<s:textfield  name="gestionEnvioHistoricoBean.codSIA" 
						id="gestionEnvioHistoricoBean.codSIA" 
						theme="simple" cssClass="W120" 
						value="%{gestionEnvioHistoricoBean.codSIA}"/> 
                    </span>
           	      </p>
           	      <p class="criteria">
           	      	<span>
                		<label style="width: 100px;" class="fieldText">Organismo:</label>
						<s:textfield  name="gestionEnvioHistoricoBean.codOrganismo" 
						id="gestionEnvioHistoricoBean.codOrganismo" 
						theme="simple" cssClass="W120" 
						value="%{gestionEnvioHistoricoBean.codOrganismo}"/>     
                    </span>
                    <span>
                    	<label style="width: 125px;" class="fieldText">Organismo pagador:</label>
						<s:textfield  name="gestionEnvioHistoricoBean.codOrganismoPagador" 
						id="gestionEnvioHistoricoBean.codOrganismoPagador" 
						theme="simple" cssClass="W120" 
						value="%{gestionEnvioHistoricoBean.codOrganismoPagador}"/> 
                    </span>
           	      </p> 	
           	       <!-- ***************************** --><div class="hr"></div><!-- ********************************************** -->
                   <p class="criteria">
           	      	<span>
                		<label class="fieldText W90" style="width: 120px;">Vista:</label>
                    	<% String vistaEnvioHistoricoS = (String)request.getAttribute("vistaEnviosIdSelected"); %>
                    	<input type="button"  value="Lotes" id="btnlotesHistorico" onclick="chkViewEnviosHistoricosValue(this)" class="<%=(vistaEnvioHistoricoS!=null&&vistaEnvioHistoricoS.equals("2"))?"buttonSelected":"buttonNoSelected" %>"/>
                    	<input type="button" value="Mensajes" id="btnmensajesHistorico" onclick="chkViewEnviosHistoricosValue(this)" class="<%=(vistaEnvioHistoricoS!=null&&vistaEnvioHistoricoS.equals("1"))?"buttonSelected":"buttonNoSelected" %>"/>
                    	<input type="button"  value="Destinatarios" id="btndestinatariosHistorico" onclick="chkViewEnviosHistoricosValue(this)" class="<%=(vistaEnvioHistoricoS!=null&&vistaEnvioHistoricoS.equals("3"))?"buttonSelected":"buttonNoSelected" %>"/>
                    	<input type="hidden" id="gestionEnvioHistoricoBean.vistaEnviosId" name="gestionEnvioHistoricoBean.vistaEnviosId" value="<%=vistaEnvioHistoricoS%>"/>
                    </span>
           	      </p> 	        	                
                <div class="footerCriteria">
                    <span class="leftSide"></span>
                    <span class="rightSide">
                       <s:submit  theme="simple" value="%{getText('buttons.text.search')}" cssClass="button"/>
                    </span>
                </div>
            </div>
            <s:hidden id="gestionEnvioHistoricoBean.servicioId" name="gestionEnvioHistoricoBean.servicioId" value="%{gestionEnvioHistoricoBean.servicioId}"/>
            <s:hidden id="pageSizeSelected" name="pageSize" value="%{pageSize}"/> 
           </s:form>
           <input type="hidden" value="${resultCount}" id="numRegistro"/>
		   <h4 class="titular">Encontradas ${resultCount} entradas</h4>           
           		   <table class="tablaHeader">
			<thead>
                	<tr> 
                    	<th class="superHeader" colspan="5">
                        	<div class="floatLeft">
                        	</div>
                            <div class="floatRight">
                             <s:if test="resultCount > 30000">
                            	 <s:a cssClass="button" onclick="return showLimitExceeded();">Exportar Excel</s:a>
                             </s:if>
                             <s:else>
                            	<s:a cssClass="button" onclick="makeExcell(this)">Exportar Excel</s:a>
                            </s:else>
                            	<!-- <input type="button" class="button" value="Exportar Excel">-->
                            </div>
                        </th>
                    </tr>
                </thead>
               </table>
              
               <script>
               function showLimitExceeded(){
					alert('El limite de registros a exportar en Excel es de 30.000, ajuste la búsqueda y vuelva a intentarlo');
					return false;
               }
               </script>
	<s:form id="frmRealizaOperacionSeleccionados" method="POST"  action="accionSeleccionados">
	 <input type="hidden" name="CheckAllS" id="CheckAllS" value="0"/>                          
			<display:table 
				id="tableId"
				summary="Tabla de resultados de búsqueda de logs"
				name="listaGestionEnviosHistoricos" 
				pagesize="${pageSize}"
				requestURI="" 
				defaultsort="1" 
				defaultorder="ascending" 
				sort="external"
				export="true" 
				cellpadding="0" 
				cellspacing="0" 
				partialList="true"
				
				size='<%=request.getAttribute("totalSize")%>'
				decorator="es.mpr.template.web.decorators.TableWrapper">
				<display:setProperty name="css.tr.even" value="null" />
				<display:setProperty name="css.tr.odd"  value="odd" />
				<display:column class="darkTD TH17">
				</display:column>				
				<%-- operacion --%>
				<display:column property="aplicacion" titleKey="plataforma.gestionenvios.aplicacion" sortable="true"
					headerClass="TH130 separator center" class="" />
				<%-- entidad --%>
				<display:column property="nombreServicioHistoricoAction" titleKey="plataforma.gestionenvios.servicio" sortable="true"
					headerClass="TH110 separator center" class="gestionEnviosColumn" media="html" />
				<%-- Id --%>
				<display:column property="nombreLoteHistoricoAction"  titleKey="plataforma.gestionenvios.loteenvio" 
					sortable="true"	headerClass="TH130 separator center" class="gestionEnviosColumn"
					media="html" />	
				
				<% String vistaEnvioHistoricoS = (String)request.getAttribute("vistaEnviosIdSelected"); 
				
						if(vistaEnvioHistoricoS.equals("1")){
				%> 
					<display:column  property="idLote" titleKey="plataforma.gestionenvios.idLote" sortable="true" 
					headerClass="TH55 separator center" class="center" />
					<display:column  property="mensajeId" titleKey="plataforma.gestionenvios.idMensaje" sortable="true" 
					headerClass="TH55 separator center" class="center" />
					<display:column property="ultimoEnvioStr" titleKey="plataforma.gestionenvios.fecha" sortable="true"
					headerClass="TH100 separator center" class="TH100 center" />
					<display:column property="estado" titleKey="plataforma.gestionenvios.estado" sortable="true"
					headerClass="TH120 separator center" class="center" />
					
				<%
						}else if (vistaEnvioHistoricoS.equals("2")){
				%>  
				 	<display:column  property="idLote" titleKey="plataforma.gestionenvios.idLote" sortable="true" 
					headerClass="TH55 separator center" class="center" />
					<display:column property="ultimoEnvioStr" titleKey="plataforma.gestionenvios.fecha" sortable="true"
					headerClass="TH100 separator center" class="TH100 center" />
					<display:column property="estado" titleKey="plataforma.gestionenvios.estado" sortable="true"
					headerClass="TH120 separator center" class="center" />
				<%
				} else { 
				%> 
					<display:column  property="idLote" titleKey="plataforma.gestionenvios.idLote" sortable="true" 
					headerClass="TH55 separator center" class="center" />
					<display:column  property="mensajeId" titleKey="plataforma.gestionenvios.idMensaje" sortable="true" 
					headerClass="TH55 separator center" class="center" />
					<display:column property="ultimoEnvioStr" titleKey="plataforma.gestionenvios.fecha" sortable="true"
					headerClass="TH100 separator center" class="TH100 center" />
					<display:column property="estado" titleKey="plataforma.gestionenvios.estado" sortable="true"
					headerClass="TH120 separator center" class="center" />
					<display:column property="destinatarioHistoricoAction"  titleKey="plataforma.gestionenvios.destinatario" 
					sortable="true"	headerClass="TH160 separator center" class="gestionEnviosColumn"
					media="html" />	
				<%
				} 
				%> 

							
				
				<%-- acciones --%>
				<display:column property="gestionEnviosHistoricosAction"  
					headerClass="TH20 separator center" class="" media="html" />
			<plataforma:securityadmin usuarioLogueado="true" showIfGranted="true">
				<display:column property="verMisimHistoricoAction"  
					headerClass="TH20 separator center" class="" media="html" />
			</plataforma:securityadmin>
			</display:table>
			<table>
			<tfoot>
				<tr>
				 <td colspan="5" class="centerNumRows">
				    
				 		<label for="pageSize" >Elementos por página:</label>
				 		<s:select 
						id="pageSizeSelect" name="pageSize" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						
						list="comboPageSize" listKey="codigo"
						listValue="descripcion" cssClass="W50" 
						onChange="submitForm()"
						value="%{pageSize}" disabled="false" />
                            	
                </td>
				</tr>
               </tfoot>
                   </table>
                       <s:hidden id="gestionEnvioHistoricoBean.aplicacionId" name="gestionEnvioHistoricoBean.aplicacionId" value="%{gestionEnvioHistoricoBean.aplicacionId}" />
           	      	   <s:hidden id="gestionEnvioHistoricoBean.canalId" name="gestionEnvioHistoricoBean.canalId" value="%{gestionEnvioHistoricoBean.canalId}"/>     	      	
                       <s:hidden id="gestionEnvioHistoricoBean.servicioId" name="gestionEnvioHistoricoBean.servicioId" value="%{gestionEnvioHistoricoBean.servicioId}"/>     	      	
           	      	   <s:hidden id="gestionEnvioHistoricoBean.servidorId" name="gestionEnvioHistoricoBean.servidorId" value="%{gestionEnvioHistoricoBean.servidorId}" />
					   <s:hidden name="gestionEnvioHistoricoBean.email" id="gestionEnvioHistoricoBean.email" value="%{gestionEnvioHistoricoBean.email}"/> 
           	      	   <s:hidden id="gestionEnvioHistoricoBean.estadoId" name="gestionEnvioHistoricoBean.estadoId" value="%{gestionEnvioHistoricoBean.estadoId}"/>
					   <s:hidden name="gestionEnvioHistoricoBean.telefonoMovil" id="gestionEnvioHistoricoBean.telefonoMovil" value="%{gestionEnvioHistoricoBean.telefonoMovil}"/>
  					   <s:hidden name="gestionEnvioHistoricoBean.fechaDesde" value="%{gestionEnvioHistoricoBean.fechaDesde}" id="gestionEnvioHistoricoBean.fechaDesde"/>
                       <s:hidden name="gestionEnvioHistoricoBean.fechaHasta" value="%{gestionEnvioHistoricoBean.fechaHasta}" id="gestionEnvioHistoricoBean.fechaHasta"/>
					   <s:hidden name="gestionEnvioHistoricoBean.idLote" id="gestionEnvioHistoricoBean.idLote" value="%{gestionEnvioHistoricoBean.idLote}"/>   	      	
            </table>	
 
                      
           </s:form>					
			<br/>
			</div>
</div>
