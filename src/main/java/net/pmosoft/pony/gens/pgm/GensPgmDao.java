package net.pmosoft.pony.gens.pgm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GensPgmDao {

	List<Map<String, Object>> selectGensPgmList(Map<String,String> params);
	int selectGensPgmCnt(Map<String,String> params);
	void insertGensPgm(Map<String,String> params);
	void deleteGensPgm(Map<String,String> params);
	void updateGensPgm(Map<String,String> params);
	
}

