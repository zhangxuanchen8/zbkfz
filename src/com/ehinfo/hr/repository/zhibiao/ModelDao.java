package com.ehinfo.hr.repository.zhibiao;

import org.apache.ibatis.annotations.Param;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes;
import com.ehinfo.hr.entity.zhibiao.Modelm;
import com.ehinfo.hr.repository.base.BaseDao;
import com.ehinfo.hr.repository.base.JYBatis;

import java.util.List;

/**
 * @author LHB
 * @date 2018/10/11 10:16
 */
@JYBatis
public interface ModelDao extends BaseDao<Modelm> {
    public Modelm findFormatByLoginName(String loginName);

    public void insertRole(Modelm model);

    public void deleteRole(Modelm model);

    public String getAllRolesId(Modelm user);

    public List<Modelm> findByPageDWZ(@Param("param")Modelm model, Page<Modelm> page, @Param("list")List<Modelm> list);

    public List<Modelm> findUserByRoleAndHos(@Param("rolename")String RoleName, @Param("hosnum")String hosnum);

    @Override
    public Modelm findById(@Param("param")Modelm model);

    public List<Modelm> getBasUserList();

    public List<Modelm> findBasTree(@Param("id")String id);
    /**
     * 获取model树
     * @return
     */
    public List<ZNodes> getModelTree(@Param("hosnum")String hosnum,@Param("nodecode")String nodecode/*,@Param("yq")String yq,@Param("zblb")String zblb*/);

    public List<ZNodes> getModelTree_withzbk(@Param("hosnum")String hosnum,@Param("nodecode")String nodecode,@Param("pid")String pid/*,@Param("yq")String yq,@Param("zblb")String zblb*/);

    public List<ZNodes> getModelTree_withPersonalRecord(@Param("hosnum")String hosnum,@Param("nodecode")String nodecode/*,@Param("yq")String yq,@Param("zblb")String zblb*/);

    public List<ZNodes> getPersonalRecordTree(@Param("hosnum")String hosnum,@Param("nodecode")String nodecode,@Param("xcszy")String xcszy/*,@Param("yq")String yq,@Param("zblb")String zblb*/);

    public List<Modelm> findbyyear(@Param("hosnum")String hosnum,@Param("year")String year);

	public List<ZNodes> getModelTree_withPersonalRecords(@Param("hosnum")String hosnum,@Param("year")String nodecode);

	public List<ZNodes> getModelTree_withzbkdept(@Param("hosnum")String hosnum,@Param("nodecode")String nodecode);

	public void updatesynergy(Modelm m);

	public List<Modelm> findbystoptime(Modelm m);

	public void updatescmodel(Modelm zbk);
	
	public List<ZNodes> getzbkPersonal(@Param("hosnum")String hosnum,@Param("pid")String pid,@Param("id")String id);
	
	 public void updateguidang(Modelm model);

	public List<Modelm> findyearmodel(Modelm m);

	public List<Modelm> findyearmodels(Modelm m);

}