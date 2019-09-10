/*******************************************************************************
@title:테이블DAO를 마리아DB로 구현 
@description-start
@description-end  
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
import java.util.List;
import java.util.Map;

import net.pmosoft.pony.comm.db.DbCon;
import net.pmosoft.pony.comm.db.LoggableStatement;

    
public class TabCommonDao implements TabDaoFactory {

    /*****************************************************************************
     *                              테이블 정보
     *****************************************************************************/
    @Override
    public List<Map<String, Object>> selectMetaTabColList(
            Map<String, String> params) {
        
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Map<String, Object>> selectMetaTabList(
            Map<String, String> params) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<Map<String, Object>> selectQryColInfo(Map<String, String> params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String selectCreateTabScript(Map<String, String> params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String selectDropTabScript(
            Map<String, String> params) {
        // TODO Auto-generated method stub
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
     *                                 쿼리
     *****************************************************************************/
    /*
     * 테이블 데이터를 리스트로 리턴
     * */
    public List<Map<String, Object>> selectTabData(Map<String, String> params) {
        Connection conn=null; PreparedStatement pstmt=null; ResultSet rs=null; String qry="";
        
        List<Map<String, Object>> listRs = new ArrayList<Map<String, Object>>();
        
        try {
            DbCon dbConn = new DbCon();
            conn = dbConn.getConnection(params);
            qry  = "SELECT  * FROM " + params.get("TAB_NM") + " \n";
            //qry += "WHERE                                   \n";
            //System.out.println(qry);
            pstmt = new LoggableStatement(conn,qry);
            pstmt.setString(1, params.get("datasource"));
            //System.out.println(((LoggableStatement)pstmt).getQueryString() + "\n");
            rs = pstmt.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            
            int colCnt = rsmd.getColumnCount();
            System.out.println("colCnt="+colCnt);
            
            //for (int i = 0; i < colCnt; i++) {
            //    System.out.println(rsmd.getColumnName(i+1));
            //}
            
            //System.out.println(((LoggableStatement)pstmt).getQueryString() + "\n");
            rs = pstmt.executeQuery();
            
            /***********************************************************************************
             *                            테이블 데이터를 리스트 
             **********************************************************************************/
            while(rs.next()){
                HashMap<String, Object> map = new HashMap<String, Object>();
                
                for (int i = 0; i < colCnt; i++) {
                    map.put(rsmd.getColumnName(i+1) ,rs.getString(i+1));
                    //if(i==0) System.out.println(rsmd.getColumnName(i+1));
                }
                listRs.add(map);
            }
            
        } catch (Exception e) { e.printStackTrace();
        } finally { if(conn != null) try { pstmt.close(); conn.close();} catch(Exception ee){}}
        
        return listRs;
    }

    /*
     * 쿼리 데이터를 리스트로 리턴
     * */
    public List<Map<String, Object>> selectQryData(Map<String, String> params) throws Exception {
        Connection conn=null; PreparedStatement pstmt=null; ResultSet rs=null; String qry="";
        
        List<Map<String, Object>> listRs = new ArrayList<Map<String, Object>>();

        try {
            
            /****************************
             * DB접속하여 RS 수보
             ****************************/
            DbCon dbConn = new DbCon();
            conn = dbConn.getConnection(params);
            qry  = params.get("qry");
            //qry  = "SELECT PKG_FUL_NM FROM pony.TDACM00010 \n";
            pstmt = new LoggableStatement(conn,qry);
            pstmt.setString(1, params.get("datasource"));
            System.out.println(((LoggableStatement)pstmt).getQueryString() + "\n");
            rs = pstmt.executeQuery();
            
            /****************************
             * 메타정보 수보
             ****************************/
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCnt = rsmd.getColumnCount();
            for (int i = 0; i < colCnt; i++) {
                System.out.println(rsmd.getColumnName(i+1));
            }
            System.out.println(((LoggableStatement)pstmt).getQueryString() + "\n");
            rs = pstmt.executeQuery();
            
            /***********************************************************************************
             *                            테이블 데이터를 리스트 
             **********************************************************************************/
            while(rs.next()){
                HashMap<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < colCnt; i++) {
                    //map.put(rsmd.getColumnName(i+1) ,rs.getString(i+1));
                    map.put(rsmd.getColumnLabel(i+1) ,rs.getString(i+1));
                    if(i==0) System.out.println(rsmd.getColumnName(i+1));
                }
                listRs.add(map);
            }
            
        } catch (Exception e) { 
            e.printStackTrace();
            throw e;
        } finally { if(conn != null) try { pstmt.close(); conn.close();} catch(Exception ee){}}
        
        return listRs;
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

 
    /*
     * 테이블 데이터를 INSERT문장으로 만들어 String으로 리턴
     * */
    public String selectInsertData(Map<String, String> params) throws Exception {
        Connection conn=null; PreparedStatement pstmt=null; ResultSet rs=null; String qry="";
        String insertDataList = "";
        
        try {
            /****************************
             * DB접속하여 RS 수보
             ****************************/
            DbCon dbConn = new DbCon();
            conn = dbConn.getConnection(params);
            qry  = "SELECT  * FROM " + params.get("TAB_NM") + " \n";
            //System.out.println(qry);
            pstmt = new LoggableStatement(conn,qry);
            pstmt.setString(1, params.get("datasource"));
            //System.out.println(((LoggableStatement)pstmt).getQueryString() + "\n");
            rs = pstmt.executeQuery();
            
            /****************************
             * 메타정보 수보
             ****************************/
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCnt = rsmd.getColumnCount();
            System.out.println("colCnt="+colCnt);

            
            /***********************************************************************************
             *                              insert 문장 생성
             **********************************************************************************/
            String insertHeader = "INSERT INTO " + params.get("dbOwner") + "." + params.get("TAB_NM") + " VALUES (";
            String insertData = "";

            String ColumnValue = "";
            while(rs.next()){
                //HashMap<String, Object> map = new HashMap<String, Object>();
                
                for (int i = 0; i < colCnt; i++) {

                    ColumnValue = rs.getString(i+1);
                    if (isChar(rsmd.getColumnTypeName(i+1))) {
                        //ColumnValue = fromRs.getString(i + 1).replace("null", "").replace("NULL", "");
                        ColumnValue = (ColumnValue != null)?ColumnValue:"";
                        insertData += "'" + ColumnValue;
                        insertData += (i < colCnt - 1) ? "'," : "');";
                        //System.out.println("insertData1="+insertData);
                    /********************************************************************************
                     * [DATE] DBMS 및 site에 따라 date 형식을 처리하는 것이 상이하므로 별도로 처리요 
                     ********************************************************************************/
                    } else if (isDate(rsmd.getColumnTypeName(i+1))) {
                        insertData += "SYSDATE";
                        insertData += (i < colCnt - 1) ? "," : ");";
                    } else {                        
                        ColumnValue = (ColumnValue.trim() != null)?ColumnValue:"0";
                        //System.out.println("333="+ColumnValue);

                        insertData += ColumnValue;
                        insertData += (i < colCnt - 1) ? "," : ");";
                        //System.out.printlninsertData"insertData3="+insertData);
                    }
                }
                insertDataList += insertHeader + insertData +"\n"; 
                insertData = "";
            }
            
        } catch (Exception e) { e.printStackTrace();
        } finally { if(conn != null) try { pstmt.close(); conn.close();} catch(Exception ee){}}
        
        return insertDataList;
    }


    public boolean isChar(String dataType) {
        boolean TF = true;
        if (dataType.equals("CHAR") || dataType.equals("VARCHAR")
                || dataType.equals("VARCHAR2"))
            TF = true;
        else
            TF = false;
        //System.out.println("isChar="+TF+" dataType="+dataType);
        return TF;
    }

    public boolean isDate(String dataType) {
        boolean TF = true;
        if (dataType.equals("DATE") || dataType.equals("TIMESTAMP"))
            TF = true;
        else
            TF = false;
        
        //System.out.println("isDate="+TF+" dataType="+dataType);
        
        return TF;
    }



}

