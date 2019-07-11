//-------------------下拉选的值为数据库中的字段nekey，值为content----------------
function loadselectvc(id, nekey, selected) {
	
	$.ajax({
		async: false,
		type : 'POST',
		url : "backstage/baseDict/selectDictByNekey",
		data : {
			nekey : nekey
		},
		dataType : 'json',
		success : function(data) {
			var opts = "";
			opts = "";
			if(selected==''){
				opts += "<option value='' selected='selected'></option>";
			}
			$(eval(data)).each(function(n, v) {
				opts += "<option value='" + v.nevalue + "'";
				if (v.nevalue == selected || v.contents == selected) {
					opts += " selected='selected'"
				}
				opts += ">" + v.contents + "</option>";
			});
			document.getElementById(id).innerHTML = opts;
		}
	});
}
//-------------------下拉选的值和显示都是数据库中的字段content-----------------
function loadselectcc(id, nekey, selected) {
	$.ajax({
		async: false,
		type : 'POST',
		url : "backstage/baseDict/selectDictByNekey",
		data : {
			nekey : nekey
		},
		dataType : 'json',
		success : function(data) {
			var opts = "";
			opts = "";
			var flag = true;
			$(eval(data)).each(function(n, v) {
				opts += "<option value='" + v.contents + "'";
				if (v.nevalue == selected || v.contents == selected) {
					opts += " selected='selected'"
					flag=false;
				}
				if(v.contents=='Y'){
					 opts += ">是</option>";
				}else if(v.contents=='N'){
					 opts += ">否</option>";
				}else{
				opts += ">" + v.contents + "</option>";
				}
			});
			
			if(selected==''||flag){
				opts = "<option value='' selected='selected'></option>"+opts;
			}
			
			
			$("#"+id).html(opts); 
		}
	});
}

function loadselectee(id, nekey, option1, selected) {
	$.ajax({
		async: false,
		type : 'POST',
		url : 'backstage/baseDict/selectDictByNekey',
		data : {
			nekey : nekey,
		},
		dataType : 'json',
		success : function(data) {
			var opts = "";
			opts = "";
			if(selected==''){
				opts += "<option value='' selected='selected'></option>";
			}
			$(eval(data)).each(function(n, v) {
				if (v.option01 == option1||v.option02 == option1) {
					opts += "<option value='" + v.contents + "'";
					if (v.nevalue == selected || v.contents == selected) {
						opts += " selected='selected'"
					}
					opts += ">" + v.contents + "</option>";
				}
			});
			$("#"+id).html(opts);
		}
	});
}

function loadselectdd(id, nekey, option1,option2, selected) {

	$.ajax({
		async: false,
		type : 'POST',
		url : 'backstage/baseDict/selectDictByNekey',
		data : {
			nekey : nekey,
		},
		dataType : 'json',
		success : function(data) {
			var opts = "";
			opts = "";
			if(selected==''){
				opts += "<option value='' selected='selected'></option>";
			}
			$(eval(data)).each(function(n, v) {
				if ((v.option01+'' == option1+'')&&(v.option02+''==option2+'')) {
					opts += "<option value='" + v.contents + "'";
					if (v.nevalue == selected || v.contents == selected) {
						opts += " selected='selected'"
					}
					opts += ">" + v.contents + "</option>";
				}
			});
			
			
			
			$("#"+id).html(opts);
		}
	});
}


//-------------------导出----------------------
var tmpDown; //导出的二进制对象
function downloadExl(json,id, type) {
    var keyMap = [];//获取键
    for(k in json[0]) {
        keyMap.push(k);
    }
    var tmpdata = [];//用来保存转换好的json 
    json.map((v, i) => keyMap.map((k, j) => Object.assign({}, {
        v: v[k],
        position: (j > 25 ? getCharCol(j) : String.fromCharCode(65 + j)) + (i + 1)
    }))).reduce((prev, next) => prev.concat(next)).forEach((v, i) => tmpdata[v.position] = {
        v: v.v
    });
    var outputPos = Object.keys(tmpdata); //设置区域,比如表格从A1到D10
    var tmpWB = {
        SheetNames: ['mySheet'], //保存的表标题
        Sheets: {
            'mySheet': Object.assign({},
                tmpdata, //内容
                {
                    '!ref': outputPos[0] + ':' + outputPos[outputPos.length - 1] //设置填充区域
                })
        }
    };
    tmpDown = new Blob([s2ab(XLSX.write(tmpWB, 
        {bookType: (type == undefined ? 'xlsx':type),bookSST: false, type: 'binary'}//这里的数据是用来定义导出的格式类型
        ))], {
        type: ""
    }); //创建二进制对象写入转换好的字节流
    var href = URL.createObjectURL(tmpDown); //创建对象超链接
    document.getElementById(id).href = href; //绑定a标签
    document.getElementById(id).click(); //模拟点击实现下载
    setTimeout(function() { //延时释放
        URL.revokeObjectURL(tmpDown); //用URL.revokeObjectURL()来释放这个object URL
    }, 100);
}

