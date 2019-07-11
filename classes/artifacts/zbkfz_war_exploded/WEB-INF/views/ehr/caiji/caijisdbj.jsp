<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link href="<%=basePath%>city-picker/docs/css/city-picker.css" rel="stylesheet"/>

<script type="text/javascript" src="./jquery.js"></script>
<script src="<%=basePath%>city-picker/docs/js/city-picker.data.js"></script>
<script src="<%=basePath%>city-picker/docs/js/city-picker.js"></script>
<script type="text/javascript">
	var allflag = false;
$(function(){
		document.getElementById("xzsj1").style.display="none";
		document.getElementById("xzsj2").style.display="none";
		document.getElementById("ccdate").style.display="none";
		document.getElementById("month").style.display="none";

});
function gettime1(s){
	if(s==2){
	document.getElementById("ccdate").style.display="inline";
	document.getElementById("xzsj1").style.display="inline";
	document.getElementById("xzsj2").style.display="none";
	document.getElementById("month").style.display="none";
	$("#month").val("");
	}else if(s==1){
		document.getElementById("xzsj2").style.display="inline";
		document.getElementById("month").style.display="inline";
		document.getElementById("ccdate").style.display="none";
		document.getElementById("xzsj1").style.display="none";
		$("#ccdate").val("");
	}
}

function caijiquanbu(){
    if(allflag){
        allflag=false;
        $("#allid").val($("#oldoldid").val());
        //document.getElementById("all").innerHTML = "✔";
	}else{
        allflag=true;
        $("#allid").val("RoyalNeverGiveUp");
        //document.getElementById("all").innerHTML = "×";
	}


}
</script>
<form action="<%=basePath %>backstage/shujuljdy/sdCaiJi" class="pageForm form-validate" method="post" callback="dialogAjaxDoneds" noEnter>
	<input type="hidden" name="id" id="allid" value="${cjpz.id}">
	<input type="hidden" id="oldoldid" value="${cjpz.id}">
	<div class="pageFormContent" layouth="26">
					<p><label for="caijimc"
						class="control-label" style="width: 80px;">采集名称：</label>
						<select name="caijimc" id="caijimc" style="width: 180px" class="form-control " disabled="disabled">
                            <option value="0">==请选择==</option>
                            <c:forEach items="${zblist}" var="l" varStatus="stat">
                                <option value="${l.zbkname}" disabled="disabled" <c:if test="${l.zbkname==cjpz.caijimc}">selected</c:if> > ${l.zbkname}</option>
                            </c:forEach>
                        </select>
                        </p>
                        <p>
							<label for="caijimc"
								   class="control-label" style="width: 80px;">采集全部：</label><input type="checkbox" onclick="caijiquanbu()" />
						</p>
					<p><label for="date_sources"
						class="control-label" style="width: 80px;" id="ccdate1" name="ccdate1">时间类型：</label>
						<input type="radio" value="0"  name="ccdate1" onclick="gettime1(1)" >月
						<input type="radio" value="1" id="ccdate2" name="ccdate1" onclick="gettime1(2)" >天
                     </p>   
					<p><label for="ccdate"
						class="control-label" style="width: 80px;" id="xzsj1">选择时间：</label>
						<input type="text" id="ccdate" name="ccdate" class="form-control date" 
							size="18" style="width: 180px;" value='<fmt:formatDate value="${cjpz.ccdate}" pattern="yyyy-MM-dd"/>' />
					<label for="ccdate"
						class="control-label" style="width: 80px;" id="xzsj2">选择时间：</label>
						<select name="month" id="month" style="width: 180px" class="form-control " >
                            <option value="0">==请选择==</option>
                            <c:forEach items="${monthcaiji}" var="l" varStatus="stat">
                                <option value="${l}"> ${l}</option>
                            </c:forEach>
                        </select>		
					</p>


					<%-- <p>
						<label for="ccdate"
						class="control-label" style="width: 80px;" id="xzsj2">选择时间：</label>
						<select name="month" id="month" style="width: 180px" class="form-control " >
                            <option value="0">==请选择==</option>
                            <c:forEach items="${monthcaiji}" var="l" varStatus="stat">
                                <option value="${l}"> ${l}</option>
                            </c:forEach>
                        </select>
					</p> --%>
	</div>	
	<div class="formBar">
		<ul>
			<li><button type="submit" class="btn btn-default btn-sm">重新采集</button></li>
			<li><button type="button" class="btn btn-close btn-sm">关闭</button></li>
		</ul>
	</div>
</form>
