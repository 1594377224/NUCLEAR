package cn.mvtech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mvtech.beans.User;
import cn.mvtech.mapper.UserMapper;
import cn.mvtech.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper UserMapper;
	@Override
	public List<User> findAll() {
		return UserMapper.findAll();
	}

}
