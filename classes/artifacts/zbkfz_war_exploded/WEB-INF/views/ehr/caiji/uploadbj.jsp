<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link href="<%=basePath%>city-picker/docs/css/city-picker.css" rel="stylesheet"/>

<script type="text/javascript" src="./jquery.js"></script>
<script src="<%=basePath%>city-picker/docs/js/city-picker.data.js"></script>
<script src="<%=basePath%>city-picker/docs/js/city-picker.js"></script>
<script type="text/javascript">
function gettime(s){
	
	if(s==2){
	document.getElementById("zhouqiday11").style.display="none";
	document.getElementById("dayname11").style.display="none";
	}else{
		document.getElementById("zhouqiday11").style.display="inline";
		document.getElementById("dayname11").style.display="inline";
	}
}
$(function(){
	debugger;
	var s =document.getElementById("zhouqi11").checked;
	if(s){
		document.getElementById("zhouqiday11").style.display="none";
		document.getElementById("dayname11").style.display="none";
	}
}
);
</script>
<form action="<%=basePath %>backstage/shujuljdy/uploadsave" id="add_ress" class="pageForm form-validate" method="post" callback="dialogAjaxDoneds" noEnter>
	<input type="hidden" name="id" value="${uppz.id}"> 
	<div class="pageFormContent" layouth="26">
					<p><label for="caijimc"
						class="control-label" style="width: 80px;">上传名称：</label>
						<input type="text" value="${uppz.caijimc }"  name="caijimc" style="width: 180px" class="form-control "/>
