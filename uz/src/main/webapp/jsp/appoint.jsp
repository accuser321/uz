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
    <title>预约信息管理</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/layout.css">
	<script type="text/javascript" src="<%=basePath%>/script/jquery-1.9.1.min.js"></script>    
    <script type="text/javascript" src="<%=basePath%>/script/common.js"></script>    
  </head>
  
  <body>
  <div class="fliter-box registInfo">
  	<table width="100%" cellpadding="0" cellspacing="0">
  		<tr>
  			<th>姓名：</th>
  			<td><input id="appName" class="ipt-text" type="text"></td>
  			<th>电话：</th>
  			<td><input id="appPhone" class="ipt-text" type="text"></td>
  			<th>公司：</th>
  			<td>
  			  <input id="appComp" class="ipt-text" type="text">
  			</td>
  			<td><a href="javascript:void(0);" onclick="schAppoint();"><img src="../images/btn_search.png" alt=""></a></td>
  		</tr>
  	</table>
  </div>
  <div class="fliter-btn">
  	<a href="javascript:void(0);"><img src="../images/btn_add.jpg" onclick="modAppoint();" alt=""></a>
  	<!-- <a href="javascript:void(0);"><img src="../images/btn_print.png" alt=""></a> -->
  </div>
  <div class="tableFrom">
  	<table width="100%">
  	  <thead>
  	  	<th><input id="Allchk" type="checkbox"></th>
  	  	<th>姓名</th>
  	  	<th>身份证号</th>
  	  	<!-- <th>接待人</th>
  	  	<th>时间</th> -->
  	  	<th>电话</th>
  	  	<th>公司</th>
  	  	<th>操作</th>
  	  </thead>
  	  <tbody>
       <c:forEach items="${appointListByPage}" var="appoint">  	  
  	  	<tr>
  	  		<td><input class="ckh" type="checkbox"></td>
  	  		<td>${appoint.name}</td>
  	  		<td>${appoint.idNum}</td>
  	  		<!-- <td>${appoint.userId}</td> -->
  	  		<!-- <td>2015-06-30</td> -->
  	  		<td>${appoint.phoneNum}</td>
  	  		<td>${appoint.companyName}</td>
  	  		<td><a href="javascript:void(0);" onclick="delAppoint('${appoint.appointId}');">删除</a></td>
  	  	</tr>
  	  	</c:forEach>
  	  </tbody>
  	</table>
  </div>
  <jsp:include page="page.jsp" flush="true">
		<jsp:param value="/appointController/findAppointListByPage" name="url" />
		<jsp:param value="userName=${sysuser.userName}&deptId=${sysuser.deptId }&userSex=${sysuser.userSex }" name="param"/>
  </jsp:include>
  <!-- 新增弹窗DIV start -->
  <div id="appointDiv" style="display:none;">
  	<div class="order-box">
      <div class="staff-tab">
      	<table width="100%">
      	  <form:form id="addAppoint" action="/appointController/insertAppoint.do" method="post">
      		<tbody>
      			<tr>
      				<th>姓名：</th>
      				<td width="252">
	      				<span>*</span>
	      				<input id="name" name="name" class="ipt-text" type="text"></td>
	      			<th>性别：</th>
      				<td>
	      				<div class="selects">
	      					<span>*</span>
	      					<select name="sex" id="sex">
	      						<option value="" selected="selected">--请选择--</option>
	      						<option value="1">男</option>
	      						<option value="0">女</option>
	      					</select>
	      				</div>
      				</td>
      				<!-- <th>接待人：</th>
      				<td><span>*</span><input class="ipt-text" type="text"></td> -->
      			</tr>
      			<tr>
      				<th>身份证号：</th>
      				<td><span>*</span>
      				<input id="idNum" name="idNum" class="ipt-text" type="text"></td>
      				<th>公司：</th>
      				<td><span>*</span>
      				<input id="companyName" name="companyName" class="ipt-text" type="text"></td>
      			</tr>
      			<tr>
      				<th>电话：</th>
      				<td><span>*</span>
      				<input id="phoneNum" name="phoneNum" class="ipt-text" type="text"></td>
      				<!-- <th>日期：</th>
      				<td>
      				  <span>*</span><div class="drop-down">      				  
	                    <b class="down-ico" onClick="WdatePicker({el:'ti-ico1'})"></b>
	                    <input class="ipt-text Wdate" id="ti-ico1" width="98" type="text" onClick="WdatePicker()" />
                      </div>&nbsp;至&nbsp;
                      <div class="drop-down">      				  
	                    <b class="down-ico" onClick="WdatePicker({el:'ti-ico2'})"></b>
	                    <input class="ipt-text Wdate" id="ti-ico2" width="98" type="text" onClick="WdatePicker()" />
                      </div>  
      				</td> -->
      			</tr>
      		  </form:form>
      		</tbody>
      	</table>
      	<div class="error"><span></span></div>
      </div>
  	</div>
  </div>
  <!-- 新增弹窗DIV end -->
