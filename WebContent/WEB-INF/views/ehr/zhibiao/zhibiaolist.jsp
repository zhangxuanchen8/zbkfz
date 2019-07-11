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
	url:"<%=basePath%>backstage/model/getModelTree_withzbk",
	type : "POST",
	dataType:"json",
	success : function(data) {
		treeNodes = data;
		zTree = $.fn.zTree.init($("#zhibiaoTree"), {
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
		var treeObj = $.fn.zTree.getZTreeObj("zhibiaoTree");
		var nodes  = treeObj.getNodes();
		 
		// var ss =  $("#supunit").val();
		 //if(ss==null || ss==''){
		//	 treeObj.expandNode(nodes[0],true); 
		 //}else{
			 var node = zTree.getNodeByParam("id", $("#supunit").val());
			 zTree.selectNode(node); 
		// }
		}
	});
}

function clickTree(event, treeId, treeNode) {
	$("#menulist_id").val(treeNode.id);
	$("#menulist_pid").val(treeNode.pId);
	//var addurl = "backstage/sysmenu/sysmenuform";
	//$("#addsysmenu").attr("href",addurl);
	//console.log(treeNode.id);
	$("#menuListReload1").attr("href","<%=basePath%>backstage/zhibiao/index?supunit="+treeNode.id+"&supunits="+treeNode.pId+"&relFlag=Y&shangji=1");
	//$("#menuListReload1").click();
	$("#menuListReload1").click();
	
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
<div class="col-md-2 panel panel-default" style="padding:0px;height:100%;width:20%;">
               <a href="<%=basePath%>backstage/zhibiao/index" id="menuListReload1" style="display: none;" target="ajax"   rel="menuref1">载入</a>
                    <div style="float: left; width: 100%; overflow: auto;" layoutH="0" >
                        <ul id="zhibiaoTree" class="ztree" attrs = '{"expandAll":true}' nodes=''style="width: 400px;">
                        </ul>
                    </div>
</div>
<div  class="col-md-10 " style="padding:0px;width:80%; " id="menuref1">
<jsp:include page="zhibiaoku.jsp" flush="true"/>
</div>
