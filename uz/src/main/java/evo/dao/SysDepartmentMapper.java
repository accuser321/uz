package evo.dao;

import java.util.List;
import java.util.Map;

import evo.model.SysDepartment;

public interface SysDepartmentMapper {
    int deleteByPrimaryKey(Integer deptId);

    int insert(SysDepartment record);

    int insertSelective(SysDepartment record);

    SysDepartment selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(SysDepartment record);

    int updateByPrimaryKey(SysDepartment record);
    
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
	 * 
	 * @Title: findSysDepartmentList
	 * @Description: 查询部门列表
	 * @author Demo demo_@evo_com
	 * @param @return    设定文件
	 * @return List<SysDepartment>    返回类型
	 * @throws
	 */
	public List<SysDepartment> findSysDepartmentList();

	public List<SysDepartment> findAllSysDepartmentListByPage(Map<String, Object> parameter);
	
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