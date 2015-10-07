关于evo项目的技术备案		---架构师：闫鑫
首先：evo工程是基础项目，其他项目都可以从evo中剥离部分代码作为独立的项目组成，uz工程就是一个例子
其次：如果有更好的基础沉淀，可先保证evo的正常运行，然后再在其他项目上拆分代码

一：技术框架
	spring+springmvc+mybatis是比较流行，而且性能由自己控制的一套框架，比传统的ssh性能更好，粒度更细，但同时要求也更高
	框架的配置文件参照spring-mvc.xml;spring-mybatis.xml;spring.xml
	其中，mybatis的分页功能，参照evo.interceptor.PageInterceptor.java
					         参照spring-mybatis.xml

mybatis中对应的一对多，一对一，多对过关系，是比较繁琐的内容，以下是分列的思路
	sys_role表 与 sys_user表 的对应关系是 one To More
	sys_role表的role_id;
			   role_name;
			   role_desc;
			   List<SysUser> sysUsers;//在One方持有More方的List					         
	
	sys_user表的user_id;
			   role_id;
			   dept_id;
			   user_name;
			   user_pass;
			   user_create_time;
			   user_phone;
			   user_sex;
			   user_able;
			   SysRole sysRoles;//在More方持有One方的对象映射
			   
	在SysRoleMapper.xml中，因为持有List对象，所以，需要在配置文件中增加<collection>标签		   
		  <resultMap type="evo.model.SysRole" id="findSysRoleListMap">
			  <id column="role_id" property="roleId" jdbcType="INTEGER" />
			  <result column="role_name" property="roleName" jdbcType="VARCHAR" />
			  <result column="role_desc" property="roleDesc" jdbcType="VARCHAR" />
			  <!-- 在主表中包含多方字表的conllection集合  后者是多方Mapper中namespace.resultMap的id-->
			  <!-- <collection property="sysUserList" resultMap="evo.dao.SysUserMapper.BaseResultMap"></collection> -->
				  <collection property="sysUsers" javaType="java.util.List" ofType="evo.model.SysUser">
					    <id column="user_id" property="userId" jdbcType="INTEGER" />
					    <result column="user_name" property="userName" jdbcType="VARCHAR" />
					    <result column="user_pass" property="userPass" jdbcType="VARCHAR" />
					    <result column="user_create_time" property="userCreateTime" jdbcType="TIMESTAMP" />
					    <result column="user_phone" property="userPhone" jdbcType="VARCHAR" />
					    <result column="user_sex" property="userSex" jdbcType="INTEGER" />
					    <result column="user_able" property="userAble" jdbcType="INTEGER" />
				  </collection>
		  </resultMap>
		  <select id="findSysRoleList"  resultMap="findSysRoleListMap">	<!-- parameterType="evo.model.SysRole" -->
		  		select 
		  		a.role_id,
		  		a.role_name,
		  		a.role_desc,
		  		b.user_id,
		  		b.user_name,
		  		b.user_pass,
		  		b.user_phone,
		  		b.user_sex,
		  		b.user_able
		  		from sys_role as a left join sys_user as b
		  		on a.role_id = b.role_id
  		  </select>	   

	在SysUserMapper.xml中，因为持有One方的对象映射，所以，需要在配置文件中增加<association>标签
			<select id="findSysRoleByUserNameAndUserPass" parameterType="java.lang.String" resultMap="sysu">
				select 
		  		a.role_name,
		  		a.role_desc,
		  		b.user_id, 
				b.role_id, 
				b.user_name, 
				b.user_pass, 
				b.user_create_time, 
				b.user_phone, user_sex,      
				b.user_able   
				from sys_role a ,sys_user b 
		  		where b.role_id = a.role_id and b.user_name = #{userName,jdbcType=VARCHAR} 
		  </select>
		     <resultMap type="evo.model.SysUser" id="sysu">
				    <id column="user_id" property="userId" jdbcType="INTEGER" />
				    <result column="dept_id" property="deptId" jdbcType="INTEGER" />
				    <result column="role_id" property="roleId" jdbcType="INTEGER" />
				    <result column="user_name" property="userName" jdbcType="VARCHAR" />
				    <result column="user_pass" property="userPass" jdbcType="VARCHAR" />
				    <result column="user_create_time" property="userCreateTime" jdbcType="TIMESTAMP" />
				    <result column="user_phone" property="userPhone" jdbcType="VARCHAR" />
				    <result column="user_sex" property="userSex" jdbcType="INTEGER" />
				    <result column="user_able" property="userAble" jdbcType="INTEGER" />
		  	  <association property="sysRoles" javaType="evo.model.SysRole" ><!-- javaType是类的全路径 -->
		  	  		 <id column="role_id" property="roleId" jdbcType="INTEGER" />
				    <result column="role_name" property="roleName" jdbcType="VARCHAR" />
				    <result column="role_desc" property="roleDesc" jdbcType="VARCHAR" />
		 	  </association>
		  </resultMap>
			   
			   
			   
			   