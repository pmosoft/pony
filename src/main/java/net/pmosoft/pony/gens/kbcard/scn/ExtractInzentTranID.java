package net.pmosoft.pony.gens.kbcard.scn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.pmosoft.pony.comm.util.FileUtil;

public class ExtractInzentTranID {
    // common
    
    // dir
    String dir = "";
    String matchFile = "";
    ArrayList fileNmList = new ArrayList();
    ArrayList mciInfo = new ArrayList();
    
    // file
    String screenNm = "";
    String screenTitle = "";
    String fileNm = "";
    ArrayList src;

    public static void main(String[] args) {
        ExtractInzentTranID extract = new ExtractInzentTranID();
        //extract.executeFile();
        extract.executeDir();
    }

    /*
     * 단일파일 처리
     * */
    void executeFile() {
        this.fileNm = "C:/../PDZ72040900.scn";
        fileToList(this.fileNm);
        parseFile(this.fileNm);
        printMciInfo();        
    }
    
    /*
     * 폴더내 다수 파일 처리
     * */
    void executeDir() {
        // 디렉토리 정보
        dir = "C:/../Screen/"; matchFile = ".*scn";
        dirToList();
        
        //Pattern p; Matcher m;
        //String src2 = "aaa 한글 bb";
        //p = Pattern.compile("[ㄱ-ㅎ가-힣]+"); m=p.matcher(src2); m.find();
        //System.out.println("m.group()="+m.group());
        
        System.out.println("fileNmList.size()="+fileNmList.size());
        
        // 해당파일별 파싱 처리
        for (int i = 0; i < fileNmList.size(); i++) {
            System.out.println(i);
            fileToList(dir+fileNmList.get(i).toString());
            parseFile(dir+fileNmList.get(i).toString());
        }

        // 최종결과 출력
        //printMciInfo();
        
        // 최종결과 출력
        printInsertStat();
        
        System.out.println("End");
                
    }

    /*
     * [1단계] 폴더내에 파일정보를 리스트에 저장
     * */
    void dirToList() {
        FileUtil fileUtil = new FileUtil();
        
        List<HashMap<String,String>> al = fileUtil.dirFileInfo(dir, matchFile, fileNmList);
        for (int i = 0; i < al.size(); i++) {
            //System.out.println(al.get(i).get("fileNm"));
            fileNmList.add(al.get(i).get("fileNm"));
        }
    }

    /*
     * [2단계] 파일을 읽어서 리스트로 변환
     * */
    void fileToList(String fileNm) {
        FileUtil fileUtil = new FileUtil();
        src = fileUtil.fileToList(fileNm);
    }
    
    /*
     * [3단계] 파일내역을 파싱하여 화면별 MCI 정보 생성
     * */
    void parseFile(String fileNm) {
        
        String src2 = "";
        String src3 = "";
        String src4 = "";
        
        String matchStr = "";
        Pattern p; Matcher m;
        
        // screenNm
        System.out.println(fileNm);
        //p = Pattern.compile("[A-Z]{3}[0-9A-Z]{8}"); m=p.matcher(fileNm); m.find();
        //screenNm = m.group().toString();
        //System.out.println(screenNm);
        
        for (int i = 0; i < src.size(); i++) {
            src2 = src.get(i).toString().replace("\"", "'");
            
            if(src2.contains("BODY ScreenTitle=")){
                p = Pattern.compile("ScreenNo='[A-Z]{3}[0-9A-Z]{8}'"); m=p.matcher(src2); m.find();
                src3 = m.group();
                p = Pattern.compile("[A-Z]{3}[0-9A-Z]{8}"); m=p.matcher(src3); m.find();
                screenNm = m.group();

                //System.out.println("src2="+src2);
                p = Pattern.compile("ScreenTitle='[a-zA-Z[ㄱ-ㅎ가-힣0-9 /()]+'"); m=p.matcher(src2); m.find();
                src3 = m.group();
                p = Pattern.compile("^ScreenTitle='[a-zA-Z[ㄱ-ㅎ가-힣0-9 /()]+'"); m=p.matcher(src3); m.find();
                screenTitle = m.group().toString().replace(",", " ");
                //System.out.println("screenTitle ="+screenTitle =);
            }
            
            if(src2.contains("TranID TranId=")){
                p = Pattern.compile("ScreenNo='[A-Z]{3}[0-9A-Z]{5}[A-Z][0-9]"); m=p.matcher(src2); m.find();
                src3 = m.group();
                mciInfo.add(screenNm+","+screenTitle+','+src3);
            }
            
        }        
        
    }

    /*
     * [4단계] 최종 MCI 정보 출력
     * */
    void printMciInfo() {
        for (int i = 0; i < mciInfo.size(); i++) {
            System.out.println(mciInfo.get(i));
        }
    
    }
    
    /*
     * [5단계] 최종 MCI 정보 Insert문장으로 출력
     * */
    void printInsertStat() {
        System.out.println("--DELETE FROM 인젠트화면정보");
        for (int i = 0; i < mciInfo.size(); i++) {
            System.out.println("INSERT INTO 인젠트화면정보 VALUES ("+mciInfo.get(i).toString().replace(",", "','")+"')");
        }
    }
    
}