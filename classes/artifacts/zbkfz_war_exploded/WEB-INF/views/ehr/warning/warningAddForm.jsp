<%@ page contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">














</script>
<style>
.table>tbody>tr>td{
	border-top: 0px;
}

</style>
<iframe width=0 height=0 frameborder=0 name="hrong"
						style="display:none"></iframe>
<div class="pageContent">
    <form action="<%=basePath%>backstage/warning/add"  class="pageForm form-validate" method="post"  callback="dialogAjaxDone" noEnter>
   		 	
       		
       		<input type="hidden"   name="id" value="${theWarning.id}">
         
            <table class="table table-condensed noselected" width="100%" layouth="29">
                <tbody>
                    <tr>
                        <td class="col-md-6">
                            <label for="warningAdd_type" class="control-label x85" style="width:80px; ">预警类别：</label>
                            <input type="text" name="warn_type" value="${theWarning.warn_type}" id="warningAdd_type"  class="form-control  validate[required] required"   size="24"></td>
                        
                        </td>
                        <td class="col-md-6">
                            <label for="warningAdd_name" class="control-label x85" style="width:80px; ">预警名称：</label>
                            <input type="text" name="warn_name" value="${theWarning.warn_name}" id="warningAdd_name" class="form-control  validate[required] required"   size="24"></td>
                        
                        </td>
                        
                    </tr>
                    <tr>
	                    <td class="col-md-6">
							<label for="" class="control-label x85" style="width:80px; ">预警对象：</label>
							<select name="warn_forward" style="width:240px;"  class="form-control validate[required]">
								<c:if test="${theWarning.warn_forward=='单位'}">
									<option value="单位" selected="selected">单位</option>
									<option value="个人" >个人</option>
								</c:if>
								<c:if test="${theWarning.warn_forward=='个人'}">
									<option value="个人" selected="selected">个人</option>
									<option value="单位" >单位</option>
								</c:if>
								<c:if test="${theWarning.warn_forward==''||theWarning.warn_forward==null}">
									<option value="个人" >个人</option>
									<option value="单位" >单位</option>
								</c:if>
							</select>
	                 	</td>
	                 	<td class="col-md-6">
                            <label for="warningAdd_role" class="control-label x85" style="width:80px; ">所需角色：</label>
                            <input type="text" name="forward_role" value="${theWarning.forward_role}" id="warningAdd_role"  class="form-control  "   size="24" ></td>
                        
                        </td>
                        
	                 	
                     </tr>
                     <tr>
                        
                        <td class="col-md-6">
                            <label for="warningAdd_getCyc" class="control-label x85" style="width:80px; ">cronExpression：</label>
                            <input type="text" name="getwarn_cyc" value="${theWarning.getwarn_cyc}" id="warningAdd_getCyc"  class="form-control  "   size="24" ></td>
                        </td>
                        <td class="col-md-6">
                            <label for="warningAdd_cyc" class="control-label x85" style="width:80px; ">预警周期：</label>
                            <input type="text" name="warn_cyc" value="${theWarning.warn_cyc}" id="warningAdd_cyc"  class="form-control  "   size="24" ></td>
                        
                        </td>
                    </tr>
                     <tr>
                        
                        <td class="col-md-6">
                            <label for="warningAdd_num" class="control-label x85" style="width:80px; ">排序号</label>
                            <input type="text" name="num" value="${theWarning.num}" id="warningAdd_num"  class="form-control number "   size="24" ></td>
                        </td>
                        <td class="col-md-6">
                           
                        </td>
                    </tr>
                    
                   
                    <tr>
                       <td class="col-md-12" colspan="2">
                       		 <label for="warningAdd_html" class="control-label x85" style="width:80px;">预警html:</label>
                       		<textarea name="warn_html"  id="warningAdd_html" class="form-control autosize" cols="58" rows="3" placeholder="">${theWarning.warn_html}</textarea>
                       </td>
                    </tr> 
                    <tr>
                       <td class="col-md-12" colspan="2">
                       		 <label for="warningAdd_sql" class="control-label x85" style="width:80px;">预警sql:</label>
                       	<textarea name="getwarn_sql"  id="warningAdd_sql" class="form-control autosize" cols="58" rows="5" placeholder="">${theWarning.getwarn_sql}</textarea>
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