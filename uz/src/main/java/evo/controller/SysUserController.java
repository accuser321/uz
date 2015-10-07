package evo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import evo.model.SysDepartment;
import evo.model.SysRight;
import evo.model.SysRole;
import evo.model.SysRoleRight;
import evo.model.SysUser;
import evo.service.IRoleRightService;
import evo.service.ISysDepartMentService;
import evo.service.ISysRightService;
import evo.service.ISysUserService;
import evo.util.DateUtil;
import evo.util.Page;

@Controller
@RequestMapping("/sysUserController")
public class SysUserController {

	@Autowired
	private ISysUserService sysUserService;
	
	@Autowired
	private ISysDepartMentService sysDepartMentService;
	
	@Autowired
	private IRoleRightService roleRightService;
	
	@Autowired
	private ISysRightService iSysRightService;

	/**
	 * 
	 * @Title: doLogin
	 * @Description: 登陆
	 * @author Demo demo_@evo_com
	 * @param @param sysUser
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping("/doLogin")
	public void doLogin(SysUser sysUser, HttpServletResponse response,
			HttpServletRequest request) {
		SysUser user = sysUserService.findSysUserByNameAndPass(
				sysUser.getUserName(), sysUser.getUserPass());
		try {
			if (user == null) {
				response.getWriter().print("{msg:'f'}");
			} else {
				List<SysRight> permisstionList =null;
				if(user.getRoleId()!=null){
					permisstionList =iSysRightService.findSysRightByRoleId(user.getRoleId());
				}else{
					permisstionList = new ArrayList<SysRight>();
				}
				response.getWriter().print("{msg:'t'}");
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("perimisionList", permisstionList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/loginSuc")
	public ModelAndView loginSuc() {
		ModelAndView view = new ModelAndView();
		view.setViewName("main");
		return view;
	}

	@RequestMapping("/loginOut")
	public ModelAndView loginOut(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		request.getSession().removeAttribute("user");
		view.setViewName("login");
		return view;
	}

	/**
	 * @Title: insertSysUser
	 * @Description: 用户注册
	 * @author Demo demo_@evo_com
	 * @param @param sysUser
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("/addEmployUser")
	public String insertSysUser(SysUser sysUser, HttpServletResponse response) {
		sysUser.setUserCreateTime(DateUtil.getStandardDateTime());
		sysUser.setUserBirthday(DateUtil.getStandardDateTime());
		sysUser.setUserAble(0);
		sysUserService.insertSysUser(sysUser);
		return "redirect:findSysUserListByPage/0.do";
	}

	/**
	 * @Title: deleteSysUserByUserId
	 * @Description: 删除用户(根据用户id)
	 * @author Demo demo_@evo_com
	 * @param @param userId
	 * @return String 返回类型
	 * important
	 * @throws
	 */
	@RequestMapping("/deleteSysUserByUserId/{userId}")
	public void deleteSysUserByUserId(@PathVariable Integer userId, HttpServletResponse response) {
		sysUserService.deleteSysUserByUserId(userId);
		try {
			response.getWriter().print("{msg:'t'}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @throws Exception 
	 * 
	 * @Title: updateSysUserByName
	 * @Description: 根据用户名修改密码
	 * @author Demo demo_@evo_com
	 * @param @param sysUser
	 * @param @return 设定文件
	 * @return String 返回类型
	 * important
	 * @throws
	 */
	@RequestMapping("/updateSysUserByName")
	public void updateSysUserByName(SysUser sysUser,HttpServletResponse response,HttpServletRequest request) throws Exception {
		SysUser  user = (SysUser) request.getSession().getAttribute("user");
		user.setUserPass(sysUser.getUserPass());
		sysUserService.updateSysUserByName(user);
		request.getSession().removeAttribute("user");
		response.getWriter().print("{msg:'t'}");
	
	}
	
	/**
	 * 
	 * @Title: updateSysUser
	 * @Description: 修改用户信息
	 * @author Demo demo_@evo_com
	 * @param @param sysUser    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@RequestMapping("/updateSysUser")
	public String updateSysUser(SysUser sysUser,HttpServletResponse response) {
		sysUserService.updateSysUser(sysUser);
		return "redirect:findSysUserListByPage/0.do";
	}
	
	@RequestMapping("/findSinglUser")
	public void findSinglUser(SysUser sysUser,HttpServletResponse response) {
		SysUser singleUer = sysUserService.findSinglUserById(sysUser);
		try {
			List<SysDepartment> departList = sysDepartMentService.findSysDepartmentList();
			JSONObject objectList = new JSONObject();
			objectList.put("singleUer", singleUer);
			objectList.put("departList", departList);
			response.getWriter().print(JSONObject.fromObject(objectList).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 
	 * @Title: findSysUserList
	 * @Description: 查询所以用户信息List
	 * @author Demo demo_@evo_com
	 * @param @return 设定文件
	 * @return List<SysUser> 返回类型
	 * @throws
	 */
	@RequestMapping("/findSysUserList")
	public ModelAndView findSysUserList() {
		ModelAndView view = new ModelAndView();
		List<SysUser> list = sysUserService.findSysUserList();
		view.addObject("sysUserList", list);
		view.setViewName("staff");
		return view;
	}
	/**
	* @Title: checkUserExist 
	* @Description: 根据用户名称检查用户是否存在
	* @param userName
	* @param response 
	* void
	* @throws
	 */
	@RequestMapping("/{userName}/checkUserExist")
	public void checkUserExist(@PathVariable String userName,HttpServletResponse response){
		boolean exist = sysUserService.checkUserExist(userName);
		try {
			response.getWriter().print("{msg:'"+exist+"'}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Title: findSysUserListByPage
	 * @Description: 查询所以用户信息List-分页
	 * @author Demo demo_@evo_com
	 * @param @return 设定文件
	 * @return List<SysUser> 返回类型
	 * important
	 * @throws
	 */
	@RequestMapping("/findSysUserListByPage/{currentPage}")
	public ModelAndView findSysUserListByPage(SysUser sysUser,
			@PathVariable int currentPage, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		Page page = new Page();
		page.setCurrentPage(currentPage);
		List<SysUser> list = sysUserService
				.findSysUserListByPage(page, sysUser);
		view.addObject("sysUserListByPage", list);
		List<SysDepartment> departList = sysDepartMentService.findSysDepartmentList();
		view.addObject("departList", departList);
		view.addObject("page", page);
		view.addObject("sysuser", sysUser);
		view.setViewName("staff");
		return view;
	}
	
	/**
	 * api
	 * @Title: findSysUserAllList
	 * @Description: 查询所有用户信息List
	 * @author Demo demo_@evo_com
	 * @param @param request
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	@RequestMapping(value="/findSysUserAllList")
	public void findSysUserAllList(HttpServletRequest request,HttpServletResponse response){
        
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		List<SysUser> list = sysUserService.findSysUserList();
		response.setContentType("text/html; charset=utf-8");
		jsonMap.put("sysuser", list);
		try {
			response.getWriter().print(JSONObject.fromObject(jsonMap).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * api
	 * @Title: findSysUserAllList
	 * @Description: 查询所有用户信息List
	 * @author Demo demo_@evo_com
	 * @param @param request
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	@RequestMapping(value="/{phoneNum}/findUserByPhoneNum",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findUserByPhoneNum(@PathVariable String phoneNum,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		SysUser sysUser = sysUserService.findUserByPhoneNum(phoneNum);
		if(sysUser!=null){
			jsonMap.put("sysuser", sysUser);
			String string = JSONObject.fromObject(jsonMap).toString();
			return string;
		}else{
			return null;
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @Title: addBindRole
	 * @Description: 权限绑定
	 * 				   带着roleId插入到用户表中
	 * @author Demo demo_@evo_com
	 * @param @param sysRole
	 * @param @param response
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	@RequestMapping("/addBindRole")
	public void addBindRole(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String roleId = request.getParameter("roleID");
		Integer roleID=Integer.valueOf(roleId);
		String[] permissionID = request.getParameter("permissionID").split(",");
		String[] UserID = request.getParameter("UserID").split(",");
		//================用户
		//根据roleId查询用户List
		List<SysUser> olist = sysUserService.findMultyUserByRole(roleID);
		//遍历出每个用户信息
		String[] OldUserID = new String[olist.size()];
		List<String> ids = new ArrayList<String>();
		
		for(int i = 0; i < olist.size(); i++){
			String odId = olist.get(i).getUserId().toString();
			OldUserID[i] = odId;
			ids.add(odId);
		}
		//将数据库中存在的roleId赋值为null
		sysUserService.removeBindRole(roleId, OldUserID);
		
		//更新成roleID
		String[] userids = new String[UserID.length];
		for (int i = 0; i < UserID.length; i++) {
			userids[i] = UserID[i];
		}
		sysUserService.updateBindRole(roleId, userids);
		
		//================权限
		//根据roleId查询权限List
		List<SysRoleRight> rrlist = roleRightService.findRoleRightListByRoleId(roleID);
		//遍历出每个权限信息
		String[] OldpermissID = new String[rrlist.size()];
		List<String> ops = new ArrayList<String>();
		
		for(int i = 0; i < rrlist.size(); i++){
			String rId = rrlist.get(i).getRightId().toString();
			OldpermissID[i] = rId;
			ops.add(rId);
		}
		//将数据库中存在的roleId赋值为null
		roleRightService.removeBindRole(roleId, OldpermissID);
		
		//更新成roleID
		String[] pis = new String[permissionID.length];
		for (int i = 0; i < permissionID.length; i++) {
			pis[i] = permissionID[i];
		}
		roleRightService.updateBindRole(roleId, pis);
		
		response.getWriter().print("{msg:'t'}");
	}
	
	/**
	 * 
	 * @Title: addtest
	 * @Description: 批量修改测试类
	 * @author Demo demo_@evo_com
	 * @param @param response
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@RequestMapping("/addtest")
	public void addtest (HttpServletResponse response) throws Exception{
		//sysUserService.addBindRole("2",new Integer[]{25,26,27});
		response.getWriter().print("success");
	}
	
	/**
	 * 
	 * @Title: deletetest
	 * @Description: 批量删除测试类
	 * @author Demo demo_@evo_com
	 * @param @param response
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@RequestMapping("/deletetest")
	public void deletetest (HttpServletResponse response) throws Exception{
		//sysUserService.deleteBindRole("2",new Integer[]{25,26,27});
		response.getWriter().print("success");
	}
	
	/**
	 * 
	 * @Title: addtest
	 * @Description: 批量修改测试类
	 * @author Demo demo_@evo_com
	 * @param @param response
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@RequestMapping("/addRoleRighttest")
	public void addRoleRighttest (HttpServletResponse response) throws Exception{
		roleRightService.addBindRoleRight("2", new String[]{"10","11"});
		response.getWriter().print("success");
	}
	
	/**
	 * 
	 * @Title: deletetest
	 * @Description: 批量删除测试类
	 * @author Demo demo_@evo_com
	 * @param @param response
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@RequestMapping("/deleteRoleRighttest")
	public void deleteRoleRighttest (HttpServletResponse response) throws Exception{
		roleRightService.deleteBindRole("2",new String[]{"10","11"});
		response.getWriter().print("success");
	}
	
	
	
}
