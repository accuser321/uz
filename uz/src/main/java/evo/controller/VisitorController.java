package evo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import evo.model.Visitor;
import evo.service.IVisitorService;
import evo.util.Page;

/**
  * 项目名称：uz
  * 类名称：VisitorController
  * 类描述：
  * 创建人：zoujun
  * 创建时间：2015-7-1 上午11:56:08
  * 修改人：zoujun
  * 修改时间：2015-7-1 上午11:56:08
  * 修改备注：
  * @version 
  */
@Controller
@RequestMapping("/visitorController")
public class VisitorController {
	
	@Autowired
	private IVisitorService visitorService;
	
	/**
	 * 基于RestFul风格的访问方式 http://localhost:8080/uz/visitorController/15914141177/findVisitorByPhoneNum.do
	 * 
	 * 数据格式:{"visitor":{"companyName":"evo","createTime":"","fingerPath":"/system/finger","idNum":"230204198503270711",
	 * 		 "idPhoto":[],"modifyTime":"2015-06-19 11:02:04.0","name":"cc","phoneNum":"15914141177","sex":1,"visiable":1,
	 * 		 "visitorId":1}}
	 * @param phoneNum
	 * @param request
	 * important
	 * @return
	 */
	@RequestMapping("/{phoneNum}/findVisitorByPhoneNum")
	@ResponseBody
	public String findVisitorByPhoneNum(@PathVariable String phoneNum,HttpServletRequest request){
		
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		
		Visitor visitor = visitorService.findVisitorByPhoneNum(phoneNum);
		
		if(visitor != null && !visitor.equals("")){
			jsonMap.put("visitor", visitor);
			String string = JSONObject.fromObject(jsonMap).toString();
			return string;
		}else{
			return null;
		}
	}
	
	/**
	 * 基于RestFul风格的访问方式 http://localhost:8080/visitorController/findAllPhoneNum.do
	 * 判断此用户是否有指纹存在
	 */
	@RequestMapping("/findAllPhoneNum")
	@ResponseBody
	public String findAllPhoneNum(){
		List<Visitor> visitorList = visitorService.findAllPhoneNum();
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		JSONArray phoneArray = new JSONArray();
		for (Visitor visitor : visitorList) {
			phoneArray.add(visitor.getPhoneNum());
		}
		if(!phoneArray.isEmpty()){
			jsonMap.put("allPhoneNum", phoneArray);
			return JSONObject.fromObject(jsonMap).toString();
		}
		else{
			return null;
		}
	}
	/**
	 * 接收Android的数据插入vistor表
	 * 数据格式:{"visitor":{"companyName":"evo","createTime":"","fingerPath":"/system/finger","idNum":"230204198503270711",
	 * 		 "idPhoto":[],"idCode":"1111111111","modifyTime":"2015-06-19 11:02:04.0","name":"cc","phoneNum":"15914141177","sex":1,"visiable":1,
	 * 		 "visitorId":1}}
	 */
	/*	@RequestMapping("/insertVistorForAndroid")
	@ResponseBody
	public void insertVistorForAndroid(HttpServletRequest request){

		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
			
			JSONObject obj = JSONObject.fromObject(jb.toString());
			//String vistor = obj.getString("visitor");
			String companyName = obj.getString("companyName");
			String createTime = obj.getString("createTime");
			String fingerPath = obj.getString("fingerPath");
			String idNum = obj.getString("idNum");
			
			String idPhoto = obj.getString("idPhoto");
			String idCode = obj.getString("idCode");
			
			String modifyTime = obj.getString("modifyTime");
			String name = obj.getString("name");
			String phoneNum = obj.getString("phoneNum");
			int sex = obj.getInt("sex");
			
			int visiable = obj.getInt("visiable");

			Visitor vis = new Visitor();
			vis.setName(name);
			vis.setPhoneNum(phoneNum);
			vis.setFingerPath(fingerPath);
			vis.setIdNum(idNum);
			vis.setCompanyName(companyName);
			vis.setSex(sex);
			vis.setVisiable(visiable);
			vis.setModifyTime(modifyTime);
			vis.setCreateTime(createTime);
			vis.setIdPhoto(idPhoto.getBytes());
			vis.setIdCode(idCode);

		visitorService.insertVistor(vis);
			
		} catch (Exception e) {  report an error 
			e.printStackTrace();
		}

	}*/
	

