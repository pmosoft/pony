package net.pmosoft.pony.gens.teos;

public class SrcTabVo {
    
    public String pathFileNm;
    public String tabNm;
    
    SrcTabVo (
            String pathFileNm,
            String tabNm
            ){
        this.pathFileNm = pathFileNm;   
        this.tabNm      =  tabNm;       
    }

    public String getPathFileNm() {
        return pathFileNm;
    }

    public void setPathFileNm(String pathFileNm) {
        this.pathFileNm = pathFileNm;
    }

    public String getTabNm() {
        return tabNm;
    }

    public void setTabNm(String tabNm) {
        this.tabNm = tabNm;
    }
    
    
}