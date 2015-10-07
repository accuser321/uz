package evo.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class BaseDao<T> {
	@Resource
	public JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	    this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	private Class<T> clazz;
	@SuppressWarnings("unchecked")
	public BaseDao() {
	    Type type = getClass().getGenericSuperclass();
	    if(type instanceof ParameterizedType){
	        ParameterizedType pt = (ParameterizedType) type;
	        Type [] parameterArgs = pt.getActualTypeArguments();
	        if(parameterArgs != null && parameterArgs.length > 0){
	            if(parameterArgs[0] instanceof Class){
	                clazz = (Class<T>) parameterArgs[0]; 
	            }
	        }
	    }
	}
	protected void update(String sql, Object ... args) throws SQLException{
	    jdbcTemplate.update(sql, args);
	}
	protected T get(String sql, Object ... args) throws SQLException{
	    return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<T>(clazz), args);
	}
	protected List<T> getForList(String sql, Object ... args) throws SQLException{
	    return jdbcTemplate.query(sql, new BeanPropertyRowMapper(clazz), args);
	}
	protected <E> E getValue(String sql, Object ... args) throws SQLException{
	    E result = null;
	    List list = jdbcTemplate.queryForList(sql, args);
	    if(list != null && list.size() > 0){
	        Map map = (Map) list.get(0);
	        result = (E) map.values().iterator().next();
	    }
	    return result;
	}
}
