package cn.hse.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.hse.beans.FlowInstance;

@Mapper
public interface FlowInstanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlowInstance record);

    int insertSelective(FlowInstance record);

    FlowInstance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlowInstance record);

    int updateByPrimaryKey(FlowInstance record);

	int addDelivery(Map<String, Object> deliveryMap);

	Map<String, Object> findCopyPerson(Map<String, Object> deliveryMap);
}