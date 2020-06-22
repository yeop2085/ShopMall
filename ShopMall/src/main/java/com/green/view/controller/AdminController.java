package com.green.view.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.green.biz.member.MemberService;
import com.green.biz.member.MemberVO;
import com.green.biz.order.OrderService;
import com.green.biz.order.OrderVO;
import com.green.biz.product.ProductService;
import com.green.biz.product.SalesQuantity;
import com.green.biz.product.dto.ProductVO;
import com.green.biz.qna.QnaService;
import com.green.biz.qna.QnaVO;
import com.green.biz.util.Criteria;
import com.green.biz.util.PageMaker;
import com.green.biz.worker.WorkerService;
import com.green.biz.worker.WorkerVO;

@Controller
@SessionAttributes("adminUser")
public class AdminController {
	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private QnaService qnaService;
	
	
	// 관리자 로그인 화면 페이지 이동
	@RequestMapping(value = "/admin_login_form", method = RequestMethod.GET)
	public String AdminLoginView() {
		System.out.println("관리자 권한으로 로그인하기");
		
		return "admin/main";
		
	}
	
	@RequestMapping(value = "/admin_login")
	public String login(@RequestParam(value = "workerId") String workerId, 
						@RequestParam(value = "workerPwd") String workerPwd,
						Model model) {
		
		int result = workerService.workerCheck(workerId, workerPwd);
		
		if (result == 1) {
			WorkerVO adminUser = workerService.getEmployee(workerId);
			
			model.addAttribute("adminUser", adminUser);
			
			return "redirect:/admin_product_list";
			
		} else {
			if (result == 0) {
				model.addAttribute("message", "비밀번호를 확인하세요.");
			} else {
				model.addAttribute("message", "아이디를 확인하세요.");
			}
			
			return "admin/main";
			
		}
		
	}
	
	@RequestMapping(value = "/admin_logout")
	public String admin_logout(SessionStatus status) {
		
		status.setComplete();
		
		return "admin/main";
		
	}
		
	@RequestMapping(value = "/admin_product_list")
	public String adminProductList(@RequestParam(value = "key", defaultValue="") String key,
								Criteria criteria, 
								HttpSession session, Model model) {
		
		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");
		
		if (adminUser == null) {
			return "admin/main";
		} else {
			System.out.println("페이지 범위 : " + criteria);
			
			// List<ProductVO> listProd = productService.listProduct("");
			List<ProductVO> listProd = productService.getListWithPaging(criteria, key);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(criteria);
			// 총 목록수를 DB에서 조회
			int totalCount = productService.countProductList(key);
			pageMaker.setTotalCount(totalCount);
			System.out.println("페이징 정보 : " + pageMaker);
			
			model.addAttribute("productList", listProd);
			model.addAttribute("productListSize", listProd.size());
			model.addAttribute("pageMaker", pageMaker);
			
			return "admin/product/productList";
		}
		
	}
	
	@RequestMapping(value = "/admin_product_write_form")
	public String adminProductWriteView(Model model) {
		
		String kindList[] = {"Heels", "Boots", "Sandals", "Slipers", 
							"Sneekers", "Sale"};
		
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productWrite";
		
	}
	
	
	@RequestMapping(value = "/admin_product_write")
	public String adminProductWrite(@RequestParam(value = "product_image") MultipartFile uploadFile, 
									ProductVO pvo, Model model, HttpSession session) {
		
		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");
		
		if (adminUser == null) {
			return "admin/main";
		} else {
			String fileName = "";
			
			// 파일 업로드 작업
			if (!uploadFile.isEmpty()) {	// 상품 이미지가 업로드됨
				String root_path = session.getServletContext().getRealPath("WEB-INF/resources/product_images/");
				System.out.println("ROOT 경로 : " + root_path);
				
				// 업로드된 파일명을 얻어온다.
				fileName = uploadFile.getOriginalFilename();
				
				try {
					File file = new File(root_path + fileName);
					uploadFile.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			pvo.setImage(fileName);		// image 컬럼에 이미지 파일명 저장 
			System.out.println(pvo);
			
			productService.insertProduct(pvo);
			
			return "redirect:/admin_product_list";
		}
	}
	
	@RequestMapping(value = "/admin_product_detail")
	public String adminProductDetail(ProductVO vo, Criteria criteria, Model model) {
		
		String[] kindList = {"", "Heels", "Boots", "Sandals", "Slipers", 
				"Sneekers", "Sale"};
		
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);
		
		int index = Integer.parseInt(product.getKind());
		model.addAttribute("kind", kindList[index]);
		model.addAttribute("criteria", criteria);
		
		return "admin/product/productDetail";
		
	}
	
	@RequestMapping(value = "/admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Model model) {
		
		String[] kindList = {"Heels", "Boots", "Sandals", "Slipers", 
				"Sneekers", "Sale"};
		
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);
		
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productUpdate";
		
	}
	
