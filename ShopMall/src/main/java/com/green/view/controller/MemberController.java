package com.green.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.green.biz.member.MemberService;
import com.green.biz.member.MemberVO;

@Controller
@SessionAttributes("loginUser")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	// 로그인 화면 표시
	@RequestMapping(value = "/login_form", method = RequestMethod.GET)
	public String loginView() {
		System.out.println("일반 사용자 권한으로 로그인하기");
		
		return "member/login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(SessionStatus status) {
		
		status.setComplete();
		
		return "redirect:/login_form";
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, Model model) {
		
		// 데이터베이스에서 사용자 존재 확인
		MemberVO loginUser = memberService.loginMember(vo);
		
		if (loginUser != null) {	// 사용자가 존재하면
			model.addAttribute("loginUser", loginUser);
			
			return "redirect:/index";
		} else {
			return "member/login_fail";
		}
		
	}
	
	// 약관 동의 화면 출력 
	@RequestMapping(value = "/contract", method = RequestMethod.GET)
	public String contractView() {
		System.out.println("약관 동의 페이지 이동 메소드");
		
		return "member/contract";
	}
	
	// 회원 가입 화면 출력 
	@RequestMapping(value = "/join_form", method = RequestMethod.GET)
	public String joinView() {
		System.out.println("회원가입 화면 출력 메소드");
		
		return "member/join";
	}
	
	// 회원 가입 화면 출력 
	@RequestMapping(value = "/join_form", method = RequestMethod.POST)
	public String joinForm() {
		System.out.println("회원가입 화면 출력 메소드");
		
		return "member/join";
	}
	
	// 회원가입 요청 처리
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinAction(@RequestParam(value = "addr1") String addr1, 
							@RequestParam(value = "addr2") String addr2, 
								MemberVO mvo) {
		System.out.println("회원가입 요청 처리");
		
		mvo.setAddress(addr1 + addr2);
		memberService.insertMember(mvo);
		
		return "member/login";		// 로그인 화면 출력 
	}
	
	
	
	
	// 사용중인 아이디인지 조회하는 화면 출력 
	@RequestMapping(value = "/id_check_form", method = RequestMethod.GET) 
	public String idCheckView(@RequestParam(value = "id", defaultValue = "", required = false) String id
					, Model model) {
		
		model.addAttribute("id", id);
		System.out.println("아이디 중복 체크 화면 출력 메소드");
		
		return "member/idcheck";
	}
	
	// 화면에 id를 받아서, 아이디가 존재하는지 데이터베이스에서 조회
	// 사용자가 있으면 message = 1
	// 사용자가 없으면 message = -1
	// JSP 화면 호출 : idcheck.jsp
	
	@RequestMapping(value = "/id_check_form", method = RequestMethod.POST)
	public String idCheckAction(@RequestParam(value = "id", defaultValue = "", required = false) String id,
				Model model) {
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		
		MemberVO user = memberService.getMember(vo);
		
		if (user != null) {	// 사용자가 이미 존재
			model.addAttribute("message", 1);
		} else {	// 사용자가 존재하지 않음. 아이디로 사용가능
			model.addAttribute("message", -1);
		}
		
		model.addAttribute("id", id);
		
		return "member/idcheck";
		
	}
	
	@RequestMapping(value = "/id_check_confirmed", method = RequestMethod.GET)
	public String idCheckConfirmed(MemberVO mvo, Model model) {
		
		model.addAttribute("reid", mvo.getId());
		
		return "member/join";
		
	}
	
	// 우편번호 조회창을 표시
	@RequestMapping(value = "/find_zip_num", method = RequestMethod.GET)
	public String findZipNumView() {
		
		return "member/findZipNum";
		
	}
	
	@RequestMapping(value = "/find_id_form", method = RequestMethod.GET)
	public String findIdView() {
		
		return "member/findIdAndPassword";
		
	}
	
	// 사용자 이름과 이메일로 아이디 찾기 액션 
	@RequestMapping(value = "/find_id", method = RequestMethod.GET)
	public String findIdAction(MemberVO vo, Model model) {
		
		MemberVO member = memberService.getMemberByNameAndEmail(vo);
		
		if (member != null) {	// 아이디가 존재하는 경우
			model.addAttribute("message", "1");
			model.addAttribute("id", member.getId());
		} else {
			model.addAttribute("message", "-1");
		}
		
		return "member/findResult";
		
	}
	
	@RequestMapping(value = "/find_password")
	public String findPassword(MemberVO vo, Model model) {
		System.out.println();
		MemberVO member = memberService.findPassword(vo);
		
		if (member != null) {
			model.addAttribute("message", "1");
			model.addAttribute("pwd", member.getPwd());
		} else {
			model.addAttribute("message", "-1");
		}
			
		return "member/findResultPassword";
	}
	
	
	
	
	
	
	
}


