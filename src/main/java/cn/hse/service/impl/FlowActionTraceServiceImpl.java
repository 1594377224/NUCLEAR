package cn.hse.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hse.beans.Flow;
import cn.hse.beans.FlowAction;
import cn.hse.beans.FlowActionTrace;
import cn.hse.beans.FlowInstance;
import cn.hse.beans.FlowStep;
import cn.hse.mapper.FlowActionMapper;
import cn.hse.mapper.FlowActionTraceMapper;
import cn.hse.mapper.FlowInstanceMapper;
import cn.hse.mapper.FlowMapper;
import cn.hse.mapper.FlowStepMapper;
import cn.hse.service.FlowActionTraceService;
import cn.hse.util.Constant;
@Service
public class FlowActionTraceServiceImpl implements FlowActionTraceService {
	private static final Logger logger=LogManager.getLogger(FlowActionTraceServiceImpl.class);
	@Autowired
	private FlowActionTraceMapper flowActionTraceMapper;
	@Autowired
	private FlowInstanceMapper flowInstanceMapper;
	@Autowired
	private FlowStepMapper flowStepMapper;
	@Autowired
	private FlowActionMapper flowActionMapper;
	@Autowired
	private FlowMapper flowMapper;
	@Override
	public int insertFlowActionTrace(FlowActionTrace flowActionTrace) {
		return flowActionTraceMapper.insert(flowActionTrace);
	}
	@Override
	public int insertFlowActionTrace(FlowActionTrace flowActionTrace, String responsiblePerson,String responsiblePersonId) {
		FlowStep flowStep=flowStepMapper.selectByPrimaryKey(2);
		flowActionTrace.setSubmituserid(null);
		flowActionTrace.setSubmitusername(null);
		flowActionTrace.setSubmituserdesc(null);
		flowActionTrace.setActioncode(null);
		flowActionTrace.setActionid(null);
		flowActionTrace.setActionname(null);
		flowActionTrace.setOwneruserdesc(Constant.ZHENG_GAI_REN);
		flowActionTrace.setOwnerusername(responsiblePerson);
		flowActionTrace.setOwneruserid(responsiblePersonId);
		flowActionTrace.setStepcode(flowStep.getStepcode());
		flowActionTrace.setStepid(flowStep.getStepid());
		flowActionTrace.setStepname(flowStep.getStepname());
		flowActionTrace.setArrivetime(new Date());
		int a=flowActionTraceMapper.insertSelective(flowActionTrace);
		//流转表id
		int traceId = flowActionTrace.getId();
		logger.info("添加整改数据=="+a);
		Integer instanceId=flowActionTrace.getId();   //获取流程实例Id
		//根据流程实例ID来更新实例状态
		FlowInstance record=new FlowInstance();
		record.setStatusid("0");
		record.setId(instanceId);
		int b=flowInstanceMapper.updateByPrimaryKeySelective(record);
		logger.info("更新实例数据状态=="+b);
		if (a==0||b==0) {
			return traceId;
		}
		return traceId;
	}
	
	//整改提交
	@Override
	public int updateChangeInfo(FlowActionTrace flowActionTrace,Integer instanceId,String responsiblePersonId,String responsiblePerson) {
		//查询操作节点
		FlowAction flowAction=flowActionMapper.selectByPrimaryKey(3);
		flowActionTrace.setActionid(flowAction.getActionid());
		flowActionTrace.setActionname(flowAction.getActionname());
		flowActionTrace.setActioncode(flowAction.getActioncode());
		flowActionTrace.setArrivetime(new Date());
		//更新当前节点的数据
		int updateResult=flowActionTraceMapper.updateByPrimaryKeySelective(flowActionTrace);
		//插入新的节点数据
		//Integer traceId=flowActionTrace.getId();
		Flow flow=flowMapper.selectByPrimaryKey(1);   //查询流程1
		FlowStep flowStep=flowStepMapper.selectByPrimaryKey(3);  //查询节点
		FlowActionTrace record=new FlowActionTrace();
		record.setInstanceid(instanceId);
		record.setFlowid(flow.getId().toString());
		record.setFlowcode(flow.getFlowcode());
		record.setFlowname(flow.getFlowname());	
		
		record.setStepid(flowStep.getStepid());
		record.setStepcode(flowStep.getStepcode());
		record.setStepname(flowStep.getStepname());

		record.setOwneruserid(responsiblePersonId);
		record.setOwnerusername(responsiblePerson);
		record.setOwneruserdesc(Constant.QUE_REN_REN);
		record.setArrivetime(new Date());
		int insertResult=flowActionTraceMapper.insertSelective(record);
		logger.info("插入数据"+insertResult);
		if (insertResult==0||updateResult==0) {
			return 0;
		}
		return 1;
	}
	
