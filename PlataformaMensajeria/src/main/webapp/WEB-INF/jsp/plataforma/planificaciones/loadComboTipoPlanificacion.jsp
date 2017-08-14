<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
                       <label style="width: 120px;" class="fieldText">Tipo:</label>
                       <s:select cssStyle="width:200px;"
						id="planificacion.tipoPlanificacionIdSelect" name="planificacion.tipoPlanificacionIdSelect" 
						emptyOption="true" theme="simple" onchange="cargaContenido(this.id)"
						labelposition="left"
						list="comboTipoPlanificaciones" listKey="codigo"
						listValue="descripcion" cssClass="" 
						value="%{planificacion.tipoPlanificacionId}" disabled="false" />  
