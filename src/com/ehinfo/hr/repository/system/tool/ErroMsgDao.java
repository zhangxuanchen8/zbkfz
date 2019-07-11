package com.ehinfo.hr.repository.system.tool;

import java.util.List;

import com.ehinfo.hr.entity.system.tool.ErroMsg;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

@JYBatis
public interface ErroMsgDao extends BaseDao<ErroMsg>{
	/**
	 * 根据导入员工信息验证返回结果_员工
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
