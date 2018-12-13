package cn.hse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hse.beans.Flow;
import cn.hse.mapper.FlowMapper;
import cn.hse.service.FlowService;
@Service
public class FlowServiceImpl implements FlowService {
	@Autowired
	private FlowMapper flowMapper;
	@Override
	public Flow selectByPrimaryKey(String i) {
		return flowMapper.selectByPrimaryKey(i);
	}

}
