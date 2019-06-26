package com.javalec.security.seed;

import org.apache.commons.codec.binary.Base64;

public class Base64Test2 {
	public static void main(String args[]) {
		base64();
	}

	public static void base64() {
		String text = "ktko";

		/* base64 encoding */
		byte[] encodedBytes = Base64.encodeBase64(text.getBytes());

		/* base64 decoding */
		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);

		System.out.println("인코딩 전 : " + text);
		System.out.println("인코딩 text : " + new String(encodedBytes));
		System.out.println("디코딩 text : " + new String(decodedBytes));
	}
}
