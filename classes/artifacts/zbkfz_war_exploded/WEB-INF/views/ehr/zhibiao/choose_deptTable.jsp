<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
function showdeptform(str){
	$('#'+str).click();
}
</script>
<div style="float: left; width:100%; margin-left:5px">
<div class="pageHeader">
    <form id="pagerForm" action="<%=basePath %>backstage/choose_dept/index" onsubmit="return navTabSearch(this);" method="POST">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input id="choose_numPerPage" type="hidden" name="numPerPage" value="${numPerPage}">
        <div class="searchBar">
            <ul class="searchContent">
                <li><label>群组名称：</label><input type="text" value="${z_name}" name="z_name" class="form-control" size="10" /></li>
                <li><button type="submit" class="btn btn-default btn-sm">查询</button></li>
                <li><a class="btn btn-orange btn-sm" href="javascript:navTab.reload('', {clearQuery:true});">清空查询</a></li>
                <li class="pull-right">
                    <div class="btn-group">
                        <a href='<%=basePath%>/backstage/choose_dept/addForm' class='btn btn-green btn-sm' target='dialog' width='720' height='580' mask='true' style="margin-right: 15px">新增</a>
				    	<a href="<%=basePath%>/backstage/choose_dept/delDept" postType="string" rel="ids" class="btn btn-red btn-sm" target="selectedTodo" title="确定要删除这些信息吗？">删除</a>
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
				<th class="center" width="5%"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
				<th orderfield="" class="center" width="8%">排序号</th>
				<th orderfield="" class="center" width="32%">群组名称</th>
				<!-- <th orderfield="" class="center" width="20%">医院名称</th> -->
				<th orderfield="" class="center" width="10%">创建时间</th>
				<th orderfield="" class="center" width="45%">备注</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${dept_list}" var="l" varStatus="stat">
            	<tr id="${l.key_id}"  ondblclick="showdeptform('${l.getKey_id()}_role')">
	 				<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.key_id}"></td>
	 				<td class="center">${ l.index_no}</td>
					<td class="center">${l.z_name}</td>
					<%-- <td class="center" title = "${l.hosnum }">${l.hosname}</td> --%>
					<td class="center"><fmt:formatDate value="${l.create_time }" pattern="yyyy-MM-dd"/></td>
					<td class="center">${l.remark}</td>
					<td hidden="hidden">
					<a hidden="hidden" id="${l.getKey_id()}_role"  href="<%=basePath%>/backstage/choose_dept/addForm?key_id=${l.getKey_id()}&hosnum=${l.getHosnum()}"  target="dialog" rel="ids" mask="true" width="720" height="580">编辑</a>
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
