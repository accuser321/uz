<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object roleID = request.getParameter("roleid");
Object roleName = request.getParameter("roleName");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTDHTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>权限绑定</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/layout.css">
	<link rel="stylesheet" href="css/ztree/zTreeStyle.css">
	<script type="text/javascript" src="<%=basePath%>/script/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/script/ztree/jquery.ztree.core-3.5.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/script/ztree/jquery.ztree.excheck-3.5.min.js"></script>
   <script type="text/javascript" src="<%=basePath%>/script/common.js"></script> 
  </head>
  
  <body style="min-width:1123px;">
   <div class="power-main">
     <table width="100%">
     	<tr>
     	  <th width="79">角色名称：</th>
     	  <td><input id="roName" class="role-text" type="text" readonly></td>
     	</tr>
     </table>
     <div class="fieldsetBox">
       <fieldset>
     	  <legend>权限绑定</legend>
     	  <input id="chk01" name="chkbox" type="checkbox" value="1"><label for="chk01">员工信息管理</label>
     	  <input id="chk02" name="chkbox" type="checkbox" value="2"><label for="chk02">登记信息管理</label>
     	  <input id="chk03" name="chkbox" type="checkbox" value="3"><label for="chk03">访问记录管理</label>
     	  <input id="chk04" name="chkbox" type="checkbox" value="4"><label for="chk04">预约信息管理</label>
     	 <!--  <input id="chk05" name="chkbox" type="checkbox" value="5"><label for="chk05">管理员信息</label> -->
     	  <input id="chk06" name="chkbox" type="checkbox" value="5"><label for="chk06">角色管理</label></br>
     	  <input id="chk07" name="chkbox" type="checkbox" value="6"><label for="chk07">部门管理</label>
     	  <input id="chk08" name="chkbox" type="checkbox" value="7"><label for="chk08">个人信息</label>
     	  <input id="chk09" name="chkbox" type="checkbox" value="8"><label for="chk08">员工考勤</label>
       </fieldset>
     </div>
     <div class="btns">
     	<a href="javascript:void(0);" onclick="powerSubmit();"><img src="images/btn_submit.jpg" alt=""></a>
     	<a href="<%=basePath%>sysRoleController/findSysRoleListByPage/0.do"><img src="images/btn_cancel.jpg" alt=""></a>
     </div>
   </div>
   <div class="power-tree">
     <div style="height:100%;padding:0; background:#e5f0ff;overflow:auto;">
	      <ul id="staffTree" class="ztree">
	      </ul>
	 </div>
   </div>   
   
   <div id="resuly" style="height: 100px;height: 50px">
   
   </div>	
</body>
<script type="text/javascript">
   var setting = {
		view: {
			showLine: false
		},
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true,
				idKey:"id",
				pIdKey:"pId",
				rootPId:0
			}
		},
		callback: {
			onCheck: zOnchek
  		}
	};

   function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("staffTree"),
		py = $("#py").attr("checked")? "p":"",
		sy = $("#sy").attr("checked")? "s":"",
		pn = $("#pn").attr("checked")? "p":"",
		sn = $("#sn").attr("checked")? "s":"",
		type = { "Y":py + sy, "N":pn + sn};
		zTree.setting.check.chkboxType = type;
		showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
	}
    
    var code;
	function showCode(str) {
		if (!code) code = $("#code");
		code.empty();
		code.append("<li>"+str+"</li>");
	}
	
	$(document).ready(function(){		
	   var roleid = '<%=roleID%>';
	   $.ajax({
 		url:"/sysRoleController/"+roleid+"/getRoleMenuTree.do",
 		type:"post",
		success:function(msg){
			var json_str = $.parseJSON(msg);
			var arrayResult=json_str.jsonTree;
			 $.fn.zTree.init($("#staffTree"), setting,arrayResult);
			 setCheck();
			 $("#py").bind("change", setCheck);
			 $("#sy").bind("change", setCheck);
			 $("#pn").bind("change", setCheck);
			 $("#sn").bind("change", setCheck);  
		 }
	   })
	   
	   $.ajax({
 		url:"sysRoleController/"+roleid+"/searchSingleById.do",
 		type:"post",
		success:function(msg){
			var json_str = $.parseJSON(msg);
			var arrayResult=json_str.singleRole;
			var name=arrayResult.roleName;
			$("#roName").val(name);
			
		 }
	   });
	   $.ajax({
		  url:"sysRoleController/"+roleid+"/searchSingleById.do",
		  type:"post",
		  cache:false,
		  success:function(msg){
			  var json_str = eval("("+msg+")");
			  var permissonResult=json_str.permisstionList;
			  var peiID="";
			  for(var i=0;i<permissonResult.length; i++){
				  peiID +=permissonResult[i].rightId+",";
			  }
			  var splitPeiID=peiID.split(",");
			  var chkobj=$("input[name='chkbox']");
			  for(var i = 0;i<chkobj.length;i++){
				  for(var j=0;j<splitPeiID.length; j++){
						if($(chkobj[i]).val()==splitPeiID[j]){
							$(chkobj[i]).prop("checked",true);
						}
					 }
			  }
		  }
		  
	   })
	   
	});
	
	function powerSubmit(){
	    var roleid = '<%=roleID%>';
	    //primisson权限ID
		var idArray=new Array(); 
		$("input[name='chkbox']:checked").each(function () {
		    idArray.push($(this).val());
		})
		var permissionStr=idArray.join(',');
		
		//用户ID
        var userId ='' ;
        var treeObj=$.fn.zTree.getZTreeObj("staffTree"),
        nodes=treeObj.getCheckedNodes(true);
        for(var i=0;i<nodes.length;i++){
        	userId += nodes[i].id+",";
        }
        if(permissionStr.length==0){
        	alert("请勾选需要绑定的权限类型！")
        	return false;
        }
		if(userId.length==0){
        	alert("请选择相应用户！");
        	return false;
        }
		 $.ajax({
		 	url:"/sysUserController/addBindRole.do",
		 	type:"post",
		 	cache:false,
		 	data:{permissionID:permissionStr,UserID:userId,roleID:roleid},
		 	success:function(msg){
				alert("绑定成功！");
				window.location.href="/sysRoleController/findSysRoleListByPage/0.do";
		 	}
		 })
		
	}
	
	function zOnchek(e,treeId,treeNode){
        var treeObj=$.fn.zTree.getZTreeObj("staffTree"),
        nodes=treeObj.getCheckedNodes(true);
        /* for(var i=0;i<nodes.length;i++){
        	userId += nodes[i].id+",";
        }
        */
    }
	
</script>
</html>

