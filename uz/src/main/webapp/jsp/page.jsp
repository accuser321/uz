<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "";
	String url = request.getParameter("url");
	String url_par = request.getParameter("param");
%>
<script type="text/javascript">
	function toPage(){
		var  targetPage = document.getElementById("pageNo").value;
		var basepath = "<%=basePath%><%=url%>";
		window.open(basepath+"/"+targetPage+".do","_self");
	}
</script>

<!-- <div class=" ">
	当前页/总页数  ${page.currentPage }/ ${page.totalNumber }
	<a href="<%=basePath%><%=url%>/1.do?<%=url_par%>" >首页</a>
	<a href="<%=basePath%><%=url%>/${page.currentPage-1}.do?<%=url_par%>" >上一页</a>
	<a href="<%=basePath%><%=url%>/${page.currentPage+1}.do?<%=url_par%>" >下一页</a>
	<a href="<%=basePath%><%=url%>/${page.totalNumber}.do?<%=url_par%>" >尾页</a>
	跳转到<input type="text" id="pageNo"> <input type="button" value="确定" onclick="toPage()">
</div> -->
<div class="page">
  	<!-- <a href="<%=basePath%><%=url%>/${page.currentPage-1}.do?<%=url_par%>" class="page-prev"></a> -->
  	<a class="page-go" href="<%=basePath%><%=url%>/1.do?<%=url_par%>" >首页</a>
	<a class="page-go" href="<%=basePath%><%=url%>/${page.currentPage-1}.do?<%=url_par%>" >上一页</a>
	<a class="page-go" href="<%=basePath%><%=url%>/${page.currentPage+1}.do?<%=url_par%>" >下一页</a>
	<a class="page-go" href="<%=basePath%><%=url%>/${page.totalNumber}.do?<%=url_par%>" >尾页</a>
  	<!-- <a href="<%=basePath%><%=url%>/${page.currentPage+1}.do?<%=url_par%>" class="page-next"></a>  -->
  	当前第 ${page.currentPage } 页，共   ${page.totalPage } 页，转到<input type="text" id="pageNo" class="ipt-text">页
  	<a class="page-go" onclick="toPage();">确定</a>
</div>
