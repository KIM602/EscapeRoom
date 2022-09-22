package com.EscapeRoom.admin.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.EscapeRoom.admin.dto.ProjectAdminFooterDto;
import com.EscapeRoom.admin.dto.ProjectAdminJoinDto;
import com.EscapeRoom.admin.dto.ProjectAdminMainDto;

public class ProjectAdminDao implements IProjectAdminDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String join(ProjectAdminJoinDto dto) {
		String result = null;
		
		try {
			int res = sqlSession.insert("join",dto);
			
			System.out.println(res);
			
			if(res > 0 ) {
				result = "success";
			}
			else {
				result = "failed";
			}
		}
		catch (Exception e) {
			e.getMessage();
			result = "failed";
		}
		return result;
	}

	//loginó��
	@Override
	public ProjectAdminJoinDto login(String pid) {
		System.out.println(pid);
		
		ProjectAdminJoinDto result = sqlSession.selectOne("login",pid);
		
		return result;
	}

	//���� ������ �̹��� DB ��� ó��
	@Override
	public String mainInsert(ProjectAdminMainDto dto) {
		System.out.println("mainInsert");
		String result;
		
		int res = sqlSession.insert("mainInsert",dto);
		
		if(res == 1) {
			result = "success";
		}
		else {
			result = "failed";
		}
		
		return result;
	}

	//main�� �̹��� �Ѹ���
	@Override
	public ArrayList<ProjectAdminMainDto> MainList() {
		System.out.println("MainList");
		
		ArrayList<ProjectAdminMainDto> dto = (ArrayList)sqlSession.selectList("MainList");
		
		return dto;
	}
	
	//main �̹��� ����
	@Override
	public String mainModify(ProjectAdminMainDto dto) {
		String result;
		
		int res = sqlSession.update("mainModify",dto);
		
		if(res == 1) {
			result = "success";
		}
		else {
			result = "failed";
		}
		
		return result;
	}

	//���̵� �ߺ�üũ
	@Override
	public String check(ProjectAdminJoinDto dto) {
		System.out.println(dto);
		
		String result = null;
		ProjectAdminJoinDto res1 = sqlSession.selectOne("check",dto);		
			if(res1 != null) {
				result = "failed";
			}
			else {
				result = "success";
			}
	
		return result;
	}

	//footer DB��� ó��
	@Override
	public String footerInsert(ProjectAdminFooterDto dto) {
		String result;
		int res = sqlSession.insert("footerInsert",dto);
		if(res == 1) {
			result = "success";
		}
		else {
			result = "failed";
		}
		return result;
	}
	
	//footer�� �̹��� �Ѹ���
	@Override
	public ArrayList<ProjectAdminFooterDto> FooterList() {
		System.out.println("FooterList");
		
		ArrayList<ProjectAdminFooterDto> dto = (ArrayList)sqlSession.selectList("FooterList");
		
		return dto;
	}

	//footer DB���� ó��
	@Override
	public String footerModify(ProjectAdminFooterDto dto) {
		String result;
		
		int res = sqlSession.update("footerModify",dto);
		
		if(res == 1) {
			result = "success";
		}
		else {
			result = "failed";
		}
		return result;
	}
	
}
