package net.pmosoft.pony.comm.excel;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.pmosoft.pony.comm.App;
import net.pmosoft.pony.comm.util.ExcelUtil;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Service
public class ExcelSrv {

	@Autowired
	private ExcelDao excelDao;

	@Autowired
	private ExcelValidatorSrv excelValidatorSrv;

	public Map<String, Object> downloadExcel(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();


        String data = params.get("data");
        System.out.println("data="+data);
        System.out.println("data="+data);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
        List<Map<String,String>> listParams  = gson.fromJson(data, type);
        System.out.println(listParams);
        ExcelUtil excelDown = new ExcelUtil();
        try {
            excelDown.downListToExcel(listParams,App.excelPath+params.get("fileNm"));
            Runtime run = Runtime.getRuntime ();
            run.exec ("cmd /c start excel.exe "+App.excelPath+params.get("fileNm"));

            result.put("isSuccess", true);

        } catch (Exception e) {

            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();

        }


        return result;
	}

    public JSONObject  uploadExcel(ArrayList<MultipartFile> files) {
         String fileNm = files.get(0).getOriginalFilename();
         //System.out.println("fileNm="+fileNm);

         JSONObject jsonObj = new JSONObject();

         try {
//             for (int i = 0; i < files.size(); i++) {
//                 byte[] bytes = files.get(i).getBytes();
//                 Path path = Paths.get(App.excelPath + fileNm);
//                 Files.write(path, bytes);
//             }
             byte[] bytes = files.get(0).getBytes();
             Path path = Paths.get(App.excelPath + fileNm);
             Files.write(path, bytes);

             ExcelUtil eu = new ExcelUtil();
             List<Map<String,String>> list = eu.xlsToList(App.excelPath + fileNm);

             jsonObj.put("success", true);
             jsonObj.put("isSuccess", true);
             jsonObj.put("successMsg", "업로드 되었습니다");
             jsonObj.put("data", list);

         } catch (Exception e) {
             //e.printStackTrace();

             System.out.println("ExcelSrv asdfasdfasdfasdf");
             jsonObj.put("isSuccess", false);
             jsonObj.put("errUsrMsg", "시스템 장애가 발생하였습니다");
             jsonObj.put("errSysMsg", e.getMessage());
         } finally {
             return jsonObj;
         }


    }

    public JSONObject  uploadExcel2(MultipartFile files) {
        String fileNm = files.getOriginalFilename();
        //System.out.println("fileNm="+fileNm);

        JSONObject jsonObj = new JSONObject();

        try {
//            for (int i = 0; i < files.size(); i++) {
//                byte[] bytes = files.get(i).getBytes();
//                Path path = Paths.get(App.excelPath + fileNm);
//                Files.write(path, bytes);
//            }
            byte[] bytes = files.getBytes();
            Path path = Paths.get(App.excelPath + fileNm);
            System.out.println(App.excelPath + fileNm);
            Files.write(path, bytes);

            List<Map<String,String>> list = null;

            jsonObj.put("success", true);
            jsonObj.put("isSuccess", true);
            jsonObj.put("successMsg", "업로드 되었습니다");
            jsonObj.put("data", list);

        } catch (Exception e) {
            //e.printStackTrace();

            System.out.println("ExcelSrv asdfasdfasdfasdf");
            jsonObj.put("isSuccess", false);
            jsonObj.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            jsonObj.put("errSysMsg", e.getMessage());
        } finally {
            return jsonObj;
        }


   }

}
