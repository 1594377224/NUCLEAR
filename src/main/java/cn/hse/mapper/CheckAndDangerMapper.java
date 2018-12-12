package cn.hse.mapper;

import cn.hse.beans.CheckAndDanger;

public interface CheckAndDangerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CheckAndDanger record);

    int insertSelective(CheckAndDanger record);

    CheckAndDanger selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CheckAndDanger record);

    int updateByPrimaryKey(CheckAndDanger record);
}