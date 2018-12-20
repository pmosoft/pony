//package net.pmosoft.pony.etcl;
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
//public class EtclTest {
//
//	@Autowired
//	private EtclSrv etclSrv;
//
//	@Autowired
//	private EtclDao etclDao;
//
//
//    @Test
//    public void testEtclProcess() {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("PKG_FUL_NM", "user");
//        etclDao.selectEtclList(params);
//        etclSrv.selectEtclList(params);
//    }	
//	
//	@Test @Ignore
//	public void testEtclCnt() {
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchKeyCombo", ""); params.put("searchValue", "");
//		params.put("PKG_FUL_NM", "user");
//		etclDao.selectEtclCnt(params);
//	}
//
//	@Test @Ignore
//	public void testEtclList() {
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchValue", "us");
//		//params.put("searchValue", "유저");
//		params.put("searchValue", "");
//		etclSrv.selectEtclList(params);
//		//EtclDao.selectEtclList(params);
//	}
//
//	@Test @Ignore
//	public void testSaveEtcl() {
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
//		//EtclDao.deleteUser(params);
//
//		Map<String, Object> result = etclSrv.saveEtcl(params);
//
//		System.out.println(result);
//		testEtclList();
//	}
//
//	@Test @Ignore
//	public void testDeleteEtcl() {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("PKG_FUL_NM", "package1");
//		etclSrv.deleteEtcl(params);
//	}
//
//	@Test @Ignore
//	public void testInsertEtcl() {
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
//		etclDao.deleteEtcl(params);
//
//		etclDao.insertEtcl(params);
//
//		testEtclList();
//	}
//
//	@Test @Ignore
//	public void testUpdateEtcl() {
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
//		etclDao.updateEtcl(params);
//
//		testEtclList();
//	}
//
//}
//
