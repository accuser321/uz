package evo.model;

import java.util.List;

public class SysRole {
    private Integer roleId;

    private String roleName;

    private String roleDesc;
    
    //在One方持有N方的List
    private List<SysUser> sysUsers;
    
    //多对多关系,持有另一方的List
    private List<SysRight> sysRightList;
    
	public List<SysRight> getSysRightList() {
		return sysRightList;
	}

	public void setSysRightList(List<SysRight> sysRightList) {
		this.sysRightList = sysRightList;
	}

	public List<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(List<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }
}