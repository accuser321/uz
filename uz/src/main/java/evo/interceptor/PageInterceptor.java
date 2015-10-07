package evo.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import evo.util.Page;

/**
 * 分页拦截器
 * @author yanxin
 *
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PageInterceptor implements Interceptor{

	/**
	 * 只有经过intercept才是要分页的
	 */
	public Object intercept(Invocation invocation) throws Throwable {

		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		//MetaObject的作用等同于StatementHandler,但可以通过反射直接访问属性
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
		MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
		//获取配置文件中SQL的id
		String id = mappedStatement.getId();
		if(id.matches(".+ByPage$")){
			BoundSql boundSql = statementHandler.getBoundSql();
			//获取原始的SQL语句
			String sql = boundSql.getSql();
			//查询总条数的SQL语句
			String countSql = "select count(*) from (" + sql +") a";
			//System.out.println("====="+countSql+"========");
			Connection connection = (Connection)invocation.getArgs()[0];
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet rs = countStatement.executeQuery();

			
			Map<String,Object> parameter = (Map<String,Object>)boundSql.getParameterObject();
			//获取前端传来的page属性
			Page page = (Page)parameter.get("page");
			if(rs.next()){
				page.setTotalNumber(rs.getInt(1));
			}
			//拼装分页的SQL语句
			String pageSql = sql + " limit " + page.getDbIndex() + "," + page.getDbNumber();
			//将拦截下来的sql,放置回去
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		//交回执行的权利
		return invocation.proceed();
	}

	/**
	 * 拦截所有的
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

	
}
