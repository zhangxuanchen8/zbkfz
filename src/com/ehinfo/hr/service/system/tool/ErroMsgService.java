package com.ehinfo.hr.service.system.tool;

import java.util.List;

import com.ehinfo.hr.entity.system.tool.ErroMsg;
import com.ehinfo.hr.service.base.BaseService;

public interface ErroMsgService extends BaseService<ErroMsg>{
	/**
	 * 根据验证信息返回验证结果
	 * @param erlist
	 * @return
	 */
	public List<ErroMsg> getErroMsg(List<ErroMsg> erlist);
	/**
	 * 根据导入员工信息验证返回结果_部门
	 * @param erlist
	 * @return
	 */
	public List<ErroMsg> getErroMsg2(List<ErroMsg> erlist);
}
