package com.qipingli;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

/**
 * Hello world!
 *
 */
public class JBlowfish 
{
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private String key;
    private String iv;
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    

    
    @SuppressWarnings("restriction")
    public String encode(String clearText, String mode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        byte[] key  = this.key.getBytes();
        byte[] iv = this.iv.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(key, "Blowfish"); 
        Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding"); 
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, new javax.crypto.spec.IvParameterSpec(iv)); 
        byte[] encoding = cipher.doFinal(clearText.getBytes());
        if ("HEX".equals(mode)){
            return bytesToHex(encoding);
        }else{
            return DatatypeConverter.printBase64Binary(encoding);
        }
        
    }
    
    @SuppressWarnings("restriction")
    public String decode(String secretText, String mode) throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        byte[] key  = this.key.getBytes();
        byte[] iv = this.iv.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(key, "Blowfish"); 
        Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding"); 
        byte[] ciphertext = DatatypeConverter.parseBase64Binary(secretText);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, new javax.crypto.spec.IvParameterSpec(iv));
        byte[] clearText = cipher.doFinal(ciphertext);
        if ("HEX".equals(mode)){
            return bytesToHex(clearText);
        }else{
            return new String(clearText);
        }
                
    }
    
    
    public static void main( String[] args )
    {
        JBlowfish jb = new JBlowfish();
        jb.setKey("secret");
        jb.setIv("12345678");
        
        try {
            String encodeRes = jb.encode("helloworld", "Base64");
            System.out.println( "encode result:"+ encodeRes);
            String decodeRes = jb.decode(encodeRes, "Base64");
            System.out.println( "decode result:"+ decodeRes);           
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println( "Blowfish demo closed" );
    }

}
