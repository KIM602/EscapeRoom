package com.EscapeRoom.reserve.dao;

import com.EscapeRoom.reserve.dto.ReserveDto;

public interface RdaoInterface {
	// �߻�޼���
	public void insertReserve(ReserveDto dto);
	public ReserveDto themeReserveTimeCheck(ReserveDto dto);
	public ReserveDto reserveFindCheck(ReserveDto dto);
	
}
