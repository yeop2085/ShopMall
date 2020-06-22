package com.green.biz.worker;

public class WorkerVO {
	
	private String id;
	private String pwd;
	private String name;
	private String phone;
	
	public String getId() {
		return id;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String toString() {
		return "WorkerVO [id : " + id + ", pwd : " + pwd + ", name : " + name + 
				", phone : " + phone + " ]";
	}
	
}

