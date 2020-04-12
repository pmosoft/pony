package net.pmosoft.pony.gens.pgm;

public class GensPgm {

    String pkgComNm        ; // 패키지 회사명                                 (예:net.pmosoft.pony)
    String slashComNm      ; // 슬래쉬 회사명                                 (예:net/pmosoft/pony)
    String srcPathNm       ; // 소스 경로명                                    (예:"d:/fframe/workspace/pony/src/main/java/net/pmosoft/pony/dams/code/");
    String srcPkgNm        ; // 소스  패키지명                                (예:net.pmosoft.pony.dams.code)
    String srcPkgPathNm    ; // 소스 슬래쉬 패키지명                       (예:net/pmosoft/pony/dams/code)
    String srcPkgPathAbbrNm; // 소스 슬래쉬 회사명이 배제된 패키지명(예:/dams/code)
    String srcBarNm        ; // 소스 구분자 - 형식명                      (예:code-list)
    String srcClassNm      ; // 소스 첫대문자 Camel명                  (예:CodeList)
    String srcMethodNm     ; // 소스 첫소문자 Camel명                  (예:codeList)
    String srcDotNm        ; // 소스 구분자 . 형식명                      (예:code.list)
    String srcTabNm        ; // 소스 구분자 _ 형식명                      (예:code_list)

    String srcTxt          ; // 소스 범용

    String tarPathNm       ;
    String tarPkgNm        ;
    String tarPkgPathNm    ;
    String tarPkgPathAbbrNm;
    String tarBarNm        ;
    String tarClassNm      ;
    String tarMethodNm     ;
    String tarDotNm        ;
    String tarTabNm        ;

    String tarTxt          ; // 타켓 범용


    String retMsg             = "";
    String retErrMsg          = "";
    String retSrcMsg          = "";
    String retTarMsg          = "";

    public String getPkgComNm() {
        return pkgComNm;
    }
    public void setPkgComNm(String pkgComNm) {
        this.pkgComNm = pkgComNm;
    }
    public String getSlashComNm() {
        return slashComNm;
    }
    public void setSlashComNm(String slashComNm) {
        this.slashComNm = slashComNm;
    }
    public String getSrcPathNm() {
        return srcPathNm;
    }
    public void setSrcPathNm(String srcPathNm) {
        this.srcPathNm = srcPathNm;
    }
    public String getSrcPkgNm() {
        return srcPkgNm;
    }
    public void setSrcPkgNm(String srcPkgNm) {
        this.srcPkgNm = srcPkgNm;
    }
    public String getSrcPkgPathNm() {
        return srcPkgPathNm;
    }
    public void setSrcPkgPathNm(String srcPkgPathNm) {
        this.srcPkgPathNm = srcPkgPathNm;
    }
    public String getSrcPkgPathAbbrNm() {
        return srcPkgPathAbbrNm;
    }
    public void setSrcPkgPathAbbrNm(String srcPkgPathAbbrNm) {
        this.srcPkgPathAbbrNm = srcPkgPathAbbrNm;
    }
    public String getSrcBarNm() {
        return srcBarNm;
    }
    public void setSrcBarNm(String srcBarNm) {
        this.srcBarNm = srcBarNm;
    }
    public String getSrcClassNm() {
        return srcClassNm;
    }
    public void setSrcClassNm(String srcClassNm) {
        this.srcClassNm = srcClassNm;
    }
    public String getSrcMethodNm() {
        return srcMethodNm;
    }
    public void setSrcMethodNm(String srcMethodNm) {
        this.srcMethodNm = srcMethodNm;
    }
    public String getSrcDotNm() {
        return srcDotNm;
    }
    public void setSrcDotNm(String srcDotNm) {
        this.srcDotNm = srcDotNm;
    }
    public String getSrcTabNm() {
        return srcTabNm;
    }
    public void setSrcTabNm(String srcTabNm) {
        this.srcTabNm = srcTabNm;
    }
    public String getTarPathNm() {
        return tarPathNm;
    }
    public void setTarPathNm(String tarPathNm) {
        this.tarPathNm = tarPathNm;
    }
    public String getTarPkgNm() {
        return tarPkgNm;
    }
    public void setTarPkgNm(String tarPkgNm) {
        this.tarPkgNm = tarPkgNm;
    }
    public String getTarPkgPathNm() {
        return tarPkgPathNm;
    }
    public void setTarPkgPathNm(String tarPkgPathNm) {
        this.tarPkgPathNm = tarPkgPathNm;
    }
    public String getTarPkgPathAbbrNm() {
        return tarPkgPathAbbrNm;
    }
    public void setTarPkgPathAbbrNm(String tarPkgPathAbbrNm) {
        this.tarPkgPathAbbrNm = tarPkgPathAbbrNm;
    }
    public String getTarBarNm() {
        return tarBarNm;
    }
    public void setTarBarNm(String tarBarNm) {
        this.tarBarNm = tarBarNm;
    }
    public String getTarClassNm() {
        return tarClassNm;
    }
    public void setTarClassNm(String tarClassNm) {
        this.tarClassNm = tarClassNm;
    }
    public String getTarMethodNm() {
        return tarMethodNm;
    }
    public void setTarMethodNm(String tarMethodNm) {
        this.tarMethodNm = tarMethodNm;
    }
    public String getTarDotNm() {
        return tarDotNm;
    }
    public void setTarDotNm(String tarDotNm) {
        this.tarDotNm = tarDotNm;
    }
    public String getTarTabNm() {
        return tarTabNm;
    }
    public void setTarTabNm(String tarTabNm) {
        this.tarTabNm = tarTabNm;
    }
    public String getRetMsg() {
        return retMsg;
    }
    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
    public String getRetErrMsg() {
        return retErrMsg;
    }
    public void setRetErrMsg(String retErrMsg) {
        this.retErrMsg = retErrMsg;
    }
    public String getRetSrcMsg() {
        return retSrcMsg;
    }
    public void setRetSrcMsg(String retSrcMsg) {
        this.retSrcMsg = retSrcMsg;
    }
    public String getRetTarMsg() {
        return retTarMsg;
    }
    public void setRetTarMsg(String retTarMsg) {
        this.retTarMsg = retTarMsg;
    }
    public String getSrcTxt() {
        return srcTxt;
    }
    public void setSrcTxt(String srcTxt) {
        this.srcTxt = srcTxt;
    }
    public String getTarTxt() {
        return tarTxt;
    }
    public void setTarTxt(String tarTxt) {
        this.tarTxt = tarTxt;
    }
}
