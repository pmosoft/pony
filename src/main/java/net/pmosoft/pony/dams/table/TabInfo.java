package net.pmosoft.pony.dams.table;

public class TabInfo {

    String stsNm        ;
    String jdbcNm       ;
    String owner        ;
    String tabNm        ;
    String tabHnm       ;
    String colId        ;
    String colNm        ;
    String colHnm       ;
    String dataTypeDesc ;
    String nullable     ;
    String pk           ;
    String dataTypeNm   ;
    String len          ;
    String decimalCnt   ;
    String colDesc      ;
    String regDtm       ;

    // 조회조건
    String condJdbcNm; //[조회조건] jdbc명
    String condOwner ; //[조회조건] 소유자
    String condTabNm ; //[조회조건] 테이블명
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
    public String getColId() {
        return colId;
    }
    public void setColId(String colId) {
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
    public String getLen() {
        return len;
    }
    public void setLen(String len) {
        this.len = len;
    }
    public String getDecimalCnt() {
        return decimalCnt;
    }
    public void setDecimalCnt(String decimalCnt) {
        this.decimalCnt = decimalCnt;
    }
    public String getColDesc() {
        return colDesc;
    }
    public void setColDesc(String colDesc) {
        this.colDesc = colDesc;
    }
    public String getRegDtm() {
        return regDtm;
    }
    public void setRegDtm(String regDtm) {
        this.regDtm = regDtm;
    }
    public String getCondJdbcNm() {
        return condJdbcNm;
    }
    public void setCondJdbcNm(String condJdbcNm) {
        this.condJdbcNm = condJdbcNm;
    }
    public String getCondOwner() {
        return condOwner;
    }
    public void setCondOwner(String condOwner) {
        this.condOwner = condOwner;
    }
    public String getCondTabNm() {
        return condTabNm;
    }
    public void setCondTabNm(String condTabNm) {
        this.condTabNm = condTabNm;
    }


}
