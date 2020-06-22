package com.green.biz.worker.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.worker.WorkerVO;

@Repository
public class WorkerDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public String workerCheck(String id) {
		System.out.println("mybatis로 workerCheck() 기능 처리");
		
		return mybatis.selectOne("WorkerDAO.workerCheck", id);
	}
	
	public WorkerVO getEmployee(String id) {
		System.out.println("mybatis로 getEmployee() 기능 처리");
		
		return mybatis.selectOne("WorkerDAO.getEmployee", id);
	}
	
	
}

