package cn.tedu.note.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.Notebook;
import cn.tedu.note.entity.User;

@Service("notebookService")
public class NotebookServiceImpl implements NotebookService {

	@Resource
	private NotebookDao notebookDao;
	@Resource
	private UserDao userDao;
	
	public List<Map<String, Object>> listNotebooks(String userId) throws UserNotFoundException {
		if(userId == null || userId.trim().isEmpty()) {
			throw new UserNameException("id为空");
		}
		User user = userDao.findUserById(userId);
		if(user == null) {
			throw new UserNameException("查无此人");
		}
		return notebookDao.findNotebooksByUserId(userId);
	}

	public Notebook addNotebook(String userId, String name) throws UserNotFoundException {
		if(userId == null || userId.trim().isEmpty()) {
			throw new UserNotFoundException("userId为空");
		}
		User user = userDao.findUserById(userId);
		if(user == null) {
			throw new UserNotFoundException("用户不存在");
		}
		if(name == null || name.trim().isEmpty()) {
			name = "我爱张婉秋";
		}
		String id = UUID.randomUUID().toString();
		String typeId = "0";
		String desc = "0";
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
		Notebook notebook = new Notebook(id, userId, typeId, name, desc, createTime);
		int n = notebookDao.addNotebook(notebook);
		if(n != 1) {
			throw new UserNotFoundException("保存失败");
		}
		return notebook;
	}

	public List<Map<String, Object>> listNotebooks(String userId, int pageNum, int pageSize)
			throws UserNotFoundException {
		if(userId == null || userId.trim().isEmpty()) {
			throw new UserNotFoundException("userId为空");
		}
		User user = userDao.findUserById(userId);
		if(user == null) {
			throw new UserNotFoundException("用户不存在");
		}
		//计算起始行号
		int start = pageNum*pageSize;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("start", start);
		params.put("rows", pageSize);
		return notebookDao.findNotebooksByPage(params);
	}

}











































