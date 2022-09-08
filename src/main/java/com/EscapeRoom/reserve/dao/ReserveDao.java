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

	// �����ڰ� ��¥ �ִ��� ���� üũ ��
	// ������ ���Ӱ� ����ȣ�� ������ ��������
	@Override
	public ReserveDto reserveFindCheck(ReserveDto dto) {
		ReserveDto Rdto = sqlSession.selectOne("reserveFindCheck",dto);

		if (Rdto == null ) {
			return null;
		}
		else {
			System.out.println("dad���� Rdto������?" + Rdto);
			return Rdto;
		}
		
		
	}
	
}
