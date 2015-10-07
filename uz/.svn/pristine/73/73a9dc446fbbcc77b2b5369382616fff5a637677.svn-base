package evo.dao;

import java.util.List;
import java.util.Map;

import evo.model.UserMonthTime;

public interface UserMonthTimeMapper {
    
	/**
	 * 查询所有信息
	 */
    public List<UserMonthTime> findUserMonthTimeList();
    public List<UserMonthTime> findUserMonthTimeListByPage(Map<String, Object> parameter);
    public List<UserMonthTime> findUserMonthTimeListByCondition(Map<String, Object> parameter);
    
    
    public List<UserMonthTime> findUserMonthByTimeList(Map<String, Object> parameter);
    /**
     * 插入数据 
     */
    public void insertUserMonthTime(UserMonthTime userMonthTime);
    
    /**
     * 根据id删除记录
     */
    public void deleteUserMonthTimeById(Integer id);
    
    /**
     * 修改记录
     */
    public void updateUserMonthTime(UserMonthTime userMonthTime);
    
    /**
     * 根据主键查询对象
     */
    UserMonthTime selectByPrimaryKey(Integer appointId);
}