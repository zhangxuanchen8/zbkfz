<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(function(){
	var modelid = '${score.itemid}';
	loadzbkHISTrees("fuzhirenyuan",modelid);
	
});
function loadzbkHISTrees(id,pid){
	$.ajax({
        type : 'POST',
        url:"<%=basePath%>/backstage/zhibiao/getzbkPersonal?pid="+pid,
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
					chkStyle : "radio",
					radioType : "all"
				},
                callback : {
                    onCheck : selectHIS,
                }
            }, data);
            var treeObj = $.fn.zTree.getZTreeObj(id);
            var nodes = treeObj.getNodes();
            treeObj.expandAll(true);
           
        }
    });
}
function selectHIS(event,treeId,treeNode){
	var id = treeNode.id;
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
    var d = $("#fzrenyuan").val(id);
    console.log(d);
    var $from = $($('#' + treeId).data('fromObj'));
    if ($from && $from.length)
       $from.val(names);
}
</script>
<div class="pageContent">
		<form action="<%=basePath%>backstage/zhibiao/savepjf" id="hospitalform"  class="pageForm form-validate"  method="post" callback="dialogAjaxDone" noEnter >
			 <input type="hidden" name="fzrenyuan" id="fzrenyuan" value=""/>
			 <input type="hidden" name="itemid" id="itemid" value="${score.itemid}"/>
			 <input type="hidden" name="score"  value="${score.avgscore}"/>
			 <input type="hidden" name="modelid" id="modelid" value="${modelid}"/>
			<table class="table" width="100%" layoutH="38">  
				<tbody>
					<tr>
						<td align="center" sytle="border-top-width:0px;width:70px" >选择人员</td>
						<td style="border-top-width:0px;width:100px" colspan="3">
							<div class="panel panel-default" id="" style="margin-left: 0px;; margin-right: 6px; padding: 10px; margin-bottom: 0px; height: 160px; overflow: auto;">
								<ul id="fuzhirenyuan" class="ztree authorityTree"></ul>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="formBar">
						<ul>
							<li>
							<button type="submit" class="btn btn-default btn-sm">保存</button>
							</li>
							<li><button type="button" class="btn btn-close btn-sm">关闭</button></li>
						</ul>
			</div>
		</form>
</div>