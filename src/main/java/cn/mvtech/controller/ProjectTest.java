package cn.mvtech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.mvtech.beans.User;
import cn.mvtech.service.UserService;
import cn.mvtech.util.G4Utils;
import cn.mvtech.util.ResultUtil;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("test")
public class ProjectTest {
	private static final Logger logger=LogManager.getLogger(ProjectTest.class);
	
	@Autowired
	private UserService userService;
	@RequestMapping("showObject")
	public Map<String, Object> showObject() {
		User user=new User();
		user.setName("张三");
		user.setAge(18);
		user.setSex("男");
		user.setAddress("河南郑州");
		User user1=new User();
		user1.setName("李四");
		user1.setAge(20);
		user1.setSex("女");
		user1.setAddress("河南开封");
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("user", user);
		map.put("user1", user1);
		return map;	
	}
	@RequestMapping("showList")
	public List<User> showList(){
		logger.info("=========测试日志打印");
		return userService.findAll();
	}
	
	@RequestMapping(value="addUser",method=RequestMethod.POST)
	public String addUser(String input){
		JSONObject inputJson = JSONObject.fromObject(input);
		JSONObject paramsJson = inputJson.getJSONObject("params");
		// 参数校验
		if(paramsJson.isEmpty()){
			return ResultUtil.result("-9999", "接入参数不完整！");
		}
		
		String id = "";
		
		if (paramsJson.containsKey("usrId")) {
			id = paramsJson.getString("usrId");
		}
		boolean[] sArr = { G4Utils.isNotEmpty(id)};
		boolean flag = BooleanUtils.and(sArr);
		if (!flag) {
			return ResultUtil.result("-9999", "接入参数不完整！");
		} else {
			String resultStr = userService.addUser(paramsJson);
			return resultStr;
		}
	}
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public Map<String, Object>  addUser(@RequestBody Map<String, Object> map){
		logger.info("=========测试日志打印"+map);
		return null;	
	}
}
