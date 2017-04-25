package org.zsl.testmybatis;



import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.hnust.dao.UserDao;
import com.cn.hnust.pojo.PageBean;
import com.cn.hnust.pojo.User;
import com.cn.hnust.pojo.UserCriteria;
import com.cn.hnust.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
	private ApplicationContext ac = null;
	@Resource
	private IUserService userService = null;
	@Autowired
	private UserDao userDao;

	/*@Before
	public void before() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		userService = (IUserService) ac.getBean("userService");
	}
*/
	@Test
	public void test1() {
		User user = userService.getUserById(1);
		System.out.println(user.getUserName());
		logger.info("值："+user.getUserName());

	}
	
	@Test
	public void test2() {
		PageBean<User> pageBean = new PageBean<User>();
		pageBean.setPageNo(1);
		pageBean.setPageSize(5);
		UserCriteria userCriteria = new UserCriteria();
		userCriteria.setUserName("fds");
		pageBean = userService.pageQueryUserList(new UserCriteria(), pageBean);
		System.out.println(pageBean.getTotalCount()+":"+pageBean.getResultList().size());
//		logger.info(JSON.toJSONString(user));
	}
	
	@Test
	public void test3() {
		UserCriteria userCriteria = new UserCriteria();
		userCriteria.setUserName("fds");
		long count = userDao.getUserCount(userCriteria);
		System.out.println(count);
//		logger.info(JSON.toJSONString(user));
	}
	
}
