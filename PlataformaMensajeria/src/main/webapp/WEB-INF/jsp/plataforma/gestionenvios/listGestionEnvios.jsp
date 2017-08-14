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
	var form = document.getElementById('frmBuscarGestionEnvios');
	checkPageSize();
	form.submit();
}
function setValue(obj){
	var value = obj.value;
	document.getElementById('gestionEnvioBean.servicioId').value=value;
}
function makeRequest(){
	document.getElementById('gestionEnvioBean.servicioId').value='';
	  $('#sid option').each(function() {
		        $(this).remove();
		});
	$.ajax({
        type: "POST",
        url: "ajaxLoadServicios.action",
        data: {idAplicacion:document.getElementById('gestionEnvioBean.aplicacionId').value}, // serializes the form's elements.
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
	            <label>Gestión de Envíos</label>
          	</h3>        
        <%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
		
<sj:dialog  id="dialogLotes" title="DETALLE LOTE" cssStyle="display:none" autoOpen="false"></sj:dialog>
<sj:dialog  id="dialogEnviosEmail" title="DETALLE MENSAJE EMAIL" cssStyle="display:none" autoOpen="false"></sj:dialog>
<sj:dialog  id="dialogEnviosSMS" title="DETALLE MENSAJE SMS" cssStyle="display:none" autoOpen="false"></sj:dialog>
<sj:dialog  id="dialogEnviosNotificacionPush" title="DETALLE MENSAJE NOTIFICACION PUSH" cssStyle="display:none" autoOpen="false"></sj:dialog>
<sj:dialog  id="dialogHistoricoEnvio" title="DETALLE DESTINATARIO" cssStyle="display:none" autoOpen="false"></sj:dialog>
				
        <div class="criteria">
		<s:form id="frmBuscarGestionEnvios" method="POST" action="listGestionEnvios"
		validate="false" theme="css_xhtml" onsubmit="return checkPageSize()">            	
                   <p class="criteria">
                     <span>
                       <label style="width: 100px;" class="fieldText">Aplicacion:</label>
                       <s:select 
						id="gestionEnvioBean.aplicacionId" name="gestionEnvioBean.aplicacionId" 
						emptyOption="false" theme="simple" onchange="makeRequest()"
						labelposition="left" headerKey="" headerValue="Todas"
						list="comboAplicaciones" listKey="codigo"
						listValue="descripcion" cssClass="W240" 
						
						value="%{gestionEnvioBean.aplicacionId}" disabled="false" />
           	      	</span>
           	      	<span>
                       <label style="width: 125px;" class="fieldText">Canal:</label>
                       <s:select 
						id="gestionEnvioBean.canalId" name="gestionEnvioBean.canalId" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboCanales" listKey="codigo" headerKey="" headerValue="Todos"
						listValue="descripcion" cssClass="W240" 
						
						value="%{gestionEnvioBean.canalId}" disabled="false" />     	      	
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
						
						value="%{gestionEnvioBean.servicioId}" disabled="false" />     	      	
           	      	</span>
                    <span>
                       <label style="width: 125px;" class="fieldText">Servidor / Proveedor:</label>
                       <s:select 
						id="gestionEnvioBean.servidorId" name="gestionEnvioBean.servidorId" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboServidores" listKey="codigo" headerKey="" headerValue="Todos"
						listValue="descripcion" cssClass="W240" 
						
						value="%{gestionEnvioBean.servidorId}" disabled="false" />
           	      	</span>                    
                    
           	      </p> 
                   <p class="criteria">
                     <span>
                       <label style="width: 100px;" class="fieldText">Destinatarios:</label>
						<s:textarea  name="gestionEnvioBean.destinatario" 
						id="gestionEnvioBean.destinatario" 
						placeholder="Para una búsqueda exhaustiva utilice además la vista 'Destinatarios'"
						theme="simple" cssClass="W238" 
						value="%{gestionEnvioBean.destinatario}"/> 
           	      	</span>
                    <span>
                       <label style="width: 125px;" class="fieldText">Estado:</label>
                       <s:select 
						id="gestionEnvioBean.estadoId" name="gestionEnvioBean.estadoId" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						
						list="comboEstados" listKey="codigo" headerKey="" headerValue="Todos"
						listValue="descripcion" cssClass="W240" 
						
						value="%{gestionEnvioBean.estadoId}" disabled="false" />
           	      	</span>            	      	
           	      </p> 	      
                  <p class="criteria">
                     <!-- <span>
                       <label style="width: 100px;" class="fieldText">Telef. Móvil:</label>
						<s:textarea  name="gestionEnvioBean.telefonoMovil" 
						id="gestionEnvioBean.telefonoMovil" 
						placeholder="Introduce números de teléfono móvil separados por ';'"
						theme="simple" cssClass="W240" 
						value="%{gestionEnvioBean.telefonoMovil}"/> 
           	      	</span>-->
                 	<span>
                    	<label class="fieldText" style="width: 100px;">
                        Fecha Inicio: </label>
						<s:textfield
								name="gestionEnvioBean.fechaDesde" value="%{gestionEnvioBean.fechaDesde}" id="gestionEnvioBean.fechaDesde"
								onchange="validateDates('gestionEnvioBean.fechaDesde','gestionEnvioBean.fechaHasta',this)"
								theme="simple"
								size="10" 
								cssClass="datepicker">
								<s:param name="value">
    							<s:date name="gestionEnvioBean.fechaDesde" format="dd/MM/yyyy HH:mm"/>
  						</s:param>
						</s:textfield> </span></p>
                         <p class="criteria"><span>
                        <label class="fieldTextNoIco" style="width: 100px;">Fecha Fin:</label>
                        <s:textfield
								name="gestionEnvioBean.fechaHasta" value="%{gestionEnvioBean.fechaHasta}" id="gestionEnvioBean.fechaHasta"
								onchange="validateDates('gestionEnvioBean.fechaDesde','gestionEnvioBean.fechaHasta',this)"
								theme="simple"
								size="10" 
								cssClass="datepicker">
						<s:param name="value">
    						<s:date name="gestionEnvioBean.fechaHasta" format="dd/MM/yyyy HH:mm"/>
  						</s:param>
						</s:textfield>
                    </span>
                    <span>
                       <label style="width: 125px;" class="fieldText">ID Lote:</label>
						<s:textfield  name="gestionEnvioBean.idLote" 
						id="gestionEnvioBean.idLote" 
						theme="simple" cssClass="W80" 
						value="%{gestionEnvioBean.idLote}"/> 
           	        	      	
                      	ID Mensaje:
						<s:textfield  name="gestionEnvioBean.MensajeId" 
						id="gestionEnvioBean.MensajeId" 
						theme="simple" cssClass="W80" 
						value="%{gestionEnvioBean.MensajeId}"/> 
           	      	</span>              	      	
           	      </p>
           	      <p class="criteria">
           	      	<span>
                    	<label style="width: 100px;" class="fieldText">Usuario:</label>
						<s:textfield  name="gestionEnvioBean.docUsuario" 
						id="gestionEnvioBean.docUsuario" 
						theme="simple" cssClass="W120" 
						value="%{gestionEnvioBean.docUsuario}"/> 
                    </span>
                    <span>
                    	<label style="width: 125px;" class="fieldText">Procedimiento/Servicio:</label>
						<s:textfield  name="gestionEnvioBean.codSIA" 
						id="gestionEnvioBean.codSIA" 
						theme="simple" cssClass="W120" 
						value="%{gestionEnvioBean.codSIA}"/> 
                    </span>
           	      </p>
           	      <p class="criteria">
           	      	<span>
                		<label style="width: 100px;" class="fieldText">Organismo:</label>
						<s:textfield  name="gestionEnvioBean.codOrganismo" 
						id="gestionEnvioBean.codOrganismo" 
						theme="simple" cssClass="W120" 
						value="%{gestionEnvioBean.codOrganismo}"/>     
                    </span>
                    <span>
                    	<label style="width: 125px;" class="fieldText">Organismo pagador:</label>
						<s:textfield  name="gestionEnvioBean.codOrganismoPagador" 
						id="gestionEnvioBean.codOrganismoPagador" 
						theme="simple" cssClass="W120" 
						value="%{gestionEnvioBean.codOrganismoPagador}"/> 
                    </span>
           	      </p> 	
   <!-- ***************************** --><div class="hr"></div><!-- ********************************************** -->
                   <p class="criteria">
           	      	<span>
                		<label class="fieldText W90" style="width: 120px;">Vista:</label>
                    	<% String vistaEnvioS = (String)request.getAttribute("vistaEnviosIdSelected"); %>
                    	<input type="button"  value="Lotes" id="btnlotes" onclick="chkViewEnviosValue(this)" class="<%=(vistaEnvioS!=null&&vistaEnvioS.equals("2"))?"buttonSelected":"buttonNoSelected" %>"/>
                    	<input type="button" value="Mensajes" id="btnmensajes" onclick="chkViewEnviosValue(this)" class="<%=(vistaEnvioS!=null&&vistaEnvioS.equals("1"))?"buttonSelected":"buttonNoSelected" %>"/>
                    	<input type="button"  value="Destinatarios" id="btndestinatarios" onclick="chkViewEnviosValue(this)" class="<%=(vistaEnvioS!=null&&vistaEnvioS.equals("3"))?"buttonSelected":"buttonNoSelected" %>"/>
                    	<input type="hidden" id="gestionEnvioBean.vistaEnviosId" name="gestionEnvioBean.vistaEnviosId" value="<%=vistaEnvioS%>"/>
                    </span>
           	      </p> 	
                <div class="footerCriteria">
                    <span class="leftSide"></span>
                    <span class="rightSide">
                    	<a class="button" href="buscarGestionEnviosHistoricos.action">Gestión de Envíos Históricos</a>
                       <s:submit onclick="mostrarCargando()" theme="simple" value="%{getText('buttons.text.search')}" cssClass="button"/>
                    </span>
                </div>
            </div>
            <div>
          <!--  <img id="indicator2" src="images/indicator.gif" alt="..." style="display:none;"/>--> 
            </div>
            <s:hidden id="gestionEnvioBean.servicioId" name="gestionEnvioBean.servicioId" value="%{gestionEnvioBean.servicioId}"/>
            <s:hidden id="pageSizeSelected" name="pageSize" value="%{pageSize}"/> 
           </s:form>
           <input type="hidden" value="${resultCount}" id="numRegistro"/>
		   <h4 class="titular">Encontradas ${resultCount} entradas</h4>           
           		   <table class="tablaHeader">
			<thead>
                	<tr> 
                    	<th class="superHeader" colspan="5">
                        	<div class="floatLeft">
                        		<a class="button" onclick="selectAllS()" href="#">Seleccionar Todos</a>
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
               function selectAllS(){
            	   var listaChecks = document.getElementById('frmRealizaOperacionSeleccionados').checkDelList;
            	   var checkAllS  = document.getElementById('CheckAllS');
            	   if(listaChecks){
                	   if(checkAllS.value=="0"){
                		   listaChecks.checked=true;
                	   }else{
                		   listaChecks.checked=false;
                	   }
                   for (i = 0; lcheck = listaChecks[i]; i++) {
                       if (checkAllS.value=="0") {
                           lcheck.checked=true;   
                           
                       }else{
                    	   lcheck.checked=false;
                    	   
                       }
                   }  
                   if(checkAllS.value=="0"){
                	   checkAllS.value= "1";
                   }else{
                	   checkAllS.value= "0";
                   }
                   checkBotonAccionSeleccionados();
            	   }
               }
               	function checkBotonAccionSeleccionados(){
               		var listaChecks = document.getElementById('frmRealizaOperacionSeleccionados').checkDelList;
               		var botonAnular = document.getElementById('anularSeleccionados');
               		var botonRevisar = document.getElementById('reenviar');
               		var enable=false;
               		if(listaChecks.checked){
               			enable=true;
               		}
               		for (i = 0; lcheck = listaChecks[i]; i++) {
               	        if (lcheck.checked) {
               	            enable=true;
               	        }
               	    }
               		if(enable){
               			botonAnular.disabled="";
               			botonRevisar.disabled="";
               		}else{
               			botonAnular.disabled="disabled";
               			botonRevisar.disabled="disabled";
               		}
               	}
               </script>
	<s:form id="frmRealizaOperacionSeleccionados" method="POST"  action="accionSeleccionados">
	 <input type="hidden" name="CheckAllS" id="CheckAllS" value="0"/>                          
			<display:table 
				id="tableId"
				summary="Tabla de resultados de búsqueda de logs"
				name="listaGestionEnvios" 
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
					<center>
					<% String vistaEnv = (String)request.getAttribute("vistaEnviosIdSelected"); 
				
						if(vistaEnv.equals("1") || vistaEnv.equals("3")){
				%>
					<input type="checkbox" onclick="checkBotonAccionSeleccionados()" id="checkDelList" name="checkDelList" value="${tableId.envioId}"/></center>
					<%
						}else{
				%>  
				<input type="checkbox" onclick="checkBotonAccionSeleccionados()" id="checkDelList" name="checkDelList" value="${tableId.idLote}"/></center>
				<%
				}
				%> 
					<input type="hidden" idd="__checkbox_checkDelList" name="__checkbox_checkDelList"/>
					</center>
				</display:column>				
				<%-- operacion --%>
				<display:column property="aplicacion" titleKey="plataforma.gestionenvios.aplicacion" sortable="true"
					headerClass="TH130 separator center" class="" />
				<%-- entidad --%>
				<display:column property="servicio" titleKey="plataforma.gestionenvios.servicio" sortable="true"
					headerClass="TH110 separator center" class="" />
				<%-- Id --%>
				<display:column property="nombreLoteAction"  titleKey="plataforma.gestionenvios.loteenvio" 
					sortable="true"	headerClass="TH130 separator center" class="gestionEnviosColumn"
					media="html" />	
				<% String vistaEnv = (String)request.getAttribute("vistaEnviosIdSelected"); 
				
						if(vistaEnv.equals("1")){
				%>
					<display:column  property="idLote" titleKey="plataforma.gestionenvios.idLote" sortable="true" 
					headerClass="TH55 separator center" class="center" />
					<display:column  property="mensajeId" titleKey="plataforma.gestionenvios.idMensaje" sortable="true" 
					headerClass="TH55 separator center" class="center" />
					<display:column property="ultimoEnvioStr" titleKey="plataforma.gestionenvios.fecha" sortable="true"
					headerClass="TH100 separator center" class="TH100 center" />
					<display:column property="estado" titleKey="plataforma.gestionenvios.estado" sortable="true"
					headerClass="TH120 separator center" class="" />
				<%
						}else if (vistaEnv.equals("2")){
				%>  
				 	<display:column  property="idLote" titleKey="plataforma.gestionenvios.idLote" sortable="true" 
					headerClass="TH55 separator center" class="center" />
					<display:column property="ultimoEnvioStr" titleKey="plataforma.gestionenvios.fecha" sortable="true"
					headerClass="TH100 separator center" class="TH100 center" />
					<display:column property="estado" titleKey="plataforma.gestionenvios.estado" sortable="true"
					headerClass="TH120 separator center" class="" />
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
					headerClass="TH120 separator center" class="" />
					<display:column property="destinatarioAction"  titleKey="plataforma.gestionenvios.destinatario" 
					sortable="true"	headerClass="TH160 separator center" class="gestionEnviosColumn"
					media="html" />	

				<%
				} 
				%> 

				<display:column property="gestionEnviosAction"  
					headerClass="TH20 separator " class="" media="html" />					
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
				<tr>
                    	<td colspan="5">
                        	<span class="leftSide">
                        	<script>
                        		function setOp(val){
                        			document.getElementById('operacionMsg').value=val;
                        			var checkAllS = document.getElementById('CheckAllS').value;
                        			
                        			if(val=="A"){
                        				if(checkAllS == "1"){
                        					var conf = confirm("¿Seguro que desea anular todos los resultados de esta búsqueda?");
	                        				if(!conf){
	                        					return false;
	                        				}
                        				}
                        			}else if(val="R"){
                        				//alert(checkAllS);
                        				if(checkAllS == "1"){
	                        				var conf = confirm("¿Seguro que desea reenviar todos los resultados de esta búsqueda?");
	                        				if(!conf){
	                        					return false;
	                        				}
                        				}
                        			}
                        			return true;
                        		}
                        	</script>
                            	<!-- <input type="button" class="button" value="Eliminar Seleccionados">-->
                            	<input type="submit"  id="anularSeleccionados" onclick="return setOp('A');" disabled="true" value="Anular Seleccionados" class="button"/>
                            	&nbsp;<input type="submit"  id="reenviar" name="revisar" onclick="return setOp('R');" disabled="true" value="Reenviar" class="button"/>
                            	<s:hidden name="operacionMsg" id="operacionMsg" value=""/>
                            </span>
		                    <span class="rightSide">
		                     &nbsp;
                            </span>
                        </td>
                    </tr>
               </tfoot>
                   </table>
                        <s:hidden id="gestionEnvioBean.aplicacionId" name="gestionEnvioBean.aplicacionId" value="%{gestionEnvioBean.aplicacionId}" />
           	      	   <s:hidden id="gestionEnvioBean.canalId" name="gestionEnvioBean.canalId" value="%{gestionEnvioBean.canalId}"/>     	      	
                       <s:hidden id="gestionEnvioBean.servicioId" name="gestionEnvioBean.servicioId" value="%{gestionEnvioBean.servicioId}"/>     	      	
           	      	   <s:hidden id="gestionEnvioBean.servidorId" name="gestionEnvioBean.servidorId" value="%{gestionEnvioBean.servidorId}" />
					   <s:hidden name="gestionEnvioBean.email" id="gestionEnvioBean.email" value="%{gestionEnvioBean.email}"/> 
           	      	   <s:hidden id="gestionEnvioBean.estadoId" name="gestionEnvioBean.estadoId" value="%{gestionEnvioBean.estadoId}"/>
					   <s:hidden name="gestionEnvioBean.telefonoMovil" id="gestionEnvioBean.telefonoMovil" value="%{gestionEnvioBean.telefonoMovil}"/>
  					   <s:hidden name="gestionEnvioBean.fechaDesde" value="%{gestionEnvioBean.fechaDesde}" id="gestionEnvioBean.fechaDesde"/>
                       <s:hidden name="gestionEnvioBean.fechaHasta" value="%{gestionEnvioBean.fechaHasta}" id="gestionEnvioBean.fechaHasta"/>
					   <s:hidden name="gestionEnvioBean.idLote" id="gestionEnvioBean.idLote" value="%{gestionEnvioBean.idLote}"/> 
					   <s:hidden name="gestionEnvioBean.mensajeId" value="%{gestionEnvioBean.mensajeId}" id="gestionEnvioBean.mensajeId"/>
					   <s:hidden name="gestionEnvioBean.multidestinatario" id="gestionEnvioBean.multidestinatario" value="%{gestionEnvioBean.multidestinatario}"/>   	      	  	      	
            </table>	
 
                      
           </s:form>					
			<br/>
			</div>
		<!-- 	<script>
				function mostrarCargando(){
					document.getElementById("indicator2").style.display="block";
				}
				function cerrarCargando(){
					document.getElementById("indicator2").style.display="none";
				}
			</script>-->
</div>

