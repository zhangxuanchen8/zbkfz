<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(function(){
	getSysnames('${sysdict.sysname}');
	getScopes('${sysdict.scope}');
	getParmvalue('${sysdict.parmvalue}')
	var $radio = $('input[name="canedit_1"]');
	$radio.on('ifChanged', function(event) {
	    var $target = $(event.target);
	    var checked = $target.prop('checked');
	    var value   = $target.val();
	    $('#canedit').val(value);
	});
});
function getScopes(value){
	/*加载scope下拉框*/
	$.post("<%=basePath%>backstage/sysDict/getScopes",function(data){ 
           var opts="";
		   opts="<option value=''> </option>";
		   $(eval(data)).each(function(i,v){
				opts+="<option value='"+v+"'";
				if (v ==value) {
					opts+= " selected='selected'";
				}
				opts+=">"+v+"</option>";
			});
			document.getElementById("scope1").innerHTML = opts;
		});
}
function getSysnames(value){
	if(value!=null&&value!=""){
		loadselectcc("sysname",80,value);	
	}else{
		loadselectcc("sysname",80,null);
		
	}
}
function getParmvalue(value){
	var defaultparms = $('#defaultparms').val();
	var parmsArr = new Array();
	parmsArr = defaultparms.split("|");
	var opts="";
	$.each(parmsArr,function(i,v){
		opts+="<option value='"+v+"'";
		if (v== value) {
			opts+= " selected='selected'";
		}
		opts+=">"+v+"</option>";
	});
	document.getElementById("parmvalue").innerHTML = opts;
}
//添加默认参数
function addParams(){
	var defaultparms = $('#defaultparms').val();
	var parmsArr = new Array();
	parmsArr = defaultparms.split("|");
	var opts="";
	$.each(parmsArr,function(i,v){
		opts+="<option value='"+v+"'";
		opts+=">"+v+"</option>";
	});
	document.getElementById("parmvalue").innerHTML = opts;
}
function sysnameChange(){
	var sysname =$("#sysname").val();
	$('#sysname2').val(sysname);
}
function scopeChange(){
	var scope =$("#scope1").val();
	$('#scope').val(scope);
}
</script>
<!-- 系统参数 -->
<div class="pageContent">
		<form action="<%=basePath%>backstage/sysDict/saveSysDict" id="sysdictform"  class="pageForm form-validate"  method="post" callback="dialogAjaxDone" noEnter >
			<input type="hidden" name="showtype" id="showtype" value="${showtype}"/>
			<table class="table table-condensed " width="100%" style="border-collapse: separate; border-spacing: 0px 5px;">
				<tbody>
				    <tr>
						<td width="80" align="right" style="border-top-width: 0px" >参数名称：</td>
						<td width="150" style="border-top-width: 0px">
						<input id="parmid" name="parmid" type="hidden"	value="${sysdict.parmid}"/>
						<input type="text"  id="parmname"  name="parmname" class="form-control validate[required] required validate[maxSize[20]]" style="width: 140px;border-color: red;" value="${sysdict.parmname}"></td>
						
						<td width="75" align="right" style="border-top-width: 0px">默认参数：</td>
						<td width="150" style="border-top-width: 0px">
							 <select class="form-control validate[required] validate[maxSize[20]]" id="parmvalue" name="parmvalue" style="width: 140px;border-color: red;">
								<option value="${sysdict.parmvalue}">${sysdict.parmvalue}</option>
						     </select>
					    </td>
					</tr>
					
					<tr>
						<td align="right" style="border-top-width: 0px">参数范围：</td>
						<td colspan="3" style="border-top-width: 0px">
						<input type="text" id="defaultparms"  name="defaultparms" class="form-control validate[maxSize[50]]" style="width: 393px;border-color: red;" onblur="addParams()" value="${sysdict.defaultparms}">
						</td>
					</tr>
					<tr>
					    <td align="right" style="border-top-width: 0px">作用范围：</td>
						<td style="border-top-width: 0px" >
							<select class="form-control validate[required]" style="width: 140px;border-color: red;" id="scope1" name="scope" onchange="scopeChange()">
						        <option value="${sysdict.scope}">${sysdict.scope}</option>
						    </select>
					    </td>
						<td style="border-top-width: 0px"  align="right">&nbsp;</td>
						<td style="border-top-width: 0px"  align="right">&nbsp;</td>
				    </tr>
				    
					<tr>
						<td align="right" style="border-top-width: 0px">可否编辑：</td>
						<td style="border-top-width: 0px">
							<input id="canedit1" class="j-icheck" type="radio" name="canedit_1" value="1"  <c:if test="${sysdict.canedit=='1' || showtype=='1'}">checked</c:if> />区域
							<input id="canedit2" class="j-icheck" type="radio" name="canedit_1" value="2"  <c:if test="${sysdict.canedit=='2'}">checked</c:if> />医院
							<input id="canedit3" class="j-icheck" type="radio" name="canedit_1" value="0"  <c:if test="${sysdict.canedit=='0'}">checked</c:if> />其它
							<input id="canedit" class="j-icheck" type="hidden" name="canedit" value="${sysdict.canedit}"/> 
						</td>
						
						<td align="right" style="border-top-width: 0px">参数分类：</td>
						<td style="border-top-width: 0px">
							   <select class="form-control " style="width:140px;border-color: red;" name="sysname1" id="sysname" onchange="sysnameChange();">
									
							   </select>
							   <input type="hidden" id="sysname2" name="sysname" value="${sysdict.sysname}"/>
						</td>
					</tr>
					
					<tr>
					    <td align="right" style="border-top-width: 0px">作用描述：</td>
						<td colspan="3" style="border-top-width: 0px" >
						<textarea name="descriptions" rows="3" class="txt form-control validate[maxSize[500]]" id="descriptions" style="width:393px;height:60px;resize: none;">${sysdict.descriptions}</textarea>
						</td>
					</tr>
					
					 <tr>
					    <td align="right" style="border-top-width: 0px">备注：</td>
					    <td colspan="3" style="border-top-width: 0px">
					    <textarea name="comments" rows="3" class="txt form-control validate[maxSize[50]]" id="comments" style="width:393px;height:60px;resize: none;">${sysdict.comments}</textarea>
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