package cn.tedu.note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Note;

public interface NoteDao {
	/**
	 * 根据笔记本ID查询全部的笔记信息
	 * @param notebookId
	 * @return
	 */
	List<Map<String,Object>> findNotesByNotebookId(String notebookId);
	
	Note findNoteById(String id);
	/**
	 * 更新note信息
	 * 参数是map，封装的note信息数据，其中必须有：
	 * 		id
	 * 		lastModifyTime
	 * 可选参数：
	 * 		nobookId
	 * 		userId
	 * 		statusId
	 * 		typeId
	 * 		title
	 * 		body	
	 * 使用：
	 * 		Map<String,Object> note = new HashMap<String,Object>();
	 * 		note.put("id","笔记的ID")；
	 * 		note.put("lastModifyTime",System.currentTimeMilies());
	 * 		note.put("title","问候");
	 * 		note.put("body","Hello World!");
	 * 		//...
	 * 		dao.updateNote(note);	
	 * @param note
	 */
	int updateNote(Map<String,Object> note);

	int countNotesById(String id);
	
	int addNote(Note note);
	
	List<Map<String,Object>> findNoteByParams(Map<String,Object> params);
	
	void deleteNotes(Map<String,Object> params);
}





















