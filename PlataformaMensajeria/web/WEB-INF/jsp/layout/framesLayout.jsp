<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<%-- Frameset Layout component 
  parameters : title, header, menu, body, footer 
--%>
<%@ page errorPage="errorpage.jsp" %>


<html>
<head>
<title>
<tiles:getAsString name="title"/>
</title>
<s:head/>
</head>
<frameset rows="60,*,60">
  <frame src="<%=request.getContextPath()%><tiles:getAsString name="header" />" SCROLLING="no" FRAMEBORDER=0 name="header" >
  <frameset cols="*,*">
  	<frame src="<%=request.getContextPath()%><tiles:getAsString name="menu" />" name="menu" SCROLLING="auto" FRAMEBORDER=0>
  	<frame src="<%=request.getContextPath()%><tiles:getAsString name="body" />" name="body" SCROLLING="auto" FRAMEBORDER=0>
  </frameset>
  <frame src="<%=request.getContextPath()%><tiles:getAsString name="footer" />" name="footer" SCROLLING="no" FRAMEBORDER=0>
</frameset>
<noframes>
<body></body>
</noframes>
</html>