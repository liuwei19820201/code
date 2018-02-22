package com.example.demo.swagger;

import com.example.demo.service.AccessLogService;
import com.example.demo.service.impl.AccessLogServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accesslog/")
public class AccessLogController {
	@Autowired
	private AccessLogService accessLogService;

	@ApiOperation(value="查询全部accesslog日志", notes="查询全部accesslog日志")
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object listLogAll() {
		System.out.println("listLogAll");
		return accessLogService.findAllList();
	}

	@ApiOperation(value="根据ID查询accesslog日志", notes="根据ID查询accesslog日志")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object findLogById(@PathVariable Long id) {
		System.out.println("findLogById");
		return accessLogService.findById(id);
	}


}
