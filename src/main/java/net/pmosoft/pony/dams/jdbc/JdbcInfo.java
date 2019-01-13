package net.pmosoft.pony.dams.jdbc;

public class JdbcInfo {

    String jdbcNm  ;
    String driver  ;
    String url     ;
    String usrId   ;
    String usrPw   ;
    String regDtm  ;
    String regUsrId;
    String updDtm  ;
    String updUsrId;

    String id  ; // 콤보 ID
    String name; // 콤보 Name


    public String getJdbcNm() {
        return jdbcNm;
    }
    public void setJdbcNm(String jdbcNm) {
        this.jdbcNm = jdbcNm;
    }
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUsrId() {
        return usrId;
    }
    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }
    public String getUsrPw() {
        return usrPw;
    }
    public void setUsrPw(String usrPw) {
        this.usrPw = usrPw;
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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
