<%@ page contentType="text/html;charset=UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0"/>


<title>高级职称评审辅助系统</title>
<script src="<%=basePath%>DWZ/js/jquery-1.7.2.min.js"></script> 
<script src="<%=basePath%>DWZ/js/dwz.ui.js"></script> 
<script src="<%=basePath%>DWZ/js/dwz.core.js"></script> 
<script src="<%=basePath%>DWZ/js/dwz.min.js"></script> 
<script src="<%=basePath%>DWZ/js/jquery.cookie.js"></script> 
<script src="<%=basePath%>static/js/sha256.js"></script>
<script src="<%=basePath%>static/js/jquery/jquery.md5.js"></script>
<script src="<%=basePath%>static/js/jquery/jquery.tips.js"></script>
<script src="<%=basePath%>DWZ/plugins/icheck/icheck.min.js"></script> 
<script src="<%=basePath%>DWZ/plugins/other/jquery.autosize.js"></script> 
<script src="<%=basePath%>DWZ/plugins/bootstrapSelect/bootstrap-select.js"></script> 
<link href="<%=basePath%>DWZ/themes/css/bootstrap.min.css" rel="stylesheet" type="text/css">  


<style type="text/css">
* {font-family: "Verdana", "Tahoma", "Lucida Grande", "Microsoft YaHei", "Hiragino Sans GB", sans-serif;}
a:link {color: #285e8e;}
.main_box {
    position: absolute; top:50%; left:50%; margin-top:-260px; margin-left: -300px; padding: 30px; width:600px; height:460px;
    background: #FAFAFA; background: rgba(255,255,255,0.5); border: 1px #DDD solid;
    border-radius: 5px;
    -webkit-box-shadow: 1px 5px 8px #888888; -moz-box-shadow: 1px 5px 8px #888888; box-shadow: 1px 5px 8px #888888;
}
.main_box .setting {position: absolute; top: 5px; right: 10px; width: 10px; height: 10px;}
.main_box .setting a {color: #FF6600;}
.main_box .setting a:hover {color: #555;}
.login_logo {margin-bottom: 20px; height: 45px; text-align: center;}
.login_logo img {height: 85px;}
.login_msg {text-align: center; font-size: 16px;}
.login_form {padding-top: 20px; font-size: 16px;}
.login_box .form-control {display: inline-block; *display: inline; zoom: 1; width: auto; font-size: 18px;}
.login_box .form-control.x319 {width: 319px;}
.login_box .form-control.x164 {width: 164px;}
.login_box .form-group {margin-bottom: 20px;}
.login_box .form-group label.t {width: 120px; text-align: right; cursor: pointer;}
.login_box .form-group.space {padding-top: 15px; border-top: 1px #FFF dotted;}
.login_box .form-group img {margin-top: 1px; height: 32px; vertical-align: top;}
.login_box .m {cursor: pointer;}
.bottom {text-align: center; font-size: 12px;}
</style>
<script type="text/javascript">
var COOKIE_NAME = 'sys__username';
var COOKIE_PASSWORD = 'sys__password';
$(function(){
	
	
	choose_bg();
	 if ($.cookie(COOKIE_NAME)){
	    $("#j_username").val($.cookie(COOKIE_NAME));
	    $("#j_password").val($.cookie(COOKIE_PASSWORD));
	    $("#j_remember").attr('checked', true);
	} else {
		$("#j_username").focus();
	} 
	 $("#login_form").submit(function(){
		 var issubmit = true;
		 
		 
		 if("" == $("#j_username").val()){
				$("#j_username").tips({side : 3,msg : "用户名不能为空！",bg : '#FF2D2D',time : 2});
				$("#j_username").focus();
				 return false;
			}else if("" == $("#j_password").val()){
				$("#j_password").tips({side : 3,msg : "密码不能为空！",bg : '#FF2D2D',time : 2});
				$("#j_password").focus();
				return  false;
				
			}
			else{
				var loginname = $("#j_username").val();
				var password = $("#j_password").val();
				var verifyCode="1";
				var code = loginname+",jy,"+password+",jy,"+verifyCode;
				$.ajax({
					type : 'post',
					url : '<%=basePath%>backstage/system_login',
					data : {
						KEYDATA:code
			
					},
					dataType : 'json',
					success : function(data, textStatus) {
						debugger;
		            	var result=data.result;
		            	var cpuser=data.cpuser;
		            	
		            	if ("success" != result) {  //如果登录不成功，则再次刷新验证码
		            		loginAlert(result);	
		            		clearLoginForm();//清除信息
						}else{
							if("cp" == cpuser){
								var hrefurl='<%=basePath%>cpuser/index'
								window.location.href=hrefurl;
							}else{
								var hrefurl='<%=basePath%>backstage/index'
								window.location.href=hrefurl;
							}
						}
					}
				});
			
			var i_index  = 0;
			$(this).find('.in').each(function(i){
				if ($.trim($(this).val()).length == 0) {
					$(this).css('border', '1px #ff0000 solid');
					issubmit = false;
					if (i_index == 0)
						i_index  = i;
				}
			});
			if (!issubmit) {
				$(this).find('.in').eq(i_index).focus();
				return false;
			}
			var $remember = $("#j_remember");
			if ($remember.attr('checked')) {
				$.cookie(COOKIE_NAME, $("#j_username").val(), { path: '/', expires: 15 });
				$.cookie(COOKIE_PASSWORD, $("#j_password").val(), { path: '/', expires: 15 });
			} else {
				$.cookie(COOKIE_NAME, null, { path: '/' });  //删除cookie
			}
			var password = HMAC_SHA256_MAC($("#j_username").val(), $("#j_password").val());
			$("#j_password").val(HMAC_SHA256_MAC($("#j_randomKey").val(), password));
	        return false;
	 }

	}); 
});

function clearLoginForm(){	
	$("#j_username").val("");
	$("#j_password").val("");
	
}
function genTimestamp(){
	var time = new Date();
	return time.getTime();
}
function changeCode(){
}
function choose_bg() {
	var bg = Math.floor(Math.random() * 9 + 1);
	$('#dqwdswdwef').css('background-image', 'url(<%=basePath%>images/loginbg_088.jpg)');
	$('#dqwdswdwef').css('background-size', '100% 100%');
	
}

function dialogloading(){
	
	$("#jyLoadingStr").empty().append("正在登录 , 请稍后 ...");
	alert(2)
	$("#jyLoading").removeClass('hide').dialog({
		dialogClass: "loading-no-close",
		minHeight: 50,
		resizable: false,
		modal: true,
		show:{effect:"fade"},hide:{effect:"fade"}
	});
	
};


function dialogloadingClose(){
	$("#jyLoading").dialog("close");
};
function dialogerror(Str){
	$("#jyErrorStr").empty().append(Str);
	$("#jyError").removeClass('hide').dialog({
		resizable: false,
		dialogClass: "title-no-close",
		modal: true,//设置为true，该dialog将会有遮罩层
		title: "<div class='widget-header'><h4 class='font-bold red' >错误</h4></div>",
		title_html: true,
		show:{effect:"shake",duration: 100},
		hide:{effect:"explode"},
		buttons: [{  
			html: "<i class='icon-ok bigger-110'></i>&nbsp;确认","class" : "btn btn-primary btn-xs",
			click: function() {						 
				$(this).dialog("close");
			}
		}]
	}); 
};
function loginAlert(msg) {
	if("nullup" == msg){
		$("#j_username").tips({side : 3,msg : "用户名或密码不能为空！",bg : '#FF2D2D',time : 2});
		$("#j_username").focus();
	}else if("usererror" == msg){
		$("#j_username").tips({side : 3,msg : "用户名有误！",bg : '#FF2D2D',time : 2});
		$("#j_username").focus();
	}else if("mmcw" == msg){
		$("#j_password").tips({side : 3,msg : "密码错误！",bg : '#FF2D2D',time : 2});
		$("#j_password").focus();
	}else if("attemptserror" == msg){
		dialogerror("错误次数过多！");
	}else if("error" == msg){
		dialogerror("输入有误！");
	}else if("inactive" == msg){
		//dialogerror("上传功能未开启！");
		$("#j_username").tips({side : 3,msg : "上传功能未开启！",bg : '#FF2D2D',time : 2});
		$("#j_username").focus();
	}
}


</script>

<div id="dqwdswdwef" layoutH='0' style="height: 100%">
<!--[if lte IE 7]>
<style type="text/css">
#errorie {position: fixed; top: 0; z-index: 100000; height: 30px; background: #FCF8E3;}
#errorie div {width: 900px; margin: 0 auto; line-height: 30px; color: orange; font-size: 14px; text-align: center;}
#errorie div a {color: #459f79;font-size: 14px;}
#errorie div a:hover {text-decoration: underline;}
</style>
<div id="errorie"><div>您还在使用老掉牙的IE，请升级您的浏览器到 IE8以上版本 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div></div>
<![endif]-->
<div class="main_box">
    <!-- <div class="setting"><a href="#" onclick="choose_bg();" title="更换背景"><span class="glyphicon glyphicon-th-large"></span></a></div> -->
	<div class="login_box">
        <div class="login_logo">
            <img src="<%=basePath%>images/loginlogo.png"  height="50">
        </div>
        <!--
		<c:if test="${!empty message}">
			<div class="login_msg">
	      		<font color="red">${message }</font>
	    	</div>
	    </c:if>
        -->
        <div class="login_form">
            <input type="hidden" value="${randomKey }" id="j_randomKey" />
    		<form action="index.html" id="login_form" method="post">
                <input type="hidden" name="jfinal_token" value="${jfinal_token }" />
    			<div class="form-group">
    				<label for="j_username" class="t">用户名：</label> <input id="j_username" value="" name="username" type="text" class="form-control x319 in" autocomplete="off">
    			</div>
    			<div class="form-group">
    				<label for="j_password" class="t">密　码：</label> <input id="j_password" value="" name="passwordhash" type="password" class="form-control x319 in">
    			</div>
    			
    			<div class="form-group">
                    <label class="t"></label>
                    <label for="j_remember" class="m"><input id="j_remember" type="checkbox" value="true">&nbsp;记住登陆账号!</label>
    			</div>
    			<div class="form-group space">
                    <label class="t"></label>　　　
    				<input type="submit" id="login_ok" value="&nbsp;登&nbsp;录&nbsp;" class="btn btn-primary btn-lg">&nbsp;&nbsp;&nbsp;&nbsp;
    				<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-default btn-lg">
    			</div>
    		</form>
        </div>
	</div>
	<!-- <div class="bottom">Copyright &copy; 2017 <a href="#">医院人事管理系统  - 系统登陆</a></div> -->
</div>

<div id="jyLoading" class="hide">
	<div class="center">
		<h4><img alt="loading" src="<c:url value="<%=basePath%>static/images/system/loading.gif"/>"><span id="jyLoadingStr"></span></h4>
	</div>
</div>
</div>
<%--  <!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0"/>
<%@include file="../common/includeBaseSet.jsp" %>
<link rel="stylesheet" href="${jypath}/static/css/system/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="${jypath}/static/css/system/ace/font-awesome.min.css" />
<link rel="stylesheet" href="${jypath}/static/css/system/jquery/jquery-ui-1.10.3.full.min.css" />
<link rel="stylesheet" href="${jypath}/static/css/system/ace/ace.min.css" /> 
<link rel="stylesheet" href="${jypath}/static/css/system/ace/camera.css" />
<link rel="stylesheet" href="${jypath}/static/css/system/system/login.css" />
<script src="${jypath}/static/js/jquery/jquery.md5.js"></script>
<script src="${jypath}/static/js/jquery/jquery-ui-1.10.3.full.min.js"></script>
<script src="${jypath}/static/js/system/login/login.js"></script>
</head>
<body class='login-layout' style="background-image: url('${jypath}/static/images/system/loginbg.jpg');margin-top:0px;" >
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1><img src="${jypath}/static/images/font.PNG"/></h1>
							<!-- <h4 class="blue">&copy; base</h4> -->
						</div>
						<div class="space-6"></div>
						<div class="position-relative">
							<div id="login-box" class="login-box visible widget-box no-border">
								<div  class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="icon-github-sign green"></i> 请输入您的信息
										</h4>
										<div class="space-6"></div>
										<form id="loginForm" method="post">
											<fieldset>
												<label class="block clearfix">
													<span class="block input-icon input-icon-right"> 
														<input type="text" id="accountNameId" name="accountName" required="required" maxlength="16" class="form-control" placeholder="请输入用户名" /><i class="icon-user"></i>
													</span>
												</label> 
												<label class="block clearfix"> 
													<span class="block input-icon input-icon-right"> 
														<input type="password" id="passwordId" name="password"  required="required" maxlength="16" class="form-control" placeholder="请输入密码" /><i class="icon-lock"></i>
													</span>
												</label>
												<label style="display:none"> 
													<span > 
														<input class="col-xs-5" id="verifyCodeId" name="verifyCode" required="required" maxlength="4" type="tel" class="form-control" placeholder="请输入验证码" onkeyup="this.value=this.value.replace(/\D/g,'')" />
													</span>
													<span class="col-sm-5"> 
														<img  id="vimg" style="cursor:pointer;width:80px;height:30px;" title="验证码" width="60" height="37" />
													</span>
												</label>
												<div class="space"></div>
												<div class="clearfix">
													<!-- <label class="inline"> <input type="checkbox" class="ace" /> <span class="lbl"> Remember Me</span></label> -->
													<button id="loginBtn" type="button" class="width-35 pull-right btn btn-sm btn-primary">
														<i class="icon-key"></i> 登录
													</button>
												</div>
												<div class="space-4"></div>
											</fieldset>
										</form>
									</div>
									<!-- /widget-main -->
									<div class="toolbar clearfix">
										<div>
<!-- 											<a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
												<i class="icon-arrow-left"></i>
											</a> -->
										</div>
										<div>
<!-- 											<a href="#" onclick="show_box('register-box'); return false;" class="user-signup-link">
												<i class="icon-arrow-right"></i>
											</a> -->
										</div>
									</div>
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /login-box -->
							<%@include file="forgot.jsp" %>
							<!-- /forgot-box -->
							<%@include file="register.jsp" %>
							<!-- /signup-box -->
							<%@include file="../common/dialog.jsp" %>
						</div>
						<!-- /position-relative -->
					</div>
				</div>
				<!-- /.col -->
			</div>
		</div>
	</div>
</body>
</html>   --%>