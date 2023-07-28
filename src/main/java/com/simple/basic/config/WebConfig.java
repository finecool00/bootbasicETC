package com.simple.basic.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.simple.basic.controller.HomeController;

@Configuration //이거 설정파일임
public class WebConfig implements WebMvcConfigurer{ //자바 빈설정을 하기 위해서 상속
	
	
	//IOC 확인
	@Autowired
	ApplicationContext applicationContext;
	
	//value 어노테이션의 사용 - application.properties파일의 값을 참조하는데에 사용
	@Value("${spring.datasource.url}")
	String url;
		
	
	
	
	@Bean //이 메서드를 빈으로 생성 
	public void test() {
		TestBean test = applicationContext.getBean(TestBean.class);
		System.out.println("TestBean이 빈으로 등록됨: "+ test);
		
		HomeController con = applicationContext.getBean(HomeController.class);
		System.out.println("HomeController가 빈으로 등록됨: " + con);
		
		int count = applicationContext.getBeanDefinitionCount();
		System.out.println("IOC 안의 빈 개수: " + count);
		
		System.out.println("application.properties 키값: "+ url);
		
		
	}
	
	//빈생성의 두가지 전략 - 1> @Controller @Service 등등 어노테이션 이용해서 빈 등록
	//                 2> 스프링 설정 파일에 빈으로 등록
	@Bean //- return 객체를 반환하는 모형을 만들면 빈으로 등록이 됨 
	public TestBean test2() {
		TestBean b = new TestBean();
		return b;
	}

}
