package evo.model;

public class SysDepartment {
    private Integer deptId;
    
    private String deptName;
    
    private Integer parentDeptId ;
    
    public Integer getParentDeptId() {
		return parentDeptId;
	}

	public void setParentDeptId(Integer parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

	//创建时间
    private String userCreateTime;
    //父
    private SysDepartment parentDepart;

	public SysDepartment getParentDepart() {
		return parentDepart;
	}

	public void setParentDepart(SysDepartment parentDepart) {
		this.parentDepart = parentDepart;
	}

	public String getUserCreateTime() {
		return userCreateTime;
	}

	public void setUserCreateTime(String userCreateTime) {
		this.userCreateTime = userCreateTime;
	}

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }
}