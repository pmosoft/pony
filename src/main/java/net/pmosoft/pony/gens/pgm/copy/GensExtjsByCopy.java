package net.pmosoft.pony.gens.pgm.copy;

import java.util.HashMap;
import java.util.Map;

public class GensExtjsByCopy extends GensCommByCopy {

    public static void main(String[] args) throws Exception { 
        GensExtjsByCopy gensExtjsByCopy = new GensExtjsByCopy();

        Map<String, String> params = new HashMap<String, String>();
        params.put("srcPackNm", "net.pmosoft.pony.gens");
        params.put("srcPgmNm" , "GenPgmByCopy");
        params.put("tarPackNm", "net.pmosoft.pony.gens");
        params.put("tarPgmNm" , "GenPgmByTmpl");
        
        gensExtjsByCopy.createPgmFile(params);
    }

    /******************************************
     * 2단계 : 복사할 파일들 지정 
     * @throws Exception 
     *****************************************/
    public void createPgmFiles() throws Exception{

        try {

            srcPgmPathNm = webBasePathNm +"/"+ srcGenPathNm;
            tarPgmPathNm = webBasePathNm +"/"+ tarGenPathNm;

            System.out.println("srcPgmPathNm = " + srcPgmPathNm );
            System.out.println("tarPgmPathNm = " + tarPgmPathNm );
            
            // View
            replaceSrcPgmToTarPgm(srcPgmPathNm,srcPgmNm+"View.js"      ,tarPgmPathNm,tarPgmNm+"View.js"      );
            // Controller
            replaceSrcPgmToTarPgm(srcPgmPathNm,srcPgmNm+"Controller.js",tarPgmPathNm,tarPgmNm+"Controller.js");
            // Model        
            replaceSrcPgmToTarPgm(srcPgmPathNm,srcPgmNm+"Model.js"     ,tarPgmPathNm,tarPgmNm+"Model.js"     );

            retMsg  =  "";
            retMsg +=  "생성된 프로그램\n";
            retMsg +=  tarPgmPathNm+"/"+tarPgmNm+"View.js\n";
            retMsg +=  tarPgmPathNm+"/"+tarPgmNm+"Controller.js\n";
            retMsg +=  tarPgmPathNm+"/"+tarPgmNm+"Model.js\n";
            retMsg +=  "\n";        
            retMsg +=  "소스 프로그램\n";
            retMsg +=  srcPgmPathNm+"/"+srcPgmNm+"View.js\n";
            retMsg +=  srcPgmPathNm+"/"+srcPgmNm+"Controller.js\n";
            retMsg +=  srcPgmPathNm+"/"+srcPgmNm+"Model.js\n";
            
            //System.out.println(retMsg);
            
        } catch (Exception e) {
            retMsg  =  e.getMessage();
            throw e;
        }
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
