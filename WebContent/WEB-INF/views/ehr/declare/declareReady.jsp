<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(function(){
	var dept_declareMessage='<%=request.getAttribute("dept_declareMessage")%>'; 
	console.log(">>>>>>>>"+dept_declareMessage);
	if(dept_declareMessage !="null"){
		alertMsg.error(dept_declareMessage);
	}
	
});
</script>
<div  class="col-md-9 " style="padding:0px; ">
<div class="panel panel-default">
	<div class="pageHeader " >
		<form id="pagerForm" onsubmit="return divSearch(this,'readyDeclare');" action="<%=basePath %>/backstage/declare/readyTable"  method="POST">
			<input type="hidden" name="dept_declareList" id="dept_declareList" value="${dept_declareList}">
			<input type="hidden" name="pageNum" value="${declareListPage.pageNum}">
        	<input type="hidden" name="numPerPage" value="${declareListPage.pageSize}">
        	<input type="hidden" name="candidate" id='candidate' value="${candidate}">
        	<input type="hidden" name="dept_declareOne" id='dept_declareOne' value="">
			<div class="searchBar">
            	<ul class="searchContent">
                	<li><label>工号或姓名:</label><input type="text" value="${code}" name="code" class="form-control" size="10" id="code" /></li>
					<li><button type="submit" id="declareListSubmit" class="btn btn-default btn-sm">查询</button></li>
				</ul>
        	</div>
		</form>
	</div>
	<div class="pageContent" >
			<table class="j-table" width="100%" layoutH="95">
				<thead>
					<tr>
						<th orderfield="declarelist_no" class="declarelisttablehead center" id="declarelist_no_head">序号</th>
		                <th orderfield="declarelist_p_no" class="declarelisttablehead center" id="declarelist_p_no_head">工号</th>
						<th orderfield="declarelist_c_name" class="declarelisttablehead center" id="declarelist_c_name_head">姓名</th>
						<th orderfield="declarelist_id_card" class="declarelisttablehead center" id="declarelist_id_card">身份证号</th>
						<th orderfield="declarelist_deptname" class="declarelisttablehead center" id="declarelist_deptname_head">科室名称</th>
						<th orderfield="declarelist_zc_name" class="declarelisttablehead center" id="declarelist_zc_name">职位</th>
					</tr>
        		</thead>
        		<tbody>
	            	<c:forEach items="${declareListPage.results}" var="l" varStatus="stat">
		            	<tr align="center" id="${l.getP_id()}"  ondblclick="addDeclareList('${l.getP_id()}')">
		            		<td  
			            		<c:if test="${l.selected=='1'}">
								 style="background-color: #66cccc" 
								</c:if>
			            	>${(declareListPage.pageNum-1)*declareListPage.pageSize+stat.index + 1}</td>
		            		
		            		<td 
			            		<c:if test="${l.selected=='1'}">
								 style="background-color: #66cccc" 
								</c:if>
		            		>${l.p_no}</td>
		            		
		            		<td  
			            		<c:if test="${l.selected=='1'}">
								 style="background-color: #66cccc" 
								</c:if>
			            	>${l.c_name}</td>
		            		
		            		<td  
			            		<c:if test="${l.selected=='1'}">
								 style="background-color: #66cccc" 
								</c:if>
			            	>${l.id_card}</td>
		            		
		            		<td  
			            		<c:if test="${l.selected=='1'}">
								 style="background-color: #66cccc" 
								</c:if>
			            	>${l.deptname}</td>
		            		
		            		<td 
			            		<c:if test="${l.selected=='1'}">
								 style="background-color: #66cccc" 
								</c:if>
			            	>${l.zc_name}</td>
		            	</tr>
            		</c:forEach> 
        		</tbody>
    		</table>
    		<div class="panelBar">
    			<div class="pages">
            		<span>每页&nbsp;</span>
            		<span class="sel">
                		<select id="declarelist_changenumPerPage" class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value},'readyDeclare')">
                    		<option value="10" <c:if test="${declareListPage.pageSize == '10' }">selected='selected'</c:if>>10</option>
                    		<option value="30" <c:if test="${declareListPage.pageSize == '30' }">selected='selected'</c:if>>30</option>
                    		<option value="60" <c:if test="${declareListPage.pageSize == '60' }">selected='selected'</c:if>>60</option>
                    		<option value="120" <c:if test="${declareListPage.pageSize == '120' }">selected='selected'</c:if>>120</option>
                    		<option value="150" <c:if test="${declareListPage.pageSize == '150' }">selected='selected'</c:if>>150</option> 
                    		<option value="100000" <c:if test="${declareListPage.pageSize == '100000' }">selected='selected'</c:if>>100000</option>
                		</select>
            		</span>
            		<span>&nbsp;条，共 ${declareListPage.totalRecord}条</span>
        		</div>
        		<div class="pagination-box" targettype="navTab" rel="readyDeclare"  totalcount="${declareListPage.totalRecord}" numperpage="${declareListPage.pageSize}" pagenumshown="5" currentpage="${declareListPage.pageNum}"  ></div>
    		</div>
		</div>
	</div>
