<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function showdictdwdz(str){
	$("#showdictdwdz").attr("href",$("#showdictdwdz").attr("data-href")+"id="+str);
	$("#showdictdwdz").click();
}
</script>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="backstage/baseDict/xinjiwh" method="post">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="numPerPage" value="${numPerPage}">	
        <div class="searchBar">
		        <ul class="searchContent">
		        	<li><label>类型:</label>
		        	<select type="text"  name="option01"  class="form-control"  >
		        	<option></option>
		        	<option value="管理" <c:if test="${option01=='管理'}">selected="selected"</c:if>>管理</option>
		        	<option value="技术" <c:if test="${option01=='技术'}">selected="selected"</c:if>>技术</option>
		        	<option value="工勤" <c:if test="${option01=='工勤'}">selected="selected"</c:if>>工勤</option>
		        	<option value="村医" <c:if test="${option01=='村医'}">selected="selected"</c:if>>村医</option>
		        	
		        	</select></li>
					<li><button class="btn btn-default btn-sm" type="submit">查询</button></li>
					<li class="pull-right">
					 <a href="<%=basePath%>backstage/baseDict/adddictxinji" target="dialog"  width='300' height='200' class="btn btn-default btn-sm">新增</a>
					 </li>
				</ul>
		</div>
    </form>
</div>

<div class="pageContent">
<div style="overflow-x:auto;overfolw-y:hidden">
<table class="table table-bordered table-hover table-striped table-top" width="100%" layoutH="70">
 	<thead>
            <tr>
            	<th width="50px">序号</th>
            	<th width="80px">项目序号</th>
                <th >类别</th>
                <th width="180px">编制薪级</th>
                <th width="180px">工资</th>
            </tr>
        </thead>
         <tbody >
         <c:forEach items="${bict}" var="l" varStatus="stat">
         <tr ondblclick="showdictdwdz('${l.dictid}')">
         <td class="center">${stat.index + 1+(pageNum-1)*numPerPage}</td>
         <td >${l.nevalue}</td>
         <td >${l.option01}</td>
         <td >${l.contents}</td>
         <td >${l.option02}</td>
         <td hidden="hidden"><a id="showdictdwdz" data-href="<%=basePath%>backstage/baseDict/adddictxinji?" target="dialog"  width='300' height='200'>修改</a></td>
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
                     <option value="100000" <c:if test="${numPerPage == '100000' }">selected='selected'</c:if>>100000</option>
                </select>

            </span>
            <span>&nbsp;条，共 ${totalcount}条</span>
        </div>
        <div class="pagination-box" targettype="navTab" totalcount="${totalcount}" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}"  >
        </div>
    </div>
</div>