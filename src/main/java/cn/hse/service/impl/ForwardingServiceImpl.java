package cn.hse.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hse.mapper.ForwardingServiceMapper;
import cn.hse.service.ForwardingService;
import cn.hse.util.ResultUtil;
import net.sf.json.JSONObject;
/**
 * 整改节点待办-转发
 * @author 
 *
 */
@Service
public class ForwardingServiceImpl implements ForwardingService{
	private static final Logger logger=LogManager.getLogger(ForwardingServiceImpl.class);

	@Autowired
	private ForwardingServiceMapper forwardingServiceMapper;
	/*
	 * 整改节点待办-转发
	 */
	@Override
	public String findForwarding(JSONObject inputJson) {
		logger.info("[整改节点待办-转发入参]"+inputJson);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String,Object> paramMap = new HashMap<String, Object>();
		String userId = inputJson.getString("userId");
		String userName = inputJson.getString("userName");
		String actionTraceId = inputJson.getString("actionTraceId");
		String ownerUserId = inputJson.getString("ownerUserId");
		String ownerUserName = inputJson.getString("ownerUserName");
		String flowName = inputJson.getString("flowName");
		String flowCode = inputJson.getString("flowCode");
		String flowId = inputJson.getString("flowId");
		String instanceId = inputJson.getString("instanceId");
		String stepId = inputJson.getString("stepId");
		String stepName = inputJson.getString("stepName");
		String stepCode = inputJson.getString("stepCode");
		paramMap.put("userId", userId);
		paramMap.put("userName", userName);
		paramMap.put("userDec", "整改转发");
		paramMap.put("actionTraceId", actionTraceId);
		paramMap.put("ownerUserId", ownerUserId);
		paramMap.put("ownerUserName", ownerUserName);
		paramMap.put("ownerUserDesc", "整改责任人");
		paramMap.put("flowName", flowName);
		paramMap.put("flowCode", flowCode);
		paramMap.put("flowId", flowId);
		paramMap.put("instanceId", instanceId);
		paramMap.put("stepId", stepId);
		paramMap.put("stepName", stepName);
		paramMap.put("stepCode", stepCode);
		//查询模板信息--转发流程
		Map<String,Object> flowMap = forwardingServiceMapper.findFlow();
		if(flowMap.isEmpty()){
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
		} else {
			String actionId = flowMap.get("actionId").toString();
			String actionCode = flowMap.get("actionCode").toString();
			String actionName = flowMap.get("actionName").toString();
			paramMap.put("actionId", actionId);
			paramMap.put("actionCode", actionCode);
			paramMap.put("actionName", actionName);
			int upNum = forwardingServiceMapper.upFlowActionTrace(paramMap);
			if (upNum < 0) {
				resultMap.put("resultCode", "-1");
				resultMap.put("resultMsg", "操作失败！");
			} else {
				int addNum = forwardingServiceMapper.addFlowActionTrace(paramMap);
				if(addNum < 0 ){
					resultMap.put("resultCode", "-1");
					resultMap.put("resultMsg", "操作失败！");
				} else {
					resultMap.put("resultCode", "0");
					resultMap.put("resultMsg", "操作成功！");
				}
			}
		}
		return ResultUtil.result("0", resultMap, new ArrayList<Map<String, Object>>());
	}
	
	/*
	 * 整改节点待办-退回 refund
	 */
	
	
	
	
}
