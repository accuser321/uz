package evo.dao;

import java.util.List;
import java.util.Map;

import evo.model.Visitor;

/**
  * 项目名称：uz
  * 类名称：VisitorMapper
  * 类描述：
  * 创建人：zoujun
  * 创建时间：2015-7-1 上午11:35:15
  * 修改人：zoujun
  * 修改时间：2015-7-1 上午11:35:15
  * 修改备注：
  * @version 
  */
public interface VisitorMapper {
	/**
	 * 根据手机号查询Visitor对象
	 */
    Visitor findVisitorByPhoneNum(String phoneNum);
    
    /**
	 * 插入Vistor对象
	 */
	public void insertVistor(Visitor visitor);
	
	/**
	 * 
	 * @Title: deleteVisitorByVisitorId
	 * @Description: 根据id删除来访者
	 * @author Demo demo_@evo_com
	 * @param @param visitor    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void deleteVisitorByVisitorId(Integer visitorId);
	

	/**
	* @Title: findAllByPage 
	* @Description: 分页查询来访者信息
	* @param parameter
	* @return 
	* List<Visitor>
	* @throws
	 */
	List<Visitor> findVisitorByPage(Map<String, Object> parameter);
	
	
	
	
	
	
	int deleteByPrimaryKey(Integer visitorId);

    int insert(Visitor record);

    int insertSelective(Visitor record);

    Visitor selectByPrimaryKey(Integer visitorId);

    int updateByPrimaryKeySelective(Visitor record);

    int updateByPrimaryKeyWithBLOBs(Visitor record);

    int updateByPrimaryKey(Visitor record);

	List<Visitor> findAllPhoneNum();

	List<Visitor> findAllVisitor();
}