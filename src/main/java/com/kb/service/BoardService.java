package com.kb.service;

import java.util.List;

import com.kb.domain.BoardVO;
import com.kb.domain.Criteria;

public interface BoardService {

	public void register(BoardVO board); 	//insert
	
	public BoardVO get(int bno);			//read
	
	public boolean modify(BoardVO board);	//update
	
	public boolean remove(int bno);			//delete
	
	public List<BoardVO> getList();			//List
	
	public List<BoardVO> getListWithPaging(Criteria cri);

	public int getListWithCnt();
	
}