</div>

<!-- 选择人员的表格 -->
<div class="col-md-3" style="padding-right:0px; ">
	<div class="panel panel-default">
		<div class="pageHeader" >
			<form action="">
				<div class="searchBar">
		            <ul class="searchContent">
		                <li class="pull-right">
		                <%-- <a href="<%=basePath%>/backstage/declare/declareInfo" class="btn btn-default btn-sm" target="navTab" width="500" height="250"   mask="true">批量申报</a> --%>
		                	<a  class="btn btn-default btn-sm" width="500" height="250" mask="true" onclick="BatchDeclaration();">批量申报</a>
		                </li>
		            </ul>
		        </div>
			</form>
		</div>
		<div class="pageContent" >
		    <table class="j-table" width="100%" layoutH="95" >
		        <thead>
		            <tr>
		               <!--  <th align="center"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th> -->
		                <th  class="declarelisttablehead center" id="modelid" >序号</th>
		                <th  class="declarelisttablehead center" id="modelid" >工号</th>
		                <th  class="declarelisttablehead center" id="modelid" >姓名</th>
		            </tr>
		        </thead>
		        <tbody id="contractListTbody">
		        	<c:forEach items="${candidateDetails}" var="l" varStatus="stat">
		            	<tr align="center" id="${l.getP_id()}"  ondblclick="addDeclareList('${l.getP_id()}')">
			            	<%-- <td><input type="checkbox" name="ids" class="j-icheck" value="${l.p_id}" onclick ="getContractSignIds(this)"></td>
			            	 --%><td>${(declareListPage.pageNum-1)*declareListPage.pageSize+stat.index + 1}</td>
							<td>${l.p_no}</td>
							<td>${l.c_name}</td>
						</tr>
            		</c:forEach>
            		<c:forEach items="${declareError}" var="l" varStatus="stat">
		            	<tr align="center" id="${l.getP_id()}"  style="color:red" ondblclick="addDeclareList('${l.getP_id()}')">
			            	<%-- <td><input type="checkbox" name="ids" class="j-icheck" value="${l.p_id}" onclick ="getContractSignIds(this)"></td>
			            	 --%><td>${(declareListPage.pageNum-1)*declareListPage.pageSize+stat.index + 1}</td>
							<td>${l.p_no}</td>
							<td>${l.c_name}</td>
						</tr>
            		</c:forEach>
            		<c:forEach items="${declareSuccessGoto}" var="l" varStatus="stat">
		            	<tr align="center" id="${l.getP_id()}" style="color:#d26f40"  ondblclick="addDeclareList('${l.getP_id()}')">
			            	<%-- <td><input type="checkbox" name="ids" class="j-icheck" value="${l.p_id}" onclick ="getContractSignIds(this)"></td>
			            	 --%><td>${(declareListPage.pageNum-1)*declareListPage.pageSize+stat.index + 1}</td>
							<td>${l.p_no}</td>
							<td>${l.c_name}</td>
						</tr>
            		</c:forEach>
            		<c:forEach items="${declareSuccess}" var="l" varStatus="stat">
		            	<tr align="center" id="${l.getP_id()}" style="color:green"  ondblclick="addDeclareList('${l.getP_id()}')">
			            	<%-- <td><input type="checkbox" name="ids" class="j-icheck" value="${l.p_id}" onclick ="getContractSignIds(this)"></td>
			            	 --%><td>${(declareListPage.pageNum-1)*declareListPage.pageSize+stat.index + 1}</td>
							<td>${l.p_no}</td>
							<td>${l.c_name}</td>
						</tr>
            		</c:forEach>     
		        </tbody>
		    </table>
		   <div class="panelBar"></div>
		</div>
	</div>
</div>


