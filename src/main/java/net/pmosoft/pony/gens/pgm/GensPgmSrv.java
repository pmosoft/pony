package net.pmosoft.pony.gens.pgm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.pmosoft.pony.gens.pgm.copy.GensAngularByCopy;
import net.pmosoft.pony.gens.pgm.copy.GensSpringByCopy;
import net.pmosoft.pony.gens.pgm.tmpl.GensPgmByTmpl;


@Service
public class GensPgmSrv {

//	@Autowired
//	private GensPgmDao gensPgmDao;

//	@Autowired
//	private GensPgmValidatorSrv gensPgmValidatorSrv;

    public Map<String, Object> textToJavaVo(GensPgm gens){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            String tarTxt = "";

            gens.setTarTxt(gens.getSrcTxt());

            List<String> srcList = Arrays.asList(gens.getSrcTxt().split("\n"));
            System.out.println(srcList);

            List<String> srcList2 = new ArrayList();
            List<List<String>> srcList3 = new ArrayList<List<String>>();

            // public class GensPgm {
            for(String s : srcList){
                srcList2 = Arrays.asList(s.split(" "));
                srcList3.add(srcList2);
                System.out.println(srcList3);
            }

            for(List<String> s : srcList3){
                if(s.get(0).contains("class")) tarTxt = "public class " +s.get(1)+" {"+"\n";
                else tarTxt += "\tprivate "+s.get(0)+" "+s.get(1)+";"+"\n";
            }
            tarTxt += "\n";
            tarTxt += "}"+"\n";

            System.out.println(tarTxt);

            //if(s.contains("class")) tarText += "public class ";
            gens.setTarTxt(tarTxt);


            result.put("isSuccess", true);
            result.put("usrMsg", "정상 처리되었습니다.");
            //result.put("retMsg", gens.getRetTarMsg());

            result.put("genOutVo", gens);

       } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }



    public Map<String, Object> cloneAngular(GensPgm gens){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            GensAngularByCopy gensAngularByCopy = new GensAngularByCopy();
            gensAngularByCopy.execute(gens);
            result.put("isSuccess", true);
            result.put("usrMsg", "정상 처리되었습니다.");
            //result.put("retMsg", gens.getRetTarMsg());
            result.put("gensOutVo", gens);

       } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> cloneSpring(GensPgm gens){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            GensSpringByCopy gensSpringByCopy = new GensSpringByCopy();
            gensSpringByCopy.execute(gens);
            result.put("isSuccess", true);
            result.put("usrMsg", "정상 처리되었습니다.");
            //result.put("retMsg", gens.getRetTarMsg());
            result.put("gensOutVo", gens);

       } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }



   public Map<String, Object> genPgmByTmpl(Map<String,String> params){

       Map<String, Object> result = new HashMap<String, Object>();

       try{

           GensPgmByTmpl gensPgmByTmpl = new GensPgmByTmpl();
           gensPgmByTmpl.createPgmFile(params);
           result.put("isSuccess", true);
           result.put("usrMsg", "정상 처리되었습니다.");
       } catch (Exception e){
           result.put("isSuccess", false);
           result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
           result.put("errSysMsg", e.getMessage());
           e.printStackTrace();
       }
       return result;
   }

}