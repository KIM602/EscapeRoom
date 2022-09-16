package com.EscapeRoom.theme.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.EscapeRoom.reserve.command.ReserveCommand;
import com.EscapeRoom.reserve.dao.ReserveDao;
import com.EscapeRoom.theme.command.ThemeCommand;
import com.EscapeRoom.theme.command.ThemeContentCommand;
import com.EscapeRoom.theme.command.ThemeDeleteCommand;
import com.EscapeRoom.theme.command.ThemeDetailsPageCommand;
import com.EscapeRoom.theme.command.ThemeEditCommand;
import com.EscapeRoom.theme.command.ThemeInsertCommand;
import com.EscapeRoom.theme.command.ThemeListCommand;
import com.EscapeRoom.theme.command.ThemeListPageCommand;
import com.EscapeRoom.theme.dao.ThemeDao;
import com.EscapeRoom.theme.dto.ThemeDto;
import com.EscapeRoom.util.Constant;

@Controller
public class ThemeController {
	
	private ThemeCommand com;
	
	//ThemeDao bean ���
	private ThemeDao tdao;
	
	@Autowired
	public void setTdao(ThemeDao tdao) {
		this.tdao = tdao;
		Constant.tdao = tdao;
	}
	
	// ���� �ٿ�
	private ReserveDao rdao;
	private ReserveCommand rcom;
			
	@Autowired
	public void setRdao(ReserveDao rdao) {
		this.rdao = rdao;
		Constant.rdao = rdao;
	}
	
	//���� ���� �ּҿ� �°� ����
	private String path ="C:/Users/kyk92/git/EscapeRoom/src/main/webapp/resources/upimage/";
	private String path1 = "C:/KYK/Util/apache-tomcat-9.0.64-windows-x64/apache-tomcat-9.0.64/wtpwebapps/EscapeRoom_security/resources/upimage/";
	
	/* User page */
	//�׸� ������(����Ʈ �ҷ���)
	@RequestMapping("/themePage")
	public String themePage(HttpServletRequest request, Model model) {
		System.out.println("�׸���������û");
		com = new ThemeListCommand();
		com.execute(request, model);
		return "theme/themePage";
	}
	
	/* Admin page */
	//�׸� ���â
	@RequestMapping("/themeInsert")
	public String themeInsert() {
		System.out.println("theme�̵�");
		return "theme/themeInsert";
	}
	
