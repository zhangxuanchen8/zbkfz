<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
var treeObj;
$(function(){
	loadDeptTree("deptTree","${dept.g_dept}");//加载科室的树
	//loadDeptTree();
});
<%-- function loadDeptTree() {
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/org/role/getDeptTree",
	type : "POST",
	dataType : "json",
	success : function(data) {
		treeNodes = data.obj.list;
		zTree = $.fn.zTree.init($("#deptTree"),{
			view:{
				selectedMulti:false,fontCss:{color:"#393939"}
			},
			data:{
				simpleData: {enable: true}
			},
			callback:{
				onClick:zTreeOnClick}
			},treeNodes);
			var treeObj = $.fn.zTree.getZTreeObj("deptTree");
			var node = zTree.getNodeByParam("id", $("#deptid").val());
			treeObj.selectNode(node);
			if(node!=null){
				zTree.setting.callback.onClick(null,zTree.setting.treeId,node);
			}
			//zTree.setting.callback.onClick(null,zTree.setting.treeId,nodes[0]);
			treeObj.expandNode(node,true);
			}
	});
} --%>
//加载组织结构树
 function loadDeptTree(id,t){
	$.ajax({
		type : 'POST',
		url:"<%=basePath%>/backstage/zhibiao/getZhiBiaoTree",
		type : "POST",
		dataType : "json",
		success : function(data) {
						$.fn.zTree.init($("#"+id), {
							view : {
								selectedMulti : false,
								fontCss : {
									color : "#393939"
								}
							},
							data : {
								simpleData : {
									enable : true
								}
							},
							check : {
								enable: true,
								chkStyle : "checkbox",
								chkboxType: { "Y": "", "N": "" }
							},
							callback : {
								onCheck : NodeCheck_Dept,
							}
						}, data.obj.list);
						
						var treeObj = $.fn.zTree.getZTreeObj(id);
						var nodes = treeObj.getNodes();
						//treeObj.expandAll(true);
						treeObj.expandNode(nodes[0],true);
						
						if(t=="" || t==null || t=="undefined"){
							return;
						}
						t=t.replace("[","");
						t=t.replace("]","");
						var tt = t.split(",");
					 	if($.trim(tt[0])==""){
							return;
						}
						for(var i=0;i<tt.length;i++){
							var s = $.trim(tt[i]);
							var node1 = treeObj.getNodeByParam("id",s);
							console.log(node1);
							treeObj.checkNode(node1,true,false); 
						} 
					}
				});
	} 
	
	
function NodeCheck_Dept(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId), nodes = zTree
	.getCheckedNodes(true);
    var ids = '', names = '';
    for (var i = 0; i < nodes.length; i++) {
  	   ids += ',' + nodes[i].id;
 	   names += ',' + nodes[i].name;
/*     	if(!nodes[i].isParent){
     	   ids += ',' + nodes[i].id;
     	   names += ',' + nodes[i].name;
     	} */
    }
    if (ids.length > 0) {
       ids = ids.substr(1), names = names.substr(1);
    }
    $("#g_deptbh_add").val(ids);
    $("#g_deptmc_add").val(names);
    var $from = $($('#' + treeId).data('fromObj'));
    if ($from && $from.length)
       $from.val(names);
}

function checkZ_name(obj){
	var hosnum = $("#hosnum_add").val();
	$.ajax({
		type : 'POST',
		url:"<%=basePath%>/backstage/choose_dept/checkZ_name?z_name="+obj.value+"&hosnum="+hosnum,
		type : "POST",
		dataType : "json",
		success : function(data) {
			if("success" == data.message){
			}else{
				$("#z_name_add").val("");
				alertMsg.error("群组名称已存在，请重命名！");
			}
		}
	});
}

</script>
<div class="pageContent">
		<form action="<%=basePath%>backstage/choose_dept/addorUpDept"  class="pageForm form-validate"  method="post" callback="dialogAjaxDone" noEnter >
		    <input type="hidden" name="hosnum" id="hosnum_add" value="${dept.hosnum}"/>
		    <input type="hidden" name="g_dept" id="g_deptbh_add" value="${dept.g_dept}"/>
		    <input type="hidden" name="" id="g_deptmc_add" value=""/>
		    <input type="hidden" name="key_id" value="${dept.key_id}"/>

			<table class="table" width="100%" layoutH="38">  
				<tbody>
				   <tr>
		             <td align="right" style="border-top-width: 0px"><label class="control-label x85">群组名称:</label></td>
		             <td style="border-top-width: 0px">
		               <input type="text" id="z_name_add" name="z_name" value="${dept.z_name }" style="width: 180px;border-color: red;" class="form-control validate[required] required" onchange="checkZ_name(this)">
		             </td>
		             <td align="right" style="border-top-width: 0px"><label class="control-label x85">排序号:</label></td>
		             <td style="border-top-width: 0px">
		               <input type="text" id="index_no_add" name="index_no" value="${dept.index_no }" style="width: 180px;border-color: red;" class="form-control validate[required] required" >
		             </td>
		          </tr>
		          <tr style="display: none;">
		             <td align="right" style="border-top-width: 0px"><label class="control-label x85">医院名称:</label></td>
		             <td style="border-top-width: 0px" colspan="2">
		               <input type="text" id="hosname_add" value="${dept.hosname }" style="width: 180px;" class="form-control" readonly="readonly">
		             </td>
		          </tr>
		          <tr>
                     <td align="right" style="border-top-width: 0px"><label class="control-label x85">选择科室:</label></td>
					 <td style="border-top-width: 0px" colspan="2">
 						 <div class="panel panel-default" id="" style="margin-left: 0px; margin-right: 26px; padding: 10px; margin-bottom: 0px; height: 370px; width:360px; overflow: auto;" >
							<ul id="deptTree" class="ztree authorityTree" ></ul>
						 </div> 
			          </td>
                  </tr>
                  <tr>
					 <td align="right" style="border-top-width: 0px"><label class="control-label x85">备注:</label></td>
					 <td colspan="3" style="border-top-width: 0px" width="200px">
					     <textarea name="remark" rows="2" class="txtarea" style="width:95%" >${dept.remark}</textarea>
					 </td>
                  </tr>
				</tbody>
			</table>
			<div class="formBar">
				<ul>
					<li><button type="submit" class="btn btn-default btn-sm">保存</button></li>
					<li><button type="button" class="btn btn-close btn-sm">关闭</button></li>
				</ul>
			</div>
		</form>
</div>