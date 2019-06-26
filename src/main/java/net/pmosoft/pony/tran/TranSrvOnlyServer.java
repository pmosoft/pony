package net.pmosoft.pony.tran;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.pmosoft.pony.comm.util.FileUtil;
import net.pmosoft.pony.comm.util.StringUtil;

public class TranSrvOnlyServer {
	private static Logger loger = LoggerFactory.getLogger(TranSrvOnlyServer.class);
	static String pathFileNm = "C:..../pony/src/net/pmosoft/pony/tran/tran.dat";

	public static void main(String[] args) {
		tranBarToCamel(pathFileNm);
	}

	/**
	* Bar를 Camel로 변환
	*/
	public static void tranBarToCamel(String pathFileNm){
		ArrayList<String> srcList = FileUtil.fileToList(pathFileNm);
		for(int i = 0; i < srcList.size(); i++) {
			System.out.println(StringUtil.tokenToLCamel(srcList.get(i).toLowerCase(),"_"));
		}
	}
}
