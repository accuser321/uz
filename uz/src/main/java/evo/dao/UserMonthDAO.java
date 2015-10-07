package evo.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import evo.model.UserMonthTime;
@Component
public class UserMonthDAO  extends BaseDao<UserMonthTime>{
     public void deleteAll(){
    	 this.getJdbcTemplate().batchUpdate("truncate table user_month_time");
     }
     
     
     public int FindMinTime(String time){
    	 int i =0;
    	 try {
			 i = this.getJdbcTemplate().queryForInt("select a.id from user_month_time as a where 1 = 1" +
			 		" and  date_format(a.today,'%Y-%m-%d') = '"+time+"' limit 1" );
			 return i;
    	 } catch (EmptyResultDataAccessException e ) {
			 return i;
		}
     }
     
     public int FindMaxTime(String time){
    	 int i =0;
    	 try {
			 i = this.getJdbcTemplate().queryForInt("select a.id from user_month_time as a where 1 = 1" +
			 		" and  date_format(a.today,'%Y-%m-%d') = '"+time+"' limit 1" );
			 return i;
    	 } catch (EmptyResultDataAccessException e ) {
			 return i;
		}
     }
     
}