<%-- 						<select name="caijimc" id="caijimc" style="width: 180px" class="form-control ">
                            <option value="0">==请选择==</option>
                            <c:forEach items="${zblist}" var="l" varStatus="stat">
                                <option value="${l.zbkname}" <c:if test="${l.zbkname==uppz.caijimc}">selected='selected'</c:if> > ${l.zbkname}</option>
                            </c:forEach>
                        </select> --%>
                     </p>
                     <p><label for="date_sources"
						class="control-label" style="width: 80px;">数据来源：</label>
						<select name="date_sources" id="date_sources11" style="width: 180px" class="form-control ">
                            <option value="0">==请选择==</option>
                            <c:forEach items="${sjlist}" var="l" varStatus="stat">
                                <option value="${l.id}" <c:if test="${l.id==uppz.date_sources}">selected='selected'</c:if> > ${l.s_name}</option>
                            </c:forEach>
                        </select>
                     </p>
                     <p><label for="date_sources"
						class="control-label" style="width: 80px;" id="zhouqi12" name="zhouqi">时间类型：</label>
						<input type="radio" value="0"  name="zhouqi" onclick="gettime(1)" <c:if test="${uppz.zhouqi=='0'}">checked='checked'</c:if>>月
						<input type="radio" value="1" id="zhouqi11" name="zhouqi" onclick="gettime(2)" <c:if test="${uppz.zhouqi!='0'}">checked='checked'</c:if>>天
                     </p>   
					<p><label for="zhouqi"
						class="control-label" style="width: 80px;" id="dayname11">日：</label>
						<select name="zhouqiday" id="zhouqiday11" style="width: 60px" class="form-control ">
						<option value="0">0</option>
						<option value="1" <c:if test="${uppz.zhouqiday=='1'}">selected='selected'</c:if>>1</option>
						<option value="2" <c:if test="${uppz.zhouqiday=='2'}">selected='selected'</c:if>>2</option>
						<option value="3" <c:if test="${uppz.zhouqiday=='3'}">selected='selected'</c:if>>3</option>
						<option value="4" <c:if test="${uppz.zhouqiday=='4'}">selected='selected'</c:if>>4</option>
						<option value="5" <c:if test="${uppz.zhouqiday=='5'}">selected='selected'</c:if>>5</option>
						<option value="6" <c:if test="${uppz.zhouqiday=='6'}">selected='selected'</c:if>>6</option>
						<option value="7" <c:if test="${uppz.zhouqiday=='7'}">selected='selected'</c:if>>7</option>
						<option value="8" <c:if test="${uppz.zhouqiday=='8'}">selected='selected'</c:if>>8</option>
						<option value="9" <c:if test="${uppz.zhouqiday=='9'}">selected='selected'</c:if>>9</option>
						<option value="10" <c:if test="${uppz.zhouqiday=='10'}">selected='selected'</c:if>>10</option>
						<option value="11" <c:if test="${uppz.zhouqiday=='11'}">selected='selected'</c:if>>11</option>
						<option value="12" <c:if test="${uppz.zhouqiday=='12'}">selected='selected'</c:if>>12</option>
						<option value="13" <c:if test="${uppz.zhouqiday=='13'}">selected='selected'</c:if>>13</option>
						<option value="14" <c:if test="${uppz.zhouqiday=='14'}">selected='selected'</c:if>>14</option>
						<option value="15" <c:if test="${uppz.zhouqiday=='15'}">selected='selected'</c:if>>15</option>
						<option value="16" <c:if test="${uppz.zhouqiday=='16'}">selected='selected'</c:if>>16</option>
						<option value="17" <c:if test="${uppz.zhouqiday=='17'}">selected='selected'</c:if>>17</option>
						<option value="18" <c:if test="${uppz.zhouqiday=='18'}">selected='selected'</c:if>>18</option>
						<option value="19" <c:if test="${uppz.zhouqiday=='19'}">selected='selected'</c:if>>19</option>
						<option value="20" <c:if test="${uppz.zhouqiday=='20'}">selected='selected'</c:if>>20</option>
						<option value="21" <c:if test="${uppz.zhouqiday=='21'}">selected='selected'</c:if>>21</option>
						<option value="22" <c:if test="${uppz.zhouqiday=='22'}">selected='selected'</c:if>>22</option>
						<option value="23" <c:if test="${uppz.zhouqiday=='23'}">selected='selected'</c:if>>23</option>
						<option value="24" <c:if test="${uppz.zhouqiday=='24'}">selected='selected'</c:if>>24</option>
						<option value="25" <c:if test="${uppz.zhouqiday=='25'}">selected='selected'</c:if>>25</option>
						<option value="26" <c:if test="${uppz.zhouqiday=='26'}">selected='selected'</c:if>>26</option>
						<option value="27" <c:if test="${uppz.zhouqiday=='27'}">selected='selected'</c:if>>27</option>
						<option value="28" <c:if test="${uppz.zhouqiday=='28'}">selected='selected'</c:if>>28</option>
						</select>
						<label for="zhouqi"
						class="control-label" style="width: 60px;">时：</label>
						<select name="zhouqixs" id="zhouqixs" style="width: 60px" class="form-control ">
						<option value="0" <c:if test="${uppz.zhouqixs=='0'}">selected='selected'</c:if>>0</option>
						<option value="1" <c:if test="${uppz.zhouqixs=='1'}">selected='selected'</c:if>>1</option>
						<option value="2" <c:if test="${uppz.zhouqixs=='2'}">selected='selected'</c:if>>2</option>
						<option value="3" <c:if test="${uppz.zhouqixs=='3'}">selected='selected'</c:if>>3</option>
						<option value="4" <c:if test="${uppz.zhouqixs=='4'}">selected='selected'</c:if>>4</option>
						<option value="5" <c:if test="${uppz.zhouqixs=='5'}">selected='selected'</c:if>>5</option>
						<option value="6" <c:if test="${uppz.zhouqixs=='6'}">selected='selected'</c:if>>6</option>
						<option value="7" <c:if test="${uppz.zhouqixs=='7'}">selected='selected'</c:if>>7</option>
						<option value="8" <c:if test="${uppz.zhouqixs=='8'}">selected='selected'</c:if>>8</option>
						<option value="9" <c:if test="${uppz.zhouqixs=='9'}">selected='selected'</c:if>>9</option>
						<option value="10" <c:if test="${uppz.zhouqixs=='10'}">selected='selected'</c:if>>10</option>
						<option value="11" <c:if test="${uppz.zhouqixs=='11'}">selected='selected'</c:if>>11</option>
						<option value="12" <c:if test="${uppz.zhouqixs=='12'}">selected='selected'</c:if>>12</option>
						<option value="13" <c:if test="${uppz.zhouqixs=='13'}">selected='selected'</c:if>>13</option>
						<option value="14" <c:if test="${uppz.zhouqixs=='14'}">selected='selected'</c:if>>14</option>
						<option value="15" <c:if test="${uppz.zhouqixs=='15'}">selected='selected'</c:if>>15</option>
						<option value="16" <c:if test="${uppz.zhouqixs=='16'}">selected='selected'</c:if>>16</option>
						<option value="17" <c:if test="${uppz.zhouqixs=='17'}">selected='selected'</c:if>>17</option>
						<option value="18" <c:if test="${uppz.zhouqixs=='18'}">selected='selected'</c:if>>18</option>
						<option value="19" <c:if test="${uppz.zhouqixs=='19'}">selected='selected'</c:if>>19</option>
						<option value="20" <c:if test="${uppz.zhouqixs=='20'}">selected='selected'</c:if>>20</option>
						<option value="21" <c:if test="${uppz.zhouqixs=='21'}">selected='selected'</c:if>>21</option>
						<option value="22" <c:if test="${uppz.zhouqixs=='22'}">selected='selected'</c:if>>22</option>
						<option value="23" <c:if test="${uppz.zhouqixs=='23'}">selected='selected'</c:if>>23</option>
						<option value="24" <c:if test="${uppz.zhouqixs=='24'}">selected='selected'</c:if>>24</option>
						</select>
							</p>
					<%-- <p><label for="table_name"
						class="control-label" style="width: 80px;">字段名：</label>
						<input type="text" id="table_name" name="table_name" class="form-control " 
							size="18" style="width: 180px;" value="${uppz.table_name}"/></p> --%>
					
                    <p><label for="mx_sql"
						class="control-label" style="width: 120px;">查询sql明细：</label>
									<textarea id="aqzy3" name="mx_sql" class="form-control" rows="5" cols="55" >
                                        </textarea>
                                        <script type="text/javascript">
                                          $("#aqzy3").text($("#aqzy4").val());
                                        </script>
                                        <input id="aqzy4" type="hidden" class="form-control j-selectzTree" value="${uppz.mx_sql}" disabled="disabled" 
                                               size="28">
                    </p>
                    <p></p>
                    <p></p>
                    <p></p>
                    <p></p>
                    <p></p>
                	<div>
                	<span size="28" style="color:#C5C1AA"></span>
                	</div>
                    <p><label for="caijibz"
						class="control-label" style="width: 120px;">插入sql明细：</label>
									<textarea id="aqzy5" name="caijibz" class="form-control" rows="5" cols="55" >
                                        </textarea>
                                        <script type="text/javascript">
                                          $("#aqzy5").text($("#aqzy6").val());
                                        </script>
                                        <input id="aqzy6" type="hidden" class="form-control j-selectzTree" value="${uppz.caijibz }" disabled="disabled" 
                                               size="28">
                    </p>
	</div>	
	<div class="formBar">
		<ul>
			<li><button type="submit" class="btn btn-default btn-sm">保存</button></li>
			<li><button type="button" class="btn btn-close btn-sm">关闭</button></li>
		</ul>
	</div>
</form>
