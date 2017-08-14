<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
                       <label style="width: 120px;" class="fieldText">Servidor:</label>
                       <s:select cssStyle="width:200px;"
						id="planificacion.servidorIdSelect" name="planificacion.servidorIdSelect" 
						theme="simple" 
						labelposition="left"
						list="comboServidores" listKey="codigo"
						listValue="descripcion" cssClass="" 
						value="%{planificacion.servidorId}" disabled="false" />  
