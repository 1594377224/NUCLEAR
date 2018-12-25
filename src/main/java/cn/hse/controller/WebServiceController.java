package cn.hse.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONObject;

@RequestMapping(value = "/webService")
@RestController
public class WebServiceController {
	private static final Logger logger=LogManager.getLogger(CheckListController.class);
	//用友接口
	private static final String url="http://10.4.210.85:59080/b2e/HseMobileServerPort?wsdl";
	//登陆接口
	private static final String uri="http://10.4.200.77/snpec_portal/services/userGet.service?wsdl";
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test() throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		org.apache.cxf.endpoint.Client client = dcf
				.createClient("http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx?wsdl");
		// getUser 为接口中定义的方法名称 张三为传递的参数 返回一个Object数组
		Object[] objects = client.invoke("qqCheckOnline", "8698053");
		// 输出调用结果
		System.out.println("*****" + objects[0].toString());
		System.out.println("*****" + objects.length);
	}
	/**
	 * 用户信息接口
	 * 入参=userName
	 */
	@RequestMapping(value = "/getHesUserInif", method = RequestMethod.POST)
	public String getHesUserInif(@RequestBody Map<String, Object> map) {
		logger.info("前台入参==="+map);
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("userName", map.get("userName"));
			paramsMap.put("params", resultMap);
			JSONObject params = JSONObject.fromObject(paramsMap);
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			org.apache.cxf.endpoint.Client client = dcf.createClient(url);
			// getUser 为接口中定义的方法名称 张三为传递的参数 返回一个Object数组
			Object[] objects = client.invoke("getHesUserInif", params.toString());
			// 输出调用结果
			logger.info("===调用用友接口请求结束");
			String result=objects[0].toString();
			logger.info("===调用用友接口请求返回参数="+result);
			/*for (Object object : objects) {
				System.out.println("请求接口返回==>" + object.toString());			
				logger.info("用户信息返回接口信息="+object.toString());
				result=object.toString();
			}*/
			logger.info("用户信息返回前台信息="+result);
			return result;
		} catch (Exception e) {
			logger.info("接口请求异常error==>"+e.toString());
		}
		return null;
	}
	
	/**
	 * 2.项目列表查询接口：getProjectList
	 * 入参=userId
	 */
	@RequestMapping(value = "/getProjectList", method = RequestMethod.POST)
	public String getProjectList(@RequestBody Map<String, Object> map) {
		logger.info("前台入参==="+map);
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("userName", map.get("userName"));
			paramsMap.put("params", resultMap);
			JSONObject params = JSONObject.fromObject(paramsMap);
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			org.apache.cxf.endpoint.Client client = dcf.createClient(url);
			// getUser 为接口中定义的方法名称 张三为传递的参数 返回一个Object数组
			Object[] objects = client.invoke("getProjectList", params.toString());
			// 输出调用结果
			logger.info("===调用用友接口请求结束");
			String result=objects[0].toString();
			logger.info("===调用用友接口请求返回参数="+result);
			/*String result=null;
			for (Object object : objects) {
				System.out.println("请求接口返回==>" + object.toString());			
				logger.info("用户信息返回接口信息="+object.toString());
				result=object.toString();
			}*/
			logger.info("用户信息返回前台信息="+result);
			return result;
		} catch (Exception e) {
			logger.info("接口请求异常error==>"+e.toString());
		}
		return null;
	}
	
	/**
	 * 3.项目列表查询接口：getProjectList
	 * 入参=projNo
	 */
	@RequestMapping(value = "/getAreaList", method = RequestMethod.POST)
	public String getAreaList(@RequestBody Map<String, Object> map) {
		logger.info("前台入参==="+map);
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("projNo", map.get("projNo"));
			paramsMap.put("params", resultMap);
			JSONObject params = JSONObject.fromObject(paramsMap);
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			org.apache.cxf.endpoint.Client client = dcf.createClient(url);
			// getUser 为接口中定义的方法名称 张三为传递的参数 返回一个Object数组
			Object[] objects = client.invoke("getAreaList", params.toString());
			// 输出调用结果
			logger.info("===调用用友接口请求结束");
			String result=objects[0].toString();
			logger.info("===调用用友接口请求返回参数="+result);
			/*String result=null;
			for (Object object : objects) {
				System.out.println("请求接口返回==>" + object.toString());			
				logger.info("用户信息返回接口信息="+object.toString());
				result=object.toString();
			}*/
			logger.info("用户信息返回前台信息="+result);
			return result;
		} catch (Exception e) {
			logger.info("接口请求异常error==>"+e.toString());
		}
		return null;
	}
	
	/**
	 * 4.责任单位列表查询接口：getDraftUnitList
	 * 入参=projNo
	 */
	@RequestMapping(value = "/getDraftUnitList", method = RequestMethod.POST)
	public String getDraftUnitList(@RequestBody Map<String, Object> map) {
		logger.info("前台入参==="+map);
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("projNo", map.get("projNo"));
			paramsMap.put("params", resultMap);
			JSONObject params = JSONObject.fromObject(paramsMap);
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			org.apache.cxf.endpoint.Client client = dcf.createClient(url);
			// getUser 为接口中定义的方法名称 张三为传递的参数 返回一个Object数组
			Object[] objects = client.invoke("getDraftUnitList", params.toString());
			// 输出调用结果
			logger.info("===调用用友接口请求结束");
			String result=objects[0].toString();
			logger.info("===调用用友接口请求返回参数="+result);
			/*String result=null;
			for (Object object : objects) {
				System.out.println("请求接口返回==>" + object.toString());			
				logger.info("用户信息返回接口信息="+object.toString());
				result=object.toString();
			}*/
			logger.info("用户信息返回前台信息="+result);
			return result;
		} catch (Exception e) {
			logger.info("接口请求异常error==>"+e.toString());
		}
		return null;
	}
	
	/**
	 * 5.抄送人列表接口：getCopyPerson
	 * 入参=projNo/userName
	 */
	@RequestMapping(value = "/getCopyPerson", method = RequestMethod.POST)
	public String getCopyPerson(@RequestBody Map<String, Object> map) {
		logger.info("前台入参==="+map);
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("projNo", map.get("projNo"));
			resultMap.put("userName", map.get("userName"));
			paramsMap.put("params", resultMap);
			JSONObject params = JSONObject.fromObject(paramsMap);
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			org.apache.cxf.endpoint.Client client = dcf.createClient(url);
			// getUser 为接口中定义的方法名称 张三为传递的参数 返回一个Object数组
			Object[] objects = client.invoke("getCopyPerson", params.toString());
			// 输出调用结果
			logger.info("===调用用友接口请求结束");
			String result=objects[0].toString();
			logger.info("===调用用友接口请求返回参数="+result);
			/*String result=null;
			for (Object object : objects) {
				System.out.println("请求接口返回==>" + object.toString());			
				logger.info("用户信息返回接口信息="+object.toString());
				result=object.toString();
			}*/
			logger.info("用户信息返回前台信息="+result);
			return result;
		} catch (Exception e) {
			logger.info("接口请求异常error==>"+e.toString());
		}
		return null;
	}
	
	/**
	 * 6.试用机组列表获取接口：getUnit
	 * 入参=projNo
	 */
	@RequestMapping(value = "/getUnit", method = RequestMethod.POST)
	public String getUnit(@RequestBody Map<String, Object> map) {
		logger.info("前台入参==="+map);
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("projNo", map.get("projNo"));
			paramsMap.put("params", resultMap);
			JSONObject params = JSONObject.fromObject(paramsMap);
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			org.apache.cxf.endpoint.Client client = dcf.createClient(url);
			// getUser 为接口中定义的方法名称 张三为传递的参数 返回一个Object数组
			Object[] objects = client.invoke("getUnit", params.toString());
			// 输出调用结果
			logger.info("===调用用友接口请求结束");
			String result=objects[0].toString();
			logger.info("===调用用友接口请求返回参数="+result);
			/*String result=null;
			for (Object object : objects) {
				System.out.println("请求接口返回==>" + object.toString());			
				logger.info("用户信息返回接口信息="+object.toString());
				result=object.toString();
			}*/
			logger.info("用户信息返回前台信息="+result);
			return result;
		} catch (Exception e) {
			logger.info("接口请求异常error==>"+e.toString());
		}
		return null;
	}
	
	/**
	 * 7.被检查单位列表接口：getInspectedUnit
	 * 入参=projNo
	 */
	@RequestMapping(value = "/getInspectedUnit", method = RequestMethod.POST)
	public String getInspectedUnit(@RequestBody Map<String, Object> map) {
		logger.info("前台入参==="+map);
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("projNo", map.get("projNo"));
			paramsMap.put("params", resultMap);
			JSONObject params = JSONObject.fromObject(paramsMap);
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			org.apache.cxf.endpoint.Client client = dcf.createClient(url);
			// getUser 为接口中定义的方法名称 张三为传递的参数 返回一个Object数组
			Object[] objects = client.invoke("getInspectedUnit", params.toString());
			// 输出调用结果
			logger.info("===调用用友接口请求结束");
			String result=objects[0].toString();
			logger.info("===调用用友接口请求返回参数="+result);
			/*String result=null;
			for (Object object : objects) {
				System.out.println("请求接口返回==>" + object.toString());			
				logger.info("用户信息返回接口信息="+object.toString());
				result=object.toString();
			}*/
			logger.info("用户信息返回前台信息="+result);
			return result;
		} catch (Exception e) {
			logger.info("接口请求异常error==>"+e.toString());
		}
		return null;
	}
	
	
	/**
	 * 8.隐患类型列表接口：getHazardTypeList
	 * 入参=projNo
	 */
	@RequestMapping(value = "/getHazardTypeList", method = RequestMethod.POST)
	public String getHazardTypeList(@RequestBody Map<String, Object> map) {
		logger.info("前台入参==="+map);
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("projNo", map.get("projNo"));
			paramsMap.put("params", resultMap);
			JSONObject params = JSONObject.fromObject(paramsMap);
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			org.apache.cxf.endpoint.Client client = dcf.createClient(url);
			// getUser 为接口中定义的方法名称 张三为传递的参数 返回一个Object数组
			Object[] objects = client.invoke("getHazardTypeList", params.toString());
			// 输出调用结果
			logger.info("===调用用友接口请求结束");
			String result=objects[0].toString();
			logger.info("===调用用友接口请求返回参数="+result);
			/*String result=null;
			for (Object object : objects) {
				System.out.println("请求接口返回==>" + object.toString());			
				logger.info("用户信息返回接口信息="+object.toString());
				result=object.toString();
			}*/
			logger.info("用户信息返回前台信息="+result);
			return result;
		} catch (Exception e) {
			logger.info("接口请求异常error==>"+e.toString()
			);
		}
		return null;
	}
	
	/**
	 * 9.检查单录入接口：createModifyHseSiteRecord
	 * 入参=projNo
	 */
	//@RequestMapping(value = "/createModifyHseSiteRecord", method = RequestMethod.POST)
	public String createModifyHseSiteRecord(JSONObject params) {
		logger.info("createModifyHseSiteRecord入参==="+params);
		try {
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			org.apache.cxf.endpoint.Client client = dcf.createClient(url);
			logger.info("===================");
			// getUser 为接口中定义的方法名称 张三为传递的参数 返回一个Object数组
			Object[] objects = client.invoke("createModifyHseSiteRecord", params.toString());
			
			logger.info("===调用用友接口返回的数组="+objects);
			// 输出调用结果
			logger.info("===调用用友接口请求结束");
			String result=objects[0].toString();
			logger.info("===调用用友接口请求返回参数="+result);
			logger.info("用户信息返回前台信息="+result);
			return result;
		} catch (Exception e) {
			logger.info("接口请求异常error==>"+e.toString());
		}
		return null;
	}
	
	
	//检查单批准接口
	
	
	/**
	 * 获取登陆信息
	 * userType为1的时候为内部用户
	 * 为0的时候为临时人员
	 */
	@RequestMapping(value="/getUserMsg",method=RequestMethod.POST)
	public String getUserMsg(@RequestBody Map<String, Object> map) {
		logger.info("前台入参==="+map);
		try {
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			org.apache.cxf.endpoint.Client client = dcf.createClient(uri);
			logger.info("userId==>"+map.get("userId").toString());
			// 调用登陆接口
			Object[] objects = client.invoke("getUserMsg", map.get("userId").toString());
			// 输出调用结果
			logger.info("===调用用友接口请求结束");
			String result=objects[0].toString();
			logger.info("===调用用友接口请求返回参数="+result);
			return result;
		} catch (Exception e) {
			logger.info("接口请求异常error==>"+e.toString()
			);
		}
		return null;
	}
}
