<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="col-md-12 " style="padding: 0px;">
	<div class="panel panel-default">
		<div class="pageHeader ">
			<form id="pagerForm"
				onsubmit="return divSearch(this,'readyDeclareView');"
				action="<%=basePath %>/backstage/declare/readyTableView" method="POST">
				<input type="hidden" name="dept_declareListView" id="dept_declareListView" value="${dept_declareListView}">
				<input type="hidden"name="pageNum" value="${declareListPage.pageNum}"> 
				<input type="hidden" name="numPerPage" value="${declareListPage.pageSize}">
				<input type="hidden" name="dept_declareOneView" >
				<div class="searchBar">
					<ul class="searchContent">
						<li><label>姓名:</label><input type="text" value="${code}"
							name="code" class="form-control" size="10" id="code" /></li>
						<li><label>专业:</label>
						<select class="form-control" name="xcszy" >
						<option value=""></option>
						<c:forEach items="${dicts2}" var="l" varStatus="stat">
						<option value="${l.nevalue}" <c:if test="${xcszy==l.nevalue}">selected="selected"</c:if>>${l.contents}</option>
						</c:forEach>
						</select></li>
						<li><label>推荐职务:</label>
						<select class="form-control" name="tjzw" >
						<option value=""></option>
						<c:forEach items="${dicts15}" var="l" varStatus="stat">
						<option value="${l.contents}" <c:if test="${tjzw==l.contents}">selected="selected"</c:if>>${l.contents}</option>
						</c:forEach>
						</select></li>
						<li><button type="submit" id="declareListViewSubmit"
								class="btn btn-default btn-sm">查询</button></li>
					</ul>
				</div>
			</form>
		</div>
		<div class="pageContent">
		<a id='empdetails${pageid}' data-href='backstage/employee/employeedetails?areaFlag=Y&pageid=${pageid}' class='btn btn-green btn-sm' target='dialog' 
						width='950' height='602' style="display: none;"  rel="" ></a>
						
			<div style="overflow-x:auto;overfolw-y:hidden">
<table class="table table-bordered table-hover table-striped table-top" width="100%" layoutH="74" rel="userAddForm">
	<thead>
		<tr align="center">
			<!-- <th class=" center" width="185px">推荐人员编号</th> -->
			<th class="center" width="40px"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
			<th class=" center" width="40px">序号</th>
            <th class=" center"  width="55px">姓名</th>
            <th class=" center" width="40px">性别</th>
            
			<th class=" center" width="150px">身份证</th>
			<th class=" center" width="80px">推荐职务</th>
			<th class=" center" width="120px">专业</th>
			<th class=" center" width="220px">量化模板名称</th>
			<th class=" center" width="60px">附件数</th>
			<th class=" center" width="70px">评聘结果</th>
			<th class=" center" width="50px">聘期</th>
			<th class=" center" width="80px">起聘时间</th>
			<th class=" center" width="70px">上传标记</th>
         	</tr>
     </thead>
     <tbody >
     <c:forEach items="${declareListViewPage.results}" var="l" varStatus="stat">
     <tr>
   	<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.getRecordno()}"></td>
 	<td class=" center">${stat.index + 1}</td>
     <td>${l.xm}</td>
     <td class=" center">${l.xb}</td>
     <td>${l.idcard}</td>
     
     <td>${l.tjhrzzg}</td>
     <td>${l.zymc}</td>
     <td>${l.name}</td>
     <td>${l.fjsize}</td>
     <td>${l.sftg}</td>
     <td>${l.prqx}</td>
     <td><fmt:formatDate value="${l.qpsj}" pattern="yyyy-MM-dd"/></td>
     <td>
     	<c:if test="${l.scbj eq 'Y'}"><font color="green" style="font-weight:bold">√</font></c:if>
     </td>
     </tr>
     </c:forEach>
     </tbody>
</table>
</div>
			<div class="panelBar">
				<div class="pages">
					<span>每页&nbsp;</span> <span class="sel"> <select
						id="declarelistView_changenumPerPage"
						class="selectpicker show-tick dropup"
						data-style="btn-default btn-sel xs" data-width="auto"
						name="numPerPage"
						onchange="navTabPageBreak({numPerPage:this.value},'readyDeclareView')">
							<option value="10"
								<c:if test="${declareListViewPage.pageSize == '10' }">selected='selected'</c:if>>10</option>
							<option value="30"
								<c:if test="${declareListViewPage.pageSize == '30' }">selected='selected'</c:if>>30</option>
							<option value="60"
								<c:if test="${declareListViewPage.pageSize == '60' }">selected='selected'</c:if>>60</option>
							<option value="120"
								<c:if test="${declareListViewPage.pageSize == '120' }">selected='selected'</c:if>>120</option>
							<option value="150"
								<c:if test="${declareListViewPage.pageSize == '150' }">selected='selected'</c:if>>150</option>
							<option value="100000"
								<c:if test="${declareListViewPage.pageSize == '100000' }">selected='selected'</c:if>>100000</option>
					</select>
					</span> <span>&nbsp;条，共 ${declareListViewPage.totalRecord}条</span>
				</div>
				<div class="pagination-box" targettype="navTab" rel="readyDeclareView"
					totalcount="${declareListViewPage.totalRecord}"
					numperpage="${declareListViewPage.pageSize}" pagenumshown="5"
					currentpage="${declareListViewPage.pageNum}"></div>
			</div>
		</div>
	</div>
</div>