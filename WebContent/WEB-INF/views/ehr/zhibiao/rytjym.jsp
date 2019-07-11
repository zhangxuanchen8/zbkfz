<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="<%=basePath%>static/js/LodopFuncs.js"></script>
<script  src="<%=basePath%>static/jqprint/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
var LODOP; //声明为全局变量     
function dayin(n) {//打印
	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
	LODOP.PRINT_INIT(0,0,"210mm","297mm","量化指标");
	LODOP.SET_PRINT_PAGESIZE(1,0,0,"2*4");
	LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT","Width:100%;Height:100%");
	LODOP.ADD_PRINT_HTM("98%",370,370,390,"<font style='font-size:12px'><span tdata='pageNO'>第##页</span>/<span tdata='pageCount'>共##页</span></font>");
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	if(n==2){
		LODOP.ADD_PRINT_TABLE(40,40,40,1000,'<table width="650"  height="1150"  border="1" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;">'+$("#rootTable11").html()+'</table>');
	    LODOP.SAVE_TO_FILE("量化考核指标.xlsx");
    }else{
    	
    	LODOP.ADD_PRINT_HTM(40,40,40,1000,'<table width="650"  height="1150"  border="1" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;">'+$("#rootTable11").html()+'</table>');
    	//LODOP.PRINT_DESIGN();
    	//console.log();
    	
    	LODOP.PREVIEW();
    }
}
function print_dayin(){
	var html='';
	var Hhtml=$("#createzbkHead").html();
	var s  = '<th orderfield="" class="center" width=""></th>';
	s += '<th orderfield="" class="center" width="60px">序号</th>';
	s+='<th orderfield="" class="center" width="60px">姓名</th>';
	s+='<th orderfield="" class="center" width="60px">分数</th>';
	s+='<th orderfield="" class="center" width="120px">身份证号码</th>';
	s+='<th orderfield="" class="center" width="150px">上传时间</th>';
	s+='<th orderfield="" class="center" width="70px">类别</th>';
	var leng = $("#createzbkBody tr").length; 
	var Bhtml='';
	html+=Hhtml;
	for(var i=0;i<leng;i++){
		Bhtml=$("#createzbkBody").find("tr").eq(i).html();
		html+="<tr>"+Bhtml+"</tr>";
	}
	
	html="<table border='1' cellspacing='0' cellpadding='0'>"+html+"<table/>"
	var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
	LODOP. SET_PRINT_PAGESIZE (2, 0, 0,"A4");
	
	LODOP.ADD_PRINT_TABLE(0,0, "100%","100%",html);
}
function synergy(){
	 	synergyBefore();
	 }
function synergyBefore(){
	$("#xietong").attr("href",$("#xietong").attr("data-href")+$("#tjymsx").val());
	$("#xietong").click();
}
function editMenu(menuid){
	$("#menuEditBt").attr("href","<%=basePath%>backstage/zhibiao/xgzhibiao?id="+menuid);
	$("#menuEditBt").click();
}
function shengcheng(){
	<%-- //$("#menuEditBt").attr("href","<%=basePath%>backstage/zhibiao/xgzhibiao?id="+menuid); --%>
	$("#shengcheng").click();
}

function shangchuan(){
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
		alertMsg.error("未选择一条！");
		return;
	}else{
		$.ajax({
			async:false,
			cache:false,
			url:"<%=basePath%>/backstage/zhibiaogl/qrtjs?ids="+id,
			type : "POST",
			dataType:"json",
			success : function(data) {
				if(data.statusCode=='200'){
					alertMsg.correct("上传成功");
		 			}else{
		 				alertMsg.error(data.message);
		 			}
		 		}
			});
	}
}
function shouhui(){
	var id = $("#tjymsx").val();
	$.ajax({
		async:false,
		cache:false,
		url:"<%=basePath%>/backstage/zhibiao/shouhui?id="+id,
		type : "POST",
		dataType:"json",
		success : function(data) {
			if(data.statusCode=='200'){
				alertMsg.correct("收回成功");
	 			}else{
	 				alertMsg.error("操作失败");
	 			}
	 		}
		});
}
</script>
<a href="" download="量化考核指标.xlsx" id="lianghuakh"></a>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return divSearch(this, 'menuref00');" action="<%=basePath %>/backstage/zhibiaogl/index1?supunit=${tjymsx}" method="post">
        <input type="hidden" id="tjymsx" name="tjymsx" value="${tjymsx}">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
        <div class="searchBar">
		        <ul class="searchContent">
              <%--  <li><label>名称:</label><input id="menulist_name" type="text" value="${name}" name="name" class="form-control" size="10" /></li>
                <li><button id="" type="submit" class="btn btn-default btn-sm">查询</button></li>
                <li><a class="btn btn-orange btn-sm" href="javascript:navTab.reload('', {clearQuery:true});">清空查询</a></li> --%>
                <li class="pull-right">
                    <div class="btn-group">
                        <ul class="searchContent">
                         <c:if test="${flag!='Y'}">
                        <c:if test="${type!='all'}">
				    	<li><a href="<%=basePath%>/backstage/zhibiaogl/qrtj" postType="string" rel="ids" class="btn btn-red btn-sm" target="selectedTodo" title="确定要提交这些信息吗？" <c:if test="${sjzbk.synergy!='Y' }">disabled="disabled" </c:if>>提交</a></li>
                        </c:if>
                        <c:if test="${type=='all'}">
                        <a href="javascript:void(0)" class="btn btn-default btn-sm" onClick="expTable('emp_list_head','createzbkBody','lianghuakh')"><span style="color: green;">导出</span></a>
                        <a href="javascript:void(0)" class="btn btn-default btn-sm" onclick="print_dayin();LODOP.PREVIEW();" style="margin-right:15px;"><span style="color: green;">打印</span></a>
                        <button type="button" class="btn btn-default btn-sm" onclick="shouhui()"  style="float:right;margin-right:15px;" <c:if test="${sjzbk.synergy!='Y' }">disabled="disabled" </c:if>>收回</button>
                        <a data-href="<%=basePath%>/backstage/zhibiao/beforeSynergy?dinggao="  id="xietong" target="dialog"  mask=true   class="btn btn-default btn-sm"  width="450" height="150" title="请先选择打分截止时间"   style="float:right;margin-right:15px;display: none;" >协同</a> 
                         <li><a href="<%=basePath%>backstage/model/matchForm"  class="btn btn-default btn-sm"  target="dialog" width="800" height="500" mask="true" rel="modelMatchForm">人员分配</a></li>
                         <a  onclick="synergy()"   mask=true   class="btn btn-default btn-sm"  width="450" height="150" title="请先选择打分截止时间"   style="float:right;margin-right:15px;"  <c:if test="${dinggaoflag!='Y' || isdinggao<0||isdinggao==null || sjzbk.synergy=='Y'}">disabled="disabled" </c:if>>协同</a> 
                        
                        <li><button onclick="shangchuan()" class="btn btn-red btn-sm">上传省平台</button></li>
                        </c:if>
                        </c:if>
                        <c:if test="${flag=='Y'}">
                         <li><button onclick="shangchuan()" class="btn btn-red btn-sm">上传省平台</button></li>
                         </c:if>
                        </ul>
                    </div>
                </li>
            </ul>
		</div>
    </form>
