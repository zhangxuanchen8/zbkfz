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
function caiji(){
	$.ajax({
		async:false,
		cache:false,
		url:"<%=basePath%>backstage/shujuljdy/caiji",
		type : "get",
		success : function(data) {
				alert("采集成功");
		}
	});
}
</script>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>/backstage/shujuljdy/cjrzindex"  method="POST">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
        <input type="hidden" name="orderField" value="${orderField}" />  
        <div class="searchBar">
            <ul class="searchContent">
                <li><label>查询时间：</label><input id="caiji_date1" type="text" value="<fmt:formatDate value='${caiji_date1 }' pattern='yyyy-MM-dd'/>" name="caiji_date1" class="form-control date" size="10" />
                <label>&nbsp;-&nbsp;</label>
                <input id="caiji_date2" type="text" value="<fmt:formatDate value='${caiji_date1 }' pattern='yyyy-MM-dd'/>" name="caiji_date2" class="form-control date" size="10" />
                </li>
                <li><button type="submit" class="btn btn-default btn-sm">查询</button></li>
                <li><a class="btn btn-orange btn-sm" href="javascript:navTab.reload('', {clearQuery:true});">清空查询</a></li>
                <!--  <li>
                    <button type="button" class="btn btn-default btn-sm"onclick="caiji()">采集</button>
               </li> -->
            </ul>
        </div>
    </form>
</div>
<div class="pageContent" >
	<div style="overflow-x:auto;overfolw-y:hidden">
    <table class="j-table list" style="width: 110%;" layoutH="95" >
        <thead id="caijipz_log_head1">
            <tr>
            	<th width="28px" class="center"><input type="checkbox" class="checkboxCtrl j-icheck " group="ids"></th>
                <th orderfield="rz_no" class="center" style="width:20px">序号</th>
                <th orderfield="rz_date" class="center" style="width: 50px">采集时间</th>
                <th orderfield="rz_name" class="center" style="width: 50px">采集项名称</th>
                <th orderfield="rz_caijifs" class="center" style="width: 50px">采集方式</th>
                <th orderfield="rz_cjren" class="center" style="width: 50px">采集人</th>
                <th orderfield="rz_cjliang" class="center" style="width: 50px">采集量</th>
                <th orderfield="rz_cjbz" class="center" style="width: 50px">采集标记</th>
            </tr>
        </thead>
        <tbody id="caijipz_log_body">
            <c:forEach items="${rz_log}" var="l" varStatus="stat">
            	<tr id="${l.id}" >
	            	<td align="center"><input type="checkbox" name="ids" class="j-icheck" value="${ l.id}"></td>
	            	<td class="center">${ stat.index + 1}</td>
	            	<td class="center"><fmt:formatDate value="${l.zxdate}" pattern="yyyy-MM-dd"/></td>
	            	<td class="center">${l.name}</td>
	            	<td class="center">${l.tongjilx}</td>
	            	<td class="center">${l.zxren}</td>
	            	<td class="center">${l.zxnum}</td>
	            	<td class="center">${l.sfcw}</td>
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