<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<%@page import="es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties" %>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR,ROL_CAID">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>

<div class="mainContent">  
        <s:form id="frmEditPlanificacion" method="POST" action="updateUsuario" validate="false" theme="simple" cssClass="">
            <h3 class="pageNameButtons">
                <span class="floatRight">
                    
                    <s:submit  theme="simple" value="%{getText('buttons.text.save')}" cssClass="button"/>
                     <input type="button" onclick="javascript:location.href='${volver}'" class="button" value="Volver">
                </span>
                <label>EDICIÓN DE USUARIOS
                </label>
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
                 		<label class="fieldText" style="width: 120px;">Telefono:</label>
                 		<s:textfield
								name="usuario.telefono" value="%{usuario.telefono}" id="usuario.telefono"
								theme="simple" style="width:300px;"
								size="50"
								cssClass=""/>
                    </p>
                      <p class="criteria">
                 		<label class="fieldText" style="width: 120px;">Organismo:</label>
                 		<s:textfield
								name="usuario.organismo" value="%{usuario.organismo}" id="usuario.organismo"
								theme="simple" style="width:300px;"
								size="50"
								cssClass=""/>
						<label class="tiptext"><img src="./img/icoHelp.png" height="10" width="10" "><label class="description">Si desea que el usuario pueda acceder a todos los envios de AEAT
						 y GISS, debe de introducir los siguientes organismos: <br> AEAT - <%=PlataformaMensajeriaProperties.getInstance().getProperty("usuario.ayuda.organismo.aeat", "")%> 
						 <br>GISS - <%=PlataformaMensajeriaProperties.getInstance().getProperty("usuario.ayuda.organismo.giss", "")%></label></label>
                    </p>
                      <p class="criteria">
                 		<label class="fieldText" style="width: 120px;">Activo:</label>
					<s:checkbox theme="simple" id="usuario.isActivo" name="usuario.isActivo" value="%{usuario.activado}" />
                      
                    </p>
                    <p class="criteria">
                        <label style="width: 150px;" class="fieldText"><i>(*) Campos obligatorios</i></label>
                       
                    </p>                                                                                 
                </div>
            </div>
            <div class="editContainer">
                <div class="nameDescription">
                    <label>Roles</label>
                </div>
                <div class="editContent">
					   <p class="criteria">
                         <label style="width: 70px;" class="fieldText">Rol:</label>
                       <s:select
                        id="ususario.rolId" name="usuario.rolId" 
                        emptyOption="false" theme="simple" 
                        labelposition="left"
                        list="comboRoles" listKey="codigo"
                        listValue="descripcion" cssClass="" onChange="showApplicationsDiv(this)"
                        value="%{usuario.rolId}" disabled="false" />
                    </p>            
                       <s:hidden id="idUsuario" name="idUsuario" value="%{usuario.usuarioId}"/>
				 
			   </div></div>
			</s:form>
		<s:if test="%{usuario.rolId!=1}">
           <div id="applicationsDiv" class="editContainer">
                <div class="nameDescription">
                    <label>Aplicaciones</label>
                    <p>Aplicaciones a las que accede el usuario</p>
                </div>			
           <div class="editContent">
			       	  <p class="criteria">
			       	    <s:form id="frmAddUsuarioAplicacion" method="POST" action="addUsuarioAplicacion" validate="false" cssClass="">
		                      <s:hidden id="usuario.organismo" name="usuario.organismo" value="%{usuario.organismo}"/>
		                          <span><label class="fieldText"> Aplicación:
		                          <s:select
											id="usuarioAplicacion.aplicacionId" name="usuarioAplicacion.aplicacionId" 
											emptyOption="true" theme="simple" 
											labelposition="left"
											list="comboAplicacionesNoAsignadas" listKey="codigo"
											listValue="descripcion" cssClass="" 
											disabled="false" />
											<s:hidden name="usuarioAplicacion.usuarioId" id="usuarioAplicacion.usuarioId"
											value="%{idUsuario}" />
			            	     </label>
		            	     
		            	         <label class="fieldTextNoIco"> Modo:
		                          <s:select
											id="usuarioAplicacion.modo" name="usuarioAplicacion.modo" 
											emptyOption="true" theme="simple" 
											labelposition="left"
											list="comboModos" listKey="codigo"
											listValue="descripcion" cssClass="" 
											value="%{usarioAplicacion.modo}" disabled="false" />
								</label>
								</span>
			          	      
		                      <a class="addLink" id="addItem" onclick="submitAddAplicacion()" name="addItem">Añadir Item</a>
		                      <s:hidden id="idUsuario" name="idUsuario" value="%{usuario.usuarioId}"/>
		                </s:form>
		                  </p>
                        <table cellspacing="0" cellpadding="0" border="0">
                        <thead>
                            <tr>                                
                                <th class="TH200">Nombre Aplicacion</th>
                                <th class="separator">Modo</th>
                                <th class="TH20 separator"></th>
                            </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="%{listaUsuarioAplicaciones}" var="usuarioAplicacion" status="usuarioAplicacionStatus">
                             <tr class="<s:if test='#usuarioAplicacionStatus.odd == true '>odd</s:if>">
                               <!-- <td>L, M, J</td> -->
                               <td><s:label value="%{nombreAplicacion}" theme="simple"/></td>
                                <td><s:label value="%{modoLectura}" theme="simple"/></td>
                                <td class="buttons">
                                    <span class="delete">
                                  <a class="btnDelete" title="Eliminar" onclick="return confirmDelete();" href="deleteUsuarioAplicacion.action?usuarioAplicacionId=${usuarioAplicacionId}&idUsuario=${usuario.usuarioId}"></a>
                                 </span></td>
                           </tr>
                        </s:iterator>
                        <s:if test="%{listaUsuarioAplicaciones == null}">
		          	      	<tr><td colspan="3">No se ha asociado ninguna aplicación al usuario</td></tr>
		          	      </s:if>
                        </tbody>
            		</table>        
 
                </div> 
           </div>   
          </s:if>
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
               
              	 <p class="criteria">
                        <span style="width: 340px;">
                            <label style="width: 120px;" class="fieldText">Último Modificador:</label>
                            <strong><s:label theme="simple" id="usuario.modificadoPor" name="usuario.modificadoPor" value="%{usuario.modificadoPor}"/></strong>
                        </span>
                        <span>
                            <label style="width: 150px;" class="fieldText">Fecha Última Modificación:</label>
                            <strong><s:label theme="simple" id="usuario.fechaModificacion" name="usuario.fechaModificacion" value="%{usuario.fechaModificacion}"/></strong>
                        </span>
                    </p>
             
                </div>
            </div>          
           <s:form id="loadUsuarioFromLDAP" name="loadUsuarioFromLDAP" action="loadUserByNameEdit" theme="simple">
        		<input type="hidden" name="userNameToLoad" id="userNameToLoad"/>
        		<input type="hidden" name="idUsuario" id="idUsuario" value="${idUsuario}"/>
        </s:form> 
        </div>
        <script>
			function submitAddAplicacion(){
				document.forms['frmAddUsuarioAplicacion'].submit();
			}
			$(".tiptext").mouseover(function() {
       		    $(this).children(".description").show();
       		}).mouseout(function() {
       		    $(this).children(".description").hide();
       		});
        </script>