package cn.hse.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hse.beans.DelayToApplyFor;
import cn.hse.beans.FlowInstance;
import cn.hse.mapper.DelayToApplyForMapper;
import cn.hse.service.DelayToApplyForService;
import cn.hse.service.FlowInstanceService;
import cn.hse.util.ResultUtil;
import net.sf.json.JSONObject;
/**
 * 延期申请
 * @author 
 *
 */
@Service
public class DelayToApplyForServiceImpl implements DelayToApplyForService{
	private static final Logger logger=LogManager.getLogger(ForwardingServiceImpl.class);
	@Autowired
	private DelayToApplyForMapper delayToApplyForMapper;
	@Autowired
	private FlowInstanceService flowInstanceService;
	@Transactional
	@Override
	public String findDelayToApplyFor(JSONObject inputJson) {
		logger.info("[延期申请-入参]"+inputJson);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String,Object> paramMap = new HashMap<String, Object>();
		Map<String,Object> flowActionTraceSubmitMap = new HashMap<String, Object>();
		String userId = inputJson.getString("userId");
		String userName = inputJson.getString("userName");
		String actionTraceId = inputJson.getString("actionTraceId");
		String instanceId = inputJson.getString("instanceId");
		String dangerId = inputJson.getString("dangerId");
		String checkId = inputJson.getString("checkId");
		String reqCompleteDate = inputJson.getString("reqCompleteDate");
		String delayToApplyForDec = inputJson.getString("delayToApplyForDec");
		String delayToApplyForDate = inputJson.getString("delayToApplyForDate");
		String delayToApplyForNo = String.valueOf((Math.random()*9+1)*100000);
		//封装延期申请单对象
		DelayToApplyFor delayToApplyForList=new DelayToApplyFor();
		delayToApplyForList.setDelayToApplyForDate(delayToApplyForDate);
		delayToApplyForList.setDelayToApplyForDec(delayToApplyForDec);
		delayToApplyForList.setReqCompleteDate(reqCompleteDate);
		delayToApplyForList.setDelayToApplyForNo(delayToApplyForNo);
		delayToApplyForList.setUserId(userId);
		delayToApplyForList.setUserName(userName);
		//保存延期申请数据入库delayToApplyFor
		int delayToApplyForNum = delayToApplyForMapper.addDelayToApplyFor(delayToApplyForList);
		int delayToApplyForId=delayToApplyForList.getId();
		//延期申请id，检查表id，隐患表id关系表更新checkAndDanger
		paramMap.put("dangerId", dangerId);
		paramMap.put("checkId", checkId);
		paramMap.put("delayToApplyForId", delayToApplyForId);
		paramMap.put("actionTraceId", actionTraceId);
		paramMap.put("instanceId", instanceId);
		int upCheckAndDangerNum = delayToApplyForMapper.upCheckAndDanger(paramMap);
		if(delayToApplyForNum>0 && upCheckAndDangerNum>0){
			//查询flow模板，flowStep流程节点，flowAction流程动作（查询模板为延期申请发起节点）
			Map<String,Object> flowMap = delayToApplyForMapper.findFlowMap();
			if(flowMap.isEmpty()){
				resultMap.put("resultCode", "-1");
				resultMap.put("resultMsg", "操作失败！");
			} else {
				String flowId = flowMap.get("id").toString();
				String flowName = flowMap.get("flowName").toString();
				String flowCode = flowMap.get("flowCode").toString();
				String stepId = flowMap.get("stepId").toString();
				String stepCode = flowMap.get("stepCode").toString();
				String stepName = flowMap.get("stepName").toString();
				String actionId = flowMap.get("actionId").toString();
				String actionCode = flowMap.get("actionCode").toString();
				String actionName = flowMap.get("actionName").toString();
				//插入一条实例表记录flowInstance
				FlowInstance flowInstance=new FlowInstance();
				flowInstance.setFlowid(flowId);
				flowInstance.setFlowname(flowName);
				flowInstance.setUserid(userId);
				flowInstance.setUsername(userName);
				flowInstance.setApplyusername(userName);
				flowInstance.setApplydatetime(new Date());
				flowInstance.setStatusid("0");
				int flowInstanceNum = flowInstanceService.insertFlowInstance(flowInstance);
				int instanceIdNew=flowInstance.getId();
				//新插入两条数据flowActionTrace（一条延期申请发起流程，一条发送到发起人进行延期申请审批流程）
				flowActionTraceSubmitMap.put("instanceId",instanceIdNew);
				flowActionTraceSubmitMap.put("flowId",flowId);
				flowActionTraceSubmitMap.put("flowName",flowName);
				flowActionTraceSubmitMap.put("flowCode",flowCode);
				flowActionTraceSubmitMap.put("stepId",stepId);
				flowActionTraceSubmitMap.put("stepCode",stepCode);
				flowActionTraceSubmitMap.put("stepName",stepName);
				flowActionTraceSubmitMap.put("actionId",actionId);
				flowActionTraceSubmitMap.put("actionCode",actionCode);
				flowActionTraceSubmitMap.put("actionName",actionName);
				flowActionTraceSubmitMap.put("ownerUserId",userId);
				flowActionTraceSubmitMap.put("ownerUserName",userName);
				flowActionTraceSubmitMap.put("ownerUserDesc","发起人");
				flowActionTraceSubmitMap.put("submitUserId",userId);
				flowActionTraceSubmitMap.put("submitUserName",userName);
				flowActionTraceSubmitMap.put("submitUserDesc","发起流程");
//				flowActionTraceSubmitMap.put("arriveTime",arriveTime);
				int flowActionTraceSubmitNum = delayToApplyForMapper.addFlowActionTraceSubmit(flowActionTraceSubmitMap);
				//插入一条实例id与延期申请单id的关系表
				Map<String,Object> instanceAndDelayMap = new HashMap<String, Object>();
				instanceAndDelayMap.put("delayToApplyForId", delayToApplyForId);
				instanceAndDelayMap.put("instanceId", instanceIdNew);
				int instanceAndDelayNum = delayToApplyForMapper.addInstanceAndDelay(instanceAndDelayMap);
				if(flowInstanceNum>0 && flowActionTraceSubmitNum>0 && instanceAndDelayNum>0){
					//查询验证审批人的信息
					Map<String,Object> flowFefund = delayToApplyForMapper.findFefundMap(paramMap);
					if(flowFefund.isEmpty()){
						resultMap.put("resultCode", "-1");
						resultMap.put("resultMsg", "操作失败！");
					} else {
						String ownerUserId = flowFefund.get("ownerUserId").toString();
						String ownerUserName = flowFefund.get("ownerUserName").toString();
						//查询模板为延期申请审批节点
						Map<String,Object> flowDalayMap = delayToApplyForMapper.findFlowflowDalayMap();
						paramMap.put("instanceIdNew", instanceIdNew);
						paramMap.put("flowId", flowId);
						paramMap.put("flowName", flowName);
						paramMap.put("flowCode", flowCode);
						paramMap.put("stepId", flowDalayMap.get("stepId"));
						paramMap.put("stepName", flowDalayMap.get("stepName"));
						paramMap.put("stepCode", flowDalayMap.get("stepCode"));
						paramMap.put("ownerUserId", ownerUserId);
						paramMap.put("ownerUserName", ownerUserName);
						paramMap.put("ownerUserDesc", "延期申请审批");
						int addNum = delayToApplyForMapper.addFlowActionTrace(paramMap);
						if(addNum>0){
							resultMap.put("resultCode", "0");
							resultMap.put("resultMsg", "操作成功！");
						} else {
							resultMap.put("resultCode", "-1");
							resultMap.put("resultMsg", "操作失败！");
						}
					}
				} else {
					resultMap.put("resultCode", "-1");
					resultMap.put("resultMsg", "操作失败！");
				}
			}
		} else {
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
		}
		return ResultUtil.result("0", resultMap, new ArrayList<Map<String, Object>>());
	}

}
