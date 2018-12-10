package cn.mvtech.service;

import java.util.List;

import cn.mvtech.beans.User;

public interface UserService {
	//查询所有的用户
		public List<User> findAll();
}
