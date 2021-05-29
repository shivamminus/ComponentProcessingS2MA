package com.componentxProcessing.main.util;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

public class Utilities {

	public static String getAlphaNumericString(int n) {

		byte[] array = new byte[256];
		new Random().nextBytes(array);

		String randomString = new String(array, Charset.forName("UTF-8"));
		StringBuffer r = new StringBuffer();

		for (int k = 0; k < randomString.length(); k++) {

			char ch = randomString.charAt(k);

			if (((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) && (n > 0)) {

				r.append(ch);
				n--;
			}
		}
		return r.toString();
	}

	/*
	 * It will return random id from UUID library
	 */
	public static String generateRequestId() {

		return UUID.randomUUID().toString();

	}

}
