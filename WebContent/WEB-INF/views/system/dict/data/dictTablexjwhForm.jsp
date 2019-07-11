<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
loadselectcc("leibiexj","64","${dict.option01}");
</script>
<div class="pageContent">
<form action="<%=basePath%>/backstage/baseDict/dictxjwhadd"  class="pageForm form-validate"  method="post" callback="dialogAjaxDone" noEnter >
<input type="hidden" name="dictid"  value="${dict.dictid}"/>
<input type="hidden" name="nevalue"  value="${dict.nevalue}"/>
<table class="table table-condensed " width="100%" style="border-collapse: separate; border-spacing: 0px 5px;">
	<tbody>
	<tr>
	<td align="right" style="border-top-width: 0px;width: 100px">类别：</td>
	<td style="border-top-width: 0px">
	<select  name="option01" id="leibiexj" class="form-control validate[required] required" style="width:100px;">
	</select>
	</tr>
		<tr>
		<td align="right" style="border-top-width: 0px;">编制薪级：</td>
	<td style="border-top-width: 0px;" >
	<input type="text"   name="contents" class="form-control validate[required] required" style="width: 100px;" value="${dict.contents}"></td>
		</tr>
	<tr>
	<td align="right" style="border-top-width: 0px;">工资</td>
	<td style="border-top-width: 0px;" >
	<input type="text"  id="option02"  name="option02" class="form-control validate[required] required" style="width:  100px;" value="${dict.option02}">
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