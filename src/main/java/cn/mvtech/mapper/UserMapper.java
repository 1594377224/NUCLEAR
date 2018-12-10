package cn.mvtech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.mvtech.beans.User;
@Mapper
public interface UserMapper {
	//查询所有的用户
	public List<User> findAll();
}
