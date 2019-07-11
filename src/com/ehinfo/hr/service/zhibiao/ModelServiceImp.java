package com.ehinfo.hr.service.zhibiao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ehinfo.hr.common.mybatis.Page;
import com.ehinfo.hr.common.utils.tree.entity.ZNodes;
import com.ehinfo.hr.entity.zhibiao.Modelm;
import com.ehinfo.hr.entity.zhibiao.new_ryzbk;
import com.ehinfo.hr.entity.zhibiao.zhibiaoku;
import com.ehinfo.hr.repository.declare.PersonalRecordDao;
import com.ehinfo.hr.repository.system.dict.BaseDictDao;
import com.ehinfo.hr.repository.zhibiao.ModelDao;
import com.ehinfo.hr.repository.zhibiao.new_ryzbkDao;
import com.ehinfo.hr.repository.zhibiao.zhibiao_sbDao;
import com.ehinfo.hr.service.base.BaseServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("ModelService")
public class ModelServiceImp extends BaseServiceImp<Modelm> implements ModelService {

	@Autowired
	private ModelDao dao;
	@Autowired
	private BaseDictDao bddao;
	@Autowired
	private new_ryzbkDao nrdao;
	@Autowired
	private PersonalRecordDao personalRecordDao;
	@Autowired
	private zhibiao_sbDao zbkdao;
	@Override
	@Transactional
	public String insert1(Modelm o) {
		
		
		//----------------------------判断是add还是修改------
		if(o.getId()==null||"".endsWith(o.getId())){
			o.setId(UUID.randomUUID().toString());
		}else{
			dao.update(o);
			return "1";
		}
		
		List<Modelm> list = dao.find(o);
		if(list.size()>0){
			
			return "0";
		}

		dao.insert(o);
		
		return "1";
	}




