package net.pmosoft.pony.comm.http;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MultipartDao {

	List<Map<String, Object>> selectMultipartList(Map<String,String> params);
	int selectMultipartCnt(Map<String,String> params);
	void insertMultipart(Map<String,String> params);
	void deleteMultipart(Map<String,String> params);
	void updateMultipart(Map<String,String> params);

}

