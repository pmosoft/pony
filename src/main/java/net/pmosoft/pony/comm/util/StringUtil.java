package net.pmosoft.pony.comm.util;

public class StringUtil {

    /*
     *  토큰을 첫문자대문자 Camel로 변환
     * */
    public static String tokenToUCamel(String str, String delimeter) {

        //String str = "aa-bb-cc";
        String[] array = str.split(delimeter);
        //System.out.println(array.length);
        String firstUpperCamel = "";
        for (int i = 0; i < array.length; i++) {
            //System.out.println(array[i]);
            //System.out.println(StringUtil.replaceFirstCharUpperCase(array[i]));
            firstUpperCamel += StringUtil.replaceFirstCharUpperCase(array[i]);
        }
        return firstUpperCamel;
    }

    /*
     *  토큰을 첫문자소문자 Camel로 변환
     * */
    public static String tokenToLCamel(String str, String delimeter) {
        //String str = "aa-bb-cc";
        String[] array = str.split(delimeter);
        //System.out.println(array.length);
        String firstLowerCamel = "";
        for (int i = 0; i < array.length; i++) {
            //System.out.println(array[i]);
            //System.out.println(StringUtil.replaceFirstCharUpperCase(array[i]));
            if(i==0) firstLowerCamel += StringUtil.replaceFirstCharLowerCase(array[i]);
            else firstLowerCamel += StringUtil.replaceFirstCharUpperCase(array[i]);
        }
        //System.out.println(firstLowerCamel);
        return firstLowerCamel;
    }


    /**
     * 특정 문자열을 첫문자를 대문자로 대체<br>
     */
    public static String replaceFirstCharUpperCase(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
    }

    /**
     * 특정 문자열을 첫문자를 소문자로 대체<br>
     */
    public static String replaceFirstCharLowerCase(String str){
        return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
    }


    /**
     * 배열을 받아 연결될 문자열로 연결한다 이때 각 엘레먼트 사이에 구분문자열을 추가한다.
     */
    public static String StringJoin(Object aStr[], String padStr){
    	StringBuffer strBuff = new StringBuffer();

    	int i = aStr.length;
    	if(i > 0){
    		strBuff.append(aStr[0]);
    	}
    	for(int j=1; j < i; j++){
    		strBuff.append(padStr);
    		strBuff.append(aStr[j].toString());
    	}
    	return strBuff.toString();
    }

}
