<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="text/javascript" src="<%=basePath%>/script/jquery-1.9.1.min.js"></script>
</head>
<body>
   <div class="leftMenu">
   	  <ul>
   	  	<c:forEach items="${perimisionList }" var="permisson">
   	  		<li><a href="<%=basePath%>${permisson.rightUrl}" target="rightFrame" onclick="changeBg(this);" >${permisson.rightText}</a></li>
   	  	</c:forEach>
   	  <%-- 	<li><a href="<%=basePath%>sysUserController/findSysUserListByPage/0.do" target="rightFrame">员工信息管理</a></li>
   	  	<li><a href="<%=basePath%>visitorController/findVisitorByPage/0.do" target="rightFrame">登记信息管理</a></li>
   	  	<li><a href="<%=basePath%>visitorRegisterController/findVisitorListByPage/0.do" target="rightFrame">访问记录管理</a></li>
   	  	<li><a href="<%=basePath%>appointController/findAppointListByPage/0.do" target="rightFrame">预约信息管理</a></li>
   	  	<li><a href="<%=basePath%>jsp/adminInfo.jsp" target="rightFrame">管理员信息</a></li>
   	  	<li><a href="<%=basePath%>sysRoleController/findSysRoleListByPage/0.do" target="rightFrame">角色管理</a></li>
   	  	<li><a href="<%=basePath%>departmentControll/findAllDepartment/0.do" target="rightFrame">部门管理</a></li>
   	  	<li><a href="<%=basePath%>jsp/personal.jsp" target="rightFrame">个人信息</a></li> --%>
   	  </ul>
   </div>
</body>
<script type="text/javascript">
  function changeBg(obj){
	  $(obj).css({"background":"url(../images/menu_bg.jpg) no-repeat"});
	  $.each($(obj).parent().siblings(),function(i){
		  $(this).children().css({"background":"#e5f0ff"});
	  })
  }
</script>
</html>
