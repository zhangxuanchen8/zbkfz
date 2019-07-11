<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<script src="static/js/jquery/jquery.form.js" ></script>
<script type="text/javascript">
$(function(){
    loadAddOrgTree("orgFormTreedept");
    loadAddzhibiaoTree("ZhiBiaoTreedept");
    loadAddzhibiaoGroupTree("ZhiBiaoTreeqzdept");
    loadAddzhibiaoTreefz("ZhiBiaoTreedeptfz","");
    //loadFullSearchTreefz('fullSearch_ck');
});
function loadFullSearchTree(id) {
	$.ajax({
	async:false,
	cache:false,
	url : "<%=basePath%>/backstage/zhibiao/getZhiBiaoTreess",
			type : "POST",
			success : function(data) {
				$("#"+id).attr("nodes", data);
			}
		});

}
function loadAddzhibiaoGroupTree(id){
	$.ajax({
		url:"<%=basePath%>/backstage/choose_dept/getZhiBiaoGroupTree",
		type : "POST",
		dataType : "json",
		success : function(data) {
						$.fn.zTree.init($("#"+id), {
							view : {
								selectedMulti : false,
								fontCss : {
									color : "#393939"
								}
							},
							data : {
								simpleData : {
									enable : true
								}
							},
							callback : {
								onClick : clickZhiBiaoFormqz
							}
						}, data);
						var treeObj = $.fn.zTree.getZTreeObj(id);
						var nodes = treeObj.getNodes();
						treeObj.expandAll(true);
					}
				});
	}
	function clickZhiBiaoFormqz(event,treeId,treeNode){
		var id = treeNode.id;
		$("#zb_qzdept_id").val(id);
	}
function loadAddzhibiaoTree(id){
	$.ajax({
		type : 'POST',
		url:"<%=basePath%>/backstage/zhibiao/getZhiBiaoTree",
		type : "POST",
		dataType : "json",
		success : function(data) {
						$.fn.zTree.init($("#"+id), {
							view : {
								selectedMulti : false,
								fontCss : {
									color : "#393939"
								}
							},
							data : {
								simpleData : {
									enable : true
								}
							},
							callback : {
								onClick : clickZhiBiaoForm,
								beforeClick : zTreeBeforeClick
							}
						}, data.obj.list);
						
					    treeObj = $.fn.zTree.getZTreeObj(id);
					    var nodes = treeObj.getNodes();
					    treeObj.expandNode(nodes[0],true);
					}
				});
	}
function loadAddzhibiaoTreefz(id,t){
	$.ajax({
		type : 'POST',
		url:"<%=basePath%>/backstage/zhibiao/getZhiBiaoTree",
		type : "POST",
		dataType : "json",
		success : function(data) {
						$.fn.zTree.init($("#"+id), {
							view : {
								selectedMulti : false,
								fontCss : {
									color : "#393939"
								}
							},
							data : {
								simpleData : {
									enable : true
								}
							},
							check : {
								enable: true,
								chkStyle : "checkbox",
								chkboxType: { "Y": "s", "N": "s" }
							},
							callback : {
								onCheck : NodeCheck_Dept,
							}
						}, data.obj.list);
						var treeObj = $.fn.zTree.getZTreeObj(id);
						var nodes = treeObj.getNodes();
						treeObj.expandNode(nodes[0],true);
						if(t=="" || t==null || t=="undefined"){
							return;
						}
						t=t.replace("[","");
						t=t.replace("]","");
						var tt = t.split(",");
					 	if($.trim(tt[0])==""){
							return;
						}
						for(var i=0;i<tt.length;i++){
							var s = $.trim(tt[i]);
							var node1 = treeObj.getNodeByParam("id",s);
							console.log(node1);
							treeObj.checkNode(node1,true,false); 
						} 
					}
				});
	}
function NodeCheck_Dept(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId), nodes = zTree
	.getCheckedNodes(true);
    var ids = '', names = '';
    for (var i = 0; i < nodes.length; i++) {
  	   ids += ',' + nodes[i].id;
 	   names += ',' + nodes[i].name;
    }
    if (ids.length > 0) {
       ids = ids.substr(1), names = names.substr(1);
    }
    $("#g_deptbh_addfz").val(ids);
    $("#g_deptmc_addfz").val(names);
    var $from = $($('#' + treeId).data('fromObj'));
    if ($from && $from.length)
       $from.val(names);
}
function clickZhiBiaoForm(event,treeId,treeNode){
	var id = treeNode.id;
	$("#zb_dept_id").val(id);
}