<script type="text/javascript" src="<%=basePath%>/script/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/jquery.artDialog.source.js"></script>
</body>
<script type="text/javascript">
  function modAppoint(){
	art.dialog({
		title:"新增预约",//这是标题
		padding:'10px 15px',  //这是内补丁，默认为10px
		content: document.getElementById('appointDiv'),
		lock:true,   //这表示是否出现背景遮罩层
		opacity:0.5,  //这表示遮罩层的背景透明度，默认这个数值
		id:'appointAdd', //加了id可以防止多次弹出同一个窗口
		ok: function () {
			var name= $.trim($("#name").val());
		    var userSex= $("#sex").val();
		    var uid= $.trim($("#idNum").val());
		    var phone= $.trim($("#phoneNum").val());
		    var companyName= $.trim($("#companyName").val());
		    
		    if(name.length==0){
		      $(".error").css({"display":"block"});
		      $('.error span').html('请输入预约人员姓名！');
		      return false;	      
		    }
		    if(userSex.length==0){
		      $(".error").css({"display":"block"});
		      $('.error span').html('请输选择预约人员性别！');
		      return false;
		    }
		    if(uid.length==0){
		      $(".error").css({"display":"block"});
		      $('.error span').html('请输入预约人员身份证！');
		      return false;
		    }
		    if(companyName.length==0){
		      $(".error").css({"display":"block"});
		      $('.error span').html('请输入预约人员公司名称！');
		      return false;
		    }else{
		    	$(".error").css({"display":"none"});
		    }
		    var p1 = /^0?(13[0-9]|15[012356789]|17[0123456789]|18[0-9]|14[57])[0-9]{8}$/;
			if(phone.length==0){	      
			      $(".error").css({"display":"block"});
			      $('.error span').html('预约人员手机号码不能是空！');
			      return false;
			}else if(!(p1.test(phone))){
				  $(".error").css({"display":"block"});
			      $('.error span').html('您输入的手机号码格式不正确！');
			      return false;
			}
			
		    $("#addAppoint").submit();

        },
        cancelVal:'取消',
        cancel:true
		
	   });
	};
	function delAppoint(appointId){
	   if(confirm("是否删除?")){
	   	$.get("/appointController/deleteAppointByAppointId/"+appointId+".do",null,function(msg){
	   		window.location.href="/appointController/findAppointListByPage/0.do";
	   	});
	   }
	};
	
	function schAppoint(){
		var appname=$.trim($("#appName").val());
		var appphone=$.trim($("#appPhone").val());
		var appcompany=$.trim($("#appComp").val());

		window.location.href="/appointController/findAppointListByPage/0.do?name="+appname+"&phoneNum="+appphone+"&companyName="+appcompany;
		
	};
</script>
</html>
