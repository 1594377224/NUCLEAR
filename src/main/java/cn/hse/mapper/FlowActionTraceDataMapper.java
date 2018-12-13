package cn.hse.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.hse.beans.FlowActionTraceData;
@Mapper
public interface FlowActionTraceDataMapper {
    int deleteByPrimaryKey(Integer traceid);

    int insert(FlowActionTraceData record);

    int insertSelective(FlowActionTraceData record);

    FlowActionTraceData selectByPrimaryKey(Integer traceid);

    int updateByPrimaryKeySelective(FlowActionTraceData record);

    int updateByPrimaryKey(FlowActionTraceData record);
}