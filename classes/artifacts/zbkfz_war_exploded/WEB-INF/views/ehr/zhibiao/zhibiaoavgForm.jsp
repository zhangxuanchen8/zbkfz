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
    var zTree;
    var i = 0;
    $(function(){
        loadAddOrgTree1("orgFormTree1");
       // loadFullSearchTree_major_model("fullSearch_now_major_main_model",2);
        //loadzbkHISTree("zbkavgTree");
    });

    /* 获取从事专业的树 */
  /*   function loadFullSearchTree_major_model(id,nekey) {
        $.ajax({
            async:false,
            cache:false,
            url : "dictionary/getZyTreeJson?nekey="+nekey,
            type : "GET",
            success : function(data) {
                $("#"+id).attr("nodes", data);
                var treeObj = $.fn.zTree.getZTreeObj("fullSearch_now_major_main_model");
                if("${xcszy_model}"!=null && "${xcszy_model}"!=""){
                    var node = treeObj.getNodeById("${xcszy_model}");
                    treeObj.checkNode(node, true, true);
                }
            }
        });
    } */

    function fullSearch_work_now_major_main_NodeCheck_model(e, treeId, treeNode) {
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
        $("#xcszy_model").val(ids);
        $("#now_major_mc_main_model").val(names);
        var $from = $($('#' + treeId).data('fromObj'));
        if ($from && $from.length)
            $from.val(names);
        loadzbkHISTree("zbkavgTree");
    }

    //加载组织结构树
    function loadAddOrgTree1(id){
        $.ajax({
            type : 'POST',
            url:"<%=basePath%>backstage/model/getModelTree_withzbk",
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
                    check: {
                        enable: true,
                        chkStyle: "radio",
                        chkboxType: { "Y": "ps", "N": "ps" }
                    },
                    callback : {
                        onCheck : clickOrg_orgForm1
                    }
                }, data);
                var treeObj = $.fn.zTree.getZTreeObj(id);
                var nodes = treeObj.getNodes();
                treeObj.expandNode(nodes[0],true);
                if("${zhibiaoku.deptid}"!=null&&"${zhibiaoku.deptid}"!=""){
                    checkmenutree("${zhibiaoku.deptid}");
                }
            }
        });
    }
    function loadzbkHISTree(id,pid){
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
 							check: {
 								enable: true,
 								chkStyle: "checkbox",
 								chkboxType: { "Y": "ps", "N": "ps" }
 							},
                    callback : {
                        onCheck : selectHIS,
                    }
                }, data);
                var treeObj = $.fn.zTree.getZTreeObj(id);
                var nodes = treeObj.getNodes();
                treeObj.expandNode(nodes[0],true);
                if("${zhibiaoku.his_deptid}"!=null&&"${zhibiaoku.his_deptid}"!=""){
                    var thenode = treeObj.getNodeByParam("id", "${zhibiaoku.his_deptid}");
                    treeObj.expandNode(thenode.getParentNode(),true);
                    treeObj.selectNode(thenode);
                }
            }
        });
    }
    function clickOrg_orgForm1(event,treeId,treeNode){
        $("#itemid").val(treeNode.id);
        loadzbkHISTree("zbkavgTree",treeNode.id);
    }
    function getChekcedId(){
        var tree = $.fn.zTree.getZTreeObj("orgFormTree1");
        var menus = tree.getCheckedNodes(true);
        var str = "";
        for (var k = 0, length = menus.length; k < length; k++) {
            if(menus[k].checked == true)
                str += menus[k].id + ","
        }
        return str;
    }
    function checkmenutree(t){
        if(t=="" || t==null || t=="undefined"){
            return;
        }
        t=t.replace("[","");
        t=t.replace("]","");
        t=t.substring(0,t.length-1);
        $("#deptid1").val(t);
        var tt = t.split(",");
        if($.trim(tt[0])==""){
            return;
        }
        var treeObj = $.fn.zTree.getZTreeObj("orgFormTree1");
        for(var i=0;i<tt.length;i++){
            var s = $.trim(tt[i]);
            var node1 = treeObj.getNodeByParam("id",s);
            treeObj.checkNode(node1,true,false);
            $("#deptname").val(node1.name);
        }
    }

    function returncompute(gongshi){
        var rel = "computeform";
        var options = {
            resizable:true,
            drawable:true,
            maxable:true,
            minable:true
        };
        var w = 690;
        var h = 450;
        if (w) options.width = w;
        if (h) options.height = h;
        var url = "<%=basePath%>backstage/zhibiaoku/Editcompute?gongshi="+encodeURIComponent(gongshi);
        $.pdialog.open(url, "","指标配置", options);
    }
    function zhibiaokuformsubmit(){
        $('#zhibiaoku_form').ajaxSubmit({
            success: showResponse_1,
            error: function () {
                alertMsg.error("服务器错误！");
            }
        });
    }
    function showResponse_1(responseText, statusText, xhr, $form){
        var responseText=JSON.parse(responseText);
        console.log(typeof(responseText["statusCode"]));
        if(responseText["statusCode"]=="200"){
            alertMsg.correct(responseText["message"]);
            $("#zhibiaokuformclose").click();
            $("#zhibiaokulistsubmit").click();
        } else {
            alertMsg.error(responseText["message"]);
        }
    }
    function selectHIS(event,treeId,treeNode){
        var treeObj = $.fn.zTree.getZTreeObj("zbkavgTree");
        var nodes = treeObj.getCheckedNodes(true);
        var nodes_str = "";
        for(var i=1;i<nodes.length;i++){
            nodes_str+=nodes[i].id+",";
        }
        nodes_str = nodes_str.substr(0,nodes_str.length-1)
        $("#nameid").val(nodes_str);
    }
