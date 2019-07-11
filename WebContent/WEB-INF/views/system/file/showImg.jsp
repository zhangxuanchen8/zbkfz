<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script type="text/javascript">
$(function(){
	
	var imgs = '${saveUrl}';
	
	if(imgs!=null&&imgs!=""&&imgs.indexOf(",")>-1){
		var urls = imgs.split(",");
		for(var i=0;i<urls.length;i++){
			if(urls[i]!=null&&urls[i]!=""){
				$("#showAllUploadPic").append("<div class='panel panel-default' style='width:100%;float: left;margin-bottom: 0px;' ><img src='<%=basePath%>"+urls[i]+"' width='100%' ></div>");
			}
			
		}
	}else if(imgs!=null&&imgs!=""&&imgs.indexOf(",")<0){
		
		$("#showAllUploadPic").html("<div class='panel panel-default' style='width:100%;float: left;margin-bottom: 0px;' ><img src='<%=basePath%>"+imgs+"' width='100%' ></div>");
	}
	
});










</script>
	<form action="#" id="showImg">
		
			
		<div class="panel panel-default" id = "showAllUploadPic"  style="padding:0px;width:100%;margin-bottom: 0px;overflow: auto;" layouth='28'>
			
		
		</div>	
		
			
		
		<div class="formBar">
			<ul>
				
				<li><button type="button" class="btn btn-close btn-sm">关闭</button></li>
			</ul>
		</div>
	</form>
