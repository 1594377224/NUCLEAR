package cn.hse.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hse.beans.FlowActionTrace;
import cn.hse.beans.FlowInstance;
import cn.hse.mapper.FlowActionTraceMapper;
import cn.hse.mapper.FlowInstanceMapper;
import cn.hse.service.FlowActionTraceService;
@Service
public class FlowActionTraceServiceImpl implements FlowActionTraceService {
	private static final Logger logger=LogManager.getLogger(FlowActionTraceServiceImpl.class);
	@Autowired
	private FlowActionTraceMapper flowActionTraceMapper;
	@Autowired
	private FlowInstanceMapper flowInstanceMapper;
	@Override
	public int insertFlowActionTrace(FlowActionTrace flowActionTrace) {
		return flowActionTraceMapper.insert(flowActionTrace);
	}
	@Override
	public int insertFlowActionTrace(FlowActionTrace flowActionTrace, String responsiblePerson,String responsiblePersonId) {
		flowActionTrace.setSubmituserid(null);
		flowActionTrace.setSubmitusername(null);
		flowActionTrace.setSubmituserdesc(null);
		flowActionTrace.setActioncode(null);
		flowActionTrace.setActionid(null);
		flowActionTrace.setActionname(null);
		flowActionTrace.setOwnerusername(responsiblePerson);
		flowActionTrace.setOwneruserid(responsiblePersonId);
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

}
