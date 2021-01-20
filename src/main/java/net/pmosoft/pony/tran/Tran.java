package net.pmosoft.pony.tran;

public class Tran {

    String delimiter   ; //
    String strToken    ; //
    String strRows     ; //
    int tokenCnt    ; //
    int delimiterCnt    ; //

    String retMsg             = "";
    String retErrMsg          = "";

    public String getdelimiter() {
        return delimiter;
    }
    public void setdelimiter(String delimiter) {
        this.delimiter = delimiter;
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
    public int getdelimiterCnt() {
        return delimiterCnt;
    }
    public void setdelimiterCnt(int delimiterCnt) {
        this.delimiterCnt = delimiterCnt;
    }

}
