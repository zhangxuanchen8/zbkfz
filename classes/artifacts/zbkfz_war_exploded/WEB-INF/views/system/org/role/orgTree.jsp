<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

    <link rel="stylesheet" href='<%=basePath%>act-process-editor/editor-app/css/jOrgChart/jquery.jOrgChart.css'/>
    <%-- <script type='text/javascript' src='<%=basePath%>act-process-editor/editor-app/libs/jquery_1.11.0/jquery.min.js'></script> --%>
    <script type='text/javascript' src='<%=basePath%>act-process-editor/editor-app/libs/jOrgChart/jquery.jOrgChart.js'></script>
    <style>
        .jOrgChart .node a {
            text-decoration: none;
            color: #0f0f0f;
            font-size: 18px;
        }
        .jOrgChart .node_shuzhe a {
            text-decoration: none;
            color: #fff;
            font-size: 13px;
        }
        .jOrgChart .node {
            width: 100px;
            height: 70px;
            line-height: 20px;
            border-radius: 4px;
            margin: 0 auto;
            word-wrap:nomal;
        }
        .jOrgChart .node_shuzhe {
            width: 15px;
            height: 200px;
            line-height: 25px;
            border-radius: 4px;
            margin: 0 auto;
        }
    </style>
    
<script type="text/javascript">
$(function(){
    //数据返回
    $.ajax({
        url: "<%=basePath%>backstage/org/role/getDeptOrg",
        type: 'POST',
        dataType : "json",
        success: function(data){
        	//alert(json);
            //console.log("getRiskJson data length:"+data.length);
            var riskTree = {};
            var riskdata = {}; //risks
            for(var i in data){
            	//console.log(data[i]);
                var row = data[i];
                riskdata[row.id] = row;
                //console.log(row);
                //console.log(riskdata[row.id]);
            }
            for(var i in riskdata){
                //console.log(riskdata[i]);
                makeRiskTree(riskTree,riskdata,riskdata[i]);
            }
            //callback(riskTree);
            //console.log(riskTree);
            var showlist = $("<ul id='org' style='display:none'></ul>");
            showall(riskTree, showlist);
            //console.log(showlist);
            $("#jOrgChart").append(showlist);
            $("#org").jOrgChart( {
                chartElement : '#jOrgChart',//指定在某个dom生成jorgchart
                depth:3,
                dragAndDrop : false //设置是否可拖动
            });  
        }
    });
});

function makeRiskTree(risktree,risks,risk) { //树 正在堆的父级 子集
	//console.log(risktree);
	//console.log(risks);
	//console.log(risk.isleaf);
    if(!risk.sub){//如果本条没有子集 添加本条
        risk.sub =  {};
    }
    if(risk.isleaf){ //如果有父级 就取出自己做成父级的数组形式
        var super_id = risks[risk.pId];
        //console.log(risks[risk.pId]);
        //console.log('now super_id，risk.super_id are:'+super_id+"———n(*≧▽≦*)n———"+risk.pId);
        makeRiskTree(risktree,risks,super_id);
        super_id.sub[risk.id] = risk;
    }else{ //若没有父级 则自己就是根节点
        risktree[risk.id]= risk;
    }
};

function showall(json, parent) {
	//console.log(json);
	//console.log(parent);
    $.each(json, function(index, val) {
    	//console.log(val.sub);
    	//console.log(val.sub.length);
    	//console.log(index);
        if(val.sub != '' && val.sub != null ){
            var li = "";
            if(val.isdept == 'Y'){
            	li = $("<li class='dept'></li>");
            li.append("<a href='javascript:void(0)' onclick=getOrgId("+val.id+");>"+val.name+"</a>").append("<ul></ul>").appendTo(parent);
            	
            }else{
            	li = $("<li class=''></li>");
            li.append("<a href='javascript:void(0)' onclick=getOrgId("+val.id+");>"+val.name+"</a>").append("<ul></ul>").appendTo(parent);
            }
            //console.log(val.name);
            //递归显示
        	//debugger;
            showall(val.sub, $(li).children().eq(1));
        }else{
        	//debugger;
            $("<li></li>").append("<a href='javascript:void(0)' onclick=getOrgId("+val.id+");>"+val.name+"</a>").appendTo(parent);
        }
    });

}
</script>
<!--显示组织架构图-->
<div style="overflow: auto; width: 100%;height: 500px; margin-top: 5px;"> 
  <table id='jOrgChart' height="100%">
  </table>
</div>

