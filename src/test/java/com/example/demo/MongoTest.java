package com.example.demo;

import com.example.demo.config.Application;
import com.example.demo.mongodb.SequenceId;
import com.example.demo.mongodb.domain.AddressEntity;
import com.example.demo.mongodb.domain.UserEntity;
import com.example.demo.mongodb.operation.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by xxiao on 16-11-9.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test(){
        log.info("=======start=======");
        AddressEntity addressEntity=new AddressEntity();
        addressEntity.setCity("北京");
        addressEntity.setProvince("北京");
        UserEntity userEntity=new UserEntity();
        userEntity.setName("刘刚");
        userEntity.setBirthday(new Date());
        userEntity.setAddresses(new ArrayList<AddressEntity>(){
            {
                add(addressEntity);
                add(addressEntity);
            }
        });
        mongoTemplate.save(userEntity);
        log.info("=======over=======");
    }
    @Test
    public void test1(){
        Query query=new Query(Criteria.where("name").regex(".*刚"));
//        query.addCriteria(Criteria.where("birthday").lte(5));
        log.info("==========="+query);
        log.info("================"+mongoTemplate.findOne(query,UserEntity.class));
    }

    @Test
    public void test2(){
        Query query=new Query(Criteria.where("seq_id").lte(4));
//        query.addCriteria(Criteria.where("birthday").lte(5));
        log.info("==========="+query);
        log.info("================"+mongoTemplate.findOne(query,SequenceId.class));
    }

    @Test
    public void test3(){
        Criteria criteria=new Criteria().andOperator(Criteria.where("name").regex(".*刚"), Criteria.where("name").is("刘刚"));
        Query query=new Query(criteria).limit(10);
//        query.addCriteria(Criteria.where("birthday").lte(5));
        log.info("==========="+query);
        log.info("================"+mongoTemplate.findOne(query,UserEntity.class));
    }

    @Test
    public void test4(){
        Criteria criteria=new Criteria().andOperator(Criteria.where("name").regex(".*刚"), Criteria.where("name").is("刘刚"));
        Query query=new Query(criteria);
        Update update=new Update();
        update.set("name","你好");
//        query.addCriteria(Criteria.where("birthday").lte(5));
        log.info("==========="+query);
        log.info("================"+mongoTemplate.updateMulti(query,update,"user"));
    }

    @Test
    public void test5(){
        log.info("#########"+userRepository.findOne(1L));
    }

    @Test
    public void test6(){
        log.info("$$$$$$$$$$$$$$$$"+userRepository.findAll());
        log.info("=============="+userRepository.findByName("你好"));
    }

    @Test
    public void test7(){
        UserEntity userEntity=userRepository.findOne(1L);
        userEntity.setName("你好");
        userRepository.save(userEntity);
    }

}
