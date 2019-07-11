<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(function(){
	//loadAddOrgTree("orgFormTree");
	//loadAddHISTree("HISFormTree");
});
	$('#name').change(function(){
		$('#shortname').val($(this).val());
	});
	//加载组织结构树
	function loadAddOrgTree(id){
		$.ajax({
			type : 'POST',
			url:"<%=basePath%>/backstage/org/role/getOrgTree",
/* 			data:{hosnum1:'${dept.hosnum}',flag:'Y'}, */
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
								callback : {
									onClick : clickOrg_orgForm,
									beforeClick : zTreeBeforeClick
								}
							}, data.obj.list);
							var treeObj = $.fn.zTree.getZTreeObj(id);
							var nodes = treeObj.getNodes();
							
							if( "${dept.kq_deptid}"!=null&&"${dept.kq_deptid}"!=""){
								
								var thenode = treeObj.getNodeByParam("id", "${dept.kq_deptid}");
								
								treeObj.expandNode(thenode.getParentNode(),true);
								/* while(thenode.getParentNode()!=null){
									
								} */
								treeObj.selectNode(thenode);
							}else{
								treeObj.expandNode(nodes[0],true);
							}
						}
					});
		}
	function clickOrg_orgForm(event,treeId,treeNode){
		var id = treeNode.id;
		$("#kq_deptid").val(id);
		$("#orgFormKqDept").val(treeNode.name);
	}
	
	function zTreeBeforeClick(treeId, treeNode, clickFlag) {
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		var sNodes = treeObj.getNodes();
		if(treeNode==sNodes[0]){
			return false;
		}   
	};	
function loadAddHISTree(id){
	$.ajax({
		type : 'POST',
		url:"<%=basePath%>/backstage/org/role/getHISTree",
/* 			data:{hosnum1:'${dept.hosnum}',flag:'Y'}, */
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
								check: {
									enable: true,
									chkStyle: "checkbox",
									chkboxType: { "Y": "ps", "N": "ps" }
								},
								callback : {
									onCheck : cleckHISForm,
								}
							}, data);
							var treeObj = $.fn.zTree.getZTreeObj(id);
							var node = zTree.getNodeByParam('id', '0000');
							var nodes = treeObj.getNodes();
							treeObj.expandNode(nodes[0],true);
							if("${dept.his_deptid}"!=null&&"${dept.his_deptid}"!=""){
								checkHIStree("${dept.his_deptid}");
							}
						}
					});
		}
function cleckHISForm(event,treeId,treeNode){
	$("#his_deptid").val(getChekcedId());
	$("#his_deptname").val(treeNode.name);
}
function getChekcedId(){
	var tree = $.fn.zTree.getZTreeObj("HISFormTree");
    var menus = tree.getCheckedNodes(true);  
    var str = "";
    for (var k = 0, length = menus.length; k < length; k++) {
    	if(menus[k].checked == true)
    		str += menus[k].id + ","
    }
    return str;
}
function checkHIStree(t){
	if(t=="" || t==null || t=="undefined"){
		return;
	}
	t=t.replace("[","");
	t=t.replace("]","");
	$("#his_deptid").val(t);
	t=t.substring(0,t.length-1);
	var tt = t.split(",");
	if($.trim(tt[0])==""){
		return;
	}
	var treeObj = $.fn.zTree.getZTreeObj("HISFormTree");
	for(var i=0;i<tt.length;i++){
		var s = $.trim(tt[i]);
		var node1 = treeObj.getNodeByParam("id",s);
		treeObj.checkNode(node1,true,false);
		$("#his_deptname").val(node1.name);
		}
}


