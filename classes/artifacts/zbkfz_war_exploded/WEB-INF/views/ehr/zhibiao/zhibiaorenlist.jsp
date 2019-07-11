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
		LODOP.ADD_PRINT_TABLE(40,40,40,1000,'<table width="650"  height="1150"  border="1" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;">'+$("#rootTable8").html()+'</table>');
	    LODOP.SAVE_TO_FILE("量化考核指标.xlsx");
    }else{
    	LODOP.ADD_PRINT_HTM(40,40,40,1000,'<table width="650"  height="1150"  border="1" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;">'+$("#rootTable8").html()+'</table>');
    	//LODOP.PRINT_DESIGN();
    	//console.log();
    	LODOP.PREVIEW();
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
	loadOrgTree();
});
function loadOrgTree() {
	var h = document.documentElement.clientHeight || document.body.clientHeight;
	var oDiv=document.getElementById('heidiv');
    oDiv.style.height=h*0.8+"px";
	var a = $("#pid8").val();
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/zhibiaogl/getrysxbgTree1?supunit2=${supunit2}&type=${type}",
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
    console.log(_);

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
            htmls += '<tr><td>'+n.name+'</td>';  
            htmls += '<td colspan="3" rowspan="1"></td>'; 
            for(var j = 1; j < maxLevel+2; j++) {  
                htmls += '<td></td>';  
            }  
            htmls += '<td>'+""+'</td></tr>';  
        }  
        $("#rootTable8").append(htmls);  
        console.log(htmls);
    });  
    //$("#rootTable8").find('tr:eq(1)').find('td:eq(0)').before('<td rowspan="'+getTotleRowCount(_['childrens'], 0)+'">'+$("#hiddenSpan1").text()+'</td>');  
}  
function getChildrenHtml(htmls, data, ml,ly) { 
		ly++;
    if(data['childrens'].length > 0) {  
    	htmls += '<td style="font-size:14px;line-height:150%;" width="15%"  rowspan="'+getTotleRowCount(data['childrens'], 0)+'">'+data.name+'('+data.score+'分)</td> <input type="hidden" name="item" value="'+data.id+'">';  
        $.each(data['childrens'], function(i, n) {  
        	if(i > 0) {  
                htmls += '<tr>';  
            }  
            htmls = getChildrenHtml(htmls, n, ml,ly);  
        });  
    } else {  
       if(ly<4){
    	 var zxc = 4-ly+1;
       htmls += '<td style="font-size:14px;line-height:150%;"  width="15%" colspan="'+zxc+'"  rowspan="'+getTotleRowCount(data['childrens'], 0)+'">'+data.name+'</td> <input type="hidden" name="item" value="'+data.id+'">';  
       htmls += '<td style = "text-align:center;font-size:14px;line-height:150%;"  width="5%"><input size="5" name = "finlscore" type="text" class="j-icheck" style="border: 0px;outline:none;text-align:center" value="'+data.finlscore+'"></td><td  style="font-size:14px;line-height:150%;"  width="30%" align="center">'+data.item_desc+'</td></tr>'; 
 /*<td>'+data.score+'</td>*/
       }else{
           htmls += '<td width="15%" style="font-size:14px;line-height:150%;"  rowspan="'+getTotleRowCount(data['childrens'], 0)+'">'+data.name+'</td> <input type="hidden" name="item" value="'+data.id+'">';  

        htmls += '<td style = "text-align:center;font-size:14px;line-height:150%;" width="5%"><input size="5"  name = "finlscore" type="text" class="j-icheck" style="border: 0px;outline:none;text-align:center"  value="'+data.finlscore+'"></td><td  style="font-size:14px;"  width="30%" align="center">'+data.item_desc+'</td></tr>'; 
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
</script>
<iframe width=0 height=0 frameborder=0 name="hrong8"
        style="display: none"></iframe>
<form action="<%=basePath %>backstage/zhibiaogl/save1" id="add_deptinfo"class="pageForm form-validate" method="post" callback="dialogAjaxDone"noEnter>
	<input type="hidden" id ="supunit8" name="supunit2" value="${supunit2}">
	<input type="hidden" id ="name" name="name" value="${name}">
	<input type="hidden" id ="pid8" name="pid" value="${pid}">
	<input type="hidden" id ="finlscore" name="finlscore" value="${finlscore}">
		<div id="heidiv" width="100%"   style="overflow:auto;margin-left: 10px;margin-right: 10px;">
		        <ul class="searchContent">
                <li class="pull-right">
                    	<td align="right"><button type="submit" <c:if test="${tjsx eq '1'}">style="display:none"</c:if> class="btn btn-default btn-sm">保存</button></td>
                </li>
                       <li class="pull-right">
	   	<a href="javascript:void(0)" class="btn btn-default btn-sm" onClick="gongzuoyc_dayin(2)" ><span style="color: green;">导出</span></a>
	    <a href="javascript:void(0)" class="btn btn-default btn-sm" onClick="gongzuoyc_dayin(1)"><span style="color: green;">打印</span></a>
                </li>
            </ul>
		<!--startprint-->  
<table id='rootTable8'  border='1' cellspacing='5' cellpadding='5' width="100%" height="90%" border-collapse="separate" border-spacing="10px;">
		<thead>
			<tr height="25px"><td align="center" style="font-size:20px;"  colspan="6">${mode.name}</td></tr>
			<!-- <tr height="25px"><td align="center" style="font-size:18px;"  colspan="6">量化考核指标</td></tr> -->
			<tr height="25px"><td align="left" style="font-size:14px;"  colspan="6">参评人员：${name} &nbsp;&nbsp;&nbsp;&nbsp;提交时间：<fmt:formatDate value="${tjsj}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;&nbsp;&nbsp;总分：${zjfs}</td><%-- <td align="right" style="font-size:14px;" colspan="2">提交时间：<fmt:formatDate value="${tjsj}" pattern="yyyy-MM-dd"/></td><td align="right" style="font-size:14px;" colspan="2">总分：${zjfs}</td> --%></tr>
              <tr height="25px"><td align="center" style="font-size:16px;"><b>分类指标</b></td><td align="center" style="font-size:16px;" colspan="3"><b>量化内容</b></td><td align="center" style="font-size:16px;"><b>分值</b></td><td align="center" style="font-size:16px;"><b>指标评分说明</b></td></tr>
         </thead>
</table>
<!--endprint-->  
</div>
</form>

