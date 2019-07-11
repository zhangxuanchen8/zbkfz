<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" src="static/js/echarts.min.js"></script>
<script type="text/javascript">
/* var myChart10 = echarts.init(document.getElementById('main_hehe10'));
var zyname = '${nlmc}';
var arr = zyname.split(',');
var jsonString = "[";
for (var i = 0; i < arr.length; i++) {
	jsonString += "'" + arr[i] + "'";
	if (i == arr.length - 1) {
		jsonString += "]";
	} else {
		jsonString += ",";
	}
}
var json = eval('(' + jsonString + ')');

var num = '${nlnum}';
var nums = num.split(',');
var numsjson = "[";
for (var i = 0; i < nums.length; i++) {
	numsjson += nums[i];
	if (i == arr.length - 1) {
		numsjson += "]";
	} else {
		numsjson += ",";
	}
}
var jsonnum = eval('(' + numsjson + ')');
option = {
		title : {
			text : '专业分布',
			textStyle : {
				fontSize : 16
			}
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		
		grid : {
			left : '1%',
			right : '3%',
			bottom : '0%',
			containLabel : true
		},
		xAxis : [ {
			type : 'category',
			data : json
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '数量',
			type : 'bar',
			 barWidth: '10%',
			smooth:true,
			data : jsonnum
		} ]
	};
myChart10.setOption(option); */
</script>
<div class="pageContent">
	<div style="overflow-x: auto; overfolw-y: hidden" layoutH="10">
	<table class="table table-bordered table-hover table-striped table-top" >
	 <thead >
	 <th class="center" >序号</th> 
	 <th class="center" >姓名</th>
	 <th class="center" >从事专业</th>
	 <th class="center" >推荐任职职务</th> 
	 <th class="center" >出席人数</th>
	 <th class="center" >赞成人数</th>
	 <th class="center" >反对人数</th>
	 <th class="center" >赞成率</th>
	 <th class="center" >二次赞成人数</th>
	 <th class="center" >二次反对人数</th>
	 <th class="center" >评聘结果</th>   
	 </thead>
	 <tbody >
	  <c:forEach items="${list_zy}" var="l" varStatus="stat">
	  <tr>
	  <td class="center">${stat.index+1}</td>
	  <td class="center">${l.xm}</td>
	  <td class="center">${l.contents}</td>
	  <td class="center">${l.tjhrzzg}</td>
	  <td class="center">${l.cxrst}</td>
	  <td class="center">${l.tyrst}</td>
	  <td class="center">${l.fdrst}</td>
	  <td class="center"><fmt:formatNumber type="number"   maxFractionDigits="2" value="${l.zcl*100}" />%</td>
	  <td class="center">${l.eczc}</td>
	  <td class="center">${l.ecfd}</td>
	  <td class="center">
	  <c:if test="${l.sftg=='通过'}">${l.sftg}</c:if>
	   <c:if test="${l.sftg=='' ||l.sftg==null}">未通过</c:if>
	  </td>
	  </tr>
	  </c:forEach>
	 </tbody>
	</table>
		<!-- <div id="main_hehe10" style="height:400px;"></div> -->
	</div>
	</div>