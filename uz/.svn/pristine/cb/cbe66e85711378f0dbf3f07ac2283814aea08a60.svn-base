package evo.dao;

import java.util.List;

import evo.model.SysRole;
import evo.model.SysRoleRight;
import evo.model.SysUser;

public interface SysRoleRightMapper {
	
	/**
	 * 
	 * @Title: findRoleRightListByRoleId
	 * @Description: 根据roleId查询角色权限表List
	 * @author Demo demo_@evo_com
	 * @param @param sysRole
	 * @param @return    设定文件
	 * @return List<SysRoleRight>    返回类型
	 * @throws
	 */
	public List<SysRoleRight> findRoleRightListByRoleId(SysRole sysRole);
	
	/**
	 * 
	 * @Title: findMultyUserByRole
	 * @Description: 根据roleId批量查询SysUser的List
	 * @author Demo demo_@evo_com
	 * @param @param roleId
	 * @param @return    设定文件
	 * @return List<SysUser>    返回类型
	 * @throws
	 */
	public List<SysRoleRight> findRoleRightListByRoleId(Integer roleId);
	
	
	/**
	 * 
	 * @Title: insertRoleRight
	 * @Description: 向角色权限表中插入数据
	 * @author Demo demo_@evo_com
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void insertRoleRight(SysRoleRight sysRoleRight);
	
	
	
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleRight record);

    int insertSelective(SysRoleRight record);

    SysRoleRight selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleRight record);

    int updateByPrimaryKey(SysRoleRight record);
}