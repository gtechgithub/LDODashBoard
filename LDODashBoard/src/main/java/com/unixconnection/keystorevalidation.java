package com.unixconnection;


import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;

/****
 *  command to generate the jks from the trust certificates
 * 
M:\>keytool -import -trustcacerts -alias gcothak2_alias -file m:\prod_csi_ca.pem.cer -keystore m:\gcothak2keystore.jks -storepass password

****/


public class keystorevalidation {

  public static void main(String[] args) throws Exception {
    String keystoreFilename = "m:\\gcothak2keystore.jks";

    char[] password = "password".toCharArray();
    String alias = "gcothak2_alias";

    FileInputStream fIn = new FileInputStream(keystoreFilename);
    KeyStore keystore = KeyStore.getInstance("JKS");

    keystore.load(fIn, password);

    Certificate cert = keystore.getCertificate(alias);

    System.out.println(cert);
  }
}