<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
var zTree;      //树
var treeNodes;	//树的节点
$(function(){
	loadTree();
    var sysdict_ztreess = $("#navTab_content").css("height");
	$("#sysdict_ztree").css("height",sysdict_ztreess-10);
});
$(window).resize(function(){
	var sysdict_ztreess = $("#navTab_content").css("height");
	
	$("#sysdict_ztree").css("height",sysdict_ztreess);
});
function loadTree() {
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/sysDict/load_sysdict_tree",
	type : "POST",
	dataType : "json",
	success : function(data) {
		treeNodes = data;
	    zTree = $.fn.zTree.init($("#sysdict_ztree"),{view:{selectedMulti:false,fontCss:{color:"#393939"}},data:{simpleData:{enable:true}},callback:{onClick:sysdict_ztreeOnClick}},treeNodes);
		var node = zTree.getNodeByParam("name", $("#scope").val());
		zTree.selectNode(node);
		zTree.expandAll(true);
			if(node){
			node.click;
			}
		}
	});
}

function sysdict_ztreeOnClick(event, treeId, treeNode) {
	var scope = treeNode.name;
	if(treeNode.isParent){
		$("#type").val("1");
	}else{
		$("#type").val("2");
	}
	$("#code").val("");
	$("#scope").val(treeNode.name);
	$("#pageNum").val("1");
	$("#numPerPage").val("20");
	navTabPageBreak({
		pageNum : 1,
		numPerPage : 20
	});
}
function cleanquery() {
	$("#code").val("");
	navTabPageBreak({
		pageNum : 1,
		numPerPage : 20
	});
}
function showsysdictinfo(str){
	$('#'+str).click();
}
</script>

<div class="col-md-2 panel panel-default" style="padding:0px;height:100%;">
	<div style="float: left; width: 100%; overflow: auto;"  layoutH="0">
		<ul id="sysdict_ztree" class="ztree" attrs = '{"onClick":"sysdict_ztreeOnClick","expandAll":true}' nodes=''style="width: 400px;">
		</ul>
	</div>
</div>
<div class="col-md-10 " style="padding:0px; ">
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>backstage/sysDict/index"  method="POST">
        <input type="hidden" name="test" value="test">
        <input type="hidden" name="type" id="type" value="${type}">
        <input type="hidden" id="scope" name="scope" value="${scope}">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input id="numPerPage" type="hidden" name="numPerPage" value="${numPerPage}">
        <div class="searchBar">
            <ul class="searchContent">
                <li><label>名称搜索</label><input type="text" value="${code}" id="code" name="code" class="form-control" size="10" /></li>
                <li><button id="orgsubmit" type="submit" class="btn btn-default btn-sm">查询</button></li>
                <li><a class="btn btn-orange btn-sm" onclick="cleanquery()">清空查询</a></li>
                <li class="pull-right">
                    <div class="btn-group">
                        <a href="<%=basePath%>backstage/sysDict/addsysdictform?showtype=1" class="btn btn-default btn-sm" target="dialog" mask="true" width="521" height="350" style="margin-right:15px;">新增</a>          
                        <a href="<%=basePath%>backstage/sysDict/editsysdictform" class="btn btn-green btn-sm" target="selectOne" rel="ids" mask="true" width="521" height="350" style="margin-right:15px;">编辑</a>
				    	<a href="<%=basePath%>backstage/sysDict/delSysdict" postType="string" rel="ids" class="btn btn-red btn-sm" target="selectedTodo" title="确定要删除这些信息吗？">删除</a>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>
<div class="pageContent">
    <div style="width:100%;overflow-x:auto;overfolw-y:hidden">
    <table class="table table-bordered table-hover table-striped table-top"  layoutH="70" id="sysdicttable">
        <thead>
			<tr>
				<th class="center" width="28"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
				<th width="50" class="center">序号</th>
			    <th width="130" class="center">名称</th>
				<th width="120"  class="center">值</th>
				<th width="150" class="center">作用范围</th>
				<th class="center">作用描述</th>
			</tr>
		</thead>
        <tbody>
        <c:forEach items="${sysdictlist}" var="l" varStatus="stat">
        <tr  ondblclick="showsysdictinfo('${stat.index}_sysdict')">
				<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.parmid}"></td>
            	<td class="center">${ stat.index + 1+(pageNum-1)*numPerPage}</td>
				<td title="${l.parmname}"  class="center">${l.parmname}</td>
				<td title="${l.parmvalue}" class="center">${l.parmvalue}</td>
				<td title="${l.scope}" class="center">${l.scope}</td>
				<td>${l.descriptions}</td>
				<td hidden="hidden">
				<a hidden="hidden" id="${stat.index}_sysdict"  href="<%=basePath%>backstage/sysDict/editsysdictform?id=${l.parmid}"  target="dialog" rel="ids" mask="true" width="521" height="350">编辑</a>
				</td>
		</tr>
		</c:forEach>
        </tbody>
    </table>
    </div>
    <div class="panelBar">
        <div class="pages">
            <span>每页&nbsp;</span>
            <span class="sel">
                <select class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({pageNum:1,numPerPage:this.value})">
                    <option value="20" <c:if test="${numPerPage == '20' }">selected='selected'</c:if>>20</option>
                    <option value="40" <c:if test="${numPerPage == '40' }">selected='selected'</c:if>>40</option>
                    <option value="60" <c:if test="${numPerPage == '60' }">selected='selected'</c:if>>60</option>
                    <option value="100" <c:if test="${numPerPage == '100' }">selected='selected'</c:if>>100</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount}条</span>
        </div>
        <div class="pagination-box" targettype="navTab" totalcount="${totalcount}" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}">
        </div>
    </div>
</div>
</div>