package com.simple.basic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simple.basic.command.MemoVO;
import com.simple.basic.memo.service.MemoService;

@Controller
@RequestMapping("/memo")
public class MemoController {
	
	@Autowired
	@Qualifier("memoService")
	private MemoService memoService;

	//화면띄우기
	
	//목록화면
	@RequestMapping("/memoList")
	public String memoList(Model model) {
		List<MemoVO> list = memoService.getMemoList();
		model.addAttribute("list", list);
		return "memo/memoList";
	}
	
	//글쓰기화면
	@RequestMapping("/memoWrite")
	public String memoWrite() {
		return "memo/memoWrite";
	}
	
	//memoForm
	@PostMapping("/memoForm")
	public String memoForm(@Valid @ModelAttribute("vo") MemoVO vo, Errors errors, Model model) {
		
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			for(FieldError err : list) {
				if(err.isBindingFailure()) {
					model.addAttribute("memo_" + err.getField(), "잘못된 입력입니다.");
				} else {
					model.addAttribute("memo_" + err.getField(), err.getDefaultMessage());
				}
			}
			return "memo/memoWrite";
			
		} else {
			memoService.memoRegist(vo);
			
		}
		
		return "redirect:/memo/memoList";
	}
	
	
}
