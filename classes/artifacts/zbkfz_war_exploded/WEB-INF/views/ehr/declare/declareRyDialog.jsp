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
	$('#impcontrInfoFlag').val('0');
	
}

function DeclareRyUpLoad(obj){
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
		 htmls = "";
		 var content = JSON.parse(contents.replace(/\s/g,""));
		 var hosnum = "${hosnum}";
		 var flag = "${flag}";
		 debugger;
		 for(var i=0;i<content.length;i++){
			 htmls+="<tr data-pid='1'>";
			 htmls+="<td class='center' contenteditable='true'  width='75px'>"+(typeof(content[i].姓名) == "undefined"?"":content[i].姓名)+"</td>";
			 htmls+="<td class='center' contenteditable='true'  width='60px'>"+(typeof(content[i].性别) == "undefined"?"":content[i].性别)+"</td>";
			 /* htmls+="<td class='center' contenteditable='true'  width='70px'>"+(typeof(content[i].出生年月) == "undefined"?"":content[i].出生年月)+"</td>";
			 htmls+="<td class='center' contenteditable='true'  width='70px'>"+(typeof(content[i].政治面貌) == "undefined"?"":content[i].政治面貌)+"</td>";
			 htmls+="<td class='center' contenteditable='true'  >"+(typeof(content[i].学历) == "undefined"?"":content[i].学历)+"</td>";
			 htmls+="<td class='center' contenteditable='true'  >"+(typeof(content[i].学术职位) == "undefined"?"":content[i].学术职位)+"</td>";
			 htmls+="<td class='center' contenteditable='true' >"+(typeof(content[i].最高学历) == "undefined"?"":content[i].最高学历)+"</td>"; */
			 htmls+="<td class='center' contenteditable='true' >"+(typeof(content[i].身份证) == "undefined"?"":content[i].身份证)+"</td>";
			 htmls+="<td class='center' contenteditable='true' >"+(typeof(content[i].推荐职务) == "undefined"?"":content[i].推荐职务)+"</td>";
			 htmls+="<td class='center' contenteditable='true' >"+(typeof(content[i].专业) == "undefined"?"":content[i].专业)+"</td>";
			/*  htmls+="<td class='center' contenteditable='true' >"+(typeof(content[i].从事专业工作年限) == "undefined"?"":content[i].从事专业工作年限)+"</td>";
			 htmls+="<td class='center' contenteditable='true' >"+(typeof(content[i].现任职资格名称) == "undefined"?"":content[i].现任职资格名称)+"</td>";
			 htmls+="<td class='center' contenteditable='true' >"+(typeof(content[i].现聘任职务) == "undefined"?"":content[i].现聘任职务)+"</td>"; */
			/*  htmls+="<td class='center' contenteditable='true' >"+(typeof(content[i].在职状态) == "undefined"?"":content[i].在职状态)+"</td>";
			 htmls+="<td class='center' contenteditable='true' >"+(typeof(content[i].学位) == "undefined"?"":content[i].学位)+"</td>";
			 htmls+="<td class='center' contenteditable='true' >"+(typeof(content[i].邮政编码) == "undefined"?"":content[i].邮政编码)+"</td>";
			 htmls+="<td class='center' contenteditable='true' >"+(typeof(content[i].个人上报的时间) == "undefined"?"":content[i].个人上报的时间)+"</td>";
			 htmls+="<td class='center' contenteditable='true' >"+(typeof(content[i].执业范围) == "undefined"?"":content[i].执业范围)+"</td>";
			 if(flag=="Y"){
				 htmls+="<td class='center' contenteditable='true' >"+(typeof(content[i].推荐资格) == "undefined"?"":content[i].推荐资格)+"</td>";
			 } */
			 htmls+="</tr>";
		 }
		 $("#declareRyBody").html(htmls);
	 }
}

