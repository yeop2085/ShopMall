package com.green.biz.order.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.order.OrderVO;

@Repository
public class OrderDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int selectMaxOseq() {
		System.out.println("Mybatis로 selectMAxOseq() 기능 처리");
		
		return mybatis.selectOne("OrderDAO.selectMaxOseq");
	}
	
	public void insertOrder(OrderVO vo) {
		System.out.println("Mybatis로 insertOrder() 기능 처리");
		
		mybatis.insert("OrderDAO.insertOrder", vo);
	}
	
	public void insertOrderDetail(OrderVO vo) {
		System.out.println("Mybatis로 insertOrderDetail() 기능 처리");
		
		mybatis.insert("OrderDAO.insertOrderDetail", vo);
	}
	
	public List<OrderVO> listOrderById(OrderVO vo) {
		System.out.println("Mybatis로 listOrderById() 기능 처리");
		
		return mybatis.selectList("OrderDAO.listOrderById", vo);
	}
	
	public List<Integer> selectSeqOrdering(String id) {
		System.out.println("Mybatis로 selectSeqOrdering() 기능 처리");
		
		return mybatis.selectList("OrderDAO.selectSeqOrdering", id);
	}
	
	// 전체 주문내역 조회
	// 입력 매개변수 : value - 사용자명 
	public List<OrderVO> listOrder(String value) {
		System.out.println("Mybatis로 listOrder() 기능 처리");
		
		return mybatis.selectList("OrderDAO.listOrder", value);
	}
	
	// 주문처리 완료 수정
	public void updateOrderResult(int pseq) {
		System.out.println("Mybatis로 updateOrderResult() 기능 처리");
		
		mybatis.update("OrderDAO.updateOrder", pseq);
	}
	
	
}