	/**
	 * 接收第三方的数据插入vistor表
	 * 数据格式:{"visitor":{"companyName":"evo","createTime":"","fingerPath":"/system/finger","idNum":"230204198503270711",
	 * 		 "idPhoto":[],"id_code":"1111111111","modifyTime":"2015-06-19 11:02:04.0","name":"cc","phoneNum":"15914141177","sex":1,"visiable":1,
	 * 		 "visitorId":1}}
	 * important
	 * @throws IOException 
	 */
	@RequestMapping("/insertVistorForThird")
	public void insertVistorForThird(HttpServletRequest request,HttpServletResponse response){
		String jsonstr = "";
		try {
			jsonstr = ConvertStreamJson(request.getInputStream());
			//使用fastjon组装对象
			Visitor visiter = (Visitor) JSON.parseObject(
					jsonstr, Visitor.class);
			visitorService.insertVistor(visiter);
			//页面返回
			response.getWriter().print("{success:'true'}");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * http将inputstream转成string
	 * @param inputStream
	 * important
	 * @return
	 */
	private String ConvertStreamJson(ServletInputStream inputStream) {
		String jsonStr = "";
		// ByteArrayOutputStream相当于内存输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		// 将输入流转移到内存输出流中
		try {
			while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, len);
			}
			// 将内存流转换为字符串
			jsonStr = new String(out.toByteArray(),"utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}
	
	/**
	* @Title: findAll 
	* @Description: TODO
	* @param visitor
	* @param currentPage
	* @return 
	* String
	* @throws
	 */
//	@RequestMapping("/getAllVistor/{currentPage}")
//	public ModelAndView findAll(Visitor visitor,@PathVariable int currentPage,HttpServletRequest request){
//		ModelAndView view = new ModelAndView();
//		Page page = new Page();
//		page.setCurrentPage(currentPage);
//		List<Visitor> reslutList = visitorService.findAllByPage(page,visitor);
//		view.addObject("list", reslutList);
//		view.addObject("page", page);
//		view.addObject("visitor", visitor);
//		view.setViewName("vistorlist");
//		return view;
//	} 
//	@RequestMapping("/modify/{id}")
//	public String modifyVisitor(Visitor visitor,@PathVariable int currentPage,HttpServletRequest request){
	/*	ModelAndView view = new ModelAndView();
		Page page = new Page();
		page.setCurrentPage(currentPage);
		List<Visitor> reslutList = visitorService.findAll(page,visitor);
		view.addObject("list", reslutList);
		view.addObject("page", page);
		view.addObject("visitor", visitor);
		view.setViewName("vistorlist");*/
//		return "redirect:/getAllVistor/0";
//	} 
//	@RequestMapping("/delete/{id}")
//	public String deleteVisitor(Visitor visitor,@PathVariable int currentPage,HttpServletRequest request){
		/*ModelAndView view = new ModelAndView();
		Page page = new Page();
		page.setCurrentPage(currentPage);
		List<Visitor> reslutList = visitorService.findAll(page,visitor);
		view.addObject("list", reslutList);
		view.addObject("page", page);
		view.addObject("visitor", visitor);
		view.setViewName("vistorlist");*/
//		return "redirect:/getAllVistor/0";
//	} 
//	
//	@RequestMapping("/addVisitor")
//	public String addVisitor(Visitor visitor){
		/*ModelAndView view = new ModelAndView();
		Page page = new Page();
		page.setCurrentPage(currentPage);
		List<Visitor> reslutList = visitorService.findAll(page,visitor);
		view.addObject("list", reslutList);
		view.addObject("page", page);
		view.addObject("visitor", visitor);
		view.setViewName("vistorlist");*/
//		return "redirect:/getAllVistor/0";
//	} 
	
	/**
	 * 
	 * @Title: insertVistor
	 * @Description: 插入Vistor对象
	 * @author Demo demo_@evo_com
	 * @param @param visitor
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	@RequestMapping("/insertVistor")
	public String insertVistor(Visitor visitor){
		visitorService.insertVistor(visitor);
		return "";
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @Title: deleteVisitorByVisitorId
	 * @Description: 根据id删除来访者
	 * @author Demo demo_@evo_com
	 * @param @param visitor
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	@RequestMapping("/deleteVisitorByVisitorId/{visitorId}")
	public void deleteVisitor(@PathVariable Integer visitorId, HttpServletResponse response) throws Exception{
		visitorService.deleteVisitorByVisitorId(visitorId);
		response.getWriter().print("{msg:'t'}");
	}
	
	
	
	/**
	 * 
	 * @Title: findVisitorByPhoneNum
	 * @Description: 根据手机号查询Visitor对象
	 * @author Demo demo_@evo_com
	 * @param @param phoneNum
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("/findVisitorByPhoneNum")
	public ModelAndView findVisitorByPhoneNum(String phoneNum){
		ModelAndView view = new ModelAndView();
		Visitor visitor = visitorService.findVisitorByPhoneNum(phoneNum);
		view.addObject("visitor", visitor);
		view.setViewName("");
		return view;
	}
	
	/**
	 * 
	 * @Title: findVisitorByPage
	 * @Description: 查询所有访问人员List -分页
	 * @author Demo demo_@evo_com
	 * @param @param phoneNum
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("/findVisitorByPage/{currentPage}")
	public ModelAndView findVisitorByPage(Visitor visitor,
			@PathVariable int currentPage, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		Page page = new Page();
		page.setCurrentPage(currentPage);
		List<Visitor> list = visitorService.findVisitorByPage(page, visitor);
				
		view.addObject("visitorListByPage", list);
		view.addObject("page", page);
		view.addObject("visitor", visitor);
		view.setViewName("registerInfo");
		return view;
	}
	
	
	
	
}
