<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<sj:dialog  id="dialogTree" title="ARBOL ORGANISMO" cssStyle="height:auto;padding:0;" autoOpen="false">
<div id="ArbolOrganismo" class="ui-dialog-content ui-widget-content" style="display: block; min-height: 0px; height: auto;">
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
	<div class="editContainer">
		<div style="width: 135px" class="nameDescription">
			<label>Arbol</label>		
		</div>
	
		<s:if test="%{organismo.codUnidadSuperior != null && organismo.codUnidadSuperior != ''}">			
			<div style="width: 770px;padding-left:15%" class="editContent" >
		</s:if>
		<s:else>
			<div style="width: 770px;padding-left:30%" class="editContent" >
		</s:else>		
			<s:if test="%{organismo.codUnidadSuperior == null && comboOrganismosHijos.isEmpty()}">
				<b><div style="color:red;">	El organismo seleccionado no tiene ningun organismo asociado </div></b>
			</s:if>
			<s:else>
									
				<ul class="tree">
					<s:if test="%{organismo.codUnidadSuperior != null && organismo.codUnidadSuperior != ''}">						
						<s:label theme="simple" 
								id="organismo" name="organismo" 
								value="%{organismo.codUnidadSuperior}" style="padding-top: 4px;padding-bottom: 4px;"/>
					
					 <ul>
						<li>
					</s:if>							
								<b><s:if test="%{organismo.codUnidadSuperior != null && organismo.codUnidadSuperior != ''}">
									<s:label theme="simple" id="organismo" name="organismo" value="%{organismo.dir3}" />									
									<ul class="hijos" style="margin-left:170px"></b>
								</s:if>	
								<s:else>
									<b></b><s:label theme="simple" id="organismo" name="organismo" value="%{organismo.dir3}" style="padding-top: 4px;padding-bottom: 4px;"/>
									<ul class="hijos" style="margin-left:50px"></b>
								</s:else>
							     	<s:iterator value="comboOrganismosHijos" status="comboOrganismosHijos">
							       		<li>
											<s:label theme="simple"
					 							id="organismo" name="organismo.organismoId" value="%{codigo}" />					       		
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
      height:1em;
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