<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="<%=basePath%>static/js/loadSelect.js"></script>
<script src="<%=basePath%>static/js/LodopFuncs.js"></script>
<script  src="<%=basePath%>static/jqprint/jquery.jqprint-0.3.js"></script>
<script language="javascript">
var LODOP; //声明为全局变量     
function gongzuoyc_dayin(n) {//打印
	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
	LODOP.PRINT_INIT(0,0,"210mm","297mm","量化指标");
	LODOP.SET_PRINT_PAGESIZE(1,0,0,"2*4");
	LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT","Width:100%;Height:100%");
	LODOP.ADD_PRINT_HTM("98%",370,370,390,"<font style='font-size:12px'><span tdata='pageNO'>第##页</span>/<span tdata='pageCount'>共##页</span></font>");
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	if(n==2){
		LODOP.ADD_PRINT_TABLE(40,40,40,1000,'<table width="650"  height="1150"  border="1" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;">'+$("#rootTable9").html()+'</table>');
	    LODOP.SAVE_TO_FILE("量化考核指标.xlsx");
    }else{
    	
    	$(":input").css("border","0px");
    	$(":input").css("outline","none");
    	$(":input").css("text-align","center");
    	//$("#zjfs").css("text-align","left");
    	LODOP.ADD_PRINT_HTM(40,40,40,1000,'<table width="650"  height="1150"  border="1" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;">'+$("#rootTable9").html()+'</table>');
    	//LODOP.PRINT_DESIGN();
    	//console.log();
    	
    	LODOP.PREVIEW();
    }
}

function zgxf(s,y){
	if(y!=null&&y!=0){
	if(s>y){
		alert("超过最大限分。");
		return false;
	}
	}
	
}

function pddl(s){
	if(s=="0"){
		alert("总分不能为0！");
		return false;
	}
}

function rytj(){
	$("#tijiaory").click();
}
var keyNames = {  
	    "name": "name",     //描述  
	    "finlscore":"finlscore",
	    "score":"score"
	}  
