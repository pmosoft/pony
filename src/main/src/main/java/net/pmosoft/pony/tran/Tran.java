package net.pmosoft.pony.tran;

public class Tran {

    String delimeter   ; //
    String strToken    ; //
    String strRows     ; //
    int tokenCnt    ; //
    int delimeterCnt    ; //

    String retMsg             = "";
    String retErrMsg          = "";

    public String getDelimeter() {
        return delimeter;
    }
    public void setDelimeter(String delimeter) {
        this.delimeter = delimeter;
    }
    public String getStrToken() {
        return strToken;
    }
    public void setStrToken(String strToken) {
        this.strToken = strToken;
    }
    public String getStrRows() {
        return strRows;
    }
    public void setStrRows(String strRows) {
        this.strRows = strRows;
    }
    public int getTokenCnt() {
        return tokenCnt;
    }
    public void setTokenCnt(int tokenCnt) {
        this.tokenCnt = tokenCnt;
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
    public int getDelimeterCnt() {
        return delimeterCnt;
    }
    public void setDelimeterCnt(int delimeterCnt) {
        this.delimeterCnt = delimeterCnt;
    }

}
