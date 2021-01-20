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

    public Map<String, Object> delimiterToRows(Tran tran){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            String str = tran.getStrToken();
            System.out.println("tran.getStrToken()="+tran.getStrToken());
            System.out.println("tran.getdelimiter()="+tran.getdelimiter());
            if(tran.getdelimiter().contains("tab")) tran.setdelimiter("\t");
            String[] arr = str.split("\\"+tran.getdelimiter());

            String strRows = "";
            for (int i = 0; i < arr.length; i++) {
                System.out.println("arr="+arr[i]);
                strRows += arr[i]+"\n";
            }
            System.out.println("strRows="+strRows);

            tran.setStrRows(strRows);
            tran.setTokenCnt(arr.length);
            tran.setdelimiterCnt(arr.length-1);
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


    public Map<String, Object> thinq(Tran tran){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            String str = tran.getStrRows();
            System.out.println("tran.getdelimiter()="+tran.getdelimiter());
            System.out.println("tran.getStrRows()="+tran.getStrRows());

            String[] arr = str.split("\\n");
            System.out.println("arr.length="+arr.length);

            String strRows = "";

            // space padding
            int maxLen = 0;
            for (int i = 0; i < arr.length; i++) {
                if(arr[i].length() > maxLen) maxLen = arr[i].length();
            }
            System.out.println("maxLen="+maxLen);
            int space = maxLen + 10;

            // parsing
            for (int i = 0; i < arr.length; i++) {
                if(tran.getdelimiter().contains("monJson")) {
                    strRows += ", json_extract_scalar(mon_data,'$[\""+arr[i]+"\"]')";
                    strRows += String.format("%"+(space-arr[i].length())+"s", "as ").replace('0', ' ');
                    strRows += arr[i].replace("-", "_")+"\n";
                }
            }

            System.out.println("strRows=\n"+strRows);

            tran.setStrRows(strRows);
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
