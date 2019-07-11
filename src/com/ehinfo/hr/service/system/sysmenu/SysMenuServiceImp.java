package com.ehinfo.hr.service.system.sysmenu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.entity.system.sysmenu.BaseMenu;
import com.ehinfo.hr.repository.system.sysmenu.SysMenuDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

@Service("SysMenuService")
public class SysMenuServiceImp extends BaseServiceImp<BaseMenu> implements SysMenuService{
	@Autowired
	private SysMenuDao dao;

	@Override
	public List<BaseMenu> system_menu_tree() {
		return dao.system_menu_tree();
	}

	@Override
	public Page<BaseMenu> searchbypage(Page<BaseMenu> page, BaseMenu bm) {
		page.setResults(dao.searchbypage(page,bm));
		return page;
	}

	@Override
	public List<BaseMenu> searchbyid(BaseMenu bm) {
		return dao.searchbyid(bm);
	}

	@Override
	public List<BaseMenu> searchbypid(BaseMenu bm) {
		return dao.searchbypid(bm);
	}

	@Override
	@Transactional
	public void addnew(BaseMenu bm) {
		if(dao.idhaveson(bm)>0){
			dao.delsinglemenu(bm);
		}
		 dao.addnew(bm);
	}

	@Override
	@Transactional
	public int delsysmenu(List<String> ids) {
			if(dao.haveson(ids)>0){
				return 0;
			}
		dao.delsysmenu(ids);
		dao.delrolemenus(ids);
		return 1;
	}
}
