package evo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import evo.dao.VisitorMapper;
import evo.dao.VisitorRegisterMapper;
import evo.model.Visitor;
import evo.model.VisitorRegister;
import evo.util.Page;

@Service("visitorRegisterService")
public class VisitorRegisterImpl implements IVisitorRegisterService {

	@Autowired
	private VisitorRegisterMapper visitorRegisterMapper;
	
	@Autowired
	private VisitorMapper visitorMapper;
	
	private static final Logger logger = Logger.getLogger(VisitorRegisterImpl.class);
	
	/**
	 * 查询所有访问者记录
	 */
	public List<VisitorRegister> findVisitorRegisterList() {
		
		List<VisitorRegister> list = visitorRegisterMapper.findVisitorRegisterList();
		
		return list;
	}
	
	public List<VisitorRegister> findAll(Map map) {
		
		List<VisitorRegister> list = visitorRegisterMapper.findAll(map);
		return list;
	}

	/**
	 * 根据访问者的手机号查询访问者记录List
	 */
	@Override
	public List<VisitorRegister> findVisitorRegisterByVisitorList(String phoneNum) {
		
		List<VisitorRegister> list = visitorRegisterMapper.findVisitorRegisterByVisitorList(phoneNum);
		logger.info(JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
		return list;
	}

	/**
	 * 
	 * @Title: findVisitorRegisterListByVisitorId
	 * @Description: 根据visitorId查询访问记录List
	 * @author Demo demo_@evo_com
	 * @param @param registerId
	 * @param @return    设定文件
	 * @return List<VisitorRegister>    返回类型
	 * @throws
	 */
	public List<VisitorRegister> findVisitorRegisterListByVisitorId(Integer visitorId){
		List<VisitorRegister> list = visitorRegisterMapper.findVisitorRegisterListByVisitorId(visitorId);
		logger.info(JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
		return list;
	}

	/**
	 * 
	 * @Title: deleteVisitorRegisterByVisitorRegisterId
	 * @Description: 根据VisitorRegisterId删除访问记录
	 * @author Demo demo_@evo_com
	 * @param @param visitorRegisterId    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void deleteVisitorRegisterByVisitorRegisterId(Integer visitorRegisterId){
		visitorRegisterMapper.deleteVisitorRegisterByVisitorRegisterId(visitorRegisterId);
	}
	
	
	/**
	 * 
	 * @Title: insertVisitorRegister
	 * @Description: 插入访问记录
	 * @author Demo demo_@evo_com
	 * @param @param visitorRegister    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void insertVisitorRegister(VisitorRegister visitorRegister){
		visitorRegisterMapper.insertVisitorRegister(visitorRegister);
	}
	
	/**
	 * 
	 * @Title: findVisitorRegisterListByPage
	 * @Description: 查询所有来访记录List-分页
	 * @author Demo demo_@evo_com
	 * @param @param visitorRegister
	 * @param @param page
	 * @param @return    设定文件
	 * @return List<VisitorRegister>    返回类型
	 * @throws
	 */
	public List<VisitorRegister> findVisitorRegisterListByPage(VisitorRegister visitorRegister,Page page){
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		VisitorRegister vr = new VisitorRegister();
//		if(visitorRegister.getVisitors().getVisitorId() != null){
//			Visitor visitor = visitorMapper.selectByPrimaryKey(visitorRegister.getVisitorId());
//			vr.setVisitorId(visitorRegister.getVisitorId());
//		}
		
		if(StringUtils.isNotBlank(visitorRegister.getVisitorName())){
			vr.setVisitorName(visitorRegister.getVisitorName());
		}
		if(StringUtils.isNotBlank(visitorRegister.getVisotorCompanyName())){
			vr.setVisotorCompanyName(visitorRegister.getVisotorCompanyName());
		}
		if(StringUtils.isNotBlank(visitorRegister.getVisitorPhoneNum())){
			vr.setVisitorPhoneNum(visitorRegister.getVisitorPhoneNum());
		}
		if(StringUtils.isNotBlank(visitorRegister.getStarff())){
			vr.setStarff(visitorRegister.getStarff());
		}
		if(StringUtils.isNotBlank(visitorRegister.getStartTime())){
			String sTime = visitorRegister.getStartTime() + " 00:00:00";
			vr.setStartTime(sTime);
		}
		if(StringUtils.isNotBlank(visitorRegister.getEndTime())){
			String eTime = visitorRegister.getEndTime() + " 23:59:59";
			vr.setEndTime(eTime);
		}
		if(visitorRegister.getVisitorId()!=null){
			Visitor selectByPrimaryKey = visitorMapper.selectByPrimaryKey(visitorRegister.getVisitorId());
			vr.setVisitorId(selectByPrimaryKey.getVisitorId());
		}
		
		parameter.put("page", page);
		parameter.put("visitorregister", vr);
		List<VisitorRegister> list = visitorRegisterMapper.findVisitorRegisterListByPage(parameter);
		for(VisitorRegister v : list){
			Visitor visitor = visitorMapper.selectByPrimaryKey(v.getVisitorId());
			v.setVisitorName(visitor.getName());
			v.setVisitorPhoneNum(visitor.getPhoneNum());
			v.setVisotorCompanyName(visitor.getCompanyName());
			v.setStartTime(v.getRegisterTime().substring(0, v.getRegisterTime().lastIndexOf(".")));
			v.setEndTime(v.getRegisterTime().substring(0, v.getRegisterTime().lastIndexOf(".")));
			v.setStarff(visitor.getStaff().toString());
		}
		return list;
	}
	
}
