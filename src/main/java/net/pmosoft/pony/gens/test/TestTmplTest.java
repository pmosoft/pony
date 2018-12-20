//package net.pmosoft.pony.gens.test;
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
//public class TestTmplTest {
//
//	@Autowired
//	private TestTmplSrv testTmplSrv;
//
//	@Autowired
//	private TestTmplDao testTmplDao;
//
//
//    @Test
//    public void testTestTmplProcess() {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("PKG_FUL_NM", "user");
//        testTmplDao.selectTestTmplList(params);
//        testTmplSrv.selectTestTmplList(params);
//    }	
//	
//	@Test @Ignore
//	public void testTestTmplCnt() {
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchKeyCombo", ""); params.put("searchValue", "");
//		params.put("PKG_FUL_NM", "user");
//		testTmplDao.selectTestTmplCnt(params);
//	}
//
//	@Test @Ignore
//	public void testTestTmplList() {
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchValue", "us");
//		//params.put("searchValue", "유저");
//		params.put("searchValue", "");
//		testTmplSrv.selectTestTmplList(params);
//		//TestTmplDao.selectTestTmplList(params);
//	}
//
//	@Test @Ignore
//	public void testSaveTestTmpl() {
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
//		//TestTmplDao.deleteUser(params);
//
//		Map<String, Object> result = testTmplSrv.saveTestTmpl(params);
//
//		System.out.println(result);
//		testTestTmplList();
//	}
//
//	@Test @Ignore
//	public void testDeleteTestTmpl() {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("PKG_FUL_NM", "package1");
//		testTmplSrv.deleteTestTmpl(params);
//	}
//
//	@Test @Ignore
//	public void testInsertTestTmpl() {
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
//		testTmplDao.deleteTestTmpl(params);
//
//		testTmplDao.insertTestTmpl(params);
//
//		testTestTmplList();
//	}
//
//	@Test @Ignore
//	public void testUpdateTestTmpl() {
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
//		testTmplDao.updateTestTmpl(params);
//
//		testTestTmplList();
//	}
//
//}
//
