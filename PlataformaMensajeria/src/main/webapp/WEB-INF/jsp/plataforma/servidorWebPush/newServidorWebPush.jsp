<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR,ROL_CAID">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">        
		<s:form id="frmNuevoServidorWebPush" method="POST" action="saveServidorWebPush" theme="simple" cssClass="">	
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
						name="servidorWebPush.nombre" size="85" value="%{servidorWebPush.nombre}" id="servidorWebPush.nombre"
						theme="simple" 
						 labelposition="left"
						 maxlength="255"
						cssClass="input_tablas_registro"/>
					</p>
					<p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Descripción (*):</label>
                        <s:textarea theme="simple" rows="6" cols="82" name="servidorWebPush.descripcion"
						labelposition="left" id="servidorWebPush.descripcion" value="%{servidorWebPush.descripcion}"/> 
                    </p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Por defecto:</label>
						<s:checkbox theme="simple" name="servidorWebPush.isDefecto" checked="checked" id="servidorWebPush.isDefecto" value="%{servidorWebPush.defecto}"></s:checkbox>
                    </p>                    
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Activo:</label>
						<s:checkbox theme="simple" name="servidorWebPush.isActivo" id="servidorWebPush.isActivo" value="%{servidorWebPush.activado}"></s:checkbox>
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
                            <strong><s:label theme="simple" id="servidorWebPush.creadopor" name="servidorWebPush.creadopor" value="%{servidorWebPush.creadopor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Creación:</label>
                            <strong><s:label theme="simple" id="servidorWebPush.fechacreacion" name="servidorWebPush.fechacreacion" value="%{servidorWebPush.fechacreacion}"/></strong>
                        </span>
                    </p>
                    <p class="criteria">
                    	<span style="width: 340px;">
                        	<label style="width: 120px;" class="fieldText">Último Modificador:</label>
                            <strong><s:label theme="simple" id="servidorWebPush.modificadopor" name="servidorWebPush.modificadopor" value="%{servidorWebPush.modificadopor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Última Modificación:</label>
                            <strong><s:label theme="simple" id="servidorWebPush.fechamodificacion" name="servidorWebPush.fechamodificacion" value="%{servidorWebPush.fechamodificacion}"/></strong>
                        </span>
                    </p>
                </div>
            </div>                                   
        </div>
      