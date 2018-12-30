package net.pmosoft.pony.gens.pgm.tmpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.pmosoft.pony.comm.util.FileUtil;
import net.pmosoft.pony.comm.util.StringUtil;

 
public class GensPgmByTmpl {

    /******************************************
     * 글로벌 프로퍼티
     *****************************************/
    String packBascNm = "net.pmosoft.pony";                           //회사명
    String prjNm = "pony";                                            //프로젝트명
    String srcBascPathNm = "c:/pony/workspace/pony/src/main";       //소스기본경로
    String javaBascPathNm = srcBascPathNm + "/java/net/pmosoft/pony"; //자바기본경로
    String webBasePathNm = srcBascPathNm + "/webapp";                   //웹기본경로
    String tmplBascPathNm = javaBascPathNm + "/gens/pgm/tmpl/sample";   //템플릿기본경로

    /******************************************
     * 입력 파라미터
     *****************************************/
    String tmplCd    = ""; //탬플릿유형(예:grid01)
    String packNm    = ""; //패키지명(예:net.pmosoft.pony.gens.test)
    String genPackNm = ""; //회사명이 배제된 패키지명(예:gens.test)
    String genPathNm = ""; //회사명이 배제된 폴더명(예:gens/test)
        
    String varNm = ""; //변수명(예:testTmpl)       
    String pgmNm = ""; //파일명(예:TestTmpl)       

    String tmplCdPathNm = ""; //템플릿타입별경로(예:extjs/grid01, spring/restful)    
    String renameTmplNm = ""; //템플릿파일명중변경되어야할명칭(예:Grid01Controoler.js.tmpl <== Grid01)    

    boolean  isFrontExe = true;
    boolean  isBackExe = true;
    
    
    /******************************************
     * 기타 파라미터
     *****************************************/
    boolean isFront = false;
    boolean isBack = false;
    
    public static void main(String[] args) {
        GensPgmByTmpl gensPgmByTmpl = new GensPgmByTmpl();

        Map<String, String> params = new HashMap<String, String>();
        params.put("tmplCd", "grid01");
        params.put("packNm", "net.pmosoft.pony.gens.test");
        params.put("pgmNm" , "testTmpl");
        gensPgmByTmpl.createPgmFile(params);
    }


    /******************************************
     * 1단계 : 입력파라미터 세팅
     *****************************************/
    public void createPgmFile(Map<String,String> params){

         System.out.println("params="+params); 
        
        
        /******************************************
         * 입력 파라미터
         *****************************************/
        tmplCd = params.get("tmplCd");                 //탬플릿유형(예:grid01)

        packNm = params.get("packNm");                 //패키지명(예:net.pmosoft.pony.gens.test)
        genPackNm = packNm.replace(packBascNm+".",""); //회사명이 배제된 패키지명(예:gens.test) 
        genPathNm = genPackNm.replace(".","/");        //회사명이 배제된 폴더명  (예:gens/test) 
        varNm = StringUtil.replaceFirstCharLowerCase(params.get("pgmNm")); //변수명(예:testTmpl)       
        pgmNm = StringUtil.replaceFirstCharUpperCase(params.get("pgmNm")); //파일명(예:TestTmpl)       

        
        /******************************************
         * 옵션 파라미터
         *****************************************/
        isFrontExe = (params.get("isFrontExe").equals(("true")))?true:false;
        isBackExe  = (params.get("isBackExe").equals(("true")))?true:false;
        

        if(isFrontExe) {
            
            //System.out.println("create front");
            
            /******************************************
             * Front-End 파일 생성
             *****************************************/
            setFrontBack("front");
            tmplCdPathNm = "extjs/"+tmplCd; renameTmplNm = StringUtil.replaceFirstCharUpperCase(tmplCd);
            createPgmFile(webBasePathNm); 
        }
        
        if(isBackExe) {
            
            //System.out.println("create back");
            
            /******************************************
             * Back-End 파일 생성
             *****************************************/
            setFrontBack("back");            
            tmplCdPathNm = "spring/restful"; renameTmplNm = "SpringRest";
            createPgmFile(javaBascPathNm);
        }    
    }
 
