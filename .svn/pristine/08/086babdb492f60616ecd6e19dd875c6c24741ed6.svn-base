package cn.tedu.note.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;
import cn.tedu.note.util.Utils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	public User login(String name, String password) throws UserNameException, PasswordException {
//		System.out.println("login");
		//检验输入参数合理性
		if(name == null || name.trim().isEmpty()) {
			throw new UserNameException("用户名不能空");
		}
		String reg = "^\\w{3,10}$";
		if(!name.matches(reg)) {
			throw new UserNameException("用户名不符合规定");
		}
		if(password == null || password.trim().isEmpty()) {
			throw new PasswordException("密码不能为空");
		}
		if(!password.matches(reg)) {
			throw new PasswordException("密码不符合规定");
		}
		//查询用户数据
		User user = userDao.findUserByName(name);
		if(user == null) {
			throw new UserNameException("用户名错误");
		}
		//比较摘要
//		String salt = "你吃了吗?";
//		String md5 = DigestUtils.md5Hex(password+salt);
		String md5 = Utils.crypt(password);
//		System.out.println(md5);
//		System.out.println(user);
		if(user.getPassword().equals(md5)) {
			//业务处理
			//登录成功，返回用户信息
			String token = UUID.randomUUID().toString();
			user.setToken(token);
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("id", user.getId());
			data.put("token", token);
			userDao.updateUser(data);
			return user;
		}
		throw new PasswordException("密码错误");
	}

	public User regist(String name, String nick, String password, String confirm)
			throws UserNameException, PasswordException {
		if(name == null || name.trim().isEmpty()) {
			throw new UserNameException("不能为空");
		}
		String reg = "^\\w{3,10}$";
		if(!name.matches(reg)) {
			throw new UserNameException("不合规则");
		}
		if(nick == null || nick.trim().isEmpty()) {
			nick = name;
		}
		if(password == null || password.trim().isEmpty()) {
			throw new PasswordException("不能为空");
		}
		if(!password.matches(reg)) {
			throw new PasswordException("不合规则");
		}
		password = password.trim();
		if(!password.equals(confirm)) {
			throw new PasswordException("确认密码不一致");
		}
		name = name.trim();
		//检验用户名是否重复?
		User one = userDao.findUserByName(name);
		if(one != null) {
			throw new UserNameException("已注册");
		}
		//UUID 用于生产永远不重复的ID
		String id = UUID.randomUUID().toString();
		String token = "";
		String pwd = Utils.crypt(password);
		User user = new User(id,name,pwd,token,nick);
		System.out.println(user);
		userDao.saveUser(user);
		return user;
	}

	public boolean checkToken(String userId, String token) {
		if(userId == null || userId.trim().isEmpty()) {
			return false;
		}
		if(token == null || token.trim().isEmpty()) {
			return false;
		}
		User user = userDao.findUserById(userId);
		if(user == null) {
			return false;
		}
		return token.equals(user.getToken());
	}

}
























