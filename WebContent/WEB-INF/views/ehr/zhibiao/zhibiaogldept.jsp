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
	url:"<%=basePath%>backstage/model/getModelTree_withzbkdept",
	type : "POST",
	dataType:"json",
	success : function(data) {
		console.log(data);
		treeNodes = data;
		zTree = $.fn.zTree.init($("#zhibiaoTreedept"), {
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
		var treeObj = $.fn.zTree.getZTreeObj("zhibiaoTreedept");
		
		    var nodes = treeObj.getNodes();
		    treeObj.expandNode(nodes[0],true);
		}
	});
}

function clickTree(event, treeId, treeNode) {
	if(treeNode.id !='zxc'){
	$("#menuListReloaddept").attr("href","<%=basePath%>backstage/zhibiaogl/xddept?supunit="+treeNode.id+"&pid="+treeNode.pId+"&relFlag=Y&supunits="+treeNode.p_id);
	$("#menuListReloaddept").click();
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
               <a href="<%=basePath%>backstage/zhibiaogl/xddept" id="menuListReloaddept" style="display: none;" target="ajax"   rel="menurefdept">载入</a>
                    <div style="float: left; width: 100%; overflow: auto;" layoutH="0" >
                        <ul id="zhibiaoTreedept" class="ztree" attrs = '{"expandAll":true}' nodes=''style="width: 400px;">
                        </ul>
                    </div>
</div>
<div  class="col-md-10 " style="padding:0px;" id="menurefdept">
<jsp:include page="zhibiaogldepttable.jsp" flush="true"/>
</div>
