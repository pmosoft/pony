package net.pmosoft.pony.comm.excel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ExcelCtrl {

	@Autowired
	private ExcelSrv excelSrv;

	/**
	 * select ExcelList
	 */
	@RequestMapping(value = "/comm/excel/downloadExcel")
	public Map<String, Object> downloadExcel(@RequestParam Map<String,String> params) {
		return excelSrv.downloadExcel(params);
	}

   @RequestMapping(value = "/comm/excel/uploadExcel")
   public void uploadExcel(@RequestParam("uploadFile") ArrayList<MultipartFile> files, HttpServletResponse response){
       
       try {
            JSONObject jsonObj = excelSrv.uploadExcel(files);
            response.setContentType("text/plain; charset=UTF-8");
            PrintWriter pw = response.getWriter();
            pw.print(jsonObj);
            pw.flush();
            pw.close();
       } catch (IOException e) {}
           //return codeSrv.uploadCodeRegList(files);
       
//       try {
//           jsonObj = excelSrv.uploadExcel(files);
//           System.out.println("ExcelCtrl jsonObj="+jsonObj);
//      } catch (Exception e) {
//           System.out.println("ExcelCtrl asdfasdfasdfasdf");
//      } finally {
//          System.out.println("ExcelCtrl asdfasdfasdfasdfasdfasdfadsf");
//          response.setContentType("text/plain; charset=UTF-8");
//          try {
//           pw = response.getWriter();
//           pw.print(jsonObj);
//           pw.flush();
//           pw.close();
//          } catch (IOException e) {
//           // TODO Auto-generated catch block
//           //e.printStackTrace();
//          }
//          
//      }
       
       
   }     


   // angular
   @RequestMapping(value = "/comm/excel/uploadExcel2")
   public void uploadExcel2(@RequestPart("uploadFile") MultipartFile files, HttpServletResponse response){
       System.out.println("11111111111111111111111111111111111111111111");
       try {
            JSONObject jsonObj = excelSrv.uploadExcel2(files);
            response.setContentType("text/plain; charset=UTF-8");
            PrintWriter pw = response.getWriter();
            pw.print(jsonObj);
            pw.flush();
            pw.close();
       } catch (IOException e) {}
           //return codeSrv.uploadCodeRegList(files);
   }   
   
}
