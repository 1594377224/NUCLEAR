package cn.hse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hse.beans.InstanceRelation;
import cn.hse.mapper.InstanceRelationMapper;
import cn.hse.service.InstanceRelationService;
@Service
public class InstanceRelationServiceImpl implements InstanceRelationService {
	@Autowired
	private InstanceRelationMapper instanceRelationMapper;
	@Override
	public int insert(InstanceRelation instanceRelation) {
		return instanceRelationMapper.insert(instanceRelation);
	}

}
