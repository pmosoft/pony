package net.pmosoft.pony.dams.code;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeDao {


    List<Code> selectCodeList(Code code);
    List<Map<String, Object>> selectCodeRegList(Map<String,String> params);
    List<Map<String, Object>> selectCodeCombo(Map<String,String> params);
    List<Map<String, Object>> selectCodeExtList(Map<String,String> params);
    List<Map<String, Object>> selectCodeExtRegList(Map<String,String> params);
    List<Map<String, Object>> selectCodeExt(Map<String,String> params);
    int selectCodeCnt(Map<String,String> params);
    int selectCodeExtCnt(Map<String,String> params);
    void insertCode(Code code);
    void insertCodeExt(Map<String,String> params);
    void updateCode(Map<String,String> params);
    void updateCodeExt(Map<String,String> params);
    void deleteCodeExt(Map<String,String> params);
    void deleteCode(Code code);
}

