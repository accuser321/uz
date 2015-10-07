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
  			<td><input class="ipt-text" type="text"></td>
  			<th>身份证号：</th>
  			<td><input class="ipt-text" type="text"></td>
  			<th>接待人：</th>
  			<td><input class="ipt-text" type="text"></td>
  		</tr>
  		<tr>
  			<th>电话：</th>
  			<td><input class="ipt-text" type="text"></td>
  			<th>公司：</th>
  			<td>
  			  <input class="ipt-text" type="text">
  			</td>
  			<th>时间：</th>
  			<td>
  			  <div class="drop-down">
                <b class="down-ico" onClick="WdatePicker({el:'ti-ico'})"></b>
                <input class="ipt-text Wdate" id="ti-ico" width="98" type="text" onClick="WdatePicker()" />
              </div>
  			  <div class="drop-down">
                 &nbsp;至&nbsp;<b class="down-ico" onClick="WdatePicker({el:'ti-ico'})"></b>
                <input class="ipt-text Wdate" id="ti-ico" width="98" type="text" onClick="WdatePicker()" />
              </div>
  			</td>
  			<td><a href=""><img src="../images/btn_search.png" alt=""></a></td>
  		</tr>
  	</table>
  </div>
  <div class="fliter-btn">
  	<a href="javascript:void(0);"><img src="../images/btn_add.jpg" onclick="modOrder();" alt=""></a>
  	<a href=""><img src="../images/btn_print.png" alt=""></a>
  </div>
  <div class="tableFrom">
  	<table width="100%">
  	  <thead>
  	  	<th><input id="Allchk" type="checkbox"></th>
  	  	<th>姓名</th>
  	  	<th>身份证号</th>
  	  	<th>接待人</th>
  	  	<th>时间</th>
  	  	<th>电话</th>
  	  	<th>公司</th>
  	  	<th>操作</th>
  	  </thead>
  	  <tbody>
  	  	<tr>
  	  		<td><input class="ckh" type="checkbox"></td>
  	  		<td>张三</td>
  	  		<td>422201201506301111</td>
  	  		<td>张三</td>
  	  		<td>2015-06-30</td>
  	  		<td>0755-1234567</td>
  	  		<td>深圳市.......公司</td>
  	  		<td><a href="#">删除</a></td>
  	  	</tr>
  	  </tbody>
  	</table>
  </div>
  <jsp:include page="page.jsp" flush="true">
		<jsp:param value="/sysUserController/findSysUserListByPage" name="url" />
		<jsp:param value="userName=${sysuser.userName}&deptId=${sysuser.deptId }&userSex=${sysuser.userSex }" name="param"/>
  </jsp:include>
  <!-- 新增弹窗DIV start -->
  <div id="orderDiv" style="display:none;">
  	<div class="order-box">
      <div class="staff-tab">
      	<table width="100%">
      		<tbody>
      			<tr>
      				<th>姓名：</th>
      				<td width="252"><span>*</span><input class="ipt-text" type="text"></td>
      				<th>接待人：</th>
      				<td><span>*</span><input class="ipt-text" type="text"></td>
      			</tr>
      			<tr>
      				<th>身份证号：</th>
      				<td><span>*</span><input class="ipt-text" type="text"></td>
      				<th>公司：</th>
      				<td><span>*</span><input class="ipt-text" type="text"></td>
      			</tr>
      			<tr>
      				<th>电话：</th>
      				<td><span>*</span><input class="ipt-text" type="text"></td>
      				<th>日期：</th>
      				<td>
      				  <span>*</span><div class="drop-down">      				  
	                    <b class="down-ico" onClick="WdatePicker({el:'ti-ico'})"></b>
	                    <input class="ipt-text Wdate" id="ti-ico" width="98" type="text" onClick="WdatePicker()" />
                      </div>&nbsp;至&nbsp;
                      <div class="drop-down">      				  
	                    <b class="down-ico" onClick="WdatePicker({el:'ti-ico'})"></b>
	                    <input class="ipt-text Wdate" id="ti-ico" width="98" type="text" onClick="WdatePicker()" />
                      </div>  
      				</td>
      			</tr>
      		</tbody>
      	</table>
      	<div class="tips"><span>请输入必填信息！</span></div>
      </div>
  	</div>
  </div>
  <!-- 新增弹窗DIV end -->
<script type="text/javascript" src="../script/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../script/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../script/jquery.artDialog.source.js"></script>
</body>
<script>
  function modOrder(){
	art.dialog({
		title:"新增",  //这是标题
		padding:'10px 15px',  //这是内补丁，默认为10px
		content: document.getElementById('orderDiv'), //这个ID就是页面里面那个藏着的div的ID
		lock:true,   //这表示是否出现背景遮罩层
		// window:'top',
		opacity:0.5,  //这表示遮罩层的背景透明度，默认这个数值
		id:'newClass', //加了id可以防止多次弹出同一个窗口
		ok: function () {
    	  // this.title('3秒后自动关闭').time(3); 
    	  alert("OK");
          // return false;
        },
        cancelVal:'取消',
        cancel:true
		// button: [  //自定义按钮
		// 	{ 
		// 	  name: '确定', 
		// 	  // name:'aa',
		// 	  callback: function () {
		// 		 alert('确定'); 
		// 		 return true; 
		// 		}				
		// 	}
		//    ]	
	   });
	}
</script>
</html>
