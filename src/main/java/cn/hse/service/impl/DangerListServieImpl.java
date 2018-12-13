package cn.hse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hse.beans.DangerList;
import cn.hse.mapper.DangerListMapper;
import cn.hse.service.DangerListServie;
@Service
@Transactional
public class DangerListServieImpl implements DangerListServie{
	@Autowired
	private DangerListMapper dangerListMapper;
	@Override
	public int insertDanger(DangerList dangerList) {
		return dangerListMapper.insert(dangerList);
	}

}
