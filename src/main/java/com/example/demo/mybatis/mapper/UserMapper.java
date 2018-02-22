package com.example.demo.mybatis.mapper;

import com.example.demo.mybatis.po.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserMapper extends CrudMapper<User>{

    User findById(Long id);

    List<User> findAllList();


}
