package com.EscapeRoom.reserve.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.EscapeRoom.reserve.dao.ReserveDao;
import com.EscapeRoom.reserve.dto.ReserveDto;
import com.EscapeRoom.util.Constant;

public class ReserveDelete implements ReserveCommand {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		
		ReserveDao rdao = Constant.rdao;
		
		String rid = request.getParameter("reserveid"); // rid�� ������ִ� Ű���� �ҷ��� ����
		System.out.println("���� rid���� ? " + rid);
		ReserveDto rdto = new ReserveDto(rid); // �Ķ���� rid�� �ϳ��� �ִ� ������ ����
		
		rdao.deleteReserve(rid); // ���� �޼ҵ� ����

	}

}
