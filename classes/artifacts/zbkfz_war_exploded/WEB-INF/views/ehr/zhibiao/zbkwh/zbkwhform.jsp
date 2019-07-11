<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
</script>
<div class="pageContent">
<form action="<%=basePath%>backstage/zbkwh/save"   class="pageForm form-validate"  method="post" callback="dialogAjaxDone" noEnter >
<input hidden="hidden" name="id" value="${zbkwh.id}">
<table class="table " width="100%"layoutH="30">
<tbody>
<tr>
<td align="center" style="border-top-width: 0px"width="">类型：</td>
<td style="border-top-width: 0px" width="100px" colspan="3">
		<input type="radio" name="category" value="医师" <c:if test="${zbkwh.category eq '医师'}"> checked="checked"</c:if>>医师</input>
		<input type="radio" name="category" value="药师"<c:if test="${zbkwh.category eq '药师'}"> checked="checked"</c:if>>药师</input>
		<input type="radio" name="category" value="护理" <c:if test="${zbkwh.category eq '护理'}"> checked="checked"</c:if>>护理</input>
		<input type="radio" name="category" value="技师"<c:if test="${zbkwh.category eq '技师'}"> checked="checked"</c:if>>技师</input>
		<input type="radio" name="category" value="通用" <c:if test="${zbkwh.category eq '通用'}"> checked="checked"</c:if>>通用</input>
</td>
</tr>
<tr>
	<td align="center" style="border-top-width: 0px"width="">一级分类：</td>
	<td style="border-top-width: 0px" >
	<input type="text"  name="yjfl" class="form-control validate[required] required" style="width:  200px;" value="${zbkwh.yjfl}"></td>
	<td align="center" style="border-top-width: 0px"width="">二级分类：</td>
	<td style="border-top-width: 0px" >
	<input type="text"  name="ejfl" class="form-control " style="width:  200px;" value="${zbkwh.ejfl}"></td>
</tr>
	<tr>
   	<td align="center" style="border-top-width: 0px" width="100px">指标名称：</td>
	<td style="border-top-width: 0px" >
	<input type="text"  name="zbkmc" class="form-control validate[required] required" style="width: 200px;" value="${zbkwh.zbkmc }"></td>
   	<td align="center" style="border-top-width: 0px"width="100px">参考分值：</td>
	<td style="border-top-width: 0px " >
	<input type="text"  name="score" class="form-control validate[required] required" style="width: 200px;" value="${zbkwh.score }">
 	</tr>
 	<tr>
	
</tr>
<tr>
	<td align="center" style="border-top-width: 0px"width="70px">单项说明：</td>
	<td style="border-top-width: 0px" width="100px" >
	<input type="text"  name="item_desc" class="form-control " style="width: 200px;" value="${zbkwh.item_desc }">
	<%-- 	<textarea  name="item_desc" class="form-control" rows="3" cols="45" >${zbkwh.item_desc}</textarea> --%>
		</td>
	</tr> 
</tbody>
</table>
		<div class="formBar">
				<ul>
					<li><button type="submit" class="btn btn-default btn-sm">保存</button></li>
					<li><button type="button" class="btn btn-close btn-sm">关闭</button></li>
				</ul>
		</div>
</form>
</div>