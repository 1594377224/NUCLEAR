package cn.hse.mapper;

import cn.hse.beans.FlowActionTraceData;

public interface FlowActionTraceDataMapper {
    int deleteByPrimaryKey(Integer traceid);

    int insert(FlowActionTraceData record);

    int insertSelective(FlowActionTraceData record);

    FlowActionTraceData selectByPrimaryKey(Integer traceid);

    int updateByPrimaryKeySelective(FlowActionTraceData record);

    int updateByPrimaryKey(FlowActionTraceData record);
}