package evo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evo.dao.SysRoleMapper;
import evo.model.SysRole;
import evo.util.Page;

/**
 * 业务实现接口模型
 * @author yanxin
 *
 */
/**
  * 项目名称：uz
  * 类名称：SysRoleImpl
  * 类描述：
  * 创建人：zoujun
  * 创建时间：2015-7-14 上午10:33:34
  * 修改人：zoujun
  * 修改时间：2015-7-14 上午10:33:34
  * 修改备注：
  * @version 
  */
@Service("sysRoleService")
public class SysRoleImpl implements ISysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	private static final Logger logger = Logger.getLogger(SysRoleImpl.class);
	
	/**
	 * 查询N方的用户信息
	 */
	public List<SysRole> findSysRoleList() {
		List<SysRole> list = sysRoleMapper.findSysRoleList();
		return list;
	}

	
	/**
	 * 
	 * @Title: addSysRole
	 * @Description: 创建角色
	 * @author Demo demo_@evo_com
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void addSysRole(SysRole sysRole) {
		sysRoleMapper.addSysRole(sysRole);
		
	}
	
	/**
	 * 
	 * @Title: deleteSysRole
	 * @Description: 删除角色
	 * @author Demo demo_@evo_com
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void deleteSysRole(Integer sysRoleId){
		sysRoleMapper.deleteSysRole(sysRoleId);
	}

	/**
	 * 
	 * @Title: updateSysRole
	 * @Description: 修改角色信息
	 * @author Demo demo_@evo_com
	 * @param @param sysRole    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void updateSysRole(SysRole sysRole) {
		sysRoleMapper.updateSysRole(sysRole);
		
	}
	
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
	public List<SysRole> findSysRoleListByPage(SysRole sysRole,Page page){
		Map<String,Object> parameter = new HashMap<String,Object>();
		
		SysRole sr = new SysRole();
		if(StringUtils.isNotBlank(sysRole.getRoleName())){
			sr.setRoleName(sysRole.getRoleName());
		}
		parameter.put("page", page);
		parameter.put("sysr", sr);
		List<SysRole> list = sysRoleMapper.findSysRoleListByPage(parameter);
		
		return list;
	}


	/**
	 * 
	 * @Title: findSysRoleListByPage
	 * @Description: 查询单个
	 * @author Demo demo_@evo_com
	 * @param @param sysRole
	 * @param @param page
	 * @param @return    设定文件
	 * @return List<SysRole>    返回类型
	 * @throws
	 */
	@Override
	public SysRole findRoleById(Integer roleId) {
		return sysRoleMapper.selectByPrimaryKey(roleId);
	}
	

/*
	*//**
	 * 登陆
	 *//*
	public SysUser doLogin(SysUser sysuser) {
//		List<?> list = sysUserMapper.findSysUser(sysuser);
//		logger.info(JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
//		if(list.size()>0){
//			SysUser user = (SysUser)list.get(0);
//			return user;
//		}
		return null;
	}

	*//**
	 * 生成菜单
	 *//*
	public String doMakeMenu(SysUser sysuser) {
		
		//查询用户
		
//		Set<SysRight> set = sysuser.getSysRole().getSysRight();
//		StringBuffer str = new StringBuffer();
//		for(SysRight r : set){
//			str.append(r.getRightId() + "= theMenu.addChild(");
//			str.append(r.getRightParentId() + ",\"");
//			str.append(r.getRightType() + "\",\"");
//			str.append(r.getRightText() + "\",\"");
//			str.append(r.getRightUrl() + "\",\"");
//			str.append("\");\n");
//		}
//		return str.toString();
		return null;
	}
	*/
	
	
	

}
