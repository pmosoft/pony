package net.pmosoft.pony.tran.kbcard.biz;

import java.util.ArrayList;

public class BizServiceList {
    
    public ArrayList<BizServiceVo> list = new ArrayList<BizServiceVo>();
    
    BizServiceList(){
        list.add(new BizServiceVo(1,"PDZ75001900","PDZ75001900","PDZ75001S0","PDZ75001S0","ASPDZ75001","ASPDZ75001","valueaddedtaxas.ASPDZ75001","valueaddedtaxto.ASPDZ75001"));
        list.add(new BizServiceVo(2,"PDZ75001900","PDZ75001900","PDZ75001S0","PDZ75001S0","ASPDZ75001","ASPDZ75001","valueaddedtaxas.ASPDZ75001","valueaddedtaxto.ASPDZ75001"));
    }
    
}