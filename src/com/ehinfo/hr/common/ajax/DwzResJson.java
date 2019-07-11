package com.ehinfo.hr.common.ajax;
import java.io.Serializable;
public class DwzResJson implements Serializable{
	private static final long serialVersionUID = 1L;
	private String statusCode = "200";
	private String message = "";				//返回的提示信息
	private String navTabId = "";				//服务器转回navTabId可以把那个navTab标记为reloadFlag=1, 下次切换到那个navTab时会重新载入内容. 
	private String callbackType = "";			//callbackType如果是closeCurrent就会关闭当前tab
	private String forwardUrl = "";				//只有callbackType="forward"时需要forwardUrl值

	private String rel="";
	private String confirmMsg="";
	
	public DwzResJson(String message, String navTabId, String callbackType) {
		this.message = message;
		this.navTabId = navTabId;
		this.callbackType = callbackType;	
	}
	public DwzResJson() {
	}

	public void  success() {
		this.message="操作成功";
	}

	public void success(String successMsg) {
		this.message=successMsg;
	}
	
	public void success(String successMsg,String navTabId) {
		this.message=successMsg;
		this.navTabId=navTabId;
	}

	public void error() {
		this.statusCode = "300";
		this.message = "操作失败";
	}

	public void error(String errorMsg) {
		this.statusCode = "300";
		this.message = errorMsg;
	}


	public void refresh(String refreshNavTabId) {
		this.navTabId = refreshNavTabId;
	}

	public void closeCurrentAndRefresh(String refreshNavTabId) {
		this.navTabId = refreshNavTabId;
		this.callbackType = "closeCurrent";
	}
	public void closeCurrentAndFoward(String refreshNavTabId, String fowardUrl) {
		this.navTabId = refreshNavTabId;
		this.callbackType = "closeCurrent";
		this.forwardUrl = fowardUrl;
	}
	
	public void closeCurrentAndRefresh(String refreshNavTabId, String message) {
		this.navTabId = refreshNavTabId;
		this.message = message;
		this.callbackType = "closeCurrent";
	}
	
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNavTabId() {
		return navTabId;
	}
	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}
	public String getCallbackType() {
		return callbackType;
	}
	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getConfirmMsg() {
		return confirmMsg;
	}
	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}
	@Override
	public String toString() {
		return "DwzResJson [statusCode=" + statusCode + ", message=" + message + ", navTabId=" + navTabId
				+ ", callbackType=" + callbackType + ", forwardUrl=" + forwardUrl + ", rel=" + rel + ", confirmMsg="
				+ confirmMsg + "]";
	}
	
	
	
	
	public String toJson(){
		return "{\"statusCode\":\"" + statusCode + "\",\"message\":\"" + message + "\",\"navTabId\":\"" + navTabId
				+ "\",\"callbackType\":\"" + callbackType + "\",\"forwardUrl\":\"" + forwardUrl + "\",\"rel\":\"" + rel + "\",\"confirmMsg\":\""
				+ confirmMsg + "\"}";
	}
	
}
