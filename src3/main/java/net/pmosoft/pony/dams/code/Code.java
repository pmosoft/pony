package net.pmosoft.pony.dams.code;

public class Code {

    String cdIdNm      ; // 코드아이디명    
    String cdIdHnm     ; // 코드아이디한글명
    String cdIdGrpNm   ; // 코드아이디그룹명
    String cd          ; // 코드          
    String cdNm        ; // 코드명        
    String cdHnm       ; // 코드한글명      
    String cdDesc      ; // 코드설명       
    String cdTyCd      ; // 코드유형코드 
    String cdStsCd     ; // 코드상태코드    
    String regDtm      ; // 등록일시       
    String regUsrId    ; // 등록자       
    String updDtm      ; // 변경일시       
    String updUsrId    ; // 변경자
    
    String searchValue ; // 조회조건:코드관련 

    public String getCdIdNm() {
        return cdIdNm;
    }

    public void setCdIdNm(String cdIdNm) {
        this.cdIdNm = cdIdNm;
    }

    public String getCdIdHnm() {
        return cdIdHnm;
    }

    public void setCdIdHnm(String cdIdHnm) {
        this.cdIdHnm = cdIdHnm;
    }

    public String getCdIdGrpNm() {
        return cdIdGrpNm;
    }

    public void setCdIdGrpNm(String cdIdGrpNm) {
        this.cdIdGrpNm = cdIdGrpNm;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getCdNm() {
        return cdNm;
    }

    public void setCdNm(String cdNm) {
        this.cdNm = cdNm;
    }

    public String getCdHnm() {
        return cdHnm;
    }

    public void setCdHnm(String cdHnm) {
        this.cdHnm = cdHnm;
    }

    public String getCdDesc() {
        return cdDesc;
    }

    public void setCdDesc(String cdDesc) {
        this.cdDesc = cdDesc;
    }

    public String getCdTyCd() {
        return cdTyCd;
    }

    public void setCdTyCd(String cdTyCd) {
        this.cdTyCd = cdTyCd;
    }

    public String getCdStsCd() {
        return cdStsCd;
    }

    public void setCdStsCd(String cdStsCd) {
        this.cdStsCd = cdStsCd;
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

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
    
}
