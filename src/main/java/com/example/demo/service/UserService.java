package com.example.demo.service;

import com.example.demo.mybatis.po.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface UserService {

    public List<User> findAllList();

    User findById(Long id);
}
