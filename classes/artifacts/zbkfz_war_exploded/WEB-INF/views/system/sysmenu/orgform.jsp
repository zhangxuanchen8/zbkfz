<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 组织 -->
<div class="pageContent">
		<form action="" id="org_form" class="pageForm form-validate"  method="post" callback="dialogAjaxDone" noEnter >
			<table class="table table-condensed " width="100%" style="border-collapse: separate; border-spacing: 0px 5px;">
				<tbody>
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input type="hidden" name="id" id="newid"></td>
					</tr>
					<tr>
						<td align="right" style="border-top-width: 0px">医院编号：</td>
						<td style="border-top-width: 0px">
						<input type="text"  readonly id="hosnum"  name="hosnum" class="form-control" style="width: 100px;" value="${dept.hosnum}"></td>
						
						<td align="right" style="border-top-width: 0px">院区编码：</td>
						<td style="border-top-width: 0px">
						<input type="text"  readonly id="nodecode"  name="nodecode" class="form-control" style="width: 100px;" value="${dept.nodecode}"></td>
						
					    <td align="right" style="border-top-width: 0px">父类科室：</td>
						<td style="border-top-width: 0px">
						<input type="text"  readonly id="parentid"  name="parentid" class="form-control" style="width: 100px;" value="${dept.parentid}"></td>
					</tr>
					<tr>
					    <td align="right" style="border-top-width: 0px">科室大类：</td>
						<td style="border-top-width: 0px">
						<input type="text"  readonly id="deptclass"  name="deptclass" class="form-control" style="width: 100px;"></td>
						
						<td align="right" style="border-top-width: 0px">临床类别：</td>
						<td style="border-top-width: 0px">
						<div class="radioDiv">
							<input id="clinicaltype1" type="radio" name="clinicaltype" value="临床"
							<c:if test="${dept.clinicaltype=='临床' || dept.clinicaltype==null }">checked="checked"</c:if> />临床 
							<input id="clinicaltype2" type="radio" name="clinicaltype"value="非临床"
							<c:if test="${dept.clinicaltype=='非临床' }">checked="checked"</c:if> />非临床
						</div>
					</td>
						
						<td align="right" style="border-top-width: 0px">科室类别：</td>
					<td style="border-top-width: 0px"><select id="depttype"
						name="depttype" class="form-control" style="width: 100px;">
							<option value=""></option>
							<c:forEach items="${depttypeList}" var="depttype">
								<option value="${depttype.contents}"
									<c:if test="${dept.depttype==null?depttype.isdefault == 'Y':depttype.contents==dept.depttype}">selected="selected"</c:if>>
									${depttype.contents}</option>
							</c:forEach>
					</select></td>
				</tr>
					<tr>
						<td align="right" style="border-top-width: 0px">科室名称：</td>
						<td style="border-top-width: 0px" colspan="3">
						<input type="text"   id=""  name="" class="form-control"></td>
						
						<td align="right" style="border-top-width: 0px">科室简称：</td>
						<td style="border-top-width: 0px">
						<input type="text"   id=""  name="" class="form-control" style="width: 100px;"></td>
					</tr>
					<tr>

					</tr>
					<tr>

					</tr>
					<tr>
	
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