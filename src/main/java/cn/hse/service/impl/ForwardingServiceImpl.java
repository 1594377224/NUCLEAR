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
import cn.hse.util.NodeSyn;
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
					//转发信息同步数据给用友接口
					Map<String,Object> paramsMap = new HashMap<String, Object>();
					paramsMap.put("projNo", inputJson.getString("projNo"));//项目编号
					paramsMap.put("recordNo", inputJson.getString("recordNo")); //检查编号
					paramsMap.put("lineNo", inputJson.getString("lineNo"));  //序号
					paramsMap.put("stepId", stepId);//节点id
					paramsMap.put("stepName", stepName);//节点名称
					paramsMap.put("stepCode", stepCode);//节点编码
					paramsMap.put("ownerUserId", userId);//当前用户id
					paramsMap.put("ownerUserName", userName);//当前用户名称
					paramsMap.put("ownerUserDesc", "发起人"); //当前用户描述
					paramsMap.put("submitUserId", ownerUserId);//提交人id
					paramsMap.put("submitUserName", ownerUserName);//提交人名称
					paramsMap.put("submitUserDesc", "整改责任人");//提交人描述
					NodeSyn nodeSyn=new NodeSyn();
					String returnObj = nodeSyn.synNodeData(paramsMap);
					logger.info("[转发--调用用友接口同步节点数据--结果]"+returnObj);
				}
			}
		}
		return ResultUtil.result("0", resultMap, new ArrayList<Map<String, Object>>());
	}
	
	/*
	 * 整改节点待办-退回 refund
	 */
	@Override
	public String findFefund(JSONObject inputJson) {
		logger.info("[整改节点待办-退回入参]"+inputJson);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String,Object> paramMap = new HashMap<String, Object>();
		String userId = inputJson.getString("userId");
		String userName = inputJson.getString("userName");
		String actionTraceId = inputJson.getString("actionTraceId");
		String instanceId = inputJson.getString("instanceId");
		paramMap.put("userId", userId);
		paramMap.put("userName", userName);
		paramMap.put("userDec", "整改回复节点-退回");
		paramMap.put("actionTraceId", actionTraceId);
		paramMap.put("instanceId", instanceId);
		//查询模板信息--退回流程
		Map<String,Object> flowFefundMap = forwardingServiceMapper.findFefund();
		if(flowFefundMap.isEmpty()){
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
		} else {
			String actionId = flowFefundMap.get("actionId").toString();
			String actionCode = flowFefundMap.get("actionCode").toString();
			String actionName = flowFefundMap.get("actionName").toString();
			paramMap.put("actionId", actionId);
			paramMap.put("actionCode", actionCode);
			paramMap.put("actionName", actionName);
			int upNum = forwardingServiceMapper.upFlowActionTrace(paramMap);
			if (upNum < 0) {
				resultMap.put("resultCode", "-1");
				resultMap.put("resultMsg", "操作失败！");
			} else {
				//查询发起人的信息
				Map<String,Object> flowFefund = forwardingServiceMapper.findFefundMap(paramMap);
				if(flowFefund.isEmpty()){
					resultMap.put("resultCode", "-1");
					resultMap.put("resultMsg", "操作失败！");
				} else {
//					(#{instanceId},#{flowId},#{flowName},#{flowCode},
//							#{stepId},#{stepName},#{stepCode},#{ownerUserId},
//							#{ownerUserName},#{ownerUserDesc},now())
					String flowId = flowFefund.get("flowId").toString();
					String flowName = flowFefund.get("flowName").toString();
					String flowCode = flowFefund.get("flowCode").toString();
					String stepId = flowFefund.get("stepId").toString();
					String stepName = flowFefund.get("stepName").toString();
					String stepCode = flowFefund.get("stepCode").toString();
					String ownerUserId = flowFefund.get("ownerUserId").toString();
					String ownerUserName = flowFefund.get("ownerUserName").toString();
					paramMap.put("flowId", flowId);
					paramMap.put("flowName", flowName);
					paramMap.put("flowCode", flowCode);
					paramMap.put("stepId", stepId);
					paramMap.put("stepName", stepName);
					paramMap.put("stepCode", stepCode);
					paramMap.put("ownerUserId", ownerUserId);
					paramMap.put("ownerUserName", ownerUserName);
					paramMap.put("ownerUserDesc", "整改回复节点-退回-重新发起流程");
					int addNum = forwardingServiceMapper.addFlowActionTrace(paramMap);
					//更新实例表statusId字段
					int upflowInstance = forwardingServiceMapper.upflowInstance(paramMap);
					if(addNum < 0 && upflowInstance < 0){
						resultMap.put("resultCode", "-1");
						resultMap.put("resultMsg", "操作失败！");
					} else {
						resultMap.put("resultCode", "0");
						resultMap.put("resultMsg", "操作成功！");
						//退回信息同步数据给用友接口
						Map<String,Object> paramsMap = new HashMap<String, Object>();
						paramsMap.put("projNo", inputJson.getString("projNo"));//项目编号
						paramsMap.put("recordNo", inputJson.getString("recordNo")); //检查编号
						paramsMap.put("lineNo", inputJson.getString("lineNo"));  //序号
						paramsMap.put("stepId", stepId);//节点id
						paramsMap.put("stepName", stepName);//节点名称
						paramsMap.put("stepCode", stepCode);//节点编码
						paramsMap.put("ownerUserId", userId);//当前用户id
						paramsMap.put("ownerUserName", userName);//当前用户名称
						paramsMap.put("ownerUserDesc", "发起人"); //当前用户描述
						paramsMap.put("submitUserId", ownerUserId);//提交人id
						paramsMap.put("submitUserName", ownerUserName);//提交人名称
						paramsMap.put("submitUserDesc", "整改回复节点-退回-重新发起流程");//提交人描述
						NodeSyn nodeSyn=new NodeSyn();
						String returnObj = nodeSyn.synNodeData(paramsMap);
						logger.info("[退回--调用用友接口同步节点数据--结果]"+returnObj);

					}
				}
			}
		}
		return ResultUtil.result("0", resultMap, new ArrayList<Map<String, Object>>());
	}
	
	
	
	
	
}
