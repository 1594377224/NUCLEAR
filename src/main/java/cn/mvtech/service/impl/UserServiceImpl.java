package cn.mvtech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mvtech.beans.User;
import cn.mvtech.mapper.UserMapper;
import cn.mvtech.service.UserService;
import net.sf.json.JSONObject;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper UserMapper;
	@Override
	public List<User> findAll() {
		return UserMapper.findAll();
	}
	@Override
	public String addUser(JSONObject paramsJson) {
		// TODO Auto-generated method stub
		return UserMapper.addUser(paramsJson);
	}

}
