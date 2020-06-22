package com.green.biz.worker;

public interface WorkerService {
	
	public int workerCheck(String id, String pwd);
	
	public WorkerVO getEmployee(String id);
	
}

