package com.cn.hnust.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cn.hnust.dao.TableDesc;
import com.cn.hnust.dao.UserDao;
import com.cn.hnust.pojo.Criteria;
import com.cn.hnust.pojo.PageBean;
import com.cn.hnust.pojo.User;
@TableDesc(nameSpace = "com.cn.hnust.dao.UserDao", tableName = "User")
@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Integer>implements UserDao {
	
	@Override
	public User getUserById(Integer id) {
		return this.get(id);
	}

	@Override
	public PageBean<User> pageQueryUserList(Criteria criteria, PageBean<User> pageBean) {
		
		return this.pageQuery(criteria, pageBean);
	}

	@Override
	public Boolean insertUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getUserCount(Criteria criteria) {
		
		return this.getCount(criteria);
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
