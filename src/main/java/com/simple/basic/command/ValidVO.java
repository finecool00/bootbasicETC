package com.simple.basic.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidVO {
	
	//@Notnull = null 허용x, (String, Integer, Double 등등에 적용)
	//@NotEmpty = null 허용x, 공백허용x (String 에 적용)
	//@NotBlank = null, 공백, 화이트스페이스 허용x (String 적용)
	//@Pattern = 정규표현식과 일치하지 않으면 err
	
	//기본타입은 wrapper타입으로 작성
	//int => Integer, long => Long, double => Double
	
	@NotEmpty(message = "이름은 필수 입니다")
	private String name;
	@NotNull(message = "급여는 숫자로 입력하세요")
	private Integer salary; //Integer은 null값을 가질 수 있음 int는 null값을 가질 수 없음
	
	@NotBlank(message = "공백일 수 없습니다")
	@Email(message = "이메일 형식이어야 합니다") //이메일은 공백이 통과가 됨
	private String email;
	@Pattern(message = "000-0000-0000형식이어야 합니다", regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}")
	private String phone;
}
