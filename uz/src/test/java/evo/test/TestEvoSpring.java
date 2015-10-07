

package evo.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.FileCopyUtils;

import com.alibaba.fastjson.JSON;

import evo.model.Appoint;
import evo.model.SysDepartment;
import evo.model.SysRight;
import evo.model.SysRole;
import evo.model.SysUser;
import evo.model.Visitor;
import evo.model.VisitorRegister;
import evo.service.IAppointService;
import evo.service.ISysDepartMentService;
import evo.service.ISysRightService;
import evo.service.ISysRoleService;
import evo.service.ISysUserService;
import evo.service.IVisitorRegisterService;
import evo.service.IVisitorService;
import evo.util.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml"})
public class TestEvoSpring {
	private static final Logger logger = Logger.getLogger(TestEvoSpring.class);
	
	@Autowired
	private ISysRoleService sysRoleService;
	
	@Autowired
	private ISysUserService sysUserService;
	
	@Autowired
	private ISysRightService sysRightService;
	
	@Autowired
	private IVisitorService visitorService;
	
	@Autowired
	private IAppointService appointService;
	
	@Autowired
	private IVisitorRegisterService visitorRegisterService;
	
	@Autowired
	private ISysDepartMentService sysDepartMentService;
	
	
	
	/**
	 * 功能:查询一个角色(One)下的所有用户(N)信息
	 */
	@Test
	public void test1(){
		List<SysRole> list = sysRoleService.findSysRoleList();
		logger.info("^^^^^^^^^^"+JSON.toJSONString(list));
		for(SysRole sysrole : list){
			List<SysUser> sysUsers = sysrole.getSysUsers();
			SysUser sysUser = sysUsers.get(0);
			System.out.println("sysUser对象是:"+sysUser);
		}
		
	}
	
	
	/**
	 * 登陆
	 */
	@Test
	public void test2(){
		SysUser sysuser = sysUserService.findSysUserByNameAndPass("yx1", "123");
		//在log4j中，将对象转为json字符串，方便查看
		logger.info("^^^^^^^^^^"+JSON.toJSONStringWithDateFormat(sysuser,"yyyy-MM-DD HH:mm:ss"));
	}
	
	/**
	 * 根据userName和userPass查询角色信息
	 * {"roleId":2,
	 * "sysRoles":{"roleDesc":"使用查看权限","roleId":2,"roleName":"客服人员"},
	 * "userAble":1,"userCreateTime":"2015-06-159 15:47:42","userId":2,"userName":"yx2","userPass":"123","userPhone":"18710010835","userSex":2}
	 */
	@Test
	public void test3(){
		SysUser sysrole = sysUserService.findSysRoleByUserNameAndUserPass("admin","123");
		logger.info("^^^^^^^^^^"+JSON.toJSONStringWithDateFormat(sysrole,"yyyy-MM-DD HH:mm:ss"));
	}
	
