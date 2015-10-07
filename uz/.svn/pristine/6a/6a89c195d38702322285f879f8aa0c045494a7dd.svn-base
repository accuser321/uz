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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/layout.css">
</head>
<body>
  <div class="navTopbg">
    <div class="navTop">
   	  <div class="top-left"><img src="../images/logo_01.png" alt=""></div>
   	  <div class="top-right">
   	    <ul>
   	      <li>欢迎您，${user.userName}</li>
   	      <li><a href="/sysUserController/loginOut.do" target="_top">退出</a></li>
   	    </ul>
   	  </div>
    </div>
   </div>
</body>
</html>