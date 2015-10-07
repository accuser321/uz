package evo.service;

import java.util.List;

import evo.model.UserMonthTime;
import evo.util.Page;

public interface IExportExcelService {

	/**
	 * 
	 * @Title: findUserMonthTimeList
	 * @Description: 查询UserMonthTime的List信息，导出功能
	 * @author Demo demo_@evo_com
	 * @param @return    设定文件
	 * @return List<UserMonthTime>    返回类型
	 * @throws
	 */
	public List<UserMonthTime> findUserMonthTimeList();	
	
	/**
	 * 根据条件查询信息-分页
	 */
	public List<UserMonthTime> findUserMonthTimeListByPage(UserMonthTime userMonthTime, Page page);
	
	
	/**
	 * 根据条件查询信息
	 */
	public List<UserMonthTime> findUserMonthTimeListByCondition(UserMonthTime userMonthTime);
	
	/**
	 * 
	 * @Title: insertUserMonthTime
	 * @Description: 插入数据
	 * @author Demo demo_@evo_com
	 * @param @param userMonthTime    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void insertUserMonthTime(UserMonthTime userMonthTime);
	
	/**
	 * 
	 * @Title: deleteUserMonthTimeById
	 * @Description: 根据id删除记录
	 * @author Demo demo_@evo_com
	 * @param @param id    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void deleteUserMonthTimeById(Integer id);
	
	/**
	 * 
	 * @Title: updateUserMonthTime
	 * @Description: 修改记录
	 * @author Demo demo_@evo_com
	 * @param @param userMonthTime    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void updateUserMonthTime(UserMonthTime userMonthTime);
	
	
	/**
	* @Title: findSingleById 
	* @Description: TODO 
	* void
	* @throws
	 */
	public  UserMonthTime findSingleById(Integer id);
	
	
	/**
	* @Title: deleteAll 
	* @Description: 清空 
	* void
	* @throws
	 */
	public void deleteAll();
	
	
	public int getExist(String str,String bigOrLow);
		
	
	
}
