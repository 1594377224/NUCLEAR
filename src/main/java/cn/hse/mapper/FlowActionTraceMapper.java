package cn.hse.mapper;

import cn.hse.beans.FlowActionTrace;

public interface FlowActionTraceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlowActionTrace record);

    int insertSelective(FlowActionTrace record);

    FlowActionTrace selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlowActionTrace record);

    int updateByPrimaryKey(FlowActionTrace record);
}