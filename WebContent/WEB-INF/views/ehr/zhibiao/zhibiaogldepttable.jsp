<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function addtree(){
	var obj = document.getElementsByName("ids");
	var i=0;
	var id="";
	for(k in obj){
        if(obj[k].checked){
        	id+=obj[k].value+",";
        	 i++; 
        }
    }
	if(i==0){
		alertMsg.error("至少选择一条指标！");
		return;
	}else{
		$("#addtreedepts").attr("href",$("#addtreedepts").attr("data-href")+"?ids="+id);
		$("#addtreedepts").click();
	}
}
function addGrouptree(){
	var obj = document.getElementsByName("ids");
	var i=0;
	var id="";
	for(k in obj){
        if(obj[k].checked){
        	id+=obj[k].value+",";
        	 i++; 
        }
    }
	if(i==0){
		alertMsg.error("至少选择一条指标！");
		return;
	}else{
		$("#addgrouptreedepts").attr("href",$("#addgrouptreedepts").attr("data-href")+"?ids="+id);
		$("#addgrouptreedepts").click();
	}
}
</script>
<div class="pageHeader">
<form id="pagerForm" onsubmit="return divSearch(this, 'menurefdept');" action="<%=basePath %>/backstage/zhibiaogl/xddept?relFlag=Y" method="post">
<input type="hidden"  name="supunit" value="${supunit}">
<input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
        <a data-href="<%=basePath%>/backstage/zhibiao/addtree" id="addtreedepts" postType="string"  target="dialog"  mask=true   class="btn btn-default btn-sm"  width="650" height="400" style="margin-right:15px;display: none;" >选定科室</a> 
       <a data-href="<%=basePath%>/backstage/zhibiao/addgrouptree" id="addgrouptreedepts" postType="string"  target="dialog"  mask=true   class="btn btn-default btn-sm"  width="650" height="250" style="margin-right:15px;display: none;" >选定群组科室</a> 
         <div class="searchBar">
		        <ul class="searchContent">
		         <li><label>指标项数目:${list_s}</label></li>
		         <li><label>已绑定科室指标项数目:${list_c}</label></li>
		        <li class="pull-right">
		        <div class="btn-group">
		        <a href="<%=basePath%>backstage/zhibiaogl/deptchoose?supunit=${supunit}&flag=Y"  class="btn btn-default btn-sm"  target="dialog" width="850" height="560" mask="true" rel="deptchoose" <c:if test="${dinggaoflag!='Y'}">disabled="disabled"</c:if>>科室分配</a>
		        </div>
		        
		        <li>
		        </li>
		        </ul>
		        </div>
		         </form>
</div>
<div class="pageContent" >
<div style="width:100%;overflow-x:auto;overfolw-y:auto">
<table  class="table table-bordered table-hover table-striped table-top" layoutH="70" rel="menurefdept">
 	<thead >
       <tr>
       <th class="center" width="28"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
        <th orderfield="" class="center" width="45px">排序号</th>
        <th orderfield="" class="center" >指标名称</th>
		<th orderfield="" class="center" width="75px">科室名称</th>
		<th orderfield="" class="center" width="75px">群组名称</th>
		<th orderfield="" class="center" width="180px">指标评分说明</th>
		<th orderfield="" class="center" width="75px">是否启用</th>
       </tr>
       </thead>
        <tbody >
            <c:forEach items="${list_zbk}" var="l" varStatus="stat">
            	<tr align="center" id="${l.i_id}" >
            	<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.i_id}"></td>
            	<td align="center">${stat.index + 1+(pageNum-1)*numPerPage}</td>
            	<td align="left">${l.item}</td>
            	<td align="center">${l.use_dept_name}</td>
            	<td align="center">${l.qzname}</td>
            	<td align="center">${l.item_desc}</td>
            	<td align="center" width = 40px>
            	<c:if test="${l.is_using eq '0'}"><font color="red" style="font-weight:bold">×</font></c:if>
            	<c:if test="${l.is_using eq '1'}"><font color="green" style="font-weight:bold">√</font></c:if>
            	</td>
            	</tr>
            </c:forEach>
        </tbody>
</table>
</div>
  <div class="panelBar">
        <div class="pages">
            <span>每页&nbsp;</span>
            <span class="sel">
                <select id="menulist_changenumPerPage" class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({pageNum:1,numPerPage:this.value},'menurefdept')">
                    <option value="20" <c:if test="${numPerPage == '20' }">selected='selected'</c:if>>20</option>
                    <option value="40" <c:if test="${numPerPage == '40' }">selected='selected'</c:if>>40</option>
                    <option value="60" <c:if test="${numPerPage == '60' }">selected='selected'</c:if>>60</option>
                    <option value="100" <c:if test="${numPerPage == '100' }">selected='selected'</c:if>>100</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount}条</span>
        </div>
        <div class="pagination-box" rel="menurefdept" targettype="navTab" totalcount="${totalcount}" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}">
        </div>
    </div>
</div>