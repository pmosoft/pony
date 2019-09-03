package net.pmosoft.pony.tran.kbcard.tab;

public class ColNmVo {
    
    public String asColNm;
    public String toColNm;
    
    
    ColNmVo (
            String asColNm,
            String toColNm
            ){
        this.asColNm = asColNm; 
        this.toColNm = toColNm;       
    }
    
    public String getAsColNm() {
        return asColNm;
    }
    public void setAsColNm(String asColNm) {
        this.asColNm = asColNm;
    }
    public String getToColNm() {
        return toColNm;
    }
    public void setToColNm(String toColNm) {
        this.toColNm = toColNm;
    }    
    
}