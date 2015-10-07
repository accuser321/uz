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

@Service("visitorService")
public class VisitorServiceImpl implements IVisitorService {

	@Autowired
	private VisitorMapper visitorMapper;
	@Autowired
	private VisitorRegisterMapper visitorRegisterMapper;
	
	private static final Logger logger = Logger.getLogger(VisitorServiceImpl.class);
	
	/**
	 * 根据手机号查询Visitor对象
	 */
	public Visitor findVisitorByPhoneNum(String phoneNum) {
		Visitor visitor = visitorMapper.findVisitorByPhoneNum(phoneNum);
		if(visitor != null){
			logger.info(JSON.toJSONStringWithDateFormat(visitor,"yyyy-MM-DD HH:mm:ss"));
			return visitor;
		}else{
			return null;
		}
		
	}
	
	/**
	 * 插入Vistor对象
	 */
	public void insertVistor(Visitor visitor){
		visitorMapper.insertVistor(visitor);
		
	}

	
	/**
	 * 
	 * @Title: deleteVisitorByVisitorId
	 * @Description: 根据id删除来访者
	 * @author Demo demo_@evo_com
	 * @param @param visitor    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void deleteVisitorByVisitorId(Integer visitorId){
		
		List<VisitorRegister> list = visitorRegisterMapper.findVisitorRegisterListByVisitorId(visitorId);
		if(list.size()>0){
			for(VisitorRegister vr : list){
				Integer registerId = vr.getRegisterId();
				visitorRegisterMapper.deleteVisitorRegisterByVisitorRegisterId(registerId);
			}
		}
		visitorMapper.deleteVisitorByVisitorId(visitorId);
		
	}
	
	
	/**
	 * 
	 * @Title: findVisitorByPage
	 * @Description: 查找所有访问人员List-分页
	 * @author Demo demo_@evo_com
	 * @param @return    设定文件
	 * @return List<SysUser>    返回类型
	 * important
	 * @throws
	 */
	@Override
	public List<Visitor> findVisitorByPage(Page page, Visitor visitor) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		Visitor v = new Visitor();
		
		if(StringUtils.isNotBlank(visitor.getName())){
			v.setName(visitor.getName());
		}
		if(StringUtils.isNotBlank(visitor.getPhoneNum())){
			v.setPhoneNum(visitor.getPhoneNum());
		}
		if(StringUtils.isNotBlank(visitor.getCompanyName())){
			v.setCompanyName(visitor.getCompanyName());
		}
		
		parameter.put("vis", v);
		parameter.put("page", page);
		List<Visitor> list = visitorMapper.findVisitorByPage(parameter);
		for (Visitor vt : list) {
			if(vt.getCreateTime()!=null)
			vt.setCreateTime(vt.getCreateTime().substring(0, vt.getCreateTime().lastIndexOf(".")));
			if(vt.getModifyTime()!=null)
			vt.setModifyTime(vt.getModifyTime().substring(0, vt.getModifyTime().lastIndexOf(".")));
		}
		logger.info("@@@@@"+JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
		return list;
	}

	@Override
	public List<Visitor> findAllPhoneNum() {
		return visitorMapper.findAllPhoneNum();
	}

	@Override
	public List<Visitor> findAllVisitor() {
		return visitorMapper.findAllVisitor();
	}
}
