
<head>
		

		<style type="text/css">
		textarea {
			width: 100%;
			height: 100px;
		}
		</style>
</head>
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>

<!-- <div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>-->

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->

        <div class="mainContent">	
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
             <div class="criteria">
		<s:form id="frmEjecutarJob" method="POST" action="seleccionarJob" validate="false" theme="css_xhtml">
                    <p class="criteria">
                 	 <span>
                       <label style="width: 100px;" class="fieldText">Job a Ejecutar:</label>
                       <s:select 
						id="jobBean.nombreJob" name="jobBean.nombreJob" 
						emptyOption="false" theme="simple"
						labelposition="left" onchange="cargarNuevoJob()"
						list="comboJobs" listKey="codigo" headerKey="" 
						listValue="descripcion" cssClass="W240" 
						value="%{jobBean.nombreJob}" disabled="false" />     	      	
           	      	</span>
           	      </p> 
         
            
            
            	 <p class="criteria">	 
                 	 <s:if test="%{jobBean == null || jobBean.nombreJob == 'HISTORIFICACION' }"> 
	                 	 <span>
	                       <label style="width: 100px;" class="fieldText">Servicio:</label>
	                       <s:select 
							id="jobBean.servicioId" name="jobBean.servicioId" 
							emptyOption="false" theme="simple" 
							labelposition="left"
							list="comboServicios" listKey="codigo" headerKey="" headerValue="Todos"
							listValue="descripcion" cssClass="W240" 
							value="%{jobBean.servicioId}" disabled="false" />     	      	
	           	      	</span>
           	      	</s:if>
           	      	<s:if test="%{jobBean.nombreJob == 'CONSERVACION' }"> 
	                 	 <span>
	                       <label style="width: 100px;" class="fieldText">Servicio:</label>
	                       <s:select 
							id="jobBean.servicioId" name="jobBean.servicioId" 
							emptyOption="false" theme="simple" 
							labelposition="left"
							list="comboServicios" listKey="codigo" headerKey="" headerValue="Todos"
							listValue="descripcion" cssClass="W240" 
							value="%{jobBean.servicioId}" disabled="false" />     	      	
	           	      	</span>
           	      	</s:if>
           	      </p>
           	      <p class="criteria">
                   <s:if test="%{jobBean.nombreJob != 'REENVIO' }">                                
                    <span>
                     <label style="width: 100px;" class="fieldText">Fecha Inicio:</label>
                      <s:textfield
								name="jobBean.fecha" value="%{jobBean.fecha}" id="jobBean.fecha"
								theme="simple"  style="width:60px;"
								size="10" maxlength="10"
								cssClass="datepickerEstadisticas" autocomplete="off">
								<s:param name="value">
    							<s:date name="jobBean.fecha" format="dd/MM/yyyy"/>
  						</s:param>
						</s:textfield>
           	      	</span>
           	      	<s:if test="%{jobBean.nombreJob == 'DIR3' }"> 
	           	      	<span>
	                     <label style="width: 100px;" class="fieldText">Fecha Fin:</label>
	                      <s:textfield
									name="jobBean.fechaFin" value="%{jobBean.fechaFin}" id="jobBean.fechaFin"
									theme="simple"  style="width:60px;"
									size="10" maxlength="10"
									cssClass="datepickerEstadisticas" autocomplete="off">
									<s:param name="value">
	    							<s:date name="jobBean.fechaFin" format="dd/MM/yyyy"/>
	  						</s:param>
							</s:textfield>
	           	      	</span>                                  
                    </s:if> 
           	      	<div class="footerCriteria">
	                    <span class="leftSide"></span>
	                    <span class="rightSide">
	                       <s:submit  theme="simple" value="%{getText('buttons.text.ejecutar')}" cssClass="button"/>
	                    </span>
             		</div>
           	      	</s:if>
           	      	                   
           	      </p>
  			 </s:form> 	 
            <s:if test="%{jobBean.nombreJob == 'REENVIO' }">              	           	      	