function saveAllDeclareRy(){
	var flag = $("#impcontrInfoFlag").val();
	if(flag==null||flag==''){
		alertMsg.error("未导入任何数据");
		return;
	}
	var jgzxid = $("#hosnumjgzxid").val();
	if(jgzxid==null||jgzxid==''){
		alertMsg.error("请先维护监管中心ID");
		return;
	}
	var tableInfo = "[";
	var tableObj = document.getElementById("declareRyBody");
	for (var i = 0; i < tableObj.rows.length; i++) {
		tableInfo+="{";
		tableInfo +="\"p_id\"\:\""+$("#declareRyBody").find("tr").eq(i).attr("data-pid")+"\",";
		for (var j = 0; j < tableObj.rows[i].cells.length; j++) {   //遍历Row中的每一列
				var cell = tableObj.rows[i].cells[j].innerText;
				var col = "";
				switch(j){
				case 0:col='xm';
					break;
				case 1:col='xb';
					break;
				case 2:col='idcard';
				break;
				case 3:col='tjhrzzg';
				break;
				case 4:col='xcszy';
				break;
				/* case 2:col='csny';
					break;
				case 3:col='zzmm';
					break;
				case 4:col='xl';
					break;
				case 5:col='xszw';
					break;
				case 6:col='xzzw';
					break;
				case 7:col='zgxl';
					break; */
				/* case 9:col='cszygznx';
					break;
				case 10:col='xrzzgmc';
					break;
				case 11:col='xprzw';
					break; */
				
				/* case 13:col='zzzt';
					break;
				case 14:col='xw';
					break;
				case 15:col='yzbm';
					break; */
				
				/* case 17:col='zcfw';
					break; */
				default:break;
			}
				tableInfo +="\""+ col+"\"\:\""+cell+"\",";  //获取Table中单元格的内容
			}
		tableInfo=tableInfo.substring(0,tableInfo.length-1)
        tableInfo+="},";
		}
	  tableInfo=tableInfo.substring(0,tableInfo.length-1)
	  tableInfo+="]";
	  var hosnum = "${hosnum}";
	  var flag = "${flag}";
	    $.ajax({
				async:false,
			cache:false,
			url:"<%=basePath%>backstage/declare/saveBatch",
			data:{ryInfo:tableInfo,hosnum:hosnum,flag:flag,jgzxid:jgzxid},
			type:"POST",
			dataType:'json',
			error:function(){
				alertMsg.error("保存数据失败!");
			},
			success:function(data){
				
				dialogAjaxDone(data);
			}
		});
	}
</script>

<div class="pageHeader">
<iframe width=0 height=0 frameborder=0 name="hrong"
						style="display:none"></iframe>
    	<input type="hidden" id="impcontrInfoFlag">
    	<input type="hidden" name="statu" value="${statu}">
    	<input type="hidden" id="hosnumjgzxid" value="${hos.jgzxid}">
        <input style="display:none;" type="file" id="DeclareRyFile" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"  onchange="DeclareRyUpLoad(this)">
        <div class="searchBar" >
            <ul class="searchContent">
           		<li><font style="font-size: 16px;color:#333;"><b>申报人员批量导入</b></font></li>
                 <li class="pull-right">
                 <button class="btn btn-orange btn-sm" onclick="impDeclareRy()" style="margin-right:15px;">导入</button>
                 <a href="<%=basePath%>/backstage/declare/expModel" class="btn btn-orange btn-sm">导出模板</a>
                 </li>

            </ul>
        </div>
    
</div>
<div class="pageContent" >
	
    <table class="table table-bordered table-hover  table-top " style="width: 100%" layoutH="95" >
        <thead>
            <tr>
               <th class=" center"  width="75px">姓名</th>
            <th class=" center" width="60px">性别</th>
            <!-- <th class=" center" width="70px">出生年月</th>
            <th class=" center" width="70px">政治面貌</th>
            <th class=" center" width="60px">学历</th>
			<th class=" center" >学术职位</th>
			<th class=" center" >最高学历</th> -->
			<th class=" center" >身份证</th>
			<th class=" center" >推荐职务</th>
			<th class=" center" >专业</th>
			<!-- <th class=" center" >量化模板名称</th>
			<th class=" center" >附件数</th>
			<th class=" center" >开启时间</th> -->
			<!-- <th class=" center" >从事专业工作年限</th>
			<th class=" center" >现聘任职务</th> -->
			<%-- <th class=" center" >在职状态</th>
			<th class=" center" >上报年份</th>
			<th class=" center" >执业范围</th>
			<c:if test="${flag=='Y'}">
			<th class=" center" >推荐资格</th>
			</c:if> --%>
            </tr>
        </thead>
        <tbody id="declareRyBody">
        	
        </tbody>
    </table>

</div>
<div class="pageContent" style="height: 41px;border-top: none;background:#f7f5fa" >
        <div class="searchBar" >
            <ul class="searchContent">
          <!--   <li style="margin-top: 6px"><font style="font-size: 15px;color: red;">导入说明:模板中，红色边框表示必填;绿色边框表示来自字典</font></li> -->
                 <li class="pull-right" style="margin-top:6px ">
                 	<button type="button" class="btn btn-close btn-sm">关闭</button>
                 </li>
                 <li class="pull-right" style="margin-top:6px ">
                 	<button class="btn btn-orange btn-sm" onclick="saveAllDeclareRy()">保存</button>
                 </li>

            </ul>
        </div>
</div>