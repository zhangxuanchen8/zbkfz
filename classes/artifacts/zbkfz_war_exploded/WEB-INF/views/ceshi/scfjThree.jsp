
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script>
$(function(){
	var h = document.documentElement.clientHeight || document.body.clientHeight;
    $("#two_xyz").css("height",(h-157));
});
function findzjlist(expertno){
	if(expertno == undefined){
		var n = 0;
		var dat = "";
		$("input[type='checkbox'][name='ids']:checked").each(function(){
			n += 1;
			dat += $(this).val();
		});
		if(!n){
			alertMsg.error("请选择一个对象!");
			return;
		}
		if(n!=1){
			alertMsg.error("只能选择一个对象!");
			return;
		}
		$("#findzjlist").attr("href","<%=basePath %>/titleAppraisal/zgrylist?expertno="+dat+"&unitid=${unitid}&zyz_no=${zyz_no}");
	}else{
    	$("#findzjlist").attr("href","<%=basePath %>/titleAppraisal/zgrylist?expertno="+expertno+"&unitid=${unitid}");
	}
	$("#findzjlist").click();
}
</script>
<div class="pageHeader">

    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath %>titleAppraisal/zgrylist?"  method="POST">
    	<input type="hidden" id="zyz_no_t" name="zyz_no" value="${zyz_no }">
    	<input type="hidden" id="unitid_t" name="unitid" value="${unitid }">
        <div class="searchBar">
            <ul class="searchContent">
                 <li class="pull-right">              	
                 	<button onclick="findzjlist()" class="btn btn-default btn-sm" >分配</button>
                    <a href="<%=basePath %>/titleAppraisal/zgrylist?" class="btn btn-default btn-sm" rel="title_addzgry"
                    	id="findzjlist" style="display:none" target="dialog" width="1200" height="550"  mask="true"></a>
                 </li>
            </ul>
        </div>
    </form>
</div>
<div class="pageContent">
    <div id="two_xyz" style="width:100%;overflow-x:auto;overfolw-y:hidden">
    <table id="table_res" class="table table-bordered table-hover table-striped table-top"  layoutH="70" >
        <thead id="tpjglr_log_head">
            <tr>
            	<th class="center" style="width: 40px">选择</th>      
            	<th class="center" style="width: 40px">序号</th>            	
            	<th class="center" style="width: 60px">专家名</th>
                <th class="center" style="width: 50px">主管专家</th> 
                <th class="center" style="width: 100px">单位</th>          
                <th class="center" style="width: 40px">设置主管专家</th>          
            </tr>
        </thead>
        <tbody id="tpjglr_log_body">
            <c:forEach items="${zjlist}" var="l" varStatus="stat">
            <c:if test="${l.zyz_zj_expertno != expertno}">
            <tr id='${l.zyz_zj_expertno}' <c:if test="${l.sfzgzj == '是' }">ondblclick="findzjlist('${l.zyz_zj_expertno}')"</c:if>>
            	<td  class="center" ><input type="checkbox" name="ids" class="j-icheck" value="${l.zyz_zj_expertno}"/></td>
            	<td  class="center">${ stat.index + 1}</td>
            	<td  class="center" title="${l.zyz_zj_name}">${l.zyz_zj_name}</td> 
            	<td  class="center" title="${l.sfzgzj }">${l.sfzgzj }</td>
            	<td  class="center" title="${l.zyz_zj_name}">${l.zyz_zj_name}</td>
            	<td  class="center" title="">
            		<c:if test="${l.sfzgzj != '是' }"><button type="button" class="btn btn-success btn-sm" onclick="updateZgzj('${l.zyz_zj_expertno}','Y')">设为主管专家</button></c:if>
            		<c:if test="${l.sfzgzj == '是' }"><button type="button" class="btn btn-danger btn-sm" onclick="updateZgzj('${l.zyz_zj_expertno}','N')">取消主管专家</button></c:if>
            	</td>
            </tr> 
            </c:if>
            </c:forEach>      
        </tbody>
    </table>
    </div>
</div>