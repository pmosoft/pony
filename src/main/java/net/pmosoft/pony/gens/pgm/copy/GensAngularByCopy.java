package net.pmosoft.pony.gens.pgm.copy;

import java.util.HashMap;
import java.util.Map;

import net.pmosoft.pony.comm.util.StringUtil;
import net.pmosoft.pony.gens.pgm.Gens;

public class GensAngularByCopy extends GensCommByCopy2 {

    public static void main(String[] args) throws Exception { 
        GensAngularByCopy gensAngularByCopy = new GensAngularByCopy();

        Gens gens = new Gens();
        gens.setSrcPathNm("d:/fframe/workspace/pony-web/src/app/layout/clone/angular");
        gens.setSrcBarNm("clone-angular");
        gens.setTarPathNm("d:/fframe/workspace/pony-web/src/app/layout/clone/spring");
        gens.setTarBarNm("clone-spring");
        
        gensAngularByCopy.setGenParam(gens);
        gensAngularByCopy.genTarPgmFiles(gens);
    }

    public void execute(Gens gens) throws Exception {
        setGenParam(gens);
        genTarPgmFiles(gens);
    }
    /******************************************
     * 1단계 : 입력 param으로 gen시 필요param를 세팅
     *****************************************/
    public void setGenParam(Gens gens) throws Exception {

        try {

            //System.out.println("Gens="+gens);

            /******************************************
             * path의 \ ==> / 변경
             *****************************************/
            gens.setSrcPathNm(gens.getSrcPathNm().replace("\\","/"));
            gens.setTarPathNm(gens.getTarPathNm().replace("\\","/"));
            /******************************************
             * path의 끝문자 / 제거
             *****************************************/
            if(gens.getSrcPathNm().endsWith("/")) gens.setSrcPathNm(gens.getSrcPathNm().substring(0,gens.getSrcPathNm().length()-1));
            if(gens.getTarPathNm().endsWith("/")) gens.setTarPathNm(gens.getTarPathNm().substring(0,gens.getTarPathNm().length()-1));

            /******************************************
             * Class 형태의 첫대문자 Camel 문자로 변경
             *****************************************/
            gens.setSrcClassNm (StringUtil.tokenToUCamel(gens.getSrcBarNm(),"-"));
            gens.setTarClassNm (StringUtil.tokenToUCamel(gens.getTarBarNm(),"-"));

            /******************************************
             * Method 형태의 첫소문자 Camel 문자로 변경
             *****************************************/
            gens.setSrcMethodNm(StringUtil.tokenToLCamel(gens.getSrcBarNm(),"-"));
            gens.setTarMethodNm(StringUtil.tokenToLCamel(gens.getTarBarNm(),"-"));
            
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
    }
    
    /******************************************
     * 2단계 : 복사할 파일들 지정 
     *****************************************/
    public void genTarPgmFiles(Gens gens) throws Exception{

        try 
        {
            
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcBarNm()+".component.html"     ,gens.getTarPathNm(),gens.getTarBarNm()+".component.html"    ,gens);
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcBarNm()+".component.ts"       ,gens.getTarPathNm(),gens.getTarBarNm()+".component.ts"      ,gens);
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcBarNm()+".component.scss"     ,gens.getTarPathNm(),gens.getTarBarNm()+".component.scss"    ,gens);
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcBarNm()+".component.spec.ts"  ,gens.getTarPathNm(),gens.getTarBarNm()+".component.spec.ts" ,gens);
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcBarNm()+".module.ts"          ,gens.getTarPathNm(),gens.getTarBarNm()+".module.ts"         ,gens);
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcBarNm()+"-routing.module.ts"  ,gens.getTarPathNm(),gens.getTarBarNm()+"-routing.module.ts" ,gens);
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcBarNm()+".ts"                 ,gens.getTarPathNm(),gens.getTarBarNm()+".ts"                ,gens);
            
            System.out.println("msg="+gens.getRetSrcMsg());
            System.out.println("msg="+gens.getRetTarMsg());
            String retMsg = "생성 프로그램";
            retMsg += gens.getRetTarMsg() +"\n\n";
            retMsg += "소스 프로그램";        
            retMsg += gens.getRetSrcMsg() +"\n\n";
            retMsg += "예외";        
            retMsg += gens.getRetErrMsg();
            
            gens.setRetMsg(retMsg);
            System.out.println("msg="+gens.getRetTarMsg());
            //System.out.println(retMsg);
            
        } catch (Exception e) {
            //retMsg  =  e.getMessage();
            throw e;
        }
    }

    /***********************************************
     * 4단계 : 리팩토링 룰  
     ***********************************************/
    public String replaceRule(String line, Gens gens) {
              
        line = line.replace(gens.getSrcBarNm()   ,gens.getTarBarNm()   ); 
        line = line.replace(gens.getSrcClassNm() ,gens.getTarClassNm() ); 
        line = line.replace(gens.getSrcMethodNm(),gens.getTarMethodNm()); 
        
        return line;
    }

    
}
