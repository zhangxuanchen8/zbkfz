<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style type="text/css">
.menuformp{margin-bottom:10px}
</style>
<script type="text/javascript">

</script>
<form action="<%=basePath %>backstage/sysmenu/addnew" id="add_deptinfo"
	class="pageForm form-validate" method="post" callback="dialogAjaxDone"
	noEnter>
<div style="border:1px solid #000">
	<div class="pageFormContent" layouth="30">
		<p calss="menuformp" style="margin-bottom:15px">
			<label style="width:66px">编号:</label> <input type="text" name="id"
				
				value="${id }"
				class="form-control  required textInput validate[required]" size="17"
				 readonly="readonly" style="width: 100px;">
		</p>
		<p calss="menuformp" style="margin-bottom:15px">
			<label style="width:66px">父节点:</label><input type="text" name="pId"
				
				value="${pId }"
				class="form-control  required textInput validate[required]" size="17"
				 readonly="readonly" style="width: 100px;">
		</p>

		<p>
			<label style="width:66px">名称:</label> <input type="text" name="name"
				
				value="${name }"
				class="form-control  required textInput validate[required]" size="17"
				 style="width: 100px;">
		</p>
		<p calss="menuformp" style="margin-bottom:15px">
			<label style="width:66px">索引:</label> <input type="text" name="index_no"
				id="j_ztree_menus2" class="form-control  validate[required]" size="17"
				 value="${index_no}" >
		</p>
		<div>
		<p calss="menuformp" style="margin-bottom:15px;width:480px">
			<label style="width:66px">链接:</label>
						<input type="text" name="url"
				id="j_ztree_menus1" class="form-control  " size="40"
				value="${url }" >
		</p>
		</div>



	</div>
	<div class="formBar">
		<ul>
			<li><button type="submit" class="btn btn-default btn-sm">保存</button></li>
			<li><button type="button" class="btn btn-close btn-sm">关闭</button></li>
		</ul>
	</div>
	</div>
</form>
