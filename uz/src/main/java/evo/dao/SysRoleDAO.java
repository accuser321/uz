package evo.dao;

import org.springframework.stereotype.Component;

import evo.model.SysRole;
@Component
public class SysRoleDAO  extends BaseDao<SysRole>{
	public void buildMappinRelation(String roleId,String[] ids){
		StringBuffer buffer = new StringBuffer();
		 for(String id:ids){
			 buffer.append(id).append(",");
		 }
		 String sql = "update sys_user  set role_id = '"+roleId+"' where user_id in ("+buffer.subSequence(0, buffer.lastIndexOf(","))+" )"; 
		 //String sql = "insert ino sys_user  role_id = '"+roleId+"' where user_id in ("+buffer.subSequence(0, buffer.lastIndexOf(","))+" )";
		 this.getJdbcTemplate().execute(sql);
	}
	
	public void deleteMappinRelation(String roleId,String[] ids){
		StringBuffer buffer = new StringBuffer();
		 for(String id:ids){
			 buffer.append(id).append(",");
		 }
		 String sql = "update sys_user set role_id = null where user_id in ("+buffer.subSequence(0, buffer.lastIndexOf(","))+" ) "; 
		 this.getJdbcTemplate().execute(sql);
	}
	
	
}
