<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<div class="pageHeader">
	<form id="pagerForm" >
	  <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
		<div class="panel-heading" >
			<h3 class="panel-title">历史登录</h3>
		</div>
		</form>
		</div>
		<div class="pageContent" >
		<div class="panel-body" style="overflow: auto;"layoutH="100">
			<table class="table table-condensed table-striped"
				width="100%" style="overflow: auto;height: 180px;">
				<thead>
					<tr>
					   <th class="center" width="10%">序号</th>
						<th class="center" width="40%">时间</th>
						<th class="center" width="20%">登陆账号</th>
						<th class="center" width="30%">IP</th>
					</tr>
				</thead>
				<tbody>
				
				   <c:forEach items="${list_loginLog}" var="l" varStatus="stat">
				   <tr>
				    <td class="center" width="10%">${ stat.index + 1}</td>
					<td class="center" width="40%"><fmt:formatDate value="${l.loginTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
					<td class="center" width="20%">${l.aName}</td>
					<td class="center" width="30%">${l.loginIP}</td>
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
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount } 条</span>
        </div>
      
        <div class="pagination-box" targettype="navTab"   totalcount="${totalcount }" numperpage="${numPerPage }" pagenumshown="10" currentpage="${pageNum}"  >
       
        </div>
	</div>
	
</div>
