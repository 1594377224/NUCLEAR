package cn.hse.mapper;

import cn.hse.beans.FlowAction;

public interface FlowActionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlowAction record);

    int insertSelective(FlowAction record);

    FlowAction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlowAction record);

    int updateByPrimaryKey(FlowAction record);
}