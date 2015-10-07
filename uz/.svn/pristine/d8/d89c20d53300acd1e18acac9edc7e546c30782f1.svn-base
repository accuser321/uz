package evo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import evo.dao.AppointMapper;
import evo.model.Appoint;
import evo.util.Page;

@Service("appointService")
public class AppointServiceImpl implements IAppointService {

	@Autowired
	private AppointMapper appointMapper;
	
	private static final Logger logger = Logger.getLogger(AppointServiceImpl.class);
	
	/**
	 * 插入Appoint对象
	 */
	public void insertAppoint(Appoint appoint) {
		appointMapper.insertAppoint(appoint);
	}



	/**
	 * 删除预约信息(根据预约id)
	 */
	public void deleteAppointByAppointId(Integer appointId) {
		appointMapper.deleteAppointByAppointId(appointId);
		
	}
	
	/**
	 * 根据手机号修改预约信息
	 */
	public void updateAppointByPhoneNum(Appoint appoint){
		appointMapper.updateAppointByPhoneNum(appoint);
	}
	
	/**
	 * 查询所有预约信息
	 */
	public List<Appoint> findAppointList(){
		List<Appoint> list = appointMapper.findAppointList();
		return list;
	}
	
	/**
	 * 查询所有预约信息-分页
	 * important:分页时
	 */
	public List<Appoint> findAppointListByPage(Appoint appoint, Page page){
		Map<String,Object> parameter = new HashMap<String,Object>();
		Appoint at = new Appoint();
		
		if(StringUtils.isNotBlank(appoint.getName())){
			at.setName(appoint.getName());
		}
		if(StringUtils.isNotBlank(appoint.getCompanyName())){
			at.setCompanyName(appoint.getCompanyName());
		}
		if(StringUtils.isNotBlank(appoint.getPhoneNum())){
			at.setPhoneNum(appoint.getPhoneNum());
		}
		
		parameter.put("appoint", at);
		parameter.put("page", page);
		
		List<Appoint> list = appointMapper.findAppointListByPage(parameter);
//		for(Appoint ap : list){
//			
//		}
		return list;
	}

	/**
	 * 根据手机号查询Appoint信息
	 */
	public Appoint findAppointByPhoneNum(String phoneNum) {
		Appoint appoint = appointMapper.findAppointByPhoneNum(phoneNum);
		if(appoint != null){
			logger.info(JSON.toJSONStringWithDateFormat(appoint,"yyyy-MM-DD HH:mm:ss"));
			return appoint;
		}else{
			return null;
		}
	}
	

	
}
