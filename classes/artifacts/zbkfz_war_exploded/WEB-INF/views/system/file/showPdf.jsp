
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<object classid="clsid:CA8A9780-280D-11CF-A24D-444553540000" width="100%" height="100%" layoutH='0' border="0" top="-10" name="pdf" > 
<param name="toolbar" value="false">
<param name="_Version" value="65539">
<param name="_ExtentX" value="20108">
<param name="_ExtentY" value="10866">
<param name="_StockProps" value="0">
<param name="SRC" value="<%=basePath %>${saveUrl}" type="application/pdf" style="width: 100%;height: 100%"  width="100%" height="97%" >

<embed name="plugin" src="<%=basePath %>${saveUrl}" type="application/pdf" width="100%" height="97%" layoutH='0'>
</object>

