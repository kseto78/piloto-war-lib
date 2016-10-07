<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<s:url id="url" action="updatePlanificacionApp"/>

		<s:form id="updatePlanificacion" name="updatePlanificacion" theme="simple" action="updatePlanificacionServidorPush">
                    <sj:dialog  id="dialogPlanifications" title="Planificación" cssStyle="min-height:90px" autoOpen="false">
					     	<div class="editContainer">
					<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
					<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
					<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>            
            		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
					        <div class="nameDescription">
					            <label>Editar Días y horas</label>
					        </div>
					        <div class="editContent">
					                	 <p class="criteria">
					                     	<span>
					                            <label class="fieldText" style="width: 120px;">Días de la semana:</label>
					                            L
					                            <s:checkbox id="planificacion.lunes" name="planificacion.lunes" value="%{planificacion.lunes}" theme="simple"/>
					                            M
					                            <s:checkbox id="planificacion.martes" name="planificacion.martes" value="%{planificacion.martes}" theme="simple"/>                          
					                            X
					                            <s:checkbox id="planificacion.miercoles" name="planificacion.miercoles" value="%{planificacion.miercoles}" theme="simple"/>                         
					                            J
					                            <s:checkbox id="planificacion.jueves" name="planificacion.jueves" value="%{planificacion.jueves}" theme="simple"/>                        
					                            V
					                            <s:checkbox id="planificacion.viernes" name="planificacion.viernes" value="%{planificacion.viernes}" theme="simple"/>  
					                            S
					                            <s:checkbox id="planificacion.sabado" name="planificacion.sabado" value="%{planificacion.sabado}" theme="simple"/>   
					                            D
					                            <s:checkbox id="planificacion.domingo" name="planificacion.domingo" value="%{planificacion.domingo}" theme="simple"/>              
											</span>
					                   <p class="criteria">
					                 		<label class="fieldText" style="width: 120px;">Hora Inicio:</label>
					                       		 <s:select 
												id="planificacion.horaDesde" name="planificacion.horaDesde" 
												emptyOption="false" theme="simple" 
												labelposition="left"
												list="comboHorasInicio" listKey="codigo"
												listValue="descripcion" cssClass="W65" 
												value="%{planificacion.horaDesde}" disabled="false" />
					                        Hora Fin:  <s:select 
												id="planificacion.horaHasta" name="planificacion.horaHasta" 
												emptyOption="false" theme="simple" 
												labelposition="left"
												list="comboHorasFin" listKey="codigo"
												listValue="descripcion" cssClass="W65" 
												value="%{planificacion.horaHasta}" disabled="false" />
					                    </p>         
					                    <input type="hidden" name="planificacion.servicioId" id="planificacion.servicioId" value="${planificacion.servicioId}"/> 
					                    <input type="hidden" name="planificacion.tipoPlanificacionId" id="planificacion.tipoPlanificacionId" 
					                    		value="${planificacion.tipoPlanificacionId}"/>
					                    <input type="hidden" name="planificacion.servidorId" id="planificacion.servidorId" 
					                    		value="${planificacion.servidorId}"/>
					                    	
					                    <input type="hidden" name="idPlanificacion" id="idPlanificacion" value="${planificacion.planificacionId}"/>
					                    <input type="hidden" name="idServidorPush" id="idServidorPush" value="${idServidorPush}"/>
					                            
					        </div>
					    </div>
					    <div class="footerPopup">
					    	<span class="leftSide"></span>
					        <span class="rightSide">
					        <s:submit id="Guardar" name="Guardar" value="Guardar" onclick="return validaPlanJS('frmEditPlanificacion','planificacion')" cssClass="button"/>
					        
					        </span>  	
					    </div>
			   </sj:dialog>
</s:form>