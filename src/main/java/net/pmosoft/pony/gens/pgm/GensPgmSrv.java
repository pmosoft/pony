package net.pmosoft.pony.gens.pgm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.pmosoft.pony.gens.pgm.copy.GensAngularByCopy;
import net.pmosoft.pony.gens.pgm.copy.GensPgmByCopy;
import net.pmosoft.pony.gens.pgm.tmpl.GensPgmByTmpl;


@Service
public class GensPgmSrv {

//	@Autowired
//	private GensPgmDao gensPgmDao;

//	@Autowired
//	private GensPgmValidatorSrv gensPgmValidatorSrv;

    public Map<String, Object> cloneAngular(Gens gens){

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

   public Map<String, Object> genPgmByCopy(Map<String,String> params){
       System.out.println(params);

       String retMsg = "";
       
       Map<String, Object> result = new HashMap<String, Object>();
       try{
           
           GensPgmByCopy gensPgmByCopy = (GensPgmByCopy) Class.forName(params.get("pgmPath")).newInstance();;
           //GensPgmByCopy gensPgmByCopy = new GensExtjsByCopy();
           retMsg = gensPgmByCopy.createPgmFile(params);
           
           result.put("isSuccess", true);
           result.put("usrMsg", "정상 처리되었습니다.");
           result.put("retMsg", retMsg);
       } catch (Exception e){
           result.put("isSuccess", false);
           result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
           result.put("errSysMsg", e.getMessage());
           result.put("retMsg", retMsg);
           e.printStackTrace();
       }
       return result; 
   }   
}
