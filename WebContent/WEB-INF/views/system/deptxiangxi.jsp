<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" src="static/js/echarts.min.js"></script>
<script type="text/javascript">
</script>


<div class="pageContent">
	<div style="overflow-x: auto; overfolw-y: hidden" layoutH="10">
	<table class="table table-bordered table-hover table-striped table-top" >
	<thead >
	<tr >
	<td colspan="2" class="center" style="font-size: 15px;">${model.name}</td>
	</tr>
	<tr >
	<td class="center"><strong>科室</strong></td>
	<td class="center"><strong>未提交人员</strong></td>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${list_zbk}" var="l" varStatus="stat">
	<tr>
	<td rowspan="${l.list_s.size()}" class="center"><strong>${l.use_dept_name}</strong></td>
	<c:forEach items="${l.list_s}" var="s" varStatus="stat" end="0">
	<td class="center">${s.xm}</td>
	</c:forEach>
	</tr>
	<c:forEach items="${l.list_s}" var="s" varStatus="stat" begin="1">
	<tr>
	<td class="center">${s.xm}</td>
	</tr>
	</c:forEach>
	</c:forEach>
	</tbody>
	</table>
	</div>
	</div>