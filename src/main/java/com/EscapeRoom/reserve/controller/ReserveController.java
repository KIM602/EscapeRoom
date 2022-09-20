package com.EscapeRoom.reserve.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.EscapeRoom.admin.command.ProjectAdminCommand;
import com.EscapeRoom.admin.command.ProjectAdminFooterCommand;
import com.EscapeRoom.admin.dao.ProjectAdminDao;
import com.EscapeRoom.reserve.command.CalendarChoiceReserverList;
import com.EscapeRoom.reserve.command.CalendarChoiceReserverListTotal;
import com.EscapeRoom.reserve.command.CalendarChoiceReserverPageList;
import com.EscapeRoom.reserve.command.InsertReserve;
import com.EscapeRoom.reserve.command.ReserveCommand;
import com.EscapeRoom.reserve.command.ReserveDelete;
import com.EscapeRoom.reserve.command.ReserveFind;
import com.EscapeRoom.reserve.command.ReserveListTotal;
import com.EscapeRoom.reserve.command.ReservePageList;
import com.EscapeRoom.reserve.command.ReserverList;
import com.EscapeRoom.reserve.command.Test123132;
import com.EscapeRoom.reserve.command.themeReserveTimeCheck;
import com.EscapeRoom.reserve.dao.ReserveDao;

import com.EscapeRoom.theme.command.ThemeCommand;
import com.EscapeRoom.theme.command.ThemeImageCommand;
import com.EscapeRoom.theme.command.ThemeNameListCommand;
import com.EscapeRoom.theme.dao.ThemeDao;
import com.EscapeRoom.util.Constant;


@Controller
public class ReserveController {
	
	//footer ��������
	private ProjectAdminCommand com;
	
	//footer
	//ProjectAdminDao bean
	private ProjectAdminDao edao;
	@Autowired
	public void setEdao(ProjectAdminDao edao) {
		this.edao = edao;
		Constant.edao = edao;
	}
	
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

		//footer ������� �߰�
		com = new ProjectAdminFooterCommand();
		com.execute(request, model);
		
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
	
	@RequestMapping("/reserveOK")
	public String reserveOK(HttpServletRequest request,Model model) {
		System.out.println("reserveOK");
		return "reserve/reserveMessagePage";
	}
	
	// �����ϴ� ����
	@RequestMapping("/reserve")
	public String reserve(HttpServletRequest request,Model model) {
		System.out.println("reserve");
		rcom = new InsertReserve();
		rcom.execute(request, model);
		
		return "reserve/reservePage";
		
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

		// �����ڰ� �ִ��� üũ ������ ������ ���� 
		@RequestMapping(value="/reserveFind")
		public String reserveFind(HttpServletRequest request,Model model) {
			System.out.println("reserveFind");
			System.out.println("nameKey����?"+request.getParameter("nameKey"));  
			System.out.println("phoneKey����?"+request.getParameter("phoneKey"));
			// ��Ʈ�� Ÿ������ ���������� �������̽����� Ŭ�������� ������
			ReserveFind rF = new ReserveFind(); // ��ü����
			String result =(String)rF.StrExecute(request, model);  // ���� �κ� 
	
		
			System.out.println("result��?" + result);
			if(result == "success") {
				
				return "reserve/reserverInformation";   
			}
			
			else if (result == "success2"){
				return "reserve/reserveInformationList";
			}
			else {	// ���������� ���� �������� ���� �� ���� ����ġ �������� �̵�
				return "reserve/reserveCheckFailPage";
			}
		}
		
		@RequestMapping("/reserveFindMoreThan2DetailPage")
		public String reserveFindMoreThan2DetailPage(HttpServletRequest request,Model model) {
			System.out.println("reserveFindMoreThan2DetailPage");
			rcom = new ReserveFind();
			rcom.execute(request, model);
			return "reserve/reserverInformation";
		}
		
		// �����ڰ� ���� ����
		@RequestMapping("/reserveDelete")
		public String reserveDelete(HttpServletRequest request,Model model) {
			System.out.println("reserveDelete");
			System.out.println("���� �׸� id�� ? " + request.getParameter("reserveid"));
			rcom = new ReserveDelete();
			rcom.execute(request, model);
			return "reserve/reserveDeleteMessagePage";
		}
		
		
		// �׽�Ʈ
		@RequestMapping("/test")
		public String test(HttpServletRequest request,Model model) {
			System.out.println("test");
			tcom = new Test123132();
			tcom.execute(request, model);
			
			return "reserve/test";
		}
		
		
		// ������ ������ ������ ����Ʈ �̱�
		@RequestMapping("/ReserverList")
		public String ReserverList(HttpServletRequest request,Model model) {
			System.out.println("ReserverList");
			rcom = new ReserverList();
			rcom.execute(request, model);
			rcom = new ReserveListTotal();
			rcom.execute(request, model);
			
			return "admin/reserver/ReserverCheck";
		}
		// ������ ���������� �׸� ����
		@RequestMapping("AdminReserveDelete")
		public String AdminReserveDelete(HttpServletRequest request,Model model) {
			System.out.println("adminReserveDelete");
			rcom = new ReserveDelete();
			rcom.execute(request, model);
			
			return "redirect:/main";
		}
		
		// ������ ������ ������ ����Ʈ 10�� ������ �߷��� ����
		@RequestMapping("ReservePageList")
		public String ReservePageList(HttpServletRequest request,Model model) {
			System.out.println("ReservePageList");
			System.out.println(request.getParameter("pageNo"));
			
			rcom = new ReservePageList();
			rcom.execute(request, model);
			
			return "admin/reserver/ReservePageList";
		}
		
		// ������ ������ �޷����� ��¥ �����Ͽ� ������ ã��
		@RequestMapping("/CalendarChoiceReserverList")
		public String CalendarReserverList(HttpServletRequest request, Model model) {
			
			System.out.println("ymd��" + request.getParameter("ymd"));
			
			rcom = new CalendarChoiceReserverList();
			rcom.execute(request, model);
			
			rcom = new CalendarChoiceReserverListTotal();
			rcom.execute(request, model);
	
			return "admin/reserver/calendarChoiceReserveList";
		}
		
		// ������ ������ �޷����� ��¥ �����Ͽ� ������ ����Ʈ 10�� ������ �߷��� ����
		@RequestMapping("/calendarChoiceReserverPageList")
		public String CalendarChoiceReserverPageList(HttpServletRequest request, Model model) {
			System.out.println("calendarChoiceReserverPageList");
			System.out.println(request.getParameter("pageNo"));
			System.out.println("������Ʈ���� rDate���� ? " + request.getParameter("rDate"));
			rcom = new CalendarChoiceReserverPageList();
			rcom.execute(request, model);
			
			return "admin/reserver/calendarChoiceReserverPageList";
		}
		
		
		
}
