package net.pmosoft.pony.gens.pgm.copy;

import java.util.HashMap;
import java.util.Map;

import net.pmosoft.pony.comm.util.StringUtil;
import net.pmosoft.pony.gens.pgm.GensPgm;

public class GensSpringByCopy extends GensCommByCopy {

    public static void main(String[] args) throws Exception {
        GensSpringByCopy gensSpringByCopy = new GensSpringByCopy();

        GensPgm gens = new GensPgm();
        gens.setPkgComNm("net.pmosoft.pony");
        gens.setSrcPathNm("d:\\fframe\\workspace\\pony\\src\\main\\java\\net\\pmosoft\\pony\\dams\\code\\");
        gens.setSrcClassNm("Code");
        gens.setTarPathNm("d:\\fframe\\workspace\\pony\\src\\main\\java\\net\\pmosoft\\pony\\dams\\jdbc\\");
        gens.setTarClassNm("JdbcInfo");


        gensSpringByCopy.setGenParam(gens);
        gensSpringByCopy.genTarPgmFiles(gens);
    }

    public void execute(GensPgm gens) throws Exception {
        setGenParam(gens);
        genTarPgmFiles(gens);
    }
    /******************************************
     * 1단계 : 입력 param으로 gen시 필요param를 세팅
     *****************************************/
    public void setGenParam(GensPgm gens) throws Exception {

        try {

            //System.out.println("Gens="+gens);

            /******************************************
             * path의 \ ==> / 변경
             *****************************************/
            gens.setSrcPathNm(gens.getSrcPathNm().replace("\\","/"));
            gens.setTarPathNm(gens.getTarPathNm().replace("\\","/"));
            /******************************************
             * path의 끝문자 / 제거
             *****************************************/
            if(gens.getSrcPathNm().endsWith("/")) gens.setSrcPathNm(gens.getSrcPathNm().substring(0,gens.getSrcPathNm().length()-1));
            if(gens.getTarPathNm().endsWith("/")) gens.setTarPathNm(gens.getTarPathNm().substring(0,gens.getTarPathNm().length()-1));

            /******************************************
             * Method 형태의 첫소문자 Camel 문자로 변경
             *****************************************/
            gens.setSrcMethodNm(StringUtil.replaceFirstCharLowerCase(gens.getSrcClassNm()));
            gens.setTarMethodNm(StringUtil.replaceFirstCharLowerCase(gens.getTarClassNm()));

            /******************************************
             * 패키지명
             *****************************************/
            System.out.println("gens.getSrcPathNm()="+gens.getSrcPathNm());

            gens.setSlashComNm(gens.getPkgComNm().replace(".","/"));
            gens.setSrcPkgPathNm(gens.getSrcPathNm().substring(gens.getSrcPathNm().indexOf(gens.getSlashComNm()),gens.getSrcPathNm().length()));
            gens.setSrcPkgNm(gens.getSrcPkgPathNm().replace("/","."));
            gens.setSrcPkgPathAbbrNm(gens.getSrcPkgPathNm().substring(gens.getSlashComNm().length(),gens.getSrcPkgPathNm().length()));

            gens.setTarPkgPathNm(gens.getTarPathNm().substring(gens.getTarPathNm().indexOf(gens.getSlashComNm()),gens.getTarPathNm().length()));
            gens.setTarPkgNm(gens.getTarPkgPathNm().replace("/","."));
            gens.setTarPkgPathAbbrNm(gens.getTarPkgPathNm().substring(gens.getSlashComNm().length(),gens.getTarPkgPathNm().length()));


            System.out.println("gens.getSlashComNm()="+gens.getSlashComNm());
            System.out.println("gens.getSrcPkgPathNm()="+gens.getSrcPkgPathNm());
            System.out.println("gens.getSrcPkgNm()="+gens.getSrcPkgNm());
            System.out.println("gens.getSrcPkgPathAbbrNm()="+gens.getSrcPkgPathAbbrNm());

        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
    }

    /******************************************
     * 2단계 : 복사할 파일들 지정
     *****************************************/
    public void genTarPgmFiles(GensPgm gens) throws Exception{

        try
        {
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcClassNm()+".java"              ,gens.getTarPathNm(),gens.getTarClassNm()+".java"         ,gens);
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcClassNm()+"Ctrl.java"          ,gens.getTarPathNm(),gens.getTarClassNm()+"Ctrl.java"         ,gens);
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcClassNm()+"Srv.java"           ,gens.getTarPathNm(),gens.getTarClassNm()+"Srv.java"          ,gens);
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcClassNm()+"Dao.java"           ,gens.getTarPathNm(),gens.getTarClassNm()+"Dao.java"          ,gens);
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcClassNm()+"ValidatorSrv.java"  ,gens.getTarPathNm(),gens.getTarClassNm()+"ValidatorSrv.java" ,gens);
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcClassNm()+"MariadbDao.xml"     ,gens.getTarPathNm(),gens.getTarClassNm()+"MariadbDao.xml"    ,gens);
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcClassNm()+"SqliteDao.xml"      ,gens.getTarPathNm(),gens.getTarClassNm()+"SqliteDao.xml"     ,gens);
            replaceSrcPgmToTarPgm(gens.getSrcPathNm(),gens.getSrcClassNm()+"SqliteDao.sql"      ,gens.getTarPathNm(),gens.getTarClassNm()+"SqliteDao.sql"     ,gens);

            System.out.println("msg="+gens.getRetSrcMsg());
            System.out.println("msg="+gens.getRetTarMsg());
            String retMsg = "생성 프로그램";
            retMsg += gens.getRetTarMsg() +"\n\n";
            retMsg += "소스 프로그램";
            retMsg += gens.getRetSrcMsg() +"\n\n";
            retMsg += "예외";
            retMsg += gens.getRetErrMsg();

            gens.setRetMsg(retMsg);
            System.out.println("msg="+gens.getRetTarMsg());
            //System.out.println(retMsg);

        } catch (Exception e) {
            //retMsg  =  e.getMessage();
            throw e;
        }
    }

    /***********************************************
     * 4단계 : 리팩토링 룰
     ***********************************************/
    public String replaceRule(String line, GensPgm gens) {

        line = line.replace(gens.getSrcPkgNm(),gens.getTarPkgNm());
        line = line.replace(gens.getSrcPkgNm(),gens.getTarPkgNm());
        line = line.replace(gens.getSrcPkgPathAbbrNm(),gens.getTarPkgPathAbbrNm());

        line = line.replace(gens.getSrcClassNm() ,gens.getTarClassNm() );
        line = line.replace(gens.getSrcMethodNm(),gens.getTarMethodNm());


        return line;
    }


}
