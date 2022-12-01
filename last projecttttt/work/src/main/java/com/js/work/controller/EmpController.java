package com.js.work.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.js.work.service.EmpService;
import com.js.work.vo.EmpVO;
import com.github.pagehelper.PageInfo;

@RestController
//@RequestMapping : 전역변수
@RequestMapping("/api/v1")
@CrossOrigin //누구나 자원(소스)을 요청할 수 있게 권한 해제
public class EmpController {
	
	@Autowired // = new 자동 생성 (Spring이 클래스를 관리해준다)
//	프레임워크(Spring boot)가 클래스를 관리해주는 기술을 Dependency Injection(DI)라고 표현한다.
	private EmpService service;
	
//	특정 부서원 조회
	@GetMapping("/emp/deptno/{deptno}")
	public List<EmpVO> callEmp(@PathVariable int deptno){
		return service.getEmpList(deptno);
	}
	
//	전체 사원 조회
//	PageInfo : 페이징 처리 도와주는 라이브러리
	@GetMapping("/emp")
	public PageInfo<Map<String, Object>> callEmpPage(@RequestParam int page){
		
		List<Map<String, Object>> list = service.getEmpPageList(page);
		int navigatePages = 5; //한블록에 보여줄 페이지수 ex)네이버 웹툰은 1블록에 10페이지
		return new PageInfo<Map<String, Object>>(list, navigatePages);
	}
	
//	사원 추가
	@PostMapping("/emp")
	public int callEmp(@RequestBody EmpVO vo) {
		return service.setEmp(vo);
	}
		
//	사원정보 통계 조회
	@GetMapping("/emp/statistics")
	public Map<String, Object> callStatistics(){
		return service.getEmpStatistics();
	}
	
//	특정 사원 조회
	@GetMapping("/emp/empno/{empno}")
	public EmpVO callEmpByEmpno(@PathVariable int empno) {
		return service.getEmpByEmpno(empno);
	}
	
//	특정 사원 정보 수정
	@PatchMapping("/emp")
	public int callEmpUpdate(@RequestBody EmpVO vo) {
		return service.getEmpUpdate(vo);
	}
	
//	특정 사원 탈퇴(여부 수정) body가 아니라 path로 데이터를 받자
	@PatchMapping("/emp/empno/{empno}")
	public int callEmpUseUpdate(@PathVariable int empno) {
		return service.getFireEmp(empno);
	}

}
