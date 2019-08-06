<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR,ROL_CAID">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>

<!-- <div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>-->

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->
        <div class="mainContent">
         <h3 class="pageNameButtons">
    			<span class="floatRight"></span>
	            <label>Registro Web Push</label>
          	</h3>     
        

         <s:form id="insertarUsuarioWebPush" name="insertarUsuarioWebPush"
			action="insertarUsuarioWebPush" theme="simple">
			<div class="HomeContent">
				<div class="alerts">
					<p>
						<button disabled
							class="js-push-btn mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
							Enable Push Messaging</button>
					</p>
					<input id="subscription" value="" />
					<p class="criteria">
						<label id="endpointLabel"
							style="width: 120px; visibility: hidden; display: none;"
							class="fieldText">EndPoint:</label>
						<s:textfield name="endpoint" value="%{endpoint}"
							id="endpoint" theme="simple"
							cssStyle="visibility:hidden;display:none;" labelposition="left"
							size="70" maxlength="255" cssClass="" />
					</p>
					<p class="criteria">
						<label id="pdhLabel"
							style="width: 120px; visibility: hidden; display: none;"
							class="fieldText">pdh:</label>
						<s:textfield name="pdh" value="%{pdh}"
							id="pdh" theme="simple"
							cssStyle="visibility:hidden;display:none;" labelposition="left"
							size="70" maxlength="255" cssClass="" />
					</p>
					<p class="criteria">
						<label id="authLabel"
							style="width: 120px; visibility: hidden; display: none;"
							class="fieldText">auth:</label>
						<s:textfield name="auth" value="%{auth}"
							id="auth" theme="simple"
							cssStyle="visibility:hidden;display:none;" labelposition="left"
							size="70" maxlength="255" cssClass="" />
					</p>
					<script type="text/javascript" src="push.js"></script>

				</div>
			</div>
		</s:form>
