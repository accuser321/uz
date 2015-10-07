package evo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import evo.model.UserMonthTime;

public class computeTime {

	static long timelimit0 = StringToLong("2015-09-16 00:00:00");
	static long timelimit6 = StringToLong("2015-09-16 06:00:00");
	static long timelimit8 = StringToLong("2015-09-16 08:00:00");
	static long timelimit12 = StringToLong("2015-09-16 12:00:00");
	static long timelimit14 = StringToLong("2015-09-16 14:00:00");
	static long timelimit18 = StringToLong("2015-09-16 18:00:00");
	static long timelimit24 = StringToLong("2015-09-16 24:00:00");
	static long hour8 = 8 * 60 * 60 * 1000;

	private static int getTotal(ArrayList<Item> temptwo) {
		int total = 0;
		// 旷工时间
		int length = temptwo.size();
		if (length == 2) {
			// System.out.println("length=====2----total=0");
			return total;

		} else {

			for (int i = 0; i < temptwo.size(); i++) {

				if (temptwo.get(i).type == 0
						&& (i - 1 >= 0 && (temptwo.get(i).time <= timelimit8))) {
					continue;
				} else if (temptwo.get(i).type == 0
						&& (i - 1 >= 0)
						&& (temptwo.get(i).time > timelimit8 && temptwo.get(i).time <= timelimit12)) {

					if (temptwo.get(i - 1).time < timelimit8) {
						// total += timelimit12 - temptwo.get(i - 1).time;
						total += temptwo.get(i).time - timelimit8;
						getTime(total);
					} else {
						total += (temptwo.get(i).time - temptwo.get(i - 1).time);
						getTime(total);
					}

				} else if (temptwo.get(i).type == 0
						&& (i - 1 >= 0)
						&& (temptwo.get(i).time > timelimit12 && temptwo.get(i).time <= timelimit14)) {

					if (temptwo.get(i - 1).time < timelimit12) {

						total += timelimit12 - temptwo.get(i - 1).time;
						getTime(total);
					} else {

					}

				} else if (temptwo.get(i).type == 0
						&& (i - 1 >= 0)
						&& (temptwo.get(i).time > timelimit14 && temptwo.get(i).time <= timelimit18)) {

					if (temptwo.get(i - 1).time < timelimit14) {
						total += (temptwo.get(i).time - timelimit14);
						getTime(total);
					} else {
						total += (temptwo.get(i).time - temptwo.get(i - 1).time);
						getTime(total);
					}

				} else if (temptwo.get(i).type == 0 && (i - 1 >= 0)
						&& (temptwo.get(i).time >= timelimit18)) {

					if (temptwo.get(i - 1).time < timelimit18) {

						total += (timelimit18 - temptwo.get(i - 1).time);
						getTime(total);
					} else {

					}

				}
			}
		}
		// System.out.println("length>=2----total="+total);

		return total;
	}

	//para:"2015-09-17"
	public static void  setLimit(String data)
	{
		
		
		computeTime.timelimit0=StringToLong(data+" 00:00:00");
		computeTime.timelimit6=StringToLong(data+" 06:00:00");
		computeTime.timelimit8=StringToLong(data+" 08:00:00");
		computeTime.timelimit12=StringToLong(data+" 12:00:00");
	    computeTime.timelimit14=StringToLong(data+" 14:00:00");
	    computeTime.timelimit18=StringToLong(data+" 18:00:00");
		computeTime.timelimit24=StringToLong(data+" 23:59:59");
	}
	/*public UserMonthTime getOneDay(ArrayList<Item> temp,String time)
	{
		
		setLimit(time);
		return getTime(temp);
	}*/
	

