package com.br.mybatis.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.br.mybatis.board.model.vo.Board;
import com.br.mybatis.board.model.vo.Reply;
import com.br.mybatis.common.model.vo.PageInfo;

public class BoardDao {
	
	public int selectListCount(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.selectListCount");
	}
	
	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi){
		
		//sqlSession.selectList("boardMapper.selectList");
		
		// 마이바티스에서는 페이징 처리를 위해 RowBounds 라는 객체 제공
		// 현재 조회되는 리스트 중에서
		// "몇 개의 게시글을 건너뛰고" "몇개를" 조회할껀지 지정해둘수 있는 객체
		
		/*
		 * * offset : 몇 개의 게시글을 건너뛸건지
		 * 
		 * ex) boardLimit : 5
		 * 								offset(건너뛸숫자)	 limit(조회할숫자)
		 * currentPage : 1		1~5			0				5
		 * currentPage : 2		6~10		5				5
		 * currentPage : 3		11~15		10				5
		 * ... 
		 */
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		// selectList : 조회결과가 없을 경우 텅빈 리스트로 반환
		return (ArrayList)sqlSession.selectList("boardMapper.selectList", null, rowBounds);
		
	}

	public int insertBoard(SqlSession sqlSession, Board b) {
		return sqlSession.insert("boardMapper.insertBoard", b);
	}
	
	
	public int increaseCount(SqlSession sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.increaseCount", boardNo);
	}
	
	
	public Board selectBoard(SqlSession sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
	}
	
	public ArrayList<Reply> selectReplyList(SqlSession sqlSession, int boardNo){
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList", boardNo);
		
	}
	
	public int selectSearchCount(SqlSession sqlSession, String condition, String keyword) {
		
		// sql 전달시에는 1개밖에 전달못함! 여러개일 경우는 한 객체에 담아서 전달해야함
		HashMap<String,String> map = new HashMap<>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		return sqlSession.selectOne("boardMapper.selectSearchCount", map);
	}
	
	public ArrayList<Board> selectSearchList(SqlSession sqlSession, String condition, String keyword, PageInfo pi){
		HashMap<String, String> map = new HashMap<>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		//map.put("keyword", keyword);
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectSearchList", map, rowBounds);
		
		
	}
	
	
	
	
	
	
	
}
