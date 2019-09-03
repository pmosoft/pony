package net.pmosoft.pony.tran.kbcard.biz;

public class BizServiceVo {
    
    public int    순번;
    public String 화면번호AS;
    public String 화면번호TO;
    public String 거래코드AS;
    public String 거래코드TO;
    public String 서비스AS;
    public String 서비스TO;
    public String 서비스패키지AS;
    public String 서비스그룹;
    
    BizServiceVo (
            int    순번,
            String 화면번호AS,
            String 화면번호TO,
            String 거래코드AS,
            String 거래코드TO,
            String 서비스AS,
            String 서비스TO,
            String 서비스패키지AS,
            String 서비스그룹
            ){
        this.순번          =   순번;       
        this.화면번호AS   = 화면번호AS;   
        this.화면번호TO   = 화면번호TO;  
        this.거래코드AS   = 거래코드AS;   
        this.거래코드TO   = 거래코드TO;   
        this.서비스AS    = 서비스AS;    
        this.서비스TO    = 서비스TO;    
        this.서비스패키지AS = 서비스패키지AS; 
        this.서비스그룹    =  서비스그룹;       
        
    }
    
    
    public int get순번() {
        return 순번;
    }
    public void set순번(int 순번) {
        this.순번 = 순번;
    }
    public String get화면번호AS() {
        return 화면번호AS;
    }
    public void set화면번호AS(String 화면번호as) {
        화면번호AS = 화면번호as;
    }
    public String get화면번호TO() {
        return 화면번호TO;
    }
    public void set화면번호TO(String 화면번호to) {
        화면번호TO = 화면번호to;
    }
    public String get거래코드AS() {
        return 거래코드AS;
    }
    public void set거래코드AS(String 거래코드as) {
        거래코드AS = 거래코드as;
    }
    public String get거래코드TO() {
        return 거래코드TO;
    }
    public void set거래코드TO(String 거래코드to) {
        거래코드TO = 거래코드to;
    }
    public String get서비스AS() {
        return 서비스AS;
    }
    public void set서비스AS(String 서비스as) {
        서비스AS = 서비스as;
    }
    public String get서비스TO() {
        return 서비스TO;
    }
    public void set서비스TO(String 서비스to) {
        서비스TO = 서비스to;
    }
    public String get서비스패키지AS() {
        return 서비스패키지AS;
    }
    public void set서비스패키지AS(String 서비스패키지as) {
        서비스패키지AS = 서비스패키지as;
    }
    public String get서비스그룹() {
        return 서비스그룹;
    }
    public void set서비스그룹(String 서비스그룹) {
        this.서비스그룹 = 서비스그룹;
    }
    
}