package com.EscapeRoom.reserve.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.EscapeRoom.reserve.dao.ReserveDao;
import com.EscapeRoom.reserve.dto.ReserveDto;
import com.EscapeRoom.util.Constant;

public class InsertReserve implements ReserveCommand {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		
		String rid = "";
		String rPhone = request.getParameter("rPhone"); //
		int rCount = Integer.parseInt(request.getParameter("rCount")); //
		String rTerms = request.getParameter("rTerms");  //
		String rDate = request.getParameter("rDate"); //
		String rTime = request.getParameter("rTime"); //
		int rCheck = 1;
		String tId = request.getParameter("tId"); //
		String rName = request.getParameter("rName"); //
		int rPrice = Integer.parseInt(request.getParameter("rPrice")); //
		String rThemeName = request.getParameter("rThemeName");

		System.out.println("rPrice�� " + rPrice);
		System.out.println("rPhone�� " + rPhone);
		System.out.println("rTerms�� " + rTerms);
		System.out.println("rDate�� " + rDate);
		System.out.println("rTime�� " + rTime);
		System.out.println("tId�� " + tId);
		System.out.println("rName�� " + rName);
		System.out.println("rPrice�� " + rPrice);
		
		ReserveDto dto = new ReserveDto(rid, rPhone, rCount, rTerms, rDate, rTime, rCheck, tId, rName, rPrice,rThemeName);
				
		ReserveDao rdao = Constant.rdao;
		rdao.insertReserve(dto);
	
	}

}
