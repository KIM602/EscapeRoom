package com.EscapeRoom.admin.dao;

import com.EscapeRoom.admin.dto.ProjectAdminJoinDto;

public interface IProjectAdminDao {
	//�߻�޼���
	public String join(ProjectAdminJoinDto dto);
	public ProjectAdminJoinDto login(String pid);
	
}
