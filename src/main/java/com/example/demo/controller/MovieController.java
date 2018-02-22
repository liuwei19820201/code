package com.example.demo.controller;

import com.example.demo.mybatis.po.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * Created by 刘巍 on 2017/7/2.
 */
@RestController
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    /*
    加载内置属性
     */
    @Value("${user.userServiceUrl}")
    private String userServiceUrl;


    @PostConstruct //构造函数后执行
    public void aaaa(){
        System.out.println("PostConstruct===！！！！！！！！！！！！===="+userServiceUrl);
    }

    @PreDestroy  //bean销毁前执行
    public void dddd(){
        System.out.println("PreDestroy===@@@@@@@@@@@@@@@===="+userServiceUrl);
    }

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id){
        User user = this.restTemplate.getForObject(this.userServiceUrl+id, User.class);
        System.out.println("user:"+user);
        return user;
    }

    /**
     * 查询microservice-provider-user服务的信息并返回
     * @return microservice-provider-user服务的信息
     */
//    @GetMapping("/user-instance")
//    public List<ServiceInstance> showInfo(){
//        return this.discoveryClient.getInstances("microservice-provider-user");
//    }
}
