package net.pmosoft.pony.gens.pgm.copy;

import java.util.Map;

public interface GensPgmByCopy {

    /******************************************
     * 1단계 : 입력파라미터 세팅
     *****************************************/
    public String createPgmFile(Map<String,String> params) throws Exception;
 
    /******************************************
     * 2단계 : 복사할 파일들 지정 
     * @throws Exception 
     *****************************************/
    public void createPgmFiles() throws Exception;

    /******************************************
     * 3단계 : 타겟 프로그램 리팩토링 생성 
     * @throws Exception 
     *****************************************/
    public void replaceSrcPgmToTarPgm(String srcPathNm, String srcFileNm, String tarPathNm, String tarFileNm) throws Exception; 

    /***********************************************
     * 4단계 : 리팩토링 룰  
     ***********************************************/
    public String replaceRule(String line) throws Exception;
    
}


