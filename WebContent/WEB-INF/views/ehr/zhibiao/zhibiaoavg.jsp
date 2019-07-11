<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function editMenu(menuid){
	$("#menuEditBt").attr("href","<%=basePath%>backstage/zhibiao/xgzhibiao?id="+menuid+"&isdinggao="+"${isdinggao}");
	$("#menuEditBt").click();
}
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
		$("#addtreedept").attr("href",$("#addtreedept").attr("data-href")+"?ids="+id);
		$("#addtreedept").click();
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
		$("#addgrouptreedept").attr("href",$("#addgrouptreedept").attr("data-href")+"?ids="+id);
		$("#addgrouptreedept").click();
	}
}
function fuzhi(){
	var obj = document.getElementsByName("ids");
	var i=0;
	var id="";
	for(k in obj){
        if(obj[k].checked){
        	id=obj[k].value;
        	 i++; 
        }
    }
	if(i==0){
		alertMsg.error("至少选择一条！");
		return;
	}else if(i>1){
		alertMsg.error("只可选择一条！");
		return;
	}else{
		$("#fuzhi").attr("href",$("#fuzhi").attr("data-href")+"?id="+id);
		$("#fuzhi").click();
	}
}

</script>
<div id="shade1" style="position:absolute;z-index:2;width:100%;height:100%;display:none;background-color:#000;opacity:0.3;filter:alpha(opacity=30);">
</div>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return divSearch(this, 'avgscore');" action="<%=basePath %>/backstage/zhibiao/avgindex?relFlag=Y" method="post">
    	<input type="hidden" id ="avgscore" name="avgscore" value="${avgscore}">
    	<input type="hidden" id ="supunit" name="supunit" value="${supunit}">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="shangji" value="1">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
        <div class="searchBar">
		        <ul class="searchContent">
               <%-- <li><label>名称:</label><input id="menulist_name" type="text" value="${name}" name="name" class="form-control" size="10" /></li> --%>
               <!--  <li><button id="menusubmit" type="submit" class="btn btn-default btn-sm">查询</button></li>
                <li><a class="btn btn-orange btn-sm" href="javascript:navTab.reload('', {clearQuery:true});">清空查询</a></li> -->
                <li class="pull-right">
                    <div class="btn-group">
						<a href="<%=basePath%>backstage/zhibiao/matchForm"  class="btn btn-default btn-sm"  target="dialog" width="800" height="500" mask="true" rel="modelMatchForm" style="margin-right:15px;">计算平均分</a>
						<button onclick="fuzhi()" type="button" class="btn btn-red btn-sm" style="float:right;margin-right:15px;" >赋值</button>
                        <a href="<%=basePath%>/backstage/zhibiao/delzhibiaopj" postType="string" rel="ids" class="btn btn-red btn-sm" target="selectedTodo" title="确定要删除这些信息吗？" mask="true" fresh="true" style="margin-right:15px;" >删除</a>
                    	<a data-href="<%=basePath%>/backstage/zhibiao/fuzhi" id="fuzhi" postType="string"  target="dialog"  mask=true   class="btn btn-default btn-sm"  width="650" height="250" style="margin-right:15px;display: none;" >赋值</a> 
                    </div>
                </li>
            </ul>
		</div>
    </form>
</div>
<div class="pageContent" >
	<div style="width:100%;overflow-x:auto;overfolw-y:auto">
		<table  class="table table-bordered table-hover table-striped table-top" layoutH="70" rel="avgscore">
        <thead id="gongzuolyc_head">
            <tr>
                <th class="center" width="28"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>

                <th orderfield="" class="center" width="45px">指标名</th>
                <th orderfield="" class="center" width="75px">指标总分</th>
				<th orderfield="" class="center" width="60px">总人数</th>

				<th orderfield="" class="center" width="75px">平均分数</th>
				<th orderfield="" class="center" width="75px">模板名称</th>
				<th orderfield="" class="center" width="75px">计算时间</th>
            </tr>
        </thead>
        <tbody id="gongzuolyc_body">
            <c:forEach items="${avgscorelist}" var="l" varStatus="stat">
            	<tr align="center" id="${l.id}" >
            	<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.getId()}"></td>
            	<td align="center">${l.item}</td>
            	<td align="center">${l.itemscore}</td>
            	<td align="center">${l.number}</td>
            	<td align="center">${l.avgscore}</td>
            	<td align="center">${l.zbkname}</td>
            	<td align="center"><fmt:formatDate value="${l.tiemname}" pattern="yyyy-MM-dd"/> </td>
            	</tr>
            </c:forEach>
        </tbody>
		</table>
		</div>
    <div class="panelBar">
        <div class="pages">
            <span>每页&nbsp;</span>
            <span class="sel">
                <select id="menulist_changenumPerPage" class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({pageNum:1,numPerPage:this.value},'avgscore')">
                    <option value="20" <c:if test="${numPerPage == '20' }">selected='selected'</c:if>>20</option>
                    <option value="40" <c:if test="${numPerPage == '40' }">selected='selected'</c:if>>40</option>
                    <option value="60" <c:if test="${numPerPage == '60' }">selected='selected'</c:if>>60</option>
                    <option value="100" <c:if test="${numPerPage == '100' }">selected='selected'</c:if>>100</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount}条</span>
        </div>
        <div class="pagination-box" rel="avgscore" targettype="navTab" totalcount="${totalcount}" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}">
        </div>
    </div>
    
    </div>