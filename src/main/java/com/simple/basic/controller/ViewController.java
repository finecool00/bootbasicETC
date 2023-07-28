package com.simple.basic.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.basic.command.SimpleVO;

@Controller
@RequestMapping("/view")
public class ViewController {
	
	//@RequestMapping(value="/ex01", method=RequestMethod.GET) - 기존 스프링방식
	@GetMapping("/ex01") //post방식은 거부
	public String ex01() {
		return "view/ex01"; //템플릿폴더 밑이 기준 templates.아래부터 경로시작
	}
	
	@GetMapping("/ex02")
	public String ex02(Model model) {
		SimpleVO vo = SimpleVO.builder()
								.sno(1)
								.first("charlie")
								.regdate(LocalDateTime.now())
								.build();
		ArrayList<SimpleVO> list = new ArrayList<>();
		
		for(int i = 1; i <= 10; i++) {
			SimpleVO simpleVo = SimpleVO.builder()
					.sno(i)
					.first("charlie" + i)
					.regdate(LocalDateTime.now())
					.build();
			list.add(simpleVo);
		}
		model.addAttribute("list", list);
		model.addAttribute("vo", vo);
		return "view/ex02";
	}
	
	@GetMapping("/ex03")
	public String ex03(Model model) {
		SimpleVO vo = SimpleVO.builder()
				.sno(1)
				.first("charlie")
				.regdate(LocalDateTime.now())
				.build();
		model.addAttribute("vo", vo);
		
		
		ArrayList<SimpleVO> list = new ArrayList<>();
		for(int i = 1; i <= 10; i++) {
			SimpleVO simpleVo = SimpleVO.builder()
					.sno(i)
					.first("charlie" + i)
					.regdate(LocalDateTime.now())
					.build();
			list.add(simpleVo);
		}
		model.addAttribute("list", list);
		return "view/ex03";
	}
	
	//키를 받는 첫번째 방법 - 쿼리스트링
	@GetMapping("/result")
	public String ex03_result(@RequestParam("sno") int sno,
							  @RequestParam("first") String first) {
		System.out.println("넘어온 값: " + sno + ", " + first);
		return "view/ex03_result";
	}
	
	//키를 받는 두번째 방법 - URL파람
	@RequestMapping("/result02/{a}/{b}")
	public String ex03_result02(@PathVariable("a") String name,
							    @PathVariable("b") int age) {
		System.out.println("넘어온 값: " + name + ", " + age);
		return "view/ex03_result";
	}
	
	@RequestMapping("/ex04")
	public String ex04() {
		return "view/ex04";
	}
	
	//ex02 - 타임르프 템플릿 형식으로 
	@RequestMapping("/ex05")
	public String ex05() {
		return "view/ex05";
	}
	//////////////////////////////////////////////
	@RequestMapping("/quiz")
	public String quiz(Model model) {
		SimpleVO vo = SimpleVO.builder().sno(1).first("Charlie").last("Price").regdate(LocalDateTime.now()).build();
		model.addAttribute("vo", vo);
		return "view/quiz";
	}
	
	@RequestMapping("/quiz_result01")
	public String quiz_result01(@ModelAttribute("sno") int sno,
								@ModelAttribute("name") String name) {
		
		
		
		return "view/quiz_result01";
		
	}

}
