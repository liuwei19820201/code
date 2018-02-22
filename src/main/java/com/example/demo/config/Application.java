package com.example.demo.config;

import com.example.demo.base.PropertyFirstLoadedFromDatabase;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

/**
 * @Slf4j 注解需要添加lombok插件，并且setting中Annotation Processors 中 Enable annotation Processing 后，log才可以使用
 */
@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@PropertyFirstLoadedFromDatabase
@ServletComponentScan
@PropertySource("classpath:config/module.yml")
@ComponentScan(basePackages = "com.example.demo")
@EnableJpaRepositories(basePackages = "com.example.demo")
@MapperScan(basePackages = "com.example.demo.mybatis")
@EntityScan(basePackages = "com.example.demo")
public class Application extends SpringBootServletInitializer{


	//外置属性文件路径
	private final  static String springConfigLocation = "D:/application.yml";

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
		applicationBuilder.properties(new Properties(){
			{
				/**
				 * 自定义默认加载属性文件，如果存在，不扫描（classpath:/,classpath/config/,file:./,file:./config/）
				 * 如果不存在，会扫描（classpath:/,classpath/config/,file:./,file:./config/）
				 */
				put("spring.config.location", "file:" + springConfigLocation);
			}
		});
		return applicationBuilder.sources(Application.class);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.setDefaultProperties(new Properties(){
			{
				/**
				 * 自定义默认加载属性文件，如果存在，不扫描（classpath:/,classpath/config/,file:./,file:./config/）
				 * 如果不存在，会扫描（classpath:/,classpath/config/,file:./,file:./config/）
				 * 加载属性文件的顺序:1、数据库；2、外部配置文件；3、内部配置文件
				 */
				put("spring.config.location", "file:" + springConfigLocation);
			}
		});
		springApplication.run(args);
//		SpringApplication.run(ConsumerMovieApplication.class, args);
		log.info("======================SpringBoot Start Success=====================");
	}
}
