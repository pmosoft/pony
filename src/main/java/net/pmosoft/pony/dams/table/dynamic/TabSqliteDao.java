package net.pmosoft.pony.dams.table.dynamic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.pmosoft.pony.comm.db.DbConnection;
import net.pmosoft.pony.comm.db.LoggableStatement;

   
public class TabSqliteDao extends TabCommonDao implements TabDaoFactory {



    @Override
    public List<Map<String, Object>> selectMetaTabColList(Map<String, String> params) {
        
        Connection conn=null; PreparedStatement pstmt=null; Statement stmt=null; ResultSet rs=null; String qry="";
        
        List<Map<String, Object>> listRs = new ArrayList<Map<String, Object>>();
        
        try {
            DbConnection dbConn = new DbConnection();
            conn = dbConn.getConnection(params);

            //원본쿼리 : net.pmosoft.pony.dams.table.TabOracleDao.xml - insertMetaTabColList
            qry  = "--                                                                                                          \n";
            qry += "SELECT                                                                                                      \n";
            qry += "        ?                                        AS DB_NM                                                   \n";
            qry += "       ,A.OWNER                                  AS OWNER                                                   \n";
            qry += "       ,A.TABLE_NAME                             AS TAB_NM                                                  \n";
            qry += "       ,A.COLUMN_ID                              AS COL_ID                                                  \n";
            qry += "       ,A.COLUMN_NAME                            AS COL_NM                                                  \n";
            qry += "       ,B.COMMENTS                               AS COL_HNM                                                 \n";
            qry += "       ,CASE WHEN A.DATA_TYPE IN ('CHAR','VARCHAR','VARCHAR2') THEN A.DATA_TYPE||'('||A.DATA_LENGTH||')'    \n";
            qry += "             WHEN A.DATA_TYPE IN ('NUMBER') THEN A.DATA_TYPE||'('||A.DATA_PRECISION||','||A.DATA_SCALE||')' \n";
            qry += "             WHEN A.DATA_TYPE IN ('DATE','TIMESTAMP') THEN A.DATA_TYPE                                      \n";
            qry += "             ELSE A.DATA_TYPE                                                                               \n";
            qry += "        END                   AS DATA_TYPE_DESC                                                             \n";
            qry += "       ,DECODE(A.NULLABLE,'Y',' ','NOT NULL') AS NULLABLE                                                   \n";
            qry += "       ,DECODE(C.COLUMN_NAME,'NULL','','PK')    AS PK                                                       \n";
            qry += "       ,A.DATA_TYPE                              AS DATA_TYPE_NM                                            \n";
            qry += "       ,A.DATA_LENGTH                            AS LEN                                                     \n";
            qry += "       ,A.DATA_PRECISION                         AS DECIMAL_CNT                                             \n";
            qry += "       ,' '                                      AS COL_DESC                                                \n";
            qry += "       ,TO_CHAR(SYSTIMESTAMP,'YYYYMMDDHH24MISSFF2') AS REG_DTM                                              \n";
            qry += "       ,''                                       AS REG_USR_ID                                              \n";
            qry += "       ,TO_CHAR(SYSTIMESTAMP,'YYYYMMDDHH24MISSFF2') AS UPD_DTM                                              \n";
            qry += "       ,''                                       AS UPD_USR_ID                                              \n";
            qry += "FROM   SYS.ALL_TAB_COLUMNS  A,                                                                              \n";
            qry += "       SYS.ALL_COL_COMMENTS B,                                                                              \n";
            qry += "      (SELECT DISTINCT TABLE_NAME,COLUMN_NAME                                                               \n";
            qry += "       FROM   SYS.ALL_IND_COLUMNS                                                                           \n";
            qry += "       WHERE  TABLE_OWNER = ?                                                                               \n";
            qry += "       AND    INDEX_NAME IN (SELECT INDEX_NAME FROM SYS.ALL_INDEXES                                         \n";
            qry += "                             WHERE  TABLE_OWNER = ?                                                         \n";
            qry += "                             AND    UNIQUENESS = 'UNIQUE')) C                                               \n";
            qry += "WHERE  A.OWNER = B.OWNER                                                                                    \n";
            qry += "AND    A.TABLE_NAME = B.TABLE_NAME                                                                          \n";
            qry += "AND    A.COLUMN_NAME = B.COLUMN_NAME                                                                        \n";
            qry += "AND    A.TABLE_NAME = C.TABLE_NAME(+)                                                                       \n";
            qry += "AND    A.COLUMN_NAME = C.COLUMN_NAME(+)                                                                     \n";
            qry += "AND    A.OWNER = ?                                                                                          \n";
            qry += "ORDER BY A.TABLE_NAME,A.COLUMN_ID                                                                           \n";
            
            //System.out.println(qry);
            //stmt = conn.createStatement();
            //rs = stmt.executeQuery(qry);
            
            //pstmt = conn.prepareStatement(qry);
            
            pstmt = new LoggableStatement(conn,qry);
            pstmt.setString(1, params.get("datasource"));
            pstmt.setString(2, params.get("dbOwner"));
            pstmt.setString(3, params.get("dbOwner"));
            pstmt.setString(4, params.get("dbOwner"));


            
            
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
                map.put("NULLABLE"      ,rs.getString("NULLABLE"  ));
                map.put("PK"            ,rs.getString("PK"  ));
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
        } finally { if(conn != null) try { conn.close();} catch(Exception ee){}}
        
        return listRs;
    }


    
    @Override
    public List<Map<String, Object>> selectMetaTabList(Map<String, String> params) {
        Connection conn=null; PreparedStatement pstmt=null; ResultSet rs=null; String qry="";
        
        List<Map<String, Object>> listRs = new ArrayList<Map<String, Object>>();
        
        try {
            DbConnection dbConn = new DbConnection();
            conn = dbConn.getConnection(params);

            //원본쿼리 : net.pmosoft.pony.dams.table.TabMariadbDao.xml - insertMetaTabColList
            qry  = "--                                                 \n";
            qry += "SELECT                                             \n";
            qry += "       ?                      AS DB_NM             \n";
            qry += "     , ?                     AS OWNER             \n";
            qry += "     , UPPER(A.NAME)          AS TAB_NM            \n";
            qry += "     , ''                     AS TAB_HNM           \n";
            qry += "     , ''                     AS TAB_DESC          \n";
            qry += "       ,strftime('%Y%m%dH%M%S','now','localtime') AS REG_DTM    \n";
            qry += "       ,''                                          AS REG_USR_ID \n";
            qry += "       ,strftime('%Y%m%dH%M%S','now','localtime') AS UPD_DTM    \n";
            qry += "       ,''                                          AS UPD_USR_ID \n";
            qry += "FROM   SQLITE_MASTER A                            \n";
            qry += "WHERE  TYPE = 'table'                             \n";
            qry += "ORDER BY A.NAME                                   \n";
            System.out.println(qry);
            
            pstmt = new LoggableStatement(conn,qry);
            pstmt.setString(1, params.get("datasource"));
            pstmt.setString(2, params.get("dbOwner"));
            
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
                map.put("REG_DTM"       ,rs.getString("REG_DTM"     ));
                map.put("REG_USR_ID"    ,rs.getString("REG_USR_ID"  ));
                map.put("UPD_DTM"       ,rs.getString("UPD_DTM"     ));
                map.put("UPD_USR_ID"    ,rs.getString("UPD_USR_ID"  ));
                
                listRs.add(map);
            }
            
            
        } catch (Exception e) { e.printStackTrace();
        } finally { if(conn != null) try { conn.close();} catch(Exception ee){}}
        
        return listRs;
    }

    
}

