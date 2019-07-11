<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(document).ready(function(){
	loadRoleTree();
	checkUserTree("${theEditUser.roles}");
	$("#signDialogPersons").val($("#selectedPerson", window.parent.document).val());
	$("#ContractModelSignUploadBt").click(function(){
		var stype = $("#contractSignType").val();
		var stime = $("#contractSigntime").val();
		var start = $("#contractSignStartdate").val();
		var persons = $("#selectedPerson", window.parent.document).val();
		var su = "0";
		$('input[name="contractSignXu"]:checked').each(function(){ 
			su='1';
		}); 
		$.ajax({
  			async:false,
			cache:false,
			url:"<%=basePath%>/backstage/contract/SignUploads",
			data:{stype:stype,stime:stime,start:start,su:su,persons:persons},
			type:"POST",
			//dataType:'json',
			error:function(){
				alertMsg.error("导出失败!!!");
			},
			success:function(data){
				mm1(data+".doc");
			}
		});
	});
});

function mm1(obj){   
    //文件路径
    var str="<%=path%>/"+obj;
	window.frames["hrong"].location.href = str;
	sa();
}
function sa() {
	if (window.frames["hrong"].document.readyState == "complete")
		window.frames["hrong"].document.execCommand('SaveAs');
	else
		setTimeout(sa(), 100);
}
//--------------------------------获取树节点-----------------------------------------
function loadRoleTree() {
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/org/role/getRleTree",
	type : "POST",
	dataType:"json",
	success : function(data) {
		$.fn.zTree.init($("#getRoleTree"), {
			view : {
				selectedMulti : false,
				fontCss : {
					color : "#393939"
				}
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "s", "N": "ps" }
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				onCheck: roleOnCheck,
				beforeCheck: roleOnCheckBefore
			}
		}, data); 
		var treeObj = $.fn.zTree.getZTreeObj("getRoleTree");
		if('${sessionScope.sessionUser.job_no}'!='0000'){
			var node=treeObj.getNodeByParam("id",'d3d8d3bfca51441899f907293046974f', null);
			treeObj.hideNode(node);
		}
		treeObj.expandAll(true);
	}
});
}
function roleOnCheckBefore(treeId, treeNode){
	if(treeNode.id=='d3d8d3bfca51441899f907293046974f'&&'${sessionScope.sessionUser.job_no}'!='0000'){
		return false;
	}
}
function checkUserTree(t){
	if(t=="" || t==null || t=="undefined"){
		return;
	}
	//t=t.substring(1,t.length-1);
	var tt = t.split(",");
	if($.trim(tt[0])==""){
		return;
	}
	var treeObj = $.fn.zTree.getZTreeObj("getRoleTree");
	for(var i=0;i<tt.length;i++){
		var s = $.trim(tt[i]);
		var node1 = treeObj.getNodeByParam("id",s);
		treeObj.checkNode(node1,true,true);
	}
}
//---------------------------树选中时间-----------------------------
function roleOnCheck(){
	$("#roleSelects").val(getMenuChekcedId());
}

