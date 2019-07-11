<%@ page contentType="text/html;charset=UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(function(){
	$("#error_container_500_img").css("width",$("#error_container_500").css("width"));
});
$(window).resize(function(){
	$("#error_container_500_img").css("width",$("#error_container_500").css("width"));
});
</script>

	<div id="error_container_500">
	
		<img alt="" src="<%=basePath %>static/img/500.png" width="260px;" id="error_container_500_img">
		
	</div>			
