package com.green.biz.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.order.CartService;
import com.green.biz.order.CartVO;
import com.green.biz.order.OrderService;
import com.green.biz.order.OrderVO;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private CartService cartService;
	
	@Override
	public int selectMaxOseq() {
		return orderDAO.selectMaxOseq();
	}
	
	// 리턴값 : oseq - 주문일련번호 
	@Override
	public int insertOrder(OrderVO vo) {
		
		int oseq = selectMaxOseq();
		
		vo.setOseq(oseq);
		
		// 주문 테이블에 주문번호와 아이디를 저장한다.
		orderDAO.insertOrder(vo);
		
		// 장바구니에서 목록을 가져온다.
		List<CartVO> cartList = cartService.listCart(vo.getId());
		
		// 장바구니에 있는 주문상세 내역을 order_detail 테이블에 저장한다.
		for (CartVO cartVO : cartList) {
			System.out.println("장바구니 내역 : " + cartVO);
			
			OrderVO order = new OrderVO();
			
			order.setOseq(oseq);	//주문번호 설정
			order.setPseq(cartVO.getPseq());	
			order.setQuantity(cartVO.getQuantity());
			
			insertOrderDetail(order);
			
			// cart 테이블에 주문처리를 완료로 수정한다.
			cartService.updateCart(cartVO.getCseq());
			
		}
		
		return oseq;
	}
	
	@Override
	public void insertOrderDetail(OrderVO vo) {
		orderDAO.insertOrderDetail(vo);
	}

	@Override
	public List<OrderVO> listOrderById(OrderVO vo) {
		return orderDAO.listOrderById(vo);
	}

	@Override
	public List<Integer> selectSeqOrdering(String id) {
		return orderDAO.selectSeqOrdering(id);
	}

	@Override
	public List<OrderVO> listOrder(String name) {
		return orderDAO.listOrder(name);
	}
	
	@Override
	public void updateOrderResult(int pseq) {
		orderDAO.updateOrderResult(pseq);
	}
	
	
}


