package com.ehinfo.hr.service.system.org;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes_WorkKind;
import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.employee.BasDept;
import com.ehinfo.hr.entity.system.org.Org;
import com.ehinfo.hr.service.base.BaseService;

public interface OrgService extends BaseService<Org>{

	/**
	 * 获取机构树
	 * @return
	 */
	public List<ZNodes> getOrgTree(String hosnum);
	/**
	 * 获取机构树
	 * @return
	 */
	public List<ZNodes> getOrgTreeWithUser(String hosnum);
	/**
	 * 获取字典树
	 * @return
	 */
	public List<ZNodes> getDatadictTree(String hosnum);
	/**
	 * 获取退休机构树
	 * @return
	 */
	public List<ZNodes> getEntryWithUser(String hosnum);
	/**
	 * 获取最大hosnum
	 * @return
	 */
	public String getmaxhosnum();
	/**
     * 获取上级机构树
     * @return
     */
	public List<ZNodes> getPreOrgTree();
	/**
	 * 获取工作分类树
	 * @return
	 */
	public List<ZNodes_WorkKind> getWorkKindTree(String pid);
	
	public List<ZNodes_WorkKind> gethosname(String pid);
	 /**
     * 权限列表包含按钮
     * @param orgId 组织Id
     * @param layer 显示层级
     * @return
     */
	public List<ZNodes> listAuthorized(String orgId,String layer);
	 /**
     * 根据角色Id保存权限列表
     * @param orgId 组织Id
     * @param auss 权限数组
     * @param layer 显示层级
     * @return
     */
	public void saveAuthorized(String orgId,String auss,String layer);
	/**
	 * 删除机构
	 * @return
	 */
	public int delOrg(Org o);
	/**
	 * 查出最大ID
	 * @return
	 */
	public int findmaxid();
	
	/**
	 * 检查hosnum是否存在
	 * @param o
	 * @return
	 */
	public Org checkHosnum(Org o);
	
	/**
	 * 获取字典sysname 
	 * @return
	 */
	public List<ZNodes> getDictSysname(@Param("hosnum")String hosnum); 
	/**
	 * 获取字典sysname子节点
	 * @param hosnum
	 * @param sysname
	 * @return
	 */
	public List<ZNodes> getDictSysnameNode(@Param("sysname")String sysname);
	
	public Page<Org> findOrgByPage(Page<Org> page, Org org, String deptid, String code);
	/**
	 * 获取机构树
	 * @return
	 */
	public List<ZNodes> getDeptTree(String hosnum);
	public List<ZNodes> getDeptTreeByid(String hosnum);
	
	public List<BaseDict> selectDict(int nekey);
	/**
	 * 找最大排序号加1
	 * @return
	 */
	public String findmaxorganizeno();
	
	public List<ZNodes>   findByHosnum(String hosnum);
	
	public List<Org> findByName(String name,String hosnum);
	
	void updatePnum(Org o);
	
	public List<Org> findLeafDeptByHosnum(String hosnum);
	public List<Org> findDeptTreeByHosnum(String hosnum);
	//查找his科室树
	public List<BasDept> getHISTree(String hosnum);
}
