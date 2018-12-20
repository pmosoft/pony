package net.pmosoft.pony.gens.pgm.copy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import net.pmosoft.pony.comm.util.FileUtil;


public class GensSpringByCopy extends GensCommByCopy {

    public static void main(String[] args) { 
        GensSpringByCopy gensSpringByCopy = new GensSpringByCopy();
        gensSpringByCopy.setPrjNm("pony"); //프로젝트명
        
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("srcPackNm", "net.pmosoft.pony.gens");
        params.put("srcPgmNm" , "GenPgmByCopy");
        params.put("tarPackNm", "net.pmosoft.pony.gens");
        params.put("tarPgmNm" , "GenPgmByTmpl");
        
        try {
            gensSpringByCopy.createPgmFile(params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /******************************************
     * 2단계 : 복사할 파일들 지정 
     * @throws Exception 
     *****************************************/
    public void createPgmFiles() throws Exception{

        srcPgmPathNm = javaPackBascPathNm +"/"+ srcGenPathNm;
        tarPgmPathNm = javaPackBascPathNm +"/"+ tarGenPathNm;
        System.out.println("srcGenPathNm = " + srcGenPathNm );
        System.out.println("tarGenPathNm = " + tarGenPathNm );
        System.out.println("srcPgmPathNm = " + srcPgmPathNm );
        System.out.println("tarPgmPathNm = " + tarPgmPathNm );

        replaceSrcPgmToTarPgm(srcPgmPathNm,srcPgmNm+"Ctrl.java"         ,tarPgmPathNm,tarPgmNm+"Ctrl.java"      );
        replaceSrcPgmToTarPgm(srcPgmPathNm,srcPgmNm+"Srv.java"          ,tarPgmPathNm,tarPgmNm+"Srv.java"      );
        replaceSrcPgmToTarPgm(srcPgmPathNm,srcPgmNm+"Dao.java"          ,tarPgmPathNm,tarPgmNm+"Dao.java"      );
        replaceSrcPgmToTarPgm(srcPgmPathNm,srcPgmNm+"ValidatorSrv.java" ,tarPgmPathNm,tarPgmNm+"ValidatorSrv.java"      );
        replaceSrcPgmToTarPgm(srcPgmPathNm,srcPgmNm+"MariadbDao.xml"    ,tarPgmPathNm,tarPgmNm+"MariadbDao.xml"      );
        
    }

    /***********************************************
     * 4단계 : 리팩토링 룰  
     ***********************************************/
    public String replaceRule(String line) {
        line = line.replace(prjNm+"."+srcGenPackNm,prjNm+"."+tarGenPackNm); //ex:pony.gens.test.TmplPgmRegView
        line = line.replace(srcPgmNm,tarPgmNm);                //ex:pony.gens.test.TmplPgmRegView
        line = line.replace(srcVarNm,tarVarNm);                //ex:TmplPgmRegView tmplPgmRegView = ..
        line = line.replace(srcGenPathNm,tarGenPathNm);   //ex:/gens/test 
        
        return line;
    }

    
}
