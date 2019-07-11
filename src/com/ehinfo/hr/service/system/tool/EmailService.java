package com.ehinfo.hr.service.system.tool;

import com.ehinfo.hr.entity.system.tool.Email;
import com.ehinfo.hr.service.base.BaseService;

public interface EmailService extends BaseService<Email>{
	
	/**发送邮件（简单版）
     * @param o
     * @return
     */
	public boolean sentEmailSimple(Email o);

}
