<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<script type="text/javascript">
var zTree;
var i = 0;
$(function(){	
	loadOrgTreeRole();
	checkmenutree("${menus}");
});

function loadOrgTreeRole() {
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/sysmenu/getmenutree",
	type : "POST",
	dataType:"json",
	success : function(data) {
		$.fn.zTree.init($("#rolesysmenuTree"), {
			view : {
				selectedMulti : false,
				fontCss : {
					color : "#393939"
				}
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				onCheck: zTreeOnCheckRole
			}
		}, data); 
		var treeObj = $.fn.zTree.getZTreeObj("rolesysmenuTree");
		treeObj.expandAll(true);
	}
});
}

function zTreeOnCheckRole(){
	$("#rolemenus").val(getMenuChekcedId());
}

function getMenuChekcedId(){
	var tree = $.fn.zTree.getZTreeObj("rolesysmenuTree");
    var menus = tree.getCheckedNodes(true);  
    var str = "";
    for (var k = 0, length = menus.length; k < length; k++) {
    	if(menus[k].checked == true)
    		str += menus[k].id + ","
    }
    return str;
}

function checkmenutree(t){
	
	if(t=="" || t==null || t=="undefined"){
		return;
	}
	t=t.replace("[","");
	t=t.replace("]","");
	//t=t.substring(1,t.length-1);
	$("#rolemenus").val(t);
	var tt = t.split(",");
	if($.trim(tt[0])==""){
		return;
	}
	var treeObj = $.fn.zTree.getZTreeObj("rolesysmenuTree");
	for(var i=0;i<tt.length;i++){
		var s = $.trim(tt[i]);
		var node1 = treeObj.getNodeByParam("id",s);
		treeObj.checkNode(node1,true,false);
	}
}
</script>
<div class="pageContent">
		<form id="role_form" method="post" action="<%=basePath%>backstage/org/role/save" class="pageForm form-validate" callback="dialogAjaxDone" noEnter>
			<input type="hidden" id="rolemenus" name="rolemenus" value=""/>
			<table class="table table-condensed "  style="margin-left:11px;border-collapse: separate; border-spacing: 0px 5px;width:500px;" layoutH="32">
				<tbody>
					<tr style="display:none">
						<td colspan="4" class="ui-state-error"><input type="hidden" name="id" value="${role.id}"></td>
					</tr>
					<tr>
						<td align="right" style="border-top-width: 0px">代码:</td>
						<td style="border-top-width: 0px"><input type="text" name="code" value="${role.code}" class="form-control validate[required] required" size="15" ></td>
						<td align="right" style="border-top-width: 0px">名称:</td>
						<td style="border-top-width: 0px"><input type="text" name="name" value="${role.name}" class="form-control validate[required] required" size="15" ></td>
					</tr>
					<tr>
						<td align="right" style="border-top-width: 0px">排序号:</td>
						<td style="border-top-width: 0px"><input type="text" name="index_no" value="${role.index_no}" class="form-control validate[required] required" size="15" ></td>
						<td align="right" style="border-top-width: 0px">级别:</td>
						<td style="border-top-width: 0px"><%-- <input type="text" name="lvl" value="${role.lvl}" class="form-control" size="15" > --%>
							<select name="lvl" data-style="btn-default btn-sel" data-width="150" data-container="body" class="form-control"  style="width: 100%; height: 28px; width: 150px">
 										<option value="1"
											<c:if test="${role.lvl == '1'}">selected="selected"</c:if>>系统级
										</option>
										<option value="2"
											<c:if test="${role.lvl == '2'}">selected="selected"</c:if>>院区级
										</option>
										<option value="3"
											<c:if test="${role.lvl == '3'}">selected="selected"</c:if>>科室级
										</option></select>
						</td>
					</tr>
					<tr>
						<td align="right" style="border-top-width: 0px">备注:</td>
						<td colspan="3" style="border-top-width: 0px">
						<input type="text" name="remark" value="${role.remark}" class="form-control" size="39.4" >
								</td>
					</tr>
					<tr>
						<td align="right" style="border-top-width: 0px">菜单:</td>
						<td colspan="3" style="border-top-width: 0px">
						<div style="width:394px;height:392px">
					          <fieldset style="border-left-width:1px">
					              <div class="clearfix">
					                  <div style="float: left; width: 95%; height:355px; overflow: auto;">
					                     <ul id="rolesysmenuTree" class="ztree" style="margin-top:-6px" attrs = '{"expandAll":true}' 
					                          nodes=''>
					                      </ul>
					                  </div>
					              </div>
					          </fieldset>
					      </div>
					      <td>
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