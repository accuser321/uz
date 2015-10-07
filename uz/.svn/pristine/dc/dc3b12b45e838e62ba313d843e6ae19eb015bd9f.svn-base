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
    <title></title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/layout.css">
	<script type="text/javascript" src="<%=basePath%>/script/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>/script/common.js"></script>
  <script type="text/javascript" src="<%=basePath%>/script/datePicker/WdatePicker.js"></script>
  </head>
  
  <body>
  <div class="fliter-box registInfo">
    <form action="/visitorRegisterController/findVisitorRegisterList/0.do" method="post">
  	<table width="100%" cellpadding="0" cellspacing="0">
  		<tr>
  			<th>姓名：</th>
  			<td><input id="visitName" name="visitorName" class="ipt-text" type="text"></td>
  			<!-- <th>身份证号：</th>
  			<td><input class="ipt-text" type="text"></td>
  			<th>接待人：</th>
  			<td><input class="ipt-text" type="text"></td> -->
  			<th>内部员工：</th>
  			<td>
  			  <div class="selects">
	                <select id="visitId" >
	                  <option value="" selected="selected">--请选择--</option>
	                  <option value="1">是</option>
	                  <option value="0">否</option>
	                </select>
              </div>
  			</td>
  			
  			<th>类型：</th>
  			<td>
  			  <div class="selects">
	                <select id="exportType"   onchange="exportSelectType();">
	                  <option value="" selected="selected">--请选择--</option>
	                  <option value="1">访问记录</option>
	                  <option value="0">打卡记录</option>
	                </select>
              </div>
  			</td> 
  		</tr>
  		<tr>
  			<th>电话：</th>
  			<td><input id="visitPhone" name="visitorPhoneNum" class="ipt-text" type="text"></td>
  			<th>公司：</th>
  			<td>
  			  <input id="visitCom" name="visotorCompanyName" class="ipt-text" type="text">
  			</td>
  			<th>时间：</th>
  			<td>
  			  <div class="drop-down">
                <b class="down-ico" onClick="WdatePicker({el:'vStartDate'})"></b>
                <input class="ipt-text Wdate" id="vStartDate" width="98" type="text" onClick="WdatePicker()" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'vEndDate\')}'})"/>
              </div>
  			  <div class="drop-down">
                 &nbsp;至&nbsp;<b class="down-ico" onClick="WdatePicker({el:'vEndDate'})"></b>
                <input class="ipt-text Wdate" id="vEndDate" width="98" type="text" onClick="WdatePicker()" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'vStartDate\')}'})"/>
              </div>
  			</td>
  			<td>
  				<a href="javascript:void(0);" ><img src="../images/btn_search.png" alt="" onclick="schRecord();"></a>
  				<a href="javascript:void(0);" ><img src="../images/export.png" alt="" onclick="exportRecord();"></a>
  			</td>
  		</tr>
  	</table>
  	</form>
  </div>
  <div class="tableFrom">
  	<table width="100%">
  	  <thead>
  	  	<th><input id="Allchk" type="checkbox"></th>
  	  	<th>姓名</th>
  	  	<!-- <th>身份证号</th>
  	  	<th>接待人</th> -->
  	  	<th>来访时间</th>
  	  	<th>电话</th>
  	  	<th>公司名称</th>
  	  	<th>正常时间</th>
  	  	<th>非正常时间</th>
  	  	<th>异常时间</th>
  	  	<th>正常时间总计</th>
  	  	<th>加班时间总计</th>
  	  	<th>操作</th>
  	  </thead>
  	  <tbody>
  	  <c:forEach items="${visitorListByPage}" var="visits">
  	  	<tr>
  	  		<td><input class="ckh" type="checkbox"></td>
  	  		<td>${visits.visitorName}</td>
  	  		<!-- <td>422201201506301111</td>
  	  		<td>张三</td> -->
  	  		<td>${visits.registerTime}</td>
  	  		<td>${visits.visitorPhoneNum}</td>
  	  		<td>${visits.visotorCompanyName}</td>
  	  		<td></td>
  	  		<td></td>
  	  		<td style="background-color: red;" ><font  size="5">321</font></td>
  	  		<td></td>
  	  		<td></td>
  	  		<td>
  	  		<a href="#">修改</a> |
  	  		<a href="javascript:void(" onclick="delVisitRecord('${visits.registerId}');">删除</a>
  	  		</td>
  	  	</tr>
  	  </c:forEach>
  	  </tbody>
  	</table>
  </div>
  <jsp:include page="page.jsp" flush="true">
		<jsp:param value="/visitorRegisterController/findVisitorListByPage" name="url" />
		<jsp:param value="userName=${sysuser.userName}&deptId=${sysuser.deptId }&userSex=${sysuser.userSex }" name="param"/>
  </jsp:include>
  
  <script type="text/javascript">
    function schRecord(){
    	var visitName= $.trim($("#visitName").val());
    	var visitId= $("#visitId").val();
    	var visitPhone= $.trim($("#visitPhone").val());
    	var visitCom= $.trim($("#visitCom").val());
    	var ti1= $("#vStartDate").val();
    	var ti2= $("#vEndDate").val();
  	    window.location.href="/visitorRegisterController/findVisitorListByPage/0.do?visitorName="+visitName+"&starff="+visitId+"&visitorPhoneNum="+visitPhone+"&visotorCompanyName="+visitCom+"&startTime="+ti1+"&endTime="+ti2+"";
    }
    function delVisitRecord(registId){
    	if(confirm("请确认是否删除？")){
  		  $.get("/visitorRegisterController/deleteVisitorRegisterByVisitorRegisterId/"+registId+".do",null,function(msg){
  			var json_str = eval("("+msg+")"); 
  			window.location.href = "/visitorRegisterController/findVisitorListByPage/0.do";
  		  });
    	  //window.location.href="/visitorRegisterController/deleteVisitorRegisterByVisitorRegisterId/"+registId+".do";
  	   }	
    }
    
    function exportRecord(){
    	//下载action
    	window.location.href = "";
    }
   function  exportSelectType(){
	   var exportType = $("#exportType").val();
	   if(exportType!=""){
		   window.location.href="/visitorRegisterController/findVisitorListByPage/0.do?visitorName="+visitName+"&starff="+visitId+"&visitorPhoneNum="+visitPhone+"&visotorCompanyName="+visitCom+"&startTime="+ti1+"&endTime="+ti2+"exportType="+exportType+"";
	   }
   }
  </script>
</body>
</html>
