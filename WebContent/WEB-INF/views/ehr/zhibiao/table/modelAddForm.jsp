<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(document).ready(function(){
    loadselectvc("model_yq",101,'${m.yq}');
    loadselectvc("model_zblb",103,'${m.zblb}');
});


</script>
<style>
.table>tbody>tr>td{
	border-top: 0px;
}
</style>
<iframe width=0 height=0 frameborder=0 name="hrong" style="display:none"></iframe>
<div class="pageContent">
    <form action="<%=basePath%>backstage/model/addModel" id="modelAddForm" class="pageForm form-validate" method="post"  callback="dialogAjaxDone" noEnter>
        <input type="hidden" name="id" value="${m.id}">
            <table class="table table-condensed " width="100%" layouth="30">
                <tbody>
                    <tr >
                        <td class="col-md-4" style="padding-top: 10px">
                            <label for="" class="control-label x85" style="width:80px; ">名称：</label>
                            <input type="text" name="name" value="${m.name}" id="model_name"  class="form-control  validate[required] required"  style="width: 200px">
                        </td>
                    </tr>
                    <tr >
                        <td class="col-md-4" style="padding-top: 10px">
                            <label for="" class="control-label x85" style="width:80px; ">简称：</label>
                            <input type="text" name="shortname" value="${m.shortname}" id="model_name"  class="form-control"  style="width: 200px">
                        </td>
                    </tr>
                    <tr>
                        <td class="col-md-4" style="padding-top: 10px">
                            <label for="" class="control-label x85" style="width:80px; ">院区：</label>
                            <select class="form-control  validate[required] required" style="width:200px;border-color: red;" name="yq" id="model_yq" >

                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="col-md-4" style="padding-top: 10px">
                            <label for="" class="control-label x85" style="width:80px; ">类别：</label>
                            <select class="form-control validate[required] required" style="width:200px;border-color: red;" name="zblb" id="model_zblb" >

                            </select>
                        </td>
                    </tr>
                    <tr >
                        <td class="col-md-4" style="padding-top: 10px">
                            <label for="" class="control-label x85" style="width:80px; ">序号：</label>
                            <input type="number" name="xh" value="${m.xh}" id="xh"  class="form-control  validate[required] required"   style="width: 200px">
                        </td>
                    </tr>
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