package evo.controller;

import java.io.IOException;
import java.util.HashMap;
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

import evo.model.Appoint;
import evo.model.SysUser;
import evo.service.IAppointService;
import evo.service.ISysUserService;
import evo.util.Page;

@Controller
@RequestMapping("/appointController")
public class AppointController {
	
	@Autowired
	private IAppointService appointService;
	
	
	@Autowired
	private ISysUserService sysUserService;
	
	/**
	 * 基于RestFul风格的访问方式 http://localhost:8080/uz/appointController/15914141177/findAppointByPhoneNum.do
	 * 
	 */
	@RequestMapping(value="/{phoneNum}/findAppointByPhoneNum" ,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void findAppointByPhoneNum(@PathVariable String phoneNum,HttpServletResponse response){
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		Appoint appoint = appointService.findAppointByPhoneNum(phoneNum);
		String result = null;
		SysUser sysUser = sysUserService.findUserByPhoneNum(phoneNum);
		response.setContentType("text/html;charset=utf-8");
		if(appoint != null && !appoint.equals("")){
			jsonMap.put("dept", "f");
			jsonMap.put("appoint", appoint);
			result = JSONObject.fromObject(jsonMap).toString();
		}else if(sysUser!=null){
			jsonMap.put("dept", "t");
			jsonMap.put("sysUser", sysUser);
			result = JSONObject.fromObject(jsonMap).toString();
		}
		try {
			response.getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * api
	 * @Title: findSysUserAllList
	 * @Description: 查询所有用户信息List
	 * @author Demo demo_@evo_com
	 * 	important
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
	 * 
	 * @Title: insertAppoint
	 * @Description: 插入Appoint对象
	 * @author Demo demo_@evo_com
	 * @param @param appoint
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	@RequestMapping("/insertAppoint")
	public String insertAppoint(Appoint appoint){
		appointService.insertAppoint(appoint);
		return "redirect:findAppointListByPage/0.do";
	}
	
	/**
	 * @Title: deleteAppointByAppointId
	 * @Description: 删除预约信息(根据预约id)
	 * @author Demo demo_@evo_com
	 * @param @param appointId
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	@RequestMapping("/deleteAppointByAppointId/{appointId}")
	public void deleteAppointByAppointId(@PathVariable Integer appointId,HttpServletResponse response){
		appointService.deleteAppointByAppointId(appointId);
		try {
			response.getWriter().print("{msg:'t'}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: updateAppointByPhoneNum
	 * @Description: 根据手机号修改预约信息
	 * @author Demo demo_@evo_com
	 * @param @param appoint
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	@RequestMapping("/updateAppointByPhoneNum")
	public String updateAppointByPhoneNum(Appoint appoint){
		appointService.updateAppointByPhoneNum(appoint);
		return "";
	}
	
	
	/**
	 * 
	 * @Title: findAppointListByPage
	 * @Description: 查询所以预约信息List-分页
	 * @author Demo demo_@evo_com
	 * @param @return 设定文件
	 * @return List<SysUser> 返回类型
	 * @throws
	 */
	@RequestMapping("/findAppointListByPage/{currentPage}")
	public ModelAndView findAppointListByPage(Appoint appoint,@PathVariable int currentPage){
		ModelAndView view = new ModelAndView();
		Page page = new Page();
		page.setCurrentPage(currentPage);
		List<Appoint> list = appointService.findAppointListByPage(appoint, page);
		view.addObject("appointListByPage", list);
		view.addObject("page", page);
		view.setViewName("appoint");
		return view;
	}
	
	
}
