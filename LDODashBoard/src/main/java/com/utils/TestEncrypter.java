package com.utils;

import com.utils.Encrypter;

public class TestEncrypter {

	public static void main(String args[])
	{
		try {
			Encrypter enc = new Encrypter("DocklandsLightRailway*1");
			String descryptValue = enc.decrypt("");
			
			System.out.println("descryptValue:" + descryptValue );
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
