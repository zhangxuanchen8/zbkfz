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
function doPrint(n) {//打印
	$("#rootTable10 tbody").html("");
	loadOrgTree1();
	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
	LODOP.PRINT_INIT(0,0,"210mm","297mm","量化指标");
	LODOP.SET_PRINT_PAGESIZE(1,0,0,"2*4");
	LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT","Width:100%;Height:100%");
	LODOP.ADD_PRINT_HTM("98%",370,370,390,"<font style='font-size:12px'><span tdata='pageNO'>第##页</span>/<span tdata='pageCount'>共##页</span></font>");
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	if(n==2){
		LODOP.ADD_PRINT_TABLE(40,40,40,1000,'<table width="650"  height="1150"  border="1" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;">'+$("#rootTable10").html()+'</table>');
		LODOP.SAVE_TO_FILE("量化考核指标.xlsx");
    }else{
	LODOP.ADD_PRINT_HTM(40,40,40,1000,'<table width="650"  height="1150"  border="1" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;">'+$("#rootTable10").html()+'</table>');
    	//LODOP.PRINT_DESIGN();
    	LODOP.PREVIEW();
    }
}

var keyNames = {  
	    "name": "name",     //描述  
	    "finlscore":"finlscore",
	    "score":"score"
	}  
$(function(){	
	  var s= $("[name='load']");
	  var ss = s[0];
	  var sss= ss.id;
	  $("#load").click();
	  //loadOrgTree2(sss);
	  $("#li"+sss).attr("class","hover");
	  loadOrgTree2(sss);
});
function loadOrgTree1() {
	$("#rootTable10 thead").html("");
	var htmls = '';  
	htmls+='<tr height="25px"><td align="center" style="font-size:20px;"  colspan="12">'+$("#modename").val()+'</td></tr>';
	htmls+='<tr height="25px"><td align="center" style="font-size:19px;"  colspan="12">'+ $("#lbxm2").val()+'</td></tr>';
	/*htmls+='<tr height="25px"><td align="center" style="font-size:18px;"  colspan="7">量化考核指标</td></tr>';*/
	htmls+='<tr height="25px"><td align="center" style="font-size:13px;"><b>分类指标</b></td><td align="center" style="font-size:13px;" colspan="8"><b>量化内容</b></td><td align="center" style="font-size:13px;"><b>分值</b></td><td align="center" style="font-size:13px;"><b>具体说明</b></td></tr>';
	$("#bttm").append(htmls);  
	
	var a = $("#pidid").val();
	var b = $("#supunit1").val();
	var h = document.documentElement.clientHeight || document.body.clientHeight;
	var oDiv=document.getElementById('eigdiv1');
    oDiv.style.height=h*0.93+"px";
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/zhibiaogl/getylTreeyll?supunit1="+$("#supunit1").val()+"&year1="+$("#pidid").val(),
	type : "POST",
	dataType:"json",
	success : function(data) {
		console.log(data);
		 	initTable(a,data,keyNames);
		 	//console.log($('#rootTable10').html());
		}
	});
}

