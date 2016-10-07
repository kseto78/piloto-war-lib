<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">        
		<s:form id="frmNuevoServidorPush" method="POST" action="saveServidorPush" theme="simple" cssClass="">	
        	<h3 class="pageNameButtons">
    			<span class="floatRight">
    				<s:submit  theme="simple" value="%{getText('buttons.text.save')}" cssClass="button"/>
                     <input type="button" onclick="javascript:location.href='${volver}'" class="button" value="Volver">
                </span>
	            <label>CREACIÓN SERVIDOR PUSH</label>
            </h3> 
        <%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
 		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
            <div class="editContainer">
            	<div class="nameDescription">
                	<label>Datos Generales</label>
                </div>
            	<div class="editContent">
                	<p class="criteria">
                        <label style="width: 120px;" class="fieldText">Nombre (*):</label> 
                        <s:textfield
						name="servidorPush.nombre" size="85" value="%{servidorPush.nombre}" id="servidorPush.nombre"
						theme="simple" 
						 labelposition="left"
						 maxlength="255"
						cssClass="input_tablas_registro"/>
					</p>
					<p class="criteria">
						 <label style="width: 120px;" class="fieldText">URL Destino (*):</label>
                        <s:textfield
						name="servidorPush.urlDestino" size="85" value="%{servidorPush.urlDestino}" id="servidorPush.urlDestino"
						theme="simple" 
						 labelposition="left"
						 maxlength="255"
						cssClass="input_tablas_registro"/>						
                    </p>
                    <p class="criteria">
						<label id="nombreUrlFeedbackLabel" style="width: 120px;visibility:hidden" class="fieldText">URL Feedback (*):</label>
                        <s:textfield
						name="servidorPush.urlFeedback" size="85" value="%{servidorPush.urlFeedback}" id="servidorPush.urlFeedback"
						theme="simple" cssStyle="visibility:hidden"
						labelposition="left"
						maxlength="255"
						cssClass="input_tablas_registro"/>						
                    </p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Descripción (*):</label>
                        <s:textarea theme="simple" rows="6" cols="82" name="servidorPush.descripcion"
						labelposition="left" id="servidorPush.descripcion" value="%{servidorPush.descripcion}"/> 
                    </p>
                    <p class="criteria">
                   	 	<label style="width: 120px;" class="fieldText">Plataforma (*):</label>
	                        <s:select onchange="checkUrlPlataforma(this)"
							id="servidorPush.plataformaId" name="servidorPush.plataformaId" 
							emptyOption="false" theme="simple" 
							labelposition="left"
							list="comboPlataformas" listKey="codigo"
							listValue="descripcion" cssClass="" 
							value="%{servidorPush.plataformaId}"/>
                    </p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Por defecto:</label>
						<s:checkbox theme="simple" name="servidorPush.isDefecto" checked="checked" id="servidorPush.isDefecto" value="%{servidorPush.defecto}"></s:checkbox>
                    </p>                    
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Activo:</label>
						<s:checkbox theme="simple" name="servidorPush.isActivo" id="servidorPush.isActivo" value="%{servidorPush.activado}"></s:checkbox>
                    </p>
                    <p class="criteria">
                        <label style="width: 150px;" class="fieldText"><i>(*) Campos obligatorios</i></label>
                       
                    </p>                       
                </div>
            </div>
        </s:form>
				    <div class="editContainer">
            	<div class="nameDescription">
                	<label>Auditoría</label>
                </div>
            	<div class="editContent">
                	<p class="criteria">
                    	<span style="width: 340px;">
                        	<label style="width: 120px;" class="fieldText">Creador:</label>
                            <strong><s:label theme="simple" id="servidorPush.creadoPor" name="servidorPush.creadoPor" value="%{servidorPush.creadoPor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Creación:</label>
                            <strong><s:label theme="simple" id="servidorPush.fechaCreacion" name="servidorPush.fechaCreacion" value="%{servidorPush.fechaCreacion}"/></strong>
                        </span>
                    </p>
                    <p class="criteria">
                    	<span style="width: 340px;">
                        	<label style="width: 120px;" class="fieldText">Último Modificador:</label>
                            <strong><s:label theme="simple" id="servidorPush.modificadoPor" name="servidorPush.modificadoPor" value="%{servidorPush.modificadoPor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Última Modificación:</label>
                            <strong><s:label theme="simple" id="servidorPush.fechaModificacion" name="servidorPush.fechaModificacion" value="%{servidorPush.fechaModificacion}"/></strong>
                        </span>
                    </p>
                </div>
            </div>                                   
        </div>
        <script>
        	checkUrlPlataforma(document.getElementById("servidorPush.plataformaId"));
		</script>