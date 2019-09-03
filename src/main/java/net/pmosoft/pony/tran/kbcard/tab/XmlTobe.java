package net.pmosoft.pony.tran.kbcard.tab;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.pmosoft.pony.comm.util.FileUtil;

public class XmlTobe {
    
    // common
    
    // dir
    String srcPathFileNm = "";
    String tarPathFileNm = "";
    String srcPath = "";
    String tarPath = "";
    
    String matchFile = "";
    ArrayList fileNmList = new ArrayList();
    
    // file
    String screenNm = "";
    String screenTitle = "";
    String fileNm = "";
    String src;
    
    ArrayList<String> srcList = new ArrayList<String>();
    ArrayList<String> tarList = new ArrayList<String>();
    
    // qry
    ArrayList<TabNmVo> tabNmList = new ArrayList<TabNmVo>();
    ArrayList<ColNmVo> colNmList = new ArrayList<ColNmVo>();

    public static void main(String[] args) {
        XmlTobe extract = new XmlTobe();
        extract.executeFile();
        //extract.executeDir();
    }

    /*
     * 단일파일 처리
     * */
    void executeFile() {
        srcPathFileNm = "aa.xml";
        tarPathFileNm = "bb.xml";
        fileToString(srcPathFileNm);
        qryTabNm();
        qryColNm();
        tranFile(tarPathFileNm);
        stringToFile(tarPathFileNm);
    }
    
    /*
     * 폴더내 다수 파일 처리
     * */
    void executeDir() {
        // 디렉토리 정보
        srcPath = "c:";
        tarPath = "c:";
        dirToList();
        qryTabNm();
        qryColNm();
        System.out.println("fileNmList.size()="+fileNmList.size());
        
        // 해당파일별 파싱 처리
        for (int i = 0; i < fileNmList.size(); i++) {
            System.out.println(i);
            fileToString(srcPath+fileNmList.get(i).toString());
            tranFile(srcPath+fileNmList.get(i).toString());
            stringToFile(tarPath+fileNmList.get(i).toString());
        }
        
        System.out.println("End");
    }

    /*
     * [1단계] 폴더내에 파일정보를 리스트에 저장
     * */
    void dirToList() {
        FileUtil fileUtil = new FileUtil();
        
        List<HashMap<String,String>> al = fileUtil.dirFileInfo(srcPath, matchFile, fileNmList);
        for (int i = 0; i < al.size(); i++) {
            //System.out.println(al.get(i).get("fileNm"));
            fileNmList.add(al.get(i).get("fileNm"));
        }
    }

    /*
     * [2단계] 파일을 읽어서 String으로 변환
     * */
    void fileToString(String pathFileNm) {
        src = FileUtil.fileToString(pathFileNm);
    }
    
    /*
     * [3단계] 테이블명 맵핑정보를 리스트로 생성
     * */
    void qryTabNm() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String DB_URL = "jdbc:hsqldb:hsql://localhost/testdb";
        String DB_USER = "sa";
        String DB_PASSWORD = "";
       
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT AS_TAB_NM, TO_TAB_NM FROM 테이블맵핑 WHERE AS_TAB_NM <> TO_TAB_NM";          
            rs = stmt.executeQuery(query);
            while (rs.next()) { 

                tabNmList.add(new TabNmVo(rs.getString("AS_TAB_NM"),rs.getString("TO_TAB_NM")));
            }
            
        } catch ( Exception e ) { e.printStackTrace(); } finally {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
    }

    /*
     * [3단계] 테이블명 맵핑정보를 리스트로 생성
     * */
    void qryColNm() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String DB_URL = "jdbc:hsqldb:hsql://localhost/testdb";
        String DB_USER = "sa";
        String DB_PASSWORD = "";
       
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT AS_COL_NM, TO_COL_NM FROM 테이블맵핑 WHERE AS_COL_NM <> TO_COL_NM";          
            rs = stmt.executeQuery(query);
            while (rs.next()) { 

                colNmList.add(new ColNmVo(rs.getString("AS_COL_NM"),rs.getString("TO_COL_NM")));
            }
            
        } catch ( Exception e ) { e.printStackTrace(); } finally {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
    }
    
    /*
     * [4단계] TOBE 테이블명, 컬럼명으로 변경
     * */
    void tranFile(String pathFileNm) {
        
        String asTabNm = "";
        String toTabNm = "";
        String asColNm = "";
        String toColNm = "";
        
        try {
            System.out.println(pathFileNm);
            
            for (int i = 0; i < tabNmList.size(); i++) {
                asTabNm = tabNmList.get(i).getAsTabNm();
                toTabNm = tabNmList.get(i).getToTabNm();
                if(asTabNm.trim().length()>1 && toTabNm.trim().length()>1){
                    src = src.replace(asTabNm,  toTabNm);
                }
            }
            
            for (int i = 0; i < colNmList.size(); i++) {
                asColNm = colNmList.get(i).getAsColNm();
                toColNm = colNmList.get(i).getToColNm();
                
                if(asColNm.trim().contains("이메일주소")) continue;
                if(asColNm.trim().contains("폐쇄년월일")) continue;
                if(asColNm.trim().contains("개점년월일")) continue;
                
                if(asColNm.trim().length()>1 && toColNm.trim().length()>1){
                    src = src.replace(asColNm,  toColNm);
                }
            }
            
            System.out.println(src);
            
        }  catch(Exception e){}
        
    }
    
    /*
     * [5단계] TOBE 파일로 생성테이블명, 컬럼명으로 변경
     * */
    void stringToFile(String pathFileNm) {
        new File(pathFileNm).delete();
        FileUtil.stringToFile(src, pathFileNm);
    }
}