function getMenuChekcedId(){
	var tree = $.fn.zTree.getZTreeObj("getRoleTree");
    var menus = tree.getCheckedNodes(true);  
    var str = "";
    for (var k = 0, length = menus.length; k < length; k++) {
    	if(menus[k].checked == true)
    		str += menus[k].id + ","
    }
    return str;
}
//---------------------------------复选框选中时间----------------------
var $chk = $('input[name="ypq"]');
$chk.on('ifChanged', function(event) {
	var cfq = $("#selectCpq").val();
	if(cfq==""){
		cfq=",";
	}
    var $target = $(event.target);
    var checked = $target.prop('checked');
    var value   = $target.val();
    if( checked ){
    	cfq+=value+',';
    } else {
    	cfq=cfq.replace(value+',','');
    }
    $("#selectCpq").val(cfq);
});
</script>
<style>
.table>tbody>tr>td{
	border-top: 0px;
}
</style>
<iframe width=0 height=0 frameborder=0 name="hrong" style="display:none"></iframe>
<div class="pageContent">
    <form action="<%=basePath%>backstage/baseUser/addUser" id="baseUserAddForm" class="pageForm form-validate" method="post"  callback="dialogAjaxDone" noEnter>
       		<input type="hidden" value="" id="selectCpq" name="selectCpq">
       		<input type="hidden" value="" id="roleSelects" name="roleSelects">
       		<input type="hidden"  id="baseUserAddForm_id" name="id" value="${theEditUser.id}">
            <table class="table table-condensed " width="100%" layouth="29">
                <tbody>
                    <tr>
                        <td class="col-md-4">
                            <label for="baseUserAdd_name" class="control-label x85" style="width:80px; ">姓名：</label>
                            <input type="text" name="name" value="${theEditUser.name}" id="baseUserAdd_name"  class="form-control  validate[required] required"   size="13">
                        </td>
                        <td class="col-md-4">
                            <label for="baseUserAdd_userName" class="control-label x85" style="width:80px; ">用户名：</label>
                            <input type="text" name="job_no" value="${theEditUser.job_no}" id="baseUserAdd_userName"  class="form-control  validate[required] required"   size="13">
                        </td>
                        <td class="col-md-4">
                            <label for="baseUserAdd_passward" class="control-label x85" style="width:80px; ">密码：</label>
                            <input type="password" name="password" value="${theEditUser.password}" id="baseUserAdd_passward"  class="form-control  validate[required] required"   size="13">
                        </td>
                    </tr>
                     <tr>
                        <td class="col-md-4">
                            <label for="baseUserAdd_idCard" class="control-label x85" style="width:80px; ">身份证：</label>
                            <input type="text" name="idcard" value="${theEditUser.idcard}" id="baseUserAdd_idCard"  class="form-control  "   size="13" >
                        </td>
                        <td class="col-md-4">
                            <label for="baseUserAdd_pno" class="control-label x85" style="width:80px; ">工号：</label>
                            <input type="text" name="user_key" value="${theEditUser.user_key}" id="baseUserAdd_pno"  class="form-control number "   size="13" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">
                        </td>
                        <td class="col-md-4">
                        	<label for="baseUserAdd_sex" class="control-label x85" style="width:80px; ">性别：</label>
                        	<select name="sex" style="width: 130px;" id="baseUserAdd_sex" class="selectpicker show-tick" data-style="btn-default btn-sel"  data-width="auto" data-container="body">
							<c:if test="theEditUser.sex='男'"></c:if>
							<c:if test="theEditUser.sex!=null and theEditUser.sex!=''">
								<option value="${theEditUser.sex}" >${theEditUser.sex}</option>
							</c:if>
							<option value="男" >男</option>
							<option value="女" >女</option>
							</select>
                        </td>
                    </tr>
                    <tr>
                        <td class="col-md-4">
                            <label for="baseUserAdd_birth" class="control-label x85" style="width:80px; ">出生日期：</label>
                            <input type="text" name="birthdate" value='<fmt:formatDate value="${theEditUser.birthdate}" pattern="yyyy-MM-dd" />' id="baseUserAdd_birth"  class="form-control  date"   size="13">
                        </td>
                        <td class="col-md-4">
                            <label for="baseUserAdd_zc" class="control-label x85" style="width:80px; ">职称：</label>
                            <input type="text" name="zc" value="${theEditUser.zc}" id="baseUserAdd_zc"  class="form-control"   size="13">
                        </td>
                        <td class="col-md-4">
                            <label for="baseUserAdd_index" class="control-label x85" style="width:80px; ">排序号：</label>
                            <input type="text" name="index_no" value="${theEditUser.index_no}" id="baseUserAdd_index"  class="form-control  number validate[required] required"   size="13" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">
                        </td>
                    </tr>
                    <tr>
                        <td class="col-md-4">
                            <label for="baseUserAdd_tel" class="control-label x85" style="width:80px; " >电话：</label>
                            <input type="text" name="phone" value="${theEditUser.phone}" id="baseUserAdd_tel"  class="form-control  "   size="13" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">
                        </td>
                        <td class="col-md-4">
                            <label for="baseUserAdd_phone" class="control-label x85" style="width:80px; ">手机：</label>
                            <input type="text" name="mobile" value="${theEditUser.mobile}" id="baseUserAdd_phone"  class="form-control  number"   size="13" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">
                        </td>
                        <td class="col-md-4">
                            <label for="baseUserAdd_short" class="control-label x85" style="width:80px; ">短号：</label>
                            <input type="text" name="short_mobile" value="${theEditUser.short_mobile}" id="baseUserAdd_short"  class="form-control  "   size="13" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">
                        </td>
                    </tr>
                    <tr>
                        <td class="col-md-4">
                            <label for="baseUserAdd_email" class="control-label x85" style="width:80px; ">邮箱地址：</label>
                            <input type="text" name="email" value="${theEditUser.email}" id="baseUserAdd_email"  class="form-control" size="13" >
                        </td>
                        <td class="col-md-4">
                            <label for="baseUserAdd_dept" class="control-label x85" style="width:80px; ">人事关系：</label>
                            <input type="text" name="baseUserAdd_dept" value="${theEditUser.dept_name==null?thedeptname_baseUser:theEditUser.dept_name}" id="baseUserAdd_dept"  class="form-control  validate[required] required" readonly="readonly" size="13">
                        	<input type="hidden" name="person_dept" value="${theEditUser.person_dept==null?thedept_baseUser:theEditUser.person_dept}" id="baseUserAdd_deptId" />
                        </td>
                        <td class="col-md-4">
                            <label for="baseUserAdd_sr" class="control-label x85" style="width:80px; ">输入习惯：</label>
                            <input type="text" name="input_custom" value="${theEditUser.input_custom}" id="baseUserAdd_sr"  class="form-control  "   size="13">
                        </td>
                    </tr>
                    <tr>
                       <td class="col-md-12" colspan="3">
                       		 <label for="contract_bz" class="control-label x85" style="width:80px;">备注：</label>
                       	<textarea name="remark"  id="contract_bz" class="form-control autosize" cols="58" rows="2" placeholder="">${theEditUser.remark}</textarea>
                       </td>
                    </tr> 
                    <tr>
                   		<td class="col-md-12" colspan="3">
                       		<label for="baseUserAdd_right" class="control-label x85" style="width:80px;float:  left;height: 220px;padding-top: 85px;">角色：</label>
                       		<div class="panel panel-default" id="baseUserAdd_right" style="margin-left: 85px;margin-right: 8px;padding: 10px;margin-bottom: 0px;height: 205px;overflow: auto;">
					                <ul id="getRoleTree" class="ztree" style="margin-top:-6px" attrs = '{"expandAll":true}'  nodes=''></ul>
                       		</div>
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