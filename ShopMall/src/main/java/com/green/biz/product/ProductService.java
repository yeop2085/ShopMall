package com.green.biz.product;

import java.util.List;

import com.green.biz.product.dto.ProductVO;
import com.green.biz.util.Criteria;

public interface ProductService {
	
	// 제품 번호별 상품 조회
	public ProductVO getProduct(ProductVO vo);
	
	// 신상품 조회 
	public List<ProductVO> getNewProductList();
	
	// 베스트 상품 조회
	public List<ProductVO> getBestProductList();
	
	// 상품 종류별 조회
	public List<ProductVO> getProductListByKind(ProductVO vo);
	
	// 상품 개수 조회
	public int countProductList(String name);
	
	// 상품 전체 목록 조회
	public List<ProductVO> listProduct(String name);
	
	// 상품 등록
	public void insertProduct(ProductVO vo);
	
	// 상품 갱신
	public void updateProduct(ProductVO vo);
	
	// 페이지별 목록 조회
	public List<ProductVO> getListWithPaging(Criteria criteria, String key);
	
	public List<ProductCommentVO> getCommentList(int pseq);
	
	public void saveComment(ProductCommentVO commentVO);
	
	public void updateComment(ProductCommentVO commentVO);
	
	public void deleteComment(int comment_seq);
	
	public List<SalesQuantity> getProductSales();
	
	
}





