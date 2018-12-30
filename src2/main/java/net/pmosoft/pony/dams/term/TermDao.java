package net.pmosoft.pony.dams.term;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TermDao {


	/**********************************************************************************
	 *
	 *                                  Term
	 *
	 **********************************************************************************/
    List<Map<String, Object>> selectExtTermList(Map<String,String> params);
    List<Map<String, Object>> selectCmpTermList(Map<String,String> params);
	List<Map<String, Object>> selectTermList(Map<String,String> params);
	int selectTermCnt(Map<String,String> params);
	void insertExtTermList(Map<String,Object> params);
    void insertTerm(Map<String,String> params);
    void deleteExtTermList(Map<String,String> params);
	void deleteTerm(Map<String,String> params);
	void updateTerm(Map<String,String> params);
	
	
    
    /**********************************************************************************
     *                                  
     *                                  Pack
     *                                  
     **********************************************************************************/
    List<Map<String, Object>> selectPackInfoList(Map<String,String> params);
    int selectPackInfoCnt(Map<String,String> params);
    void insertPackInfo(Map<String,String> params);
    void deletePackInfo(Map<String,String> params);
    void updatePackInfo(Map<String,String> params);
	

}

