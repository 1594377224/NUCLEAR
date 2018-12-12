package cn.hse.mapper;

import cn.hse.beans.InstanceRelation;

public interface InstanceRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InstanceRelation record);

    int insertSelective(InstanceRelation record);

    InstanceRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InstanceRelation record);

    int updateByPrimaryKey(InstanceRelation record);
}