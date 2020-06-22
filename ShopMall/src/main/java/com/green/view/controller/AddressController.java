package com.green.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.biz.address.AddressService;
import com.green.biz.address.AddressVO;

@Controller
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@RequestMapping(value = "/find_zip_num", method = RequestMethod.POST)
	public String findZipNumAction(AddressVO avo, Model model) {
		System.out.println("µø¿Ã∏ß : " + avo.getDong());
		
		List<AddressVO> addrList = addressService.selectAddressByDong(avo);
		
		model.addAttribute("addressList", addrList);
		
		return "member/findZipNum";
		
	}
	
}
