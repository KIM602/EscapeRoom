package com.EscapeRoom.theme.dao;

import java.util.ArrayList;

import com.EscapeRoom.theme.dto.ThemeDto;

public interface TdaoInterface {
	/* User page */
	//�׸� �Ұ�
	public ArrayList<ThemeDto> themeList();
	
	/* Admin page */
	//�׸����
	public String insertform(ThemeDto dto);
	//��ϵ� �׸� �󼼺��� ������
	public ThemeDto themeDetailsPage(String tid);
	//�׸� ���� ������
	public ThemeDto themeEdit(String tid);
	//�׸� ���� ó��
	public String edit(ThemeDto dto);
	//�׸� ����
	public void delete(String tid);
	
	/* Reserve */
	public ArrayList<ThemeDto> themeNameList();
	public ThemeDto themeImage(String tId);
	// top3
	public ThemeDto top3Image(String tid);
}
