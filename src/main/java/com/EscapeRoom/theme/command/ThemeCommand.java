package com.EscapeRoom.theme.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface ThemeCommand {
	//�߻�޼���
	public void execute(HttpServletRequest request, Model model);
}
