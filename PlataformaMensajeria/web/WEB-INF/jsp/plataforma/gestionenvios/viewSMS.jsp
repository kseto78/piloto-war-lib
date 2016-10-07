<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<sj:dialog  id="dialogEnviosSMS" title="DETALLE SMS" cssStyle="min-height:90px" autoOpen="false">
<div id="SMS" class="ui-dialog-content ui-widget-content" display: block; min-height: 0px; height: auto;">
	<div class="editContainer">
        <div class="nameDescription" style="width: 135px">
            <label>Datos Generales</label>
        </div>
        <div class="editContent" style="width:770px;">
            <p class="criteria">
                <label style="width: 90px;" class="fieldText">ID SMS:</label>
                <strong><s:label value="%{detalleEmail.mensajeId}"/></strong>
            </p>         
            <p class="criteria">
            	<span style="width: 280px;">
                    <label style="width: 90px;" class="fieldText">Aplicación:</label>
                    <strong><s:label value="%{detalleEmail.nombreAplicacion}"/></strong>
                </span>
            	<span>
                    <label style="width: 70px;" class="fieldText">Servicio:</label>
                    <strong><s:label value="%{detalleEmail.nombreServicio}"/></strong>
	            </span>
            </p>
            <p class="criteria">
                <label style="width: 90px;" class="fieldText">Lote Envío:</label>
                <strong><s:label value="%{detalleEmail.nombreLoteEnvio}"/></strong>
            </p>
            <p class="criteria">
                <label style="width: 90px;" class="fieldText">ID Lote Envío:</label>
                <strong><s:label value="%{detalleEmail.idLote}"/></strong>
            </p>
            <p class="criteria">
            	<span style="width: 280px;">
                    <label style="width: 90px;" class="fieldText">Usuario:</label>
                    <strong><s:label value="%{detalleEmail.docUsuario}"/></strong>
                </span>
            	<span>
                    <label style="width: 140px;" class="fieldText">Procedimiento/Servicio:</label>
                    <strong><s:label value="%{detalleEmail.codSIA}"/></strong>
	            </span>
            </p>
            <p class="criteria">
            	<span style="width: 280px;">
                    <label style="width: 90px;" class="fieldText">Organismo:</label>
                    <strong><s:label value="%{detalleEmail.codOrganismo}"/></strong>
                </span>
            	<span>
                    <label style="width: 140px;" class="fieldText">Organismo pagador:</label>
                    <strong><s:label value="%{detalleEmail.codOrganismoPagador}"/></strong>
	            </span>
            </p>
        </div>
    </div>
    <div class="editContainer">
        <div class="nameDescription" style="width: 135px">
            <label>SMS</label>
        </div>
        <div class="editContent" style="width:770px;">
           <p class="criteria">
                <label style="width: 90px;" class="fieldText">Mensaje:</label>
                <strong style="display: inline-block;width: 650px">
                	<s:label value="%{detalleEmail.cuerpo}"/>
                </strong>
            </p>
        </div>        
    </div>
       <div class="editContainer">
        <div style="width: 135px" class="nameDescription">
            <label>Destinatarios</label>
        </div>
         
    <div style="width: 770px"  class="editContent">
     <s:set var="total">${resultCount}</s:set>
    <s:if test="#total > 20">
          <h4 class="titular">Encontradas ${resultCount} entradas. Por rendimiento se muestran 20.</h4>
    </s:if>
     <s:if test="#total < 20">
        <h4 class="titular">Encontradas ${resultCount} entradas</h4>
    </s:if>
  
	    <display:table 
					id="tableMensajesId"
					summary="Tabla de resultados de búsqueda de logs"
					name="listaGestionEnviosDestinatariosMensaje" 
					pagesize="${pageSize}"
					requestURI="viewHistorico.action" 
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
					
					<display:column property="fechaFormateada" titleKey="plataforma.gestionViewLotes.fecha" 
						headerClass="TH100 separator center"  class="TH100 separator center" />
					
					<display:column property="estado" titleKey="plataforma.gestionViewLotes.estado" 
						headerClass="TH100 separator center" class="TH100 separator center" />
					
					<display:column property="destinatario" titleKey="plataforma.gestionViewLotes.destinatario" 
					headerClass="TH100 separator center" class="TH100 separator center" />
					
					<%-- acciones --%>
					<display:column property="historicoDestinatarioAction" titleKey="plataforma.gestionViewLotes.blanco"
						headerClass="TH20 separator " class="TH20 separator center" media="html" />	
									
			</display:table>
		</div>
	</div>
   
</div>
</sj:dialog>