<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EDGE">
<title>区域卫生人事管理系统</title>
<meta name="Keywords" content="人力资源，his,区域医疗"/>
<meta name="Description" content="区域医疗管理系统"/> 
<base href="<%=basePath %>"/>
<link href="<%=basePath%>DWZ/themes/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
<link href="<%=basePath%>DWZ/plugins/uploadify/css/uploadify.css" rel="stylesheet" media="screen"/>
<link href="<%=basePath%>DWZ/plugins/Huploadify/Huploadify.css" rel="stylesheet" media="screen"/>
<link href="<%=basePath%>DWZ/themes/css/style.css" rel="stylesheet" media="screen"/>
<link href="<%=basePath%>DWZ/themes/purple/core.css" rel="stylesheet" media="screen"/>
<link href="<%=basePath%>DWZ/plugins/kindeditor_4.1.10/themes/default/default.css" rel="stylesheet" media="screen" />
<link href="<%=basePath%>DWZ/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet" media="screen" />
<link href="<%=basePath%>DWZ/plugins/validationEngine/validationEngine.jquery.css" rel="stylesheet" media="screen" />
<link href="<%=basePath%>DWZ/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet" media="screen" />
<link href="<%=basePath%>DWZ/themes/css/FA/css/font-awesome.min.css" rel="stylesheet" media="screen" />
<script src="<%=basePath%>DWZ/js/jquery-1.7.2.min.js"></script>
<script src="<%=basePath%>DWZ/js/jquery.cookie.js"></script>
<script src="<%=basePath%>DWZ/js/dwz.main.js"></script>
<script src="<%=basePath%>DWZ/js/jy.main.min.js"></script>
<script src="<%=basePath%>static/js/echarts.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/swfupload/swfupload.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/uploadify/scripts/jquery.uploadify.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/Huploadify/jquery.Huploadify.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/kindeditor_4.1.10/kindeditor-all.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/kindeditor_4.1.10/lang/zh_CN.js"></script>
<script src="<%=basePath%>DWZ/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/ztree/jquery.ztree.excheck-3.5.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/ztree/jquery.ztree.exedit-3.5.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/ztree/jquery.ztree.exhide-3.5.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/validationEngine/jquery.validationEngine-zh_CN.js"></script>
<script src="<%=basePath%>DWZ/plugins/validationEngine/jquery.validationEngine.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/bootstrap.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/bootstrapTags/bootstrap-tags.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/icheck/icheck.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/dragsort/jquery.dragsort-0.5.1.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/clock/jqClock.min.js"></script>
<script src="<%=basePath%>DWZ/plugins/other/jquery.autosize.js"></script>
<script src="<%=basePath%>DWZ/js/ableexport.js"></script>
<script src="<%=basePath%>DWZ/js/xlsx.full.min.js"></script>
<script src="<%=basePath%>static/js/loadSelect.js"></script>
<script  src="<%=basePath%>static/jqprint/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
$(function(){
    DWZ.init("<%=basePath%>DWZ/dwz.frag.xml", {
        loginUrl:"<%=basePath%>logintimeout.html", loginTitle:"登录",    // 弹出登录对话框
        statusCode:{ok:200, error:300, timeout:301}, //【可选】
        pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
        keys: {statusCode:"statusCode", message:"message"}, //【可选】
        ui:{hideMode:'display'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
        debug:false,    // 调试模式 【true|false】
        callback:function(){
            initEnv();
            $("#topTheme").theme({themeBase:"<%=basePath%>DWZ/themes"}); // themeBase 相对于index页面的主题base路径
        }
    });
    //getMenu();
    if('${reductions.size()}'==0){
    	$("#inoutbell").removeClass("animated");
    }else{
    	$("#inoutbell").addClass("animated");
    }
});
//菜单-事件
function MainMenuClick(event, treeId, treeNode) {
    if (treeNode.isParent) {
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        zTree.expandNode(treeNode);
        return;
    }
    if (treeNode.target && treeNode.target == 'dialog'){
        $.pdialog.open(treeNode.nurl, treeNode.rel, treeNode.name, {});
    } else if (treeNode.target && treeNode.target == 'navTab') {
        navTab.openTab(treeNode.rel, treeNode.nurl, { title: treeNode.name});
    }
}
function getMenu(){
	$.ajax({
			async:false,
		cache:false,
		url:"<%=basePath%>backstage/menu/getMenu",
		type : "POST",
		success : function(data) {
			$("#main_treeDemo_0").attr("nodes",data.toString());
		}
	});
}
function clickinoutdialog(obj){
	$("#inoutdialog").attr("href",obj.href);
	$("#inoutdialog").attr("title",obj.title);
	$("#inoutdialog").click();
}
</script>
<style>
/* TADA */
@keyframes tada {
	0% {transform: scale(1)}
	10%,20% {transform:scale(.9) rotate(-8deg);}
	30%,50%,70% {transform:scale(1.3) rotate(8deg)}
	40%,60% {transform:scale(1.3) rotate(-8deg)}
	80%,100% {transform:scale(1) rotate(0)}
}
.faa-tada.animated,
.faa-tada.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-tada {
	animation: tada 2s linear infinite;
}
.faa-tada.animated.faa-fast,
.faa-tada.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-tada.faa-fast {
	animation: tada 1s linear infinite;
}
.faa-tada.animated.faa-slow,
.faa-tada.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-tada.faa-slow {
	animation: tada 3s linear infinite;
}
</style>
</head>
<body scroll="no">
    <div id="layout">
        <div id="header">
            <div class="headerNav">
                <div class="logo" style="margin-top: 4px;"><img src="<%=basePath%>images/indexlogo.png" /></div>
                <ul class="topnav">
                    <li>
                      <a class=" j-popover" data-container="body" data-toggle="popover" data-placement="bottom"  data-el-content="#popover-01" data-html="true">
					  <i id="inoutbell" class="fa fa-bell fa-1x faa-tada animated" style="color:#FFD700"></i>
					  </a>
                       <div id="popover-01" hidden="hidden">
                       <c:forEach var="l" items="${reductions}" varStatus="stat">
                       <p>${l.c_name}<a href="backstage/employee/employeedetails?pid=${l.p_id}&areaFlag=Y&pageid=inout&inoutid=${l.id}" title=' <c:if test="${l.status=='N'}">调入申请（${l.c_name}由${l.transfer_out_hosname}调入本院）</c:if><c:if test="${l.status=='Y'}">调出申请（${l.c_name}由本院调入${l.transfer_in_hosname}）</c:if><c:if test="${l.status=='J'}">借调申请（${l.c_name}由本院调入${l.transfer_in_hosname}）</c:if>' 
                       onclick="clickinoutdialog(this);return false"><c:if test="${l.status=='N'}">调入</c:if><c:if test="${l.status=='Y'}">调出</c:if><c:if test="${l.status=='J'}">借调</c:if>申请</a></p>
                       </c:forEach>
                       </div>
                        <a id="inoutdialog" hidden="hidden" href="" target="dialog" title="111" rel="form" width="950" height="602"></a>
                    </li>
                   <!--  <li>2014-07-23&nbsp;<span id="clock"></span></li> -->
                    <li>当前用户：${currentAccount.name}</li>
                    <li><a href="<%=basePath %>backstage/baseUser/changePsd" target="dialog" mask="true" rel="form" width="400" height="260">修改密码</a></li>
                    <li><a href="<%=basePath %>/backstage/index">网站首页</a></li>
                    <!-- <li><a href="http://www.j-ui.com" target="_blank">DWZ(J-UI)官网</a></li> -->
                    <li><a href="<%=basePath%>backstage/system_logout">退出</a></li>
                </ul>
                <ul class="topTheme" id="topTheme">
                    <li theme="default" class="theme_default" title="黑白分明"><i class="fa fa-circle"></i></li>
                    <li theme="orange" class="theme_orange" title="橘子红了"><i class="fa fa-circle"></i></li>
                    <li theme="purple" class="theme_purple" title="紫罗兰"><i class="fa fa-circle"></i></li>
                    <li theme="blue" class="theme_blue" title="青出于蓝"><i class="fa fa-circle"></i></li>
                    <li theme="green" class="theme_green" title="绿草如茵"><i class="fa fa-circle"></i></li>
                </ul>
            </div>
        </div>
        <div id="leftside">
            <div id="sidebar_s">
                <div class="collapse">
                    <div class="toggleCollapse"><div title="展开菜单"><i class="fa fa-angle-double-right"></i></div></div>
                </div>
            </div>
            <div id="sidebar">
                <div class="toggleCollapse"><h2 >主菜单</h2><div title="收缩菜单"><i class="fa fa-angle-double-left"></i></div></div>
                <div class="panel-group panel-main j-accordion" id="accordion_menu" data-heightbox="#sidebar" data-offsety="26">
                    <c:forEach items="${menuList}" var="l" varStatus="index">
                    	<c:if test="${index.index==0 }">
                    		<div class="panel panel-default">
	                        <div class="panel-heading panelContent">
	                            <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion_menu" href="#collapse_${l.id}" class="active"><i class="${l.icon }"></i>&nbsp;${l.name}</a></h4>
	                        </div>
	                        <div id="collapse_${l.id}" class="panel-collapse panelContent collapse in">
	                            <div class="panel-body">
	                                <ul id="main_treeDemo_${l.id}" class="ztree ztree_main" attrs = '{"onClick":"MainMenuClick", "expandAll":true}' 
	                                    nodes='${l.menu}'>
	                                </ul>
	                            </div>
	                        </div>
	                        <div class="panelFooter"><div class="panelFooterContent"></div></div>
                    	</div>
                    	</c:if>
                    	<c:if test="${index.index>0 }">
                    		<div class="panel panel-default">
	                        <div class="panel-heading panelContent">
	                            <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion_menu" href="#collapse_${l.id}" class="active"><i class="${l.icon }"></i>&nbsp;${l.name}</a></h4>
	                        </div>
	                        <div id="collapse_${l.id}" class="panel-collapse panelContent collapse">
	                            <div class="panel-body">
	                                <ul id="main_treeDemo_${l.id}" class="ztree ztree_main" attrs = '{"onClick":"MainMenuClick", "expandAll":true}' 
	                                    nodes='${l.menu}'>
	                                </ul>
	                            </div>
	                        </div>
	                        <div class="panelFooter"><div class="panelFooterContent"></div></div>
                    	</div>
                    	</c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div id="container">
            <div id="navTab" class="tabsPage">
                <div class="tabsPageHeader">
                    <div class="tabsPageHeaderContent">
                        <ul class="navTab-tab nav nav-tabs">
                            <li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
                        </ul>
                    </div>
                    <div class="tabsLeft"><i class="fa fa-angle-double-left"></i></div>
                    <div class="tabsRight"><i class="fa fa-angle-double-right"></i></div>
                    <div class="tabsMore"><i class="fa fa-angle-double-down"></i></div>
                </div>
                <ul class="tabsMoreList">
                    <li><a href="javascript:;">我的主页</a></li>
                </ul>
                <div class="navTab-panel tabsPageContent layoutBox" id="navTab_content">
                    <div class="page unitBox">
						<div class="page unitBox">
							<div class="pageFormContent" layoutH="0">
								<div style="margin-right: 0px; overflow: hidden;">
									<div class="row" style="padding: 0 10px;">
									<!-- 公告 -->
										<div class="col-md-6">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h3 class="panel-title">区域公告</h3>
												</div>
												<div class="panel-body">
													<table
														class="table table-condensed table-hover table-striped"
														width="100%">
														<thead>
															<tr>
																<th class="center">时间</th>
																<th class="center">by</th>
																<th class="center">内容</th>
																<th class="center">状态</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td class="center">07/23 09:30</td>
																<td class="center">admin</td>
																<td class="center">人事系统更新通知。</td>
																<td class="center"><span
																	class="label label-warning">未读</span></td>
															</tr>
															<tr>
																<td class="center">07/20 15:05</td>
																<td class="center">admin</td>
																<td class="center">劳动节放假公告。</td>
																<td class="center"><span
																	class="label label-success">已读</span></td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h3 class="panel-title">院内公告</h3>
												</div>
												<div class="panel-body">
													<table
														class="table table-condensed table-hover table-striped"
														width="100%">
														<thead>
															<tr>
																<th class="center">时间</th>
																<th class="center">by</th>
																<th class="center">内容</th>
																<th class="center">状态</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td class="center">07/23 09:30</td>
																<td class="center">admin</td>
																<td class="center">明天上午10：00开会。</td>
																<td class="center"><span
																	class="label label-warning">未读</span></td>
															</tr>
															<tr>
																<td class="center">07/20 15:05</td>
																<td class="center">admin</td>
																<td class="center">项目X的完成进度通报。</td>
																<td class="center"><span
																	class="label label-success">已读</span></td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
								<c:if test="${warning_index[0]!=null&&warning_index[0]!='' }">
									<c:forEach var="l" items="${warning_index }" varStatus="stat">
										<c:if test="${l[0]!=null&&l[0]!='' }">
										<div class="col-md-4">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h3 class="panel-title">${l[0].warn_type}</h3>
												</div>
												<div class="panel-body">
													<table
														class="table table-condensed table-hover table-striped"
														width="100%">
														<thead>
															<tr>
																<th class="center">类别</th>
																<th class="center">数量</th>
																<th class="center">操作</th>
															</tr>
														</thead>
														<tbody>
														<c:forEach var="s" items="${l}" varStatus="stat">
															<tr>
																<td class="left">${s.warn_name} </td>
																<td class='center'><span style="background-color: red" class="badge">${s.counts}</span></td>
																<td class="center">
																	<%-- <a id="main_treeDemo_0_9_a"   href = '<%=basePath%>backstage/employee/index?pageid=yj${l[0].warn_type}&warn_type=${l[0].warn_type}&warn_name=${s.warn_name}' class="level1" treenode_a=""  target="navTab" style="" title="合同批量签订">
																	<span id="main_treeDemo_0_9_ico" title="" treenode_ico="" class="button ico_docu" style=""></span>
																	<span id="main_treeDemo_0_9_span">批量签订</span></a> --%>
																	${s.warn_html}
																</td>
															</tr>
														</c:forEach>	
														</tbody>
													</table>
												</div>
											</div>
										</div>
										</c:if>
									</c:forEach>
								</c:if>	
										<div class="col-md-12">
											<div class="panel panel-default">
									  <div class="panel-heading"><h2 class="panel-title">最近登陆 <a href="<%=basePath%>backstage/contract/historyIndex"   target="navTab" title="历史记录" ><i class="fa fa-angle-double-left" style="float: right;"></i></a></h2></div>
												<div class="panel-body">
													<table class="table table-condensed table-striped"
														width="100%">
														<thead>
															<tr>
															   <th class="center" width="10%">序号</th>
																<th class="center" width="40%">时间</th>
																<th class="center" width="20%">登陆账号</th>
																<th class="center" width="30%">IP</th>
															</tr>
														</thead>
														<tbody>
														   <c:forEach items="${list_loginLog}" var="l" varStatus="stat">
														   <tr>
														    <td class="center" width="10%">${ stat.index + 1}</td>
															<td class="center" width="40%"><fmt:formatDate value="${l.loginTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
															<td class="center" width="20%">${l.aName}</td>
															<td class="center" width="30%">${l.loginIP}</td>
															</tr>	
															</c:forEach>	
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
                </div>
            </div>
        </div>
    </div>
    <div id="footer">
    	Copyright &copy; 2017 <a href="javascript:void(0);">医院人事管理系统</a>　
    </div>
</body>
</html>