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

    public Map<String, Object> delimeterToRows(Tran tran){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            String str = tran.getStrToken();
            System.out.println("tran.getStrToken()="+tran.getStrToken());
            System.out.println("tran.getDelimeter()="+tran.getDelimeter());
            String[] arr = str.split("\\"+tran.getDelimeter());

            String strRows = "";
            for (int i = 0; i < arr.length; i++) {
                System.out.println("arr="+arr[i]);
                strRows += arr[i]+"\n";
            }
            System.out.println("strRows="+strRows);

            tran.setStrRows(strRows);
            tran.setTokenCnt(arr.length);
            tran.setDelimeterCnt(arr.length-1);
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
