package com.EscapeRoom.reserve.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.EscapeRoom.reserve.dao.ReserveDao;
import com.EscapeRoom.reserve.dto.ReserveDto;
import com.EscapeRoom.util.Constant;

public class ReserveFind implements ReserveCommand {
	
	
	// �����ڰ� �ܼ�
	@Override
	public void execute(HttpServletRequest request, Model model) {
		ReserveDao rdao = Constant.rdao;
		String rid = request.getParameter("rId"); // ���̵� �ޱ�
		System.out.println("rid����  ? " + rid);
		ReserveDto rdto = new ReserveDto(rid); // �ش�Ǵ� ���� id�� ����
		ArrayList<ReserveDto> result = rdao.reserveFindMoreThan2DetailPage(rdto); 
		model.addAttribute("reserveCheckData",result);
	}
	
	// ��ȸ�� �����ߴٴ� String Ÿ���� ������� �ʿ��ؼ� ���� Ŭ������ ������
	public String StrExecute(HttpServletRequest request, Model model) {
		ReserveDao rdao = Constant.rdao;
		String name = request.getParameter("nameKey");  // ���ǿ��� �޾ƿ� Ű���� �̿��Ͽ� ���� ������
		String phone = request.getParameter("phoneKey");
		System.out.println("reservefind���� name��" + name);
		System.out.println("reservefind���� phone��" + phone);
		ReserveDto rdto = new ReserveDto(phone, name); // Ȯ�ο� ������ ���� ������ ReserveDto�� setter
		ArrayList<ReserveDto> result = rdao.reserveFindCheck(rdto);  // ������ �������� ����
		String StrResult;  // ���� �������θ� �ľ�
		if(result.size() < 1)  { // ������ ������� ����������
			StrResult = "fail";  // ���� ���� �޼��� ����
		}
		else {
			if(result.size() == 1) { // ����� ����Ʈ�� �Ѱ��� ���
				StrResult ="success";
				model.addAttribute("reserveCheckData",result); // ���������� ����ϱ� ���� �𵨰� ����
				
			}
			else { // ����� ����Ʈ�� 2�� �̻��� ���
				StrResult = "success2";  
				model.addAttribute("reserveCheckData",result);
			}
			
		}
		return StrResult;
	}


}
