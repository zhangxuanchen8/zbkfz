<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function jy(a){
	 var re = /^[1-9]+[0-9]*]*$/;
	if(!re.test(a)){
		alert("请输入数字！");
		return false;
	}else{
		var domInput = document.getElementById('score');
	    var b = domInput.value;
		if(parseInt(a)>parseInt(b)){
			alert("请输入合理的值");
			return false;
		}else{
			return true;
		}
	}
}
</script>
<div class="pageContent">
		<form action="<%=basePath%>backstage/zhibiao/savescore" id="hospitalform"  class="pageForm form-validate"  method="post" callback="dialogAjaxDone" noEnter >
		    <input type="hidden" name="supunit1" id="supunit1" value="${supunit1}"/>
		    <input type="hidden" name="supunit" id="supunit" value="${supunit}"/>
			<input type="hidden" name="zhibiao" id="zhibiao" value="${zhibiao}"/>
			<input type="hidden" name="id" id="id" value="${zhibiao.i_id}"/>
			<table class="table " width="100%"layoutH="38"> <!-- style="border-collapse: separate; border-spacing: 0px 5px;" -->   
				<tbody>
				    
				    	<tr>
				    	<td align="center" style="border-top-width: 0px" width="100px">项目名称：</td>
						<td style="border-top-width: 0px" >
						<input type="text"  id="item"  name="item"  disabled="disabled" class="form-control validate[required] required" style="width: 200px;border-color: red;" value="${zhibiao.item}"></td>
				    	<td align="center" style="border-top-width: 0px"width="30px">参考分值：</td>
						<td style="border-top-width: 0px " >
						<input type="text" id="score" name="score"  disabled="disabled" class="form-control validate[required] required" style="width: 200px;border-color: red;" value="${zhibiao.score}">
				  		</tr>
					<tr>
						<!-- <td align="center" style="border-top-width: 0px;">医院编码：</td>
						<td style="border-top-width: 0px">
						<input type="text"  id="hosnum"  name="hosnum" disabled="disabled" class="form-control" style="width: 200px;" value="医师类"></td> -->
						<td align="center" style="border-top-width: 0px"width="">评职称类别：</td>
						<td style="border-top-width: 0px" >
						<input type="text" id="category" name="category"  disabled="disabled"  class="form-control" style="width:  200px;" value="${zhibiao.category}">
						</td>
						<td align="center" style="border-top-width: 0px"width="">父节点：</td>
						<td style="border-top-width: 0px" >
						<input type="text"  readonly id="pid"  name="pid" class="form-control" style="width:  200px;" value="${supunit1}"></td>
					</tr>
					<tr>
						<td align="center" style="border-top-width: 0px"width="70px">合计类型：</td>
						<td style="border-top-width: 0px" >
						<input type="radio" name="is_max" disabled="disabled"  value="1"<c:if test="${zhibiao.is_max eq '1'}"> checked="checked"</c:if>>取最高分</input>
						<input type="radio" name="is_max" disabled="disabled"  value="0"<c:if test="${zhibiao.is_max eq '0'}"> checked="checked"</c:if>>取累加值</input>
						</td>
					    <td align="center" style="border-top-width: 0px"width="70px">是否启用：</td>
						<td style="border-top-width: 0px" width="100px">
						<input type="radio" disabled="disabled"  name="is_using" value="1"<c:if test="${zhibiao.is_using eq '1'}"> checked="checked"</c:if>>是</input>
						<input type="radio" disabled="disabled"  name="is_using" value="0"<c:if test="${zhibiao.is_using eq '0'}"> checked="checked"</c:if>>否</input>
						</td>
					</tr>
					<tr>
						<td align="center" style="border-top-width: 0px">分值：</td>
						<td style="border-top-width: 0px" width="70px">
						<input type="text"   id="finlscore"  name="finlscore" class="form-control validate[required] required" value="${zhibiao.finlscore}" style="width: 100%;border-color: red;" onblur="jy(this.value)"/></td>
						</tr>
					<tr>
						<td align="center" style="border-top-width: 0px"width="70px">单项评分说明：</td>
						<td style="border-top-width: 0px" width="100px" colspan="3">
						<textarea id="aqzy1" name="item_desc" class="form-control" rows="2" cols="45" disabled="disabled"  >
                                        </textarea>
                                        <script type="text/javascript">
                                          $("#aqzy1").text($("#aqzy2").val());
                                        </script>
                                        <input id="aqzy2" type="hidden" disabled="disabled"  class="form-control j-selectzTree" value="${zhibiao.item_desc}"
                                               size="20">
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