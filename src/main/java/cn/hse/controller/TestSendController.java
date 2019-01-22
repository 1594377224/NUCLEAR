package cn.hse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mascloud.sdkclient.Client;

import cn.hse.util.G4Utils;
import cn.hse.util.ResultUtil;
import net.sf.json.JSONObject;
@RestController
@RequestMapping("/send")
public class TestSendController {
	private static final Logger logger=LogManager.getLogger(TestSendController.class);
	  /* 
	   * 流程节点变动，发送短息通知
	   */
	 @ResponseBody
	 @RequestMapping(value="/smsSend",method=RequestMethod.POST)
	 public String SmsSend(@RequestBody Map<String, Object> map){
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 JSONObject inputJson = JSONObject.fromObject(map);
		 logger.info("[验证是否登录成功，发送短息--入参]"+inputJson);
		 try {
			 final Client client =  Client.getInstance();
			// 测试环境IP
			 boolean loginResult=client.login("http://112.35.4.197:15000","SMSEND","SMSSend","国核工程有限公司");
	
			 if(!loginResult) {
				 resultMap.put("resultCode", "-1");
				 resultMap.put("resultMsg", "短信企业身份认证失败!");
				}
			 //检查单编号
			 String checkContent = inputJson.getOrDefault("checkContent","XXXXXXX").toString();
			 //检查人
			 String checkPerson = inputJson.getOrDefault("checkPerson","XXX").toString();
			 //手机号
			 String  mobiles = G4Utils.getMapValue2String(inputJson, "mobiles");
			 String[] mobilesArr = mobiles.split(",");
			
//			 List<Map<String,Object>> mobilesList = JSONArray.fromObject(inputJson.get("mobiles"));
//			 String[] mobilesArr = mobilesList.toArray(new String[mobilesList.size()]);
			 //短息发送内容
			 String content = "您好，编号为"+checkContent+"的检查单已发送至您处，请及时整改回复，检查人("+checkPerson+")";
			 
			 int sendResult = client.sendDSMS (mobilesArr,
					 content, "",  1,"0SvLq0nJc", UUID.randomUUID().toString(),true);
			 logger.info("推送结果: " + sendResult); 
			 if(sendResult>0){
				 resultMap.put("resultCode", "0");
				 resultMap.put("resultMsg", "短信发送成功"+sendResult+"条");
			 } else {
				 resultMap.put("resultCode", "-1");
				 resultMap.put("resultMsg", "短信发送成功"+sendResult+"条");
			 }
			 
	 	 } catch (Exception e) {
			e.printStackTrace();
		 }
		 
		 return ResultUtil.result("0", resultMap, new ArrayList<Map<String, Object>>());
	
	 }

}