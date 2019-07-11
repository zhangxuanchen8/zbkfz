<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	
</script>
<div class="pageHeader">
	<form  id="pagerForm" name="formtable"  rel="pagerForm" action="<%=basePath%>backstage/zhibiao/commonzhibiao" onsubmit="return dwzSearch(this,'dialog');"  method="POST">
		<input hidden="hidden" id="cpageNum" name="pageNum" value="${pageNum}">
		<input hidden="hidden" id="cpageSize" name="numPerPage" value="${numPerPage }">
		<input hidden="hidden" id="supunit" name="supunit" value="${supunit}">
		 <div class="searchBar">
		    <ul class="searchContent">
		      <li><label>类别:</label>
		      <select   class="form-control" name="category">
    	  	   <option value=""></option>
          	   <option value="医师" <c:if test="${category eq '医师'}">selected="selected"</c:if>>医师</option>
          	   <option value="药师" <c:if test="${category eq '药师'}">selected="selected"</c:if>>药师</option>
          	   <option value="护理" <c:if test="${category eq '护理'}">selected="selected"</c:if>>护理</option>
          	   <option value="技师" <c:if test="${category eq '技师'}">selected="selected"</c:if>>技师</option>
          	   <option value="通用" <c:if test="${category eq '通用'}">selected="selected"</c:if>>通用</option>
          	 </select>
		      </li>
		       <li><label>名称:</label><input  type="text" value="${name}" name="name" class="form-control" size="10" /></li>
		      <li><button type="submit" class="btn btn-default btn-sm">查询</button></li>
		      <li class="pull-right">
		      <button  onclick="yinruzbc()" type="button" class="btn btn-default btn-sm">引入</button>
		      <a id="yinruzbc" href="<%=basePath%>/backstage/zhibiao/zhibiaoyinru?pid=${supunit}&list="  target="ajaxToDo" mask="true"   callback="dialogAjaxDone" style="display:none"  >引入操作</a>
		      <button id="yinruclose" type="button" class="btn btn-close btn-sm">关闭</button>
		    </ul>
		 </div>
	</form>
	</div>
	<div class="pageContent" >
	<table  id="addzhibiao_table"class="table table-bordered table-hover table-striped table-top" layoutH="70" targetType="dialog" >
        <thead id="addzhibiao_head">
            <tr>
                <th class="center" width="28"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
                <th orderfield="" class="center" >项目名</th>
                <th orderfield="" class="center" width="60px">职称类别</th>
                <th orderfield="" class="center" width="150px">一级分类</th>
                <th orderfield="" class="center" width="150px">二级分类</th>
				<th orderfield="" class="center" width="65px">评分</th>
				<th orderfield="" class="center" width="150px">指标评分说明</th>
            </tr>
        </thead>
        <tbody id="addzhibiao_body">
            <c:forEach items="${zhibiao_wh_list}" var="l" varStatus="stat">
            	<tr align="center" id="${l.id}" >
            	<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.getId()}"></td>
            	<td align="center">${l.zbkmc}</td>
            	<td align="center">${l.category}</td>
            	<td align="center">${l.yjfl}</td>
            	<td align="center">${l.ejfl}</td>
            	<td align="center">${l.score}</td>
            	<td align="center">${l.item_desc}</td>
            	</tr>
            </c:forEach>
        </tbody>
	</table>
    <div class="panelBar">
        <div class="pages">
            <span>每页&nbsp;</span>
            <span class="sel">
                <select  class="combox" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="dialogPageBreak({pageNum:1,numPerPage:this.value})">
                    <option value="10" <c:if test="${numPerPage == '10' }">selected='selected'</c:if>>10</option>
                    <option value="20" <c:if test="${numPerPage == '20' }">selected='selected'</c:if>>20</option>
                    <option value="50" <c:if test="${numPerPage == '50' }">selected='selected'</c:if>>50</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount}条</span>
        </div>
        <div class="pagination-box"  targetType="dialog" totalcount="${totalcount}" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}">
      </div>
    </div>
    </div>
<script>
function yinruzbc(){
// 	var list="[";
	var list="";
	for(var i=0;i<addzhibiao_body.rows.length;i++){
		if(addzhibiao_body.rows[i].cells[0].getElementsByTagName("input")[0].checked){
		list+=addzhibiao_body.rows[i].cells[0].getElementsByTagName("input")[0].value+",";
		}
	}
// 	list+="]";
	$("#yinruzbc").attr("href","<%=basePath%>/backstage/zhibiao/zhibiaoyinru?pid=${supunit}&list="+list);
	$("#yinruzbc").click();
}
function leisubmit(){
//	$("#zhibiaolei").val($("#category").attr("value"));
	$("form[name='formtable']").submit();
}
</script>