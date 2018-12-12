package cn.hse.service;

import java.util.List;

import cn.hse.beans.User;
import net.sf.json.JSONObject;



public interface UserService {
	//查询所有的用户
		public List<User> findAll();

		public String addUser(JSONObject paramsJson);

		public int addUserTest(int i, int j);
		
		public String findUser(JSONObject paramsJson);
}
