package com.EscapeRoom.theme.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.EscapeRoom.theme.dao.ThemeDao;
import com.EscapeRoom.theme.dto.ThemeDto;
import com.EscapeRoom.util.Constant;

public class ThemeNameCommand implements ThemeCommand {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		ThemeDao tdao = Constant.tdao;
		String tId = (String)request.getAttribute("tid");
		System.out.println("tId��" + tId);
		ThemeDto tdto = tdao.themeImage(tId);
		
		System.out.println("execute tdto��"+tdto);
		model.addAttribute("theme",tdto);

	}

}
