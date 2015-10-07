package evo.service;

import java.util.List;

import evo.model.SysRight;

public interface ISysRightService {

    /**
     * 多对多关系
     * 根据角色id查询权限信息
     */
	public List<SysRight> findSysRightByRoleId(Integer roleId);
	
	/**
	 * 根据sys_right表中得rightId,查询rightParentId=rightId的List数据
	 * 此list表明有三级目录
	 */
	public List<SysRight> findSysRightListByRightId(Integer rightId);
	
	
}
