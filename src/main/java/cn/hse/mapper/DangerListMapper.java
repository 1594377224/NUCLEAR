package cn.hse.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.hse.beans.DangerList;

@Mapper
public interface DangerListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DangerList record);

    int insertSelective(DangerList record);

    DangerList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DangerList record);

    int updateByPrimaryKey(DangerList record);

	DangerList selectDangerByCheckId(int checkId);

	int delDangerId(int dangerId);
}