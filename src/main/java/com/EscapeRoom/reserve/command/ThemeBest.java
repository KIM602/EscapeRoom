package com.EscapeRoom.reserve.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.EscapeRoom.reserve.dao.ReserveDao;
import com.EscapeRoom.reserve.dto.ReserveDto;
import com.EscapeRoom.reserve.dto.ReserveDto2;
import com.EscapeRoom.reserve.dto.ReserveDto4;
import com.EscapeRoom.reserve.dto.TodayReserveCheckDto;
import com.EscapeRoom.theme.dto.ThemeDto;
import com.EscapeRoom.util.Constant;

public class ThemeBest implements ReserveCommand {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		
		ReserveDao rdao = Constant.rdao;
		
		ReserveDto2 rdto = new ReserveDto2(null, null, null);
		ArrayList<ReserveDto2> blist =  rdao.ThemeBest(rdto);
		
		
		
		
		String tid = null; // request SetŰ����
		String rank = null;  // request SetŰ����
		String cnt = null; // request SetŰ����
		
		
		for(int j=1; j<10; j++) {
			String ThemeList = "ThemeList"+j;
			
			TodayReserveCheckDto tList = (TodayReserveCheckDto) model.asMap().get(ThemeList);
			String ThemeListTid = tList.gettId();
			System.out.println("ThemeListTid"+ThemeListTid);
				
			for(int i=0;i<blist.size();i++) {
				
				tid = "tid" + (0+i); // �׸� ���̵�  request SetŰ����
				rank = "rank" + (0+i); // ���� request SetŰ����
				cnt = "cnt" + (0+i); // Ƚ�� request SetŰ����
				request.setAttribute(tid, blist.get(i).getTid());
				request.setAttribute(rank, blist.get(i).getRank());
				request.setAttribute(cnt, blist.get(i).getCnt());
				
				String Tid = (String) request.getAttribute(tid); // request get�뵵
				String Rank = (String) request.getAttribute(rank); // request get�뵵
				String Cnt = (String) request.getAttribute(cnt); // request get�뵵
				
				if(Tid.equals(ThemeListTid)) { // ���ڿ� ��
					String bestList = "bestList" + j; // ����Ʈ����Ʈ�� ThemeList�� ���� ��ġ�ؾ� �ϹǷ� j��ȣ�� �̸��� ����
					System.out.println("bestList" + bestList);
					System.out.println("ThemeListTid = " + ThemeList + " and Tid = " + Tid );
					System.out.println("Rank = " + Rank);
					rdto= new ReserveDto2(Cnt, Rank, Tid); //�����
					model.addAttribute(bestList,rdto); // ���� ����
				}
			}
			
		}
		
		
				
				

	}

}