	public static UserMonthTime getTime(List<Item> temp,String time) {
		setLimit(time);
		/*
		 * temp == null,参数异常 temp.size==0,没有记录
		 */

		if (temp == null || temp.size() == 0) {
			return null;
		}
		UserMonthTime userMonthTime = new UserMonthTime();

		/*
		 * 删除最后为0的所有打卡记录,当遇到type=1的记录时停止 0000000：全部删掉 1000000:1 0101000:0101
		 * 0011011000：0011011
		 */

		for (int i = temp.size() - 1; i >= 0; i--) {
			if (temp.get(i).type == 0) {
				temp.remove(i);
				i = temp.size();
				continue;
			}
			if (temp.get(i).type == 1) {
				break;
			}
		}

		/*
		 * 当只有一条记录，或者没有记录时（全0），或者（第一条记录为出去）。
		 * 0000000(n个0)这种记录，在经过上面的处理后，temp.size=0;
		 * 1000000这种记录，在经过上面的处理后，temp.size=1; 10101000这种记录，在经过上面的处理后，
		 * temp.get(0).type == 1
		 */

		if (temp.size() <= 1 || temp.get(0).type == 1) {

			userMonthTime.setWorkTime(-1L);
			userMonthTime.setOutTime(-1L);
			userMonthTime.setOverTime(-1L);
			userMonthTime.setIlligalTime(-1L);
			System.out.println("----all--lligal--");
			return userMonthTime;
		}

		ArrayList<Item> temptwo = new ArrayList<Item>();

		for (int i = 0; i < temp.size(); i++) {
			temptwo.add(temp.get(i));
		}

		// 去除list里重复的11，或者00
		for (int i = 0; i < temptwo.size(); i++) {
			if (i - 1 >= 0) {
				if (temptwo.get(i - 1).type == temptwo.get(i).type) {
					temptwo.remove(i - 1);
					i = i - 1;// i=0;
				}

			}
		}
		for (int i = 0; i < temptwo.size(); i++) {
			System.out.println(longToStrng(temptwo.get(i).time) + "type="
					+ temptwo.get(i).type);
		}
		// tfirst第一次打卡时间
		long tfirst = temptwo.get(0).time;
		// tend最后一次打卡时间
		long tend = temptwo.get(temptwo.size() - 1).time;
		System.out.println("------------------------------------2---");

		int length = temptwo.size();

		if (timelimit0 <= tfirst && tfirst <= timelimit6) {
			//1442344687000  1441058400000
			// 旷工时间
			int total = getTotal(temptwo);

			if (timelimit0 <= tend && tend <= timelimit6) {

				// int total = getTotal(temptwo);//测试1
				System.out.println("测试1-工作时间=0" + "--旷工时间=8" + "义务时间=0"
						+ "非法时间 =" + getTime(tend - tfirst));

				userMonthTime.setWorkTime(0L);
				userMonthTime.setOutTime(8L*60*60*1000);
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime( tend - tfirst);
				return userMonthTime;

			} else if (timelimit6 <= tend && tend <= timelimit8) {
				// int total = getTotal(temptwo);//测试2
				System.out.println("测试2-工作时间=0" + "--旷工时间=8" + "义务时间=0"
						+ "非法时间 =" + getTime(timelimit6 - tfirst));

				userMonthTime.setWorkTime(0L);
				userMonthTime.setOutTime(8L*60*60*1000);
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime(timelimit6 - tfirst);
				return userMonthTime;
			} else if (timelimit8 < tend && tend <= timelimit12) {

				System.out.println("测试3-工作时间="
						+ getTime(tend - timelimit8 - total) + "--旷工时间="
						+ getTime(hour8 - (tend - timelimit8 - total))
						+ "--义务时间=0" + "非法时间 =" + getTime(timelimit6 - tfirst));

				userMonthTime.setWorkTime(tend - timelimit8 - total);
				userMonthTime.setOutTime(hour8 - (tend - timelimit8 - total));
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime(timelimit6 - tfirst);
				return userMonthTime;

			} else if (timelimit12 < tend && tend <= timelimit14) {

				System.out.println("测试4-工作时间="
						+ getTime(timelimit12 - timelimit8 - total) + "--旷工时间="
						+ getTime(hour8 - (timelimit12 - timelimit8 - total))
						+ "--义务时间=0" + "非法时间 =" + getTime(timelimit6 - tfirst));

				userMonthTime.setWorkTime(timelimit12 - timelimit8 - total);
				userMonthTime.setOutTime(hour8 - (timelimit12 - timelimit8 - total));
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime(timelimit6 - tfirst);
				return userMonthTime;

			} else if (timelimit14 <= tend && tend <= timelimit18) {

				System.out.println("-测试5-工作时间="
						+ getTime(timelimit12 - timelimit8 + tend - timelimit14
								- total)
						+ "--旷工时间="
						+ getTime(hour8
								- (timelimit12 - timelimit8 + tend
										- timelimit14 - total)) + "义务时间=0"
						+ "非法时间 =" + getTime(timelimit6 - tfirst));

				userMonthTime
						.setWorkTime(timelimit12 - timelimit8 + tend
										- timelimit14 - total);
				userMonthTime.setOutTime(hour8 - (timelimit12 - timelimit8 + tend
								- timelimit14 - total));
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime(timelimit6 - tfirst);
				return userMonthTime;

			} else if (timelimit18 < tend && tend <= timelimit24) {

				System.out.println("--测试6--工作时间="
						+ getTime(timelimit12 - timelimit8 + timelimit18
								- timelimit14 - total)
						+ "--旷工时间="
						+ getTime(hour8
								- (timelimit12 - timelimit8 + timelimit18
										- timelimit14 - total)) + "义务时间="
						+ getTime(tend - timelimit18) + "非法时间 ="
						+ getTime(timelimit6 - tfirst));

				userMonthTime
						.setWorkTime(timelimit12 - timelimit8 + timelimit18
										- timelimit14 - total);
				userMonthTime.setOutTime(hour8 - (timelimit12 - timelimit8 + timelimit18
								- timelimit14 - total));
				userMonthTime.setOverTime(tend - timelimit18);
				userMonthTime.setIlligalTime(timelimit6 - tfirst);
				return userMonthTime;

			}

		} else if (timelimit6 <= tfirst && tfirst <= timelimit8) {

			// 旷工时间
			int total = getTotal(temptwo);

			if (timelimit6 <= tend && tend <= timelimit8) {

				// int total = getTotal(temptwo);
				System.out.println("-测试7-工作时间=0" + "--旷工时间=8" + "义务时间=0"
						+ "非法时间 =0");

				userMonthTime.setWorkTime(0L);
				userMonthTime.setOutTime(8L*60*60*1000);
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;
			} else if (timelimit8 < tend && tend <= timelimit12) {

				System.out.println("-测试8-工作时间="
						+ getTime(tend - timelimit8 - total) + "--旷工时间="
						+ getTime(hour8 - (tend - timelimit8 - total))
						+ "--义务时间=0" + "非法时间 =0");
				userMonthTime.setWorkTime(tend - timelimit8 - total);
				userMonthTime.setOutTime(hour8 - (tend - timelimit8 - total));
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;

			} else if (timelimit12 < tend && tend <= timelimit14) {

				System.out.println("-测试9-工作时间="
						+ getTime(timelimit12 - timelimit8 - total) + "--旷工时间="
						+ getTime(hour8 - (timelimit12 - timelimit8 - total))
						+ "--义务时间=0" + "非法时间 =0");
				userMonthTime.setWorkTime(timelimit12 - timelimit8 - total);
				userMonthTime.setOutTime(hour8 - (timelimit12 - timelimit8 - total));
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;
			} else if (timelimit14 <= tend && tend <= timelimit18) {

				System.out.println("-测试10-工作时间="
						+ getTime(timelimit12 - timelimit8 + tend - timelimit14
								- total)
						+ "--旷工时间="
						+ getTime(hour8
								- (timelimit12 - timelimit8 + tend
										- timelimit14 - total)) + "义务时间=0"
						+ "非法时间 =0");
				userMonthTime
						.setWorkTime(timelimit12 - timelimit8 + tend
										- timelimit14 - total);
				userMonthTime.setOutTime(hour8 - (timelimit12 - timelimit8 + tend
								- timelimit14 - total));
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;

			} else if (timelimit18 < tend && tend <= timelimit24) {

				System.out.println("-测试11--工作时间="
						+ getTime(timelimit12 - timelimit8 + timelimit18
								- timelimit14 - total)
						+ "--旷工时间="
						+ getTime(hour8
								- (timelimit12 - timelimit8 + timelimit18
										- timelimit14 - total)) + "义务时间="
						+ getTime(tend - timelimit18) + "非法时间 =0");
				userMonthTime
						.setWorkTime(timelimit12 - timelimit8 + timelimit18
										- timelimit14 - total);
				userMonthTime.setOutTime(hour8 - (timelimit12 - timelimit8 + timelimit18
								- timelimit14 - total));
				userMonthTime.setOverTime(tend - timelimit18);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;

			}

		} else if (timelimit8 < tfirst && tfirst <= timelimit12) {

			int total = getTotal(temptwo);

			if (timelimit8 < tend && tend <= timelimit12) {

				System.out.println("--测试12--工作时间="
						+ getTime(tend - tfirst - total) + "--旷工时间="
						+ getTime(hour8 - (tend - tfirst - total)) + "--义务时间=0"
						+ "非法时间 =0");
				userMonthTime.setWorkTime(tend - tfirst - total);
				userMonthTime
						.setOutTime(hour8 - (tend - tfirst - total));
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;

			} else if (timelimit12 < tend && tend <= timelimit14) {

				System.out.println("---测试13-工作时间="
						+ getTime(timelimit12 - tfirst - total) + "--旷工时间="
						+ getTime(hour8 - (timelimit12 - tfirst - total))
						+ "--义务时间=0" + "非法时间 =0");
				userMonthTime.setWorkTime(timelimit12 - tfirst - total);
				userMonthTime.setOutTime(hour8 - (timelimit12 - tfirst - total));
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;
			} else if (timelimit14 <= tend && tend <= timelimit18) {

				System.out
						.println("--测试14--工作时间="
								+ getTime(timelimit12 - tfirst + tend
										- timelimit14 - total)
								+ "--旷工时间="
								+ getTime(hour8
										- (timelimit12 - tfirst + tend
												- timelimit14 - total))
								+ "义务时间=0" + "非法时间 =0");
				userMonthTime.setWorkTime(timelimit12 - tfirst + tend - timelimit14 - total);
				userMonthTime
						.setOutTime(hour8 - (timelimit12 - tfirst + tend
										- timelimit14 - total));
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;

			} else if (timelimit18 < tend && tend <= timelimit24) {

				System.out.println("---测试15--工作时间="
						+ getTime(timelimit12 - tfirst + timelimit18
								- timelimit14 - total)
						+ "--旷工时间="
						+ getTime(hour8
								- (timelimit12 - tfirst + timelimit18
										- timelimit14 - total)) + "义务时间="
						+ getTime(tend - timelimit18) + "非法时间 =0");
				userMonthTime
						.setWorkTime(timelimit12 - tfirst + timelimit18
										- timelimit14 - total);
				userMonthTime.setOutTime(hour8 - (timelimit12 - tfirst + timelimit18
								- timelimit14 - total));
				userMonthTime.setOverTime(tend - timelimit18);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;

			}

		} else if (timelimit12 < tfirst && tfirst <= timelimit14) {

			int total = getTotal(temptwo);
			if (timelimit12 < tend && tend <= timelimit14) {

				System.out.println("测试16--工作时间=0" + "--旷工时间=8" + "--义务时间=0"
						+ "非法时间 =0");
				userMonthTime.setWorkTime(0L);
				userMonthTime.setOutTime(8L*60*60*1000);
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;

			} else if (timelimit14 <= tend && tend <= timelimit18) {

				System.out.println("--测试17-工作时间="
						+ getTime(tend - timelimit14 - total) + "--旷工时间="
						+ getTime(hour8 - (tend - timelimit14 - total))
						+ "--义务时间=0" + "非法时间 =0");
				userMonthTime.setWorkTime(tend - timelimit14 - total);
				userMonthTime.setOutTime(hour8 - (tend - timelimit14 - total));
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;

			} else if (timelimit18 < tend && tend <= timelimit24) {

				System.out.println("--测试18--工作时间="
						+ getTime(timelimit18 - timelimit14 - total)
						+ "--旷工时间="
						+ getTime(hour8 - (timelimit18 - timelimit14 - total))
						+ "--义务时间=" + getTime(tend - timelimit18) + "非法时间 =0");
				userMonthTime.setWorkTime(timelimit18 - timelimit14 - total);
				userMonthTime.setOutTime(hour8 - (timelimit18 - timelimit14 - total));
				userMonthTime.setOverTime(tend - timelimit18);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;

			}

		} else if (timelimit14 <= tfirst && tfirst <= timelimit18) {

			int total = getTotal(temptwo);
			if (timelimit14 <= tend && tend <= timelimit18) {

				System.out.println("测试19--工作时间="
						+ getTime(tend - tfirst - total) + "--旷工时间="
						+ getTime(hour8 - (tend - tfirst - total)) + "--义务时间=0"
						+ "非法时间 =0");
				userMonthTime.setWorkTime(tend - tfirst - total);
				userMonthTime
						.setOutTime(hour8 - (tend - tfirst - total));
				userMonthTime.setOverTime(0L);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;

			} else if (timelimit18 < tend && tend <= timelimit24) {

				System.out.println("--测试20--工作时间="
						+ getTime(timelimit18 - tfirst - total) + "--旷工时间="
						+ getTime(hour8 - (timelimit18 - tfirst - total))
						+ "--义务时间=" + getTime(tend - timelimit18) + "非法时间 =0");
				userMonthTime.setWorkTime(timelimit18 - tfirst - total);
				userMonthTime.setOutTime(hour8 - (timelimit18 - tfirst - total));
				userMonthTime.setOverTime(tend - timelimit18);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;

			}

		} else if (timelimit18 < tfirst && tfirst <= timelimit24) {

			int total = getTotal(temptwo);
			// tend最后一次打卡时间
			if (timelimit18 < tend && tend <= timelimit24) {

				System.out.println("测试21--工作时间=0" + "--旷工时间=8 小时" + "--义务时间="
						+ getTime(tend - tfirst) + "非法时间 =0");
				userMonthTime.setWorkTime(0L);
				userMonthTime.setOutTime(8L*60*60*1000);
				userMonthTime.setOverTime(tend - tfirst);
				userMonthTime.setIlligalTime(0L);
				return userMonthTime;

			}
		}
		return null;

	}

	public static String longToStrng(long time) {

		Date date = new Date(time);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		// System.out.println("TIME:::"+dateString);
		return dateString;
	}

	
	public static long StringToLong(String time) {
		// String time="2009-10-21 10:35:05";//时间格式的字符串
		/*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date s = null;
		try {
			s = formatter.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("TIME:::"+s);
*/		
		
		SimpleDateFormat 	format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d =null;
		try {
			d = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long timeL = d.getTime();
		return timeL;
		
		
		//System.out.println(time);

		//Date date = new Date(time);
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String dateString = formatter.format(date);
	}

	public static String getTime(long time) {
		String str = "";
		time = time / 1000;
		int s = (int) (time % 60);
		int m = (int) (time / 60 % 60);
		int h = (int) (time / 3600);
		str = h + "小时" + m + "分" + s + "秒";
		System.out.println(str);
		return str;
	}
}
