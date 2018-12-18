package cn.hse.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.hse.beans.FlowActionTrace;
@Mapper
public interface FlowActionTraceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlowActionTrace record);

    int insertSelective(FlowActionTrace record);

    FlowActionTrace selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlowActionTrace record);

    int updateByPrimaryKey(FlowActionTrace record);

	FlowActionTrace selectByStepIdAndInstanceId(Integer instanceId, String i);
}