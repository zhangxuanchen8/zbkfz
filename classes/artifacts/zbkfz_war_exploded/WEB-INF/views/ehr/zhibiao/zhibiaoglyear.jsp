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
	loadOrgTree();
	$("#zhibiaoTree").css("height",$("#navTab_content").css("height")-10);
});
$(window).resize(function(){
	var deptZtreeH = $("#navTab_content").css("height");
	$("#zhibiaoTree").css("height",deptZtreeH);
});
function loadOrgTree() {
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/zhibiaogl/getyearTree",
	type : "POST",
	dataType:"json",
	success : function(data) {
		console.log(data);
		treeNodes = data;
		zTree = $.fn.zTree.init($("#zhibiaoTree5"), {
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
		}, treeNodes); 
		 var node = zTree.getNodeByParam('id', '0');
		 zTree.selectNode(node);
		 	zTree.expandAll(true);
		 	zTree.setting.callback.onClick(null, zTree.setting.treeId, node); 
		}
	});
}

function clickTree(event, treeId, treeNode) {
	$("#menulist_id").val(treeNode.id);
	$("#menulist_pid").val(treeNode.pId);
	if(treeNode.id !='0' && treeNode.pId !='0'){
	$("#menuListReload5").attr("href","<%=basePath%>backstage/zhibiaogl/index2?year="+treeNode.id+"&relFlag=Y&supunit="+treeNode.pId);
	$("#menuListReload5").click();
	}
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
               <a href="" id="menuListReload5" style="display: none;" target="ajax"   rel="menuref5">载入</a>
                    <div style="float: left; width: 100%; overflow: auto;" layoutH="0" >
                        <ul id="zhibiaoTree5" class="ztree" attrs = '{"expandAll":true}' nodes=''style="width: 400px;">
                        </ul>
                    </div>
</div>
<div  class="col-md-10 " style="padding:0px;" id="menuref5">
</div>
