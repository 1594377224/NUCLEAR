package cn.mvtech.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mvtech.beans.User;
import cn.mvtech.mapper.UserMapper;
import cn.mvtech.service.UserService;
import cn.mvtech.util.ResultUtil;
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

}
