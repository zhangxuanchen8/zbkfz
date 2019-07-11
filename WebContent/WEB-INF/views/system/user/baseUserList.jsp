
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<style>
div.grid{border-left: 0px;border-right: 0px;}
</style>

<script type="text/javascript">
var index=0;
$(document).ready(function(){
	var ss = $("#navTab_content").css("height");
	
	$("#ztree_userList").css("height",ss-10);
	loadorgtree_baseUser();
});
$(window).resize(function(){
	var ss = $("#navTab_content").css("height");
	
	$("#ztree_userList").css("height",ss);
});
function loadorgtree_baseUser() {
	$.ajax({
		async:false,
		cache:false,
		url:"<%=basePath%>backstage/org/role/getOrgTree_dwz",
		type : "post",
		//datatype : "json",
		success : function(data) {
			$.fn.zTree.init($("#ztree_userList"), {
				view : {
					selectedMulti : false,
					fontCss : {
						color : "#393939"
					}
				},
				data : {
					simpleData : {
						enable : true
					}
				},
				callback : {
					onClick : getPid_baseUser
				}
			},  eval('(' + data + ')') ); 
			
				var zTree = $.fn.zTree.getZTreeObj("ztree_userList");
				var nodes = zTree.getNodes();
				if (nodes.length>0) 
				{
			       zTree.selectNode(nodes[0]);
			        zTree.setting.callback.onClick(null, zTree.setting.treeId, nodes[0]);
			       
				}
				zTree.expandNode(nodes[0],true);
			if("${dept_baseUser}"!=null&&"${dept_baseUser}"!=""){
				var node = zTree.getNodeByParam("id", "${thedept_baseUser}");
				zTree.selectNode(node);
				
				
			}
		},
		error:function(data){
			alert("加载科室失败！");
		}
	});
}
	function getPid_baseUser(event, treeId, treeNode) {
		var ids = new Array();
		getChildren(ids, treeNode)
		allLastLoad = ids.toString();
		$("#thedeptname_baseUser").val(treeNode.name);
		$("#thedept_baseUser").val(treeNode.id);
		$("#dept_baseUser").val(allLastLoad);
		$("#bas_addReload").attr("href","<%=basePath%>backstage/baseUser/baseUserIndexN?depts="+allLastLoad+"&thedept="+treeNode.id+"&deptname="+encodeURI(treeNode.name));
		$("#bas_addReload").click();
	}
	function getChildren(ids, treeNode) {
		ids.push(treeNode.id);
		if (treeNode.isParent) {
			for ( var obj in treeNode.children) {
				getChildren(ids, treeNode.children[obj]);
			}
		}
		return ids;
	}
</script>
<div class=" panel panel-default"  style="padding:0px;overflow: auto;width:16%;float: left;" layoutH="0" >
               <a href="<%=basePath%>backstage/baseUser/baseUserIndexN?" id="bas_addReload" style="display: none;" target="ajax"   rel="userAddForm">载入</a>
                        <ul id="ztree_userList" class="ztree" style="width: 400px;">
                        </ul>
</div>
<div  class="col-md-10 " style="padding:0px; " id="userAddForm">
	<jsp:include page="baseUserTable.jsp" flush="true"/>
</div>





