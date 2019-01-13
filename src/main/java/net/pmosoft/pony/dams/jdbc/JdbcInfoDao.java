package net.pmosoft.pony.dams.jdbc;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JdbcInfoDao {

    void           insertJdbcInfo(JdbcInfo inVo);
    List<JdbcInfo> selectJdbcInfoList(JdbcInfo inVo);
    JdbcInfo       selectJdbcInfo(JdbcInfo inVo);
    List<JdbcInfo> selectComboJdbcList(JdbcInfo inVo);
    int            selectJdbcInfoCnt(JdbcInfo inVo);
    void           updateJdbcInfo(JdbcInfo inVo);
    void           deleteJdbcInfo(JdbcInfo inVo);
}

