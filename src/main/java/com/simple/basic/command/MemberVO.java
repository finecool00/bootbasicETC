package com.simple.basic.command;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO {
	
	@Pattern(message = "영문자 숫자 포함 8자리이어야 합니다", regexp = "[a-zA-Z0-9]{8,}")
	private String id;
	@Pattern(message = "영문자 숫자 특수문자 포함 8글자 이상이어야 합니다" , regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,}$")
	private String pw;
	
}
