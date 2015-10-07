<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTDHTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title></title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="shortcut icon" type="image/x-icon" href="<%=basePath%>/images/favicon.ico">
</head>
  <frameset rows="110,*" cols="*" frameborder="0" framespacing="0" border="0">
  	<frame id="topFrame" src="<%=basePath%>jsp/top.jsp" name="topFrame" scrolling="No" noresize="noresize" title="topFrame">
  		<frameset cols="300,*" frameborder="0" framespacing="0" border="0">
  			<frame id="leftMenu" src="<%=basePath%>jsp/menu.jsp" name="leftMenu" scrolling="no" noresize="noresize" title="leftMenu"></frame>
  			<frame id="index" src="<%=basePath%>jsp/index.jsp" name="rightFrame" scrolling="yes" noresize="noresize" title="leftFrame"></frame>
  		</frameset>
  	</frame>
  </frameset>
  <noframes>
<body>
</body>
  </noframes>
</html>