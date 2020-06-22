package com.green.biz.product.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.product.ProductCommentVO;
import com.green.biz.product.SalesQuantity;
import com.green.biz.product.dto.ProductVO;
import com.green.biz.util.Criteria;

@Repository
public class ProductDAO{
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public ProductVO getProduct(ProductVO vo) {
		
		System.out.println("Mybatis�� getProduct() ��� ó��");
		
		return mybatis.selectOne("ProductDAO.getProduct", vo);
	
	}
	
	public List<ProductVO> getNewProductList() {
		
		System.out.println("Mybatis�� getNewProductList() ��� ó��");
		
		return mybatis.selectList("ProductDAO.getNewProductList");
		
	}
	
	public List<ProductVO> getBestProductList() {
		
		System.out.println("Mybatis�� getBestProductList() ��� ó��");
		
		return mybatis.selectList("ProductDAO.getBestProductList");
		
	}
	
	/*
	 * ī�װ��� ��ȸ 
	 */
	public List<ProductVO> getProductListByKind(ProductVO vo) {
		
		System.out.println("Mybatis�� getProductListByKind() ��� ó��");
		
		return mybatis.selectList("ProductDAO.getProductListByKind", vo);
		
	}
	
	
	// �� ��ǰ ���� ��ȸ
	public int countProductList(String name) {
		System.out.println("Mybatis�� countProductList() ��� ó��");
		
		return mybatis.selectOne("ProductDAO.countProductList", name);
	}
	
	// ��ǰ ��� ��ȸ
	public List<ProductVO> listProduct(String name) {
		System.out.println("Mybatis�� listProduct() ��� ó��");
		
		return mybatis.selectList("ProductDAO.listProduct", name);
	}
	
	// ��ǰ ���	
	public void insertProduct(ProductVO vo) {
		System.out.println("Mybatis�� insertProduct() ��� ó��");
		
		mybatis.insert("ProductDAO.insertProduct", vo);
	}
	
	// ��ǰ���� ����
	public void updateProduct(ProductVO vo) {
		System.out.println("Mybatis�� updateProduct() ��� ó��");
		
		mybatis.update("ProductDAO.updateProduct", vo);
	}
	
	// �������� ��ǰ ��� ��ȸ
	public List<ProductVO> getListWithPaging(Criteria criteria, String key) {
		System.out.println("Mybatis�� getListWithPaging() ��� ó��");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("criteria", criteria);
		map.put("key", key);
		
		return mybatis.selectList("ProductDAO.listWithPaging", map);
	}
	
	// ��ǰ�� ���� �޼ҵ�
	public List<ProductCommentVO> getCommentList(int pseq) {
		System.out.println("Mybatis�� getCommentList() ��� ó��");
		
		return mybatis.selectList("CommentDAO.getCommentList", pseq);
	}
	
	public void saveComment(ProductCommentVO commentVO) {
		System.out.println("Mybatis�� saveComment() ��� ó��");
		
		mybatis.insert("CommentDAO.saveComment", commentVO);
	}
	
	public void updateComment(ProductCommentVO commentVO) {
		System.out.println("Mybatis�� updateComment() ��� ó��");
		
		mybatis.update("CommentDAO.updateComment", commentVO);
	}
	
	public void deleteComment(int comment_seq) {
		System.out.println("Mybatis�� deleteComment() ��� ó��");
		
		mybatis.delete("CommentDAO.deleteComment", comment_seq);
	}
	
	// ��ǰ�� �Ǹ� ����
	public List<SalesQuantity> getProductSales() {
		System.out.println("Mybatis�� getProductSales() ��� ó��");
		
		return mybatis.selectList("ProductDAO.getProductSales");
	}
	
	
	
}



