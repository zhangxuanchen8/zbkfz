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
function getshujubjForm(id){
	$("#shujubj").attr("href","backstage/shujuljdy/shujubj?id="+id);
	$("#shujubj").click();
}
</script>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>/backstage/shujuljdy/sjpzindex"  method="POST">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
        <input type="hidden" name="orderField" value="${orderField}" />  
        <div class="searchBar">
            <ul class="searchContent">
                 <li class="pull-right">
               		<a href="backstage/shujuljdy/shujubj" id="addshujulj" class="btn btn-default btn-sm" target='dialog' width='620' height='310' mask='true' rel="getContractFormPage" >新增</a>
               		<a style="display:none" href="backstage/shujuljdy/shujubj" id="shujubj" class="btn btn-default btn-sm" target='dialog' width='620' height='310' mask='true' >编辑</a>
                 	<a href="backstage/shujuljdy/delShujupz" class="btn btn-default btn-sm" title="确定要删除吗,删除将无法还原?" target="checkedAjaxTodo" idname="ids">删除</a>
                </li>
            </ul>
        </div>
    </form>
</div>
<div class="pageContent" >
	<div style="overflow-x:auto;overfolw-y:hidden">
    <table class="j-table list" style="width: 110%;" layoutH="95" >
        <thead id="tongjipz_log_head1">
            <tr>
            	<th width="3px" class="center"><input type="checkbox" class="checkboxCtrl j-icheck " group="ids"></th>
                <th orderfield="shuju_no"class="center" style="width:2px">序号</th>
                <th orderfield="shuju_sjmc" class="center" style="width: 110px">数据源名称</th>
                <th orderfield="shuju_ly" class="center" style="width: 110px">来源</th>
            </tr>
        </thead>
        <tbody id="tongjipz_log_body">
            <c:forEach items="${sjpz}" var="l" varStatus="stat">
            	<tr id="${l.id}" ondblclick="getshujubjForm('${l.id}')" >
	            	<td align="center"><input type="checkbox" name="ids" class="j-icheck" value="${ l.id}"></td>
	            	<td class="center">${ stat.index + 1}</td>
	            	<td class="center">${l.s_name}</td>
	            	<td class="center">${l.sources}</td>
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