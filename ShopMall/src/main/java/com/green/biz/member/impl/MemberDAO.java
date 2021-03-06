package com.green.biz.member.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.member.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// id가 존재하는지 확인
	public MemberVO getMember(String id) {
		System.out.println("Mybatis로 getMember() 기능 처리");
		return mybatis.selectOne("MemberDAO.getMember", id);
	}
	
	// id와 pwd를 확인하는 메소드 
	public MemberVO loginMember(MemberVO vo) {
		System.out.println("Mybatis로 loginMember() 기능 처리");
		return mybatis.selectOne("MemberDAO.loginMember", vo);
	}
	
	public void insertMember(MemberVO vo) {
		System.out.println("Mybatis로 insertMember() 기능 처리");
		mybatis.insert("MemberDAO.insertMember", vo);
	}
	
	public MemberVO getMemberByNameAndEmail(MemberVO vo) {
		System.out.println("Mybatis로 getMemberByNameAndEmail() 기능 처리");
		
		return mybatis.selectOne("MemberDAO.getMemberByNameAndEmail", vo);
	}
	
	public MemberVO findPassword(MemberVO vo) {
		System.out.println("Mybatis로 findPassword() 기능 처리");
		
		return mybatis.selectOne("MemberDAO.getFindPassword", vo);
	}
	
	public List<MemberVO> listMember(String value) {
		System.out.println("Mybatis로 listMember() 기능 처리");
		
		return mybatis.selectList("MemberDAO.listMember", value);
	}
	
	
}

