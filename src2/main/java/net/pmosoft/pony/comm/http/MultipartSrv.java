package net.pmosoft.pony.comm.http;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class MultipartSrv {

	@Autowired
	private MultipartDao multipartDao;

	@Autowired
	private MultipartValidatorSrv multipartValidatorSrv;

	
   public Map<String, Object> excelUpload(ArrayList<MultipartFile> files) {
        //System.out.println("fn="+files.get(0).getOriginalFilename());
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            for (int i = 0; i < files.size(); i++) {
                byte[] bytes = files.get(i).getBytes();
                Path path = Paths.get("c:/pony/files/excel/" + files.get(i).getOriginalFilename());
                Files.write(path, bytes);
            } 

            result.put("isSuccess", true);
            
        } catch (Exception e) {
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }        
        return result;
   }     
	
}
