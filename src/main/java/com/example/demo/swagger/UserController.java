package com.example.demo.swagger;

import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;

	@ApiOperation(value="查询全部用户", notes="查询全部用户")
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object listAll() {
		System.out.println("进入listAll方法111");
		return userService.findAllList();
	}

	@ApiOperation(value="根据ID查询用户", notes="根据ID查询用户")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object findById(@PathVariable Long id) {
		System.out.println("findById-111");
		return userService.findById(id);
	}


}
