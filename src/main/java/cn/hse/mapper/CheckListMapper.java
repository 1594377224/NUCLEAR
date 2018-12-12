package cn.hse.mapper;

import cn.hse.beans.CheckList;

public interface CheckListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CheckList record);

    int insertSelective(CheckList record);

    CheckList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CheckList record);

    int updateByPrimaryKey(CheckList record);
}