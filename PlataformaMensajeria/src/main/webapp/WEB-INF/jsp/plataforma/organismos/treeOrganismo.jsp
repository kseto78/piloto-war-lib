<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<sj:dialog  id="dialogTree" title="ARBOL ORGANISMO" cssStyle="height:auto;padding:0;" autoOpen="false">
<div id="ArbolOrganismo" class="ui-dialog-content ui-widget-content" style="display: block; min-height: 0px; height: auto;">
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR,ROL_CAID">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
	<div class="editContainer">
		<s:if test="%{organismo.codUnidadSuperior != null && organismo.codUnidadSuperior != ''}">			
			<div style="width: 925px;/*padding-left:25%"*/ class="editContent" >
		</s:if>
		<s:else>
			<div style="width: 925px;padding-left:40%" class="editContent" >
		</s:else>		
			<s:if test="%{organismo.codUnidadSuperior == null && comboOrganismosHijos.isEmpty()}">
				<b><div style="color:red;">	El organismo seleccionado no tiene ningun organismo asociado </div></b>
			</s:if>
			<s:else>
									
				<ul class="tree" style="float:right;min-inline-size: 700px;">
					<s:if test="%{organismo.codUnidadSuperior != null && organismo.codUnidadSuperior != ''}">						
						<label theme="simple" id="organismo" name="organismo.organismoId" style="padding-top: 4px;padding-bottom: 4px;">
						 	<s:if test="%{organismo.codUnidadSuperior.substring(0,1) == \"A\"}">
						 		<img src="img/iconosOrganismos/A.png" height="10" width="10">
						 	</s:if>
						 	<s:if test="%{organismo.codUnidadSuperior.substring(0,1) == \"E\"}">
						 		<img src="img/iconosOrganismos/E.png" height="10" width="10">
						 	</s:if>	
						 	<s:if test="%{organismo.codUnidadSuperior.substring(0,1) == \"I\"}">
						 		<img src="img/iconosOrganismos/I.png" height="10" width="10">
						 	</s:if>
						 	<s:if test="%{organismo.codUnidadSuperior.substring(0,1) == \"U\"}">
						 		<img src="img/iconosOrganismos/U.png" height="10" width="10">
						 	</s:if>
						 	<s:if test="%{organismo.codUnidadSuperior.substring(0,1) == \"L\"}">
						 		<img src="img/iconosOrganismos/L.png" height="10" width="10">
						 	</s:if>
						 		<s:url value="%{organismo.codUnidadSuperior}" />	
						 </label>
						 <label theme="simple" id="nombreOrg" name="nombreOrg" title="<s:url value="%{organismoPadre.nombre}"/>"
								style= "justify-content: center; width:800px; letter-spacing:0px; margin-bottom:0px;<s:if test="%{organismoPadre.nombre.length() >= 140}">display:inline-block</s:if> 
		 						">	  
									-<s:url value="%{organismoPadre.nombre}" />									 	
				 		</label>			
					 <ul>
						<li>
					</s:if>							
								<b><s:if test="%{organismo.codUnidadSuperior != null && organismo.codUnidadSuperior != ''}">
									<label theme="simple"
					 							id="organismo" name="organismo" style="padding-top: 4px;padding-bottom: 4px;">
					 					<s:if test="%{organismo.dir3.substring(0,1) == \"A\"}">
									 		<img src="img/iconosOrganismos/A.png" height="10" width="10">
									 	</s:if>
									 	<s:if test="%{organismo.dir3.substring(0,1) == \"E\"}">
									 		<img src="img/iconosOrganismos/E.png" height="10" width="10">
									 	</s:if>	
									 	<s:if test="%{organismo.dir3.substring(0,1) == \"I\"}">
									 		<img src="img/iconosOrganismos/I.png" height="10" width="10">
									 	</s:if>
									 	<s:if test="%{organismo.dir3.substring(0,1) == \"U\"}">
									 		<img src="img/iconosOrganismos/U.png" height="10" width="10">
									 	</s:if>
									 	<s:if test="%{organismo.codUnidadSuperior.substring(0,1) == \"L\"}">
						 					<img src="img/iconosOrganismos/L.png" height="10" width="10">
						 				</s:if>
					 							<s:url value="%{organismo.dir3}" />	
					 				</label>
		 								<label theme="simple" id="nombreOrg" name="nombreOrg"
					 							 style= "justify-content: center; letter-spacing:0px;" title="<s:url value="%{organismo.nombre}" />">
		 									-<s:url value="%{organismo.nombre}" />	
		 								</label>
									<ul class="hijos" style="margin-left:170px"></b>
								</s:if>	
								<s:else>
									<b></b><s:label theme="simple" id="organismo" name="organismo" value="%{organismo.dir3}" style="padding-top: 4px;padding-bottom: 4px;"/>
									<ul class="hijos" style="margin-left:50px"></b>
								</s:else>
							     	<s:iterator value="comboOrganismosHijos" status="comboOrganismosHijos">
							       		<li>
							       			<label theme="simple"
					 							id="organismo" name="organismo.organismoId">
					 							<s:if test="%{codigo.substring(0,1) == \"A\"}">
											 		<img src="img/iconosOrganismos/A.png" height="10" width="10">
											 	</s:if>
											 	<s:if test="%{codigo.substring(0,1) == \"E\"}">
											 		<img src="img/iconosOrganismos/E.png" height="10" width="10">
											 	</s:if>	
											 	<s:if test="%{codigo.substring(0,1) == \"I\"}">
											 		<img src="img/iconosOrganismos/I.png" height="10" width="10">
											 	</s:if>
											 	<s:if test="%{codigo.substring(0,1) == \"U\"}">
											 		<img src="img/iconosOrganismos/U.png" height="10" width="10">
											 	</s:if>
											 	<s:if test="%{organismo.codUnidadSuperior.substring(0,1) == \"L\"}">
						 							<img src="img/iconosOrganismos/L.png" height="10" width="10">
						 						</s:if>												
												<s:url value="%{codigo}" />	
											</label>
											<label theme="simple" id="nombreOrg" name="nombreOrg" title="<s:url value="%{descripcion}"/>"
				 								style= "justify-content: center; width:485px; letter-spacing:0px;<s:if test="%{descripcion.length() >= 86}">display:inline-block</s:if> 
							 						">	  
				 									-<s:url value="%{descripcion}" />									 	
				 							</label>															       		
							       		</li>	 
									</s:iterator> 					       
						         </ul>
		         <s:if test="%{organismo.codUnidadSuperior != null && organismo.codUnidadSuperior != ''}">
			    	 </li>
	 			 </ul>
			    </s:if>
		    </s:else>
		</div>
	</div>
