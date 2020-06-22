package com.green.biz.product;

import java.util.Date;

import lombok.Data;

@Data
public class ProductCommentVO {
	
	private int comment_seq;
	private int pseq;
	private String content;
	private String writer;
	private Date regdate;
	private Date modifydate;
	
	/*
	public int getComment_seq() {
		return comment_seq;
	}
	
	public void setComment_seq(int comment_seq) {
		this.comment_seq = comment_seq;
	}
	
	public int getPseq() {
		return pseq;
	}
	
	public void setPseq(int pseq) {
		this.pseq = pseq;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public Date getRegdate() {
		return regdate;
	}
	
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public Date getModifydate() {
		return modifydate;
	}
	
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	
	@Override
	public String toString() {
		return "ProductComment [comment_seq=" + comment_seq + ", pseq=" + pseq + ", content=" + content + ", writer="
				+ writer + ", regdate=" + regdate + ", modifydate=" + modifydate + "]";
	}
	*/
	
}

