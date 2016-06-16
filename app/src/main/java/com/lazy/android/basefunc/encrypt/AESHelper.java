package com.lazy.android.basefunc.encrypt;

import com.lazy.android.basefunc.LZUtils.UtilsStringNum;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



/**
 * AES加密、解密。为保证平台兼容性，使用 AES/ECB/PKCS7Padding 模式
 * 私钥   AES固定格式为128/192/256 bits.即：16/24/32bytes。DES固定格式为128bits，即8bytes。
 * 
 * @author gavin
 * 
 */
public class AESHelper {
	/**
	 * aes加密
	 * @param data 要加密的串
	 * @param key  私钥  例如 key="0123456789abcdef"
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, String key) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, keyspec);
			byte[] encrypted = cipher.doFinal(data.getBytes());
			return UtilsStringNum.byte2HexStr(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String desEncrypt(String data, String key) {
		try {
			byte[] encrypted = UtilsStringNum.hexStr2Bytes(data);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			cipher.init(Cipher.DECRYPT_MODE, keyspec);
			byte[] original = cipher.doFinal(encrypted);
			return new String(original, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String args[]) {
		String key = "A0d8E1tm2Y&rQ*i5";
		String str = "777";
		String enry = encrypt(str, key);
		System.out.println(enry);
	}
}
