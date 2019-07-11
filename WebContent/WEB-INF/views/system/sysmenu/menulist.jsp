<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<%-- <base href="<%=basePath%>"> --%>
<script type="text/javascript">
var zTree;
var i = 0;
$(function(){	
	loadOrgTree();
	$("#sysmenuTree").css("height",$("#navTab_content").css("height")-10);
});
$(window).resize(function(){
	var deptZtreeH = $("#navTab_content").css("height");
	$("#sysmenuTree").css("height",deptZtreeH);
});
function loadOrgTree() {
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/sysmenu/getmenutree",
	type : "POST",
	dataType:"json",
	success : function(data) {
		$.fn.zTree.init($("#sysmenuTree"), {
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
				onClick : clickTree
			}
		}, data); 
		var treeObj = $.fn.zTree.getZTreeObj("sysmenuTree");
		 var node = treeObj.getNodeByParam('id', 'root');
		 if("${pId}" == "" || "${pId}" == null){
		 	treeObj.expandNode(node, true, true, true,true);
		 	treeObj.selectNode(node);
		 	$("#menulistForm input[name$='pId']").val("root");
		}else{
			treeObj.expandNode(node, true, true, true,true);
			node = treeObj.getNodeByParam('id', "${id}");
			treeObj.selectNode(node);
			var addurl = "<%=basePath%>/backstage/sysmenu/sysmenuform";
			addurl+="?pId=${id}";
			$("#addsysmenu").attr("href",addurl);
		
		}
	}
});
}

function clickTree(event, treeId, treeNode) {
	$("#menulist_id").val(treeNode.id);
	$("#menulist_pid").val(treeNode.pId);
	var addurl = "backstage/sysmenu/sysmenuform";
	$("#addsysmenu").attr("href",addurl);
	$("#menuListReload").attr("href","<%=basePath%>backstage/sysmenu/sysmenuList?addUrl="+addurl+"&theId="+treeNode.id+"&thePid="+treeNode.pId+"&relFlag=Y");
	$("#menuListReload").click();
}

function cleanquery(){
	$("#menulist_name").val("");
	navTabPageBreak({pageNum:1,numPerPage:20});
}

function saveAll(){
	var $form = $("#formdict"); 
	$form.submit();
}
</script>
<div class="col-md-2 panel panel-default" style="padding:0px;height:100%;">
               <a href="" id="menuListReload" style="display: none;" target="ajax"   rel="menuref">载入</a>
                    <div style="float: left; width: 100%; overflow: auto;" layoutH="0" >
                        <ul id="sysmenuTree" class="ztree" attrs = '{"expandAll":true}' nodes=''style="width: 400px;">
                        </ul>
                    </div>
</div>
<div  class="col-md-10 " style="padding:0px; " id="menuref">
</div>
