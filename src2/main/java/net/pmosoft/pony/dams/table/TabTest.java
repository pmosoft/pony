package net.pmosoft.pony.dams.table;

import java.util.HashMap;
import java.util.Map;

import net.pmosoft.pony.PonyApplication;
import net.pmosoft.pony.dams.code.CodeDao;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PonyApplication.class)
public class TabTest {

	@Autowired
	private TabSrv tabSrv;

	@Autowired
	private TabDao tabDao;

    @Autowired
    private CodeDao codeDao;

    @Test
    public void selectInsertData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("dbDriver"  , "org.hsqldb.jdbcDriver");        
        params.put("dbConn"    , "jdbc:log4jdbc:mariadb://pmosoft.net:3306/pony");        
        params.put("dbUser"    , "pony");        
        params.put("dbPassword", "f1234");        
        params.put("dbType"    , "MARIADB");        
        params.put("dbOwner"   , "FFRAME");        
        params.put("TAB_NM"    , "TDACM00060");        
        
        System.out.println(tabSrv.selectInsertData(params));
    }    
    
    @Test @Ignore
    public void selectQryData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("dbDriver"  , "org.hsqldb.jdbcDriver");        
        params.put("dbConn"    , "jdbc:log4jdbc:mariadb://pmosoft.net:3306/pony");        
        params.put("dbUser"    , "pony");        
        params.put("dbPassword", "f1234");        
        params.put("dbType"    , "MARIADB");        
        params.put("dbOwner"   , "FFRAME");        
        params.put("TAB_NM"    , "TDACM00060");        
        params.put("qry"       , "SELECT * FROM FFRAME.TDACM00060");        
        
        tabSrv.selectQryData(params);
    }	
	
    @Test @Ignore
    public void selectMetaTabColSchema() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("CD_ID_NM", "DB_CONN_CD");        
        params.put("CD", "01");        
        tabSrv.selectMetaTabColList(params);
        //codeDao.selectCodeExt(params);
        
        //tableDao.deleteMetaTabCol(params);        
        //tableDao.insertMetaTabColList(params);        
        //tableDao.selectMetaTabColList(params);        
    }	
    @Test @Ignore
    public void testTabProcess() {
        Map<String, String> params = new HashMap<String, String>();
        //tableDao.deleteMetaTabCol(params);        
        //tableDao.insertMetaTabColList(params);        

        //tableDao.selectCmpTabColList(params);        
        //tableSrv.selectCmpTabColList(params);
        selectMetaTabColList();
        //tableSrv.insertCmpTabColList(params);

        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
        }        
        
    }
	
    @Test @Ignore
    public void selectMetaTabColList() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("step", "1");
        tabSrv.selectMetaTabColList(params);
        
        //tableDao.deleteMetaTabCol(params);        
        //tableDao.insertMetaTabColList(params);        
        //tableDao.selectMetaTabColList(params);        
    }

	@Test @Ignore
	public void testTabCnt() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("searchKeyCombo", ""); params.put("searchValue", "");
		params.put("PKG_FUL_NM", "user");
		tabDao.selectTabCnt(params);
	}

	@Test @Ignore
	public void testTabList() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("searchValue", "us");
		params.put("searchValue", "유저");
		params.put("searchValue", "");
		tabSrv.selectTabList(params);
		//TermDao.selectTabList(params);
	}

	@Test @Ignore
	public void testSaveTab() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("PKG_FUL_NM", "package1");
		params.put("PKG2_NM"   , "pk");
		params.put("PKG3_NM"   , "pkg");
		params.put("PKG4_NM"   , "pack");
		params.put("PKG_HNM"   , "패키지");
		params.put("PKG_DESC"  , "패키지4");
		params.put("USE_YN"     , "Y");
		params.put("REG_USR_ID", "admin");
		params.put("UPD_USR_ID", "admin");

		//TermDao.deleteUser(params);

		Map<String, Object> result = tabSrv.saveTab(params);

		System.out.println(result);
		testTabList();
	}

	@Test @Ignore
	public void testDeleteTab() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("PKG_FUL_NM", "package1");
		tabSrv.deleteTab(params);
	}

	@Test @Ignore
	public void testInsertTab() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("searchKeyCombo", ""); params.put("searchValue", "");
		params.put("PKG_FUL_NM", "package");
		params.put("PKG2_NM"   , "pk");
		params.put("PKG3_NM"   , "pkg");
		params.put("PKG4_NM"   , "pack");
		params.put("PKG_HNM"   , "패키지");
		params.put("PKG_DESC"  , "패키지");
		params.put("USE_YN"     , "Y");
		params.put("REG_USR_ID", "admin");
		params.put("UPD_USR_ID", "admin");

//		tabDao.deleteTab(params);

//		tabDao.insertTab(params);

		testTabList();
	}

}
