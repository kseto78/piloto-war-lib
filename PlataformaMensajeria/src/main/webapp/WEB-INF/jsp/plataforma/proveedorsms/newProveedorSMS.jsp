<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>


<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR,ROL_CAID">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">        
		<s:form id="frmNuevoProveedorSMS" method="POST" action="saveProveedorSMS" theme="simple" cssClass="">	
        	<h3 class="pageNameButtons">
    			<span class="floatRight">
    				<s:submit  theme="simple" value="%{getText('buttons.text.save')}" cssClass="button"/>
                     <input type="button" onclick="javascript:location.href='${volver}'" class="button" value="Volver">
                </span>
	            <label>CREACIÓN PROVEEDOR SMS</label>
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
						name="proveedorSMS.nombre" size="85" value="%{proveedorSMS.nombre}" id="proveedorSMS.nombre"
						theme="simple" 
						 labelposition="left"
						 maxlength="255"
						cssClass="input_tablas_registro"/>
					</p>
					<p class="criteria">
						 <label style="width: 120px;" class="fieldText">URL Destino (*):</label>
                        <s:textfield
						name="proveedorSMS.urldestino" size="85" value="%{proveedorSMS.urldestino}" id="proveedorSMS.urldestino"
						theme="simple" 
						 labelposition="left"
						 maxlength="255"
						cssClass="input_tablas_registro"/>						
                    </p>
                    <p class="criteria">
	                    <label style="width: 130px;" class="fieldText">Método de Consulta (*):</label>
	                     <s:select headerKey="" headerValue=""	list="#{'false':'Recepcion de Estado',
	                    'true':'Consulta de Estado'}" name="proveedorSMS.metodoconsulta" id="proveedorSMS.metodoconsulta"
	                      value ="%{proveedorSMS.metodoconsulta}" />
                    </p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Descripción (*):</label>
                        <s:textarea theme="simple" rows="6" cols="82" name="proveedorSMS.descripcion"
						labelposition="left" id="proveedorSMS.descripcion" value="%{proveedorSMS.descripcion}"/> 
						
                    </p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Por defecto:</label>
						<s:checkbox theme="simple" name="proveedorSMS.isDefecto" checked="checked" id="proveedorSMS.isDefecto" value="%{proveedorSMS.defecto}"></s:checkbox>
                    </p>                    
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Activo:</label>
						<s:checkbox theme="simple" name="proveedorSMS.isActivo" id="proveedorSMS.isActivo" value="%{proveedorSMS.activado}"></s:checkbox>
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
                            <strong><s:label theme="simple" id="proveedorSMS.creadopor" name="proveedorSMS.creadopor" value="%{proveedorSMS.creadopor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Creación:</label>
                            <strong><s:label theme="simple" id="proveedorSMS.fechacreacion" name="proveedorSMS.fechacreacion" value="%{proveedorSMS.fechacreacion}"/></strong>
                        </span>
                    </p>
                    <p class="criteria">
                    	<span style="width: 340px;">
                        	<label style="width: 120px;" class="fieldText">Último Modificador:</label>
                            <strong><s:label theme="simple" id="proveedorSMS.modificadopor" name="proveedorSMS.modificadopor" value="%{proveedorSMS.modificadopor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Última Modificación:</label>
                            <strong><s:label theme="simple" id="proveedorSMS.fechamodificacion" name="proveedorSMS.fechamodificacion" value="%{proveedorSMS.fechamodificacion}"/></strong>
                        </span>
                    </p>
                </div>
            </div>                                   
        </div>
