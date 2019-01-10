package cn.hse.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mascloud.sdkclient.Client;

import cn.hse.util.HttpClientUtil;
import cn.hse.util.MD5Utils;
import cn.hse.util.ResultUtil;
import cn.hse.util.SendUtil;
import net.sf.json.JSONObject;
/**
 * 环境要求：短信发送服务器具有直连访问互联网的能力。
 *
 */
@RestController
@RequestMapping("/send")
public class TestSendController {
	
	public static void main(String[] args) {
		try {
			final Client client =  Client.getInstance();
			// 正式环境IP，登录验证URL，用户名，密码，集团客户名称
//			client.login("http://mas.ecloud.10086.cn/app/sdk/login", "SDK账号名称（不是页面端账号）", "密码","集团客户名称");
			// 测试环境IP
			boolean loginResult=client.login("http://112.35.4.197:15000","stlpt9", "passwd@0987",  "政企分公司测试");

			if(!loginResult) {
				System.out.println("短信企业身份认证失败");
				return;
			}
			
			int sendResult = client.sendDSMS (new String[] {"13600000000"},
					"sdk短信发送内容测试", "",  1,"短信签名ID", UUID.randomUUID().toString(),true);
			System.out.println("推送结果: " + sendResult);
			
			//添加黑白名单
//			client.addMember("18602761993", "9003451262");
			//查询黑白名单
//			client.queryMember("9003451262");
			//删除黑白名单
//			client.deleteMember("18602761993", "9003451262");
			
			// 获取提交报告线程
//			Thread t1 = new Thread() {
//				public void run() {
//					while(true) {
//						List< SubmitReportModel > list  = client.getSubmitReport();
//						try {
//							Thread.sleep(2000);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			};
//			t1.start();

			// 获取状态报告线程
//			Thread t2 = new Thread() {
//				public void run() {
//					while(true) {
//						List< StatusReportModel > StatusReportlist = client.getReport();
//						try {
//							Thread.sleep(2000);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			};
//			t2.start();

			// 获取上行线程
//			Thread t3 = new Thread() {
//				public void run() {
//					while(true) {
//						List< MoModel> lis = client.getMO();
//						try {
//							Thread.sleep(2000);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			};
//			t3.start();
//			
//			for(;;);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
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
	 
	 
	 
	  /* 
	   * 流程节点变动，发送短息通知
	   */
	 @ResponseBody
	 @RequestMapping(value="/smsSend",method=RequestMethod.POST)
	 public String SmsSend(@RequestBody Map<String, Object> map){
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 Map<String, Object> paramMap = new HashMap<String, Object>();
		 JSONObject inputJson = JSONObject.fromObject(map);
		 logger.info("[验证是否登录成功，发送短息--入参]"+inputJson);
		 //发送短信地址
		 String url = "http://112.35.1.155:1992/sms/norsubmit";
		 
		 //集团客户名称
		 String ecName = inputJson.get("ecName").toString();
		 //用户名
		 String apId = inputJson.get("apId").toString();
		 //密码
		 String secretKey = inputJson.get("secretKey").toString();
		 //手机号码数组，允许群发信息
		 String mobiles =  inputJson.get("mobiles").toString();
//		 String[] mobiles = { "1761012021","13233879200" };
		 //发送短信内容
		 String content = inputJson.get("content").toString();
		 //网关签名编码，必填
		 String sign = inputJson.get("sign").toString();
		 
		 //扩展码
		 String addSerial = inputJson.get("addSerial").toString();
		 //macAPI输入参数签名结果，签名算法：将ecName，apId，secretKey，mobiles，content ，sign，addSerial
		 //按照顺序拼接，然后通过md5(32位小写)计算后得出的值
//		 String mac = "";
		 
		 paramMap.put("ecName", ecName);
		 paramMap.put("apId", apId);
		 paramMap.put("secretKey", secretKey);
		 paramMap.put("mobiles", mobiles);
		 paramMap.put("content", content);
		 paramMap.put("sign", sign);
		 paramMap.put("addSerial", addSerial);
		 
		 StringBuffer stringBuffer = new StringBuffer();
		 stringBuffer.append(ecName);
		 stringBuffer.append(apId);
		 stringBuffer.append(secretKey);
		 stringBuffer.append(mobiles);
		 stringBuffer.append(content);
		 stringBuffer.append(sign);
		 stringBuffer.append(addSerial);
		 paramMap.put("mac", MD5Utils.encode(stringBuffer.toString()));
		 
		 String  param = JSONObject.fromObject(paramMap).toString();
		 
		 String base64String = Base64.encodeBase64String(param.getBytes());
		 logger.info("[用base64加密后的参数]"+base64String);
		 String httpPost = HttpClientUtil.httpPost(url, base64String);
		 JSONObject object = JSONObject.fromObject(httpPost);
		 logger.info("[调用短息发送接口的返回值--]"+object);
		 //true,false
		 Boolean success = (Boolean) object.get("success");
		 //用于验证短信提交报告和状态报告的一致性（取值msgGroup）注:如果数据验证不通过msgGroup为空
//		 String msgGroup = object.get("msgGroup").toString();
		 //响应码  
		 //IllegalMac	无效mac
		 //InvalidMessage	非法消息
		 //InvalidUsrOrPwd	非法用户名或密码
		 //NoSignId	未找到签名
		 //IllegalSignId	无效的签名
		 //success	成功
		 //TooManyMobiles	手机号超出最大上限（5000）
		 String rspcod = object.get("rspcod").toString();
		 if(success){
				resultMap.put("resultCode", "0");
				resultMap.put("resultMsg", rspcod);
			} else {
				resultMap.put("resultCode", "-1");
				resultMap.put("resultMsg", rspcod);
			}
		return ResultUtil.result("0", resultMap, null);
	 }
}