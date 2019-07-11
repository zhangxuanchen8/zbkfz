package com.ehinfo.hr.repository.system.org;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes_WorkKind;
import com.ehinfo.hr.entity.system.dict.BaseDict;
import com.ehinfo.hr.entity.system.employee.BasDept;
import com.ehinfo.hr.entity.system.org.Org;
import com.ehinfo.hr.entity.system.resources.OrgResources;
import com.ehinfo.hr.entity.system.resources.RoleResources;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;
@JYBatis
public interface OrgDao extends BaseDao<Org>{
	/**
     * 通过ID获取机构
     * @return
     */
	public Org getOrg(@Param("id")String id);
	/**
     * 通过父ID子机构
     * @return
     */
	public List<Org> findAndChild(@Param("id")String id);
	/**
     * 获得新的不是权限的Id
     * @return
     */
	public List<String> getNotAuthoByOrg(@Param("orgId")String orgId,@Param("resIds")List<String> resIds,@Param("layer")String layer);
	/**
     * 获取机构树
     * @return
     */
	public List<ZNodes> getOrgTree(@Param("hosnum")String hosnum);
	/**
	 * 获取机构树
	 * @return
	 */
	public List<ZNodes> getOrgTreeWithUser(@Param("hosnum")String hosnum);
	/**
	 * 获取字典树
	 * @return
	 */
	public List<ZNodes> getDatadictTree(@Param("hosnum")String hosnum);
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
     * 权限列表包含按钮
     * @param orgId 组织Id
     * @return
     */
	public List<ZNodes> listAuthorized(@Param("orgId")String orgId,@Param("layer")String layer);	
	/**
     * 权限列表包含按钮（带门限）
     * @param pId   父Id
     * @param orgId 组织Id
     * @return
     */
	public List<ZNodes> listAuthorizedByTh(@Param("pId")String pId,@Param("orgId")String orgId,@Param("layer")String layer);
	 /**
     * 根据组织Id删除所有权限关系
     * @param orgId 组织Id
     * @return
     */
	public void delAuthorizedByOrgId(@Param("orgId")String orgId);
	 /**
     * 根据组织Id和显示层级删除权限关系
     * @param orgId 组织Id
     * @param layer 显示层级
     * @return
     */
	public void delAuthorizedByOrgIdAndLayer(@Param("orgId")String orgId,@Param("layer")String layer);
	 /**
     * 根据组织Id删除所有权限关系(批量)
     * @param os 组织Id集合
     * @return
     */
	public void deleteBatchAuthorizedByOrgId(List<Org> os);
	/**
     * 通过组织资源对象列表建立权限关系(批量插入)
     * @param  list 角色资源对象列表
     * @return
     */
	public void insertAuthorizedByOrgId(List<OrgResources> list);
	 /**
     * 根据组织删除权限
     * @param os 组织id和资源id集合
     * @return
     */
	public void delBatchAuthByOrg(List<OrgResources> or);
	 /**
     * 根据角色删除权限
     * @param os 角色id和资源id集合
     * @return
     */
	public void delBatchAuthByRole(List<RoleResources> rr);
	 /**
     * 根据登录用户的hosnum加载树
     * @param os 角色id和资源id集合
     * @return
     */
	public List<ZNodes> getPreOrgTree(String hosnum);
	/**
     * 获取工作分类树
     * @return
     */
	public List<ZNodes_WorkKind> getWorkKindTree(@Param("pid")String hosnum);
	
	public List<ZNodes_WorkKind> gethosname(@Param("pid")String hosnum);
	
	
	/**
	 * 获取工作分类树
	 * @return
	 */
	public int findmaxid();
	/**
	 * 获取退休审批人员
	 * @return
	 */
	public List<ZNodes> getEntryWithUser(@Param("hosnum")String hosnum);
	
	/**
	 * 检查hosnum
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
	
	public List<Org> findOrgByPage(Page<Org> page, @Param("param")Org org, @Param("deptid")String deptid, @Param("code")String code);
	/**
	 * 获取部门树
	 */
	public List<ZNodes> getDeptTree(@Param("hosnum")String hosnum);
	
	public List<ZNodes> getDeptTreeByid(@Param("hosnum")String hosnum);//2018/6/2
	
	public List<BaseDict> selectDict(@Param("nekey")int nekey);
	public String findmaxorganizeno();
	
	
	public List<ZNodes>   findByHosnum(@Param("hosnum")String hosnum);
	public List<Org> findByName(@Param("name")String name, @Param("hosnum")String hosnum);
	void updatePnum(Org o);
	public List<Org> findLeafDeptByHosnum(@Param("hosnum")String hosnum);
	public Org findHospitalByHosnum(@Param("hosnum")String hosnum);
	public List<Org> findDeptTreeByHosnum(@Param("hosnum")String hosnum);
	public List<BasDept> getHISTree(@Param("hosnum")String hosnum);
}
