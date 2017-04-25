package com.cn.hnust.service;


import java.util.List;

import com.cn.hnust.pojo.Criteria;
import com.cn.hnust.pojo.PageBean;
import com.cn.hnust.pojo.User;

public interface IUserService {
	/**
     * 分页查询用户记录信息
     * @param criteria 查询条件
     * @param  pageBean     分页信息
     * @return
     */
    public PageBean<User> pageQueryUserList(Criteria criteria, PageBean<User> pageBean);
    /**
     * 保存用户
     * @param user 保存的用户
     * @return
     */
    public Boolean insertUser(User user);
    /**
     * 删除用户 逻辑删除
     * @param user 逻辑删除用户
     * @return
     */
    public Boolean deleteUser(User user);
    /**
     * 更新用户
     * @param user 更新用户
     * @return
     */
    public Boolean updateUser(User user);
    /**
     * 根据条件查询用户总数
     * @param Criteria 更新用户
     * @return
     */
    public Long getUserCount(Criteria criteria);
    
    User getUserById(Integer id);
    
    List<User> getAllUsers();
}
