package cn.hse.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import cn.hse.beans.FlowActionTrace;
@Mapper
public interface FlowActionTraceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlowActionTrace record);

    int insertSelective(FlowActionTrace record);

    FlowActionTrace selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlowActionTrace record);

    int updateByPrimaryKey(FlowActionTrace record);

//	FlowActionTrace selectByStepIdAndInstanceId(@Param("instanceId")Integer instanceId, @Param("i")String i);

	FlowActionTrace selectByStepIdAndInstanceId(Map<String, Object> flowActionTraceMap);
}