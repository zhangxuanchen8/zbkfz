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
	<title>浙江省卫生高级职称评聘监管服务平台</title>
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
                loginUrl:"<%=basePath%>index.jsp", loginTitle:"登录",    // 弹出登录对话框
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
        	loadOrgTree();
        });
        
        function loadState(){
        	var ch = document.getElementsByClassName("check");
        	var z = 0;
        	var f = 0;
        	for(var i=0;i<ch.length;i++){
            	var ch1 = document.getElementById("checka"+i);
				if(ch1.checked){
        			z += 1;
        		}
	        	var ch2 = document.getElementById("checkb"+i);
        		if(ch2.checked){
        			f += 1;
        		}
        	}
        	var t = (z*100/(z+f)).toFixed(2);
        	$("#state1").val(z);
        	$("#state2").val(f);
        	$("#state3").val(z+f);
        	if(t!='NaN'){
        		$("#state4").val(t+"%")
        	}else{
        		$("#state4").val(0);
        	}
        }
        function ycfpszy_d(){
    		var check = '';
    		var ch = document.getElementById('ischange_d');
    		if(ch.checked){
    			check='check';
    		}
    		$("#check_d").val(check);
    		$("#submit_d").click();
    	}
        
        //菜单-事件
        function dialogAjaxDoneds(json){
            DWZ.ajaxDone(json);
            if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
                if (json.navTabId){
                    navTab.reload(json.forwardUrl, {navTabId: json.navTabId});
                    if(json.tabindex != null && json.tabindex != ""){
                        $.pdialog.reloadDialog(json.navTabId,json.tabindex);
                    }
                } else {
                    var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
                    var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
                    navTabPageBreak(args, json.rel);
                }
                if ("closeCurrent" == json.callbackType) {
                    $.pdialog.closeCurrent();
                }
            }
        }

        function upload(){
        	$("#resultUpload").click();
        };
        function importResult(){
        	var fileName = $("#resultUpload").val();
        	if(fileName==null||fileName.split(".").length<=1){
        		 alertMsg.error("请选择后缀为jpg,pdf的文件!");
        			return;
        	 }else if(fileName.split(".")[fileName.split(".").length-1]!='jpg' && fileName.split(".")[fileName.split(".").length-1]!='JPG' && fileName.split(".")[fileName.split(".").length-1]!='pdf' && fileName.split(".")[fileName.split(".").length-1]!='PDF'){
        			alertMsg.error("请选择后缀jpg,pdf的文件!");
        			return;
        	}
        	ResultfjImports();
        };
        
        function ResultfjImports() {
        	$("#loadingGain").show();
        	var fileName = $("#resultUpload").val();
        	var fileObj = document.getElementById("resultUpload").files[0];// 获取读取的File对象
        	var size = fileObj.size;
        	if(size>4000000){
        		alertMsg.error("文件过大，请重新上传,不可大于4M");
        		$("#loadingGain").hide();
        		return;
        	}
        	//-------------------------
        	var FileController = "<%=basePath%>backstage/tool/webuploader/uploadPic.htm"; // 接收上传文件的后台地址  
        	if (fileObj) {
        		// FormData 对象  
        		var form = new FormData();
        		form.append("file", fileObj);// 文件对象     
        		// XMLHttpRequest 对象  
        		var xhr = new XMLHttpRequest();
        		xhr.open("post", FileController, true);
        		xhr.onload = function() {
        			$("#resultUpload").after($("#resultUpload").clone().val(""));
        			$("#resultUpload").remove();
        			data = xhr.responseText;
        			data=data.replace(/\s/g, "");
        			var json = $.parseJSON( data );
        				//alert(json.saveUrl+" "+json.imgname);
        			if(json.resMsg=='上传成功'){
        				var demolist = $("#demoList").html();
        				$("#demoList").html(demolist+'<tr ><td style="display:none"><input id="imgurl" name="imgurl" class=" form-control " style="width: 100%; height: 100%;" value="'+json.saveUrl+'" ></td><td ><input id="ddIns" name="ddIns" class=" form-control " style="width: 100%; height: 100%;" value="'+json.imgname+'" ></td><td><input id="ioprice_addIns" class="form-control " style="width: 100%; height: 100%;" value="等待保存"></td><td class="center"><a href="javascript:;" style="font-size: 23px;color: red;text-decoration: none;" class="j-del" target="ajaxTodo" callfun="table_del_callfun" title="确定要删除该行信息吗？">×</a></td></tr>');
        				
        				var fujian = $("#ResultUploadFile").val();
        				$("#ResultUploadFile").val(fujian+','+json.saveUrl);
        				$("#loadingGain").hide();
        			}
        			if(json.resMsg=='超过上传文件大小限制'){
        				alertMsg.error("文件过大，请重新上传");
        				$("#loadingGain").hide();
        			}
        			if(json.resMsg=='请上传允许格式的文件'){
        				alertMsg.error("格式有误，请重新上传");
        				$("#loadingGain").hide();
        			}
        			//--------------接保存的方法----------------------------
        		};
        		xhr.send(form);
        	} else {
        		alertMsg.error("未选择文件");
        	}
        }
        
        function clickColor(obj){  
            $("#fj_log_body").parent().find("tr").find("td").css('background-color','#ffffff');
            $("#fj_log_body").parent().find("tr").find("td").css('background-color','');
          $(obj).children().css('background-color','#79ff9f');
   }  
       function scfj(){
    	   $("#menusubmit").click();
       }
       
       function scbj() {
    		$.ajax({
    			async:false,
    			cache:false,
    			url:"<%=basePath%>cpuser/scbj",
    			type : "get",
    			success : function(data) {
    					if(data==1){
    						alertMsg.correct("提交成功");
    					}else{
    						alertMsg.error("提交失败");
    					}
    			}
    		});
    	}
       function zpfbc() {
    	var ids = $("#zbkids").val();
    	var zpfids = "";
    	if(ids!=null && ids !=''){
    		var id = ids.split(",");
    		for(var i=0;i<id.length-1;i++){
    			var myH=document.getElementById("zpf"+id[i]).value;
    			zpfids+=myH+",";
    		}
    	}
    	 $.ajax({
			async:false,
			cache:false,
			url:"<%=basePath%>cpuser/zpfbc",
			data:{ids:ids,zpfids:zpfids},
			type:"POST",
			success : function(data) {
				if(data=='ok'){
					alertMsg.correct("保存成功！！");
				}else{
					alertMsg.error("保存失败!");
				}
			}
		}); 
   		} 
       
       var keyNames = {  
    		    "name": "name",     //描述  
    		    "finlscore":"finlscore",
    		    "score":"score"
    		} 
     
    	function loadOrgTree() {
    		var h = document.documentElement.clientHeight || document.body.clientHeight;
    		var oDiv=document.getElementById('heidiv');
    	    oDiv.style.height=h*0.8+"px";
    		var a = '${pid}';
    		$.ajax({
    		async:false,
    		cache:false,
    		url:"<%=basePath%>cpuser/getrysxbgTree?supunit2=${supunit2}&type=all",
    		type : "POST",
    		dataType:"json",
    		success : function(data) {
    			 	initTable(a,data,keyNames);
    			}
    		});
    	}




    	/** 
    	 * 业务不同， key的名称不同, 调用之前可以赋值，传null则用下面默认 
    	 */  

    	var maxLevel=0;  
    	/** 
    	 * 初始化表格 
    	 */  
    	function initTable(parentId, data, keyNames) {  
    	    maxLevel = getMaxLeve(data);
    	    $("#jcsxTd").attr('colspan', maxLevel+1)  
    	    var _ = {"id":parentId, "childrens":[]};  
    	    $.each(data, function(i, n) {  
    	        n['childrens']=[];  
    	        _ = initTreeData(_, n);  
    	    });  	

    	    createTable(_, keyNames);  
    	}  
    	/** 
    	 * 组装改数据成一棵树 
    	 */  
    	function initTreeData(_, d) {  
    	    if(_.id == d.parentId) {  
    	        _['childrens'].push(d);  
    	        return _;  
    	    }  
    	    if(0 == _['childrens'].length) {  
    	        return _;  
    	    }  
    	    $.each(_['childrens'], function(i, n) {  
    	        _['childrens'][i]= initTreeData(_['childrens'][i], d);  
    	    });  
    	    return _;  
    	}  
    	  

    	function createTable(_, keyNames) {  
    	var ly = 0;
    	$.each(_['childrens'], function(i, n) {

    	        var htmls = '';  
    	        
    	        if(n['childrens'].length > 0) {  
    	            htmls = '<tr>'+getChildrenHtml(htmls, n, maxLevel,ly);  
    	        } else {  
    	            htmls += '<tr><td width="10%">'+n.name+'</td>';  
    	            for(var j = 1; j < maxLevel; j++) {  
    	                htmls += '<td></td>';  
    	            }  
    	            htmls += '<td colspan="11" rowspan="1" height="30px">'+""+'</td></tr>';   
    	        }  
    	        $("#rootTable9").append(htmls);  
    	    });  
    	}  
    	function getChildrenHtml(htmls, data, ml,ly) { 

    			ly++;
    	    if(data['childrens'].length > 0) {  
    	    	htmls += '<td style="line-height:180%" width="11%" height="30px" rowspan="'+getTotleRowCount(data['childrens'], 0)+'">'+data.name+'('+data.score+'分)</td> <input type="hidden" name="item" value="'+data.id+'">';  
    	        $.each(data['childrens'], function(i, n) {  
    	        	if(i > 0) {  
    	                htmls += '<tr>';  
    	            }  
    	            htmls = getChildrenHtml(htmls, n, ml,ly);  
    	        });  
    	    } else {  
    	       if(ly<5){
    	    	 var zxc = 5-ly+1;
    	           htmls += '<td width="12%" style="line-height:180%" height="30px" colspan="'+zxc+'"  rowspan="'+getTotleRowCount(data['childrens'], 0)+'">';
    	    	   htmls += '&nbsp;&nbsp;'+ data.name+ '</td> <input type="hidden" name="item" value="'+data.id+'">';
    	    	   htmls += '<td style = "text-align:center;line-height:180%" width="5%" height="30px" >'+data.score+'</td>';
    	      <%--  if(data.zbk_fj != '' && data.zbk_fj != "null"){
    	    	   htmls += '<td style = "text-align:center;line-height:180%" width="1%" height="30px" rowspan="'+getTotleRowCount(data['childrens'], 0)+'">';
    	    	   htmls += '<a href="<%=basePath%>backstage/zhibiaogl/lookFj?zbk_fj='+data.zbk_fj+'" width="700" height="400" lookupgroup="users" rel="rrr" title="附件"><i class="fa fa-paperclip fa-lg"></i></a></td>';
    	           
    	       }else{
    	    	   htmls += '<td style = "text-align:center;line-height:180%" width="5%" height="30px" rowspan="'+getTotleRowCount(data['childrens'], 0)+'"></td>';
    	       } --%>
    	       htmls += '<td style = "text-align:center;line-height:180%" width="10%" height="30px" rowspan="'+getTotleRowCount(data['childrens'], 0)+'">';
	           htmls += '<a id="add_fj" href="<%=basePath %>cpuser/licence_details?id='+data.id+'" class="btn btn-green btn-sm" target="dialog" width="550" height="350" mask="true" rel="rel_add_fj">上传</a>('+data.fjnum+')</td>';
	        
    	       htmls += '<td style = "text-align:center;line-height:180%" width="5%" height="30px" ><input size="5"  id = "zpf'+data.id+'" type="text" class="j-icheck" style="text-align:center;" value="'+data.zpf+'" ></td>';
    	       htmls += '<td  style="font-size:14px;line-height:150%;"   align="center">'+data.item_desc+'</td>'; 
    	       htmls += '<td  style="font-size:14px;line-height:150%;"  width="10%" align="center">'+data.dept+'</td></tr>'; 
    	       }else{
    	               htmls += '<td width="15%" style="line-height:180%" height="30px" colspan="'+zxc+'"  rowspan="'+getTotleRowCount(data['childrens'], 0)+'">';
    	        	   htmls += '&nbsp;&nbsp;'+ data.name + '</td> <input type="hidden" name="item" value="'+data.id+'">';
    	        	   htmls += '<td style = "text-align:center;line-height:180%" width="5%" height="30px" >'+data.score+'</td>';
    	          <%--  if(data.zbk_fj != '' && data.zbk_fj != "null"){
    	        	   htmls += '<td style = "text-align:center;line-height:180%" width="1%" height="30px" rowspan="'+getTotleRowCount(data['childrens'], 0)+'">';
    	        	   htmls += '<a href="<%=basePath%>backstage/zhibiaogl/lookFj?zbk_fj='+data.zbk_fj+'" width="700" height="400" lookupgroup="users" rel="rrr"><i class="fa fa-paperclip fa-lg"></i></a>';
    	           }else{
    	        	   htmls += '<td style = "text-align:center;line-height:180%" width="5%" height="30px" rowspan="'+getTotleRowCount(data['childrens'], 0)+'"></td>';
    	           } --%>
    	           htmls += '<td style = "text-align:center;line-height:180%" width="10%" height="30px" rowspan="'+getTotleRowCount(data['childrens'], 0)+'">';
    	           htmls += '<a id="add_fj" href="<%=basePath %>cpuser/licence_details?id='+data.id+'" class="btn btn-green btn-sm" target="dialog" width="550" height="350" mask="true" rel="rel_add_fj">上传</a>('+data.fjnum+')</td>';
    	        
    	           htmls += '<td style = "text-align:center;line-height:180%" width="5%" height="30px" ><input size="5"  id = "zpf'+data.id+'" type="text" class="j-icheck" style="text-align:center;" value="'+data.zpf+'" ></td>';
    	        htmls += '<td  style="font-size:14px;line-height:150%;"  width="15%" align="center">'+data.item_desc+'</td>'; 
    	        htmls += '<td  style="font-size:14px;line-height:150%;"  width="5%" align="center">'+data.dept+'</td></tr>'; 
    	       }

    	    }  
    	    return htmls;  
    	} 
    	  
    	/** 
    	 * 最大合并行数 
    	 */  
    	function getTotleRowCount(data, count) {  
    	    if(0 == data.length) {  
    	    return count+1;  
    	    }  
    	    $.each(data, function(i, n) {  
    	    if(n['childrens'].length > 0) {  
    	    count = getTotleRowCount(n['childrens'], count)-1;  
    	    }   
    	    count += 1;  
    	    });  
    	    return count;  
    	}  
    	/** 
    	 * 获取最大深度 
    	 */  
    	function getMaxLeve(_data) {  
    		var v = 0;  
    	    $.each(_data, function(i, n) {  
    	    if(parseInt(n.zbjb) > v)  
    	    v = parseInt(n.zbjb);  
    	    });  
    	    return v;  
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
		#splitBar {display:none;}
	</style>
</head>
<body>
<div id="qqqqqq">
	<div id="header">
		<div class="headerNav">
			<div class="logo" style="margin-top: 4px;"><img src="<%=basePath%>images/indexlogo.png" /></div>
			<ul class="topnav">
				<li>
					<a class=" j-popover" data-container="body" data-toggle="popover" data-placement="bottom"  data-el-content="#popover-01" data-html="true">
						<i id="inoutbell" class="fa fa-bell fa-1x faa-tada animated" style="color:#FFD700"></i>
					</a>
					<a id="inoutdialog" hidden="hidden" href="" target="dialog" title="111" rel="form" width="950" height="602"></a>
				</li>
				<li>当前用户：${name}</li>
				<li><a href="<%=basePath %>cpuser/changePsd" target="dialog" mask="true" rel="form" width="400" height="260">修改密码</a></li>
				<li><a href="<%=basePath%>cpuser/cpuser_tc">退出</a></li>
			</ul>
			<ul class="topTheme" id="topTheme">
				<!-- <li theme="default" class="theme_default" title="黑白分明"><i class="fa fa-circle"></i></li>
				<li theme="orange" class="theme_orange" title="橘子红了"><i class="fa fa-circle"></i></li>
				<li theme="purple" class="theme_purple" title="紫罗兰"><i class="fa fa-circle"></i></li>
				<li theme="green" class="theme_green" title="绿草如茵"><i class="fa fa-circle"></i></li> -->
				<li theme="blue" class="theme_blue" title="青出于蓝"><i class="fa fa-circle"></i></li>
			</ul>
		</div>
	</div>
	
	<div class="container-fluid">
	<!--  style="width:99%;height:100px; margin:0.5%" class="position" -->
		<div id="navTab" class="tabs">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul class="nav nav-tabs">
						<li><a href="javascript:;"><span>我的主页</span></a></li>
					</ul>
				</div>
				<div class="tabsRight"><i class="fa fa-angle-double-right"></i></div>
				<div class="tabsMore"><i class="fa fa-angle-double-down"></i></div>
			</div>
			<div class=" tabsContent" >

<div id="title3_submit">
	<div class="pageHeader">
    <form id="pagerForm" onsubmit="return divSerach(this);" action="<%=basePath %>cpuser/index"  method="POST">
    <input type="hidden" id="zbkids"  value="${ids}"/>
        <div class="searchBar">
            <ul class="searchContent">
                  <li class="pull-right"></li>
		    		<li style="font-size: 20px;margin-left: 328px;">
		    			${lhmbym}
		    		</li>
		    		<li style="margin-left: 150px;margin-right: 100px;">
		    			截止时间<font color="red">${sjdate }</font>&nbsp;&nbsp;&nbsp;剩余：<font color="red">${sjdate1}</font>天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    			<button type="button" class="btn btn-default btn-sm" id="pz_fjs" value="" onclick="zpfbc()" <c:if test="${user.scbj=='Y' }">disabled="disabled" </c:if>>保存</button>
		    			<button type="button" class="btn btn-default btn-sm" id="pz_fj" value="" onclick="scbj()" <c:if test="${user.scbj=='Y' }">disabled="disabled" </c:if>>提交</button>
		    		</li>
            </ul>
        </div>
    </form>
</div>
<div class="pageContent">
   <div id="heidiv" width="100%"   style="overflow:auto;margin-left: 10px;margin-right: 10px;">
  		<table id='rootTable9'  border='1' cellspacing='5' cellpadding='5' width="100%" height="90%" border-collapse="separate" border-spacing="10px;">
		
        <thead>
              <tr height="25px"><td align="center" style="font-size:16px;"><b>分类指标</b></td><td align="center" style="font-size:16px;" colspan="4"><b>量化内容</b></td><td align="center" style="font-size:16px;"><b>分值</b><td align="center" style="font-size:16px;"><b>附件</b><td align="center" style="font-size:16px;"><b>自评分</b></td><td align="center" style="font-size:16px;"><b>指标评分说明</b></td><td align="center" style="font-size:16px;"><b>考评部门</b></td></tr>
         </thead>
		</table>
    </div>
</div>
</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
</div>
</body>
<script src="<%=basePath%>DWZ/js/dwz.database.js"></script>
</html>