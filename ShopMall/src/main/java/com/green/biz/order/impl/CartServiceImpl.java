package com.green.biz.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.order.CartService;
import com.green.biz.order.CartVO;

@Service("cartService")
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDAO cartDAO;
	
	@Override
	public void insertCart(CartVO vo) {
		cartDAO.insertCart(vo);
	}
	
	@Override
	public List<CartVO> listCart(String userId) {
		return cartDAO.listCart(userId);
	}
	
	@Override
	public void deleteCart(int cseq) {
		cartDAO.deleteCart(cseq);
	}
	
	@Override
	public void updateCart(int cseq) {
		cartDAO.updateCart(cseq);
	}
	
}

