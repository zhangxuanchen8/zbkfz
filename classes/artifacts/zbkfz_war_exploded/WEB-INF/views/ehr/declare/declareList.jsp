
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<style>
div.grid {
	border-left: 0px;
	border-right: 0px;
}
</style>

<script type="text/javascript">
$(function(){
	loadorgtree_declareList();//加载树
});
//ajax添加树形结构
function loadorgtree_declareList() {
	$.ajax({
		async:false,
		cache:false,
		url:"<%=basePath%>backstage/org/role/getOrgTree_dwz",
		type : "post",
		//datatype : "json",
		success : function(data) {
			$("#ztree_declareList").attr("nodes",data.toString());
		},
		error:function(data){
			alertMsg.error("数据加载错误");
		}
	});
}
//获取table内容
function getPid_declareList(event, treeId, treeNode){
	$("#dept_declareList").val(treeNode.id);//添加树id即部门id
	$("#declareListSubmit").click();//提交declareReady的隐藏表单
}
//选中的人员，提交选中人员
function addDeclareList(pid){
	var ArrPids = new Array();
	var pids = $("#candidate").val();
	//多个选中人使用“,”隔开的字符串
	if(pids == null||pids==""){
		$("#candidate").val(","+pid+",");
	}else{
		if(pids.indexOf(","+pid+",")>-1){
			pids=pids.replace(pid+",","");
			$("#candidate").val(pids);
		}else{
			$("#candidate").val(pids+pid+",");
		}
	}
	$("#dept_declareOne").val(pid);
	$("#declareListSubmit").click();//提交declareReady的隐藏表单
}

function BatchDeclaration(){
	alertMsg.confirm("您修改的资料未保存，请选择保存或取消！", {
		 okCall: function(){
			  var str=$("#candidate").val();
				console.log(str);
				$.ajax({
					url:'<%=basePath%>/backstage/declare/declareInfo',
				    type:'POST', 
				    data:{
				    	candidate:str
				    },
				    async:false,
				    success:function(data){
				    	alertMsg.correct("提交成功");
				    	window.location.href="<%=basePath%>/backstage/declare/index";
				    	//window.location.reload();//OK
				    	  //location.replace(location) 
				    	$("#candidate").val("");
						$("#declareListSubmit").click();//提交declareReady的隐藏表单
				    },
				    error:function(e){
				    	alertMsg.error('请联系管理员！', [options])
				    	}
				    	
				}); 
				/* $.ajax({
					type:"post",
					url:"http://192.168.31.86:8080/personalJsonResult.htm",
					data:{"xm":"路人甲", "xb":"男", "csny":"201805", "zzmm":"群众", "xgzdw":"金东区孝顺镇鞋塘卫生院", "xl":"本科", 
						"xszw":"学术职位", "xzzw":"行政职位", "hxbyh":"大学", "zgxl":"本科", "cjgzsj":"201805", 
						"cszygznx":"15", "xrzzgmc":"资格名称", "xrzzgqdsj":"201805", "xprzw":"临床", "xprzwsj":"201805", 
						"jcqk":"于xxxx年获得xxx", "yydj":"医院等级", "dwdj":"单位等级", "idcard":"140303197807180427", 
						"charter_code":"执业资格类别", "xlqd02":"201805", "txdz":"xx省xx市", "lxdh":"12345678901", 
						"zzzt":"在职", "xw":"学士", "yzbm":"333333", "unitid":"191821", "zcfw":"中医儿科学", "ks":"检验检查", 
						"photo":"", 
						"tjhrzzg":"副主任医师"},
					datatype:"json",
					error:function(XMLHttpRequest, textStatus, errorThrown){
						console.log(XMLHttpRequest);
						console.log(textStatus);
						console.log(errorThrown);
	       			},
					success:function(data){
						alert(data);
					}
				}); */
		 },
		 cancelCall : function() {}
		}
	);
	
	
}

</script>

<div class="col-md-2 panel panel-default"
	style="padding: 0px; height: 100%; overflow: auto; float: left; width: 16%;"
	layoutH="0">
	<ul id="ztree_declareList" class="ztree"
		attrs='{"onClick":"getPid_declareList","expandAll":true}' nodes=''
		style="width: 400px;">
	</ul>
</div>

<!-- 添加table -->
<div class="col-md-10 " style="padding: 0px;" id="readyDeclare">
	<jsp:include page="declareReady.jsp"></jsp:include>
</div>