    /******************************************
     * 프론트 인지 백인지 세팅
     *****************************************/
    private void setFrontBack(String str){
        if(str.equals("front")) {isFront = true; isBack = false; }
        else { isFront = false; isBack = true; }
    }

    /******************************************
     * 2단계 : 템플릿 파일 로딩 
     *****************************************/
    public void createPgmFile(String tarBasePathNm){
 
        String tmplPathNm = "";
        String tmplFileNm = "";
 
        String tarPathNm = "";
        String tarFileNm = "";
        
        tmplPathNm = tmplBascPathNm+"/"+tmplCdPathNm;
        tarPathNm = tarBasePathNm +"/"+ genPathNm;
 
        System.out.println("tmplPathNm="+tmplPathNm);
        System.out.println("tarPathNm="+tarPathNm);
            
        File dir = new File(tmplPathNm);
        File[] fileList = dir.listFiles();
 
        for (int i = 0; i < fileList.length; i++) {
            //System.out.println("tmplpgmNm="+tmplFileNm);
            
            File file = fileList[i];
            tmplFileNm = file.getName();
            //System.out.println("tmplpgmNm="+tmplFileNm);
            tarFileNm = tmplFileNm.replace(renameTmplNm,pgmNm).replace(".tmpl", "");
            //System.out.println("tms 
 
            //System.out.println("tmplPathNm/tmplFileNm="+tmplPathNm+"/"+tmplFileNm);
            //System.out.println("tarPathNm/tarFileNmm="+tarPathNm+"/"+tarFileNm);
            replaceTmplFileToTarPgmFile(tmplPathNm,tmplFileNm,tarPathNm,tarFileNm);
        }
    }

    /******************************************
     * 3단계 : 프로그램 파일 생성  
     *****************************************/
    public void replaceTmplFileToTarPgmFile(String inPathNm, String inFileNm, String outPathNm, String outFileNm) {
 
        try {
            
            if(!FileUtil.fileIsLive(outPathNm+"/"+outFileNm)) {

                FileUtil.makeDir(outPathNm);
                FileUtil.fileDelete(outPathNm+"/"+outFileNm);
                
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inPathNm+"/"+inFileNm)));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outPathNm+"/"+outFileNm, true));
     
                String line = "";
                while(((line = br.readLine()) != null)) {
                    //System.out.println("line="+line);
                    line = exeReplaceRule(line);
                    bw.write(line+"\n");
                }
                bw.flush();
                bw.close();           
                br.close();
            }    
        } catch (Exception e) {
            System.out.println("e=" + e.getMessage());
        }
    }    
 

    /***********************************************
     * 템플릿 문자열 변경 : 프론트인지 백인지 선택  
     ***********************************************/
    public String exeReplaceRule(String line) {
        if(isFront) line = exeExtjsReplaceRule(line);
        if(isBack)  line = exeSpringRestfulReplaceRule(line);
        return line;
    }    
 
    /******************************************
     * 템플릿 문자열 변경 : Extjs  
     *****************************************/
    public String exeExtjsReplaceRule(String line) {
        
        line = line.replace("$extjsPackNm$",prjNm+"."+genPackNm); //ex:pony.gens.test.TmplPgmRegView
        line = line.replace("$PgmNm$",pgmNm);                //ex:pony.gens.test.TmplPgmRegView
        line = line.replace("$pgmNm$",varNm);                //ex:TmplPgmRegView tmplPgmRegView = ..
        line = line.replace("$restfulPathName$",genPathNm);   //ex:/gens/test 
        
        return line;
    }    

    /******************************************
     * 템플릿 문자열 변경 : 스프링  
     *****************************************/
    public String exeSpringRestfulReplaceRule(String line) {
        line = line.replace("$packNm$",packNm);     //ex:pony.gens.test.TmplPgmRegView
        line = line.replace("$PgmNm$",pgmNm);  //ex:pony.gens.test.TmplPgmRegView
        line = line.replace("$pgmNm$",varNm);  //ex:TmplPgmRegView tmplPgmRegView = ..
        line = line.replace("$genPathNm$",genPathNm); //ex:pony.gens.test.TmplPgmRegView
        
        return line;
    }    
}
