package cn.tedu.test;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;

public class MyBatisTestCase {
	
	ClassPathXmlApplicationContext ctx;
	
	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("spring-web.xml","spring-mybatis.xml","spring-service.xml");
	}
	@Test
	public void test1() {
		DataSource ds = ctx.getBean("dataSource",DataSource.class);
		System.out.println(ds);
	}
	@Test
	public void test2() {
		SqlSessionFactory factory = ctx.getBean("sqlSessionFactory",SqlSessionFactory.class);
		System.out.println(factory);
	}
	@Test
	public void test3() {
		MapperScannerConfigurer scanner = ctx.getBean("mapperScanner",MapperScannerConfigurer.class);
		System.out.println(scanner);
	}
	@Test
	public void test4() {
		UserDao dao = ctx.getBean("userDao",UserDao.class);
		User user = new User("101","wanqiu","123","","niuniu");
		dao.saveUser(user);
		//select * from cn_user_ where cn_user_id='123'
	}
	@Test
	public void test5() {
		UserDao dao = ctx.getBean("userDao",UserDao.class);
		String id = "123";
		User user = dao.findUserById(id);
		System.out.println(user);
	}
	@Test
	public void test6() {
		UserDao dao = ctx.getBean("userDao",UserDao.class);
		String name = "zhangwanqiu";
		User user = dao.findUserByName(name);
		System.out.println(user);
	}
	
	@Test
	public void test7() {
		String userId = "52f9b276-38ee-447f-a3aa-0d54e7a736e4";
		NotebookDao dao = ctx.getBean("notebookDao",NotebookDao.class);
		List<Map<String,Object>> list = dao.findNotebooksByUserId(userId);
		for(Map<String,Object> n : list) {
			System.out.println(n);
		}
	}

}


























