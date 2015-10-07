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
    <link rel="stylesheet" type="text/css" href="../css/layout.css">
</head>
<body>
  <div class="mainindex">
        <div class="welinfo">
            <span>
                <img src="../images/sun.png" alt="天气" />
            </span> <b>您好！${user.userName}，欢迎使用智优出入管理系统。</b>
          <!-- (admin@uimaker.com)
            <a href="#">帐号设置</a> -->
        </div>

        <!-- <div class="welinfo">
            <span>
                <img src="../images/time.png" alt="时间" />
            </span> <i>您上次登录的时间：2013-10-09 15:22</i>
            <!-- （不是您登录的？
            <a href="#">请点这里</a>） -->
        </div>
        <div class="xline"></div>
    </div>
</body>
</html>