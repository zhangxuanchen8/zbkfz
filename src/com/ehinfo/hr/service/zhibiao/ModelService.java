package com.ehinfo.hr.service.zhibiao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes;
import com.ehinfo.hr.entity.zhibiao.Modelm;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku;
import com.ehinfo.hr.service.base.BaseService;

public interface ModelService extends BaseService<Modelm> {
	
   
	/**
     * 根据登录帐号查找loginName和BasUserType，正常只有一条数据
     * and a.isvalid='1' and a.BasUser_type='1'需要该条件
     * @param loginName
     * @return
     */
    public Modelm findFormatByLoginName(String loginName);
   
   
	public int preResetPwd(String opwd, String npwd, String qpwd, Modelm o);
	 /**
     * 删除人员
     * @param Modelm
     * @return
     */
	public void deleteBasUser(Modelm o);

	public void match(String model_m,String model_p,String unitid);
	 /**
     * 批量删除人员
     * @param chks 人员id 
     * @return
     */
	public void deleteBatchBasUser(String chks);

	@Override
	public Modelm findById(Modelm user);

	public String getAllRolesId(Modelm user);

	String insert1(Modelm o);

	public Page<Modelm> findByPageDWZ(Modelm user, Page<Modelm> page, List<Modelm> dept);

	public List<Modelm> findUserByRoleAndHos(String Role, String hosnum);


	public List<Modelm> findBasTree(String id);
	/**
	 * 获取model树
	 * @return
	 */
	public List<ZNodes> getModelTree(String hosnum,String nodecode);

	public List<ZNodes> getModelTree_withzbk(String hosnum,String nodecode,String pid);

	public List<ZNodes> getModelTree_withPersonalRecord(String hosnum,String nodecode);
	public List<ZNodes> getModelTree_withPersonalRecords(String hosnum,String nodecode);
	public List<ZNodes> getPersonalRecordTree(String hosnum,String nodecode,String xcszy);
	public List<Modelm> findbyyear(String hosnum,String year);

	public void updatesynergy(Modelm m);
	public List<ZNodes> getModelTree_withzbkdept(String hosnum, String nodecode);
	public List<Modelm> findbystoptime(Modelm m);
	public void updatescmodel(Modelm zbk);
	
	public List<ZNodes> getzbkPersonal(String hosnum,String pid,String id);
	
	public void modelguidang(String ids);
	
	public List<Modelm> findyearmodel(Modelm m);
	
	public List<Modelm> findyearmodels(Modelm m);
}
