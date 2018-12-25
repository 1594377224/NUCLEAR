package cn.hse.service;

import java.util.Map;

import cn.hse.beans.FlowInstance;

public interface FlowInstanceService {

	int insertFlowInstance(FlowInstance flowInstance);

	int updateInstance(Integer instanceId);

	int updateInstanceEnd(Integer instanceId);
	//插入信息到抄送人delivery表
	int addDelivery(Map<String, Object> deliveryMap);

	Map<String, Object> findCopyPerson(Map<String, Object> deliveryMap);

}
