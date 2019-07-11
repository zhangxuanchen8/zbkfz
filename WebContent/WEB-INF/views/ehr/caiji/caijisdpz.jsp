<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <style type="text/css">
  .s2 {
   background-color:#FFFF00;
  }
 </style>
<script type="text/javascript">
//-------------选择两条记录----------
function getcaijiForm(id){
	$("#caiijpz").attr("href","backstage/shujuljdy/caijibj1?id="+id);
	$("#caiijpz").click();
}
</script>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>/backstage/shujuljdy/cjpzindex1"  method="POST">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
        <input type="hidden" name="orderField" value="${orderField}" />  
        <div class="searchBar">
            <ul class="searchContent">
                 <li><label>名称：</label><input type="text" value="${code}" name="code" id="code" class="form-control" size="10" /></li>
                <li><button type="submit" class="btn btn-default btn-sm">查询</button></li>
                <li><a class="btn btn-orange btn-sm" href="javascript:navTab.reload('', {clearQuery:true});">清空查询</a></li>
                 <li class="pull-right">
               		<!-- <a href="backstage/shujuljdy/caijibj"class="btn btn-default btn-sm" target='dialog' width='600' height='450' mask='true' rel="getContractFormPage" >新增</a> -->
               		<a style="display:none" href="backstage/shujuljdy/caijibj" id="caiijpz" class="btn btn-default btn-sm" target='dialog' width='600' height='450' mask='true' >编辑</a>
                 	<a href="backstage/shujuljdy/sdCaiJi" class="btn btn-default btn-sm" title="是否确定采集?" target="checkedAjaxTodo" idname="ids">采集</a>
                </li>
            </ul>
        </div>
    </form>
</div>
<div class="pageContent" >
	<div style="overflow-x:auto;overfolw-y:hidden">
    <table class="j-table list" style="width: 110%;" layoutH="95" >
        <thead id="caijisdpz_log_head1">
            <tr>
            	<th width="1px" class="center"><input type="checkbox" class="checkboxCtrl j-icheck " group="ids"></th>
                <th orderfield="pz_no" class="center" style="width:3px">序号</th>
                <th orderfield="pz_caijimc" class="center" style="width: 180px">采集项名称</th>
                <th orderfield="pz_caijisj" class="center" style="width: 180px">最近采集时间</th>
            </tr>
        </thead>
        <tbody id="caijisdpz_log_body">
            <c:forEach items="${cjpz}" var="l" varStatus="stat">
            	<tr id="${l.id}" ondblclick="getcaijiForm('${l.id}')" >
	            	<td align="center"><input type="checkbox" name="ids" class="j-icheck" value="${ l.id}"></td>
	            	<td class="center">${ stat.index + 1}</td>
	            	<td class="center">${l.caijimc}</td>
	            	<td class="center"><fmt:formatDate value="${l.sccjsj}" pattern="yyyy-MM-dd"/></td>
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
        <div class="pagination-box" targettype="navTab" totalcount="${totalcount }" numperpage="${numPerPage }" pagenumshown="10" currentpage="${pageNum}"  >
        </div>
    </div>
</div>