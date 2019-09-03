package net.pmosoft.pony.gens.teos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.pmosoft.pony.comm.excel.ExcelSrv;
import net.pmosoft.pony.comm.util.FileUtil;

public class ExtractTabInfoFromSrc {
    // common

    // dir
    String dir = "";
    String matchFile = "";
    //ArrayList<String> fileInfoList = new ArrayList<String>();
    List<HashMap<String, String>> fileInfoList = new ArrayList<HashMap<String, String>>();
    // file
    String pathFileNm = "";
    String pathNm = "";
    String fileNm = "";
    String src;

    // table
    ArrayList<String> tabNmList = new ArrayList<String>();


    // result
    //ArrayList<SrcTabVo> srcTabInfoList = new ArrayList<SrcTabVo>();
    List<Map<String,String>> srcTabInfoList = new ArrayList<Map<String,String>>();

    public static void main(String[] args) {
        ExtractTabInfoFromSrc extract = new ExtractTabInfoFromSrc();
        //extract.executeFile();
        extract.executeDir();
    }

    /*
     * 단일파일 처리
     * */
    void executeFile() {
        pathNm = "D:/fframe/workspace/asis/src/CellPlanCommon/Analysis/"; fileNm = "CDBAnalysisResult.cpp";
        pathFileNm = pathNm + fileNm;
        qryTabNm();
        fileToString(pathFileNm);
        extractTabInfo(pathFileNm);
        ToExcel();
        //ToDB();
    }

    /*
     * 폴더내 다수 파일 처리
     * */
    void executeDir() {
        // 디렉토리 정보
        dir = "D:/fframe/workspace/asis/src/"; matchFile = ".*cpp";
        dirToList();

        //Pattern p; Matcher m;
        //String src2 = "aaa 한글 bb";
        //p = Pattern.compile("[ㄱ-ㅎ가-힣]+"); m=p.matcher(src2); m.find();
        //System.out.println("m.group()="+m.group());

        System.out.println("fileInfoList.size()="+fileInfoList.size());

        // 해당파일별 파싱 처리
        for (int i = 0; i < fileInfoList.size(); i++) {
            System.out.println(i);
            fileToString(fileInfoList.get(i).get("pathFileNm")+fileInfoList.get(i).get("fileName"));
            extractTabInfo(dir+fileInfoList.get(i).toString());
        }
        ToExcel();

        System.out.println("End");

    }

    /*
     * [1단계] 폴더내에 파일정보를 리스트에 저장
     * */
    void dirToList() {
        FileUtil.dirFileInfo(dir, matchFile, fileInfoList);
    }

    /*
     * [2단계] 파일을 읽어서 스트링으로 변환
     * */
    void fileToString(String pathFileNm) {
        src = FileUtil.fileToString(pathFileNm,"euc-kr");
        //System.out.println("src==="+src);
    }

    /*
     * [3단계] 테이블명 맵핑정보를 리스트로 생성
     * */
    void qryTabNm() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String DB_URL = "jdbc:sqlite:C:/pony/pony.db";
        String DB_USER = "";
        String DB_PASSWORD = "";

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT TAB_NM FROM TDACM00080 WHERE UPPER(JDBC_NM) = 'EOS_DEV'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                //System.out.println(rs.getString("TAB_NM"));
                tabNmList.add(rs.getString("TAB_NM"));
            }
        } catch ( Exception e ) { e.printStackTrace(); } finally {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
    }


    /*
     * [4단계] 소스에서 테이블 추출
     * */
    void extractTabInfo(String pathFileNm) {
        System.out.println("extractTabInfo start");
        //Map<String, String> map = new HashMap<String, String>();
        try {
            System.out.println(pathFileNm);
            System.out.println("tabNmList.size()==="+tabNmList.size());
            for (int i = 0; i < tabNmList.size(); i++) {
                if(src.contains(tabNmList.get(i))) {
                    Map<String, String> map = new HashMap<String, String>();
                    //map.put(tabNmList.get(i),pathFileNm);
                    map.put("fileNm",pathFileNm);
                    map.put("tabNm",tabNmList.get(i));
                    //map.put("aa", "bb");
                    srcTabInfoList.add(map);
                    System.out.println(tabNmList.get(i));
                }
            }


        }  catch(Exception e){ e.printStackTrace(); }

        System.out.println("extractTabInfo end");

    }

    /*
     * [5단계] 엑셀로 출력
     * */
    void ToExcel() {
        ExcelSrv excel = new ExcelSrv();
        excel.downloadExcel(srcTabInfoList,"srcTabInfo.xls");
    }

    /*
     * [5단계] DB로 저장
     * */
    void ToDB() {
        for (int i = 0; i < srcTabInfoList.size(); i++) {
            System.out.println(srcTabInfoList.get(i).get(pathFileNm));

        }
    }

}