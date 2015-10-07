package evo.model;

import com.alibaba.fastjson.annotation.JSONField;

public class UserMonthTime {
	private Integer id;
	private Integer userId;
	private String userName;
	@JSONField(format = "yyyy-MM-dd")
	private String today;
	private String workTimeStr;
	private String outTimeStr;
	private String illigalTimeStr;
	private String overTimeStr;
	
    private Long outTime;
    private Long illigalTime;
    private Long workTime;
    private Long overTime;
    private String startTime;
    private String endTime;
    
	//在N方持有One方的对象映射
    private SysUser sysUsers;

	public SysUser getSysUsers() {
		return sysUsers;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setSysUsers(SysUser sysUsers) {
		this.sysUsers = sysUsers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public Long getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Long workTime) {
		this.workTime = workTime;
	}

	public Long getOutTime() {
		return outTime;
	}

	public void setOutTime(Long outTime) {
		this.outTime = outTime;
	}

	public Long getIlligalTime() {
		return illigalTime;
	}

	public void setIlligalTime(Long illigalTime) {
		this.illigalTime = illigalTime;
	}

	public Long getOverTime() {
		return overTime;
	}

	public void setOverTime(Long overTime) {
		this.overTime = overTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWorkTimeStr() {
		return workTimeStr;
	}

	public void setWorkTimeStr(String workTimeStr) {
		this.workTimeStr = workTimeStr;
	}

	public String getOutTimeStr() {
		return outTimeStr;
	}

	public void setOutTimeStr(String outTimeStr) {
		this.outTimeStr = outTimeStr;
	}

	public String getIlligalTimeStr() {
		return illigalTimeStr;
	}

	public void setIlligalTimeStr(String illigalTimeStr) {
		this.illigalTimeStr = illigalTimeStr;
	}

	public String getOverTimeStr() {
		return overTimeStr;
	}

	public void setOverTimeStr(String overTimeStr) {
		this.overTimeStr = overTimeStr;
	}

	@Override
	public String toString() {
		return "UserMonthTime [用户IDuserId=" + userId + ", 时间today=" + today
				+ ", 工作时间workTime=" + workTime + ", 旷工时间outTime=" + outTime
				+ ", 非法时间illigalTime=" + illigalTime + ", 加班时间overTime=" + overTime
				+ "]";
	}
	
}
