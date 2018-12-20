//package net.pmosoft.pony.dams.table;
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
//public class TabColTest {
//
//	@Autowired
//	private TabSrv tabSrv;
//
//	@Autowired
//	private TabDao tabDao;
//
//
//	@Test @Ignore
//	public void testTabCnt() {
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchKeyCombo", ""); params.put("searchValue", "");
//		params.put("PKG_FUL_NM", "user");
//		tabDao.selectTabCnt(params);
//	}
//
//	@Test
//	public void testTabList() {
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchValue", "us");
//		//params.put("searchValue", "유저");
//		params.put("searchValue", "");
//		tabSrv.selectTabColList(params);
//		//TermDao.selectTabList(params);
//	}
//
//	@Test @Ignore
//	public void testSaveTab() {
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
//		Map<String, Object> result = tabSrv.saveTab(params);
//
//		System.out.println(result);
//		testTabList();
//	}
//
//	@Test @Ignore
//	public void testDeleteTab() {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("PKG_FUL_NM", "package1");
//		tabSrv.deleteTab(params);
//	}
//
//	@Test @Ignore
//	public void testInsertTab() {
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
//		tabDao.deleteTab(params);
//
//		tabDao.insertTab(params);
//
//		testTabList();
//	}
//
//	@Test @Ignore
//	public void testUpdateTab() {
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
//		tabDao.updateTab(params);
//
//		testTabList();
//	}
//
//
//}
//
