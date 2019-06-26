package net.pmosoft.pony.dams.table;

import java.util.ArrayList;

public class TabInfoData {

	public ArrayList<TabNm> tabList = new ArrayList<TabNm>();

	public TabInfoData(){

		tabList.add(new TabNm("aaa","테이블"));

	}

	public class TabNm {
		public String tabNm;
		public String tabHnm;

		public TabNm(String tabNm, String tabHnm){
			this.tabNm = tabNm;
			this.tabHnm = tabHnm;
		}
	}
}