function zTreeBeforeClick(treeId, treeNode, clickFlag) {
	return !treeNode.isParent;
};
function loadAddOrgTree(id){
	$.ajax({
        type : 'POST',
        url:"<%=basePath%>backstage/model/getModelTree_withzbk?supunit=${supunit}",
        type : "POST",
        dataType : "json",
        success : function(data) {
            $.fn.zTree.init($("#"+id), {
                view : {
                    selectedMulti : false,
                    dblClickExpand: false,
                    fontCss : {
                        color : "#393939"
                    }
                },
                data : {
                    simpleData : {
                        enable : true
                    }
                },
                check: {
                	enable: true,
					chkStyle: "checkbox",
					chkboxType: { "Y": "", "N": "" }
                },
                callback : {
                    //onCheck : clicknode_dept,
                    onDblClick: zTreeOnDblClick,
                    beforeDblClick: zTreeBeforeOnDblClick,
                    beforeCheck: zTreeBeforeCheck
                }
            }, data);
            var treeObj = $.fn.zTree.getZTreeObj(id);
            var nodes = treeObj.getNodes();
		    treeObj.expandAll(true);
        }
    });
}
function zTreeOnDblClick(event,treeId,treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("orgFormTreedept");
	treeObj.checkNode(treeNode,true,false);
	treeObj.setChkDisabled(treeNode, true);
	var itemdept_add = $("#itemdept_add").val();
	var nodeid = treeNode.id;
	var flag=true;
	var ids = itemdept_add.split(',');
	for(var i=0;i<ids.length;i++){
		if(nodeid==ids[i]){
			flag = false;
		}
	}
	if(flag){
		var sss = getFilePath(treeNode);
		var ArrId = sss.split(',');
		ArrId.splice(0,2);
		var id="";
		var name="";
		for(var i=0;i<ArrId.length;i++){
			if(ArrId[i]!=null&&ArrId[i]!=''){
				id+=ArrId[i];
				if(i!=ArrId.lenth-1){
					id+='-';
				}
			}
		}
		id=id.substring(0,id.length-1);
		var itemdept_add = $("#itemdept_add").val();
		itemdept_add = treeNode.id+","+itemdept_add;
		$("#itemdept_add").val(itemdept_add);
		var html =  $("#selectedzbk").html();
		html +='<p><button type="button" style="" class="btn btn-default  btn-sm">'+id+'<span style="color:red" onclick="deleteEmailUser('+treeNode.id+',this)">×</span></button></p>'
		$("#selectedzbk").html(html);
	}
	
	
}
function getFilePath(treeObj){
	if(treeObj==null)return "";
	var filename = treeObj.name;
	var pNode = treeObj.getParentNode();
	if(pNode!=null){
	filename = getFilePath(pNode) +","+ filename;
	}
	return filename;
	}
function zTreeBeforeOnDblClick(treeId, treeNode, clickFlag) {
	
	 return !treeNode.isParent;
}
function zTreeBeforeCheck(treeId, treeNode, clickFlag) {
	
	 return !treeNode.isParent;
};
function deleteEmailUser(id,obj){
	var treeObj = $.fn.zTree.getZTreeObj("orgFormTreedept");
	var node1 = treeObj.getNodeByParam("id",id);
	
	treeObj.setChkDisabled(node1, false);
	treeObj.checkNode(node1,false,true);
	var allId = $("#itemdept_add").val();
	if(allId!=null&&allId!=''){
		var ArrId = allId.split(',');
		for(var i=0;i<ArrId.length;i++){
			if(id==ArrId[i]){
				ArrId.splice(i,1);
				$(obj).parent().remove();
			}
		}
		var id="";
		for(var i=0;i<ArrId.length;i++){
			if(ArrId[i]!=null&&ArrId[i]!=''){
				
				id+=ArrId[i];
				if(i!=ArrId.lenth-1){
					id+=',';
				}
			}
		}
		if(id.lastIndexOf(',')!=-1){
			id = id.substring(0,id.length-1);
		}
		$("#itemdept_add").val(id);
	}
	
}
    $('input[type=radio][name=xzdepts]').change(function() {
    	var s = $("#zb_dept_id").val();
    	var q =$("#zb_dept_id").val();
    	var treeObj = $.fn.zTree.getZTreeObj("ZhiBiaoTreedept");
    	var node1 = treeObj.getNodeByParam("id",s);
    	if(this.value == 'N'){
    		treeObj.cancelSelectedNode(node1);
    		$("#zb_dept_id").val("");
    		$("#g_deptbh_addfz").val("");
    	}
    	var treeObj2 = $.fn.zTree.getZTreeObj("ZhiBiaoTreeqzdept");
    	var node2 = treeObj2.getNodeByParam("id",q);
    	var treeObj3 = $.fn.zTree.getZTreeObj("ZhiBiaoTreedeptfz");
    	if(this.value != 'N'){
    		treeObj2.cancelSelectedNode(node2);
    		treeObj3.checkAllNodes(false);
    		treeObj3.cancelSelectedNode();
    		
    		$("#zb_qzdept_id").val("");
    	}
    
    	
    	
        if (this.value == 'Y') {
        	$("#ZhiBiaoTreeqzdept").hide();
            $("#ZhiBiaoTreedept").show();
            $("#ZhiBiaoTreedeptfz").hide();
        }else if (this.value == 'N') {
            $("#ZhiBiaoTreeqzdept").show();
            $("#ZhiBiaoTreedept").hide();
            $("#ZhiBiaoTreedeptfz").hide();
        }else if (this.value == 'F') {
            $("#ZhiBiaoTreeqzdept").hide();
            $("#ZhiBiaoTreedept").hide();
            $("#ZhiBiaoTreedeptfz").show();
        }
    });
