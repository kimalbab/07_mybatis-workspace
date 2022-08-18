package com.br.mybatis.board.model.service;

import java.util.ArrayList;

import com.br.mybatis.board.model.vo.Board;
import com.br.mybatis.board.model.vo.Reply;
import com.br.mybatis.common.model.vo.PageInfo;

public interface BoardService {
	
	// 1. 게시글 리스트조회
	int selectListCount();
	ArrayList<Board> selectList(PageInfo pi);
	
	// 2. 게시글 작성
	int insertBoard(Board b);
	
	// 3. 게시글 상세조회
	int increaseCount(int boardNo);
	Board selectBoard(int boardNo);
	ArrayList<Reply> selectReplyList(int boardNo);
	
	// 4. 게시글 검색리스트 조회
	int selectSearchCount(String condition, String keyword);
	ArrayList<Board> selectSearchList(String condition, String keyword, PageInfo pi);
	
	

}




