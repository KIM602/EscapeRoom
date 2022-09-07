package com.EscapeRoom.reserve.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.EscapeRoom.reserve.command.InsertReserve;
import com.EscapeRoom.reserve.command.ReserveCommand;
import com.EscapeRoom.reserve.command.themeReserveTimeCheck;
import com.EscapeRoom.reserve.dao.ReserveDao;
import com.EscapeRoom.reserve.dto.ReserveDto;
import com.EscapeRoom.theme.command.ThemeCommand;
import com.EscapeRoom.theme.command.ThemeImageCommand;
import com.EscapeRoom.theme.command.ThemeNameListCommand;
import com.EscapeRoom.theme.dao.ThemeDao;
import com.EscapeRoom.util.Constant;


@Controller
public class ReserveController {

	//�׸� ���� ����Ʈ ��������뵵
	private ThemeCommand tcom;
	private ThemeDao tdao;
	
	// ���� �ٿ�
	private ReserveDao rdao;
	private ReserveCommand rcom;
	
	@Autowired
	public void setRdao(ReserveDao rdao) {
		this.rdao = rdao;
		Constant.rdao = rdao;
	}

	@Autowired
	public void setTdao(ThemeDao tdao) {
		this.tdao = tdao;
		Constant.tdao = tdao;
	}
	
	// �׸� ���Ӹ���Ʈ ����
	@RequestMapping("/themeNameList")
	public String themeNameList(HttpServletRequest request, Model model) {
		System.out.println("�׸������Դϴ�");
		
		System.out.println("ymd��" + request.getParameter("ymd"));
		model.addAttribute("ymd",request.getParameter("ymd"));
		tcom = new ThemeNameListCommand();
		tcom.execute(request, model);
		
		return "theme/themeNameList";
	}
	
	// ���࿡�� �׸��� �����Ͽ� ���� �޾� �̹��� ��� 
	@RequestMapping("/imageView")
	public String imageView(HttpServletRequest request, Model model) {
		System.out.println("themevalue����" + request.getParameter("themevalue"));
		System.out.println("�Ѿ��ymd����" + request.getParameter("ymd"));
		model.addAttribute("ymd",request.getParameter("ymd"));
		
		
		tcom = new ThemeImageCommand();
		tcom.execute(request, model);
		
		return "theme/themeImage";

	}
	
	// ���������� �̵�
	@RequestMapping("/reservePage")
	public String reservePage(HttpServletRequest request, Model model) {
		System.out.println("reservePage");

		return "reserve/reservePage";
	}
	
	// �����ϱ� ������
	@RequestMapping("/reserveForm")
	public String reserveForm(HttpServletRequest request,Model model) {
		System.out.println("reserveForm�̵�");
		model.addAttribute("ymd",request.getParameter("ymd"));
		model.addAttribute("themeTime",request.getParameter("themeTime"));
		// 
		tcom = new ThemeImageCommand();
		tcom.execute(request, model);
		
		return "reserve/reserveForm";
	}
	
	// �����ϴ� ����
	@RequestMapping("/reserve")
	public String reserve(HttpServletRequest request,Model model) {
		System.out.println("reserve");
		rcom = new InsertReserve();
		rcom.execute(request, model);
		
		return "main/Main";
		
	}

	// �׸� �ð� ����ִ��� Ȯ���ϸ鼭 �ҷ����� (ajax�뵵)
	@RequestMapping("/themeReserveTimeCheck" )
	public String themeReserveTimeCheck(HttpServletRequest request,Model model) {
		System.out.println("themeReserveTimeCheck");
		model.addAttribute("ymd",request.getParameter("ymd"));
		model.addAttribute("themevalue",request.getParameter("themevalue"));
		System.out.println("ymd�� : " + request.getParameter("ymd"));
		System.out.println("themevalue�� : " + request.getParameter("themevalue"));
		
		rcom = new themeReserveTimeCheck();
		rcom.execute(request, model);
		
		return "reserve/reserveTime";
	}
	
	
	// ����Ȯ��/���� ������ �̵�
		@RequestMapping("/reserveCheckCanclePage")
		public String reserveCheckCanclePage(HttpServletRequest request, Model model) {
			System.out.println("reserveCheckCanclePage");

			return "reserve/reserveCheckCanclePage";
		}


}
