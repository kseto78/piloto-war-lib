<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR,ROL_CAID">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
        <s:form id="frmEditPlanificacion" method="POST" action="saveUsuario" validate="false" cssClass="" theme="simple">
            <h3 class="pageNameButtons">
                <span class="floatRight">
                    
    				<s:submit  theme="simple" value="%{getText('buttons.text.save')}" cssClass="button"/>
                     <input type="button" onclick="javascript:location.href='${volver}'" class="button" value="Volver">
                </span>
                <label>CREACIÓN DE USUARIOS
                </label>
            </h3>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>

            <div class="editContainer">
                <div class="nameDescription">
                    <label>Datos Generales</label>
                </div>
                <div class="editContent">
                  <!--  <p class="criteria">
                         <label style="width: 120px;" class="fieldText">Aplicacion:</label>
                       <s:select
                        id="planificacion.aplicacionId" name="planificacion.aplicacionId" 
                        emptyOption="true" theme="simple" 
                        labelposition="left"
                        list="comboAplicaciones" listKey="codigo"
                        listValue="descripcion" cssClass="" 
                        value="%{planificacion.aplicacionId}" disabled="false" /> <input  type="button" value="Ir a Aplicación" class="button" onclick='javascript:location.href="editAplicacion.action?idAplicacion=${servicio.aplicacionId}"'/>
                    </p>-->
   
                   <p class="criteria">
                 		<label class="fieldText" style="width: 120px;">Usuario AutenticA (*):</label>
                       	<s:textfield
								name="usuario.login" value="%{usuario.login}" id="usuario.login"
								theme="simple" style="width:300px;"
								size="50"
								cssClass=""/>
                      
                    </p>   
                      <p class="criteria">
                 		<label class="fieldText" style="width: 120px;">Nombre (*):</label>
                 		<s:textfield
								name="usuario.nombre" value="%{usuario.nombre}" id="usuario.nombre"
								theme="simple" style="width:300px;"
								size="50"
								cssClass=""/>
                      
                    </p>    
                      <p class="criteria">
                 		<label class="fieldText" style="width: 120px;">Email:</label>
                 		<s:textfield
								name="usuario.email" value="%{usuario.email}" id="usuario.email"
								theme="simple" style="width:300px;"
								size="50"
								cssClass=""/>
                    </p>
                      <p class="criteria">
                 		<label class="fieldText" style="width: 120px;">Activo:</label>
						<s:checkbox theme="simple" name="newActivo"
						id="newActivo" value="%{newActivo}"></s:checkbox>                      
                    </p>      
                    <p class="criteria">
                        <label style="width: 150px;" class="fieldText"><i>(*) Campos obligatorios</i></label>
                       
                    </p>                                                                           
                </div>
            </div>
            <div class="editContainer">
                <div class="nameDescription">
                    <label>Rol </label>
                    <p>Rol del usuario
                    </p>
                </div>
                <div class="editContent">
					   <p class="criteria">
                         <label style="width: 70px;" class="fieldText">Rol:</label>
                       <s:select
                        id="ususario.rolId" name="usuario.rolId" 
                        emptyOption="false" theme="simple" 
                        labelposition="left"
                        list="comboRoles" listKey="codigo"
                        listValue="descripcion" cssClass="" 
                        value="%{usuario.rolId}" disabled="false" />
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
                            <strong><s:label theme="simple" id="usuario.creadoPor" name="usuario.creadoPor" value="%{usuario.creadoPor}"/></strong>
                        </span>
                        <span>
                            <label style="width: 150px;" class="fieldText">Fecha Creación:</label>
                            <strong><s:label theme="simple" id="usuario.fechaCreacion" name="v.fechaCreacion" value="%{usuario.fechaCreacion}"/></strong>
                        </span>
                    </p>
              <!--  <p class="criteria">
                        <span style="width: 340px;">
                            <label style="width: 120px;" class="fieldText">Último Modificador:</label>
                            <strong><s:label theme="simple" id="usuario.modificadoPor" name="usuario.modificadoPor" value="%{usuario.modificadoPor}"/></strong>
                        </span>
                        <span>
                            <label style="width: 150px;" class="fieldText">Fecha Última Modificación:</label>
                            <strong><s:label theme="simple" id="usuario.fechaModificacion" name="usuario.fechaModificacion" value="%{usuario.fechaModificacion}"/></strong>
                        </span>
                    </p>
             -->
                </div>
            </div>            
</s:form>
        </div>
        <s:form id="loadUsuarioFromLDAP" name="loadUsuarioFromLDAP" action="loadUserByName" theme="simple">
        		<input type="hidden" name="userNameToLoad" id="userNameToLoad"/>
        </s:form>