
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
	function editBaseUser(modelID){
		$("#editBaseUser").attr("href","backstage/model/addForm?modelID="+modelID);
		$("#editBaseUser").click();
	}
	
	$("#baseUserAdd").click(function(){
		$("#addBaseUser").attr("href","backstage/model/addForm?");
		$("#addBaseUser").click();

	});

</script>



	<div class="pageHeader " >
		<form id="pagerForm" onsubmit="return divSearch(this, 'modelAddForm');" action="<%=basePath %>backstage/model/baseUserIndexN?"  method="POST">
			
		
			
			<input type="hidden" id="pageNum_id" name="pageNum" value="${page.pageNum}">
        	<input type="hidden" id="numPerPage_id" name="numPerPage" value="${page.pageSize}">
        	<input type="hidden" name="orderField" value="">
        	<input type="hidden" name="orderDirection" value="">

        	
			<div class="searchBar">
            	<ul class="searchContent">
                	<li><label>名称:</label><input type="text" value="${modelName}" name="modelName" class="form-control" size="10" id="modelName" placeholder=""/></li>
                	
					<li><button type="submit" id="baseUserSubmit" class="btn btn-default btn-sm">查询</button></li>
					<li><a class="btn btn-orange btn-sm" href="javascript:navTab.reload('', {clearQuery:true});">清空查询</a></li> 
					
					<li class="pull-right">
                
                
                    <a href="<%=basePath%>/backstage/model/guidang" postType="string" rel="ids" class="btn btn-red btn-sm" target="selectedTodo" title="确定要归档这些信息吗？归档后相关数据均不可再修改" >归档</a>
                	<button type="button" id="baseUserAdd" class="btn btn-default btn-sm">新增</button>
                    <a style="display:none" href="backstage/model/addForm" id="addBaseUser" class="btn btn-default btn-sm" target="dialog" width="367" height="280" mask="true">新增</a>
                    <a style="display:none" href="backstage/model/addForm" id="editBaseUser" class="btn btn-default btn-sm" target="dialog" width="367" height="280" mask="true">编辑</a>
                    <a href="backstage/model/delBatch" class="btn btn-default btn-sm" target="checkedAjaxTodo" idname="ids">删除</a>
                    
                    <div class="btn-group">
                        
                        
                        
                    </div>
                </li>
				</ul>
				
        	</div>
		</form>
	</div>
	<div class="pageContent" >
			<table class="table table-bordered table-hover table-striped table-top" width="100%" layoutH="70" rel="modelAddForm">
				<thead>
					<tr align="center">
						<th width="28" class="center"><input type="checkbox" class="checkboxCtrl j-icheck " group="ids"></th>
						<th orderfield="employeelist_no" class="employeelisttablehead center" id="employeelist_no_head">序号</th>
		                <th orderfield="employeelist_p_no" class="employeelisttablehead center" id="employeelist_p_no_head">名称</th>
						<th orderfield="employeelist_zc_level" class="employeelisttablehead center" id="employeelist_zc_level_head">类别</th>
						<th orderfield="employeelist_c_name" class="employeelisttablehead center" id="employeelist_c_name_head">院区</th>
						<th orderfield="employeelist_deptname" class="employeelisttablehead center" id="employeelist_deptname_head">排序号</th>
						<th class="center">上传状态</th>
						
            		</tr>
        		</thead>
        		<tbody>
	            	<c:forEach items="${page.results}" var="l" varStatus="stat">
		            	<tr align="center" id="${l.id}"   ondblclick="editBaseUser('${l.id}')">
			            	<td align="center"><input type="checkbox" name="ids" class="j-icheck" value="${ l.id}"></td>
			            	<td align="center" style="text-align: center;">${(page.pageNum-1)*page.pageSize+stat.index + 1}</td>
							<td align="center" style="text-align: center;">${l.name}</td>
							<td align="center" style="text-align: center;">${l.zblbname}</td>
							<td align="center" style="text-align: center;">${l.yqname}</td>
							<td align="center" >${l.xh}</td>
							<td align="center" >
							<c:if test="${l.state=='Y'}">等待上传</c:if>
							<c:if test="${l.state=='N'}">已上传</c:if>
							<c:if test="${l.state==''}">未上传</c:if>
							</td>
						</tr>
            		</c:forEach> 
        		</tbody>
    		</table>
    		<div class="panelBar">
    			<div class="pages">
            		<span>每页&nbsp;</span>
            		<span class="sel">
                		<select id="employeelist_changenumPerPage" class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value},'modelAddForm')">
                    		<option value="10" <c:if test="${page.pageSize == '10' }">selected='selected'</c:if>>10</option>
                    		<option value="30" <c:if test="${page.pageSize == '30' }">selected='selected'</c:if>>30</option>
                    		<option value="60" <c:if test="${page.pageSize == '60' }">selected='selected'</c:if>>60</option>
                    		<option value="120" <c:if test="${page.pageSize == '120' }">selected='selected'</c:if>>120</option>
                    		<option value="150" <c:if test="${page.pageSize == '150' }">selected='selected'</c:if>>150</option> 
                    		<option value="100000" <c:if test="${page.pageSize == '100000' }">selected='selected'</c:if>>100000</option> 
                		</select>
            		</span>
            		<span>&nbsp;条，共 ${page.totalRecord}条</span>
        		</div>
        		<div class="pagination-box" targettype="navTab" rel="modelAddForm"  totalcount="${page.totalRecord}" numperpage="${page.pageSize}" pagenumshown="5" currentpage="${page.pageNum}"  ></div>
    		</div>
		</div>






<script type="text/javascript">
function changenumPerPage(){
	debugger
	  var newnum = $('#employeelist_changenumPerPage  option:selected').text(); 
	  $("#employeelist_numPerPage").val(newnum);
	  $("#pagerForm").submit();
}
</script>
