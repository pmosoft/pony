package net.pmosoft.pony.etl.extract;

public class EtlVo {
    
    public String asTabNm;
    public String asColId;
    public String asColNm;
    public String asDataTypeDesc;
    public String toTabNm;
    public String toColId;
    public String toColNm;
    public String toDataTypeDesc;
    
    EtlVo(
          String asTabNm
        , String asColId
        , String asColNm
        , String asDataTypeDesc
        , String toTabNm
        , String toColId
        , String toColNm
        , String toDataTypeDesc
        ) {
          this.asTabNm        = asTabNm       ;
          this.asColId        = asColId       ;
          this.asColNm        = asColNm       ;
          this.asDataTypeDesc = asDataTypeDesc;
          this.toTabNm        = toTabNm       ;
          this.toColId        = toColId       ;
          this.toColNm        = toColNm       ;
          this.toDataTypeDesc = toDataTypeDesc;
    }
    
    public String getAsTabNm() {
        return asTabNm;
    }
    public void setAsTabNm(String asTabNm) {
        this.asTabNm = asTabNm;
    }
    public String getAsColId() {
        return asColId;
    }
    public void setAsColId(String asColId) {
        this.asColId = asColId;
    }
    public String getAsColNm() {
        return asColNm;
    }
    public void setAsColNm(String asColNm) {
        this.asColNm = asColNm;
    }
    public String getAsDataTypeDesc() {
        return asDataTypeDesc;
    }
    public void setAsDataTypeDesc(String asDataTypeDesc) {
        this.asDataTypeDesc = asDataTypeDesc;
    }
    public String getToTabNm() {
        return toTabNm;
    }
    public void setToTabNm(String toTabNm) {
        this.toTabNm = toTabNm;
    }
    public String getToColId() {
        return toColId;
    }
    public void setToColId(String toColId) {
        this.toColId = toColId;
    }
    public String getToColNm() {
        return toColNm;
    }
    public void setToColNm(String toColNm) {
        this.toColNm = toColNm;
    }
    public String getToDataTypeDesc() {
        return toDataTypeDesc;
    }
    public void setToDataTypeDesc(String toDataTypeDesc) {
        this.toDataTypeDesc = toDataTypeDesc;
    }
    
    
}