package cn.tedu.note.service;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Note;

public interface NoteService {
	List<Map<String,Object>> listNotes(String notebookId) throws NotebookNotFoundException;
	
	Note loadNote(String id) throws NoteNotFoundException;
	
	//更新功能
	boolean saveNote(String id,String title,String body) throws NoteNotFoundException;
	
	//添加笔记功能
	Note addNote(String userId,String notebookId,String title)throws UserNotFoundException,NotebookNotFoundException;
	
	//删除笔记功能
	Note deleteNote(String id)throws NoteNotFoundException;
	
}
