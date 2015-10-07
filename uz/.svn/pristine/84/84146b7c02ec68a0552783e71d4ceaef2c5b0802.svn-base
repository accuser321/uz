package evo.dao;

import java.util.List;
import java.util.Map;

import evo.model.SysRole;
import evo.util.Page;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    List<SysRole> findSysRoleList();
    
	/**
	 * 
	 * @Title: addSysRole
	 * @Description: 创建角色
	 * @author Demo demo_@evo_com
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 */
    public void addSysRole(SysRole sysRole);
    
	/**
	 * 
	 * @Title: deleteSysRole
	 * @Description: 删除角色
	 * @author Demo demo_@evo_com
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 */
    public void deleteSysRole(Integer sysRoleId);
    
	/**
	 * 
	 * @Title: updateSysRole
	 * @Description: 修改角色信息
	 * @author Demo demo_@evo_com
	 * @param @param sysRole    设定文件
	 * @return void    返回类型
	 * @throws
	 */
    public void updateSysRole(SysRole sysRole);
    
    /**
	 * 
	 * @Title: findSysRoleListByPage
	 * @Description: 查询所有角色信息List-分页
	 * @author Demo demo_@evo_com
	 * @param @param sysRole
	 * @param @param page
	 * @param @return    设定文件
	 * @return List<SysRole>    返回类型
	 * @throws
	 */
	public List<SysRole> findSysRoleListByPage(Map<String, Object> parameter);
    
}