package com.laima.maimm.global.util;

public class CryptorUtil {
	private byte[] s = new byte[256]; // SBox
	private String key;

	/*
	 * RC4Init param keyString return void
	 */
	public CryptorUtil(String keyString) {
		int i = 0, j = 0;
		byte[] key = keyString.getBytes();
		byte[] k = new byte[256];
		byte tmp = 0;
		for (i = 0; i < 256; i++) {
			s[i] = (byte) i;
			k[i] = key[i % key.length];
		}

		for (i = 0; i < 256; i++) {
			j = (j + (char) s[i] + (char) k[i]) % 256;
			// System.out.printf("i = %d,  j = %d, s[i] = %d, k[i] = %d\n", i,
			// j, s[i], k[i]);
			tmp = s[i];
			s[i] = s[j]; // 交换s[i]和s[j]
			s[j] = tmp;
		}
	}

	/*
	 * RC4Crypt encryption and decryption use the same method param data : input
	 * data return output data
	 */
	public byte[] RC4Crypt(byte[] data) {
		int i = 0, j = 0, t = 0;
		int k = 0;
		byte tmp;
		byte s2[] = new byte[256];
		for (i = 0; i < 256; i++) {
			s2[i] = s[i];
		} // backup SBox
		for (k = 0; k < data.length; k++) {
			i = (i + 1) % 256;
			j = (j + (char) s2[i]) % 256;
			tmp = s2[i];
			s2[i] = s2[j]; // 交换s[x]和s[y]
			s2[j] = tmp;
			t = ((char) s2[i] + (char) s2[j]) % 256;
			data[k] ^= s2[t];

		}
		return data;
	}
	
	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