</script>
<div class="pageContent">
<form id="deptchooses" method="post"  action="<%=basePath%>backstage/zhibiaogl/deptchooses" class="pageForm form-validate" callback="dialogAjaxDone"  noEnter>
<input type="hidden" name="itemdept_add" id="itemdept_add" value=""/>
<input type="hidden" name="zb_dept_id" id="zb_dept_id" value=""/>
<input type="hidden" name="zb_qzdept_id" id="zb_qzdept_id" value=""/>
<input type="hidden" name="g_dept" id="g_deptbh_addfz" value=""/>
<input type="hidden" name="" id="g_deptmc_addfz" value=""/>
<table class="table table-condensed "  style="margin-left:11px;border-collapse: separate; border-spacing: 0px 5px;width:790px;" layoutH="32">
<tbody>
<tr>
                <td align="right" style="border-top-width: 0px;width: 40px" rowspan="3">模板:</td>
                <td  style="border-top-width: 0px;width:380px;vertical-align: top;" rowspan="3">
                    <div style="height:200px;width:400px">
                        <fieldset style="border-left-width:1px">
                            <div class="clearfix">
                                <div style="float: left; width: 95%; height:390px; overflow: auto;">
                                    <ul id="orgFormTreedept" class="ztree" style="margin-top:-6px" attrs = '{"expandAll":true}'
                                        nodes=''>
                                    </ul>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </td>
                <td align="left" style="border-top-width: 0px;width: 60px">指标项:</td>
                <td  style="border-top-width: 0px">
                 <div style="height:200px;width:280px">
                     <fieldset style="border-left-width:1px">
                            <div style="float: left; width: 95%; height:180px; overflow: auto;" id="selectedzbk">
                             </div>
                        </fieldset>
                        </div>
                </td>
            </tr>
            <tr>
            <td align="left" style="border-top-width: 0px;width: 50px"></td>
                <td  style="border-top-width: 0px">
                <div class="radioDiv">
					<input id="xzdept" type="radio" name="xzdepts"  value="Y" <c:if test="${flag=='Y' }">checked="checked"</c:if>/>牵头科室
					<input id="xzdeptfz" type="radio" name="xzdepts" value="F" <c:if test="${flag=='F' }">checked="checked"</c:if>/>辅助科室
					<input id="xzqunzu" type="radio" name="xzdepts" value="N" <c:if test="${flag=='N' }">checked="checked"</c:if>/>群组
				</div>
                 <div style="height:200px;width:280px">
                 
                     <fieldset style="border-left-width:1px">
                     <div style="float: left; width: 95%; height:180px; overflow: auto;" >
									<ul id="ZhiBiaoTreedept" class="ztree authorityTree" ></ul>
									<ul id="ZhiBiaoTreedeptfz" class="ztree authorityTree" style="display: none;"></ul>
									<ul id="ZhiBiaoTreeqzdept" class="ztree authorityTree" style="display: none;"></ul>
							</div>
                        </fieldset>
                        </div>
                </td>
            </tr>
          <!--   <tr>
            <td align="left" style="border-top-width: 0px;width: 50px"></td>
            <td align="left" style="border-top-width: 0px;">
                            <label >辅助科室：</label>
                            <input type="text" autocomplete="off"
           id="Search_ck_all" class="form-control j-selectzTree " value="" 
                				size="20"  value="" data-tree="#fullSearch_ck" onclick="ckktidcx()" style="font-size: 12px;">
                			<ul id="fullSearch_ck" style="height: 300px;" class="ztree hide" attrs = '{ "checkEnable":true, "chkStyle":"checkbox","chkboxType":{"Y":"", "N": ""}, "radioType":"all", "onCheck":"fullSearch_ck_NodeCheck"}' 
                   			 nodes=''></ul>
                        </td>
            </tr> -->
</tbody>
</table>
<div class="formBar">
				<ul>
					<li><button type="submit" class="btn btn-default btn-sm">保存</button></li>
					<li><button type="button" class="btn btn-close btn-sm">关闭</button></li>
				</ul>
			</div>
</form>
</div>