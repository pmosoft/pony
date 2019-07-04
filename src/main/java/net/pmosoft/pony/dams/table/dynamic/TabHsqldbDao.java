/*******************************************************************************
@title:테이블DAO를 마리아DB로 구현 
@description-start
@description-end  
@developer:피승현
@progress-rate:80%
@update-history-start
-------------------------------------------------------------------------------
|   날짜   |수정자|내용
-------------------------------------------------------------------------------
|2017.11.01|피승현|최초개발
-------------------------------------------------------------------------------
@update-history-end
********************************************************************************/

package net.pmosoft.pony.dams.table.dynamic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.pmosoft.pony.comm.db.DbCon;
import net.pmosoft.pony.comm.db.LoggableStatement;

   
public class TabHsqldbDao extends TabCommonDao implements TabDaoFactory {



    /**********************************************************************************
    *
    *                                    Meta
    *
    **********************************************************************************/
    
    @Override
    public List<Map<String, Object>> selectMetaTabColList(Map<String, String> params) {

        Connection conn=null; PreparedStatement pstmt=null; ResultSet rs=null; String qry="";
        
        List<Map<String, Object>> listRs = new ArrayList<Map<String, Object>>();
        
        try {
            DbCon dbConn = new DbCon();
            conn = dbConn.getConnection(params);

            qry  = "--                                                                                         \n";
            qry += "SELECT                                                                                     \n";
            qry += "        ?                     AS DB_NM                                                     \n";
            qry += "       ,UPPER(A.TABLE_SCHEMA) AS OWNER                                                     \n";
            qry += "       ,UPPER(A.TABLE_NAME)   AS TAB_NM                                                    \n";
            qry += "       ,A.ORDINAL_POSITION    AS COL_ID                                                    \n";
            qry += "       ,A.COLUMN_NAME         AS COL_NM                                                    \n";
            qry += "       ,A.COLUMN_COMMENT      AS COL_HNM                                                   \n";
            qry += "       ,CASE WHEN UPPER(A.DATA_TYPE) = 'INT' THEN UPPER(A.DATA_TYPE)                       \n";
            qry += "             ELSE UPPER(A.COLUMN_TYPE)                                                     \n";
            qry += "        END                   AS DATA_TYPE_DESC                                            \n";
            qry += "       ,CASE WHEN IS_NULLABLE = 'NO' THEN 'NOT NULL' ELSE '' END AS NULLABLE               \n";
            qry += "       ,''                    AS PK                                                        \n";
            qry += "       ,UPPER(A.DATA_TYPE)    AS DATA_TYPE_NM                                              \n";
            qry += "       ,CASE WHEN UPPER(A.DATA_TYPE) IN ('CHAR','VARCHAR') THEN A.CHARACTER_MAXIMUM_LENGTH \n";
            qry += "             WHEN UPPER(A.DATA_TYPE) IN ('INT','NUMERIC') THEN A.NUMERIC_PRECISION         \n";
            qry += "        END                   AS LEN                                                       \n";
            qry += "       ,A.NUMERIC_SCALE       AS DECIMAL_CNT                                               \n";
            qry += "       ,' '                   AS COL_DESC                                                  \n";
            qry += "       ,DATE_FORMAT(NOW(),'%Y%m%d%H%i') AS REG_DTM                                         \n";
            qry += "       ,''                    AS REG_USR_ID                                                \n";
            qry += "       ,DATE_FORMAT(NOW(),'%Y%m%d%H%i') AS UPD_DTM                                         \n";
            qry += "       ,''                    AS UPD_USR_ID                                                \n";
            qry += "FROM   INFORMATION_SCHEMA.COLUMNS A                                                        \n";
            qry += "WHERE  1=1                                                                                 \n";
            qry += "AND    A.TABLE_SCHEMA = ?                                                                  \n";
            qry += "AND    A.TABLE_NAME LIKE CONCAT(CONCAT('%',?),'%')                                         \n";
            //qry += "AND    A.COLUMN_NAME LIKE '%'                                                              \n";
            qry += "ORDER BY A.TABLE_NAME,A.ORDINAL_POSITION                                                   \n";

            //System.out.println(qry);

            pstmt = new LoggableStatement(conn,qry);
            pstmt.setString(1, params.get("datasource"));
            pstmt.setString(2, params.get("dbOwner"));
            pstmt.setString(3, params.get("TAB_NM"));
            
            System.out.println(((LoggableStatement)pstmt).getQueryString() + "\n");
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                //map.put("STS_NM"        ,rs.getString("STS_NM"        )); 
                map.put("DB_NM"         ,rs.getString("DB_NM"         )); 
                map.put("OWNER"         ,rs.getString("OWNER"         ));
                map.put("TAB_NM"        ,rs.getString("TAB_NM"        ));
                map.put("COL_ID"        ,rs.getString("COL_ID"        ));
                map.put("COL_NM"        ,rs.getString("COL_NM"        ));
                map.put("COL_HNM"       ,rs.getString("COL_HNM"       ));
                map.put("DATA_TYPE_DESC",rs.getString("DATA_TYPE_DESC"));
                map.put("NULLABLE"      ,rs.getString("NULLABLE"      ));
                map.put("PK"            ,rs.getString("PK"            ));
                map.put("DATA_TYPE_NM"  ,rs.getString("DATA_TYPE_NM"  ));
                map.put("LEN"           ,rs.getString("LEN"           ));
                map.put("DECIMAL_CNT"   ,rs.getString("DECIMAL_CNT"   ));
                map.put("COL_DESC"      ,rs.getString("COL_DESC"      ));
                map.put("REG_DTM"       ,rs.getString("REG_DTM"       ));
                map.put("REG_USR_ID"    ,rs.getString("REG_USR_ID"    ));
                map.put("UPD_DTM"       ,rs.getString("UPD_DTM"       ));
                map.put("UPD_USR_ID"    ,rs.getString("UPD_USR_ID"    ));
                
                listRs.add(map);
            }
            
            
        } catch (Exception e) { e.printStackTrace();
        } finally { if(conn != null) try { pstmt.close(); conn.close();} catch(Exception ee){}}
        
