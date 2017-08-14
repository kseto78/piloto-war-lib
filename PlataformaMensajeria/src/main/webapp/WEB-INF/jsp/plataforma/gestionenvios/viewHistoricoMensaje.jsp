<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
    <sj:dialog  id="dialogHistoricoEnvio" title="DETALLE DESTINATARIO" cssStyle="height:auto;padding:0;" autoOpen="false">
<div id="Detalle" class="ui-dialog-content ui-widget-content" style="display: block; min-height: 0px; height: auto;">
	<div class="editContainer">
        <div style="width: 135px" class="nameDescription">
            <label>Datos Generales</label>
        </div>
        <div style="width: 770px" class="editContent">
 			<p class="criteria">
 				<span style="width: 49%">
	                <label style="width: 90px;" class="fieldText">ID Mensaje:</label>
 	                <strong><s:label value="%{detalleMensaje.mensajeId}"/></strong>
 				</span>
 				<span style="width: 49%">
	                <label style="width: 90px;" class="fieldText">Lote Envío:</label>
 	                <strong><s:label value="%{detalleMensaje.loteEnvio}"/></strong>
 				</span>
 			</p>
 			<p class="criteria">
 				<span style="width: 49%">
	                <label style="width: 90px;" class="fieldText">Aplicacion:</label>
 	                <strong><s:label value="%{detalleMensaje.aplicacion}"/></strong>
 				</span>
 				<span style="width: 49%">
	                <label style="width: 90px;" class="fieldText">Id Lote Envío:</label>
 	                <strong><s:label value="%{detalleMensaje.idLote}"/></strong>
 				</span>
 			</p>
 			<p class="criteria">
 				<span style="width: 49%">
	                <label style="width: 90px;" class="fieldText">Servicio:</label>
	                <strong><s:label value="%{detalleMensaje.servicio}"/></strong>
 				</span>
            </p>        
            <p class="criteria">
            	<span style="width: 49%">
	                <label style="width: 90px;" class="fieldText">Id Externo:</label>
	                <strong><s:label value="%{destinatariosMensajes.codigoExterno}"/></strong>
            	</span>
            </p>
           <p class="criteria">
            	<span style="width: 49%">
	                <label style="width: 90px;" class="fieldText">Usuario:</label>
	                <strong><s:label value="%{detalleMensaje.idLoteEnvio}"/></strong>
            	</span>
            	<span style="width: 49%">
	                <label style="width: 90px;" class="fieldText">Procedimiento/Servicio:</label>
	                <strong><s:label value=""/></strong>
            	</span>
            </p>
            <p class="criteria">
            	<span style="width: 49%">
	                <label style="width: 90px;" class="fieldText">Organismo:</label>
	                <strong><s:label value="%{detalleMensaje.codOrganismo}"/></strong>
            	</span>
            	<span style="width: 49%">
	                <label style="width: 90px;" class="fieldText">Org. Pagador:</label>
	                <strong><s:label value="%{detalleMensaje.codOrganismoPagador}"/></strong>
            	</span>
            </p>
            
        </div>
    </div>
   <div class="editContainer">
        <div style="width: 135px" class="nameDescription">
            <label>Histórico</label>
        </div>
        <div style="width: 770px"  class="editContent">
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
                 <s:iterator value="%{listaHistoricosMensaje}" var="historicoEmail" status="historicoEmailStatus">
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
    </div>

</div>
</sj:dialog>