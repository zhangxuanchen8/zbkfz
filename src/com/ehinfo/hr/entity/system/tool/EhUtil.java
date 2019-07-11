package com.ehinfo.hr.entity.system.tool;
import com.ehinfo.hr.common.utils.base.Tools;
public class EhUtil {
    public static String createcallbackjson(String status,String message,String navTabId,String rel, String callbacktype, String forwardUrl, String confirMsg, String tabindex){
    	String callbackjson = "";
    	if(Tools.isEmpty(message)){//返回的message
    		message = "";
    	}
    	if(Tools.isEmpty(navTabId)){//需要刷新的navTabId也就是打开窗口的a标签的rel属性
    		navTabId = "";
    	}
    	if(Tools.isEmpty(rel)){
    		rel = "";
    	}				
    	if(Tools.isEmpty(callbacktype)){//如果返回closeCurrent则会关闭当前会话窗口
    		callbacktype = "";
    	}
    	if(Tools.isEmpty(forwardUrl)){
    		forwardUrl = "";
    	}
    	if(Tools.isEmpty(confirMsg)){
    		confirMsg = "";
    	}
    	if(!Tools.isEmpty(tabindex)){
    	/*这个是我自己加的 , 目的是用来实现二级dialog保存后刷新一级dialog , 同时定位到一级dialog中的某个tab页面
    	  在dwz.ajax.js中的function dialogAjaxDone(json)方法中做了判断
    	  如果json.tabindex不为空 , 则进入刷新dialog的方法	
    	*/
    		tabindex = ",\"tabindex\":\""+tabindex+"\"";
    	}else{
    		tabindex = ",\"tabindex\":\"\"";
    	}
    	callbackjson = "{\"statusCode\":\""+status+"\",\"message\":\""+message+"\",\"navTabId\":\""+navTabId+"\",\"rel\":\""+rel+"\",\"callbackType\":\""+callbacktype+"\",\"forwardUrl\":\""+forwardUrl+"\",\"confirmMsg\":\""+confirMsg+"\""+tabindex+"}";
    	return callbackjson;

    }
}
