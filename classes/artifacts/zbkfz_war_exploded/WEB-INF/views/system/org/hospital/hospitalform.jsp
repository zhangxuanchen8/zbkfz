<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(function(){
	/* loadselect("orgtype",12,"${hospital.orgtype}");
	loadselect("distcode",1,"${hospital.distcode}"); */
	loadselect("degreelevel",25,"${hospital.degreelevel}");
	loadselect("hosdegree",53,"${hospital.hosdegree}");
	loadselect("dwhyfl",'58',"${hospital.dwhyfl}");
	loadselect("sydwfl",'54',"${hospital.sydwfl}");
	$('#hosnum').keyup(function(){
		$('#nodecode').val($(this).val());
	});

});
function loadselect(id,nekey,selected){
	$.ajax({
		type : 'POST',
		url : '<%=basePath%>backstage/org/hospital/selectDict',
		data : {
			nekey : nekey
		},
		dataType : 'json',
		success : function(data) {
			var opts="";
			opts="<option value=''> </option>";
			$.each(data,function(){
				opts+="<option value='"+this.nevalue+"'";
				if (this.contents == selected || this.nevalue == selected) {
					opts+= " selected='selected'";
				}
				opts+=">"+this.contents+"</option>";
			});
			document.getElementById(id).innerHTML = opts;
		}
	});
}
$('#nodetype').change(function(){
	if(this.value=='院区'){
		if($('#hosnum').hasClass('validate[required] required')){
		$('#hosnum').removeClass('validate[required] required');
		$('#hosnum').attr('readonly','readonly');
		$('#hosnum').val($('#supunit').val());
		$('#nodecode').removeAttr('readonly');
		$('#nodecode').addClass('validate[required] required');
		}
	}
	if(this.value=='医院'){
		if(!$('#hosnum').hasClass('validate[required] required')){
		$('#hosnum').addClass('validate[required] required');
		$('#hosnum').removeAttr('readonly');
		$('#hosnum').val($('#oldhosnum').val());
		$('#nodecode').attr('readonly','readonly');
		$('#nodecode').removeClass('validate[required] required');
		$('#nodecode').val($('#oldhosnum').val());
		}
	}
});
$('#zzppdw').change(function(){
	$('#nodecode').attr('readonly','readonly');
});
</script>
<!-- 卫生院 -->
<div class="pageContent">
		<form action="<%=basePath%>backstage/org/hospital/save" id="hospitalform"  class="pageForm form-validate"  method="post" callback="dialogAjaxDone" noEnter >
		    <input type="hidden" name="supunit" id="supunit" value="${supunit}"/>
			<input type="hidden" name="showtype" id="showtype" value="${showtype}"/>
			<input type="hidden" name="oldhosnum" id="oldhosnum" value="${hospital.hosnum}"/>
			<input type="hidden" name="oldnodecode" id="oldnodecode" value="${hospital.nodecode}"/>
			<table class="table table-condensed " width="100%" style="border-collapse: separate; border-spacing: 0px 5px;"   layoutH="38">
				<tbody>
				    <tr>
						<td align="right" style="border-top-width: 0px">名&emsp;&emsp;称：</td>
						<td colspan="3" style="border-top-width: 0px" width="300px">
						<input type="text"  id="hosname"  name="hosname" class="form-control validate[required] required" style="width: 100%;border-color: red;" value="${hospital.hosname}"></td>
						
						<td align="right" style="border-top-width: 0px">简&emsp;&emsp;称：</td>
						<td style="border-top-width: 0px" width="150px">
						<input type="text"  id="shortname"  name="shortname" class="form-control" style="width: 100%;" maxlength="10" value="${hospital.shortname}"></td>
						
					</tr>
					<tr>
						<td align="right" style="border-top-width: 0px">上级单位：</td>
						<td colspan="3" style="border-top-width: 0px" width="300px">
						<input type="text"  readonly id="parentname"  name="parentname" class="form-control" style="width: 100%;" value="${parentname}"></td>
						<td align="right" style="border-top-width: 0px">医院编码：</td>
						<td style="border-top-width: 0px" width="150px">
						<input type="text" id="hosnum" name="hosnum"  maxlength="6" onkeyup="this.value=this.value.replace(/\D/g,'')" class="form-control <c:if test="${hospital.nodetype=='医院'}">validate[required] required</c:if>" style="width: 100%;border-color: red;" <c:if test="${hospital.nodetype=='院区'}">readonly='readonly'</c:if> value="${hospital.hosnum}">
						</td>
					</tr>
					<tr>
						<td align="right" style="border-top-width: 0px;display: none;" >节点编码：</td>
						<td style="border-top-width: 0px;display: none;" width="100px">
						<input type="text"   id="nodecode" maxlength="4"  name="nodecode" class="form-control <c:if test="${hospital.nodetype=='院区'}">validate[required] required</c:if>" style="width: 100%;" <c:if test="${hospital.nodetype=='医院'}">readonly='readonly'</c:if> value="${hospital.nodecode}"></td>
					    <td align="right" style="border-top-width: 0px">机构类型：</td>
						<td style="border-top-width: 0px" id="nodetype_pos" width="100px">
						<select class="form-control validate[required]" style="width: 100%;" name="nodetype" id="nodetype">
									<option value="医共体"  <c:if test="${hospital.nodetype=='医共体'}">selected='selected'</c:if>>医共体</option>
									<option value="区域"  <c:if test="${hospital.nodetype=='区域'}">selected='selected'</c:if>>区域</option>
									<option value="医院"  <c:if test="${hospital.nodetype=='医院'}">selected='selected'</c:if>>医院</option>
									<option value="中心卫生院"  <c:if test="${hospital.nodetype=='中心卫生院'}">selected='selected'</c:if>>中心卫生院</option>
									<option value="乡(镇)卫生院"  <c:if test="${hospital.nodetype=='乡(镇)卫生院'}">selected='selected'</c:if>>乡(镇)卫生院</option>
									<option value="村卫生室"  <c:if test="${hospital.nodetype=='村卫生室'}">selected='selected'</c:if>>村卫生室</option>
									<option value="社区服务中心"  <c:if test="${hospital.nodetype=='社区服务中心'}">selected='selected'</c:if>>社区服务中心</option>
									<option value="机关单位"  <c:if test="${hospital.nodetype=='机关单位'}">selected='selected'</c:if>>机关单位</option>
									
							<%-- <c:choose>
								<c:when test="${hospital.nodetype=='医院'}">
									<option value="医院" selected='selected'>医院</option>
									<option value="区域">区域</option>
									<option value="村卫生室">村卫生室</option>
									<option value="社区服务中心">社区服务中心</option>
									<option value="分院">分院</option>
									<option value="机关单位">机关单位</option>
								</c:when>
								<c:when test="${hospital.nodetype=='区域'}">
									<option value="区域" selected='selected'>区域</option>
									<option value="医院">医院</option>
									<option value="村卫生室">村卫生室</option>
									<option value="社区服务中心">社区服务中心</option>
									<option value="分院">分院</option>
									<option value="机关单位">机关单位</option>
								</c:when>
								<c:when test="${hospital.nodetype=='村卫生室'}">
									<option value="村卫生室" selected='selected'>村卫生室</option>
									<option value="区域">区域</option>
									<option value="医院">医院</option>
									<option value="社区服务中心">社区服务中心</option>
									<option value="分院">分院</option>
									<option value="机关单位">机关单位</option>
								</c:when>
								<c:when test="${hospital.nodetype=='社区服务中心'}">
									<option value="村卫生室">村卫生室</option>
									<option value="区域">区域</option>
									<option value="医院">医院</option>
									<option value="社区服务中心" selected='selected'>社区服务中心</option>
									<option value="分院">分院</option>
									<option value="机关单位">机关单位</option>
								</c:when>
								<c:when test="${hospital.nodetype=='分院'}">
									<option value="村卫生室">村卫生室</option>
									<option value="区域">区域</option>
									<option value="医院">医院</option>
									<option value="社区服务中心">社区服务中心</option>
									<option value="分院" selected='selected'>分院</option>
									<option value="机关单位">机关单位</option>
								</c:when>
								<c:when test="${hospital.nodetype=='机关单位'}">
									<option value="村卫生室">村卫生室</option>
									<option value="区域">区域</option>
									<option value="医院">医院</option>
									<option value="社区服务中心">社区服务中心</option>
									<option value="分院">分院</option>
									<option value="机关单位" selected='selected'>机关单位</option>
								</c:when>
								<c:otherwise>
									<option value="院区">院区</option>
									<option value="医院">医院</option>
									<option value="村卫生室">村卫生室</option>
									<option value="社区服务中心">社区服务中心</option>
									<option value="分院">分院</option>
									<option value="机关单位">机关单位</option>
								</c:otherwise>
							</c:choose> --%>
					    </select>
					    </td>
						<td align="right" style="border-top-width: 0px">排序号：</td>
						<td style="border-top-width: 0px" width="90px">
						<input type="text"   id="organizeno"  name="organizeno" class="form-control validate[custom[integer]]" value="${hospital.organizeno}" style="width: 100%;"></td>
						<td align="right" style="border-top-width: 0px">床&ensp;位&ensp;数：</td>
						<td style="border-top-width: 0px" width="150px">
						<input type="text"   id="beds"  name="beds" class="form-control validate[custom[integer]]" value="${hospital.beds}" style="width: 100%;"></td>
						<td align="right" style="border-top-width: 0px;display: none;">机构分类：</td>
						<td style="border-top-width: 0px;display: none;" id="orgtype_pos" width="100px">
							<select class="form-control " style="width: 100%;" name="orgtype" id="orgtype">
								<option value="${hospital.orgtype}">${hospital.orgtype}</option>
						    </select> 
					    </td>
				    </tr>
				    <tr>
						<td align="right" style="border-top-width: 0px">单位行业分类：</td>
						<td colspan="3" style="border-top-width: 0px" width="300px">
							<select class="form-control " style="width: 100%;" name="dwhyfl" id="dwhyfl">
								<option value="${hospital.sydwfl}">${hospital.sydwfl}</option>
						    </select> 
						</td>
						<td align="right" style="border-top-width: 0px">事业单位分类：</td>
						<td colspan="3" style="border-top-width: 0px" width="300px">
							<select class="form-control " style="width: 100%;" name="sydwfl" id="sydwfl">
								<option value="${hospital.sydwfl}">${hospital.sydwfl}</option>
						    </select> 
						</td>
					</tr>
					<tr>
						<td align="right" style="border-top-width: 0px">医共体类型：</td>
						<td style="border-top-width: 0px" width="150px">
						<select class="form-control" style="width: 100%;" name="ygtlx" id="ygtlx">
									<option value=""  <c:if test="${hospital.ygtlx==''}">selected='selected'</c:if>></option>
									<option value="总院"  <c:if test="${hospital.ygtlx=='总院'}">selected='selected'</c:if>>总院</option>
									<option value="分院"  <c:if test="${hospital.ygtlx=='分院'}">selected='selected'</c:if>>分院</option>
									</select>
						</td>
						<td align="right" style="border-top-width: 0px" colspan="2">卫生机构（组织）代码：</td>
						<td colspan="2" style="border-top-width: 0px" width="300px">
						<input type="text"  id="jgdm"  name="jgdm" class="form-control" style="width: 100%;" value="${hospital.jgdm}"></td>
						
						
						
					</tr>
					
					<tr>
						<td align="right" style="border-top-width: 0px">自主评聘单位：</td>
						<td style="border-top-width: 0px" width="150px">
						<select class="form-control" style="width: 100%;" name="zzppdw" id="zzppdw">
									<option value="N"  <c:if test="${hospital.zzppdw=='N' || hospital.zzppdw=='' }">selected='selected'</c:if>>否</option>
									<option value="Y"  <c:if test="${hospital.zzppdw=='Y'}">selected='selected'</c:if>>是</option>
									</select>
						</td>
						<td align="right" style="border-top-width: 0px" colspan="2">监管中心ID：</td>
						<td colspan="2" style="border-top-width: 0px" width="300px">
						<input type="text"  id="jgzxid"  name="jgzxid" class="form-control" style="width: 100%;" value="${hospital.jgzxid}" ></td>
					</tr>
					<tr >
						<%-- <td align="right" style="border-top-width: 0px">行政区划：</td>
					    <td style="border-top-width: 0px" width="100px">
					       <select class="form-control" style="width:100%" name="distcode" id="distcode">
					       <option value="${hospital.distcode}">${distname}</option>
						   </select>
					    </td> --%>
						<td align="right" style="border-top-width: 0px">医院级别：</td>
						<td style="border-top-width: 0px" id="hosdegree_pos" width="100px">
							<select class="form-control" style="width:100%" name="hosdegree" id="hosdegree">
								<option value="${hospital.hosdegree}">${hospital.hosdname}</option>
							</select>
							<input type="hidden" id="hosdname" name="hosdname" value="${hospital.hosdname}"/>
						</td>
						
						<td align="right" style="border-top-width: 0px"  colspan="2">级别等级：</td>
						<td style="border-top-width: 0px" id="degreelevel_pos" width="100px">
							   <select class="form-control" style="width:100%" name="degreelevel" id="degreelevel">
									<option value="${hospital.degreelevel}">${hospital.degreelname}</option>
							   </select>
							   <input type="hidden" id="degreelname" name="degreelname" value="${hospital.degreelname}"/>
						</td>
					</tr>
					<tr style="display: none;">
						<td align="right" style="border-top-width: 0px">全院人数：</td>
						<td style="border-top-width: 0px" width="100px">
						<input type="text"   id="empnumber"  name="empnumber" class="form-control validate[custom[integer]]" value="${hospital.empnumber}" style="width: 100%;"></td>
						<td align="right" style="border-top-width: 0px">医&ensp;生&ensp;数：</td>
						<td style="border-top-width: 0px" width="100px">
						<input type="text"   id="doctors"  name="doctors" class="form-control validate[custom[integer]]" value="${hospital.doctors}" style="width: 100%;"></td>
					</tr>
					 <tr style="display: none;">
						<td align="right" style="border-top-width: 0px">护&ensp;士&ensp;数：</td>
						<td style="border-top-width: 0px" width="100px">
						<input type="text"   id="nurses"  name="nurses" class="form-control validate[custom[integer]]" value="${hospital.nurses}" style="width: 100%;"></td>
					    <td align="right" style="border-top-width: 0px">地&emsp;&emsp;址：</td>
					    <td colspan="3" style="border-top-width: 0px" width="300px">
					    <input name="address" type="text" id="address" style="width:100%" class="form-control" value="${hospital.address}"/></td>
				      </tr>
				      <tr style="display: none;">
					    <td align="right" style="border-top-width: 0px">电&emsp;&emsp;话：</td>
					    <td style="border-top-width: 0px" width="100px">
					    <input type="text" id="tel" name="tel" style="width:100px" class="form-control" value="${hospital.tel}"/></td>
					    <td align="right" style="border-top-width: 0px">医保编号：</td>
					    <td style="border-top-width: 0px" width="100px">
					    <input class="form-control validate[custom[onlyLetterNumber]]" name="ycentercode" type="text" id="ycentercode" style="width:100%" value="${hospital.ycentercode}"/></td>
					    <td align="right" style="border-top-width: 0px">农保编号：</td>
					    <td style="border-top-width: 0px" width="100px">
					    <input class="form-control validate[custom[onlyLetterNumber]]" name="ncentercode" type="text" id="ncentercode" style="width:100%" value="${hospital.ncentercode}"/></td>
					    <td align="right" style="border-top-width: 0px">&nbsp;</td>
					    <td style="border-top-width: 0px">&nbsp;</td>
				      </tr>
					  <tr style="display: none;">
					    <td align="right" style="border-top-width: 0px">简&emsp;&emsp;介：</td>
					    <td colspan="5" style="border-top-width: 0px" width="500px">
					    <textarea name="introduction" rows="3" class="txtarea" id="introduction" style="width:100%" >${hospital.introduction}</textarea>
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