</script>
<div class="pageContent">
    <form id="modelavgForm" method="post"  action="<%=basePath%>backstage/zhibiao/match" class="pageForm form-validate" callback="dialogAjaxDone"  noEnter>
        <input type="hidden" id="deptid1" name="deptid" value=""/>
        <input type="hidden" name="id" value="${new_ryzbk.id }"/>
        <input type="hidden" id="itemid" name="itemid" value=""/>
        <input type="hidden" id="nameid" name="nameid" value=""/>
        <table class="table table-condensed "  style="margin-left:11px;border-collapse: separate; border-spacing: 0px 5px;width:750px;" layoutH="32">
            <tbody>
            <tr>
                <td align="right" style="border-top-width: 0px"></td>
                <td style="border-top-width:0px">
                </td>
                <td align="right" style="border-top-width: 0px"></td>
                <td  style="border-top-width: 0px;padding-left:16px">
                </td>
            </tr>
            <tr>
            <td align="right" style="border-top-width: 0px">模板:</td>
                <td  style="border-top-width: 0px">
                    <div style="height:200px;width:320px">
                        <fieldset style="border-left-width:1px">
                            <div class="clearfix">
                                <div style="float: left; width: 95%; height:340px; overflow: auto;">
                                    <ul id="orgFormTree1" class="ztree" style="margin-top:-6px" attrs = '{"expandAll":true}'
                                        nodes=''>
                                    </ul>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </td>
                 <td align="right" style="border-top-width: 0px">人员:</td>
                <td  style="border-top-width: 0px">
                    <div style="height:200px;width:330px">
                        <fieldset style="border-left-width:1px">
                            <div class="clearfix">
                                <div style="float: left; width: 95%; height:340px; overflow: auto;">
                                    <ul id="zbkavgTree" class="ztree" style="margin-top:-6px" attrs = '{"expandAll":true}'
                                        nodes=''>
                                    </ul>   
                                </div>
                            </div>
                        </fieldset>
                    </div>
               
                </td>
            </tr>
            </tbody>
        </table>
        <div class="formBar">
            <ul>
                <li><button type="submit"  class="btn btn-default btn-sm">保存</button></li>
                <li><button type="button" id="zhibiaokuformclose" class="btn btn-close btn-sm">关闭</button></li>
            </ul>
        </div>
    </form>
</div>