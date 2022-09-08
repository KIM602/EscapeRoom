package com.EscapeRoom.reserve.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.EscapeRoom.reserve.dao.ReserveDao;
import com.EscapeRoom.reserve.dto.ReserveDto;
import com.EscapeRoom.util.Constant;

public class reserveFind implements ReserveCommand {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		// ������ ���⼭��
	}
	
	// ��ȸ�� �����ߴٴ� String Ÿ���� ������� �ʿ��ؼ� ���� Ŭ������ ������
	public String StrExecute(HttpServletRequest request, Model model) {
		ReserveDao rdao = Constant.rdao;
		String name = request.getParameter("nameKey");  // ���ǿ��� �޾ƿ� Ű���� �̿��Ͽ� ���� ������
		String phone = request.getParameter("phoneKey");
		System.out.println("reservefind���� name��" + name);
		System.out.println("reservefind���� phone��" + phone);
		ReserveDto rdto = new ReserveDto(phone, name);  // Ȯ�ο� ������ ���� ������ ReserveDto�� setter
		ReserveDto result = rdao.reserveFindCheck(rdto);  // ������ �������� ����
		String StrResult;  // ���� �������θ� �ľ�
	
		if(result == null)  { // ������ ������� ����������
			StrResult = "fail";  // ���� ���� �޼��� ����
		}
		else {
			StrResult ="success";
			//request.setAttribute("tid", result.gettId());
			System.out.println("tid����?"+result.gettId());
			request.setAttribute("tid", result.gettId()); // �׸�Id�� ���� ���̺� �־� �ܷ�Ű�� tid �� ����
			
			model.addAttribute("reserveCheckData",result); // ���������� ����ϱ� ���� �𵨰� ����
		}
		return StrResult;
	}


}
