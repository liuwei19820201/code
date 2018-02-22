package com.example.demo.swagger;

import com.example.demo.common.tools.CustomAnnotationBeanNameGenerator;
import com.example.demo.mongodb.domain.AddressEntity;
import com.example.demo.mongodb.domain.UserEntity;
import com.example.demo.mongodb.operation.UserRepository;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
@Slf4j
@ComponentScan(basePackages = "com.example.demo.mongodb",nameGenerator = CustomAnnotationBeanNameGenerator.class)
@RestController
@RequestMapping("/mongo/")
public class MongoController {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserRepository userRepository;

	@ApiOperation(value="插入Mongo用户", notes="插入Mongo用户")
	@RequestMapping(value = "/saveMongo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void saveMongo() {
		System.out.println("进入saveMongo");
		log.info("=======start=======");
		AddressEntity addressEntity=new AddressEntity();
		addressEntity.setCity("河北");
		addressEntity.setProvince("河北");
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

	@ApiOperation(value="通过名称模糊查找Mongo用户", notes="通过名称模糊查找Mongo用户")
	@RequestMapping(value = "/findMongoByName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void findMongoByName() {
		System.out.println("findMongoByName");
		Query query = new Query(Criteria.where("name").regex(".*威"));
		log.info("==========="+query);
		log.info("================"+mongoTemplate.findOne(query,UserEntity.class));
		log.info("=======over=======");
	}

	@ApiOperation(value="通过Id模糊查找Mongo用户", notes="通过Id模糊查找Mongo用户")
	@RequestMapping(value = "/findMongoById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void findMongoById() {
		System.out.println("findMongoById");
		Query query=new Query(Criteria.where("seq_id").lte(2));
		log.info("==========="+query);
		log.info("================"+mongoTemplate.findOne(query,UserEntity.class));
		log.info("=======over=======");
	}

	@ApiOperation(value="通过名称模糊查找Mongo用户1", notes="通过名称模糊查找Mongo用户1")
	@RequestMapping(value = "/findMongoByName1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void findMongoByName1() {
		System.out.println("findMongoByName1");
		Criteria criteria=new Criteria().andOperator(Criteria.where("name").regex(".*威"), Criteria.where("name").is("刘威"));
		Query query=new Query(criteria).limit(10);
		log.info("==========="+query);
		log.info("================"+mongoTemplate.findOne(query,UserEntity.class));
	}

	@ApiOperation(value="查找更新Mongo用户", notes="查找更新Mongo用户")
	@RequestMapping(value = "/findMongoByUpdate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void findMongoByUpdate() {
		System.out.println("findMongoByUpdate");
		Criteria criteria=new Criteria().andOperator(Criteria.where("name").regex(".*刚"), Criteria.where("name").is("刘刚"));
		Query query=new Query(criteria);
		Update update=new Update();
		update.set("name","你好");
		log.info("==========="+query);
		log.info("================"+mongoTemplate.updateMulti(query,update,"user"));
	}

	@ApiOperation(value="查找Mongo用户2", notes="查找Mongo用户2")
	@RequestMapping(value = "/findMongo2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void findMongo2(){
		log.info("#########"+userRepository.findOne(1L));
	}

	@ApiOperation(value="查找所有用户", notes="查找所有用户")
	@RequestMapping(value = "/findMongoAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void findMongoAll(){
		log.info("$$$$$$$$$$$$$$$$"+userRepository.findAll());
		log.info("=============="+userRepository.findByName("你好"));
	}

	@ApiOperation(value="根据Id查找用户", notes="根据Id查找用户")
	@RequestMapping(value = "/findMongoById2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void findMongoById2(){
		UserEntity userEntity=userRepository.findOne(2L);
		userEntity.setName("你好刘刚");
		userRepository.save(userEntity);
	}


}
