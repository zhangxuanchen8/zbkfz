<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<script type="text/javascript">


/* 上传附件 */
$(function(){
	$("#qwdascxdvc").click(function () {
        location.href = $(this).find("a").attr("href");
    });
	$('body').append("<div style='display:none;width:100%; margin:0 auto;position:fixed;left:0;top:0;bottom: 0;z-index: 111;opacity: 0.5;' id='loadingGain'><a class='mui-active' style='left: 50%;position: absolute;top:50%'><span class='mui-spinner'></span><p style='margin-left: -50px;'><img src='<%=basePath %>img/loading.gif'>文件上传中，请稍等...</p></a></div>");

});

function upload(){
	$("#resultUpload").click();
};
function importResult(){
	var fileName = $("#resultUpload").val();
	if(fileName==null||fileName.split(".").length<=1){
		 alertMsg.error("请选择后缀为jpg,png,pdf的文件!");
			return;
	 }else if(fileName.split(".")[fileName.split(".").length-1]!='jpg' && fileName.split(".")[fileName.split(".").length-1]!='JPG' && fileName.split(".")[fileName.split(".").length-1]!='png' && fileName.split(".")[fileName.split(".").length-1]!='PNG' && fileName.split(".")[fileName.split(".").length-1]!='pdf' && fileName.split(".")[fileName.split(".").length-1]!='PDF'){
			alertMsg.error("请选择后缀jpg,png,pdf的文件!");
			return;
	}
	if(fileName.split(".")[fileName.split(".").length-1]=='jpg' || fileName.split(".")[fileName.split(".").length-1]=='JPG'){
		ResultfjImports();
	}else{
		ResultfjImportspdf();
	}
};
function ResultfjImports() {
	$("#loadingGain").show();
	var fileName = $("#resultUpload").val();
	var fileObj = document.getElementById("resultUpload").files[0];// 获取读取的File对象
	var size = fileObj.size;
	if(size>4000000){
		alertMsg.error("文件过大，请重新上传,不可大于4M");
		$("#loadingGain").hide();
		return;
	}
	//-------------------------
	var FileController = "<%=basePath%>tool/webuploader/uploadPic.htm"; // 接收上传文件的后台地址  
	if (fileObj) {
		// FormData 对象  
		var form = new FormData();
		form.append("file", fileObj);// 文件对象     
		// XMLHttpRequest 对象  
		var xhr = new XMLHttpRequest();
		debugger;
		xhr.open("post", FileController, true);
		xhr.onload = function() {
			$("#resultUpload").after($("#resultUpload").clone().val(""));
			$("#resultUpload").remove();
			data = xhr.responseText;
			data=data.replace(/\s/g, "");
			var json = $.parseJSON( data );
				//alert(json.saveUrl+" "+json.imgname);
			if(json.resMsg=='上传成功'){
				var demolist = $("#demoList").html();
				$("#demoList").html(demolist+'<tr ><td style="display:none"><input id="imgurl" name="imgurl" class=" form-control " style="width: 100%; height: 100%;" value="'+json.saveUrl+'" ></td><td ><input id="ddIns" name="ddIns" class=" form-control " style="width: 100%; height: 100%;" value="'+json.imgname+'" ></td><td><input id="ioprice_addIns" class="form-control " style="width: 100%; height: 100%;" value="等待保存"></td><td class="center"><a href="javascript:;" style="color: red;text-decoration: none;" class="btn btn-red btn-sm j-del" target="ajaxTodo" callfun="table_del_callfun" title="确定要删除该行信息吗？">×</a></td></tr>');
				
				var fujian = $("#ResultUploadFile").val();
				$("#ResultUploadFile").val(fujian+','+json.saveUrl);
				$("#loadingGain").hide();
			}
			if(json.resMsg=='超过上传文件大小限制'){
				alertMsg.error("文件过大，请重新上传");
				$("#loadingGain").hide();
			}
			if(json.resMsg=='请上传允许格式的文件'){
				alertMsg.error("格式有误，请重新上传");
				$("#loadingGain").hide();
			}
			//--------------接保存的方法----------------------------
		};
		xhr.send(form);
	} else {
		alertMsg.error("未选择文件");
	}
}
function clearPath(){
	$("#ResultlUploadAddr").html("");
	$("#ResultUploadFile").val("");
	$("#clearPath").hide();
};

