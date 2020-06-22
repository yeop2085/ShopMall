package com.green.biz.product;

import java.util.List;

import com.green.biz.product.dto.ProductVO;
import com.green.biz.util.Criteria;

public interface ProductService {
	
	// ��ǰ ��ȣ�� ��ǰ ��ȸ
	public ProductVO getProduct(ProductVO vo);
	
	// �Ż�ǰ ��ȸ 
	public List<ProductVO> getNewProductList();
	
	// ����Ʈ ��ǰ ��ȸ
	public List<ProductVO> getBestProductList();
	
	// ��ǰ ������ ��ȸ
	public List<ProductVO> getProductListByKind(ProductVO vo);
	
	// ��ǰ ���� ��ȸ
	public int countProductList(String name);
	
	// ��ǰ ��ü ��� ��ȸ
	public List<ProductVO> listProduct(String name);
	
	// ��ǰ ���
	public void insertProduct(ProductVO vo);
	
	// ��ǰ ����
	public void updateProduct(ProductVO vo);
	
	// �������� ��� ��ȸ
	public List<ProductVO> getListWithPaging(Criteria criteria, String key);
	
	public List<ProductCommentVO> getCommentList(int pseq);
	
	public void saveComment(ProductCommentVO commentVO);
	
	public void updateComment(ProductCommentVO commentVO);
	
	public void deleteComment(int comment_seq);
	
	public List<SalesQuantity> getProductSales();
	
	
}





