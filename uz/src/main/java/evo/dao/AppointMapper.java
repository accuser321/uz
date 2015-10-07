package evo.dao;

import java.util.List;
import java.util.Map;

import evo.model.Appoint;

public interface AppointMapper {
    int deleteByPrimaryKey(Integer appointId);

    int insert(Appoint record);

    int insertSelective(Appoint record);

    Appoint selectByPrimaryKey(Integer appointId);

    int updateByPrimaryKeySelective(Appoint record);

    int updateByPrimaryKey(Appoint record);
    
	/**
	 * 插入Appoint对象
	 */
	public void insertAppoint(Appoint appoint);
	

	/**
	 * 删除预约信息(根据预约id)
	 */
	public void deleteAppointByAppointId(Integer appointId);
	
	/**
	 * 根据手机号修改预约信息
	 */
	public void updateAppointByPhoneNum(Appoint appoint);
	
	/**
	 * 查询所有预约信息
	 */
	public List<Appoint> findAppointList();
	
	/**
	 * 查询所有预约信息-分页
	 */
	public List<Appoint> findAppointListByPage(Map<String,Object> parameter);
	
	/**
	 * 根据手机号查询Appoint信息
	 */
	public Appoint findAppointByPhoneNum(String phoneNum);
}