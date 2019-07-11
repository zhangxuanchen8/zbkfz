
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(function(){
	efr();
	function efr(){
		$("#warningReload").click();
	}
	
});


</script>

<a href="<%=basePath%>backstage/warning/warningIndex" id="warningReload" style="display: ;" target="ajax"   rel="warningList">载入</a>
<div  class="col-md-12 " style="padding:0px; id="warningList" >
	<jsp:include page="warningTable.jsp" flush="true"/>
</div>





