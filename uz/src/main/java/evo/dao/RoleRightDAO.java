package evo.dao;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import evo.model.SysRoleRight;
@Component
public class RoleRightDAO  extends BaseDao<SysRoleRight>{
	public void buildMappinRelation(String roleId,String[] ids){
		String sql = "";
		if(ids!=null&&ids.length>0){
			for(String id:ids){
				 sql = "insert into sys_role_right(role_id,right_id)  values ('"+roleId+"' ,'"+id+"')";
				 this.getJdbcTemplate().execute(sql);
			 }
			 
		}
		 
	}
	
	public void removeMappinRelation(String roleId,String[] ids){
		if(StringUtils.isNotBlank(roleId)){
			String	 sql = "delete from  sys_role_right  where role_id = "+roleId+"" ;
					 this.getJdbcTemplate().execute(sql);
		}
		
	}
	
	
	public void deleteMappinRelation(String roleId,String[] ids){
		StringBuffer buffer = new StringBuffer();
		 for(String id:ids){
			 buffer.append(id).append(",");
		 }
		 String sql = "delete from sys_role_right  where right_id in ("+buffer.subSequence(0, buffer.lastIndexOf(","))+" ) and role_id ='"+roleId+"'"; 
		 this.getJdbcTemplate().execute(sql);
	}
	
	public void insertMappinRelation(String roleId,String[] ids){
		 for(String id:ids){
			 String sql = "insert into sys_role_right (role_id,right_id) values ( "+roleId+" , "+id+")"; 
			 this.getJdbcTemplate().execute(sql);
		 }
		 
	}
}
