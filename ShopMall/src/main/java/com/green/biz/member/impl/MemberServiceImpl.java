package com.green.biz.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.member.MemberService;
import com.green.biz.member.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public MemberVO getMember(MemberVO vo) {
		return memberDAO.getMember(vo.getId());
	}
	
	@Override
	public MemberVO loginMember(MemberVO vo) {
		return memberDAO.loginMember(vo);
	}
	
	@Override
	public void insertMember(MemberVO vo) {
		memberDAO.insertMember(vo);
	}
	
	@Override
	public MemberVO getMemberByNameAndEmail(MemberVO vo) {
		return memberDAO.getMemberByNameAndEmail(vo);
	}

	@Override
	public MemberVO findPassword(MemberVO vo) {
		return memberDAO.findPassword(vo);
	}
	
	@Override
	public List<MemberVO> listMember(String value) {
		return memberDAO.listMember(value);
	}
	
}



