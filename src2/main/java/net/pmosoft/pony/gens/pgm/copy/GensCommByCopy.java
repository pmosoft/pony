package net.pmosoft.pony.gens.pgm.copy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Map;

import net.pmosoft.pony.comm.util.FileUtil;
import net.pmosoft.pony.comm.util.StringUtil;


public class GensCommByCopy implements GensPgmByCopy {

    

    /******************************************
     * 글로벌 프로퍼티
     *****************************************/
    public String prjNm           = "";                                    //프로젝트명
    String bascPathNm             = "c:/pony/workspace";                       //소스기본경로
    String packBascNm             = ""; //회사명
    String javaBascPathNm         = ""; //소스기본경로
    String javaPackBascPathNm     = ""; //자바기본경로
    String webBasePathNm          = "";                  //웹기본경로

    public void setPrjNm(String prjNm) {
        this.prjNm                  = prjNm;
        this.packBascNm             = "net.pmosoft."+prjNm;                        //회사명
        this.javaBascPathNm         = bascPathNm+"/"+prjNm+"/src/main";            //소스기본경로
        this.javaPackBascPathNm     = javaBascPathNm + "/java/net/pmosoft/"+prjNm; //자바기본경로
        this.webBasePathNm          = javaBascPathNm + "/webapp";                  //웹기본경로
    }

    
    /******************************************
     * 입력 파라미터 
     *****************************************/
    String srcPackNm          = ""; //패키지명(예:net.pmosoft.pony.gens.test)
    String srcGenPackNm       = ""; //회사명이 배제된 패키지명(예:gens.test)
    String srcGenPathNm       = ""; //회사명이 배제된 폴더명(예:gens/test)
    String srcPgmPathNm       = ""; //소스 프로그램 기본경로
    String srcVarNm           = ""; //변수명(예:testTmpl)       
    String srcPgmNm           = ""; //파일명(예:TestTmpl)       
    String srcPgmPathFileNm   = ""; //소스 프로그램 기본경로
    
    String tarPackNm          = ""; //패키지명(예:net.pmosoft.pony.gens.test)
    String tarGenPackNm       = ""; //회사명이 배제된 패키지명(예:gens.test)
    String tarGenPathNm       = ""; //회사명이 배제된 폴더명(예:gens/test)
    String tarPgmPathNm       = ""; //타겟 프로그램 기본경로
    String tarVarNm           = ""; //변수명(예:testTmpl)       
    String tarPgmNm           = ""; //파일명(예:TestTmpl)       
    String tarPgmPathFileNm   = ""; //타겟 프로그램 기본경로
           
    /******************************************
     * 기타 파라미터
     *****************************************/
    
    /******************************************
     * response 파라미터
     *****************************************/
    String retMsg = "";

    /******************************************
     * 1단계 : 입력파라미터 세팅
     *****************************************/
    public String createPgmFile(Map<String,String> params) throws Exception {

        try {

            System.out.println("params="+params);
            
            /******************************************
             * 입력 파라미터
             *****************************************/
            srcPackNm    = params.get("srcPackNm");              //패키지명(예:net.pmosoft.pony.gens.test)
            srcGenPackNm = srcPackNm.replace(packBascNm+".",""); //회사명이 배제된 패키지명(예:gens.test) 
            srcGenPathNm = srcGenPackNm.replace(".","/");        //회사명이 배제된 폴더명  (예:gens/test) 
            srcVarNm     = StringUtil.replaceFirstCharLowerCase(params.get("srcPgmNm")); //변수명(예:testTmpl)       
            srcPgmNm     = StringUtil.replaceFirstCharUpperCase(params.get("srcPgmNm")); //파일명(예:TestTmpl)

            tarPackNm    = params.get("tarPackNm");              //패키지명(예:net.pmosoft.pony.gens.test)
            tarGenPackNm = tarPackNm.replace(packBascNm+".",""); //회사명이 배제된 패키지명(예:gens.test) 
            tarGenPathNm = tarGenPackNm.replace(".","/");        //회사명이 배제된 폴더명  (예:gens/test)
            tarVarNm     = StringUtil.replaceFirstCharLowerCase(params.get("tarPgmNm")); //변수명(예:testTmpl)       
            tarPgmNm     = StringUtil.replaceFirstCharUpperCase(params.get("tarPgmNm")); //파일명(예:TestTmpl)       
            
            System.out.println("srcPackNm    = " + srcPackNm    );
            System.out.println("srcGenPackNm = " + srcGenPackNm );
            System.out.println("srcGenPathNm = " + srcGenPathNm );
            System.out.println("srcVarNm     = " + srcVarNm     );
            System.out.println("srcPgmNm     = " + srcPgmNm     );
            System.out.println("tarPackNm    = " + tarPackNm    );
            System.out.println("tarGenPackNm = " + tarGenPackNm );
            System.out.println("tarGenPathNm = " + tarGenPathNm );
            System.out.println("tarVarNm     = " + tarVarNm     );
            System.out.println("tarPgmNm     = " + tarPgmNm     );
            
            createPgmFiles();
            
            return retMsg;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }

        
    }
 
    /******************************************
     * 2단계 : 복사할 파일들 지정 
     * @throws Exception 
     *****************************************/
    public void createPgmFiles() throws Exception{}

    /******************************************
     * 3단계 : 타겟 프로그램 리팩토링 생성  
     * @throws Exception 
     *****************************************/
    public void replaceSrcPgmToTarPgm(String srcPathNm, String srcFileNm, String tarPathNm, String tarFileNm) throws Exception {
        
        try {

            if(!FileUtil.fileIsLive(srcPathNm+"/"+srcFileNm)) {
                throw new Exception("소스 파일이 존재하지 않습니다("+srcPathNm+"/"+srcFileNm+")");
            }    
            
            if(!FileUtil.fileIsLive(tarPathNm+"/"+tarFileNm)) {

                
                FileUtil.makeDir(tarPathNm);
                FileUtil.fileDelete(tarPathNm+"/"+tarFileNm);
                
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(srcPathNm+"/"+srcFileNm)));
                BufferedWriter bw = new BufferedWriter(new FileWriter(tarPathNm+"/"+tarFileNm, true));
     
                String line = "";
                while(((line = br.readLine()) != null)) {
                    //System.out.println("line="+line);
                    line = replaceRule(line);
                    bw.write(line+"\n");
                }
                bw.flush();
                bw.close();           
                br.close();
            }    
        } catch (Exception e) {
            System.out.println("Exception=" + e.getMessage());
            e.printStackTrace(); 
            throw e;
        }
    }      
    
    /******************************************
     * 4단계 : 리팩토링 룰  
     *****************************************/
     public String replaceRule(String line) { return null;}


    
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
