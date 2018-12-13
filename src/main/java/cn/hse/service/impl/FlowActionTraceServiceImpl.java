package cn.hse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hse.beans.FlowActionTrace;
import cn.hse.mapper.FlowActionTraceMapper;
import cn.hse.service.FlowActionTraceService;
@Service
public class FlowActionTraceServiceImpl implements FlowActionTraceService {
	@Autowired
	private FlowActionTraceMapper flowActionTraceMapper;
	@Override
	public int insertFlowActionTrace(FlowActionTrace flowActionTrace) {
		return flowActionTraceMapper.insert(flowActionTrace);
	}

}
