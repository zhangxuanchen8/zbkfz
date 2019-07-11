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
</script>

<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>/backstage/declare/ryview"  method="POST">
           <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
    <div class="searchBar">
     <ul class="searchContent">
      <li><label>工号/姓名：</label><input type="text" value="" name="code" class="form-control" size="10" /></li>
      <li><button type="submit" class="btn btn-default btn-sm">查询</button></li>
     <!--   <li class="pull-right">
      <button class="btn btn-orange btn-sm"  style="margin-right:15px;">批量上传</button> 
     <a id='' href='backstage/declare/impDialog' class='btn btn-green btn-sm' target='dialog' width="1000" height="800" max="true" rel="rewards1" >批量导入</a>
      </li> -->
     </ul>
    </div>
    </form>
</div>
<div class="pageContent" >
<div style="overflow-x:auto;overfolw-y:hidden">
<table class="table table-bordered table-hover table-striped table-top" width="100%" layoutH="74" rel="userAddForm">
	<thead>
		<tr align="center">
			<th class="center" width="40px"><input type="checkbox" class="checkboxCtrl j-icheck" group="ids"></th>
			<th  width="40px">序号</th>
            <th class=" center"  width="75px">姓名</th>
            <th class=" center" width="60px">性别</th>
            <th class=" center" width="70px">出身日期</th>
            <th class=" center" width="70px">政治面貌</th>
			<th class=" center" >身份证号</th>
			<th class=" center" width="60px">学历</th>
			<th class=" center" >申报资格</th>
			<th class=" center" >行政职务</th>
			<th class=" center" >现从事专业</th>
			<th class=" center" >申报年份</th>
         	</tr>
     </thead>
     <tbody >
     <c:forEach items="${page.results}" var="l" varStatus="stat">
     <tr>
   	<td class="center"><input type="checkbox" name="ids" class="j-icheck" value=""></td>
 	<td >${stat.index + 1}</td>
     <td>${l.xm}</td>
     <td>${l.xb}</td>
     <td>${l.csny}</td>
     <td>${l.zzmm}</td>
     <td>${l.idcard}</td>
     <td>${l.xl}</td>
     <td>${l.tjhrzzg}</td>
     <td>${l.xzzw}</td>
     <td>${l.comments}</td>
     <td>${l.year}</td>
     <td></td>
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
                	<option value="10" <c:if test="${numPerPage == '10' }">selected='selected'</c:if>>10</option>
                    <option value="20" <c:if test="${numPerPage == '20' }">selected='selected'</c:if>>20</option>
                    <option value="40" <c:if test="${numPerPage == '40' }">selected='selected'</c:if>>40</option>
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