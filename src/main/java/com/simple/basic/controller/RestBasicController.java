package com.simple.basic.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.basic.command.SimpleVO;

@RestController 
// @ResponseBody(자바의 객체를 보내도 중간에 responseBody가 JSON 형식으로 변환해서 처리해줌) + @Controller
public class RestBasicController {

	@GetMapping("/basic")
	public String basic() {
		return "<h3>hello world</h3>";
	}
	
	//데이터를 보내는 방법 - 자바의 객체를 보내도 중간에 responseBody가 JSON 형식으로 변환해서 처리해줌 
	@GetMapping("/getObject")
	public SimpleVO getObject() {
		SimpleVO vo = new SimpleVO(1, "Charlie", "Price", LocalDateTime.now());
		return vo;
	}
	
	@GetMapping("/getMap") //map을 보내도 JSON형식으로 보내지게 됨
	public Map<String, Object> getMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "Charlie");
		return map;
	}
	
	@GetMapping("/getList")
	public List<SimpleVO> getList() {
		List<SimpleVO> list = new ArrayList<>();
		list.add(new SimpleVO(1, "Charlie", "Price", LocalDateTime.now()));
		list.add(new SimpleVO(2, "Lola", "London", LocalDateTime.now()));
		return list;
	}
	
	//데이터를 받는 방법
	//get => 쿼리스트링 or 쿼리파라미터 이용해서 넘겨줌(URL 주소에 담아서)
	//post => form 형식 or json 을 이용해 body에 담아서 넘김
	
	//http://localhost:8181/getData?sno=1&first=Lola&last=Simon
	@GetMapping("/getData")
	public SimpleVO getData(SimpleVO vo) {
		System.out.println(vo.toString());
		return new SimpleVO(1, "Charlie", "Price", LocalDateTime.now());
	}
	
	//http://localhost:8181/getData2?sno=1&first=Lola
	//sno, first는 필수 값이 됨
	@GetMapping("/getData2")
	public SimpleVO getData2(@RequestParam("sno") int sno,
							 @RequestParam("first") String first) {
		System.out.println(sno);
		System.out.println(first);
		return new SimpleVO(1, "Charlie", "Price", LocalDateTime.now());
	}
	
	//http://localhost:8181/getData3/3/Charlie
	@GetMapping("/getData3/{sno}/{first}")
	public SimpleVO getData3(@PathVariable("sno") int sno,
							@PathVariable("first") String first) {
		
		System.out.println(sno);
		System.out.println(first);
		
		return new SimpleVO(1, "Charlie", "Price", LocalDateTime.now());
	}
	
	////////////////////////////////////////
	//post방식의 데이터 받기
	
	//보내는 입장에서 form형식의 데이터로 써줘야함
	@PostMapping("/getForm")
	public SimpleVO getForm(SimpleVO vo) {
		System.out.println(vo.toString());
		
		return new SimpleVO(1, "Charlie", "Price", LocalDateTime.now());
	}
	
	//보내는 입장에서 JSON 형식의 데이터를 써줘야함
	/*
	 * { "sno": "1", "first" : "Charlie", "last" : "Price"}
	 */
	@PostMapping("/getJSON")
	public SimpleVO getJSON(@RequestBody SimpleVO vo) { //@RequestBody JSON 데이터를 VO에 맵핑
		System.out.println(vo.toString());
		
		return new SimpleVO(1, "Charlie", "Price", LocalDateTime.now()); 
	}
	
	@PostMapping("/getJSON2")
	public SimpleVO getJSON2(@RequestBody Map<String, Object> map) { //@RequestBody JSON 데이터를 VO에 맵핑
		System.out.println(map.toString());
		
		return new SimpleVO(1, "Charlie", "Price", LocalDateTime.now()); 
	}
	
	//////////////////////////////
	//consumer = 반드시 이 타입으로 보내라 명시
	//producer = 내가 이 타입으로 줄게! 명시(default = application/json), xml을 사용하려면 xml데이터바인딩 라이브러리가 필요
	
	//보내는 타입은 text로 줄게 너는 json데이터로 보내라
	@PostMapping(value = "/getResult", produces = "text/plain", consumes="application/json")
	public String getResult(@RequestBody String str) {
		System.out.println(str);
		return "<h3>문자열</h3>"; 
	}
	
	//응답문서 직접 작성하기 = ResponseEntity<보낼데이터타입>
	@PostMapping("/createResponse")
	public ResponseEntity<SimpleVO> createResponse() {
		//1st
		SimpleVO vo = new SimpleVO(1, "Charlie", "Price", LocalDateTime.now());
//		ResponseEntity<SimpleVO> result = new ResponseEntity<>(vo, HttpStatus.OK); // 데이터, 상태코드
//		ResponseEntity<SimpleVO> result = new ResponseEntity<>(HttpStatus.OK); // 상태코드
		 
		//2nd
		//헤더에 대한 내용 정의 (없어도 됨)
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "JSON WEB TOKEN~");
		header.add("Content-Type", "application/json");
		header.add("Access-Control-Allow-Origin", "*");
		ResponseEntity<SimpleVO> result = new ResponseEntity<>(vo, header, HttpStatus.OK); //데이터, 헤더, 상태코드
		return result;
		
	}
	///////////////////////////////////
	//클라이언트 fetch
	//요청주소: /api/vl/getData
	//메서드 : get
	//클라이언트에서 보낼데이터: num, name
	//서버에서 보낼 데이터는 : SimpleVO
	//받을 수 있는 restAPI를 생성
	
	@GetMapping("/api/vl/getData")
	public SimpleVO getData_api(@RequestParam("num") int num, @RequestParam("first") String first, @RequestParam("last") String last) {
		
		return new SimpleVO(num, first, last, LocalDateTime.now());
	}
	
	@CrossOrigin("*")
	@GetMapping("/api/vl/getData/{num}/{name}")
	public ResponseEntity<SimpleVO> getFetch(@PathVariable("num") int num,
											 @PathVariable("name") String name) {
		System.out.println(num);
		System.out.println(name);
		return new ResponseEntity<>(new SimpleVO(num, name, name, LocalDateTime.now()), HttpStatus.OK);
	}
	
	
	//클라이언트 fetch
	//요청주소 : /api/vl/getInfo
	//메서드 : post
	//클라이언트에서 보낼데이터 : num, name
	//서버에서 보낼 데이터는 List<SimpleVO>
	//받을 수 있는 restAPI를 생성
	//ResponseEntity로 응답
	
	//@CrossOrigin("http://localhost:3000")
	@CrossOrigin("*") //모든 서버에 대한 요청을 허용
	@PostMapping(value="/api/vl/getInfo", consumes="application/json")
	public ResponseEntity<List<SimpleVO>> getInfo(@RequestBody Map<String, Object> map) {
		//1st
		List<SimpleVO> list = new ArrayList<>();
		SimpleVO vo = new SimpleVO(Integer.parseInt((String) map.get("num")) , (String) map.get("name"), (String) map.get("name"), LocalDateTime.now());
		list.add(vo);
	
		System.out.println(vo.toString());
		ResponseEntity<List<SimpleVO>> result = new ResponseEntity<>(list, HttpStatus.OK); //데이터, 헤더, 상태코드
		return result;
		
	}
	
}
