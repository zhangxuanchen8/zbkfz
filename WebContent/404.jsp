<%@ page contentType="text/html;charset=UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(function(){
	$("#error_container_404_img").css("width",$("#error_container_404").css("width"));
});
$(window).resize(function(){
	$("#error_container_404_img").css("width",$("#error_container_404").css("width"));
});
</script>
<div class="error-container">
	
	<img alt="" src="<%=basePath %>static/img/404.png"  width="260px;" id="error_container_404_img">
		
</div>	