<%@ taglib prefix="framework" uri="/framework"%>
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<div class="mainContent">	
<plataforma:securityadmin usuarioLogueado="true" showIfGranted="true">
<s:form id="estadoLotesForm" name="estadoLotesForm" action="infoHome" theme="simple">
<div class="HomeContent">
<div class="alerts">
<h2>Estado de los últimos lotes del mes actual</h2>
					<!-- 	<div class="search">

				
				<p class="criteria">
					<span class=" TH145"> <label class="fieldText TH30">Año</label>
					<s:select
                        id="filtroAnyoLotes" name="filtroAnyoLotes" 
                        emptyOption="false" theme="simple" 
                        labelposition="left"
                        list="comboAnyos" listKey="codigo"
                        listValue="descripcion" cssClass="TH60" 
                        value="%{filtroAnyoLotes}" />
					</span> <span class=" TH170"> <label class="fieldText TH40">Mes</label>
					<s:select
                        id="filtroMesLotes" name="filtroMesLotes" 
                        emptyOption="false" theme="simple" 
                        labelposition="left"
                        list="comboMeses" listKey="codigo"
                        listValue="descripcion" cssClass="TH100" 
                        value="%{filtroMesLotes}" />
					</span>
					<s:submit cssClass="button" value="Buscar" />
				</p>
			</div>	-->			
			<div>
			<table border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th class="TH50">ID Lote</th>
						<th class="TH220 separator center">Lote</th>
						<th class="TH65 separator center">Fecha</th>
						<th class="TH115 separator">Aplicaci&oacute;n</th>
						<th class="TH55 separator center">Enviados</th>
						<th class="TH60 separator center">Pendientes</th>
						<th class="TH60 separator center">Incidencias</th>
						<th class="TH60 separator center">Anulados</th>
						
						
					</tr>
				</thead>
				<tbody>
 					<s:iterator value="%{listadoEstadosLotesEnvios}" var="estadoLotesEnvios" status="estadoLotesEnviosStatus">
                              <tr class="<s:if test='#estadoLotesEnviosStatus.odd == true '></s:if><s:else>odd</s:else>">
                                <td class="TH50"><a href="<%=request.getContextPath()+"/listGestionEnvios.action?gestionEnvioBean.idLote="%>${loteEnvioId}">${loteEnvioId}</a></td>
									<td class="TH220  center">${nombreLote}</td>
									<td class="TH65  center">${fechaFormateada}</td>
									<td class="TH115 ">${nombreAplicacion}</td>
									<td class="TH55 center">${enviadosString}</td>
									<td class="TH60 center">${pendienteString}</td>
									<td class="TH60 center">${incidenciaString}</td>
									<td class="TH60 center">${anuladoString}</td>
                              </tr>
                     </s:iterator>				
				</tbody>
			</table>
		</div>							
	</div>
	<div class="emails">
		<h2>Mensajes Pendientes</h2>
		<div>
			<table border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th class="TH170"></th>
						<th class="center">Email</th>
						<th class="center separator">SMS</th>
 						<th class="center separator TH90">Recepcion SMS</th> 
 						<th class="center separator">Push</th> 
					</tr>
				</thead>
				<tbody>
 					<s:iterator value="%{listaEnviosPendientesCanal}" var="enviosPendientes" status="enviosPendientesStatus">
                              <tr class="<s:if test='#enviosPendientesStatus.odd == true '></s:if><s:else>odd</s:else>">
                                <td class="colorGray"><a href="<%=request.getContextPath()+"/listGestionEnvios.action?gestionEnvioBean.aplicacionId="%>${aplicacionId}&gestionEnvioBean.estadoId=5">${aplicacion}</a></td>
                                <td class="center">${emailString}</td>
                                <td class="center">${SMSString}</td>
                                <td class="center">${RecepcionSMSString}</td>
                                 <td class="center">${PushString}</td> 
                              </tr>
                     </s:iterator>				
				</tbody>
				<tfoot>
					<tr class="totalsHome">
						<td>Totales</td>
						<td class="center">${totalesEmail}</td>
						<td class="separator center">${totalesSMS}</td>
 						<td class="separator center">${totalesRecepcionSMS}</td> 
						<td class="separator center">${totalesPush}</td> 
					</tr>
				</tfoot>	
			</table>
		</div>
	</div>
	<div class="servers">
		<h2>Uso Servidores</h2>
		<div>
			<div class="search">
				<p class="criteria">
					<span class=" TH145"> <label class="fieldText TH30">Año</label>
					<s:select
                        id="filtroAnyo" name="filtroAnyo" 
                        emptyOption="false" theme="simple" 
                        labelposition="left"
                        list="comboAnyos" listKey="codigo"
                        listValue="descripcion" cssClass="TH60" 
                        value="%{filtroAnyo}" />
					</span> <span class=" TH170"> <label class="fieldText TH40">Mes</label>
					<s:select
                        id="filtroMes" name="filtroMes" 
                        emptyOption="false" theme="simple" 
                        labelposition="left"
                        list="comboMeses" listKey="codigo"
                        listValue="descripcion" cssClass="TH100" 
                        value="%{filtroMes}" />
					</span>
					<s:submit cssClass="button" value="Buscar" />
				</p>
				
			</div>
			<h4>SMTP</h4>
			<table border="0" cellpadding="0" cellspacing="0">
				<tbody>
				<s:iterator value="%{listaUsoServidores}" var="usoServidorBean" status="enviosPendientesStatus">
					 <tr class="<s:if test='#enviosPendientesStatus.odd == true '></s:if><s:else>odd</s:else>">
						<td class="colorGray TH130">${servidor}</td>
						<td>${nenvios} Envíos</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
			<h4>SMS</h4>
			<table border="0" cellpadding="0" cellspacing="0">
				<tbody>
				<s:iterator value="%{listaUsoProveedores}" var="usoServidorBean" status="enviosPendientesStatus">
					 <tr class="<s:if test='#enviosPendientesStatus.odd == true '></s:if><s:else>odd</s:else>">
						<td class="colorGray TH130">${servidor}</td>
						<td>${nenvios} Envíos</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
 			<h4>RECEPTORES SMS</h4> 
			<table border="0" cellpadding="0" cellspacing="0"> 
 				<tbody> 
 				<s:iterator value="%{listaUsoReceptores}" var="usoServidorBean" status="enviosPendientesStatus"> 
 					 <tr class="<s:if test='#enviosPendientesStatus.odd == true '></s:if><s:else>odd</s:else>"> 
 						<td class="colorGray TH130">${servidor}</td> 
 						<td>${nenvios} Envíos</td>
 					</tr> 
 				</s:iterator> 
				</tbody> 
 			</table> 
 			<h4>PUSH</h4> 
			<table border="0" cellpadding="0" cellspacing="0"> 
 				<tbody> 
 				<s:iterator value="%{listaUsoServidoresPush}" var="usoServidorBean" status="enviosPendientesStatus"> 
 					 <tr class="<s:if test='#enviosPendientesStatus.odd == true '></s:if><s:else>odd</s:else>"> 
 						<td class="colorGray TH130">${servidor}</td> 
 						<td>${nenvios} Envíos</td> 
 					</tr> 
 				</s:iterator> 
 				</tbody> 
 			</table> 
		</div>
	</div>
</div>
</s:form>
</plataforma:securityadmin>
</div>