package cn.hse.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mascloud.sdkclient.Client;



public class SendSmsUtil {
	
	private static final Logger logger=LogManager.getLogger(SendSmsUtil.class);
	
	public static String SmsSend(String checkContent,String checkPerson,String mobiles) {
		logger.info("===调用短信开始===断线入参=检查编号"+checkContent+"-检查人="+checkPerson+"-手机号="+mobiles);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (checkContent==null || checkPerson==null || mobiles==null) {
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "短信入参不完整，请检查!");
			return ResultUtil.result("-1", resultMap, new ArrayList<Map<String, Object>>());
		}
		logger.info("===开始进行短信身份验证===");
		final Client client =  Client.getInstance();
		boolean loginResult=client.login("http://112.35.4.197:15000","SMSEND","SMSSend","国核工程有限公司");
		if(!loginResult) {
			 resultMap.put("resultCode", "-1");
			 resultMap.put("resultMsg", "短信企业身份认证失败!");
			 return ResultUtil.result("-1", resultMap, new ArrayList<Map<String, Object>>());
		}
		logger.info("===短信身份验证通过===");
		 //短息发送内容
		 String content = "您好，编号为"+checkContent+"的检查单已发送至您处，请及时整改回复，检查人("+checkPerson+")";
		 String[] mobilesArr = mobiles.split(",");
		 int sendResult = client.sendDSMS (mobilesArr,content, "",1,"0SvLq0nJc", UUID.randomUUID().toString(),true);
		 logger.info("短信推送结果: " + sendResult); 
		 if(sendResult>0){
			 resultMap.put("resultCode", "0");
			 resultMap.put("resultMsg", "短信发送成功"+sendResult+"条");
			 return ResultUtil.result("0", resultMap, new ArrayList<Map<String, Object>>());
		 } else {
			 resultMap.put("resultCode", "-1");
			 resultMap.put("resultMsg", "短信发送失败");
			 return ResultUtil.result("-1", resultMap, new ArrayList<Map<String, Object>>());
		 }
	}
}
