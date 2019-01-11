package cn.hse.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ForwardingServiceMapper {

	int upFlowActionTrace(Map<String, Object> paramMap);

	Map<String, Object> findFlow();

	int addFlowActionTrace(Map<String, Object> paramMap);

	Map<String, Object> findFefund();

	Map<String, Object> findFefundMap(Map<String, Object> paramMap);

	int upflowInstance(Map<String, Object> paramMap);

	int addFlowActionTraceData(Map<String, Object> traceDataMap);

}
