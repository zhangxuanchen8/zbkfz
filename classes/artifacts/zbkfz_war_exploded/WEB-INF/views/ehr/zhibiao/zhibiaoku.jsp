<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function editMenu(menuid){
	$("#menuEditBt").attr("href","<%=basePath%>backstage/zhibiao/xgzhibiao?id="+menuid+"&isdinggao="+"${isdinggao}");
	$("#menuEditBt").click();
}
function synergyBefore(){
	$("#xietong").attr("href",$("#xietong").attr("data-href")+$("#supunit").val());
	$("#xietong").click();
}
function shengcheng(){
	<%-- //$("#menuEditBt").attr("href","<%=basePath%>backstage/zhibiao/xgzhibiao?id="+menuid); --%>
	$.ajax({
		async:false,
		cache:false,
		url:"<%=basePath%>backstage/zhibiaogl/yulanmz?id="+$("#supunit").val(),
		type : "get",
		success : function(data) {
			$("#shengcheng").text(data);
		}
	});
	$("#shengcheng").click();
}

function dinggao() {
	$.ajax({
		async:false,
		cache:false,
		url:"<%=basePath%>backstage/zhibiao/dinggao23?dinggao1="+$("#supunit").val(),
		type : "get",
		success : function(data) {
			debugger;
			if(data=="1"){
				alert("还有未设置的末级节点，请使用自动纠错功能！");
				return false;
			}
			var bool = window.confirm("定稿后将不能再进行修改，是否确认定稿？");
			if(bool){
				$.ajax({
					async:false,
					cache:false,
					url:"<%=basePath%>backstage/zhibiao/dinggao?dinggao="+$("#supunit").val(),
					type : "get",
					beforeSend:function(){
						$('#shade').css('display','block');
					},
					success : function(data) {
						$("#shade").css("display","none");
						alertMsg.correct("已定稿");
						$("#pagerForm").submit();
					}
				});
			}else{
				return false;
			}
		}
	});
}

function deldinggao() {
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/zhibiao/deldinggao?dinggao="+$("#supunit").val(),
	type : "get",
	beforeSend:function(){
		$('#shade').css('display','block');
	},
	success : function(data) {
		$("#shade").css("display","none");
		alertMsg.correct("已取消定稿");
		$("#pagerForm").submit();
	}
});
}
 function synergy(){
<%--  	$.ajax({
 	async:false,
 	cache:false,
 	url:"<%=basePath%>backstage/zhibiao/synergy?dinggao="+$("#supunit").val(), 
 	type : "post",
 	beforeSend:function(){
 		$('#shade').css('display','block');
	},
 	success : function(data) {
 		$("#shade").css("display","none"); 
 		if(data!=null&&data!=""){
			data=JSON.parse(data);
 		if(data.message=='200'){
 			synergyBefore();
 			}else{
 				alertMsg.error(data.message);
 			}
 		}
 	}
 }); --%>
 	synergyBefore();
 }
function yinru(){
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/zhibiao/yinru?yryr="+$("#yryear").val()+"&pid="+$("#supunit").val(),
	type : "post",
	success : function(data) {
		if(data=="0"){
			alertMsg.info("请选择对应节点");
		}else{
			alertMsg.info("引入成功");
		}
	}
});
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