	//整改验证通过
	@Override
	public int updateChange(FlowActionTrace flowActionTrace,Integer instanceId) {
		//更新流转表
		FlowAction flowAction=flowActionMapper.selectByPrimaryKey(6);
		flowActionTrace.setActionid(flowAction.getActionid());
		flowActionTrace.setActionname(flowAction.getActionname());
		flowActionTrace.setActioncode(flowAction.getActioncode());
		int updateResult=flowActionTraceMapper.updateByPrimaryKeySelective(flowActionTrace);
		logger.info("更新结果条数===="+updateResult);
		//插入闭合数据
		/*Flow flow=flowMapper.selectByPrimaryKey(1);   //查询流程1
		FlowStep flowStep=flowStepMapper.selectByPrimaryKey(3);  //查询节点
		FlowAction action=flowActionMapper.selectByPrimaryKey(8);  //查询操作步骤
		FlowActionTrace trace=new FlowActionTrace();
		trace.setInstanceid(instanceId);
		trace.setFlowid(flow.getId().toString());
		trace.setFlowcode(flow.getFlowcode());
		trace.setFlowname(flow.getFlowname());	
		
		trace.setStepid(flowStep.getStepid());
		trace.setStepcode(flowStep.getStepcode());
		trace.setStepname(flowStep.getStepname());
		
		trace.setActionid(action.getActionid());
		trace.setActionname(action.getActionname());
		trace.setActioncode(action.getActioncode());

		trace.setOwneruserid(flowActionTrace.getSubmituserid());
		trace.setOwnerusername(flowActionTrace.getSubmitusername());
		trace.setOwneruserdesc(flowActionTrace.getSubmituserdesc());
		
		trace.setSubmituserid(flowActionTrace.getSubmituserid());
		trace.setSubmitusername(flowActionTrace.getSubmitusername());
		trace.setSubmituserdesc(flowActionTrace.getSubmituserdesc());
		trace.setArrivetime(new Date());
		int c=flowActionTraceMapper.insertSelective(trace);*/
		return updateResult;
	}
	//整改验证不通过
	@Override
	public int updateChangeLast(FlowActionTrace flowActionTrace, Integer instanceId) {
		FlowAction flowAction=flowActionMapper.selectByPrimaryKey(7);  //查询操作步骤
		flowActionTrace.setActionid(flowAction.getActionid());
		flowActionTrace.setActionname(flowAction.getActionname());
		flowActionTrace.setActioncode(flowAction.getActioncode());
		int updateResult=flowActionTraceMapper.updateByPrimaryKeySelective(flowActionTrace);
		Map<String, Object> flowActionTraceMap = new HashMap<String, Object>();
		flowActionTraceMap.put("instanceId", instanceId);
		flowActionTraceMap.put("stepId", "200");
		//根据实例ID和节点ID来查询上一节点
		FlowActionTrace f=flowActionTraceMapper.selectByStepIdAndInstanceId(flowActionTraceMap);
		
		FlowActionTrace trace=new FlowActionTrace();
		trace.setInstanceid(instanceId);
		trace.setFlowid(f.getFlowid());
		trace.setFlowcode(f.getFlowcode());
		trace.setFlowname(f.getFlowname());	
		
		trace.setStepid(f.getStepid());
		trace.setStepcode(f.getStepcode());
		trace.setStepname(f.getStepname());
		
		/*trace.setActionid(action.getActionid());
		trace.setActionname(action.getActionname());
		trace.setActioncode(action.getActioncode());*/

		trace.setOwneruserid(f.getOwneruserid());
		trace.setOwnerusername(f.getOwnerusername());
		trace.setOwneruserdesc(f.getOwneruserdesc());
		
		/*trace.setSubmituserid(flowActionTrace.getSubmituserid());
		trace.setSubmitusername(flowActionTrace.getSubmitusername());
		trace.setSubmituserdesc(flowActionTrace.getSubmituserdesc());*/
		trace.setArrivetime(new Date());
		int c=flowActionTraceMapper.insertSelective(trace);
		if (updateResult==0||c==0) {
			return 0;
		}
		return 1;
	}
	@Override
	public int updateFlowActionTrace(FlowActionTrace flowActionTrace) {
		return flowActionTraceMapper.updateByPrimaryKeySelective(flowActionTrace);
	}
	
	//退回再次提交
	@Override
	public int updateRetResubmit(FlowActionTrace flowActionTrace, Integer instanceId, String responsiblePersonId,
			String responsiblePerson) {
		//查询操作节点
		FlowAction flowAction=flowActionMapper.selectByPrimaryKey(1);
		flowActionTrace.setActionid(flowAction.getActionid());
		flowActionTrace.setActionname(flowAction.getActionname());
		flowActionTrace.setActioncode(flowAction.getActioncode());
		flowActionTrace.setArrivetime(new Date());
		//更新当前节点的数据
		int updateResult=flowActionTraceMapper.updateByPrimaryKeySelective(flowActionTrace);
		//插入新的节点数据
		//Integer traceId=flowActionTrace.getId();
		Flow flow=flowMapper.selectByPrimaryKey(1);   //查询流程1
		FlowStep flowStep=flowStepMapper.selectByPrimaryKey(2);  //查询节点
		FlowActionTrace record=new FlowActionTrace();
		record.setInstanceid(instanceId);
		record.setFlowid(flow.getId().toString());
		record.setFlowcode(flow.getFlowcode());
		record.setFlowname(flow.getFlowname());	
		
		record.setStepid(flowStep.getStepid());
		record.setStepcode(flowStep.getStepcode());
		record.setStepname(flowStep.getStepname());

		record.setOwneruserid(responsiblePersonId);
		record.setOwnerusername(responsiblePerson);
		record.setOwneruserdesc(Constant.ZHENG_GAI_REN);
		record.setArrivetime(new Date());
		int insertResult=flowActionTraceMapper.insertSelective(record);
		logger.info("插入数据"+insertResult);
		if (insertResult==0||updateResult==0) {
			return 0;
		}
		return 1;
	}

}   
    