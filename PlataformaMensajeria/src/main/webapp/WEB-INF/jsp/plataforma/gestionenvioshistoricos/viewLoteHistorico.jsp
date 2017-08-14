<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
    <sj:dialog  id="dialogLotes" title="DETALLE LOTE" cssStyle="height:auto;padding:0;" autoOpen="false">
<div id="LoteHistorico" class="ui-dialog-content ui-widget-content" style="display: block; min-height: 0px; height: auto;">
	<div class="editContainer">
        <div style="width: 135px" class="nameDescription">
            <label>Datos Generales</label>
        </div>
        <div style="width: 770px" class="editContent">
 			<p class="criteria">
 				<span style="width: 49%">
	                <label style="width: 90px;" class="fieldText">Aplicacion:</label>
 	                <strong><s:label value="%{detalleLote.nombreAplicacion}"/></strong>
 				</span>
 			</p>
 			<p class="criteria">
 				<span style="width: 49%">
	                <label style="width: 90px;" class="fieldText">Servicio:</label>
	                <strong><s:label value="%{detalleLote.nombreServicio}"/></strong>
 				</span>
            </p>        
            <p class="criteria">
            	<span style="width: 49%">
	                <label style="width: 90px;" class="fieldText">Lote Envío::</label>
	                <strong><s:label value="%{detalleLote.nombreLoteEnvio}"/></strong>
            	</span>
            </p>
           <p class="criteria">
            	<span style="width: 49%">
	                <label style="width: 90px;" class="fieldText">ID Lote Envío:</label>
	                <strong><s:label value="%{detalleLote.idLoteEnvio}"/></strong>
            	</span>
            </p>
            
        </div>
    </div>
    <div class="editContainer">
        <div style="width: 135px" class="nameDescription">
            <label>Mensajes Lote</label>
        </div>
         
    <div style="width: 770px"  class="editContent">
     <s:set var="total">${resultCount}</s:set>
    <s:if test="#total > 20">
          <h4 class="titular">Encontradas ${resultCount} entradas. Por rendimiento sólo se muestran 20. Para más información realice la búsqueda por mensaje</h4>
    </s:if>
     <s:if test="#total < 20">
        <h4 class="titular">Encontradas ${resultCount} entradas</h4>
    </s:if>
   
	    <display:table 
					id="tableLotesHistoricoId"
					summary="Tabla de resultados de búsqueda de logs"
					name="listaGestionEnviosMensajesHistoricos" 
					pagesize="${pageSize}"
					requestURI="viewLoteHistorico.action" 
					defaultsort="1" 
					defaultorder="ascending" 
					sort="external"
					export="true" 
					cellpadding="0" 
					cellspacing="0" 
					partialList="true"
					requestURIcontext="false"
					size="20"
					decorator="es.mpr.template.web.decorators.TableWrapper">
					<display:setProperty name="css.tr.even" value="null" />
					<display:setProperty name="css.tr.odd"  value="odd" />
					
					<display:column property="mensajeId" titleKey="plataforma.gestionViewLotes.mensaje" 
						headerClass="TH110 separator center"  class="TH110 separator center" />
					
					<display:column property="fechaFormateada" titleKey="plataforma.gestionViewLotes.fecha" 
						headerClass="TH110 separator center" class="TH110 separator center" />
					
					<display:column property="estadoActual" titleKey="plataforma.gestionViewLotes.estado" 
					headerClass="TH110 separator center" class="TH110 separator center" />
					
					
					<%-- acciones --%>
					<display:column property="gestionLotesHistoricosAction"  titleKey="plataforma.gestionViewLotes.blanco" 
						headerClass="TH20 separator center " class="TH20 separator center " media="html" />					
			</display:table>
		</div>
	</div>

</div>
</sj:dialog>