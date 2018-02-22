package com.example.demo.mongodb.operation;


import com.example.demo.mongodb.base.BaseRepository;
import com.example.demo.mongodb.domain.UserEntity;

/**
 * @author lagon
 * @time 2017/2/18 9:15
 * @description 用户相关操作接口
 */
public interface UserRepository extends BaseRepository<UserEntity,Long> {

    UserEntity findByName(String name);

}
