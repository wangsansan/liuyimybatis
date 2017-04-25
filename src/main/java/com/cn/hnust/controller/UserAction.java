package com.cn.hnust.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;

public class UserAction extends BaseAction {
	
	private static final long serialVersionUID = -3675623781845348379L;
	
	private IUserService userService;
	private User user;
	
	public String execute(){
		return SUCCESS;
	}
	
	
	

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



}
