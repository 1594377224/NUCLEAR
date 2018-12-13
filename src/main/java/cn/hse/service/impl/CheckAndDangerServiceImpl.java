package cn.hse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hse.beans.CheckAndDanger;
import cn.hse.mapper.CheckAndDangerMapper;
import cn.hse.service.CheckAndDangerService;

@Service
public class CheckAndDangerServiceImpl implements CheckAndDangerService{
	@Autowired
	private CheckAndDangerMapper checkAndDanerMapper;
	@Override
	public int insertCheckAndDanger(CheckAndDanger checkAndDanger) {
		return checkAndDanerMapper.insert(checkAndDanger);
	}

}
