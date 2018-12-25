package cn.hse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.sf.json.JSONObject;
@Mapper
public interface ProcessStatusMapper {
	//待办查询标识（0）
	List<Map<String, Object>> findToDo(Map<String, Object> map);

	int findCountToDo(Map<String, Object> map);
	//已办查询标识（1）
	List<Map<String, Object>> findHaveToDo(Map<String, Object> map);

	int findCountHaveToDo(Map<String, Object> map);
	//流转查询标识（2）
	List<Map<String, Object>> findCirculation(Map<String, Object> map);

	int findCountCirculation(Map<String, Object> map);
	//草稿查询标识（3）
	List<Map<String, Object>> findDraft(Map<String, Object> map);

	int findCountDraft(Map<String, Object> map);
	//流转信息查询
	List<Map<String, Object>> findTransferInformation(JSONObject inputJson);

	int findCountHaveToDoAnd(Map<String, Object> map);

	int findCountCirculationAnd(Map<String, Object> map);

	int findCountToDoAnd(Map<String, Object> map);

	List<Map<String, Object>> findWaitingRead(Map<String, Object> map);

	List<Map<String, Object>> findHaveRead(Map<String, Object> map);

	int findCountWaitingRead(Map<String, Object> map);

	int findCountHaveRead(Map<String, Object> map);

	int updateHaveRead(JSONObject inputJson);

}
