package net.pmosoft.pony.etl.vo;

import net.pmosoft.pony.dams.jdbc.JdbcInfo;
import net.pmosoft.pony.dams.table.TabInfo;

public class DbToDbVo {
    
    public JdbcInfo asJdbcInfo = new JdbcInfo();
    public JdbcInfo toJdbcInfo = new JdbcInfo();

    public TabInfo asTabInfo = new TabInfo();
    public TabInfo toTabInfo = new TabInfo();
    
}