<%--         	  <s:hidden id="pageSizeSelected" name="pageSize" value="%{pageSize}"/>  --%>
			  <br>	
	          <h4 style="padding-left: 10px;">Planificaciones Automaticas</h4>
				<br>
        	   <display:table 
				id="tablaProcesos"
				summary="Tabla de resultados de búsqueda de logs"
				name="listaProcesos"				
				requestURI="" 
				defaultsort="1" 
				defaultorder="ascending" 
				sort="external"
				export="true" 
				cellpadding="0" 
				cellspacing="0" 
				partialList="false"
				
				
				decorator="es.mpr.template.web.decorators.TableWrapper">
				
							
				<%-- nombre --%>
				<display:column property="nombre" titleKey="plataforma.ejecucionjobs.nombre" sortable="true"
					headerClass="TH130 separator center" class="" />
				<%-- inicio ultima ejecucion --%>
				<display:column property="inicioUltimaEjecucion" titleKey="plataforma.ejecucionjobs.inicioUltimaEjecucion" sortable="true"
					headerClass="TH30 separator center" class="gestionEnviosColumn" media="html"  format="{0,date,MM/dd/yyyy hh:mm:ss}" />
				
				<%-- fin ultima ejecucion --%>
				<display:column property="finUltimaEjecucion"  titleKey="plataforma.ejecucionjobs.finUltimaEjecucion" 
					sortable="true"	headerClass="TH50 separator center" class="gestionEnviosColumn" format="{0,date,MM/dd/yyyy hh:mm:ss}"
					media="html" />
				<%-- proxima ejecucion --%>
				<display:column property="proximaEjecucion"  titleKey="plataforma.ejecucionjobs.proximaEjecucion" 
					sortable="false"	headerClass="TH50 separator center" class="gestionEnviosColumn"
					media="html" />
				<%-- proxima ejecucion --%>
				<display:column property="estado"  titleKey="plataforma.ejecucionjobs.estado" 
					sortable="false"	headerClass="TH60 separator center" class="gestionEnviosColumn"
					media="html" />
				<%-- activo --%>
				<display:column property="isActivo" titleKey="plataforma.ejecucionjobs.activo" sortable="false"
 					headerClass="TH50 separator center" class="center" />
				<%-- acciones --%>
				<display:column property="ejecucionJobAction"
  					headerClass="TH50 separator center" class="" media="html" />
			</display:table>
			
			<br><br>
			
			<h4 style="padding-left: 10px;">Planificaciones Manuales</h4>			
			<s:form id="frmEliminarProcesosManualesSeleccionados" method="POST" onsubmit="return confirmDeleteSelected();" action="deleteProcesosManualesSeleccionados">
				<h4 class="titular">Encontradas <%=request.getAttribute("totalSize")%> entradas</h4>
			 <script>
               	function checkBotonEliminarSeleccionados(){
               		var listaChecks = document.getElementById('frmEliminarProcesosManualesSeleccionados').checkDelList;

               		var botonEliminarSeleccionados = document.getElementById('eliminaSeleccionados');
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
               			botonEliminarSeleccionados.disabled="";
               		}else{
               			botonEliminarSeleccionados.disabled="disabled";
               		}
               	}
               </script>
                
			 <display:table 
				id="tableId"
				summary="Tabla de resultados de planificaciones manuales"
				name="listaProcesosManuales" 
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
				<display:column class="darkTD TH15">
					<center>
					
					<input type="checkbox" onclick="checkBotonEliminarSeleccionados()" id="checkDelList" name="checkDelList" value="${tableId.procesosManualesId }"/></center> 
					<input type="hidden" idd="__checkbox_checkDelList" name="__checkbox_checkDelList"/>
 				
				</display:column>
							
				<%-- nombre --%>
				<display:column property="nombre" titleKey="plataforma.ejecucionjobs.nombre" sortable="true"
					headerClass="TH130 separator center" class="" />
				<%-- job --%>
				<display:column property="job" titleKey="plataforma.ejecucionjobs.job" sortable="true"
					headerClass="TH130 separator center" class="" />
				<%-- id Servicios --%>
				<display:column property="parametro2" titleKey="plataforma.ejecucionjobs.idServicios" sortable="false"
					headerClass="TH130 separator center" class="" />
				<%-- acciones --%>
				<display:column property="procesosManualesAction"
  					headerClass="TH30 separator center" class="" media="html" />
			</display:table>
			<table>
			<tfoot>
				<tr>
                    	<td colspan="5">
                        	<span class="leftSide">
                            	<!-- <input type="button" class="button" value="Eliminar Seleccionados">-->
                            	<s:submit  id="eliminaSeleccionados" theme="simple" disabled="true" value="%{getText('button.plataforma.eliminarseleccionados')}" cssClass="button"/>
                            </span>
		                    <span class="rightSide">
                            	<input  type="button" value="Nueva Entrada" class="button" onclick='javascript:location.href="nuevoProcesoManual.action"'/>
                            </span>
                        </td>
                    </tr>
               </tfoot>
             </table>
		     </s:form>         
            </s:if>
         	<s:else>
         	
             </s:else>           	      
            
            </div>
          
           		

   <script>
	function cargarNuevoJob() {              
        document.frmEjecutarJob.action="cargarServiciosConservacionEvent.action";
	    document.frmEjecutarJob.submit();

	}

	function confirmEjecutarManual(){
		var r = confirm('¿Desea ejecutar la planificación manual?');
		if(r){return true;
		}else{return false;
		}
	} 

	function confirmEjecutar(){
		var r = confirm('¿Desea ejecutar el proceso?');
		if(r){return true;
		}else{return false;
		}
	} 

</script>        