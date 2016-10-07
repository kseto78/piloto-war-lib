<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<sj:dialog  id="dialogEnviosNotificacionPush" title="DETALLE NOTIFICACION PUSH" cssStyle="min-height:90px" autoOpen="false">
<div id="notificacion" class="ui-dialog-content ui-widget-content" display: block; min-height: 0px; height: auto;">
	<div class="editContainer">
        <div class="nameDescription" style="width: 135px">
            <label>Datos Generales</label>
        </div>
        <div class="editContent" style="width:770px;">
            <p class="criteria">
                <label style="width: 90px;" class="fieldText">ID Mensaje:</label>
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
            </p>
        </div>
    </div>
    <div class="editContainer">
        <div class="nameDescription" style="width: 135px">
            <label>Notificación Push</label>
        </div>
        <div class="editContent" style="width:770px;">
            <p class="criteria">
                <label style="width: 130px;" class="fieldText">Título:</label>
                <strong><s:label value="%{detalleEmail.asunto}"/></strong>
            </p>
            <p class="criteria">
                <label style="width: 130px;" class="fieldText">Mensaje:</label>
                <strong><s:label value="%{detalleEmail.cuerpo}"/></strong>
            </p>
            <p class="criteria">
                <label style="width: 130px;" class="fieldText">Identificador Usuario:</label>
                <strong><s:label value="%{detalleEmail.nombreUsuario}"/></strong>
            </p>
            <p class="criteria">
                <label style="width: 130px;" class="fieldText">Icono:</label>
                <strong><s:label value="%{detalleEmail.icono}"/></strong>
            </p>
            <p class="criteria">
                <label style="width: 130px;" class="fieldText">Sonido:</label>
                <strong><s:label value="%{detalleEmail.sonido}"/></strong>
            </p>
        </div>        
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
					name="listaGestionEnviosDestinatariosMensajeHistoricos" 
					pagesize="${pageSize}"
					requestURI="viewHistoricoHist.action" 
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
						headerClass="TH110 separator center"  class="TH110 separator center" />
					
					<display:column property="estado" titleKey="plataforma.gestionViewLotes.estado" 
						headerClass="TH110 separator center" class="TH110 separator center" />
					
					<display:column property="destinatario" titleKey="plataforma.gestionViewLotes.destinatario" 
					headerClass="TH110 separator center" class="TH110 separator center" />
					
					
					<%-- acciones --%>
				<display:column property="historicoHistDestinatarioAction" titleKey="plataforma.gestionViewLotes.blanco"
					headerClass="TH20 separator" class="TH20 separator center" media="html" />			
			</display:table>
		</div>
	</div>
    <!-- 
    <div class="editContainer">
        <div class="nameDescription" style="width: 135px">
            <label>Histórico</label>
        </div>
        <div class="editContent" style="width:770px;">
        	<table cellspacing="0" cellpadding="0" border="0">
            	<thead>
                	<tr>
                        <th class="TH130">Fecha</th>
                        <th class="TH150 separator">Estado</th>
                        <th class="separator">Servidor</th>
                        <th class="separator">Descripci&oacute;n</th>
                    </tr>
                </thead>
                <tbody>
                 <s:iterator value="%{detalleEmail.listadoHistorico}" var="historicoEmail" status="historicoEmailStatus">
                    <tr class="<s:if test='#historicoEmailStatus.odd == true '></s:if><s:else>odd</s:else>">
                    
                    	<td>${historicoEmail.fechaFormateada}</td>
						<td>${historicoEmail.estado}</td>
                        <td>${historicoEmail.nombreServidor}</td>
                        <td>${historicoEmail.descripcion}</td>
                    </tr>
                 </s:iterator>
                </tbody>
                
            </table>
        </div>
    </div> -->
</div>
</sj:dialog>