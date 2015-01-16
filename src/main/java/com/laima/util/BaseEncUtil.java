package com.laima.util;

import org.apache.commons.codec.binary.Base64;

import com.laima.exception.DESException;

public class BaseEncUtil {
	
	private CryptorUtil cryptorUtil;

	public String disp_encode(String org) {
		String desStr = null;
		try {
			byte[] desBytes = cryptorUtil.RC4Crypt(org.getBytes("UTF-8"));
			desStr = Base64.encodeBase64URLSafeString(desBytes);
		} catch (Exception e) {
			throw new DESException("DES encode error !", e);
		}
		return desStr;
	}

	public String disp_decode(String org) {
		String desStr = null;
		try {
			byte[] desBytes = Base64.decodeBase64(org);
			desStr = new String(cryptorUtil.RC4Crypt(desBytes), "UTF-8");
		} catch (Exception e) {
			throw new DESException("DES decode error !", e);
		}
		return desStr;
	}
	
	public CryptorUtil getCryptorUtil() {
		return cryptorUtil;
	}

	public void setCryptorUtil(CryptorUtil cryptorUtil) {
		this.cryptorUtil = cryptorUtil;
	}
}
