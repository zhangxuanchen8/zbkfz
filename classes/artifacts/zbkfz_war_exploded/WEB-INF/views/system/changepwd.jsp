<%@ page contentType="text/html;charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="pageContent">
	
	<form id="j_pwschange_form" action="<%=basePath %>backstage/baseUser/preResetPWD" class="pageForm form-validate" method="post" callback="dialogAjaxDone" noEnter>
		<input type="hidden" name="users.id" value="test">
		<input type="hidden" id="j_pwschange_username" value="${currentAccount.name}">
		<input type="hidden" id="j_pwschange_oldpass" name="users.password">
		<input type="hidden" id="j_pwschange_newpass" name="newpassword">
		<div class="pageFormContent" layouth="32">
            <div class="form-group">
                <label for="j_pwschange_oldpassword" class="control-label x85">旧密码：</label>
                <input type="password" class="form-control validate[required] required" name="pwschange_oldpassword" id="j_pwschange_oldpassword" value="" placeholder="旧密码" size="24">
            </div>
            <div class="form-group" style="margin: 20px 0 20px; ">
                <label for="j_pwschange_newpassword" class="control-label x85">新密码：</label>
                <input type="password" class="form-control validate[required] required" name="pwschange_newpassword" id="j_pwschange_newpassword" value="" placeholder="新密码" size="24">
            </div>
            <div class="form-group">
                <label for="j_pwschange_secpassword" class="control-label x85">确认密码：</label>
                <input type="password" class="form-control validate[required,equals[j_pwschange_newpassword]] required" name="pwschange_secpassword" id="j_pwschange_secpassword" value="" placeholder="确认新密码" size="24">
            </div>
		</div>
		<div class="formBar">
            <ul>
                <li><button type="submit" class="btn btn-default btn-sm">保存</button></li>
                <li><button type="button" class="btn btn-close btn-sm">取消</button></li>
            </ul>
		</div>
	</form>
</div>