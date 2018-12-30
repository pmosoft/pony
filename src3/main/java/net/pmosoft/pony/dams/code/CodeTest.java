//package net.pmosoft.pony.dams.code;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import net.pmosoft.pony.PonyApplication;
//import net.pmosoft.pony.comm.App;
//import net.pmosoft.pony.comm.util.ExcelUtil;
//
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = PonyApplication.class)
//public class CodeTest {
//
//	@Autowired
//	private CodeSrv codeSrv;
//
//	@Autowired
//	private CodeDao codeDao;
//
//    @Test @Ignore
//    public void testExcel() throws Exception {
//        
//        ExcelUtil eu = new ExcelUtil();
//        eu.xlsToList(App.excelPath + "code.xls");
//        
//    }
//	
//	@Test
//	public void testCodeCnt() {
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchKeyCombo", ""); params.put("searchValue", "");
//		params.put("PKG_FUL_NM", "user");
//		codeDao.selectCodeCnt(params);
//		
//	}
//
//	@Test @Ignore
//	public void testCodeList() {
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchValue", "us");
//		//params.put("searchValue", "유저");
//		params.put("searchValue", "");
//		codeSrv.selectCodeList(params);
//		//TermDao.selectCodeList(params);
//	}
//
//	@Test @Ignore
//	public void testSaveCode() {
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
//		//TermDao.deleteUser(params);
//
//		Map<String, Object> result = codeSrv.saveCode(params);
//
//		System.out.println(result);
//		testCodeList();
//	}
//
//	@Test @Ignore
//	public void testDeleteCode() {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("PKG_FUL_NM", "package1");
//		codeSrv.deleteCode(params);
//	}
//
//	@Test @Ignore
//	public void testInsertCode() {
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
//		codeDao.deleteCode(params);
//
//		codeDao.insertCode(params);
//
//		testCodeList();
//	}
//
//	@Test @Ignore
//	public void testUpdateCode() {
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
//		codeDao.updateCode(params);
//
//		testCodeList();
//	}
//
//
//}
//
