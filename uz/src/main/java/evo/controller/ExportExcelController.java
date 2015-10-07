package evo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import evo.dao.SysUserMapper;
import evo.model.SysUser;
import evo.model.UserMonthTime;
import evo.model.VisitorRegister;
import evo.service.IExportExcelService;
import evo.service.ISysUserService;
import evo.service.IVisitorRegisterService;
import evo.util.DateUtil;
import evo.util.Page;

@Controller
@RequestMapping("/exportExcelController")
public class ExportExcelController<T> {

	@Autowired
	private IExportExcelService exportExcelService;

	@Autowired
	private ISysUserService sysUserService;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 
	 * @Title: findUserMonthTimeListByPage
	 * @Description: 分页查询
	 * @author Demo demo_@evo_com
	 * @param @param userMonthTime
	 * @param @param currentPage
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("/findUserMonthTimeListByPage/{currentPage}")
	public ModelAndView findUserMonthTimeListByPage(UserMonthTime userMonthTime,@PathVariable int currentPage,HttpServletRequest request){
		String startTime =request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		//开始时间
				if(StringUtils.isBlank(startTime)){
					Calendar cend = Calendar.getInstance();
					cend.set(Calendar.DAY_OF_MONTH, 1);
					Date date = cend.getTime();
					startTime = format.format(date);
				}
				
				//结束时间
				if(StringUtils.isBlank(endTime)){
					Calendar cend = Calendar.getInstance();
					cend.set(Calendar.DAY_OF_MONTH, cend.getActualMaximum(Calendar.DATE));
					Date date = cend.getTime();
					endTime = format.format(date);
				}
		
		userMonthTime.setStartTime(startTime);
		userMonthTime.setEndTime(endTime);
		int start =exportExcelService.getExist(startTime, "b");
		int end = exportExcelService.getExist(endTime, "big");
		try {
			//if(!(end>0&&start>0)){
				findAll(userMonthTime,request);
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView view = new ModelAndView();
		Page page = new Page();
		page.setCurrentPage(currentPage);
		userMonthTime.setToday("2015-09-30 00:00:00");
		List<UserMonthTime> list = exportExcelService.findUserMonthTimeListByPage(userMonthTime, page);
		
		for(UserMonthTime monthTime:list){
			SysUser user = sysUserService.findUserById(monthTime.getUserId());
			monthTime.setUserName(user.getUserName());
			monthTime.setWorkTimeStr(computeTime.getTime(monthTime.getWorkTime()));
			monthTime.setIlligalTimeStr(computeTime.getTime(monthTime.getIlligalTime()));
			monthTime.setOverTimeStr(computeTime.getTime(monthTime.getOverTime()));
			monthTime.setOutTimeStr(computeTime.getTime(monthTime.getOutTime()));
			monthTime.setToday(monthTime.getToday().substring(0,10));
		}
		view.addObject("userMonthTimeList", list);
		view.addObject("page", page);
		view.addObject("userMonthTime", userMonthTime);
		view.setViewName("usermonth");
		return view;
		
	}
	@Autowired
	private IVisitorRegisterService visitorRegisterService;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	@RequestMapping("/findall")
	public void findAll(UserMonthTime userMonthTimeOld,HttpServletRequest request) throws Exception{
		String startTime =userMonthTimeOld.getStartTime();
		String endTime = userMonthTimeOld.getEndTime();
		//数据库中已存在的最大值
		int end =0 ;
		//数据库中已存在的最小值
		int start =0 ;
		
		String userName = userMonthTimeOld.getUserName();
		
		Date startd = format.parse(startTime);
		Calendar cstart = Calendar.getInstance();
		cstart.setTime(startd);
	    
		
		Date dend = format.parse(endTime);
		Calendar cend = Calendar.getInstance();
		cend.setTime(dend);
		start =exportExcelService.getExist(startTime, "b");
		end = exportExcelService.getExist(endTime, "big");
		//if(!(end>0&&start>0)){
		exportExcelService.deleteAll();
		List<String> dayList =new ArrayList<>();
		int sub_m = cend.get(Calendar.MONTH)-cstart.get(Calendar.MONTH);
		int curdays = cstart.getActualMaximum(Calendar.DATE);
		Calendar curC = Calendar.getInstance();
		curC.setTime(startd);
		for(int k=1;k<=curdays;k++){
			curC.set(Calendar.DAY_OF_MONTH, k);
			if(curC.getTimeInMillis()>=cstart.getTimeInMillis()&&curC.getTimeInMillis()<=cend.getTimeInMillis()){
				dayList.add(format.format(curC.getTime()));
			}
		}
		for(int i=1;i<=sub_m;i++){
			curC.setTime(startd);
			curC.add(Calendar.MONTH, i);
			int days = curC.getActualMaximum(Calendar.DATE);
			for(int k=1;k<=days;k++){
				curC.set(Calendar.DAY_OF_MONTH, k);
				if(curC.getTimeInMillis()>=cstart.getTimeInMillis()&&curC.getTimeInMillis()<=cend.getTimeInMillis()){
					dayList.add(format.format(curC.getTime()));
				}
			}
			
		}
		//按照天和日期导出
		List<SysUser> listUser  = new ArrayList<>();
		if(StringUtils.isNotEmpty(userName))
			listUser = sysUserMapper.findUserByName(userName);
		else
			listUser = sysUserMapper.findSysUserList();
		
		for(String day:dayList){
			for(SysUser user:listUser){
				Map<String, Object> map = new HashMap<>();
				map.put("visitorId", user.getUserId().intValue());
				map.put("today", day);
				List<VisitorRegister> listWork =	visitorRegisterService.findAll(map);
				List<Item> itemList = new ArrayList<Item>();
				for(VisitorRegister register:listWork){
					register.setRegisterTime(register.getRegisterTime().substring(0, register.getRegisterTime().lastIndexOf(".")));
					Item item = new Item();
					item.type = register.getTitie();
					item.time = DateUtil.getLongTime(register.getRegisterTime());
					itemList.add(item);
				}
				UserMonthTime userMonthTime = 	computeTime.getTime(itemList,day);
				if(userMonthTime==null){
					userMonthTime = new UserMonthTime();
					userMonthTime.setIlligalTime(-1L);
					userMonthTime.setOutTime(-1L);
					userMonthTime.setOverTime(-1L);
					userMonthTime.setWorkTime(-1L);
				}
				if(userMonthTime.getIlligalTime()==null&&userMonthTime.getIlligalTime().intValue()!=-1){
					userMonthTime.setIlligalTime(Long.valueOf(userMonthTime.getIlligalTime()));
				}else if(userMonthTime.getIlligalTime().equals(-1L)){
					userMonthTime.setIlligalTime(0L);
				}
				if(userMonthTime.getWorkTime()==null&&userMonthTime.getWorkTime().intValue()!=-1){
					userMonthTime.setWorkTime(Long.valueOf(userMonthTime.getWorkTime()));
				}else if(userMonthTime.getWorkTime().equals(-1L)){
					userMonthTime.setWorkTime(0L);
				}
				if(userMonthTime.getOverTime()==null&&userMonthTime.getOverTime().intValue()!=-1){
					userMonthTime.setOverTime(Long.valueOf(userMonthTime.getOverTime()));
				}else if(userMonthTime.getOverTime().equals(-1L)){
					userMonthTime.setOverTime(0L);
				}
				if(userMonthTime.getOutTime()==null&&userMonthTime.getOutTime().intValue()!=-1){
					userMonthTime.setOutTime(Long.valueOf(userMonthTime.getOutTime()));
				}else if(userMonthTime.getOutTime().equals(-1L)){
					userMonthTime.setOutTime(0L);
				}
				userMonthTime.setToday(day);
				//userMonthTime.setUserId(user.getUserId().toString());
				userMonthTime.setUserId(user.getUserId().intValue());
				//存储
				exportExcelService.insertUserMonthTime(userMonthTime);
			}
			}
		//}
	}
 
	
	/**
	 * 
	 * @Title: exportExcel
	 * @Description: 导出excel
	 * @author Demo demo_@evo_com
	 * @param @param userMonthTime
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(UserMonthTime userMonthTime,HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("octets/stream"); 
		String excelName = "员工考勤"+userMonthTime.getStartTime()+"至"+userMonthTime.getEndTime();
		
		ModelAndView view = new ModelAndView();
		
		ExportExcelController<UserMonthTime> ex2 = new ExportExcelController<UserMonthTime>();
		String[] headers2 = { "序号","用户id","用户名", "当日时间", "工作时间", "不在工作区时间", "非法时间", "加班时间"};
//		List<UserMonthTime> dataset2 = new ArrayList<UserMonthTime>();
		//List<UserMonthTime> dataset2 = exportExcelService.findUserMonthTimeList();
		List<UserMonthTime> dataset2 = exportExcelService.findUserMonthTimeListByCondition(userMonthTime);
		for(UserMonthTime user : dataset2){
			Integer userId = user.getUserId();
			SysUser users = sysUserService.findUserById(userId);
			user.setUserName(users.getUserName());
			
			 user.setToday(user.getToday().substring(0, 10));
			 
			String illigalTimeStr = computeTime.getTime(user.getIlligalTime());
			user.setIlligalTimeStr(illigalTimeStr);
			String outTimeStr = computeTime.getTime(user.getOutTime());
			user.setOutTimeStr(outTimeStr);
			String overTimeStr = computeTime.getTime(user.getOverTime());
			user.setOverTimeStr(overTimeStr);
			String workTimeStr = computeTime.getTime(user.getWorkTime());
			user.setWorkTimeStr(workTimeStr);
			
		}
		
		try {
 
			//网络下载
			response.addHeader("Content-Disposition", "attachment;filename="+new String( excelName.getBytes("gb2312"), "ISO8859-1" )+".xls"); 
			//本地存储
			OutputStream out2 = response.getOutputStream(); 
			ex2.exportExcel(headers2, dataset2, out2);
			out2.flush();
			out2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exportExcel(Collection<T> dataset, OutputStream out) {
		exportExcel("导出EXCEL文档", null, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, Collection<T> dataset,
			OutputStream out) {
		exportExcel("导出EXCEL文档", headers, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, Collection<T> dataset,
			OutputStream out, String pattern) {
		exportExcel("导出EXCEL文档", headers, dataset, out, pattern);
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	@SuppressWarnings("unchecked")
	public void exportExcel(String title, String[] headers,
			Collection<T> dataset, OutputStream out, String pattern) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
				0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("yanxin");

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();//获取了Model中所有的属性
			for (short i = 0; i < 8; i++) {		//fields.length
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName,
							new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = "";
					 if (value instanceof Integer) {
						 int intValue = (Integer) value;
						 cell.setCellValue(intValue);
					 }
					 if (value instanceof Long) {
						 long intValue = (Long) value;
						 textValue = computeTime.getTime(intValue);
						 cell.setCellValue(intValue);
					 }
					// } else if (value instanceof Float) {
					// float fValue = (Float) value;
					// textValue = new HSSFRichTextString(
					// String.valueOf(fValue));
					// cell.setCellValue(textValue);
					// } else if (value instanceof Double) {
					// double dValue = (Double) value;
					// textValue = new HSSFRichTextString(
					// String.valueOf(dValue));
					// cell.setCellValue(textValue);
					// } else if (value instanceof Long) {
					// long longValue = (Long) value;
					// cell.setCellValue(longValue);
					// }
					if (value instanceof Boolean) {
						boolean bValue = (Boolean) value;
						textValue = "男";
						if (!bValue) {
							textValue = "女";
						}
					} else if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
								1023, 255, (short) 6, index, (short) 6, index);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(
								bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					} 
					else {
						if(value!=null)
						// 其它数据类型都当作字符串简单处理
						textValue = value.toString();
					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(
									textValue);
							HSSFFont font3 = workbook.createFont();
							font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入数据
	 * @Title: insertUserMonthTime
	 * @Description: TODO
	 * @author Demo demo_@evo_com
	 * @param @param userMonthTime
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	@RequestMapping("/insertUserMonthTime")
	public String insertUserMonthTime(UserMonthTime userMonthTime){
		exportExcelService.insertUserMonthTime(userMonthTime);
		return "";
	}
	
	/**
	 * 
	 * @Title: deleteUserMonthTimeById
	 * @Description: TODO
	 * @author Demo demo_@evo_com
	 * @param @param id
	 * @param @param response
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@RequestMapping("/deleteUserMonthTimeById/{id}")
	public void deleteUserMonthTimeById(@PathVariable Integer id,HttpServletResponse response) throws Exception{
		exportExcelService.deleteUserMonthTimeById(id);
		response.getWriter().print("{msg:'t'}");
	}
	

	/**
	 * 
	 * @Title: updateUserMonthTime
	 * @Description: 修改记录
	 * @author Demo demo_@evo_com
	 * @param @param userMonthTime
	 * @param @param response
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	@RequestMapping("/updateUserMonthTime")
	public String updateUserMonthTime(UserMonthTime userMonthTime,HttpServletResponse response) {
		exportExcelService.updateUserMonthTime(userMonthTime);
		return "";
	}
	/**
	* @Title: findSingleByID 
	* @Description: 查询单个
	* @param id
	* @param response 
	* void
	* @throws
	 */
	@RequestMapping("/findSingle")
	public void findSingleByID(@PathVariable Integer id,HttpServletResponse response){
		UserMonthTime userMonthTime = exportExcelService.findSingleById(id);
		JSONObject object = new JSONObject();
		object.put("userMonthTime", userMonthTime);
		try {
			response.getWriter().print(object.fromObject(object).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		// 测试学生
//		// ExportExcel<Student> ex = new ExportExcel<Student>();
//		// String[] headers =
//		// { "学号", "姓名", "年龄", "性别", "出生日期" };
//		// List<Student> dataset = new ArrayList<Student>();
//		// dataset.add(new Student(10000001, "张三", 20, true, new Date()));
//		// dataset.add(new Student(20000002, "李四", 24, false, new Date()));
//		// dataset.add(new Student(30000003, "王五", 22, true, new Date()));
//		// 测试图书
//		ExportExcelController<Book> ex2 = new ExportExcelController<Book>();
//		String[] headers2 = { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN",
//				"图书出版社", "封面图片" };
//		List<Book> dataset2 = new ArrayList<Book>();
//		try {
//			BufferedInputStream bis = new BufferedInputStream(
//					new FileInputStream("d://book.bmp"));
//			byte[] buf = new byte[bis.available()];
//			while ((bis.read(buf)) != -1) {
//				//
//			}
//
//			 dataset2.add(new Book(1, "jsp", "leno", 300.33f, "1234567",
//			 "清华出版社", buf));
//			 dataset2.add(new Book(2, "java编程思想", "brucl", 300.33f, "1234567",
//			 "阳光出版社", buf));
//			 dataset2.add(new Book(3, "DOM艺术", "lenotang", 300.33f, "1234567",
//			 "清华出版社", buf));
//			 dataset2.add(new Book(4, "c++经典", "leno", 400.33f, "1234567",
//			 "清华出版社", buf));
//			 dataset2.add(new Book(5, "c#入门", "leno", 300.33f, "1234567",
//			 "汤春秀出版社", buf));
//
//			// OutputStream out = new FileOutputStream("E://a.xls");
//			OutputStream out2 = new FileOutputStream("E://b.xls");
//			// ex.exportExcel(headers, dataset, out);
//			ex2.exportExcel(headers2, dataset2, out2);
//			// out.close();
//			JOptionPane.showMessageDialog(null, "导出成功!");
//			System.out.println("excel导出成功！");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}