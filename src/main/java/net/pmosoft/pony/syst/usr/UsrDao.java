package net.pmosoft.pony.syst.usr;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsrDao {
	List<Map<String, Object>> selectUsrList(Map<String,String> params);

	int selectUsrCnt(Map<String,String> params);
	
	void insertUsr(Map<String,String> params);
	void deleteUsr(Map<String,String> params);
	void updateUsr(Map<String,String> params);
}

