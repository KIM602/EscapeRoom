package com.EscapeRoom.theme.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.EscapeRoom.theme.dao.ThemeDao;
import com.EscapeRoom.theme.dto.ThemeDto;
import com.EscapeRoom.util.Constant;

public class ThemeNameListCommand implements ThemeCommand {

	
	
	// reserve�뵵
	@Override
	public void execute(HttpServletRequest request, Model model) {
		ThemeDao tdao = Constant.tdao;
		ArrayList<ThemeDto> dtos = tdao.themeNameList(); // �迭�� �׸��̸� ����Ʈ ���
		
		model.addAttribute("themeNameList",dtos); // �� ����

	}

}
