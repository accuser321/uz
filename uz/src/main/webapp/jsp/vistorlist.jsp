<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>My JSP 'vistorlist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
    	总数
    	${page.totalNumber}
    	${list}
    	<div align="center">
    		<table border="1px">
    		<thead>
    			<th>名称</th>
    			<th>电话</th>
    			<th>操作</th>
    		</thead>
    		<tbody>
    		<c:forEach items="${list}" var="test">
    			<tr>
    			<td>
    				${test.name }
    			</td>
    			<td>
    				${test.phoneNum }
    			</td>
    			<td>
    				<a href="/visitorController/dele/${test.visitorId }">删除</a>  
    				<a href="/visitorController/modify/${test.visitorId }">修改</a>  ${test.visitorId }
    			</td>
    		</c:forEach>
    		</tbody>	
    		</table>
    	</div>
    	<jsp:include page="page.jsp" flush="true">
			<jsp:param value="/visitorController/getAllVistor" name="url" />
			<jsp:param value="name=${visitor.name }&phoneNum=${visitor.phoneNum }" name="param"/>
		</jsp:include>
		
  </body>
 
</html>
