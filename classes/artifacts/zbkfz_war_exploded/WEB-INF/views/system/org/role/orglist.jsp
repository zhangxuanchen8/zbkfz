<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
var zTree;
var i = 0;
$(function(){
	
	$("#ztree_deptList").css("height",$("#navTab_content").css("height")-10);
	
	loadDeptTree();
	
});
$(window).resize(function(){
	$("#ztree_deptList").css("height",$("#navTab_content").css("height"));
});
function loadDeptTree() {
		$.ajax({
		async:false,
		cache:false,
		url:"<%=basePath%>backstage/org/role/getDeptTree",
		type : "POST",
		dataType : "json",
		success : function(data) {
			treeNodes = data.obj.list;
			zTree = $.fn.zTree.init($("#ztree_deptList"),{
				view:{
					selectedMulti:false,fontCss:{color:"#393939"}
				},
				data:{
					simpleData: {enable: true}
				},
				callback:{
					onClick:zTreeOnClick}
				},treeNodes);
				var treeObj = $.fn.zTree.getZTreeObj("ztree_deptList");
				var node = zTree.getNodeByParam("id", $("#deptid").val());
				treeObj.selectNode(node);
				if(node!=null){
					zTree.setting.callback.onClick(null,zTree.setting.treeId,node);
				}
				//zTree.setting.callback.onClick(null,zTree.setting.treeId,nodes[0]);
				treeObj.expandNode(node,true);
				}
		});
	}

function zTreeOnClick(event,treeId,treeNode) {	
	$("#deptid").val(treeNode.id);
	var code=$("#org_code").val();
	$("#deptListReload").attr("href","<%=basePath%>backstage/org/role/orgindex?thedept="+treeNode.id+"&thedeptname="+encodeURI(treeNode.name)+"&relFlag=Y&code="+code);
	$("#deptListReload").click();
	
}
function showorgform(str){
	$('#'+str).click();
}
</script>

<div class="col-md-2 panel panel-default" style="padding:0px;height:100%;">
               <a href="<%=basePath%>backstage/org/role/orgindex" id="deptListReload" style="display: none;" target="ajax"   rel="deptListRef">载入</a>
                    <div style="float: left; width: 100%; overflow: auto;"  layoutH="0">
                    <input type="hidden" id="deptid" name="deptid" value="${deptid}">
                        <ul id="ztree_deptList" class="ztree" attrs = '{"expandAll":true}' nodes=''style="width: 400px;">
                        </ul>
                    </div>
</div>

<div  class="col-md-10 " style="padding:0px; " id="deptListRef">
	<jsp:include page="orgTable.jsp" />
</div>