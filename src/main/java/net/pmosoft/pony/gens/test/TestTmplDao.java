package net.pmosoft.pony.gens.test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestTmplDao {

	List<Map<String, Object>> selectTestTmplList(Map<String,String> params);
	int selectTestTmplCnt(Map<String,String> params);
	void insertTestTmpl(Map<String,String> params);
	void deleteTestTmpl(Map<String,String> params);
	void updateTestTmpl(Map<String,String> params);

}

