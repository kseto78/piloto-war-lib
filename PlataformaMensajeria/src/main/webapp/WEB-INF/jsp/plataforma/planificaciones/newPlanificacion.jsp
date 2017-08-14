<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">            
		<% String nAction = (String) request.getAttribute("nAction");
			if(nAction==null||(nAction!=null&&nAction.equals(""))){
				nAction="savePlanificacion";
				//request.setAttribute("nAction",nAction);
			}
		%>
        <s:form id="frmEditPlanificacion" method="POST" action="%{nAction}" theme="simple" onsubmit="return loadValueServidorId();" validate="false" cssClass="">
            <h3 class="pageNameButtons">
                <span class="floatRight">
                    
                    <s:submit  theme="simple" value="%{getText('buttons.text.save')}" onclick="return validaPlanJS('frmEditPlanificacion','planificacion')" cssClass="button"/>
                    <input type="button" onclick="javascript:location.href='${volver}'" class="button" value="Volver">
                </span>
                <label>CREACIÓN DE PLANIFICACIONES
                </label>
            </h3>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>

            <div class="editContainer">
                <div class="nameDescription">
                    <label>Datos Generales</label>
                </div>
                <div class="editContent">
                    <p class="criteria">
                         <label style="width: 120px;" class="fieldText">Aplicacion:</label>
                       <s:select cssStyle="width:200px;"
                        id="planificacion.aplicacionId" name="planificacion.aplicacionId" 
                        emptyOption="true" theme="simple" 
                        labelposition="left"
                        list="comboAplicaciones" listKey="codigo"
                        listValue="descripcion" cssClass="" 
                        value="%{planificacion.aplicacionId}" disabled="false" /> 
                    </p>
                    <p class="criteria">
                       <label style="width: 120px;" class="fieldText">Servicios:</label>
                       <s:select cssStyle="width:200px;"
						id="planificacion.servicioId" name="planificacion.servicioId" 
						emptyOption="true" theme="simple" onchange="cargaContenido(this.id)"
						labelposition="left"
						list="comboServicios" listKey="codigo"
						listValue="descripcion" cssClass="" 
						value="%{planificacion.servicioId}" />  
						<!--<input  type="button" value="Ir a Servicio" class="button" onclick='javascript:location.href="editServicio.action?idServicio=${planificacion.servicioId}"'/>-->
                    </p>
                    <p class="criteria">
                       <label style="width: 120px;" class="fieldText">Tipo:</label>
                       <s:select cssStyle="width:200px;"
						id="planificacion.tipoPlanificacionIdSelect" name="planificacion.tipoPlanificacionIdSelect" 
						emptyOption="true" theme="simple" onchange="cargaContenido(this.id)"
						labelposition="left"
						list="comboTipoPlanificaciones" listKey="codigo"
						listValue="descripcion" cssClass="" 
						value="%{planificacion.tipoPlanificacionId}" disabled="false" />  
                    </p>
                    <p class="criteria">
                       <label style="width: 120px;" class="fieldText">Servidor:</label>
                       <s:select cssStyle="width:200px;"
						id="planificacion.servidorIdSelect" name="planificacion.servidorIdSelect" 
						emptyOption="false" theme="simple" 
						labelposition="left" 
						list="comboServidores" listKey="codigo"
						listValue="descripcion" cssClass="" 
						value="%{planificacion.servidorId}" disabled="false" />
                    </p>    
                    <p class="criteria">
                    <s:hidden theme="simple" id="planificacion.tipoPlanificacionId" name="planificacion.tipoPlanificacionId" value="%{planificacion.tipoPlanificacionId}"/>
                    <s:hidden theme="simple" id="planificacion.servidorId" name="planificacion.servidorId" value="%{planificacion.servidorId}"/>
                    <s:hidden theme="simple" id="planificacion.planificacionId" name="planificacion.planificacionId" value="%{planificacion.planificacionId}"/>
                    <s:hidden theme="simple" id="idPlanificacion" name="idPlanificacion" value="%{planificacion.planificacionId}" />
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
<!--                     <p>                       
                        <label  style="width: 120px;" class="fieldText">Nombre:</label>
                        <s:textfield
                        name="planificacion.nombre" value="%{planificacion.nombre}" id="servicio.nombre"
                        theme="simple" 
                        labelposition="left"
                         size="85" maxlength="255"
                        cssClass="input_tablas_registro"/>
                    </p>
                    <p class="criteria">
                        <label style="width: 120px;" class="fieldText">Descripción:</label>
                        <s:textarea  name="planificacion.descripcion" id="planificacion.descripcion" theme="simple" rows="6" cols="85" value="%{planificacion.descripcion}"> </s:textarea>
                    </p>-->
                    <p class="criteria">
                        <label style="width: 120px;" class="fieldText">Activo:</label>
                        <s:checkbox theme="simple" id="planificacion.isActivo" name="planificacion.isActivo" value="%{planificacion.activado}" />
                    </p>
                    
                </div>
            </div>
           <div class="editContainer">
                <div class="nameDescription">
                    <label>Auditoría</label>
                </div>
                <div class="editContent">
                    <p class="criteria">
                        <span style="width: 340px;">
                            <label style="width: 120px;" class="fieldText">Creador:</label>
                            <strong><s:label theme="simple" id="planificacion.creadoPor" name="planificacion.creadoPor" value="%{planificacion.creadoPor}"/></strong>
                        </span>
                        <span>
                            <label style="width: 150px;" class="fieldText">Fecha Creación:</label>
                            <strong><s:label theme="simple" id="planificacion.fechaCreacion" name="planificacion.fechaCreacion" value="%{planificacion.fechaCreacion}"/></strong>
                        </span>
                    </p>
                    <p class="criteria">
                        <span style="width: 340px;">
                            <label style="width: 120px;" class="fieldText">Último Modificador:</label>
                            <strong><s:label theme="simple" id="planificacion.modificadoPor" name="planificacion.modificadoPor" value="%{planificacion.modificadoPor}"/></strong>
                        </span>
                        <span>
                            <label style="width: 150px;" class="fieldText">Fecha Última Modificación:</label>
                            <strong><s:label theme="simple" id="planificacion.fechaModificacion" name="planificacion.fechaModificacion" value="%{planificacion.fechaModificacion}"/></strong>
                        </span>
                    </p>
                </div>
            </div>            
            <input type="hidden" name="idAplicacion" id="idAplicacion" value="${idAplicacion}"/>
            <input type="hidden" name="var" id="var" value="${var}"/>
            <input type="hidden" name="idFrom" id="idFrom" value="${idFrom}"/>
            <input type="hidden" name="from" id="from" value="${from}"/>
            <input type="hidden" name="nAction" id="nAction" value="${nAction}"/>
</s:form>
        </div>