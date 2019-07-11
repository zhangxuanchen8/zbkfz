<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function impDeclareRy(){
	var file = $(":file");  
	file.after(file.clone().val(""));  
	file.remove();
	$('#DeclareRyFile').click();
}

function DeclareRyFileLoad(obj){
	if(!obj.files) {
        return;
    }
	var f = obj.files[0];
	fileName = f.name;
	if(fileName.lastIndexOf(".")!=-1){
	    var fileType = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();
	    if(fileType!="xls"&&fileType!="xlsx"){
	    	alertMsg.error("不支持文件类型"+fileType+"请选择xls或xlsx文件");
	        document.getElementById("impemp_file").value="";
	        return;
	    }
	 }else{
		 alertMsg.error("请选择要上传的xls文件!");
		 return;
	 }
	var reader = new FileReader();//---HTML5的读取文件的接口
	reader.readAsBinaryString(f); 
   reader.onload = function(e) {
	   var data = e.target.result;
	   var data = e.target.result;
		if(false) {
			wb = XLSX.read(btoa(fixdata(data)), {//手动转化
				type: 'base64'
			});
		} else {
			wb = XLSX.read(data, {
				type: 'binary'
			});
		}
		var contents = JSON.stringify( XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]) );
		var content = JSON.parse(contents.replace(/\s/g,""));
		htmls = "";
   }	
}


function szsj(s) {
	$.ajax({
		async:false,
		cache:false,
		url:"<%=basePath%>cpuser/fjdate?gbbj="+s,
		type : "post",
		success : function(data) {
				if(data==1){
					alert("关闭成功");
				}else{
					alert("关闭失败");
				}
		}
	});
}
</script>

<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>/backstage/declare/ryindex"  method="POST">
      <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
        <input type="hidden" name="scbj" value="${scbj}">
         <input type="hidden" name="option02" value="${option02}">
         <input type="hidden" name="prqx" value="${prqx}">
         <input type="hidden" name="flag" value="${flag}">
         <input type="hidden" name="statu" value="${statu}">
          <input type="hidden" name="biaozhi" value="${biaozhi}">
    <!--   <input style="display:none ;" type="file" id="DeclareRyFile" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"  onchange="DeclareRyFileLoad(this)"> -->
    <div class="searchBar">
     <ul class="searchContent">
   <li><label>姓名：</label><input type="text" value="${name}" name="name" class="form-control" size="10" /></li>
      <li><button type="submit" class="btn btn-default btn-sm">查询</button></li>  
      <c:if test="${biaozhi!='Y' }">
      <li class="pull-right">
      <a id='' href="<%=basePath%>backstage/declare/fjscym?statu=${statu}" class='btn btn-red btn-sm' target='dialog'  width="450" height="150" title="附件上传截止时间" style="margin-right:15px;">开启填报</a>
       <button id="" type="button" class="btn btn-red btn-sm" style="float:right;margin-right:15px;" onclick="szsj('N')">结束填报</button>
       </li>
       <li class="pull-right">
     <a id='' href='backstage/declare/impDialog' class='btn btn-green btn-sm' target='dialog' width="1000" height="800" max="true" rel="rewards1" >批量导入</a>
     <a href="<%=basePath%>/backstage/declare/delper" postType="string" rel="ids" class="btn btn-red btn-sm" target="selectedTodo" title="确定要删除这些信息吗？" mask="true" fresh="true" >删除</a>
      </li>
      </c:if>
     </ul>
    </div>
    </form>
</div>
<div class="pageContent" >
<div style="width:100%;overflow-x:auto;overfolw-y:hidden">
<table class="table table-bordered table-hover table-striped table-top" width="100%" layoutH="74" rel="userAddForm">
	<thead>
		<tr align="center">
			<th class="center" width="40px"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
			<th class=" center" width="40px">序号</th>
            <th class=" center"  width="65px">姓名</th>
            <th class=" center" width="40px">性别</th>
			<th class=" center" width="150px">身份证</th>
			<th class=" center" width="80px">推荐职务</th>
			<th class=" center" width="150px">专业</th>
			<th class=" center" width="230px">量化模板名称</th>
			<c:if test="${option02==null || option02==''}">
			<th class=" center" >附件数</th>
			<th class=" center" >截止时间</th> 
			<th class=" center" >开启时间</th>
			<th class=" center" width="70px">填报标记</th>
			</c:if>
			<c:if test="${flag!=null &&flag!=''}">
			<th class=" center" >起聘时间</th> 
			<th class=" center" >聘期</th>
			<th class=" center" >聘任结束时间</th>
			</c:if>
         	</tr>
     </thead>
     <tbody >
     <c:forEach items="${page.results}" var="l" varStatus="stat">
     <tr>
   	<td class="center"><input type="checkbox" name="ids" class="j-icheck" value="${l.getRecordno()}"></td>
 	<td class="center">${stat.index + 1}</td>
     <td class="center">${l.xm}</td>
     <td class=" center">${l.xb}</td>
     <td class="center">${l.idcard}</td>
     
     <td class="center">${l.tjhrzzg}</td>
     <td class="center">${l.zymc}</td>
     <td class="center">${l.name}</td>
     <c:if test="${option02==null || option02==''}">
     <td class="center">${l.fjsize}</td>
     <td class="center">${l.tjdate}</td>
     <td class="center">${l.kqdate}</td>
     <td class="center">
     	<c:if test="${l.scbj eq 'Y'}"><font color="green" style="font-weight:bold">√</font></c:if>
     </td>
     </c:if>
     <c:if test="${flag!=null &&flag!=''}">
			<td class="center" ><fmt:formatDate value="${l.qpsj}" pattern="yyyy-MM-dd"/></td> 
			<td class="center" >${l.prqx}</td>
			<td class="center" >${l.ssss}</td>
	</c:if>
     </tr>
     </c:forEach>
     </tbody>
</table>
</div>
 <div class="panelBar">
        <div class="pages">
            <span>每页&nbsp;</span>
            <span class="sel">
                <select id="menulist_changenumPerPage" class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({pageNum:1,numPerPage:this.value})">
                    <option value="30" <c:if test="${numPerPage == '30' }">selected='selected'</c:if>>30</option>
                    <option value="60" <c:if test="${numPerPage == '60' }">selected='selected'</c:if>>60</option>
                    <option value="100" <c:if test="${numPerPage == '100' }">selected='selected'</c:if>>100</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount}条</span>
        </div>
        <div class="pagination-box"  targettype="navTab" totalcount="${totalcount}" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}">
        </div>
    </div>
</div>