package cn.mvtech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mvtech.beans.User;
import cn.mvtech.service.UserService;

@RestController
@RequestMapping("test")
public class ProjectTest {
	/*private static Logger LOGGER=(Logger) LoggerFactory.getLogger(ProjectTest.class);*/
	
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
		
		return userService.findAll();
	}
}