//下载附件
function result_down(str){
	$.ajax({
		url:'<%=basePath%>backstage/tool/webuploader/download.htm',
	    type:'POST', 
	    data:{
	    	id:str
	    },
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	   
	    success:function(data){
	    	window.frames["hrong"].location.href = "<%=basePath%>"+data.saveUrl;
	    	sa();
	    }
	});
}
function sa() {
	if (window.frames["hrong"].document.readyState == "complete")
		window.frames["hrong"].document.execCommand('SaveAs');
	else
		setTimeout(sa(), 100);
}

function downloadIMg(str1,str2){   
    //文件路径
    var str="<%=basePath %>"+str1;
    var a = document.createElement("a");
    a.href = str;
    a.download =str2;
    a.click();
}

function uploadpdf(){
	$("#resultUploadpdf").click();
};
function importResultpdf(){
	var fileName = $("#resultUpload").val();
	if(fileName==null||fileName.split(".").length<=1){
		 alertMsg.error("请选择后缀为pdf的文件!");
			return;
	 }else if(fileName.split(".")[fileName.split(".").length-1]!='pdf' && fileName.split(".")[fileName.split(".").length-1]!='PDF' ){
			alertMsg.error("请选择后缀pdf的文件!");
			return;
	}
	ResultfjImportspdf();
};
function ResultfjImportspdf() {
	$("#loadingGain").show();
	var fileName = $("#resultUpload").val();
	var fileObj = document.getElementById("resultUpload").files[0];// 获取读取的File对象
	var size = fileObj.size;
	if(size>4000000){
		alertMsg.error("文件过大，请重新上传,不可大于4M");
		$("#loadingGain").hide();
		return;
	}
	//-------------------------
	var FileController = "<%=basePath%>tool/webuploader/uploadDocFile.htm"; // 接收上传文件的后台地址  
	if (fileObj) {
		// FormData 对象  
		var form = new FormData();
		form.append("file", fileObj);// 文件对象     
		// XMLHttpRequest 对象  
		var xhr = new XMLHttpRequest();
		xhr.open("post", FileController, true);
		xhr.onload = function() {
			data = xhr.responseText;
			data=data.replace(/\s/g, "");
			var json = $.parseJSON( data );
			if(json.resMsg=='上传成功'){
				var demolist = $("#demoList").html();
				$("#demoList").html(demolist+'<tr ><td style="display:none"><input id="imgurl" name="imgurl" class=" form-control " style="width: 100%; height: 100%;" value="'+json.saveUrl+'" ></td><td ><input id="ddIns" name="ddIns" class=" form-control " style="width: 100%; height: 100%;" value="'+json.pdfname+'" ></td><td><input id="ioprice_addIns" class="form-control " style="width: 100%; height: 100%;" value="等待保存"></td><td class="center"><a href="javascript:;" style="color: red;text-decoration: none;" class="btn btn-red btn-sm j-del" target="ajaxTodo" callfun="table_del_callfun" title="确定要删除该行信息吗？">×</a></td></tr>');
				
				var fujian = $("#ResultUploadFile").val();
				$("#ResultUploadFile").val(fujian+','+json.saveUrl);
				$("#loadingGain").hide();
			}
			if(json.resMsg=='超过上传文件大小限制'){
				alertMsg.error("文件过大，请重新上传");
				$("#loadingGain").hide();
			}
			if(json.resMsg=='请上传允许格式的文件'){
				alertMsg.error("格式有误，请重新上传");
				$("#loadingGain").hide();
			}
			//--------------接保存的方法----------------------------
		};
		xhr.send(form);
	} else {
		alertMsg.error("未选择文件");
	}
	
}

