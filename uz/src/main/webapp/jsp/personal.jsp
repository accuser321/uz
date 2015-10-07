<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTDHTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>个人信息</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/layout.css">
	<script type="text/javascript" src="<%=basePath%>/script/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/script/common.js"></script> 
    <script type="text/javascript" src="<%=basePath%>/script/jquery.artDialog.source.js"></script>
    <script type="text/javascript" src="<%=basePath%>/script/iframeTools.source.js"></script>
  </head>
  
  <body>
  <div class="perTable">
  	<table>
  		<tbody>
  			<tr>
  				<th>当前密码：</th>
  				<td><input id="curPwd" type="password" maxlength="12" class="ipt-text"><span class="curpwd"></span></td>
  			</tr>
  			<tr>
  				<th>新密码：</th>
  				<td><input id="newPwd" type="password" maxlength="12" class="ipt-text"><span class="newpwd"></span></td>
  			</tr>
  			<tr>
  				<th>确认密码：</th>
  				<td><input id="okPwd" type="password" maxlength="12" class="ipt-text"><span class="okpwd"></span></td>
  			</tr>
  		</tbody>
  	</table>
  	<div class="perBtn">
      <a href="javascript:void(0);" onclick="modifyPwd();"><img src="../images/btn_ok.jpg" alt=""></a>
      <a href="javascript:void(0);"><img src="../images/btn_cancel.jpg" alt=""></a>
  	</div>
  </div>
  <script type="text/javascript">
    function modifyPwd(){
      var curpwd= $.trim($("#curPwd").val());
      var newpwd= $.trim($("#newPwd").val());
      var okpwd= $.trim($("#okPwd").val());
      var name= '${user.userName}';
      var pwd= '${user.userPass}';
      if(curpwd.length==0){
        $(".curpwd").css({"display":"inline-block"});
        $(".curpwd").html("请输入您的当前登录密码");
        return false;
      }
      if(curpwd.length!=0 && curpwd!=pwd){
          $(".curpwd").css({"display":"inline-block"});
          $(".curpwd").html("您的当前登录密码输入有误")
          return false;
      }
      if(curpwd.length!=0 && curpwd==pwd){
          $(".curpwd").css({"display":"none"});
      }
      if(newpwd.length==0){
        $(".newpwd").css({"display":"inline-block"});
        $(".newpwd").html("您的新密码不能是空");
        return false;
      }
      if(newpwd.length!=0 && newpwd.length<6){
    	  $(".newpwd").css({"display":"inline-block"});
          $(".newpwd").html("您的新密码最小长度6位，由数字或者字母组成");
          return false;
      }
      if(newpwd.length!=0 && newpwd.length>=6){
    	  $(".newpwd").css({"display":"none"});
      }
      if(okpwd.length==0){
        $(".okpwd").css({"display":"inline-block"});
        $(".okpwd").html("新密码确认不能是空")
        return false;
      }
      if(newpwd!=okpwd){
         $(".okpwd").css({"display":"inline-block"});
         $(".okpwd").html("两次输入的密码不一致");
         return false;
      }if(okpwd.length!=0 && okpwd==newpwd){
    	 $(".okpwd").css({"display":"none"});
      }
      if(newpwd==okpwd && curpwd==pwd){
         $.ajax({
              url:"/sysUserController/updateSysUserByName.do",
              type:"post",
              cache:"false",
              data:{userPass:newpwd},
              success:function(msg){
              var json_str=eval("("+'msg'+")");
              var timer;
              art.dialog({
                 content: '密码修改成功，正在跳转到登录界面...',
                 width:270,
                 height:90,
                 opacity:0.5,
                 id:"tiao",
                 init: function () {
                    var that = this, i = 3;
                    var fn = function () {
                         that.title(i + '秒后关闭');
                         !i && that.close();
                         i --;
                    };
                    timer = setInterval(fn, 1000);
                 },
                 close: function () {
                    clearInterval(timer);
                    top.location.href="/sysUserController/loginOut.do";
                 }
               }).show();
            }
          })
    
      }

  }
   
  </script>
</body>
</html>
