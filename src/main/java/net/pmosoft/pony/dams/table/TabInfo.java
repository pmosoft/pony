package net.pmosoft.pony.dams.table;

import net.pmosoft.pony.dams.jdbc.JdbcInfo;

public class TabInfo {

    String stsNm        ;
    String jdbcNm       ;
    String owner        ;
    String tabNm        ;
    String tabHnm       ;
    int    colId        ;
    String colNm        ;
    String colHnm       ;
    String dataTypeDesc ;
    String nullable     ;
    String pk           ;
    String dataTypeNm   ;
    int    len          ;
    int    decimalCnt   ;
    long   tabRows      ;
    String tabRegDt     ;
    String tabRegDt2    ;
    String tabUpdDt     ;
    String tabUpdDt2    ;
    String regDtm       ;
    String regUsrId     ;
    String updDtm       ;
    String updUsrId     ;

    boolean chk;
    String orderBy;
    String ascDesc;

    boolean chkWhere    ;
    String txtWhere     ;
    boolean chkSelect   ;
    String txtSelect    ;

    String whereColTab  ;

    String qry          ;


    JdbcInfo jdbcInfo = new JdbcInfo();

    public String getStsNm() {
        return stsNm;
    }
    public void setStsNm(String stsNm) {
        this.stsNm = stsNm;
    }
    public String getJdbcNm() {
        return jdbcNm;
    }
    public void setJdbcNm(String jdbcNm) {
        this.jdbcNm = jdbcNm;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getTabNm() {
        return tabNm;
    }
    public void setTabNm(String tabNm) {
        this.tabNm = tabNm;
    }
    public String getTabHnm() {
        return tabHnm;
    }
    public void setTabHnm(String tabHnm) {
        this.tabHnm = tabHnm;
    }
    public int getColId() {
        return colId;
    }
    public void setColId(int colId) {
        this.colId = colId;
    }
    public String getColNm() {
        return colNm;
    }
    public void setColNm(String colNm) {
        this.colNm = colNm;
    }
    public String getColHnm() {
        return colHnm;
    }
    public void setColHnm(String colHnm) {
        this.colHnm = colHnm;
    }
    public String getDataTypeDesc() {
        return dataTypeDesc;
    }
    public void setDataTypeDesc(String dataTypeDesc) {
        this.dataTypeDesc = dataTypeDesc;
    }
    public String getNullable() {
        return nullable;
    }
    public void setNullable(String nullable) {
        this.nullable = nullable;
    }
    public String getPk() {
        return pk;
    }
    public void setPk(String pk) {
        this.pk = pk;
    }
    public String getDataTypeNm() {
        return dataTypeNm;
    }
    public void setDataTypeNm(String dataTypeNm) {
        this.dataTypeNm = dataTypeNm;
    }
    public int getLen() {
        return len;
    }
    public void setLen(int len) {
        this.len = len;
    }
    public int getDecimalCnt() {
        return decimalCnt;
    }
    public void setDecimalCnt(int decimalCnt) {
        this.decimalCnt = decimalCnt;
    }
    public long getTabRows() {
        return tabRows;
    }
    public void setTabRows(long tabRows) {
        this.tabRows = tabRows;
    }
    public String getTabRegDt() {
        return tabRegDt;
    }
    public void setTabRegDt(String tabRegDt) {
        this.tabRegDt = tabRegDt;
    }
    public String getTabRegDt2() {
        return tabRegDt2;
    }
    public void setTabRegDt2(String tabRegDt2) {
        this.tabRegDt2 = tabRegDt2;
    }
    public String getTabUpdDt() {
        return tabUpdDt;
    }
    public void setTabUpdDt(String tabUpdDt) {
        this.tabUpdDt = tabUpdDt;
    }
    public String getTabUpdDt2() {
        return tabUpdDt2;
    }
    public void setTabUpdDt2(String tabUpdDt2) {
        this.tabUpdDt2 = tabUpdDt2;
    }
    public String getRegDtm() {
        return regDtm;
    }
    public void setRegDtm(String regDtm) {
        this.regDtm = regDtm;
    }
    public String getRegUsrId() {
        return regUsrId;
    }
    public void setRegUsrId(String regUsrId) {
        this.regUsrId = regUsrId;
    }
    public String getUpdDtm() {
        return updDtm;
    }
    public void setUpdDtm(String updDtm) {
        this.updDtm = updDtm;
    }
    public String getUpdUsrId() {
        return updUsrId;
    }
    public void setUpdUsrId(String updUsrId) {
        this.updUsrId = updUsrId;
    }
    public String getOrderBy() {
        return orderBy;
    }
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    public String getAscDesc() {
        return ascDesc;
    }
    public void setAscDesc(String ascDesc) {
        this.ascDesc = ascDesc;
    }
    public boolean isChk() {
        return chk;
    }
    public void setChk(boolean chk) {
        this.chk = chk;
    }
    public JdbcInfo getJdbcInfo() {
        return jdbcInfo;
    }
    public void setJdbcInfo(JdbcInfo jdbcInfo) {
        this.jdbcInfo = jdbcInfo;
    }
    public boolean isChkWhere() {
        return chkWhere;
    }
    public void setChkWhere(boolean chkWhere) {
        this.chkWhere = chkWhere;
    }
    public String getTxtWhere() {
        return txtWhere;
    }
    public void setTxtWhere(String txtWhere) {
        this.txtWhere = txtWhere;
    }
    public boolean isChkSelect() {
        return chkSelect;
    }
    public void setChkSelect(boolean chkSelect) {
        this.chkSelect = chkSelect;
    }
    public String getTxtSelect() {
        return txtSelect;
    }
    public void setTxtSelect(String txtSelect) {
        this.txtSelect = txtSelect;
    }
    public String getQry() {
        return qry;
    }
    public void setQry(String qry) {
        this.qry = qry;
    }
    public String getWhereColTab() {
        return whereColTab;
    }
    public void setWhereColTab(String whereColTab) {
        this.whereColTab = whereColTab;
    }

}
