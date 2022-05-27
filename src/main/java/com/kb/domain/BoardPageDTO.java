package com.kb.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardPageDTO {

	private int startPage;	//시작페이지
	private int endPage;	//끝 페이지
	
	private boolean prev, next;	//이전 다음
	
	private int total;	//전체 레코드 수
	private BoardCriteria cri;	//현재 페이지, 한 페이지 당 보여줄 갯수
	private final int PAGE_NUM = 5;
	
	public BoardPageDTO(int total, BoardCriteria cri) {
		this.total = total;
		this.cri = cri;
		
		this.endPage = (int)(Math.ceil(cri.getPageNum()/(PAGE_NUM*1.0)))*PAGE_NUM;
		
		this.startPage = this.endPage - (PAGE_NUM-1);
		
		int realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
	
	
}
