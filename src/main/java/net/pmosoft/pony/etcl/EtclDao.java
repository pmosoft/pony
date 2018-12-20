package net.pmosoft.pony.etcl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EtclDao {

	List<Map<String, Object>> selectEtclList(Map<String,String> params);
	int selectEtclCnt(Map<String,String> params);
	void insertEtcl(Map<String,String> params);
	void deleteEtcl(Map<String,String> params);
	void updateEtcl(Map<String,String> params);

}

