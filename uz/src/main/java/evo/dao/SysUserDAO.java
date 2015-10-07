package evo.dao;

import org.springframework.stereotype.Component;

import evo.model.SysRole;
@Component
public class SysUserDAO  extends BaseDao<SysRole>{
	
	/**
	 * 
	 * @Title: buildMappinRelation
	 * @Description: 批量绑定
	 * @author Demo demo_@evo_com
	 * @param @param roleId
	 * @param @param ids    设定文件
	 * @return void    返回类型
	 * important
	 * @throws
	 */
	public void buildMappinRelation(String roleId,String[] ids){
		StringBuffer buffer = new StringBuffer();
		String sql = "";
		if(ids!=null&&ids.length>0)
		 for(String id:ids){
			 buffer.append(id).append(",");
			 sql = "update sys_user  set role_id = '"+roleId+"' where user_id in ("+buffer.subSequence(0, buffer.lastIndexOf(","))+" )"; 
		 }
		 if(buffer.toString().length()>1)
		 this.getJdbcTemplate().execute(sql);
	}
	
	
	
	public void removeMappinRelation(String roleId,String[] ids){
		StringBuffer buffer = new StringBuffer();
		String sql = "";
		if(ids!=null&&ids.length>0)
		 for(String id:ids){
			 buffer.append(id).append(",");
			 sql = "update sys_user  set role_id = null where user_id in ("+buffer.subSequence(0, buffer.lastIndexOf(","))+" )"; 
		 }
		 if(buffer.toString().length()>1)
		 this.getJdbcTemplate().execute(sql);
	}
	
	
	public void deleteMappinRelation(String roleId,String[] ids){
		StringBuffer buffer = new StringBuffer();
		 for(String id:ids){
			 buffer.append(id).append(",");
		 }
		 String sql = "delete from sys_user  where user_id in ("+buffer.subSequence(0, buffer.lastIndexOf(","))+" ) and role_id ='"+roleId+"'"; 
		 this.getJdbcTemplate().execute(sql);
	}
	
	public void updateMappinRelation(String roleId,Integer[] ids){
		StringBuffer buffer = new StringBuffer();
		 for(Integer id:ids){
			 buffer.append(id.intValue()).append(",");
		 }
		 String sql = "update sys_user  set role_id = '"+roleId+"' where user_id in ("+buffer.subSequence(0, buffer.lastIndexOf(","))+" )"; 
		 this.getJdbcTemplate().execute(sql);
	}
	
}
