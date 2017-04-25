package com.cn.hnust.pojo;
//

//Source code recreated from a .class file by IntelliJ IDEA
//(powered by Fernflower decompiler)
//

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BaseTableEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String createPin;
	private Date createTime;
	private String updatePin;
	private Date updateTime;
	private Integer sysVersion;
	private Boolean yn;
	private HashMap<String, Object> qryWhere;
	private List<String> qryOrderBy;
	private Long qryStart;
	private Integer qryCount;

	public BaseTableEntity() {
	}

	public String getCreatePin() {
		return this.createPin;
	}

	public Long getQryStart() {
		return qryStart;
	}

	public void setQryStart(Long qryStart) {
		this.qryStart = qryStart;
	}

	public Integer getQryCount() {
		return qryCount;
	}

	public void setQryCount(Integer qryCount) {
		this.qryCount = qryCount;
	}

	public void setCreatePin(String createPin) {
		this.createPin = createPin;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdatePin() {
		return this.updatePin;
	}

	public void setUpdatePin(String updatePin) {
		this.updatePin = updatePin;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getSysVersion() {
		return this.sysVersion;
	}

	public void setSysVersion(Integer sysVersion) {
		this.sysVersion = sysVersion;
	}

	public Boolean getYn() {
		return this.yn;
	}

	public void setYn(Boolean yn) {
		this.yn = yn;
	}

	public HashMap<String, Object> getQryWhere() {
		return this.qryWhere;
	}

	public void setQryWhere(HashMap<String, Object> qryWhere) {
		this.qryWhere = qryWhere;
	}

	public List<String> getQryOrderBy() {
		return this.qryOrderBy;
	}

	public void setQryOrderBy(List<String> qryOrderBy) {
		this.qryOrderBy = qryOrderBy;
	}
}