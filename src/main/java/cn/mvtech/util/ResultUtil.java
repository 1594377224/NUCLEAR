package cn.mvtech.util;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class ResultUtil {

	/**
	 * 响应信息(服务层使用)
	 * @return
	 */
	public static String result(String code, Map<String, Object> map, List<Map<String, Object>> list){
		JSONObject json = new JSONObject();
		
		if("0".equals(code)){
			json.put("rtnMsg", "成功");
			json.put("rtnCode", "0");
		}else{
			json.put("rtnMsg", "失败");
			json.put("rtnCode", "-9999");
		}
		json.put("beans", list);
		json.put("object", map);
		return json.toString();
	}
	
	
	
	
	/**
	 * 响应信息
	 * @return
	 */
	public static String resultStr(String code, String jsonStr, List<Map<String, Object>> list){
		JSONObject json = new JSONObject();
		
		if("0".equals(code)){
			json.put("rtnMsg", "成功");
			json.put("rtnCode", "0");
		}else{
			json.put("rtnMsg", "失败");
			json.put("rtnCode", "-9999");
		}
		json.put("beans", list);
		json.put("object", jsonStr);
		return json.toString();
	}
	
	/**
	 * 响应信息 (接口层使用) 
	 * @param rtnCode 
	 * @param rtnMsg
	 * @return
	 */
	public static String result(String rtnCode,String rtnMsg){
		JSONObject json = new JSONObject();
		
		if("0".equals(rtnCode)){
			json.put("rtnMsg", "成功");
			json.put("rtnCode", "0");
		}else{
			json.put("rtnMsg", rtnMsg);
			json.put("rtnCode", "-9999");
		}
		json.put("beans", null);
		json.put("object", null);
		return json.toString();
	}
}
