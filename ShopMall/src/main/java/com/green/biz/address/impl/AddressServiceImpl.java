package com.green.biz.address.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.address.AddressService;
import com.green.biz.address.AddressVO;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressDAO addressDAO;
	
	@Override
	public List<AddressVO> selectAddressByDong(AddressVO avo) {
		return addressDAO.selectAddressByDong(avo);
	}
	
}
