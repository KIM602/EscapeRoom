package com.EscapeRoom.admin.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface ProjectAdminCommand {
	
	//�߻�޼���
	public void execute(HttpServletRequest request, Model model) ;
	
}
