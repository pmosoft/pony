package net.pmosoft.pony.tran;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TranSrv {

//	@Autowired
//	private GensPgmDao gensPgmDao;

//	@Autowired
//	private GensPgmValidatorSrv gensPgmValidatorSrv;

    public Map<String, Object> delimeterToArray(Tran tran){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            //GensAngularByCopy gensAngularByCopy = new GensAngularByCopy();
            //gensAngularByCopy.execute(gens);
            result.put("isSuccess", true);
            result.put("usrMsg", "정상 처리되었습니다.");
            //result.put("retMsg", gens.getRetTarMsg());
            result.put("tranOutVo", tran);
            
       } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
    
}
