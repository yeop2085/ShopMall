package com.green.biz.member;

import java.util.List;

// import java.util.List;

// import com.green.biz.product.dto.ProductVO;

public interface MemberService {
	
	public MemberVO getMember(MemberVO vo);
	
	public MemberVO loginMember(MemberVO vo);
	
	public void insertMember(MemberVO vo);
	
	// public List<ProductVO> getMemberList();
	
	public MemberVO getMemberByNameAndEmail(MemberVO vo);
	
	public MemberVO findPassword(MemberVO vo);
	
	public List<MemberVO> listMember(String value);
	
}
