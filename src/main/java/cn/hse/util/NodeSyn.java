package cn.hse.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.hse.controller.SumbitController;
import cn.hse.controller.WebServiceController;
import net.sf.json.JSONObject;

/**
 * 
 *节点同步
 *
 */
public class NodeSyn {
	private static final Logger logger=LogManager.getLogger(SumbitController.class);
	//同步节点数据
	public String synNodeData(Map<String, Object> map) {
		logger.info("[流程节点同步数据封装入参]map="+map);
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("projNo", map.get("projNo").toString());  //项目编号
		resultMap.put("recordNo", map.get("recordNo").toString());  //检查编号
		resultMap.put("lineNo",map.get("lineNo").toString());  //序号
		resultMap.put("id", "");   
		resultMap.put("stepId", map.get("stepId").toString());   //节点id
		resultMap.put("stepName", map.get("stepName").toString());  //节点名称
		resultMap.put("stepCode", map.get("stepCode").toString());  //节点编码
		resultMap.put("ownerUserId", map.get("ownerUserId").toString());  //当前用户id
		resultMap.put("Own0erUserName", map.get("Own0erUserName").toString());  //当前用户名称
		resultMap.put("ownerUserDesc", map.get("ownerUserDesc").toString());   //当前用户描述
		resultMap.put("submitUserId", map.get("submitUserId").toString());  //提交人id
		resultMap.put("submitUserName", map.get("submitUserName").toString());   //提交人名称
		resultMap.put("submitUserDesc", map.get("submitUserDesc").toString());  //提交人描述
		
		paramsMap.put("flow", resultMap);
		logger.info("[同步节点封装数据完成]="+paramsMap);
		//转为json
		JSONObject params = JSONObject.fromObject(paramsMap);
		
		WebServiceController webServiceController=new WebServiceController();
		
		//调用返回的结果
		String returnResult=webServiceController.createFlowActionTrace(params);
		JSONObject json=JSONObject.fromObject(returnResult);
		String str="";
		if (json.get("status").toString().equals("0")) {
			logger.info("[同步用友整改接口返回结果]==="+json.get("status"));
			str="流程节点数据同步失败！";
			return str;
		}
		str="流程节点数据同步成功！";
		return str;
	}
}
