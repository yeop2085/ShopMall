package com.green.biz.worker.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.worker.WorkerService;
import com.green.biz.worker.WorkerVO;

@Service("workerService")
public class WorkerServiceImpl implements WorkerService {
	
	@Autowired
	private WorkerDAO workerDAO;

	@Override
	public int workerCheck(String id, String pwd) {
		
		int result = -1;
		
		String pwd_id_db = workerDAO.workerCheck(id);
		
		if (pwd_id_db != null) {			// 사용자 아이디를 조건으로 조회해서 나온 pwd 비교
			if (pwd.equals(pwd_id_db)) {	// 화면에서 입력한 암호하고 테이블이 암호하고 일치
				result = 1;		// 정상적 로그인
			} else {
				result = 0;		// 암호 불일치
			}
		} else {
			result = -1;		// 아이디가 존재하지 않음
		}
		
		return result;
	}
	
	@Override
	public WorkerVO getEmployee(String id) {
		return workerDAO.getEmployee(id);
	}
	
}

