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
	loadTree();
	 var hospital_ztreess = $("#navTab_content").css("height");
	 $("#hospital_ztree").css("height",hospital_ztreess-10);
});
$(window).resize(function(){
	var hospital_ztreess = $("#navTab_content").css("height");
	$("#hospital_ztree").css("height",hospital_ztreess);
});
function loadTree() {
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/org/hospital/getHospitalTree",
	type : "POST",
	dataType : "json",
	success : function(data) {
		treeNodes = data;
	zTree = $.fn.zTree.init($("#hospital_ztree"), {
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
						onClick : zTreeOnClick
					}
				}, treeNodes);
				var node = zTree.getNodeByParam("id", $("#supunit").val());
				zTree.selectNode(node);
				zTree.expandAll(true);
			}
		});
	}

	function zTreeOnClick(event, treeId, treeNode) {
		$("#code").val("");
		$("#supunit").val(treeNode.id);
		$("#parentname").val(treeNode.name);
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
	function showhospitalinfo(str){
		$('#'+str).click();
	}
	function hospitaladd(){
		var ids = $("#supunit").val();
		if(ids==null||""==ids){
			alertMsg.error("请先选择节点");
			return;
		}
		$("#hospitaladdform").click();
	}
</script>
<div class="col-md-2 panel panel-default" style="padding:0px;height:100%;">
	<div style="float: left; width: 100%; overflow: auto;" layoutH="0">
		<ul id="hospital_ztree" class="ztree" attrs = '{"onClick":"zTreeOnClick","expandAll":true}' nodes=''style="width: 400px;">
		</ul>
	</div>
</div>
<div class="col-md-10 " style="padding:0px; ">
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath %>/backstage/org/hospital/index"  method="POST">
        <input type="hidden" name="test" value="test">
        <input type="hidden" id="supunit" name="supunit" value="${supunit}">
        <input type="hidden" id="parentname" name="parentname" value="${parentname}">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input id="numPerPage" type="hidden" name="numPerPage" value="${numPerPage}">
        <div class="searchBar">
            <ul class="searchContent">
                <li><label>名称</label><input type="text" value="${code}" id="code" name="code" class="form-control" size="10" /></li>
                <li><button id="orgsubmit" type="submit" class="btn btn-default btn-sm">查询</button></li>
                <li><a class="btn btn-orange btn-sm" onclick="cleanquery()">清空查询</a></li>
                <li class="pull-right">
                    <div class="btn-group">
                        <a id="hospitaladdform" style="display:none;" href="<%=basePath%>/backstage/org/hospital/addhospitalform?supunit=${supunit}&showtype=1" class="btn btn-default btn-sm" target="dialog" mask="true" width="650" height="375" style="margin-right:15px;">新增</a>
                        <a class="btn btn-default btn-sm" onclick="hospitaladd();return false" style="margin-right:15px;">新增</a>          
                        <a href="<%=basePath%>/backstage/org/hospital/edithospitalfrom" class="btn btn-green btn-sm" target="selectOne" rel="ids" mask="true" width="650" height="375" style="margin-right:15px;display: none;">编辑</a>
				    	<a href="<%=basePath%>/backstage/org/hospital/delBasHospitals" postType="string" rel="ids" class="btn btn-red btn-sm" target="selectedTodo" title="确定要删除这些信息吗？">删除</a>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>
<div class="pageContent">
    <div style="width:100%;overflow-x:auto;overfolw-y:hidden">
    <table class="table table-bordered table-hover table-striped table-top"  layoutH="70" id="hospitaltable">
        <thead>
			<tr>
				<th class="center" width="28"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
			    <th class="center">序号</th>
				<th class="center">医院编码</th>
				<th class="center">名称</th>
				<th class="center">类型</th>
				<th class="center">电话</th>
				<th class="center">地址</th>
			</tr>
		</thead>
        <tbody>
        <c:forEach items="${hospitallist}" var="l" varStatus="stat">
        <tr  ondblclick="showhospitalinfo('${stat.index}_hospital')">
			<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.hosnum};${l.hosnum}"></td>
            	<td class="center">${ stat.index + 1+(pageNum-1)*numPerPage}</td>
				<td class="center">${l.hosnum}</td>
				<td class="center">${l.hosname}</td>
				<td class="center">${l.nodetype}</td>
				<td class="center">${l.tel}</td>
				<td class="center">${l.address}</td>
				<td hidden="hidden">
				<a hidden="hidden" id="${stat.index}_hospital"  href="backstage/org/hospital/edithospitalfrom?hosnum=${l.hosnum}"  target="dialog" rel="ids" mask="true" width="650" height="375">编辑</a>
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
                    <option value="10000" <c:if test="${numPerPage == '10000' }">selected='selected'</c:if>>10000</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount}条</span>
        </div>
        <div class="pagination-box" targettype="navTab" totalcount="${totalcount}" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}">
        </div>
    </div>
</div>
</div>