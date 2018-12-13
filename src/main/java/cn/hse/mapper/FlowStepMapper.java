package cn.hse.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.hse.beans.FlowStep;
@Mapper
public interface FlowStepMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlowStep record);

    int insertSelective(FlowStep record);

    FlowStep selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlowStep record);

    int updateByPrimaryKey(FlowStep record);

	FlowStep selectByStepId(String stepid);
}