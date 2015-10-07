package evo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evo.dao.UserMonthDAO;
import evo.dao.UserMonthTimeMapper;
import evo.model.UserMonthTime;
import evo.util.Page;

@Service("exportExcelService")
public class ExportExcelServiceImpl implements IExportExcelService {

	@Autowired
	private UserMonthTimeMapper userMonthTimeMapper;
	
	@Autowired
	private UserMonthDAO userMonthdao;
	
	
//	@Autowired
//	private SysUserMapper sysUserMapper;
	/**
	 * 查询所有信息
	 */
	@Override
	public List<UserMonthTime> findUserMonthTimeList() {
		List<UserMonthTime> list = userMonthTimeMapper.findUserMonthTimeList();
		return list;
	}

	/**
	 * 根据条件查询信息-分页
	 */
	@Override
	public List<UserMonthTime> findUserMonthTimeListByPage(UserMonthTime userMonthTime, Page page) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("umt", userMonthTime);
		parameter.put("page", page);
		List<UserMonthTime> list = userMonthTimeMapper.findUserMonthTimeListByPage(parameter);
		return list;
	}
	
	/**
	 * 根据条件查询信息
	 */
	public List<UserMonthTime> findUserMonthTimeListByCondition(UserMonthTime userMonthTime){
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("umt", userMonthTime);
		List<UserMonthTime> list = userMonthTimeMapper.findUserMonthByTimeList(parameter);
		return list;
	}
	
	
	/**
	 * 插入数据
	 */
	@Override
	public void insertUserMonthTime(UserMonthTime userMonthTime) {
		userMonthTimeMapper.insertUserMonthTime(userMonthTime);
	}

	/**
	 * 根据id删除记录
	 */
	@Override
	public void deleteUserMonthTimeById(Integer id) {
		userMonthTimeMapper.deleteUserMonthTimeById(id);
	}

	/**
	 * 修改记录
	 */
	@Override
	public void updateUserMonthTime(UserMonthTime userMonthTime) {
		userMonthTimeMapper.updateUserMonthTime(userMonthTime);
	}

	@Override
	public UserMonthTime findSingleById(Integer id) {
		return userMonthTimeMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteAll() {
		userMonthdao.deleteAll();
	}

	@Override
	public int getExist(String str, String bigOrLow) {
		if(bigOrLow.equals("big")){
			return userMonthdao.FindMaxTime(str);
		}
		return userMonthdao.FindMinTime(str);
	}
}
