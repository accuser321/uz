package evo.dao;

import java.util.List;

import evo.model.SysRight;
import evo.model.SysRole;

public interface SysRightMapper {
    int deleteByPrimaryKey(Integer rightId);

    int insert(SysRight record);

    int insertSelective(SysRight record);

    SysRight selectByPrimaryKey(Integer rightId);

    int updateByPrimaryKeySelective(SysRight record);

    int updateByPrimaryKey(SysRight record);
    
    
    /**
     * 多对多关系
     * 根据角色id查询权限信息List
     */
    public List<SysRight> findSysRightByRoleId(Integer roleId);
    
	/**
	 * 根据sys_right表中得rightId,查询rightParentId=rightId的List数据
	 * 此list表明有三级目录
	 */
    public List<SysRight> findSysRightListByRightId(Integer rightId);
    
}