package com.hao.dom.mapper;

import com.hao.dom.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    // 多条件查询
    public List<User> findByIdAndUsername1(@Param("id") int id,@Param("username") String username);

    // 模糊查询一
    public List<User> findByUsername(String username);

    // 添加用户，获取返回主键
    public void saveUser(User user);

    public void saveUser2(User user);

    public List<User> foreachUser(Integer[] id);

}
