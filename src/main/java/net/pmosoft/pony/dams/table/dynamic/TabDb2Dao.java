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

import net.pmosoft.pony.comm.db.DbConnection;
import net.pmosoft.pony.comm.db.LoggableStatement;

    
public class TabDb2Dao extends TabCommonDao implements TabDaoFactory {

/*

 
WITH TEMP01 AS (
SELECT A.TBCREATOR
,A.TBNAME
,TRIM(A.TBCREATOR)||'.'||A.TBNAME AS OWNER_TAB_NM
,B.REMARKS AS TAB_HNM
,A.COLNO
,A.NAME AS COL_NM
,A.REMARKS AS COL_HNM
,A.COLTYPE
,CASE WHEN A.COLTYPE IN ('CHAR','VARCHAR','VARCHAR2') THEN TRIM(A.COLTYPE)||'('||A.LENGTH||')'
      WHEN A.COLTYPE IN ('DECIMAL') THEN TRIM(A.COLTYPE)
      WHEN A.COLTYPE IN ('TIMESTMP') THEN 'TIMESTAMP'
      WHEN A.COLTYPE IN ('DATE','TIMESTAMP') THEN TRIM(A.COLTYPE)
      ELSE A.COLTYPE
 END AS DATA_TYPE2     
,A.NULLS
,A.KEYSEQ
,A.DEFAULT
FROM SYSIBM.SYSCOLUMNS A,
     SYSIBM.SYSTABLES B
WHERE A.TBNAME = B.NAME AND A.TBCREATOR = B.CREATOR
AND B.NAME IN (SELECT TBNAME FROM SYSIBM.SYSCOLUMNS WHERE TRCREATOR IN ('NAML') AND TBNAME LIKE UPPER('STR_%) AND NAME = 'TRAN_SEQ')
AND A.TBCREATOR IN ('NAML')
ORDER BY A.TBNAME, A.COLNO
-----------------------
-- 테이블 정보
-----------------------
), TEMP02 AS (
    SELECT DISTINCT
           A.TBCREATOR
         , A.TBNAME
         , A.OWNER_TAB_NM
         , A.TAB_HNM
    FROM   TEMP01 A
----------------------------------
-- RENAME TABLE RENAME 문장 생성
----------------------------------
), TEMP021 AS (
    SELECT DISTINCT
           OWNER_TAB_NM
         , -100 RNUM
         , 'RENAME '||OWNER_TAB_NM||' TO '||TBNAME||'_T1;' C1
    FROM   TEMP02       
----------------------------------
-- DROP TABLE 문장 생성
----------------------------------
), TEMP03 AS (
    SELECT DISTINCT
           OWNER_TAB_NM
         , -10 RNUM
         , 'DROP TABLE '||OWNER_TAB_NM||';' C1
    FROM   TEMP02       
----------------------------------
-- CREATE TABLE 문장 생성
----------------------------------
), TEMP04 AS (
    SELECT 
           OWNER_TAB_NM
         , -5 RNUM
         , 'CREATE TABLE '||OWNER_TAB_NM||'(' AS C1
    FROM   TEMP02 A      
), TEMP05 AS (
    SELECT 
           OWNER_TAB_NM
         , COLNO RNUM
         , '    '|| A.COL_NM
         , '    '|| A.DATA_TYPE2
         , '    '|| DECODE(A.NULLS,'N','NOT NULL','NULL')||' '
         , '    '|| CASE WHEN A.NULLS IS NULL OR A.COLTYPE IN ('DATE','TIMESTMP') THEN '' ELSE DECODE(A.NULLS,'N',' WITH DEFAULT '||NVL(DEFAULT,''),'') END
         , ',' AS C1
    FROM   TEMP01 A      
----------------------------------
-- PRIMARY KEY 문장 생성
----------------------------------
), TEMP06 AS (
    SELECT 
           OWNER_TAB_NM
         , RNUM
         , SUBSTR(C1,1,LENGTH(C1)-1) C1         
    FROM  (
           SELECT OWNER_TAB_NM      
                , 500 RNUM
                , '    PRIMARY KEY (' ||
                  NVL(MAX(CASE WHEN PK_NUM = 1 THEN A.COL_NM||',' ELSE NULL END),'') ||
                  NVL(MAX(CASE WHEN PK_NUM = 2 THEN A.COL_NM||',' ELSE NULL END),'') ||
                  NVL(MAX(CASE WHEN PK_NUM = 3 THEN A.COL_NM||',' ELSE NULL END),'') ||
                  NVL(MAX(CASE WHEN PK_NUM = 4 THEN A.COL_NM||',' ELSE NULL END),'') ||
                  NVL(MAX(CASE WHEN PK_NUM = 5 THEN A.COL_NM||',' ELSE NULL END),'') ||
                  NVL(MAX(CASE WHEN PK_NUM = 6 THEN A.COL_NM||',' ELSE NULL END),'') ||
                  NVL(MAX(CASE WHEN PK_NUM = 7 THEN A.COL_NM||',' ELSE NULL END),'') C1
           FROM  (
                  SELECT
                         A.OWNER_TAB_NM
                       , A.COL_NM
                       , ROW_NUMBER() OVER (PARTITION BY A.TBNAME ORDER BY A.TBNAME, A.COLNO) PK_NUM
                  FROM   TEMP01 A
                  WHERE  A.KEYSEQ > 0
                  ORDER BY A.OWNER_TAB_NM
                 ) A
           GROUP BY A.OWNER_TAB_NM              
          )
----------------------------------
-- TABLESPACE 문장 생성
----------------------------------
), TEMP07 AS (
    SELECT OWNER_TAB_NM
         , 600 RNUM
         , ')) IN NAML32_T01 INDEX IN NAML32_I01;' C1         
    FROM   TEMP02
----------------------------------
-- GRANT 문장 생성
----------------------------------
), TEMP08 AS (
    SELECT OWNER_TAB_NM
         , 700 RNUM
         , 'GRANT ALL ON '||OWNER_TAB_NM||' TO USER uaiss11;' C1         
    FROM   TEMP02
----------------------------------
-- TABLE COMMENT 문장 생성
----------------------------------
), TEMP09 AS (
    SELECT OWNER_TAB_NM
         , 800 RNUM
         , 'COMMENT ON TABLE '||OWNER_TAB_NM||' IS '''||TAB_HNM||''';' C1         
    FROM   TEMP02
----------------------------------
-- COLUMN COMMENT 문장 생성
----------------------------------
), TEMP10 AS (
    SELECT OWNER_TAB_NM
         , 1000+COLNO RNUM
         , 'COMMENT ON COLUMN '||OWNER_TAB_NM||'.'||COL_NM||' IS '''||COL_HNM||''';' C1    
    FROM   TEMP01
----------------------------------
-- 데이터 복원
----------------------------------
), TEMP11 AS (
    SELECT OWNER_TAB_NM
         , 2000 RNUM
         , 'INSERT INTO '||OWNER_TAB_NM||' SELECT ' C1    
    FROM   TEMP02
), TEMP12 AS (
    SELECT OWNER_TAB_NM
         , 2100+COLNO RNUM
         , DECODE(COLNO,0,' ',',')||COL_NM C1    
    FROM   TEMP01
), TEMP13 AS (
    SELECT OWNER_TAB_NM
         , 3000 RNUM
         , 'FROM '||OWNER_TAB_NM||'_T1' C1
    FROM   TEMP02
----------------------------------
-- 데이터 확인 조회
----------------------------------
), TEMP14 AS (
    SELECT OWNER_TAB_NM
         , 4000 RNUM
         , 'SELECT * FROM '||OWNER_TAB_NM||';' C1    
    FROM   TEMP02
), TEMP15 AS (
    SELECT OWNER_TAB_NM
         , 5000 RNUM
         , 'DROP TABLE '||OWNER_TAB_NM||'_T1;' C1    
    FROM   TEMP02
), TEMP16 AS (
    SELECT *
    FROM  (SELECT * FROM TEMP021 UNION ALL
           SELECT * FROM TEMP03 UNION ALL
           SELECT * FROM TEMP04 UNION ALL
           SELECT * FROM TEMP05 UNION ALL
           SELECT * FROM TEMP06 UNION ALL
           SELECT * FROM TEMP07 UNION ALL
           SELECT * FROM TEMP08 UNION ALL
           SELECT * FROM TEMP09 UNION ALL
           SELECT * FROM TEMP10 UNION ALL
           SELECT * FROM TEMP11 UNION ALL
           SELECT * FROM TEMP12 UNION ALL
           SELECT * FROM TEMP13 UNION ALL
           SELECT * FROM TEMP14 UNION ALL
           SELECT * FROM TEMP15
          ) A
    ORDER BY OWNER_TAB_NM, RNUM      
)
SELECT * FROM TEMP16
          
 * */

