package com.EscapeRoom.reserve.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.EscapeRoom.reserve.dto.ReserveDto;
import com.EscapeRoom.reserve.dto.ReserveDto2;
import com.EscapeRoom.reserve.dto.ReserveDto3;
import com.EscapeRoom.reserve.dto.TodayReserveTidDto;
import com.EscapeRoom.reserve.dto.startNoRdate;


public class ReserveDao implements RdaoInterface {
	
	@Autowired //���� ���Թ޴� ������̼�
	private SqlSession sqlSession;
	//�����ϱ�
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
	public ArrayList<ReserveDto> reserveFindCheck(ReserveDto dto) {
			ArrayList<ReserveDto> Rdto = (ArrayList)sqlSession.selectList("reserveFindCheck",dto); // Select�޼��带 ����Ͽ� ������ 2���� ������ ���� ���� ������
			System.out.println("Rdto���� ? " + Rdto);
			return Rdto;
		
	}
	
	//������ id������ ������ ��������
	@Override
	public ArrayList<ReserveDto> ReserveFindMoreThan2DetailPage(ReserveDto rdto) {
		ArrayList<ReserveDto> result  = (ArrayList)sqlSession.selectList("ReserveFindMoreThan2DetailPage",rdto);
		return result;
	}
	
	//�������
	@Override
	public void deleteReserve(String rId) {
		sqlSession.delete("deleteReserve",rId);
		
	}

	
	@Override
	public ArrayList<ReserveDto> ReserverList() {
		ArrayList<ReserveDto> result = (ArrayList)sqlSession.selectList("reserveList");
		return result;
	}
	@Override
	public ArrayList<ReserveDto> CalendarChoiceReserverList(ReserveDto rdto) {
		ArrayList<ReserveDto> result = (ArrayList)sqlSession.selectList("calendarChoiceReserverList",rdto);
		return result;
	}
	@Override
	public ArrayList<ReserveDto> ReservePageList(String pageNo) {
		int page = Integer.parseInt(pageNo);
		int startNo = (page - 1) * 10 + 1; // ���۹�ȣ
		ArrayList<ReserveDto> result = (ArrayList)sqlSession.selectList("reservePageList",startNo);
		return result;
		
	}
	@Override
	public ArrayList<ReserveDto> CalendarChoiceReserverPageList(String pageNo, String rDate) {
		int page = Integer.parseInt(pageNo);
		int startNo = (page - 1) * 10 + 1;
		
		startNoRdate snr = new startNoRdate(startNo, rDate);
		
		ArrayList<ReserveDto> result = (ArrayList)sqlSession.selectList("calendarChoiceReserverPageList",snr);
		return result;
	}
	
	//top3
	@Override
	public ArrayList<ReserveDto3> Top3(ReserveDto3 dto) {
		ArrayList<ReserveDto3> result = (ArrayList)sqlSession.selectList("Top3");
		return result;
	}
	@Override
	public int ReserveListTotal() {
		int result = sqlSession.selectOne("ReserveListTotal");
		return result;
	}
	@Override
	public int CalendarChoiceReserverListTotal(String ymd) {
		int result = sqlSession.selectOne("CalendarChoiceReserverListTotal",ymd);
		return result;
	}

	
	// �׸�id�� ��� ������ �Ǿ��ִ��� Ȯ�ο�
	@Override
	public int todayReservethemeCheck(TodayReserveTidDto dto1) {
		
		int result = sqlSession.selectOne("todayReservethemeCheck",dto1);
		return result;
	}
	
	@Override
	public ArrayList<ReserveDto2> ThemeBest(ReserveDto2 rdto) {
		ArrayList<ReserveDto2> result = (ArrayList)sqlSession.selectList("themeBest",rdto);
		return result;
	}
	
}
