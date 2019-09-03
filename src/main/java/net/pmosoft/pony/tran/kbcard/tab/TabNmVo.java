package net.pmosoft.pony.tran.kbcard.tab;

public class TabNmVo {
    
    public String asTabNm;
    public String toTabNm;
    
    
    TabNmVo (
            String asTabNm,
            String toTabNm
            ){
        this.asTabNm = asTabNm; 
        this.toTabNm = toTabNm;       
    }
    
    public String getAsTabNm() {
        return asTabNm;
    }
    public void setAsTabNm(String asTabNm) {
        this.asTabNm = asTabNm;
    }
    public String getToTabNm() {
        return toTabNm;
    }
    public void setToTabNm(String toTabNm) {
        this.toTabNm = toTabNm;
    }    
    
}