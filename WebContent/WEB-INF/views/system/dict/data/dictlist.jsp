<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<style type="text/css">  
    div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}  
    div#rMenu a{  
        cursor: pointer;  
        list-style: none outside none;  
    }  
</style>
<script type="text/javascript">
var zTree;
var i = 0;
$(function(){	
	loadOrgTree();
	var datadictTreess = $("#navTab_content").css("height");
	
	$("#datadictTree").css("height",datadictTreess-10);
});
$(window).resize(function(){
	var datadictTreess = $("#navTab_content").css("height");
	
	$("#datadictTree").css("height",datadictTreess);
});



function loadOrgTree(){
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		dataType : "json",
		url:"backstage/org/role/getDatadictTree",
		success : function(data) {
			treeNodes = data;
			zTree = $.fn.zTree.init($("#datadictTree"),{view:{selectedMulti:false,fontCss:{color:"#393939"}},data:{simpleData: {enable: true}},callback:{onClick:zTreeOnClickDicts,onRightClick:zTreeOnRightClick}},treeNodes);
			var node = zTree.getNodeByParam("id", $("#dictid").val());
			zTree.selectNode(node);
		}
	});
}

function zTreeOnClickDicts(event, treeId, treeNode) {
	$("#contents").val("");
	if (treeNode.isParent) {
		return false;
	}
	nekey = treeNode.nekey;
	$("#nekey").val(nekey);
//-----------------------------------------------------------------	
	$("#dictsReload").attr("href","<%=basePath%>backstage/baseDict/getdDictDetails?nodeid="+nekey+"&relFlag=Y");
	$("#dictsReload").click();
	
}

//在ztree上的右击事件  
function zTreeOnRightClick(event, treeId, treeNode) {  
    if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {  
        showRMenu("root", event.clientX, event.clientY);  
    } else if (treeNode && !treeNode.noR) {  
        showRMenu("node", event.clientX, event.clientY);  
    }  
}  


function cleanquery(){
	$("#contents").val("");
	navTabPageBreak({pageNum:1,numPerPage:20},'dictListRef');
}

function saveAll(){
	if(submitCheck()){ 
		var dlist = new Array();//
		$("#tabledict").find("tr").each(function(){
		    if($(this).attr("data-hos")!="0"){//是自己医院的字典才进行更新
                var dict = new Object() ; //生成一个dict对象
                $(this).find("td").each(function(){
                    if(typeof($(this).find('input').attr('name'))!='undefined'&&typeof($(this).find('input').val())!='undefined'&&$(this).find('input').val()!=null&&$(this).find('input').attr('name')!='index'){
                        dict[$(this).find('input').attr('name')]=$(this).find('input').val();//向dict的array属性添加一个元素 并赋值
                    }
                });
                if(!$.isEmptyObject(dict)){
                    dlist.push(dict);
                }
			}

		});	
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			data:{dlist:JSON.stringify(dlist),hosnum:$("#hosnum").val(),sysname:$("#sysname").val(),nekey:$("#nekey").val()},
			dataType : "json",
			url:"backstage/baseDict/saveOrUpdate",
			success:function(data){
				if(data.statusCode=='200'){
					alertMsg.correct(data.message);
				}else{
					alertMsg.error(data.message);
				}
				$("#dictSearch").click();
			}
		});
	 } 
}

//判断对象是否为{}
function isEmptyObject(e) {  
    var t;  
    for (t in e)  
        return !1;  
    return !0  
} 

function checknevalue(id){
	var tdnevalue=$("#"+id);
	console.log(tdnevalue);
	var tddictid=tdnevalue.parent().parent().find("input[name='dictid']")
	tdnevalue.blur(function(){
		$.ajax({
			async : false,
			type:'post',
			data:{nevalue:tdnevalue.val(),nekey:$("#nekey").val(),dictid:tddictid.val()},
			dataType : "json",
			url:"backstage/baseDict/checkNevalue",
			success:function(data){
				if(data!=null){
					alertMsg.error(data.message);
					$(tdnevalue).css('color','#ff0000');
					$("#"+id).parent().css('border-color','#ff0000');
				}else{
					$("#"+id).parent().css('border-color','#cccccc');
					$(tdnevalue).css('color','black');
				}
			} 
		});
	})
}

 function submitCheck(){
	var flag = true;
	var id = '';
/* 	if(arrRepeat(getdictids())){ */
		$("input[name*='nevalue'],#dictList input[name*='contents']").each(function(a,b){
			var hex = RGBToHex($(b).css('color'));
			var hexb = RGBToHex($(b).parent().css('border-color'));
			if(hex=="#FF0000"||hexb=="#FF0000"){
				id ='#'+$(b).attr("id");
				$(id).blur();
		        alertMsg.info("请修改红色字体已存在序号！");
				flag=false;
				return false;
		     }
			if($(b).val()==""){
				id ='#'+$(b).attr("id");
				$(id).blur();
		        alertMsg.info("请将空值补充完整");
				flag=false;
				return false;
		     }
	    });
/* 	}else{
		flag=false; 
	} */
	//$("#nevalue_1").foucs();
	return flag;
} 

function RGBToHex(rgb){ 
   var regexp = /[0-9]{0,3}/g;  
   var re = rgb.match(regexp);//利用正则表达式去掉多余的部分，将rgb中的数字提取
   var hexColor = "#"; var hex = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'];  
   for (var i = 0; i < re.length; i++) {
        var r = null, c = re[i], l = c; 
        var hexAr = [];
        while (c > 16){  
              r = c % 16;  
              c = (c / 16) >> 0; 
              hexAr.push(hex[r]);  
         } hexAr.push(hex[c]);
         if(l < 16&&l != ""){        
             hexAr.push(0)
         }
       hexColor += hexAr.reverse().join(''); 
    }  
   //alert(hexColor)  
   return hexColor;  
}

function getdictids(){
	var getdictids = [];
	$("input[name*='nevalue']").each(function(){
		getdictids.push($(this).val());
	});
	return getdictids;
	
}

//验证重复
/* function arrRepeat(arr){
	var flag=true;
	var nary=arr.sort(); 

	for(var i=0;i<arr.length;i++){ 

	if (nary[i]==nary[i+1]){ 

			alertMsg.info("有重复项目序号：" + nary[i]); 
		
			flag=false;
		} 
	}
	return flag;
} */


</script>

	<div class="col-md-2 panel panel-default" style="padding:0px;height:100%;margin: 0px;" >
	          	 <a href="<%=basePath%>backstage/baseDict/getdDictDetails" id="dictsReload" style="display: none;" target="ajax"   rel="dictListRef">载入</a>
	                  <div style="float: left; width: 100%; overflow: auto;" layoutH="0" >
	                     <ul id="datadictTree" class="ztree" attrs = '{"expandAll":true}' 
	                          nodes=''style="width: 400px;">
	                      </ul>
	                  </div>
	             
	         
	      </div>
<div class="col-md-10" style="padding:0px; " id="dictListRef">
	<jsp:include page="dictTable.jsp"></jsp:include>
</div>
<div id="rMenu">  
    <a href="#" class="list-group-item" onclick="addMenu();">新增</a>  
    <a href="#" class="list-group-item" onclick="updateMenu();">修改</a>  
    <a href="#" class="list-group-item" onclick="deleteMenu();">删除</a>  
</div>
