<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<sj:dialog  id="dialogTree" title="ARBOL ORGANISMO PDP" cssStyle="height:auto;padding:0;" autoOpen="false">
<div id="ArbolOrganismoPdp" class="ui-dialog-content ui-widget-content" style="display: block; min-height: 0px; height: auto;">
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR,ROL_CAID">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
	<div class="editContainer">
		<div style="width: 925px;" class="editContent" >				
			<s:if test="%{comboOrganismosHijos.isEmpty()}">
				<b><div style="color:red;padding-left:250px">El PdP-diputación seleccionado no tiene ningun organismo asociado </div></b>				
			</s:if>
			<s:else>			
				<ul class="tree" style="min-inline-size:600px;float:right">	
					<b><s:label theme="simple" class="organismo" name="organismo" value="%{organismo.nombre}" style="padding-top: 4px;padding-bottom: 4px;" />									
						<ul class="hijos"></b>
			     	<s:iterator value="comboOrganismosHijos" status="comboOrganismosHijos">
			       		<li>			       				
			       			<a href="javascript:;" onclick="javascript:seleccion(<s:property value="%{descripcion}" />)" name="organismo.organismoId" >
			       			<label theme="simple"
					 							id="${descripcion}" class="organismo" name="organismo.organismoId">
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
									 	<s:if test="%{codigo.substring(0,1) == \"L\"}">
						 					<img src="img/iconosOrganismos/L.png" height="10" width="10">
						 				</s:if>
					 							<s:url value="%{codigo}" />	
					 							
		 							</label>
 							</a>
 							<label theme="simple" id="nombreOrg" name="nombreOrg"  title="<s:url value="%{nombre}" />"
		 								style= "justify-content: center; letter-spacing:0px;<s:if test="%{nombre.length() >= 115}">display:inline-block</s:if> 
					 							">
		 									-<s:url value="%{nombre}" />									 	
 							</label>		       		
			       		</li>	 
					</s:iterator> 					       
		         </ul>	
		    </s:else>
		</div>
	</div>
</div>
</sj:dialog>

<style type="text/css">
label.organismo{
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