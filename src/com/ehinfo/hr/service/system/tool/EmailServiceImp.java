package com.ehinfo.hr.service.system.tool;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.email.MailUtil;
import com.ehinfo.hr.entity.system.tool.Email;
import com.ehinfo.hr.service.base.BaseServiceImp;
@Service("EmailService")
public class EmailServiceImp extends BaseServiceImp<Email> implements EmailService{

	@Transactional
	public boolean sentEmailSimple(Email o) {
		boolean res=MailUtil.send(MailUtil.setConfig(Const.EMAIL_CONFIG),o.getToList(),o.getSubject(),o.getBody());
		if(res){
			o.setCreateTime(new Date());
			baseDao.insert(o);;
		}
		return res;
	}


}
