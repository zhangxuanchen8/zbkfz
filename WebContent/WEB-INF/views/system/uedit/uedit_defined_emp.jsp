
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
	  	</div>
	  	<div id="uedit_2" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">参加工作时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">入职时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">流入方式</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">在职状态</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">编制情况</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">离职时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">流出方式</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">院龄</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">工龄</button>
	  	</div>
	  	<div id="uedit_3" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">合同名称</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">合同开始时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">合同结束时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">合同期限</button>
			
	  	</div>
	  	<div id="uedit_4" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">科室调动时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">科室调动管理部门</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">科室调动类型</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前所在科室</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前考勤科室</button>
	  	</div>
	  	<div id="uedit_5" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">调至当前岗位时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前岗位类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前所在岗位</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">调至当前岗位原因</button>
	  	</div>
	  	<div id="uedit_6" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前所有工作</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前主要工作</button>
	  	</div>
	  	<div id="uedit_7" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前最高学历</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前最高学历对应专业</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前最高学历对应学校</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前最高学历对应入学时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前最高学历对应毕业时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前最高学历对应学制</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前最高学历对应学位</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前最高学历对应学位授予事件</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前最高学历对应学位证书编号</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前最高学历对应毕业证书编号</button>
	  	</div>
	  	<div id="uedit_8" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前职称类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前职称等级</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前职称名称</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前职称获取时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前职称评审组织</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前职称签发单位</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前职称证书编号</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前职称聘任职称名称</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前职称聘任时间</button>
		</div>
	  	<div id="uedit_9" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前执业资格名称</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前执业类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前执业范围</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前执业地点</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">执业资格首次注册时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前执业资格证书编号</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前执业资格发证机关</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前执业资格发证时间</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前执业资格变更项目</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前执业资格批准机关</button>
	  	</div>
	  	<div id="uedit_10" class="uedit_all" style="display: none;">
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前政治面貌</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前政治状态</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前政治面貌变更时间</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">入党派时单位</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">入党派时所在支部</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">入党派时介绍人</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">党派关系是否在本院</button>
	  	</div>
	  	<div id="uedit_11" class="uedit_all" style="display: none;">
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前工资来源</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前岗位类别</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前岗位等级</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前薪级</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前基本工资</button>
			<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前工作发放类别</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">当前工资执行日期</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">是否领导</button>
	  		<button class="button btn btn-default btn-xs" onclick="insertHtml(this)">是否双肩挑</button>
	  	</div>
	  </div>
	</div>
</div>