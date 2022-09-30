package com.EscapeRoom.reserve.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.EscapeRoom.reserve.dao.ReserveDao;
import com.EscapeRoom.reserve.dto.ReserveDto;
import com.EscapeRoom.reserve.dto.ReserveDto4;
import com.EscapeRoom.reserve.dto.TodayReserveCheckDto;
import com.EscapeRoom.reserve.dto.TodayReserveTidDto;
import com.EscapeRoom.reserve.dto.startNoRdate;
import com.EscapeRoom.theme.dao.ThemeDao;
import com.EscapeRoom.theme.dto.ThemeDto;
import com.EscapeRoom.util.Constant;

public class ThemeMultipleReserveTimeCheck implements ReserveCommand {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		
		ReserveDao rdao = Constant.rdao;
		ThemeDao tdao = Constant.tdao;
		
		// ���� ��¥ ���ϱ�
		Date today= new Date(); 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = simpleDateFormat.format(today);
		
		
		// ���� �𵨰� 'themeList' ���� tid��,tname,tphoto,tprofile ���� �۾�
		HashMap<String, Object> map = (HashMap)model.asMap();
		ArrayList<ThemeDto> tList = (ArrayList)map.get("themeList");
		String tid = null;
		String tname = null;
		String tphoto = null;
		String tprofile = null;
		String ttime = null;
		String tdifficulty =null;
		String tgenre = null;
		
		
		
