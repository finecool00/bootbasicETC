package com.simple.basic.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.simple.basic.util.interceptor.UserAuthHandler;

@Configuration //이거 설정파일임
public class WebConfig implements WebMvcConfigurer{ //자바 빈설정을 하기 위해서 상속
	
	
	
	//인터셉터로 사용할 클래스를 bean
	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}

	//스프링설정에 인터셉터를 추가
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userAuthHandler()).addPathPatterns("/user/**") //모든 user 페이지 포함
												  .excludePathPatterns("/user/login") //user/login 제외
												  .excludePathPatterns("/user/loginForm"); //로그인메서드 제외
	//								              .addPathPatterns("/user/mypage")
	//								              .addPathPatterns(("/user/modify")
		
		//인터셉터는 여러개가 있을 수도 있음
	}
	
	
	
	
	
	
	
	
//	
//	//IOC 확인
//	@Autowired
//	ApplicationContext applicationContext;
//	
//	//value 어노테이션의 사용 - application.properties파일의 값을 참조하는데에 사용
//	@Value("${spring.datasource.url}")
//	String url;
//		
//	
//	
//	
//	@Bean //이 메서드를 빈으로 생성 
//	public void test() {
//		TestBean test = applicationContext.getBean(TestBean.class);
//		System.out.println("TestBean이 빈으로 등록됨: "+ test);
//		
//		HomeController con = applicationContext.getBean(HomeController.class);
//		System.out.println("HomeController가 빈으로 등록됨: " + con);
//		
//		int count = applicationContext.getBeanDefinitionCount();
//		System.out.println("IOC 안의 빈 개수: " + count);
//		
//		System.out.println("application.properties 키값: "+ url);
//		
//		
//	}
//	
//	//빈생성의 두가지 전략 - 1> @Controller @Service 등등 어노테이션 이용해서 빈 등록
//	//                 2> 스프링 설정 파일에 빈으로 등록
//	@Bean //- return 객체를 반환하는 모형을 만들면 빈으로 등록이 됨 
//	public TestBean test2() {
//		TestBean b = new TestBean();
//		return b;
//	}

}
