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
	$("#zhibiaoavgTree").css("height",$("#navTab_content").css("height")-10);
});
$(window).resize(function(){
	var deptZtreeH = $("#navTab_content").css("height");
	$("#zhibiaoavgTree").css("height",deptZtreeH);
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
		zTree = $.fn.zTree.init($("#zhibiaoavgTree"), {
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
		var treeObj = $.fn.zTree.getZTreeObj("zhibiaoavgTree");
		var nodes  = treeObj.getNodes();
			 var node = zTree.getNodeByParam("id", $("#supunit").val());
			 zTree.selectNode(node); 
		}
	});
}

function clickTree(event, treeId, treeNode) {
	$("#menulist_id").val(treeNode.id);
	$("#menulist_pid").val(treeNode.pId);
	$("#avgmenuList").attr("href","<%=basePath%>backstage/zhibiao/avgindex?itemid="+treeNode.id+"&itempid="+treeNode.pId+"&relFlag=Y");
	$("#avgmenuList").click();
	
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
               <a href="<%=basePath%>backstage/zhibiao/index" id="avgmenuList" style="display: none;" target="ajax"   rel="avgscore">载入</a>
                    <div style="float: left; width: 100%; overflow: auto;" layoutH="0" >
                        <ul id="zhibiaoavgTree" class="ztree" attrs = '{"expandAll":true}' nodes=''style="width: 400px;">
                        </ul>
                    </div>
</div>
<div  class="col-md-10 " style="padding:0px;width:80%; " id="avgscore">
<jsp:include page="zhibiaoavg.jsp" flush="true"/>
</div>
