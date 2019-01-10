package net.pmosoft.pony.dams.jdbc;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JdbcInfoDao {


    List<JdbcInfo> selectJdbcInfoList(JdbcInfo jdbcInfo);
    List<Map<String, Object>> selectJdbcInfoRegList(Map<String,String> params);
    List<Map<String, Object>> selectJdbcInfoCombo(Map<String,String> params);
    List<Map<String, Object>> selectJdbcInfoExtList(Map<String,String> params);
    List<Map<String, Object>> selectJdbcInfoExtRegList(Map<String,String> params);
    List<Map<String, Object>> selectJdbcInfoExt(Map<String,String> params);
    int selectJdbcInfoCnt(Map<String,String> params);
    int selectJdbcInfoExtCnt(Map<String,String> params);
    void insertJdbcInfo(Map<String,String> params);
    void insertJdbcInfoExt(Map<String,String> params);
    void updateJdbcInfo(Map<String,String> params);
    void updateJdbcInfoExt(Map<String,String> params);
    void deleteJdbcInfoExt(Map<String,String> params);
    void deleteJdbcInfo(Map<String,String> params);
}

