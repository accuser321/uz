package evo.service;

import java.util.List;

import evo.model.SysRole;
import evo.model.SysRoleRight;

public interface IRoleRightService {

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
	 * @Title: findRoleRightListByRoleId
	 * @Description: 根据roleId查询角色权限表List
	 * @author Demo demo_@evo_com
	 * @param @param roleId
	 * @param @return    设定文件
	 * @return List<SysUser>    返回类型
	 * @throws
	 */
	public List<SysRoleRight> findRoleRightListByRoleId(Integer roleId);
	
	
	/**
	 * 
	 * @Title: addBindRoleRight
	 * @Description: 权限绑定
	 * 				   带着roleId插入到角色权限表中
	 * @author Demo demo_@evo_com
	 * @param @param sysRole
	 * @param @param response
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	public void addBindRoleRight(String roleId,String[] roleRightId);
	
	
	/**
	 * 
	 * @Title: deleteBindRole
	 * @Description: 解除权限绑定 
	 * 				   带着roleId插入到角色权限表中
	 * @author Demo demo_@evo_com
	 * @param @param sysRole
	 * @param @param response
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	public void deleteBindRole(String roleId,String[] roleRightId);
	
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
	
	public void updateBindRole(String roleId,String[] roleRightId);
	
	public void removeBindRole(String roleId,String[] roleRightId);
}
