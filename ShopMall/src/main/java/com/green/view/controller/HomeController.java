package com.green.view.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.biz.product.ProductService;
import com.green.biz.product.dto.ProductVO;

/**
 * Handles requests for the application home page.
 */


@Controller	
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model) {
		
		// ����Ʈ ��ǰ ��ȸ ���� ȣ��
		List<ProductVO> bestProductList = productService.getBestProductList();
		model.addAttribute("bestProductList", bestProductList);
		
		// �Ż�ǰ ��ȸ ���� ȣ��
		List<ProductVO> newProductList = productService.getNewProductList(); 
		model.addAttribute("newProductList", newProductList);
		
		// model ����ҿ� ����Ʈ ��ǰ, �Ż�ǰ ����
		
		
		return "index";
	}
	
	
}

