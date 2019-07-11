<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function editMenu(menuid){
	
	$("#menuEditBt").attr("href","<%=basePath%>/backstage/sysmenu/sysmenuform?id="+menuid);
	$("#menuEditBt").click();
}
</script>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return divSearch(this, 'menuref');" action="<%=basePath %>/backstage/sysmenu/sysmenuList?relFlag=Y" method="post">
    	<input type="hidden" id ="menulist_pid" name="pId" value="${pId}">
    	<input type="hidden" id ="menulist_id" name="id" value="${id}">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
        <input type="hidden" name="menuAddUrl" value="${addUrl}">
        <%-- <input type="hidden" name="orderField" value="${param.orderField}">
        <input type="hidden" name="orderDirection" value="${param.orderDirection}">  --%>
        <div class="searchBar">
		        <ul class="searchContent">
                <li><label>名称:</label><input id="menulist_name" type="text" value="${name}" name="name" class="form-control" size="10" /></li>
                <li><button id="menusubmit" type="submit" class="btn btn-default btn-sm">查询</button></li>
                <li><a class="btn btn-orange btn-sm" href="javascript:navTab.reload('', {clearQuery:true});">清空查询</a></li>
                <li class="pull-right">
                    <div class="btn-group">
                        <a id="addsysmenu" href="<%=basePath%>${addUrl==null||addUrl==''?'backstage/sysmenu/sysmenuform?pId=root':addUrl}" class="btn btn-default btn-sm" target="dialog" mask="true" width="610" height="220" style="margin-right:15px;">新增</a>          
                        <a href="<%=basePath%>/backstage/sysmenu/sysmenuform" id="menuEditBt" mask="true" width="610" height="220" style="display: none" target="dialog">编辑</a>
				    	<a href="<%=basePath%>/backstage/sysmenu/delsysmenu" postType="string" rel="ids" class="btn btn-red btn-sm" target="selectedTodo" title="确定要删除这些信息吗？"  >删除</a>
                    </div>
                </li>
            </ul>
		</div>
    </form>
</div>
<div class="pageContent" >
	<div style="width:100%;overflow-x:auto;overfolw-y:auto">
		<table  class="table table-bordered table-hover table-striped table-top" layoutH="70" rel="menuref">
        <thead>
            <tr>

                <th class="center" width="28"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
                <th orderfield="" class="center" >编号</th>
				<th orderfield="" class="center" >名称</th>
				<th orderfield="" class="center" >链接</th>
				<th orderfield="" class="center" >索引</th>

            </tr>
        </thead>
        <tbody>
            <c:forEach items="${menus}" var="l" varStatus="stat">
            	<tr ondblclick="editMenu('${l.id}')">
            	<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.getId()}"></td>
            	<td align="center">${l.id}</td>
            	<td align="center">${l.name}</td>
            	<td align="left">${l.url}</td>
            	<td align="center">${l.index_no}</td>
            	<tr>
            </c:forEach>
           

        </tbody>
		</table>
		</div>
    <div class="panelBar">
        <div class="pages">
            <span>每页&nbsp;</span>
            <span class="sel">
                <select id="menulist_changenumPerPage" class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({pageNum:1,numPerPage:this.value},'menuref')">
                    <option value="20" <c:if test="${numPerPage == '20' }">selected='selected'</c:if>>20</option>
                    <option value="40" <c:if test="${numPerPage == '40' }">selected='selected'</c:if>>40</option>
                    <option value="60" <c:if test="${numPerPage == '60' }">selected='selected'</c:if>>60</option>
                    <option value="100" <c:if test="${numPerPage == '100' }">selected='selected'</c:if>>100</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount}条</span>
        </div>
        <div class="pagination-box" rel="menuref" targettype="navTab" totalcount="${totalcount}" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}">
        </div>
    </div>
    
    </div>