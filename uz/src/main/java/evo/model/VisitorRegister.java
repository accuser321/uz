package evo.model;

import com.alibaba.fastjson.annotation.JSONField;

public class VisitorRegister {

	private Integer registerId;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private String registerTime;
	
	private Integer visitorId;
	
	private Integer titie;
	
	//在N方持有One方的对象映射
    private Visitor visitors;

    //增加VO属性
    private String visitorName;
    //增加VO属性
    private String visitorPhoneNum;
    //增加VO属性
    private String visotorCompanyName;
    //身份证
    private String idNum;
    //内部员工
    private String starff;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    
   
	public String getStarff() {
		return starff;
	}

	public void setStarff(String starff) {
		this.starff = starff;
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

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getVisitorPhoneNum() {
		return visitorPhoneNum;
	}

	public void setVisitorPhoneNum(String visitorPhoneNum) {
		this.visitorPhoneNum = visitorPhoneNum;
	}

	public String getVisotorCompanyName() {
		return visotorCompanyName;
	}

	public void setVisotorCompanyName(String visotorCompanyName) {
		this.visotorCompanyName = visotorCompanyName;
	}

	public Integer getRegisterId() {
		return registerId;
	}

	public void setRegisterId(Integer registerId) {
		this.registerId = registerId;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(Integer visitorId) {
		this.visitorId = visitorId;
	}

	public Visitor getVisitors() {
		return visitors;
	}

	public void setVisitors(Visitor visitors) {
		this.visitors = visitors;
	}

	public Integer getTitie() {
		return titie;
	}

	public void setTitie(Integer titie) {
		this.titie = titie;
	}

	 
    
}
