package cn.hse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hse.beans.DangerList;
import cn.hse.mapper.CheckAndDangerMapper;
import cn.hse.mapper.DangerListMapper;
import cn.hse.service.DangerListServie;
@Service
@Transactional
public class DangerListServieImpl implements DangerListServie{
	@Autowired
	private DangerListMapper dangerListMapper;
	@Autowired
	private CheckAndDangerMapper checkAndDangerMapper;
	@Override
	public int insertDanger(DangerList dangerList) {
		return dangerListMapper.insert(dangerList);
	}
	@Override
	public DangerList selectDangerByCheckId(int checkId) {
		return dangerListMapper.selectDangerByCheckId(checkId);
	}
	@Override
	public int delCheckAndDanger(int dangerId) {
		int a=dangerListMapper.delDangerId(dangerId);
		int b=checkAndDangerMapper.deldangerIdRela(dangerId);
		if (a==0||b==0) {
			return 0;
		}
		return 1;
	}
	@Override
	public int updateDanger(DangerList dangerList) {
		return dangerListMapper.updateByPrimaryKeySelective(dangerList);
	}

}
