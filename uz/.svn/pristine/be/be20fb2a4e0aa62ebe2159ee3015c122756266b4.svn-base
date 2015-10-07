package evo.model;

import java.util.List;

public class SysRight {
    private Integer rightId;

    private String rightParentId;

    private String rightType;

    private String rightText;

    private String rightUrl;
    
    //多对多关系,持有另一方的List
    private List<SysRole> sysRoleList;
    

    public List<SysRole> getSysRoleList() {
		return sysRoleList;
	}

	public void setSysRoleList(List<SysRole> sysRoleList) {
		this.sysRoleList = sysRoleList;
	}

    public Integer getRightId() {
		return rightId;
	}

	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}

	public String getRightParentId() {
        return rightParentId;
    }

    public void setRightParentId(String rightParentId) {
        this.rightParentId = rightParentId == null ? null : rightParentId.trim();
    }

    public String getRightType() {
        return rightType;
    }

    public void setRightType(String rightType) {
        this.rightType = rightType == null ? null : rightType.trim();
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText == null ? null : rightText.trim();
    }

    public String getRightUrl() {
        return rightUrl;
    }

    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl == null ? null : rightUrl.trim();
    }
}