	@RequestMapping(value = "/admin_product_update")
	public String adminProductUpdate(@RequestParam(value = "product_image") MultipartFile uploadFile, 
									ProductVO vo, Model model, HttpSession session) {
		
		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");
		
		if (adminUser == null) {
			return "admin/main";
		} else {
			String fileName = "";
			
			// 파일 업로드 작업
			if (!uploadFile.isEmpty()) {	// 상품 이미지가 업로드됨
				String root_path = session.getServletContext().getRealPath("WEB-INF/resources/product_images/");
				System.out.println("ROOT 경로 : " + root_path);
				
				// 업로드된 파일명을 얻어온다.
				fileName = uploadFile.getOriginalFilename();
				
				try {
					File file = new File(root_path + fileName);
					uploadFile.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				vo.setImage(fileName);
				
			}
			
			if (vo.getBestyn() == null) {
				vo.setBestyn("n");
			}
			
			if (vo.getUseyn() == null) {
				vo.setUseyn("n");
			}
			
			System.out.println("업데이트 정보 : " + vo);
			productService.updateProduct(vo);
			
			return "redirect:/admin_product_detail?pseq=" + vo.getPseq();
			
		}
	}
	
	// 주문목록 표시 
	@RequestMapping(value = "/admin_order_list")
	public String adminOrderList(HttpSession session, Model model) {
		
		List<OrderVO> orderList = orderService.listOrder("");
		
		model.addAttribute("orderList", orderList);
			
		return "admin/order/orderList";
		
	}
	
	// 주문 완료 처리 (입금 확인) 
	@RequestMapping(value = "/admin_order_save")
	public String adminOrderSave(@RequestParam(value = "result") int[] odseq) {
		
		for (int i=0; i<odseq.length; i++) {
			orderService.updateOrderResult(odseq[i]);
		}
		
		return "redirect:/admin_order_list";
		
	}
	
	// 회원 목록 표시
	@RequestMapping(value = "/admin_member_list")
	public String adminMemberList(@RequestParam(value = "key", defaultValue="") String name, 
									Model model) {
		
		System.out.println("검색키 : " + name);
		List<MemberVO> listMember = memberService.listMember(name);
		
		model.addAttribute("memberList", listMember);
		
		return "admin/member/memberList";
		
	}
	
	// 게시판 목록 표시
	@RequestMapping(value = "/admin_qna_list")
	public String adminQnaList(Model model) {
		
		List<QnaVO> qnaList = qnaService.listAllQna();
		
		model.addAttribute("qnaList", qnaList);
		
		return "admin/qna/qnaList";
	}
	
	
	@RequestMapping(value = "/admin_qna_detail")
	public String adminQnaDetail(QnaVO vo, Model model) {
		
		QnaVO qnaVO = qnaService.getQna(vo.getQseq());
		
		model.addAttribute("qnaVO", qnaVO);
		
		return "admin/qna/qnaDetail";
	}
	
	
	@RequestMapping(value = "/admin_qna_repsave")
	public String adminQnaRepSave(QnaVO vo, Model model) {
		
		qnaService.updateQna(vo);
		
		return "redirect:admin_qna_list";
	}
	
	@RequestMapping(value = "admin_sales_record_form")
	public String adminProductSalesChart(Model model) {
		
		return "admin/order/salesRecords";
	}
	
	@RequestMapping(value = "sales_record_chart", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<SalesQuantity> sales_record_chart() {
		
		List<SalesQuantity> listSales = productService.getProductSales();
		
		System.out.println("판매실적 >>");
		System.out.println("   제품명      수량");
		System.out.println("---------------");
		
		for (SalesQuantity item : listSales) {
			System.out.printf("%10s%3d\n", item.getPname(), item.getQuantity());
		}
		
		System.out.println("================");
		
		return listSales;
	}
	
	
	
	
	
	
}