$(function(){
	//debugger;
	//var s = $("#zjfs").val();
	//if(s=="0"){
	//	$("#zjfs").val("");
	//} 
	loadOrgTree();
});
function loadOrgTree() {
	var h = document.documentElement.clientHeight || document.body.clientHeight;
	var oDiv=document.getElementById('heidiv');
    oDiv.style.height=h*0.8+"px";
	var a = $("#pid").val();
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/zhibiaogl/getrysxbgTree?supunit2=${supunit2}&type=${type}",
	type : "POST",
	dataType:"json",
	success : function(data) {
		console.log(data);
		 	initTable(a,data,keyNames);
		 	//console.log($('#rootTable9').html());
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
    //console.log(_);

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
        //console.log(htmls);
    });  
    //$("#rootTable").find('tr:eq(1)').find('td:eq(0)').before('<td rowspan="'+getTotleRowCount(_['childrens'], 0)+'">'+$("#hiddenSpan1").text()+'</td>');  
}  
function getChildrenHtml(htmls, data, ml,ly) { 

		ly++;
    if(data['childrens'].length > 0) {
    	if(data.heji != '' && data.heji != "null"){
    		htmls += '<td style="line-height:180%" width="9%" height="30px" rowspan="'+getTotleRowCount(data['childrens'], 0)+'">'+data.name+'(<strong >'+data.heji+'分</strong>)</td> <input type="hidden" name="item" value="'+data.id+'">'; 
    	}else{
    		htmls += '<td style="line-height:180%" width="9%" height="30px" rowspan="'+getTotleRowCount(data['childrens'], 0)+'">'+data.name+'</td> <input type="hidden" name="item" value="'+data.id+'">';  
    	}
    	$.each(data['childrens'], function(i, n) {  
        	if(i > 0) {  
                htmls += '<tr>';  
            }  
            htmls = getChildrenHtml(htmls, n, ml,ly);  
        });  
    } else {  
       if(ly<6){
    	 var zxc = 6-ly+1;
           htmls += '<td width="12%" style="line-height:180%" height="30px" colspan="'+zxc+'"  rowspan="'+getTotleRowCount(data['childrens'], 0)+'">';
    	   htmls += '&nbsp;&nbsp;'+ data.name+'</td> <input type="hidden" name="item" value="'+data.id+'">';
    	   htmls += '<td style = "text-align:center;line-height:180%" width="5%" height="30px" >'+data.score+'</td>';
       if(data.zbk_fj != '' && data.zbk_fj != "null"){
    	   htmls += '<td style = "text-align:center;line-height:180%" width="1%" height="30px" rowspan="'+getTotleRowCount(data['childrens'], 0)+'">';
    	   htmls += '<a href="<%=basePath%>backstage/zhibiaogl/lookFj?zbk_fj='+data.zbk_fj+'" width="700" height="400" lookupgroup="users" rel="rrr" title="附件"><i class="fa fa-paperclip fa-lg"></i></a></td>';
           
       }else{
    	   htmls += '<td style = "text-align:center;line-height:180%" width="5%" height="30px" rowspan="'+getTotleRowCount(data['childrens'], 0)+'"></td>';
       }
       htmls += '<td style = "text-align:center;line-height:180%" width="5%" height="30px" >'+data.zpf+'</td>';
       //htmls += '<td style = "text-align:center;line-height:180%;"  width="5%"><input size="5" name = "finlscore'+data.id+'" type="text" class="j-icheck" style="border:none;outline:medium;text-align:center;" value="'+data.finlscore+'" onchange="zgxf(this.value,'+data.maxscore+')"></td><td  style="font-size:14px;line-height:150%;"  width="50%" align="center">'+data.item_desc+'</td></tr>'; 
       htmls += '<td style = "text-align:center;line-height:180%;" width="5%" height="30px"><input size="5"  name = "finlscore'+data.id+'" type="text" class="j-icheck" style="border:none;outline:medium;text-align:center;" value="'+data.finlscore+'" onchange="zgxf(this.value,'+data.maxscore+')"></td>';
       htmls += '<td  style="font-size:14px;line-height:150%;"  width="8%" align="center">'+data.item_desc+'</td>'; 
       htmls += '<td  style="font-size:14px;line-height:150%;"  width="5%" align="center">'+data.dept+'</td></tr>'; 
       }else{
               htmls += '<td width="15%" style="line-height:180%" height="30px" colspan="'+zxc+'"  rowspan="'+getTotleRowCount(data['childrens'], 0)+'">';
        	   htmls += '&nbsp;&nbsp;'+ data.name + '</td> <input type="hidden" name="item" value="'+data.id+'">';
        	   htmls += '<td style = "text-align:center;line-height:180%" width="5%" height="30px" >'+data.score+'</td>';
           if(data.zbk_fj != '' && data.zbk_fj != "null"){
        	   htmls += '<td style = "text-align:center;line-height:180%" width="1%" height="30px" rowspan="'+getTotleRowCount(data['childrens'], 0)+'">';
        	   htmls += '<a href="<%=basePath%>backstage/zhibiaogl/lookFj?zbk_fj='+data.zbk_fj+'" width="700" height="400" lookupgroup="users" rel="rrr"><i class="fa fa-paperclip fa-lg"></i></a>';
           }else{
        	   htmls += '<td style = "text-align:center;line-height:180%" width="5%" height="30px" rowspan="'+getTotleRowCount(data['childrens'], 0)+'"></td>';
           }
           htmls += '<td style = "text-align:center;line-height:180%" width="5%" height="30px" >'+data.zpf+'</td>';
        htmls += '<td style = "text-align:center;line-height:180%;" width="5%" height="30px"><input size="5"  name = "finlscore'+data.id+'" type="text" class="j-icheck" style="border:none;outline:medium;text-align:center;" value="'+data.finlscore+'" onchange="zgxf(this.value,'+data.maxscore+')"></td>';
        htmls += '<td  style="font-size:14px;line-height:150%;"  width="8%" align="center">'+data.item_desc+'</td>'; 
        htmls += '<td  style="font-size:14px;line-height:150%;"  width="5%" align="center">'+data.dept+'</td></tr>'; 
       }/* <td>'+data.score+'</td> */

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

function xiugaizf(str){
	var zf = $("#zjfs").val();
	var pid = $("#pid").val();
	$.ajax({
		async:false,
		cache:false,
		url:"<%=basePath%>/backstage/zhibiao/xiugaizf?id="+str+"&zjfs="+zf+"&pid="+pid,
		type : "POST",
		dataType:"json",
		success : function(data) {
			if(data.statusCode=='200'){
				alertMsg.correct("修改成功");
	 			}else{
	 				alertMsg.error("操作失败");
	 			}
	 		}
		});
}
</script>
<iframe width=0 height=0 frameborder=0 name="hrong"
        style="display: none"></iframe>
<form action="<%=basePath %>backstage/zhibiaogl/save2?type=${type}" id="add_deptinfo"class="pageForm form-validate" method="post" callback="dialogAjaxDone"noEnter>
	<input type="hidden" id ="supunit2" name="supunit2" value="${supunit2}">
	<input type="hidden" id ="name" name="name" value="${name}">
	<input type="hidden" id ="pid" name="pid" value="${pid}">
	<input type="hidden" id ="finlscore" name="finlscore" value="${finlscore}">
	<input type="hidden" id="mail_flag" name="mail_flag" value="">
		<div id="heidiv" width="100%"   style="overflow:auto;margin-left: 10px;margin-right: 10px;">
		        <ul class="searchContent">
		       <c:if test="${type!='all'}">
                <li class="pull-right">
                    	<button type="submit" <c:if test="${tjsx eq '1' || sjzbk.synergy!='Y'}">style="display:none"</c:if> class="btn btn-default btn-sm">保存</button>
                </li>
                </c:if> 
                <c:if test="${type=='all'}">
                <li class="pull-right">
                    	<button type="submit" class="btn btn-default btn-sm">保存</button>
                    	<button type="submit" onclick="$('#mail_flag').val('S')" class="btn btn-default btn-sm">合计</button>
                    	<button type="button" class="btn btn-default btn-sm" onclick="xiugaizf('${supunit2}')">修改</button>
                </li>
                </c:if>
                       <li class="pull-right">
	   	<a href="javascript:void(0)" class="btn btn-default btn-sm" onClick="gongzuoyc_dayin(2)" ><span style="color: green;">导出</span></a>
	    <a href="javascript:void(0)" class="btn btn-default btn-sm" onClick="gongzuoyc_dayin(1)"><span style="color: green;">打印</span></a>
                </li>
            </ul>
            <br>
		<!--startprint-->  
<table id='rootTable9'  border='1' cellspacing='5' cellpadding='5' width="100%" height="90%" border-collapse="separate" border-spacing="10px;">
		<thead>
			<tr height="25px"><td align="center" style="font-size:20px;"  colspan="12">${mode.name}<span>${list_s.stop_time}</span></td></tr>
			<%-- <tr height="25px"><td align="center" style="font-size:19px;"  colspan="7">${leixing}-临床型评聘标准</td></tr>
			<tr height="25px"><td align="center" style="font-size:18px;"  colspan="10">量化考核指标</td></tr> --%>
			<tr height="25px"><td align="left" style="font-size:14px;"  colspan="12">参评人员：${name} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${type=='all'}">总分：<input size="5" id="zjfs"  name ="zjfs" type="text" class="j-icheck" style="outline:medium;text-align:center;" value="<fmt:formatNumber type="number"   maxFractionDigits="2" value="${zjfs}" />" ></c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业：${contents} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;推荐职务：${leixing}</tr>
              <tr height="25px"><td align="center" style="font-size:16px;"><b>分类指标</b></td><td align="center" style="font-size:16px;" colspan="5"><b>量化内容</b></td><td align="center" style="font-size:16px;"><b>分值</b><td align="center" style="font-size:16px;"><b>附件</b><td align="center" style="font-size:16px;"><b>自评分</b><td align="center" style="font-size:16px;"><b>得分</b></td><td align="center" style="font-size:16px;"><b>指标评分说明</b></td><td align="center" style="font-size:16px;"><b>考评部门</b></td></tr>
         </thead>
         
</table>
</div>
</form>

