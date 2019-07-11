
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<div class="pageHeader">
    <form id="pagerForm" onsubmit="return divSearch(this, 'dictListRef');" action="backstage/baseDict/getdDictDetails?relFlag=Y" method="post">
    	<input type="hidden" id =nekey name="nekey" value="${columName.nekey}">
    	<input type="hidden" id ="hosnum" name="hosnum" value="${hosnum}">
    	<input type="hidden" id ="sysname" name="sysname" value="${columName.sysname}">
    	<input type="hidden" id ="dictid" name="dictid" value="${columName.dictid}">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="numPerPage" value="${numPerPage}">
        <div class="searchBar">
		        <ul class="searchContent">
					<li><label>查询内容：</label><input type="text" id="contents" name="contents" value="${contents}" class="form-control" size="30" /></li>
					<li><button class="btn btn-default btn-sm" id="dictSearch">查询</button></li>
					<li><a href="javascript:void(0)" class="btn btn-orange btn-sm" onclick="cleanquery()">清空查询</a></li>
					<li class="pull-right">
                    	<ul>
                  			<li><a href="javascript:void(0)" class="btn btn-default btn-sm" onclick="saveAll()">保存</a></li>
                    	</ul>
                	</li>
				</ul>
		</div>
    </form>
</div>
<div class="pageContent" >
	<div style="width:100%;overflow-x:auto;overfolw-y:auto">
	<form id="dictList" class="pageForm form-validate">
		<table id="baseTable_colum" class="table table-bordered table-hover table-striped table-top itemDetail">
			<thead><tr><th class="center"><c:if test="${columName.contents!=null}">
					  ${columName.nekey}_${columName.contents}(${columName.stdinfo})
				</c:if></th></tr></thead>
		</table>
		<table id="tabledict" class="table table-bordered table-hover table-striped table-top itemDetail" layoutH="95" rel="dictListRef">
			<thead>
				<tr>
					<th type="text" name="dictid" width="38" class="center" defaultVal="" fieldAttrs="{'disabled':'disabled'}"></th>
					<th type="text" name="nevalue" class="center" fieldClass="validate[required]" fieldAttrs="{'id':'nevalue_#index#','onChange':'checknevalue(this.id)'}">项目序号</th>
					<th type="text" name="contents" class="center" fieldClass="validate[required]" fieldAttrs="{'id':'contents_#index#'}" >${columName.contents}</th>
					<c:forEach items="${bdoptionlist}" var="bdcolum" varStatus="stat">
						<th type="text" id="contents" name="${bdcolum.property}" class="center" fieldClass="validate[required]" fieldAttrs="{'id':'${bdcolum.property}_#index#'}">${bdcolum.propertyvalue}</th>
					</c:forEach>
					<th type="del" style="width:50px;" fieldAttrs="{'title':'确定要删除行吗？', 'value':'删除'}">
                        <input type="text" value="1" class="form-control sm2 num-add" title="待添加的行数">
                        <a href="javascript:;" class="j-add" title="添加行"><i class="fa fa-plus"></i></a>
                    </th>
				</tr>
			</thead>
			<tbody id="dict_list">
				<c:forEach items="${list}" var="l" varStatus="stat">
                    <c:if test="${l.hosnum!=hosnum}">
                        <tr class="" data-hos="0">
                            <td style='display:none'><input type="hidden" name="dictid" value="${l.dictid }"></td>
                            <td align="center"><input style="text-align:center;color: #576a0e;width:100%;" readonly name="index"
                                                      class="form-control" disabled="disabled" value="${stat.count }">
                            </td>
                            <td align="center"><input style="text-align:center;color: #576a0e;width:100%;" readonly
                                                      id="nevalue_${stat.count-1 }" name="nevalue"
                                                      onChange="checknevalue(this.id)"
                                                      class="form-control validate[required]" value="${l.nevalue }">
                            </td>
                            <td align="center"><input style="text-align:center;color: #576a0e;width:100%;" readonly
                                                      id="contents_${stat.count-1 }" name="contents"
                                                      class="form-control validate[required]" value="${l.contents }">
                            </td>
                            <c:forEach items="${bdoptionlist}" var="bdcolum" varStatus="stat">
                                <c:if test="${bdcolum.property!=null}">
                                    <td><input style="text-align:center;color: #576a0e;width:100%;" readonly id="${bdcolum.property}_${stat.count-1 }"
                                               name="${bdcolum.property}" class="form-control validate[required]"
                                               value="${l[bdcolum.property] }"></td>
                                </c:if>

                            </c:forEach>
                            <td>

                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${l.hosnum==hosnum}">
                        <tr class="readonly">
                            <td style='display:none'><input type="hidden" name="dictid" value="${l.dictid }"></td>
                            <td align="center"><input style="text-align:center;width:100%;" id="" name="index"
                                                      class="form-control" disabled="disabled" value="${stat.count }">
                            </td>
                            <td align="center"><input style="text-align:center;width:100%;"
                                                      id="nevalue_${stat.count-1 }" name="nevalue"
                                                      onChange="checknevalue(this.id)"
                                                      class="form-control validate[required]" value="${l.nevalue }">
                            </td>
                            <td align="center"><input style="text-align:center;width:100%;"
                                                      id="contents_${stat.count-1 }" name="contents"
                                                      class="form-control validate[required]" value="${l.contents }">
                            </td>
                            <c:forEach items="${bdoptionlist}" var="bdcolum" varStatus="stat">
                                <c:if test="${bdcolum.property!=null}">
                                    <td><input style="text-align:center;width:100%;" readonly id="${bdcolum.property}_${stat.count-1 }"
                                               name="${bdcolum.property}" class="form-control validate[required]"
                                               value="${l[bdcolum.property] }"></td>
                            </c:if>
                                </c:forEach>

                            <td>
                                <a href="backstage/baseDict/deleteByDictId?dictid=${l.dictid }"
                                   class="btn btn-red btn-sm j-del" target="ajaxTodo" callfun="table_del_callfun"
                                   title="确定要删除该行信息吗？">删除</a>
                            </td>
                        </tr>
                    </c:if>
				</c:forEach>
			</tbody>
		</table>
		</form>
		</div>
		<div class="panelBar">
        <div class="pages">
            <span>每页&nbsp;</span>
            <span class="sel">
                <select class="selectpicker show-tick dropup" data-style="btn-default btn-sel xs" data-width="auto" name="numPerPage" onchange="navTabPageBreak({pageNum:1,numPerPage:this.value},'dictListRef')">
                    <option value="10" <c:if test="${numPerPage == '10' }">selected='selected'</c:if>>10</option>
                    <option value="20" <c:if test="${numPerPage == '20' }">selected='selected'</c:if>>20</option>
                    <option value="30" <c:if test="${numPerPage == '30' }">selected='selected'</c:if>>30</option>
                    <option value="40" <c:if test="${numPerPage == '40' }">selected='selected'</c:if>>40</option>
                </select>
            </span>
            <span>&nbsp;条，共 ${totalcount } 条</span>
        </div>
        <div class="pagination-box" targettype="navTab" rel="dictListRef"  totalcount="${totalcount }" numperpage="${numPerPage}" pagenumshown="10" currentpage="${pageNum}"  >
        </div>
    </div>
    </div>