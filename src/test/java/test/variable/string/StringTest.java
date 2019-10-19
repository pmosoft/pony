package test.variable.string;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.pmosoft.pony.comm.util.StringUtil;

public class StringTest
{

    public static void main(String[] args)throws Exception
    {
        //test01();
        //끝문자제거();
        //구분자배열();
        //정규식02();
        Contain();
        //Cast01();
    }

    static void Cast01() {

        String str = "1.0";
        //String str = "1";
        System.out.println(Math.round(Float.parseFloat(str)));
    }


    static void Base64() {

        String text = "ktko";
        byte[] targetBytes = text.getBytes();

        Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode(targetBytes);

        Decoder decoder = Base64.getDecoder();
        byte[] decodedBytes = decoder.decode(encodedBytes);

        System.out.println("인코딩 전 : " + text);
        System.out.println("인코딩 text : " + new String(encodedBytes));
        System.out.println("디코딩 text : " + new String(decodedBytes));
    }

    static void Base64_2() {
        String text = "ktko";

        //byte[] encodedBytes = Base64.encodeBase64(text.getBytes());

        //byte[] decodedBytes = Base64.decodeBase64(encodedBytes);

        //System.out.println("인코딩 전 : " + text);
        //System.out.println("인코딩 text : " + new String(encodedBytes));
        //System.out.println("디코딩 text : " + new String(decodedBytes));

    }


    static void Contain() {
        //matches("DATE|TIMESTAMP")
        String str = "integer";
        if(str.contains("int")) System.out.println("find"); else System.out.println("na");
        //if(str.contains("var2char|null")) System.out.println("find"); else System.out.println("na");
        //if(str.matches(".*(var2char|null).*")) System.out.println("find"); else System.out.println("na");
        //if(str.toUpperCase().matches(".*(NUMBER|INT|NUMERIC).*")) System.out.println("find"); else System.out.println("na");
        //if(str.toUpperCase().matches("NUMBER|INT|NUMERIC")) System.out.println("find"); else System.out.println("na");

    }

    static void 정규식02() {
        Pattern p; Matcher m;
        String str, pStr;
        str  = ".00223.0039.00151.0091";
        //pStr = "([^.]{3})";
        pStr = "([^.]{3}(\\.))";
        //pStr = "([^.]{3}(\\.|$))";
        //pStr = "([^.]{3}(\\.|$))|.";
        p = Pattern.compile(pStr); m = p.matcher(str);
        while(m.find()) System.out.println(m.group());
    }

    static void 정규식01() {
        Pattern p; Matcher m;
        String str, pStr;
        str  = "001";
        pStr = "(^[0-9]*$)";
        p = Pattern.compile(pStr); m = p.matcher(str);
        while(m.find()) System.out.println(m.group());
    }

    static void 구분자배열() {
        String str = "aa-bb-cc";
        String[] array = str.split("\\-");
        System.out.println(array.length);
        String firstLowerCamel = "";
        String firstUpperCamel = "";
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
            System.out.println(StringUtil.replaceFirstCharUpperCase(array[i]));
            firstUpperCamel += StringUtil.replaceFirstCharUpperCase(array[i]);
            if(i==0) firstLowerCamel += StringUtil.replaceFirstCharLowerCase(array[i]);
            else firstLowerCamel += StringUtil.replaceFirstCharUpperCase(array[i]);
        }
        System.out.println(firstUpperCamel);
        System.out.println(firstLowerCamel);
        System.out.println(StringUtil.tokenToLCamel(str,"-"));
    }

    static void 끝문자제거() {
        String r01 = "aa/aa/";
        if(r01.endsWith("/")) System.out.println(r01.substring(0,r01.length()-1));
    }

    static void test01() {

//      char a=','; //char�� �̱�(')
//      char b='' ;
//      String src ="15,000,000";
//      String src1 = ",";
//      String src2 = "";
//      String src3 = "7ABCD1";

//    String aaa = "20031002702����                          000000000622620001                                         000000000144140000000013068040000000000000000000000000144140000000000000000000000000000000��������������μ������Ρ�����                                                        0000000700000000007431  0000000000000000";

//    System.out.println(aaa.length());

//      byte[] ba;
//      ba = (aaa.getBytes());

//    System.out.println(ba.length);

//    SET @��ÿ�������  = (SELECT ��ÿ�������  FROM TB_IQF_����_������ WHERE �������� = DATEFORMAT(GETDATE() ,'yyyymmdd'));
//    SET @�Ϳ�������    = (SELECT �Ϳ�������    FROM TB_IQF_����_������ WHERE �������� = DATEFORMAT(GETDATE() ,'yyyymmdd'));

      String src="WHERE �������� = DATEFORMAT(GETDATE() ,'yyyymmdd'))";
      //System.out.println(src);
      //src.substring(0,7

      src = src.replaceAll("DATEFORMAT\\(GETDATE\\(\\) ,'yyyymmdd'\\)","'20061120'");
      System.out.println(src);

      System.out.println ( System.currentTimeMillis ( ) );

      //System.out.println(src.indexOf("getdddate"));
//      String src1="��ȭ��� ���� ���к����� ����� �����մϴ�.";
//      int src_len = src.length();
//      String src_len1 = src_len + "";
//      String src_len2 = Integer.toString(src_len);
//      if(src_len > 14)
//      {
//          src_len1 = src.substring(0,15);
//          src_len1 = src_len1 + "...";
//      }
//      System.out.println(src_len);
//
//
//      String src_len3 = Integer.toString(src_len);
//      String src_len4 = Integer.toString( Integer.parseInt(src_len3)- 1 );
//      System.out.println(src_len4);
//
//      //   molding_nm  = (molding_nm == null ? "&nbsp;" : encode.br(molding_nm));
//    src = src.substring(src.indexOf("'")+1,src.length());


//    char a='a'; //char�� �̱�(')
//    char b='b';
//    String src1 ="'";
//    String src2 = "''";
//    System.System.out.printlnln("src1=" + src1);
//    System.System.out.printlnln("src1=" + src1.charAt(0));
//    System.System.out.printlnln("src2=" + src2);
//    System.System.out.printlnln("src2=" + src2.charAt(0));
//    src = src.replace(src1.charAt(0),src2.charAt(0));
//    System.System.out.printlnln(src);

    }

}

