package com.br.mybatis.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.br.mybatis.board.model.vo.Board;
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
	
	
	
	
	
	
	
	
	
	
	
	
}