function s2ab(s) { //字符串转字符流
    var buf = new ArrayBuffer(s.length);
    var view = new Uint8Array(buf);
    for(var i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
    return buf;
}
// 将指定的自然数转换为26进制表示。映射关系：[0-25] -> [A-Z]。
function getCharCol(n) {
    let temCol = '',
        s = '',
        m = 0
    while(n > 0) {
        m = n % 26 + 1
        s = String.fromCharCode(m + 64) + s
        n = (n - m) / 26
    }
    return s
}

//------------------------//判断对象是否为{}
function isEmptyObject(e) {  
    var t;  
    for (t in e)  
        return !1;  
    return !0  
} 



function getTableDate(headId,bodyId){
	var empList = [];
	var empContent ; 
	
	var tableObj = document.getElementById(headId);
    for (var i = 0; i < tableObj.rows.length; i++) {    //遍历Table的所有Row
    	empContent = new Object() ; 
    	
    	for (var j = 0; j < tableObj.rows[i].cells.length; j++) {   //遍历Row中的每一列
    		empContent[j] =  tableObj.rows[i].cells[j].innerText;
        }
    	if(!$.isEmptyObject(empContent)){
			empList.push(empContent);
		};
    }
    
    tableObj = document.getElementById(bodyId);
    for (var i = 0; i < tableObj.rows.length; i++) {    //遍历Table的所有Row
    	empContent = new Object() ; 
    	
    	for (var j = 0; j < tableObj.rows[i].cells.length; j++) {   //遍历Row中的每一列
    		empContent[j] =  tableObj.rows[i].cells[j].innerText;
        }
    	if(!$.isEmptyObject(empContent)){
			empList.push(empContent);
		};
    }
    return empList;
}





function expTable(headId,bodyId,loadId){
	downloadExl(getTableDate(headId,bodyId),loadId);
}



//------------------------------RBG(43,54,67)转#432435的方法
function RGBToHex(rgb){ 
	   var regexp = /[0-9]{0,3}/g;  
	   var re = rgb.match(regexp);// 利用正则表达式去掉多余的部分，将rgb中的数字提取
	   var hexColor = "#"; var hex = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'];  
	   for (var i = 0; i < re.length; i++) {
	        var r = null, c = re[i], l = c; 
	        var hexAr = [];
	        while (c > 16){  
	              r = c % 16;  
	              c = (c / 16) >> 0; 
	              hexAr.push(hex[r]);  
	         } hexAr.push(hex[c]);
	         if(l < 16&&l != ""){        
	             hexAr.push(0)
	         }
	       hexColor += hexAr.reverse().join(''); 
	    }  
	   // alert(hexColor)
	   return hexColor;  
	}


//--------------身份证号准确性的简单验证
function IdentityCodeValid(idCard) { 
	//15位和18位身份证号码的正则表达式  
    var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;  
    //如果通过该验证，说明身份证格式正确，但准确性还需计算  
    if (regIdCard.test(idCard)) {  
        if (idCard.length == 18) {  
            var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); //将前17位加权因子保存在数组里  
            var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); //这是除以11后，可能产生的11位余数、验证码，也保存成数组  
            var idCardWiSum = 0; //用来保存前17位各自乖以加权因子后的总和  
            for (var i = 0; i < 17; i++) {  
                idCardWiSum += idCard.substring(i, i + 1) * idCardWi[i];  
            }  
            var idCardMod = idCardWiSum % 11;//计算出校验码所在数组的位置  
            var idCardLast = idCard.substring(17);//得到最后一位身份证号码  
            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X  
            if (idCardMod == 2) {  
                if (idCardLast == "X" || idCardLast == "x") {  
                    return true;  
                    //alert("恭喜通过验证啦！");  
                } else {  
                    return false;  
                    //alert("身份证号码错误！");  
                }  
            } else {  
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码  
                if (idCardLast == idCardY[idCardMod]) {  
                    //alert("恭喜通过验证啦！");  
                    return true;  
                } else {  
                    return false;  
                    //alert("身份证号码错误！");  
                }  
            }  
        }  
    } else {  
        //alert("身份证格式不正确!");  
        return false;  
    }  
  }

var EH = EH||{};
EH = {
		Ckeck:{//判断是否是英文或数字,是返回true，不是返回false
			  isEnNum:function(str){if(/^[0-9a-zA-Z]+$/.test(str))return true;return false;},
			  //判断是否是英文,是返回true，不是返回false
			  isEn:function(str){if(/^[A-Za-z]+$/.test(str))return true;return false;},
			  //判断是否是电子邮箱,是返回true，不是返回false
			  isEmail:function(email){if(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email))return true;return false;},
			  //判断是否是日期,是返回true，不是返回false
			  isDate:function(date){if(date.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/))return true;return false;},		
			  //判断是否是日期时间,是返回true，不是返回false
			  isDatetime:function(datetime){if(datetime.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/))return true;return false;}	
			  
		}
}
