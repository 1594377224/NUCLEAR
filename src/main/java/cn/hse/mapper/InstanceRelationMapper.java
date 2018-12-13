package cn.hse.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.hse.beans.InstanceRelation;
@Mapper
public interface InstanceRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InstanceRelation record);

    int insertSelective(InstanceRelation record);

    InstanceRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InstanceRelation record);

    int updateByPrimaryKey(InstanceRelation record);
}