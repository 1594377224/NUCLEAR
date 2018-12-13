package cn.hse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hse.beans.FlowStep;
import cn.hse.mapper.FlowStepMapper;
import cn.hse.service.FlowStepService;
@Service
public class FlowStepServiceImpl implements FlowStepService {
	@Autowired
	private FlowStepMapper flowStepMapper;

	@Override
	public FlowStep selectFlowStep(String stepid) {
		return flowStepMapper.selectByStepId(stepid);
	}

}
