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
            <h3 class="pageNameButtons">
    			<span class="floatRight"></span>
	            <label>Organismos</label>
          	</h3>  
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
            <div class="criteria">
		<s:form id="frmBuscarOrganismos" method="POST" action="buscarOrganismos"
		validate="false" theme="css_xhtml">            	
            	<p class="criteria">
                    <label class="fieldText" style="width: 100px;">Cod. Organismo:</label>
                    <s:textfield
							name="organismo.dir3" id="organismo.dir3"
							theme="simple" 
							key="field.general.dir3" cssStyle="width:250px;" labelposition="left"
							size="60" maxlength="9"
							cssClass="input_tablas_registro" />
                </p>
            	<p class="criteria">
                    <label class="fieldText" style="width: 100px;">Nombre:</label>
                    <s:textfield
							name="organismo.nombre" id="organismo.nombre"
							theme="simple" 
							key="field.general.nombre" cssStyle="width:250px;" labelposition="left"
							size="60" maxlength="255"
							cssClass="input_tablas_registro" />
                </p>
                
                <p class="criteria">
                	<span>
                    	<label class="fieldText" style="width: 100px;">
                        Fecha Desde: </label>
						<s:textfield
 							name="organismo.fechaCreacionDesde" value="%{organismo.fechaCreacionDesde}" id="organismo.fechaCreacionDesde" 
							onchange="validateDates('organismo.fechaCreacionDesde','organismo.fechaCreacionDesde',this)" 
 							theme="simple" 
 							size="10"  
 							cssClass="datepicker"> 
 							<s:param name="value"> 
	  							<s:date name="organismo.fechaCreacionDesde" format="dd/MM/yyyy HH:mm"/> 
 							</s:param> 
 						</s:textfield>  
					</span>
				</p>
				
				<p class="criteria">
                	<span>
                    	<label class="fieldText" style="width: 100px;">
                        Fecha Hasta: </label>
						<s:textfield
 							name="organismo.fechaCreacionHasta" value="%{organismo.fechaCreacionHasta}" id="organismo.fechaCreacionHasta" 
							onchange="validateDates('organismo.fechaCreacionHasta','organismo.fechaCreacionHasta',this)" 
 							theme="simple" 
 							size="10" 
							cssClass="datepicker"> 
							<s:param name="value"> 
	  							<s:date name="organismo.fechaCreacionHasta" format="dd/MM/yyyy HH:mm"/> 
 							</s:param> 
						</s:textfield>  
					</span>
				</p>
                
                <p class="criteria">
                     <span>
                     <label theme="simple" style="width: 100px;" class="fieldText">Estado:</label>
	                    <s:select id="organismo.estado"
						name="organismo.estado" emptyOption="false"
						theme="simple" labelposition="left" list="comboTiposEstados"
						listKey="codigo" headerKey="" headerValue="Todos"
						listValue="descripcion" cssClass="W100" 
						value="%{organismo.estado}" disabled="false" />
					</span>   
           	      	<span>
           	      		<label theme="simple" style="width: 130px;" class="fieldText">Asociadas a servicios:</label>
						<s:checkbox theme="simple" name="organismo.asociadosServicio" id="organismo.asociadosServicio" value="%{organismo.asociadosServicio}"></s:checkbox>
           	      	</span> 
                </p>
                <div class="footerCriteria">
                    <span class="leftSide"></span>
                    <span class="rightSide">
                       <s:submit  theme="simple" value="%{getText('buttons.text.search')}" cssClass="button"/>
                    </span>
                </div>
            </div>
           </s:form>
 
 		<s:set name="listaOrganismos" value="%{listaOrganismos}" />

		<s:form id="frmEliminarOrganismosSeleccionados" method="POST" onsubmit="return confirmDeleteSelected();" action="deleteOrganismosSeleccionados">
		<h4 class="titular">Encontradas <%=request.getAttribute("totalSize")%> entradas</h4>
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
               <script>
               	function checkBotonEliminarSeleccionados(){
               		var listaChecks = document.getElementById('frmEliminarOrganismosSeleccionados').checkDelList;

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
				summary="Tabla de resultados de b�squeda de organismos"
				name="listaOrganismos" 
				
				class="" 
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
					
					<input type="checkbox" onclick="checkBotonEliminarSeleccionados()" id="checkDelList" name="checkDelList" value="${tableId.organismoId }"/></center> 
					<input type="hidden" idd="__checkbox_checkDelList" name="__checkbox_checkDelList"/>
 				
				</display:column>
				<%-- nombre --%>
				<display:column property="organismoId" titleKey="field.organismo.id" sortable="false"
					headerClass="TH30 separator center" class="center" />	
				<display:column property="dir3" titleKey="field.organismo.DIR3" sortable="true"
					headerClass="TH100 separator center" class="center" />			
				<display:column property="nombre" titleKey="field.organismo.nombre" sortable="true"
					headerClass="TH170 separator center" class="" />
				
				<%-- descripcion --%>
				<display:column property="estado" titleKey="field.organismo.estado" sortable="true"
					headerClass="TH70 separator center" class="center" />
				<display:column property="fechaEstadoStr" titleKey="field.organismo.fechaestado" sortable="false"
					headerClass="TH80 separator center" class="center" />
				<display:column property="codUnidadRaiz" titleKey="field.organismo.padre" sortable="true"
					headerClass="TH100 separator center" class="center" /> 
				<display:column property="isActivo" titleKey="field.organismo.activo" sortable="false"
					headerClass="TH50 separator center" class="center" /> 
				<%-- acciones --%>
				<display:column property="organismoAction"
					headerClass="TH45 separator center" class="" media="html" />
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
                            	<input  type="button" value="Nueva Entrada" class="button" onclick='javascript:location.href="nuevoOrganismo.action"'/>
                            </span>
                        </td>
                    </tr>
               </tfoot>
                   </table>
            </s:form>
