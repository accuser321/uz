package evo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import evo.model.SysDepartment;
import evo.service.ISysDepartMentService;
import evo.util.Page;

/**
  * 项目名称：uz
  * 类名称：DepartmentControll
  * 类描述：
  * 创建人：zoujun
  * 创建时间：2015-7-10 下午2:00:16
  * 修改人：zoujun
  * 修改时间：2015-7-10 下午2:00:16
  * 修改备注：
  * @version 
  */
@Controller
@RequestMapping("/departmentControll")
public class DepartmentControll {

	@Autowired
	private ISysDepartMentService  iSysDepartMentService;
	/**
	 * 
	* @Title: addDepartment 
	* @Description: 添加部门
	* @param sysDepartment
	* @return 
	* String
	* @throws
	 */
	@RequestMapping("/addDepartment")
	public String addDepartment(SysDepartment sysDepartment){
		iSysDepartMentService.addSysDepart(sysDepartment);
		
		
		
		return "redirect:findAllDepartment/0.do";
	}
	/**
	 * @throws Exception 
	 * 
	* @Title: addDepartment 
	* @Description: 删除
	* @param sysDepartment
	* @return 
	* String
	* @throws
	 */
	@RequestMapping("/delDepartment/{deptId}")
	public void delDepartment(@PathVariable Integer deptId,HttpServletResponse response) throws Exception{
		SysDepartment sysDepartment = new SysDepartment();
		sysDepartment.setDeptId(deptId);
		iSysDepartMentService.deleteSysDepart(sysDepartment);
		response.getWriter().print("{msg:'t'}");
	}
	/**
	 * 
	* @Title: addDepartment 
	* @Description: 修改
	* @param sysDepartment
	* @return 
	* String
	* @throws
	 */
	@RequestMapping("/modifyDepartment")
	public String modifyDepartment(SysDepartment sysDepartment){
		iSysDepartMentService.updateSysDepart(sysDepartment);
		return "redirect:findAllDepartment/0.do";
	}
	/**
	 * 
	* @Title: addDepartment 
	* @Description: 查询单个
	* @param sysDepartment
	* @return 
	* String
	* @throws
	 */
	@RequestMapping("/searchDepartment")
	public void searchDepartment(SysDepartment sysDepartment,HttpServletResponse response ){
		SysDepartment singleDepartment = iSysDepartMentService.findSingleById(sysDepartment.getDeptId());
		List<SysDepartment> departList = iSysDepartMentService.findSysDepartmentList();
		JSONObject objectList = new JSONObject();
		objectList.put("singleDepartment", singleDepartment);
		objectList.put("departList", departList);
		try {
			response.getWriter().print(JSONObject.fromObject(objectList).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	* @Title: addDepartment 
	* @Description: 分页查询
	* @param sysDepartment
	* @return 
	* String
	* @throws
	 */
	@RequestMapping("/findAllDepartment/{currentPage}")
	public ModelAndView findAllDepartmentByPage(SysDepartment sysDepartment,@PathVariable int currentPage){
		ModelAndView view = new ModelAndView();
		Page page = new Page();
		page.setCurrentPage(currentPage);
		List<SysDepartment> list = iSysDepartMentService.findAllSysDepartmentListByPage(sysDepartment, page);
		List<SysDepartment> departList = iSysDepartMentService.findSysDepartmentList();
		view.addObject("list", list);
		view.addObject("departList", departList);
		view.addObject("page", page);
		view.setViewName("department");
		return view;
	}
}
