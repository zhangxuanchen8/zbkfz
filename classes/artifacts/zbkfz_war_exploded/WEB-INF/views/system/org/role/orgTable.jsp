<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function deptadd(){
	var ids = $("#deptid").val();
	if(ids==null||""==ids){
		alertMsg.error("请先选择医院节点");
		return;
	}
	$("#adddeptfrom").click();
}
</script>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return divSearch(this, 'deptListRef');" action="<%=basePath %>/backstage/org/role/orgindex?relFlag=Y"  method="POST">
        <input type="hidden" name="test" value="test">
        <input type="hidden" id="deptid" name="deptid" value="${deptid}">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input id="numPerPage" type="hidden" name="numPerPage" value="${numPerPage}">
        <div class="searchBar">
            <ul class="searchContent">
                <li><label>名称</label><input type="text" value="${code}" id="org_code" name="code" class="form-control" size="10" /></li>
                <li><button id="orgsubmit" type="submit" class="btn btn-default btn-sm">查询</button></li>
                <li><a class="btn btn-orange btn-sm" href="javascript:navTab.reload('', {clearQuery:true});">清空查询</a></li>
                <li class="pull-right">
                    <div class="btn-group">
                        <a href="<%=basePath%>/backstage/org/role/editdeptfrom?pid=${deptid}" id="adddeptfrom" class="btn btn-default btn-sm" target="dialog" mask="true" width="800" height="550" style="display:none;">新增</a>     
                         <a class="btn btn-default btn-sm" onclick="deptadd();return false" style="margin-right:15px;">新增</a>
                        <a href="<%=basePath%>/backstage/org/role/editdeptfrom" class="btn btn-green btn-sm" target="selectOne" rel="ids" mask="true" width="800" height="550" style="margin-right:15px;">编辑</a>
				    	<a href="<%=basePath%>/backstage/org/role/delOrg" postType="string" rel="ids" class="btn btn-red btn-sm" target="selectedTodo" title="确定要删除这些信息吗？">删除</a>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>
<div class="pageContent">
    <div style="width:100%;overflow-x:auto;overfolw-y:hidden">
    <table class="table table-bordered table-hover table-striped table-top" rel="deptListRef" layoutH="70" id="depttable">
        <thead>
			<tr>
				<th class="center" width="28"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
			    <th class="center">序号</th>
				<th class="center">科室名称</th>
				<th class="center">考勤科室</th>
				<th class="center">上级科室</th>
				<th class="center">科室电话</th>
				<th class="center">是否末级科室</th>
				
			</tr>
		</thead>
        <tbody>
        <c:forEach items="${deptlist}" var="l" varStatus="stat">
        <tr  ondblclick="showorgform('${l.getId()}_org')">
				<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.getId()}"></td>
            	<td class="center">${ stat.index + 1+(pageNum-1)*numPerPage}</td>
				<td class="center">${l.name}</td>
				<td class="center">${l.kq_dept}</td>
				<td class="center">${l.pName}</td>
				<td class="center">${l.tel}</td>
				<td class="center">
					<c:if test="${l.isleaf=='Y' }">
						<span class="badge">是</span>
					</c:if>
				</td>
				<td hidden="hidden">
				<a hidden="hidden" id="${l.getId()}_org"  href="<%=basePath%>backstage/org/role/editdeptfrom?id=${l.getId()}&pid=${deptid}"  target="dialog" rel="ids" mask="true" width="800" height="550">编辑</a>
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
                <select id="dept_changenumPerPage" class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({pageNum:1,numPerPage:this.value},'deptListRef')">
                    <option value="20" <c:if test="${numPerPage == '20' }">selected='selected'</c:if>>20</option>
                    <option value="40" <c:if test="${numPerPage == '40' }">selected='selected'</c:if>>40</option>
                    <option value="60" <c:if test="${numPerPage == '60' }">selected='selected'</c:if>>60</option>
                    <option value="100" <c:if test="${numPerPage == '100' }">selected='selected'</c:if>>100</option>
                     <option value="10000" <c:if test="${numPerPage == '10000' }">selected='selected'</c:if>>10000</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount}条</span>
        </div>
        <div class="pagination-box" targettype="navTab" totalcount="${totalcount}" rel="deptListRef" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}">
        </div>
    </div>
</div>