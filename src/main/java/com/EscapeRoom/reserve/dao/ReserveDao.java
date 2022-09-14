package com.EscapeRoom.reserve.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.EscapeRoom.reserve.dto.ReserveDto;
import com.EscapeRoom.reserve.dto.ReserveDto2;

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
			ReserveDto Rdto = sqlSession.selectOne("reserveFindCheck",dto); // SelectOne�޼��带 ����Ͽ� ������ 2���� ������ ���� ���� ������
			System.out.println("Rdto���� ? " + Rdto);
			return Rdto;
		
	}
	//�������
	@Override
	public void deleteReserve(String rId) {
		sqlSession.delete("deleteReserve",rId);
		
	}

	@Override
	public ArrayList<ReserveDto2> reserveTop31(ReserveDto2 dto2) {
		ArrayList<ReserveDto2> result = (ArrayList)sqlSession.selectList("reserveTop31");
		return result;
	}
	@Override
	public ArrayList<ReserveDto> ReserverList() {
		ArrayList<ReserveDto> result = (ArrayList)sqlSession.selectList("ReserveList");
		return result;
	}
	@Override
	public ArrayList<ReserveDto> CalendarChoiceReserverList(ReserveDto rdto) {
		ArrayList<ReserveDto> result = (ArrayList)sqlSession.selectList("CalendarChoiceReserverList",rdto);
		return result;
	}


	
}
