package cn.hse.service;

import cn.hse.beans.FlowInstance;

public interface FlowInstanceService {

	int insertFlowInstance(FlowInstance flowInstance);

	int updateInstance(Integer instanceId);

}
