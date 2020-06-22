package com.green.biz.qna.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.qna.QnaService;
import com.green.biz.qna.QnaVO;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	private QnaDAO qnaDAO;
	
	@Override
	public List<QnaVO> listQna(String id) {
		return qnaDAO.listQna(id);
	}

	@Override
	public QnaVO getQna(int seq) {
		return qnaDAO.getQna(seq);
	}
	
	@Override
	public void insertQna(QnaVO qnaVO) {
		qnaDAO.insertQna(qnaVO);
	}
	
	@Override
	public List<QnaVO> listAllQna() {
		return qnaDAO.listAllQna();
	}
	
	@Override
	public void updateQna(QnaVO vo) {
		qnaDAO.updateQna(vo);
	}
	
	
}


