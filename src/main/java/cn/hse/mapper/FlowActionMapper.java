package cn.hse.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.hse.beans.FlowAction;
@Mapper
public interface FlowActionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlowAction record);

    int insertSelective(FlowAction record);

    FlowAction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlowAction record);

    int updateByPrimaryKey(FlowAction record);
}