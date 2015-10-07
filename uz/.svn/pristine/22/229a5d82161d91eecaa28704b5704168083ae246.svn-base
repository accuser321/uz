package evo.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import evo.dao.SysRightMapper;
import evo.model.SysRight;

@Service("sysRightService")
public class SysRightServiceImpl implements ISysRightService {

	@Autowired
	private SysRightMapper sysRightMapper;
	
	private static final Logger logger = Logger.getLogger(SysRightServiceImpl.class);
	
    /**
     * 多对多关系
     * 根据角色id查询权限信息List
     */
	public List<SysRight> findSysRightByRoleId(Integer roleId) {
		List<SysRight> list = sysRightMapper.findSysRightByRoleId(roleId);
		logger.info(JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
		return list;
	}


	/**
	 * 根据sys_right表中得rightId,查询rightParentId=rightId的List数据
	 * 此list表明有三级目录
	 */
	public List<SysRight> findSysRightListByRightId(Integer rightId) {
		List<SysRight> list = sysRightMapper.findSysRightListByRightId(rightId);
		return list;
	}

}
