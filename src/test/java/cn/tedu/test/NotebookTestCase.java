package cn.tedu.test;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.entity.Notebook;
import cn.tedu.note.service.NotebookService;

public class NotebookTestCase {

	ClassPathXmlApplicationContext ctx;
	
	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("spring-web.xml","spring-mybatis.xml","spring-service.xml",
				"spring-aop.xml");
	}
	
	@Test
	public void test1() {
		String userId = "52f9b276-38ee-447f-a3aa-0d54e7a736e4";
		NotebookDao dao = ctx.getBean("notebookDao",NotebookDao.class);
		List<Map<String,Object>> list = dao.findNotebooksByUserId(userId);
		for(Map<String,Object> n : list) {
			System.out.println(n);
		}
	}
	
	@Test
	public void test2() {
		String userId = "52f9b276-38ee-447f-a3aa-0d54e7a736e4";
		NotebookService service = ctx.getBean("notebookService",NotebookService.class);
		List<Map<String,Object>> list = service.listNotebooks(userId);
		for(Map<String,Object> n : list) {
			System.out.println(n);
		}
	}
	
	@Test
	public void test3() {
		NotebookDao dao = ctx.getBean("notebookDao",NotebookDao.class);
		String id = "555";
		String userId = "22";
		String typeId = "0";
		String name = "wanqiu";
		String desc = "0";
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
		Notebook notebook = new Notebook(id, userId, typeId, name, desc, createTime);
		dao.addNotebook(notebook);
	}
	
	@Test
	public void test4() {
		String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		NotebookDao dao = ctx.getBean("notebookDao",NotebookDao.class);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("start", 6);
		params.put("rows", 6);
		List<Map<String,Object>> list = dao.findNotebooksByPage(params);
		for(Map<String,Object> map : list) {
			System.out.println(map);
		}
	}
	
}






















