package cn.hse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
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

}
