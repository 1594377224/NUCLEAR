package cn.hse.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.hse.beans.CheckList;

@Mapper
public interface CheckListMapper {
    int deleteByPrimaryKey(String id);

    int insert(CheckList record);

    int insertSelective(CheckList record);

    CheckList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CheckList record);

    int updateByPrimaryKey(CheckList record);

}