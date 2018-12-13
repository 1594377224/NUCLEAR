package cn.hse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hse.beans.FlowAction;
import cn.hse.mapper.FlowActionMapper;
import cn.hse.service.FlowActionService;
@Service
public class FlowActionServiceImpl implements FlowActionService {
	@Autowired
	private FlowActionMapper flowActionMapper;
	@Override
	public FlowAction selectFlowAction(int i) {
		return flowActionMapper.selectByPrimaryKey(i);
	}

}
