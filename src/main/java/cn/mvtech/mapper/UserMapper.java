package cn.mvtech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.mvtech.beans.User;
import net.sf.json.JSONObject;
@Mapper
public interface UserMapper {
	//查询所有的用户
	public List<User> findAll();
	//添加用户
	public int addUser(JSONObject paramsJson);
	public int addUserTest(@Param("i")int i,@Param("j")int j);
}
