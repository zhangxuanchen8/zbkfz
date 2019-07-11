
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<style>
div.grid{border-left: 0px;border-right: 0px;}
</style>

<script type="text/javascript">


	
function editWarning(id){
	$("#addWarning").attr("href","<%=basePath%>backstage/warning/WarningAdd?id="+id);
	$("#addWarning").click();
}		
	
</script>



	<div class="pageHeader " >
		<form id="pagerForm" action="<%=basePath %>backstage/warning/warningIndex"   method="POST" onsubmit="return navTabSearch(this);" >
			<input type="hidden" name="pageNum" value="${page.pageNum}">
        	<input type="hidden" name="numPerPage" value="${page.pageSize}">
        	<input type="hidden" name="orderField" value="${orderField}">
        	<input type="hidden" name="orderDirection" value="${orderDirection}">
			<div class="searchBar">
            	<ul class="searchContent">
                	<li><label>预警名称:</label><input type="text" value="${warning.warn_name}" name="warn_name" class="form-control" size="10" id="" placeholder=""/></li>
					<li><button type="submit" class="btn btn-default btn-sm">查询</button></li>
					<li><a class="btn btn-orange btn-sm" href="javascript:navTab.reload('', {clearQuery:true});">清空查询</a></li> 
					<li class="pull-right">
						<a href="<%=basePath%>backstage/warning/WarningAdd"  class="btn btn-default btn-sm" target="dialog" width="700" height="500" mask="true">新增</a>
	                    <a style="display: none" href="<%=basePath%>backstage/warning/WarningAdd" id="addWarning" class="btn btn-default btn-sm" target="dialog" width="700" height="500" mask="true">新增</a>
	                    <a href="backstage/warning/delBatch" class="btn btn-default btn-sm" target="checkedAjaxTodo" idname="ids">删除</a>
                    <div class="btn-group">
                    </div>
                </li>
				</ul>
        	</div>
		</form>
	</div>
	<div class="pageContent" >
			<table class="j-table" width="100%" layoutH="95" rel="">
				<thead>
					<tr align="center">
						<th width="28"><input type="checkbox" class="checkboxCtrl j-icheck " group="ids"></th>
						<th  class="center" >序号</th>
		                <th orderfield="warn_type" class="<c:if test="${orderField=='warn_type'}">orderby ${orderDirection }</c:if> center" >预警类别</th>
						<th orderfield="warn_name" class="<c:if test="${orderField=='warn_name'}">orderby ${orderDirection }</c:if> center" >预警名称</th>
						<th orderfield="warn_forward" class="<c:if test="${orderField=='warn_forward'}">orderby ${orderDirection }</c:if> center" >预警的对象</th>
						<th orderfield="warn_cyc" class="<c:if test="${orderField=='warn_cyc'}">orderby ${orderDirection }</c:if> center" >预警提醒周期</th>
						<th orderfield="forward_role" class="<c:if test="${orderField=='forward_role'}">orderby ${orderDirection }</c:if> center" >查看预警所需角色</th>
						<th orderfield="operatdate" class="<c:if test="${orderField=='operatdate'}">orderby ${orderDirection }</c:if> center" >创建时间</th>
						
            		</tr>
        		</thead>
        		<tbody>
	            	<c:forEach items="${page.results}" var="l" varStatus="stat">
		            	<tr align="center" id="${l.id}"   ondblclick="editWarning('${l.id}')">
		            	
		            	
		            	
			            	<td align="center"><input type="checkbox" name="ids" class="j-icheck" value="${ l.id}"></td>
			            	<td align="center" style="text-align: center;">${(page.pageNum-1)*page.pageSize+stat.index + 1}</td>
							<td align="center" style="text-align: center;">${l.warn_type}</td>
							<td align="center" style="text-align: center;">${l.warn_name}</td>
							<td align="center" style="text-align: center;">${l.warn_forward}</td>
							<td align="center" style="text-align: center;">${l.warn_cyc}</td>
							<td align="center" style="text-align: center;">${l.forward_role}</td>
							<td align="center" style="text-align: center;"><fmt:formatDate value="${l.operatdate}"
												pattern="yyyy-MM-dd HH:mm:SS" /></td>
						</tr>
            		</c:forEach> 
        		</tbody>
    		</table>
    		<div class="panelBar">
    			<div class="pages">
            		<span>每页&nbsp;</span>
            		<span class="sel">
                		<select  class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                    		<option value="10" <c:if test="${page.pageSize == '10' }">selected='selected'</c:if>>10</option>
                    		<option value="30" <c:if test="${page.pageSize == '30' }">selected='selected'</c:if>>30</option>
                    		<option value="60" <c:if test="${page.pageSize == '60' }">selected='selected'</c:if>>60</option>
                    		<option value="120" <c:if test="${page.pageSize == '120' }">selected='selected'</c:if>>120</option>
                    		<option value="150" <c:if test="${page.pageSize == '150' }">selected='selected'</c:if>>150</option> 
                    		<option value="10000" <c:if test="${page.pageSize == '10000' }">selected='selected'</c:if>>10000</option> 
                		</select>
            		</span>
            		<span>&nbsp;条，共 ${page.totalRecord}条</span>
        		</div>
        		<div class="pagination-box" targettype="navTab"   totalcount="${page.totalRecord}" numperpage="${page.pageSize}" pagenumshown="5" currentpage="${page.pageNum}"  ></div>
    		</div>
		</div>

