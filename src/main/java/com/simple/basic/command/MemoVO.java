package com.simple.basic.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemoVO {
	
	private int mno;
	@Pattern(message = "5글자 이상 입력하세요", regexp = "^.{5,}$")
	private String memo;
	@Pattern(regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}", message = "000-0000-0000 형식이어야 합니다")
	private String phone;
	@Pattern(regexp = "^.{4}$", message = "비밀번호는 4자리 숫자입니다.")
	private String pw;
	private String secret;

}