    /*****************************************************************************
     *                              테이블 정보
     *****************************************************************************/
    @Override
    public List<Map<String, Object>> selectMetaTabColList(Map<String, String> params) {

        Connection conn=null; PreparedStatement pstmt=null; ResultSet rs=null; String qry="";
        
        List<Map<String, Object>> listRs = new ArrayList<Map<String, Object>>();
        
        try {
            DbConnection dbConn = new DbConnection();
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
            qry += "       ,CASE WHEN A.COLUMN_KEY = 'PRI' THEN 'Y' ELSE '' END    AS PK                                                        \n";
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

            pstmt = new LoggableStatement(conn,qry);
            pstmt.setString(1, params.get("datasource"));
            pstmt.setString(2, params.get("dbOwner"));
            pstmt.setString(3, params.get("TAB_NM"));
            
            System.out.println(((LoggableStatement)pstmt).getQueryString() + "\n");
            rs = pstmt.executeQuery(); ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()){
                LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                for (int i = 0; i < rsmd.getColumnCount(); i++) 
                    map.put(rsmd.getColumnLabel(i+1) ,rs.getString(i+1)); 
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
            DbConnection dbConn = new DbConnection();
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
            rs = pstmt.executeQuery(); ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()){
                LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                for (int i = 0; i < rsmd.getColumnCount(); i++) 
                    map.put(rsmd.getColumnName(i+1) ,rs.getString(i+1)); 
                listRs.add(map);
            }
        } catch (Exception e) { e.printStackTrace();
        } finally { if(conn != null) try { pstmt.close(); conn.close();} catch(Exception ee){}}
        
        return listRs;
    }

    @Override
    public String selectDropTabScript(Map<String, String> params) {
        return "DROP TABLE "+params.get("dbOwner")+"."+params.get("TAB_NM")+";";
    }
    
    @Override
    public String selectCreateTabScript(Map<String, String> params) {
         
        List<Map<String, Object>> list = selectMetaTabColList(params);
         
        String c01 = "CREATE TABLE "+params.get("dbOwner")+"."+params.get("TAB_NM")+"(";
 
        String c02 = "";
        for (int i = 0; i < list.size(); i++) {
            c02 += list.get(i).get("COL_NM")+"\n";
        }
        
        System.out.println( c02 );
        String s1 = "";
        
        return null;
    }

    @Override
    public String selectTabCommentScript(
            Map<String, String> params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String selectColCommentScript(
            Map<String, String> params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String selectGrantUsrScript(
            Map<String, String> params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String selectIndexScript(
            Map<String, String> params) {
        // TODO Auto-generated method stub
        return null;
    }    
    
    /*****************************************************************************
     *                                 ETT
     *****************************************************************************/
    @Override
    public List<Map<String, Object>> selectCsvData(Map<String, String> params)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}