        return listRs;
    }


    @Override
    public List<Map<String, Object>> selectMetaTabList(Map<String, String> params) {
        Connection conn=null; PreparedStatement pstmt=null; ResultSet rs=null; String qry="";
        
        List<Map<String, Object>> listRs = new ArrayList<Map<String, Object>>();
        
        try {
            DbCon dbConn = new DbCon();
            conn = dbConn.getConnection(params);

            //원본쿼리 : net.pmosoft.pony.dams.table.TabMariadbDao.xml - insertMetaTabColList
            qry  = "--                                                                  \n";
            qry += "SELECT                                                              \n";
            qry += "       ?                      AS DB_NM                              \n";
            qry += "     , UPPER(A.TABLE_SCHEMA)  AS OWNER                              \n";
            qry += "     , UPPER(A.TABLE_NAME)    AS TAB_NM                             \n";
            qry += "     , UPPER(A.TABLE_COMMENT) AS TAB_HNM                            \n";
            qry += "     , ''                     AS TAB_DESC                           \n";
            qry += "     , A.TABLE_ROWS           AS ROW_CNT                            \n";
            qry += "     , DATE_FORMAT(NOW(),'%Y%m%d%H%i') AS REG_DTM                   \n";
            qry += "     , ''                    AS REG_USR_ID                          \n";
            qry += "     , DATE_FORMAT(NOW(),'%Y%m%d%H%i') AS UPD_DTM                   \n";
            qry += "     , ''                    AS UPD_USR_ID                          \n";
            qry += "FROM   INFORMATION_SCHEMA.TABLES A                                  \n";
            qry += "WHERE  1=1                                                          \n";
            qry += "AND    A.TABLE_SCHEMA = ?                                           \n";
            qry += "AND   (UPPER(A.TABLE_NAME) LIKE UPPER(CONCAT(CONCAT('%',?),'%'))    \n";
            qry += "       OR                                                           \n";
            qry += "       UPPER(A.TABLE_COMMENT) LIKE UPPER(CONCAT(CONCAT('%',?),'%')) \n";
            qry += "       )                                                            \n";
            qry += "ORDER BY A.TABLE_SCHEMA, A.TABLE_NAME                               \n";
            
            //System.out.println(qry);

            pstmt = new LoggableStatement(conn,qry);
            pstmt.setString(1, params.get("datasource"));
            pstmt.setString(2, params.get("dbOwner"));
            pstmt.setString(3, params.get("TAB_NM"));
            pstmt.setString(4, params.get("TAB_NM"));
            
            System.out.println(((LoggableStatement)pstmt).getQueryString() + "\n");
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                
                //map.put("STS_NM"        ,rs.getString("STS_NM"        )); 
                map.put("DB_NM"         ,rs.getString("DB_NM"       )); 
                map.put("OWNER"         ,rs.getString("OWNER"       ));
                map.put("TAB_NM"        ,rs.getString("TAB_NM"      ));
                map.put("TAB_HNM"       ,rs.getString("TAB_HNM"     ));
                map.put("TAB_DESC"      ,rs.getString("TAB_DESC"    ));
                map.put("ROW_CNT"       ,rs.getString("ROW_CNT"    ));
                map.put("REG_DTM"       ,rs.getString("REG_DTM"     ));
                map.put("REG_USR_ID"    ,rs.getString("REG_USR_ID"  ));
                map.put("UPD_DTM"       ,rs.getString("UPD_DTM"     ));
                map.put("UPD_USR_ID"    ,rs.getString("UPD_USR_ID"  ));
                
                listRs.add(map);
            }
            
            
        } catch (Exception e) { e.printStackTrace();
        } finally { if(conn != null) try { pstmt.close(); conn.close();} catch(Exception ee){}}
        
        return listRs;
    }

    
}

