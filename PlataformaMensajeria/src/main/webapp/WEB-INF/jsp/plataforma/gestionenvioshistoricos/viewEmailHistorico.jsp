<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<sj:dialog id="dialogEnviosEmail" title="DETALLE EMAIL"
	cssStyle="height:auto;padding:0;" autoOpen="false">
	<div id="Email" class="ui-dialog-content ui-widget-content"
		style="display: block; min-height: 0px; height: auto;">
		<div class="editContainer">
			<div style="width: 135px" class="nameDescription">
				<label>Datos Generales</label>
			</div>
			<div style="width: 770px" class="editContent">
				<p class="criteria">
					<span style="width: 49%"> <label style="width: 90px;"
						class="fieldText">ID Email:</label> <strong><s:label
								value="%{detalleEmail.mensajeId}" /></strong>
					</span> <span style="width: 49%"> <label style="width: 90px;"
						class="fieldText">Lote Envío:</label> <strong><s:label
								value="%{detalleEmail.nombreLoteEnvio}" /></strong>
					</span>
				</p>
				<p class="criteria">
					<span style="width: 49%"> <label style="width: 90px;"
						class="fieldText">Aplicación:</label> <strong><s:label
								value="%{detalleEmail.nombreAplicacion}" /></strong>
					</span> <span style="width: 49%"> <label style="width: 90px;"
						class="fieldText">ID Lote Envío:</label> <strong><s:label
								value="%{detalleEmail.idLote}" /></strong>
					</span>
				</p>
				<p class="criteria">
					<span style="width: 49%"> <label style="width: 90px;"
						class="fieldText">Servicio:</label> <strong><s:label
								value="%{detalleEmail.nombreServicio}" /></strong>
					</span> <span style="width: 49%"> <label style="width: 90px;"
						class="fieldText">ID Externo:</label> <strong><s:label
								value="%{detalleEmail.idExterno}" /></strong>
					</span>
				</p>
				<p class="criteria">
					<span style="width: 49%"> <label style="width: 90px;"
						class="fieldText">Tipo contenido:</label> <strong><s:label
								value="%{detalleEmail.tipoContenido}" /></strong>
					</span> <span style="width: 49%"> <label style="width: 90px;"
						class="fieldText">Codificaci&oacute;n:</label> <strong><s:label
								value="%{detalleEmail.codificacion}" /></strong>
					</span>
				</p>
				<p class="criteria">
					<span style="width: 49%"> <label style="width: 90px;"
						class="fieldText">Prioridad:</label> <strong><s:label
								value="%{detalleEmail.prioridadHTML}" /></strong>
					</span>
				</p>
				<p class="criteria">
					<span style="width: 49%"> <label style="width: 90px;"
						class="fieldText">Usuario:</label> <strong><s:label
								value="%{detalleEmail.docUsuario}" /></strong>
					</span> <span> <label style="width: 140px;" class="fieldText">Procedimiento/Servicio:</label>
						<strong><s:label value="%{detalleEmail.codSIA}" /></strong>
					</span>
				</p>
				<p class="criteria">
					<span style="width: 49%"> <label style="width: 90px;"
						class="fieldText">Organismo:</label> <strong><s:label
								value="%{detalleEmail.codOrganismo}" /></strong>
					</span>
				</p>
			</div>
		</div>

		<div class="editContainer">
			<div style="width: 135px" class="nameDescription">
				<label>Asunto</label>
			</div>
			<div style="width: 770px" class="editContent">
				<div class="headEmail" style="padding-left: 15px;">
					<s:label value="%{detalleEmail.asunto}" />
				</div>
			</div>
		</div>
		<div class="editContainer">
			<div style="width: 135px" class="nameDescription">
				<label>Cuerpo</label>
			</div>
			<div style="width: 770px" class="editContent">
				<div class="bodyEmail" style="padding-left: 15px; min-height: auto">
					<script> function viewMail(id){
        		window.open('viewContentHistorico.action?idEnvio=1_'+id,'winname'+id,'directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,width=800,height=600');
        		
        	}</script>
					<s:label>
						<a onclick="viewMail(${detalleEmail.mensajeId})" href="#" class="sortable"><b>Abrir
								en nueva ventana</b></a>
					</s:label>
				</div>
			</div>
		</div>
		<div class="editContainer">
			<div style="width: 135px" class="nameDescription">
				<label>Adjuntos</label>
			</div>
			<div style="width: 770px" class="editContent">
				<strong> <s:iterator
						value="%{detalleEmail.listadoAdjuntos}" var="adjunto"
						status="adjuntoEmailStatus">
						<span class="attached"><a
							href="downloadAdjuntoHistorico.action?idAdjunto=${adjunto.adjuntoId}&idEmail=${detalleEmail.mensajeId}">${adjunto.nombre}</a></span>
					</s:iterator>
				</strong>
			</div>
		</div>
	</div>
	<div class="editContainer">
		<div style="width: 135px" class="nameDescription">
			<label>Destinatarios</label>
		</div>

		<div style="width: 770px" class="editContent">
		 <s:set var="total">${resultCount}</s:set>
    <s:if test="#total > 20">
          <h4 class="titular">Encontradas ${resultCount} entradas. Por rendimiento se muestran 20.</h4>
    </s:if>
     <s:if test="#total < 20">
        <h4 class="titular">Encontradas ${resultCount} entradas</h4>
    </s:if>
			
			<display:table id="tableMensajesId"
				summary="Tabla de resultados de búsqueda de logs"
				name="listaGestionEnviosDestinatariosMensajeHistoricos" pagesize="${pageSize}"
				requestURI="viewHistoricoHist.action" defaultsort="1"
				defaultorder="ascending" sort="external" export="true"
				cellpadding="0" cellspacing="0" partialList="true"
				requestURIcontext="false"
				size="20"
				decorator="es.mpr.template.web.decorators.TableWrapper">
				<display:setProperty name="css.tr.even" value="null" />
				<display:setProperty name="css.tr.odd" value="odd" />

				<display:column property="fechaFormateada"
					titleKey="plataforma.gestionViewLotes.fecha"
					headerClass="TH110 separator center" class="TH110 separator center" />

				<display:column property="estado"
					titleKey="plataforma.gestionViewLotes.estado"
					headerClass="TH110 separator center" class="TH110 separator center" />

				<display:column property="destinatario"
					titleKey="plataforma.gestionViewLotes.destinatario"
					headerClass="TH110 separator center" class="TH110 separator center" />


				<%-- acciones --%>
				<display:column property="historicoHistDestinatarioAction" titleKey="plataforma.gestionViewLotes.blanco"
					headerClass="TH20 separator" class="TH20 separator center" media="html" />
					
			</display:table>
		</div>
	</div>

	</div>
</sj:dialog>