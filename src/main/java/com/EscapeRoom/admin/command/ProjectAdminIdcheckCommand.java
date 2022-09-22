package com.EscapeRoom.admin.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.EscapeRoom.admin.dao.ProjectAdminDao;
import com.EscapeRoom.admin.dto.ProjectAdminJoinDto;
import com.EscapeRoom.admin.util.Constant;



public class ProjectAdminIdcheckCommand implements ProjectAdminCommand {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		String pid = request.getParameter("pid");
		
		System.out.println("pid��"+pid);
		
		ProjectAdminJoinDto dto = new ProjectAdminJoinDto(pid);
		
		ProjectAdminDao edao = Constant.edao;
		
		String result = edao.check(dto);
		System.out.println("execute���� result��"+result);
		request.setAttribute("result", result);
	}

}
