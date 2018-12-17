package cn.hse.service;

import cn.hse.beans.FlowActionTrace;

public interface FlowActionTraceService {

	int insertFlowActionTrace(FlowActionTrace flowActionTrace);

	int insertFlowActionTrace(FlowActionTrace flowActionTrace, String responsiblePerson,String responsiblePersonId);

}
