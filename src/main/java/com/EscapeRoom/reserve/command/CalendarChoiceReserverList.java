package com.EscapeRoom.reserve.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.EscapeRoom.reserve.dao.ReserveDao;
import com.EscapeRoom.reserve.dto.ReserveDto;
import com.EscapeRoom.util.Constant;

public class CalendarChoiceReserverList implements ReserveCommand {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		
		ReserveDao rdao = Constant.rdao;
		String ymd = request.getParameter("ymd");  // ymd��� calendar���� ������ ��¥�� ������
		ReserveDto rdto = new ReserveDto(null, null, 0, null, ymd, null, 0, null, null, 0); // ymd�� �̿��ؼ� ����ϴ�, �������� null�� �Ǵ� 0�� �־���
		
		ArrayList<ReserveDto> result = rdao.calendarChoiceReserverList(rdto);
		model.addAttribute("result",result);
		model.addAttribute("rdate",ymd); 

	}

}
