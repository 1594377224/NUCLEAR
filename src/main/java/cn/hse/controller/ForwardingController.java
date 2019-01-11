package cn.hse.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.hse.service.ForwardingService;
import cn.hse.util.G4Utils;
import cn.hse.util.ResultUtil;
import net.sf.json.JSONObject;

/**
 * 整改节点待办-转发
 * @author 
 *
 */
@RestController
@RequestMapping("forwarding")
public class ForwardingController {
	private static final Logger logger=LogManager.getLogger(ForwardingController.class);
	
	@Autowired
	private ForwardingService forwardingService;
	
	/*
	 * 整改节点待办-转发
	 */
	@RequestMapping(value="/findForwarding",method=RequestMethod.POST)
	public String findForwarding(@RequestBody Map<String, Object> map){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject inputJson = JSONObject.fromObject(map);
		logger.info("[整改节点待办-转发入参]"+inputJson);
		// 参数校验
		if(inputJson.isEmpty()){
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
			return ResultUtil.result("0", resultMap, null);
		}
		//用户登录id
		String userId = "" ;
		//用户登录名称
		String userName = "" ;
		//ActionTrace表的id
		String actionTraceId = "" ;
		//转给人id
		String ownerUserId = "" ;
		//转给人名称
		String ownerUserName = "" ;
		//模板名称
		String flowName =  "" ;
		//模板编码
		String flowCode =  "" ;
		//模板id
		String flowId =  "" ;
		//实例id
		String instanceId =  "" ;
		//节点编码
		String stepCode =  "" ;
		//节点id
		String stepId =  "" ;
		//节点名称
		String stepName =  "" ;
		//转发意见
		String data =  "" ;
		if (inputJson.containsKey("actionTraceId")) {
			actionTraceId = inputJson.getString("actionTraceId");
		}
		if (inputJson.containsKey("ownerUserId")) {
			ownerUserId = inputJson.getString("ownerUserId");
		}
		if (inputJson.containsKey("ownerUserName")) {
			ownerUserName = inputJson.getString("ownerUserName");
		}
		if (inputJson.containsKey("flowName")) {
			flowName = inputJson.getString("flowName");
		}
		if (inputJson.containsKey("flowCode")) {
			flowCode = inputJson.getString("flowCode");
		}
		if (inputJson.containsKey("instanceId")) {
			instanceId = inputJson.getString("instanceId");
		}
		if (inputJson.containsKey("flowId")) {
			flowId = inputJson.getString("flowId");
		}
		if (inputJson.containsKey("userId")) {
			userId = inputJson.getString("userId");
		}
		if (inputJson.containsKey("userName")) {
			userName = inputJson.getString("userName");
		}
		if (inputJson.containsKey("stepCode")) {
			stepCode = inputJson.getString("stepCode");
		}
		if (inputJson.containsKey("stepId")) {
			stepId = inputJson.getString("stepId");
		}
		if (inputJson.containsKey("stepName")) {
			stepName = inputJson.getString("stepName");
		}
		if (inputJson.containsKey("data")) {
			data = inputJson.getString("data");
		}
		boolean[] sArr = { G4Utils.isNotEmpty(actionTraceId),G4Utils.isNotEmpty(ownerUserId),G4Utils.isNotEmpty(ownerUserName)
				,G4Utils.isNotEmpty(flowName),G4Utils.isNotEmpty(flowCode),G4Utils.isNotEmpty(instanceId),G4Utils.isNotEmpty(flowId)
				,G4Utils.isNotEmpty(userId),G4Utils.isNotEmpty(userName),G4Utils.isNotEmpty(stepCode)
				,G4Utils.isNotEmpty(stepId),G4Utils.isNotEmpty(stepName),G4Utils.isNotEmpty(data)};
		boolean flag = BooleanUtils.and(sArr);
		if (!flag) {
			return ResultUtil.result("-9999", "接入参数不完整！");
		} else {
			String resultStr = forwardingService.findForwarding(inputJson);
			return resultStr;
		} 
	}
	
	/*
	 * 整改节点待办-退回 refund
	 */
	@RequestMapping(value="/findFefund",method=RequestMethod.POST)
	public String findFefund(@RequestBody Map<String, Object> map){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject inputJson = JSONObject.fromObject(map);
		logger.info("[整改节点待办-退回入参]"+inputJson);
		// 参数校验
		if(inputJson.isEmpty()){
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
			return ResultUtil.result("0", resultMap, null);
		}
		//用户登录id
		String userId = "" ;
		//用户登录名称
		String userName = "" ;
		//ActionTrace表的id
		String actionTraceId = "" ;
		//实例id
		String instanceId =  "" ;
		//退回意见
		String data =  "" ;
		if (inputJson.containsKey("userId")) {
			userId = inputJson.getString("userId");
		}
		if (inputJson.containsKey("userName")) {
			userName = inputJson.getString("userName");
		}
		if (inputJson.containsKey("actionTraceId")) {
			actionTraceId = inputJson.getString("actionTraceId");
		}
		if (inputJson.containsKey("instanceId")) {
			instanceId = inputJson.getString("instanceId");
		}
		if (inputJson.containsKey("data")) {
			data = inputJson.getString("data");
		}
		boolean[] sArr = { G4Utils.isNotEmpty(actionTraceId),G4Utils.isNotEmpty(instanceId)
				,G4Utils.isNotEmpty(userId),G4Utils.isNotEmpty(userName),G4Utils.isNotEmpty(data)};
		boolean flag = BooleanUtils.and(sArr);
		if (!flag) {
			return ResultUtil.result("-9999", "接入参数不完整！");
		} else {
			String resultStr = forwardingService.findFefund(inputJson);
			return resultStr;
		} 
	}
}