function idjc() {
	$.ajax({
		async:false,
		cache:false,
		url:"<%=basePath%>backstage/zhibiao/jiucuo23?dinggao2="+$("#supunit").val(),
		type : "get",
		success : function(data) {
			alertMsg.info("已自动纠错"+data+"条！");
		}
	});
}
function shangchuanzbk() {
	$.ajax({
		async:false,
		cache:false,
		url:"<%=basePath%>backstage/zhibiao/shangchuanzbk?supunit="+$("#supunit").val(),
		type : "get",
		success : function(data) {
			alertMsg.info("上传成功！");
		}
	});
}
</script>
<div id="shade" style="position:absolute;z-index:2;width:100%;height:100%;display:none;background-color:#000;opacity:0.3;filter:alpha(opacity=30);">
</div>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return divSearch(this, 'menuref1');" action="<%=basePath %>/backstage/zhibiao/index?relFlag=Y" method="post">
    	<input type="hidden" id ="zhib" name="zhib" value="${zhibiao}">
    	<input type="hidden" id ="supunit" name="supunit" value="${supunit}">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="shangji" value="1">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
        <div class="searchBar">
		        <ul class="searchContent">
               <li><label>名称:</label><input id="menulist_name" type="text" value="${name}" name="name" class="form-control" size="10" /></li>
                <li><button id="menusubmit" type="submit" class="btn btn-default btn-sm">查询</button></li>
                <li><a class="btn btn-orange btn-sm" href="javascript:navTab.reload('', {clearQuery:true});">清空查询</a></li>
               <%--  <li><input id="zj" type="text" value="总计分数为：${zj}分" name="zj" class="form-control" size="15" disabled="disabled" /></li> --%>
              <li><label></label><select style="width: 160px"name="yryear" id="yryear"  class="form-control">
                			 <option></option> 
                			<c:forEach items="${list_model}" var="l" varStatus="stat">
	                		 <option value="${l.id}">${l.name}</option>
	                	    </c:forEach>
                		     			 </select></li>
               <li><button type="button" class="btn btn-default btn-sm" onclick="yinru()" style="float:right;margin-right:15px;" <c:if test="${isdinggao>0 || isxt>0}">disabled="disabled" </c:if>>引入</button></li>
                <li class="pull-right">
                    <div class="btn-group">
                   		<a data-href="<%=basePath%>/backstage/zhibiao/addtree" id="addtreedept" postType="string"  target="dialog"  mask=true   class="btn btn-default btn-sm"  width="650" height="250" style="margin-right:15px;display: none;" >选定科室</a> 
                   		<a data-href="<%=basePath%>/backstage/zhibiao/addgrouptree" id="addgrouptreedept" postType="string"  target="dialog"  mask=true   class="btn btn-default btn-sm"  width="650" height="250" style="margin-right:15px;display: none;" >选定群组科室</a> 

                    	<a id="shengcheng" href="<%=basePath%>/backstage/zhibiaogl/yulan?supunit=${supunit}" class="btn btn-default btn-sm" target="dialog" max="true" width="910" height="640" style="margin-right:15px;display: none" value="" title=""></a>          

                        <a href="<%=basePath%>/backstage/zhibiao/addhospitalform?supunit=${supunit}" class="btn btn-default btn-sm" target="dialog" mask="true" width="650" height="350" style="margin-right:15px;" <c:if test="${isdinggao>0}">disabled="disabled"</c:if> >新增</a>          
                        <a href="<%=basePath%>/backstage/zhibiao/xgzhibiao" id="menuEditBt" mask="true" width="650" height="350"  style="display: none" target="dialog" <c:if test="${year != null}">disabled="disabled"</c:if>>编辑</a>
                        <a href="javascript:;" class="j-showMoreSearch" rel="tourgroup_list" title="更多选项"><i class="fa fa-angle-double-down"></i></a>
				    	<a href="<%=basePath%>/backstage/zhibiao/delzhibiao" postType="string" rel="ids" class="btn btn-red btn-sm" target="selectedTodo" title="确定要删除这些信息吗？" mask="true" fresh="true" <c:if test="${year != null && year != ''||isdinggao>0 || supunit eq root}">disabled="disabled"</c:if> >删除</a>

                    </div>
                </li>
            </ul>
		</div>
		   	<div class="moreSearch" style="z-index:1;" >
              <%--   <a href="<%=basePath%>/backstage/zhibiao/commonzhibiao?supunit=${supunit}" id="commonzhibiao" class="btn btn-default btn-sm" mask="true" width="1000" height="500"   target="dialog" style="float:right;margin-right:15px;" <c:if test="${supunit eq root || lower_level==0||isdinggao>0}">disabled="disabled" </c:if> >引入指标库</a> --%>
			   	<button onclick="shangchuanzbk()" type="button" class="btn btn-red btn-sm" style="float:right;margin-right:15px;" <c:if test="${dinggaoflag!='Y'}">disabled="disabled"</c:if>>上传省平台</button>
			   	
			  <button type="button" class="btn btn-default btn-sm" onclick="shengcheng()" style="float:right;margin-right:15px;" <c:if test="${dinggaoflag!='Y'  || isxt>0}">disabled="disabled"</c:if>>预览</button>
			 <%-- <a data-href="<%=basePath%>/backstage/zhibiao/beforeSynergy?dinggao="  id="xietong" target="dialog"  mask=true   class="btn btn-default btn-sm"  width="450" height="150" title="请先选择打分截止时间"   style="float:right;margin-right:15px;display: none;" >协同</a> 
			   	<a  onclick="synergy()"   mask=true   class="btn btn-default btn-sm"  width="450" height="150" title="请先选择打分截止时间"   style="float:right;margin-right:15px;" <c:if test="${dinggaoflag!='Y' || isdinggao<0||isdinggao==null || isxt>0}">disabled="disabled" </c:if> >协同</a>  --%>
	        	<button type="button" class="btn btn-default btn-sm" onclick="deldinggao()"  style="float:right;margin-right:15px;" <c:if test="${dinggaoflag!='Y' || isdinggao==null || isxt>0}">disabled="disabled"</c:if>>解除定稿</button>
	        	<button type="button" class="btn btn-default btn-sm" onclick="dinggao()"  style="float:right;margin-right:15px;" <c:if test="${dinggaoflag!='Y' || isdinggao>0 || isxt>0}">disabled="disabled"</c:if>>定稿</button>
	        	<button id="idjc1" type="button" class="btn btn-default btn-sm" style="float:right;margin-right:15px;" onclick="idjc()">自动纠错</button>
        	</div>
    </form>
