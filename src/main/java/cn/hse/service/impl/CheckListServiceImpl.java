package cn.hse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hse.beans.CheckList;
import cn.hse.mapper.CheckListMapper;
import cn.hse.service.CheckListService;
@Service
@Transactional
public class CheckListServiceImpl implements CheckListService{
	@Autowired
	private CheckListMapper checkListMapper;
	@Override
	public int insertCheck(CheckList checkList) {
		return checkListMapper.insert(checkList);
	}
}