</script>
<form id="licence_add_Form"  action="<%=basePath%>/cpuser/save_fj"  method="post" callback="dialogAjaxDone" class="pageForm form-validate" noEnter>          
   <input type="hidden" name="i_id" value="${i_id}">
   <input type="hidden" name="userid" value="${userid}">
   <input type="hidden" name="scbj" value="${scbj}">
   <div class="searchBar" style="height: 32px;">
     <ul class="searchContent">
        <li class="pull-right">
          <button type="submit" class="btn btn-default btn-sm" <c:if test="${scbj eq 'Y'}">disabled="disabled"</c:if>>保存</button>
          <!-- <button type="reset" class="btn btn-default btn-sm" onclick="">清空</button> -->
        </li>
         <li class="pull-right">
			 <td  align="left" style="border-top-width: 0px;width: 90px;" colspan="4">
				 <button type="button" class="btn btn-default btn-sm" id="pz_fj" value="" onclick="upload()" <c:if test="${scbj=='Y' }">disabled="disabled" </c:if>>上传</button>
				        &nbsp;&nbsp;&nbsp;
				<!-- <input type="button" class="btn btn-default btn-sm" id="pz_fj_pdf" value="pdf上传" onclick="uploadpdf()"/> -->
			 	<input type="file" id="resultUpload" style="display: none" accept="pdf" onchange="importResult()"/> 
			 	<input type="file" id="resultUploadpdf" style="display: none" accept="pdf" onchange="importResultpdf()"/> 
			 	<input type="hidden" id="ResultUploadFile" name="fujian"  value="${licenceInfo[0].zbk_fj }"/>
		 	 </td>
		  </li>
      </ul>
   </div>
  <div class="pageContent" style="height: 240px;">
	<!-- <div style="width: 100%; overflow-x: auto; overfolw-y: hidden; height:330px;"> -->
		<!-- <table class="table table-bordered table-hover table-striped table-top" style="height:100px; " >
           <tr>
		   </tr>
		</table> -->
	
		
	<div class="tabs" eventType="click" style="margin-left: 10px;margin-right: 10px;margin-bottom: 0px;margin-top: 12px;">
		<div id="gergre" class="tabsContent" style=" overfloat-y: auto;padding:0px;border-top: 1px;border-top-style: solid;border-top-color: #000;border-radius:4px 4px 4px 4px; height:220px; ">

		<table class="table table-bordered table-hover table-striped table-top itemDetail" style="width: 100%;" layoutH='95' >
			<thead >
				<tr>
				<th style="display:none" class="center" type="html" html='<input id="imgurl" name="imgurl" class=" form-control " style="width: 100%; height: 100%;"  value="">'  ></th>
					<th style="width: 40%" class="center"type="html" html='<input id="ddIns" name="ddIns" class=" form-control " style="width: 100%; height: 100%;"  value="">'  >文件名</th>
					<th style="width: 30%" class="center" type="html" html='<input  class=" form-control "  style="width: 100%; height: 100%;"  value="">'>上传状态</th>
					<th type="del" width="30%" fieldAttrs="{'title':'确定要删除行吗？??', 'value':'删'}">
						操作
	                </th> 
				</tr>
			</thead>
			<tbody id="demoList">
			<c:forEach items="${licenceInfo[0].list_file }" var="l" varStatus="stat">
					<tr >
						<td style="display:none"><input id="imgurl" name="imgurl" class=" form-control ro" readonly="readonly"
							style="width: 100%; height: 100%;" value="${l.id}" ></td>
						<td ><input id="ddIns" name="ddIns" class=" form-control ro" readonly="readonly"
							style="width: 100%; height: 100%;" value="${l.filename}" ></td>
						<td><input  class="form-control " readonly="readonly"
							style="width: 70%; height: 100%;" value="已保存"></td>
						<td class="center">
							<a href="<%=basePath%>tool/webuploader/readDef?id=${l.id}" class="btn btn-green btn-sm " mask="true" target="dialog" width="700" height="500" rel="chakantupian1"><span title="查看" ><i class="fa fa-search fa-fw"></i></span></a>
							<a href="javascript:;" class="btn btn-red btn-sm j-del" target="ajaxTodo" callfun="table_del_callfun" title="确定要删除该行信息吗？"  <c:if test="${scbj eq 'Y'}">disabled="disabled"</c:if>><i class="fa fa-remove fa-fw"></i></a>
						</td>
					</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
  </div>
  	<!-- </div> -->
	</div>
</form>
