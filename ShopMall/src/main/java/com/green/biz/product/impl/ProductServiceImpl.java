package com.green.biz.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.product.ProductCommentVO;
import com.green.biz.product.ProductService;
import com.green.biz.product.SalesQuantity;
import com.green.biz.product.dto.ProductVO;
import com.green.biz.util.Criteria;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired(required=true)
	private ProductDAO productDAO;
	
	@Override
	public ProductVO getProduct(ProductVO vo) {
		return productDAO.getProduct(vo);
	}
	
	@Override
	public List<ProductVO> getNewProductList() {
		return productDAO.getNewProductList();
	}
	
	@Override
	public List<ProductVO> getBestProductList() {
		return productDAO.getBestProductList();
	}
	
	@Override
	public List<ProductVO> getProductListByKind(ProductVO vo) {
		return productDAO.getProductListByKind(vo);
	}
	
	
	@Override
	public int countProductList(String name) {
		return productDAO.countProductList(name);
	}

	@Override
	public List<ProductVO> listProduct(String name) {
		return productDAO.listProduct(name);
	}

	@Override
	public void insertProduct(ProductVO vo) {
		productDAO.insertProduct(vo);
	}
	
	@Override
	public void updateProduct(ProductVO vo) {
		productDAO.updateProduct(vo);
	}

	@Override
	public List<ProductVO> getListWithPaging(Criteria criteria, String key) {
		return productDAO.getListWithPaging(criteria, key);
	}

	@Override
	public List<ProductCommentVO> getCommentList(int pseq) {
		return productDAO.getCommentList(pseq);
	}

	@Override
	public void saveComment(ProductCommentVO commentVO) {
		productDAO.saveComment(commentVO);
	}

	@Override
	public void updateComment(ProductCommentVO commentVO) {
		productDAO.updateComment(commentVO);
	}

	@Override
	public void deleteComment(int comment_seq) {
		productDAO.deleteComment(comment_seq);
	}

	@Override
	public List<SalesQuantity> getProductSales() {
		return productDAO.getProductSales();
	}
	
	
	
	
}