	//�׸����ó��
	@RequestMapping("/inserttheme")
	public String inserttheme(MultipartHttpServletRequest mphr, Model model) {
		System.out.println("inserttheme");
		String tid = ""; //rId�� ���Ƿ� ����(DB���� seq�ѹ��� �����ϴϱ� 
		String tname = mphr.getParameter("tname");
		String tgenre = mphr.getParameter("tgenre");
		String tdifficulty = mphr.getParameter("tdifficulty");
		String ttime = mphr.getParameter("ttime");
		String tprofile = mphr.getParameter("tprofile");
		String tphoto = null; //DB�� ������ �����̸�
		
		//��ȯ�Ǵ� ���� �����ʹ� MultipartFile���̰� getFile(���ϼӼ���)�� ���Ѵ�
		MultipartFile mf = mphr.getFile("tphoto");
		
		//���ε� �Ǵ� ���� ������ġ (������Ʈ��, ��Ĺ������)
		//�ʱ� �ż� �������� �ٷ� �����ֱ����� ��Ĺ���� ����(war���Ϸ� ��Ĺ������ �����ô� ���ʿ�)
		
		//���ε�� ���� �̸�
		String originFileName = mf.getOriginalFilename();
		long prename = System.currentTimeMillis();
		long fileSize = mf.getSize();
		System.out.println("originFileName : " + originFileName);
		System.out.println("fileSize : " + fileSize);
		//�����ϱ����� �ߺ��� �Ǵ� ���� ���ϸ��� ���ϱ� ���� ����� ���ϸ�
		String safeFile = path + prename + originFileName;
		String safeFiel1 = path1 + prename + originFileName;
		//DB�� ������ ���� �̸�
		tphoto = prename + originFileName;
		ThemeDto tdto = new ThemeDto(tid, tname, tgenre, tdifficulty, ttime, tprofile, tphoto);
		mphr.setAttribute("tdto", tdto);
		
		com = new ThemeInsertCommand();
		com.execute(mphr, model);
		
		//model��ü�� ���� �����Ϸ��� asMap()�޼��带 ���
		Map<String, Object> map = model.asMap();
		String res = (String)map.get("result");
		System.out.println("res : " + res);
		if(res.equals("success")) {
			try {
				mf.transferTo(new File(safeFile));
				mf.transferTo(new File(safeFiel1));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return "redirect:/main";
		}
		else {
			return "redirect:/main";
		}
	}
	
	//��ϵ� �׸� ��� ������
	@RequestMapping("/themeListPage")
	public String themeListPage(HttpServletRequest request, Model model) {
		System.out.println("themeListPage");
		com = new ThemeListPageCommand();
		com.execute(request, model);
		return "theme/themeListPage";
	}
		
	//��ϵ� �׸� �󼼺��� ������
	@RequestMapping("/themeDetailsPage")
	public String themeDetailsPage(HttpServletRequest request, Model model) {
		System.out.println("themeDetailsPage�Դϴ�");
		com = new ThemeDetailsPageCommand();
		com.execute(request, model);
		return "theme/themeDetailsPage";
	}
		
	//�¸� ���� ������
	@RequestMapping("/themeEdit")
	public String themeEdit(HttpServletRequest request, Model model) {
		System.out.println("themeEdit");
		com = new ThemeContentCommand();
		com.execute(request, model);
		return "theme/themeEdit";
	}
		
	//�׸� ���� DBó��
	@RequestMapping(value="/edit", produces="application/text; charset=UTF-8")
	public String edit(MultipartHttpServletRequest request, Model model) {
		System.out.println("edit��û");
		String tid = request.getParameter("tid"); //rId�� ���Ƿ� ����(DB���� seq�ѹ��� �����ϴϱ� 
		System.out.println("tid��" + tid);
		String tname = request.getParameter("tname");
		String tgenre = request.getParameter("tgenre");
		String tdifficulty = request.getParameter("tdifficulty");
		String ttime = request.getParameter("ttime");
		String tprofile = request.getParameter("tprofile");
		String tphoto = null;
		
		MultipartFile mf = request.getFile("tphoto");
	
		//���ε�� ���� �̸�
		String originFileName = mf.getOriginalFilename();
		long prename = System.currentTimeMillis();
		long fileSize = mf.getSize();
		System.out.println("originFileName : " + originFileName);
		System.out.println("fileSize : " + fileSize);
		
		//�����ϱ����� �ߺ��� �Ǵ� ���� ���ϸ��� ���ϱ� ���� ����� ���ϸ�
		String safeFile = path + prename + originFileName;
		String safeFiel1 = path1 + prename + originFileName;
		
		//DB�� ������ ���� �̸�
		tphoto = prename + originFileName;
		ThemeDto tdto = new ThemeDto(tid, tname, tgenre, tdifficulty, ttime, tprofile, tphoto);
		request.setAttribute("tdto", tdto);
		
		com = new ThemeEditCommand();
		com.execute(request, model);
		Map<String, Object> map = model.asMap();
		String res = (String)map.get("result");
		System.out.println("res : " + res);
		if(res.equals("success")) {
			try {
				mf.transferTo(new File(safeFile));
				mf.transferTo(new File(safeFiel1));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return "redirect:/main";
		}
		else {
			return "redirect:/main";
		}
	}
		
	//�׸� ����
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete��û");
		com = new ThemeDeleteCommand();
		com.execute(request, model);
		com = new ThemeListPageCommand();
		com.execute(request, model);
		return "theme/themeListPage";
	}
}
