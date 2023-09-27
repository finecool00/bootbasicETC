package com.simple.basic.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.simple.basic.command.UploadVO;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@Value("${project.upload.path}") //application.properties의 값 가져오기
	private String uploadPath;
	
	
	//폴더 생성 함수
	public String makeFolder() {
		String path = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		File file = new File(uploadPath + "/" + path);
		
		if(file.exists() == false) { //존재하면 true, 존재하지 않으면 false
			file.mkdirs();
		}
		return path; //날짜 폴더명 반환
	}

	@GetMapping("/upload")
	public String upload() {
		return "upload/upload";
	}
	
	//파일데이터는 MultipartFile객체로 받음
	@PostMapping("/upload_ok")
	public String upload_ok(@RequestParam("file") MultipartFile file) {
		//파일 이름을 받음
		String originname = file.getOriginalFilename();
		
		//브라우저 별로 파일의 경로가 다를 수 있기 떄문에 \\기준으로 파일명만 잘라서 다시 저장
		String filename = originname.substring(originname.lastIndexOf("\\") + 1);
		
		//파일 사이즈
		long size = file.getSize();
		
		//동일한 파일을 재업로드 시 기존 파일을 덮어버리기 때문에 난수이름으로 파일명을 바꿔서 올림
		String uuid = UUID.randomUUID().toString();
		
		//날짜별로 폴더 생성
		String filepath = makeFolder();
		
		
		//save할 경로
		String savepath = uploadPath + "/" + filepath + "/" + uuid + "_" +filename;
		
		
		
		File saveFile = new File(savepath);
		try {
			file.transferTo(saveFile); //파일 업로드를 진행
		} catch (Exception e) {
			System.out.println("파일업로드 중 error발생");
			e.printStackTrace();
		}
	
		//데이터베이스에 추후 저장
		System.out.println(originname);
		System.out.println("실제파일명: "+ filename);
		System.out.println("난수값: "+ uuid);
		System.out.println(size);
		System.out.println(uploadPath);
		System.out.println("세이브할 경로: " + savepath);
		System.out.println("날짜폴더경로: " + filepath);
		System.out.println("===========================");
		
		return "upload/upload_ok";
	}
	
	//복수태그를 사ㅏ용한 다중파일 업로드 - List(MutipartFile)
	@PostMapping("/upload_ok2")
	public String upload_ok2(@RequestParam("file") List<MultipartFile> list) {
	
		//빈 file객체는 제외하고 새로운 리스트 생성
		list = list.stream().filter(f -> f.isEmpty() == false).collect(Collectors.toList());
		
		for(MultipartFile file : list) {
			
			System.out.println(file.isEmpty());
			
			
			//파일 이름을 받음
			String originname = file.getOriginalFilename();
			
			//브라우저 별로 파일의 경로가 다를 수 있기 떄문에 \\기준으로 파일명만 잘라서 다시 저장
			String filename = originname.substring(originname.lastIndexOf("\\") + 1);
			
			//파일 사이즈
			long size = file.getSize();
			
			//동일한 파일을 재업로드 시 기존 파일을 덮어버리기 때문에 난수이름으로 파일명을 바꿔서 올림
			String uuid = UUID.randomUUID().toString();
			
			//날짜별로 폴더 생성
			String filepath = makeFolder();
			
			
			//save할 경로
			String savepath = uploadPath + "/" + filepath + "/" + uuid + "_" +filename;
			
			File saveFile = new File(savepath);
			try {
				file.transferTo(saveFile); //파일 업로드를 진행
			} catch (Exception e) {
				System.out.println("파일업로드 중 error발생");
				e.printStackTrace();
			}
			
			//데이터베이스에 추후 저장
			System.out.println(originname);
			System.out.println("실제파일명: "+ filename);
			System.out.println("난수값: "+ uuid);
			System.out.println(size);
			System.out.println(uploadPath);
			System.out.println("세이브할 경로: " + savepath);
			System.out.println("날짜폴더경로: " + filepath);
			
		} //end for
		
		return "upload/upload_ok";
	}
	
	//multiple옵션을 사용한 다중파일 업로드
	@PostMapping("/upload_ok3")
	public String upload_ok3(MultipartHttpServletRequest files) { //MultipartFile보다 상위
		List<MultipartFile> list = files.getFiles("file"); // 클라이언트의 input name값 : 다중으로 올린 값이 list 에 저장됨
	for(MultipartFile file : list) {
			
			System.out.println(file.isEmpty());
			
			
			//파일 이름을 받음
			String originname = file.getOriginalFilename();
			
			//브라우저 별로 파일의 경로가 다를 수 있기 떄문에 \\기준으로 파일명만 잘라서 다시 저장
			String filename = originname.substring(originname.lastIndexOf("\\") + 1);
			
			//파일 사이즈
			long size = file.getSize();
			
			//동일한 파일을 재업로드 시 기존 파일을 덮어버리기 때문에 난수이름으로 파일명을 바꿔서 올림
			String uuid = UUID.randomUUID().toString();
			
			//날짜별로 폴더 생성
			String filepath = makeFolder();
			
			
			//save할 경로
			String savepath = uploadPath + "/" + filepath + "/" + uuid + "_" +filename;
			
			File saveFile = new File(savepath);
			try {
				file.transferTo(saveFile); //파일 업로드를 진행
			} catch (Exception e) {
				System.out.println("파일업로드 중 error발생");
				e.printStackTrace();
			}
			
			//데이터베이스에 추후 저장
			System.out.println(originname);
			System.out.println("실제파일명: "+ filename);
			System.out.println("난수값: "+ uuid);
			System.out.println(size);
			System.out.println(uploadPath);
			System.out.println("세이브할 경로: " + savepath);
			System.out.println("날짜폴더경로: " + filepath);
			
		} //end for
		return "upload/upload_ok";
	}
	
	//비동기 방식으로 받기 -> 폼데이터(@RequestBody는 필요없음)
	@PostMapping("/upload_ok4")
	public @ResponseBody ResponseEntity<String> upload_ok4(UploadVO vo) { // 일반 컨트롤러에서 비동기 방식으로 받을 떄 @ResponseBody 필요
		System.out.println(vo.getFile());
		MultipartFile file = vo.getFile(); //getter
		
		//파일 이름을 받음
		String originname = file.getOriginalFilename();
		
		//브라우저 별로 파일의 경로가 다를 수 있기 떄문에 \\기준으로 파일명만 잘라서 다시 저장
		String filename = originname.substring(originname.lastIndexOf("\\") + 1);
		
		//파일 사이즈
		long size = file.getSize();
		
		//동일한 파일을 재업로드 시 기존 파일을 덮어버리기 때문에 난수이름으로 파일명을 바꿔서 올림
		String uuid = UUID.randomUUID().toString();
		
		//날짜별로 폴더 생성
		String filepath = makeFolder();
		
		
		//save할 경로
		String savepath = uploadPath + "/" + filepath + "/" + uuid + "_" +filename;
		
		File saveFile = new File(savepath);
		try {
			file.transferTo(saveFile); //파일 업로드를 진행
		} catch (Exception e) {
			System.out.println("파일업로드 중 error발생");
			e.printStackTrace();
		}
		
		//데이터베이스에 추후 저장
		System.out.println(originname);
		System.out.println("실제파일명: "+ filename);
		System.out.println("난수값: "+ uuid);
		System.out.println(size);
		System.out.println(uploadPath);
		System.out.println("세이브할 경로: " + savepath);
		System.out.println("날짜폴더경로: " + filepath);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//이미지 띄워주기
	@GetMapping("/display")
	public @ResponseBody byte[] display(@RequestParam("filename") String filename,
										@RequestParam("filepath") String filepath,
										@RequestParam("uuid") String uuid) {
		byte[] data = null;
		//읽어올 경로
		String path = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
		try {
			data = FileCopyUtils.copyToByteArray(new File(path)); //이미지 경로를 바이트 배열로 구함
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	 
}
