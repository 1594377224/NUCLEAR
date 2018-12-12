package cn.mvtech.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mvtech.beans.User;
import cn.mvtech.mapper.UserMapper;
import cn.mvtech.service.UserService;
import cn.mvtech.util.ResultUtil;
import net.sf.json.JSONObject;

@Service
public class UserServiceImpl implements UserService{
	private static final Logger logger=LogManager.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper UserMapper;
	@Override
	public List<User> findAll() {
		return UserMapper.findAll();
	}
	@Override
	public String addUser(JSONObject paramsJson) {
		// TODO Auto-generated method stub
		Map<String, Object> resultErrMap = new HashMap<String, Object>();
		int addUser = UserMapper.addUser(paramsJson);
		if(addUser>0){
			resultErrMap.put("resultCode", "0");
			resultErrMap.put("resultMsg", "操作成功！");
		}else {
			resultErrMap.put("resultCode", "-1");
			resultErrMap.put("resultMsg", "操作失败！");
		}
		
		return  ResultUtil.result("0", resultErrMap, new ArrayList<Map<String, Object>>());
	}
	@Override
	@Transactional
	public int addUserTest(int i, int j) {
		int a=UserMapper.addUserTest(i,j);
		int c=i/j;
		return a;
	}
	@Override
	public String findUser(JSONObject paramsJson) {
		List<Map<String, Object>> list = UserMapper.findUser(paramsJson);
		logger.info("=========list====="+list.toString());
		// TODO Auto-generated method stub
		return list.toString();
	}

}
