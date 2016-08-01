package com.senseinfosys.pubsense.gateway.infrastructure.id;

import java.security.SecureRandom;

public class SecretFactory {

	public static final String getSecret(int length) {
		if (length < 0) {
			throw new IllegalArgumentException("Negative length: " + length);
		}
		char[] pa = new char[length];

		for (int i = 0; i < length; i++) {
			int d = RANDOM.nextInt(3);
			if (d == 0) {
				pa[i] = LOWERCASES[RANDOM.nextInt(26)];
			} else if (d == 1) {
				pa[i] = UPPERCASES[RANDOM.nextInt(26)];
			} else if (d == 2) {
				pa[i] = DIGITS[RANDOM.nextInt(10)];
			}
		}
		return new String(pa);
	}

	private static char[] createCharArray(int start, int end) {
		char[] ar = new char[end - start + 1];
		for (int i = 0; i < end - start + 1; i++) {
			ar[i] = (char) (start + i);
		}
		return ar;
	}

	private static final char[] LOWERCASES = createCharArray('a', 'z');
	private static final char[] UPPERCASES = createCharArray('A', 'Z');
	private static final char[] DIGITS = createCharArray('0', '9');
	private static final SecureRandom RANDOM = new SecureRandom();
}