	@Override
	 public Modelm findFormatByLoginName(String loginName){
		Modelm a = null;
			try {
				a= dao.findFormatByLoginName(loginName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return a;
		}
	 
	

	
	
	
	//----------------------重置密码前的密码核对--------------------------
	@Override
	public int preResetPwd(String opwd,String npwd,String qpwd,Modelm o) {
		/*int res=0;
		if(StringUtils.isNotBlank(opwd)&&StringUtils.isNotBlank(npwd)){
			if(StringUtils.equals(npwd, qpwd)){     //------------------------------两次新密码一样----------------------
				if(StringUtils.equals(o.getPassword(), opwd)){   //-----------------输入的原密码正确---------------------
					o.setPassword(npwd);
					dao.update(o);
					res=1;
				}else{
					res=2;//密码不正确
				}
			}else{
				res=3;//两次密码不一致
			}
		}
		return res;*/
		return 1;
	}

	
	
	
	
	//------------------------删除用户，不允许删除，置个删除标记-------------------------------
	@Override
	@Transactional
	public void deleteBasUser(Modelm o) {
		//事务删除
		dao.delete(o);
		
	}

	
	//------------------------批量删除用户，不允许删除，置个删除标记-------------------------------
	@Override	
	@Transactional
	public void deleteBatchBasUser(String chks) {
		//事务删除	
		if(StringUtils.isNotBlank(chks)){
			String[] chk =chks.split(",");
			List<Modelm> list=new ArrayList<Modelm>();
			for(String s:chk){
				Modelm sd=new Modelm();
				sd.setId(s);
				list.add(sd);
			}
			dao.deleteBatch(list);
			
		}
	
	}

	@Override
	@Transactional
	public void match(String model_m,String model_p,String unitid) {
		//事务删除
		if(StringUtils.isNotBlank(model_m)){
			String[] chk =model_p.split(",");
			List<new_ryzbk> list=new ArrayList<new_ryzbk>();
			for(String s:chk){
				new_ryzbk nr= new new_ryzbk();
				nr.setPid(model_m);
				nr.setRecordno(s);
				nr.setUnitid(unitid);
				list.add(nr);
			}
			nrdao.deleteBatch(list);

			if(StringUtils.isNotBlank(model_m)) {
				for (String s : chk) {
					new_ryzbk nr = new new_ryzbk();
					nr.setPid(model_m);
					nr.setRecordno(s);
					nr.setUnitid(unitid);
					nrdao.insert(nr);
				}

			}
		}

	}



	@Override
	public Modelm findById(Modelm user) {
		// TODO Auto-generated method stub
		return dao.findById(user);
	}



	@Override
	public String getAllRolesId(Modelm user) {
		// TODO Auto-generated method stub
		return dao.getAllRolesId(user);
	}



	@Override
	public Page<Modelm> findByPageDWZ(Modelm user, Page<Modelm> page, List<Modelm> dept) {
		// TODO Auto-generated method stub
		
		page.setResults(dao.findByPageDWZ(user, page,dept));
		
		return page;
	}





	@Override
	@Transactional
	public List<Modelm> findUserByRoleAndHos(String RoleName, String hosnum){
		return dao.findUserByRoleAndHos(RoleName,hosnum);
	}





	@Override
	public List<Modelm> findBasTree(String id) {
		return dao.findBasTree(id);
	}

	@Override
	public List<ZNodes> getModelTree(String hosnum, String nodecode) {
		/*List<ZNodes> list = new ArrayList<ZNodes>();
		BaseDict bd = new BaseDict();
		bd.setNekey(101);
		List<BaseDict> yqlist = bddao.selectDictByNekey(bd);
		bd.setNekey(91);
		List<BaseDict> zblblist = bddao.selectDictByNekey(bd);
		for(BaseDict bd_101:yqlist){
			ZNodes z = new ZNodes();
			z.setId(bd_101.getNevalue());
			z.setpId("im");
		}*/

		return dao.getModelTree(hosnum,nodecode);
	}

	@Override
	public List<ZNodes> getModelTree_withzbk(String hosnum, String nodecode,String pid) {

		return dao.getModelTree_withzbk(hosnum,nodecode,pid);
	}
	@Override
	public List<ZNodes> getModelTree_withzbkdept(String hosnum, String nodecode) {

		return dao.getModelTree_withzbkdept(hosnum,nodecode);
	}
	@Override
	public List<ZNodes> getModelTree_withPersonalRecord(String hosnum, String nodecode) {

		return dao.getModelTree_withPersonalRecord(hosnum,nodecode);
	}
	@Override
	public List<ZNodes> getModelTree_withPersonalRecords(String hosnum, String nodecode) {

		return dao.getModelTree_withPersonalRecords(hosnum,nodecode);
	}
	@Override
	public List<ZNodes> getPersonalRecordTree(String hosnum, String nodecode,String xcszy) {

		return dao.getPersonalRecordTree(hosnum,nodecode,xcszy);
	}
	
	@Override
	public List<Modelm> findbyyear(String hosnum,String year) {
		return dao.findbyyear(hosnum,year);
	}
	@Override
	public void updatesynergy(Modelm m){
		dao.updatesynergy(m);
	}
	@Override
	public List<Modelm> findbystoptime(Modelm m) {
		return dao.findbystoptime(m);
	}
	@Override
	public void updatescmodel(Modelm zbk){
		dao.updatescmodel(zbk);
	}

	@Override
	public List<ZNodes> getzbkPersonal(String hosnum, String pid,String id) {
		return dao.getzbkPersonal(hosnum, pid,id);
	}
	
	@Override
	@Transactional
	public void modelguidang(String  ids){
		String[] chk =ids.split(",");
		Modelm m = null;
		for(int i=0;i<chk.length;i++){
			m=new Modelm();
			m.setId(chk[i]);
			dao.updateguidang(m);
			personalRecordDao.updategdper(chk[i]);
			String allids = zbkdao.getpid(chk[i]);
			zbkdao.updategdzbk(allids);
		}
	}
	@Override
	public List<Modelm> findyearmodel(Modelm m) {
		return dao.findyearmodel(m);
	}
	@Override
	public List<Modelm> findyearmodels(Modelm m) {
		return dao.findyearmodels(m);
	}
}