</div>
</sj:dialog>

<style type="text/css">
#organismo{
  border:1px; 
  border-style:solid;
  border-color:black;
  display:inline-block;
  margin:auto;
  background-color: white;
  padding-left: 10px;
  padding-right: 10px;
  letter-spacing: 0px;
  margin-top: 2px
}
#nombreOrg { 
  width: 640px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; 
  margin-bottom: -6px;
}
ul.hijos{
  margin-left: 100px;
}
ul.tree, ul.tree ul {
    list-style: none;
     margin: 0;
     padding: 50;
     letter-spacing: -4px;
   } 
   ul.tree ul {
     margin-left: 40px;
     margin-top: -4px;
   }
   ul.tree li {
     margin: 0;    
     line-height: 20px;   
     border-left:3px solid rgb(0,0,0);
   }
   ul.tree li:last-child {
       border-left:none;       
   }
   ul.tree li:before {
      position:relative;      
      height:1.2em;
      width:120px;
      color:white;
      border-bottom:3px solid rgb(0,0,0);
      content:"       ";
      display:inline-block;      
   }
   ul.tree li:last-child:before {
      border-left:3px solid rgb(0,0,0); 
      top:-0.1em;	      
   }

</style>
<script>
var nombOrganismo = "${organismo.nombre}";
if(nombOrganismo.length > 103 ){
	document.getElementById('nombreOrg').style.display="inline-block";
}

</script>