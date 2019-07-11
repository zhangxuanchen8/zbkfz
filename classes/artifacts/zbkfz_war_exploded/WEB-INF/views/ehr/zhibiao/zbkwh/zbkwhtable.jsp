<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function editZhiBiao(id){
	$("#editzhibiao").attr("href","<%=basePath%>/backstage/zbkwh/form?id="+id);
	$("#editzhibiao").click();
}
</script>

<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);"  action="<%=basePath %>/backstage/zbkwh/index"  method="POST">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
        <div class="searchBar">
            <ul class="searchContent">
          	   <li><label>类型:</label>
          	   <select   name="category"  class="form-control" >
          	   <option value=""></option>
          	   <option value="医师" <c:if test="${category eq '医师'}">selected = "selected"</c:if> >医师</option>
          	   <option value="药师" <c:if test="${category eq '药师'}">selected = "selected"</c:if> >药师</option>
          	   <option value="护理" <c:if test="${category eq '护理'}">selected = "selected"</c:if> >护理</option>
          	   <option value="技师" <c:if test="${category eq '技师'}">selected = "selected"</c:if> >技师</option>
          	   <option value="通用" <c:if test="${category eq '通用'}">selected = "selected"</c:if> >通用</option>
          	   </select>
          	   </li>
                <li><button type="submit" class="btn btn-default btn-sm">查询</button></li>
                <li class="pull-right">
                    <div class="btn-group">
                        <a href="<%=basePath%>/backstage/zbkwh/form" class="btn btn-default btn-sm" target="dialog"  width="600" height="250" style="margin-right:15px;">新增</a> 
                         <a id="editzhibiao" data-href="<%=basePath%>/backstage/zbkwh/form?id=" class="hidden" target="dialog" width="600" height="250" style="margin-right:15px;">修改</a>         
				    	<a href="<%=basePath%>/backstage/zbkwh/del" postType="string" rel="ids" class="btn btn-red btn-sm" target="selectedTodo" title="确定要删除这些信息吗？删除后将无法恢复！！！">删除</a>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>
<div class="pageContent" >
	<div style="width:100%;overflow-x:auto;overfolw-y:auto">
		<table  class="table table-bordered table-hover table-striped table-top" layoutH="70" rel="zhibiaotable">
        <thead id="gongzuolyc_head">
            <tr>
                <th class="center" width="28"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
               
                <th orderfield="" class="center" width="40px">类型</th>
                <th orderfield="" class="center" width="200px">一级分类</th>
                <th orderfield="" class="center" >二级分类</th>
                 <th orderfield="" class="center" >指标名</th>
				<th orderfield="" class="center" width="60px">分值</th>
				<th orderfield="" class="center" >指标评分说明</th>
            </tr>
        </thead>
        <tbody id="gongzuolyc_body">
            <c:forEach items="${zhibiao_wh_list}" var="l" varStatus="stat">
            	<tr align="center" id="${l.id}" ondblclick="editZhiBiao('${l.id}')">
            	<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.getId()}"></td>
            	
            	<td align="center">${l.category}</td>
            	<td align="center">${l.yjfl}</td>
            	<td align="center">${l.ejfl}</td>
            	<td align="center">${l.zbkmc}</td>
            	<td align="center">${l.score}</td>
            	<td align="center">${l.item_desc}</td>
            	<tr>
            </c:forEach>
        </tbody>
		</table>
		</div>
    <div class="panelBar">
        <div class="pages">
            <span>每页&nbsp;</span>
            <span class="sel">
                <select id="menulist_changenumPerPage" class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({pageNum:1,numPerPage:this.value})">
                	<option value="10" <c:if test="${numPerPage == '10' }">selected='selected'</c:if>>10</option>
                    <option value="20" <c:if test="${numPerPage == '20' }">selected='selected'</c:if>>20</option>
                    <option value="40" <c:if test="${numPerPage == '40' }">selected='selected'</c:if>>40</option>
                    <option value="60" <c:if test="${numPerPage == '60' }">selected='selected'</c:if>>60</option>
                    <option value="100" <c:if test="${numPerPage == '100' }">selected='selected'</c:if>>100</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount}条</span>
        </div>
        <div class="pagination-box"  targettype="navTab" totalcount="${totalcount}" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}">
        </div>
    </div>
</div>