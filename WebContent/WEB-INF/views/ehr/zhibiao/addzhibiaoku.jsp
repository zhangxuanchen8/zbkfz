<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
// var treeObj;
// $(function(){
// 	loadAddzhibiaoTree("ZhiBiaoTree");
// });
//加载组织结构树
// function loadAddzhibiaoTree(id){
// 	$.ajax({
// 		type : 'POST',
<%-- 		url:"<%=basePath%>/backstage/zhibiao/getZhiBiaoTree", --%>
// /* 			data:{hosnum1:'${dept.hosnum}',flag:'Y'}, */
// 		type : "POST",
// 		dataType : "json",
// 		success : function(data) {
// 						$.fn.zTree.init($("#"+id), {
// 							view : {
// 								selectedMulti : false,
// 								fontCss : {
// 									color : "#393939"
// 								}
// 							},
// // 							check: {
// // 								enable: true,
// // 								chkStyle: "checkbox"
// // 								chkboxType: { "Y": "ps", "N": "ps" }
// // 							},
// 							data : {
// 								simpleData : {
// 									enable : true
// 								}
// 							},
// 							callback : {
// 								onClick : clickZhiBiaoForm,
// 								beforeClick : zTreeBeforeClick
// 							}
// 						}, data.obj.list);
// 					    treeObj = $.fn.zTree.getZTreeObj(id);
// 						var nodes = treeObj.getNodes();
						
// 						if("${zhibiao_dept_id}"!=null&&"${zhibiao_dept_id}"!=""){
// 							var node1 = treeObj.getNodeByParam("id","${zhibiao_dept_id}");
// 							//treeObj.expandNode(node1.getParentNode,true);
// 							treeObj.selectNode(node1);
// 						}else{
// 							treeObj.expandNode(nodes[0],true);
// 						}
// 					}
// 				});
// 	}
// function clickZhiBiaoForm(event,treeId,treeNode){
// 	var id = treeNode.id;
// 	$("#zhibiao_dept_id").val(id);
// }

// function zTreeBeforeClick(treeId, treeNode, clickFlag) {
// 	var treeObj = $.fn.zTree.getZTreeObj(treeId);
// 	var sNodes = treeObj.getNodes();
// };

</script>
<div class="pageContent">
		<form action="<%=basePath%>backstage/zhibiao/save" id="hospitalform"  class="pageForm form-validate"  method="post" callback="dialogAjaxDone" noEnter >
		    <input type="hidden" name="supunit1" id="supunit1" value="${supunit1}"/>
		    <input type="hidden" name="supunit" id="supunit" value="${supunit}"/>
		    <input type="hidden" name="category1" id="category1" value="${category}"/>
			<input type="hidden" name="zhibiao" id="zhibiao" value="${zhibiao}"/>
			<input type="hidden" name="id" id="id" value="${zhibiao.i_id}"/>
			<input type="hidden" name="zhibiao_dept_id" id="zhibiao_dept_id" value="${zhibiao_dept_id}"/>
			<table class="table " width="100%"layoutH="38">   
				<tbody>
				    
				    	<tr>
				    	<td align="center" style="border-top-width: 0px" width="100px">项目名称：</td>
						<td style="border-top-width: 0px" >
						<input type="text"  id="item"  name="item" class="form-control validate[required] required" style="width: 200px;border-color: red;" value="${zhibiao.item}"></td>
				    	<td align="center" style="border-top-width: 0px"width="30px">评分：</td>
						<td style="border-top-width: 0px " >
						<input type="text" id="score" name="score" class="form-control validate[required] required" style="width: 200px;border-color: red;"  value="${zhibiao.score}">
				  		</tr>
					<tr>
						<td align="center" style="border-top-width: 0px"width="">类别：</td>
						<td style="border-top-width: 0px" >
						<input type="text" id="category" name="category"  disabled="disabled"  class="form-control" style="width:  200px;" value="${category}">
						</td>
						<td align="center" style="border-top-width: 0px"width="">父节点：</td>
						<td style="border-top-width: 0px" >
						<input type="text"  readonly id="pid"  name="pid" class="form-control" style="width:  200px;" value="${supunit1}"></td>
					</tr>
					<tr>
						<td align="center" style="border-top-width: 0px"width="70px">合计类型：</td>
						<td style="border-top-width: 0px" >
						<input type="radio" name="is_max" value="1"<c:if test="${zhibiao.is_max eq '1' }"> checked="checked"</c:if>>取最高分</input>
						<input type="radio" name="is_max" value="0" <c:if test="${zhibiao.is_max eq '0' || zhibiao.is_max eq null}"> checked="checked"</c:if>>取累加值</input>
						</td>
					    <td align="center" style="border-top-width: 0px"width="70px">是否启用：</td>
						<td style="border-top-width: 0px" width="100px">
						<input type="radio" name="is_using" value="1" <c:if test="${zhibiao.is_using eq '1' || zhibiao.is_using eq null}"> checked="checked"</c:if>>是</input>
						<input type="radio" name="is_using" value="0"<c:if test="${zhibiao.is_using eq '0' }"> checked="checked"</c:if>>否</input>
						</td>
					</tr>
					<tr>
						<td align="center" style="border-top-width: 0px">末级节点：</td>
						<td style="border-top-width: 0px" width="70px">
						<input type="radio" name="last" value="1"<c:if test="${zhibiao.last eq '1'}"> checked="checked"</c:if>>是</input>
						<input type="radio" name="last" value="0" <c:if test="${zhibiao.last eq '0' || zhibiao.last eq null}"> checked="checked"</c:if>>否</input>
						</td>
						<td align="center" style="border-top-width: 0px">序号：</td>
						<td style="border-top-width: 0px" width="70px">
						<input type="text"  id="xuhao"  name="xuhao" class="form-control validate[required] required" style="width:  200px;" value="${zhibiao.xuhao}"></td>
					</tr>
					<tr>
						<td align="center" style="border-top-width: 0px">最高限分：</td>
						<td style="border-top-width: 0px" width="70px">
						<input type="text"  name="maxscore" class="form-control " style="width:  200px;" onkeyup="this.value=this.value.replace(/[^\-?\d.]/g,'')"  value="${zhibiao.maxscore}"></td>
						<td align="center" style="border-top-width: 0px">基础分：</td>
						<td style="border-top-width: 0px" width="70px">
						<input type="text"  name="is_sub" class="form-control " style="width:  200px;" onkeyup="this.value=this.value.replace(/[^\-?\d.]/g,'')"  value="${zhibiao.is_sub}"></td>
					
					</tr>
					<tr>
						<td align="center" style="border-top-width: 0px"width="70px">单项评分说明：</td>
						<td style="border-top-width: 0px" width="100px" colspan="3">
						<textarea id="aqzy1" name="item_desc" class="form-control" rows="2" cols="45" >
                                        </textarea>
                                        <script type="text/javascript">
                                          $("#aqzy1").text($("#aqzy2").val());
                                        </script>
                                        <input id="aqzy2" type="hidden" class="form-control j-selectzTree" value="${zhibiao.item_desc}"
                                               size="20">
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