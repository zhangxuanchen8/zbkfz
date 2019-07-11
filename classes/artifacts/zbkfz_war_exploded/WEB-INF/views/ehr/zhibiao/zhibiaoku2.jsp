<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function editMenu(menuid){
	$("#menuEditBt").attr("href","<%=basePath%>backstage/zhibiao/xgzhibiao?id="+menuid);
	$("#menuEditBt").click();
}

function yinru() {
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/zhibiaogl/yinru",
	type : "get",
	success : function(data) {
		$("#main_treeDemo_19_2_a").attr("href","<%=basePath%>backstage/zhibiaogl/getpeopleTree1");
		$("#main_treeDemo_19_2_a").click();
		$("#zhibiaoTree_1_a").attr("href","<%=basePath%>backstage/zhibiaogl/getpeopleTree1");
		$("#zhibiaoTree_1_a").click();
	}
});
}
function dinggao() {
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/zhibiao/dinggao?dinggao=1",
	type : "get",
	success : function(data) {
		alert("已定稿！");
	}
});
}
function shengcheng(){
	<%-- //$("#menuEditBt").attr("href","<%=basePath%>backstage/zhibiao/xgzhibiao?id="+menuid); --%>
	$("#shengcheng").click();
}
</script>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return divSearch(this, 'menuref2');" action="<%=basePath %>/backstage/zhibiao/index" method="post">
    	<input type="hidden" id ="zhib" name="zhib" value="${zhibiao}">
    	<input type="hidden" id ="supunit" name="supunit" value="${supunit}">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
    	<input type="hidden" id ="supunit" name="supunit" value="${supunit}">
        <div class="searchBar">
		        <ul class="searchContent">
               <li><label>名称:</label><input id="menulist_name" type="text" value="${name}" name="name" class="form-control" size="10" /></li>
                <li><button id="menusubmit" type="submit" class="btn btn-default btn-sm">查询</button></li>
                <li><a class="btn btn-orange btn-sm" href="javascript:navTab.reload('', {clearQuery:true});">清空查询</a></li>
                <li class="pull-right">
                    <div class="btn-group">
                    	 <!-- <a href="" class="btn btn-red btn-sm" onclick="yinru()">引入</a> -->
                    	 <button type="button" class="btn btn-default btn-sm"onclick="shengcheng()" <c:if test="${supunit ne 0000}">disabled="disabled"</c:if>>生成</button>
                    	<td align="right"><button type="button" class="btn btn-default btn-sm"onclick="yinru()">引入</button></td>
                    	<td align="right"><button type="button" class="btn btn-default btn-sm"onclick="dinggao()">定稿</button></td>
                    	<a id="shengcheng" href="<%=basePath%>/backstage/zhibiaogl/yulan" class="btn btn-default btn-sm" target="dialog" mask="true" width="910" height="640" style="margin-right:15px;display: none" >医师类量化表</a>   
                        <a href="<%=basePath%>/backstage/zhibiao/xgzhibiao" id="menuEditBt" mask="true" width="650" height="250"  style="display: none" target="dialog">编辑</a>
                        <a href="<%=basePath%>backstage/zhibiao/getHospitalTree" id="menuEditBt1" style="display: none" postType="string" target="navTab" >编辑</a>
                        <a href="<%=basePath%>backstage/zhibiao/getHospitalTree" id="menuEditBt2" style="display: none" postType="string" target="ajax" >编辑</a>
                        <%-- <a href="<%=basePath%>/backstage/zhibiao/addhospitalform?supunit=${supunit}" class="btn btn-default btn-sm" target="dialog" mask="true" width="650" height="250" style="margin-right:15px;">新增</a>          
				    	<a href="<%=basePath%>/backstage/zhibiao/delzhibiao" postType="string" rel="ids" class="btn btn-red btn-sm" target="selectedTodo" title="确定要删除这些信息吗？"  >删除</a> --%>
                    </div>
                </li>
            </ul>
		</div>
    </form>
</div>
<div class="pageContent" >
	<div style="width:100%;overflow-x:auto;overfolw-y:auto">
		<table  class="table table-bordered table-hover table-striped table-top" layoutH="70" rel="menuref2">
        <thead>
            <tr>

                <th class="center" width="28"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
                 <th orderfield="" class="center" >指标编号</th>
                <th orderfield="" class="center" ></th>
                <th orderfield="" class="center" >合计类型</th>
				<th orderfield="" class="center" >指标名称</th>
				<th orderfield="" class="center" >参考分值</th>
				<th orderfield="" class="center" >分值</th>
				<th orderfield="" class="center" >指标评分说明</th>

            </tr>
        </thead>
        <tbody>
            <c:forEach items="${zhibiao}" var="l" varStatus="stat">
            	<tr align="center" id="${l.i_id}" ondblclick="editMenu('${l.i_id}')">
            	<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.getI_id()}"></td>
            	<td align="center">${l.i_id}</td>
            	<td align="center" width = 40px>
            	<c:if test="${l.is_using eq '0'}"><font color="red" style="font-weight:bold">×</font></c:if>
            	<c:if test="${l.is_using eq '1'}"><font color="green" style="font-weight:bold">√</font></c:if>
            	</td>
            	<td align="center">
				<c:if test="${l.is_max eq '0'}"><font style="font-weight:bold">取累加值</font></c:if>
            	<c:if test="${l.is_max eq '1'}"><font  style="font-weight:bold">取最高值</font></c:if>
				</td>
            	<td align="left">${l.item}</td>
            	<td align="center">${l.score}</td>
            	<td align="center">${l.finlscore}</td>
            	<td align="center">${l.item_desc}</td>
            	<tr>
            </c:forEach>
           

        </tbody>
		</table>
		</div>
    <div class="panelBar">
        <div class="pages">
            <span>每页&nbsp;</span>
            <span class="sel">
                <select id="menulist_changenumPerPage" class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({pageNum:1,numPerPage:this.value},'menuref2')">
                    <option value="20" <c:if test="${numPerPage == '20' }">selected='selected'</c:if>>20</option>
                    <option value="40" <c:if test="${numPerPage == '40' }">selected='selected'</c:if>>40</option>
                    <option value="60" <c:if test="${numPerPage == '60' }">selected='selected'</c:if>>60</option>
                    <option value="100" <c:if test="${numPerPage == '100' }">selected='selected'</c:if>>100</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount}条</span>
        </div>
        <div class="pagination-box" rel="menuref2" targettype="navTab" totalcount="${totalcount}" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}">
        </div>
    </div>
    
    </div>