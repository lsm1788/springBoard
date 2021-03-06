package com.kb.domain;

import lombok.Data;

@Data
public class BoardCriteria {

	private int pageNum;//현재페이지번호
	private int amount; //한페이지당 보여줄 게시글 수

	private String type;
	private String keyword;
	
	
	public BoardCriteria() {
		this(1, 5);
	}

	public BoardCriteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	/*
	 *  사용법 : Mybatis xml에서 #{pageStart}이렇게 사용됩니다.
	 */
	
	public int getPageStart(){
		return (getPageNum() - 1) * getAmount(); 
	}
	
}
