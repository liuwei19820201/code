package com.example.demo;

import com.example.demo.config.Application;
import com.example.demo.messaging.producer.lw.PushLwMessageProducer;
import com.example.demo.mybatis.po.User;
import com.example.demo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {
	@Autowired
	private UserService userService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private PushLwMessageProducer pushLwMessageProducer;

	@Test
	public void contextLoads() {
		System.out.println("findAllList");
		List<User> user = userService.findAllList();
		System.out.println(user.size());
	}

	@Test
	public void testMQ() {
//		System.out.println("testMQ");
//		LwPushReq lwPushReq = new LwPushReq();
//		lwPushReq.setLoanCode("A000002");
//
//		pushLwMessageProducer.sendMessage(lwPushReq);
//
//		User user = userManage.findById((long) 1);
//		System.out.println(user.getName());
	}

	@Test
	public void test() throws Exception {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}

	@Test
	public void testObj() throws Exception {
		User user = userService.findById(2l);
		//user.setBirthday(new Date("2007-10-10"));
		ValueOperations<String, User> operations = redisTemplate.opsForValue();
		redisTemplate.opsForValue().set("user", user);
//		operations.set("user", user);
//		operations.set("user.f", user,1, TimeUnit.SECONDS);
		Thread.sleep(1000);
		//redisTemplate.delete("com.neo.f");
		boolean exists=redisTemplate.hasKey("user.f");
		if(exists){
			System.out.println("exists is true");
		}else{
			System.out.println("exists is false");
		}
		// Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
	}

}