/* function addEmailUseraaaa(json){
	var allId = json.id;
	$("#gwyy").val(allId);
	console.log(allId);
} */
</script>
<!-- 组织 -->
<div class="pageContent">
		<form action="<%=basePath%>/backstage/org/role/addOrg" id="org_form" class="pageForm form-validate"  method="post" callback="dialogAjaxDone" noEnter >
			<input type="hidden" name="oldname" id="oldname" value="${dept.name}"/>
			<input type="hidden" id="deptid" name="deptid" value="${deptid}">
			
			<table class="table table-condensed " width="100%" style="border-collapse: separate; border-spacing: 0px 5px;">
				<tbody>
					<tr style="display:none">
						<td><input type="hidden" name="id" id="id" value="${dept.id}"></td>
						<td><input type="hidden" id="pId" name="pId" value="${dept.pId}"></td>
						<td><input type="hidden" id="kq_deptid" name="kq_deptid" value="${dept.kq_deptid}"></td>
						<td><input type="hidden" id="his_deptid" name="his_deptid" value="${dept.his_deptid}"></td>
					</tr>
					<tr>
						<td align="right" style="border-top-width: 0px">医院编号：</td>
						<td style="border-top-width: 0px">
						<input type="text" size="15" readonly id="hosnum" name="hosnum" class="form-control" style="width: 100px;" value="${dept.hosnum}"></td>
						
						<td align="right" style="border-top-width: 0px">院区编码：</td>
						<td style="border-top-width: 0px">
						<input type="text" size="15" readonly id="nodecode" name="nodecode" class="form-control" style="width: 100px;" value=""></td>
						
					    <td align="right" style="border-top-width: 0px">排序号：</td>
						<td style="border-top-width: 0px">
						<input type="text" size="15" name="organizeno" class="form-control validate[required] required validate[custom[integer]]" style="width: 100px;border-color:red;" value="${dept.organizeno}"></td>
					</tr>
					<tr>
					    <td align="right" style="border-top-width: 0px">科室大类：</td>
						<td style="border-top-width: 0px">
						<input type="text" size="15" readonly id="pName"  name="pName" class="form-control" style="width: 100px;" value="${dept.pName}"></td>
						<td align="right" style="border-top-width: 0px">考勤科室：</td>
						<td style="border-top-width: 0px" colspan="1">
						<input type="text" name="kq_dept" 
							id="orgFormKqDept" class="form-control" size="15"
							readonly="readonly" value="${kq_deptname}"></td>
							
						<td align="right" style="border-top-width: 0px">联系人：</td>
						<td style="border-top-width: 0px" colspan="1">
						<input type="text"   id="contact"  size="15"  name="contact" class="form-control" value="${dept.contact}"></td>
									<%-- <td align="right" style="border-top-width: 0px">临床类别：</td>
									<td style="border-top-width: 0px">
										<div class="radioDiv">
											<input id="clinicaltype1" type="radio" name="clinicaltype" value="临床"
											<c:if test="${dept.clinicaltype=='临床' || dept.clinicaltype==null }">checked="checked"</c:if> />临床 
											<input id="clinicaltype2" type="radio" name="clinicaltype"value="非临床"
											<c:if test="${dept.clinicaltype=='非临床' }">checked="checked"</c:if> />非临床
										</div>
									</td> --%>
						
									<%--<td align="right" style="border-top-width: 0px">科室类别：</td>
										<td style="border-top-width: 0px"><select id="depttype"	name="depttype" class="form-control" style="width: 150px;">
											<option value=""></option>
											<c:forEach items="${depttypeList}" var="depttype">
												<option value="${depttype.contents}"
													<c:if test="${dept.depttype==null?depttype.isdefault == 'Y':depttype.contents==dept.depttype}">selected="selected"</c:if>>
													${depttype.contents}</option>
											</c:forEach>
										</select></td> --%>
				</tr>
					<tr>
						<td align="right" style="border-top-width: 0px">科室名称：</td>
						<td style="border-top-width: 0px" >
						<input type="text"   id="name"  name="name" style="border-color:red;" class="form-control validate[required] required" value="${dept.name}"  size="15" ></td>
						
						<td align="right" style="border-top-width: 0px">末级判断：</td>
						<td style="border-top-width: 0px" >
							<div class="radioDiv">
								<input id="isleaf1" type="radio" name="isleaf" value="Y"
								<c:if test="${dept.isleaf=='Y' || dept.isleaf==null }">checked="checked"</c:if> />是 
								<input id="isleaf2" type="radio" name="isleaf"value="N"
								<c:if test="${dept.isleaf=='N' }">checked="checked"</c:if> />否
							</div>
						</td>
						<td align="right" style="border-top-width: 0px">科室简称：</td>
						<td style="border-top-width: 0px" >
						<input type="text"   id="shortname" style="border-color:red;"  name="shortname" class="form-control validate[required] required" value="${dept.shortname}"  size="15" ></td>
					</tr>
					</tr>
					<tr>
						<td align="right" style="border-top-width: 0px">HIS科室：</td>
						<td style="border-top-width: 0px" >
						<input type="text"   id="his_deptname"  name="his_deptname"  class="form-control"   size="15" readonly="readonly" ></td>
						<td align="right" style="border-top-width: 0px">系数：</td>
						<td style="border-top-width: 0px" >
						<input type="number"   id="coeffcient"  name="coefficient"  class="form-control " style="width:150px;" value="${dept.coefficient}" step="0.01"></td>
						<td align="right" style="border-top-width: 0px">核算类型：</td>
						<td style="border-top-width: 0px" >
							<select name="his_type" style="border-top-width:0px;width:150px;" class="form-control">
								<option value="" ></option>
								<c:forEach items="${histypelist}" var="h" varStatus="stat">
									<option value="${h.contents}" <c:if test="${h.contents eq dept.his_type }" > selected="selected" </c:if> >${h.contents }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						
						<td align="right" style="border-top-width: 0px">电话：</td>
						<td style="border-top-width: 0px">
						<input type="text"   id="tel"  size="15"  name="tel" class="form-control" value="${dept.tel}"></td>
						<td align="right" style="border-top-width: 0px">
                           <%-- <a href="<%=basePath%>backstage/postMsg/ksYy?id=${dept.id}&name=${dept.name}"
		                      width='700' height='400' lookupgroup="users" rel="ssss" 
		                      class="btn btn-orange btn-sm">岗位引入
		                   </a>	 --%>
		               </td>

					</tr>
					<tr>
						<td align="right" style="border-top-width: 0px">描述：</td>
						<td align="left" colspan="5" style="border-top-width: 0px" >
						<textarea rows="2" style="width: 700px;" id="description" name="description" class="form-control"><c:if test="${dept.description!=null&&dept.description!=''}">${dept.description}</c:if></textarea>
						</td>
					</tr>
					<tr style="height: 200px">
					
						<!-- <td align="right" style="border-top-width: 0px">考勤科室：</td>
						<td style="border-top: 0px; padding-top: 10px;" colspan="2">
							
							<div class="panel panel-default" id="orgForm_treeDiv" style="margin-left: 0px; margin-right: 6px; padding: 10px; margin-bottom: 0px; height: 200px; overflow: auto;">
								<ul id="orgFormTree" class="ztree authorityTree"></ul>
							</div> 那棵树 
						</td> -->
						<!-- <td style="border-top: 0px; padding-top: 10px;" colspan="3">
							<div class="panel " style="margin-left:0px;margin-right:0px;margin-bottom: 0px; height: 200px;width:80px;float:left;text-align:center;line-height:200px;">HIS科室:</div>
							<div class="panel panel-default" id="HISForm_treeDiv" style="margin-left: 0px; margin-right: 6px; padding: 10px; margin-bottom: 0px; height: 200px;width:300px; overflow: auto;float:left;">
								<ul id="HISFormTree" class="ztree authorityTree"></ul>
							</div>
						</td> -->
					</tr>
				</tbody>
			</table>
			<div class="formBar">
						<div style="color: red;margin-top: 6px;float: left;">*不选择考勤科室则默认为本科室</div>
						<ul>
							<li><button type="submit" class="btn btn-default btn-sm">保存</button></li>
							<li><button type="button" class="btn btn-close btn-sm">关闭</button></li>
						</ul>
			</div>
		</form>
</div>