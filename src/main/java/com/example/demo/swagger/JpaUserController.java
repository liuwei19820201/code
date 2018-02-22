package com.example.demo.swagger;

import com.example.demo.jpa.dao.JpaUserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.config.redis.CacheConstant.*;

@RestController
@RequestMapping("/jpauser/")
public class JpaUserController {
	@Autowired
	private JpaUserRepository jpaUserRepository;

	@ApiOperation(value="查询全部用户", notes="查询全部用户")
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Cacheable(value = USER_All, key = USER_All)
	public Object listAll() {
		System.out.println("进入jpa-listAll方法");
		return jpaUserRepository.findAll();
	}

	@ApiOperation(value="根据ID查询用户", notes="根据ID查询用户")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Cacheable(value = USER, key = "#id == null ? '" + USER_EMP_NO + "' : '" + USER_EMP_NO +  "' + #id")
	public Object findById(@PathVariable Long id) {
		System.out.println("jpa-findById");
		return jpaUserRepository.findOne(id);
	}


}
