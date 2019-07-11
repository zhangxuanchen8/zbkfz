
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











function empdetails(pid_a){
	$("#"+pid_a).click();
}



//--------------------------------选中签订人员--------------------------------

function getFormatDateByLong(l) { 
	return getFormatDate(new Date(l)); 
} 

function getFormatDate(date) { 
	var str = date.toLocaleDateString().replace(/(\d+)[^\d](\d+)[^\d](\d+)[^\d]/, "$1-$2-$3");
	return str;
} 
	function getPid_baseUser(event, treeId, treeNode) {
		
		
		var ids = new Array();
		getChildren(ids, treeNode)
		allLastLoad = ids.toString();
		$("#thedeptname_baseUser").val(treeNode.name);
		$("#thedept_baseUser").val(treeNode.id);
		$("#dept_baseUser").val(allLastLoad);
		$("#baseUserSubmit").click();
	}
	function getChildren(ids, treeNode) {
		ids.push(treeNode.id);
		if (treeNode.isParent) {
			for ( var obj in treeNode.children) {
				getChildren(ids, treeNode.children[obj]);
			}
		}
		return ids;
	}
	function getContractSignIds(obj){
		
	}
	
	
	
	function editBaseUser(userID){
		$("#addBaseUser").attr("href","backstage/baseUser/addForm?userID="+userID);
		$("#addBaseUser").click();
	}
	
	$("#baseUserAdd").click(function(){
		var theDept = $("#thedept_baseUser").val();
		var allDept = $("#dept_baseUser").val();
		var deptname = $("#thedeptname_baseUser").val();
		
		
		/* if(theDept==""||theDept!=allDept){
			alertMsg.error("请选择末级科室!");
			return;
		}else{ */
			
		$("#addBaseUser").attr("href","backstage/baseUser/addForm?thedept_baseUser="+theDept+"&deptname="+encodeURI(encodeURI(deptname)));
		$("#addBaseUser").click();
		/* } */
		
	});
		
	
</script>



	<div class="pageHeader " >
		<form id="pagerForm" onsubmit="return divSearch(this, 'userAddForm');" action="<%=basePath %>backstage/baseUser/baseUserIndexN"  method="POST">
			
		
			
			<input type="hidden" name="dept_baseUser" id="dept_baseUser" value="${dept_baseUser}">
			<input type="hidden" name="pageNum" value="${page.pageNum}">
        	<input type="hidden" name="numPerPage" value="${page.pageSize}">
        	<input type="hidden" name="orderField" value="">
        	<input type="hidden" name="orderDirection" value="">
        	<input type="hidden" id="thedept_baseUser" name="thedept_baseUser" value="${thedept_baseUser}">
        	<input type="hidden" id="thedeptname_baseUser" name="thedeptname_baseUser" value="${thedeptname_baseUser}">
        	
			<div class="searchBar">
            	<ul class="searchContent">
                	<li><label>姓名:</label><input type="text" value="${baseUserName}" name="baseUserName" class="form-control" size="10" id="baseUserName" placeholder=""/></li>
                	
					<li><button type="submit" id="baseUserSubmit" class="btn btn-default btn-sm">查询</button></li>
					<li><a class="btn btn-orange btn-sm" href="javascript:navTab.reload('', {clearQuery:true});">清空查询</a></li> 
					
					<li class="pull-right">
                
                
                
                
                	<button type="button" id="baseUserAdd" class="btn btn-default btn-sm">新增</button>
                    <a style="display:none" href="backstage/baseUser/addForm" id="addBaseUser" class="btn btn-default btn-sm" target="dialog" width="700" height="500" mask="true"></a>
                    
                    <a href="backstage/baseUser/delBatch" class="btn btn-default btn-sm" target="checkedAjaxTodo" idname="ids">删除</a>
                    
                    <div class="btn-group">
                        
                        
                        
                    </div>
                </li>
				</ul>
				
        	</div>
		</form>
	</div>
	<div class="pageContent" >
			<table class="j-table" width="100%" layoutH="95" rel="userAddForm">
				<thead>
					<tr align="center">
						<th width="28"><input type="checkbox" class="checkboxCtrl j-icheck " group="ids"></th>
						<th orderfield="employeelist_no" class="employeelisttablehead center" id="employeelist_no_head">序号</th>
		                <th orderfield="employeelist_p_no" class="employeelisttablehead center" id="employeelist_p_no_head">科室</th>
						<th orderfield="employeelist_c_name" class="employeelisttablehead center" id="employeelist_c_name_head">工号</th>
						<th orderfield="employeelist_deptname" class="employeelisttablehead center" id="employeelist_deptname_head">姓名</th>
						<th orderfield="employeelist_zc_type" class="employeelisttablehead center" id="employeelist_zc_type_head">性别</th>
						<th orderfield="employeelist_zc_level" class="employeelisttablehead center" id="employeelist_zc_level_head">电话</th>
						
            		</tr>
        		</thead>
        		<tbody>
	            	<c:forEach items="${page.results}" var="l" varStatus="stat">
		            	<tr align="center" id="${l.id}"   ondblclick="editBaseUser('${l.id}')">
			            	<td align="center"><input type="checkbox" name="ids" class="j-icheck" value="${ l.id}"></td>
			            	<td align="center" style="text-align: center;">${(page.pageNum-1)*page.pageSize+stat.index + 1}</td>
							<td align="center" style="text-align: center;">${l.dept_name}</td>
							<td align="center" style="text-align: center;">${l.job_no}</td>
							<td align="center" style="text-align: center;">${l.name}</td>
							<td align="center" style="text-align: center;">${l.sex}</td>
							<td align="center" >${l.phone}</td>
							
						</tr>
            		</c:forEach> 
        		</tbody>
    		</table>
    		<div class="panelBar">
    			<div class="pages">
            		<span>每页&nbsp;</span>
            		<span class="sel">
                		<select id="employeelist_changenumPerPage" class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value},'userAddForm')">
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
        		<div class="pagination-box" targettype="navTab" rel="userAddForm"  totalcount="${page.totalRecord}" numperpage="${page.pageSize}" pagenumshown="5" currentpage="${page.pageNum}"  ></div>
    		</div>
		</div>






<script type="text/javascript">
function changenumPerPage(){
	  var newnum = $('#employeelist_changenumPerPage  option:selected').text(); 
	  $("#employeelist_numPerPage").val(newnum);
	  $("#pagerForm").submit();
}
</script>
