//package net.pmosoft.pony.comm.http;
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
//public class MultipartTest {
//
//	@Autowired
//	private MultipartSrv multipartSrv;
//
//	@Autowired
//	private MultipartDao multipartDao;
//
//
//    @Test
//    public void testMultipartProcess() {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("PKG_FUL_NM", "user");
//        multipartDao.selectMultipartList(params);
//        multipartSrv.selectMultipartList(params);
//    }	
//	
//	@Test @Ignore
//	public void testMultipartCnt() {
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchKeyCombo", ""); params.put("searchValue", "");
//		params.put("PKG_FUL_NM", "user");
//		multipartDao.selectMultipartCnt(params);
//	}
//
//	@Test @Ignore
//	public void testMultipartList() {
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchValue", "us");
//		//params.put("searchValue", "유저");
//		params.put("searchValue", "");
//		multipartSrv.selectMultipartList(params);
//		//MultipartDao.selectMultipartList(params);
//	}
//
//	@Test @Ignore
//	public void testSaveMultipart() {
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
//		//MultipartDao.deleteUser(params);
//
//		Map<String, Object> result = multipartSrv.saveMultipart(params);
//
//		System.out.println(result);
//		testMultipartList();
//	}
//
//	@Test @Ignore
//	public void testDeleteMultipart() {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("PKG_FUL_NM", "package1");
//		multipartSrv.deleteMultipart(params);
//	}
//
//	@Test @Ignore
//	public void testInsertMultipart() {
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
//		multipartDao.deleteMultipart(params);
//
//		multipartDao.insertMultipart(params);
//
//		testMultipartList();
//	}
//
//	@Test @Ignore
//	public void testUpdateMultipart() {
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
//		multipartDao.updateMultipart(params);
//
//		testMultipartList();
//	}
//
//}
//
