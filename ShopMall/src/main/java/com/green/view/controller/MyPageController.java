package com.green.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.biz.member.MemberVO;
import com.green.biz.order.CartService;
import com.green.biz.order.CartVO;
import com.green.biz.order.OrderService;
import com.green.biz.order.OrderVO;

@Controller
public class MyPageController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	
	/*
	@RequestMapping(value = "/cart_list", method = RequestMethod.GET)
	public String loginView() {
		
		return "mypage/cartList";
		
	}
	*/
	
	@RequestMapping(value = "/cart_insert")
	public String cartInsert(CartVO vo, Model model, HttpServletRequest request) {
		// ���ǿ� ����� ����� ������ ���´�.
		MemberVO loginUser = (MemberVO) request.getSession().getAttribute("loginUser");
		
		if (loginUser == null) {	// �α����� �ȵǾ� ������ �α��� ȭ�� ǥ�� 
			return "member/login";		// jsp ȣ�� 
		} else {
			vo.setId(loginUser.getId());
			
			cartService.insertCart(vo);
			
			return "redirect:/cart_list";	// ����� ȭ�鿡�� Servlet���� ��û�ϴ� ���ڿ� 
		}
		
	}
	
	@RequestMapping(value = "/cart_list") 
	public String cartList(Model model, HttpSession session) {
		// ���ǿ� ����� ����� ������ ���´�.
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login";
		} else {
			List<CartVO> cartList = cartService.listCart(loginUser.getId());
			
			int totalPrice = 0;
			for (CartVO cartVO : cartList) {
				totalPrice += cartVO.getQuantity() * cartVO.getPrice2();
			}
			
			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalPrice);
			
			return "mypage/cartList";
		}
		
	}
	
	@RequestMapping(value = "/cart_delete")
	public String cartDelete(@RequestParam(value = "cseq") int[] cseq, Model model) {
		
		for (int i=0; i<cseq.length; i++) {
			cartService.deleteCart(cseq[i]);
		}
		
		return "redirect:/cart_list";
		
	}
	
	@RequestMapping(value = "/order_insert")
	public String orderInsert(OrderVO vo, Model model, HttpSession session) {
		// ���ǿ� ����� ����� ������ ���´�.
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login";
		} else {
			vo.setId(loginUser.getId());
			
			// �ֹ� ���̺� �ֹ������� �����Ѵ�. 
			int oseq = orderService.insertOrder(vo); 
			vo.setResult("1");
			model.addAttribute("oseq", oseq);
			
		}
		
		return "redirect:/order_list";
		
	}
	
	@RequestMapping(value = "/order_list")
	public String orderListAction(@RequestParam(value = "oseq") int oseq, 
					Model model, OrderVO order, HttpSession session) {
		// ���ǿ� ����� ����� ������ ���´�.
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login";
		} else {
			order.setId(loginUser.getId());
			order.setOseq(oseq);
			order.setResult("1");
			
			List<OrderVO> orderList= orderService.listOrderById(order);
			
			int totalPrice = 0;
			for (OrderVO vo : orderList) {
				totalPrice += vo.getPrice2();
			}
			
			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", totalPrice);
			
			return "mypage/orderList";
		}
		
	}
	
	@RequestMapping(value = "/mypage")
	public String MyPageView(OrderVO order, Model model, HttpSession session) {
		
		// ���ǿ� ����� ����� ������ ���´�.
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login";
		} else {
			List<Integer> oseqList = orderService.selectSeqOrdering(loginUser.getId());
			
			// ������� ��ü �ֹ����� ��� 
			List<OrderVO> orderList = new ArrayList<OrderVO>();
			
			for (int oseq : oseqList) {
				order.setId(loginUser.getId());
				order.setOseq(oseq);
				order.setResult("1");
				
				// �ֹ� ��ȣ�� �ֹ����� ��ȸ 
				List<OrderVO> listBySeq = orderService.listOrderById(order);
				
				// �� �ֹ��� ���� ���
				OrderVO vo = new OrderVO();
				
				vo.setIndate(listBySeq.get(0).getIndate());
				vo.setOseq(listBySeq.get(0).getOseq());
				
				if (listBySeq.size() > 1) {
					vo.setPname(listBySeq.get(0).getPname() + " �� " + (listBySeq.size() -1 ) + "��");
				} else {
					vo.setPname(listBySeq.get(0).getPname());
				}
				
				int totalPrice = 0;
				
				for (int i=0; i<listBySeq.size(); i++) {
					totalPrice += listBySeq.get(i).getPrice2();
				}
				
				vo.setPrice2(totalPrice);
				
				orderList.add(vo);
				
				model.addAttribute("title", "�������� �ֹ� ����");
				model.addAttribute("orderList", orderList);
				
			}
			
		}
		
		return "mypage/mypage";
		
	}
	
	@RequestMapping(value = "/order_detail")
	public String orderDetailView(OrderVO vo, Model model, HttpSession session) {
		// ���ǿ� ����� ����� ������ ���´�.
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login";
		} else {
			vo.setId(loginUser.getId());
			vo.setResult("1");
			
			List<OrderVO> orderList = orderService.listOrderById(vo);
			
			// �ֹ��� ������ ����
			OrderVO orderDetail = new OrderVO();
			orderDetail.setIndate(orderList.get(0).getIndate());
			orderDetail.setOseq(orderList.get(0).getOseq());
			orderDetail.setMname(orderList.get(0).getMname());
			orderDetail.setResult(orderList.get(0).getResult());
			
			int totalPrice = 0;
			for (int i=0; i<orderList.size(); i++) {
				totalPrice += orderList.get(i).getPrice2();
			}
			
			model.addAttribute("orderDetail", orderDetail);
			model.addAttribute("totalPrice", totalPrice);
			model.addAttribute("orderList", orderList);
			model.addAttribute("title", "�ֹ�������");
			
			return "mypage/orderDetail";
			
		}
		
	}
	
	@RequestMapping(value = "/order_all")
	public String orderAllView(OrderVO order, Model model, HttpSession session) {
		
		// ���ǿ� ����� ����� ������ ���´�.
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login";
		} else {
			List<Integer> oseqList = orderService.selectSeqOrdering(loginUser.getId());
			
			// ������� ��ü �ֹ����� ��� 
			
			List<OrderVO> orderList = new ArrayList<OrderVO>();
			
			for (int oseq : oseqList) {
				order.setId(loginUser.getId());
				order.setOseq(oseq);
				order.setResult("1");
				List<OrderVO> orderListing = orderService.listOrderById(order);
				
				// �� �ֹ��� ���� ���
				OrderVO item = orderListing.get(0);
				item.setPname(item.getPname() + " �� " + orderListing.size() + " �� ");
				
				int totalPrice = 0;
				
				for (OrderVO orderVO : orderListing) {
					totalPrice += orderVO.getPrice2() * orderVO.getQuantity();
				}
				
				item.setPrice2(totalPrice);
				
				orderList.add(item);
				
			}
			
			model.addAttribute("title", "�� �ֹ� ����");
			model.addAttribute("orderList", orderList);
			
			return "mypage/mypage";
			
		}
		
	}
	
	
	
}

