package net.pmosoft.pony.comm.excel;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExcelDao {

	List<Map<String, Object>> selectExcelList(Map<String,String> params);
	int selectExcelCnt(Map<String,String> params);
	void insertExcel(Map<String,String> params);
	void deleteExcel(Map<String,String> params);
	void updateExcel(Map<String,String> params);

}

