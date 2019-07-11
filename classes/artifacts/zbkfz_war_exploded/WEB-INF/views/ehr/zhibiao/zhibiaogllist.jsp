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
	url:"<%=basePath%>backstage/zhibiaogl/getpeopleTree1",
	type : "POST",
	dataType:"json",
	success : function(data) {
		console.log(data);
		treeNodes = data;
		zTree = $.fn.zTree.init($("#zhibiaoTree3"), {
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
		 var node = zTree.getNodeByParam('id', '0000');
		 zTree.selectNode(node);
		 	zTree.expandAll(true);
		 	zTree.setting.callback.onClick(null, zTree.setting.treeId, node);
		}
	});
}

function clickTree(event, treeId, treeNode) {
	$("#menulist_id3").val(treeNode.id);
	$("#menulist_pid3").val(treeNode.pId);
	//var addurl = "backstage/sysmenu/sysmenuform";
	//$("#addsysmenu").attr("href",addurl);
	$("#menuListReload3").attr("href","<%=basePath%>backstage/zhibiaogl/index?supunit="+treeNode.id+"&relFlag=Y");
	$("#menuListReload3").click();
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
<div class="col-md-2 panel panel-default" style="padding:0px;height:100%;width:30%;">
               <a href="" id="menuListReload3" style="display: none;" target="ajax"   rel="menuref3">载入</a>
                    <div style="float: left; width: 100%; overflow: auto;" layoutH="0" >
                        <ul id="zhibiaoTree3" class="ztree" attrs = '{"expandAll":true}' nodes=''style="width: 400px;">
                        </ul>
                    </div>
</div>
<div  class="col-md-10 " style="padding:0px;width:70%; " id="menuref3">
<jsp:include page="zhibiaoku2.jsp" flush="true"/>
</div>
