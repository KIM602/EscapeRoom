package com.EscapeRoom.reserve.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface ReserveCommand {
	//�߻�޼���
	public void execute(HttpServletRequest request, Model model);
}