	/**
	 * 多对多关系
	 * 根据角色id查询权限信息List
	 * [
		{"rightId":2,"rightParentId":"ROOT_MENU","rightText":"车辆管理","rightType":"0","rightUrl":"index.jsp"},
		{"rightId":3,"rightParentId":"2","rightText":"车辆二级目录A","rightType":"1","rightUrl":"index.jsp"},
		{"rightId":4,"rightParentId":"3","rightText":"车辆三级目录","rightType":"1","rightUrl":"index.jsp"},
		{"rightId":7,"rightParentId":"2","rightText":"车辆二级目录B","rightType":"1","rightUrl":"index.jsp"},
		{"rightId":8,"rightParentId":"2","rightText":"车辆二级目录C","rightType":"1","rightUrl":"index.jsp"}
		]
	 */
	@Test
	public void test4(){
		List<SysRight> list = sysRightService.findSysRightByRoleId(1);
		logger.info("^^^^^^^^^^"+JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
	}
	
	/**
	 * 根据sys_right表中得rightId,查询rightParentId=rightId的List数据
	 * 此list表明有三级目录
	 * [
	 * {"rightId":4,"rightParentId":"3","rightText":"车辆三级目录A","rightType":"1","rightUrl":"index.jsp"},
	 * {"rightId":9,"rightParentId":"3","rightText":"车辆三级目录B","rightType":"1","rightUrl":"index.jsp"}
	 * ]
	 */
	@Test
	public void test5(){
		List<SysRight> list = sysRightService.findSysRightListByRightId(4);
		logger.info("^^^^^^^^^^"+JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
	}
	
	/**
	 * 插入Vistor记录
	 */
	@Test
	public void test6(){
		Visitor visitor = new Visitor();
		visitor.setName("iie");
		visitor.setPhoneNum("15914141186");
		visitor.setIdNum("230202020202020202");
		visitor.setCompanyName("evo");
		visitor.setSex(1);
		visitor.setVisiable(1);
		visitor.setModifyTime("2015-06-09 10:06:52");
		visitor.setCreateTime("2015-06-09 10:06:52");
		visitor.setStaff(0);
		visitor.setFinger1Path("/finger1");
		visitor.setFinger2Path("/finger2");
		visitor.setFinger3Path("/finger3");
		//插入图片,存入数据库的blob中
		BufferedInputStream in = new BufferedInputStream(getClass().getResourceAsStream("/1.jpg"));  
		try {
			byte[] b = FileCopyUtils.copyToByteArray(in);
//			visitor.setFinger1Photo(b);
//			visitor.setFinger2Photo(b);
//			visitor.setFinger3Photo(b);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		visitorService.insertVistor(visitor);
	}
	
	/**
	 * 用户注册
	 */
	@Test
	public void test7(){
		SysUser sysuser = new SysUser();
		sysuser.setRoleId(1);
		sysuser.setDeptId(1);
		sysuser.setUserName("yx9");
		sysuser.setUserPass("129");
		//sysuser.setUserCreateTime("2015-06-09 10:06:52");
		sysuser.setUserPhone("15914141177");
		sysuser.setUserSex(1);
		//sysuser.setUserAble(1);
		sysuser.setUserAddress("宝安");
		//sysuser.setUserBirthday("2015-06-09");
		sysUserService.insertSysUser(sysuser);
		
	}
	
	/**
	 * 删除用户(根据用户id)
	 */
	@Test
	public void test8(){
		sysUserService.deleteSysUserByUserId(2);
	}
	
	/**
	 * 根据用户名修改密码
	 */
	@Test
	public void test9(){
		SysUser sysuser = new SysUser();
		sysuser.setRoleId(1);
		sysuser.setDeptId(1);
		sysuser.setUserName("yx1");
		sysuser.setUserPass("111222333");
		sysuser.setUserCreateTime("2015-06-09 10:06:52");
		sysuser.setUserPhone("15914141177");
		sysuser.setUserSex(1);
		sysuser.setUserAble(1);
		sysUserService.updateSysUserByName(sysuser);
	}
	
	/**
	 * 插入Appoint预约对象
	 */
	@Test
	public void test10(){
		Appoint appoint = new Appoint();
		appoint.setCompanyName("evo");
		appoint.setIdNum("230202020202020202");
		appoint.setPhoneNum("15914141177");
		appoint.setName("yanxin");
		appoint.setSex(1);
		appointService.insertAppoint(appoint);
	}
	
	/**
	 * 删除预约信息(根据预约id)
	 */
	@Test
	public void test11(){
		appointService.deleteAppointByAppointId(2);
	}
	
	/**
	 * 根据手机号修改预约信息
	 */
	@Test
	public void test12(){
		Appoint appoint =new Appoint();
		appoint.setCompanyName("evo");
		appoint.setIdNum("230101010101010101");
		appoint.setName("xx");
		appoint.setPhoneNum("15914141177");
		appoint.setSex(1);
		appointService.updateAppointByPhoneNum(appoint);
	}
	
	/**
	 * 查询所有预约信息
	 */
	@Test
	public void test13(){
		List<Appoint> list = appointService.findAppointList();
		logger.info("^^^^^^^^^^"+JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
	}
	
	/**
	 * 查询所有预约信息-分页
	 */
//	@Test
//	public void test16(){
//		Page page = new Page();
//		page.setCurrentPage(1);
//		List<Appoint> list = appointService.findAppointListByPage("x","15914141177",page);
//		logger.info("@@@@@@"+JSON.toJSONStringWithDateFormat(list, "yyyy-MM-DD HH:mm:ss"));
//	}
	
	/**
	 * 查询所有访问者记录
	 */
	@Test
	public void test14(){
		List<VisitorRegister> list = visitorRegisterService.findVisitorRegisterList();
		logger.info("^^^^^^^^^^"+JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
	}
	
	/**
	 * 根据访问者的手机号查询访问者记录List
	 */
	@Test
	public void test15(){
		List<VisitorRegister> list = visitorRegisterService.findVisitorRegisterByVisitorList("15914141177");
		logger.info("^^^^^^^^^^"+JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
	}
	
	/**
	 * 
	 * @Title: findSysUserList
	 * @Description: 查询所以用户信息List
	 * @author Demo demo_@evo_com
	 * @param @return    设定文件
	 * @return List<SysUser>    返回类型
	 * @throws
	 */
	@Test
	public void test17(){
		List<SysUser> list = sysUserService.findSysUserList();
		logger.info("^^^^^^^^^^"+JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
	}
	
	/**
	 * 
	 * @Title: findSysUserListByPage
	 * @Description: 查询所以用户信息List-分页
	 * @author Demo demo_@evo_com
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Test
	public void test18(){
		Page page = new Page();
		page.setCurrentPage(1);
		SysUser sysUser = new SysUser();
		sysUser.setUserName("yx2");
		List<SysUser> list = sysUserService.findSysUserListByPage(page, sysUser);
		logger.info("^^^^^^^^^^"+JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
	}
	
	/**
	 * 
	 * @Title: addSysRole
	 * @Description: 创建角色
	 * @author Demo demo_@evo_com
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Test
	public void test19(){
		SysRole sysRole = new SysRole();
		sysRole.setRoleId(4);
		sysRole.setRoleName("测试人员");
		sysRole.setRoleDesc("测试");
		sysRoleService.addSysRole(sysRole);
		
	}
	
	/**
	 * 
	 * @Title: deleteSysRole
	 * @Description: 删除角色
	 * @author Demo demo_@evo_com
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Test
	public void test20(){
		sysRoleService.deleteSysRole(3);
		
	}
	
	/**
	 * 
	 * @Title: updateSysRole
	 * @Description: 修改角色信息
	 * @author Demo demo_@evo_com
	 * @param @param sysRole    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Test
	public void test21(){
		SysRole sysRole = new SysRole();
		sysRole.setRoleId(3);
		sysRole.setRoleName("汽车管理");
		sysRole.setRoleDesc("修改测试");
		sysRoleService.updateSysRole(sysRole);
		
	}
	
	/**
	 * 
	 * @Title: addSysDepart
	 * @Description: 增加部门
	 * @author Demo demo_@evo_com
	 * @param @param sysDepartment    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Test
	public void test22(){
		SysDepartment sysDepartment = new SysDepartment();
		sysDepartment.setDeptId(3);
		sysDepartment.setDeptName("测试部门");
		sysDepartMentService.addSysDepart(sysDepartment);
	}
	
	/**
	 * 
	 * @Title: deleteSysDepart
	 * @Description: 删除部门
	 * @author Demo demo_@evo_com
	 * @param @param sysDepartment    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Test
	public void test23(){
		SysDepartment sysDepartment = new SysDepartment();
		sysDepartment.setDeptId(3);
		sysDepartMentService.deleteSysDepart(sysDepartment);
	}
	
	/**
	 * 
	 * @Title: updateSysDepart
	 * @Description: 修改部门信息
	 * @author Demo demo_@evo_com
	 * @param @param sysDepartment    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Test
	public void test24(){
		SysDepartment sysDepartment = new SysDepartment();
		sysDepartment.setDeptId(3);
		sysDepartment.setDeptName("升级部门");
		sysDepartMentService.updateSysDepart(sysDepartment);
	}
	
	/**
	 * 
	 * @Title: findSysDepartmentList
	 * @Description: 查询部门信息列表
	 * @author Demo demo_@evo_com
	 * @param @param sysDepartment    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Test
	public void test25(){
		List<SysDepartment> list = sysDepartMentService.findSysDepartmentList();
		logger.info("^^^^^^^^^^"+JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
	}
	
	/**
	 * 
	 * @Title: findSysDepartmentBySysUserId
	 * @Description: 根据用户id查询部门信息
	 * @author Demo demo_@evo_com
	 * @param @param sysUser
	 * @param @return    设定文件
	 * @return SysDepartment    返回类型
	 * @throws
	 */
	@Test
	public void test26(){
		SysUser sysuser = new SysUser();
		sysuser.setUserId(1);
		SysUser user = sysUserService.findSysDepartmentBySysUserId(sysuser);
		logger.info("^^^^^^^^^^"+JSON.toJSONStringWithDateFormat(user,"yyyy-MM-DD HH:mm:ss"));
	}
	
	/**
	 * 
	 * @Title: updateSysUser
	 * @Description: 修改用户信息
	 * @author Demo demo_@evo_com
	 * @param @param sysUser    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Test
	public void test27(){
		SysUser sysuser = new SysUser();
		sysuser.setUserAddress("家");
		sysuser.setUserName("hehe");
		sysuser.setUserId(1);
		sysUserService.updateSysUser(sysuser);
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
	@Test
	public void test28(){
		
		visitorService.deleteVisitorByVisitorId(1);;
	}
	
	/**
	 * 
	 * @Title: findVisitorRegisterListByVisitorId
	 * @Description: 根据visitorId查询访问记录List
	 * @author Demo demo_@evo_com
	 * @param @param visitorId
	 * @param @return    设定文件
	 * @return List<VisitorRegister>    返回类型
	 * @throws
	 */
	@Test
	public void test29(){
		
		visitorRegisterService.findVisitorRegisterListByVisitorId(1);
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
	@Test
	public void test30(){
		Page page = new Page();
		page.setCurrentPage(1);
		Visitor visitor = new Visitor();
		visitor.setName("xingxing");
		visitor.setPhoneNum("18664188628");
		visitor.setCompanyName("evo");
		visitorService.findVisitorByPage(page, visitor);
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
	@Test
	public void test31(){
		VisitorRegister visitorRegister = new VisitorRegister();
		//visitorRegister.setRegisterId();
		visitorRegister.setRegisterTime("2015-06-09 10:06:52");
		visitorRegister.setVisitorId(11);
		visitorRegisterService.insertVisitorRegister(visitorRegister);
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
	@Test
	public void test32(){
		visitorRegisterService.deleteVisitorRegisterByVisitorRegisterId(8);
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
	@Test
	public void test33(){
		Page page = new Page();
		page.setCurrentPage(1);
		VisitorRegister visitorRegister = new VisitorRegister();
		visitorRegister.setVisotorCompanyName("xingxing");
		List<VisitorRegister> list = visitorRegisterService.findVisitorRegisterListByPage(visitorRegister, page);
		logger.info("^^^^^^^^^^"+JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
	}
	
	/**
	 * 
	 * @Title: findSysRoleListByPage
	 * @Description: 查询所有角色信息List-分页
	 * @author Demo demo_@evo_com
	 * @param @param sysRole
	 * @param @param page
	 * @param @return    设定文件
	 * @return List<SysRole>    返回类型
	 * @throws
	 */
	@Test
	public void test34(){
		Page page = new Page();
		page.setCurrentPage(2);
		SysRole sysRole = new SysRole();
		sysRole.setRoleName("管理员");
		List<SysRole> list = sysRoleService.findSysRoleListByPage(sysRole, page);
		logger.info("^^^^^^^^^^"+JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
	}
	
	/**
	 * 
	 * @Title: findSysUserByDepartId
	 * @Description: 根据部门id查询用户信息List
	 * @author Demo demo_@evo_com
	 * @param @param sysDepartment
	 * @param @return    设定文件
	 * @return List    返回类型
	 * @throws
	 */
	@Test
	public void test35(){
		SysDepartment sysDepartMent = new SysDepartment();
		sysDepartMent.setDeptId(2);
		List<SysDepartment> list = sysUserService.findSysUserByDepartId(sysDepartMent);
		logger.info("^^^^^^^^^^"+JSON.toJSONStringWithDateFormat(list,"yyyy-MM-DD HH:mm:ss"));
	}
	
}
