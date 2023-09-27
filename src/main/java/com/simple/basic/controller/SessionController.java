package com.simple.basic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class SessionController {

	@GetMapping("/mypage")
	public String mypage(HttpSession session) {
		
		//인증이 안된 상태로 마이페이지에 접근할 경우 
//		if(session.getAttribute("username") == null) {
//			return "redirect:/user/login";
//		}
		
		System.out.println("컨트롤러실행됨");
		return "user/mypage";
	}
	@GetMapping("/login")
	public String login() {
		System.out.println("컨트롤러실행됨");
		return "user/login";
	}
	
	//@PostMapping("/loginForm")
	/*
	 * public String loginForm(HttpServletRequest request) {
	 * 
	 * //로그인시도 성공 HttpSession session = request.getSession();
	 * 
	 * return "user/mypage"; }
	 */
	
	@PostMapping("/loginForm")
	public String loginForm(HttpSession session) {
		System.out.println("컨트롤러실행됨");
		//로그인시도 성공
		if(true) {
			session.setAttribute("username", "aaa123");
			return "redirect:/user/mypage";
			
		} else {
			return "redirect:/"; //홈화면으로...
		}
		
	}
	@GetMapping("/modify")
	public String modify(HttpSession session) {
//		if(session.getAttribute("username") == null) {
//			return "redirect:/user/login";
//		}
		System.out.println("컨트롤러실행됨");
		return "user/modify";
	}
}
