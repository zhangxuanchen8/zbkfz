<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link href="<%=basePath%>city-picker/docs/css/city-picker.css" rel="stylesheet"/>

<script type="text/javascript" src="./jquery.js"></script>
<script src="<%=basePath%>city-picker/docs/js/city-picker.data.js"></script>
<script src="<%=basePath%>city-picker/docs/js/city-picker.js"></script>
<script type="text/javascript">
/* $("#addlxrphone").blur(function () {
	//debugger;
	var phone1=/^[1][3,4,5,7,8][0-9]{9}$/;
	var phone = $("#addlxrphone").val();
    if(phone.match(phone1)){
    	return;
    }else{
    	 alertMsg.error("请输入正确的手机号！");
    	return false;
    }
}); */
</script>
<form action="<%=basePath %>backstage/shujuljdy/sjsave" id="add_ress" class="pageForm form-validate" method="post" callback="dialogAjaxDoneds" noEnter>
	<input type="hidden" name="id" value="${sjpz.id}"> 
	<div class="pageFormContent" layouth="26">
					<p><label for="sjname"
						class="control-label" style="width: 80px;">数据源名称：</label>
						<input type="text" id="s_name" name="s_name" class="form-control " 
							size="18" style="width: 180px;" value="${sjpz.s_name}"/></p>
					<%-- <p><label for="sjsour"
						class="control-label" style="width: 80px;">来源：</label>
						<input type="text" id="sources" name="sources" class="form-control " 
							size="18" style="width: 180px;" value="${sjpz.sources}"/></p> --%>
					<p><label for="sjip"
						class="control-label" style="width: 80px;">ip：</label>
						<input type="text" id="ip" name="ip" class="form-control " 
							size="18" style="width: 180px;" value="${sjpz.ip}"/></p>
					<p><label for="sjuser"
						class="control-label" style="width: 80px;">用户：</label>
						<input type="text" id="user_account" name="user_account" class="form-control " 
							size="18" style="width: 180px;" value="${sjpz.user_account}"/></p>
					<p><label for="sjpass"
						class="control-label" style="width: 80px;">密码：</label>
						<input type="text" id="pwd" name="pwd" class="form-control " 
							size="18" style="width: 180px;" value="${sjpz.pwd}"/></p>
					<p><label for="port"
						class="control-label" style="width: 80px;">端口号：</label>
						<input type="text" id="port" name="port" class="form-control " 
							size="18" style="width: 180px;" value="${sjpz.port}"/></p>
					<p><label for="sqlname"
						class="control-label" style="width: 80px;">库名：</label>
						<input type="text" id="sqlname" name="sqlname" class="form-control " 
							size="18" style="width: 180px;" value="${sjpz.sqlname}"/></p>
					<p><label for="sjtype"
						class="control-label" style="width: 80px;">类型：</label>
						<select name="stype" id="stype" style="width: 180px" class="form-control ">
                            <option value="0">==请选择==</option>
                            <option value="oracle" <c:if test="${'oracle'==sjpz.stype}">selected</c:if> >oracle</option>
                            <option value="mysql" <c:if test="${'mysql'==sjpz.stype}">selected</c:if> >mysql</option>
                            <option value="sqlserver" <c:if test="${'sqlserver'==sjpz.stype}">selected</c:if> >sqlserver</option>
                        </select>
						
						
						<%-- <input type="text" id="stype" name="stype" class="form-control " 
							size="18" style="width: 180px;" value="${sjpz.stype}"/> --%></p>
					<p><label for="sjrem"
						class="control-label" style="width: 80px;">备注：</label>
						<input type="text" id="remark" name="remark" class="form-control " 
							size="18" style="width: 180px;" value="${sjpz.remark}"/></p>
						
	</div>	
	<div class="formBar">
		<ul>
			<li><button type="submit" class="btn btn-default btn-sm">保存</button></li>
			<li><button type="button" class="btn btn-close btn-sm">关闭</button></li>
		</ul>
	</div>
</form>
