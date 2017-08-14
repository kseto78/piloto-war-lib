<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<div class="mainContent">
		
	<s:form id="formDecodificador" method="POST" action="decodificador"
		validate="false" theme="simple" cssClass="">
		<h3 class="pageNameButtons">
			<label>DECODIFICADOR</label>
		</h3>
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
			<div class="editContainer">
			<div class="nameDescription">
				<label>XML Cifrado</label>
			</div>
			<div class="editContent">
			
				<p class="criteria">
					<s:textarea name="decodeBean.xmlCifrado" id="decodeBean.xmlCifrado" style="margin: 0px; width: 550px; height: 350px;"
						theme="simple" rows="15" cols="70" value="%{decodeBean.xmlCifrado}">
					</s:textarea>
				</p>
				
				<p class="criteria">
					<span style="width: 500px; height: 30px; padding-top: 10px; padding-bottom: 10px;"> 
					
						<label style="width: 140px;" class="fieldText">Certificado para descifrar: </label> 
						
						<s:select name="decodeBean.certificado" id="decodeBean.certificado"
						emptyOption="false" theme="simple" labelposition="left"
						list="comboCertificados" listKey="codigo"
						listValue="descripcion" cssStyle="width:140px" cssClass=""
						value="%{decodeBean.certificado}" disabled="false" />
							               		
	               		<s:submit class="button" value="Decodificar" style="width: 85px;" />
	               		
						<s:submit class="button" value="Limpiar" action="limpiar" style="width: 85px;"/>
	               		
	               	</span>
				</div>
			</div>
	</s:form>

			<div class="editContainer">
			<div class="nameDescription">
				<label>XML Descifrado</label>
			</div>
			<div class="editContent">				
				<p class="criteria">
					<s:textarea name="decodeBean.xmlDescifrado" id="decodeBean.xmlDescifrado" style="margin: 0px; width: 550px; height: 350px;"
						theme="simple" rows="15" cols="70" value="%{decodeBean.xmlDescifrado}">
					</s:textarea>
				</p>
			</div>
			</div>

</div>