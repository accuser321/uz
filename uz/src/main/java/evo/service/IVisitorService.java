package evo.service;

import java.util.List;

import evo.model.Visitor;
import evo.util.Page;

/**
  * 项目名称：uz
  * 类名称：IVisitorService
  * 类描述：
  * 创建人：zoujun
  * 创建时间：2015-7-1 上午11:13:09
  * 修改人：zoujun
  * 修改时间：2015-7-1 上午11:13:09
  * 修改备注：
  * @version 
  */
public interface IVisitorService {

	/**
	 * 根据手机号查询用户信息
	 */
	public Visitor findVisitorByPhoneNum(String phoneNum);
	
	/**
	 * 插入Vistor对象
	 */
	public void insertVistor(Visitor visitor);
	
	/**
	 * 分页查找所有访问人员
	 */
	public List<Visitor> findVisitorByPage(Page page,Visitor visitor);
	
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

	public List<Visitor> findAllPhoneNum();

	public List<Visitor> findAllVisitor();
	
}
