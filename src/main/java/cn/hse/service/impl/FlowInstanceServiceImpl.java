package cn.hse.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hse.beans.FlowInstance;
import cn.hse.mapper.FlowInstanceMapper;
import cn.hse.service.FlowInstanceService;

@Service
@Transactional
public class FlowInstanceServiceImpl implements FlowInstanceService {
	@Autowired
	private FlowInstanceMapper flowInstanceMapper;

	@Override
	public int insertFlowInstance(FlowInstance flowInstance) {
		return flowInstanceMapper.insert(flowInstance);
	}

	@Override
	public int updateInstance(Integer instanceId) {
		FlowInstance flowInstance=new FlowInstance();
		flowInstance.setId(instanceId);
		flowInstance.setStatusid("1");   //整改中
		return flowInstanceMapper.updateByPrimaryKeySelective(flowInstance);
	}

	@Override
	public int updateInstanceEnd(Integer instanceId) {
		FlowInstance flowInstance=new FlowInstance();
		flowInstance.setId(instanceId);
		flowInstance.setStatusid("2");   //完成
		return flowInstanceMapper.updateByPrimaryKeySelective(flowInstance);
	}
	//插入信息到抄送人delivery表
	@Override
	public int addDelivery(Map<String, Object> deliveryMap) {
		
		
		return flowInstanceMapper.addDelivery(deliveryMap);
	}

}
