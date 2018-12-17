package cn.hse.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.hse.beans.Flow;

@Mapper
public interface FlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Flow record);

    int insertSelective(Flow record);

    Flow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Flow record);

    int updateByPrimaryKey(Flow record);
}