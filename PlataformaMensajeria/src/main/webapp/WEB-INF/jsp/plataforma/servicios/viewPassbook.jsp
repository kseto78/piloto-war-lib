<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<s:url id="url" action="updateViewPassbook"/>

<s:form id="formSaveViewPassbook" method="POST" action="saveViewPassbook" validate="false" theme="simple" cssClass="" enctype="multipart/form-data">
	<sj:dialog  id="dialogPassbook" title="Detalle Passbook" autoOpen="false">
				<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
				<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
				<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
			<div class="editContainer">
           		 <%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
				<div class="nameDescription">
					<label>Imágenes Passbook</label>
				</div>
				<div class="editContent">
					<p class="criteria">
						<label style="width: 120px;" class="fieldText">Logo:</label>
					<s:if test="%{servicioOrganismos.logo != null}">
						<img style="width:100px; height:100px; border: 1px solid #999999;" id="servicioOrganismos.imagenLogo" src="${logo64}"/>				
						<span id="ajax_logo"
						name="ajax" title="Eliminar Logo"
						onclick="return confirmDeleteImagenPassbook('imagenLogo',this,${servicioOrganismos.servicioOrganismoId},${servicioOrganismos.servicioId},${servicioOrganismos.organismoId});"
						class="btnDelete"></span>
					</s:if>
					<s:else>
						<input type="file" name="logo" id="logo" value="%{servicioOrganismos.logo}" onchange="subidaFicheroLogo()"/>
					</s:else>
					</p>
					<p class="criteria">
						<label style="width: 120px;" class="fieldText">Background:</label>
					<s:if test="%{servicioOrganismos.background != null}">
						<img style="width:100px; height:100px; border: 1px solid #999999;" id="servicioOrganismos.imagenBackground" src="${background64}"/>				
						<span id="ajax_background"
						name="ajax" title="Eliminar Background"
						onclick="return confirmDeleteImagenPassbook('imagenBackground',this,${servicioOrganismos.servicioOrganismoId},${servicioOrganismos.servicioId},${servicioOrganismos.organismoId})"
						class="btnDelete"></span>
					</s:if>
					<s:else>
						<input type="file" name="background" id="background" value="%{servicioOrganismos.background}" onchange="subidaFicheroBackground()"/>
					</s:else>
					</p>
					<p class="criteria">
						<label style="width: 120px;" class="fieldText">Icon:</label>
					<s:if test="%{servicioOrganismos.icon != null}">
						<img style="width:100px; height:100px; border: 1px solid #999999;" id="servicioOrganismos.imagenIcon" src="${icon64}"/>				
						<span id="ajax_icon"
						name="ajax" title="Eliminar Icon"
						onclick="return confirmDeleteImagenPassbook('imagenIcon',this,${servicioOrganismos.servicioOrganismoId},${servicioOrganismos.servicioId},${servicioOrganismos.organismoId})"
						class="btnDelete"></span>
					</s:if>
					<s:else>
						<input type="file" name="icon" id="icon" value="%{servicioOrganismos.icon}" onchange="subidaFicheroIcon()"/>
					</s:else>
					</p>
					
					<p class="criteria">
					<span class="leftSide">
<%-- 						<span id="ajax_${servicioOrganismos.servicioOrganismoId}_${servicioOrganismos.organismoId}" --%>
<!-- 						name="ajax" title="Previsualizar" -->
<%-- 						onclick="return previsualizar(this,${servicioOrganismos.servicioOrganismoId},${servicioOrganismos.servicioId},${servicioOrganismos.organismoId})" --%>
<!-- 						class="button">Previsualizar</span> -->
						<a class="button" id="Previsualizar" name="Previsualizar" onclick=""
						href="previsualizar.action?idServicioOrganismo=${servicioOrganismos.servicioOrganismoId}&idServicio=${servicioOrganismos.servicioId}&idOrganismo=${servicioOrganismos.organismoId}">Previsualizar</a>
						<input type="button" value="Previsualizar" class="button" style="visibility: hidden;"
						onclick='javascript:location.href="previsualizar.action?idServicioOrganismo=${servicioOrganismos.servicioOrganismoId}&idServicio=${servicioOrganismos.servicioId}&idOrganismo=${servicioOrganismos.organismoId}"' />
					</span>
					</p>
					
					<s:hidden name="idServicio" id="idServicio" value="%{servicioOrganismos.servicioId}" />
					<s:hidden name="idOrganismo" id="idOrganismo" value="%{servicioOrganismos.organismoId}" />
					<s:hidden name="idServicioOrganismo" id="idServicioOrganismo" value="%{servicioOrganismos.servicioOrganismoId}" />
				</div>
			</div>
			<div class="footerPopup">
				<span class="leftSide"></span> <span class="rightSide"> 
					<s:submit id="Guardar" name="Guardar" value="Guardar" cssClass="button"/>
<%-- 					<span id="ajax_${servicioOrganismos.servicioOrganismoId}" --%>
<!-- 					name="ajax" title="Guardar" -->
<%-- 					onclick="return guardar(this,${servicioOrganismos.servicioOrganismoId},${servicioOrganismos.servicioId},${servicioOrganismos.organismoId});" --%>
<!-- 					class="button">Guardar</span> -->
				</span>
			</div>
		</sj:dialog>
	</s:form>
	<script>
function subidaFicheroLogo(){
	   if(document.getElementById('logo').value.split('.').pop() == "exe"){
		  alert("El tipo de fichero no puede ser de tipo exe.");
		  document.getElementById('logo').value = null; 
 	}        	   
}
function subidaFicheroBackground(){
	   if(document.getElementById('background').value.split('.').pop() == "exe"){
		  alert("El tipo de fichero no puede ser de tipo exe.");
		  document.getElementById('background').value = null; 
	}        	   
}
function subidaFicheroIcon(){
	   if(document.getElementById('icon').value.split('.').pop() == "exe"){
		  alert("El tipo de fichero no puede ser de tipo exe.");
		  document.getElementById('icon').value = null; 
	}        	   
}
</script>