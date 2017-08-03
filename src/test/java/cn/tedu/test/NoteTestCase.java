package cn.tedu.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.NoteDao;
import cn.tedu.note.entity.Note;
import cn.tedu.note.service.NoteService;

public class NoteTestCase {
	
	ClassPathXmlApplicationContext ctx;
	
	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("spring-web.xml","spring-mybatis.xml","spring-service.xml",
				"spring-aop.xml");
	}
	
	@Test
	public void test1() {
		String id = "0037215c-09fe-4eaa-aeb5-25a340c6b39b";
		NoteDao dao = ctx.getBean("noteDao",NoteDao.class);
		List<Map<String,Object>> list = dao.findNotesByNotebookId(id);
		for(Map<String,Object> map : list) {
			System.out.println(map);
		}
	}
	
	@Test
	public void test2() {
		String id = "0037215c-09fe-4eaa-aeb5-25a340c6b39b";
		NoteService service = ctx.getBean("noteService",NoteService.class);
		List<Map<String, Object>> list;
		
		list = service.listNotes(id);
		
		for(Map<String,Object> map : list) {
			System.out.println(map);
		}
		
	}
	
	@Test
	public void test3() {
		String id = "19fbb55b-0541-433b-a7cd-dba52220a447";
		NoteDao dao = ctx.getBean("noteDao",NoteDao.class);
		Note note = dao.findNoteById(id);
		System.out.println(note);
	}
	
	@Test
	public void test4() {
		String id = "19fbb55b-0541-433b-a7cd-dba52220a447";
		NoteService service = ctx.getBean("noteService",NoteService.class);
		Note note = service.loadNote(id);
		System.out.println(note);
	}
	
	@Test
	public void test5() {
		NoteDao dao = ctx.getBean("noteDao",NoteDao.class);
		String id = "19fbb55b-0541-433b-a7cd-dba52220a447";
		Map<String,Object> note = new HashMap<String,Object>();
		//加入必选参数
		note.put("id", id);
		note.put("lastModifyTime", System.currentTimeMillis());
		//加入可选参数
		note.put("title", "婉秋");
		note.put("body", "Hello World!");
		dao.updateNote(note);
		//select cn_note_title from cn_note where cn_note_id=?
	}
	
	@Test
	public void test6() {
		String id = "19fbb55b-0541-433b-a7cd-dba52220a447";
		NoteDao dao = ctx.getBean("noteDao",NoteDao.class);
		int n = dao.countNotesById(id);
		System.out.println(n);
	}
	
	@Test
	public void test7() {
		NoteService service = ctx.getBean("noteService",NoteService.class);
		String id = "19fbb55b-0541-433b-a7cd-dba52220a447";
		boolean b = service.saveNote(id, "Java", "Java Hello World!");
		System.out.println(b);
		Note n = service.loadNote(id);
		System.out.println(n);
	}
	
	@Test
	public void test8() {
		NoteDao dao = ctx.getBean("noteDao",NoteDao.class);
		String id = "12121";
		String notebookId = "0037215c-09fe-4eaa-aeb5-25a340c6b39b";
		String userId = "52f9b276-38ee-447f-a3aa-0d54e7a736e4";
		String statusId = "0";
		String typeId = "0";
		String title = "Java";
		String body = "Hello";
		long now = System.currentTimeMillis();
		Note note = new Note(id, notebookId,
				userId, statusId, typeId, title, body, now, now);
		int n = dao.addNote(note);
		System.out.println(n);
	}
	
	@Test
	public void test9() {
		NoteService service = ctx.getBean("noteService",NoteService.class);
		String userId = "52f9b276-38ee-447f-a3aa-0d54e7a736e4";
		String notebookId = "0037215c-09fe-4eaa-aeb5-25a340c6b39b";
		Note note = service.addNote(userId, notebookId, "wanqiu I love you");
		System.out.println(note);
	}
	
	@Test
	public void test10() {
		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("userId", "");
//		params.put("notebookId", "");
		params.put("key", "%1%");
		params.put("start", 0);
		params.put("rows", 10);
		NoteDao dao = ctx.getBean("noteDao",NoteDao.class);
		List<Map<String,Object>> list = dao.findNoteByParams(params);
		for(Map<String,Object> map : list) {
			System.out.println(map);
		}
	}
	
	@Test
	public void test11() {
		String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		List<String> idList = new ArrayList<String>();
		idList.add("019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0");
		idList.add("01da5d69-89d5-4140-9585-b559a97f9cb0");
//		idList.add("");
//		idList.add("");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("idList", idList);
		NoteDao dao = ctx.getBean("noteDao",NoteDao.class);
		dao.deleteNotes(params);
	}
	
}

























