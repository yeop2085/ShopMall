package com.green.biz.util;

public class Criteria {
	
	private int pageNum;		// ���� ������ ��ȣ
	private int numPerPage;		// �������� ��� �׸� ��
	
	public Criteria() {
		this(1, 10);	// �⺻�� : 1�������� �׸�� 10��
	}
	
	public Criteria(int pageNum, int numPerPage) {
		this.pageNum = pageNum;
		this.numPerPage = numPerPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		
		if (pageNum <= 0) {
			this.pageNum = 1;
		} else {
			this.pageNum = pageNum;
		}
		
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		
		if (numPerPage <= 0 || numPerPage > 50) {
			this.numPerPage = 10;
		} else {
			this.numPerPage = numPerPage;
		}
		
	}
	
	// �� ���������� �����ϴ� �׸� ��ȣ�� ��ȯ�Ѵ�. 
	public int getPageStart() {
		return (this.pageNum -1) * this.numPerPage + 1;
	}
		
	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", numPerPage=" + numPerPage + "]";
	}
	
	
}

