package cn.hse.mapper;

import cn.hse.beans.Flow;

public interface FlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Flow record);

    int insertSelective(Flow record);

    Flow selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Flow record);

    int updateByPrimaryKey(Flow record);
}