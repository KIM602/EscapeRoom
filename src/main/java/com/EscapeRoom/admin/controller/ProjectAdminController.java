package com.EscapeRoom.admin.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.EscapeRoom.admin.command.ProjectAdminCommand;
import com.EscapeRoom.admin.command.ProjectAdminJoinCommand;
import com.EscapeRoom.admin.dao.ProjectAdminDao;
import com.EscapeRoom.admin.util.Constant;

@Controller
public class ProjectAdminController {
	
	private ProjectAdminCommand com;
	
	//��ȣȭ ó�� bean
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		Constant.passwordEncoder = passwordEncoder;
	}
	
	//ProjectAdminDao bean
	private ProjectAdminDao edao;
	@Autowired
	public void setEdao(ProjectAdminDao edao) {
		this.edao = edao;
		Constant.edao = edao;
	}
	
	@RequestMapping("/AdminJoinView")
	public String JoinView() {
		System.out.println("JoinView��û");
		return "admin/AdminJoinView";
	}
	
	//ȸ������ó��
	@RequestMapping("/join")
	public String join(HttpServletRequest request, Model model) {
		System.out.println("join ��û");
		com = new ProjectAdminJoinCommand();
		com.execute(request, model);
		
		String result = (String)request.getAttribute("result");
		
		if(result.equals("success")) {
			System.out.println("success");
			return "admin/AdminLoginView";
		}
		else {
			return "join-failed";
		}
	}
	
	//loginâ ȭ��
	@RequestMapping("/AdminLoginView")
	public String AdminLoginView() {
		System.out.println("AdminLoginView ��û");
		return "admin/AdminLoginView";
	}
	
	@RequestMapping("/admin")
	public String admin() {
		System.out.println("AdminLoginView ��û");
		return "admin/AdminLoginView";
	}
	
	//login����ó��
	@RequestMapping("/main")
	public String main(HttpServletRequest request, Model model, Authentication authentication) {
		System.out.println("login ���� �� �̵� ��û");
		
		getUsername(authentication,request);
		String username = (String)request.getAttribute("username");
		String auth = (String)request.getAttribute("auth");
		
		return "admin/DashBoardMain2";
		
	}
	
	
	@RequestMapping("/processLogin")
	public ModelAndView processLogin(
			@RequestParam(value="login", required = false) String login,
			@RequestParam(value="error", required = false) String error,
			@RequestParam(value="logout", required = false) String logout,
			HttpSession session, Model pmodel
			) {
		System.out.println("processLogin ��û");
		ModelAndView model = new ModelAndView();
		if(login != null && login != "") {
			model.addObject("login", "�α��� ��");
		}
		if(error != null && error != "") { //�α��� ���� �� processLogin?error=1
			model.addObject("error", "�Է��� ������ �ùٸ��� �ʽ��ϴ�.");
		}
		if(logout != null && logout != "") { //processLogin?logout=1
			model.addObject("logout", "���������� �α׾ƿ� �Ǿ����ϴ�.");
		}
		
		model.setViewName("admin/AdminLoginView");
		return model;
			
	}
	
	//logout
	@RequestMapping("/AdminLogoutView")
	public String AdminLogoutView()	{
		System.out.println("AdminLogoutView ��û");
		return "admin/AdminLogoutView";
	}
	
	//error
	@RequestMapping("/error")
	public String error() {
		System.out.println("error page ��û");
		return "admin/error";
	}
	
	//DashBoardMain
	@RequestMapping("/DashBoardMain")
	public String DashBoardMain() {
		System.out.println("DashBoardMain ��û");
		return "admin/DashBoardMain";
	}
	
	private void getUsername(Authentication authentication, HttpServletRequest request) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		System.out.println(userDetails.getUsername()); //�α����� ���̵�
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String auth = authorities.toString(); //role�� �� ���ڿ� ��ȯ
		System.out.println(auth); //[ROLE_USER]����
		request.setAttribute("username", username);
		request.setAttribute("auth", auth);
	}
	
}
