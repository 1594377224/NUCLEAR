package cn.hse.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.hse.beans.CheckAndDanger;
@Mapper
public interface CheckAndDangerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CheckAndDanger record);

    int insertSelective(CheckAndDanger record);

    CheckAndDanger selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CheckAndDanger record);

    int updateByPrimaryKey(CheckAndDanger record);

	int deldangerIdRela(Integer dangerId);
}