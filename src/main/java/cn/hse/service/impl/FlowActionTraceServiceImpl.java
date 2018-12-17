package cn.hse.service.impl;

import java.util.Date;

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
		flowActionTrace.setOwnerusername(responsiblePerson);
		flowActionTrace.setOwneruserid(responsiblePersonId);
		flowActionTrace.setStepcode(flowStep.getStepcode());
		flowActionTrace.setStepid(flowStep.getStepid());
		flowActionTrace.setStepname(flowStep.getStepname());
		int a=flowActionTraceMapper.insertSelective(flowActionTrace);
		logger.info("添加整改数据=="+a);
		Integer instanceId=flowActionTrace.getId();   //获取流程实例Id
		//根据流程实例ID来更新实例状态
		FlowInstance record=new FlowInstance();
		record.setStatusid("0");
		record.setId(instanceId);
		int b=flowInstanceMapper.updateByPrimaryKeySelective(record);
		logger.info("更新实例数据状态=="+b);
		if (a==0||b==0) {
			return 0;
		}
		return 1;
	}
	
	//整改提交
	@Override
	public int updateChangeInfo(FlowActionTrace flowActionTrace,Integer instanceId,String responsiblePersonId,String responsiblePerson) {
		//查询操作节点
		FlowAction flowAction=flowActionMapper.selectByPrimaryKey(3);
		flowActionTrace.setActionid(flowAction.getActionid());
		flowActionTrace.setActionname(flowAction.getActionname());
		flowActionTrace.setActioncode(flowAction.getActioncode());
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
