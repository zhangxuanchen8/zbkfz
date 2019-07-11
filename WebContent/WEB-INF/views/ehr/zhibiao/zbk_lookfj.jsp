<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link href="<%=basePath%>city-picker/docs/css/city-picker.css" rel="stylesheet"/>

<script type="text/javascript" src="./jquery.js"></script>

<style type="text/css">
    .s1 {
        background-color: #7cc5e5;
    }

    .noselected > tbody > tr {
        background-color: transparent !important;
    }
</style>


<script type="text/javascript">
</script>
<div class="tabsContent" style="height: 250px;">
  <div class="tabs" currentIndex="${tabindex}" eventType="click">
    <!-- <div class="tabsHeader">
       <div class="tabsHeaderContent">
           <ul class="nav nav-tabs">
              <li><a href="javascript:;" id="index0tabs"><span>附件</span></a></li>
           </ul>
       </div>
    </div> -->

      <div>
        <div layouth="59">
           <div>
			<table id="" class="table table-bordered " style="width: 100%; height: 100%; text-align: center">
			  <thead>
				<tr>
					<th class="center">序号</th>
					<th class="center">文件名称</th>
                    <th class="center">操作</th>
				</tr>
			  </thead>
			  <tbody id="">
				<c:forEach items="${list_fj}" var="l" varStatus="stat">
					<tr id="">
					   <td  class="center">${stat.index + 1 }</td>
					   <td  class="center" title="${l.id }">${l.filename }</td>
					   <td>
					   	 <a href="<%=basePath%>backstage/tool/webuploader/readDef?id=${l.id }" class="btn btn-green btn-sm " target="dialog" width="800" height="600" rel="chakan"><i class="fa fa-search"></i></a>
						 <!-- <a href="javascript:;" class="btn btn-red j-del" target="ajaxTodo" callfun="table_del_callfun" title="确定要删除该行信息吗？">删</a> -->
					   </td>
					</tr>
				</c:forEach>
			  </tbody>
		    </table>
		 </div>
        </div>
      </div>
   </div>
</div>






