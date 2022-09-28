package com.EscapeRoom.theme.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.EscapeRoom.theme.dao.ThemeDao;
import com.EscapeRoom.theme.dto.ThemeDto;


// �׸��̹����� �������� ����ϴ� Constant �����ϴ� ����� �ƴ� �ٸ� ������� �ٲپ���.
// ThemeImageCommand�� bean�� ������Ŵ
@Repository
public class ThemeImageCommand implements ThemeCommand {

	
	// themeDao Bean�� ���Թ���
	@Autowired
	private ThemeDao tdao;
	// reserve�뵵
	@Override
	public void execute(HttpServletRequest request, Model model) {
		String tId = request.getParameter("themevalue"); // �׸� id�� ������ִ� Ű�� themevalue�� tId ������ ����
		System.out.println("tId��" + tId);
		ThemeDto tdto = tdao.themeImage(tId); // ������ �Ķ���ͷ� �̿��Ͽ� Dao ������ ����  Ÿ���� Themedto�� ����
		
		System.out.println("execute tdto��"+tdto);
		model.addAttribute("theme",tdto); // �𵨿� ����
		
	}

}