		for(int j=0;j<tList.size();j++ ) {
			// �����۾�
			tid = "tid" + j;
			tname = "tname" + j;
			tphoto = "tphoto" + j;
			tprofile = "tprofile" + j;
			ttime = "ttime" + j;
			tdifficulty = "tdifficulty" + j;
			tgenre = "tgenre" + j;
			request.setAttribute(tid, tList.get(j).getTid());
			request.setAttribute(tname, tList.get(j).getTname());
			request.setAttribute(tphoto, tList.get(j).getTphoto());
			request.setAttribute(tprofile, tList.get(j).getTprofile());
			request.setAttribute(ttime, tList.get(j).getTtime());
			request.setAttribute(tdifficulty, tList.get(j).getTdifficulty());
			request.setAttribute(tgenre, tList.get(j).getTgenre());
			
			// ������ ����
			String tId = (String) request.getAttribute(tid);
			String tName = (String) request.getAttribute(tname);
			String tPhoto = (String) request.getAttribute(tphoto);
			String tProfile = (String) request.getAttribute(tprofile);
			String tTime = (String) request.getAttribute(ttime);
			String tDifficulty = (String) request.getAttribute(tdifficulty);
			String tGenre = (String) request.getAttribute(tgenre);
			
			String rtime; //�׸� ���� �ð� �����
			String todayCheck; // �𵨸� �����
			
			for(int i=1; i<9;i++) {   // �ð���� 
				if(i%2==1) {//i=1 / 10:20��, i=3 / 13:20�� ,i=5 /16:20��  ,i=7 / 19:20�� 
					rtime = "1"+ ((i-1)*3/2) +":20";
					if(j==0) {
						todayCheck = "todayCheck"+i;
						// ����Ǿ��ִ��� üũ ������ rCheck 1��ȯ
						ReserveDto rdto = new ReserveDto(strToday,tId,rtime);
						ReserveDto rdtoSu = rdao.themeReserveTimeCheck(rdto);
						TodayReserveCheckDto trcDto;
						if(rdtoSu==null) { // rCheck ���°��
							trcDto = new TodayReserveCheckDto(tId, tName, tPhoto, tProfile, rtime, strToday, tTime, tDifficulty, tGenre, 0);
						}
						else { //rCheck �ִ� ���
							trcDto = new TodayReserveCheckDto(tId, tName, tPhoto, tProfile, rtime, strToday, tTime, tDifficulty, tGenre, rdtoSu.getrCheck());
						}
						model.addAttribute(todayCheck,trcDto);
						System.out.println("todayCheck��" + todayCheck);
					}
					else {
						todayCheck = "todayCheck"+j+i;
						ReserveDto rdto = new ReserveDto(strToday,tId,rtime);
						ReserveDto rdtoSu = rdao.themeReserveTimeCheck(rdto);
						TodayReserveCheckDto trcDto;
						if(rdtoSu==null) { // rCheck ���°��
							trcDto = new TodayReserveCheckDto(tId, tName, tPhoto, tProfile, rtime, strToday, tTime, tDifficulty, tGenre, 0);
						}
						else { //rCheck �ִ� ���
							trcDto = new TodayReserveCheckDto(tId, tName, tPhoto, tProfile, rtime, strToday, tTime, tDifficulty, tGenre, rdtoSu.getrCheck());
						}
						model.addAttribute(todayCheck,trcDto);
						System.out.println("todayCheck��" + todayCheck);
					}
					
				}
				
				else if(i%2==0) {
					if(i==8) { // i=8�϶� 20:50��
						if(j==0) {
							rtime = "20:50";
							todayCheck = "todayCheck"+i;
							ReserveDto rdto = new ReserveDto(strToday,tId,rtime);
							ReserveDto rdtoSu = rdao.themeReserveTimeCheck(rdto);
							TodayReserveCheckDto trcDto;
							if(rdtoSu==null) { // rCheck ���°��
								trcDto = new TodayReserveCheckDto(tId, tName, tPhoto, tProfile, rtime, strToday, tTime, tDifficulty, tGenre, 0);
							}
							else { //rCheck �ִ� ���
								trcDto = new TodayReserveCheckDto(tId, tName, tPhoto, tProfile, rtime, strToday, tTime, tDifficulty, tGenre, rdtoSu.getrCheck());
							}
							model.addAttribute(todayCheck,trcDto);
							System.out.println("todayCheck��" + todayCheck);
						}
						else {
							rtime = "20:50";
							todayCheck = "todayCheck"+j+i;
							ReserveDto rdto = new ReserveDto(strToday,tId,rtime);
							ReserveDto rdtoSu = rdao.themeReserveTimeCheck(rdto);
							TodayReserveCheckDto trcDto;
							if(rdtoSu==null) { // rCheck ���°��
								trcDto = new TodayReserveCheckDto(tId, tName, tPhoto, tProfile, rtime, strToday, tTime, tDifficulty, tGenre, 0);
							}
							else { //rCheck �ִ� ���
								trcDto = new TodayReserveCheckDto(tId, tName, tPhoto, tProfile, rtime, strToday, tTime, tDifficulty, tGenre, rdtoSu.getrCheck());
							}
							model.addAttribute(todayCheck,trcDto);
							System.out.println("todayCheck��" + todayCheck);
						}
						
					}
					
					else { 
						if(j==0) {
							// i=2 / 11:50, i=4 / 14:50, i=6 / 17:50;
							rtime = "1"+ ((i-1)*3/2) + ":50";
							todayCheck = "todayCheck"+i; 
							ReserveDto rdto = new ReserveDto(strToday,tId,rtime);
							ReserveDto rdtoSu = rdao.themeReserveTimeCheck(rdto);
							TodayReserveCheckDto trcDto;
							if(rdtoSu==null) { // rCheck ���°��
								trcDto = new TodayReserveCheckDto(tId, tName, tPhoto, tProfile, rtime, strToday, tTime, tDifficulty, tGenre, 0);
							}
							else { //rCheck �ִ� ���
								trcDto = new TodayReserveCheckDto(tId, tName, tPhoto, tProfile, rtime, strToday, tTime, tDifficulty, tGenre, rdtoSu.getrCheck());
							}
							model.addAttribute(todayCheck,trcDto);
							System.out.println("todayCheck��" + todayCheck);
						}
						else {
							// i=2 / 11:50, i=4 / 14:50, i=6 / 17:50;
							rtime = "1"+ ((i-1)*3/2) + ":50";
							todayCheck = "todayCheck"+j+i;
							
							ReserveDto rdto = new ReserveDto(strToday,tId,rtime);
							ReserveDto rdtoSu = rdao.themeReserveTimeCheck(rdto);
							TodayReserveCheckDto trcDto;
							if(rdtoSu==null) { // rCheck ���°��
								trcDto = new TodayReserveCheckDto(tId, tName, tPhoto, tProfile, rtime, strToday, tTime, tDifficulty, tGenre, 0);
							}
							else { //rCheck �ִ� ���
								trcDto = new TodayReserveCheckDto(tId, tName, tPhoto, tProfile, rtime, strToday, tTime, tDifficulty, tGenre, rdtoSu.getrCheck());
							}
							model.addAttribute(todayCheck,trcDto);
							System.out.println("todayCheck��" + todayCheck);
						}
					
					}
				}
			
			}
			
			//�׸�����Ʈ8�� ����� ���� ����뵵
			String ThemeList = "ThemeList" + (j+1); // �׸�����Ʈ ����
			TodayReserveCheckDto trcDto = new TodayReserveCheckDto(tId, tName, tPhoto, tProfile, null, strToday, tTime, tDifficulty, tGenre, 0);
			model.addAttribute(ThemeList,trcDto);
		
			
			String TodayReserve = "TodayReserve"+ (j+1);
			// �׸�id�� ���� ������ �Ǿ��ִ��� Ȯ�ο�
			TodayReserveTidDto Trdt = new TodayReserveTidDto(strToday, null, tId);
			int result = rdao.todayReservethemeCheck(Trdt);
			
			
			// �Ϸ��� �� �׸��� 8���� �ð�ǥ�� ������ 
			if(result == 8) { // 8���� ������� �߸� �ش� �׸��� ��� ���� ����Ȼ�����
				String rCheckFinish = "finish";
				model.addAttribute(TodayReserve,rCheckFinish);
			}
			else {
				String rCheckFinish = "NotYet";
				model.addAttribute(TodayReserve,rCheckFinish);
			}
	
		}
	}

}
