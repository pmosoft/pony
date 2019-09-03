package net.pmosoft.pony.gens.kbcard.lenqry;

import java.util.ArrayList;

import net.pmosoft.pony.comm.util.FileUtil;

public class LengthQuery {
 
    
    public static void main(String[] args) {
        LengthQuery gen = new LengthQuery();
        gen.execute();
    }
    
    void execute(){
        FileUtil fileUtil = new FileUtil();
        ArrayList<String> src = fileUtil.fileToList("C:/../LengthQuery.dat");
        String colNm = "";
        String qry1 = "";
        
        for (int i = 0; i < src.size(); i++) {
            colNm = src.get(i);
            qry1 += "SELECT TAB_NM, COL_NM FROM TDACM00080 WHERE COL+NM LIKE '%"+colNm+"' UNION ALL \n";
        }
        
        System.out.println(qry1);
    }
    
}