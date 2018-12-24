/*package cn.hse.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mascloud.sdkclient.Client;

import cn.hse.util.RandomUUID;
import cn.hse.util.SendUtil;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("/send")
public class TestSendController {
	private static final Logger logger=LogManager.getLogger(TestSendController.class);
	 @RequestMapping("/sendTest" )
	    public void t() {
	         String host = "http://112.35.1.155:1992";
	            String path = "/sms/tmpsubmit";
	            String method = "POST";
	            String appcode = "这里填写你的验证码";
	            Map<String, String> headers = new HashMap<String, String>();
	            //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	            headers.put("Authorization", "APPCODE " + appcode);
	            Map<String, String> querys = new HashMap<String, String>();
	            querys.put("mobile", "这里填写你要发送的手机号码");
	            //querys.put("param", "code:1234");
	            //querys.put("param", "这里填写你和商家定义的变量名称和变量值填写格式看上一行代码");
	            querys.put("tpl_id", "这里填写你和商家商议的模板");
	            Map<String, String> bodys = new HashMap<String, String>();
	            try {
	                HttpResponse response = SendUtil.doPost(host, path, method, headers, querys, bodys);
	                System.out.println(response.toString());
	                //获取response的body
	                //System.out.println(EntityUtils.toString(response.getEntity()));
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	    }
	 
	 
	 
	  * 验证是否登录成功，发送短息
	  
	 @RequestMapping(value="/smsSend",method=RequestMethod.POST)
	 public String SmsSend(@RequestBody Map<String, Object> map){
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 JSONObject inputJson = JSONObject.fromObject(map);
		 logger.info("[验证是否登录成功，发送短息--入参]"+inputJson);
		 //身份认证地址
		 String url = "http://112.35.4.197:15000";
		 //用户登录帐号 caoxinglin
		 String userAccount = inputJson.get("userAccount").toString();
		 //用户登录密码 super1234
		 String password = inputJson.get("password").toString();
		 //用户企业名称
		 String ecname = inputJson.get("ecname").toString();
		 
		 //手机号码数组，允许群发信息
//		 String mobiles = "";
		 //发送短信内容
		 String smsContent = "";
		 //扩展码
		 String addSerial = "";
		 //短信优先级，取值1-5
		 String smsPriority = "";
		 //网关签名编码，必填
		 String Sign = "";
		 //发送数据批次号，32位世界上唯一编码，由字母和数字组成
		 String msgGroup = RandomUUID.RandomID();
		 //是否需要上行，True代表需要；false代表不需要
		 String IsMo = "";
		 
		 Client client = Client.getInstance( );
		 boolean isLoggedin = client.login( url, userAccount, password, ecname );
		 if( isLoggedin ) {
			System.out.println( "Login successed" );
		 } else {
			System.out.println( "Login failed" );
		 }
		 String[] mobiles = { "1761012021" };
		 int sendResult = client.sendDSMS( mobiles, "你好", "", 1, "NPHB12", "2004563256421", true );
		 if( sendResult == mobiles.length ) {
			System.out.println( "Successfully send " + mobiles.length + " SMS(s)" );
		 } else {
			System.out.println( "Failed to send " + mobiles.length + " SMS(s)" );
		}
		 
		 
		return null;
	 }
}
*/