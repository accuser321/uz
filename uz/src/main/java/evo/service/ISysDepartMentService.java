package evo.service;

import java.util.List;

import evo.model.SysDepartment;
import evo.util.Page;

public interface ISysDepartMentService {

	/**
	 * 
	 * @Title: addSysDepart
	 * @Description: 增加部门
	 * @author Demo demo_@evo_com
	 * @param @param sysDepartment    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void addSysDepart(SysDepartment sysDepartment);
	
	/**
	 * 
	 * @Title: deleteSysDepart
	 * @Description: 删除部门
	 * @author Demo demo_@evo_com
	 * @param @param sysDepartment    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void deleteSysDepart(SysDepartment sysDepartment);
	
	/**
	 * 
	 * @Title: updateSysDepart
	 * @Description: 修改部门信息
	 * @author Demo demo_@evo_com
	 * @param @param sysDepartment    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void updateSysDepart(SysDepartment sysDepartment);
	
	/**
	 * @param page 
	 * @param sysDepartment 
	 * 
	 * @Title: findSysDepartmentList
	 * @Description: 查询部门列表
	 * @author Demo demo_@evo_com
	 * @param @return    设定文件
	 * @return List<SysDepartment>    返回类型
	 * @throws
	 */
	public List<SysDepartment> findSysDepartmentList();
	
	/**
	 * @param page 
	 * @param sysDepartment 
	 * 
	 * @Title: findSysDepartmentList
	 * @Description: 查询部门列表
	 * @author Demo demo_@evo_com
	 * @param @return    设定文件
	 * @return List<SysDepartment>    返回类型
	 * @throws
	 */
	public List<SysDepartment> findAllSysDepartmentListByPage(SysDepartment sysDepartment, Page page);
	/**
	* @Title: findSingleById 
	* @Description: 查询单个
	* @param deptId
	* @return 
	* SysDepartment
	* @throws
	 */
	public SysDepartment findSingleById(Integer deptId);
	
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
	public List findDepartForDepartIdWithParentId(Integer deptId);
	
}
