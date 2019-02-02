//package net.pmosoft.pony.comm.excel;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import net.pmosoft.pony.AbstractTest;
//import net.pmosoft.pony.FframeApplication;
//
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = FframeApplication.class)
//public class ExcelTest {
//
//	@Autowired
//	private ExcelSrv excelSrv;
//
//	@Autowired
//	private ExcelDao excelDao;
//
//
//    @Test
//    public void testExcelProcess() {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("PKG_FUL_NM", "user");
//        excelDao.selectExcelList(params);
//        excelSrv.selectExcelList(params);
//    }	
//	
//	@Test @Ignore
//	public void testExcelCnt() {
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchKeyCombo", ""); params.put("searchValue", "");
//		params.put("PKG_FUL_NM", "user");
//		excelDao.selectExcelCnt(params);
//	}
//
//	@Test @Ignore
//	public void testExcelList() {
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchValue", "us");
//		//params.put("searchValue", "유저");
//		params.put("searchValue", "");
//		excelSrv.selectExcelList(params);
//		//ExcelDao.selectExcelList(params);
//	}
//
//	@Test @Ignore
//	public void testSaveExcel() {
//
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("PKG_FUL_NM", "package1");
//		params.put("PKG2_NM"   , "pk");
//		params.put("PKG3_NM"   , "pkg");
//		params.put("PKG4_NM"   , "pack");
//		params.put("PKG_HNM"   , "패키지");
//		params.put("PKG_DESC"  , "패키지4");
//		params.put("USE_YN"     , "Y");
//		params.put("REG_USR_ID", "admin");
//		params.put("UPD_USR_ID", "admin");
//
//		//ExcelDao.deleteUser(params);
//
//		Map<String, Object> result = excelSrv.saveExcel(params);
//
//		System.out.println(result);
//		testExcelList();
//	}
//
//	@Test @Ignore
//	public void testDeleteExcel() {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("PKG_FUL_NM", "package1");
//		excelSrv.deleteExcel(params);
//	}
//
//	@Test @Ignore
//	public void testInsertExcel() {
//
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchKeyCombo", ""); params.put("searchValue", "");
//		params.put("PKG_FUL_NM", "package");
//		params.put("PKG2_NM"   , "pk");
//		params.put("PKG3_NM"   , "pkg");
//		params.put("PKG4_NM"   , "pack");
//		params.put("PKG_HNM"   , "패키지");
//		params.put("PKG_DESC"  , "패키지");
//		params.put("USE_YN"     , "Y");
//		params.put("REG_USR_ID", "admin");
//		params.put("UPD_USR_ID", "admin");
//
//		excelDao.deleteExcel(params);
//
//		excelDao.insertExcel(params);
//
//		testExcelList();
//	}
//
//	@Test @Ignore
//	public void testUpdateExcel() {
//
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchKeyCombo", ""); params.put("searchValue", "");
//		params.put("PKG_FUL_NM", "package");
//		params.put("PKG2_NM"   , "pk");
//		params.put("PKG3_NM"   , "pkg");
//		params.put("PKG4_NM"   , "pack");
//		params.put("PKG_HNM"   , "패키지");
//		params.put("PKG_DESC"  , "패키지2");
//		params.put("USE_YN"     , "Y");
//		params.put("REG_USR_ID", "admin");
//		params.put("UPD_USR_ID", "admin");
//
//		excelDao.updateExcel(params);
//
//		testExcelList();
//	}
//
//}
//
