package cn.hse.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/login")
public class LoginController {
	private static final Logger logger=LogManager.getLogger(CheckListController.class);
	/**
	 * 获取验证码
	 * @param request
	 */
	/*@RequestMapping(value = "/getSmsInfo", method = RequestMethod.POST)
	public void getSmsInfo(HttpServletRequest request,String id) {
		logger.info("[获取验证码的入参]="+id);
		HttpSession session=request.getSession();
		//生成验证码
		String str="0123456789";
		StringBuilder sb=new StringBuilder(4);
		for(int i=0;i<4;i++){
		char ch=str.charAt(new Random().nextInt(str.length()));
		sb.append(ch);
		}
		String code=sb.toString();
		session.setAttribute("code", code);  //验证码
		//获取用户信息
		WebServiceController webServiceController=new WebServiceController();
		String userInfo=webServiceController.getUserMsg(id);  //入参用户id
		if (userInfo==null) {
			logger.info("[获取用户信息]="+userInfo);
		}
		JSONObject json=JSONObject.fromObject(userInfo);
		String mobile=json.getString("mobile");  //手机号
		String userName=json.getString("userName"); //用户名
		String userType=json.getString("userType");  //类型  1为内部成员，0位临时成员
		String department=json.getString("department");  //部门
		String jobTitle=json.getString("jobTitle");     //职位
	}*/
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody Map<String, Object> map) {
		logger.info("[用户登陆信息入参map]="+map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		return null;
	}
}
