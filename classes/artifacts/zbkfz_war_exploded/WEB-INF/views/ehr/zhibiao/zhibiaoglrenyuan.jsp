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
	url:"<%=basePath%>backstage/model/getModelTree_withPersonalRecord?type=${type}",
	type : "POST",
	dataType:"json",
	success : function(data) {
		console.log(data);
		treeNodes = data;
		zTree = $.fn.zTree.init($("#zhibiaoTree4"), {
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
		 /*var node = zTree.getNodeByParam('id', '0000');
		 zTree.selectNode(node);*/
		 	zTree.expandAll(true);
		 	//zTree.setting.callback.onClick(null, zTree.setting.treeId, node);
		}
	});
}

function clickTree(event, treeId, treeNode) {
	$("#menulist_id").val(treeNode.id);
	$("#menulist_pid").val(treeNode.pId);
	//var addurl = "backstage/sysmenu/sysmenuform";
	//$("#addsysmenu").attr("href",addurl);
	
	<%-- $.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/zhibiaogl/getcppd?supunit="+treeNode.id,
	type : "POST",
	dataType:"json",
	success : function(data) {
		if(data=="0"){
			alert("请先将模板定稿！");
		return false;
		}else{
	$("#menuListReload4").attr("href","<%=basePath%>backstage/zhibiaogl/index1?supunit="+treeNode.id+"&relFlag=Y");
	$("#menuListReload4").click();
		}
	}
	}); --%>
	if(treeNode.id !='88888888' && treeNode.id !='zxc'){
		if(treeNode.pId!='88888888' ){
			$("#menuListReload4").attr("href","<%=basePath%>backstage/zhibiaogl/index1?supunit="+treeNode.id+"&ismodel="+treeNode.p_id+"&supunits="+treeNode.pId+"&relFlag=Y&type=${type}");
			$("#menuListReload4").click();
		}else{
			alertMsg.error("人员未分配模板");
			//alert("人员未分配模板");
		}
	}
	
	
	
}



//显示右键菜单
function showRMenu(type, x, y) {
	x=x/4;
	y=y/2;
    $("#rMenu ul").show();
    $("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"}); //设置右键菜单的位置、可见
    $("body").bind("mousedown", onBodyMouseDown);
}
//隐藏右键菜单
function hideRMenu() {
    if (rMenu) rMenu.css({"visibility": "hidden"}); //设置右键菜单不可见
    $("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
    	$("#rMenu").css({"visibility" : "hidden"});
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
function aaa(a){
	alert(a);
	debugger;
	var a = a;
	var id=document.getElementById("new_name").value;
	$.ajax({
		async:false,
		cache:false,
		url:"<%=basePath%>backstage/zhibiaogl/new_ryzbk?id="+id+"&a="+a,
		type : "POST",
		dataType:"json",
		success : function(data) {
			alert("更换成功请刷新树");
		}
		});
}
</script>

<style type="text/css">
 div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
    div#rMenu a{
        cursor: pointer;
        list-style: none outside none;
    }

</style>
<input type="hidden" id ="new_name" name="new_name" value="">
<div class="col-md-2 panel panel-default" style="padding:0px;height:100%;">
               <a href="" id="menuListReload4" style="display: none;" target="ajax"   rel="menuref4">载入</a>
                    <div style="float: left; width: 100%; overflow: auto;" layoutH="0" >
                        <ul id="zhibiaoTree4" class="ztree" attrs = '{"expandAll":true}' nodes=''style="width: 400px;">
                        </ul>
                    </div>
</div>

<div id="rMenu">
    <a href="javascript:void(0)" class="list-group-item" onclick="aaa('0000');">医师类</a>
    <a href="javascript:void(0)" class="list-group-item" onclick="aaa('0001');">药师类</a>
    <a href="javascript:void(0)" class="list-group-item" onclick="aaa('0002');">护理类</a>
    <a href="javascript:void(0)" class="list-group-item" onclick="aaa('0003');">药剂类</a>
</div>
<div  class="col-md-10 " style="padding:0px;" id="menuref4">
<jsp:include page="rytjym.jsp" flush="true"/>
</div>
