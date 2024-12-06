package com.zmkj.mapper.mysql;

import com.zmkj.entity.mysql.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    List<User> searchUsers(String userName);
}
