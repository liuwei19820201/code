package com.example.demo.service.impl;

import com.example.demo.mybatis.mapper.UserMapper;
import com.example.demo.mybatis.po.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
@Service
public class UserServiceImpl implements UserService {

    public static final String MY_KEY = "mykey";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    public UserMapper userMapper;

    @Override
//    @Cacheable(value = "userList")
    public List<User> findAllList() {
//        redisTemplate.opsForValue().set("name","yingu");
//        System.out.println(redisTemplate.opsForValue().get("name"));

//        Object list1 = redisTemplate.opsForValue().get("list");
//        if(list1 != null){
//            System.out.println(list1);
//            return (List)list1;
//        }
        List<User> list = userMapper.findAllList();
        redisTemplate.opsForValue().set("list",list);
        return list;
    }

//    @Cacheable(value = "user")
    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }
}
