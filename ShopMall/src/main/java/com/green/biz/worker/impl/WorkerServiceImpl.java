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
		
		if (pwd_id_db != null) {			// ����� ���̵� �������� ��ȸ�ؼ� ���� pwd ��
			if (pwd.equals(pwd_id_db)) {	// ȭ�鿡�� �Է��� ��ȣ�ϰ� ���̺��� ��ȣ�ϰ� ��ġ
				result = 1;		// ������ �α���
			} else {
				result = 0;		// ��ȣ ����ġ
			}
		} else {
			result = -1;		// ���̵� �������� ����
		}
		
		return result;
	}
	
	@Override
	public WorkerVO getEmployee(String id) {
		return workerDAO.getEmployee(id);
	}
	
}

