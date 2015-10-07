<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'vistorlist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function submit(){
			
		 
			windos.location.href="";
			winodws.open("path","_top");
			
		}
	</script>

  </head>
  
  <body>
    	testaaa <a href="/visitorController/getAllVistor/0.do">test</a>
    
           传参数<a href="/visitorController/getAllVistor/0.do?name=邹俊&phoneNum=13824315349">传参数</a>
           
     <form action="/visitorController/getAllVistor/0.do" method="post">
     		<table>
     			<tr>
     				<td>姓名</td>
     				<td><input type="text" name="name"><</td>
     			</tr>
     			<input type="button" value="提交" onclick="submit()">
     			<input type="submit" nclick="return submit()">
     		</table>
     </form>      
  </body>
</html>
