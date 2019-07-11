<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
</script>
<div class="pageContent">
		<form action="<%=basePath%>cpuser/fjdate?statu=${statu}"   class="pageForm form-validate"  method="post" callback="dialogAjaxDone" noEnter >
			<table class="table" width="100%" layoutH="38" style="margin-top:16px;">  
				<tbody>
					<tr>
						<td align="center" style="border-top:30px;width:150px;" >附件上传截止时间：</td>
						<td style="border-top-width:0px;width:100px">
<!-- 							<div class="panel panel-default" id="orgForm_treeDiv" style="margin-left: 0px;; margin-right: 6px; padding: 10px; margin-bottom: 0px; height: 160px; overflow: auto;"> -->
<!-- 									<ul id="ZhiBiaoTree" class="ztree authorityTree"></ul> -->
<!-- 							</div> -->
								<input type="text" name="sjdate" readonly='readonly' class="date form-control  validate[required] required" [pattern="yyyy-MM-dd"] [yearstart="-80"] [yearend="5"] />
						</td>
					</tr>
				</tbody>
			</table>
			<div class="formBar">
					<ul>
						<li>
						<button type="submit" class="btn btn-default btn-sm">保存</button>
						</li>
						<li><button type="button" class="btn btn-close btn-sm">关闭</button></li>
					</ul>
			</div>
		</form>
</div>