package com.EscapeRoom.theme.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.EscapeRoom.theme.dto.ThemeDto;

public class ThemeDao implements TdaoInterface {
	@Autowired //���� ���Թ޴� ������̼�
	private SqlSession sqlSession;
	
	/* �׸� ��� */
	@Override
	public String insertform(ThemeDto dto) {
		String result;
		int res = sqlSession.insert("insertform", dto);
		if(res == 1) {
			result = "success";
		}
		else {
			result = "failed";
		}
		return result;
	}

	/* �׸� ������ */
	@Override
	public ArrayList<ThemeDto> themeList() {
		ArrayList<ThemeDto> dtos = (ArrayList)sqlSession.selectList("themeList");
		return dtos;
	}

	/* �׸� �󼼺��� */
	@Override
	public ThemeDto themeDetailsPage(String tid) {
		System.out.println("themeDetailsPage");
		ThemeDto dto = sqlSession.selectOne("themeDetailsPage", tid);
		return dto;
	}
	
	/* ���� ó�� */
	@Override
	public String edit(ThemeDto dto) {
		String result;
		int res = sqlSession.update("edit", dto);
		if(res == 1) {
			result = "success";
		}
		else {
			result = "failed";
		}
		return result;
	}
	
	/* �׸� ���� ������ */
	@Override
	public ThemeDto themeEdit(String tid) {
		ThemeDto dto = sqlSession.selectOne("themeEdit", tid);
		return dto;
	}
	
	/* �׸� ���� ó�� */
	@Override
	public void delete(String tid) {
		int res = sqlSession.delete("delete", tid);
		System.out.println(res);
	}
	
	/* reserve */
	// �׸����� ����Ʈ reserve�뵵
	@Override
	public ArrayList<ThemeDto> themeNameList() {
		System.out.println("�׸����Ӹ���Ʈ�Դϴ�");
		ArrayList<ThemeDto> dtos = (ArrayList)sqlSession.selectList("themeNameList");
		return dtos;
	}
	// �׸� �̹����뵵 reserve�뵵
	@Override
	public ThemeDto themeImage(String tId) {
		System.out.println("�׸�tId����?"+tId);
		ThemeDto dto = sqlSession.selectOne("themeImage",tId);
		return dto;
	}
	
	@Override
	//���� top3 ������ �̹��� �ҷ�����
	public ThemeDto top3Image(String tid) {
		System.out.println("�׸�id��" + tid);
		ThemeDto dto = sqlSession.selectOne("top3Image",tid);
		return dto;
	}
}