</div>
<div class="pageContent" >
	<div style="width:100%;overflow-x:auto;overfolw-y:auto">
		<table  class="table table-bordered table-hover table-striped table-top" layoutH="70" rel="menuref1">
        <thead id="gongzuolyc_head">
            <tr>
                <th class="center" width="28"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
                <th orderfield="" class="center" width="45px">排序号</th>
                <th orderfield="" class="center" width="150px">指标名称</th>
                <th orderfield="" class="center" width="75px">合计类型</th>
				<th orderfield="" class="center" width="60px">分值</th>
				<th orderfield="" class="center" width="75px">科室名称</th>
				<th orderfield="" class="center" width="75px">群组名称</th>
				<th orderfield="" class="center" >指标评分说明</th>
				<th orderfield="" class="center" width="75px">是否启用</th>
            </tr>
        </thead>
        <tbody id="gongzuolyc_body">
            <c:forEach items="${zhibiao}" var="l" varStatus="stat">
            	<tr align="center" id="${l.i_id}" ondblclick="editMenu('${l.i_id}')">
            	<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.getI_id()}"></td>
            	<td align="center">${l.xuhao}</td>
            	<td align="left">${l.item}</td>
            	<td align="center">
				<c:if test="${l.is_max eq '0'}"><font style="font-weight:bold">取累加值</font></c:if>
            	<c:if test="${l.is_max eq '1'}"><font  style="font-weight:bold">取最高值</font></c:if>
				</td>
            	<td align="center">${l.score}</td>
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
                <select id="menulist_changenumPerPage" class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({pageNum:1,numPerPage:this.value},'menuref1')">
                    <option value="20" <c:if test="${numPerPage == '20' }">selected='selected'</c:if>>20</option>
                    <option value="40" <c:if test="${numPerPage == '40' }">selected='selected'</c:if>>40</option>
                    <option value="60" <c:if test="${numPerPage == '60' }">selected='selected'</c:if>>60</option>
                    <option value="100" <c:if test="${numPerPage == '100' }">selected='selected'</c:if>>100</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount}条</span>
        </div>
        <div class="pagination-box" rel="menuref1" targettype="navTab" totalcount="${totalcount}" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}">
        </div>
    </div>
    
    </div>