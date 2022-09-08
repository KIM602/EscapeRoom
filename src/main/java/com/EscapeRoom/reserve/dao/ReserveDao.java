package com.EscapeRoom.reserve.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.EscapeRoom.reserve.dto.ReserveDto;

public class ReserveDao implements RdaoInterface {
	
	@Autowired //���� ���Թ޴� ������̼�
	private SqlSession sqlSession;

	@Override
	public void insertReserve(ReserveDto dto) {
		sqlSession.insert("insertReserve",dto);
		
	}

	// �׸� �ð� üũ
	@Override
	public ReserveDto themeReserveTimeCheck(ReserveDto dto) {
		ReserveDto Rdto = sqlSession.selectOne("themeReserveTimeCheck",dto);
		return Rdto;
		
	}

	// �����ڰ� ��¥ �ִ��� ���� üũ
	@Override
	public String reserveFindCheck(ReserveDto dto) {
		String result = null;
		
		try {
			int res = sqlSession.selectOne("reserveFindCheck",dto);
			System.out.println("res"+res);
			if (res>0) {
				result="success";
			}
			else {
				result="failed";
			}
		}
		catch (Exception e) {
			e.getMessage();
			result="failed";
		}
		
		
			
		
		
		
		return result;
		
	}
	
}
