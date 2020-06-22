package com.green.biz.address.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.address.AddressVO;

@Repository
public class AddressDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<AddressVO> selectAddressByDong(AddressVO avo) {
		System.out.println("Mybatis�� selectAddressByDong() ��� ó��");
		return mybatis.selectList("AddressDAO.selectAddressByDong", avo.getDong());
	}
	
}

