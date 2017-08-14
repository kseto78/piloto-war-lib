<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">        
		<s:form id="frmNuevoReceptorSMS" method="POST" action="saveReceptorSMS" theme="simple" cssClass="">	
        	<h3 class="pageNameButtons">
    			<span class="floatRight">
    				<s:submit  theme="simple" value="%{getText('buttons.text.save')}" cssClass="button"/>
                     <input type="button" onclick="javascript:location.href='${volver}'" class="button" value="Volver">
                </span>
	            <label>CREACIÓN RECEPTOR SMS</label>
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
						name="receptorSMS.nombre" size="85" value="%{receptorSMS.nombre}" id="receptorSMS.nombre"
						theme="simple" 
						 labelposition="left"
						 maxlength="255"
						cssClass="input_tablas_registro"/>
					</p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Descripción (*):</label>
                        <s:textarea theme="simple" rows="6" cols="82" name="receptorSMS.descripcion"
						labelposition="left" id="receptorSMS.descripcion" value="%{receptorSMS.descripcion}"/> 
                    </p>
                    <p class="criteria">
						<label id="userSMSLabel" style="width: 180px;" class="fieldText">Usuario operadora (*):</label>
						<s:textfield
						name="receptorSMS.usuario" value="%{receptorSMS.usuario}" id="receptorSMS.usuario"
						theme="simple"
						 labelposition="left"
						 size="30" maxlength="255"
						cssClass=""/>
					</p>
					<p class="criteria">
						<label id="passwordSMSLabel" style="width: 180px;" class="fieldText">Contraseña operadora (*):</label>
						<s:password
						name="receptorSMS.password" value="%{receptorSMS.password}" id="receptorSMS.password"
						theme="simple"
						 labelposition="left"
						 size="30" maxlength="255" showPassword="true"
						cssClass=""/>
					</p>
					<p class="criteria" id="repPassLabel">
						<label id="rePasswordSMSLabel" style="width: 180px" class="fieldText">Rep. Contraseña operadora (*):</label>
						<s:password
						name="receptorSMS.rePassword" value="" id="receptorSMS.rePassword"
						theme="simple"
						 labelposition="left"
						 showPassword="true"
						 size="30" maxlength="255"
						cssClass=""/>
						<s:hidden name="checkPassword" id="checkPassword" value="true"/>
					</p>
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Por defecto:</label>
						<s:checkbox theme="simple" name="receptorSMS.isDefecto" checked="checked" id="receptorSMS.isDefecto" value="%{receptorSMS.defecto}"></s:checkbox>
                    </p>                    
                    <p class="criteria">
                        <label theme="simple" style="width: 120px;" class="fieldText">Activo:</label>
						<s:checkbox theme="simple" name="receptorSMS.isActivo" id="receptorSMS.isActivo" value="%{receptorSMS.activado}"></s:checkbox>
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
                            <strong><s:label theme="simple" id="receptorSMS.creadopor" name="receptorSMS.creadopor" value="%{receptorSMS.creadopor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Creación:</label>
                            <strong><s:label theme="simple" id="receptorSMS.fechacreacion" name="receptorSMS.fechacreacion" value="%{receptorSMS.fechacreacion}"/></strong>
                        </span>
                    </p>
                    <p class="criteria">
                    	<span style="width: 340px;">
                        	<label style="width: 120px;" class="fieldText">Último Modificador:</label>
                            <strong><s:label theme="simple" id="receptorSMS.modificadopor" name="receptorSMS.modificadopor" value="%{receptorSMS.modificadopor}"/></strong>
                        </span>
                        <span>
                        	<label style="width: 150px;" class="fieldText">Fecha Última Modificación:</label>
                            <strong><s:label theme="simple" id="receptorSMS.fechamodificacion" name="receptorSMS.fechamodificacion" value="%{receptorSMS.fechamodificacion}"/></strong>
                        </span>
                    </p>
                </div>
            </div>                                   
        </div>
