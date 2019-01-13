package net.pmosoft.pony.dams.table;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TabInfoDao {

    /**********************************************************************************
    *
    *                                 MetaTabInfo
    *
    **********************************************************************************/
    List<TabInfo> selectMetaTabInfoList(TabInfo inVo);
    List<Map<String, Object>> selectCmpTabColList(Map<String,String> params);

    void insertMetaTabCol(Map<String, Object> params);
    void insertCmpTabCol(Map<String,String> params);

    void deleteMetaTabCol(Map<String,String> params);

    /**********************************************************************************
    *
    *                                   MetaTab
    *
    **********************************************************************************/

    int selectMetaTabCnt(Map<String,Object> params);

    //net.pmosoft.pony.dams.table.dynamic.TabMariadbDao 에서 처리
    //List<Map<String, Object>> selectMetaTabList(Map<String,String> params);
    void insertMetaTab(Map<String,Object> params);

    void deleteMetaTab(Map<String,String> params);
    void insertCmpTab(Map<String,String> params);

    /**********************************************************************************
     *
     *                                  TabCol
     *
     **********************************************************************************/
    List<Map<String, Object>> selectTabColList(Map<String,String> params);
    int selectTabColCnt(Map<String,String> params);
    void insertTabCol(Map<String,String> params);
    void updateTabCol(Map<String,String> params);
    void deleteTabCol(Map<String, String> params);

	/**********************************************************************************
	 *
	 *                                  Tab
	 *
	 **********************************************************************************/
	List<Map<String, Object>> selectTabList(Map<String,String> params);
	int selectTabCnt(Map<String,String> params);
	void insertTab(Map<String,String> params);
	void updateTab(Map<String,String> params);
    void deleteTab(List<Map<String, String>> params);


}

