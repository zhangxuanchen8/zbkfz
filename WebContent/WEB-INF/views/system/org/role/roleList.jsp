<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
function add(){
	$("#add").click();
}

function showroleform(str){
	$('#'+str).click();
}

function delBatch(){
	var ids =[];  
	$('input[name="ids"]:checked').each(function(){ids.push($(this).val());});     
	if(ids.length==0) {alert("未选中"); return false;}
	$.ajax({
		url:'backstage/org/role/delrole',
	    type:'POST', 
	 
	    data:{
	    	ids:ids.toString()
	    },
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    success:function(data){
	    	alert("删除成功");
	    }
	});
	
}

</script>
<div style="float: left; width:100%; margin-left:5px">
<div class="pageHeader">
    <form id="pagerForm" action="<%=basePath %>backstage/org/role/index" onsubmit="return navTabSearch(this);" method="POST">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input id="employeelist_numPerPage" type="hidden" name="numPerPage" value="${numPerPage}">
        <div class="searchBar">
            <ul class="searchContent">
                <li><label>角色姓名：</label><input type="text" value="${name}" name="name" class="form-control" size="10" /></li>
                <!--<li><label>客户名称：</label><input type="text" value="" name="name" class="form-control" size="8" /></li>-->
                <li><label>状态:</label>
                </li>
                <li><button type="submit" class="btn btn-default btn-sm">查询</button></li>
                <li><a class="btn btn-orange btn-sm" href="javascript:navTab.reload('', {clearQuery:true});">清空查询</a></li>
                <li class="pull-right">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default btn-sm " style="margin-right:15px;" onclick="add();">新增</button>
                        <span style="display:none"><a id='add' href='backstage/org/role/addForm' class='btn btn-green btn-sm' target='dialog' width='530' height='600' mask='true'>新增</a></span>
                        <a href="<%=basePath%>backstage/org/role/addForm" class="btn btn-green btn-sm" target="selectOne" rel="ids" mask="true" width="530" height="600" style="margin-right:15px;">编辑</a>
				    	<a href="<%=basePath%>/backstage/org/role/delrole" postType="string" rel="ids" class="btn btn-red btn-sm" target="selectedTodo" title="确定要删除这些信息吗？">删除</a>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>

<div class="pageContent" >
	<div style="width:100%;overflow-x:auto;overfolw-y:hidden">
    <table class="table table-bordered table-hover table-striped table-top"  width="100%" layoutH="70">
        <thead>
            <tr>
				<th class="center" width="28"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
                <th orderfield="" class="center" >代码</th>
				<th orderfield="" class="center" >角色名称</th>
				<th orderfield="" class="center" >级别</th>
				<th orderfield="" class="center" >排序号</th>
				<th orderfield="" class="center" >备注</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${role_listlist}" var="l" varStatus="stat">
            	<tr id="${l.id}"  ondblclick="showroleform('${l.getId()}_role')">
	 				<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.id}"></td>
	 				<td class="center">${ l.code}</td>
					<td class="center">${l.name}</td>
					<td class="center">
						<c:if test="${l.lvl == '1'}">系统级</c:if>
						<c:if test="${l.lvl == '2'}">院区级</c:if>
						<c:if test="${l.lvl == '3'}">科室级</c:if>
					</td>
					<td class="center">${l.index_no}</td>
					<td class="center">${l.remark}</td>
					<td hidden="hidden">
					<a hidden="hidden" id="${l.getId()}_role"  href="<%=basePath%>/backstage/org/role/addForm?id=${l.getId()}"  target="dialog" rel="ids" mask="true" width="530" height="600">编辑</a>
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
                <select class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                    <option value="30" <c:if test="${numPerPage == '30' }">selected='selected'</c:if>>30</option>
                    <option value="60" <c:if test="${numPerPage == '60' }">selected='selected'</c:if>>60</option>
                    <option value="120" <c:if test="${numPerPage == '120' }">selected='selected'</c:if>>120</option>
                    <option value="150" <c:if test="${numPerPage == '150' }">selected='selected'</c:if>>150</option>
                    <option value="10000" <c:if test="${numPerPage == '10000' }">selected='selected'</c:if>>10000</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount } 条</span>
        </div>
        <div class="pagination-box" targettype="navTab"  totalcount="${totalcount }" numperpage="${numPerPage }" pagenumshown="10" currentpage="${pageNum}"  >
        </div>
    </div>   
</div>
</div>
