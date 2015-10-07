package evo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import evo.dao.SysDepartmentMapper;
import evo.model.SysDepartment;
import evo.util.DateUtil;
import evo.util.Page;

/**
 * 业务实现接口模型
 * @author yanxin
 *
 */
@Transactional
@Service("sysDepartMentService")
public class SysDepartMentImpl implements ISysDepartMentService {

	@Autowired
	private SysDepartmentMapper sysDepartmentMapper;

	/**
	 * 
	 * @Title: addSysDepart
	 * @Description: 增加部门
	 * @author Demo demo_@evo_com
	 * @param @param sysDepartment    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void addSysDepart(SysDepartment sysDepartment) {
		sysDepartment.setUserCreateTime(DateUtil.getStandardDateTime());
		sysDepartmentMapper.addSysDepart(sysDepartment);
		
	}

	/**
	 * 
	 * @Title: deleteSysDepart
	 * @Description: 删除部门
	 * @author Demo demo_@evo_com
	 * @param @param sysDepartment    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void deleteSysDepart(SysDepartment sysDepartment) {
		sysDepartmentMapper.deleteSysDepart(sysDepartment);
		
	}

	/**
	 * 
	 * @Title: updateSysDepart
	 * @Description: 修改部门信息
	 * @author Demo demo_@evo_com
	 * @param @param sysDepartment    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void updateSysDepart(SysDepartment sysDepartment) {
		sysDepartmentMapper.updateSysDepart(sysDepartment);
		
	}
	
	
	/**
	 * 
	 * @Title: findSysDepartmentList
	 * @Description: 查询部门列表
	 * @author Demo demo_@evo_com
	 * @param @return    设定文件
	 * @return List<SysDepartment>    返回类型
	 * @throws
	 */
	public List<SysDepartment> findSysDepartmentList(){
		List<SysDepartment> list = sysDepartmentMapper.findSysDepartmentList();
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	/**
	 * 
	 * @Title: findSysDepartmentList
	 * @Description: 查询部门列表 -分页
	 * @author Demo demo_@evo_com
	 * @param @return    设定文件
	 * @return List<SysDepartment>    返回类型
	 * @throws
	 */
	@Override
	public List<SysDepartment> findAllSysDepartmentListByPage(
			SysDepartment sysDepartment, Page page) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("sysDepartment", sysDepartment);
		parameter.put("page", page);
		List<SysDepartment> list = sysDepartmentMapper.findAllSysDepartmentListByPage(parameter);
		for(SysDepartment sDepartment:list){
			if(null!=sDepartment.getParentDeptId()){
				SysDepartment parentDepartment = sysDepartmentMapper.selectByPrimaryKey(sDepartment.getParentDeptId());
				sDepartment.setParentDepart(parentDepartment);
			}
			sDepartment.setUserCreateTime(sDepartment.getUserCreateTime().substring(0, sDepartment.getUserCreateTime().lastIndexOf(".")));
		}
		return list;
	}
	/**
	 * 查询单个
	 */
	@Override
	public SysDepartment findSingleById(Integer deptId) {
		return sysDepartmentMapper.selectByPrimaryKey(deptId);
	}
	
	/**
	 * 
	 * @Title: findDepartForDepartIdWithParentId
	 * @Description: 将SysDepartment对象，作为参数，查询和deptId相同的所有parentId
	 * @author Demo demo_@evo_com
	 * @param @param deptId
	 * @param @return    设定文件
	 * @return List    返回类型
	 * @throws
	 */
	public List<SysDepartment> findDepartForDepartIdWithParentId(Integer deptId){
		List<SysDepartment> list = sysDepartmentMapper.findDepartForDepartIdWithParentId(deptId);
		return list;
	}
}
