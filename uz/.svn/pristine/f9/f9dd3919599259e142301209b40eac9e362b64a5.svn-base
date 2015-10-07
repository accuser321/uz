package evo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import evo.model.SysRole;
import evo.model.SysRoleRight;
import evo.model.Visitor;
import evo.service.IRoleRightService;

@Controller
@RequestMapping("/roleRightController")
public class RoleRightController {
	
	@Autowired
	private IRoleRightService roleRightService;
	
	
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
	@RequestMapping("/findRoleRightListByRoleId")
	public ModelAndView findRoleRightListByRoleId(SysRole sysRole,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		List<SysRoleRight> list = roleRightService.findRoleRightListByRoleId(sysRole);
		view.addObject("roleRightList", list);
		
		return view;
	}
	
	/**
	 * 
	 * @Title: insertRoleRight
	 * @Description: 向角色权限表中插入数据
	 * @author Demo demo_@evo_com
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@RequestMapping("/insertRoleRight")
	public String insertRoleRight(SysRoleRight sysRoleRight){
		roleRightService.insertRoleRight(sysRoleRight);
		return "";
	}
	
}