</div>
<div class="pageContent" >
	<div style="width:100%;overflow-x:auto;overfolw-y:auto">
		<table  class="table table-bordered table-hover table-striped table-top" layoutH="70" rel="menuref00" id="rootTable11">
        <thead id="emp_list_head">
            <tr id="createzbkHead">

                <th class="center" width="28"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
                <th orderfield="" class="center" >序号</th>
                <th orderfield="" class="center" >姓名</th>
                <th orderfield="" class="center" >总分</th>
                 <c:if test="${type=='all'}">
                <c:forEach items="${zbkus}" var="l" varStatus="stat">
                <th orderfield="" class="center" style="width: 70px">${l.item}</th>
                </c:forEach>
                	</c:if>	
                <th orderfield="" class="center" >身份证号码</th>
                <c:if test="${type!='all'}">
                <th orderfield="" class="center" >提交时间</th>
                </c:if>
                <c:if test="${type=='all'}">
                <th orderfield="" class="center" >上传时间</th>
                </c:if>
                <th orderfield="" class="center" >类别</th>
                
            </tr>
        </thead>
        <tbody id="createzbkBody">
            <c:forEach items="${score}" var="l" varStatus="stat">
            	<tr align="center" id="" >
            	<td align="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.getId()}"></td>
            	<td align="center">${ stat.index + 1}</td>
            	<td align="center">${l.xm}</td>
            	<td align="center"><fmt:formatNumber type="number" maxFractionDigits="2" value="${l.finlscore}" /></td>
            	 <c:if test="${type=='all'}">
            	<c:forEach items="${l.zbks}" var="s" varStatus="stat">
                <td align="center"><fmt:formatNumber type="number"   maxFractionDigits="2" value="${s.scorep}" /></td>
                </c:forEach>
                <c:forEach step="1" begin="${l.zbks.size()}" end="${zbkus.size()-1}">
                <td align="center"></td>
                </c:forEach>
                </c:if>
            	<td align="center">${l.idcard}</td>
            	<td align="center">
            	<c:if test="${type!='all'}">
            	<fmt:formatDate value="${l.year}" pattern="yyyy-MM-dd"/> 
            	</c:if>
            	</td>
            	<td align="center">${l.leixing}</td>
            	<tr>
            </c:forEach>
        </tbody>
		</table>
		</div>
		</div>
    <div class="panelBar">
        <div class="pages">
            <span>每页&nbsp;</span>
            <span class="sel">
                <select id="menulist_changenumPerPage" class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({pageNum:1,numPerPage:this.value},'menuref00')">
                   <%--  <option value="30" <c:if test="${numPerPage == '30' }">selected='selected'</c:if>>30</option>
                    <option value="60" <c:if test="${numPerPage == '60' }">selected='selected'</c:if>>60</option>
                    <option value="100" <c:if test="${numPerPage == '100' }">selected='selected'</c:if>>100</option> --%>
                    <option value="1000" <c:if test="${numPerPage == '1000' }">selected='selected'</c:if>>1000</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount}条</span>
        </div>
        <div class="pagination-box" rel="menuref00" targettype="navTab" totalcount="${totalcount}" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}">
        </div>
    </div>
    
    </div>