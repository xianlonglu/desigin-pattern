package com.gupaoedu.vip;

/**
 * 角色
 */
public class Role {

	private String name;

	private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "User{" + "name='" + name + '\'' + ", remark=" + remark + '}';
	}
}
