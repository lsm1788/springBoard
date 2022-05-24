package com.kb.mapper;

import java.util.List;

import com.kb.domain.BoardVO;
import com.kb.domain.Criteria;

public interface BoardMapper {
	
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board);
	
	public BoardVO read(int bno);
	
	public int update(BoardVO board);
	
	public int delete(int bno);

	public int getListWithCnt();

}
