package cn.hse.mapper;

import cn.hse.beans.FlowStep;

public interface FlowStepMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlowStep record);

    int insertSelective(FlowStep record);

    FlowStep selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlowStep record);

    int updateByPrimaryKey(FlowStep record);
}