package cn.hse.mapper;

import cn.hse.beans.FlowInstance;

public interface FlowInstanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlowInstance record);

    int insertSelective(FlowInstance record);

    FlowInstance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlowInstance record);

    int updateByPrimaryKey(FlowInstance record);
}