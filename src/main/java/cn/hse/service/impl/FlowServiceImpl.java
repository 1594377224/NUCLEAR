package cn.hse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hse.beans.Flow;
import cn.hse.mapper.FlowMapper;
import cn.hse.service.FlowService;
@Service
@Transactional
public class FlowServiceImpl implements FlowService {
	@Autowired
	private FlowMapper flowMapper;
	@Override
	public Flow selectByPrimaryKey(Integer i) {
		return flowMapper.selectByPrimaryKey(i);
	}

}
