package net.pmosoft.pony.comm.http;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MultipartCtrl {

	@Autowired
	private MultipartSrv multipartSrv;

	   @RequestMapping(value = "/comm/http/excelUpload")
	   public Map<String, Object> excelUpload(@RequestParam("uploadFile") ArrayList<MultipartFile> files) {
	       return multipartSrv.excelUpload(files);
	   }     

}
