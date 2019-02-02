package net.pmosoft.pony.gens.pgm.copy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import net.pmosoft.pony.comm.util.FileUtil;
import net.pmosoft.pony.gens.pgm.GensPgm;


public class GensCommByCopy {

    /******************************************
     * 1단계 : 입력 param으로 gen시 필요param를 세팅
     *****************************************/
    public void setGenParam(GensPgm gens) throws Exception {
        try {
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
 
    /******************************************
     * 2단계 : 복사할 파일들 지정 
     *****************************************/
    public void genTarPgmFiles(GensPgm gens) throws Exception{
    }    
    /******************************************
     * 3단계 : 타겟 프로그램 리팩토링 생성  
     *****************************************/
    public void replaceSrcPgmToTarPgm(String srcPathNm, String srcFileNm, String tarPathNm, String tarFileNm, GensPgm gens) throws Exception {
        
        String retMsg = "";
        
        try {

            if(!FileUtil.fileIsLive(srcPathNm+"/"+srcFileNm)) {
                //throw new Exception("소스 파일이 존재하지 않습니다("+srcPathNm+"/"+srcFileNm+")");
                retMsg = "소스 파일이 존재하지 않습니다("+srcPathNm+"/"+srcFileNm+")";
                gens.setRetErrMsg(gens.getRetErrMsg()+"\n"+retMsg);
                System.out.println(retMsg);
                return;
            }    
            
            //if(!FileUtil.fileIsLive(tarPathNm+"/"+tarFileNm)) {

                
                FileUtil.makeDir(tarPathNm);
                FileUtil.fileDelete(tarPathNm+"/"+tarFileNm);
                
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(srcPathNm+"/"+srcFileNm)));
                BufferedWriter bw = new BufferedWriter(new FileWriter(tarPathNm+"/"+tarFileNm, true));
     
                String line = "";
                while(((line = br.readLine()) != null)) {
                    //System.out.println("line="+line);
                    line = replaceRule(line,gens);
                    bw.write(line+"\n");
                }
                bw.flush();
                bw.close();           
                br.close();

                gens.setRetSrcMsg(gens.getRetSrcMsg()+"\n"+srcPathNm+"/"+srcFileNm);
                gens.setRetTarMsg(gens.getRetTarMsg()+"\n"+tarPathNm+"/"+tarFileNm);

            //}
            //return gens;

        } catch (Exception e) {
            System.out.println("Exception=" + e.getMessage());
            e.printStackTrace(); 
            throw e;
        }
    }      
    
    /******************************************
     * 4단계 : 리팩토링 룰  
     *****************************************/
     public String replaceRule(String line, GensPgm gens) { return null;}
   
//
//    /******************************************
//     * 템플릿 문자열 변경 : 스프링  
//     *****************************************/
//    public String exeSpringRestfulReplaceRule(String line) {
//        line = line.replace("$tarPackNm$",tarPackNm);     //ex:pony.gens.test.TmplPgmRegView
//        line = line.replace("$tarPgmNm$",tarPgmNm);  //ex:pony.gens.test.TmplPgmRegView
//        line = line.replace("$tarPgmNm$",tarVarNm);  //ex:TmplPgmRegView tmplPgmRegView = ..
//        line = line.replace("$tarGenPathNm$",tarGenPathNm); //ex:pony.gens.test.TmplPgmRegView
//        
//        return line;
//    }
//
    
}
