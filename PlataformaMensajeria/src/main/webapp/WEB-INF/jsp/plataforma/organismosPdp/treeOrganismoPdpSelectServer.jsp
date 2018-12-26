<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<sj:dialog  id="dialogTree" title="ARBOL ORGANISMO PDP" cssStyle="height:auto;padding:0;" autoOpen="false">
<div id="ArbolOrganismoPdp" class="ui-dialog-content ui-widget-content" style="display: block; min-height: 0px; height: auto;">
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
	<div class="editContainer">
		<div style="width: 135px" class="nameDescription">
			<label>Arbol de Organismos Pdp</label>		
		</div>
			
		<div style="width: 770px;padding-left:15%" class="editContent" >					
			<s:if test="%{comboOrganismosHijos.isEmpty()}">
				<b><div style="color:red;">	El organismo pdp seleccionado no tiene ningun organismo asociado </div></b>
			</s:if>
			<s:else>			
				<ul class="tree">			
					<b><s:label theme="simple" class="organismo" name="organismo" value="%{organismo.nombre}" style="margin-left:130px" />									
						<ul class="hijos" style="margin-left:170px"></b>
			     	<s:iterator value="comboOrganismosHijos" status="comboOrganismosHijos">
			       		<li>
			       			<a href="javascript:;" onclick="javascript:seleccionServidor(<s:property value="%{descripcion}" />)" name="organismo.organismoId" >
			       				<s:label theme="simple" id="%{descripcion}" 
	 							class="organismo" name="organismo.organismoId" value="%{codigo}" />
 							</a>					       		
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