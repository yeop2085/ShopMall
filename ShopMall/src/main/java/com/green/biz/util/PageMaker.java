package com.green.biz.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

// ȭ�鿡 ������ ������ ǥ���ϱ� ���� Ŭ����
public class PageMaker {
	
	private Criteria cri;		// Page��ȣ, �������� �׸� �� 
	
	private int totalCount;		// ��ü �Խñ��� ��
	private int startPage;		// ���� ������ ��ȣ
	private int endPage;		// �� ������ ��ȣ
	private boolean prev;		// ���� ������ ���� ����
	private boolean next;		// ���� ������ ���� ����
	private int displayPageNum = 10;	// ȭ�� �ϴܿ� ���̴� �������� ����
	private int realEndPage;	// ��ü �׸���� ���� ���� �� ������ ����
	
	// ���ʷ� ������ ���� �����ϴ� �޼ҵ�
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		fieldInit();
	}
	
	public void fieldInit() {
		// �� ������ ���
		endPage = (int)(Math.ceil(cri.getPageNum() / (double) displayPageNum) 
				* displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		realEndPage = (int)(Math.ceil(totalCount / (double) cri.getNumPerPage()));
		
		// ������ ����� ���� ��������(realEndPage)�� ���� endPage�� ����
		if (endPage > realEndPage) {
			endPage = realEndPage;
		}
		
		prev = startPage == 1 ? false : true;	// 1������ �� ��� '����' ��ư�� ǥ�� �ȵ�
		next = endPage * cri.getNumPerPage() >= totalCount ? false : true;
		
	}
	
	// �Է� �Ű����� : 
	// page - ǥ���� ������ ��ȣ
	// ��� :
	// ?pageNum=3&numPerPage=10	
	public String makeQuery(int page) {
		UriComponents uriComp = UriComponentsBuilder.newInstance()
				.queryParam("pageNum", page)
				.queryParam("numPerPage", cri.getNumPerPage())
				.build();
		
		return uriComp.toUriString();
	}
	
	
	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getRealEndPage() {
		return realEndPage;
	}

	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	@Override
	public String toString() {
		return "PageMaker [cri=" + cri + ", totalCount=" + totalCount + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum
				+ ", realEndPage=" + realEndPage + "]";
	}
	
	
}

