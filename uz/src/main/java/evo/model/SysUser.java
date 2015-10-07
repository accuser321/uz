package evo.model;

import com.alibaba.fastjson.annotation.JSONField;

public class SysUser {
    private Integer userId;

    private Integer roleId;

    private Integer deptId;

    private String userName;

    private String userPass;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String userCreateTime;

    private String userPhone;

    private Integer userSex;

    private Integer userAble;
    
    
    private String userAddress;
    
    @JSONField(format = "yyyy-MM-dd")
    private String userBirthday;
    
    //在N方持有One方的对象映射
    private SysRole sysRoles;
    
    //在N方持有One方的对象映射
    private SysDepartment sysDepartments;
    
    //增加VO属性
    private String departName ;

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public SysDepartment getSysDepartments() {
		return sysDepartments;
	}

	public void setSysDepartments(SysDepartment sysDepartments) {
		this.sysDepartments = sysDepartments;
	}

	public SysRole getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(SysRole sysRoles) {
		this.sysRoles = sysRoles;
	}

	public Integer getUserId() {
        return userId;
    }

    public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }


    public String getUserCreateTime() {
		return userCreateTime;
	}

	public void setUserCreateTime(String userCreateTime) {
		this.userCreateTime = userCreateTime;
	}

	public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getUserAble() {
        return userAble;
    }

    public void setUserAble(Integer userAble) {
        this.userAble = userAble;
    }
}