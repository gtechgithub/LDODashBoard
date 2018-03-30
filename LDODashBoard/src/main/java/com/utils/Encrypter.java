package com.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;


public class Encrypter {
	
    private byte key[];

    public Encrypter(String s) throws Exception
    {
            key = base64ToByte(s);
    }

    /** this function will convert string to byte in base 64 **/
    private static byte[] base64ToByte(String s) throws Exception
     {
            return Base64.decodeBase64(s);
     }

    /** this function will convert byte to the string **/
     private static String byteToBase64(byte byte0[])
     {
            return Base64.encodeBase64String(byte0).trim();
     }

     /** this function will descript the given string **/
	public String decrypt(String s) throws Exception {
		try {
			SecretKeySpec secretkeyspec = new SecretKeySpec(key, "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, secretkeyspec);
			
			byte byte0[] = cipher.doFinal(base64ToByte(s));
			String s1 = new String(byte0, "UTF-8");
			return s1;
			
		} catch (Exception exception) {
			throw new Exception("Failed to decrypt string", exception);
		}
	}
        
}
