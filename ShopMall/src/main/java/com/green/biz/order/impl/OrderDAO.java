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
		System.out.println("Mybatis�� selectMAxOseq() ��� ó��");
		
		return mybatis.selectOne("OrderDAO.selectMaxOseq");
	}
	
	public void insertOrder(OrderVO vo) {
		System.out.println("Mybatis�� insertOrder() ��� ó��");
		
		mybatis.insert("OrderDAO.insertOrder", vo);
	}
	
	public void insertOrderDetail(OrderVO vo) {
		System.out.println("Mybatis�� insertOrderDetail() ��� ó��");
		
		mybatis.insert("OrderDAO.insertOrderDetail", vo);
	}
	
	public List<OrderVO> listOrderById(OrderVO vo) {
		System.out.println("Mybatis�� listOrderById() ��� ó��");
		
		return mybatis.selectList("OrderDAO.listOrderById", vo);
	}
	
	public List<Integer> selectSeqOrdering(String id) {
		System.out.println("Mybatis�� selectSeqOrdering() ��� ó��");
		
		return mybatis.selectList("OrderDAO.selectSeqOrdering", id);
	}
	
	// ��ü �ֹ����� ��ȸ
	// �Է� �Ű����� : value - ����ڸ� 
	public List<OrderVO> listOrder(String value) {
		System.out.println("Mybatis�� listOrder() ��� ó��");
		
		return mybatis.selectList("OrderDAO.listOrder", value);
	}
	
	// �ֹ�ó�� �Ϸ� ����
	public void updateOrderResult(int pseq) {
		System.out.println("Mybatis�� updateOrderResult() ��� ó��");
		
		mybatis.update("OrderDAO.updateOrder", pseq);
	}
	
	
}


