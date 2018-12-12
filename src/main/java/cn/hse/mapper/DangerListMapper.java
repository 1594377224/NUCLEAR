package cn.hse.mapper;

import cn.hse.beans.DangerList;

public interface DangerListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DangerList record);

    int insertSelective(DangerList record);

    DangerList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DangerList record);

    int updateByPrimaryKey(DangerList record);
}