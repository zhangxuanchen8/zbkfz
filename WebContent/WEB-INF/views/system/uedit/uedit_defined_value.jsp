
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%=basePath%>DWZ/themes/css/bootstrap.min.css" rel="stylesheet" media="screen"/>

<script src="<%=basePath%>DWZ/js/jquery-1.7.2.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/bootstrap.min.js"></script>
<script>

function insertHtml(obj){
	var gjz = $(obj).html();
	window.parent.form_ue.execCommand( 'inserthtml', '<span style="text-decoration: none; font-family: 楷体, 楷体_GB2312, SimKai; color: rgb(23, 54, 93);">{'+gjz+'}</span>' );
	
	
}
function cutTab(obj){
	var tabid = $(obj).attr("data-tab");
	$(".uedit_all").hide();
	$("#"+tabid).show();
}

</script>

<div class="pageHeader">
	<div class="panel panel-default" style="margin-bottom:0px;">
	  <div class="panel-heading"  style="padding: 5px 5px;">
	  	<button type="button" data-tab="uedit_1" onclick="cutTab(this)" class="btn btn-default btn-xs">基本信息</button>
	  	<button type="button" data-tab="uedit_2" onclick="cutTab(this)" class="btn btn-default btn-xs">工作</button>
	  	<button type="button" data-tab="uedit_3" onclick="cutTab(this)" class="btn btn-default btn-xs">合同</button>
	  	<button type="button" data-tab="uedit_4" onclick="cutTab(this)" class="btn btn-default btn-xs">部门</button>
	  	<button type="button" data-tab="uedit_5" onclick="cutTab(this)" class="btn btn-default btn-xs">岗位</button>
	  	<button type="button" data-tab="uedit_6" onclick="cutTab(this)" class="btn btn-default btn-xs">工作</button>
	  	<button type="button" data-tab="uedit_7" onclick="cutTab(this)" class="btn btn-default btn-xs">学历</button>
	  	<button type="button" data-tab="uedit_8" onclick="cutTab(this)" class="btn btn-default btn-xs">职称</button>
	  	<button type="button" data-tab="uedit_9" onclick="cutTab(this)" class="btn btn-default btn-xs">执业资格</button>
	  	<button type="button" data-tab="uedit_10" onclick="cutTab(this)" class="btn btn-default btn-xs">政治面貌</button>
	  	<button type="button" data-tab="uedit_11" onclick="cutTab(this)" class="btn btn-default btn-xs">工资</button>
	  	<button type="button" data-tab="uedit_12" onclick="cutTab(this)" class="btn btn-default btn-xs">考评</button>
	  	<button type="button" data-tab="uedit_13" onclick="cutTab(this)" class="btn btn-default btn-xs">奖惩</button>
	  	<button type="button" data-tab="uedit_14" onclick="cutTab(this)" class="btn btn-default btn-xs">培训</button>
	  </div>
	  <div class="panel-body" style="padding: 5px 5px;">
	  	<div id="uedit_1" class="uedit_all" >
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">工号</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">姓名</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">性别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">民族</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">身份证号</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">年龄</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">出生日期</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">籍贯</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">家庭住址</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">婚姻状况</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">宗教信仰</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">E-mail</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">手机号</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">家庭电话</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前日期</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前时间</button>
	  		
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原排序号</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原工号</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原姓名</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原性别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原身份证号</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原民族</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原宗教信仰</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原健康状况</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原婚姻状况</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原籍贯</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原家庭住址</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原家庭电话</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原手机号</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原邮箱</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原在职状态</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原在岗状态</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原进本单位时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原流入方式</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原人员来源</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原离开本单位时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原流出方式</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原人员去向</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原入职前工龄</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原院龄</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原总工龄</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原备注</button>
			
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新工号</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新姓名</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新性别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新身份证号</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新民族</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新宗教信仰</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新健康状况</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新婚姻状况</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新籍贯</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新家庭住址</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新家庭电话</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新手机号</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新邮箱</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新在职状态</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新在岗状态</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新进本单位时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新流入方式</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新人员来源</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新离开本单位时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新流出方式</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新人员去向</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新入职前工龄</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新院龄</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新总工龄</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新备注</button>
			
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">操作人</button>
	  	</div>
	  	<div id="uedit_2" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原专技工作</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">专技工作</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原管理工作</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">管理工作</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原保障工作</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">保障工作</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">工作调动时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原主要工作</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新主要工作</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">工作调动原因</button>
	  	</div>
	  	
	  	
	  	<div id="uedit_3" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">合同名称</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">合同开始时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">合同结束时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">合同期限</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原合同名称</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原合同开始时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原合同结束时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原合同期限</button>
	  	</div>
	  	
	  	<div id="uedit_4" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原科室名称</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原科室调动时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原科室调动类型</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原科室调动管理部门</button>
		<!-- 	<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原科室考勤科室</button> -->
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新科室名称</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新科室调动时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新科室调动类型</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新科室调动管理部门</button>
		<!-- 	<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新科室考勤科室</button> -->
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">调动理由</button>
	  	</div>
	  	
	  	<div id="uedit_5" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原岗位分类</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原岗位明细</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原执行时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原变动理由</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新岗位分类</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新岗位明细</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新执行时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">岗位调动理由</button>
	  	</div>
	  	
	  	<div id="uedit_6" class="uedit_all" style="display: none;">
	  	
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">工作调动原因</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">工作调动时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">调至工作类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">调至工作</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原工作类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原工作</button>
	  	</div>
	  	
	  	<div id="uedit_7" class="uedit_all" style="display: none;">
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原毕业院校</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原学位授予时间</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原最高学历</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原学位</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原专业</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原入学时间</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原毕业时间</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原学制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原学习形式</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原学位证书编号</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原毕业证书编号</button>
	  		<!-- <button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原学历对应最高或初始标记</button> -->
	  	
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新毕业院校</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新学位授予时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新最高学历</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新学位</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新入学时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新毕业时间</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新学制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新学习形式</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新学位证书编号</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新毕业证书编号</button>
	  		<!-- <button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新学历对应最高或初始标记</button> -->
	  		
	  	</div>
	  	
	  	<div id="uedit_8" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新职称</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新职称类型</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新职称水平</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新职称专业</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新职称取得时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新评审组织</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新签发单位</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新职称证书编号</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新聘任职称名称</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新职称聘任时间</button>
	  	<!-- 	<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新职称证书附件</button> -->
	  		
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原职称</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原职称类型</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原职称水平   </button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原职称专业</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原职称取得时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原评审组织</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原签发单位</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原职称证书编号</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原聘任职称名称</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原职称聘任时间</button>
	  	<!-- 	<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原职称证书附件</button> -->
		</div>
	  	
	  	<div id="uedit_9" class="uedit_all" style="display: none;">
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新执业资格</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新执业类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新执业范围</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新执业地点</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新首次注册时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新执业证书编号</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新发证机关</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新发证时间</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新变更项目</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新批准机关</button>
	  	<!-- 	<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新职业资格证书附件</button> -->
	  		
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原执业资格</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原执业类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原执业范围</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原执业地点</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原首次注册时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原执业证书编号</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原发证机关</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原发证时间</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原变更项目</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原批准机关</button>
<!-- 	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原职业资格证书附件</button> -->
			
	  	</div>
	  	<div id="uedit_10" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新政治面貌</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新政治状态</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新政治状态变更时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新入党时单位</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新所在支部</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新入党介绍人</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新组织关系所属</button>
	  		
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原政治面貌</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原政治状态</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原政治状态变更时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原入党时单位</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原所在支部</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原入党介绍人</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原组织关系所属</button>
	  	</div>
	  	<div id="uedit_11" class="uedit_all" style="display: none;">
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新编制情况</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新工资来源</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新工资发放类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新岗位类别</button>
		
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新领导职务</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新岗位等级-事业编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新薪级-事业编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新双肩挑岗位-事业编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新岗位工资-事业编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新薪级工资-事业编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新护理10%-事业编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新绩效津贴-事业编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新职务-公务员</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新职务级别-公务员</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新职务档次-公务员</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新职务工资-公务员</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新级别工资-公务员</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新技术等级-机关技术工</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新岗位等级-机关技术工</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新等级工资-机关技术工</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新普通工等级-机关普通工</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新普通工岗位工资-机关普通工</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新职务津贴-机关编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新基本工资</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新工资执行时间</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新工资变动理由</button>
	  		
	  	    <button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原编制情况</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原工资来源</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原工资发放类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原岗位类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原领导职务</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原岗位等级-事业编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原薪级-事业编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原双肩挑岗位-事业编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原岗位工资-事业编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原薪级工资-事业编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原护理10%-事业编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原绩效津贴-事业编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原职务-公务员</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原职务级别-公务员</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原职务档次-公务员</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原职务工资-公务员</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原级别工资-公务员</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原技术等级-机关技术工</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原岗位等级-机关技术工</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原等级工资-机关技术工</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原普通工等级-机关普通工</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原普通工岗位工资-机关普通工</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原职务津贴-机关编制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原基本工资</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原工资执行时间</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原工资变动理由</button>
	  	</div>
	  	
	  	<div id="uedit_12" class="uedit_all" style="display: none;">
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新考评类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新考评性质</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新考评对应开始时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新考评对应结束时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新考评标题</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新考评分数</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新考评结果</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新考评评语</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新考评对应工作总结</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新考评备注</button>
			
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原考评类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原考评性质</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原考评对应开始时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原考评对应结束时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原考评标题</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原考评分数</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原考评结果</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原考评评语</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原考评对应工作总结</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原考评备注</button>
	  	</div>
	  	
	  	<div id="uedit_13" class="uedit_all" style="display: none;">
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新奖惩类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新奖或惩</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新奖惩等级</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新奖惩名称</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新奖惩获得日期</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新奖惩评定单位</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新奖惩获得原因</button>
			
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原奖惩类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原奖或惩</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原奖惩等级</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原奖惩名称</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原奖惩获得日期</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原奖惩评定单位</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原奖惩获得原因</button>
	  	</div>
	  	
	  	<div id="uedit_14" class="uedit_all" style="display: none;">
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新培训班名称</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新培训班方式</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新培训班费用</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新培训起始时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新培训终止时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新培训天数</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新培训学习内容</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新培训性质</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新培训类别</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新学习方式</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新主办单位</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新主办单位类别</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新总课时</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">新培训结果</button>
	  		
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原培训班名称</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原培训班方式</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原培训班费用</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原培训起始时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原培训终止时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原培训天数</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原培训学习内容</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原培训性质</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原培训类别</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原学习方式</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原主办单位</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原主办单位类别</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原总课时</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">原培训结果</button>
	  	</div>
	  </div>
	</div>
</div>