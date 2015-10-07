package evo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;


/**
  * 
  * 项目名称：EVO
  * 类名称：PropertiesUtil
  * 类描述：获取配置文件
  * 创建人：zj
  * 创建时间：2014-3-13 上午10:16:46
  * 修改人：zj
  * 修改时间：2014-3-13 上午10:16:46
  * 修改备注：
  * @version 
  * 
  */
public class PropertiesUtil {
		/**
		 * 获取属性文件
		 *@Describtion: TODO
		 *@param key
		 *@return
		 *@throws Exception
		 *@return String
		 */
		public static String getProperty(String key)
		{
			try {
				Properties properties = null;
				if (properties == null) {
					properties = new Properties();
				}
				properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties"));
				if(StringUtils.isBlank((String) properties.get(key))){
					properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
				}
				return (String) properties.get(key);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
}
