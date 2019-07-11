<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
var treeObj;
$(function(){
	loadAddzhibiaoTree("ZhiBiaoTree");
});
//加载组织结构树
function loadAddzhibiaoTree(id){
	$.ajax({
		type : 'POST',
		url:"<%=basePath%>/backstage/zhibiao/getZhiBiaoTree",
		type : "POST",
		dataType : "json",
		success : function(data) {
						$.fn.zTree.init($("#"+id), {
							view : {
								selectedMulti : false,
								fontCss : {
									color : "#393939"
								}
							},
// 							check: {
// 								enable: true,
// 								chkStyle: "checkbox"
// 								chkboxType: { "Y": "ps", "N": "ps" }
// 							},
							data : {
								simpleData : {
									enable : true
								}
							},
							callback : {
								onClick : clickZhiBiaoForm,
								beforeClick : zTreeBeforeClick
							}
						}, data.obj.list);
					    treeObj = $.fn.zTree.getZTreeObj(id);
						var nodes = treeObj.getNodes();
						if("${zhibiao_dept_id}"!=null&&"${zhibiao_dept_id}"!=""){
							var node1 = treeObj.getNodeByParam("id","${zhibiao_dept_id}");
							treeObj.selectNode(node1);
						}else{
							treeObj.expandNode(nodes[0],true);
						}
					}
				});
	}
function clickZhiBiaoForm(event,treeId,treeNode){
	var id = treeNode.id;
	$("#zhibiao_dept_id").val(id);
}

function zTreeBeforeClick(treeId, treeNode, clickFlag) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	var sNodes = treeObj.getNodes();
};
</script>
<div class="pageContent">
		<form action="<%=basePath%>backstage/zhibiao/saveTree" id="hospitalform"  class="pageForm form-validate"  method="post" callback="dialogAjaxDone" noEnter >
		    <input type="hidden" name="ids" id="ids" value="${ids}"/>
		    <input type="hidden" name="zhibiao_dept_id" id="zhibiao_dept_id" value="${zhibiao_dept_id}"/>
			<table class="table" width="100%" layoutH="38">  
				<tbody>
					<tr>
						<td align="center" sytle="border-top-width:0px;width:70px" >选择科室</td>
						<td style="border-top-width:0px;width:100px" colspan="3">
							<div class="panel panel-default" id="orgForm_treeDiv" style="margin-left: 0px;; margin-right: 6px; padding: 10px; margin-bottom: 0px; height: 160px; overflow: auto;">
									<ul id="ZhiBiaoTree" class="ztree authorityTree"></ul>
							</div>
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