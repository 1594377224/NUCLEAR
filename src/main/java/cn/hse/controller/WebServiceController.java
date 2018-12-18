package cn.hse.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONObject;

@RequestMapping(value = "/webService")
@RestController
public class WebServiceController {

	//用友接口
	private static final String url="http://10.4.210.85:59080//b2e/HseMobileServerPort?wsdl";
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test() throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		org.apache.cxf.endpoint.Client client = dcf
				.createClient("http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx?wsdl");
		// getUser 为接口中定义的方法名称 张三为传递的参数 返回一个Object数组
		Object[] objects = client.invoke("qqCheckOnline", "8698053");
		// 输出调用结果
		System.out.println("*****" + objects[0].toString());
	}
	
	@RequestMapping(value = "/getHesUserInif", method = RequestMethod.GET)
	public void getHesUserInif() throws Exception {
		Map paramsMap=new HashMap();
		Map map=new HashMap();
		map.put("userName", "LIUZHAOJUAN");
		paramsMap.put("params", map);
		JSONObject params=JSONObject.fromObject(paramsMap);
		System.out.println("params==>"+params);
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		org.apache.cxf.endpoint.Client client = dcf.createClient(url);
		// getUser 为接口中定义的方法名称 张三为传递的参数 返回一个Object数组
		Object[] objects = client.invoke("getHesUserInif", params);
		// 输出调用结果
		for (Object object : objects) {
			
			System.out.println("请求接口返回==>" + object.toString());
		}
	}
	
	
}
