package cn.tedu.note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.note.dao.NoteDao;
import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.Note;
import cn.tedu.note.entity.Notebook;
import cn.tedu.note.entity.User;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Resource
	private NoteDao noteDao;
	
	@Resource
	private NotebookDao notebookDao;
	
	@Resource
	private UserDao userDao;
	
	public List<Map<String, Object>> listNotes(String notebookId) throws NotebookNotFoundException {
		if(notebookId == null || notebookId.trim().isEmpty()) {
			throw new NotebookNotFoundException("为空");
		}
		Notebook notebook = notebookDao.findNotebookById(notebookId);
		if(notebook == null) {
			throw new NotebookNotFoundException("笔记本不存在");
		}
		return noteDao.findNotesByNotebookId(notebookId);
	}

	//NoteServiceImpl
	public Note loadNote(String id) throws NoteNotFoundException {
		if(id == null || id.trim().isEmpty()) {
			throw new NoteNotFoundException("不能为空");
		}
		Note note = noteDao.findNoteById(id);
		if(note == null) {
			throw new NoteNotFoundException("id错误");
		}
		return note;
	}

	public boolean saveNote(String id, String title, String body) throws NoteNotFoundException {
		if(id == null || id.trim().isEmpty()) {
			throw new NoteNotFoundException("id空");
		}
//		Note note = noteDao.findNoteById(id);
		int num = noteDao.countNotesById(id);
		if(num != 1) {
			throw new NoteNotFoundException("没有笔记");
		}
		Map<String,Object> note = new HashMap<String,Object>();
		if(title != null && !title.trim().isEmpty()) {
			note.put("title",title);
		}
		if(body == null) {
			body = "";
		}
		note.put("body", body);
		note.put("id", id);
		note.put("lastModifyTime", System.currentTimeMillis());
		int n = noteDao.updateNote(note);
		return n==1;
	}

	public Note addNote(String userId, String notebookId, String title)
			throws UserNotFoundException, NotebookNotFoundException {
		if(userId == null || userId.trim().isEmpty()) {
			throw new UserNotFoundException("userId为空");
		}
		User user = userDao.findUserById(userId);
		if(user == null) {
			throw new UserNotFoundException("用户不存在");
		}
		if(notebookId == null || notebookId.trim().isEmpty()) {
			throw new NotebookNotFoundException("notebookId为空");
		}
		Notebook notebook = notebookDao.findNotebookById(notebookId);
		if(notebook == null) {
			throw new NotebookNotFoundException("笔记本不存在");
		}
		if(title == null || title.trim().isEmpty()) {
			title = "我喜欢张婉秋";
		}
		String id = UUID.randomUUID().toString();
		String statusId = "0";
		String typeId = "0";
		long time = System.currentTimeMillis();
		String body = "";
		Note note = new Note(id, notebookId, userId, statusId, typeId, title, body, time, time);
		int n = noteDao.addNote(note);
		if(n != 1) {
			throw new NotebookNotFoundException("保存失败");
		}
		return note;
	}

	public Note deleteNote(String id) throws NoteNotFoundException {
		if(id == null || id.trim().isEmpty()) {
			throw new NoteNotFoundException("id为空");
		}
		Note note = noteDao.findNoteById(id);
		if(note == null) {
			throw new NoteNotFoundException("笔记不存在");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("lastModifyTime", System.currentTimeMillis());
		map.put("statusId", "0");
		int n = noteDao.updateNote(map);
		if(n != 1) {
			throw new NoteNotFoundException("删除失败");
		}
		return note;
	}

}




































