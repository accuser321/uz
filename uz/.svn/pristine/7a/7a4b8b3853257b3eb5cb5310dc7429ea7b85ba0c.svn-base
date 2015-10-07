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
  			<!-- <th>内部员工：</th>
  			<td>
  			  <div class="selects">
	                <select id="visitId" >
	                  <option value="" selected="selected">--请选择--</option>
	                  <option value="1">是</option>
	                  <option value="0">否</option>
	                </select>
              </div>
  			</td> -->
  			<!-- <th>月份：</th>
  			<td>
  			  <div class="selects">
	                <select id="exportType"   onchange="exportSelectMoth();">
	                  <option value="" selected="selected">--请选择--</option>
	                  <option value="1">1</option>
	                  <option value="2">2</option>
	                  <option value="1">3</option>
	                  <option value="2">4</option>
	                  <option value="1">5</option>
	                  <option value="2">6</option>
	                  <option value="1">7</option>
	                  <option value="2">8</option>
	                  <option value="1">9</option>
	                  <option value="2">10</option>
	                  <option value="1">12</option>
	                  <option value="2">12</option>
	                </select>
              </div>
  			</td>  -->
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
  		<tr>
  			
  		</tr>
  	</table>
  	</form>
  </div>
  <div class="tableFrom">
  	<table width="100%">
  	  <thead>
  	  	<th><input id="Allchk" type="checkbox"></th>
  	  	<th>姓名</th>
  	  	<th>时间</th>
  	  	<th>正常时间</th>
  	  	<th>非正常时间</th>
  	  	<th>异常时间</th>
  	  	<th>加班时间</th>
  	  	<!-- <th>操作</th> -->
  	  </thead>
  	  <tbody>
  	  <c:forEach items="${userMonthTimeList}" var="userMonth">
  	  	<tr>
  	  		<td><input class="ckh" type="checkbox"></td>
  	  		<td>${userMonth.userName}</td>
  	  		<td>${userMonth.today}</td>
  	  		<td>${userMonth.workTimeStr}</td>
  	  		<td >${userMonth.outTimeStr}</td>
  	  		<td>${userMonth.illigalTimeStr}</td>
  	  		<td>${userMonth.overTimeStr}</td>
  	  		<%-- <td>
  	  		<a href="#">修改</a> |
  	  		<a href="javascript:void(" onclick="delVisitRecord('${userMonth.id}');">删除</a>
  	  		</td> --%>
  	  	</tr>
  	  </c:forEach>
  	  </tbody>
  	</table>
  </div>
  <jsp:include page="page.jsp" flush="true">
		<jsp:param value="/exportExcelController/findUserMonthTimeListByPage" name="url" />
		<jsp:param value="userName=${userMonthTime.userName}&startTime=${userMonthTime.startTime}&endTime=${userMonthTime.endTime}" name="param"/>
  </jsp:include>
   <!-- 新增弹窗DIV start -->
  <div id="roleDiv" style="display:none;">
  	<div class="adminTable modal-table">
      <form:form action="/exportExcelController/insertUserMonthTime.do" method="post" id="midifyRoleForm">
         <input id="modify_id" name="id" type="hidden" /> 
  		<table>
  			<tbody>
  				<tr>
  					<th style="width:70px;">员工名称：</th>
  					<td>
	  					<select id="selectUser">
	  						
	  					</select>
  					</td>
  					<th style="width:70px;">工作时间：</th>
  					<td><input id="add_workTimeStr" name="workTimeStr" class="ipt-text" type="text"></td>
  				</tr>
  				<tr>
  					<th style="width:70px;">非工作时间：</th>
  					<td><input id="add_outTimeStr" name="outTimeStr" class="ipt-text" type="text"></td>
  					<th style="width:70px;">异常时间：</th>
  					<td><input id="add_illigalTimeStr" name="illigalTimeStr" class="ipt-text" type="text"></td>
  					<th style="width:70px;">加班时间：</th>
  					<td><input id="add_overTimeStr" name="overTimeStr" class="ipt-text" type="text"></td>
  				</tr>
  			</tbody>
  		</table>
      </form:form>
  		<div class="error"><span></span></div>
  </div>		 	  
  <!-- 新增弹窗DIV end -->
  <!-- 修改弹窗DIV start -->
  <div id="ModifyDiv" style="display:none;">
  	<div class="adminTable modal-table">
      <form:form action="/exportExcelController/updateUserMonthTime.do" method="post" id="midifyRoleForm">
         <input id="modify_id" name="id" type="hidden" /> 
  		<table>
  			<tbody>
  				<tr>
  					<th style="width:70px;">员工名称：</th>
  					<td><label id="modi_name"></label></td>
  					<th style="width:70px;">工作时间：</th>
  					<td><input id="mod_workTimeStr" name="workTimeStr" class="ipt-text" type="text"></td>
  				</tr>
  				<tr>
  					<th style="width:70px;">非工作时间：</th>
  					<td><input id="mod_outTimeStr" name="outTimeStr" class="ipt-text" type="text"></td>
  					<th style="width:70px;">异常时间：</th>
  					<td><input id="mod_illigalTimeStr" name="illigalTimeStr" class="ipt-text" type="text"></td>
  					<th style="width:70px;">加班时间：</th>
  					<td><input id="mod_overTimeStr" name="overTimeStr" class="ipt-text" type="text"></td>
  				</tr>
  			</tbody>
  		</table>
      </form:form>
  		<div class="error"><span></span></div>
  	</div>
  </div>		 	  
  <!-- 修改弹窗DIV end -->
  
  
  <script type="text/javascript">
  
  $(function(){
	  
	  var myDate = new Date();
	  var date = new Date();
	  date.setDate(1);
	  
	  var startTime = '${userMonthTime.startTime}';
	  var endTime = '${userMonthTime.endTime}';
	  if(startTime.length!=0)
	  $("#vStartDate").val(startTime);
	  else{
		  $("#vStartDate").val(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
		  $("#vEndDate").val(myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate());
	  }
	  if(endTime.length!=0)
	  $("#vEndDate").val(endTime);
	  
  })
  
  
    function schRecord(){
    	var username= $("#visitName").val();
    	var startTime   = $("#vStartDate").val();
    	var endTime= $("#vEndDate").val();
  	    window.location.href="/exportExcelController/findUserMonthTimeListByPage/0.do?userName="+username+"&startTime="+startTime+"&endTime="+endTime+"";
    }
   /*  function delVisitRecord(registId){
    	if(confirm("请确认是否删除？")){
  		  $.get("/visitorRegisterController/deleteVisitorRegisterByVisitorRegisterId/"+registId+".do",null,function(msg){
  			var json_str = eval("("+msg+")"); 
  			window.location.href = "/visitorRegisterController/findVisitorListByPage/0.do";
  		  });
    	  //window.location.href="/visitorRegisterController/deleteVisitorRegisterByVisitorRegisterId/"+registId+".do";
  	   }	
    }
    
    
    
    function modifyRoles(roleId){
    	$.post("/exportExcelController/findSingle.do",{roleId:roleId},function(msg){
            var json_str = eval("("+msg+")"); 
            var userMonthTime = json_str.userMonthTime;
            $("#modi_name").val(userMonthTime.userName);
            $("#modify_id").val(userMonthTime.id);
            $("#modify_id").val(userMonthTime.id);
            
           
            art.dialog({
            title:"修改",
            padding:'10px 15px',  //这是内补丁，默认为10px
            content: document.getElementById('roleModifyDiv'), 
            lock:true,   //这表示是否出现背景遮罩层
            opacity:0.5,  //这表示遮罩层的背景透明度，默认这个数值
            id:'newCla', //加了id可以防止多次弹出同一个窗口
            ok: function () {
                var deptname= $.trim($("#mod_roleName").val()); 
                var roleDesc= $.trim($("#mod_roleDesc").val());
                
                if(deptname.length==0){
                  $("#roleModifyDiv .error").css({"display":"block"});
                  $("#roleModifyDiv .error span").html("请输入要修改的部门名称！");
                  return false;
                }
                if(roleDesc.length==0){
                  $("#roleModifyDiv .error").css({"display":"block"});
                  $("#roleModifyDiv .error span").html("请选择员工所在上级部门！");
                  return false;
                }
                $("#modify_roleid").val(roleId);
                $("#midifyRoleForm").submit();
                
                },
                cancelVal:'取消',
                cancel:true
              });
            
            });
    }
     */
    
    function exportRecord(){
    		var username= $("#visitName").val();
        	var startTime   = $("#vStartDate").val();
        	var endTime= $("#vEndDate").val();
    	window.location.href = "/exportExcelController/exportExcel.do?userName="+username+"&startTime="+startTime+"&endTime="+endTime+"";
    }
  </script>
</body>
</html>
