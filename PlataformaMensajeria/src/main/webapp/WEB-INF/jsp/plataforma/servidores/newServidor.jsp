<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR,ROL_CAID">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">        
		<s:form id="frmNuevoServidor" method="POST" action="saveServidor" theme="simple" cssClass="">	
        	<h3 class="pageNameButtons">
    			<span class="floatRight">
    				<s:submit  theme="simple" value="%{getText('buttons.text.save')}" cssClass="button"/>
                     <input type="button" onclick="javascript:location.href='${volver}'" class="button" value="Volver">
                </span>
	            <label>CREACIÓN SERVIDOR SMTP</label>
            </h3>
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
						name="servidor.nombre" value="%{servidor.nombre}" id="servidor.nombre"
						theme="simple" 
						 labelposition="left"
						 size="85" maxlength="255"
						cssClass="input_tablas_registro"/>
                    </p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Descripción (*):</label>
                        <s:textarea theme="simple" rows="6" cols="82" name="servidor.descripcion"
						labelposition="left" id="servidor.descripcion" value="%{servidor.descripcion}"/> 
						
                    </p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Por defecto:</label>
						<s:checkbox theme="simple" name="servidor.isDefecto" checked="checked" id="servidor.isDefecto" value="%{servidor.defecto}"></s:checkbox>
                    </p>                    
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Activo:</label>
						<s:checkbox theme="simple" name="servidor.isActivo" id="servidor.isActivo" value="%{servidor.activado}"></s:checkbox>
                    </p>
                    <p class="criteria">
                        <label style="width: 120px;" class="fieldText">Cuota Diaria:</label>
                        <s:textfield
							name="servidor.cuota" value="%{servidor.cuota}" id="servidor.cuota"
							theme="simple" 
							title="Cuota Diaria"
							labelposition="left"
							 size="5" maxlength="100"
							cssClass="" onkeypress="return numbersonly(this, event)"/>          
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
                            <strong><s:label theme="simple" id="servidor.creadoPor" name="servidor.creadoPor" value="%{servidor.creadoPor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Creación:</label>
                            <strong><s:label theme="simple" id="servidor.fechaCreacion" name="servidor.fechaCreacion" value="%{servidor.fechaCreacion}"/></strong>
                        </span>
                    </p>
                    <p class="criteria">
                    	<span style="width: 340px;">
                        	<label style="width: 120px;" class="fieldText">Último Modificador:</label>
                            <strong><s:label theme="simple" id="servidor.modificadoPor" name="servidor.modificadoPor" value="%{servidor.modificadoPor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Última Modificación:</label>
                            <strong><s:label theme="simple" id="servidor.fechaModificacion" name="servidor.fechaModificacion" value="%{servidor.fechaModificacion}"/></strong>
                        </span>
                    </p>
                </div>
            </div>                        
        </div>
