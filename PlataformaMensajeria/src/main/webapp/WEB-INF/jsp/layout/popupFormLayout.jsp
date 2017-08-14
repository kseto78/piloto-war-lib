<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%@ page errorPage="errorpage.jsp" %>
<html:html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<meta http-equiv='pragma' content='no-cache'>
	<meta http-equiv='cache-control' content='no-cache'>
	<meta http-equiv='expires' content='0'>
	<title><tiles:getAsString name="title"/></title>
	<link rel="stylesheet"
		href='${pageContext.request.contextPath}/css/aplicacionesExternas.css'
		type="text/css">
	<script language="Javascript">
		var imgsrc='<%=request.getContextPath()%>/images/layouts/';
	</script>
	<script language="Javascript" src="<%=request.getContextPath()%>/js/incluir_scripts.js" ></script>
	<s:head/>
</head>
<fwk:body>
<table border="0" width="770" cellspacing="0" cellpadding="0">
  <tr>
    <td><tiles:insert attribute="header"/></td>
  </tr>
  
  <tr>
    <td valign="top" align="left">
	  	<table width="100%" cellspacing="0" cellpadding="0" style="margin-left:0px">
	      <tr>
	        <td><html:errors/></td>
	      </tr>
	      <tr>
	        <td><tiles:insert attribute='body'/></td>
	      </tr>
	  	</table>
    </td>
  </tr>
  
  <tr>
    <td><tiles:insert attribute="footer" /></td>
  </tr>
  
</table>

</fwk:body>
</html:html>
