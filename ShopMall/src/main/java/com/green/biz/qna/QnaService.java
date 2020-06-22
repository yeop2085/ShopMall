package com.green.biz.qna;

import java.util.List;

public interface QnaService {
	
	public List<QnaVO> listQna(String id);
	
	public QnaVO getQna(int seq);
	
	public void insertQna(QnaVO qnaVO);
	
	public List<QnaVO> listAllQna();
	
	public void updateQna(QnaVO vo);
	
}

