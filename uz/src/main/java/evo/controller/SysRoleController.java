package evo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import evo.model.SysDepartment;
import evo.model.SysRight;
import evo.model.SysRole;
import evo.model.SysUser;
import evo.model.TreeVo;
import evo.service.ISysDepartMentService;
import evo.service.ISysRightService;
import evo.service.ISysRoleService;
import evo.service.ISysUserService;
import evo.util.Page;

@Controller
@RequestMapping("/sysRoleController")
public class SysRoleController {

	
	private static final Logger logger = Logger.getLogger(SysRoleController.class);
	
	@Autowired
	private ISysRoleService sysRoleService;
	
	@Autowired
	private ISysDepartMentService sysDepartMentService;
	
	@Autowired
	private ISysUserService sysUserService;
	
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
	@RequestMapping("/findSysRoleListByPage/{currentPage}")
	public ModelAndView findSysRoleListByPage(SysRole sysRole,
			@PathVariable int currentPage,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		Page page = new Page();
		page.setCurrentPage(currentPage);
		List<SysRole> list = sysRoleService.findSysRoleListByPage(sysRole, page);
		view.addObject("sysRoleListByPage", list);
		view.addObject("page", page);
		view.addObject("sysrole", sysRole);
		view.setViewName("role");
		return view;
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
	@RequestMapping("/addSysRole")
	public String addSysRole(SysRole sysRole, HttpServletResponse response) {
		sysRoleService.addSysRole(sysRole);
		return "redirect:findSysRoleListByPage/0.do";
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
	@RequestMapping("/deleteSysRole/{roleId}")
	public void deleteSysRoleByRoleId(@PathVariable Integer roleId, HttpServletResponse response){
		sysRoleService.deleteSysRole(roleId);
		try {
			response.getWriter().print("{msg:'t'}");
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	@RequestMapping("/updateSysRole")
	public String updateSysRole(SysRole sysRole,HttpServletResponse response){
		sysRoleService.updateSysRole(sysRole);
		return "redirect:findSysRoleListByPage/0.do";
	}
	
	
	/**
	 * 
	 * @Title: updateSysRole
	 * @Description: 查询单个
	 * @author Demo demo_@evo_com
	 * @param @param sysRole    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@RequestMapping("/modifySingle")
	public void modifySingle(SysRole sysRole,HttpServletResponse response){
		SysRole singleRole = sysRoleService.findRoleById(sysRole.getRoleId());
		try {
			JSONObject singleObject = new JSONObject();
			singleObject.put("singleRole", singleRole);
			response.getWriter().print(JSONObject.fromObject(singleObject).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @throws IOException 
	 * 
	 * @Title: getRoleMenuTree
	 * @Description: 获取角色菜单树
	 * @author Demo demo_@evo_com
	 * @param @param sysDepartment
	 * @param @param response
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * important 角色ID是唯一的，在前后台之间带着角色ID
	 * @throws
	 */
	@RequestMapping("/{roleID}/getRoleMenuTree")
	public void getRoleMenuTree(SysDepartment sysDepartment,@PathVariable Integer roleID,HttpServletResponse response) throws IOException{
		ModelAndView view = new ModelAndView();
		JSONArray array = new JSONArray();
		//获取部门信息
		getDepartByd(0,roleID,array);
		Map resultMap = new HashMap();
		resultMap.put("jsonTree", array);
		response.setContentType("text/html;charset=gb2312");
		System.out.println(JSONObject.fromObject(resultMap).toString());
		response.getWriter().print(JSONObject.fromObject(resultMap).toString());
	}
	
	/**
	 * 
	 * @Title: getDepartByd
	 * @Description: 获取部门信息
	 * @author Demo demo_@evo_com
	 * @param @param deptId
	 * @param @param roleID
	 * @param @param array
	 * @param @return    设定文件
	 * @return JSONArray    返回类型
	 * @throws
	 */
	public JSONArray getDepartByd(Integer deptId,Integer roleID,JSONArray array){
		SysDepartment sysDepartment = sysDepartMentService.findSingleById(deptId);
		//将SysDepartment对象，作为参数，查询和deptId相同的所有parentId
		List<SysDepartment> chilelist = sysDepartMentService.findDepartForDepartIdWithParentId(sysDepartment.getDeptId());
		//根据部门id查询用户信息List
		List<SysUser> ulist = sysUserService.findSysUserByDepartId(sysDepartment);
		TreeVo vo =null ; 
		JSONObject 	jsonTree = new JSONObject();
		vo = new TreeVo();
		vo.setId(sysDepartment.getDeptId());
		vo.setName(sysDepartment.getDeptName());
		vo.setpId(sysDepartment.getParentDeptId());
		array.add(vo);
		
		for (SysUser user:ulist) {
			 vo = new TreeVo();
			vo.setId(user.getUserId());
			vo.setName(user.getUserName());
			vo.setpId(deptId);
			if(user.getRoleId()!=null&&user.getRoleId().compareTo(roleID)==0){
				vo.setChecked(true);
			}
			array.add(vo);
		}
		//递归自身方法
		 for(SysDepartment de:chilelist){
			 getDepartByd(de.getDeptId(),roleID,array);
		 }
		 return array;
	}
	
	@Autowired
	private ISysRightService iSysRightService;
	/**
	 * 
	 * @Title: updateSysRole
	 * @Description: 
	 * @author Demo demo_@evo_com
	 * @param @param sysRole    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@RequestMapping("/{roleId}/searchSingleById")
	public void searchSingleById(@PathVariable Integer roleId,HttpServletResponse response){
		SysRole singleRole = sysRoleService.findRoleById(roleId);
		try {
			JSONObject singleObject = new JSONObject();
			List<SysRight> permisstionList =iSysRightService.findSysRightByRoleId(roleId);
			singleObject.put("singleRole", singleRole);
			singleObject.put("permisstionList", permisstionList);
			response.setContentType("text/html;charset=utf-8"); 
			response.getWriter().print(JSONObject.fromObject(singleObject).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
