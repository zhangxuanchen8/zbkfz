<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function editMenu(menuid){
	$("#menuEditBt").attr("href","<%=basePath%>backstage/zhibiao/xgzhibiao?id="+menuid);
	$("#menuEditBt").click();
}
function shengcheng(){
	<%-- //$("#menuEditBt").attr("href","<%=basePath%>backstage/zhibiao/xgzhibiao?id="+menuid); --%>
	$("#shengcheng").click();
}

</script>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return divSearch(this, 'menuref00');" action="<%=basePath %>/backstage/zhibiaogl/index1?supunit=${tjymsx}" method="post">
        <input type="hidden" id="tjymsx" name="tjymsx" value="${tjymsx}">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
        <div class="searchBar">
		        <ul class="searchContent">
              <%--  <li><label>名称:</label><input id="menulist_name" type="text" value="${name}" name="name" class="form-control" size="10" /></li>
                <li><button id="" type="submit" class="btn btn-default btn-sm">查询</button></li>
                <li><a class="btn btn-orange btn-sm" href="javascript:navTab.reload('', {clearQuery:true});">清空查询</a></li> --%>
                <li class="pull-right">
                    <div class="btn-group">
				    	<a href="<%=basePath%>/backstage/zhibiaogl/qrtj" postType="string" rel="ids" class="btn btn-red btn-sm" target="selectedTodo" title="确定要提交这些信息吗？" >提交</a>
                    </div>
                </li>
            </ul>
		</div>
    </form>
</div>
<div class="pageContent" >
	<div style="width:100%;overflow-x:auto;overfolw-y:auto">
		<table  class="table table-bordered table-hover table-striped table-top" layoutH="70" rel="menuref00">
        <thead>
            <tr>

                <th class="center" width="28"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
                <th orderfield="" class="center" >序号</th>
                <th orderfield="" class="center" >姓名</th>
                <th orderfield="" class="center" >分数</th>
                <th orderfield="" class="center" >身份证号码</th>
                <th orderfield="" class="center" >提交时间</th>
                <th orderfield="" class="center" >类别</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${score}" var="l" varStatus="stat">
            	<tr align="center" id="" >
            	<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.getId()}"></td>
            	<td align="center">${ stat.index + 1}</td>
            	<td align="center">${l.xm}</td>
            	<td align="center">${l.scorep}</td>
            	<td align="center">${l.idcard}</td>
            	<td align="center">
            	<fmt:formatDate value="${l.year}" pattern="yyyy-MM-dd"/></td>
            	<td align="center">${l.leixing}</td>
            	<tr>
            </c:forEach>
        </tbody>
		</table>
		</div>
    <div class="panelBar">
        <div class="pages">
            <span>每页&nbsp;</span>
            <span class="sel">
                <select id="menulist_changenumPerPage" class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({pageNum:1,numPerPage:this.value},'menuref00')">
                    <option value="20" <c:if test="${numPerPage == '20' }">selected='selected'</c:if>>20</option>
                    <option value="40" <c:if test="${numPerPage == '40' }">selected='selected'</c:if>>40</option>
                    <option value="60" <c:if test="${numPerPage == '60' }">selected='selected'</c:if>>60</option>
                    <option value="100" <c:if test="${numPerPage == '100' }">selected='selected'</c:if>>100</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount}条</span>
        </div>
        <div class="pagination-box" rel="menuref00" targettype="navTab" totalcount="${totalcount}" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}">
        </div>
    </div>
    
    </div>