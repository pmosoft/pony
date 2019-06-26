package com.javalec.security.seed;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;


public class Base64Test {

    public static void main(String[] args)  {
    	base64();
    }

    public static void base64() {
		String text = "ktko";
        byte[] targetBytes = text.getBytes();
        
        // Base64 인코딩 ///////////////////////////////////////////////////
        Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode(targetBytes);
        
        // Base64 디코딩 ///////////////////////////////////////////////////
        Decoder decoder = Base64.getDecoder();
        byte[] decodedBytes = decoder.decode(encodedBytes);
        
		System.out.println("인코딩 전 : " + text);
		System.out.println("인코딩 text : " + new String(encodedBytes));
		System.out.println("디코딩 text : " + new String(decodedBytes));
	}
}