function loadOrgTree2(s) {
	$("#year1").val(s);
	 $("#rootTable10 tbody").html("");
	var a = $("#year1").val();
	var b = $("#supunit1").val();
	var h = document.documentElement.clientHeight || document.body.clientHeight;
	var oDiv=document.getElementById('eigdiv1');
    oDiv.style.height=h*0.86+"px";
	$.ajax({
	async:false,
	cache:false,
	url:"<%=basePath%>backstage/zhibiaogl/getylTreeyll?supunit1="+$("#supunit1").val()+"&year1="+$("#year1").val(),
	type : "POST",
	dataType:"json",
	success : function(data) {
		console.log(data);
		 	initTable(a,data,keyNames);
		 	console.log($('#rootTable10').html());
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
	debugger;
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
        	 htmls += '<tr><td style="font-size:14px;line-height:140%" height="40px" >'+n.name+'</td>';
         	htmls += '<td  style="font-size:14px;line-height:140%" height="40px"  width="30%" colspan="'+8+'"  rowspan="'+getTotleRowCount(n['childrens'], 0)+'">&nbsp;&nbsp;&nbsp;'+n.name+'</td> <input type="hidden" name="item" value="'+n.id+'">';
             for(var j = 1; j < maxLevel+1; j++) {  
                 htmls += '<td></td>';  
             }   
             htmls += '<td  style="font-size:14px;line-height:180%" width="5%" align="center">'+n.score+'</td><td  style="font-size:14px;line-height:180%"  width="35%" align="left">&nbsp;&nbsp;&nbsp;'+n.item_desc+'</td></tr>';
        }  
        $("#rootTable10").append(htmls);  
        console.log(htmls);
    });  
    //$("#rootTable").find('tr:eq(1)').find('td:eq(0)').before('<td rowspan="'+getTotleRowCount(_['childrens'], 0)+'">'+$("#hiddenSpan1").text()+'</td>');  
}  
function getChildrenHtml(htmls, data, ml,ly) { 
	debugger;
	ly++;
    if(data['childrens'].length > 0) {  
        if(data.finlscore!="0"){
        	htmls += '<td style="font-size:14px;line-height:180%" height="40px" width="15%"  rowspan="'+getTotleRowCount(data['childrens'], 0)+'">&nbsp;&nbsp;&nbsp;'+data.name+'<br></br>(<span style="font-size:12px">最高限分'+data.finlscore+'分</span>)</td> <input type="hidden" name="item" value="'+data.id+'">';
        }else{
        	htmls += '<td style="font-size:14px;line-height:180%" height="40px" width="15%"  rowspan="'+getTotleRowCount(data['childrens'], 0)+'">&nbsp;&nbsp;&nbsp;'+data.name+'</td> <input type="hidden" name="item" value="'+data.id+'">';
        }
    	
    	$.each(data['childrens'], function(i, n) {  
        	if(i > 0) {  
                htmls += '<tr>';  
            }  
            htmls = getChildrenHtml(htmls, n, ml,ly);  
        });  
    } else {  
       if(ly<9){
    	 var zxc = 9-ly+1;
       htmls += '<td  style="font-size:14px;line-height:180%" height="40px"  width="30%" colspan="'+zxc+'"  rowspan="'+getTotleRowCount(data['childrens'], 0)+'">&nbsp;&nbsp;&nbsp;'+data.name+'</td> <input type="hidden" name="item" value="'+data.id+'">';
       htmls += '<td  style="font-size:14px;line-height:180%" height="40px" width="5%" align="center">'+data.score+'</td><td  style="font-size:14px;line-height:180%"  width="35%" align="center">'+data.item_desc+'</td></tr>';
 
       }else{
           htmls += '<td  style="font-size:14px;line-height:180%" height="40px" width="30%"  rowspan="'+getTotleRowCount(data['childrens'], 0)+'">&nbsp;&nbsp;&nbsp;'+data.name+'</td> <input type="hidden" name="item" value="'+data.id+'">';

        htmls += '<td  style="font-size:14px;line-height:180%" height="40px"  width="5%" align="center">'+data.score+'</td><td  style="font-size:14px;line-height:180%"  width="35%" align="center">'+data.item_desc+'</td></tr>';
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
 * 最大合并行数 
 */  
function getTotlecolCount(data, count) {  
    if(0 == data.length) {  
    return count+1;  
    }  
    $.each(data, function(i, n) {  
    if(n['childrens'].length > 0) {  
    count = getTotlecolCount(n['childrens'], count)-1;  
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
$(function(){

    $(".l-left li").click(function() {

        $(this).siblings('li').removeClass('hover');  // 删除其他兄弟元素的样式

        $(this).addClass('hover');                            // 添加当前元素的样式

    });

});
</script>
<!-- <script type="text/javascript" src="http://www.sz886.com/js/jquery-1.9.1.min.js"></script> -->
<style>
.l-left{width:210px; height:700px; float:left; background:#f2f2f2;}
.l-right{padding:30px; overflow:hidden;}

.l-left li{height:38px; line-height:38px; text-align:left; list-style:none;width:210px;}
.l-left li a{display:block; line-height:38px; color:#000; text-decoration:none; padding-left:5px;}
.l-left li a:hover,.hover{background:#fff; border-bottom:1px solid #c9c9c9;}
.hover{border-top:1px solid #c9c9c9;}
.l-right div{display:none;text-align:center;}
.l-right div:target{display:block;} 
</style>
<iframe width=0 height=0 frameborder=0 name="hrong10"
        style="display: none"></iframe>
<form action="" id="add_deptinfo10"class="pageForm form-validate" method="post" callback="dialogAjaxDone"noEnter >
	<input type="hidden" id ="supunit1" name="supunit1" value="${supunit1}">
	<input type="hidden" id ="year1" name="year1" value="${year1}">
	<input type="hidden" id ="pidid"value="${pidid}">
	<input type="hidden" id ="lbxm2"value="${lbxm2}">
	<input type="hidden" id ="modename"value="${mode.name}">
		<table  cellspacing='5' cellpadding='5' width="100%" height="70%" style="background-color:white;box-shadow: inset 0px -1px black;">
		<tr height="25px"><td align="center" style="font-size:20px;" >${mode.name}</td></tr>
			<!-- <tr height="25px"><td align="center" style="font-size:19px;" ></td></tr> -->
		        <ul class="searchContent">
                <li class="pull-right">
                    <td align="right"><input type="button" class="btn btn-default btn-sm"  postType="string" rel="" target="" style="margin-right:15px;margin-bottom:5px" value="打印" onclick="doPrint(1);"></td>
                </li>
            </ul>
		</table>
		<div id="eigdiv1" style=" overflow:auto;background-color:white;" width="100%">
         <div class="l-left" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 14em;" >
          <c:forEach items="${list}" var="l" varStatus="stat">
            <li id="li${l.i_id }"><p style="line-height: center"><a href="javascript:void(0)" id="${l.i_id }" name="load" onclick="loadOrgTree2(this.id)" title="${l.item }">${l.item }</a></p></li>
            </c:forEach>
        </div>
        <div class="l-right">
		<table id='rootTable10'  border='1' cellspacing='5' cellpadding='5' width="100%" height="70%" >
		<thead id="bttm">
              <tr height="25px"><td align="center" style="font-size:13px;"><b>分类指标</b></td><td align="center" style="font-size:13px;" colspan="8"><b>量化内容</b></td><td align="center" style="font-size:13px;"><b>分值</b></td><td align="center" style="font-size:13px;"><b>具体说明</b></td></tr>
         </thead>
</table>
</div>
<!--endprint-->  
</div>
</form>

