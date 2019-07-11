
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<style>
div.grid {
	border-left: 0px;
	border-right: 0px;
}
</style>

<script type="text/javascript">
$(function(){
	loadorgtree_declareListView();//加载树
});
//ajax添加树形结构
function loadorgtree_declareListView() {
	$.ajax({
		async:false,
		cache:false,
		url:"<%=basePath%>backstage/declare/getYear",
		type : "post",
		//datatype : "json",
		success : function(data) {
			$("#ztree_declareListView").attr("nodes",data.toString());
		},
		error:function(data){
			alertMsg.error("数据加载错误");
		}
	});
}
//获取table内容
function getPid_declareListView(event, treeId, treeNode){
	$("#dept_declareListView").val(treeNode.id);//添加树id即部门id
	$("#declareListViewSubmit").click();//提交declareReady的隐藏表单
}

function empdetails${pageid}(pid,name,pno){  
	   $("#empdetails${pageid}").attr("rel",pid);
	   $("#empdetails${pageid}").attr("href",$("#empdetails${pageid}").attr("data-href")+"&pid="+pid);
	   $("#empdetails${pageid}").html('&lt;i class="fa fa-user-md"  &gt;&lt;/i&gt; &lt;font style="margin-top:0px;" id="theSelectEmp'+pid+'" &gt; '+name+'  ('+pno+')&lt;/font&gt;')
	   $("#empdetails${pageid}").click();
	}
function clickColor${pageid}(obj){  
    $("#emp_list_body${pageid}").parent().find("tr").find("td").css('background-color','#ffffff');
    $("#emp_list_body${pageid}").parent().find("tr").find("td").css('background-color','');
  $(obj).children().css('background-color','#79ff9f');
}  

</script>

<div class="col-md-2 panel panel-default"
	style="padding: 0px; height: 100%; overflow: auto; float: left; width: 16%;"
	layoutH="0">
	<ul id="ztree_declareListView" class="ztree"
		attrs='{"onClick":"getPid_declareListView","expandAll":true}' nodes=''
		style="width: 400px;">
	</ul>
</div>

<!-- 添加table -->
 <div class="col-md-10 " style="padding: 0px;" id="readyDeclareView">
	<jsp:include page="declareReadyView.jsp"></jsp:include>
</div> 
