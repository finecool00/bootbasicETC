package com.simple.basic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.simple.basic.command.BuilderVO;
import com.simple.basic.command.BuilderVO.Builder;
import com.simple.basic.command.BuilderVO2;

@SpringBootTest // 스프링부트테스트클래스
public class JDBCTest {
	
	
	@Test
	public void testCode01() {
//		Builder b = BuilderVO.builder();
//		b = b.age(10);
//		b = b.name("charile");
//		BuilderVO vo = b.build();
		
		//VO는 setter가 없기 때문에 객체불변성을 보장
		BuilderVO vo = BuilderVO.builder().age(10).name("charlie").build();
		
		System.out.println(vo.toString());
		
		BuilderVO2 vo2 = BuilderVO2.builder().name("Lola").age(30).build();
		System.out.println(vo2.toString());
	}
	

}
