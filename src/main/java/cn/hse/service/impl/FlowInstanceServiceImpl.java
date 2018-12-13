package cn.hse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hse.beans.FlowInstance;
import cn.hse.mapper.FlowInstanceMapper;
import cn.hse.service.FlowInstanceService;

@Service
public class FlowInstanceServiceImpl implements FlowInstanceService {
	@Autowired
	private FlowInstanceMapper flowInstanceMapper;

	@Override
	public int insertFlowInstance(FlowInstance flowInstance) {
		return flowInstanceMapper.insert(flowInstance);
	}

}
