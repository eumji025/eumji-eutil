package com.eumji.secure.basse64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TestBase64JDK8 {
	public static void main(String[] args) {
		String base64Result = "MDEyMzQ1Njc4Oe+8jDAxMjM0NTY3ODnvvIwwMTIzNDU2Nzg577yMMDEyMzQ1Njc4Oe+8jDAxMjM0\n" +
				"NTY3ODnvvIwwMTIzNDU2Nzg577yMMDEyMzQ1Njc4OQ==";
		byte[] decode = Base64.getMimeDecoder().decode(base64Result);
		System.out.println(new String(decode, StandardCharsets.UTF_8));

	}
}
